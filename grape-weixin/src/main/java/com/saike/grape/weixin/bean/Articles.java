package com.saike.grape.weixin.bean;

import java.io.Serializable;

/**
 * 多图文消息
 * 
 */
public class Articles extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String Title;
    private String Description;

    public String getPicurl() {
        return Picurl;
    }

    public void setPicurl(String picurl) {
        Picurl = picurl;
    }

    private String Picurl;
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}
