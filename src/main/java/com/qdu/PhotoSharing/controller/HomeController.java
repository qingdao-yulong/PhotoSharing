package com.qdu.PhotoSharing.controller;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {

    PhotoService photoService;

    @Autowired
    public HomeController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("")
    public String homePage(Model model) {
        List<Photo> photoList = photoService.getPhotosByDisplay(true);

            model.addAttribute("photos", photoList);

        return "index";
    }
}

