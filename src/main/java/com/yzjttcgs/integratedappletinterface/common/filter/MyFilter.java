package com.yzjttcgs.integratedappletinterface.common.filter;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class MyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getContentType() != null && request.getContentType().startsWith("application/json")) {
            MyRequestWrapper requestWrapper = new MyRequestWrapper(request);
            filterChain.doFilter(requestWrapper, servletResponse);
        } else {
            filterChain.doFilter(request, servletResponse);
        }
    }
}
