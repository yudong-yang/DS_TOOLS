package cn.com.duiba.ds.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;






public class Autologin {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		CreditTool tool=new CreditTool("jlg88lyxz7siqtmr", "1x0eap95f4xfi77uaptrnwh9ewzvlm");
//		CreditTool tool=new CreditTool("4JyqhUdCevCFUFWKRNwEKfNKmefN", "3fdv8oRZnkpmjhuCmarrzhxaAVLT"); 
//		CreditTool tool=new CreditTool("LBkgPbiAoeWbhVKhJF3bsiJcXVC", "3DNTUFyTUEzuYDL6NPaE47Ef4cpB");
		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 
//		CreditTool tool=new CreditTool("3LYfsV4NfNvbG1i3FTtTR6kxSXCt", "3iqx1RCxMVXY74rhXYPbuYXCs9Db");
//		CreditTool tool=new CreditTool("3QbH44LBS6WiJ6hPJfetS2A83Vvs", "2eaKi5Tgm4FD6V62bPayJuG9NcQZ");
//		visitDstTime=1579898070489
//		CreditTool tool=new CreditTool("2sJxES2gMD8SVtFcurUoEZJUSpnp", "3tHLewFAHSgixVp4SFQJ8tPUWrTG"); 
//		CreditTool tool=new CreditTool("2wwNpHTbV12BgWqQVDAwTTQ67fpk ", "2nUXyMRdKFPTKanL8JTXR6TTtFWH");	
		Map<String, String> params=new HashMap<String, String>();
		params.put("uid","4444");
		params.put("credits","1100");
//		params.put("dcustom",URLEncoder.encode("status=0&token=asdfadsf","UTF-8"));
		params.put("transfer","transfer");
//		params.put("vip","10");
		params.put("alipay","876945996@qq.com");
		params.put("realname","杨东");
//		params.put("qq","823458789");
//		params.put("phone","823458789");
//		params.put("test","001");
//		params.put("appSecret","002");
		params.put("timestamp","1598596085000");
//		params.put("signKeys","test|test1");
		
		String redirect = "";
		
	if(redirect!=null&&redirect!=""&&redirect != "null"){
			
			System.out.println(redirect);
			params.put("redirect",redirect);
		 
		}

	
	
	String url=tool.buildUrlWithSign("https://www.duiba.com.cn/autoLogin/autologin?",params);
//	String url=tool.buildUrlWithSign("http://pre.duiba.com.cn/autoLogin/autologin?",params);
//	String url=tool.buildUrlWithSign("http://m.dui88.com/autoLogin/autologin?",params);//测试环境
//	String url=tool.buildUrlWithSign("http://pre.duiba.com.cn/autoLogin/autologin?",params);
     System.out.println(url);
	
	}

}
