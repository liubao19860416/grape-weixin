package org.hamster.weixinmp.model.send;

import org.hamster.weixinmp.model.send.item.wrapper.WxSendItemArticleWrapper;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class SendItemPicDescJson {
    private String touser;
    private String msgtype;
    private WxSendItemArticleWrapper news;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public WxSendItemArticleWrapper getNews() {
        return news;
    }

    public void setNews(WxSendItemArticleWrapper news) {
        this.news = news;
    }

    public SendItemPicDescJson(String touser, String msgtype,
            WxSendItemArticleWrapper news) {
        super();
        this.touser = touser;
        this.msgtype = msgtype;
        this.news = news;
    }

    public SendItemPicDescJson() {
        super();
    }

}
