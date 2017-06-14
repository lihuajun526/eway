<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="java.util.List" %>
<%
    String appPath = Config.get("app.path");
    List<Project> projectList = (List<Project>) request.getAttribute("projects");
    Integer projectid = 0;
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>购买的服务</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
    <script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
</head>
<body class="wtwx-warp3">
<%
    if (projectList == null || projectList.size() == 0) {
%>
<div class="wx-callmmone"><img src="<%=appPath%>/images/wtwx-icon54.png" width="212" height="152"/><span>您未购买任何服务</span>
</div>
<%
} else {
%>
<div>
    <ul class="wx-call-lst2">
        <%
            boolean isFirst = true;
            for (Project project : projectList) {
                String title = project.getTitle();
                title = title.length() > 10 ? title.substring(0, 10) + "..." : title;
                if (isFirst) {
                    %><li onclick="listByPro(this,<%=project.getId()%>)" class="on"><a href="#"><%=project.getTitle()%></a><span></span></li><%
                    projectid = project.getId();
                    isFirst = false;
                    continue;
                }
                %><li onclick="listByPro(this,<%=project.getId()%>)"><a><%=title%></a></li><%
            }
        %>
    </ul>
    <div id="srvs"></div>
</div>
<%
    }
%>
</div>
</body>
<script>
    $("#srvs").load("<%=appPath%>/user/center/do/services/<%=projectid%>");
    function listByPro(obj, id) {
        $(obj).parent().children('li').each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
        $("#srvs").load("<%=appPath%>/user/center/do/services/" + id);
    }
</script>
</html>



