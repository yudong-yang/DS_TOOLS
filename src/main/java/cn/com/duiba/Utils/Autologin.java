package cn.com.duiba.Utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;



/**
 * AppKey：T8Zi4hotfreHvNWxTWGPnxp19tu
AppSecret：3ND1C4xa25ofttLZBMjdsHGpV4V6
 * @author Administrator
 *
 */


public class Autologin {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
//		CreditTool tool=new CreditTool("mJ8z2yCMDPbSJrN11Y5jcPMhXmY", "fZiCYZcyqJo8hidEf2NYtwJaKjX"); 
//		CreditTool tool=new CreditTool("jlg88lyxz7siqtmr", "1x0eap95f4xfi77uaptrnwh9ewzvlm");
//		CreditTool tool=new CreditTool("4JyqhUdCevCFUFWKRNwEKfNKmefN", "3fdv8oRZnkpmjhuCmarrzhxaAVLT"); 
//		CreditTool tool=new CreditTool("4NQE3wSd9vxCm8BqPBp1NWNXHf1E", "2nYmEH9QSfnA6AVhcjerGXPt52DL");
//		CreditTool tool=new CreditTool("3oXxTaJ8txkUPJZmhK4xKa6sxDJ3", "41Lr1JgG4vpHH34SHJbHd3aTjYVH"); //掌阅
//		CreditTool tool=new CreditTool("Tb1kbsySL552ZJupmtiAh7iGbfJ", "6pkVko2WybzJNi8EnLf6bYvdKwb");
//		CreditTool tool=new CreditTool("3MnzhxdJ9yNsHfALjJc9YaVRbgY4", "2CkusGvHpKwacZjoFmdHZHTnQhc4");//蘑菇街
		CreditTool tool=new CreditTool("T8Zi4hotfreHvNWxTWGPnxp19tu", "3ND1C4xa25ofttLZBMjdsHGpV4V6");	//杨玉东--内测专用
//		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v"); 
//		CreditTool tool=new CreditTool("2E23HXJD9PzgakhfxdfFtyfbFajr", "2erj9VMKRV3aFMswaWFcidrGiYX2");	
		Map<String, String> params=new HashMap<String, String>();
		params.put("uid","qaviptest");
		params.put("credits","0");
//		params.put("dcustom","status=0&btnText=签到");
		params.put("timestamp",System.currentTimeMillis()+600000000+"");
		params.put("activityVip","1");
//		params.put("times","5");
//		params.put("actid","2515914");
//		params.put("validType","0");
//		params.put("addType","1");
//		params.put("alipay","&panzhiyong900＠163.com");
//		params.put("realname","潘志勇");
//		params.put("qq","823458789");
//		params.put("phone","182****9680");
		String redirect = "https://activity.m.duiba.com.cn/newtools/index?id=2699312";
	if(redirect!=null&&redirect!=""&&redirect != "null")
			params.put("redirect",redirect); 
		
//	String url=tool.buildUrlWithSign_new("http://home.m.duiba.com.cn/autoLogin/autologin_v2?",params);
	
	String url=tool.buildUrlWithSign("http://home.m.duiba.com.cn/autoLogin/autologin?",params);
     System.out.println(url);
	
	}

}
