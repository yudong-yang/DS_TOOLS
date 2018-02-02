package cn.com.duiba.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.duiba.entity.AppDemo;
import cn.com.duiba.service.AppDemoService;

@Controller
@RequestMapping("/")
public class AppController {
	
	
	@Autowired
    private AppDemoService appDemoService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		return "cookie/login";
	}
	
	@RequestMapping("/iframedemo")
	public String demo(HttpServletRequest request) {
		return "iframedemo";
	}
	
	
	@RequestMapping(value = "/cookie" , method = RequestMethod.POST)
	public String cookie(HttpServletRequest request ,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username= request.getParameter("username");
			Cookie usernameCookie = new Cookie("username", URLEncoder.encode(username, "UTF-8"));
			Cookie visittimesCookie = new Cookie("visitTimes", "0");
			
			response.addCookie(usernameCookie);
			response.addCookie(visittimesCookie);
			
			return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response ,Model model) throws UnsupportedEncodingException {
		
		String username = "";
		int visitTimes = 0;
//		username = request.getParameter("username");
		// 所有的 cookie
		Cookie[] cookies = request.getCookies();
		
		// 遍历所有的 Cookie 寻找 用户帐号信息与登录次数信息
		for(int i=0; cookies!=null&&i<cookies.length; i++){
			Cookie cookie = cookies[i];
			if("username".equals(cookie.getName())){
				username = cookie.getValue();
				System.out.println(username);
				model.addAttribute("username", URLDecoder.decode(username, "UTF-8"));
			}
			else if("visitTimes".equals(cookie.getName())){
				visitTimes = Integer.parseInt(cookie.getValue());
				System.out.println("visits==:"+visitTimes);
				cookie.setValue("" + ++visitTimes);
			}
		}
		
		// 如果没有找到 Cookie 中保存的用户名，则转到登录界面
		if(username == null || username.trim().equals("")){
			model.addAttribute("errmsg", "你还没有登录");
		}
		
		// 修改 Cookie，更新用户的访问次数
		Cookie visitTimesCookie = new Cookie("visitTimes", Integer.toString(visitTimes));
		response.addCookie(visitTimesCookie);
//		Cookie vVSCookie = new Cookie("visitTimes", "5");
		System.out.println(visitTimes);
		model.addAttribute("visitTimesCookie", visitTimes);
		return "cookie/cookie";
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
