package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.common.constant.ExceptionTypeEnum;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.ProjectAdviser;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.MessageService;
import com.qheeshow.eway.service.service.ProjectAdviserService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/project/adviser")
public class ProjectAdviserController extends BaseController {

    @Autowired
    private ProjectAdviserService projectAdviserService;
    @Autowired
    private MessageService messageService;

    @RequestMapping("/apply/{projectid}/authj")
    @ResponseBody
    public String apply(@PathVariable Integer projectid, HttpSession session) {

        Result result = new Result();
        User loginUser = (User) session.getAttribute("loginUser");
        try {
            projectAdviserService.apply(projectid, loginUser.getId());
        } catch (CommonException e) {
            if (e.getCode().equals(ExceptionTypeEnum.Investor_Not_Auth_ERROR.getCode())) {
                result.set(-2, "亲爱的用户，请先认证成为合格投资人才能申请成为专职顾问哦！");
            } else if (e.getCode().equals(ExceptionTypeEnum.Is_Not_Adviser_ERROR.getCode())) {
                result.set(-3, "对不起，您的身份不是投资人，所以不能申请成为专职顾问");
            } else if (e.getCode().equals(ExceptionTypeEnum.Adviser_Info_Not_Full.getCode())) {
                result.set(-4, "对不起，您的投资人身份信息需完善");
            } else {
                result.set(-5, e.getDesc());
            }
            return result.toString();
        }
        return result.toString();
    }

    @RequestMapping("/list/{projectid}")
    @ResponseBody
    public String list(@PathVariable Integer projectid) {

        Result<List<Investor>> result = new Result<>();

        List<Investor> list = projectAdviserService.list(projectid);
        result.setData(list);

        return result.toString();
    }

    @RequestMapping("/list/all/{status}")
    @ResponseBody
    public String listAll(@PathVariable Integer status) {

        Result<List<ProjectAdviser>> result = new Result<>();

        List<ProjectAdviser> list = projectAdviserService.listByStatus(status);
        result.setData(list);

        return result.toString();
    }

    @RequestMapping("/status/set/{projectid}/{userid}/{status}")
    @ResponseBody
    public String setStatus(@PathVariable Integer projectid, @PathVariable Integer userid, @PathVariable Integer status,
                            String description) {

        Result result = new Result();

        ProjectAdviser projectAdviser = projectAdviserService.getByProjectAndUser(projectid, userid);
        projectAdviser.setStatus(status);
        projectAdviser.setDescription(description);

        projectAdviserService.save(projectAdviser);

        // TODO: 17-2-8 通过不通过要发送message

        return result.toString();
    }

    @RequestMapping("/is/able")
    @ResponseBody
    public String isAbleToBeAdviser(ProjectAdviser projectAdviser) {

        Result<Boolean> result = new Result<>();

        Boolean isAble = projectAdviserService.isAbleToBeAdviser(projectAdviser);

        result.setData(isAble);

        return result.toString();
    }

}
