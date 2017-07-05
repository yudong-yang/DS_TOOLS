package cn.com.duiba.ds.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import cn.com.duiba.ds.tools.sdk.CreditConfirmParams;
import cn.com.duiba.ds.tools.sdk.CreditTool;
import cn.com.duiba.ds.tools.sdk.SignTool;

public class buildUrl {
	

//	static final String AppKey = "E1kBGx6bfYj1HtX9e8MPKfkEqsY";

//	static final String AppSecret = "3zEURgDuJLgDnDAymW35H6M6pUiN";
			
			
	static final String AppKey = "3gyWdRiPKkaMiiH6V3RUFybsdeDZ";

	static final String AppSecret = "4DEz67Z1VmzWVxUy5mVUnZoS2d8v";

	/**
	 * 前置商品查询URL
	 * 
	 * @return
	 */
	public static String getQianzhiUrl(String count) {
		// CreditTool tool = new CreditTool("2EANZHTgJCXk1GDH5XyGve2eLbmU",
		// "6pvMLfyL58H95N2JbGmz7Fxh2Dt");//阿凡提前置接口查询
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("count", count);
		// params.put("is_https", "True");
		 params.put("timestamp","1598596085000");
		String url = tool.buildUrlWithSign(
				"http://www.duiba.com.cn/queryForFrontItem/query?", params);
		return url;
	}

	/**
	 * 订单列表查询URL
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String OrderList(String reqType, String rowId,
			String orderTimeType, String startDay, String endDay)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// CreditTool tool = new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ",
		// "4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("reqType", reqType);
		params.put("rowId", rowId);
		params.put("orderTimeType", orderTimeType);
		params.put("startDay", startDay);
		params.put("endDay", endDay);
		String url = tool.buildUrlWithSignNew(
				"http://hd.dlp.duiba.com.cn/dsOrder/orderRecordWithSign?",
				params);
		return url;
	}

	/**
	 * 商品列表查询URL
	 * 
	 * @return
	 */
	public static String getItemList() {
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		// CreditTool tool = new CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ",
		// "4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
		Map<String, String> params = new HashMap<String, String>();
		String url = tool.buildUrlWithSign(
				"http://www.duiba.com.cn/QueryForItemList/query?", params);
		return url;
	}

	/**
	 * 商品列表查询URL
	 * 
	 * @return
	 */
	public static String activityData(String type, String count) {
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("count", count);
		String url = tool.buildUrlWithSign(
				"http://dlp.duiba.com.cn/DataSearch/activityData?", params);
		return url;
	}
	
	
	
	/**
	 * PC端获取商品列表页面
	 * 
	 * @return
	 */
	public static String getPCDatalist(String vip, String uid, String limitNum) {
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("vip", vip);
		params.put("uid", uid);
		params.put("limitNum", limitNum);
		params.put("timestamp","1498145755349");
		String url = tool.buildUrlWithSign(
				"http://goods.m.duiba.com.cn/pc/pcdata/data?", params);
		return url;
	}

	/**
	 * 查询订单状态URL
	 * 
	 * @return
	 */
	public static String getOrderStatusUrl(String orderNum, String bizId) {
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		String url = tool.buildCreditOrderStatusRequest(orderNum, bizId);
		return url;
	}

	public static Map<String, String> UrltoMap(String Url, String appSecret)
			throws UnsupportedEncodingException {
		String getUrl = Url;

		Map<String, String> map = new HashMap<String, String>();
		String[] d = getUrl.split("\\?");
		System.out.println(d.length);
		if (d.length == 2) {
			String str = d[1];
			System.out.println(str);
			String[] param = str.split("&");
			for (String s : param) {
				if (s.endsWith("=")) {
					map.put(s.split("=")[0], "");
				} else {
					map.put(s.split("=")[0],
							URLDecoder.decode(s.split("=")[1], "UTF-8"));
				}
			}
			map.put("appSecret", appSecret);
		}
		return map;
	}

	/**
	 * 批量取消发货
	 * 
	 * @return
	 */
	public static String batchCancel(String orderNums) {

		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderNums", orderNums);
		String url = tool.buildUrlWithSign(
				"http://www.duiba.com.cn/sendObject/batchCancel?", params);
		System.out.println("batchCancel==" + url);
		return url;
	}

	/**
	 * 批量发货
	 * 
	 * @return
	 */
	public static String batchSend(String info) {
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		// String info="2016090614090878900564458C0283|申通快递|2254874";
		params.put("expressInfo", info);
		String url = tool.buildUrlWithSign(
				"http://www.duiba.com.cn/sendObject/batchSend?", params);
		System.out.println("batchSend==" + url);
		return url;
	}

