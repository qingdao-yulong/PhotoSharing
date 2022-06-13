package com.qdu.PhotoSharing.service;

import com.qdu.PhotoSharing.entity.PhotoLike;

import java.util.List;

public interface PhotoLikeService {

    public PhotoLike getPhotoLikeById(int id);

    public List<PhotoLike> getPhotoLikesByUserId(int userId);

    public List<PhotoLike> getPhotoLikesByPhotoId(int photoId);

    public PhotoLike getPhotoLikeByPictureIdAndUserId(int picture, int user);

    public PhotoLike createPhotoLike(PhotoLike photoLike);

    public PhotoLike editPhotoLike(PhotoLike photoLike);

    public void deletePhotoLike(int photoLike);

    public void deletePhotoLikesByPictureId(int picture);

    public PhotoLike getPhotoLikeByUserIdAndLikedAt(int user, String likedAt);

}
