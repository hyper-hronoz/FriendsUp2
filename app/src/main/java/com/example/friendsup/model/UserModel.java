package com.example.friendsup.model;

public class UserModel {
    public String username;
    public String password;
    public String gender;
    public String email;

    public UserModel(String username, String password, String gender, String email) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.email = email;
    }

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserModel(String username) {
        this.username = username;
    }

    public UserModel() {}

    public String getUsername() {
        return username;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