	/**
	 * 审核订单URL
	 * 
	 * @param passOrderNums
	 * @return
	 */
	public static String batchAudit(String passOrderNums) {
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("passOrderNums", passOrderNums);
		String url = tool.buildUrlWithSign(
				"http://www.duiba.com.cn/audit/apiAudit?", params);
		System.out.println(url);
		return url;
	}

	public static String batchcancerAudit(String passOrderNums) {
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		// CreditTool tool=new
		// CreditTool("3gyWdRiPKkaMiiH6V3RUFybsdeDZ","4DEz67Z1VmzWVxUy5mVUnZoS2d8v");
		Map<String, String> params = new HashMap<String, String>();
		params.put("rejectOrderNums", passOrderNums);
		String url = tool.buildUrlWithSign(
				"http://www.duiba.com.cn/audit/apiAudit?", params);
		System.out.println(url);
		return url;
	}
	
	
	public static String virtulconfirm(Boolean success, String errorMessage, String OrderNum){
		CreditTool tool = new CreditTool(AppKey, AppSecret);
		CreditConfirmParams p = new CreditConfirmParams();
		p.setOrderNum(OrderNum);
		p.setSuccess(success);
		p.setErrorMessage(errorMessage);
		String url = tool.buildCreditConfirmRequest(p);
		return url;		
	}
	
	
	
	
	
	/**
	 * @param appsecret , URL
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void signToolUrl(String appSecret,String URL) throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
			Map<String, String> p =buildUrl.UrltoMap(URL, appSecret);
			System.out.println("签名数组==："+p);
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

	/**
	 * 
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public static String fileread(String filename)
			throws FileNotFoundException, IOException {
		File file = new File(filename);
		@SuppressWarnings("resource")
		FileReader reader = new FileReader(file);
		int fileLen = (int) file.length();
		char[] chars = new char[fileLen];
		reader.read(chars);
		String txt = String.valueOf(chars);
		return txt;
	}

	/**
	 * 构建http Get 请求方法
	 * 
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
		} catch (Exception e) {
			System.out.println("异常" + e);
			return "请求异常";
		} finally {
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
	
	

	/**
	 * 构建http Post 请求方法
	 * 
	 * @return
	 */
	 public static String sendPost(String url, Map<String, String> params) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            String param = mapToparams(params);
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }

	private static  String mapToparams(Map<String, String> params) {
		String param = "";
		for(String key:params.keySet()){
			try {
				if(params.get(key)==null || params.get(key).length()==0){
					param+=key+"="+params.get(key)+"&";
				}else{
					param+=key+"="+URLEncoder.encode(params.get(key), "utf-8")+"&";
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return param;
	}

	public static String getActivityTimes(String uid, String json ,String activityId, String times, String bizId) {
		CreditTool tool=new CreditTool("jlg88lyxz7siqtmr", "1x0eap95f4xfi77uaptrnwh9ewzvlm");
		//		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", uid);
		params.put("bizId", bizId);
		params.put("activityId", activityId);
		params.put("times", times+"");
//		params.put("json", json);
		String url = tool.buildUrlWithSign(
				"https://activity.m.duiba.com.cn/activityVist/addTimes?", params);
		return url;
		
	}   
	
	
	public static String getActivityTimes2(String uid, String json ,String activityId, String times, String bizId) {
		CreditTool tool=new CreditTool("jlg88lyxz7siqtmr", "1x0eap95f4xfi77uaptrnwh9ewzvlm");
		//		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", uid);
		params.put("bizId", bizId);
		params.put("activityId", activityId);
		params.put("times", times+"");
		params.put("json", json);
		String url = tool.buildUrlWithSign(
				"https://activity.m.duiba.com.cn/activityVist/addTimes?", params);
		return url;
		
	}  
	
	
	public static String getActivityTimes3(String uid, String json ,String activityId, String times, String bizId) {
		CreditTool tool=new CreditTool("jlg88lyxz7siqtmr", "1x0eap95f4xfi77uaptrnwh9ewzvlm");
		//		CreditTool tool = new CreditTool(AppKey, AppSecret);
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", uid);
		params.put("bizId", bizId);
//		params.put("activityId", activityId);
//		params.put("times", times+"");
		params.put("json", json);
		String url = tool.buildUrlWithSign(
				"https://activity.m.duiba.com.cn/activityVist/addTimes?", params);
		return url;
		
	}
}
