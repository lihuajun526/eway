package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-1-11.
 */
public class Project extends BaseModel{

    private String title;//项目标题
    private String desc;//项目描述
    private String content;//项目内容
    private Integer userid;//项目负责人
    private Integer status;//项目状态:1新项目,2审核通过,3审核未通过

}
