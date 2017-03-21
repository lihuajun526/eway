package com.qheeshow.eway.service.service;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface OrderService {

    /**
     * 下单
     * @param userid
     * @param projectid
     * @param orderStr
     */
    void place(Integer userid, Integer projectid, String orderStr);

}
