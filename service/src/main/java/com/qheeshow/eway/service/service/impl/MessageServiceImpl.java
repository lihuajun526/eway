package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.Message;
import com.qheeshow.eway.service.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override public Map<String, Object> listByCondition(Integer userid, Integer status, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override public void del(Integer id, Integer userid) {

    }
}
