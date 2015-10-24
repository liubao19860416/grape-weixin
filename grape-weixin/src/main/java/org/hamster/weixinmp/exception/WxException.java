package org.hamster.weixinmp.exception;

import org.hamster.weixinmp.model.WxRespCode;

/**
 * 微信自定义异常信息
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxException extends Exception {

    private static final long serialVersionUID = -5181800588832010641L;
    private WxRespCode error;

    public WxException() {
    }

    public WxException(String message) {
        super(message);
    }

    public WxException(Throwable cause) {
        super(cause);
    }

    public WxException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxException(WxRespCode errorJson) {
        super(errorJson.getErrmsg());
        this.error = errorJson;
    }

    public WxRespCode getError() {
        return error;
    }

    public void setError(WxRespCode error) {
        this.error = error;
    }

}
