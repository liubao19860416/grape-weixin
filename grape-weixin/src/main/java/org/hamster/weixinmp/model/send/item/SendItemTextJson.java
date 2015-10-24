package org.hamster.weixinmp.model.send.item;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class SendItemTextJson {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SendItemTextJson() {
        super();
    }

    public SendItemTextJson(String text) {
        super();
        this.text = text;
    }

}
