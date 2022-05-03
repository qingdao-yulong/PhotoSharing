package com.qdu.PhotoSharing.entity;

import javax.persistence.*;

@Entity
@Table(name="Pictures")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String filePath;
    private int userId;
    private String uploadedAt;
    private String category;
    private Boolean display;

    @Transient
    private int likes;

    public Photo() {
    }

    public Photo(String filePath, int userId, String uploadedAt, String category, Boolean display) {
        this.filePath = filePath;
        this.userId = userId;
        this.uploadedAt = uploadedAt;
        this.category = category;
        this.display = display;
    }

    public int getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }
}
