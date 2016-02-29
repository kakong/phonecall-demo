package com.zjk.phonecall.entity;

/**
 * Created by zhongjiakang on 16/2/29.
 */
public class UserEntity {
    //public int id;
    public int id;
    public String name;
    public String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
