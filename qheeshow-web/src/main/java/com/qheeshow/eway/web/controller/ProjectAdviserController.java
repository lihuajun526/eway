package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.exception.CommonException;
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

    @RequestMapping("/apply/{projectid}/{userid}")
    @ResponseBody
    public String apply(@PathVariable Integer projectid, @PathVariable Integer userid) {

        Result result = new Result();

        try {
            projectAdviserService.apply(projectid, userid);
        } catch (CommonException e) {
            result.set(-1, e.getDesc());
            return result.toString();
        }

        return result.toString();
    }

    @RequestMapping("/list/{projectid}")
    @ResponseBody
    public String list(@PathVariable Integer projectid) {

        Result<List<User>> result = new Result<>();

        List<User> list = projectAdviserService.list(projectid);
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
    public String setStatus(@PathVariable Integer projectid, @PathVariable Integer userid, @PathVariable Integer status, String desc) {

        Result result = new Result();

        ProjectAdviser projectAdviser = projectAdviserService.getByProjectAndUser(projectid,userid);
        projectAdviser.setStatus(status);
        projectAdviser.setDesc(desc);

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
