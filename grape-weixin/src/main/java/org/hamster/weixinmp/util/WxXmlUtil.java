package org.hamster.weixinmp.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemImageEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemThumbEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemVideoEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemVoiceEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgImageEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLinkEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLocEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgTextEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgVideoEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgVoiceEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespImageEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespMusicEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespVideoEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespVoiceEntity;


/**
 * 
 * @author Liubao
 * @2015年7月9日
 *
 */
public class WxXmlUtil {

	private WxXmlUtil() {
	}

	/**
	 * 封装请求参数实体bean方法
	 */
	public static WxAuthReq getAuthReq(String signature, String timestamp,String nonce, String echostr) {
		WxAuthReq result = new WxAuthReq();
		result.setSignature(signature);
		result.setTimestamp(timestamp);
		result.setNonce(nonce);
		result.setEchostr(echostr);
		return result;
	}

	public static WxMsgTextEntity getMsgText(Element ele) throws DocumentException {
		WxMsgTextEntity result = msgEntityFactory(WxMsgTextEntity.class, ele);
		result.setMsgId(longVal(ele, "MsgId"));
		result.setContent(strVal(ele, "Content"));
		return result;
	}
	
	public static WxMsgImageEntity getMsgImage(Element ele) throws DocumentException {
		WxMsgImageEntity result = msgEntityFactory(WxMsgImageEntity.class, ele);
		WxItemImageEntity image = new WxItemImageEntity();
		image.setMediaId(strVal(ele, "MediaId"));
		image.setPicUrl(strVal(ele, "PicUrl"));
		result.setImage(image);
		return result;
	}
	
	public static WxMsgVoiceEntity getMsgVoice(Element ele) throws DocumentException {
		WxMsgVoiceEntity result = msgEntityFactory(WxMsgVideoEntity.class, ele);
		WxItemVoiceEntity voice = new WxItemVoiceEntity();
		voice.setMediaId(strVal(ele, "MediaId"));
		voice.setFormat(strVal(ele, "Format"));
		if (!StringUtils.isEmpty(ele.elementText("Recognition"))) {
			voice.setRecognition(strVal(ele, "Recognition"));
		}
		result.setVoice(voice);
		return result;
	}
	
	public static WxMsgVideoEntity getMsgVideo(Element ele) throws DocumentException {
		
		WxItemThumbEntity thumb = new WxItemThumbEntity();
		thumb.setMediaId(strVal(ele, "ThumbMediaId"));
		
		WxItemVideoEntity video = new WxItemVideoEntity();
		video.setThumb(thumb);
		video.setMediaId(strVal(ele, "MediaId"));
		
		WxMsgVideoEntity result = msgEntityFactory(WxMsgVideoEntity.class, ele);
		result.setVideo(video);
		return result;
	}
	
	public static WxMsgLocEntity getMsgLoc(Element ele) throws DocumentException {
		WxMsgLocEntity result = msgEntityFactory(WxMsgLocEntity.class, ele);
		result.setLabel(strVal(ele, "Label"));
		result.setLocationX(doubleVal(ele, "Location_X"));
		result.setLocationY(doubleVal(ele, "Location_Y"));
		result.setScale(doubleVal(ele, "Scale"));
		return result;
	}

	public static WxMsgLinkEntity getMsgLink(Element ele) throws DocumentException {
		WxMsgLinkEntity result = msgEntityFactory(WxMsgLinkEntity.class, ele);
		result.setTitle(strVal(ele, "Title"));
		result.setDescription(strVal(ele, "Description"));
		result.setUrl(strVal(ele, "Url"));
		return result;
	}
	
	public static WxMsgEventEntity getMsgEvent(Element ele) throws DocumentException {
		WxMsgEventEntity result = msgEntityFactory(WxMsgEventEntity.class, ele);
		result.setEvent(strVal(ele, "Event"));
		
		if (ele.elementText("EventKey") != null) {
			result.setEventKey(strVal(ele, "EventKey"));
		}
		if (ele.elementText("Ticket") != null) {
			result.setEventKey(strVal(ele, "Ticket"));
		}
		return result;
	}
	
	//==================================下面是响应部分的工具方法=================================
	
	public static Element getRespTextXML(WxRespTextEntity respText) throws DocumentException {
		Element ele = respElementFactory(respText);
		ele.addElement("Content").addCDATA(respText.getContent());
		return ele;
	}
	
	public static Element getRespImage(WxRespImageEntity respImage) throws DocumentException {
		Element ele = respElementFactory(respImage);
		Element imageEle = ele.addElement("Image");
		imageEle.addElement("MediaId").addCDATA(respImage.getImage().getMediaId());
		return ele;
	}
	
