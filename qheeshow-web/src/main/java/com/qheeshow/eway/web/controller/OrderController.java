package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.OrderService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;


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
     * @param count1
     * @param count2
     * @param count3
     * @param count4
     * @param session
     * @return
     */
    @RequestMapping("/place/{projectid}/{count1}/{count2}/{count3}/{count4}")
    @ResponseBody
    public String place(@PathVariable Integer projectid, @PathVariable Integer count1, @PathVariable Integer count2, @PathVariable Integer count3, @PathVariable Integer count4, HttpSession session) {
        //goodsid_count#goodsid_count#goodsid_count
        User loginUser = (User) session.getAttribute("loginUser");
        StringBuffer orderStr = new StringBuffer();
        if (count1.intValue() > 0) {
            orderStr.append("1_" + count1 + "#");
        }
        if (count2.intValue() > 0) {
            orderStr.append("2_" + count2 + "#");
        }
        if (count3.intValue() > 0) {
            orderStr.append("3_" + count3 + "#");
        }
        if (count4.intValue() > 0) {
            orderStr.append("4_" + count4 + "#");
        }
        Result<String> result = new Result();
        try {
            String qrcode = orderService.place(loginUser.getId(), projectid, orderStr.toString(), "WECHAT");
            result.setData(qrcode);
        } catch (Exception e) {
            LOGGER.error("下单失败:", e);
            result.setCode(-1);
            result.setMessage("下单失败");
        }
        return result.toString();
    }

}
