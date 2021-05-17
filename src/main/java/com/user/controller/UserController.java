package com.user.controller;

import com.user.entity.User;
import com.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @GetMapping("listUserNew")
    public List<User> updateNew() {
        try {
            return userService.updateNew();
        } catch (Exception e) {
            logger.error("查询用户列表错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
