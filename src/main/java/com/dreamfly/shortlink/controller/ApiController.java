package com.dreamfly.shortlink.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import com.google.gson.JsonObject;
import com.dreamfly.shortlink.google.util;
import com.dreamfly.shortlink.entity.db;
import com.dreamfly.shortlink.services.service;

@RestController // 标明这是一个SpringMVC的Controller控制器
@RequestMapping("/")
public class ApiController {

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
        Integer n = service.insert(u_id, o_url, url);
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
                return new RedirectView("sorry");
            }
        }else {
            return new RedirectView("sorry");
        }
    }

}
