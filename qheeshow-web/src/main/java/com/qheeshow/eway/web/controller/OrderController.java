package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Order;
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
import java.util.Map;


/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 购买套餐
     * @param projectid
     * @param goodsid
     * @param payType
     * @param session
     * @return
     */
    @RequestMapping("/place/{projectid}/{goodsid}/{payType}/authj")
    @ResponseBody
    public String place(@PathVariable Integer projectid, @PathVariable Integer goodsid, @PathVariable String payType, HttpSession session) {
        //goodsid_count#goodsid_count#goodsid_count
        User loginUser = (User) session.getAttribute("loginUser");
        Result<Map<String, String>> result = new Result<>();
        try {
            Map<String, String> map = orderService.place(loginUser.getId(), projectid, goodsid, payType);
            result.setData(map);
        } catch (Exception e) {
            LOGGER.error("下单失败:", e);
            result.setCode(-1);
            result.setMessage("下单失败");
        }
        return result.toString();
    }

    /**
     * 话费充值
     *
     * @param goodsid
     * @param payType
     * @param session
     * @return
     */
    @RequestMapping("/recharge/{goodsid}/{payType}/authj")
    @ResponseBody
    public String recharge(@PathVariable Integer goodsid, @PathVariable String payType, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Result<Map<String, String>> result = new Result<>();
        try {
            Map<String, String> map = orderService.recharge(loginUser.getId(), goodsid, payType);
            result.setData(map);
        } catch (Exception e) {
            LOGGER.error("下单失败:", e);
            result.setCode(-1);
            result.setMessage("下单失败");
        }
        return result.toString();
    }

    /**
     * 获取订单状态
     *
     * @param orderid
     * @return
     */
    @RequestMapping("/status/{orderid}")
    @ResponseBody
    public String getOrderStatus(@PathVariable Integer orderid) {

        Result<Integer> result = new Result<>();
        result.setData(1);

        Order order = orderService.get(orderid);
        result.setData(order.getStatus());

        return result.toString();
    }

}
