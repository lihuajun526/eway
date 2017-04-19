package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.CommonQa;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.CommonQaService;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/qa")
public class CommonQaController extends BaseController {

    @Autowired
    private CommonQaService commonQaService;
    @Autowired
    private UserService userService;

    @RequestMapping("/list/{projectid}/{index}")
    public ModelAndView list(@PathVariable Integer projectid, @PathVariable Integer index) {

        CommonQa commonQa = new CommonQa();
        commonQa.setPageSize(6);

        Map map = commonQaService.listByPage(commonQa, index);
        Integer count = (Integer) map.get("count");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/common_qa");
        modelAndView.addObject("commonQas", (List<CommonQa>) map.get("commonQas"));
        modelAndView.addObject("projectid", projectid);
        modelAndView.addObject("pageIndex", index);
        modelAndView.addObject("count", count);
        modelAndView.addObject("pageCount", count % 6 == 0 ? count / 6 : (count / 6 + 1));

        return modelAndView;
    }

    /**
     * 提问
     * @param commonQa
     * @param session
     * @return
     */
    @RequestMapping("/q/authj")
    public String q(CommonQa commonQa, HttpSession session) {

        Result result = new Result();

        User loginUser = (User)session.getAttribute("loginUser");

        commonQa.setParentid(0);
        commonQa.setUserid(loginUser.getId());
        commonQa.setName(loginUser.getName());
        commonQa.setPhoto(loginUser.getPhoto());
        commonQa.setStatus(1);

        commonQaService.save(commonQa);

        return result.toString();
    }

    /**
     * 回答
     * @param commonQa
     * @param session
     * @return
     */
    @RequestMapping("/a/authj")
    public String a(CommonQa commonQa, HttpSession session) {
        Result result = new Result();

        CommonQa q = commonQaService.get(commonQa.getParentid());
        User qUser = userService.get(commonQa.getqUserid());
        User loginUser = (User)session.getAttribute("loginUser");

        commonQa.setQuestion(q.getContent());
        commonQa.setqName(qUser.getName());
        commonQa.setUserid(loginUser.getId());
        commonQa.setName(loginUser.getName());
        commonQa.setPhoto(loginUser.getPhoto());
        commonQa.setStatus(1);

        commonQaService.save(commonQa);

        return result.toString();
    }

}
