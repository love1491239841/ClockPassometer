package com.example.administrator.clockpassometer.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/6/7 0007.
 */

public class Record  extends DataSupport {
    private double distance;
    private double speed;
    private String time;
    private String state;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
