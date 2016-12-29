package cn.com.duiba.test;

import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;

public class xunitongzhi {

	public static void main(String[] args) {

		CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ","4DEz67Z1VmzWVxUy5mVUnZoS2d8v");

		Map<String, String> params = new HashMap<String, String>();
		params.put("orderNum", "2016040816220088700723441");
		params.put("success", "true");
		params.put("errorMessage", "");
		params.put("credits", "1");

		String url = tool.buildUrlWithSign(
				"http://www.duiba.com.cn/confirm/confirm?", params);
		System.out.println(url);
	}

}
