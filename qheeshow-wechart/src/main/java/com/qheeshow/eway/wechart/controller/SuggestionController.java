package com.qheeshow.eway.wechart.controller;

import com.vip.integral.base.BaseController;
import com.vip.integral.base.Result;
import com.operational.platform.dbservice.model.Suggestion;
import com.operational.platform.dbservice.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/suggest")
public class SuggestionController extends BaseController {

    @Autowired
    private SuggestionService suggestionService;

    @ResponseBody
    @RequestMapping("/save")
    public String save(Suggestion suggestion) {

        Result<Boolean> result = new Result<>();

        suggestionService.save(suggestion);

        result.set("提交成功",true);

        return result.toString();
    }



}
