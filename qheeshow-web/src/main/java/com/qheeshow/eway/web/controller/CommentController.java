package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Comment;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.CommentService;
import com.qheeshow.eway.service.service.InvestorService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

        if(commentService.listByUserAndInvestor(loginUser.getId(),comment.getInvestorid()).size()>0){
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

}
