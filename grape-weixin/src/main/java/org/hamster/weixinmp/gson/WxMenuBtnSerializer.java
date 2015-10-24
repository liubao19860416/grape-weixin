package org.hamster.weixinmp.gson;

import java.lang.reflect.Type;

import org.hamster.weixinmp.constant.WxMenuBtnTypeEnum;
import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;
import org.springframework.util.CollectionUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 微信菜单,递归调用,添加菜单menu及子菜单项目
 * CollectionUtils工具类的使用
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxMenuBtnSerializer implements JsonSerializer<WxMenuBtnEntity> {

    public JsonElement serialize(WxMenuBtnEntity src, Type typeOfSrc,
            JsonSerializationContext context) {
        return recursiveParse(src);
    }

    private JsonObject recursiveParse(WxMenuBtnEntity parentEntity) {
        JsonObject parent = new JsonObject();
        parent.addProperty("type", parentEntity.getType());
        parent.addProperty("name", parentEntity.getName());

        WxMenuBtnTypeEnum type = WxMenuBtnTypeEnum.valueOf(parentEntity.getType());
        switch (type) {
        case CLICK:
            parent.addProperty("key", parentEntity.getKey());
            break;
        case VIEW:
            parent.addProperty("url", parentEntity.getUrl());
            break;
        default:
            break;
        }
        //递归调用,添加菜单menu及子菜单项目
        if (!CollectionUtils.isEmpty(parentEntity.getSubButtons())) {
            JsonArray children = new JsonArray();
            for (WxMenuBtnEntity child : parentEntity.getSubButtons()) {
                children.add(recursiveParse(child));
            }
            parent.add("sub_button", children);
        }
        return parent;
    }

}
