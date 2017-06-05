<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title><%=Config.get("app.name")%></title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css" />
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css" />
</head>
<body class="wtxt-pb">
<div>
    <div class="wx-404"><img src="<%=appPath%>/images/wtwx-icon50.png" width="185" height="185"/><span>哎呀! 您访问的页面不存在…</span></div>
</div>
</body>
</html>
