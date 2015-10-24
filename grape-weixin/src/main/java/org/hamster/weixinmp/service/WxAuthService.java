package org.hamster.weixinmp.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.auth.WxAuth;
import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * 基本的appid等排序及SHA-1加密及验证token等操作
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Service
public class WxAuthService {

    private static final Logger logger = LoggerFactory.getLogger(WxAuthService.class);

    @Autowired
    protected WxConfig config;

    public WxAuth getAccessToken(String appid, String appsecret) throws WxException {
        Map<String, String> paramsJson = new HashMap<String, String>();
        paramsJson.put("grant_type", "client_credential");
        paramsJson.put("appid", appid);
        paramsJson.put("secret", appsecret);

        WxAuth result = WxUtil.sendRequest(config.getAccessTokenCreateUrl(),
                HttpMethod.GET, paramsJson, null, WxAuth.class);
        result.setGrantType("client_credential");
        result.setAppid(appid);
        result.setSecret(appsecret);
        return result;
    }

    public boolean validateAuth(String signature, String timestamp,String nonce, String echostr) throws WxException {
        WxAuthReq authReq = new WxAuthReq();
        authReq.setCreatedDate(new Date());
        authReq.setSignature(signature);
        authReq.setTimestamp(timestamp);
        authReq.setNonce(nonce);
        authReq.setEchostr(echostr);

        String excepted = hash(getStringToHash(timestamp, nonce,config.getToken()));

        if (signature == null || !signature.equals(excepted)) {
            logger.error("Authentication failed! excepted echostr ->" + excepted);
            logger.error("=================================actual ->" + signature);
            return false;
        }
        return true;
    }

    protected static String getStringToHash(String timestamp, String nonce,String token) {
        List<String> list = new ArrayList<String>();
        list.add(timestamp);
        list.add(nonce);
        list.add(token);

        //String result = "";
        StringBuilder sb = new StringBuilder();
        //排序
        Collections.sort(list);
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            //result += list.get(i);
            sb.append(list.get(i));
        }
        //return result;
        return sb.toString();
    }

    /**
     * 加密SHA-1算法
     */
    protected static String hash(String str) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] b = md.digest(str.getBytes());
            for (int i = 0; i < b.length; i++) {
                sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("加密SHA-1算法抛出异常...",e);
            return null;
        }
        return sb.toString();
    }

}
