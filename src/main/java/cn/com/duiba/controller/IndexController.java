package cn.com.duiba.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.duiba.Utils.GetAppkey;
import cn.com.duiba.Utils.PropertiesLoader;
import cn.com.duiba.ds.encrypt.BlowfishUtils;
import cn.com.duiba.ds.tools.buildUrl;
import cn.com.duiba.ds.tools.sdk.CreditTool;
import cn.com.duiba.ds.tools.sdk.SignTool;
import cn.com.duiba.service.UserService;
import cn.com.duiba.service.VirtualService;

@Controller
public class IndexController {
	private static final String secretKey = "Ziy66Kf";
	public static int pv = 0;
	public static int uv = 0;
	@Autowired
	private UserService userService;
	@Autowired
	private VirtualService virtualService;

	
	 @Autowired  
	 private GetAppkey getAppkey; 
	
	
	@RequestMapping("/test")
	public String fpTest() {
		return "fp_middle/fptest";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/sign")
	public String signTool() {
		return "duiba/signTool";
	}
	
	 @RequestMapping("/testapp")  
	    public @ResponseBody String test(){  
	        System.out.println(getAppkey.getAppKey()+"---"+getAppkey.getAppSecret());  
	        return "ok";  
	    }   
	
	 
	 @RequestMapping(value="/testPost", method = RequestMethod.POST)  
	    public @ResponseBody String testPost(HttpServletRequest request){  
		 String name = request.getParameter("name");
		 String sex = request.getParameter("sex");
	        return "{'name':"+name+",'sex':"+sex+"}";  
	    }   

	
	@RequestMapping("/signtools")
	@ResponseBody
		public String signtools(HttpServletRequest request) throws FileNotFoundException, UnsupportedEncodingException, IOException{
		 String URL = request.getParameter("url");
		 String appSecret = request.getParameter("appSecret"); 
		 Map<String, String> p =buildUrl.UrltoMap(URL, appSecret);
			String str= SignTool.signstring(p);
			String sign1=(String) p.get("sign");
			p.remove("sign");
			String sign = SignTool.sign(p);
			String msg=null;
			if(sign.endsWith(sign1)){
				msg="恭喜你，签名通过！";
			}else{
				msg="很遗憾，签名不通过！";
			}
	        return str+"$$"+p+"$$"+sign+"$$"+msg;  
		}
	
	
	@RequestMapping("/appAddtest")
	public String appAddtest(HttpServletRequest request ,Model model) {
		String appKey = request.getParameter("appKey");
		String appSectet = request.getParameter("appSectet");
		Map<String, String> map = new LinkedHashMap<String, String>();  
        map.put("aaa.appKey",appKey);  
        map.put("aaa.appSectet",appSectet);  
        System.out.println(map);
		PropertiesLoader.updateProperties("KeySecretMap.properties", map);
		model.addAttribute("url1","http://yang.s1.natapp.cc/duiba/consume/aaa");
		model.addAttribute("url2","http://yang.s1.natapp.cc/duiba/notify/aaa");
		return "url";
	}

	@RequestMapping("/autologin")
	public String autologin() {
		return "autologin";
	}

	@RequestMapping("/getAppSecret")
	@ResponseBody
	public String getAppSecret(HttpServletRequest request) {
		String appSecretCode = request.getParameter("secretcode");

		String appSecret = BlowfishUtils.decryptBlowfish(appSecretCode,
				secretKey);
		if (appSecret == null) {
			return "测试专用appsecret";
		} else {
			return appSecret;
		}
	}

	@RequestMapping("/geturl")
	@ResponseBody
	public String geturl(HttpServletRequest request)
			throws UnsupportedEncodingException {
		
			CreditTool tool = new CreditTool("jlg88lyxz7siqtmr",
					"1x0eap95f4xfi77uaptrnwh9ewzvlm"); // 115
			Map<String, String> params = new HashMap<String, String>();
			String uid = request.getParameter("uid");
			String credit = request.getParameter("credits");
			String dcustom = request.getParameter("dcustom");
			String redirect = request.getParameter("redirect");
			String demo = request.getParameter("rdo1");
			System.out.println("redirect==:"+redirect);
			String url1 = "";

			if (demo.equals("test")) {

				url1 = "http://m.dui88.com/autoLogin/autologin?";
			} else if ((demo.equals("normal"))) {
				url1 = "http://www.duiba.com.cn/autoLogin/autologin?";
			} else if ((demo.equals("pre"))) {
				url1 = "http://pre.duiba.com.cn/autoLogin/autologin?";
			}

			if (uid != null && uid != "") {

				params.put("uid", uid);
			} else {
				params.put("uid", "not_login");
			}

			if (credit != null && credit != "") {
				params.put("credits", credit);
			} else {
				params.put("credits", "0");
			}
			if (dcustom != null && dcustom != "") {
				params.put("dcustom", URLEncoder.encode(dcustom, "UTF-8"));
				// params.put("dcustom",dcustom);
			}
			if (redirect != null && redirect != "" && redirect != "null") {
				params.put("redirect", redirect);
			}

			String url = tool.buildUrlWithSign(url1, params);
			System.out.println(url);
			return url;
		}
	
	
	
	
	
	@RequestMapping("/autourl")
	@ResponseBody
	public String autourl(HttpServletRequest request)
			throws UnsupportedEncodingException {
		String appKey = request.getParameter("appKey");
		String appSecret = request.getParameter("appSecret");
			CreditTool tool = new CreditTool(appKey,appSecret); // 115
			Map<String, String> params = new HashMap<String, String>();
			String uid = request.getParameter("uid");
			String credits = request.getParameter("credits");
			String transfer = request.getParameter("transfer");
			if (uid != null && uid != "") {

				params.put("uid", uid);
			} else {
				params.put("uid", "not_login");
			}

			if (credits != null && credits != "") {
				params.put("credits", credits);
			} else {
				params.put("credits", "0");
			}
			if (transfer != null && transfer != "") {
				params.put("transfer", transfer);
			}
			long times = System.currentTimeMillis()+2400000;
			params.put("timestamp", times+"");
			String url = tool.buildUrlWithSign("http://www.duiba.com.cn/autoLogin/autologin?", params);
			System.out.println(url);
			return url;
		}
	

	@RequestMapping("/cookievisits")
	public String cookievisits() {
		return "cookievisits";
	}

	@RequestMapping("/getvisits")
	public String getvisits(HttpServletRequest request,
			@CookieValue("num") String cookvalue) {
		int sum = ++pv;
		// int i= Integer.parseInt(cookvalue, 10);
		if (cookvalue.equals("1")) {
			int num = ++uv;
			System.out.println("（1）PV = :" + sum + "==||==UV = " + num);
		} else {
			System.out.println("（2）PV = :" + pv + " ==||==  UV = :" + uv);
		}
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
		/*
		 * int [] arr = {1,2,3,4}; model.addAttribute("arr", arr);
		 */
		return "virtual";
	}
}