package com.qdu.PhotoSharing.controller;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.entity.User;
import com.qdu.PhotoSharing.helper.PhotoLikeHelper;
import com.qdu.PhotoSharing.helper.PhotoUploadHelper;
import com.qdu.PhotoSharing.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/account")
public class PhotoController {

    PhotoService photoService;
    PhotoLikeHelper photoLikeHelper;
    PhotoUploadHelper puh;

    @Autowired
    public PhotoController(PhotoService photoService, PhotoUploadHelper puh, PhotoLikeHelper photoLikeHelper) {
        this.photoService = photoService;
        this.puh = puh;
        this.photoLikeHelper = photoLikeHelper;
    }

    @GetMapping("/{userId}/photos")
    public String userPhotos(Model model, @PathVariable int userId) {
        List<Photo> photoList = photoService.getPhotosByUserId(userId);

        photoLikeHelper.calculatePhotoLikesForList(photoList);

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
        String fileName = puh.saveImage("photos/", file);
        photo.setFilePath(fileName);
        photoService.createPhoto(photo);

        return new RedirectView("/account/" + userId + "/photos");
    }

    @GetMapping("/{userId}/photo/{photoId}/delete")
    public void deletePhoto(@PathVariable int photoId, @PathVariable int userId) {
        photoService.deletePhoto(photoId);
    }
}