package cn.com.duiba.Zice;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;

public class Autologin {
	
	public static void main(String[] args) throws UnsupportedEncodingException {

	//	CreditTool tool=new CreditTool("3pNyWfK2N3hF5n68Qawg2hLcvVok", "57np8h4aCgs8LfzhyzpiX7nu2MU"); //酷我测试环境~   ~酷我-测试	18806/34337
//		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 
//		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 	
		CreditTool tool = new CreditTool("21bPuGyabWsbjFAxtUBbbMqDSX1a","34mpqvaceTjkcYewPBrBiAWC5YWv"); 	
		Map<String, String> params=new HashMap<String, String>();
		params.put("uid","222");
		params.put("credits","8");
		params.put("signKeys","appKey|appSecret|uid|credits|redirect");	
		String redirect = "";
	if(redirect!=null&&redirect!=""&&redirect != "null")
			params.put("redirect",redirect); 
		
		String url=tool.buildUrlWithSign("http://www.duiba.com.cn/autoLogin/autologin?",params);
		System.out.println(url);
	
	}

}
