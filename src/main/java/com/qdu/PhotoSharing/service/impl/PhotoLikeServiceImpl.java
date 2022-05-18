package com.qdu.PhotoSharing.service.impl;

import com.qdu.PhotoSharing.entity.PhotoLike;
import com.qdu.PhotoSharing.repository.PhotoLikeRepository;
import com.qdu.PhotoSharing.service.PhotoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoLikeServiceImpl implements PhotoLikeService {

    PhotoLikeRepository plr;

    @Autowired
    public PhotoLikeServiceImpl(PhotoLikeRepository plr) {
        this.plr = plr;
    }

    @Override
    public PhotoLike getPhotoLikeById(int id) {
        return plr.getById(id);
    }

    @Override
    public List<PhotoLike> getPhotoLikesByUserId(int userId) {
        return plr.getPhotoLikesByUserId(userId);
    }

    @Override
    public List<PhotoLike> getPhotoLikesByPhotoId(int photoId) {
        return plr.getPhotoLikesByPictureId(photoId);
    }

    @Override
    public PhotoLike createPhotoLike(PhotoLike photoLike) {
        return plr.save(photoLike);
    }

    @Override
    public PhotoLike editPhotoLike(PhotoLike photoLike) {
        return plr.save(photoLike);
    }

    @Override
    public void deletePhotoLike(int photoLike) {
        plr.deleteById(photoLike);
    }
}
