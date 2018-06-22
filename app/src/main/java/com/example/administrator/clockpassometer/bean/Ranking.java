package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/6/1 0001.
 */

public class Ranking  {
    private String userName;

    public Ranking(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
