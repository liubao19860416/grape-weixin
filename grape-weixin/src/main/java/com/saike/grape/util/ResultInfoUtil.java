package com.saike.grape.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 结果集封装的工具类
 * 
 * @author Liubao
 * @2015年3月18日
 * 
 */
public class ResultInfoUtil {
    
    public final static String SUCCESS = "0";
    public final static String ERROR = "1";
    public final static String NOT_SUPPORTED = "2";
    
    private static Map<String, String> errorMap = new HashMap<String, String>();
    static {
        errorMap.put(SUCCESS, "操作成功");
        errorMap.put(ERROR, "操作失败!");
        errorMap.put(NOT_SUPPORTED, "操作不支持!");
    }

    /***
     * 设置正确 结果
     * 
     * @param obj 结果集数据对象
     * @return
     */
    public static ResultInfo setSuccessInfo(Object obj) {
        ResultSuccessInfo resultSuccessInfo = new ResultSuccessInfo();
        resultSuccessInfo.setErrorCode(SUCCESS);
        resultSuccessInfo.setResult(obj);
        return resultSuccessInfo;
    }

    /**
     * 
     * @param errorCode 结果代码
     * @param obj 结果集数据对象
     * @return
     */
    public static ResultInfo setSuccessInfo(String errorCode, Object obj) {
        ResultSuccessInfo resultSuccessInfo = new ResultSuccessInfo();
        resultSuccessInfo.setErrorCode(errorCode);
        resultSuccessInfo.setResult(obj);
        return resultSuccessInfo;
    }

    /***
     * 设置错误 结果
     * 
     * @param type 错误类型
     * @return
     */
    public static ResultInfo setErrorInfo(String type) {
        ResultErrorInfo resultInfo = new ResultErrorInfo();
        resultInfo.setErrorMessage(ResultInfoUtil.errorMap.get(type));
        resultInfo.setErrorCode(type);
        return resultInfo;
    }

    /**
     * 
     * @param errorCode 错误码
     * @param errorMessage 错误信息
     * @return
     */
    public static ResultInfo setErrorInfo(String errorCode, String errorMessage) {
        ResultErrorInfo resultErrorInfo = new ResultErrorInfo();
        resultErrorInfo.setErrorMessage(errorMessage);
        resultErrorInfo.setErrorCode(errorCode);
        return resultErrorInfo;
    }

}
