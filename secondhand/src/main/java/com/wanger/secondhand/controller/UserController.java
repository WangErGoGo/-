package com.wanger.secondhand.controller;


import com.wanger.secondhand.entity.User;
import com.wanger.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wanger
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public MyMessage register(@RequestParam("openid") String openId) {
        User user = new User(openId);

        if (userService.getBaseMapper().insert(user) != 0) {
            return new MyMessage(0, "注册成功");
        }
        return new MyMessage(-1, "注册失败");
    }
}

