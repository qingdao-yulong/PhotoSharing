package com.qdu.PhotoSharing.controller;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.helper.PhotoUserHelper;
import com.qdu.PhotoSharing.service.PhotoService;
import com.qdu.PhotoSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {

    PhotoService photoService;
    PhotoUserHelper photoUserHelper;

    @Autowired
    public HomeController(PhotoService photoService, PhotoUserHelper photoUserHelper) {
        this.photoService = photoService;
        this.photoUserHelper = photoUserHelper;
    }

    @GetMapping("")
    public String homePage(Model model) {
        List<Photo> photoList = photoService.getPhotosByDisplay(true);

        photoUserHelper.linkPhotoListWithUsers(photoList);

        photoList.sort(Comparator.comparing(Photo::getLikes));

        if (photoList.size() > 9) {
        photoList.subList(0, 9);
        }

        model.addAttribute("photos", photoList);

        return "index";
    }
}

