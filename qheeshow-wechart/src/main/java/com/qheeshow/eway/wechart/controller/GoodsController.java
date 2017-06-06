package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.bean.wechat.pay.exception.OrderWechatException;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.GoodsService;
import com.qheeshow.eway.service.service.OrderDetailService;
import com.qheeshow.eway.service.service.ProjectService;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.base.Result;
import com.qheeshow.eway.wechart.base.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/goods/do")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/list/{projectid}")
    public ModelAndView list(@PathVariable Integer projectid, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        List<Project> projects = new ArrayList<>();
        Object o = session.getAttribute("loginUser");
        if (o == null) {
            modelAndView.setViewName("pub/404");
            return modelAndView;
        }
        User loginUser = (User) o;
        projects = projectService.listByUser(loginUser.getId());
        List<Goods> goodses = goodsService.listByStatus(1);
        Boolean flag1 = false;
        Boolean flag2 = false;
        Boolean flag3 = false;
        Boolean flag4 = false;

        if (projects.size() == 0) {//未创建项目

        } else {
            Project project = null;
            if (projectid.intValue() == 0) {
                project = projects.get(0);
                projectid = project.getId();
            } else
                project = projectService.get(projectid);
                /*if (project.getStatus().intValue() == 1) {//新项目
                    buyBtncls1 = "g-purchase2";
                }*/
            boolean buyed2 = false;
            boolean buyed34 = false;
            List<OrderDetail> orderDetails = orderDetailService.listByProject(project.getId());
            for (OrderDetail orderDetail : orderDetails) {
                if (orderDetail.getGoodsid().intValue() == 1) {

                } else if (orderDetail.getGoodsid().intValue() == 2) {
                    buyed2 = true;
                } else if (orderDetail.getGoodsid().intValue() == 3) {
                    buyed34 = true;
                } else if (orderDetail.getGoodsid().intValue() == 4) {
                    buyed34 = true;
                }
            }
            if (buyed34)
                flag1 = true;
            flag2 = !buyed2;
            flag3 = flag4 = !buyed34;

        }
        modelAndView.setViewName("goods/goods_list");
        modelAndView.addObject("projectid", projectid);
        modelAndView.addObject("projects", projects);
        modelAndView.addObject("goodses", goodses);
        modelAndView.addObject("flag1", flag1);
        modelAndView.addObject("flag2", flag2);
        modelAndView.addObject("flag3", flag3);
        modelAndView.addObject("flag4", flag4);
        return modelAndView;
    }

    @RequestMapping("/get/{goodsid}/{flag}")
    public ModelAndView get(@PathVariable Integer goodsid, @PathVariable Integer flag) {
        ModelAndView modelAndView = new ModelAndView();

        Goods goods = goodsService.selectByPrimaryKey(goodsid);

        modelAndView.addObject("goods", goods);
        modelAndView.addObject("flag", flag);
        modelAndView.setViewName("goods/t_" + goodsid);
        return modelAndView;
    }

    /**
     * 购买套餐
     *
     * @param payType
     * @param orderStr
     * @param session
     * @return
     */
    @RequestMapping("/preorder/{payType}/v_authj")
    @ResponseBody
    public String preOrder(@PathVariable String payType, String orderStr,Integer projectid, HttpSession session) {

        Result<Tip<ResultOrder>> result = new Result<>();
        Tip<ResultOrder> tip = new Tip<>();
        result.setData(tip);

        if (StringUtils.isEmpty(orderStr)) {
            result.setCode(-1);
            result.setMessage("对不起，请选择要购买的商品");
            return result.toString();
        }

        if (orderStr.charAt(orderStr.length() - 1) == '#')
            orderStr = orderStr.substring(0, orderStr.length() - 1);

        User loginUser = (User) session.getAttribute("loginUser");
        ResultOrder resultOrder = null;

        try {
            resultOrder = goodsService.preOrder(orderStr, payType,projectid, loginUser.getId(), loginUser.getGzhOpenid());
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
        } catch (OrderWechatException e) {
            LOGGER.error("error", e);
            result.setCode(-1);
            result.setMessage("对不起，下单失败，请联系梧桐小e，电话15002060446");
        }

        return result.toString();
    }


}
