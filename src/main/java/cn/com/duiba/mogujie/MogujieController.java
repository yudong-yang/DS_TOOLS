package cn.com.duiba.mogujie;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mogujie.openapi.MogujieClient;
import com.mogujie.openapi.exceptions.ApiException;
import com.mogujie.openapi.util.HttpClient;
import com.mogujie.openapi.util.MD5Util;
import com.mogujie.openapi.util.SignUtil;





public class MogujieController {
	public static void main(String[] args) throws IOException, ApiException {
		Map<String,String> map = new HashMap<>();
		map.put("a", "于冬");
		map.put("b", "cc");
		String s = SignUtil.signRequest(map, "aa",null);
		
		String url = HttpClient.buildQuery(map, "utf-8");
		String url2 = HttpClient.doGet("http://www.baidu.com?", map, "utf-8");
		System.out.println(s);
	}
	

}
