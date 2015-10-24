package com.saike.grape.weixin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.saike.grape.controller.BaseController;
import com.saike.grape.weixin.api.MessageProcessingHandler;
import com.saike.grape.weixin.bean.Articles;
import com.saike.grape.weixin.bean.Attachment;
import com.saike.grape.weixin.bean.InMessage;
import com.saike.grape.weixin.bean.OutMessage;
import com.saike.grape.weixin.bean.WeChatPublic;
import com.saike.grape.weixin.cons.WechatProData;
import com.saike.grape.weixin.cons.XStreamFactory;
import com.saike.grape.weixin.oauth.Group;
import com.saike.grape.weixin.oauth.Menu;
import com.saike.grape.weixin.oauth.Message;
import com.saike.grape.weixin.oauth.Qrcod;
import com.saike.grape.weixin.oauth.User;
import com.saike.grape.weixin.util.Tools;
import com.thoughtworks.xstream.XStream;

/**
 * 微信常用的API
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController extends BaseController<WeChatController> {

//    @Value("${company.server}")
//    protected String eyouduCompanyServer;

    private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static final String PAYFEEDBACK_URL = "https://api.weixin.qq.com/payfeedback/update";
    private static final String DEFAULT_HANDLER = "com.eyoudu.weizhan.wechat.inf.DefaultMessageProcessingHandlerImpl";
    private static final String GET_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=";
    private static final String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
    private static final String REFRESH_TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    private static Class<?> messageProcessingHandlerClazz = null;
    /**
     * 消息操作接口
     */
    public static final Message message = new Message();
    /**
     * 菜单操作接口
     */
    public static final Menu menu = new Menu();
    /**
     * 用户操作接口
     */
    public static final User user = new User();
    /**
     * 分组操作接口
     */
    public static final Group group = new Group();

    /**
     * 分组操作接口
     */
    public static final Qrcod qrcod = new Qrcod();

    @RequestMapping("/wechatAPI/{validateToken}")
