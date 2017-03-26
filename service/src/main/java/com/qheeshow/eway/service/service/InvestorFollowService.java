package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.InvestorFollow;

/**
 * Created by lihuajun on 2017/3/26.
 */
public interface InvestorFollowService {

    void save(InvestorFollow investorFollow);

    Boolean isFollow(InvestorFollow investorFollow);

}
