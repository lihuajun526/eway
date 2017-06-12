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
    <title>我的金融顾问</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
    <script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
</head>
<body>
<div>
    <%
        if (projectList == null || projectList.size() == 0) {
    %>
    <div class="wx-callmmone"><img src="<%=appPath%>/images/wtwx-icon54.png" width="212" height="152"/><span>您尚未创建项目，所以暂无金融顾问，请在电脑端创建项目</span>
    </div>
    <%
    } else {
    %>
    <ul class="wx-call-lst2">
        <%
            boolean isFirst = true;
            for (Project project : projectList) {
                String title = project.getTitle();
                title = title.length() >= 10 ? title.substring(0, 9) + "..." : title;
                if (isFirst) {
                    projectid = project.getId();
        %>
        <li onclick="listByPro(this,<%=project.getId()%>)" class="on"><a><%=title%>
        </a><span></span></li>
        <%
                isFirst = false;
                continue;
            }
        %>
        <li onclick="listByPro(this,<%=project.getId()%>)"><a><%=title%>
        </a></li>
        <%
        %><%
        }
    %>
    </ul>
    <div class="wx-call-tit">金融顾问</div>
    <div id="advisers"></div>
    <script>
        $("#advisers").load("<%=appPath%>/user/center/do/advisers/<%=projectid%>");
        function listByPro(obj, id) {
            $(obj).parent().children('li').each(function () {
                $(this).removeClass("on");
            });
            $(obj).attr("class", "on");
            $("#advisers").load("<%=appPath%>/user/center/do/advisers/" + id);
        }
    </script>
    <%
        }
    %>
</div>
</body>
</html>



