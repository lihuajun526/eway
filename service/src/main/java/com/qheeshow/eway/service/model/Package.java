package com.qheeshow.eway.service.model;

import java.math.BigDecimal;

/**
 * Created by lihuajun on 17-2-8.
 */
public class Package extends BaseModel{

    private String title;
    private String desc;
    private BigDecimal price;

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
}
