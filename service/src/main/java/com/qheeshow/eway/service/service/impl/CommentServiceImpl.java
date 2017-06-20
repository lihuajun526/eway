package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.CommentMapper;
import com.qheeshow.eway.service.model.Comment;
import com.qheeshow.eway.service.model.CommentExample;
import com.qheeshow.eway.service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-3-15.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void save(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public List<Comment> listByInvestor(Integer investorid) {

        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andInvestoridEqualTo(investorid);
        return commentMapper.selectByExample(example);
    }

    @Override
    public List<Comment> listByUserAndInvestor(Integer userid, Integer investorid) {
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        criteria.andInvestoridEqualTo(investorid);
        return commentMapper.selectByExample(example);
    }

    @Override
    public Map<String, Object> listByPage(Comment comment) {
        Map<String, Object> map = new HashMap<>();
        map.put("comments", commentMapper.listByPage(comment));
        map.put("count", commentMapper.countByPage(comment).size());
        return map;
    }
}
