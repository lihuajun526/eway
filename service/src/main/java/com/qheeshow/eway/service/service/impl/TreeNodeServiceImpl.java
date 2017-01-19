package com.qheeshow.eway.service.service.impl;


import com.qheeshow.eway.service.dao.TreeNodeMapper;
import com.qheeshow.eway.service.model.TreeNode;
import com.qheeshow.eway.service.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 16-7-25.
 */
@Service("treeNodeService")
public class TreeNodeServiceImpl implements TreeNodeService {

    @Autowired
    private TreeNodeMapper treeNodeMapper;

    @Override public int save(TreeNode treeNode) {
        return treeNodeMapper.insert(treeNode);
    }

    @Override public int update(TreeNode treeNode) {
        return 0;
    }

    @Override public TreeNode get(Integer id) {
        return null;
    }

    @Override public List<TreeNode> listByParent(Integer parentid) {
        return treeNodeMapper.listByParent(parentid);
    }
}
