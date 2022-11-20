package App.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.JsonObject;

import App.google.util;
import App.entity.db;
import App.services.service;

@SpringBootApplication // Spring Boot项目的核心注解，主要目的是开启自动配置
@RestController // 标明这是一个SpringMVC的Controller控制器
@RequestMapping("/")
public class ApiController {
    @GetMapping(value = "/sorry", produces = {MediaType.TEXT_HTML_VALUE})
    public String sorry(){
        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<!DOCTYPE html><html><head>	<title>网页走丢了</title>	<meta charset=\"utf-8\">	<meta name=\"viewport\"	content=\"user-scalable=no,width=device-width,initial-scale=1.0,maximum-scale=1.0\"></head><body><div id=\"content_left\"><div class=\"nors\"><div class=\"norsSuggest\"><h3 class=\"norsTitle\">很抱歉，您要访问的页面不存在！</h3><p class=\"norsTitle2\">温馨提示：</p><ol><li>请检查您访问的网址是否正确</li><li>如果您不能确认访问的网址，或有任何意见或建议，请及时发送邮件:<span>admin@dfvips.com</span>反馈给我们。</li></ol></div></div></div></body><style data-for=\"result\">        body {color: #333; background: #fff; padding: 0; margin: 0; position: relative; min-width: 700px; font-family: arial; font-size: 12px }        p, form, ol, ul, li, dl, dt, dd, h3 {margin: 0; padding: 0; list-style: none }        a{        	text-decoration: none;        }        h3{color: #000}        #content_left {width: 540px; padding-left: 121px; padding-top: 5px }        #content_left {margin-bottom: 14px; padding-bottom: 5px; }        .norsSuggest {display: inline-block; color: #333; font-family: arial; font-size: 13px; position: relative; }         .norsTitle {font-size: 22px; font-family: Microsoft Yahei; font-weight: normal; color: #333; margin: 35px 0 25px 0; }        .norsTitle2 {font-family: arial; font-size: 13px; color: #666; }        .norsSuggest ol {margin-left: 47px; }        .norsSuggest li {margin: 13px 0; }        span{        	color: blue;        }</style></html>");
        return sbHtml.toString();
    }

    @GetMapping(value = "/Api/{o_url}")
    public String Api(@PathVariable String o_url) {
        try {
            o_url=URLDecoder.decode(o_url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String u_id = util.hash32(o_url);
        String url = "https://2cn.dev/"+u_id;
        Integer is_exit = service.search(u_id).size();
        Integer n = 0;
        if(is_exit == 0){
            n = service.insert(u_id, o_url, url);
        }else {
            n = 1;
        }
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("success", "true");
//		System.out.println(n);
        if (n==1||n==0) {
            jsonobj.addProperty("status", 200);
            jsonobj.addProperty("url", url);
            jsonobj.addProperty("o_url",o_url);
        }else {
            jsonobj.addProperty("status", 500);
        }
        return jsonobj.toString();
    }

    @GetMapping(value = "/{id}")
    public RedirectView getid(@PathVariable String id) {
        if(!id.equals("sorry")) {
            List<db> list = service.search(id);
//		    System.out.println(list.size());
            if (list.size()>0) {
                String url = list.get(0).getO_url();
                return new RedirectView(url);
            }else {
                return new RedirectView("/sorry");
            }
        }else {
            return new RedirectView("/sorry");
        }
    }

}