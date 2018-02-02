package cn.com.duiba.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import cn.com.duiba.ds.tools.buildUrl;
import cn.com.duiba.entity.ExpressInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestGetUrl {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		
//		String url2 = buildUrl.getQianzhiUrl("10");
		ExpressInfo info = new ExpressInfo("54416211932052C0759","全峰快递","121324234");
		List<ExpressInfo> infos = new ArrayList<ExpressInfo>();
		infos.add(info);
		String urls = buildUrl.batchSend(infos);
		System.out.println(urls);
		
//		System.out.println(url2);
//		Sendgoods();
		String orderNum="50735737782900C0422";
//		VirtualNotify(orderNum);
		
	}

	public static void VirtualNotify(String OrderNum) throws ClientProtocolException, IOException {
		
		String url = buildUrl.virtulconfirm(true,"", OrderNum);
		System.out.println(url);
		final CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		get.setConfig(RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000)
				.setSocketTimeout(5000).build());
		CloseableHttpResponse response = null;
		response = client.execute(get);
		if (response.getStatusLine().getStatusCode() == 200) {
			String body = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(body);
		}
	}

	/**
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws ParseException
	 */
	public static void Sendgoods() throws IOException, ClientProtocolException,
			ParseException {
		List <ExpressInfo> infos =new ArrayList<ExpressInfo>();	
		infos.add(new ExpressInfo("50359237491980C0140","京东快递","卡号：JDV14868410000177；密码：334B-32A1-8C89-8FE8"));
		infos.add(new ExpressInfo("50359231204617C0140","京东快递","卡号：JDV14868410000178；密码：CE15-73FC-81E3-E8C4"));
		String oAuthUrl= buildUrl.batchSend(infos);
			final CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(oAuthUrl);
			get.setConfig(RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(10000)
					.setSocketTimeout(10000).build());
			CloseableHttpResponse response = null;
			
			response = client.execute(get);

			if (response.getStatusLine().getStatusCode() == 200) {
				String body = EntityUtils.toString(response.getEntity(), "utf-8");
				JSONObject oAuthResJson = null;
				oAuthResJson = JSON.parseObject(body); 
				System.out.println(oAuthResJson);
			}
	}

}
