package cn.com.duiba.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import cn.com.duiba.ds.tools.sdk.AddCreditsParams;
import cn.com.duiba.ds.tools.sdk.CreditConsumeParams;
import cn.com.duiba.ds.tools.sdk.CreditConsumeResult;
import cn.com.duiba.ds.tools.sdk.CreditNotifyParams;
import cn.com.duiba.ds.tools.sdk.CreditTool;
import cn.com.duiba.ds.tools.sdk.VirtualConsumeParams;
import cn.com.duiba.ds.tools.sdk.VirtualConsumeResult;
import cn.com.duiba.entity.Credits;
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
	
    
	@Value("${duiba.appKey}")
	private String appKey;
	@Value("${duiba.appSecret}")
	private String appSecret;
	@RequestMapping("/consume")
	@ResponseBody
	public String consume(HttpServletRequest request) {
		CreditTool tool=new CreditTool(appKey, appSecret);
		try {
			CreditConsumeParams params= tool.parseCreditConsume(request);
			System.out.println(params.getParams()+"==="+params.getDescription());
			String paramsAppKey = request.getParameter("appKey"); //获取code
			String orderNum = request.getParameter("orderNum"); //获取兑吧订单号
			String bizId = "dbmarket-" + orderNum;
//			String bizId = orderNum;
			Long credits =params.getCredits();
			String Uid =request.getParameter("uid");
			String errorMessage = "";
			boolean success = false;

		    if (!appKey.equals(paramsAppKey)) {
		        errorMessage = "服务器异常，appKey不匹配";
		    } else if (orderNum == null) {
		        errorMessage = "服务器异常，订单号为空";}
		        else if (Uid.equals("4444")) {
			        errorMessage = "该用户不支持兑换";
			        System.out.println(errorMessage);
		    } else {
		    	success = true;
		    }
		   
		    CreditConsumeResult ccr = new CreditConsumeResult(success);
		    
		    Credits credit=creditsService.ParamToCredits(params,bizId);
		    userService.cutCredit(Uid, credits);
		    creditsService.insert(credit);
		    ccr.setErrorMessage(errorMessage);
		    ccr.setBizId(bizId);
		    ccr.setCredits(userService.GetCreditsByUid(Uid));
			return ccr.toString();
//			 return"测试环境响应失败内容测试的";
		} catch (Exception e) {
			  CreditConsumeResult ccr = new CreditConsumeResult(false);
			  ccr.setErrorMessage("扣积分异常"+e.getMessage());
			  ccr.setCredits(-1L);
			  ccr.setBizId("bizid=123345323423");
//				return ccr.toString();
			  return"测试环境响应失败内容测试的";
			 
		}
	}
	
	//加积分接口
	@RequestMapping("/addCredits")
	@ResponseBody
	public String addCredits(HttpServletRequest request) {
		CreditTool tool=new CreditTool(appKey, appSecret);
		try {
			AddCreditsParams params= tool.parseaddCredits(request);
			String paramsAppKey = request.getParameter("appKey"); //获取code
			String orderNum = request.getParameter("orderNum"); //获取兑吧订单号
			String bizId = "dbmarket-" + orderNum;
//			String bizId = orderNum;
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
		   
		    CreditConsumeResult ccr = new CreditConsumeResult(success);
		    userService.AddCredit(Uid, credits);
		    ccr.setErrorMessage(errorMessage);
		    ccr.setBizId(bizId);
		    ccr.setCredits(userService.GetCreditsByUid(Uid));
			return ccr.toString();
		} catch (Exception e) {
			  CreditConsumeResult ccr = new CreditConsumeResult(false);
			  ccr.setErrorMessage("加积分异常"+e.getMessage());
			  ccr.setCredits(-1L);
			  ccr.setBizId("bizid=123345323423");
				return ccr.toString();
//			  return"测试环境响应失败内容测试的";
			 
		}
	}
	
	
	@RequestMapping("/notify")
	@ResponseBody
	public String notify(HttpServletRequest request) {
		CreditTool tool=new CreditTool(appKey, appSecret);
		 try {
			CreditNotifyParams params= tool.parseCreditNotify(request);
			    String orderNum=params.getOrderNum();
			    if(params.isSuccess()){
			    	System.out.println("响应信息null");
			    	return "ok";
			    	
			    }else{
			    	
			    }
			    	String uid = creditsService.findCreditByUid(orderNum).getUserId();
			    	Long credits=creditsService.findCreditByUid(orderNum).getCredits();
			    	userService.returnCredit(uid , credits);
			    	
			    	return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "ok";
		}
		
	}
	
	@RequestMapping("/virtual")
	@ResponseBody
	public String virtual(HttpServletRequest request) {
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
			      return result.toString();
			    }
			
			    	
		} catch (Exception e) {
			e.printStackTrace();
			VirtualConsumeResult result= new VirtualConsumeResult("fail");
			result.setErrorMessage(e.toString());
			return result.toString();
		}
		
	}
	
	
	@RequestMapping("/autologin")
	public RedirectView autologin(HttpServletRequest request,@CookieValue("cook") String cookvalue)  { {
		System.out.println("获取的cookie值==="+cookvalue);
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
	
	
	
	@RequestMapping("/autologin2")
	public RedirectView autologin2(HttpServletRequest request)  { {
		CreditTool tool=new CreditTool(appKey, appSecret);
		String uid = request.getParameter("uid");
		String dbredirect = request.getParameter("dbredirect");
//		String dbredirect = "http://www.duiba.com.cn/mobile/detail?itemId=1888&dbnewopen";
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
	
	
	@RequestMapping("/testlogin")
	public String testlogin(HttpServletRequest request, Model model) throws UnsupportedEncodingException { {
		CreditTool tool = new CreditTool("jlg88lyxz7siqtmr",
				"1x0eap95f4xfi77uaptrnwh9ewzvlm"); // 115
		Map<String, String> params = new HashMap<String, String>();
		String uid = request.getParameter("uid");
		String credit = request.getParameter("credits");
		String dcustom = request.getParameter("dcustom");
		String demo = request.getParameter("rdo1");
		String rdo2 = request.getParameter("rdo2");
		System.out.println("选择按钮：" + request.getParameter("rdo1")+"===="+request.getParameter("rdo2"));

		String url1 = "";

		if (demo.equals("test")) {
			
			url1 = "http://m.dui88.com/autoLogin/autologin?";
		} else if((demo.equals("normal"))) {
			url1 = "http://www.duiba.com.cn/autoLogin/autologin?";
		}else if((demo.equals("pre"))) {
			url1 = "http://pre.duiba.com.cn/autoLogin/autologin?";
		}

		if (uid != null && uid != "") {
			
			params.put("uid", uid);
		} else {
			params.put("uid", "not_login");
		}

		if (credit != null && credit != "") {
			params.put("credits", credit);
		} else {
			params.put("credits", "0");
		}
		String redirect = "";
		if (dcustom != null && dcustom != "") {
			params.put("dcustom", URLEncoder.encode(dcustom,"UTF-8"));
//			params.put("dcustom",dcustom);
		}
		if (redirect != null && redirect != "" && redirect != "null") {
			params.put("redirect", redirect);
		}

		String url = tool.buildUrlWithSign(url1, params);
		System.out.println(url);

		if(rdo2.equals("1"))
		{
			return "redirect:"+url;
		}else{
			model.addAttribute("url", url);
	         return "url";
		}}
}}
		

	

