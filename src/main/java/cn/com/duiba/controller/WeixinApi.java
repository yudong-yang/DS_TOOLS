package cn.com.duiba.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.duiba.ds.tools.wxsdk.AesException;
import cn.com.duiba.ds.tools.wxsdk.weixinTool;

@Controller
@RequestMapping("/weixin")
public class WeixinApi {
	
	private static Logger log = LoggerFactory.getLogger(WeixinApi.class);
	
	@Value("${weixin.token}")
	private String token;

	@RequestMapping("/access")
	@ResponseBody
	public String access(HttpServletRequest request) throws  AesException {
		
		Map<String, String> params = weixinTool.weiXinaccess(request);
		String sign = weixinTool.singwithToken(token,params);
		System.out.println("签名串=="+sign+"====="+"请求中的签名"+params.get("signature"));
		if(sign.endsWith(params.get("signature"))){
			System.out.println(sign);
			return params.get("echostr");
			
		}else {
			System.out.println("签名不通过");
			return null;
		}
		
}

	
	@RequestMapping("/getCode")
	@ResponseBody
	public String getCode(HttpServletRequest request) {
		request.getAttribute("code");
		request.getAttribute("STATE");
		return token;
	
		
}
	
}