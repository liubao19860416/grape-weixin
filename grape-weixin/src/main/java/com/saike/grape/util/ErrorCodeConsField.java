package com.saike.grape.util;

import java.io.Serializable;

/**
 * ErrorCode常量数值类
 * 
 * @author Liubao
 * @2015年3月18日
 * 
 */
public final class ErrorCodeConsField implements Serializable {

    private static final long serialVersionUID = 8789423033223487174L;

    private ErrorCodeConsField() {
    }

    // 操作失败
    public static final String ERROR_MSG_10002 = "10002";
    // 参数为空
    public static final String ERROR_MSG_10003 = "10003";
    // 注册成功
}
