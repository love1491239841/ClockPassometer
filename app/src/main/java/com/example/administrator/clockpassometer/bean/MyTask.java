package com.example.administrator.clockpassometer.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class MyTask extends DataSupport{
    private String name;
    private String time;
    private String Content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
