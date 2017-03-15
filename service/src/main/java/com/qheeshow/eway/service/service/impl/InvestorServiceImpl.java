package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.InvestorMapper;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.InvestorExample;
import com.qheeshow.eway.service.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class InvestorServiceImpl implements InvestorService {

    @Autowired
    InvestorMapper investorMapper;

    @Override
    public void save(Investor investor) {
        investorMapper.insertSelective(investor);
        if (investor.getId().intValue() == 0) {
            investor.setStatus(1);
            investorMapper.insert(investor);
        } else {
            investorMapper.updateByPrimaryKeySelective(investor);
        }
    }

    @Override
    public void update(Investor investor) {
        investorMapper.updateByPrimaryKeySelective(investor);
    }

    @Override
    public Investor detail(Integer id) {
        Investor investor = investorMapper.selectByPrimaryKey(id);
        return investor;
    }

    @Override
    public List<Investor> list(Investor investor, HttpSession session) {
        Integer status = investor.getStatus();
        InvestorExample example = new InvestorExample();
        InvestorExample.Criteria criteria = example.createCriteria();
        //列表查看权限控制
        if (status != null) {
            if ((status == 1 && session.getAttribute("roleid").toString().equals("2")) || status == 2 || status == 3) {
                criteria.andStatusEqualTo(investor.getStatus());
            } else {
                criteria.andStatusEqualTo(-1);
            }
        } else {
            if (session.getAttribute("roleid").toString().equals("2")) {
                criteria.andStatusEqualTo(-1);
            }
        }
        example.setOrderByClause("create_time");
        List<Investor> investors = investorMapper.selectByExample(example);
        return investors;
    }

    @Override public Investor get(Integer id) {
        return investorMapper.selectByPrimaryKey(id);
    }

}
