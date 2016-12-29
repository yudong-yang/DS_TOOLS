package cn.com.duiba.entity;

public class Credits {
	private String userId;
	private Long credits;
	private String orderNum;
	private String bizId;
	private String type;
	private String timestamp;
	private String description;
	private int facePrice;
	private int actualPrice;
	private String ip;
	private String waitAudit;
	private String params;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userid) {
		this.userId = userid;
	}
	public Long getCredits() {
		return credits;
	}
	public void setCredits(Long credits) {
		this.credits = credits;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFacePrice() {
		return facePrice;
	}
	public void setFacePrice(int facePrice) {
		this.facePrice = facePrice;
	}
	public int getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(int actualPrice) {
		this.actualPrice = actualPrice;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getWaitAudit() {
		return waitAudit;
	}
	public void setWaitAudit(String waitAudit) {
		this.waitAudit = waitAudit;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	

}