	public static Element getRespVoice(WxRespVoiceEntity respVoice) throws DocumentException {
		Element ele = respElementFactory(respVoice);
		Element voiceEle = ele.addElement("Voice");
		voiceEle.addElement("MediaId").addCDATA(respVoice.getVoice().getMediaId());
		return ele;
	}
	
	public static Element getRespVideo(WxRespVideoEntity respVideo) throws DocumentException {
		Element ele = respElementFactory(respVideo);
		Element videoEle = ele.addElement("Video");
		videoEle.addElement("MediaId").addCDATA(respVideo.getVideo().getMediaId());
		videoEle.addElement("Title").addCDATA(StringUtils.defaultString(respVideo.getVideo().getTitle()));
		videoEle.addElement("Description").addCDATA(StringUtils.defaultString(respVideo.getVideo().getDescription()));
		return ele;
	}
	
	public static Element getRespMusic(WxRespMusicEntity respMusic, WxItemThumbEntity thumb) throws DocumentException {
		Element ele = respElementFactory(respMusic);
		Element musicEle = ele.addElement("Music");
		musicEle.addElement("Title").addCDATA(StringUtils.defaultString(respMusic.getMusic().getTitle()));
		musicEle.addElement("Description").addCDATA(StringUtils.defaultString(respMusic.getMusic().getDescription()));
		musicEle.addElement("MusicUrl").addCDATA(StringUtils.defaultString(respMusic.getMusic().getMusicUrl()));
		musicEle.addElement("HQMusicUrl").addCDATA(StringUtils.defaultString(respMusic.getMusic().getHqMusicUrl()));
		musicEle.addElement("ThumbMediaId").addCDATA(thumb.getMediaId());
		return ele;
	}
	
	public static Element getRespPicDesc(WxRespPicDescEntity respPicDesc) throws DocumentException {
		Element ele = respElementFactory(respPicDesc);
		ele.addElement("ArticleCount").addText(String.valueOf(respPicDesc.getArticles().size()));
		Element articlesEle = ele.addElement("Articles");
		for (WxItemPicDescEntity item : respPicDesc.getArticles()) {
			Element itemEle = articlesEle.addElement("item");
			itemEle.addElement("Title").addCDATA(item.getTitle());
			itemEle.addElement("Description").addCDATA(item.getDescription());
			itemEle.addElement("PicUrl").addCDATA(item.getPicUrl());
			itemEle.addElement("HQMusicUrl").addCDATA(item.getUrl());
		}
		return ele;
	}
	
	public static Element toXML(String xmlstr) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlstr);
		return doc.getRootElement();
	}
	
	
	//==============================Private And Ohter Methods===================================
	
	
	public static WxMsgTypeEnum getReqType(Element ele) {
	    String type = ele.element("MsgType").getTextTrim();
	    return WxMsgTypeEnum.inst(type);
	}
	
	/**
	 * 将Element解析成WxBaseMsgEntity实体
	 */
	@SuppressWarnings("unchecked")
	private static <T> T msgEntityFactory(Class<? extends WxBaseMsgEntity> clazz, Element ele) {
		WxBaseMsgEntity result;
		try {
			result = clazz.newInstance();
			result.setToUserName(strVal(ele, "ToUserName"));
			result.setFromUserName(strVal(ele, "FromUserName"));
			result.setCreateTime(longVal(ele, "CreateTime"));
			result.setCreatedDate(new Date());
			result.setMsgType(strVal(ele, "MsgType"));
			if (ele.element("MsgId") != null) {
				result.setMsgId(longVal(ele, "MsgId"));
			}
			return (T) result;
		} catch (Exception e) {
			// never occurs
			return null;
		}
	}
	
	/**
     * 将WxBaseRespEntity解析成Element实体
     */
	private static Element respElementFactory(WxBaseRespEntity entity) {
		Element ele = DocumentHelper.createElement("xml");
		ele.addElement("ToUserName").addCDATA(entity.getToUserName());
		ele.addElement("FromUserName").addCDATA(entity.getFromUserName());
		String createTime = String.valueOf(entity.getCreateTime());
		if (StringUtils.isBlank(createTime)) {
			Long currentTime = WxUtil.currentTimeInSec();
			entity.setCreateTime(currentTime);
			createTime = String.valueOf(currentTime);
		}
		ele.addElement("CreateTime").setText(createTime);
		ele.addElement("MsgType").addCDATA(entity.getMsgType());
		ele.addElement("FuncFlag").setText(String.valueOf(entity.getFuncFlag()));
		return ele;
	}

	private static String strVal(Element ele, String name) {
		return ele.element(name).getStringValue();
	}

	private static Long longVal(Element ele, String name) {
		return Long.valueOf(ele.element(name).getStringValue());
	}

	private static Double doubleVal(Element ele, String name) {
		return Double.valueOf(ele.element(name).getStringValue());
	}

}
