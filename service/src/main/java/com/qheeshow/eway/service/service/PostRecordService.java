package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.PostRecord;

import java.util.List;

/**
 * Created by lihuajun on 2017/5/16.
 */
public interface PostRecordService {

    List<PostRecord> listByUserAndToday(Integer userid);

    void save(PostRecord postRecord);

    List<PostRecord> listByInvestorAndProject(Integer investorid,Integer projectid);

}
