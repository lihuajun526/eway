package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.OrderDetailMapper;
import com.qheeshow.eway.service.model.OrderDetail;
import com.qheeshow.eway.service.model.OrderDetailExample;
import com.qheeshow.eway.service.model.OrderExample;
import com.qheeshow.eway.service.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/21.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetail> listByProject(Integer projectid) {
        return orderDetailMapper.listByProject(projectid);
    }

    @Override
    public List<OrderDetail> listByOrder(Integer orderid) {
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria criteria =example.createCriteria();
        criteria.andOrderidEqualTo(orderid);
        return orderDetailMapper.selectByExample(example);
    }
}
