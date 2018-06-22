package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class LoginMessage {
    private String id;
    private String msg;
    public LoginMessage(String id, String msg) {
        this.id = id;
        this.msg = msg;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
