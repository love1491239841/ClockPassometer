package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/5/17 0017.
 */

public class Score {
    private String name;
    private String score;

    public Score(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
