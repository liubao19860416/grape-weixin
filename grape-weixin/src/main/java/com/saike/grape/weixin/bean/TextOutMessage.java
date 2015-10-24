package com.saike.grape.weixin.bean;

/**
 * 输出文字消息
 * 
 */
public class TextOutMessage extends OutMessage {

    private static final long serialVersionUID = -4429212265537588828L;
    private String MsgType = "text";
    // 文本消息
    private String Content;

    public TextOutMessage() {
    }

    public TextOutMessage(String content) {
        Content = content;
    }

    public String getMsgType() {
        return MsgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
