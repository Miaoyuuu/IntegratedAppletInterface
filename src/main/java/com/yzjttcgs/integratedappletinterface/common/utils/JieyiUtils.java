package com.yzjttcgs.integratedappletinterface.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yzjttcgs.integratedappletinterface.domain.dto.ParkDto;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-26 10:44:17
 */
@Slf4j
public class JieyiUtils {

    public static void updParkDto(ParkDto dto, String txncode) {
        if (dto != null)
        {
            dto.setTxncode(txncode);
            dto.setInstid("10000001");
            dto.setMchntid("100000010000001");
            dto.updTxntime();
            dto.updSeqid();
        }
    }

    public static String platformAOP(ParkDto dto) {
        Map<String, String> map = new HashMap<>();
        String sign = null;
        String json = JSONObject.toJSONString(dto);
        try {
            RSAUtil wer = new RSAUtil();
            wer.initPrivate("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJMa3CDmpX4wjFUX/FZOOpB4q5WeW7PGLQOetCmzCTM8rGUlpyHfeTKVoEAo7irEPp5yf64nf6te4kzkovZPR739tlJOrZ5QAeJsa89wWhSZWBRsiSBbKBiXVKaTgYXpX9k3UWA7KS8MtxaRGgY7gQbCnX0xvORsHJMz0LbDAQ63AgMBAAECgYAsHro3A/ZOyrDMNM1x9TSLcCXGUiCe4qlNlKPkfdYcj+4EyC5Aco4fJdFSbPRBz2oYi67PxPj9pQ+qkEhwJ4cUdlxcDv5UbaiY1lbUYZmtpyt+1vVmd8HdbtbXE8x7xyt0SkybRsNZcyT2qUg73QJIMwn1hylJie4J+rv9MV5HgQJBAOh4w/5FPsQnIaKlVuqMKHgFk48e81Bi/ZLt1x9Fdt6vSEPlWe540wS1NqKzpbea8MxkWHLj+gJGS2uNAb4nlX8CQQCh/keg0BqflebVCEjmunK8PbDdkWK87yGtIxzVm5vB+T9LI9nerhCC+YQOgWZiyjLboElNZcY3hIr54ung9lLJAkAYOUVLAJIqxF0X4pys8g/hectHdZUrAjWkEs6Avq9FQwSHtqVpWQO8ENnxmECjYizHT4l750+M3yBKw7Wzx9InAkBdQ+0xZR8u6jswQ8jVLluIjWwa5O2YIYWeDS04vpJ6p2oNdOKnFkhjYAzr3Qx5rDiDwlDxwrrQk/r/y+kjoC4hAkBdw/Ky7ddAytkj6itU6TNwkCzYTlLFSnbscAzIoV744Zc8Wza79Y3MwBg0qpLIQ27RniwrwMIsHFZ1jcvh5Q07");
            sign = NumberStringUtil.bytesToHexString(wer.signRSA(json.getBytes(), false, "utf-8"));
            dto.setMac(sign);
            json = JSONObject.toJSONString(dto);
        } catch (Exception e) {
            log.error("请求云平台异常：", e);
            map.put("resultcode", "408");
            return JSON.toJSONString(map);
        }

        Map<String, String> params = new HashMap<>();
        params.put("data", json);
        String context = null;
        try {
            long beginTime = System.currentTimeMillis();
            log.info("=================开始请求平台，【入参={}】", json);
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            context = HttpUtils.post("http://58.220.99.187:40001", params, 20, "UTF-8", headers);
            log.info("=================请求平台结束，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), context);
        } catch (IOException e) {
            log.error("请求云平台异常：", e);
            map.put("resultcode", "408");
            return JSON.toJSONString(map);
        }
        return context;
    }
}
