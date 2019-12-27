package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.weshare.entity.User;
import com.tongji.weshare.entity.UserExample;
import com.tongji.weshare.model.ResponseUserModel;
import com.tongji.weshare.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@RestController
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(String email, String userName, String password) throws UnsupportedEncodingException {
        String response;
        byte[] unbase = Base64.getDecoder().decode(password);
        String unbasePassword = new String(unbase, "UTF-8");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        ResponseUserModel responseUserModel = new ResponseUserModel();
        long count = userService.countUsers(userExample);
        if (count==0) {
            User user = new User();
            user.setEmail(email);
            user.setUsername(userName);
            user.setPassword(unbasePassword);
            user.setAvatarUrl("avatar/1.jpg");
            UserExample userExample1 = new UserExample();
            UserExample.Criteria criteria1 = userExample1.createCriteria();
            long tot = userService.countUsers(userExample1);
            int userId = (int)tot + 1;
            user.setUserId(userId);
            int insertUser = userService.newUser(user);
            responseUserModel.setIsSuccess(0);
            responseUserModel.setUserId(userId);
        }
        response = JSON.toJSONString(responseUserModel);
        return response;
    }

}
