package utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * EncryptDES 加密解密程序
 *
 * @author yjcai
 */
public class EncryptDES {
    private String Key;
    private String Iv;
    private String OperMode;

	/**
	 * 构造函数，初始化默认加密解密的密钥和模式
       test git
	 */
    public EncryptDES() {
        Key         = "11111111";
        Iv          = "00000000";
        OperMode    = "DES/CBC/NoPadding";
    }

	/**
	 * 构造函数，初始化加密解密的密钥和模式

	 * @param sKey
	 * @param sIv
	 * @param sOperMode
	 */
    public EncryptDES(String sKey,String sIv,String sOperMode) {
    	Key         = sKey;
    	Iv          = sIv;
    	OperMode    = sOperMode;
    }

	/**
	 * 计算偏移量
	 * @param sourceByte
	 * @param key
	 * @param operMode
	 * @param iv
	 * @return
	 * @throws Exception
	 */
	private byte[] inDES(byte[] sourceByte, SecretKey key, String operMode, IvParameterSpec iv) throws Exception {
		
        Cipher cipher = Cipher.getInstance(operMode);
		//初始化加密类，定义模式，密匙，IV
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(sourceByte);
		    	
	}


	/**
	 * 数据解密
	 * @param sourceByte
	 * @param key
	 * @param operMode
	 * @param iv
	 * @return
	 * @throws Exception
	 */
	private byte[] outDes(byte[] sourceByte, SecretKey key, String operMode, IvParameterSpec iv) throws Exception{		
        Cipher cipher = Cipher.getInstance(operMode);
		//初始化加密类，定义模式，密匙，IV
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(sourceByte);		    
	}

	/**
	 * 按8的倍数补位 0x00
	 * @param sourceByte
	 * @return
	 * @throws Exception
	 */
	public static byte[] doPadding(byte[] sourceByte)throws Exception {
		
		byte[] returnByte;
		int bytelen;
		int intValue;
		double douValue;
		
		bytelen = sourceByte.length;
		douValue =(double)bytelen/8;
		intValue = (int)douValue;

		//判断是否是整数

		if (douValue > intValue) {
			
			intValue = intValue +1;
			returnByte = new byte[intValue*8];
			
			for (int i=0; i<bytelen; i++) {
				returnByte[i] = sourceByte[i];		
			}
			
			for (int i=bytelen; i<intValue*8; i++) {
				returnByte[i] = 0x00;
			}
		}
		else {
			returnByte = new byte[intValue*8];
			returnByte = sourceByte;
		}
		return returnByte;
	}

	/**
	 * 去除补位的 0x00
	 * @param sourceByte
	 * @return
	 * @throws Exception
	 */
	public static byte[] cutPadding(byte[] sourceByte) throws Exception {
		int bytelen = 0;
		for (int i = 0; i < sourceByte.length; i++) {
			if (sourceByte[i] == (byte) 0x00) {
          		bytelen = i;
          		break;
        	}
        	else {
          		bytelen = sourceByte.length;
        	}
      	}
      	
      	byte[] returnByte = new byte[bytelen];
      	
      	for (int i = 0; i < bytelen; i++) {
			returnByte[i] = sourceByte[i];
      	}
      	return returnByte;
    }

	/**
	 * 获取秘钥
	 * @param keyByte
	 * @return
	 * @throws Exception
	 */
  	public static SecretKey genDESKey(byte[] keyByte) throws Exception {
    	SecretKey key = null;
    	key = new SecretKeySpec(keyByte, "DES");
    	return key;
  	}

	/**
	 * 产生偏移量
	 * @param ivByte
	 * @return
	 * @throws Exception
	 */
  	public static IvParameterSpec genDESIv(byte[] ivByte) throws Exception {
    	IvParameterSpec iv = null;
    	iv = new IvParameterSpec(ivByte);
    	return iv;
  	}

