package com.yzjttcgs.integratedappletinterface.common.aspect;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.yzjttcgs.integratedappletinterface.common.exception.ServiceException;
import com.yzjttcgs.integratedappletinterface.common.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class SignatureValidationAspect {

    @Pointcut("@annotation(com.yzjttcgs.integratedappletinterface.common.annotation.SignatureValidation)")
    private void annotationPointcut() {
    }

    @Before("annotationPointcut()")
    public void doBefore(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String str = "";
        String listString = "";
        BufferedReader br = null;
        try {
            br = request.getReader();
            while ((str = br.readLine()) != null) {
                listString += str;
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException("验签失败！");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        Map<String, String> map = JSON.parseObject(listString, new TypeReference<Map<String, String>>() {
        });
        String result = SignUtil.checkSign(map);
        if (!"success".equals(result)) {
            log.error("验签失败，参数：{}", listString);
            throw new ServiceException("验签失败！");
        }
    }
}
