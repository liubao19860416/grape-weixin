package com.saike.grape.weixin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 输出图文消息
 * 
 */
public class NewsOutMessage extends BaseBean implements Serializable{

    private static final long serialVersionUID = 4824946320823389162L;
    private String MsgType = "news";
    private Integer ArticleCount;
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    private List<Articles> Articles;

    public String getMsgType() {
        return MsgType;
    }

    public int getArticleCount() {
        return ArticleCount;
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

    /**
     * 微信规定的图片最大值为10;
     * @param articles
     */
    public void setArticles(List<Articles> articles) {
        if (articles != null) {
            if (articles.size() > 10)
                articles = new ArrayList<Articles>(articles.subList(0, 10));

            ArticleCount = articles.size();
        }
        Articles = articles;
    }
}
