package cn.com.duiba.ds.tools.sdk;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AddCreditsParams {

	private String appKey;
	private Date timestamp;//时间戳
	private Long credits;//消耗积分数
	private String orderNum="";//兑吧订单号
	private String description="";
	private String type="";//类型：QB,Phonebill,Alipay,Coupon  所有类型不区分大小写
	private String uid="";
	private String ip="";//用户兑换时使用的ip地址，有可能为空
	private String transfer="";
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
}
