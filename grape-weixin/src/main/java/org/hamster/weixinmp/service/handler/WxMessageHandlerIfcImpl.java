package org.hamster.weixinmp.service.handler;

import java.util.Map;

import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.springframework.stereotype.Service;

@Service
public class WxMessageHandlerIfcImpl implements WxMessageHandlerIfc {

    private Integer priority = 0;

    @Override
    public WxMsgTypeEnum[] listIntetestedMessageType() {
        return null;
    }

    @Override
    public WxBaseRespEntity handle(WxBaseMsgEntity msg,
            Map<String, Object> context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
