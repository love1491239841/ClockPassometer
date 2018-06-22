package com.example.administrator.clockpassometer.bean;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class Exchang {
    private String productName;
    private int integral;

    public Exchang(String productName, int integral) {
        this.productName = productName;
        this.integral = integral;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
