package com.qdu.PhotoSharing.controller;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.entity.PhotoLike;
import com.qdu.PhotoSharing.entity.User;
import com.qdu.PhotoSharing.helper.PhotoLikeHelper;
import com.qdu.PhotoSharing.helper.PhotoUploadHelper;
import com.qdu.PhotoSharing.helper.PhotoUserHelper;
import com.qdu.PhotoSharing.service.PhotoLikeService;
import com.qdu.PhotoSharing.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/account")
public class PhotoController {

    PhotoService photoService;
    PhotoLikeHelper photoLikeHelper;
    PhotoLikeService photoLikeService;
    PhotoUserHelper photoUserHelper;
    PhotoUploadHelper puh;

    @Autowired
    public PhotoController(PhotoService photoService, PhotoUploadHelper puh, PhotoLikeHelper photoLikeHelper, PhotoLikeService photoLikeService, PhotoUserHelper photoUserHelper) {
        this.photoService = photoService;
        this.puh = puh;
        this.photoLikeHelper = photoLikeHelper;
        this.photoLikeService = photoLikeService;
        this.photoUserHelper = photoUserHelper;
    }

    @GetMapping("/{userId}/photos")
    public String userPhotos(Model model, @PathVariable int userId) {
        List<Photo> photoList = photoService.getPhotosByUserId(userId);

        photoLikeHelper.calculatePhotoLikesForList(photoList);

        photoUserHelper.linkPhotoListWithUsers(photoList);

        photoList.sort(Comparator.comparing(Photo::getLikes));

        model.addAttribute("photoList", photoList);

        return "/account/photos/user_photos";
    }

    @GetMapping("/{userId}/photoUpload")
    public String toPhotoUpload(@PathVariable int userId) {
        return "/account/photos/upload_photo";
    }

    @PostMapping("/{userId}/photoUpload")
    public RedirectView photoUpload(Model model, @ModelAttribute Photo photo, @PathVariable int userId, @RequestParam("fileImage") MultipartFile file) throws IOException {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String fileName = puh.saveImage(file);
        photo.setFilePath(fileName);
        photo.setUploadedAt(date);
        photoService.createPhoto(photo);

        return new RedirectView("/account/" + userId + "/photos");
    }

    @GetMapping("/{userId}/photo/{photoId}/delete")
    public void deletePhoto(@PathVariable int photoId, @PathVariable int userId) {
        photoService.deletePhoto(photoId);
    }

    @GetMapping("/{userId}/photos/liked")
    public String likedPhotos(Model model, @PathVariable int userId) {

        List<PhotoLike> photoLikes = photoLikeService.getPhotoLikesByUserId(userId);

        List<Photo> photoList = new ArrayList<>();

        for (PhotoLike pl : photoLikes) {
            Photo p = photoService.getPhotoById(pl.getPictureId());

            photoList.add(p);
        }

        photoUserHelper.linkPhotoListWithUsers(photoList);

        photoLikeHelper.calculatePhotoLikesForList(photoList);

        photoList.sort(Comparator.comparing(Photo::getLikes));

        // Reverse the list so the order is desc
        Collections.reverse(photoList);

        model.addAttribute("photos", photoList);

        return "account/photos/likedPhotos";
    }

    @GetMapping("/photo/edit/{p}")
    public String toEditPhoto(Model model, @PathVariable int p) {
        Photo photo = photoService.getPhotoById(p);
        model.addAttribute("category", photo.getCategory());
        model.addAttribute("display", photo.getDisplay());

        return "account/photos/editPhoto";
    }

    @PostMapping("/photo/edit/{p}")
    public RedirectView editPhoto(Model model, @ModelAttribute Photo photo, @PathVariable int p, HttpServletRequest req) {
        photo.setId(p);
        if (photo.getDisplay() == null) {
            photo.setDisplay(false);
        } else {
            photo.setDisplay(true);
        }
        Photo oldP = photoService.getPhotoById(p);
        photo.setFilePath(oldP.getFilePath());
        photo.setUploadedAt(oldP.getUploadedAt());
        photo.setUserId(oldP.getUserId());
        photoService.editPhoto(photo);
        HttpSession s = req.getSession();
        User u = (User) s.getAttribute("user");

        return new RedirectView("/account/" + u.getId() + "/photos");
    }

    @GetMapping("/photo/delete/{p}")
    @ResponseBody
    @Transactional
    public void deletePhoto(Model model, @PathVariable int p) {
        photoLikeService.deletePhotoLikesByPictureId(p);
        photoService.deletePhoto(p);
    }
}