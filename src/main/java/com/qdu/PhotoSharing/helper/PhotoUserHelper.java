package com.qdu.PhotoSharing.helper;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.entity.User;
import com.qdu.PhotoSharing.service.PhotoService;
import com.qdu.PhotoSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoUserHelper {

    private UserService userService;
    private PhotoService photoService;

    @Autowired
    public PhotoUserHelper(UserService userService, PhotoService photoService) {
        this.userService = userService;
        this.photoService = photoService;
    }

    /**
     * Sets a User object against a Photo object for a list of Photos
     * @param photoList
     */
    public void linkPhotoListWithUsers(List<Photo> photoList) {
        for (Photo p : photoList) {
            int i = p.getUserId();

            User user = userService.getUserById(i);

            p.setUser(user);
        }
    }

    /**
     * Sets a User object against a Photo object
     * @param photo
     */
    public void linkPhotoWithUser(Photo photo) {
            int i = photo.getUserId();

            User user = userService.getUserById(i);

            photo.setUser(user);
    }

}
