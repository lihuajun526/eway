package com.qheeshow.eway.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.InvestorWithBLOBs;
import com.qheeshow.eway.service.service.InvestorService;

@Controller
@RequestMapping("/investor")
public class InvestorController {
	
	@Autowired
	InvestorService investorService;
	
	/**
	 * 
	 * @Title: submit
	 * @Description: 提交审核信息
	 * @author yue
	 * @date 2017年3月5日 下午2:52:57
	 * @param investor
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/submit")
    @ResponseBody
	public HaResponse submit(InvestorWithBLOBs investor,HttpSession session){
		session.setAttribute("user_id", "123");
		investor.setUserId(Integer.parseInt(session.getAttribute("user_id").toString()));
		investorService.submit(investor);
		return HaResponse.sussess();
	}
	
	/**
	 * 
	 * @Title: update
	 * @Description: 审核
	 * @author yue
	 * @date 2017年3月5日 下午2:53:12
	 * @param investor
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/update")
	@ResponseBody
	public HaResponse update(InvestorWithBLOBs investor,HttpSession session){
		session.setAttribute("roleid", "1");
		if(!session.getAttribute("roleid").toString().equals("2")){
			return HaResponse.fail("您无权限执行此操作");
		}
		investorService.update(investor);
		return HaResponse.sussess();
	}
	
	/**
	 * 
	 * @Title: detail
	 * @Description: 根据id获取详情
	 * @author yue
	 * @date 2017年3月5日 下午2:53:21
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/detail")
	@ResponseBody
	public HaResponse detail(Integer id){
		InvestorWithBLOBs investor = investorService.detail(id);
		return HaResponse.sussess(investor);
	}
	
	/**
	 * 
	 * @Title: list
	 * @Description: 获取列表
	 * @author yue
	 * @date 2017年3月5日 下午2:53:29
	 * @param investor
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public HaResponse list(InvestorWithBLOBs investor,HttpSession session){
		List<InvestorWithBLOBs> investors = investorService.list(investor,session);
		return HaResponse.sussess(investors);
	}

}
