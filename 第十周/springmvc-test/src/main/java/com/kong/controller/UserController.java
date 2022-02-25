package com.kong.controller;

import com.kong.domain.User;
import com.kong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        User user = userService.login(username,password);
        if(user!=null){
            session.setAttribute("user",user);
            return "redirect:/success.jsp";
        }
        return "redirect:/error.jsp";
    }

    @RequestMapping("/register")
    public String reg(User user){
        System.out.println(user.getAge());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        userService.reg(user);
        return "redirect:/success.jsp";
    }

}
