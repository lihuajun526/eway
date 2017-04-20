<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%
    String appPath = Config.get("app.path");
    User loginUser = (User) session.getAttribute("loginUser");
    String flag = "1";
%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--个人中心</title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script src="<%=appPath%>/jquery/ajaxfileupload.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp"%>
<input type="file" id="photoFile" name="photoFile" style="display: none;"
       onchange="uploadImage('photoFile','photoImg')"/>

<div class="g-proj">
    <div class="g-conter">
        <div class="g-mg-l">
            <div class="g-mg-lone">
                <ul class="g-mg-lonelst">
                    <li class="on1">
                        <a onclick="selectFile('photoFile');">
                            <img id="photoImg"
                                 src="<%=StringUtils.isEmpty(loginUser.getPhoto())?appPath+"/images/bg-new1.png":loginUser.getPhoto()%>"
                                 width="90" height="90"/>
                        </a>
                        <a class="camera"><img src="<%=appPath%>/images/wt-icon14.png" width="21" height="21"/></a>
                    </li>
                    <li class="on2"><%=loginUser.getName()%>
                    </li>
                    <%
                        if (loginUser.getRoleid().intValue() == 20) {//创业者
                            if (StringUtils.isEmpty(loginUser.getPhoto())) {
                    %>
                    <li class="on3"><a onclick="selectFile('photoFile');">上传头像</a></li>
                    <%
                    } else {
                    %>
                    <li class="on3"><a onclick="selectFile('photoFile');">修改头像</a></li>
                    <%
                        }
                    } else if (loginUser.getRoleid().intValue() >= 30 && loginUser.getRoleid().intValue() < 40) {//投资人
                        if (request.getAttribute("investor") == null) {
                    %>
                    <li class="on3"><a href="<%=appPath%>/investor/<%=loginUser.getId()%>/add/edit/1/auth">完善信息</a></li>
                    <%
                    } else if (loginUser.getRoleid().intValue() == 30) {
                    %>
                    <li class="on3"><a href="<%=appPath%>/investor/<%=loginUser.getId()%>/add/edit/2/auth">申请认证</a></li>
                    <%
                    } else {
                    %>
                    <li class="on3"><a href="<%=appPath%>/investor/<%=loginUser.getId()%>/add/edit/1/auth">修改信息</a></li>
                    <%
                            }
                        }
                    %>
                </ul>
            </div>
            <div class="g-mg-ltwo">
                <ul class="g-mg-ltwolst">
                    <li style="cursor: pointer" onclick="menu(this,'<%=appPath%>/center/project/1/5/1');" class="on">
                        项目管理
                    </li>
                    <%
                        if (loginUser.getRoleid() == 20) {
                    %>
                    <li style="cursor: pointer" onclick="menu(this,'<%=appPath%>/center/myservices/0');">购买的服务</li>
                    <%
                        }
                    %>
                    <li style="cursor: pointer" onclick="menu(this,'<%=appPath%>/center/message/list/1/1/5');">消息</li>
                    <li style="cursor: pointer" onclick="menu(this,'<%=appPath%>/center/user_setting.jsp');">个人设置</li>
                </ul>
            </div>
        </div>
        <div id="content" class="g-mg-r"></div>
    </div>
</div>
<%@include file="../pub/foot.jsp"%>
<script>
    function menu(obj, url) {
        if ($(obj).attr("class") == "on")
            return;
        $(obj).parent().children('li').each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
        load(url);
    }
    function load(url) {
        $("#content").load(url);
    }
    load("<%=appPath%>/center/project/1/1/1");

    function selectFile(id) {
        $('#' + id).click();
    }
    //上传logo
    function uploadImage(fileid, imgid) {
        var file = $('#' + fileid);
        if (!file || !file.val())
            return;
        var patn = /\.jpg$|\.jpeg$|\.png$|\.gif$/i;
        if (!patn.test(file.val())) {
            alert("请选择图片文件");
            return;
        }
        $.ajaxFileUpload({
                    url: '<%=appPath%>/image/upload', //用于文件上传的服务器端请求地址
                    type: 'post',
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: fileid, //文件上传域的ID
                    dataType: 'json', //返回值类型 一般设置为json
                    success: function (result) {  //服务器成功响应处理函数
                        if (result.code == -1) {
                            alert(result.message);
                            return;
                        }
                        $('#' + imgid).attr("src", result.data.path);
                        $.get("<%=appPath%>/user/photo/upload/authj?path=" + result.data.path, function (result) {

                        }, "json");
                    }
                }
        );
    }
    function modifyName(obj) {
        if ("修改" == $(obj).val()) {
            $(obj).val("确定");
            $("#name_").hide();
            $("#name").show();
            $("#name").focus();
        } else if ("确定" == $(obj).val()) {
            var name = $("#name").val();
            if (!name.length) {
                alert("真实姓名不能为空");
                return;
            }
            $.ajax({
                type: 'POST',
                url: "<%=appPath%>/user/name/modify/authj",
                data: {'name': name},
                success: function (result) {
                    if (result.code == -1) {
                        alert(result.message);
                        return;
                    }
                    $(obj).val("修改");
                    $("#name").hide();
                    $("#name_").html(name);
                    $("#name_").show();
                },
                dataType: "json"
            });
        }
    }

    function modifyEmail(obj) {
        if ("修改" == $(obj).val()) {
            $(obj).val("确定");
            $("#email_").hide();
            $("#email").show();
            $("#email").focus();
        } else if ("确定" == $(obj).val()) {
            var email = $("#email").val();
            if (!email.length) {
                alert("email不能为空");
                return;
            }
            $.ajax({
                type: 'POST',
                url: "<%=appPath%>/user/email/modify/authj",
                data: {'email': email},
                success: function (result) {
                    if (result.code == -1) {
                        alert(result.message);
                        return;
                    }
                    $(obj).val("修改");
                    $("#email").hide();
                    $("#email_").html(email);
                    $("#email_").show();
                },
                dataType: "json"
            });
        }
    }

    function modifyPwd(obj) {
        if ("修改" == $(obj).val()) {
            $(obj).val("确定");
            $("#password_").hide();
            $("#password").show();
            $("#password").focus();
        } else if ("确定" == $(obj).val()) {
            var password = $("#password").val();
            if (!password.length) {
                alert("密码不能为空");
                return;
            }
            if (password.length < 8) {
                alert("密码至少8位");
                return;
            }
            $.ajax({
                type: 'POST',
                url: "<%=appPath%>/user/password/modify/authj",
                data: {'password': password},
                success: function (result) {
                    if (result.code == -1) {
                        alert(result.message);
                        return;
                    }
                    $(obj).val("修改");
                    $("#password").hide();
                    $("#password_").html("********");
                    $("#password_").show();
                },
                dataType: "json"
            });
        }
    }
</script>
</html>
