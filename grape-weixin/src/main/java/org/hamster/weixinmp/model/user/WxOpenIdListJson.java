package org.hamster.weixinmp.model.user;

import java.util.List;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxOpenIdListJson {
    private List<String> openid;

    public List<String> getOpenid() {
        return openid;
    }

    public void setOpenid(List<String> openid) {
        this.openid = openid;
    }

    public WxOpenIdListJson() {
        super();
    }

    public WxOpenIdListJson(List<String> openid) {
        super();
        this.openid = openid;
    }

}
