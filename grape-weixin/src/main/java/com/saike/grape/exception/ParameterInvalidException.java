package com.saike.grape.exception;

import com.saike.grape.util.ResultInfo;

/**
 * 自定义异常：参数解析类异常类
 * 
 * @author Liubao
 * @2015年3月18日
 * 
 */
public class ParameterInvalidException extends RuntimeException {

    private static final long serialVersionUID = -2713084450600477176L;

    private ResultInfo resultInfo;

    public ParameterInvalidException(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    public ResultInfo getResultInfo() {
        return this.resultInfo;
    }

}
