package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/6/7 0007.
 */

public class Userinfo {
    private String no;
    private String name;
    private boolean sex;
    private String schoolName;
    private String deptName;
    private String className;

    public Userinfo(String no, String name, boolean sex, String schoolName, String deptName, String className) {
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.schoolName = schoolName;
        this.deptName = deptName;
        this.className = className;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
