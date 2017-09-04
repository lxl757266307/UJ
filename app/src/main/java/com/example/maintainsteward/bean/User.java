package com.example.maintainsteward.bean;

/**
 * Created by Administrator on 2017/8/5.
 */

public class User {
    String phone;
    String password;

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
