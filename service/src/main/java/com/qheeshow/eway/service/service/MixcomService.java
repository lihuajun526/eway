package com.qheeshow.eway.service.service;

import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.service.model.CallRecord;

import java.io.UnsupportedEncodingException;

/**
 * Created by lihuajun on 2017/3/24.
 */
public interface MixcomService {

    String bound(String a, String b, int time) throws UnsupportedEncodingException, CommonException, RequestException;

    String unBound();

    void saveRecord(CallRecord callRecord) throws CommonException;

}
