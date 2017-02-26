package com.qheeshow.eway.service.service;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface OrderService {

    /**
     * 下单
     * @param projectid
     * @param orderStr
     */
    void place(Integer projectid, String orderStr);

}
