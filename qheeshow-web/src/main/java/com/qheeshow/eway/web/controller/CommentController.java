package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Comment;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.CommentService;
import com.qheeshow.eway.service.service.InvestorService;
import com.qheeshow.eway.service.service.UserService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private InvestorService investorService;
    @Autowired
    private UserService userService;

    @RequestMapping("/save/authj")
    @ResponseBody
    public String save(Comment comment, HttpSession session) {
        Result result = new Result();

        Investor investor = investorService.get(comment.getInvestorid());
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser.getId().intValue() == investor.getUserid()) {
            result.set(-1, "您不能评价自己");
            return result.toString();
        }

        if (commentService.listByUserAndInvestor(loginUser.getId(), comment.getInvestorid()).size() > 0) {
            result.set(-1, "您已评价过，不能重复评价");
            return result.toString();
        }

        if (comment.getTags().indexOf("#") != -1) {
            comment.setTags(comment.getTags().substring(0, comment.getTags().length() - 1));
        }

        comment.setUserid(loginUser.getId());
        commentService.save(comment);
        return result.toString();
    }

    @RequestMapping("/list/{investorid}/{pageIndex}")
    public ModelAndView list(@PathVariable Integer investorid, @PathVariable Integer pageIndex) {

        int pageSize = 5;
        int recordCount = 0;

        Comment comment = new Comment();
        comment.setInvestorid(investorid);
        comment.setPageSize(pageSize);
        comment.setStartRow((pageIndex - 1) * pageSize);
        Map<String, Object> map = commentService.listByPage(comment);
        List<Comment> comments = (List<Comment>) map.get("comments");
        recordCount = (Integer) map.get("count");

        for (Comment comment1 : comments) {
            User user = userService.get(comment1.getUserid());
            comment1.setName(user.getName());
            comment1.setPhoto(user.getPhoto());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("investor/comment_list");
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("recordCount", recordCount);
        modelAndView.addObject("pageCount", recordCount % pageSize == 0 ? recordCount / pageSize : (recordCount / pageSize + 1));
        return modelAndView;
    }

}
