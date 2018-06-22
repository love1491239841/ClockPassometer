package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/6/7 0007.
 */

public class User {
    private String name;
    private String no;

    public User(String name, String no) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
