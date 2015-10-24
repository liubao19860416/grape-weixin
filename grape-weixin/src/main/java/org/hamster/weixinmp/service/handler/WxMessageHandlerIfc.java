package org.hamster.weixinmp.service.handler;

import java.util.Map;

import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public interface WxMessageHandlerIfc {

    /**
     * 列出所有的消息类型
     */
    WxMsgTypeEnum[] listIntetestedMessageType();

    /**
     * 具体的处理消息的实现方法
     */
    WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context);

    /**
     * 设置其对应的优先级别
     */
    Integer getPriority();
}
