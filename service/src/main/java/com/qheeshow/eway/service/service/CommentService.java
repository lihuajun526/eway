package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Comment;

import java.util.List;

/**
 * Created by lihuajun on 17-3-15.
 */
public interface CommentService {

    void save(Comment comment);

    List<Comment> listByInvestor(Integer investorid);

    List<Comment> listByUserAndInvestor(Integer userid,Integer investorid);

}
