package cn.com.duiba.test.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class demo1 {
	public static void main(String[] args) throws IOException {
		
		 URL url = new URL("https://yun.duiba.com.cn/upload/3300gr31l4.json");  
	        InputStream ism=url.openStream();  
	        byte[] bytes=new byte[1024];  
	        ism.read(bytes);  
	        String str=new String(bytes,"utf-8");  
	        System.err.println(str);  
	        while(ism.read(bytes)>-1){  
	            System.out.println(str);  
	        }  
	}
}

