package com.saike.grape.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 *上汽项目?
 *
 * @author v_lijixian
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SignUtil {
    
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp,String privateKey) {
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        // 生成签名结果
        String sign = buildRequestSign(sPara,privateKey);

        // 签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", sign);
        sPara.put("sign_type", "RSA");
        
        return sPara;
    }
    public static String messageDigest(String text, String publicKey){
        StringBuilder sbText = new StringBuilder();
        sbText.append(text);
        sbText.append(publicKey);
        String messageDigest = DigestUtils.md5Hex(text.getBytes());
        return messageDigest;
    }
   
    public static String buildRequestSign(Map<String, String> sPara,String privateKey) {
        // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String prestr = createLinkString(sPara); 
        return sign(prestr, privateKey, "utf-8");
    }
    public static String sign(String content, String privateKey, String inputCharset) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes()));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance("SHA1WithRSA");

            signature.initSign(priKey);
            signature.update(content.getBytes(inputCharset));

            byte[] signed = signature.sign();

//            return Base64.encodeBase64String(signed);
            return Base64.encodeBase64(signed).toString();
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidKeySpecException e) {
        } catch (SignatureException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        StringBuffer sbPreStr = new StringBuffer();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            // 拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                sbPreStr.append(key);
                sbPreStr.append("=");
                sbPreStr.append(value);
            } else {
                sbPreStr.append(key);
                sbPreStr.append("=");
                sbPreStr.append(value);
                sbPreStr.append("&");
            }
        }

        return String.valueOf(sbPreStr);
    }
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new LinkedHashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        Iterator<Entry<String, String>>  ite = sArray.entrySet().iterator();
        while(ite.hasNext()){
            Entry<String, String> entry = ite.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
    public static boolean verifySign(Map<String, String> params,String publicKey, String sign) {
        // 过滤空值、sign与sign_type参数
        Map<String, String> sParaNew = paraFilter(params);
        // 获取待签名字符串
        String preSignStr = createLinkString(sParaNew);
        // 获得签名验证结果
        boolean isSign = verify(preSignStr, sign, publicKey, "utf-8");
        return isSign;
    }
    /**
     * RSA验签名检查
     * 
     * @param content 待签名数据
     * @param sign 签名值
     * @param aliPublicKey 支付宝公钥
     * @param inputCharset 编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String publicKey, String inputCharset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decodeBase64(publicKey.getBytes());
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature.getInstance("SHA1WithRSA");

            signature.initVerify(pubKey);
            signature.update(content.getBytes(inputCharset));

            boolean bverify = signature.verify(Base64.decodeBase64(sign.getBytes()));
            return bverify;
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidKeySpecException e) {
        } catch (SignatureException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return false;
    }
}
