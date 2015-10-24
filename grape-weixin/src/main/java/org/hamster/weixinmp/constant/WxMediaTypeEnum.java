package org.hamster.weixinmp.constant;

/**
 * 微信数据媒体类型定义枚举类
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public enum WxMediaTypeEnum {
    IMAGE(WxMediaType.IMAGE), 
    MUSIC(WxMediaType.MUSIC), 
    PIC_DESC(WxMediaType.PIC_DESC), 
    THUMB(WxMediaType.THUMB), 
    VIDEO(WxMediaType.VIDEO), 
    VOICE(WxMediaType.VOICE), 
    DEFAULT(WxMediaType.DEFAULT), 
    NAN(WxMediaType.NAN)
    ;

    private final String _text;
    
    private WxMediaTypeEnum(final String text) {
        this._text = text;
    }

    @Override
    public String toString() {
        return _text;
    }

    public static WxMediaTypeEnum inst(String strVal) {
        for (WxMediaTypeEnum type : WxMediaTypeEnum.values()) {
            if (type.toString().equalsIgnoreCase(strVal)) {
                return type;
            }
        }
        return NAN;
    }
}
