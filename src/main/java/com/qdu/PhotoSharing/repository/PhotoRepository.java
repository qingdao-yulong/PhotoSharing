package com.qdu.PhotoSharing.repository;

import com.qdu.PhotoSharing.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    public List<Photo> getPhotosByUserId(int userId);

    public List<Photo> getPhotosByCategory(String category);

    public List<Photo> getPhotosByDisplay(boolean display);

    public List<Photo> getPhotosByUserIdAndDisplay(int userId, Boolean display);

    public Photo getPhotoByUserIdAndUploadedAt(int user, String date);

    public List<Photo> getPhotosByDisplayAndCategory(boolean display, String category);
}
