package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.MessageMapper;
import com.qheeshow.eway.service.model.Message;
import com.qheeshow.eway.service.model.MessageExample;
import com.qheeshow.eway.service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-2-8.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void save(Message message) {

    }

    @Override
    public List<Message> listByStatus(Integer status) {
        return null;
    }

    @Override
    public Map<String, Object> listByCondition(Integer userid, Integer status, Integer pageIndex, Integer pageSize) {
        Message message = new Message();
        message.setToUserid(userid);
        message.setStatus(status);
        message.setPageSize(pageSize);
        message.setStartRow((pageIndex - 1) * message.getPageSize());
        Map<String, Object> map = new HashMap<>();
        map.put("messages", messageMapper.listByCondition(message));
        map.put("count", messageMapper.getCount(message).size());
        return map;
    }

    @Override
    public void del(Integer id, Integer userid) {
        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andToUseridEqualTo(userid);
        messageMapper.deleteByExample(example);
    }
}
