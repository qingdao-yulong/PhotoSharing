package com.qdu.PhotoSharing.entity;

import javax.persistence.*;

@Entity
@Table(name = "PictureLikes")
public class PhotoLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int picture;
    private int user;
    private String likedAt;

    public PhotoLike() {
    }

    public PhotoLike(int picture, int user, String likedAt) {
        this.picture = picture;
        this.user = user;
        this.likedAt = likedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(String likedAt) {
        this.likedAt = likedAt;
    }
}
