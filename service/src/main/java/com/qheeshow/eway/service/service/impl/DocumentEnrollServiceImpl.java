package com.qheeshow.eway.service.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qheeshow.eway.service.dao.DocumentEnrollMapper;
import com.qheeshow.eway.service.dao.DocumentMapper;
import com.qheeshow.eway.service.dao.UserMapper;
import com.qheeshow.eway.service.model.Document;
import com.qheeshow.eway.service.model.DocumentEnroll;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.DocumentEnrollService;

@Service
public class DocumentEnrollServiceImpl implements DocumentEnrollService{
	
	@Autowired
    private DocumentMapper documentMapper;
	
	@Autowired
	private DocumentEnrollMapper documentEnrollMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String addNewEnroll(DocumentEnroll documentEnroll){
		documentEnroll.setCreateTime(new Date());
		documentEnrollMapper.insert(documentEnroll);
		//获取该活动设置是否需要报名费
		Document document = documentMapper.selectByPrimaryKey(documentEnroll.getDocumentId());
		User user = userMapper.selectByPrimaryKey(documentEnroll.getUserId());
		if(document.getEnrollType() != null && document.getEnrollType() == 1 && user.getStatus() != null && user.getStatus() != 2){
			//该用户未通过审核
			return "0";
		}
		String url = "1";
		if(document.getEnrollPrice() != null && document.getEnrollPrice().compareTo(BigDecimal.ZERO) > 0){
			//若需要报名费则根据活动设置生产订单相关信息发送请求至 支付宝/微信 获取支付二维码
			
		}
		return url;
	}
	
	

}
