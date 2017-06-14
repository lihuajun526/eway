package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/user/center/do")
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
    @Autowired
    private ProjectAdviserService projectAdviserService;
    @Autowired
    private InvestorFollowService investorFollowService;

    @RequestMapping("/v_authj")
    @ResponseBody
    public String vAuth() {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        return result.toString();
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("center/index");

        Object o = session.getAttribute("loginUser");
        if (o == null)
            return modelAndView;
        User loginUser = (User) o;
        if (loginUser.getRoleid() == null)
            return modelAndView;
        if (loginUser.getRoleid().intValue() >= 30 && loginUser.getRoleid().intValue() < 40) {//投资人
            Investor investor = investorService.getByUser(loginUser.getId());
            modelAndView.addObject("investor", investor);
        }

        modelAndView.addObject("", "");
        return modelAndView;
    }

    /**
     * 平台推荐的项目
     *
     * @param session
     * @param pageIndex
     * @return
     */
    @RequestMapping("/project/suggest/list/{pageIndex}")
    @ResponseBody
    public String listSuggest(HttpSession session, @PathVariable Integer pageIndex) {

        Result<List<Project>> result = new Result<>();

        User loginUser = (User) session.getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>();
        ProjectSuggest projectSuggest = new ProjectSuggest();
        Investor investor = investorService.getByUser(loginUser.getId());
        if (investor == null) {
            map.put("projects", new ArrayList<>());
        } else {
            projectSuggest.setInvestorid(investor.getId());
            projectSuggest.setStatus(0);
            projectSuggest.setPageSize(10);
            projectSuggest.setStartRow((pageIndex - 1) * 10);
            map = projectService.listSuggest(projectSuggest);
        }
        List<Project> projects = (List<Project>) map.get("projects");
        result.setData(projects);
        return result.toString();
    }

    /**
     * 关注的项目
     *
     * @param session
     * @param pageIndex
     * @return
     */
    @RequestMapping("/project/follow/list/{pageIndex}")
    @ResponseBody
    public String listFollow(HttpSession session, @PathVariable Integer pageIndex) {

        Result<List<Project>> result = new Result<>();

        User loginUser = (User) session.getAttribute("loginUser");
        ProjectFollow projectFollow = new ProjectFollow();
        projectFollow.setUserid(loginUser.getId());
        projectFollow.setPageSize(10);
        projectFollow.setStartRow((pageIndex - 1) * 10);
        Map<String, Object> map = projectService.listFollow(projectFollow);
        List<Project> projects = (List<Project>) map.get("projects");

        result.setData(projects);
        return result.toString();
    }

    /**
     * 金融顾问
     *
     * @param session
     * @param pageIndex
     * @return
     */
    @RequestMapping("/project/adviser/list/{pageIndex}")
    @ResponseBody
    public String listAdviserProject(HttpSession session, @PathVariable Integer pageIndex) {

        Result<List<Project>> result = new Result<>();

        User loginUser = (User) session.getAttribute("loginUser");
        ProjectAdviser projectAdviser = new ProjectAdviser();
        projectAdviser.setUserid(loginUser.getId());
        projectAdviser.setPageSize(10);
        projectAdviser.setStartRow((pageIndex - 1) * 10);
        Map<String, Object> map = projectService.listAdviser(projectAdviser);
        List<Project> projects = (List<Project>) map.get("projects");

        result.setData(projects);
        return result.toString();
    }

    @RequestMapping("/unsuggest/{projectid}")
    @ResponseBody
    public String unSuggest(@PathVariable Integer projectid, HttpSession session) {
        Result result = new Result();
        User loginUser = (User) session.getAttribute("loginUser");

        Investor investor = investorService.getByUser(loginUser.getId());

        ProjectSuggest projectSuggest = new ProjectSuggest();
        projectSuggest.setProjectid(projectid);
        projectSuggest.setInvestorid(investor.getId());
        projectSuggestService.del(projectSuggest);
        return result.toString();
    }

    /**************************************************************
     * 企业/创业者
     **************************************************************/

    /**
     * 我的金融顾问
     * @param session
     * @return
     */
    @RequestMapping("/projects/advisers")
    public ModelAndView proAndAdvs(HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        List<Project> projects = projectService.listByUser(loginUser.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("center/company/adviser_list");
        modelAndView.addObject("projects", projects);

        return modelAndView;
    }

    @RequestMapping("/advisers/{projectid}")
    public ModelAndView advisers(@PathVariable Integer projectid) {

        List<Investor> investors = projectAdviserService.listByProject(projectid);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("center/company/advisers");
        modelAndView.addObject("investors", investors);

        return modelAndView;
    }

    /**
     * 我购买的服务
     * @param session
     * @return
     */
    @RequestMapping("/projects/services")
    public ModelAndView proAndSrvs(HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        List<Project> projects = projectService.listByUser(loginUser.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("center/company/service_list");
        modelAndView.addObject("projects", projects);

        return modelAndView;
    }

    @RequestMapping("/services/{projectid}")
    public ModelAndView services(@PathVariable Integer projectid) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("center/company/services");
        modelAndView.addObject("goodsItems", new ArrayList<GoodsItem>());
        modelAndView.addObject("investors", new ArrayList<Investor>());
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

    @RequestMapping("/follow/list")
    public ModelAndView listFollow(HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        List<Investor> investors = investorFollowService.listByUser(loginUser.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("center/company/follow_list");
        modelAndView.addObject("investors", investors);

        return modelAndView;
    }

    @RequestMapping("/unfollow/{investorid}")
    @ResponseBody
    public String unFollow(HttpSession session, @PathVariable Integer investorid) {

        Result result = new Result();

        User loginUser = (User) session.getAttribute("loginUser");
        InvestorFollow investorFollow = new InvestorFollow();
        investorFollow.setUserid(loginUser.getId());
        investorFollow.setInvestorid(investorid);
        investorFollowService.unFollow(investorFollow);

        return result.toString();
    }

    @RequestMapping("/order/list")
    public ModelAndView listOrder(HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        List<Order> orders = orderService.listByUser(loginUser.getId());

        Map<Integer, Project> map = new HashMap<>();

        for (Order order : orders) {
            if (order.getProjectid() != null)
                map.put(order.getId(), projectService.get(order.getProjectid()));
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("center/company/order_list");
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("map", map);


        return modelAndView;
    }

}
