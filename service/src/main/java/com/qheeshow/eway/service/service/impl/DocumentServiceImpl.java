package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.Document;
import com.qheeshow.eway.service.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 2017/2/20.
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Override
    public void save(Document document) {

    }

    @Override
    public List<Document> listByStatus(Integer status) {

        List<Document> list = new ArrayList<>();

        return list;
    }

    @Override
    public Document get(Integer id) {
        return null;
    }
}
