package org.hamster.weixinmp.constant;

/**
 * 微信消息类型定义类
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public abstract class WxMsgType {
    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String LOCATION = "location";
    public static final String LINK = "link";
    public static final String EVENT = "event";
    public static final String VIDEO = "video";
    public static final String VOICE = "voice";

    private WxMsgType() {
        super();
    }

}
