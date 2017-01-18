package com.qheeshow.eway.common.bean;

/**
 * Created by lihuajun on 17-1-18.
 */
public enum CommonStatus {

    NEW(1),
    PASS(2),
    NOTPASS(3);

    private Integer status;

    CommonStatus(Integer status) {
        this.status = status;
    }

    public Integer value() {
        return status;
    }

}
