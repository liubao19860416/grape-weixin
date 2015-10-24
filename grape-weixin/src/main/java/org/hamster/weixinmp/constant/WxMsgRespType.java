package org.hamster.weixinmp.constant;

/**
 * 微信消息响应类型定义类
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public abstract class WxMsgRespType {
    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";
    public static final String MUSIC = "music";
    public static final String NEWS = "news";

    private WxMsgRespType() {
        super();
    }

}
