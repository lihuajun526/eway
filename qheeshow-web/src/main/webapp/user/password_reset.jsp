<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
%>
<html>
<head>
    <title><%=Config.get("app.name")%>--密码重置</title>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="g-lgin">
    <div class="g-lgin-m">
        <form id="resetPwdForm">
            <div class="g-lgin-cnt3 g-lgin-cnt2">
                <div class="g-lgin-tit">找回密码</div>
                <ul class="g-lgin-lst">
                    <li><input id="mobile" name="mobile" class="g-lgin-ipt2" placeholder="手机号"/></li>
                    <li><input id="smsCode" name="smsCode" class="g-lgin-ipt2" placeholder="短信验证码"/><span><a href="#">获取验证码</a></span>
                    </li>
                    <li><input id="password" name="password" type="password" class="g-lgin-ipt2" placeholder="请输入新密码"/>
                    </li>
                    <li><input id="rePassword" name="rePassword" type="password" class="g-lgin-ipt2"
                               placeholder="请再次输入新密码"/></li>
                </ul>
                <div class="g-pw-btnw2"><input type="button" value="确 定" class="g-lgin-btn"></div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script type="text/javascript">
    function resetPwd () {
        if (!validate())
            return;
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '<%=appPath%>/user/password/reset',
            data: $('#resetPwdForm').serialize(),
            success: function (result) {
                if (!result.data) {
                    alert(result.message);
                    return;
                }
                window.location.href = "<%=appPath%>/index";
            }
        });
    }
    function validate() {
        var mobile = $("#mobile").val();
        var password = $("#password").val();
        if (!mobile.length) {
            alert("用户名不能为空");
            return false;
        }
        if (!password.length)
            return false;
        return true;
    }
</script>
</html>
