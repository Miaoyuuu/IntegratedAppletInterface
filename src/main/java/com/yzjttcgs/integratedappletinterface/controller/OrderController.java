package com.yzjttcgs.integratedappletinterface.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.yzjttcgs.integratedappletinterface.common.annotation.SignatureValidation;
import com.yzjttcgs.integratedappletinterface.common.constant.CommonConstants;
import com.yzjttcgs.integratedappletinterface.common.domain.R;
import com.yzjttcgs.integratedappletinterface.common.exception.ServiceException;
import com.yzjttcgs.integratedappletinterface.common.utils.HttpUtils;
import com.yzjttcgs.integratedappletinterface.common.utils.JieyiUtils;
import com.yzjttcgs.integratedappletinterface.domain.dto.OrderPayDto;
import com.yzjttcgs.integratedappletinterface.domain.dto.ParkPayNoticeDto;
import com.yzjttcgs.integratedappletinterface.domain.request.PayOrderNoticeRequest;
import com.yzjttcgs.integratedappletinterface.domain.request.PrePayRequest;
import com.yzjttcgs.integratedappletinterface.domain.request.QueryOrderDetailRequest;
import com.yzjttcgs.integratedappletinterface.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-19 15:25:51
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/detail")
    @SignatureValidation
    public R<Object> detail(@RequestBody @Valid QueryOrderDetailRequest request) {
        String carNo = request.getCarNo();
        log.info("订单查询:{}", carNo);
        Map<String, Object> param = BeanUtil.beanToMap(request);
        String post = HttpUtils.sendPost(CommonConstants.YXYZ_URL + "/jst-park-app/wxscanCode/queryOrderDetail", JSON.toJSONString(param));
        log.info("订单查询返回：{}", post);
        JSONObject jsonObject = JSON.parseObject(post);
        String code = jsonObject.getString("code");
        if ("200".equals(code)){
            JSONObject data = jsonObject.getJSONObject("data");
            data.remove("coupon");
            if (StrUtil.isNotEmpty(request.getMobile())){
                String userId = userService.selectUserId(request.getMobile());
                if (userId != null){
                    String parkId = data.getString("parkid");
                    JSONObject couponParam = new JSONObject();
                    couponParam.put("userId", userId);
                    couponParam.put("parkId", parkId);
                    String couponResult = HttpUtils.sendPost(CommonConstants.COUPON_URL + "/web/user/coupon/couponListForPay", JSON.toJSONString(couponParam));
                    log.info("优惠券查询返回：{}", couponResult);
                    JSONObject couponJo = JSON.parseObject(couponResult);
                    JSONArray jsonArray = couponJo.getJSONObject("data").getJSONArray("list");
                    if (jsonArray != null && !jsonArray.isEmpty()){
                        data.put("coupon", jsonArray);
                    }
                }
            }
            return R.ok(data);
        }else {
            return R.error(jsonObject.getString("msg"));
        }
    }

    @RequestMapping("/prePay")
    @SignatureValidation
    public R<Object> prePay(@RequestBody @Valid PrePayRequest request){
        log.info("预支付下单:{}", JSON.toJSONString(request));
        OrderPayDto dto = new OrderPayDto();
        dto.setOrdid(request.getOrderId());
        dto.setPayreqtype(request.getPayReqType());
        dto.setUserid(request.getUserId());
        dto.setReqchnl("other");
        JieyiUtils.updParkDto(dto, "parkingpayreq");
        dto.setStarttime(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        dto.setPayorder("1");//是否下单 0-仅查询；1-下单

        String result = JieyiUtils.platformAOP(dto);
        JSONObject jsonObject = JSON.parseObject(result);
        if (!"0000".equals(jsonObject.getString("resultcode"))) {
            throw new ServiceException("预支付下单失败");
        }
        Map<String, String> data = new HashMap<>();
        data.put("parkSTime", jsonObject.getString("parkstime"));
        data.put("parkETime", jsonObject.getString("parketime"));
        data.put("parkTime",  "");
        if(StrUtil.isNotBlank(data.get("parkstime")) && StrUtil.isNotBlank(data.get("parketime"))){
            Date sDate = DateUtil.parse(data.get("parkstime"), "yyyyMMddHHmmss") ;
            Date eDate = DateUtil.parse(data.get("parketime"), "yyyyMMddHHmmss") ;
            data.put("parkTime",  (eDate.getTime()-sDate.getTime())/1000+ "");
        }
        data.put("orderId", jsonObject.getString("ordid"));
        data.put("amt", jsonObject.getString("amt"));
        data.put("parkPaySsn", jsonObject.getString("parkpayssn"));
        return R.ok(data);
    }

    @RequestMapping("/payOrderNotice")
    @SignatureValidation
    public R<String> payOrderNotice(@RequestBody @Valid PayOrderNoticeRequest request) {
        log.info("结束订单:{}", JSON.toJSONString(request));
        // 调用捷羿云平台接口，支付完成通知，通知车场放行
        ParkPayNoticeDto dto = new ParkPayNoticeDto();
        dto.setPaytime(request.getPayTime());
        dto.setParkpayssn(request.getParkPaySsn());
        dto.setPayamt(request.getPayAmount());
        dto.setPromoteamount(request.getPromoteAmount());
        dto.setOrdid(request.getOrderId());
        dto.setPayplt(request.getPayPlt());
        dto.setPayseq(request.getPaySsn());
        String context;
        String outvalidtime = null;
        try {
            context = PayOrderNotice(dto);
            log.info("通知云平台结束");
            if (StrUtil.isNotEmpty(context)) {
                JSONObject jsonObject = JSON.parseObject(context);
                String resultcode = (String) jsonObject.get("resultcode");
                if (!"0000".equals(resultcode)) {
                    log.error("==================通知云平台异常 ！{}", context);
                }
                outvalidtime = (String) jsonObject.get("outvalidtime");
            }
        } catch (Exception e) {
            log.error("==================通知云平台异常 ！");
            throw new ServiceException("结束订单失败");
        }
        return R.ok(outvalidtime);
    }

    public String PayOrderNotice(ParkPayNoticeDto dto) {
        dto.setReqchnl("other");
        JieyiUtils.updParkDto(dto, "payordnotice");
        dto.setStatus("Y");//Y-支付成功；N-支付失败
        dto.setPaychnlseq("");
        dto.setPayflowno("");
        dto.setSecpayplt("");
        String context = null;
        log.info("支付完成通知云平台");
        try {
            context = JieyiUtils.platformAOP(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }

}
