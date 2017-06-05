package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.service.model.Order;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.service.OrderService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.wechart.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


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
        if (order.getProjectid() != null)
            project = projectService.get(order.getProjectid());
        else
            project = new Project();

        modelAndView.setViewName("order/pay_result");
        modelAndView.addObject("order", order);
        modelAndView.addObject("project", project);

        return modelAndView;

    }

}
