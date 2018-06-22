package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class Site {
    private String name;
    private String phone;
    private String site;
    private String id;

    public Site(String name, String phone, String site, String id) {
        this.name = name;
        this.phone = phone;
        this.site = site;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
