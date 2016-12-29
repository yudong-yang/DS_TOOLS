package cn.com.duiba.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.duiba.entity.User;
import cn.com.duiba.service.UserService;
import cn.com.duiba.service.VirtualService;
@Controller
public class IndexController  {
	
   public static int pv = 0;
   public static int uv = 0;
	@Autowired
    private UserService userService;
	@Autowired
    private VirtualService virtualService;
	
	@RequestMapping("/test")
	public String fpTest(){
		return "fp_middle/fptest";
	}
	@RequestMapping("/index")
	public String index(){
		
		return "index";
	}
	
	@RequestMapping("/list")
    public String list(Model model) {
		List<User> list = userService.getList();
    	 model.addAttribute("users", list);
    	 int [] arr = {1,2,3,4};
    	 model.addAttribute("arr", arr);
         return "list";
    } 
	
	@RequestMapping("/autologin")
    public String autologin() {	
         return "autologin";
    } 
	
	
	@RequestMapping("/cookievisits")
    public String cookievisits() {
         return "cookievisits";
    } 
	
	
	@RequestMapping("/getvisits")
    public String getvisits(HttpServletRequest request,@CookieValue("num") String cookvalue) {
		int sum = ++pv;
//		int i= Integer.parseInt(cookvalue, 10);
		if(cookvalue.equals("1")){
		int num= ++uv;
		System.out.println("（1）PV = :"+sum+"==||==UV = "+num);
		}
		else{System.out.println("（2）PV = :"+pv+" ==||==  UV = :"+uv);}
         return "cookievisits";
    } 
	

	@RequestMapping("/midellogin")
    public String midellogin() {	
         return "loginPage";
    } 
	
	@RequestMapping("/virtual")
    public String virtual(Model model) {
		Long credits = virtualService.getCredits("11");
    	 model.addAttribute("credits", credits);
    	 /*int [] arr = {1,2,3,4};
    	 model.addAttribute("arr", arr);*/
         return "virtual";
    } 
}