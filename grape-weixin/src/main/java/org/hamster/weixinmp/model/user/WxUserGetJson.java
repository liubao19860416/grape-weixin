package org.hamster.weixinmp.model.user;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public class WxUserGetJson {
    private Long total;
    private Long count;
    private String next_openid;
    private WxOpenIdListJson data;

    public WxUserGetJson() {
        super();
    }

    public WxUserGetJson(Long total, Long count, WxOpenIdListJson data,
            String next_openid) {
        super();
        this.total = total;
        this.count = count;
        this.data = data;
        this.next_openid = next_openid;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public WxOpenIdListJson getData() {
        return data;
    }

    public void setData(WxOpenIdListJson data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
