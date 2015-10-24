package org.hamster.weixinmp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.constant.WxMsgRespTypeEnum;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespImageEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespMusicEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespVideoEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespVoiceEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.handler.WxMessageHandlerIfc;
import org.hamster.weixinmp.util.WxXmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息服务类
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Service
public class WxMessageService {

//    @Autowired(required = false)
//    private List<WxMessageHandlerIfc> handlers;
    
    @Autowired
    private WxMessageHandlerIfc wxMessageHandlerIfc;

    /**
     * 步骤01:解析请求url中的xml数据为实体WxBaseMsgEntity信息
     */
    public WxBaseMsgEntity parseXML(String xml) throws DocumentException, WxException {
        
        Element ele = DocumentHelper.parseText(xml).getRootElement();
        String msgType = null;
        if ((msgType = ele.elementText("MsgType")) == null) {
            throw new WxException("cannot find MsgType Node!\n" + xml);
        }
        WxMsgTypeEnum msgTypeEnum = WxMsgTypeEnum.inst(msgType);
        switch (msgTypeEnum) {
        case EVENT:
            return WxXmlUtil.getMsgEvent(ele);
        case IMAGE:
            return WxXmlUtil.getMsgImage(ele);
        case LINK:
            return WxXmlUtil.getMsgLink(ele);
        case LOCATION:
            return WxXmlUtil.getMsgLoc(ele);
        case TEXT:
            return WxXmlUtil.getMsgText(ele);
        case VIDEO:
            return WxXmlUtil.getMsgVideo(ele);
        case VOICE:
            return WxXmlUtil.getMsgVoice(ele);
        default:
            // never happens
            break;
        }
        return null;
    }

    /**
     * 步骤02:处理实体WxBaseMsgEntity信息,生成WxBaseRespEntity返回值实体信息
     */
    public WxBaseRespEntity handleMessage(WxBaseMsgEntity msg) {
        List<WxMessageHandlerIfc> handlerList = new ArrayList<WxMessageHandlerIfc>();
//        if (handlers != null) {
//            handlerList.addAll(handlers);
//        }
        //TODO 添加进行消息处理的实现类即可,添加到该handlerList中
        handlerList.add(wxMessageHandlerIfc);
        
        //排序
        Collections.sort(handlerList, new WxMessageHandlerComparator());

        Map<String, Object> context = new HashMap<String, Object>();
        WxBaseRespEntity result = null;
        for (WxMessageHandlerIfc handler : handlerList) {
            //具体的处理方法
            result = handler.handle(msg, context);
        }

        //返回默认值信息
        if (result == null) {
            result = defaultResult(msg.getToUserName(), msg.getFromUserName());
        }
        return result;
    }

    /**
     * 步骤02:处理响应实体WxBaseRespEntity,返回Element格式数据
     */
    public Element parseRespXML(WxBaseRespEntity resp) throws DocumentException {
        WxMsgRespTypeEnum type = WxMsgRespTypeEnum.inst(resp.getMsgType());
        switch (type) {
        case IMAGE:
            return WxXmlUtil.getRespImage((WxRespImageEntity) resp);
        case MUSIC:
            return WxXmlUtil.getRespMusic((WxRespMusicEntity) resp,
                    ((WxRespMusicEntity) resp).getThumb());
        case NEWS:
            return WxXmlUtil.getRespPicDesc((WxRespPicDescEntity) resp);
        case TEXT:
            return WxXmlUtil.getRespTextXML((WxRespTextEntity) resp);
        case VIDEO:
            return WxXmlUtil.getRespVideo((WxRespVideoEntity) resp);
        case VOICE:
            return WxXmlUtil.getRespVoice((WxRespVoiceEntity) resp);
        default:
            break;
        }
        return null;
    }

    /**
     * 设置默认文本信息
     */
    protected WxRespTextEntity defaultResult(String fromUserName,
            String toUserName) {
        WxRespTextEntity result = new WxRespTextEntity();
        result.setContent("您好,您的消息已收到.");
        result.setCreatedDate(new Date());
        result.setCreateTime(new Date().getTime() / 1000);
        result.setFromUserName(fromUserName);
        result.setMsgType(WxMsgRespType.TEXT);
        result.setToUserName(toUserName);
        return result;
    }

}

/**
 * 内部类,比较器
 */
final class WxMessageHandlerComparator implements Comparator<WxMessageHandlerIfc> {
    public int compare(WxMessageHandlerIfc o1, WxMessageHandlerIfc o2) {
        if (o1.getPriority() > o2.getPriority()) {
            return -1;
        } else if (o1.getPriority() < o2.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }

}
