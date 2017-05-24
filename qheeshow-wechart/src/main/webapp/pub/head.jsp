<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loginUser = session.getAttribute("loginUser") == null ? null : (User) session.getAttribute("loginUser");
%>
<div id="tip" style="display: none;">
    <div class="shade"></div>
    <div class="wtwx-project-radius">
        <a onclick="closeTip()" class="wtwx-project-radius-close"></a>

        <h1>温馨提示</h1>

        <h3 id="t_message"></h3>

        <div id="t_action" class="wtwx-project-radius-btn1"></div>
    </div>
    <script>
        function closeTip() {
            $("#tip").hide();
        }
        function openTip(result) {
            $("#t_message").html(result.message);
            if (result.data.link == "close") {
                $("#t_action").html("<a onclick='closeTip()'>" + result.data.action + "</a>");
            } else {
                $("#t_action").html("<a href='" + result.data.link + "'>" + result.data.action + "</a>");
            }
            $("#tip").show();
        }
    </script>
</div>
