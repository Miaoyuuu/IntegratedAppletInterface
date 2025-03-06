package com.yzjttcgs.integratedappletinterface.common.aspect;

import com.yzjttcgs.integratedappletinterface.common.domain.R;
import com.yzjttcgs.integratedappletinterface.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ServiceException.class)
    public <T> R<T> handleCommonException(ServiceException e) {
        return R.error(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public <T> R<T> handleBindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return R.error(1, message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> R<T> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return R.error(1, message);
    }

    @ExceptionHandler(value = Exception.class)
    public <T> R<T> handleException(Exception e) {
        log.error(e.getMessage());
        return R.error(500, "系统未知异常，请联系管理员");
    }
}
