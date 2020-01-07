package com.testSystem.controller;

import com.testSystem.entity.User;
import com.testSystem.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return  "login";
    }

    @RequestMapping("/index")
    public String index(){
        return  "index";
    }

    @RequestMapping("/submitLogin")
    @ResponseBody
    public String submitLogin(String userName, String password){
        String result =  userService.submitLogin(userName,password);
        return  result;
    }
    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public User getCurrentUser() {
        return userService.getUser();
    }
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("/skipMenu")
    public String skipMenu(HttpServletRequest request, Model model)
    {
        String url = request.getParameter("url");
        model.addAttribute("reveiceId","empty");
        return url;
    }

    @RequestMapping("/selDeUser")
    @ResponseBody
    public List<User> selDeUser(){
        List<User> result =  userService.selDeUser();
        return  result;
    }
}
