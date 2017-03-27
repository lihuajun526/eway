<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loginUser = session.getAttribute("loginUser") == null ? null : (User) session.getAttribute("loginUser");
    String flag = request.getParameter("flag");
    flag = StringUtils.isEmpty(flag) ? "1" : flag;
%>
<html>
<body>
<div class="g-top">
    <div class="g-logo"></div>
    <div class="g-link">
        <ul>
            <li<%="1".equals(flag) ? " class='on'" : ""%>><a href="/index">首页</a></li>
            <li<%="2".equals(flag) ? " class='on'" : ""%>><a href="/project/list">项目</a></li>
            <li<%="3".equals(flag) ? " class='on'" : ""%>><a href="/investor/list">投资人</a></li>
            <li<%="4".equals(flag) ? " class='on'" : ""%>><a href="/activity/list">活动</a></li>
        </ul>
    </div>
    <div class="g-login">
        <ul>
            <%
                if (loginUser != null) {
            %>
            <li class="on5"><a
                    href="/center/index"><%=StringUtils.isEmpty(loginUser.getName()) ? loginUser.getMobile() : loginUser.getName()%>
            </a></li>
            <li class="on4"><a href="/center/index"><img src="/images/wt-top3.png" width="30" height="30"/></a></li>
            <li class="on1"><a href="/goods/list/0">购买服务</a></li>
            <li class="on2"><a href="/project/0/add/edit/1">创建项目</a></li>
            <%
            } else {
            %>
            <li class="on1"><a href="/goods/list/0">购买服务</a></li>
            <li class="on2"><a href="/project/0/add/edit/1">创建项目</a></li>
            <li class="on3"><a href="/user/login.jsp"><img src="/images/wt-top3.png"/></a></li>
            <%
                }
            %>
        </ul>
    </div>
</div>
</body>
</html>
