package org.hamster.weixinmp.constant;

/**
 * 微信消息响应类型定义枚举类
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public enum WxMsgRespTypeEnum {
    TEXT(WxMsgRespType.TEXT), 
    IMAGE(WxMsgRespType.IMAGE), 
    MUSIC(WxMsgRespType.MUSIC), 
    NEWS(WxMsgRespType.NEWS), 
    VIDEO(WxMsgRespType.VIDEO), 
    VOICE(WxMsgRespType.VOICE);
    
    private final String _text;

    private WxMsgRespTypeEnum(final String text) {
        this._text = text;
    }

    @Override
    public String toString() {
        return _text;
    }

    public static WxMsgRespTypeEnum inst(String strVal) {
        for (WxMsgRespTypeEnum type : WxMsgRespTypeEnum.values()) {
            if (type.toString().equalsIgnoreCase(strVal)) {
                return type;
            }
        }
        return null;
    }
}
