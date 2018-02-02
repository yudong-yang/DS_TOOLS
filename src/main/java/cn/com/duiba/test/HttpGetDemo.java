package cn.com.duiba.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import cn.com.duiba.ds.tools.buildUrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class HttpGetDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String name="./src/main/java/cn/com/duiba/test/consumer.txt";
		String URL = buildUrl.fileread(name).toString();
		String response=doGet(URL);
		
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
	
	
	
/**
 * 构建httpPost 请求	
 * @param url
 * @return
 */
	public static String doPost(String url, Map<String, String> map)  
    {  
        String uriAPI = url;//Post方式没有参数在这里  
        String result = "";  
        HttpPost httpRequst = new HttpPost(uriAPI);//创建HttpPost对象  
          
        List <NameValuePair> params = new ArrayList<NameValuePair>();
        for (String key : map.keySet()) {
        	   System.out.println("key= "+ key + " and value= " + map.get(key));
        	   params.add(new BasicNameValuePair(key, map.get(key)));  
        	   }  
        try {  
            httpRequst.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);  
            if(httpResponse.getStatusLine().getStatusCode() == 200)  
            {  
                HttpEntity httpEntity = httpResponse.getEntity();  
                result = EntityUtils.toString(httpEntity);//取出应答字符串  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        catch (ClientProtocolException e) {  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        catch (IOException e) {  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        return result;  
    } 
	
/**
 * 构建httpGet 请求	
 * @param url
 * @return
 */
	 public static String doGet(String url)  
	    {  
	        String result= "";  
	        HttpGet httpRequst = null;  
	        try {  
					URL url1 = new URL(url);   
					URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
					httpRequst = new HttpGet(uri); 
	            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类  
	            if(httpResponse.getStatusLine().getStatusCode() == 200)  
	            {  
	                HttpEntity httpEntity = httpResponse.getEntity();  
	                result = EntityUtils.toString(httpEntity);//取出应答字符串  
	            // 一般来说都要删除多余的字符   
	                result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格    
	            }  
	                   else   
	                        httpRequst.abort();  
	           } catch (ClientProtocolException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            result = e.getMessage().toString();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            result = e.getMessage().toString();  
	        }   catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 } 
	        return result;  
	    }  
	
}