	/**
	 * 数据加密
	 * @param inByte
	 * @return
	 * @throws Exception
	 */
    public byte[] doEncrypt(byte[] inByte) throws Exception {

        byte[] useSource = doPadding(inByte);				
        SecretKey useKey = genDESKey(Key.getBytes());
        IvParameterSpec useIv = genDESIv(Iv.getBytes());
        String useOperMode	= OperMode;
        byte[] byteOut = inDES(useSource, useKey, useOperMode, useIv);
        return byteOut;
	}

	/**
	 * 数据解密
	 * @param inByte
	 * @return
	 * @throws Exception
	 */
    public byte[] doDecrypt(byte [] inByte) throws Exception {
            
			SecretKey useKey = genDESKey(Key.getBytes());
			IvParameterSpec useIv = genDESIv(Iv.getBytes());
			String useOperMode	= OperMode;
			byte[] byteOut = outDes(inByte, useKey, useOperMode, useIv);
			byteOut = cutPadding(byteOut);				
            return byteOut;	
    }

	/**
	 * 得到byte[]的16进制值
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b){
		
		String hs="";
		String stmp="";
		
		for (int n=0;n<b.length;n++){
			stmp=(Integer.toHexString(b[n] & 0XFF));
			if (stmp.length()==1) hs=hs+"0"+stmp;
			else hs=hs+stmp;
			if (n<b.length-1)  hs=hs+":";
		}
		return hs.toUpperCase();
	}
	
	/**
	 * 
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 * 
	 * @return
	 * @throws Exception 
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String getEncryptStr(String userId,String token,String deviceid) throws Exception{
		//加密解密的密钥和模式(8位随机数字）
		String sKey = "93840131";
		String sIv = "13288310";
		String sOperMode = "DES/CBC/NoPadding";
		String source = userId + "_" + System.currentTimeMillis() + "_" + token + "_" + deviceid;
		//加密
		//对报文进行加密后转BASE64处理
		byte[] b1 = source.getBytes("UTF-8");
		byte[] enc2 = new EncryptDES(sKey, sIv, sOperMode).doEncrypt(b1);
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String encryptStr=base64Encoder.encode(enc2);
		//String newEncryptStr=encryptStr;		
		
		encryptStr=encryptStr.replaceAll("%", "%25").replaceAll("\\+", "%2B").replaceAll(" ", "%20").replaceAll("/", "%2F")
		.replaceAll("\\?", "%3F").replaceAll("#", "%23").replaceAll("&", "%26").replaceAll("=", "%3D");
		return encryptStr;
	}
	
	public static void main(String[] args) throws Exception {
		//加密解密的密钥和模式(8位随机数字）
		String sKey = "93840131";
		String sIv = "13288310";
		String sOperMode = "DES/CBC/NoPadding";

		String userId ="560127";
		String token = "2250ca4ab1674b5da2de7143b8556431-564604";
		String deviceid = "FF3230A2-8E57-4FDF-9029-2DE940CD9FC7";

		String source = userId + "_" + System.currentTimeMillis() + "_" + token + "_" + deviceid;

		System.out.println("初始字符串:" + source);
		//加密
		//对报文进行加密后转BASE64处理
		byte[] b1 = source.getBytes("UTF-8");
		byte[] enc2 = new EncryptDES(sKey, sIv, sOperMode).doEncrypt(b1);
		BASE64Encoder base64Encoder = new BASE64Encoder();
		//加密后的字符串
		String sss = base64Encoder.encode(enc2);
		System.out.println("需要加密的字符串:" + sss);
		String aaa="sas?55"+sss;
		System.out.println("转义后字符串:"+aaa.replaceAll("%", "%25").replaceAll("\\+", "%2B").replaceAll(" ", "%20").replaceAll("/", "%2F")
				.replaceAll("\\?", "%3F").replaceAll("#", "%23").replaceAll("&", "%26").replaceAll("=", "%3D"));

		//解密
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] b2 = base64Decoder.decodeBuffer(sss);
		byte[] res = new EncryptDES(sKey, sIv, sOperMode).doDecrypt(b2);
		System.out.println("解密后的的字符串:" + new String(res, "UTF-8"));
	}
}
