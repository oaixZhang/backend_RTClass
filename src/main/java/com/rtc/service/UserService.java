package com.rtc.service;

import com.rtc.entity.MyResponse;
import com.rtc.entity.User;
import com.rtc.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiao
 * on 2017/6/2.
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public MyResponse register(User user) {
        int id = userDAO.addUser(user);
        if (id != 0) {
            user.setId(id);
            return new MyResponse(user);
        } else
            return new MyResponse(400, "register failed", "");
    }

    public MyResponse selectUserById(int id) {
        return new MyResponse(userDAO.selectUserById(id));
    }

    public MyResponse login(String account, String password) {
        User user = userDAO.selectUserByAccount(account);
        if (user.getPassword().equals(password))
            return new MyResponse(user);
        else
            return new MyResponse(400, "login failed", "");

    }
}
