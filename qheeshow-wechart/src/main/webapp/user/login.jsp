<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title><%=Config.get("app.name")%>-登录</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
</head>
<body class="wtwx-warp">
<%@include file="../pub/head.jsp" %>
<div>
    <form id="loginForm">
        <ul class="wx-buycl-3">
            <li>
                <div class="wx-buycl-1">手机号码</div>
                <div class="wx-buycl-2"><input id="mobile" name="mobile" placeholder="请输入手机号码" class="wx-buycl-ipt"/>
                </div>
            </li>
            <li>
                <div class="wx-buycl-1">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</div>
                <div class="wx-buycl-2"><input type="password" id="password" name="password" placeholder="请输入密码" class="wx-buycl-ipt"/>
                </div>
            </li>
        </ul>
        <div class="wx-invest-btn3"><a onclick="login()">登录</a></div>
    </form>
</div>
</body>
<script>
    function login() {
        var mobile = $("#mobile").val();
        var password = $("#password").val();
        if (isEmpty(mobile)) {
            openTip({'message': '对不起，请输入手机号', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        if (isEmpty(password)) {
            openTip({'message': '对不起，请输入密码', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        $.post("<%=appPath%>/user/login", $('#loginForm').serialize(), function (result) {
            openTip(result);
        }, "json");
    }
</script>
</html>
