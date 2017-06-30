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

    @RequestMapping("/list/{projectid}")
    public ModelAndView list(@PathVariable Integer projectid, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Object o = session.getAttribute("loginUser");
        if (o == null) {
            modelAndView.setViewName("pub/404");
            return modelAndView;
        }


        User loginUser = (User) o;
        List<Project> projects = new ArrayList<>();
        projects = projectService.listByUser(loginUser.getId());

        if (projects.size() == 0) {//未创建项目
            projectid = 0;
        } else {
            if (projectid.intValue() == 0 || projectService.get(projectid) == null) {
                projectid = projects.get(0).getId();
            }
        }
        modelAndView.setViewName("goods/goods_list");
        modelAndView.addObject("projectid", projectid);
        modelAndView.addObject("projects", projects);
        return modelAndView;
    }

    @RequestMapping("/get/{goodsid}")
    public ModelAndView get(@PathVariable Integer goodsid) {
        ModelAndView modelAndView = new ModelAndView();

        if (goodsid.intValue() == 3) {
            modelAndView.setViewName("goods/t_recharge");
        } else {
            Goods goods = goodsService.selectByPrimaryKey(goodsid);
            modelAndView.addObject("goods", goods);
            modelAndView.setViewName("goods/t_" + goodsid);
        }
        return modelAndView;
    }

    /**
     * 购买套餐
     *
     * @param payType
     * @param goodsid
     * @param projectid
     * @param session
     * @return
     */
    @RequestMapping("/preorder/{payType}/v_authj")
    @ResponseBody
    public String preOrder(@PathVariable String payType, Integer goodsid, Integer projectid, HttpSession session) {

        Result<Tip<ResultOrder>> result = new Result<>();
        Tip<ResultOrder> tip = new Tip<>();
        result.setData(tip);

        if (goodsid == null || goodsid.intValue() == 0) {
            result.setCode(-1);
            result.setMessage("对不起，请选择要购买的商品");
            return result.toString();
        }
        Goods goods = goodsService.selectByPrimaryKey(goodsid);
        if (goods == null) {
            result.setCode(-1);
            result.setMessage("对不起，您选择的商品已下架");
            return result.toString();
        }

        User loginUser = (User) session.getAttribute("loginUser");
        ResultOrder resultOrder = null;
        try {
            resultOrder = goodsService.preOrder(goods, payType, projectid, loginUser.getId(), loginUser.getGzhOpenid());
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
