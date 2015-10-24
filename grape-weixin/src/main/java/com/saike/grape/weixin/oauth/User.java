package com.saike.grape.weixin.oauth;

import com.alibaba.fastjson.JSONObject;
import com.saike.grape.weixin.HttpKit;
import com.saike.grape.weixin.bean.UserInfo;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 用户操作接口
 */
public class User {

	private static final String USER_INFO_URI = "https://api.weixin.qq.com/cgi-bin/user/info";
	private static final String USER_GET_URI = "https://api.weixin.qq.com/cgi-bin/user/get";

	/**
	 * 拉取用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 * @throws java.io.IOException
	 * @throws java.security.NoSuchProviderException
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.KeyManagementException
	 */
	public UserInfo getUserInfo(String accessToken, String openid) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openid);
		String  jsonStr = HttpKit.get(USER_INFO_URI, params);
		if(StringUtils.isNotEmpty(jsonStr)){
			JSONObject obj = JSONObject.parseObject(jsonStr);
			if(obj.get("errcode") != null){
				throw new Exception(obj.getString("errmsg"));
			}
			UserInfo user = JSONObject.toJavaObject(obj, UserInfo.class);
			return user;
		}
		return null;
	}
	
	/**
	 * 获取帐号的关注者列表
	 * @param accessToken
	 * @param next_openid
	 * @return
	 */
	public JSONObject getFollwersList(String accessToken, String next_openid) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("next_openid", next_openid);
		String  jsonStr = HttpKit.get(USER_GET_URI, params);
		if(StringUtils.isNotEmpty(jsonStr)){
			JSONObject obj = JSONObject.parseObject(jsonStr);
			if(obj.get("errcode") != null){
				throw new Exception(obj.getString("errmsg"));
			}
			return obj;
		}
		return null;
	}
	
}