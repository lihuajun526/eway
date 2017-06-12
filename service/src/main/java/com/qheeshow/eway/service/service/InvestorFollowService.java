package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.InvestorFollow;

import java.util.List;

/**
 * Created by lihuajun on 2017/3/26.
 */
public interface InvestorFollowService {

    void save(InvestorFollow investorFollow);

    Boolean isFollow(InvestorFollow investorFollow);

    List<Investor> listByUser(Integer userid);

    void unFollow(InvestorFollow investorFollow);

}
