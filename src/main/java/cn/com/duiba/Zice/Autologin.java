package cn.com.duiba.Zice;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;

public class Autologin {
	
	public static void main(String[] args) throws UnsupportedEncodingException {

		//CreditTool tool=new CreditTool("T8Zi4hotfreHvNWxTWGPnxp19tu", "3ND1C4xa25ofttLZBMjdsHGpV4V6"); 
		CreditTool tool=new CreditTool("jlg88lyxz7siqtmr", "1x0eap95f4xfi77uaptrnwh9ewzvlm");
		Map<String, String> params=new HashMap<String, String>();
		params.put("uid","test");
		params.put("credits","0");
		params.put("dcustom","avatar=http://yun.duiba.com.cn/duibaManagerWeb/7fen28siy4.png&nickname=xiaoming");
		
	String redirect="";
	
   // redirect = "http://activity.m.duiba.com.cn/newtools/index?id=2675067";
	if(redirect!=null&&redirect!=""&&redirect != "null")
			params.put("redirect",redirect); 
		
	String url=tool.buildUrlWithSign("https://home.m.duiba.com.cn/autoLogin/autologin?",params);
     System.out.println(url);
	}
}





