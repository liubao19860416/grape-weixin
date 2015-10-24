package utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * 主要是针对中文进行加解密
 */
public class URLEncodeUtils {
    public static final String ENCODE = "utf-8";

    public static String decode(String s) {
        try {
            return URLDecoder.decode(s, ENCODE);
        } catch (Exception e) {
            return s;
        }
    }

    public static String encode(String s) {
        try {
            return URLEncoder.encode(s, ENCODE);
        } catch (Exception e) {
            return s;
        }
    }
}
