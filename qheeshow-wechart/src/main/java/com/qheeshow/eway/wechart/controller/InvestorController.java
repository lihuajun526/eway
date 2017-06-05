package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.*;
import com.qheeshow.eway.service.service.*;
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
    @Autowired
    private XwcmclassinfoService xwcmclassinfoService;

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
     *
     * @param projectid
     * @param userid
     * @param session
     * @return
     */
    @RequestMapping("/project/post/{userid}/{projectid}/v_authj")
    @ResponseBody
    public String postPro(@PathVariable Integer userid, @PathVariable Integer projectid, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getRoleid().intValue() >= 30 || loginUser.getRoleid().intValue() < 20) {
            result.setMessage("对不起，企业/创业者才能投递项目");
            return result.toString();
        }

        //企业每天最多投递5次
        List<PostRecord> postRecordList = postRecordService.listByUserAndToday(loginUser.getId());
        if (postRecordList.size() >= 5) {
            result.setMessage("对不起，每天做多可投递5次");
            return result.toString();
        }

        Project project = projectService.get(projectid);
        if (StringUtils.isEmpty(project.getBp())) {
            result.setMessage("对不起，该项目没有上传商业计划书，请在电脑端上传");
            return result.toString();
        }

        Investor investor = investorService.getByUser(userid);

        if (postRecordService.listByInvestorAndProject(investor.getId(), projectid).size() > 0) {
            result.setMessage("对不起，该项目已投递给该投资人，不能重复投递");
            return result.toString();
        }

        //投递项目
        PostRecord postRecord = new PostRecord();
        postRecord.setUserid(loginUser.getId());
        postRecord.setInvestorid(investor.getId());
        postRecord.setProjectid(project.getId());
        postRecordService.save(postRecord);
        //发送邮件
        MailBean mailBean = new MailBean();
        mailBean.setSubject(project.getTitle() + "商业计划书");
        mailBean.setToAddress(investor.getEmail());
        mailBean.setContent("<body><h1>" + investor.getTrueName() + "，您好</h1><br/><a href='" + project.getBp() + "'>点击下载</a>" + project.getTitle() + "商业计划书</body>");
        mailService.sendHtmlMail(mailBean);

        result.setMessage("投递成功");
        return result.toString();
    }

    /**
     * 获取所有行业
     *
     * @return
     */
    @RequestMapping("/industry/list")
    public ModelAndView listAllIndustry() {

        ModelAndView modelAndView = new ModelAndView();

        List<Xwcmclassinfo> industrys = xwcmclassinfoService.listByRoot(Config.getInt("classinfo.rootid.industry"));
        Map<Integer, Integer> map = new HashMap<>();

        Investor investor = new Investor();
        investor.setPageSize(100);
        investor.setStartRow(0);
        for (Xwcmclassinfo industry : industrys) {
            investor.setIndustryId("#" + industry.getClassinfoid() + "#");
            map.put(industry.getClassinfoid(), investorService.listByIndustry(investor).size());
        }

        modelAndView.setViewName("investor/industry_list");
        modelAndView.addObject("industrys", industrys);
        modelAndView.addObject("map", map);

        return modelAndView;
    }

    /**
     * 添加评价
     *
     * @param comment
     * @param session
     * @return
     */
    @RequestMapping("/comment/save/v_authj")
    @ResponseBody
    public String save(Comment comment, HttpSession session) {
        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        Investor investor = investorService.get(comment.getInvestorid());
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser.getId().intValue() == investor.getUserid()) {
            result.setMessage("您不能评价自己");
            return result.toString();
        }

        if (commentService.listByUserAndInvestor(loginUser.getId(), comment.getInvestorid()).size() > 0) {
            result.setMessage("您已评价过，不能重复评价");
            return result.toString();
        }

        if (comment.getTags().indexOf("#") != -1) {
            comment.setTags(comment.getTags().substring(0, comment.getTags().length() - 1));
        }

        comment.setUserid(loginUser.getId());
        commentService.save(comment);

        result.setMessage("评价成功");
        return result.toString();
    }

    /**
     * 评价列表
     *
     * @param investorid
     * @return
     */
    @RequestMapping("/comment/list/{investorid}")
    public ModelAndView listComment(@PathVariable Integer investorid) {
        ModelAndView modelAndView = new ModelAndView();

        List<Comment> comments = commentService.listByInvestor(investorid);

        if (comments.size() > 20)
            comments = comments.subList(0, 20);

        for (Comment comment : comments) {
            User user = userService.get(comment.getUserid());
            comment.setPhoto(user.getPhoto());
            comment.setName(user.getName());
        }

        modelAndView.setViewName("investor/comment_list");
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }

    /**
     * 获取某行业下的投资人
     *
     * @param industryid
     * @param pageIndex
     * @return
     */
    @RequestMapping("/list/industry/{industryid}/{pageIndex}")
    @ResponseBody
    public String listByIndustry(@PathVariable Integer industryid, @PathVariable Integer pageIndex) {
        Result<Map<String, Object>> result = new Result<>();

        Map<String, Object> data = new HashMap<>();

        Map<String, Object> map = investorService.listByCondition("0", String.valueOf(industryid), "0", pageIndex, 10, null);
        List<Investor> investors = (List<Investor>) map.get("investors");

        for (Investor investor : investors) {
            investor.setCityName(investor.getCityName().replaceAll("#", " "));
        }

        data.put("investors", investors);
        data.put("count", (Integer) map.get("count"));
        data.put("indusName", xwcmclassinfoService.get(industryid).getCname());

        result.setData(data);
        return result.toString();
    }

    /**
     * 搜索投资人
     *
     * @param keyword
     * @return
     */
    @RequestMapping("/search/{keyword}")
    @ResponseBody
    public String search(@PathVariable String keyword) {
        Result<List<Investor>> result = new Result<>();

        List<Investor> investors = investorService.search(keyword);

        for (Investor investor : investors) {
            investor.setCityName(investor.getCityName().replaceAll("#", " "));
        }

        result.setData(investors);
        return result.toString();
    }

    /**
     * 投资人认证
     *
     * @param investor
     * @return
     */
    @RequestMapping("/auth/v_authj")
    @ResponseBody
    public String auth(Investor investor, String smsCode, HttpSession session) {
        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        if (StringUtils.isEmpty(investor.getMobile())) {
            result.setMessage("对不起，手机号不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(smsCode)) {
            result.setMessage("对不起，手机验证码不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(investor.getBusinessCardPositive())) {
            result.setMessage("对不起，请上传名片正面");
            return result.toString();
        }
        if (StringUtils.isEmpty(investor.getBusinessCardOpposite())) {
            result.setMessage("对不起，请上传名片反面");
            return result.toString();
        }
        Object o = session.getAttribute(investor.getMobile() + "_auth_smsCode");
        if (o == null) {
            result.setMessage("对不起，手机验证码错误");
            return result.toString();
        }
        String smsCodeS = (String) o;
        if (!smsCode.equals(smsCodeS)) {
            result.setMessage("对不起，手机验证码错误");
            return result.toString();
        }

        User loginUser = (User) session.getAttribute("loginUser");
        Investor investorDb = investorService.getByUser(loginUser.getId());
        investor.setId(investorDb.getId());
        investorService.save(investor);

        result.setMessage("认证成功");
        tip.setAction("返回");
        tip.setLink("javascript:window.history.go(-1);");
        return result.toString();
    }

}
