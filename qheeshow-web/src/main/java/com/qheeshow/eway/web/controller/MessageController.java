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

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/center/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/list/{status}")
    public ModelAndView list(@PathVariable Integer status, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        List<Message> messages = messageService.listByUserAndStatus(loginUser.getId(), status);



        return null;
    }

}
