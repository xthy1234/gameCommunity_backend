package com.cn.gamecommunity_backend.entity;

public class User1 {
    private String name;
    private String account;
    private String password;
    private char sex;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User1(String name, String account, String password, String phone, char sex) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
    }
}
