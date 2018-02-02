package cn.com.duiba.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CookieUtilController {

	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public String cookie(HttpServletRequest request ,HttpServletResponse response,Model model) throws UnsupportedEncodingException {
		String username= request.getParameter("username");
			Cookie usernameCookie = new Cookie("username", URLEncoder.encode(username, "UTF-8"));
			response.addCookie(usernameCookie);
			model.addAttribute("username", URLDecoder.decode(username, "UTF-8"));
			return "cookie/success";
	}
	

	
	@RequestMapping("/cookie")
	public String login(HttpServletRequest request,HttpServletResponse response ,Model model) throws UnsupportedEncodingException {	
		String username = "";
		Cookie[] cookies = request.getCookies();
		for(int i=0; cookies!=null&&i<cookies.length; i++){
			Cookie cookie = cookies[i];
			if("username".equals(cookie.getName())){
				username = cookie.getValue();
				model.addAttribute("username", URLDecoder.decode(username, "UTF-8"));
			}
		}
		System.out.println("username=="+username);
		if(username == null || username.trim().equals("")){
			model.addAttribute("errmsg", "你还没有登录");
			return "cookie/login";
		}		
		return "cookie/success";
	}
}
