<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    String flag = "1";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--登录</title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/js/util.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp"%>
<div class="g-lgin">
    <form id="loginForm">
        <div class="g-lgin-m">
            <ul class="g-lgin-t">
                <li class="on1"><a>登录</a></li>
                <li class="on2"><a href="regist.jsp" style="text-decoration:none">注册</a></li>
            </ul>
            <div class="g-lgin-cnt">
                <ul class="g-lgin-lst">
                    <li>
                        <input id="mobile" name="mobile" class="g-lgin-ipt" placeholder="请输入手机号"/>
                        <em class="g-lgin2-left-top"></em><em class="g-lgin2-right-top"></em><em class="g-lgin-right-bottom"></em><em class="g-lgin-left-bottom"></em>
                    </li>
                    <li>
                        <input id="password" name="password" type="password" class="g-lgin-ipt" placeholder="请输入密码"/>
                        <em class="g-lgin2-left-top"></em><em class="g-lgin2-right-top"></em><em class="g-lgin-right-bottom"></em><em class="g-lgin-left-bottom"></em>
                    </li>
                </ul>
                <ul class="g-lgin-lst2">
                    <li class="on1"><input name="remind" type="checkbox" value="1"><span>记住密码</span></li>
                    <li class="on2"><a href="#">找回密码</a></li>
                </ul>
                <div class="clear"></div>
                <div id="tips" class="g-noair2" style="display: none;"></div>
                <div class="g-pw-btnw"><input type="button" value="登  录" class="g-lgin-btn" onclick="login();"></div>
                <div class="g-pw-weixin"><a onclick="weChatLogin()">微信快速登录</a></div>
            </div>
        </div>
    </form>
</div>
<%@include file="../pub/foot.jsp"%>
</body>
<script type="text/javascript">
    function showTip(value){
        $("#tips").html(value);
        $("#tips").show();
    }
    function login() {
        var mobile = $("#mobile").val();
        var password = $("#password").val();
        if (!mobile.length || !password.length) {
            showTip("账号和密码不能为空");
            return;
        }
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '<%=appPath%>/user/login',
            data: $('#loginForm').serialize(),
            success: function (result) {
                if (!result.success) {
                    showTip(result.data);
                    return;
                }
                window.location.href = "<%=appPath%>/index";
            }
        });
    }
    function weChatLogin() {
//     	var webAppId = "wx268fcfe924dcb171";
//     	var resturUrl = "http://localhost:8080/weChat/getUserMessage.html";
        var webAppId = "wx268fcfe924dcb171";
        var resturUrl = "https://www.zhihu.com";
        var href = "https://open.weixin.qq.com/connect/qrconnect?appid=" + webAppId;
        href += "&redirect_uri=" + encodeURIComponent(resturUrl);
        href += "&response_type=code" +
                "&scope=snsapi_login" +
                "#wechat_redirect";
//     	location.href = href;
        window.open(href, "newwindow", "height=550, width=600, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
    }
    $('#password').keydown(function (e) {
        if (e.keyCode == 13) {
            login();
        }
    });
</script>
</html>
