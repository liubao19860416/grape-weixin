package org.hamster.weixinmp.model.send.item.wrapper;

import java.util.List;

import org.hamster.weixinmp.model.send.item.SendItemArticleJson;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxSendItemArticleWrapper {

    private List<SendItemArticleJson> articles;

    public List<SendItemArticleJson> getArticles() {
        return articles;
    }

    public void setArticles(List<SendItemArticleJson> articles) {
        this.articles = articles;
    }

    public WxSendItemArticleWrapper() {
        super();
    }

    public WxSendItemArticleWrapper(List<SendItemArticleJson> articles) {
        super();
        this.articles = articles;
    }

}
