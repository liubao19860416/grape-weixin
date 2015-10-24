package com.saike.grape.weixin.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 发送的图片信息
 */
public class SendPicsInfo extends BaseBean implements Serializable{

    private static final long serialVersionUID = 3736185430954677571L;
    private int Count;
    private PicList PicList;

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public PicList getPicList() {
        return PicList;
    }

    public void setPicList(PicList picList) {
        PicList = picList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
