package com.saike.grape.weixin.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 图片信息
 */
public class PicItem extends BaseBean implements Serializable{

    private static final long serialVersionUID = 6509064896136649475L;
    private String PicMd5Sum;

    public String getPicMd5Sum() {
        return PicMd5Sum;
    }

    public void setPicMd5Sum(String picMd5Sum) {
        PicMd5Sum = picMd5Sum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}