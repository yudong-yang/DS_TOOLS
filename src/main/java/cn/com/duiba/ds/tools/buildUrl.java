package cn.com.duiba.ds.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditTool;



public class buildUrl {
	/**
	 * 前置商品查询URL
	 * @return
	 */
	public static String getQianzhiUrl(String count) {
		CreditTool tool = new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
			Map<String, String> params = new HashMap<String, String>();
			params.put("count", count);
			params.put("is_https", "True");
//			params.put("timestamp","1598596085000");
			String url = tool.buildUrlWithSign("https://www.duiba.com.cn/queryForFrontItem/query?", params);	
			return url;
		}
	
	/**
	 * 订单列表查询URL
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String OrderList(String reqType,String rowId,String orderTimeType,String startDay,String endDay) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		CreditTool tool = new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
			Map<String, String> params = new HashMap<String, String>();
			params.put("reqType", reqType);
			params.put("rowId", rowId);
			params.put("orderTimeType", orderTimeType);
			params.put("startDay", startDay);
			params.put("endDay", endDay);
			String url = tool.buildUrlWithSignNew("http://hd.dlp.duiba.com.cn/dsOrder/orderRecordWithSign?", params);	
			return url;
		}
	
	
	
	/**
	 * 商品列表查询URL
	 * @return
	 */
	public static String getItemList() {
		CreditTool tool = new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
			Map<String, String> params = new HashMap<String, String>();
			String url = tool.buildUrlWithSign("http://www.duiba.com.cn/QueryForItemList/query?", params);	
			return url;
		}
	
	/**
	 * 商品列表查询URL
	 * @return
	 */
	public static String activityData(String type, String count) {
		CreditTool tool = new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ", "4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
			Map<String, String> params = new HashMap<String, String>();
			params.put("type", type);
			params.put("count", count);
			String url = tool.buildUrlWithSign("http://dlp.duiba.com.cn/DataSearch/activityData?", params);	
			return url;
		}


	/**
	 * 查询订单状态URL
	 * @return
	 */
public static String getOrderStatusUrl(String orderNum,String bizId) {
		
		CreditTool tool = new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ","4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
		String url = tool.buildCreditOrderStatusRequest(orderNum, bizId);
		return url;
	}


public static Map<String, String> UrltoMap(String Url,String appSecret) throws UnsupportedEncodingException
{
String getUrl = Url;

Map<String, String> map = new HashMap<String, String>();	
	String[] d = getUrl.split("\\?");
	System.out.println(d.length);
	if (d.length == 2) {
		String str = d[1];
		System.out.println(str);
		String[] param = str.split("&");
		for (String s : param) {
			if(s.endsWith("=")){
				map.put(s.split("=")[0], "");
			}else{
				map.put(s.split("=")[0], URLDecoder.decode(s.split("=")[1], "UTF-8"));
			}
		}
		map.put("appSecret", appSecret);   
	}
	return map;
}


/**
 * 批量取消发货
 * @return
 */
public static String   batchCancel(String orderNums) {

	CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ","4DEz67Z1VmzWVxUy5mVUnZoS2d8v");//��ʽ

	Map<String, String> params = new HashMap<String, String>();
	params.put("orderNums",orderNums);
	String url = tool.buildUrlWithSign("http://www.duiba.com.cn/sendObject/batchCancel?", params);
	System.out.println("batchCancel=="+url);
	return url;
}


/**
 * 批量发货
 * @return
 */
public static String  batchSend(String info) {
	CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ","4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
	Map<String, String> params = new HashMap<String, String>();
//	String info="2016090614090878900564458C0283|申通快递|2254874";
	params.put("expressInfo",info);
	String url = tool.buildUrlWithSign("http://www.duiba.com.cn/sendObject/batchSend?", params);
	System.out.println("batchSend=="+url);
	return url;
}

/**
 * 审核订单URL
 * @param passOrderNums
 * @return
 */
public static String  batchAudit(String passOrderNums){
CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ","4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
Map<String, String> params = new HashMap<String, String>();
params.put("passOrderNums",passOrderNums);
String url = tool.buildUrlWithSign("http://www.duiba.com.cn/audit/apiAudit?", params);
System.out.println(url);
return url;
}


public static String  batchcancerAudit(String passOrderNums){
CreditTool tool=new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ","4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
Map<String, String> params = new HashMap<String, String>();
params.put("rejectOrderNums",passOrderNums);
String url = tool.buildUrlWithSign("http://www.duiba.com.cn/audit/apiAudit?", params);
System.out.println(url);
return url;
}


/**
 * 
 * @param filename
 * @return
 * @throws FileNotFoundException
 * @throws IOException
 */

public static String fileread(String filename) throws FileNotFoundException, IOException {
	File file = new File(filename);
    @SuppressWarnings("resource")
	FileReader reader = new FileReader(file);
    int fileLen = (int)file.length();
    char[] chars = new char[fileLen];
    reader.read(chars);
    String txt = String.valueOf(chars);
	return txt;
}


	/**
	 * 构建http Get 请求方法
	 * @return
	 */

	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {

			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println("result=="+result);
		} catch (Exception e) {
			System.out.println("异常" + e);
			return "请求异常";
		}
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				return "请求链接出错";
			}
		}
		return result;
	}	
}
