package com.saike.grape.controller.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.saike.grape.controller.BaseController;
import com.saike.grape.util.ResultInfo;
import com.saike.grape.util.ResultInfoUtil;
import com.saike.grape.util.SignUtil;

/**
 * 基础测试controller
 * @author Liubao
 * @2015年6月2日
 *
 */
@Controller
@RequestMapping(value = "/basic")
public class BasicTestController extends BaseController<BasicTestController> {
    
    @RequestMapping(value = "/test/0", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody ResultInfo getTestJson() {
        getLogger().info("request: /basic/test/0");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "liubao");
        map.put("password", "123456");
        String message = super.getMessage("user.not.login");
        map.put("message",message);
        return ResultInfoUtil.setSuccessInfo(map);
    }
    
    @RequestMapping(value = "/index/0", method = { RequestMethod.POST, RequestMethod.GET })
    public String toIndex0(Model model) {
        getLogger().info("request: index/0");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "liubao1");
        map.put("password", "1234567");
        String message = super.getMessage("user.not.login");
        map.put("message",message);
        model.addAllAttributes(map);
        model.addAttribute("password", "12345678");
        return "index";
    }
    
    @RequestMapping(value = "/index/1", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView toIndex1(HttpServletRequest request,HttpServletResponse response) {
        getLogger().info("request: index/1");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("username", "刘保");
        modelAndView.addObject("password", "1234568");
        return modelAndView;
    }
    
    /**
     * 绑定其他数据,key=JSESSIONID
     */
    @RequestMapping(value = "/sessionId")
    public String sessionId(HttpSession s, @RequestHeader("accept") String accept,
            @CookieValue("JSESSIONID") String sessionId) {
        System.err.println("accept:" + accept + ",sessionid:" + sessionId);
        System.err.println("sess is :" + s.getId());
        return "itcast";
    }
    
    /**
     * 下载模版
     */
    @RequestMapping(value = "/downloadFile")
    public void downloadFile(OutputStream out, HttpSession s, HttpServletResponse resp)
            throws Exception {
        System.err.println("out is :" + out);
        String path = s.getServletContext().getRealPath("/download/template.doc");
        resp.setContentType("application/force-download");
        resp.setHeader("Content-Disposition", "attatchment;filename=a.doc");
        InputStream in = new FileInputStream(path);
        Streams.copy(in, out, true);
    }
    
    /**
     * 接收文件上传的 FileItem
     * @param file
     * @return
     * @throws Exception
     */
   @RequestMapping(value = "/upload")
   public String upload(@RequestParam("fileName") MultipartFile fileName, HttpSession session) throws Exception {
       System.err.println("fileName:" + fileName.getOriginalFilename());
       String path = session.getServletContext().getRealPath("/upload");
       Streams.copy(fileName.getInputStream(), new FileOutputStream(path + "/"
               + fileName.getOriginalFilename()), true);
       return "upload";
   }
   
   @RequestMapping(value = "/upload/index")
   public String toUpload() throws Exception {
       return "upload";
   }
   
   @RequestMapping(value = "/multiUpload/index")
   public String toMultiUpload() throws Exception {
       return "multiUpload";
   }
   
   /**
    * 上传多个文件
    */
   @RequestMapping(value = "/multiUpload")
   public String toMultiUpload(@RequestParam("fileName") List<MultipartFile> fileNames, HttpSession session)
           throws Exception {
       System.err.println(">>>>"+fileNames.size());
       String path = session.getServletContext().getRealPath("/upload");
       for (MultipartFile file : fileNames) {
           Streams.copy(file.getInputStream(), new FileOutputStream(path + "/"
                   + file.getOriginalFilename()), true);
       }
       return "multiUpload";
   }

}
