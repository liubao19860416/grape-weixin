package com.saike.grape.util;

import java.util.HashMap;

/**
 * 返回成功的结果集
 * 
 * @author Liubao
 * @2015年3月18日
 * 
 */
public class ResultSuccessInfo extends ResultInfo {
    /***
     * 返回标识
     */
    private String errorCode = ResultInfoUtil.SUCCESS;
    /***
     * 返回对象
     */
    private Object result;

    public ResultSuccessInfo() {
        super();
    }

    /*public ResultSuccessInfo(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }*/

    public ResultSuccessInfo(Object result) {
        this.result = result;

    }

    public ResultSuccessInfo(String errorCode, Object result) {
        super(errorCode);
        this.errorCode = errorCode;
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getResult() {

        return result;
    }

    public void setResult(Object result) {
        if (result == null) {
            this.result = new HashMap();
            return;
        }
        this.result = result;
    }

}
