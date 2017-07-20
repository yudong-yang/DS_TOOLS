package cn.com.duiba.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.duiba.ds.tools.buildUrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


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
	
	
	
	public String doPost()  
    {  
        String uriAPI = "http://XXXXXX";//Post方式没有参数在这里  
        String result = "";  
        HttpPost httpRequst = new HttpPost(uriAPI);//创建HttpPost对象  
          
        List <NameValuePair> params = new ArrayList<NameValuePair>();  
        params.add(new BasicNameValuePair("str", "I am Post String"));  
          
        try {  
            httpRequst.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);  
            if(httpResponse.getStatusLine().getStatusCode() == 200)  
            {  
                HttpEntity httpEntity = httpResponse.getEntity();  
                result = EntityUtils.toString(httpEntity);//取出应答字符串  
            }  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        catch (ClientProtocolException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        return result;  
    } 
}
