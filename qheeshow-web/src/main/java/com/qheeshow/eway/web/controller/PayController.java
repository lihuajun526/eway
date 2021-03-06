package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.common.bean.wechat.pay.WechatNotify;
import com.qheeshow.eway.common.bean.wechat.pay.WechatNotifyTwo;
import com.qheeshow.eway.common.util.Bean2Xml;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.model.Order;
import com.qheeshow.eway.service.model.OrderDetail;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.*;
import com.qheeshow.eway.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lihuajun on 2017/4/25.
 */
@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ActivitySignService activitySignService;

    /**
     * 模式一回调
     */
    @RequestMapping(value = "/wechat/notify/1")
    @ResponseBody
    public String wechatNotify1(HttpServletRequest request) throws IOException {

        ServletInputStream sis = null;
        String xmlData = null;
        // 取HTTP请求流
        sis = request.getInputStream();
        // 取HTTP请求流长度
        int size = request.getContentLength();
        // 用于缓存每次读取的数据
        byte[] buffer = new byte[size];
        // 用于存放结果的数组
        byte[] xmldataByte = new byte[size];
        int count = 0;
        int rbyte = 0;
        // 循环读取
        while (count < size) {
            // 每次实际读取长度存于rbyte中
            rbyte = sis.read(buffer);
            for (int i = 0; i < rbyte; i++) {
                xmldataByte[count + i] = buffer[i];
            }
            count += rbyte;
        }
        xmlData = new String(xmldataByte, "UTF-8");
        WechatNotify wechatNotify = (WechatNotify) Bean2Xml.toBean(xmlData, WechatNotify.class);

        return null;
    }

    /**
     * 模式二回调
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/wechat/notify/2")
    @ResponseBody
    public String wechatNotify2(HttpServletRequest request) throws IOException {

        ServletInputStream sis = null;
        String xmlData = null;
        // 取HTTP请求流
        sis = request.getInputStream();
        // 取HTTP请求流长度
        int size = request.getContentLength();
        // 用于缓存每次读取的数据
        byte[] buffer = new byte[size];
        // 用于存放结果的数组
        byte[] xmldataByte = new byte[size];
        int count = 0;
        int rbyte = 0;
        // 循环读取
        while (count < size) {
            // 每次实际读取长度存于rbyte中
            rbyte = sis.read(buffer);
            for (int i = 0; i < rbyte; i++) {
                xmldataByte[count + i] = buffer[i];
            }
            count += rbyte;
        }
        xmlData = new String(xmldataByte, "UTF-8");
        WechatNotifyTwo wechatNotifyTwo = (WechatNotifyTwo) Bean2Xml.toBean(xmlData, WechatNotifyTwo.class);
        Map<String, String> result = new HashMap<>();
        result.put("return_code", "SUCCESS");
        result.put("return_msg", "OK");

        if (!wechatNotifyTwo.getReturn_code().equals("SUCCESS") || !wechatNotifyTwo.getResult_code().equals("SUCCESS")) {
            //支付失败
            result.put("return_code", "FAIL");
            LOGGER.info("支付失败，订单号【】", wechatNotifyTwo.getOut_trade_no());
            return StrUtil.map2Xml(result);
        }
        Order order = orderService.getByOrder(wechatNotifyTwo.getOut_trade_no());
        //检查订单是否已处理
        if (order.getFlag().intValue() != 0) {
            //订单已处理
            return StrUtil.map2Xml(result);
        }
        //验证订单金额
        if (order.getPrice().multiply(new BigDecimal(100)).intValue() != wechatNotifyTwo.getTotal_fee().intValue()) {
            result.put("return_code", "FAIL");
            LOGGER.info("订单金额不匹配");
            return StrUtil.map2Xml(result);
        }
        //todo 验证签名

        //修改订单状态
        order.setStatus(2);
        order.setFlag(1);

        orderService.save(order);

        if (order.getProjectid() == null) {
            List<OrderDetail> list = orderDetailService.listByOrder(order.getId());
            if (list.size() == 1) {
                OrderDetail orderDetail = list.get(0);
                if (orderDetail.getActivityid() != null) {//添加报名
                    ActivitySign activitySign = new ActivitySign();
                    activitySign.setActivityId(orderDetail.getActivityid());
                    activitySign.setStatus(1);
                    activitySign.setUserid(order.getUserid());
                    activitySignService.save(activitySign);
                }
            }
        } else if (order.getProjectid().intValue() == 0) {//充话费
            User user = userService.get(order.getUserid());
            List<OrderDetail> list = orderDetailService.listByOrder(order.getId());
            if (list.size() == 1) {
                OrderDetail orderDetail = list.get(0);
                int goodsid = orderDetail.getGoodsid();
                if (goodsid == 141) {
                    user.setCallTime(user.getCallTime() == null ? 200 : user.getCallTime() + 200);
                } else if (goodsid == 142) {
                    user.setCallTime(user.getCallTime() == null ? 400 : user.getCallTime() + 400);
                } else if (goodsid == 143) {
                    user.setCallTime(user.getCallTime() == null ? 600 : user.getCallTime() + 600);
                } else if (goodsid == 144) {
                    user.setCallTime(user.getCallTime() == null ? 800 : user.getCallTime() + 800);
                } else if (goodsid == 145) {
                    user.setCallTime(user.getCallTime() == null ? 1000 : user.getCallTime() + 1000);
                }
                userService.update(user);
            }
        }
        return StrUtil.map2Xml(result);
    }

    private Map<String, String> crMap(WechatNotifyTwo wechatNotifyTwo) {
        Map<String, String> map = new TreeMap<>();
        return null;
    }

}
