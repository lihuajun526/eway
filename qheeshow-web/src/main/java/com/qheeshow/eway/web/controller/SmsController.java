package com.qheeshow.eway.web.controller;

import com.alibaba.fastjson.JSON;
import com.qheeshow.eway.common.exception.SendSMSException;
import com.qheeshow.eway.common.util.SMSSender;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/sms")
public class SmsController extends BaseController {

    @RequestMapping(value = "/get/{phone}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable String phone) {
        Result<Boolean> result = new Result<>();
        result.setData(true);
        try {
            SMSSender.send(phone);
        } catch (SendSMSException e) {

            result.setData(false);
        }
        return JSON.toJSONString(result);
    }

}
