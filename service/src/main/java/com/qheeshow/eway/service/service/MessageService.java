package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Message;

import java.util.List;

/**
 * Created by lihuajun on 17-2-8.
 */
public interface MessageService {

    void save(Message message);

    List<Message> listByStatus(Integer status);

}
