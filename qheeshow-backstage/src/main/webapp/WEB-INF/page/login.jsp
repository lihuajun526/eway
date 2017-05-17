<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String appPath = Config.get("app.path");
%>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>梧桐e路-管理员登陆</title>
    <link rel="stylesheet" href="<%=appPath%>/css/reset.css" />
    <link rel="stylesheet" href="<%=appPath%>/css/login.css" />
    <script type="text/javascript" src="<%=appPath%>/jquery-easyui-1.4.5/jquery.min.js"></script>

</head>
<body>
<div class="page">
    <div class="loginwarrp">
        <div class="logo">梧桐e路-管理员登陆</div>
        <div class="login_form">
            <form id="Login">
                <li class="login-item">
                    <span>用户名：</span>
                    <input type="text" id="username" name="name" class="login_input" >
                    <span id="count-msg" class="error"></span>
                </li>
                <li class="login-item">
                    <span>密　码：</span>
                    <input type="password" id="password" name="password" class="login_input" >
                    <span id="password-msg" class="error"></span>
                </li>
                <li class="login-sub">
                    <input style="cursor: pointer;" type="button" value="登录" onclick="login();"/>
                    <input style="cursor: pointer;" type="reset" name="Reset" value="重置" />
                </li>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    window.onload = function() {
        var config = {
            vx : 4,
            vy : 4,
            height : 2,
            width : 2,
            count : 100,
            color : "121, 162, 185",
            stroke : "100, 200, 180",
            dist : 6000,
            e_dist : 20000,
            max_conn : 10
        }
        CanvasParticle(config);
    }
    function login() {
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '<%=appPath%>/user/login',
            data: $('#Login').serialize(),
            success: function (result) {
                if (!result.data) {
                    alert(result.message);
                    return;
                }
                window.location.href = "<%=appPath%>/index";
            }
        });
    }
</script>
<script type="text/javascript" src="<%=appPath%>/js/canvas-particle.js"></script>
</body>
</html>
