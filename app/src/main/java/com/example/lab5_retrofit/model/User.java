package com.example.lab5_retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {
    @SerializedName("_id")
    private String id;
    private String username;
    private String email;
    private String diachi;
    private String image;

    public User() {
    }

    public User(String id, String username, String email, String diachi, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.diachi = diachi;
        this.image = image;
    }

    public User(String username, String email, String diachi, String image) {
        this.username = username;
        this.email = email;
        this.diachi = diachi;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
