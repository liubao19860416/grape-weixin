package com.saike.grape.weixin.bean;

/**
 * 输出音乐消息
 * 
 */
public class MusicOutMessage extends OutMessage {
    private static final long serialVersionUID = 5935789521982237259L;
    private String MsgType = "music";
    private String MusicUrl;
    private String HQMusicUrl;

    public String getMsgType() {
        return MsgType;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String hQMusicUrl) {
        HQMusicUrl = hQMusicUrl;
    }
}
