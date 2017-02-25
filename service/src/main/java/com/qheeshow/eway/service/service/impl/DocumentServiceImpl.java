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
    	document.setCrtime(nowTime);
    	if(document.getDocstatus() != null && document.getDocstatus() == 2){
    		document.setDocpubtime(nowTime);
    	}
    	documentMapper.insertSelective(document);
    }
    
    @Override
    public void update(DocumentWithBLOBs document) {
    	Date nowTime = new Date();
    	document.setDocreltime(nowTime);
    	if(document.getDocstatus() != null && document.getDocstatus() == 2){
    		document.setDocpubtime(nowTime);
    	}
    	DocumentExample example = new DocumentExample();
    	example.createCriteria().andCruserEqualTo(document.getCruser()).andDocidEqualTo(document.getDocid());
    	documentMapper.updateByExampleSelective(document, example);
    }

    @Override
    public List<DocumentWithBLOBs> getList(DocumentWithBLOBs document) {
    	DocumentExample example = new DocumentExample();
    	DocumentExample.Criteria criteria =  example.createCriteria();
    	if(document.getDocstatus() != null){
    		criteria.andDocstatusEqualTo(document.getDocstatus());
    	}
    	if(document.getDoctitle() != null){
    		criteria.andDoctitleLike(document.getDoctitle());
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
