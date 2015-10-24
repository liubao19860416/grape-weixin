package com.saike.grape.weixin.api.impl;

import com.saike.grape.weixin.api.MessageProcessingHandler;
import com.saike.grape.weixin.bean.InMessage;
import com.saike.grape.weixin.bean.OutMessage;
import com.saike.grape.weixin.bean.TextOutMessage;

public class DefaultMessageProcessingHandlerImpl implements
        MessageProcessingHandler {

    private OutMessage outMessage;

    @Override
    public void allType(InMessage msg) {
        TextOutMessage out = new TextOutMessage();
        out.setContent("您的消息已经收到！");
        setOutMessage(out);
    }

    @Override
    public void textTypeMsg(InMessage msg) {
    }

    @Override
    public void locationTypeMsg(InMessage msg) {
    }

    @Override
    public void imageTypeMsg(InMessage msg) {
    }

    @Override
    public void videoTypeMsg(InMessage msg) {
    }

    @Override
    public void voiceTypeMsg(InMessage msg) {
    }

    @Override
    public void linkTypeMsg(InMessage msg) {
    }

    @Override
    public void verifyTypeMsg(InMessage msg) {
    }

    @Override
    public void eventTypeMsg(InMessage msg) {
    }


    @Override
    public void afterProcess(InMessage inMessage, OutMessage outMessage) {
    }
    
    
    public void setOutMessage(OutMessage outMessage) {
        this.outMessage = outMessage;
    }
    
    public OutMessage getOutMessage() {
        return outMessage;
    }
}
