package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/6/15 0015.
 */

public class Inquire {
    private int score;
    private String coursesName;
    private String teacherName;

    public Inquire(int score, String coursesName, String teacherName) {
        this.score = score;
        this.coursesName = coursesName;
        this.teacherName = teacherName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCoursesName() {
        return coursesName;
    }

    public void setCoursesName(String coursesName) {
        this.coursesName = coursesName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
