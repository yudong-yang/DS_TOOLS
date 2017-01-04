package cn.com.duiba.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.duiba.entity.User;
import cn.com.duiba.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/insert/{number}")
    public String IsertUser(@PathVariable("number") int number){
        logger.info("插入数据库");
        for (int i = 0; i < number; i++) {
			
		
        User user=new User();
        String uuid = System.currentTimeMillis()+"";
        String uid = uuid.substring(8);
        user.setUserid(uid);
        user.setUsername("xiaowang");
        user.setCredits(12547L);
        user.setVip("4");
        user.setPhone("");
		userService.insert(user);
        }
		return "redirect:/user/list";	
    }   
    
    @RequestMapping("/getcredits")
    @ResponseBody
    public Long getCredits(){
        logger.info("从数据库读取积分");
        return userService.GetCreditsByUid("3333");
    }
    
	@RequestMapping("/list")
	public String list(Model model) {
		List<User> list = userService.getList();
		model.addAttribute("users", list);
		return "list";
	}
    
    @RequestMapping("/delete")
    @ResponseBody
    public String DeleteByid(HttpServletRequest request){
    	String userid=request.getParameter("userid");
        logger.info("删除指定元素");
        return userService.deleteByid(userid);
    }
}
