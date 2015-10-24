package com.saike.grape.weixin;

/**
 * 消息类型
 * 
 */
public enum MsgTypes {
    TEXT(), LOCATION(), IMAGE(), LINK(), VOICE(), EVENT(), VIDEO(), NEWS(), MUSIC(), VERIFY();

    public String getType() {
        return this.name().toLowerCase();
    }
}
