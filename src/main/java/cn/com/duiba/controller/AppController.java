package cn.com.duiba.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.duiba.entity.AppDemo;
import cn.com.duiba.service.AppDemoService;

@Controller
@RequestMapping("/")
public class AppController {
	
	
	@Autowired
    private AppDemoService appDemoService;
	
	@RequestMapping("/share")
	public String index(HttpServletRequest request) {
		return "test/Sharedemo";
	}
	
	@RequestMapping("/iframedemo")
	public String demo(HttpServletRequest request) {
		return "iframedemo";
	}
	
	
	
	@RequestMapping("/fpmiddle")
	public String fpmiddle(HttpServletRequest request) {
		return "fp_middle/middlepage";
	}
	

	
	
	
	@RequestMapping("/err")
	@ResponseBody
	public String test(HttpServletRequest request) {
		return "http request weixin openid error";
	}	
	
	@RequestMapping("/app")
	
	public String app(HttpServletRequest request) {
		return "duiba/appurl";
	}
	
/*	@RequestMapping("/appurl")
	@ResponseBody
	public String appUrl(HttpServletRequest request) {
		String appname = request.getParameter("appname");
		String appkey = request.getParameter("appkey");
		String appsecret = request.getParameter("appsecret");
		Map<String, String> map = new LinkedHashMap<String, String>();  
		map.put(appname+".appKey", appkey);
		map.put(appname+".appSecret", appsecret);
		PropertiesLoader.GetPropertiesValue(appname+"appkey");
		
		PropertiesLoader.updateProperties("KeySecretMap.properties", map);
		
		String creditsUrl = "http://yang.s1.natapp.cc/duiba/consume/"+appname;
		String notifyUrl = "http://yang.s1.natapp.cc/duiba/notify/"+appname;
		String redirectUrl = "http://yang.s1.natapp.cc/duiba/autologin2/"+appname+"?";
		return creditsUrl+"=="+notifyUrl+"=="+redirectUrl;
	}*/
	
	
	
	@RequestMapping("/appurl")
	@ResponseBody
	public String appUrl(HttpServletRequest request) {
		String appname = request.getParameter("appname").trim();
		String appcode = request.getParameter("appcode").trim();
		String appkey = request.getParameter("appkey").trim();
		String appsecret = request.getParameter("appsecret").trim();
	    AppDemo app = new AppDemo();
		app.setAppname(appname);
		app.setAppkey(appkey);
		app.setAppsectet(appsecret);
		app.setAppcode(appcode);
		if(appDemoService.findByAppCode(appcode).isEmpty()){
			appDemoService.insert(app);
		}
		return appDemoService.urlToString(appcode);	
//			return "账号标示已存在";
	}
	
	@RequestMapping("/testJson")
	@ResponseBody
	public String testJson(HttpServletRequest request) {
		return "{\"name\":\"yangyudong\",\"age\":\"23\"}";
	}
}
