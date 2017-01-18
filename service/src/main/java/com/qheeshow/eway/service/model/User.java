package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-1-12.
 */
public class User extends BaseModel {

    private String mobile;
    private String email;
    private String password;
    private String openid;
    private String nickname;
    private String name;
    private Integer roleid;//角色：1超级管理员,2普通管理员,3创业者,4投资人
    private Integer companyid;//企业id

}

