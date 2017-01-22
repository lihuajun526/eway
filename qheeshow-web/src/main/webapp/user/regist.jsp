<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <script type="text/javascript" src="/statics/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="/statics/jquery/jquery-validate.js"></script>
    <script type="text/javascript" src="/statics/js/util.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<form id="registForm">
    <table>
        <tr>
            <td>手机号</td>
            <td><input id="mobile" name="mobile" data-tip="请输入您的手机号码" class="required" data-valid="isNonEmpty||isMobile"
                       data-error="手机号码不能为空||手机号码不正确"/></td>
        </tr>
        <tr>
            <td>真实姓名</td>
            <td><input name="name" data-tip="请输入您的真实姓名" class="required" data-valid="isNonEmpty"
                       data-error="真实姓名不能为空"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input name="email" data-tip="请输入您的邮箱" class="required" data-valid="isNonEmpty||isEmail"
                       data-error="email不能为空||邮箱格式不正确"/></td>
        </tr>
        <tr>
            <td>手机验证码</td>
            <td><input name="smsCode" class="required" data-valid="isNonEmpty"
                       data-error="验证码不能为空"/>
                <button type="button" onclick="getSmsCode()">获取短信验证码</button>
            </td>
        </tr>
        <tr>
            <td>身份</td>
            <td>
                <input type="radio" name="roleid" value="3" checked>创业者
                <input type="radio" name="roleid" value="4">投资人
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password" class="required" data-valid="isNonEmpty||minLength:8"
                       data-error="密码不能为空||密码至少8个字符"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="button" onclick="regist()">注册</button>
            </td>
        </tr>
    </table>
</form>
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
        $.get("/sms/get/" + mobile, function (result) {
            alert(result.message);
        }, "json");
    }
    //提交表单
    function regist() {
        if (!$('#registForm').validate('submitValidate'))
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
                window.location.href = "../project/project_create.jsp";
            }
        });
    }
</script>
</html>
