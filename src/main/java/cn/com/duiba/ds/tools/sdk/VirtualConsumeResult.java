package cn.com.duiba.ds.tools.sdk;

public class VirtualConsumeResult {

	private String status;
	private String errorMessage="";
	private String supplierBizId="";
	private Long credits=-1L;
	
	public VirtualConsumeResult(String status){
		this.status=status;
	}
	
	
	public String toString(){
		if(status=="success"){
			return "{'status':'success','credits':'"+credits+"','supplierBizId':'"+supplierBizId+"'}";
		}else if(status=="process"){
			return "{'status':'process','credits':'"+credits+"','supplierBizId':'"+supplierBizId+"'}";
		}else 
			return "{'status':'fail','errorMessage':'"+errorMessage+"','supplierBizId':'"+supplierBizId+"'}";
			
	}   


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getSupplierBizId() {
		return supplierBizId;
	}


	public void setSupplierBizId(String supplierBizId) {
		this.supplierBizId = supplierBizId;
	}


	public Long getCredits() {
		return credits;
	}


	public void setCredits(Long credits) {
		this.credits = credits;
	}

}
