package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.common.page.PageInfo;
import com.qheeshow.eway.service.dao.InvestorMapper;
import com.qheeshow.eway.service.dao.UserMapper;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.InvestorExample;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class InvestorServiceImpl implements InvestorService {

    @Autowired
    private InvestorMapper investorMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(Investor investor) {
        if (investor.getId() == null) {
            investor.setStatus(1);
            investor.setAuthStatus(0);
            investor.setIsSign(0);
            investor.setIsBest(0);
            investorMapper.insert(investor);
            //更新user中的photo
            User user = new User();
            user.setId(investor.getUserid());
            user.setPhoto(investor.getPhoto());
            userMapper.updateByPrimaryKeySelective(user);
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

    @Override
    public List<Investor> listAll(Investor investor, PageInfo pageInfo) {
        InvestorExample example = new InvestorExample();
        InvestorExample.Criteria criteria = example.createCriteria();
        criteria.andStatusIn(new ArrayList<Integer>(Arrays.asList(2, 3)));
        if (investor.getStageId() != null) {
            criteria.andStageIdLike("%#" + investor.getStageId() + "#%");
        }
        if (investor.getCityId() != null) {
            criteria.andCityIdLike("%#" + investor.getCityId() + "#%");
        }
        if (investor.getIndustryId() != null) {
            criteria.andIndustryIdLike("%#" + investor.getIndustryId() + "#%");
        }
        if (investor.getTrueName() != null) {
            criteria.andTrueNameLike("%" + investor.getTrueName() + "%");
        }
//	    if(investor.getIdentityId() != null){
//	    	criteria.andIdentityIdLike("%#" + investor.getIdentityId() + "#%");
//	    }
        example.setOrderByClause("create_time");
        example.setPageInfo(pageInfo);
        List<Investor> investors = investorMapper.selectByExample(example);
        return investors;
    }

    @Override
    public Investor get(Integer id) {
        return investorMapper.selectByPrimaryKey(id);
    }

    @Override
    public Investor getByUser(Integer userid) {
        InvestorExample example = new InvestorExample();
        InvestorExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);

        List<Investor> list = investorMapper.selectByExample(example);

        if (list.size() == 0)
            return null;
        else return list.get(0);
    }

    @Override
    public List<Investor> listSuggest(Integer projectid) {
        return investorMapper.listSuggest(projectid);
    }

    @Override
    public int getCountByCondition(Investor investor) {
        InvestorExample example = new InvestorExample();
        InvestorExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(investor.getIndustryId()))
            criteria.andIndustryIdLike(investor.getIndustryId());
        if (!StringUtils.isEmpty(investor.getCityId()))
            criteria.andCityIdLike(investor.getCityId());
        if (!StringUtils.isEmpty(investor.getStageId()))
            criteria.andStageIdLike(investor.getStageId());
        if (!StringUtils.isEmpty(investor.getSinglePriceId()))
            criteria.andSinglePriceIdEqualTo(investor.getSinglePriceId());
        return investorMapper.countByExample(example);
    }

    @Override
    public List<Investor> listByCondition(Investor investor) {
        InvestorExample example = new InvestorExample();
        InvestorExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(investor.getIndustryId()))
            criteria.andIndustryIdLike(investor.getIndustryId());
        if (!StringUtils.isEmpty(investor.getCityId()))
            criteria.andCityIdLike(investor.getCityId());
        if (!StringUtils.isEmpty(investor.getStageId()))
            criteria.andStageIdLike(investor.getStageId());
        if (!StringUtils.isEmpty(investor.getSinglePriceId()))
            criteria.andSinglePriceIdEqualTo(investor.getSinglePriceId());
        return investorMapper.selectByExample(example);
    }

    @Override
    public Map<String, Object> listByCondition(String cityid, String industryid, String stageid, Integer pageIndex, Integer pageSize, String keyword) {
        Investor investor = new Investor();
        if (!"0".equals(cityid))
            investor.setCityId("#" + cityid + "#");
        if (!"0".equals(industryid))
            investor.setIndustryId("#" + industryid + "#");
        if (!"0".equals(stageid))
            investor.setStageId("#" + stageid + "#");
        investor.setKeyword(keyword);
        investor.setPageSize(pageSize);
        investor.setStartRow((pageIndex - 1) * investor.getPageSize());
        Map<String, Object> map = new HashMap<>();
        map.put("investors", investorMapper.listByCondition(investor));
        map.put("count", investorMapper.getCount(investor).size());
        return map;
    }

    @Override
    public List<Investor> bestInvestor(Integer num) {
        return investorMapper.bestInvestor(num);
    }

    @Override
    public Map<String, Object> listByInvestor(Investor investor) {
        List<Investor> list = investorMapper.listByInvestor(investor);
        Integer count = investorMapper.countByInvestor(investor);
        Map map = new HashMap<>();
        map.put("investors", list);
        map.put("count", count);
        return map;
    }

    @Override
    public void updateStatus(Integer investorid, Integer status) {
        Investor investor = new Investor();
        investor.setId(investorid);
        investor.setStatus(status);

        investorMapper.updateByPrimaryKeySelective(investor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public void updateAuth(Integer userid, Integer investorid, Integer authStatus) {
        Investor investor = new Investor();
        investor.setId(investorid);
        investor.setAuthStatus(authStatus);

        investorMapper.updateByPrimaryKeySelective(investor);

        User user = new User();
        user.setId(userid);
        if (authStatus.intValue() == 2) {
            user.setRoleid(31);
        } else {
            user.setRoleid(30);
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void setBest(Integer investorid, Integer isBest) {
        Investor investor = new Investor();
        investor.setId(investorid);
        investor.setIsBest(isBest);

        investorMapper.updateByPrimaryKeySelective(investor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public void setSign(Integer userid, Integer investorid, Integer isSign) {
        Investor investor = new Investor();
        investor.setId(investorid);
        investor.setIsSign(isSign);

        investorMapper.updateByPrimaryKeySelective(investor);

        User user = new User();
        user.setId(userid);
        if (isSign.intValue() == 2) {
            user.setRoleid(32);
        } else {
            user.setRoleid(31);
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<Investor> listByIndustry(Investor investor) {
        return investorMapper.listByCondition(investor);
    }

    @Override
    public List<Investor> search(String keyword) {

        InvestorExample example = new InvestorExample();
        InvestorExample.Criteria criteria = example.createCriteria();
        criteria.andTrueNameLike("%" + keyword + "%");

        return investorMapper.selectByExample(example);
    }
}
