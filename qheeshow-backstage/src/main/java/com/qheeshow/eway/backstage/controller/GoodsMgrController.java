package com.qheeshow.eway.backstage.controller;

import com.alibaba.fastjson.JSON;

import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.Result;
import com.qheeshow.eway.backstage.base.ResultDg;
import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/sys/goods")
public class GoodsMgrController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/fix")
    @ResponseBody
    public String saveOrUpdate(Goods goods) {

        Result<Boolean> result = new Result<>();

        if (goods.getId() == 0) {
            goodsService.save(goods);
        } else if (goods.getId() > 0) {
            goodsService.update(goods);
        }
        result.set(1, true);
        return result.toString();
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(Goods goods) {

        Result<Goods> result = new Result<>();

        goods = goodsService.selectByPrimaryKey(goods.getId());

        result.set("添加成功", goods);
        return result.toString();
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(Integer type) {

        ResultDg<List<Goods>> resultDg = new ResultDg<>();

        List<Goods> list = goodsService.listByType(type);
        resultDg.setTotal(list == null ? 0 : list.size());
        resultDg.setRows(list);

        return JSON.toJSONString(resultDg);
    }

    @RequestMapping("/status/update")
    @ResponseBody
    public String updateStatus(Goods goods) {

        Result<Boolean> result = new Result<>();

        goodsService.update(goods);

        result.set("成功", true);
        return result.toString();
    }
}
