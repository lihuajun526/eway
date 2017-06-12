<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title></title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/dropload.css"/>
</head>
<body>
<div>
    <ul class="wtwx-mgement-t">
        <li onclick="setType(this,'project_suggest_list.html')" class="on"><a href="#">平台推荐</a>
        </li>
        <li onclick="setType(this,'project_follow_list.html')"><a href="#">关注的项目</a></li>
    </ul>
    <div id="container"></div>
    <div id="more" class='dropload-refresh'></div>
</div>
</body>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script type="text/javascript" src="<%=appPath%>/dot/doT.min.js"></script>
<script type="text/javascript" src="<%=appPath%>/js/config.js"></script>
<script>
    function setType(o, url) {
        $(o).parent().children('li').each(function () {
            $(this).removeClass("on");
        });
        $(o).attr("class", "on");
        $('#container').load(url);
    }
    $('#container').load("project_suggest_list.html");
</script>
</html>


