package com.example.administrator.clockpassometer.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class Commodity implements Serializable {
    private int id;
    private String name;
    private int storage;
    private int integral;
    private String content;

    public Commodity(int id, String name, int storage, int integral, String content) {
        this.id = id;
        this.name = name;
        this.storage = storage;
        this.integral = integral;
        this.content = content;
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

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
