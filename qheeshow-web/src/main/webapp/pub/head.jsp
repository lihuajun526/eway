<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loginUser = session.getAttribute("loginUser") == null ? null : (User) session.getAttribute("loginUser");
%>
<html>
<body>
<div class="g-top">
    <div class="g-logo"></div>
    <div class="g-link">
        <ul>
            <li class="on"><a href="#">首页</a></li>
            <li><a href="#">项目</a></li>
            <li><a href="#">投资人</a></li>
            <li><a href="#">活动</a></li>
        </ul>
    </div>
    <div class="g-login">
        <ul>
            <li class="on1"><a href="#">购买服务</a></li>
            <li class="on2"><a href="#">创建项目</a></li>
            <li class="on3"><a href="#"><img src="images/wt-top3.png"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>
