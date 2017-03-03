package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.service.OrderService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 下单
     *
     * @param projectid
     * @param orderStr
     * @return
     */
    @RequestMapping("/place")
    @ResponseBody
    public String place(Integer projectid, String orderStr) {
        //goodsid_count#goodsid_count#goodsid_count
        Result result = new Result();
        orderService.place(projectid, orderStr);
        return result.toString();
    }

}
