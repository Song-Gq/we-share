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
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(String email, String password) throws UnsupportedEncodingException {
        String response;
        byte[] unbase = Base64.getDecoder().decode(password);
        String unbasePassword = new String(unbase, "UTF-8");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<User> users = userService.getUsers(userExample);
        ResponseUserModel responseUserModel = new ResponseUserModel();
        if (users.size()==1) {
            if (users.get(0).getPassword().equals(unbasePassword)) {
                responseUserModel.setIsSuccess(0);
                responseUserModel.setUserId(users.get(0).getUserId());
                responseUserModel.setUserName(users.get(0).getUsername());
            }
        }
        response = JSON.toJSONString(responseUserModel);
        return response;
    }
}
