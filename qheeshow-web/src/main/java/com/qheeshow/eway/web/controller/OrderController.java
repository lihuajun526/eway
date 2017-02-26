package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {



    @RequestMapping("/place")
    public String place(String orderStr,String projectid) {
        //goodsid_count#goodsid_count#goodsid_count
        Result result = new Result();
        String[]orders = orderStr.split("#");


        return "index";
    }

}
