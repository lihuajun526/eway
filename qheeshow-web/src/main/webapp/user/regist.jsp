<%--
  Created by IntelliJ IDEA.
  User: lihuajun
  Date: 17-1-19
  Time: 上午10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <script type="text/javascript" src="/statics/jquery/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="/statics/js/util.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<form method="post" action="">
    <table>
        <tr>
            <td>手机号</td>
            <td><input id="mobile" name="mobile"/></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input name="email"/></td>
        </tr>
        <tr>
            <td>手机验证码</td>
            <td><input name="smsCode"/>
                <button type="button" onclick="getSmsCode()">获取短信验证码</button>
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input name="password" type="password"/></td>
        </tr>
        <tr>
            <td>再次输入密码</td>
            <td><input name="rePassword" type="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">注册</button>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    function getSmsCode() {
        var mobile = $("#mobile").val();
        alert(mobile);
        if (isEmpty(mobile)) {
            alert("请填写手机号");
            return;
        }
        $.get("/sms/get/" + mobile, function (result) {
            alert(result.data);
        }, "json");
    }

</script>
</html>
