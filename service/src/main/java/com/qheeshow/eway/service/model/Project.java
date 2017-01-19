package com.qheeshow.eway.service.model;

import java.math.BigDecimal;

/**
 * Created by lihuajun on 17-1-11.
 */
public class Project extends BaseModel{

    private String title;//项目标题
    private String summary;//项目简介
    private String desc;//项目介绍
    private String content;//项目内容
    private String videoLink;//宣传视频
    private String proLink;//项目网址
    private Integer userid;//项目负责人
    private Integer status;//项目状态:1新项目,2审核通过,3审核未通过
    private String logo;//项目logo
    private String tags;//项目标签
    private String bp;//商业计划书
    private BigDecimal financingLimit;//融资额度
    private Integer percent;//出让股份百分比
    private String referee;//推荐人姓名


}
