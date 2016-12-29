package cn.com.duiba.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.duiba.entity.User;
import cn.com.duiba.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> getUser(){
        logger.info("从数据库读取User集合");
        return userService.getList();
    }
    @RequestMapping("/insert")
    public void IsertUser(){
        logger.info("插入数据库");
        User user=new User();
        user.setUserid("123456");
        user.setUsername("xiaowang");
        user.setCredits(12547L);
        user.setVip("4");
        user.setPhone(null);
		 userService.insert(user);
    }   
    
    @RequestMapping("/getcredits")
    public Long getCredits(){
        logger.info("从数据库读取积分");
        return userService.GetCreditsByUid("3333");
    }
}
