package cn.com.duiba.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


/**
 * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。开发者需要进行妥善保存。
 * access_token的存储至少要保留512个字符空间。
 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效
 * jsapi_ticket的有效期为7200秒，通过access_token来获取。
 * 由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api调用受限，影响自身业务，
 * 开发者必须在自己的服务全局缓存jsapi_ticket
 */
@Component
public class WeixinService {

	private static Logger log = LoggerFactory.getLogger(WeixinService.class);

	public static final String WEIXIN_APPID = "wx66ad20e6ee0efefd";
	private static final String ACCESS_TOKEN_KEY = "access_token_key";
	private static final String WEIXIN_APPSECRET = "f4077f637f7beecc08db6c8dbea75269";
	
	//配置accessToken本地缓存
	LoadingCache<String, String> accessTokenCache = CacheBuilder.newBuilder()
			.maximumSize(1000)
			.expireAfterWrite(2, TimeUnit.HOURS)
			.build(
				new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						return reloadAccessToken();
					}
			});
	
	//配置jsApiTicket本地缓存
	LoadingCache<String, String> jsApiTicketCache = CacheBuilder.newBuilder()
			.maximumSize(1000)
			.expireAfterWrite(2, TimeUnit.HOURS)
			.build(
				new CacheLoader<String, String>() {
					@Override
					public String load(String accessToken) throws Exception {
						return reloadJsApiTicket(accessToken);
					}
			});

	//暴露给外部的取accessToken公共方法
	public String getAccessToken(){
		String accessToken = null;
		try {
			accessToken = accessTokenCache.get(ACCESS_TOKEN_KEY);
		} catch (ExecutionException e) {
			log.error("getAccessToken", e);
		}
		return accessToken;
	}
	
	//暴露给外部的根据accessToken取jsApiTicket的公共方法
	public String getJsApiTicket(String accessToken){
		String jsApiTicket = null;
		try {
			jsApiTicket = jsApiTicketCache.get(accessToken);
		} catch (ExecutionException e) {
			log.error("getJsApiTicket", e);
		}
		return jsApiTicket;
	}

	//内部生成accessToken的缓存刷新方法
	private String reloadAccessToken() {
		try {
			String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WEIXIN_APPID + "&secret=" + WEIXIN_APPSECRET + "";
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(getTokenUrl);
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			String responseStr = EntityUtils.toString(entity);
			JSONObject json = JSON.parseObject(responseStr);
			String accessToken = String.valueOf(json.get("access_token"));
			return accessToken;
		} catch (Exception e) {
			log.error("reloadAccessToken", e);
		}
		return null;
	}

	//内部生成JsApiTicket的缓存刷新方法
	private String reloadJsApiTicket(String accessToken) {
		try {
				String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpget = new HttpGet(getTokenUrl);
				CloseableHttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				String responseStr = EntityUtils.toString(entity);
				JSONObject json = JSON.parseObject(responseStr);
				System.out.println(responseStr);
				String jsapiTicket = String.valueOf(json.get("ticket"));
				return jsapiTicket;
		} catch (Exception e) {
			log.error("reloadJsApiTicket", e);
		}
		return null;
	}
}
