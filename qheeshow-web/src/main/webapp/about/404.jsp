<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    String flag = "1";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>
    </title>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
</head>
<body>
<%@include file="../pub/head.jsp"%>
<div class="g-proj">
    <div class="g-pser">
        <div class="wt-404"><img src="<%=appPath%>/images/wt-404-bg.png"/></div>
        <div class="wt-404-ct">哎呀! 您访问的页面出错啦…</div>
        <div class="wt-404-ct2">
            <a onclick="javascript:location.reload();" class="on1">刷新页面</a>
            <a href="<%=appPath%>/index" class="on2">返回首页</a>
        </div>
    </div>
</div>
<%@include file="../pub/foot.jsp"%>
</body>
</html>
