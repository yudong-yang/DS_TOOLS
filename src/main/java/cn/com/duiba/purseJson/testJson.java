package cn.com.duiba.purseJson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class testJson {

	
	public static void writeData(String PATH)
	{  
        ArrayList<AppInfo> floks=new ArrayList<AppInfo>();
        for (int i = 0; i < 3; i++) {
			
        AppInfo appinfo1=new AppInfo();  
        appinfo1.setAppId("10"+i);  
        appinfo1.setAppName("xiaoming");  
        appinfo1.setAppKey("dsfasdfasdfds");
        appinfo1.setAppSecret("alskjdflsjadlkfj");  
        floks.add(appinfo1);  
        }
        MyJsonWriter writer=new MyJsonWriter(floks);  
        writer.setFilePath(PATH);  
        writer.getJsonData();  
    }  

	
	  
    public static String getFileFromSD(String path) {  
        String result = "";  
   
        try {  
            FileInputStream f = new FileInputStream(path);  
            BufferedReader bis = new BufferedReader(new InputStreamReader(f));  
            String line = "";  
            while ((line = bis.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
   
    }  

	
public static void main(String[] args) {
	String path = "./src/main/java/cn/com/duiba/purseJson/newduiba.txt";
	
	String s = getFileFromSD(path);
	//writeData(path);
	
	
	AppInfo info = getByid(s,"101");
	
	System.out.println(info.getAppName());
}



/**
 * @param s
 * @param appid
 */
public static  AppInfo getByid(String s, String appid) {
	AppInfo info = new AppInfo();
	JSONArray arr =JSON.parseArray(s);
	for (int i = 0; i < arr.size(); i++) {
		JSONObject ob = (JSONObject) arr.get(i);
		if(ob.getString("appId").endsWith("101")){
			info.setAppId(ob.getString("appId"));
			info.setAppKey(ob.getString("appKey"));
			info.setAppName(ob.getString("appName"));
			info.setAppSecret(ob.getString("appSecret"));
		}
	}
	return info;
}
}
