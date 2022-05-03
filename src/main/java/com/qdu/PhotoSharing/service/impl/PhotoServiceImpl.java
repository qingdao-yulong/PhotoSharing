package com.qdu.PhotoSharing.service.impl;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.repository.PhotoRepository;
import com.qdu.PhotoSharing.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo getPhotoById(int id) {
        return photoRepository.findById(id).get();
    }

    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    @Override
    public List<Photo> getPhotosByUserId(int userId) {
        return photoRepository.getPhotosByUserId(userId);
    }

    @Override
    public List<Photo> getPhotosByCategory(String category) {
        return photoRepository.getPhotosByCategory(category);
    }

    @Override
    public List<Photo> getPhotosByDisplay(boolean display) {
        return photoRepository.getPhotosByDisplay(display);
    }

    @Override
    public List<Photo> getPhotosByUserIdAndDisplay(int userId, boolean display) {
        return photoRepository.getPhotosByUserIdAndDisplay(userId, display);
    }

    @Override
    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public Photo editPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void deletePhoto(int photo) {
        photoRepository.deleteById(photo);
    }
}
