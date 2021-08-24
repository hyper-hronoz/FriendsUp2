package com.example.friendsup.model;

import com.google.gson.annotations.SerializedName;

public class RegisteredUserModel extends UserModel {

    @SerializedName("_id")
    public String id;
    public String about;
    public String userPhoto;
    public int age;

    @Override
    public String toString() {
        return "RegisteredUserModel{" +
                    "age=" + age +
                    ", id='" + id + '\'' +
                    ", about='" + about + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", gender='" + gender + '\'' +
                    ", email='" + email + '\'' +
                '}';
    }

    public RegisteredUserModel(String about, int age, String userPhoto, String username) {
        super(username);
        this.about = about;
        this.age = age;
        this.userPhoto = userPhoto;
    }

    public RegisteredUserModel(String username, String about, String id, int age) {
        super(username);
        this.about = about;
        this.id  = id;
        this.age = age;
    }

    public RegisteredUserModel() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getAge() {
        return age;
    }

    public String getAbout() {
        return about;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setId(String string) {
        this.id = string;
    }
}

