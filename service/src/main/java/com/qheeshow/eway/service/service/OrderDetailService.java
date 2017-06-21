package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.OrderDetail;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface OrderDetailService {

    List<OrderDetail> listByProject(Integer projectid);

    List<OrderDetail> listByOrder(Integer orderid);

}
