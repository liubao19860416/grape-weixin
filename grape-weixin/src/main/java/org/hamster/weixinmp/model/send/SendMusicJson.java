package org.hamster.weixinmp.model.send;

import org.hamster.weixinmp.model.send.item.SendItemMusicJson;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class SendMusicJson {
    private String touser;
    private String msgtype;
    private SendItemMusicJson music;

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

    public SendItemMusicJson getMusic() {
        return music;
    }

    public void setMusic(SendItemMusicJson music) {
        this.music = music;
    }

    public SendMusicJson() {
        super();
    }

    public SendMusicJson(String touser, String msgtype, SendItemMusicJson music) {
        super();
        this.touser = touser;
        this.msgtype = msgtype;
        this.music = music;
    }

}
