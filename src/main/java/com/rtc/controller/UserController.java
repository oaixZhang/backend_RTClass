package com.rtc.controller;

import com.google.gson.Gson;
import com.rtc.entity.MyResponse;
import com.rtc.entity.User;
import com.rtc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiao
 * on 2017/6/2.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    MyResponse register(@RequestParam("account") String account, @RequestParam("password") String password,
                        @RequestParam("name") String name, @RequestParam("tag") int tag) {
        User user = new User();
        user.setAccout(account);
        user.setPassword(password);
        user.setName(name);
        user.setTag(tag);
        return userService.register(user);
    }

    @RequestMapping(value = "/queryUser/{id}", method = RequestMethod.GET)
    public @ResponseBody
    MyResponse queryUserById(@PathVariable("id") int id) {
        return userService.selectUserById(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String login(@RequestParam("account") String account, @RequestParam("password") String password) {
        return new Gson().toJson(userService.login(account, password));
    }
}
