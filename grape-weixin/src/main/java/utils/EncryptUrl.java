package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 */
public class EncryptUrl{
	
	private static final Logger logger = LoggerFactory.getLogger(EncryptUrl.class);

	/**
	 * 根据传入参数 针对用户信息 加密
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 */
	public static String getEncryptUrlStr(String url,String userId,
			String token,String deviceid,String appChannel,String pageChannel) {
		if(url==null){
			return null;
		}
		if(userId==null||token==null||deviceid==null){
			return url;
		}
		String encryptStr=null;
		try {
			encryptStr=EncryptDES.getEncryptStr(userId, token, deviceid);
		} catch (Exception e) {
			logger.error("加密url链接出错", e);
			return url;
		}
		String urlStr="";
		if(url.indexOf("?")==-1){
			urlStr=url+"?encryptStr="+encryptStr;
		}else{
			urlStr=url+"&encryptStr="+encryptStr;
		}
		//校验 应用渠道
		if(appChannel!=null&&!"".equals(appChannel)){
			urlStr=urlStr+"&appChannel="+appChannel;
		}
		//校验页面渠道
		if(pageChannel!=null&&!"".equals(pageChannel)){
			urlStr=urlStr+"&pageChannel="+pageChannel;
		}
		
		return urlStr;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("ssss=="+getEncryptUrlStr("http://www.aaa.com/sss","345345","sskdjfa39rowewer349","askdjfek434534","rtsdf",null));
	}
}
