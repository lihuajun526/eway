<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loginUser = (User) session.getAttribute("loginUser");
    List<Project> projects = (List<Project>) request.getAttribute("projects");
    Integer projectid = (Integer) request.getAttribute("projectid");
    String appPath = Config.get("app.path");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/dropload.css"/>
    <title>购买套餐</title>
</head>
<body class="wtxt-package">
<div>
    <ul class="wtwx-package-lst">
        <%
            if (projects.size() == 0) {
        %>请先登录梧桐e路(http://www.qheeshow.com)电脑版创建项目<%
    } else {
        boolean isFirst = true;
        for (Project project : projects) {
            if (projectid.intValue() == 0 && isFirst) {
                isFirst = false;
    %><a>
        <li class="on1"><img src="<%=project.getLogo()%>" width="100%"/><span><%=project.getTitle()%></span></li>
    </a><%
            continue;
        }
        if (project.getId().intValue() == projectid.intValue()) {
    %><a>
        <li class="on1"><img src="<%=project.getLogo()%>" width="100%"/><span><%=project.getTitle()%></span></li>
    </a><%
            continue;
        }
    %><a onclick="checkPro()">
        <li><img src="<%=project.getLogo()%>" width="100%"/><span><%=project.getTitle()%></span></li>
    </a><%
            }
        }
    %>
    </ul>
    <div class="wtwx-package-left">
        <ul id="goodses" class="wtwx-package-left-lst">
            <li class="on" onclick="load(this,'../../t_1.jsp')"><a>套餐一</a></li>
            <li onclick="load(this,'../../t_1.jsp')"><a>套餐二</a></li>
            <li onclick="load(this,'../../t_1.jsp')"><a>套餐三</a></li>
            <li onclick="load(this,'../../t_1.jsp')"><a>套餐四</a></li>
        </ul>
    </div>
    <div id="items" class="wtwx-package-right"></div>
</div>
<div class="wtwx-package-menu">
    共计：23567元
    <a href="#" class="wtwx-package-menu-pay">立即付款</a>
    <div class="wtwx-package-menu-cart">
        <a href="#">4</a>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
    function load(obj, url) {
        $("#goodses>li").each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
        $("#items").load(url);
    }
    $("#items").load("../../t_1.jsp");
</script>
</html>

