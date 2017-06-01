package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.PostRecordMapper;
import com.qheeshow.eway.service.model.PostRecord;
import com.qheeshow.eway.service.model.PostRecordExample;
import com.qheeshow.eway.service.service.PostRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/5/16.
 */
@Service
public class PostRecordServiceImpl implements PostRecordService {

    @Autowired
    private PostRecordMapper postRecordMapper;

    @Override
    public List<PostRecord> listByUserAndToday(Integer userid) {

        return postRecordMapper.listByUserAndToday(userid);
    }

    @Override
    public void save(PostRecord postRecord) {
        postRecordMapper.insert(postRecord);
    }

    @Override
    public List<PostRecord> listByInvestorAndProject(Integer investorid, Integer projectid) {

        PostRecordExample example = new PostRecordExample();
        PostRecordExample.Criteria criteria = example.createCriteria();
        criteria.andInvestoridEqualTo(investorid);
        criteria.andProjectidEqualTo(projectid);

        return postRecordMapper.selectByExample(example);
    }
}
