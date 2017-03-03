package com.qheeshow.eway.service.model;

import java.math.BigDecimal;

/**
 * Created by lihuajun on 17-2-8.
 */
public class OrderDetail extends BaseModel {

    private Integer orderid;//订单id
    private Integer goodsid;//商品id
    private BigDecimal price;//商品价格
    private Integer count;//数量

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
