package org.hamster.weixinmp.constant;

/**
 * 微信事件类型定义枚举类
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public enum WxMsgEventTypeEnum {
    SUBSCRIBE(WxMsgEventType.SUBSCRIBE), 
    UNSUBSCRIBE(WxMsgEventType.UNSUBSCRIBE), 
    SCAN(WxMsgEventType.SCAN), 
    LOCATION(WxMsgEventType.LOCATION), 
    CLICK(WxMsgEventType.CLICK);
    
    private final String _text;

    private WxMsgEventTypeEnum(final String text) {
        this._text = text;
    }

    @Override
    public String toString() {
        return _text;
    }

    public static WxMsgEventTypeEnum inst(String strVal) {
        for (WxMsgEventTypeEnum type : WxMsgEventTypeEnum.values()) {
            if (type.toString().equalsIgnoreCase(strVal)) {
                return type;
            }
        }
        return null;
    }
}
