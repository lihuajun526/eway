package com.qheeshow.eway.backstage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.DocumentEnroll;
import com.qheeshow.eway.service.service.DocumentEnrollService;

@Controller
@RequestMapping("/documentEnroll")
public class DocumentEnrollController {

	@Autowired
    private DocumentEnrollService documentEnrollService;

	/**
	 * 
	 * @Title: addNewEnroll
	 * @Description: 报名参加活动
	 * @author yue
	 * @date 2017年2月25日 下午2:56:17
	 * @param documentEnroll
	 * @param session
	 * @return
	 */
    @RequestMapping("/addNewEnroll")
    @ResponseBody
    public HaResponse addNewEnroll(DocumentEnroll documentEnroll,HttpSession session) {
    	if(session.getAttribute("userId") != null){
    		documentEnroll.setUserId((Integer) session.getAttribute("userId"));
//    		documentEnroll.setUserName(session.getAttribute("userName").toString());
    		String url = documentEnrollService.addNewEnroll(documentEnroll);
            return HaResponse.sussess(url);
    	}else{
    		return HaResponse.fail();
    	}
    }
	
}
