package cn.com.duiba.ds.tools.sdk;

import java.util.Date;

public class RecordParams {

	private String appKey;
	private Date timestamp;//时间戳
	private Long credits;//消耗积分数
	private String recordDetailUrl;//兑换记录详情页地址(访问时需包装成免登录url)
	private String logoUrl="";//图标的链接地址
	private String uid;
	private String title;//兑换记录信息的标题
	private String recordId;//兑换记录id号
	
	
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
	public Long getCredits() {
		return credits;
	}
	public void setCredits(Long credits) {
		this.credits = credits;
	}
	public String getRecordDetailUrl() {
		return recordDetailUrl;
	}
	public void setRecordDetailUrl(String recordDetailUrl) {
		this.recordDetailUrl = recordDetailUrl;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	
}
