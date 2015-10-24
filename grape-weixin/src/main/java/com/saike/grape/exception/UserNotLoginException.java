/*
 * 
 */

package com.saike.grape.exception;

import com.saike.grape.util.ErrorCodeConsField;

/**
 * 用户没有登录异常
 * 
 * @author Liubao
 * @2015年3月18日
 * 
 */
public class UserNotLoginException extends GrapeException {

    private final static String NOT_LOGIN_ERROR_CODE = ErrorCodeConsField.ERROR_MSG_10002;

    public UserNotLoginException() {
        super(NOT_LOGIN_ERROR_CODE, "user.not.login", null);
    }

}
