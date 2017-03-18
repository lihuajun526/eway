package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Message;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-2-8.
 */
public interface MessageService {

    void save(Message message);

    List<Message> listByStatus(Integer status);

    public Map<String, Object> listByCondition(Integer userid, Integer status,
            Integer pageIndex, Integer pageSize);

    void del(Integer id, Integer userid);

}
