package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/6/6 0006.
 */

public class Task {
    private String way;
    private String distance;
    private String state;

    public Task(String way, String distance, String state) {
        this.way = way;
        this.distance = distance;
        this.state = state;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
