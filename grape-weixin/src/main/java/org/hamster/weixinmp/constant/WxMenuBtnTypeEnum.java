package org.hamster.weixinmp.constant;

/**
 * 微信菜单类型定义枚举类
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public enum WxMenuBtnTypeEnum {
    VIEW(WxMenuBtnType.VIEW), 
    CLICK(WxMenuBtnType.CLICK);
    
    private final String _text;
    
    private WxMenuBtnTypeEnum(final String text) {
        this._text = text;
    }

    @Override
    public String toString() {
        return _text;
    }

    public static WxMenuBtnTypeEnum inst(String strVal) {
        for (WxMenuBtnTypeEnum type : WxMenuBtnTypeEnum.values()) {
            if (type.toString().equalsIgnoreCase(strVal)) {
                return type;
            }
        }
        return null;
    }
}
