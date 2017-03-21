package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Comment;
import com.qheeshow.eway.service.service.CommentService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/save")
    @ResponseBody
    public String save(Comment comment) {

        Result result = new Result();

        commentService.save(comment);

        return result.toString();
    }

}