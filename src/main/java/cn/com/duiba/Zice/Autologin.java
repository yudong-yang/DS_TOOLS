package cn.com.duiba.Zice;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;

public class Autologin {
	
	public static void main(String[] args) throws UnsupportedEncodingException {

		CreditTool tool=new CreditTool("v39mFR123jm3KuA7vmDJ2FzdzRR", "3CuutYrtVjskq3xHNDNUunJQLCTe"); 
//		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 
//		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 
		Map<String, String> params=new HashMap<String, String>();
		params.put("uid","test07");
		params.put("credits","100");
//		params.put("bizid","act-"+System.currentTimeMillis());
//		params.put("times","2");
//		params.put("actid","2221001");
		String redirect = "";
	if(redirect!=null&&redirect!=""&&redirect != "null")
			params.put("redirect",redirect); 
		
	String url=tool.buildUrlWithSign("https://www.duiba.com.cn/autoLogin/autologin?",params);
     System.out.println(url);
	
	}

}
