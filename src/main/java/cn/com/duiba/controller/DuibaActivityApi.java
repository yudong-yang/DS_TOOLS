package cn.com.duiba.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import cn.com.duiba.ds.tools.sdk.CreditTool;
import cn.com.duiba.ds.tools.sdk.VirtualConsumeParams;
import cn.com.duiba.ds.tools.sdk.VirtualConsumeResult;




@Controller
@RequestMapping("/duiba")
public class DuibaActivityApi {
	private static final Logger logger = LoggerFactory
			.getLogger(DuibaActivityApi.class);

	@Value("${activity.appKey}")
	private String appKey;
	@Value("${activity.appSecret}")
	private String appSecret;

	@RequestMapping("/activity")
	public RedirectView activity(HttpServletRequest request) {
		{
			CreditTool tool = new CreditTool(appKey, appSecret);
			String uid = request.getParameter("uid");
			String transfer = request.getParameter("transfer");
			String dbredirect = request.getParameter("dbredirect");
			Map<String, String> params = new HashMap<String, String>();
			if (StringUtils.isNotBlank(uid)){
				params.put("uid", uid);
				logger.info("用户id==" + uid);
				params.put("credits", "0");
			} else {
				params.put("uid", "not_login");
				logger.info("用户id==" + uid);
				params.put("credits", "0");
			}
			
			if (StringUtils.isNotBlank(dbredirect)) {
				params.put("redirect", dbredirect);
				logger.info("用户redirect" + dbredirect);
			}
			if (StringUtils.isNotBlank(transfer)) {
				params.put("transfer", transfer);
				logger.info("transfer===" + transfer);
			}
			String url = tool.buildUrlWithSign(
					"http://www.duiba.com.cn/autoLogin/autologin?", params);
			return new RedirectView(url, true, false, true);
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

			String supplierBizId="virtual"+params.getOrderNum();
			    	VirtualConsumeResult result= new VirtualConsumeResult("success");
			    	 result.setSupplierBizId(supplierBizId);
					 result.setCredits(0L);
			      return result.toString();
			    
			
			    	
		} catch (Exception e) {
			e.printStackTrace();
			VirtualConsumeResult result= new VirtualConsumeResult("fail");
			result.setErrorMessage(e.toString());
			return result.toString();
		}
		
	}
	
	

}
