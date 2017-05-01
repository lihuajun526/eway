<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    String flag = "1";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--密码重置</title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/images/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div class="g-lgin">
    <div class="g-lgin-m">
        <div class="g-lgin-warp">

            <div class="g-lgin-radius-top"></div>
            <form id="resetPwdForm">
                <div class="g-lgin-cnt3">
                    <div class="g-lgin-tit">找回密码</div>
                    <ul class="g-lgin-lst">
                        <li><input id="mobile" name="mobile" type="text" class="g-lgin-ipt2" placeholder="手机号"/><em
                                class="g-lgin-left-top"></em><em
                                class="g-lgin-right-top"></em><em class="g-lgin-right-bottom"></em><em
                                class="g-lgin-left-bottom"></em></li>
                        <li><input id="smsCode" name="smsCode" type="text" class="g-lgin-ipt2"
                                   placeholder="手机验证码"/><span><a id="counter"
                                                                 onclick="getSmsCode();">获取验证码</a></span>
                            <%--<em class="g-lgin-left-top"></em><em class="g-lgin1-right-top"></em><em
                                    class="g-lgin1-right-bottom"></em><em class="g-lgin-left-bottom"></em>--%>
                        </li>
                        <li><input id="password" name="password" type="password" class="g-lgin-ipt2"
                                   placeholder="请输入新密码"/><em
                                class="g-lgin-left-top"></em><em class="g-lgin-right-top"></em><em
                                class="g-lgin-right-bottom"></em><em class="g-lgin-left-bottom"></em></li>
                        <li><input id="rePassword" name="rePassword" type="password" class="g-lgin-ipt2"
                                   placeholder="再次输入密码"/><em
                                class="g-lgin-left-top"></em><em class="g-lgin-right-top"></em><em
                                class="g-lgin-right-bottom"></em><em class="g-lgin-left-bottom"></em></li>
                    </ul>
                    <div id="tips" class="g-noair" style="display: none;"></div>
                    <div class="g-pw-btnw2"><input onclick="resetPwd();" type="button" value="确 定" class="g-lgin-btn">
                    </div>
                </div>
            </form>
            <div class="g-lgin-radius-bottom"></div>
        </div>
    </div>
</div>
<%@include file="../pub/foot.jsp" %>
</body>
<script type="text/javascript">
    function resetPwd() {
        if (!validate())
            return;
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '<%=appPath%>/user/password/reset',
            data: $('#resetPwdForm').serialize(),
            success: function (result) {
                if (!result.data) {
                    xalert(result.message);
                    return;
                }
                window.location.href = "./login.jsp";
            }
        });
    }
    function validate() {
        var mobile = $("#mobile").val();
        var smsCode = $("#smsCode").val();
        var password = $("#password").val();
        var rePasssword = $("#rePassword").val();
        if (!mobile.length) {
            tip("请输入手机号");
            return false;
        }
        if (!smsCode.length) {
            tip("请输入验证码");
            return false;
        }
        if (!password.length || !rePasssword.length) {
            tip("请输入密码");
            return false;
        }
        return true;
    }
    var timer = 0;
    var counter = 0;
    //获取短信验证码
    function getSmsCode() {
        if (counter > 0)
            return;
        var mobile = $("#mobile").val();
        if (!mobile.length) {
            tip("请输入手机号");
            return;
        }
        $.get("<%=appPath%>/sms/get/" + mobile + "?type=resetPwd", function (result) {
            if (result.data) {
                xalert("发送成功");
                $("#counter").parent().attr("class", "g-ove");
                counter = 45;
                timer = window.setInterval(showTime, 1000);
            } else
                xalert(result.message);
        }, "json");
    }
    function showTime() {
        if (counter > 0) {
            $("#counter").html("倒计时" + counter + "秒");
            counter--;
        } else {
            window.clearInterval(timer);
            $("#counter").parent().removeAttr("class");
            $("#counter").html("获取验证码");
        }

    }
    function tip(value) {
        $("#tips").html(value);
        $("#tips").show();
    }
    $('#rePassword').keydown(function (e) {
        if (e.keyCode == 13) {
            resetPwd();
        }
    });
</script>
</html>
