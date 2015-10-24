package com.saike.grape.weixin.api;

import com.saike.grape.weixin.bean.InMessage;
import com.saike.grape.weixin.bean.OutMessage;


/**
 * 消息处理器
 *
 */
public interface MessageProcessingHandler {
	
	/**
	 * 统一处理器
	 */
	public void allType(InMessage msg);
	
	/**
	 * 文字内容的消息处理
	 */
	public void textTypeMsg(InMessage msg);
	
	/**
	 * 地理位置类型的消息处理
	 */
	public void locationTypeMsg(InMessage msg);
	
	/**
	 * 图片类型的消息处理
	 */
	public void imageTypeMsg(InMessage msg);
	
	/**
	 * 视频类型的消息处理
	 */
	public void videoTypeMsg(InMessage msg);
	
	/**
	 * 链接类型的消息处理
	 */
	public void linkTypeMsg(InMessage msg);

	/**
	 * 语音类型的消息处理
	 */
	public void voiceTypeMsg(InMessage msg);
	
	/**
	 * 验证消息处理
	 */
	public void verifyTypeMsg(InMessage msg);

	/**
	 * 事件类型的消息处理。<br/>
	 * 在用户首次关注公众账号时，系统将会推送一条subscribe的事件
	 */
	public void eventTypeMsg(InMessage msg);

	/**
	 * 处理流程结束，返回输出信息之前执行
	 */
	public void afterProcess(InMessage inMsg, OutMessage outMsg);
	
//	/**
//	 * 设置输出
//	 */
//	public void setOutMessage(OutMessage outMessage);
//	
//	/**
//	 * 处返回输出对象
//	 */
//	public OutMessage getOutMessage();
//	
}
