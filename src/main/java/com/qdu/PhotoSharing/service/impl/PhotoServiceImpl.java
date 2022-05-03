package com.qdu.PhotoSharing.service.impl;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.helper.PhotoLikeHelper;
import com.qdu.PhotoSharing.repository.PhotoRepository;
import com.qdu.PhotoSharing.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    PhotoRepository photoRepository;
    PhotoLikeHelper photoLikeHelper;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository, PhotoLikeHelper photoLikeHelper) {
        this.photoRepository = photoRepository;
        this.photoLikeHelper = photoLikeHelper;
    }

    @Override
    public Photo getPhotoById(int id) {
        return photoRepository.findById(id).get();
    }

    @Override
    public List<Photo> getAllPhotos() {
        List<Photo> photoList =  photoRepository.findAll();
        photoLikeHelper.calculatePhotoLikesForList(photoList);

        return photoList;
    }

    @Override
    public List<Photo> getPhotosByUserId(int userId) {
        List<Photo> photoList = photoRepository.getPhotosByUserId(userId);
        photoLikeHelper.calculatePhotoLikesForList(photoList);

        return photoList;
    }

    @Override
    public List<Photo> getPhotosByCategory(String category) {
        List<Photo> photoList = photoRepository.getPhotosByCategory(category);
        photoLikeHelper.calculatePhotoLikesForList(photoList);

        return photoList;
    }

    @Override
    public List<Photo> getPhotosByDisplay(boolean display) {
        List<Photo> photoList = photoRepository.getPhotosByDisplay(display);
        photoLikeHelper.calculatePhotoLikesForList(photoList);

        return photoList;
    }

    @Override
    public List<Photo> getPhotosByUserIdAndDisplay(int userId, boolean display) {
        List<Photo> photoList = photoRepository.getPhotosByUserIdAndDisplay(userId, display);
        photoLikeHelper.calculatePhotoLikesForList(photoList);

        return photoList;
    }

    @Override
    public Photo createPhoto(Photo photo) {
        Photo newPhoto = photoRepository.save(photo);
        newPhoto.setLikes(0);

        return newPhoto;
    }

    @Override
    public Photo editPhoto(Photo photo) {
        Photo updated = photoRepository.save(photo);
        photoLikeHelper.calculatePhotoLikes(updated);

        return updated;
    }

    @Override
    public void deletePhoto(int photo) {
        photoRepository.deleteById(photo);
    }
}
