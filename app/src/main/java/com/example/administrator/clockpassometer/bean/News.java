package com.example.administrator.clockpassometer.bean;

import android.provider.ContactsContract;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class News implements Serializable {
    private String title;
    private long addTime;
    private String content;

    public News(String title, long addTime, String content) {
        this.title = title;
        this.addTime = addTime;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
