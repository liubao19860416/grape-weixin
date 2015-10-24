package utils;

import sun.misc.BASE64Decoder;

/**
 * Base64加密/解密
 */
@SuppressWarnings("restriction")
public class Base64Utils {
    
    /**
     * 测试函数
     */
    public static void main(String[] args) {
        String str = getBASE64("~系统测试用户~");
        System.out.println("stringtoBASE64 ->  " + str);
        str = "JTdCJTIyY3VzdE5hbWUlMjIlM0ElMjJubm5rZmtqJTIyJTJDJTIybG9jYWxYJTIyJTNBJTIyJTIyJTJDJTIybG9jYWxZJTIyJTNBJTIyJTIyJTJDJTIybW9iaWxlUGhvbmUlMjIlM0ElMjIxMzYxMjM0NTY3OCUyMiUyQyUyMnVzZXJJZCUyMiUzQSUyMjU3MjIzMSUyMiU3RA==";
        System.out.println("stringfromBASE64 -> " + getFromBASE64(str));

    }
    
	/**
	 * base64加密
	 */
	@SuppressWarnings("deprecation")
	public static String getBASE64(String str) {
		if (str == null || str == "")
			return null;
		return (new sun.misc.BASE64Encoder()).encode(java.net.URLEncoder
				.encode(str).getBytes());
	}

	/**
	 * base64解密
	 */
	@SuppressWarnings("deprecation")
	public static String getFromBASE64(String str) {
		if (str == null || str == "")
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(str);
			return java.net.URLDecoder.decode(new String(b));
		} catch (Exception e) {
			return null;
		}
	}

}
