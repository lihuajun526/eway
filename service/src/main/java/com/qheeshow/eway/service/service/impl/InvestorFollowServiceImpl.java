package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.InvestorFollowMapper;
import com.qheeshow.eway.service.dao.InvestorMapper;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.InvestorExample;
import com.qheeshow.eway.service.model.InvestorFollow;
import com.qheeshow.eway.service.model.InvestorFollowExample;
import com.qheeshow.eway.service.service.InvestorFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2017/3/26.
 */
@Service
public class InvestorFollowServiceImpl implements InvestorFollowService {

    @Autowired
    private InvestorFollowMapper investorFollowMapper;
    @Autowired
    private InvestorMapper investorMapper;

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

    @Override
    public List<Investor> listByUser(Integer userid) {
        InvestorFollowExample example = new InvestorFollowExample();
        InvestorFollowExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        example.setOrderByClause("create_time desc");
        List<InvestorFollow> list = investorFollowMapper.selectByExample(example);
        List<Integer> ids = new ArrayList<>();

        for (InvestorFollow investorFollow : list) {
            ids.add(investorFollow.getInvestorid());
        }

        if (ids.size() == 0)
            return new ArrayList<>();

        if (ids.size() > 20)
            ids = ids.subList(0, 20);

        InvestorExample example1 = new InvestorExample();
        InvestorExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andIdIn(ids);
        return investorMapper.selectByExample(example1);
    }

    @Override
    public void unFollow(InvestorFollow investorFollow) {

        InvestorFollowExample example = new InvestorFollowExample();
        InvestorFollowExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(investorFollow.getUserid());
        criteria.andInvestoridEqualTo(investorFollow.getInvestorid());

        investorFollowMapper.deleteByExample(example);
    }
}
