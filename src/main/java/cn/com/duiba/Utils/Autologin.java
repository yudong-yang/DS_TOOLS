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
		CreditTool tool=new CreditTool("3oXxTaJ8txkUPJZmhK4xKa6sxDJ3", "41Lr1JgG4vpHH34SHJbHd3aTjYVH"); //掌阅
//		CreditTool tool=new CreditTool("Tb1kbsySL552ZJupmtiAh7iGbfJ", "6pkVko2WybzJNi8EnLf6bYvdKwb");
//		CreditTool tool=new CreditTool("3MnzhxdJ9yNsHfALjJc9YaVRbgY4", "2CkusGvHpKwacZjoFmdHZHTnQhc4");//蘑菇街
//		visitDstTime=1579898070489
//		CreditTool tool=new CreditTool("3MnzhxdJ9yNsHfALjJc9YaVRbgY4", "2CkusGvHpKwacZjoFmdHZHTnQhc4"); 
//		CreditTool tool=new CreditTool("E1kBGx6bfYj1HtX9e8MPKfkEqsY", "3zEURgDuJLgDnDAymW35H6M6pUiN");	
		Map<String, String> params=new HashMap<String, String>();
		params.put("uid","rit_eI81N2hA8YFVLZ9nxg==:be39534a12929643a4880df809f72b41b5178409");
		params.put("credits","72");
//		params.put("dcustom","status=0&btnText=签到");
		params.put("tck_chl_9dd","ljVXtJdyNwca0ArD7-Nccg==");
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
		params.put("signKeys","uid|tck_chl_9dd|credits|appKey|timestamp|redirect|");
		
		String redirect = "https://weact.m.duiba.com.cn/newtools/index?id=2357386";
		params.put("redirect",redirect);
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
