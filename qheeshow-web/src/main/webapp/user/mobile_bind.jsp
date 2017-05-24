<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    String flag = "1";
    String unionid = (String) session.getAttribute("unionid");
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--完善信息</title>
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
            <form id="bindForm">
                <input type="hidden" name="unionid" value="<%=unionid%>"/>
                <input id="roleid" name="roleid" type="hidden" value="20"/>

                <div class="g-lgin-cnt3">
                    <div class="g-lgin-tit">完善信息</div>
                    <ul class="g-lgin-lst3">
                        <li onclick="setRole(20,this);" class="on"><a>企业/创业者</a></li>
                        <li onclick="setRole(30,this);"><a>投资人</a></li>
                    </ul>
                    <ul class="g-lgin-lst">
                        <li>
                            <input id="name" name="name" type="text" class="g-lgin-ipt2" placeholder="请务必填写真实姓名"/>
                            <em class="g-lgin-left-top"></em><em class="g-lgin-right-top"></em><em
                                class="g-lgin-right-bottom"></em><em class="g-lgin-left-bottom"></em>
                        </li>
                        <li><input id="mobile" name="mobile" type="text" class="g-lgin-ipt2" placeholder="手机号"/><em
                                class="g-lgin-left-top"></em><em
                                class="g-lgin-right-top"></em><em class="g-lgin-right-bottom"></em><em
                                class="g-lgin-left-bottom"></em></li>
                        <li><input id="smsCode" name="smsCode" type="text" class="g-lgin-ipt2"
                                   placeholder="手机验证码"/><span onclick="getSmsCode();"><a id="counter">获取验证码</a></span>
                        </li>
                        <li>
                            <input id="email" name="email" type="text" class="g-lgin-ipt2" placeholder="请输入邮箱"/>
                            <em class="g-lgin-left-top"></em><em class="g-lgin-right-top"></em><em
                                class="g-lgin-right-bottom"></em><em class="g-lgin-left-bottom"></em>
                        </li>
                    </ul>
                    <div id="tips" class="g-noair" style="display: none;"></div>
                    <div class="g-pw-btnw2"><input onclick="bind();" type="button" value="确 定" class="g-lgin-btn">
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
    function bind() {
        if (!validate())
            return;
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '<%=appPath%>/user/mobile/bind',
            data: $('#bindForm').serialize(),
            success: function (result) {
                if (!result.data) {
                    xalert(result.message);
                    return;
                }
                if (result.code == 1) {
                    window.location.href = "<%=appPath%>/index";
                } else {
                    var roleid = $("#roleid").val();
                    if (roleid == 20) {
                        window.location.href = "<%=appPath%>/project/0/add/edit/1/auth";
                    } else if (roleid == 30) {
                        window.location.href = "<%=appPath%>/investor/0/add/edit/1/auth";
                    }
                }
            }
        });
    }
    function validate() {
        var name = $("#name").val();
        var mobile = $("#mobile").val();
        var smsCode = $("#smsCode").val();
        var email = $("#email").val();
        if (!name.length) {
            tip("请输入用户名");
            return false;
        }
        if (!mobile.length) {
            tip("请输入手机号");
            return false;
        }
        if (!smsCode.length) {
            tip("请输入验证码");
            return false;
        }
        if (!email.length) {
            tip("请输入邮箱");
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
        $.get("<%=appPath%>/sms/get/" + mobile + "?type=bind", function (result) {
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
    $('#email').keydown(function (e) {
        if (e.keyCode == 13) {
            resetPwd();
        }
    });
    function setRole(roleid, obj) {
        $(obj).parent().children('li').each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
        $("#roleid").val(roleid);
    }
</script>
</html>
