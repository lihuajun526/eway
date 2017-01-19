package com.qheeshow.eway.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.Result;
import com.qheeshow.eway.backstage.bean.EasyTreeNode;
import com.qheeshow.eway.service.model.TreeNode;
import com.qheeshow.eway.service.service.TreeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/tree")
public class TreeNodeController extends BaseController {

    @Autowired
    private TreeNodeService treeNodeService;

    @RequestMapping("/add")
    @ResponseBody
    public String save(TreeNode treeNode) {

        Result<TreeNode> result = new Result<>();

        treeNodeService.save(treeNode);

        result.set("添加成功", treeNode);
        return result.toString();
    }

    @RequestMapping("/modify")
    @ResponseBody
    public String update(TreeNode treeNode) {

        Result<Boolean> result = new Result<>();

        treeNodeService.update(treeNode);

        result.set("更新成功", true);
        return result.toString();
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(Integer id) {

        Result<TreeNode> result = new Result<>();

        TreeNode treeNode = treeNodeService.get(id);

        result.setData(treeNode);
        return result.toString();
    }

    @RequestMapping("/tree")
    @ResponseBody
    public String tree(Integer id) {

        if (id == null)
            id = 0;

        List<TreeNode> list = treeNodeService.listByParent(id);

        if (list == null)
            return "[{\"id\": 0,\"text\": \"根\"}]";

        List<EasyTreeNode> easyTreeNodes = new ArrayList<>();

        for (TreeNode treeNode : list) {
            EasyTreeNode easyTreeNode = new EasyTreeNode();
            easyTreeNode.setId(treeNode.getId());
            easyTreeNode.setText(treeNode.getText());
            easyTreeNode.setAttributes(JSON.parseObject(treeNode.getAttributes()));

            if (isLeaf(treeNode))
                easyTreeNode.setState("open");
            else
                easyTreeNode.setState("closed");
            easyTreeNodes.add(easyTreeNode);
        }
        return JSONArray.toJSONString(easyTreeNodes);
    }

    /**
     * 判断是否是叶子节点
     *
     * @param treeNode
     * @return
     */
    private boolean isLeaf(TreeNode treeNode) {
        List<TreeNode> children = treeNodeService.listByParent(treeNode.getId());
        if (children == null || children.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
