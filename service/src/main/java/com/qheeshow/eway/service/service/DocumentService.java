package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Document;

import java.util.List;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface DocumentService {

    void save(Document document);

    List<Document> listByStatus(Integer status);

    Document get(Integer id);

}
