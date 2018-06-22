package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/5/16 0016.
 */

public class Game {
    private String name;
    private long beginTime;
    private long overTime;

    public Game(String name, long beginTime, long overTime) {
        this.name = name;
        this.beginTime = beginTime;
        this.overTime = overTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getOverTime() {
        return overTime;
    }

    public void setOverTime(long overTime) {
        this.overTime = overTime;
    }
}
