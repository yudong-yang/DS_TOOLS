package cn.com.duiba.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.duiba.sendmsg.SendMsgUtil;

/**
 * 短信验证码发送机制和校验
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/Msg")
public class MsgCodeController {

	@RequestMapping("/getcode")
	public String getcode(HttpServletRequest request) {
		return "checkcode";
	}
	@RequestMapping("/checksuccess")
	@ResponseBody
	public String checksuccess(HttpServletRequest request) {
		return "sussess";
	}

	/**
	 * 通过获取手机号，发送相应的短信验证码
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/sendMessage")
	@ResponseBody
	public String sendMessage(HttpServletRequest request)
			throws UnsupportedEncodingException {
		String code = SendMsgUtil.createRandomVcode();
		String phones = request.getParameter("telephone");
		String content = "尊敬的用户，您的验证码为[" + code + "]，请注意保存，不要告诉第其他人【杨玉东】";
//		SendMsgUtil.sendMsg(phones, content);
		HttpSession session = request.getSession();
		session.setAttribute("code", code);
		return "success";
	}

	/**
	 * 短信验证的输入验证
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkcode")
	@ResponseBody
	public String checkcode(HttpServletRequest request) {
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String check = (String) session.getAttribute("code");
		System.out.println("code==:" + code + "======session=:" + check);
		if (code.equals(check)) {
			return "success";
		} else {
			return "fail";
		}
	}
}
