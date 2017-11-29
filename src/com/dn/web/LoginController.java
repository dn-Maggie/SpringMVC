package com.dn.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dn.domain.User;
import com.dn.service.UserService;

/**
 * Created by Maggie on 2017/9/22 0022.
 */

@Controller

public class LoginController {
    private UserService userService;
    @RequestMapping(value = "/index.do")
    public String loginPage(){
        return "login";
    }
    @RequestMapping(value = "/loginCheck.do")
    public ModelAndView loginCheck(HttpServletRequest request ,User loginUser){
        boolean isValidUser = userService.hasMatchUser(loginUser.getUserName(),loginUser.getPassword());
        if (!isValidUser){
            return new ModelAndView("login","error","用户名或密码错误");
        }else{
            User user = userService.findUserByUserName(loginUser.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }

    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

}
