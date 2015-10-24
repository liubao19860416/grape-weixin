package org.hamster.weixinmp.model.menu;

/**
 * 微信菜单获取json格式数据实体
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxMenuGetJson {
    private WxMenuCreateJson menu;

    public WxMenuCreateJson getMenu() {
        return menu;
    }

    public void setMenu(WxMenuCreateJson menu) {
        this.menu = menu;
    }

    public WxMenuGetJson() {
        super();
    }

    public WxMenuGetJson(WxMenuCreateJson menu) {
        super();
        this.menu = menu;
    }

}
