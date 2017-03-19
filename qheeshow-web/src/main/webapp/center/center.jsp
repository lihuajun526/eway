<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.User" %>
<%
    User loginUser = (User)session.getAttribute("loginUser");
%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title><%=Config.get("app.name")%>--个人中心</title>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <script src="/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="g-proj">
    <div class="g-conter">
        <!--*************************left************************-->
        <div class="g-mg-l">
            <div class="g-mg-lone">
                <ul class="g-mg-lonelst">
                    <li class="on1">
                        <img src="images/bg-tzr.png" width="90" height="90"/>
                        <a href="#" class="camera"><img src="images/wt-icon14.png" width="21" height="21"/></a>
                    </li>
                    <li class="on2"><%=loginUser.getName()%></li>
                    <li class="on3"><a href="#">申请认证</a></li>
                </ul>
            </div>
            <div class="g-mg-ltwo">
                <ul class="g-mg-ltwolst">
                    <li><a href="#">项目管理</a></li>
                    <li><a href="#">企业服务</a></li>
                    <li><a href="#">项目管理</a></li>
                    <li class="on"><a href="#">消息</a></li>
                </ul>
            </div>
        </div>
        <div id="content" class="g-mg-r"></div>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</html>
