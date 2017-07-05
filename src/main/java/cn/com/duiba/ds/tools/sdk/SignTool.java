package cn.com.duiba.ds.tools.sdk;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SignTool {

	public static boolean signVerify(String appSecret,HttpServletRequest request){
		Map<String, String[]> map=request.getParameterMap();
		Map<String, String> data=new HashMap<String, String>();
		for(String key:map.keySet()){
			data.put(key, map.get(key)[0]);
			
		}
		return signVerify(appSecret, data);
	}
	
	public static boolean signVerify(String appSecret,Map<String, String> params){
		Map<String, String> map=new HashMap<String, String>();
		map.put("appSecret", appSecret);
		map.remove("formid");
		for(String key:params.keySet()){
			if(!key.equals("sign")){
				map.put(key, params.get(key));
			}
		}
		
		String sign=sign(map);
		if(sign.equals(params.get("sign"))){
			return true;
		}
		return false;
	}
	
	private static String toHexValue(byte[] messageDigest) {
		if (messageDigest == null)
			return "";
		StringBuilder hexValue = new StringBuilder();
		for (byte aMessageDigest : messageDigest) {
			int val = 0xFF & aMessageDigest;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	/**
	 * 
	 * @param params
	 * @return
	 */
	public static String sign(Map<String,String> params){
		List<String> keys=new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String string="";
		for(String s:keys){
			string+=params.get(s);
		}
		System.out.println("签名前字串==:"+string);
		String sign="";
		try {
			sign = toHexValue(encryptMD5(string.getBytes(Charset.forName("utf-8"))));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("md5 error");
		}
		return sign;
	}
	
	private static byte[] encryptMD5(byte[] data)throws Exception{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(data);
		return md5.digest();
	}
	
	
	public static  String signstring(Map<String, String> params){
		List<String> keys=new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String str="";
		for(String s:keys){
			str+=params.get(s);
		}
		return str;
	}
	
	
	
	public static void main(String[] args) {
		String appKey="key";
		String appSecret="secret";
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("appKey", "testappkey");
		params.put("appSecret", "testappsecret");
		params.put("description", "兑吧");
		/*params.put("success", "true");
		params.put("errorMessage", "失败的话这里会返回错误原因");
		params.put("orderNum", "order-for-test-1465377152951");
		params.put("bizId", "D14653773184192390");
		params.put("paramsTest7", "7");*/
		String sign=sign(params);
		System.out.println(sign);
			
	}
}
