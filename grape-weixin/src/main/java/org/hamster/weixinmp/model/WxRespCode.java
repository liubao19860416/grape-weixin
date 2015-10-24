package org.hamster.weixinmp.model;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxRespCode {
    private Integer errcode;
    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public WxRespCode() {
        super();
    }

    public WxRespCode(Integer errcode, String errmsg) {
        super();
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

}
