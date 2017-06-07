<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title><%=Config.get("app.name")%>-投资人认证</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
</head>
<body class="wtwx-warp">
<%@include file="../pub/head.jsp" %>
<div>
    <form id="authForm">
        <input id="businessCardPositive" name="businessCardPositive" type="hidden"/>
        <input id="businessCardOpposite" name="businessCardOpposite" type="hidden"/>
        <ul class="wx-buycl">
            <li>
                <div class="wx-buycl-1">手机号码</div>
                <div class="wx-buycl-2">
                    <input id="mobile" name="mobile" placeholder="请输入手机号码" class="wx-buycl-ipt"/>
                </div>
            </li>
            <li>
                <div class="wx-buycl-1">短信验证码</div>
                <div class="wx-buycl-2">
                    <input id="smsCode" name="smsCode" placeholder="输入验证码" class="wx-buycl-ipt"/>
                    <a id="counter" onclick="getSmsCode()" class="wx-buycl-numb">获取验证码</a>
                </div>
            </li>
        </ul>
        <div class="wx-invest">
            添加个人名片正面
            <input id="positive" name="positive" type='file' unselectable="on" class="on1"
                   onchange="upload('positive','businessCardPositive')"/>
            <img id="positive_"/>
        </div>
        <div class="wx-invest">
            添加个人名片背面
            <input id="opposite" name="opposite" type='file' unselectable="on" class="on1"
                   onchange="upload('opposite','businessCardOpposite')"/>
            <img id="opposite_"/>
        </div>
        <div class="wx-invest-btn"><a onclick="auth()">申请认证</a></div>
    </form>
</div>
</body>
<script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.10.2.min.js"></script>
<script src="<%=appPath%>/jquery/jquery-form.js"></script>
<script>
    //上传logo
    function upload(fileid, hiddenid) {
        var file = $('#' + fileid);
        if (!file || !file.val())
            return;
        var patn = /\.jpg$|\.jpeg$|\.png$|\.gif$/i;
        if (!patn.test(file.val())) {
            openTip({'message': '请选择图片文件', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        $('#' + fileid).wrap("<form id='myUpload' action='<%=appPath%>/image/upload' method='post' enctype='multipart/form-data'></form>");
        $('#myUpload').ajaxSubmit({
            dataType: 'json',
            success: function (result) {
                $('#' + fileid + "_").attr("src", result.data.path);
                $('#' + hiddenid).val(result.data.path);
                $('#' + fileid).unwrap();
            },
            error: function (xhr) {
                openTip({'message': '上传失败', 'data': {'link': 'close', 'action': '知道了'}});
                $('#' + fileid).unwrap();
            }
        });
    }
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
        $.get("<%=appPath%>/sms/get/" + mobile + "?type=auth", function (result) {
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
        var mobile = $("#mobile").val();
        var smsCode = $("#smsCode").val();
        var businessCardPositive = $("#businessCardPositive").val();
        var businessCardOpposite = $("#businessCardOpposite").val();
        if (isEmpty(mobile)) {
            openTip({'message': '对不起，请输入手机号', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        if (isEmpty(smsCode)) {
            openTip({'message': '对不起，请输入短信验证码', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        if (isEmpty(businessCardPositive)) {
            openTip({'message': '对不起，请上传名片正面', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        if (isEmpty(businessCardOpposite)) {
            openTip({'message': '对不起，请上传名片背面', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        $.post("<%=appPath%>/investor/do/auth/v_authj", $('#authForm').serialize(), function (result) {
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
