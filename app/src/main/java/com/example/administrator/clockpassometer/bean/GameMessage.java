package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/5/31 0031.
 */

public class GameMessage {
    private String sponsor;
    private String daynumber;
    private String peoplenumber;

    public GameMessage(String name, String sponsor, String daynumber, String peoplenumber) {
        this.sponsor = sponsor;
        this.daynumber = daynumber;
        this.peoplenumber = peoplenumber;
    }
    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getDaynumber() {
        return daynumber;
    }

    public void setDaynumber(String daynumber) {
        this.daynumber = daynumber;
    }

    public String getPeoplenumber() {
        return peoplenumber;
    }

    public void setPeoplenumber(String peoplenumber) {
        this.peoplenumber = peoplenumber;
    }
}
