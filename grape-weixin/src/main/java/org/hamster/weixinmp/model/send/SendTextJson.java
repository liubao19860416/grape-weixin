package org.hamster.weixinmp.model.send;

import org.hamster.weixinmp.model.send.item.SendItemTextJson;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class SendTextJson {
    private String touser;
    private String msgtype;
    private SendItemTextJson content;

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

    public SendItemTextJson getContent() {
        return content;
    }

    public void setContent(SendItemTextJson content) {
        this.content = content;
    }

    public SendTextJson() {
        super();
    }

    public SendTextJson(String touser, String msgtype, SendItemTextJson content) {
        super();
        this.touser = touser;
        this.msgtype = msgtype;
        this.content = content;
    }

}
