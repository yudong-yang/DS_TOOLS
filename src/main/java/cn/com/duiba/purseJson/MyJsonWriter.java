package cn.com.duiba.purseJson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.json.JSONStringer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;


class MyJsonWriter {  
	   ArrayList<AppInfo> Apps;  
	   File saveFile;  
	    public MyJsonWriter(ArrayList<AppInfo> Apps){  
	        this.Apps=Apps;  
	    }  
	      
	    public void setFilePath(String filepath){  
	        saveFile=new File(filepath);  
	       try {  
	            saveFile.createNewFile();  
	      } catch (IOException e) {  
     
	            e.printStackTrace();  
     }  
	    }  
	      
 public String getJsonData(){  
	       String jsonData = null;  
	//      String jsonData=new JSONStringer().object().key("village").value("abc").endObject().toString();  
	        try {  
	            StringBuilder builder=new StringBuilder();  
	            ArrayList<String> folksData=new ArrayList<String>();  
	            JSONArray array = new JSONArray();  
	           for(int i=0;i<Apps.size();i++){  
	        	   AppInfo appInfo=Apps.get(i);  
	                JSONObject jsonObject=new JSONObject();  
	                jsonObject.put("appId", appInfo.getAppId());  
	                jsonObject.put("appName", appInfo.getAppName());  
	                jsonObject.put("appKey", appInfo.getAppKey());  
	                jsonObject.put("appSecret", appInfo.getAppSecret());  
	                folksData.add(jsonObject.toString());  
	                array.add(jsonObject);
	            }  
	//          JSONArray jsonArray=new JSONArray(folksData);  
	           int len = array.size();  
	            jsonData=array.toString();  
	            System.out.println(jsonData);  
	            writeData(jsonData);  
	        } catch (JSONException e) {  
	         
	            e.printStackTrace();  
	        }  
	        return jsonData;  
	    }  
	  
	    private void writeData(String jsonData) {  
	       
	        try {  
	            BufferedReader reader=new BufferedReader(new StringReader(jsonData));  
	            BufferedWriter writer=new BufferedWriter(new FileWriter(saveFile));  
	            int len=0;  
	            char[] buffer=new char[1024];   
	            while((len=reader.read(buffer))!=-1){  
	                writer.write(buffer, 0, len);  
	            }  
	           writer.flush();  
	            writer.close();  
	           reader.close();  
	        } catch (IOException e) {  
	           
	            e.printStackTrace();  
	        }  
	    } 
	    
	    
	  
	    
	}    