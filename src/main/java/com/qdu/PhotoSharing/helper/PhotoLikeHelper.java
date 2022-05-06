package com.qdu.PhotoSharing.helper;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.entity.PhotoLike;
import com.qdu.PhotoSharing.service.PhotoLikeService;
import com.qdu.PhotoSharing.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoLikeHelper {
    PhotoService photoService;
    PhotoLikeService photoLikeService;

    @Autowired
    public PhotoLikeHelper(PhotoService photoService, PhotoLikeService photoLikeService) {
        this.photoService = photoService;
        this.photoLikeService = photoLikeService;
    }

    /**
     * Calculates the number of likes against a photo and stores it against the Photo object for a list of Photos
     * @param photoList
     */
    public void calculatePhotoLikesForList(List<Photo> photoList) {
        for (Photo p : photoList) {
            List<PhotoLike> photoLikeList = photoLikeService.getPhotoLikesByPhotoId(p.getId());
            int size = photoLikeList.size();

            p.setLikes(size);
        }
    }

    /**
     * Calculates the number of likes against a photo and stores it against the Photo object for a single Photo
     * @param photo
     */
    public void calculatePhotoLikes(Photo photo) {
        List<PhotoLike> photoLikeList = photoLikeService.getPhotoLikesByPhotoId(photo.getId());
        int size = photoLikeList.size();

        photo.setLikes(size);
    }
}
