package cn.com.duiba.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import cn.com.duiba.ds.tools.sdk.AddCreditResult;
import cn.com.duiba.ds.tools.sdk.AddCreditsParams;
import cn.com.duiba.ds.tools.sdk.CreditConsumeParams;
import cn.com.duiba.ds.tools.sdk.CreditConsumeResult;
import cn.com.duiba.ds.tools.sdk.CreditNotifyParams;
import cn.com.duiba.ds.tools.sdk.CreditTool;
import cn.com.duiba.ds.tools.sdk.VirtualConsumeParams;
import cn.com.duiba.ds.tools.sdk.VirtualConsumeResult;
import cn.com.duiba.entity.Credits;
import cn.com.duiba.service.AppDemoService;
import cn.com.duiba.service.CreditsService;
import cn.com.duiba.service.UserService;
import cn.com.duiba.service.VirtualService;



@Controller
@RequestMapping("/duiba")
public class DuibaApi {
	private static final Logger logger = LoggerFactory.getLogger(DuibaApi.class);

	@Autowired
    private CreditsService creditsService;
	@Autowired
    private UserService userService;
	
	@Autowired
    private VirtualService virtualService;
	
	@Autowired
    private AppDemoService appDemoService;
    

	public String GetAppkeyByid(String appcode){
		return appDemoService.findByAppCode(appcode).get(0).getAppkey();
	}
	public String GetSecretByid(String appcode){
		return appDemoService.findByAppCode(appcode).get(0).getAppsectet();
	}
	

	@RequestMapping("/consume/{appid}")
	@ResponseBody
	public String consume(HttpServletRequest request,HttpServletResponse response, @PathVariable("appid") String appid) {
		String appKey=GetAppkeyByid(appid);
		String appSecret=GetSecretByid(appid);
		CreditTool tool=new CreditTool(appKey, appSecret);
		try {
			CreditConsumeParams params= tool.parseCreditConsume(request);
			String paramsAppKey = request.getParameter("appKey"); //获取code
			String orderNum = request.getParameter("orderNum"); //获取兑吧订单号
			String bizId = "dbmarket-" + orderNum;
			Long credits =params.getCredits();
			String Uid =request.getParameter("uid");
			String errorMessage = "";
			boolean success = false;
		    if (!appKey.equals(paramsAppKey)) {
		        errorMessage = "服务器异常，appKey不匹配";
		    } else if (orderNum == null) {
		        errorMessage = "服务器异常，订单号为空";}
		        else if (Uid.equals("5555")) {
			        errorMessage = "该用户不支持兑换";
		    }else if (userService.GetCreditsByUid(Uid)==null) {
		        errorMessage = "用户不存在";
		    }else if (userService.GetCreditsByUid(Uid)<=credits) {
	        errorMessage = "用户积分不足";
    }
		    else {
		    	success = true;
		    }
		   
		    CreditConsumeResult ccr = new CreditConsumeResult(success);
		    userService.cutCredit(Uid, credits);
		    creditsService.createList(params,bizId);
		    ccr.setErrorMessage(errorMessage);
		    ccr.setBizId(bizId);
		    ccr.setCredits(userService.GetCreditsByUid(Uid));
			return ccr.toString();

		} catch (Exception e) {
			  CreditConsumeResult ccr = new CreditConsumeResult(false);
			  ccr.setErrorMessage("扣积分异常:"+e.getMessage());
			  ccr.setCredits(-1L);
			  ccr.setBizId("bizid=123345323423");
			  System.out.println("响应内容==："+ccr.toString());
				return ccr.toString();
			 
		}
	}
	
