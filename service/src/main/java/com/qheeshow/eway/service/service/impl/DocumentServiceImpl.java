package com.qheeshow.eway.service.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qheeshow.eway.service.dao.DocumentMapper;
import com.qheeshow.eway.service.model.DocumentExample;
import com.qheeshow.eway.service.model.DocumentWithBLOBs;
import com.qheeshow.eway.service.service.DocumentService;

/**
 * Created by lihuajun on 2017/2/20.
 */
@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentMapper documentMapper;
	
    @Override
    public void save(DocumentWithBLOBs document) {
    	Date nowTime = new Date();
    	document.setCreateTime(nowTime);
    	if(document.getStatus() != null && document.getStatus() == 2){
    		document.setPublishTime(nowTime);
    	}
    	documentMapper.insertSelective(document);
    }
    
    @Override
    public void update(DocumentWithBLOBs document) {
    	Date nowTime = new Date();
    	if(document.getStatus() != null && document.getStatus() == 2){
    		document.setPublishTime(nowTime);
    	}
    	DocumentExample example = new DocumentExample();
    	example.createCriteria().andCreateUserIdEqualTo(document.getCreateUserId()).andIdEqualTo(document.getId());
    	documentMapper.updateByExampleSelective(document, example);
    }

    @Override
    public List<DocumentWithBLOBs> getList(DocumentWithBLOBs document) {
    	DocumentExample example = new DocumentExample();
    	DocumentExample.Criteria criteria =  example.createCriteria();
    	if(document.getStatus() != null){
    		criteria.andStatusEqualTo(document.getStatus());
    	}
    	if(document.getTitle() != null){
    		criteria.andTitleLike(document.getTitle());
    	}
        List<DocumentWithBLOBs> list = documentMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    @Override
    public DocumentWithBLOBs get(Integer id) {
    	DocumentWithBLOBs dou = documentMapper.selectByPrimaryKey(id);
        return dou;
    }
}
