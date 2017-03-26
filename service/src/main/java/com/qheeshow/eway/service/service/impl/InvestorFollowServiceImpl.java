package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.InvestorFollowMapper;
import com.qheeshow.eway.service.model.InvestorFollow;
import com.qheeshow.eway.service.model.InvestorFollowExample;
import com.qheeshow.eway.service.service.InvestorFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/26.
 */
@Service
public class InvestorFollowServiceImpl implements InvestorFollowService {

    @Autowired
    private InvestorFollowMapper investorFollowMapper;

    @Override
    public void save(InvestorFollow investorFollow) {
        investorFollowMapper.insert(investorFollow);
    }

    @Override
    public Boolean isFollow(InvestorFollow investorFollow) {
        InvestorFollowExample example = new InvestorFollowExample();
        InvestorFollowExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(investorFollow.getUserid());
        criteria.andInvestoridEqualTo(investorFollow.getInvestorid());
        List<InvestorFollow> list = investorFollowMapper.selectByExample(example);
        if (list.size() == 0)
            return false;
        else
            return true;
    }
}
