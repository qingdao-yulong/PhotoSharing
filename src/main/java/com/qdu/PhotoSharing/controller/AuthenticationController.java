package com.qdu.PhotoSharing.controller;

import com.qdu.PhotoSharing.entity.User;
import com.qdu.PhotoSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String toLogin() {
        return "authentication/login";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        User user = userService.getUserByEmailAndPassword(req.getParameter("email"), req.getParameter("password"));
        if (null != user) {
            httpSession.setAttribute("user", user);
            if (user.isSuperUser()) {
                httpSession.setAttribute("admin", 1);
            }
            return "index";
        } else {
            return "authentication/login_error";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        httpSession.invalidate();

        return "index";
    }
}
