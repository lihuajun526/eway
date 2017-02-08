package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-2-8.
 */
public class OrderDetail extends BaseModel {

    private Integer orderid;
    private Integer goodsid;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }
}
