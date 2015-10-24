package utils;

import java.io.ByteArrayOutputStream;

public class HexAndStringUtils {
    
    // 将16进制数转换为汉字
    public static String deUnicode(String content) {
        String enUnicode = null;
        String deUnicode = null;
        for (int i = 0; i < content.length(); i++) {
            if (enUnicode == null) {
                enUnicode = String.valueOf(content.charAt(i));
            } else {
                enUnicode = enUnicode + content.charAt(i);
            }
            if (i % 4 == 3) {
                if (enUnicode != null) {
                    if (deUnicode == null) {
                        deUnicode = String.valueOf((char) Integer.valueOf(
                                enUnicode, 16).intValue());
                    } else {
                        deUnicode = deUnicode
                                + String.valueOf((char) Integer.valueOf(
                                        enUnicode, 16).intValue());
                    }
                }
                enUnicode = null;
            }

        }
        return deUnicode;
    }

    // 将汉字转换为16进制数
    public static String enUnicode(String content) {
        String enUnicode = null;
        for (int i = 0; i < content.length(); i++) {
            if (i == 0) {
                enUnicode = getHexString(Integer.toHexString(content.charAt(i)).toUpperCase());
            } else {
                enUnicode += getHexString(Integer.toHexString(content.charAt(i)).toUpperCase());
            }
        }
        return enUnicode;
    }

    private static String getHexString(String hexString) {
        String hexStr = "";
        for (int i = hexString.length(); i < 4; i++) {
            if (i == hexString.length())
                hexStr = "0";
            else
                hexStr = hexStr + "0";
        }
        return hexStr + hexString;
    }

    /**
     * 测试main方法,一个字符转换为4位数16进制数据
     */
    public static void main(String[] args) throws Exception {
        String str = "中文转换为16进制数据测试";
        System.out.println(str.length());
        System.out.println(enUnicode(str));
        str = "4E2D65878F6C63624E3A003100368FDB52366570636E6D4B8BD5";
        System.out.println(deUnicode(str));
        str = "C2C6A2488C442FFD91A25F23A3720FDACD002B1ACFA08495C2434BC0FAEBC4D114AC1A61FBAC992DBA2F6308654FFD68DD4E805747B27AAC5F501A5E950597983F5B386B91323BC294BD305BE867511AAE3469BAAF5A3784A0CDC160444BF514D3F7A969AB2D1D7EB2E53D71F5527FAB028FF23CA1AFDBAF3B6BF24BA06E71B32B196EE7499AF108";
//        for(int i=0;i<str.length();i++){
//            System.out.println(i+"===>"+deUnicode(str.substring(i)));
//        }
        System.out.println(str.length());
        str = "ordertype=doctor&vefirycode=ENKL&usercardid=141771&hospitalid=42502656400&templateid=15030400000536&doctorname=袁菲&hospitalname=瑞金医院&resdeptl2=病理科&date=2015-04-02 星期四&time=13:00-13:59&fee=17&address=";
        System.out.println(str.length());
        System.out.println(Base64Utils.getBASE64("liubao"));
        System.out.println(Base64Utils.getFromBASE64("bGl1YmFv"));
        
    }
       
    
    /*
     * 16进制数字字符集
     */
    private static String hexString = "0123456789ABCDEF";
    
    // 转化字符串为十六进制编码
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
    
    // 转化十六进制编码为字符串
    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    
    public static String bytesToHexString(byte[] src){  
        StringBuilder stringBuilder = new StringBuilder("");  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        for (int i = 0; i < src.length; i++) {  
            int v = src[i] & 0xFF;  
            String hv = Integer.toHexString(v);  
            if (hv.length() < 2) {  
                stringBuilder.append(0);  
            }  
            stringBuilder.append(hv);  
        }  
        return stringBuilder.toString();  
    }  
    
    public static byte[] hexStringToBytes(String hexString) {  
        if (hexString == null || hexString.equals("")) {  
            return null;  
        }  
        hexString = hexString.toUpperCase();  
        int length = hexString.length() / 2;  
        char[] hexChars = hexString.toCharArray();  
        byte[] d = new byte[length];  
        for (int i = 0; i < length; i++) {  
            int pos = i * 2;  
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
        }  
        return d;  
    }  

    private static byte charToByte(char c) {  
        return (byte) "0123456789ABCDEF".indexOf(c);  
    }  
     
    //将指定byte数组以16进制的形式打印到控制台  
    public static void printHexString( byte[] b) {    
       for (int i = 0; i < b.length; i++) {   
         String hex = Integer.toHexString(b[i] & 0xFF);   
         if (hex.length() == 1) {   
           hex = '0' + hex;   
         }   
         System.out.print(hex.toUpperCase() );   
       }   
      
    }  

    public static String bytes2HexString(byte[] b) {  
      String ret = "";  
      for (int i = 0; i < b.length; i++) {  
       String hex = Integer.toHexString(b[ i ] & 0xFF);  
       if (hex.length() == 1) {  
        hex = '0' + hex;  
       }  
       ret += hex.toUpperCase();  
      }  
      return ret;  
    }  
    
    /*
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    /*
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String decode(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }
    
    public static String hexStringToString(String str){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < str.length(); i=i+7) {
            byte[] bytes = HexString2Bytes(str.substring(i, i+8));
            sb.append(new String(bytes));
        }
        return sb.toString();
    }
    
    
    /**
    * 将指定字符串src，以每两个字符分割转换为16进制形式
    * 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9}
    * @param src String
    * @return byte[]
    */
    public static byte[] HexString2Bytes(String src) {
        byte[] ret = new byte[8];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < 8; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }
    
    /**
    * 将两个ASCII字符合成一个字节；
    * 如："EF"--> 0xEF
    * @param src0 byte
    * @param src1 byte
    * @return byte
    */
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
                .byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
                .byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }



}
