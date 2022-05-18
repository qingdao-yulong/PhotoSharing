package com.qdu.PhotoSharing.entity;

import javax.persistence.*;

@Entity
@Table(name = "picture_likes")
public class PhotoLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pictureId;
    private int userId;
    private String likedAt;

    public PhotoLike() {
    }

    public PhotoLike(int pictureId, int userId, String likedAt) {
        this.pictureId = pictureId;
        this.userId = userId;
        this.likedAt = likedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(String likedAt) {
        this.likedAt = likedAt;
    }
}
