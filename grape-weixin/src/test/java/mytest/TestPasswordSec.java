package mytest;

import org.junit.Test;

import utils.EncryptUtils;
import utils.HexAndStringUtils;
import utils.Md5Util;
import utils.RandomUtil;
import utils.RandomUtil.TicketType;
import utils.URLEncodeUtils;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class TestPasswordSec {
    
    @Test
    public void testBase64Encoder() throws Exception {
        String str="liubao";
        Base64Encoder base64Encoder=new Base64Encoder();
        String encode = base64Encoder.encode(str.getBytes());
        System.out.println(encode);
        
        //encode=base64Encoder.encode(str.getBytes());
        str = "211F2F13C2C9E0A1C1B6EA7105A609694A734E48C0E80FE66EAB4B9644233C1420A4C3A2F479AEBABD027556571E7ED8EB8F9A2D00D49F101D0C6178DD08BC332CF700D7A71F11741757D7858A87D3F002F28DD1BAA816FC41A4DFE24522F607607F3B01ABC63C5479DAE8704AF204E8D2CA663962F8F7EE30C451552E29FDCFFDEC2FFC16F014B0858801D710A674E0E771E099BB90D2B3AB8A447147A51083E46AD9A4BE1C16BEB0FEC3038A2CA11CD0D17A696D0016E1";
        byte[] decode = base64Encoder.decode(str);
        System.out.println("===============");
        System.out.println(new String(decode));
        System.out.println(HexAndStringUtils.decode(new String(decode,"utf-8")));
        System.out.println("===============");
        System.out.println(new String(decode,"utf-8"));
        System.out.println(HexAndStringUtils.decode(new String(decode,"utf-8")));
        System.out.println("===============");
        System.out.println(HexAndStringUtils.encode(base64Encoder.encode("{".getBytes("utf-8"))));
        System.out.println(base64Encoder.encode(HexAndStringUtils.encode("{").getBytes("utf-8")));
    }
    
    @Test
    public void testDESandBASE64() throws Exception {
        String str = "211F2F13C2C9E0A1C1B6EA7105A609694A734E48C0E80FE66EAB4B9644233C1420A4C3A2F479AEBABD027556571E7ED8EB8F9A2D00D49F101D0C6178DD08BC332CF700D7A71F11741757D7858A87D3F002F28DD1BAA816FC41A4DFE24522F607607F3B01ABC63C5479DAE8704AF204E8D2CA663962F8F7EE30C451552E29FDCFFDEC2FFC16F014B0858801D710A674E0E771E099BB90D2B3AB8A447147A51083E46AD9A4BE1C16BEB0FEC3038A2CA11CD0D17A696D0016E1";
        System.out.println(HexAndStringUtils.encode("date"));
        System.out.println(HexAndStringUtils.decode(str));
        System.out.println("738b521a".toUpperCase());
    }
    
    @Test
    public void testMD5() throws Exception {
        System.out.println(Md5Util.toMd5("liubao"));
    }
    
    @Test
    public void testRandomUtil() throws Exception {
        String str="1234567890abcdefghijklmnopqrstuvwxyz";
        System.out.println(RandomUtil.genRandomNum(10));
        System.out.println(RandomUtil.getRandomCharacter(str));
        System.out.println(RandomUtil.getRandomString(TicketType.WEBSITE_ACCOUNT_CHECKCODE));
        System.out.println(RandomUtil.getRandomString(str, 10));
    }
    
    @Test
    public void testURLDecoderUtils() throws Exception {
        String str="刘保";
        System.out.println(URLEncodeUtils.encode(str));
        System.out.println(URLEncodeUtils.decode(str));
    }
    
    @Test
    public void testEncryptUtils() throws Exception {
        String str="liubao";
        String encrypt = EncryptUtils.encrypt(str);
        System.out.println(encrypt);
        System.out.println(EncryptUtils.decrypt(encrypt));
    }
    

}
