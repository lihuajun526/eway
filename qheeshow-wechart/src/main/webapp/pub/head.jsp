<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loginUser = session.getAttribute("loginUser") == null ? null : (User) session.getAttribute("loginUser");
%>
<div id="tip">
    <div class="shade"></div>
    <div class="wtwx-project-radius">
        <a onclick="closeTip()" class="wtwx-project-radius-close"></a>
        <h1>温馨提示</h1>
        <h3 id="message"></h3>
        <div id="action" class="wtwx-project-radius-btn1"><a href="#">马上认证</a></div>
    </div>
    <script>function closeTip(){$("#tip").hide();}</script>
</div>