	//加积分接口
	@RequestMapping("/addCredits/{appid}")
	@ResponseBody
	public String addCredits(HttpServletRequest request, @PathVariable("appid") String appid) {
		
		String appKey=GetAppkeyByid(appid);
		String appSecret=GetSecretByid(appid);
		CreditTool tool=new CreditTool(appKey, appSecret);
		try {		
			AddCreditsParams params= tool.parseaddCredits(request);
			String paramsAppKey = request.getParameter("appKey"); //获取code
			String orderNum = request.getParameter("orderNum"); //获取兑吧订单号
			String bizId = "dbmarket:" + orderNum;
			Long credits =params.getCredits();
			String Uid =request.getParameter("uid");
			String errorMessage = ""; 
			boolean success = false;

		    if (!appKey.equals(paramsAppKey)) {
		        errorMessage = "服务器异常，appKey不匹配";
		    } else if (orderNum == null) {
		        errorMessage = "服务器异常，订单号为空";
		    } else {
		    	success = true;
		    }
		   
		    AddCreditResult ccr = new AddCreditResult(success);
		    userService.AddCredit(Uid, credits);
		    ccr.setErrorMessage(errorMessage);
		    ccr.setBizId(bizId);
		    ccr.setCredits(userService.GetCreditsByUid(Uid));
			return ccr.toString();
		} catch (Exception e) {
			AddCreditResult ccr = new AddCreditResult(false);
			  ccr.setErrorMessage("加积分异常"+e.getMessage());
			  ccr.setCredits(-1L);
			return ccr.toString();

			 
		}
	}
	
	
	@RequestMapping("/notify/{appid}")
	@ResponseBody
	@Transactional
	public String notify(HttpServletRequest request, @PathVariable("appid") String appid) {
		String appKey=GetAppkeyByid(appid);
		String appSecret=GetSecretByid(appid);
		CreditTool tool=new CreditTool(appKey, appSecret);
		 try {
			CreditNotifyParams params= tool.parseCreditNotify(request);
			    String orderNum=params.getOrderNum();
			    if(params.isSuccess()){
			    	creditsService.updateOrderStatus(orderNum,"success");
			    	return "ok";
			    }else{
			    	Credits Credit =  creditsService.findCreditByUid(orderNum);
			    	String orderstatus = Credit.getStatus();
			    	logger.info("返回订单状态====："+orderstatus);
			    	if(orderstatus=="success"){
			    		logger.info("用户积分返还情况====：订单是成功状态，不需要返还积分");	
			    	}else if(orderstatus==null){
			    	String msg = userService.returnCredit(Credit.getUserId() , Credit.getCredits());
			    	logger.info("用户积分返还情况====："+msg);
			    	if(msg.equals("积分返还成功")){
			    	creditsService.updateOrderStatus(orderNum,"fail");
			    	}else{logger.info("用户积分返还情况====："+msg);}
			    	}else{logger.info("用户积分返还情况====：积分已经返还，请勿重复返还");}
			    	return "ok";
			    }
		} catch (Exception e) {
			
			return "ok";
		}
		
	}
	
