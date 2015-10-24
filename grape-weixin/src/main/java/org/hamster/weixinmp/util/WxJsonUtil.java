package org.hamster.weixinmp.util;

import java.util.List;

import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;
import org.hamster.weixinmp.model.WxAccessTokenJson;
import org.hamster.weixinmp.model.WxRespCode;
import org.hamster.weixinmp.model.menu.WxMenuCreateJson;

import com.google.gson.Gson;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxJsonUtil {

    private WxJsonUtil() {
    }

    public static final String toMenuCreateReqBody(List<WxMenuBtnEntity> wxMenuBtnList) {
        Gson gson = new Gson();
        WxMenuCreateJson wxMenuCreateJson = new WxMenuCreateJson();
        wxMenuCreateJson.setButton(wxMenuBtnList);
        return gson.toJson(wxMenuCreateJson);
    }

    public static final WxRespCode toWxErrorJson(String errorResult) {
        if (errorResult != null && 
                (errorResult.startsWith("{\"errcode") || errorResult.startsWith("{\"errmsg"))) {
            return new Gson().fromJson(errorResult, WxRespCode.class);
        }
        return null;
    }

    public static final WxAccessTokenJson toAccessTokenJson(String result) {
        return new Gson().fromJson(result, WxAccessTokenJson.class);
    }

}