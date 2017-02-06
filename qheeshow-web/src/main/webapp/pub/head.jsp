<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loginUser = session.getAttribute("loginUser") == null ? null : (User) session.getAttribute("loginUser");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="/index.jsp">首页</a>&nbsp;
    <%
        if (loginUser == null) {
    %><a href="/user/login.jsp">登录</a>&nbsp;
    <a href="/user/regist.jsp">注册</a><%
} else {
%>
    <%=loginUser.getName()%>
    <%
        }
    %>

</div>
</body>
</html>
