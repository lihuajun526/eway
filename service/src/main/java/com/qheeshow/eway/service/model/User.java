package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-1-12.
 */
public class User extends BaseModel{

    private String mobile;
    private String email;
    private String password;
    private String openid;
    private String nickname;
    private String name;
    private Integer type;//类型：1创业者，2投资人
    private Integer companyid;//企业id

}

