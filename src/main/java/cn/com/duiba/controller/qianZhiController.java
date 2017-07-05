package cn.com.duiba.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.duiba.ds.tools.buildUrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
public class qianZhiController  {
	


	@RequestMapping("/qianzhi")
    public String qianzhi(HttpServletRequest request, Model model, HttpServletResponse response) {
		String num = request.getParameter("num");
		if(num==null||num==""){
			num="10";
		}
		response.addCookie(new Cookie("cook", "3333"));
		
		String url= buildUrl.getQianzhiUrl(num);
//		String url= buildUrl.getPCDatalist("1","4444",num);
		System.out.println("前置商品URL=="+url);
		String body = buildUrl.sendGet(url);
		System.out.println("data==:"+body);
		JSONObject JsonBody  = JSON.parseObject(body);
		JSONArray data = JSON.parseArray(JsonBody.getString("data"));
//		String message = (String) JSON.parse(JsonBody.getString("message")); 
//		Object status = JSON.parse(JsonBody.getString("success"));
//		System.out.println("status==:"+status+"===message==:"+message);
		model.addAttribute("url", url);
    	model.addAttribute("data", data);
         return "duiba/qianzhi";
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
	
	
	
	@RequestMapping("/pclist")
    public String pclist(HttpServletRequest request, Model model, HttpServletResponse response) {
		String num = request.getParameter("num");
		if(num==null||num==""){
			num="8";
		}
		response.addCookie(new Cookie("cook", "3333"));
		String uid = request.getParameter("uid");
		if(uid==null||uid==""){
			uid="3333";
		}
		String url= buildUrl.getPCDatalist("1",uid,num);
		System.out.println("PC积分商城=="+url);
		String body = buildUrl.sendGet(url);
		System.out.println("data==:"+body);
		JSONObject JsonBody  = JSON.parseObject(body);
		JSONArray data = JSON.parseArray(JsonBody.getString("data"));
		model.addAttribute("url", url);
    	model.addAttribute("data", data);
         return "duiba/pclist";
    } 
}