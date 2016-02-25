package com.hujiang.designsupportlibrarydemo;

/**
 * Created by zhongjiakang on 16/2/24.
 */
public class CoustomerEntity {
    String name;
    String number;
    boolean contact_status;

    public boolean isContact_status() {
        return contact_status;
    }

    public void setContact_status(boolean contact_status) {
        this.contact_status = contact_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
