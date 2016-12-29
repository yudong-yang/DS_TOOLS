package cn.com.duiba.ds.tools;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.duiba.ds.encrypt.BlowfishUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class CookieUtil {
	/**
	 * 
	 * @param name
	 * @param wildcard
	 *            *.duiba.com.cn 级别，还是完整域名级别
	 * @return
	 */
	public static Cookie createCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		return cookie;
	}


	/**
	 * 删除通配符级别的cookie
	 * 
	 * @param name
	 */
	public static void deleteCookie(String name,HttpServletResponse response) {
		Cookie cookie = createCookie(name, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}


	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	
	public static void addCookieForOpenId(HttpServletResponse response, String openId,String key){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("time", new Date().getTime());
		jsonObject.put("openId", openId);
		String envalue=BlowfishUtils.encryptBlowfish(jsonObject.toJSONString(), key);
		addCookie(response, "data-openId", envalue);
	}
	
	public static String getCookieForOpenId(HttpServletRequest request,String key){
		Cookie cookie = getCookieByName(request,"data-openId");
		if(null != cookie){
			String content = BlowfishUtils.decryptBlowfish(cookie.getValue(), key);
			JSONObject json = JSON.parseObject(content);
			if (new Date().getTime() - json.getLong("time") < 24 * 60 * 60 * 1000L) {// 24小时过期
				return json.getString("openId");
			}
		}
		return null;
	}

}
