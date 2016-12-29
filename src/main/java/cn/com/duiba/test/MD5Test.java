package cn.com.duiba.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.MD5;

public class MD5Test {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("timestamp", System.currentTimeMillis()+"");
		params.put("appkey", "3gyWdRiPKkaMiiH6V3RUFybsdeDZ");
		params.put("appsecret", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
		params.put("rowId", "2");
		
		
		String string="";
		string = string+params.get("timestamp")+params.get("appkey")+params.get("appsecret")+params.get("rowId");
		System.out.println(string);
			String sign= MD5.md5(string);
			System.out.println(sign);
		

	}

}
