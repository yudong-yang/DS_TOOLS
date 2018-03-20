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
//		CreditTool tool=new CreditTool("3oXxTaJ8txkUPJZmhK4xKa6sxDJ3", "41Lr1JgG4vpHH34SHJbHd3aTjYVH"); //掌阅
//		CreditTool tool=new CreditTool("Tb1kbsySL552ZJupmtiAh7iGbfJ", "6pkVko2WybzJNi8EnLf6bYvdKwb");
//		CreditTool tool=new CreditTool("3MnzhxdJ9yNsHfALjJc9YaVRbgY4", "2CkusGvHpKwacZjoFmdHZHTnQhc4");//蘑菇街

		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 
//		CreditTool tool=new CreditTool("E1kBGx6bfYj1HtX9e8MPKfkEqsY", "3zEURgDuJLgDnDAymW35H6M6pUiN");	
		Map<String, String> params=new HashMap<String, String>();
		params.put("uid","not_login");
		params.put("credits","0");
		params.put("transfer","weixin");
//		params.put("bizid","act-"+System.currentTimeMillis());
//		params.put("times","2");
//		params.put("actid","2221001");
//		params.put("alipay","&panzhiyong900＠163.com");
//		params.put("realname","阿斯顿潘志勇");
		params.put("timestamp", System.currentTimeMillis()+"00");
//		params.put("phone","182****9680");
		String redirect = "";
	if(redirect!=null&&redirect!=""&&redirect != "null")
			params.put("redirect",redirect); 
		
	String url=tool.buildUrlWithSign("https://www.duiba.com.cn/autoLogin/autologin?",params);
     System.out.println(url);
	
	}

}
