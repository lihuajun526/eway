package com.qheeshow.eway.service.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qheeshow.eway.service.dao.DocumentEnrollMapper;
import com.qheeshow.eway.service.dao.DocumentMapper;
import com.qheeshow.eway.service.model.Document;
import com.qheeshow.eway.service.model.DocumentEnroll;
import com.qheeshow.eway.service.service.DocumentEnrollService;

@Service
public class DocumentEnrollServiceImpl implements DocumentEnrollService{
	
	@Autowired
    private DocumentMapper documentMapper;
	
	@Autowired
	private DocumentEnrollMapper documentEnrollMapper;
	
	@Override
	public String addNewEnroll(DocumentEnroll documentEnroll){
		documentEnroll.setCreateTime(new Date());
		documentEnrollMapper.insert(documentEnroll);
		//获取该活动设置是否需要报名费
		Document document = documentMapper.selectByPrimaryKey(documentEnroll.getDocumentId());
		String url = "";
		if(document.getCruser() != null){
			//若需要报名费则根据活动设置生产订单相关信息发送请求至 支付宝/微信 获取支付二维码
		}
		return url;
	}
	
	

}
