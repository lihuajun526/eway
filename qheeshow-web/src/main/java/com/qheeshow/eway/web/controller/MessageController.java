package com.qheeshow.eway.web.controller;

import com.qheeshow.eway.service.model.Message;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.MessageService;
import com.qheeshow.eway.web.base.BaseController;
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
@RequestMapping("/center/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/list/{status}/{pageIndex}/{pageSize}")
    public ModelAndView list(@PathVariable Integer status, @PathVariable Integer pageIndex, @PathVariable Integer pageSize, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        Map<String, Object> map = messageService.listByCondition(loginUser.getId(), status, pageIndex, pageSize);
        List<Message> messages = (List<Message>) map.get("messages");
        Integer count = (Integer) map.get("count");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("center/message_list");
        modelAndView.addObject("messages", messages);
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("pageCount", count % pageSize == 0 ? count / pageSize : (count / pageSize + 1));
        return modelAndView;
    }
}
