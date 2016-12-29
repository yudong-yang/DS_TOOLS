package cn.com.duiba.ds.tools.wxsdk;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class weixinTool {

	
	public static String singwithToken(String Token,Map<String, String> params){
		String timestamp= params.get("timestamp");
		String nonce=params.get("nonce");
		String token =Token;
		String sign="";
		try {
			sign = SHA1.getSHA1(token, timestamp, nonce);
		} catch (AesException e) {
			System.out.println("签名失败");
			e.printStackTrace();
		}
		return sign;
		
	}

	public static Map<String, String> weiXinaccess(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("timestamp", request.getParameter("timestamp"));
		params.put("signature", request.getParameter("signature"));
		params.put("echostr", request.getParameter("echostr"));
		params.put("nonce", request.getParameter("nonce"));
		return params;
	}
}
