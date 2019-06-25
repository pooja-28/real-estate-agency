package com.example.pooja.myapplication;

public class userinfo {
    String email,name;
    long phone;

    public userinfo(){

    }

    public userinfo(String email, String name,long phone) {
        this.email = email;
        this.name = name;
        this.phone=phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }
}
