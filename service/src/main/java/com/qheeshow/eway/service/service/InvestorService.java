package com.qheeshow.eway.service.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.qheeshow.eway.common.page.PageInfo;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.Project;

public interface InvestorService {

    public void save(Investor investor);

    public void update(Investor investor);

    public Investor detail(Integer id);

    public List<Investor> list(Investor investor, HttpSession session);

    public List<Investor> listAll(Investor investor, PageInfo pageInfo);

    Investor get(Integer id);

    List<Investor> listSuggest(Integer projectid);

    int getCountByCondition(Investor investor);

    List<Investor> listByCondition(Investor investor);

    Map<String, Object> listByCondition(String cityid,
                                        String industryid, String stageid, Integer pageIndex, Integer pageSize);

    List<Investor> bestInvestor(Integer num);

    Map<String, Object> listByInvestor(Investor investor);

    void updateStatus(Integer investorid,Integer status);

    void updateAuth(Integer investorid,Integer authStatus);

    void setBest(Integer investorid,Integer isBest);

    void setSign(Integer investorid, Integer isSign);
}
