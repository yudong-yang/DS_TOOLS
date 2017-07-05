package cn.com.duiba.Utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;






public class Autologin {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
//		CreditTool tool=new CreditTool("mJ8z2yCMDPbSJrN11Y5jcPMhXmY", "fZiCYZcyqJo8hidEf2NYtwJaKjX"); 
//		CreditTool tool=new CreditTool("jlg88lyxz7siqtmr", "1x0eap95f4xfi77uaptrnwh9ewzvlm");
//		CreditTool tool=new CreditTool("4JyqhUdCevCFUFWKRNwEKfNKmefN", "3fdv8oRZnkpmjhuCmarrzhxaAVLT"); 
//		CreditTool tool=new CreditTool("4NQE3wSd9vxCm8BqPBp1NWNXHf1E", "2nYmEH9QSfnA6AVhcjerGXPt52DL");
//		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 
//		CreditTool tool=new CreditTool("Tb1kbsySL552ZJupmtiAh7iGbfJ", "6pkVko2WybzJNi8EnLf6bYvdKwb");
//		CreditTool tool=new CreditTool("3MnzhxdJ9yNsHfALjJc9YaVRbgY4", "2CkusGvHpKwacZjoFmdHZHTnQhc4");//蘑菇街
//		visitDstTime=1579898070489
		CreditTool tool=new CreditTool("3MnzhxdJ9yNsHfALjJc9YaVRbgY4", "2CkusGvHpKwacZjoFmdHZHTnQhc4"); 
//		CreditTool tool=new CreditTool("E1kBGx6bfYj1HtX9e8MPKfkEqsY", "3zEURgDuJLgDnDAymW35H6M6pUiN");	
		Map<String, String> params=new HashMap<String, String>();
		params.put("uid","5YmRIV2YuN00qBTIHpb7lQ==");
		params.put("credits","657");
//		params.put("dcustom","status=0&btnText=签到");
		params.put("transfer","7B83AF80D2781B0D7890B683DEACE8A2");
//		params.put("vip","1234");
		
//		params.put("alipay","panzhiyong900@163.com");
//		params.put("realname","潘志勇");
//		params.put("qq","823458789");
//		params.put("phone","182****9680");
//		params.put("test","001");
//		params.put("test1","002");
//		params.put("test03","003");
//		params.put("appSecret","002");
//		params.put("timestamp","1499145755349");
//		params.put("signKeys","test|test1");
		
//		String redirect = "https://activity.m.duiba.com.cn/newtools/index?id=2310631";
		params.put("redirect","");
/*	if(redirect!=null&&redirect!=""&&redirect != "null"){
			
			System.out.println(redirect);
			params.put("redirect",redirect);
		 
		}*/

	
	
	String url=tool.buildUrlWithSign("https://www.duiba.com.cn/autoLogin/autologin?",params);
//	String url=tool.buildUrlWithSign("http://pre.duiba.com.cn/autoLogin/autologin?",params);
//	String url=tool.buildUrlWithSign("http://home.m.dui88.com:80/autoLogin/autologin?",params);//测试环境
//	String url=tool.buildUrlWithSign("http://pre.duiba.com.cn/autoLogin/autologin?",params);
     System.out.println(url);
	
	}

}
