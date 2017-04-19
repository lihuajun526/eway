<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.User" %>
<%
    String appPath = Config.get("app.path");
    User loginUser = (User) session.getAttribute("loginUser");
%>
<div class="g-mg-rwap">

    <ul class="g-mg-rtitlst">
        <li class="on"><a href="#">个人设置</a></li>
    </ul>
    <ul class="g-mg-account">
        <li class="on1">真实姓名：</li>
        <li class="on2">
            <span id="name_" class="on1"><%=loginUser.getName()%></span>
            <input id="name" value="<%=loginUser.getName()%>" class="g-mg-account-ipt" style="display: none;"/>
        </li>
        <li class="on3"><input type="button" value="修改" class="g-mg-account-btn2" onclick="modifyName(this);"></li>
    </ul>
    <ul class="g-mg-account">
        <li class="on1">登录密码：</li>
        <li class="on2">
            <span id="password_" class="on1">********</span>
            <input id="password" type="password" class="g-mg-account-ipt" style="display: none;"/>
        </li>
        <li class="on3"><input type="button" value="修改" class="g-mg-account-btn2" onclick="modifyPwd(this);"></li>
    </ul>
    <ul class="g-mg-account">
        <li class="on1">电子邮箱：</li>
        <li class="on2">
            <span id="email_" class="on1"><%=loginUser.getEmail()%></span>
            <input id="email" value="<%=loginUser.getEmail()%>" class="g-mg-account-ipt" style="display: none;"/>
        </li>
        <li class="on3"><input type="button" value="修改" class="g-mg-account-btn2" onclick="modifyEmail(this);"></li>
    </ul>

</div>
