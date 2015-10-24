package com.saike.grape.weixin.bean;

import java.io.Serializable;
import java.util.List;

public class OutMessage extends BaseBean implements Serializable{

    private static final long serialVersionUID = 1779777800032120865L;
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Content;
    private Integer ArticleCount;
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;
    private List<Articles> Articles;

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

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

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public List<Articles> getArticles() {
        return Articles;
    }

    public void setArticles(List<Articles> articles) {
        Articles = articles;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    private int FuncFlag = 0;

    public String getToUserName() {
        return ToUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }
}