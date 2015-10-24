package org.hamster.weixinmp.model.send.item;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class SendItemArticleJson {
    private String title;
    private String description;
    private String url;
    private String picurl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public SendItemArticleJson() {
        super();
    }

    public SendItemArticleJson(String title, String description, String url,
            String picurl) {
        super();
        this.title = title;
        this.description = description;
        this.url = url;
        this.picurl = picurl;
    }

}
