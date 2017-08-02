package cn.com.duiba.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;
import cn.com.duiba.ds.tools.sdk.SignTool;

public class xunitongzhi {

	public static void main(String[] args) {

//	 String orderNum="36229072813063C0489";
		CreditTool tool = new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
	 List orders = new ArrayList<String>();
	 orders.add("36227971801122C0821");
	 orders.add("36227931955272C0821");
	 orders.add("36227922526289C0821");
	 orders.add("36227681345847C0489");
	 orders.add("36227650937174C0489");
	 orders.add("36227635208549C0489");
	String orderNums = tool.List2String(orders);
	 System.out.println(orderNums);
		Map<String, String> params = tool.buildParams(orderNums);
		String result = HttpGetDemo.doPost("http://www.duiba.com.cn/sendObject/batchCancel",params);
		System.out.println(result);
	}
	
}
