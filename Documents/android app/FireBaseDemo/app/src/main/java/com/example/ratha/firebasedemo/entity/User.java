package com.example.ratha.firebasedemo.entity;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ratha on 12/11/2017.
 */

@IgnoreExtraProperties
public class User {
    public String name;
    public String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int userId;

    public User(){}
    public User(String name,String email){
        this.name=name;this.email=email;
    }

    public User(String name,String email,String password){
        this.name=name;this.email=email;this.password=password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map<String,Object> toMap(){

        Map<String,Object> restult=new HashMap<>();
        restult.put("userId",userId);
        restult.put("name",name);
        restult.put("email",email);

        return restult;

    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }
}
