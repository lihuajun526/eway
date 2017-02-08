package com.qheeshow.eway.service.constant;

/**
 * @author: Zhou Xuanang
 * @Date: 09:59 2016/11/10.
 */
public enum ExceptionTypeEnum {

    Project_Adviser_Apply_Exist_ERROR("100000", "已申请过,不能重复申请"),
    Project_Follow_Exist_ERROR("100001", "已关注项目,不能重复关注"),
    User_Follow_Exist_ERROR("100002", "已关注投资人,不能重复关注"),
    ;

    public final String code;
    public final String desc;

    ExceptionTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
