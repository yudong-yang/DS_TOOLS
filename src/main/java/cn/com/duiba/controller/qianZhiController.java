package cn.com.duiba.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.duiba.ds.tools.buildUrl;

@Controller
public class qianZhiController  {
	


	@RequestMapping("/qianzhi")
    public String qianzhi(Model model, HttpServletResponse response) {
		response.addCookie(new Cookie("cook", "3333"));
		String url= buildUrl.getQianzhiUrl("50");
		String body = buildUrl.sendGet(url);
		JSONObject oAuthResJson  = JSON.parseObject(body);
		JSONArray data = JSON.parseArray(oAuthResJson.getString("data"));
    	 model.addAttribute("data", data);
         return "qianzhi";
    } 
	
	@RequestMapping("/itemList")
    public String itemlist(Model model, HttpServletResponse response) {
		
		response.addCookie(new Cookie("cook", "3333"));
		String url= buildUrl.getItemList();
		String body = buildUrl.sendGet(url);
		JSONObject oAuthResJson  = JSON.parseObject(body);
		JSONArray data = JSON.parseArray(oAuthResJson.getString("data"));
    	 model.addAttribute("data", data);
         return "qianzhi";
    }
}