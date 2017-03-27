package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.*;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/center")
public class UserCenterController extends BaseController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectFollowService projectFollowService;
    @Autowired
    private ProjectSuggestService projectSuggestService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private GoodsItemMapService goodsItemMapService;
    @Autowired
    private GoodsItemService goodsItemService;
    @Autowired
    private InvestorService investorService;

    @RequestMapping("/index")
    public String index() {
        return "center/center";
    }

    @RequestMapping("/project/{type}/{pageSize}/{pageIndex}")
    public ModelAndView projectMgr(HttpSession session, @PathVariable Integer type, @PathVariable Integer pageSize, @PathVariable Integer pageIndex) {

        User loginUser = (User) session.getAttribute("loginUser");
        List<Project> projects = null;
        ModelAndView modelAndView = new ModelAndView();
        if (loginUser.getRoleid() >= 20 && loginUser.getRoleid() < 30) {//创业者
            modelAndView.setViewName("center/user_project_list");
            projects = projectService.listByUser(loginUser.getId());
            for (Project project : projects) {
                project.setForcus(projectFollowService.list(project.getId()).size());
            }
        } else {//投资人
            modelAndView.setViewName("center/investor_project_" + type + "_list");
            Integer count = 0;
            Map<String, Object> map = new HashMap<>();
            if (type.intValue() == 1) {//平台推荐的项目
                ProjectSuggest projectSuggest = new ProjectSuggest();
                projectSuggest.setInvestorid(loginUser.getId());
                projectSuggest.setStatus(0);
                projectSuggest.setPageSize(pageSize);
                projectSuggest.setStartRow((pageIndex - 1) * pageSize);
                map = projectService.listSuggest(projectSuggest);
                projects = (List<Project>) map.get("projects");
            } else if (type.intValue() == 2) {//关注的项目
                ProjectFollow projectFollow = new ProjectFollow();
                projectFollow.setUserid(loginUser.getId());
                projectFollow.setPageSize(pageSize);
                projectFollow.setStartRow((pageIndex - 1) * pageSize);
                map = projectService.listFollow(projectFollow);
                projects = (List<Project>) map.get("projects");
            } else if (type.intValue() == 3) {//金融顾问
                ProjectAdviser projectAdviser = new ProjectAdviser();
                projectAdviser.setUserid(loginUser.getId());
                projectAdviser.setPageSize(pageSize);
                projectAdviser.setStartRow((pageIndex - 1) * pageSize);
                map = projectService.listAdviser(projectAdviser);
                projects = (List<Project>) map.get("projects");
            }
            count = (Integer) map.get("count");
            modelAndView.addObject("type", type);
            modelAndView.addObject("pageSize", pageSize);
            modelAndView.addObject("pageIndex", pageIndex);
            modelAndView.addObject("pageCount", count % pageSize == 0 ? count / pageSize : (count / pageSize + 1));
        }
        modelAndView.addObject("projects", projects == null ? new ArrayList<>() : projects);
        return modelAndView;
    }

    @RequestMapping("/unsuggest/{projectid}")
    @ResponseBody
    public String unSuggest(@PathVariable Integer projectid, HttpSession session) {
        Result result = new Result();
        User loginUser = (User) session.getAttribute("loginUser");
        ProjectSuggest projectSuggest = new ProjectSuggest();
        projectSuggest.setProjectid(projectid);
        projectSuggest.setInvestorid(loginUser.getId());
        projectSuggestService.del(projectSuggest);
        return result.toString();
    }

    @RequestMapping("/myservices/{projectid}")
    public ModelAndView myServices(@PathVariable Integer projectid, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("center/user_service_list");
        List<Project> projects = projectService.listByUser(loginUser.getId());
        modelAndView.addObject("projects", projects);
        modelAndView.addObject("goodsItems", new ArrayList<GoodsItem>());
        modelAndView.addObject("investors", new ArrayList<Investor>());
        if (projects.isEmpty())
            return modelAndView;
        //查询为某个项目购买的服务
        if (projectid.intValue() == 0)
            projectid = projects.get(0).getId();
        modelAndView.addObject("projectid", projectid);
        List<OrderDetail> orderDetails = orderDetailService.listByProject(projectid);
        if (orderDetails.isEmpty())
            return modelAndView;
        Map<Integer, Integer> goodsCountMap = new HashMap<>();
        for (OrderDetail orderDetail : orderDetails) {
            Integer goodsid = orderDetail.getGoodsid();
            Integer count = goodsCountMap.get(goodsid);
            if (count == null) {
                goodsCountMap.put(goodsid, orderDetail.getCount());
            } else {
                goodsCountMap.put(goodsid, count.intValue() + orderDetail.getCount().intValue());
            }
        }
        Map<Integer, Integer> itemCountMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : goodsCountMap.entrySet()) {
            List<GoodsItemMap> goodsItemMaps = goodsItemMapService.listByGoods(entry.getKey());
            for (GoodsItemMap goodsItemMap : goodsItemMaps) {
                Integer itemid = goodsItemMap.getItemid();
                Integer count = goodsItemMap.getCount();
                if (count == null) {
                    itemCountMap.put(itemid, goodsItemMap.getCount() * entry.getValue());
                } else {
                    itemCountMap.put(itemid, count.intValue() + goodsItemMap.getCount() * entry.getValue());
                }
            }
        }
        List<GoodsItem> goodsItems = goodsItemService.listByIds(itemCountMap.keySet());
        for (GoodsItem goodsItem : goodsItems) {
            goodsItem.setCount(itemCountMap.get(goodsItem.getId()));
        }
        List<Investor> investors = investorService.listSuggest(projectid);
        modelAndView.addObject("goodsItems", goodsItems);
        modelAndView.addObject("investors", investors);
        return modelAndView;
    }
}
