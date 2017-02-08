package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.Message;
import com.qheeshow.eway.service.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-2-8.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Override public void save(Message message) {

    }

    @Override public List<Message> listByStatus(Integer status) {
        return null;
    }
}
