package cn.com.duiba.Zice;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;

public class Autologin {
	
	public static void main(String[] args) throws UnsupportedEncodingException {

		CreditTool tool=new CreditTool("3pNyWfK2N3hF5n68Qawg2hLcvVok", "57np8h4aCgs8LfzhyzpiX7nu2MU"); //酷我测试环境~   ~酷我-测试	18806/34337
//		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 
//		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 
		Map<String, String> params=new HashMap<String, String>();
		params.put("uid","200306800");
		params.put("credits","0");
//		params.put("bizId","act-"+System.currentTimeMillis());
//		params.put("times","1");
//		params.put("actid","2392806");
//		params.put("vip","0");
		String redirect = "http://trade.m.duiba.com.cn/Crecord/recordNotifyRedirect?id=2795319412";
	if(redirect!=null&&redirect!=""&&redirect != "null")
			params.put("redirect",redirect); 
		
	String url=tool.buildUrlWithSign("https://www.duiba.com.cn/autoLogin/autologin?",params);
     System.out.println(url);
	
	}

}
