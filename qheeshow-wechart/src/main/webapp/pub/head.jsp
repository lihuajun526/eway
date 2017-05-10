<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loginUser = session.getAttribute("loginUser") == null ? null : (User) session.getAttribute("loginUser");
%>
<%--<div class="wtwx-top">
    <a href="#" class="wtwx-top-l">返回</a>
    <a href="#" class="wtwx-top-r"></a>
    项目列表
</div>--%>
