package com.yzjttcgs.integratedappletinterface.common.interceptor;

import cn.hutool.core.lang.UUID;
import com.yzjttcgs.integratedappletinterface.common.constant.CommonConstants;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tid = UUID.randomUUID().toString().replace("-", "");
        MDC.put(CommonConstants.TRACE_ID, tid);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {
        MDC.remove(CommonConstants.TRACE_ID);
    }

}
