package cn.com.duiba.Utils;

import cn.com.duiba.ds.tools.buildUrl;
import cn.com.duiba.test.HttpGetDemo;

public class TestGetUrl {

	public static void main(String[] args) {

		
		String url2 = buildUrl.getQianzhiUrl("10");
		System.out.println(url2);
		String url = "http://www.duiba.com.cn/autoLogin/autologin?sign=9448999d8641426d566db26a0431c892&uid=2&appKey=21bPuGyabWsbjFAxtUBbbMqDSX1a&credits=8&timestamp=1519980431143&";
		HttpGetDemo.doGet(url);
	}

}
