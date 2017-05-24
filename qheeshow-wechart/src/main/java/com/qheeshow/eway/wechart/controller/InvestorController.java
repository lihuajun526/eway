package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.web.HaResponse;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/investor/do")
public class InvestorController extends BaseController {

    @Autowired
    private InvestorService investorService;
    @Autowired
    private InvestorFollowService investorFollowService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PostRecordService postRecordService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;


    /**
     * @param id
     * @return
     * @Title: detail
     * @Description: 根据id获取详情
     * @author yue
     * @date 2017年3月5日 下午2:53:21
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public HaResponse detail(Integer id) {
        Investor investor = investorService.detail(id);
        return HaResponse.sussess(investor);
    }

    /**
     * 根据条件过滤投资人
     *
     * @param industryid
     * @param pageIndex
     * @param keyword
     * @return
     */
    @RequestMapping("/list/{industryid}/{pageIndex}")
    @ResponseBody
    public String listByCondition(
            @PathVariable String industryid,
            @PathVariable Integer pageIndex, String keyword) {
        LOGGER.debug("根据条件过滤投资人");

        Result<List<Investor>> result = new Result<>();

        int pageSize = 10;
        Map<String, Object> map = investorService.listByCondition("0", industryid, "0", pageIndex, pageSize, keyword);
        List<Investor> investors = (List<Investor>) map.get("investors");
        for (Investor investor : investors) {
            investor.setCityName(investor.getCityName().replaceAll("#", " "));
        }
        result.setData(investors);

        return result.toString();
    }

    @RequestMapping("/{id}")
    public ModelAndView get(@PathVariable Integer id) {
        Investor investor = investorService.get(id);
        ModelAndView modelAndView = new ModelAndView();

        Map<String, Integer> tags = new HashMap<>();
        List<Comment> list = commentService.listByInvestor(id);
        for (Comment comment : list) {
            String[] sTags = comment.getTags().split("#");
            for (String sTag : sTags) {
                Integer count = tags.get(sTag);
                if (count == null) {
                    count = 1;
                } else {
                    count++;
                }
                tags.put(sTag, count);
            }
        }

        modelAndView.setViewName("investor/investor_detail");
        modelAndView.addObject("tags", tags);
        modelAndView.addObject("investor", investor);
        return modelAndView;
    }

    @RequestMapping("/follow/{investorid}/v_auth")
    @ResponseBody
    public String follow(@PathVariable Integer investorid, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        Investor investor = investorService.get(investorid);

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getId().intValue() == investor.getUserid().intValue()) {
            result.setMessage("您不能关注自己");
            result.setCode(-1);
            return result.toString();
        }
        InvestorFollow investorFollow = new InvestorFollow();
        investorFollow.setUserid(loginUser.getId());
        investorFollow.setInvestorid(investorid);
        investorFollowService.save(investorFollow);

        result.setMessage("关注成功");
        return result.toString();
    }

    @RequestMapping("/isfollow/{investorid}")
    @ResponseBody
    public String isfollow(@PathVariable Integer investorid, HttpSession session) {

        Result<Boolean> result = new Result<>();
        result.setData(false);
        Object o = session.getAttribute("loginUser");
        if (o == null)
            return result.toString();
        User loginUser = (User) o;
        InvestorFollow investorFollow = new InvestorFollow();
        investorFollow.setUserid(loginUser.getId());
        investorFollow.setInvestorid(investorid);
        Boolean isFollow = investorFollowService.isFollow(investorFollow);
        result.setData(isFollow);
        return result.toString();
    }

    /**
     * 投递项目
     *
     * @param investorid
     * @param session
     * @return
     */
    @RequestMapping("/project/post/{investorid}/authj")
    @ResponseBody
    public String postProject(@PathVariable Integer investorid, HttpSession session) {

        Result<Boolean> result = new Result<>();
        result.setData(false);
        Object o = session.getAttribute("loginUser");
        if (o == null)
            return result.toString();
        User loginUser = (User) o;
        if (loginUser.getRoleid().intValue() != 20) {
            result.setMessage("对不起，企业/创业者才能投递项目");
            return result.toString();
        }

        List<Project> list = projectService.listByUser(loginUser.getId());
        if (list.size() == 0) {
            result.setMessage("对不起，您尚未创建项目，请先创建项目");
            return result.toString();
        }
        //企业每天最多投递5次
        List<PostRecord> postRecordList = postRecordService.listByUserAndToday(loginUser.getId());
        if (postRecordList.size() >= 5) {
            result.setMessage("对不起，每天做多可投递5次");
            return result.toString();
        }

        //todo  判断投递的项目是否有BP

        //投递项目
        PostRecord postRecord = new PostRecord();
        postRecord.setUserid(loginUser.getId());
        postRecord.setInvestorid(investorid);
        postRecord.setProjectid(list.get(0).getId());
        postRecordService.save(postRecord);
        //发送邮件

        InvestorFollow investorFollow = new InvestorFollow();
        investorFollow.setUserid(loginUser.getId());
        investorFollow.setInvestorid(investorid);
        Boolean isFollow = investorFollowService.isFollow(investorFollow);
        result.setData(isFollow);
        return result.toString();
    }

    /**
     * 是否可以投递项目
     *
     * @param session
     * @return
     */
    @RequestMapping("/isable/post/v_auth")
    @ResponseBody
    public String isAblePost(HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);
        result.setCode(-1);

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRoleid().intValue() >= 30 || loginUser.getRoleid().intValue() < 20) {
            result.setMessage("对不起，您不是企业/创业者，不能投递项目");
            return result.toString();
        }
        List<Project> list = projectService.listByUser(loginUser.getId());
        if (list == null || list.size() == 0) {
            result.setMessage("您还没有创建项目，请登录PC网站创建项目");
            return result.toString();
        }
        result.setCode(0);
        return result.toString();
    }

    /**
     * 投递项目
     * @param projectid
     * @param userid
     * @param session
     * @return
     */
    @RequestMapping("/project/post/{projectid}/{investorid}/v_auth")
    @ResponseBody
    public String postPro(@PathVariable Integer projectid, @PathVariable Integer userid, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        User user = userService.get(userid);
        Project project = projectService.get(projectid);

        //邮件发送商业计划书
        MailBean mailBean = new MailBean();
        mailBean.setContent("<div><a href='" + project.getBp() + "'>点击下载商业计划书</a></div>");
        mailBean.setToAddress(user.getEmail());
        mailBean.setSubject("\"" + project.getTitle() + "\"项目的商业计划书");
        mailService.sendHtmlMail(mailBean);

        result.setMessage("投递成功");
        return result.toString();
    }
}
