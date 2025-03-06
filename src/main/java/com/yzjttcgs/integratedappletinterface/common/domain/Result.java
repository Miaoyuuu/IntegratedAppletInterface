package com.yzjttcgs.integratedappletinterface.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-26 11:13:08
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code = "200";
    private String msg = "成功";
    private Object data;

    public Result(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Result(Object data){
        this.data = data;
    }

    public Result(String code, String msg, Object data){
        this(code, msg);
        this.data = data;
    }
}
