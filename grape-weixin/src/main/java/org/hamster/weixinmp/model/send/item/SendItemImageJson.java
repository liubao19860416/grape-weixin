package org.hamster.weixinmp.model.send.item;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class SendItemImageJson {
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public SendItemImageJson() {
        super();
    }

    public SendItemImageJson(String mediaId) {
        super();
        this.mediaId = mediaId;
    }

}
