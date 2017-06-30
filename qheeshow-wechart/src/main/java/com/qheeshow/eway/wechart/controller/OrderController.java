package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.model.Order;
import com.qheeshow.eway.service.model.OrderWechat;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.OrderService;
import com.qheeshow.eway.service.service.PayService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.base.Result;
import com.qheeshow.eway.wechart.base.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/order/do")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PayService payService;

    /**
     * 获取订单详情
     *
     * @param orderno
     * @return
     */
    @RequestMapping("/get/{orderno}")
    public ModelAndView get(@PathVariable String orderno) {

        ModelAndView modelAndView = new ModelAndView();

        Order order = orderService.getByOrder(orderno);
        Project project = null;
        if (order.getProjectid() != null && order.getProjectid().intValue() > 0)
            project = projectService.get(order.getProjectid());
        else
            project = new Project();

        modelAndView.setViewName("order/pay_result");
        modelAndView.addObject("order", order);
        modelAndView.addObject("project", project);

        return modelAndView;

    }

    @RequestMapping("/pay/{payType}/{orderid}/v_authj")
    @ResponseBody
    public String preOrder(@PathVariable String payType, @PathVariable Integer orderid, HttpSession session) {

        Result<Tip<ResultOrder>> result = new Result<>();
        Tip<ResultOrder> tip = new Tip<>();
        result.setData(tip);

        Order order = orderService.get(orderid);

        User loginUser = (User) session.getAttribute("loginUser");

        ResultOrder resultOrder = null;
        OrderWechat orderWechat = new OrderWechat();
        orderWechat.setDescription(order.getTitle());
        orderWechat.setOrderno(order.getOrderNo());
        orderWechat.setTotalFee(String.valueOf(order.getPrice().multiply(new BigDecimal(100)).intValue()));
        orderWechat.setTradeType("JSAPI");
        orderWechat.setOpenid(loginUser.getGzhOpenid());
        try {
            resultOrder = payService.order(orderWechat);
        } catch (Exception e) {
            LOGGER.error("error", e);
            result.setCode(-1);
            result.setMessage("对不起，支付失败，请联系梧桐小e，电话" + Config.get("customer.tel"));
            return result.toString();
        }
        resultOrder.setOrderno(order.getOrderNo());
        resultOrder.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        //签名
        Map<String, String> params = new TreeMap<>();
        params.put("appId", resultOrder.getAppid());
        params.put("timeStamp", resultOrder.getTimeStamp());
        params.put("nonceStr", resultOrder.getNonce_str());
        params.put("package", "prepay_id=" + resultOrder.getPrepay_id());
        params.put("signType", "MD5");
        resultOrder.setSign(StrUtil.sign(params));
        tip.setData(resultOrder);

        return result.toString();
    }

}
