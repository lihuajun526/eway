package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.CommentMapper;
import com.qheeshow.eway.service.model.Comment;
import com.qheeshow.eway.service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lihuajun on 17-3-15.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override public void save(Comment comment) {
        commentMapper.insert(comment);
    }
}
