package cn.com.duiba.test;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.com.duiba.ds.tools.buildUrl;

public class zhifubao {
	
	public static void main(String[] args) throws Exception {
		
		 String name="./src/main/java/cn/com/duiba/test/zhifubao.txt";
			String URL = buildUrl.fileread(name);
//			System.out.println(URL);
		String getUrl=URL;
		
		
Map<String, String> p=new HashMap<String, String>();
		
		String[] d=getUrl.split("\\?");
		if(d.length==2){
			String str=d[1];
			
			String[] pairs=str.split("&");

			for(String s:pairs){
				if(s!=null){
				String decodeS=URLDecoder.decode(s, "utf-8");
				p.put(decodeS.split("=")[0], decodeS.split("=")[1]);
				}
			}
		}
		
		
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", p.get("service"));
		sParaTemp.put("partner", p.get("partner"));
		sParaTemp.put("_input_charset", "utf-8");
		sParaTemp.put("notify_url", p.get("notify_url"));
		sParaTemp.put("email", p.get("email"));
		sParaTemp.put("account_name", p.get("account_name"));
		sParaTemp.put("pay_date", p.get("pay_date"));
		sParaTemp.put("batch_no", p.get("batch_no"));
		sParaTemp.put("batch_fee", p.get("batch_fee"));
		sParaTemp.put("batch_num", p.get("batch_num"));
		sParaTemp.put("detail_data", p.get("detail_data"));
		
		sParaTemp.put("sign", p.get("sign"));

		List<String> keys = new ArrayList<String>(sParaTemp.keySet());
		Collections.sort(keys);
		String string = "";
		for (String s : keys) {
			if (string.length() == 0) {
				string += s + "=" + sParaTemp.get(s);
			} else {
				string += "&" + s + "=" + sParaTemp.get(s);
			}

		}
		sParaTemp.put("sign_type", "MD5");
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>(sParaTemp.size());
        for(Map.Entry<String,String> entry : sParaTemp.entrySet()){
            String value = entry.getValue();
            if(value != null){
                pairs.add(new BasicNameValuePair(entry.getKey(),value));
            }
        }
        
        String url=d[0];
        if(!url.endsWith("?")){
        	url+="?";
        }

		HttpPost post=new HttpPost(url+"_input_charset=utf-8");
		post.setEntity(new UrlEncodedFormEntity(pairs,Charset.forName("utf-8")));
		post.addHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=utf-8");
		
		
		
		CloseableHttpClient client=HttpClientBuilder.create().build();
		CloseableHttpResponse response = client.execute(post);
		String body = EntityUtils.toString(response.getEntity());
		System.out.println("处理成功");
		System.out.println(body);
	}
}
