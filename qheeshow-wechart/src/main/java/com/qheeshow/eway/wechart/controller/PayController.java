package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.bean.wechat.pay.WechatNotifyTwo;
import com.qheeshow.eway.common.util.Bean2Xml;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.model.Order;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.GoodsService;
import com.qheeshow.eway.service.service.OrderService;
import com.qheeshow.eway.service.service.PayService;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.base.Result;
import com.qheeshow.eway.wechart.base.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihuajun on 2017/4/25.
 */
@Controller
@RequestMapping("/pay/do")
public class PayController extends BaseController {

    @Autowired
    private PayService payService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

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

        return StrUtil.map2Xml(result);
    }

    private Map<String, String> crMap(WechatNotifyTwo wechatNotifyTwo) {
        Map<String, String> map = new TreeMap<>();
        return null;
    }


    @RequestMapping(value = "/draw/v_authj")
    @ResponseBody
    public String draw(String limit, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);
        result.setCode(-2);

        String rex = "^[1-9]\\d*$";
        Pattern p = Pattern.compile(rex);
        if (!p.matcher(limit).find()) {
            result.setMessage("对不起，提现金额必须为正整数");
            return result.toString();
        }
        User loginUser = (User) session.getAttribute("loginUser");
        if (StringUtils.isEmpty(loginUser.getOpenid())) {
            result.setMessage("对不起，请在微信公众号内提现");
            return result.toString();
        }
        loginUser = userService.get(loginUser.getId());
        if (loginUser.getAccount().compareTo(new BigDecimal(limit)) <= 0) {
            result.setMessage("对不起，您的账户余额不足");
            return result.toString();
        }


        return null;
    }

    public static void main(String[] args) {

        String num = "1.111";
        String rex = "^[1-9]\\d*$";
        Pattern p = Pattern.compile(rex);
        Matcher m = p.matcher(num);
        System.out.println(m.find());

    }

}
