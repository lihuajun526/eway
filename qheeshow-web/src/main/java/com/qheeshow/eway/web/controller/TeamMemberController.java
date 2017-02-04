package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.TeamMember;
import com.qheeshow.eway.service.service.TeamMemberService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-6-14.
 */
@RequestMapping("/team/member")
@Controller
public class TeamMemberController extends BaseController {

    @Autowired
    private TeamMemberService teamMemberService;

    @RequestMapping("/save")
    @ResponseBody
    public String save(TeamMember teamMember) {
        Result<TeamMember> result = new Result<>();

        teamMemberService.save(teamMember);

        result.setData(teamMember);

        return result.toString();
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del(@PathVariable Integer memberid) {
        Result result = new Result();

        teamMemberService.del(memberid);

        return result.toString();
    }

}
