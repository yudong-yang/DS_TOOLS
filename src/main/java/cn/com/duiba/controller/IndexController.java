package cn.com.duiba.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.duiba.ds.encrypt.BlowfishUtils;
import cn.com.duiba.ds.tools.sdk.CreditTool;
import cn.com.duiba.entity.User;
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

	@RequestMapping("/test")
	public String fpTest() {
		return "fp_middle/fptest";
	}

	@RequestMapping("/index")
	public String index() {

		return "index";
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
		{
			CreditTool tool = new CreditTool("jlg88lyxz7siqtmr",
					"1x0eap95f4xfi77uaptrnwh9ewzvlm"); // 115
			Map<String, String> params = new HashMap<String, String>();
			String uid = request.getParameter("uid");
			String credit = request.getParameter("credits");
			String dcustom = request.getParameter("dcustom");
			String demo = request.getParameter("rdo1");

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
			String redirect = "";
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