	@RequestMapping("/virtual/{appid}")
	@ResponseBody
	public String virtual(HttpServletRequest request, @PathVariable("appid") String appid) {
		String appKey=GetAppkeyByid(appid);
		String appSecret=GetSecretByid(appid);
		
		CreditTool tool=new CreditTool(appKey, appSecret);
		 try {
			VirtualConsumeParams params= tool.virtualCreditConsume(request);
			String virparam= params.getParams();
			String account= params.getAccount();
			System.out.println("虚拟商品标示符======"+virparam+"====账号==="+account);
			 Long credits = virtualService.getCredits(virparam);
			 System.out.println("查询虚拟商品对应积分"+credits); 
		  	String uid=params.getUid();//获取用户id
			String supplierBizId="virtual"+params.getOrderNum();
			   if(virparam.equals("33")){	
			    	VirtualConsumeResult result= new VirtualConsumeResult("fail");
			    	result.setErrorMessage("该档位不支持兑换，请切换其他档位！！！");
			        result.setSupplierBizId(supplierBizId);
				    result.setCredits(userService.GetCreditsByUid(uid));
				    return result.toString();
			    }else if(credits==null){
			    	VirtualConsumeResult result= new VirtualConsumeResult("fail");
			    	result.setErrorMessage("该档位不存在或者档位错误");
			        result.setSupplierBizId(supplierBizId);
				    result.setCredits(userService.GetCreditsByUid(uid));
				    return result.toString();
			    }
			    else{
			    	VirtualConsumeResult result= new VirtualConsumeResult("success");
			    	 result.setSupplierBizId(supplierBizId);
					 result.setCredits(userService.GetCreditsByUid(uid));
					 userService.returnCredit(uid, credits);
					 System.out.println("虚拟商品增加积分"+credits);
					 Thread.sleep(5000); 
					 
			      return result.toString();
			    }
			
			    	
		} catch (Exception e) {
			e.printStackTrace();
			VirtualConsumeResult result= new VirtualConsumeResult("fail");
			result.setErrorMessage(e.toString());
			return result.toString();
		}
		
	}
	
	
	@RequestMapping("/autologin/{appid}")
	public RedirectView autologin(HttpServletRequest request,@CookieValue("cook") String cookvalue, @PathVariable("appid") String appid)  { {
		System.out.println("获取的cookie值==="+cookvalue);
		String appKey=GetAppkeyByid(appid);
		String appSecret=GetSecretByid(appid);
		CreditTool tool=new CreditTool(appKey, appSecret);
		String uid=null;
		if(cookvalue!=null){
		 uid = cookvalue;
		}else{
			uid="not_login";
		}
		String dbredirect = request.getParameter("dbredirect");
		Map<String, String> params=new HashMap<String, String>();
		if(uid!=null&&uid!=""&&uid!= "null"){
			params.put("uid",uid);
			logger.info("用户id=="+uid);
			Long credit = userService.GetCreditsByUid(uid);
			logger.info("用户积分"+credit);
			if(credit!=null){
			params.put("credits",credit.toString());
			}
		}else{
			params.put("uid","not_login");
			logger.info("用户id"+uid);
			params.put("credits","0");
		}
	if(dbredirect!=null&&dbredirect!=""&&dbredirect != "null"){
			params.put("redirect",dbredirect);
			logger.info("用户redirect="+dbredirect);
		}
	String url=tool.buildUrlWithSign("http://www.duiba.com.cn/autoLogin/autologin?",params);
	System.out.println("免登陆地址=："+url);
	return new RedirectView(url, true, false, true);
	}
	}
	
	
	
	@RequestMapping("/dbredirect/{appid}")
	public RedirectView autologin2(HttpServletRequest request, @PathVariable("appid") String appid)  { 
		String appKey=GetAppkeyByid(appid);
		String appSecret=GetSecretByid(appid);
		CreditTool tool=new CreditTool(appKey, appSecret);
		String transfer = request.getParameter("transfer");	
		String uid = request.getParameter("uid");
		String dbredirect = request.getParameter("dbredirect");
		Map<String, String> params=new HashMap<String, String>();
		if (StringUtils.isNotBlank(transfer)) {
			params.put("transfer", transfer);
			logger.info("transfer===" + transfer);
		}
		if(uid!=null&&uid!=""&&uid!= "null"&&uid!="not_login"){
			params.put("uid",uid);
			logger.info("用户id=="+uid);
			Long credits = userService.GetCreditsByUid(uid);
			logger.info("用户积分"+credits);
			if(credits!=null){
			params.put("credits",credits.toString());
			}else{params.put("credits","0");}
		}else{
			params.put("uid","not_login");
			logger.info("用户id"+uid);
			params.put("credits","0");
		}
	if(dbredirect!=null&&dbredirect!=""&&dbredirect != "null"){
			params.put("redirect",dbredirect);
			logger.info("用户redirect="+dbredirect);
		}
	params.put("signKeys", "uid|credits|redirect");
	String url=tool.buildUrlWithSign("http://www.duiba.com.cn/autoLogin/autologin?",params);
	System.out.println("免登陆地址=："+url);
	return new RedirectView(url, true, false, true);
	}
	
}
		

	

