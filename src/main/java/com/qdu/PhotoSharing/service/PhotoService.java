package com.qdu.PhotoSharing.service;

import com.qdu.PhotoSharing.entity.Photo;

import java.util.List;

public interface PhotoService {

    public Photo getPhotoById(int id);

    public List<Photo> getAllPhotos();

    public List<Photo> getPhotosByUserId(int userId);

    public List<Photo> getPhotosByCategory(String category);

    public List<Photo> getPhotosByDisplay(boolean display);

    public List<Photo> getPhotosByUserIdAndDisplay(int userId, boolean display);

}
