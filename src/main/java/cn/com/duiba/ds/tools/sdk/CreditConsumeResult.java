package cn.com.duiba.ds.tools.sdk;

public class CreditConsumeResult {

	private boolean success;
	private String errorMessage="";
	private String bizId="";
	private Long credits=-1L;//用户积分余额
	
	public CreditConsumeResult(boolean success){
		this.success=success;
	}
	
	
	public String toString(){
		if(success){
			return "{'status':'ok','errorMessage':'','bizId':'"+bizId+"','credits':'"+credits+"'}";
			
			
//			return "{'status':'ok','errorMessage':'','data':{'bizid':'"+bizId+"'}}";
		}else{
			return "{'status':'fail','errorMessage':'"+errorMessage+"','credits':'"+credits+"'}";
//			return "{'code':'积分失败','status':'fail','errorMessage':'"+errorMessage+"','credits':'"+credits+"'}";
//			return "{\"code\":\"积分失败\",\"status\":\"fail\",\"errorMessage\":\"对不起,您的账号已被拉入黑名单,暂不能兑换!\",\"credits\":\"11166\"}";
			
			
		}
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getBizId() {
		return bizId;
	}


	public void setBizId(String bizId) {
		this.bizId = bizId;
	}


	public Long getCredits() {
		return credits;
	}


	public void setCredits(Long credits) {
		this.credits = credits;
	}

}
