package com.qdu.PhotoSharing.service;

import com.qdu.PhotoSharing.entity.PhotoLike;

import java.util.List;

public interface PhotoLikeService {

    public PhotoLike getPhotoLikeById(int id);

    public List<PhotoLike> getPhotoLikesByUserId(int userId);

    public List<PhotoLike> getPhotoLikesByPhotoId(int photoId);

}
