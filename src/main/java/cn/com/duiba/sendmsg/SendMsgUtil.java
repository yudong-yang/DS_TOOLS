/**
  * 文件说明
  * @Description:扩展说明
  * @Copyright: 2015 dreamtech.com.cn Inc. All right reserved
  * @Version: V6.0
  */
package cn.com.duiba.sendmsg;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**  
 * @Author: feizi
 * @Date: 2015年4月17日 上午9:24:48 
 * @ModifyUser: feizi
 * @ModifyDate: 2015年4月17日 上午9:24:48 
 * @Version:V6.0
 */
public class SendMsgUtil {
	
	/**
	 * 发送短信消息
	  * 方法说明
	  * @Discription:扩展说明
	  * @param phones
	  * @param content
	  * @return
	  * @return String
	 * @throws UnsupportedEncodingException 
	  * @Author: feizi
	  * @Date: 2015年4月17日 下午7:18:08
	  * @ModifyUser：feizi
	  * @ModifyDate: 2015年4月17日 下午7:18:08
	 */
	
	public static String sendMsg(String phones,String content) throws UnsupportedEncodingException{
		//短信接口URL提交地址
		String url = "http://service.winic.org:8009/sys_port/gateway/index.asp";//中信通短信接口地址
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("id", URLEncoder.encode("YuDong-Yang","GB2312"));
		params.put("pwd", "yang123456");
//		params.put("to", "短信类别编号");
//		params.put("extno", "扩展编号");
		
		//手机号码，多个号码使用英文逗号进行分割
		params.put("to", phones);
		//将短信内容进行URLEncoder编码
		params.put("content", URLEncoder.encode(content,"GB2312"));
		return HttpRequestUtil.getRequest(url, params);
	}
	
	
	
/*	public static String sendMsg(String phones,String content) throws UnsupportedEncodingException{
		//短信接口URL提交地址
		String url = "http://service.winic.org:8009/sys_port/gateway/index.asp";//中信通短信接口地址
		
		Map<String, String> params = new HashMap<String, String>();
		
		    params.put("apikey", "apikey");
		    params.put("text", content);
		    params.put("mobile", phones);
		params.put("content", URLEncoder.encode(content,"GB2312"));
		return HttpRequestUtil.postRequest(url, params);
	}*/
	
	/**
	 * 随机生成6位随机验证码
	  * 方法说明
	 */
	public static String createRandomVcode(){
		//验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int)(Math.random() * 9);
		}
		return vcode;
	}
	
	/**
	 * 测试
	  * 方法说明
	  * @Discription:扩展说明
	  * @param args
	  * @return void
	 * @throws UnsupportedEncodingException 
	  * @Author: yudong
	  * @Date: 2017年2月24日 下午7:26:36
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(SendMsgUtil.createRandomVcode());
//		System.out.println("&ecb=12".substring(1));
		System.out.println(sendMsg("18258149680", "尊敬的用户，您的验证码为" + SendMsgUtil.createRandomVcode() + "，有效期为60秒，如有疑虑请详询400-066-855（客服电话）【XXX中心】"));
	}
}
