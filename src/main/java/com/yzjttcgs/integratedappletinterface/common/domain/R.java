package com.yzjttcgs.integratedappletinterface.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yzjttcgs.integratedappletinterface.common.constant.CommonConstants;
import com.yzjttcgs.integratedappletinterface.common.constant.HttpStatus;
import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 标识返回状态
     */
    private Integer code;

    /**
     * 标识返回内容
     */
    private T data;
    /**
     * 标识返回消息
     */
    private String message;
    /**
     * 标识返回日志ID
     */
    private String logId;

    public static <T> R<T> ok() {
        return new R<>(HttpStatus.SUCCESS, null, "success", MDC.get(CommonConstants.TRACE_ID));
    }

    public static <T> R<T> ok(T data) {
        return new R<>(HttpStatus.SUCCESS, data, "success", MDC.get(CommonConstants.TRACE_ID));
    }

    public static <T> R<T> ok(T data, String message) {
        return new R<>(HttpStatus.SUCCESS, data, message, MDC.get(CommonConstants.TRACE_ID));
    }

    public static <T> R<T> error() {
        return new R<>(HttpStatus.ERROR, null, "fail", MDC.get(CommonConstants.TRACE_ID));
    }

    public static <T> R<T> error(String message) {
        return new R<>(HttpStatus.ERROR, null, message, MDC.get(CommonConstants.TRACE_ID));
    }

    public static <T> R<T> error(Integer code, String message) {
        return new R<>(code, null, message, MDC.get(CommonConstants.TRACE_ID));
    }

    public R(Integer code, T data, String message, String logId) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.logId = logId;
    }

    public R<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }


    public R<T> message(String message) {
        this.message = message;
        return this;
    }

}