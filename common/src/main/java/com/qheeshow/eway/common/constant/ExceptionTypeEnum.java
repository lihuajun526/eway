package com.qheeshow.eway.common.constant;

/**
 * @author: Zhou Xuanang
 * @Date: 09:59 2016/11/10.
 */
public enum ExceptionTypeEnum {

    Project_Adviser_Apply_Exist_ERROR("100000", "已申请过,不能重复申请"),
    Project_Follow_Exist_ERROR("100001", "已关注项目,不能重复关注"),
    User_Follow_Exist_ERROR("100002", "已关注投资人,不能重复关注"),
    Investor_Not_Auth_ERROR("100003", "投资人尚未认证"),
    Project_Adviser_Full_ERROR("100004", "顾问名额已满"),
    Is_Not_Adviser_ERROR("100005", "非投资人"),
    Get_Mixcom_No_ERROR("100006", "获得米糠小号失败"),
    Bound_Mixcom_No_ERROR("100006", "绑定米糠小号失败"),
    Bound_Map_Not_Exist_ERROR("100007", "绑定关系不存在"),
    Calling_Not_Exist_ERROR("100008", "主叫号码不存在"),
    UnBound_Mixcom_No_ERROR("100006", "解绑米糠小号失败"),
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
