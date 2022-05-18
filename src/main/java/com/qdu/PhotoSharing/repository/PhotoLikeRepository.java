package com.qdu.PhotoSharing.repository;

import com.qdu.PhotoSharing.entity.PhotoLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoLikeRepository extends JpaRepository<PhotoLike, Integer> {

    public List<PhotoLike> getPhotoLikesByUserId(int user);

    public List<PhotoLike> getPhotoLikesByPictureId(int picture);

}
