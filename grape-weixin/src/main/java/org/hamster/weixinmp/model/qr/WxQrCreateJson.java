package org.hamster.weixinmp.model.qr;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxQrCreateJson {
    private String action_name;
    private Long expire_seconds;
    private WxQrActionInfoJson action_info;

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public Long getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(Long expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public WxQrActionInfoJson getAction_info() {
        return action_info;
    }

    public void setAction_info(WxQrActionInfoJson action_info) {
        this.action_info = action_info;
    }

    public WxQrCreateJson() {
        super();
    }

    public WxQrCreateJson(String action_name, Long expire_seconds,
            WxQrActionInfoJson action_info) {
        super();
        this.action_name = action_name;
        this.expire_seconds = expire_seconds;
        this.action_info = action_info;
    }

}
