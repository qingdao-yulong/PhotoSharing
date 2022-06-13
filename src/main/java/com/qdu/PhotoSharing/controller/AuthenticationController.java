package com.qdu.PhotoSharing.controller;

import com.qdu.PhotoSharing.entity.PhotoLike;
import com.qdu.PhotoSharing.entity.User;
import com.qdu.PhotoSharing.service.PhotoLikeService;
import com.qdu.PhotoSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AuthenticationController {

    UserService userService;
    HomeController homeController;
    PhotoLikeService photoLikeService;

    @Autowired
    public AuthenticationController(UserService userService, HomeController homeController, PhotoLikeService photoLikeService) {
        this.userService = userService;
        this.homeController = homeController;
        this.photoLikeService = photoLikeService;
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
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            PhotoLike photoLike = photoLikeService.getPhotoLikeByUserIdAndLikedAt(user.getId(), date);
            if (null != photoLike) {
                httpSession.setAttribute("canLike", 0);
            } else {
                httpSession.setAttribute("canLike", 1);
            }
            return homeController.homePage(model, req);
        } else {
            return "authentication/login_error";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        httpSession.invalidate();

        return homeController.homePage(model, req);
    }

    @GetMapping("/register")
    public String toRegister() {
        return "authentication/register";
    }

    @PostMapping("/register")
    public String register(Model model, HttpServletRequest req, @ModelAttribute User user) {
        HttpSession httpSession = req.getSession();
        User u = userService.getUserByEmail(user.getEmail());
        if (null != u) {
            return "error";
        } else {
            user.setSuperUser(false);
            User newUser = userService.createUser(user);
            if (null != newUser) {
                httpSession.setAttribute("user", newUser);
                httpSession.setAttribute("admin", 0);
                return homeController.homePage(model, req);
            } else {
                return "error";
            }
        }
    }
}
