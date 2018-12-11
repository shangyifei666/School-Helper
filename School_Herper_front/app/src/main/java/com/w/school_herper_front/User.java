package com.w.school_herper_front;

/*
 * 功能：用户信息类的创建和获取数值
 * 开发人：赵璐
 * 开发时间：2018.11.27
 *
 * 描述：注册页面，相关xml：activity_main.xml
 */

import android.hardware.usb.UsbRequest;

public class User {
    private String phone;
    private String password;
    private String name;
    private String school;
    private String stuNumber;

    public User(String phone, String password){
        this.phone = phone;
        this.password = password;
    }

    public User(String phone, String password, String name, String school, String stuNumber) {
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.school = school;
        this.stuNumber = stuNumber;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

