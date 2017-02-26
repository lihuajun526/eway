package com.qheeshow.eway.service.model;

import java.math.BigDecimal;

/**
 * Created by lihuajun on 17-1-15.
 */
public class Goods extends BaseModel {

    private String title;
    private String desc;
    private BigDecimal price;
    private String items;//#号分隔
    private Integer status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
