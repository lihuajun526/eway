package com.qheeshow.eway.backstage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.DocumentWithBLOBs;
import com.qheeshow.eway.service.service.DocumentService;


/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/document")
public class DocumentController extends BaseController {

    @Autowired
    private DocumentService documentService;

    /**
     * 
     * @Title: save
     * @Description: 新增或修改活动
     * @author yue
     * @date 2017年2月25日 下午1:35:19
     * @param document
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public HaResponse save(DocumentWithBLOBs document,HttpSession session) {
    	session.setAttribute("userId", "1");
    	if(session.getAttribute("userId") != null){
    		document.setCreateUserId(Integer.parseInt(session.getAttribute("userId").toString()));
    		if(document.getId() == null){
                documentService.save(document);
    		}else{
            	documentService.update(document);
    		}
            return HaResponse.sussess();
    	}else{
    		return HaResponse.fail();
    	}
    }

    /**
     * 
     * @Title: update
     * @Description: 修改活动
     * @author yue
     * @date 2017年2月25日 下午1:38:14
     * @param document
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public HaResponse update(DocumentWithBLOBs document,HttpSession session) {
    	session.setAttribute("userId", "1");
    	if(session.getAttribute("userId") != null){
    		document.setCreateUserId(Integer.parseInt(session.getAttribute("userId").toString()));
        	documentService.update(document);
            return HaResponse.sussess();
    	}else{
    		return HaResponse.fail();
    	}
    }
    
    /**
     * 
     * @Title: getList
     * @Description: 根据条件获取活动列表
     * @author yue
     * @date 2017年2月25日 下午1:35:36
     * @param document
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public HaResponse getList(DocumentWithBLOBs document) {
        List<DocumentWithBLOBs> list = documentService.getList(document);
        return HaResponse.sussess(list);

    }

    /**
     * 
     * @Title: get
     * @Description: 根据id获取活动详情
     * @author yue
     * @date 2017年2月25日 下午1:35:49
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{appid}", method = RequestMethod.GET)
    @ResponseBody
    public HaResponse get(@PathVariable("appid") Integer id) {
        DocumentWithBLOBs document = documentService.get(id);
        return HaResponse.sussess(document);
    }
    
}
