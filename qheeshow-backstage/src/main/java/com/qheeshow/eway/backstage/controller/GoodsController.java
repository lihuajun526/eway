package com.qheeshow.eway.backstage.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.GoodsWithBLOBs;
import com.qheeshow.eway.service.service.GoodsService;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 
     * @Title: saveOrUpdate
     * @Description: 添加或修改商品
     * @author yue
     * @date 2017年2月26日 下午1:39:52
     * @param goods
     * @param session
     * @return
     */
    @RequestMapping("/fix")
    @ResponseBody
    public HaResponse saveOrUpdate(GoodsWithBLOBs goods,HttpSession session) {
    	session.setAttribute("userId", "1");
    	if(session.getAttribute("userId") != null){
    		goods.setCreateUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        	if(goods.getId() != null){
                goodsService.update(goods);
        	}else{
        		goods.setCreateTime(new Date());
                goodsService.save(goods);
        	}
            return HaResponse.sussess();
    	}else{
    		return HaResponse.fail();
    	}
    }

    /**
     * 
     * @Title: get
     * @Description: 根据id获取商品详情
     * @author yue
     * @date 2017年2月26日 下午1:40:06
     * @param id
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public HaResponse get(Integer id) {
    	GoodsWithBLOBs goods = goodsService.selectByPrimaryKey(id);
        return HaResponse.sussess(goods);
    }

    /**
     * 
     * @Title: list
     * @Description: 获取商品列表
     * @author yue
     * @date 2017年2月26日 下午1:40:27
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public HaResponse list() {
        List<GoodsWithBLOBs> list = goodsService.listAll();
        return HaResponse.sussess(list);
    }

}
