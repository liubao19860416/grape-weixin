package org.hamster.weixinmp.controller;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxMessageService;
import org.hamster.weixinmp.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meidusa.fastjson.JSON;
import com.saike.grape.controller.BaseController;

/**
 * 只有一个POST和一个GET方法请求时,可以省略对应的请求映射路径@RequestMapping的value值信息
 * @author Liubao
 * @2015年7月9日
 *
 */
@Controller
@RequestMapping("/rest/weixinmp")
public class WxController extends BaseController<WxController>{
    private static final Logger logger = Logger.getLogger(WxController.class);

    @Autowired
    private WxAuthService authService;
    
    @Autowired
    private WxMessageService messageService;

    /**
     * 验证微信接口是否调用通过的验证接口
     */
    //@RequestMapping(value = "/0", method = { RequestMethod.GET })
    @RequestMapping(method = {RequestMethod.GET})
    public @ResponseBody String authGet(@RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("echostr") String echostr) throws WxException {
        
        String message = getMessage("10002");
        
        if (authService.validateAuth(signature, timestamp, nonce, echostr)) {
            logger.info("received authentication message from Weixin Server.");
            return echostr;
        }
        return null;
    }

    /**
     * 微信服务最终调用的POST接口
     */
    //@RequestMapping(value = "/1", method = RequestMethod.POST)
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    String messagePost(@RequestBody String requestXMLBody) throws DocumentException,WxException {
        WxBaseMsgEntity msg = messageService.parseXML(requestXMLBody);
        logger.info("请求字符串信息:@RequestBody " + requestXMLBody + " message.");
        logger.info("received " + msg.getMsgType() + " message.");
        WxBaseRespEntity resp = messageService.handleMessage(msg);
        logger.info("响应实体信息:@resp " + JSON.toJSONString(resp) + " message.");
        return messageService.parseRespXML(resp).asXML();
    }

}
