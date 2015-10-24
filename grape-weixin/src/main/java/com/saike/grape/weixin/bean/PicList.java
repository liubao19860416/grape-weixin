package com.saike.grape.weixin.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

public class PicList extends BaseBean implements Serializable{

    private static final long serialVersionUID = 719631946309503769L;
    // 实际测试目前还没有item这级xml，
    // 会分发为好几条图片消息
    private List<String> item;

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}