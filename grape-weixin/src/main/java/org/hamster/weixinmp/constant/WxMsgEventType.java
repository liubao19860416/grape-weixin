package org.hamster.weixinmp.constant;
/**
 * 微信事件类型定义枚举类
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
public abstract class WxMsgEventType {
	public static final String SUBSCRIBE = "subscribe";
	public static final String UNSUBSCRIBE = "unsubscribe";
	public static final String SCAN = "scan";
	public static final String LOCATION = "LOCATION";
	public static final String CLICK = "CLICK";
	
    private WxMsgEventType() {
        super();
    } 
	

}
