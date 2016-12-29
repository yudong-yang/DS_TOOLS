package cn.com.duiba.ds.tools.sdk;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author yangyudong
 *
 */
public class VirtualConsumeParams {

	private String appKey;
	private Date timestamp;
	private Long credits;
	private String supplierBizId="";
	private String uid="";
	private String description="";
	private String orderNum="";
	private String account="";
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	private String params="";
	public Long getCredits() {
		return credits;
	}
	public void setCredits(Long credits) {
		this.credits = credits;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public Map<String, String> toRequestMap(String appSecret){
		Map<String, String> map=new HashMap<String, String>();
		
		map.put("description", description);
		map.put("uid", uid);
		map.put("appKey", appKey);
		map.put("supplierBizId", supplierBizId);
		map.put("appSecret", appSecret);
		map.put("timestamp", timestamp.getTime()+"");
		map.put("orderNum", orderNum);
		map.put("params", params);
		
		
		
		String sign=SignTool.sign(map);
		
		map.remove("appSecret");
		map.put("sign", sign);
		return map;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getSupplierBizId() {
		return supplierBizId;
	}
	public void setSupplierBizId(String supplierBizId) {
		this.supplierBizId = supplierBizId;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
	
}

