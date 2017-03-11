<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" href="/images/animate.min.css">
    <link rel="stylesheet" href="/images/bootstrap.css">
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <script type="text/javascript" src="/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="/js/util.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="g-lgin">
    <div class="g-lgin-m">
        <ul class="g-lgin-t g-lgin-t2">
            <li class="on2"><a href="/user/login.jsp">登录</a></li>
            <li class="on1"><a>注册</a></li>
        </ul>
        <form id="registForm">
            <input id="roleid" name="roleid" type="hidden" value="3"/>

            <div class="g-lgin-cnt g-lgin-cnt2">
                <ul class="g-lgin-lst3">
                    <li><a style="cursor: pointer" onclick="setRole(3);">企业/创业者</a></li>
                    <li><a style="cursor: pointer" onclick="setRole(4);">投资人</a></li>
                </ul>
                <ul class="g-lgin-lst">
                    <li><input value="李华君" id="name" name="name" type="text" class="g-lgin-ipt2" placeholder="请输入真实姓名"/></li>
                    <li><input value="18857107097" id="mobile" name="mobile" type="text" class="g-lgin-ipt2" placeholder="请输入手机号"/></li>
                    <li><input value="515182557@qq.com" id="email" name="email" type="text" class="g-lgin-ipt2" placeholder="请输入邮箱"/></li>
                    <li><input value="1234" id="smsCode" name="smsCode" type="text" class="g-lgin-ipt2"
                               placeholder="请输入短信验证码"/><span><a
                            style="cursor: pointer" onclick="getSmsCode();">获取验证码</a></span>
                    </li>
                    <li><input value="password" id="password" name="password" type="password" class="g-lgin-ipt2" placeholder="请输入密码"/></li>
                    <li><input value="password" id="rePassword" name="rePassword" type="password" class="g-lgin-ipt2" placeholder="请再次输入密码"/>
                    </li>
                </ul>
                <div class="g-pw-btnw2"><input type="button" value="注 册" class="g-lgin-btn" onclick="regist();"></div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    //获取短信验证码
    function getSmsCode() {
        var mobile = $("#mobile").val();
        if (isEmpty(mobile)) {
            alert("请填写手机号");
            return;
        }
        $.get("/sms/get/" + mobile + "?type=regist", function (result) {
            alert(result.message);
        }, "json");
    }
    //提交表单
    function regist() {
        if (!validate())
            return;
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '/user/regist',
            data: $('#registForm').serialize(),
            success: function (result) {
                if (!result.data) {
                    alert(result.message);
                    return;
                }
                window.location.href = "/project/0/add/edit/1";
            }
        });
    }
    function setRole(roleid) {
        $("#roleid").val(roleid);
    }
    function validate() {
        var name = $("#name").val();
        var mobile = $("#mobile").val();
        var email = $("#email").val();
        var smsCode = $("#smsCode").val();
        var password = $("#password").val();
        var rePassword = $("#rePassword").val();
        if (!name.length) {
            alert("请输入用户名");
            return false;
        }
        if (!mobile.length) {
            return false;
        }
        if (!email.length) {
            return false;
        }
        if (!smsCode.length) {
            return false;
        }
        if (!password.length) {
            return false;
        }
        if (!rePassword.length) {
            return false;
        }
        if (!/(^1[3|5|8][0-9]{9}$)/.test(mobile)) {
            return false;
        }
        if (!/(^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)/.test(email)) {
            return false;
        }
        if (password != rePassword) {
            return false;
        }
        return true;
    }
</script>
</html>
