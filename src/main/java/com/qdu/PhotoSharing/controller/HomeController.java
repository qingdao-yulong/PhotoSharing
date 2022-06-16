package com.qdu.PhotoSharing.controller;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.entity.PhotoLike;
import com.qdu.PhotoSharing.entity.User;
import com.qdu.PhotoSharing.helper.PhotoLikeHelper;
import com.qdu.PhotoSharing.helper.PhotoUserHelper;
import com.qdu.PhotoSharing.service.PhotoLikeService;
import com.qdu.PhotoSharing.service.PhotoService;
import com.qdu.PhotoSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    PhotoService photoService;
    PhotoUserHelper photoUserHelper;
    PhotoLikeHelper photoLikeHelper;
    PhotoLikeService photoLikeService;

    @Autowired
    public HomeController(PhotoService photoService, PhotoUserHelper photoUserHelper, PhotoLikeHelper photoLikeHelper, PhotoLikeService photoLikeService) {
        this.photoService = photoService;
        this.photoUserHelper = photoUserHelper;
        this.photoLikeHelper = photoLikeHelper;
        this.photoLikeService = photoLikeService;
    }

    @GetMapping("")
    public String homePage(Model model, HttpServletRequest req) {

        HttpSession session = req.getSession();

        List<Photo> photoList = photoService.getPhotosByDisplay(true);

        photoUserHelper.linkPhotoListWithUsers(photoList);

        photoLikeHelper.calculatePhotoLikesForList(photoList);

        if (photoList.size() > 6) {
        photoList.subList(0, 6);
        }

        if (null != session.getAttribute("user")) {
            User u = (User) session.getAttribute("user");
            for (Photo p : photoList) {
                List<PhotoLike> photoLikeList = photoLikeService.getPhotoLikesByPhotoId(p.getId());
                for (PhotoLike pl : photoLikeList) {
                    if (pl.getUserId() == u.getId()) {
                        p.setLiked(1);
                    }
                }
            }
        }

        model.addAttribute("photos", photoList);

        return "index";
    }

    @GetMapping("/filter/{category}")
    public String filterPictures(Model model, HttpServletRequest req, @PathVariable String category) {

        HttpSession session = req.getSession();

        List<Photo> photoList = photoService.getPhotosByDisplay(true);

        if (!category.equals("all")) {
            photoList.removeIf(photo -> !photo.getCategory().equals(category));
        }

        model.addAttribute("category", category);

        photoUserHelper.linkPhotoListWithUsers(photoList);

        photoLikeHelper.calculatePhotoLikesForList(photoList);

        if (photoList.size() > 9) {
            photoList.subList(0, 9);
        }

        if (null != session.getAttribute("user")) {
            User u = (User) session.getAttribute("user");
            for (Photo p : photoList) {
                List<PhotoLike> photoLikeList = photoLikeService.getPhotoLikesByPhotoId(p.getId());
                for (PhotoLike pl : photoLikeList) {
                    if (pl.getUserId() == u.getId()) {
                        p.setLiked(1);
                    }
                }
            }
        }

        model.addAttribute("photos", photoList);

        return "index";
    }
}

