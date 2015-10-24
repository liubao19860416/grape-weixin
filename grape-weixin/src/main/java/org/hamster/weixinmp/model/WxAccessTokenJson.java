package org.hamster.weixinmp.model;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxAccessTokenJson {

    private String access_token;
    private String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public WxAccessTokenJson() {
        super();
    }

    public WxAccessTokenJson(String access_token, String expires_in) {
        super();
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

}
