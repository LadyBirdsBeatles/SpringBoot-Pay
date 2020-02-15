package com.xiaochen.beatles.controller;

import com.xiaochen.beatles.pojo.User;
import com.xiaochen.beatles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @author leechenxiang
 * @version V1.0
 * @Title: UserController.java
 * @Package com.sihai.controller
 * @Description: 用户controller
 * Copyright: Copyright (c) 2016
 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 * @date 2017年8月17日 下午8:39:47
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public ModelAndView products() throws Exception {
        List<User> userList = userService.getUserList();
        ModelAndView model = new ModelAndView("user");
        model.addObject("userList", userList);

        return model;
    }

}
