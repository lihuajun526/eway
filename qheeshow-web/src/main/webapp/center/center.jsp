<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.User" %>
<%
    User loginUser = (User) session.getAttribute("loginUser");

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
        <div class="g-mg-l">
            <div class="g-mg-lone">
                <ul class="g-mg-lonelst">
                    <li class="on1">
                        <img src="/images/bg-tzr.png" width="90" height="90"/>
                        <a href="#" class="camera"><img src="/images/wt-icon14.png" width="21" height="21"/></a>
                    </li>
                    <li class="on2"><%=loginUser.getName()%>
                    </li>
                    <%
                        if (loginUser.getRoleid() == 4) {
                    %>
                    <li class="on3"><a href="/investor/<%=loginUser.getId()%>/add/edit/2">申请认证</a></li>
                    <%
                        }
                    %>
                </ul>
            </div>
            <div class="g-mg-ltwo">
                <ul class="g-mg-ltwolst">
                    <li onclick="menu(this,'/center/project/mgr/5/1');" class="on">项目管理</li>
                    <%
                        if (loginUser.getRoleid() == 3) {
                    %>
                    <li onclick="menu(this,'');">购买的服务</li>
                    <%
                        }
                    %>
                    <li onclick="menu(this,'/center/message/list/1/1/5');">消息</li>
                    <li onclick="menu(this,'');">个人设置</li>
                </ul>
            </div>
        </div>
        <div id="content" class="g-mg-r"></div>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
<script>
    function menu(obj) {
        if ($(obj).attr("class") == "on")
            return;
        $(obj).parent().children('li').each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
    }
    $("#content").load("/center/project/1/1/1");
    function load(url) {
        $("#content").load(url);
    }

</script>
</html>
