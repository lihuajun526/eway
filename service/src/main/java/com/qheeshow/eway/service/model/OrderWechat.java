package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 2017/4/26.
 */
public class OrderWechat {

    private String description;
    private String orderno;
    private String totalFee;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
}
