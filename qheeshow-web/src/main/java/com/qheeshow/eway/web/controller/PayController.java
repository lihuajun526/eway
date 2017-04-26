package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lihuajun on 2017/4/25.
 */
@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {

    @RequestMapping("/wechat/notify")
    public void wechatNotify(){
        System.out.println("asdfasdsa");
    }


}
