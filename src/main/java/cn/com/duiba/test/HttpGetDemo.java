package cn.com.duiba.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.duiba.ds.tools.buildUrl;
import cn.com.duiba.ds.tools.sdk.SignTool;

public class HttpGetDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String name="./src/main/java/cn/com/duiba/test/consumer.txt";
		String URL = buildUrl.fileread(name).toString();
		
		String url="http://127.0.0.1:8080/testPost";
		Map<String, String> params = new HashMap<String , String>();
		params.put("name", "xiaomingsdfsd");
		params.put("sex", "1");
		String response = buildUrl.sendPost(url, params);
		
		System.out.println(response);
		
	}
	
	
	public static String buildInfo(String orderNum, String ExpName ,String ExnNum){
		StringBuffer Info = new StringBuffer();
		Info.append(orderNum).append("|").append(ExpName).append("|").append(ExnNum);
		return Info.toString();
		
	}
	
	
	
	public static void PCDataJSON(String vip,String uid,String limitNum ) {
        String url= buildUrl.getPCDatalist(vip,uid, limitNum );
		String body = buildUrl.sendGet(url);
		JSONObject oAuthResJson = JSON.parseObject(body);
		JSONArray data = JSON.parseArray(oAuthResJson.getString("data"));
		System.out.println(data.size());
		for (int i = 0; i < data.size(); i++) {
			JSONObject result = JSON.parseObject(data.getString(i));
			System.out.println(result.getString("title"));
			System.out.println(result.getString("image"));
			System.out.println(result.getString("redirect"));
			System.out.println(result.getString("credits"));
			System.out.println("------------------------------");
		}
	}
}
