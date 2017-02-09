package cn.com.duiba.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.com.duiba.ds.tools.buildUrl;
import cn.com.duiba.ds.tools.sdk.SignTool;

public class HttpGetDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		 String name="./src/main/java/cn/com/duiba/test/consumer.txt";
			String URL = buildUrl.fileread(name).toString();
		
		String appSecret = "3gyWdRiPKkaMiiH6V3RUFybsdeDZ";

			Map<String, String> p =buildUrl.UrltoMap(URL, appSecret);
			System.out.println(p);
			String sign1=(String) p.get("sign");
			System.out.println("链接中签名sign1=="+sign1);
			p.remove("sign");
			String sign = SignTool.sign(p);
			System.out.println("根据参数签名sign2===="+sign);
			if(sign1.equals(sign)){
				System.out.println("恭喜！签名验证通过~~");
			}
			else{
				System.out.println("签名验证失败！！");
			}		
	}	
}
