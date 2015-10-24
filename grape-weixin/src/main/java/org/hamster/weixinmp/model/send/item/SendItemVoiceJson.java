package org.hamster.weixinmp.model.send.item;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class SendItemVoiceJson {
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public SendItemVoiceJson() {
        super();
    }

    public SendItemVoiceJson(String mediaId) {
        super();
        this.mediaId = mediaId;
    }

}
