package com.yzjttcgs.integratedappletinterface.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yzjttcgs.integratedappletinterface.common.annotation.SignatureValidation;
import com.yzjttcgs.integratedappletinterface.common.constant.CommonConstants;
import com.yzjttcgs.integratedappletinterface.common.domain.R;
import com.yzjttcgs.integratedappletinterface.common.utils.HttpUtils;
import com.yzjttcgs.integratedappletinterface.domain.request.CouponLockRequest;
import com.yzjttcgs.integratedappletinterface.domain.request.CouponQuickPayRequest;
import com.yzjttcgs.integratedappletinterface.domain.request.CouponUseRequest;
import com.yzjttcgs.integratedappletinterface.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-28 14:31:19
 */
@RestController
@RequestMapping("/coupon")
@Slf4j
public class CouponController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/lock")
    @SignatureValidation
    public R<String> use(@RequestBody @Valid CouponLockRequest request) {
        log.info("优惠券锁定:{}", JSON.toJSONString(request));
        JSONObject couponParam = new JSONObject();
        couponParam.put("userCpIds", request.getUserCpIds());
        couponParam.put("lockType", request.getLockType());
        String couponResult = HttpUtils.sendPost(CommonConstants.COUPON_URL + "/open/order/use/lockUserCoupon", JSON.toJSONString(couponParam));
        log.info("优惠券锁定：{}", couponResult);
        JSONObject jsonObject = JSON.parseObject(couponResult);
        if (jsonObject.getInteger("code") == 0) {
            log.info("优惠券锁定成功");
            return R.ok();
        } else {
            log.info("优惠券锁定失败");
            return R.error(jsonObject.getString("msg"));
        }
    }

    @RequestMapping("/use")
    @SignatureValidation
    public R<String> use(@RequestBody @Valid CouponUseRequest request) {
        log.info("优惠券核销:{}", JSON.toJSONString(request));
        JSONObject couponParam = new JSONObject();
        String userId = userService.selectUserId(request.getMobile());
        couponParam.put("userId", userId);
        couponParam.put("username", request.getMobile());
        couponParam.put("carNo", request.getCarNo());
        couponParam.put("parkId", request.getParkId());
        couponParam.put("parkName", request.getParkName());
        couponParam.put("intoTime", request.getIntoTime());
        couponParam.put("payTime", request.getPayTime());
        couponParam.put("payType", request.getPayType());
        couponParam.put("payable", request.getPayable());
        couponParam.put("paidin", request.getPaidin());
        couponParam.put("freeTime", request.getFreeTime());
        couponParam.put("cpIds", request.getUserCpIds());
        couponParam.put("recordNo", request.getParkPaySsn());
        String couponResult = HttpUtils.sendPost(CommonConstants.COUPON_URL + "/open/order/use/add", JSON.toJSONString(couponParam));
        log.info("优惠券核销返回：{}", couponResult);
        JSONObject jsonObject = JSON.parseObject(couponResult);
        if (jsonObject.getInteger("code") == 0) {
            log.info("优惠券核销成功");
            return R.ok();
        } else {
            log.info("优惠券核销失败");
            return R.error(jsonObject.getString("msg"));
        }
    }

    /**
     * 获取用户最优券的面额，从支付金额中扣减
     * @param request
     * @return
     */
    @RequestMapping("/quickpay")
    @SignatureValidation
    public R<Object> quickpay(@RequestBody @Valid CouponQuickPayRequest request) {
        log.info("获取用户最优券的面额:{}", JSON.toJSONString(request));
        JSONObject couponParam = new JSONObject();
        String userId = userService.selectUserId(request.getMobile());
        couponParam.put("userId", userId);
        couponParam.put("parkId", request.getParkId());
        couponParam.put("payMoney", request.getPayMoney());
        couponParam.put("hours", request.getHours());
        String couponResult = HttpUtils.sendPost(CommonConstants.COUPON_URL + "/open/coupon/quickpay/use", JSON.toJSONString(couponParam));
        log.info("获取用户最优券的面额返回：{}", couponResult);
        JSONObject jsonObject = JSON.parseObject(couponResult);
        if (jsonObject.getInteger("code") == 0) {
            JSONObject data = jsonObject.getJSONObject("data");
            return R.ok(data);
        } else {
            log.info("获取用户最优券的面额失败");
            return R.error(jsonObject.getString("msg"));
        }
    }
}
