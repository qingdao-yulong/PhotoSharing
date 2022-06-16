package com.qdu.PhotoSharing.controller;

import com.qdu.PhotoSharing.entity.PhotoLike;
import com.qdu.PhotoSharing.entity.User;
import com.qdu.PhotoSharing.service.PhotoLikeService;
import com.qdu.PhotoSharing.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PhotoLikeController {

    PhotoLikeService photoLikeService;
    PhotoService photoService;

    @Autowired
    public PhotoLikeController(PhotoLikeService photoLikeService, PhotoService photoService) {
        this.photoLikeService = photoLikeService;
        this.photoService = photoService;
    }

    @PostMapping("photo/like/{p}")
    @ResponseBody
    public void likePhoto(Model model, @PathVariable int p, HttpServletRequest req) {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        PhotoLike photolike = photoLikeService.getPhotoLikeByPictureIdAndUserId(p, u.getId());
        if (null != photolike) {
            photoLikeService.deletePhotoLike(photolike.getId());
            session.removeAttribute("canLike");
            session.setAttribute("canLike", 1);
        } else {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            PhotoLike pl = new PhotoLike(p, u.getId(), date);
            photoLikeService.createPhotoLike(pl);
            session.removeAttribute("canLike");
            session.setAttribute("canLike", 0);
        }
    }
}