//    @RequestMapping("/wechatAPI/0")
    public void doFilter(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        Boolean isGet = request.getMethod().equals("GET");
        if (isGet) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        ServletInputStream in = request.getInputStream();
        String xmlMsg = Tools.inputStream2String(in);
        String xml = processing(xmlMsg);
        // 只能是GET方式的请求!!!所以返回了空串
        response.getWriter().write("");
    }

    /**
     * GET请求方式,进行数据格式的校验等操作
     */
    private void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String path = request.getServletPath();
        String pathInfo = path.substring(path.lastIndexOf("/"));
        String _token;
        String outPut = "error";
        if (pathInfo != null) {
            _token = pathInfo.substring(1);
            String signature = request.getParameter("signature");// 微信加密签名
            String timestamp = request.getParameter("timestamp");// 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");//
            // 验证  
            if (checkSignature(_token, signature, timestamp, nonce)) {
                outPut = echostr;
            }
        }
        response.getWriter().write(outPut);
    }

   

    /**
     * 消息体转换
     */
    private static InMessage parsingInMessage(String responseInputString) {
        // 转换微信post过来的xml内容
        XStream xs = XStreamFactory.init(false);
        xs.ignoreUnknownElements();
        xs.alias("xml", InMessage.class);
        InMessage msg = (InMessage) xs.fromXML(responseInputString);
        return msg;
    }

    // ====================================一些公共public的静态方法============================================

    /**
     * 签名检查
     */
    public static Boolean checkSignature(String token, String signature,
            String timestamp, String nonce) {
        return Tools.checkSignature(token, signature, timestamp, nonce);
    }

    /**
     * 根据接收到用户消息进行处理(POST)
     */
    public static String processing(String responseInputString) {
        InMessage inMessage = parsingInMessage(responseInputString);
        // 发送方帐号
        String fromUserName = inMessage.getFromUserName();
        // 开发者微信号
        String toUserName = inMessage.getToUserName();

        OutMessage oms = null;
        // 加载处理器
        if (messageProcessingHandlerClazz == null) {
            // 获取自定消息处理器，如果自定义处理器则使用默认处理器。
            String handler = DEFAULT_HANDLER;
            // handler = handler == null ? DEFAULT_HANDLER : handler;
            try {
                messageProcessingHandlerClazz = Thread.currentThread()
                        .getContextClassLoader().loadClass(handler);
            } catch (Exception e) {
                throw new RuntimeException(
                        "messageProcessingHandler Load Error！");
            }
        }
        String xml = "";
        try {
            MessageProcessingHandler messageProcessingHandler = (MessageProcessingHandler) messageProcessingHandlerClazz
                    .newInstance();
            // 取得消息类型
            String type = inMessage.getMsgType();
            Method getOutMessage = messageProcessingHandler.getClass()
                    .getMethod("getOutMessage");
            Method alMt = messageProcessingHandler.getClass().getMethod(
                    "allType", InMessage.class);
            Method mt = messageProcessingHandler.getClass().getMethod(
                    type + "TypeMsg", InMessage.class);
            alMt.invoke(messageProcessingHandler, inMessage);
            if (mt != null) {
                mt.invoke(messageProcessingHandler, inMessage);
            }
            Object obj = getOutMessage.invoke(messageProcessingHandler);
            if (obj != null) {
                oms = (OutMessage) obj;
            }
            // 调用事后处理
            try {
                Method aftMt = messageProcessingHandler.getClass().getMethod(
                        "afterProcess", InMessage.class, OutMessage.class);
                aftMt.invoke(messageProcessingHandler, inMessage, oms);
            } catch (Exception e) {
            }

            obj = getOutMessage.invoke(messageProcessingHandler);
            if (obj != null) {
                oms = (OutMessage) obj;
//                setMsgInfo(oms, inMessage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (oms != null) {
            // 把发送发送对象转换为xml输出
            XStream xs = XStreamFactory.init(true);
            xs.alias("xml", oms.getClass());
            xs.alias("item", Articles.class);
            xml = xs.toXML(oms);
        }
        return xml;
    }
    
    /**
     * 获取access_token1
     */
    public static String getAccessToken() throws Exception {
        String appid = WechatProData.AppId;
        String secret = WechatProData.AppSecret;
        String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid
                + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("access_token").toString();
    }

    /**
     * 获取access_token2
     */
    public static String getAccessToken(String appid1, String secret1)
            throws Exception {
        String appid = appid1.trim();
        String secret = secret1.trim();
        String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid
                + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("access_token").toString();
    }

    /**
     * 获取媒体资源
     */
    public static Attachment getMedia(String accessToken, String mediaId)
            throws IOException, ExecutionException, InterruptedException {
        String url = GET_MEDIA_URL + accessToken + "&media_id=" + mediaId;
        return HttpKit.download(url);
    }

    /**
     * 上传素材文件
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> uploadMedia(String accessToken,
            String type, File file) throws KeyManagementException,
            NoSuchAlgorithmException, NoSuchProviderException, IOException,
            ExecutionException, InterruptedException {
        String url = UPLOAD_MEDIA_URL + accessToken + "&type=" + type;
        String jsonStr = HttpKit.upload(url, file);
        return JSON.parseObject(jsonStr, Map.class);
    }

    /**
     * 判断是否来自微信, 5.0 之后的支持微信支付
     */
    public static boolean isWeiXin(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgent)) {
            Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
            Matcher m = p.matcher(userAgent);
            String version = null;
            if (m.find()) {
                version = m.group(1);
            }
            return (null != version && NumberUtils.toInt(version) >= 5);
        }
        return false;
    }

    /**
     * 获取请求urlAddress的响应数据
     */
    public static JSONObject getdata(String urlAddress) throws Exception {
        HttpClient client = new DefaultHttpClient();
        String retStr = null;
        URL url = new URL(urlAddress);
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(),
                url.getQuery(), null);
        HttpGet httpget = new HttpGet(uri);
        HttpResponse httpResponse = client.execute(httpget);
        if (200 == httpResponse.getStatusLine().getStatusCode()) {
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null)
                retStr = EntityUtils.toString(httpEntity);
        }
        JSONObject ob = JSONObject.parseObject(retStr);

        return ob;
    }

    // ====================================一些公共暂时无用的静态方法============================================

    /**
     * 支付反馈
     */
    public static boolean payfeedback(String openid, String feedbackid)
            throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        map.put("openid", openid);
        map.put("feedbackid", feedbackid);
        String jsonStr = HttpKit.get(PAYFEEDBACK_URL, map);
        Map<String, Object> jsonMap = JSONObject.parseObject(jsonStr);
        return "0".equals(jsonMap.get("errcode").toString());
    }

    /**
     * 设置发送消息体
     */
    public void setMsgInfo(OutMessage outMessage, InMessage inMmessage)
            throws Exception {
        // 判断事件的类型
        String wechatId = inMmessage.getFromUserName();// 用户的微信ID
        String companywechatId = inMmessage.getToUserName();// 当前公众号的ID
        // 获取当前微信公众号信息
        WeChatPublic weChatPublic = getweChatPubBywechatId(companywechatId);
        if (weChatPublic != null) {
            if (weChatPublic.getAccessToken() == null)// 判断token是否为Null
            {
                WeChatPublic wcp = checkToken(weChatPublic);
                weChatPublic.setAccessToken(wcp.getAccessToken());
            }
            // TODO
        } else {
            // 如果用户回复的不是关键字，不做处理，或者其他处理
            Message message = new Message();
            String result1 = message.sendText(weChatPublic.getAccessToken(),
                    inMmessage.getFromUserName(), "");
            System.out.println("result:" + result1);
        }
    }

    /**
     * TODO 待实现
     * 
     * @param companywechatId
     * @return
     */
    private WeChatPublic getweChatPubBywechatId(String companywechatId) {
        // TODO Auto-generated method stub
        return null;
    }

    // 重新获取accesstoken
    public WeChatPublic checkToken(WeChatPublic weChatPublic) throws Exception {
        // PrizeController pcontroller=new PrizeController();
        String token = getAccessToken(weChatPublic.getAppid(),
                weChatPublic.getAppsecret());// 获取token
        // 更新token到数据库
        // updateByWeChatId(weChatPublic.getWechatId(), token);
        weChatPublic.setAccessToken(token);
        return weChatPublic;
    }

    /**
     * 根据token获取 关注者列表
     */
    public List getattention(WeChatPublic weChatPublic) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
                + weChatPublic.getAccessToken();
        // 先根究accesstoken获取用户的分组
        JSONObject groupdata = getdata(url);
        Object o = groupdata.get("errcode");
        String errcode1 = null;
        if (o != null) {
            // 判断token是否过期，如果过期，则重新获取并且更新数据库，如果没有过期则使用当前token
            WeChatPublic newweChatPublic = checkToken(weChatPublic);
            getattention(newweChatPublic);
        }
        // errcode为0的时候说明请求成功
        JSONObject data = groupdata.getJSONObject("data");
        Object total = groupdata.get("total");
        double count = Double.valueOf(String.valueOf(total));
        Object next_openid = groupdata.get("next_openid");
        double h = count / 10000;
        int i = (int) Math.ceil(h);
        List list = new ArrayList();
        if (count > 10000) {
            for (int j = 0; j < i - 1; j++) {
                String getUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
                        + weChatPublic.getAccessToken()
                        + "&next_openid="
                        + next_openid.toString();
                JSONObject groupdata2 = getdata(getUrl);
                Object o2 = groupdata2.get("errcode");
                // errcode为0的时候说明请求成功
                if (o2 != null) {
                    // 判断token是否过期，如果过期，则重新获取并且更新数据库，如果没有过期则使用当前token
                    WeChatPublic newweChatPublic = checkToken(weChatPublic);
                    getattention(newweChatPublic);
                }
                next_openid = groupdata2.getJSONObject("next_openid");
                JSONArray openid = data.getJSONArray("openid");
                list.add(openid.toArray());
            }
        } else {
            JSONArray openid = data.getJSONArray("openid");
            list.add(openid.toArray());
        }
        return list;
    }

}
