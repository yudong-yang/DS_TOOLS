package cn.com.duiba.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import cn.com.duiba.ds.tools.Dateformat;
import cn.com.duiba.ds.tools.buildUrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class jsonTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, NoSuchAlgorithmException {
		 String name="./src/main/java/cn/com/duiba/test/consumer.txt";
			String URL = buildUrl.fileread(name).toString();
			//ConsumeJSON(URL);
			//http://wechat.duiba.com.cn/dbredirect?uid=HMX_2&credits=500&dbredirect=https%3A%2F%2Factivity.m.duiba.com.cn%2Fnewtools%2Findex%3Fid%3D2629701
			
			//			String statusurl= buildUrl.getQianzhiUrl("2");
			
			
			String autourl = "";
			ConsumeJSON(URL);
//			String statusurl= buildUrl.getActivityTimes("HMX_12", "1", "2629701", "2", System.currentTimeMillis()+"");
//			System.out.println(statusurl);
			
//			ConsumeJSON(statusurl);
			
			/*String OrderNum = "50181405467519C1019";
			
			String s = "+++   +++";
			
			String ss = URLEncoder.encode(s);
			System.out.println("ss=="+ss);
			
			String s1 = URLDecoder.decode(ss);
			System.out.println("s1=="+s1);
			
			
			if(ss.contains("+")){
			String ss1 = ss.replace("+", "%20");
			System.out.println("ss1=="+ss1);
			
			String ss2 = URLDecoder.decode(ss1);
			System.out.println("ss2=="+ss2);
			}*/
			
			
			/*	String qianzhi = buildUrl.getQianzhiUrl("30");
			System.out.println("前置url==:"+qianzhi);
			for (int i = 0; i < 100; i++) {
				
				qianzhiJSON(qianzhi);
				Thread.sleep(5000);
			}*/
//			String url=buildUrl.getQianzhiUrl("10");
			
			//String url = buildUrl.virtulconfirm(true,"", OrderNum);
			//System.out.println(url);
//			String url2=buildUrl.OrderList("order", "2", "finish", "2016-12-26", "2016-12-27");
//			System.out.println(URL);
//			System.out.println(url2);
		
	}

	public static void ConsumeJSON(String url) {
	//	System.out.println("发起请求时间==："+Dateformat.DateToFomat(System.currentTimeMillis()));
		Long t1=System.currentTimeMillis();
//		String body = buildUrl.sendGet(url);
		String body = HttpGetDemo.doGet(url);
	//	System.out.println("响应时间====:"+Dateformat.DateToFomat(System.currentTimeMillis())+"\n请求响应内容===："+body);
		Long t2 = System.currentTimeMillis();
		try {
			JSONObject result = JSON.parseObject(body);
			System.out.println(result);
			System.out.println(t2-t1);
		/*	System.out.println(result.getString("status"));
			System.out.println(result.getString("supplierBizId"));
			System.out.println(result.getString("errorMessage"));
			System.out.println(result.getString("credits"));*/
		} catch (Exception e) {
			System.out.println("json 解析异常 "+e);
		}
	}

	public static void qianzhiJSON(String num) {
        String url= buildUrl.getQianzhiUrl(num);
		String body = buildUrl.sendGet(url);
		JSONObject oAuthResJson = JSON.parseObject(body);
		JSONArray data = JSON.parseArray(oAuthResJson.getString("data"));
		System.out.println(data.size());
		for (int i = 0; i < data.size(); i++) {
			JSONObject result = JSON.parseObject(data.getString(i));
			System.out.println(result.getString("credits"));
			System.out.println(result.getString("small_image"));
			System.out.println(result.getString("title"));
			System.out.println(result.getString("url"));
			System.out.println("------------------------------");
		}
	}
}
