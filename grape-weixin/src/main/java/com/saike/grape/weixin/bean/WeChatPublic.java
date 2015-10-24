package com.saike.grape.weixin.bean;

import java.io.Serializable;

/**
 * 添加的微信拥有者信息
 * 
 * @author Liubao
 * @2015年6月29日
 * 
 */
public class WeChatPublic extends BaseBean implements Serializable {

    private static final long serialVersionUID = 9149213375158290109L;
    private String wechatId;
    private String appsecret;
    private String appid;
    private String userId;
    private String wechatPName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String accessToken;

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setWechatPName(String wechatPName) {
        this.wechatPName = wechatPName;
    }

    public String getWechatPName() {
        return wechatPName;
    }

}
