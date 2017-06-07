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
    <title><%=Config.get("app.name")%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
</head>
<body class="wtwx-warp">
<%@include file="../pub/head.jsp" %>
<div>
    <form id="userForm">
        <div class="wx-buycl-topbtn">
            <a class="on1">企业家</a><a class="on2">投资人</a>
        </div>
        <ul class="wx-buycl-3">
            <li>
                <div class="wx-buycl-1">真实姓名</div>
                <div class="wx-buycl-2"><input id="name" name="name" placeholder="请输入真实姓名" class="wx-buycl-ipt"/></div>
            </li>
            <li>
                <div class="wx-buycl-1">手机号码</div>
                <div class="wx-buycl-2"><input id="mobile" name="mobile" placeholder="请输入手机号码" class="wx-buycl-ipt"/></div>
            </li>
            <li>
                <div class="wx-buycl-1">短信验证码</div>
                <div class="wx-buycl-2">
                    <input id="smsCode" name="smsCode" placeholder="输入验证码" class="wx-buycl-ipt"/>
                    <a id="counter" onclick="getSmsCode()" class="wx-buycl-numb">获取验证码</a>
                </div>
            </li>
            <li>
                <div class="wx-buycl-1">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</div>
                <div class="wx-buycl-2"><input id="email" name="email" placeholder="请输入邮箱" class="wx-buycl-ipt"/></div>
            </li>
        </ul>
        <div class="wx-invest-btn3"><a href="#">确认</a></div>
    </form>
</div>
</body>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
    var counter = 0;
    var timer = 0;
    //获取短信验证码
    function getSmsCode() {
        if (counter > 0)
            return;
        var mobile = $("#mobile").val();
        if (isEmpty(mobile)) {
            openTip({'message': '对不起，请输入手机号', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        $.get("<%=appPath%>/sms/get/" + mobile + "?type=append", function (result) {
            if (result.data) {
                openTip({'message': '发送成功', 'data': {'link': 'close', 'action': '知道了'}});
                counter = 45;
                timer = window.setInterval(showTime, 1000);
            } else
                openTip({'message': result.message, 'data': {'link': 'close', 'action': '知道了'}});
        }, "json");
    }
    function showTime() {
        if (counter > 0) {
            $("#counter").html("倒计时" + counter + "秒");
            counter--;
        } else {
            window.clearInterval(timer);
            $("#counter").html("获取验证码");
        }
    }
    function auth() {
        var name = $("#name").val();
        var mobile = $("#mobile").val();
        var smsCode = $("#smsCode").val();
        var email = $("#email").val();
        if (isEmpty(name)) {
            openTip({'message': '对不起，请输入真实姓名', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        if (isEmpty(mobile)) {
            openTip({'message': '对不起，请输入手机号', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        if (isEmpty(smsCode)) {
            openTip({'message': '对不起，请输入短信验证码', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        if (isEmpty(email)) {
            openTip({'message': '对不起，请输入邮箱', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        $.post("<%=appPath%>/user/append", $('#userForm').serialize(), function (result) {
            openTip(result);
        }, "json");
    }
    function isEmpty(str) {
        if (str == null || str == '')
            return true;
        return false;
    }

</script>
</html>
