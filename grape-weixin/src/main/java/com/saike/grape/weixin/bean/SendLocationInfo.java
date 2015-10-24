package com.saike.grape.weixin.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 发送的位置信息
 */
public class SendLocationInfo extends BaseBean implements Serializable{

    private static final long serialVersionUID = -947906044512099294L;
    private String Location_X;
    private String Location_Y;
    private Long Scale;
    private String Label;
    private String Poiname;

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public Long getScale() {
        return Scale;
    }

    public void setScale(Long scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getPoiname() {
        return Poiname;
    }

    public void setPoiname(String poiname) {
        Poiname = poiname;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
