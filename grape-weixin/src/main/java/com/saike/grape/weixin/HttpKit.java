package com.saike.grape.weixin;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.saike.grape.weixin.bean.Attachment;
import com.saike.grape.weixin.oauth.Menu;

/**
 * https 请求 微信为https的请求
 */ 
public class HttpKit {
	private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.addQueryParameter(key, params.get(key));
            }
        }

        if (headers != null && !headers.isEmpty()) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                builder.addHeader(key, params.get(key));
            }
        }
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }

    /**
     * @description 功能描述: get 请求
     */
    public static String get(String url) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
        return get(url, null);
    }

    /**
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
        return get(url, params, null);
    }

    /**
     * @description 功能描述: POST 请求
     */
    public static String post(String url, Map<String, String> params) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.addParameter(key, params.get(key));
            }
        }
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }

    /**
     * 上传媒体文件
     */
    public static String upload(String url, File file) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL"; // 定义数据分隔线
        builder.setHeader("connection", "Keep-Alive");
        builder.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
        builder.setHeader("Charsert", "UTF-8");
        builder.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
        builder.setBody(new UploadEntityWriter(end_data, file));

        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }

    /**
     * 下载资源
     */
    public static Attachment download(String url) throws ExecutionException, InterruptedException, IOException {
        Attachment att = new Attachment();
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        Future<Response> f = builder.execute();
        if (f.get().getContentType().equalsIgnoreCase("text/plain")) {
            att.setError(f.get().getResponseBody(DEFAULT_CHARSET));
        } else {
            BufferedInputStream bis = new BufferedInputStream(f.get().getResponseBodyAsStream());
            String ds = f.get().getHeader("Content-disposition");
            String fullName = ds.substring(ds.indexOf("filename=\"") + 10, ds.length() - 1);
            String relName = fullName.substring(0, fullName.lastIndexOf("."));
            String suffix = fullName.substring(relName.length() + 1);

            att.setFullName(fullName);
            att.setFileName(relName);
            att.setSuffix(suffix);
            att.setContentLength(f.get().getHeader("Content-Length"));
            att.setContentType(f.get().getContentType());
            att.setFileStream(bis);
        }
        http.close();
        return att;
    }

    public static String post(String url, String s) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        builder.setBody(s);
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }
    
    public  static void main(String[] args) throws Exception{
        //String  accessToken= "ImNmKTpiGwecjo-HQeiMM-iW-ZEoP25v9OcfYlTZF8bgV_OxW2pv6R0Q6xOFKIR7QHmq5QsyNUtyxEDvgLjixxoCkIj7XE6qLpw09L0isMA";
         String toekn = "JsUTxb5GvIDFfSXdwU8O-LgC6D6lRjNdMiSi8x4LW-07u2N7O6t0OYZ3DCwODUHTN5AXKwAFP25nHI-btTr-JKIwqTZLPjHTxH0a_RfHGVo";
       // WeChat.processing()
        Menu menu=new Menu();
        String params="{\n" +
                "    \"button\": [\n" +
                "        {\n" +
                "            \"name\": \"扫码\", \n" +
                "            \"sub_button\": [\n" +
                "                {\n" +
                "                    \"type\": \"scancode_waitmsg\", \n" +
                "                    \"name\": \"扫码带提示\", \n" +
                "                    \"key\": \"rselfmenu_0_0\", \n" +
                "                    \"sub_button\": [ ]\n" +
                "                }, \n" +
                "                {\n" +
                "                    \"type\": \"scancode_push\", \n" +
                "                    \"name\": \"扫码推事件\", \n" +
                "                    \"key\": \"rselfmenu_0_1\", \n" +
                "                    \"sub_button\": [ ]\n" +
                "                },\n" +
                "                   {\n" +
                "                   \"name\": \"砸金蛋\", \n" +
                "                   \"type\": \"click\", \n" +
                "                   \"key\": \"zajindan_2.0\"\n" +
                "               }\n" +
                "            ]\n" +
                "        }, \n" +
                "        {\n" +
                "            \"name\": \"发图\", \n" +
                "            \"sub_button\": [\n" +
                "                {\n" +
                "                    \"type\": \"pic_sysphoto\", \n" +
                "                    \"name\": \"系统拍照发图\", \n" +
                "                    \"key\": \"rselfmenu_1_0\", \n" +
                "                   \"sub_button\": [ ]\n" +
                "                 }, \n" +
                "                {\n" +
                "                    \"type\": \"pic_photo_or_album\", \n" +
                "                    \"name\": \"拍照或者相册发图\", \n" +
                "                    \"key\": \"rselfmenu_1_1\", \n" +
                "                    \"sub_button\": [ ]\n" +
                "                }, \n" +
                "                {\n" +
                "                    \"type\": \"pic_weixin\", \n" +
                "                    \"name\": \"微信相册发图\", \n" +
                "                    \"key\": \"rselfmenu_1_2\", \n" +
                "                    \"sub_button\": [ ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }, \n" +
                "        {\n" +
                "            \"name\": \"发送位置\", \n" +
                "            \"type\": \"location_select\", \n" +
                "            \"key\": \"rselfmenu_2_0\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        menu.createMenu(toekn,params);
       // String str= map.get("menu").toString();
        //System.out.println(str);
    }
}