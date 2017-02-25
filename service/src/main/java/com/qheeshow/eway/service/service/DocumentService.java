package com.qheeshow.eway.service.service;


import java.util.List;

import com.qheeshow.eway.service.model.DocumentWithBLOBs;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface DocumentService {

    void save(DocumentWithBLOBs document);
    
    void update(DocumentWithBLOBs document);

    List<DocumentWithBLOBs> getList(DocumentWithBLOBs document);

    DocumentWithBLOBs get(Integer id);

}
