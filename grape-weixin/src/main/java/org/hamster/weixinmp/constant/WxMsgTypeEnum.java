package org.hamster.weixinmp.constant;

/**
 * 微信消息类型定义枚举类
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public enum WxMsgTypeEnum {
    TEXT(WxMsgType.TEXT), 
    IMAGE(WxMsgType.IMAGE), 
    LOCATION(WxMsgType.LOCATION), 
    LINK(WxMsgType.LINK), 
    EVENT(WxMsgType.EVENT), 
    VIDEO(WxMsgType.VIDEO), 
    VOICE(WxMsgType.VOICE);
    
    private final String _text;

    private WxMsgTypeEnum(final String text) {
        this._text = text;
    }

    @Override
    public String toString() {
        return _text;
    }

    public static WxMsgTypeEnum inst(String strVal) {
        for (WxMsgTypeEnum type : WxMsgTypeEnum.values()) {
            if (type.toString().equalsIgnoreCase(strVal)) {
                return type;
            }
        }
        return null;
    }

}
