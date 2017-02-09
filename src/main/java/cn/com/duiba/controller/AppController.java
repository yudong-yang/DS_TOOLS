package cn.com.duiba.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class AppController {
	
	@RequestMapping("/")
	
	public String index(HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping("/err")
	@ResponseBody
	public String test(HttpServletRequest request) {
		return "http request weixin openid error";
	}
}
