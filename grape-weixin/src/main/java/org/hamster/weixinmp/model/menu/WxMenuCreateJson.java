package org.hamster.weixinmp.model.menu;

import java.util.List;

import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;

/**
 * 
 * 微信菜单创建json实体
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxMenuCreateJson {

    private List<WxMenuBtnEntity> button;

    public List<WxMenuBtnEntity> getButton() {
        return button;
    }

    public void setButton(List<WxMenuBtnEntity> button) {
        this.button = button;
    }

    public WxMenuCreateJson() {
        super();
    }

    public WxMenuCreateJson(List<WxMenuBtnEntity> button) {
        super();
        this.button = button;
    }

}
