package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-2-8.
 */
public class PackageGoodsMapping extends BaseModel {

    private Integer packageid;
    private Integer goodsid;

    public Integer getPackageid() {
        return packageid;
    }

    public void setPackageid(Integer packageid) {
        this.packageid = packageid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }
}
