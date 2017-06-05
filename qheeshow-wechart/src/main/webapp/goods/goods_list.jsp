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
    boolean flag1 = (Boolean) request.getAttribute("flag1");
    boolean flag2 = (Boolean) request.getAttribute("flag2");
    boolean flag3 = (Boolean) request.getAttribute("flag3");
    boolean flag4 = (Boolean) request.getAttribute("flag4");
%>
<html>
<head>

    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>

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
            for (int i = 0; i < projects.size(); i++) {
                Project project = projects.get(i);
                if (i == 0) {
        %>
        <a>
            <li class="on1">
                <p><%=project.getTitle().length() > 12 ? project.getTitle().substring(0, 11) + "..." : project.getTitle()%>
                </p></li>
        </a>
        <%
                continue;
            }
        %>
        <a>
            <li>
                <p><%=project.getTitle().length() > 12 ? project.getTitle().substring(0, 11) + "..." : project.getTitle()%>
                </p></li>
        </a>
        <%
            }
        %>
    </ul>
    <div class="wtwx-package-prompt">提示：<%=projects.size() == 0 ? "请先登录梧桐e路电脑版创建项目" : "购买过套餐三或套餐四的用户才能购买套餐一"%>
    </div>
    <div class="wtwx-package-left">
        <ul id="goodses" class="wtwx-package-left-lst">
            <li class="on" onclick="load(this,'<%=appPath%>/goods/do/get/1/<%=flag1?1:0%>')"><a>套餐一</a></li>
            <li onclick="load(this,'<%=appPath%>/goods/do/get/2/<%=flag2?1:0%>')"><a>套餐二</a></li>
            <li onclick="load(this,'<%=appPath%>/goods/do/get/3/<%=flag3?1:0%>')"><a>套餐三</a></li>
            <li onclick="load(this,'<%=appPath%>/goods/do/get/4/<%=flag4?1:0%>')"><a>套餐四</a></li>
        </ul>
    </div>
    <div id="items" class="wtwx-package-right"></div>
</div>
<div class="wtwx-package-menu">
    共计：<span id="sum">0</span>元
    <a href="#" class="wtwx-package-menu-pay">立即付款</a>

    <div class="wtwx-package-menu-cart">
        <%--<a href="#">4</a>--%>
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
        $("#items").html("加载中...");
        $("#items").load(url);
    }
    $("#items").load("<%=appPath%>/goods/do/get/1/<%=flag1?1:0%>");
    var goods1 = 0, price1 = 0;
    var goods2 = 0, price2 = 0;
    var goods3 = 0, price3 = 0;
    var goods4 = 0, price4 = 0;
    var curGoods = 1;
    function add(o) {
        if ($(o).attr("class") == "on5")
            return;
        if (curGoods == 1) {
            goods1++;
            $("#i2").html(goods1);
        } else if (curGoods == 2) {
            goods2++;
            $("#i2").html(goods2);
            $("#i1").attr("class", "on5");
        } else if (curGoods == 3) {
            goods3++;
            $("#i2").html(goods3);
            $("#i1").attr("class", "on5");
        } else if (curGoods == 4) {
            goods4++;
            $("#i2").html(goods4);
            $("#i1").attr("class", "on5");
        }
        $("#i3").attr("class", "on3");
        sum();
    }
    function del(o) {
        if ($(o).attr("class") == "on4")
            return;
        if (curGoods == 1) {
            goods1--;
            $("#i2").html(goods1);
            if (goods1 == 0)
                $("#i3").attr("class", "on4");
        } else if (curGoods == 2) {
            goods2--;
            $("#i2").html(goods2);
            $("#i3").attr("class", "on4");
        } else if (curGoods == 3) {
            goods3--;
            $("#i2").html(goods3);
            $("#i3").attr("class", "on4");
        } else if (curGoods == 4) {
            goods4--;
            $("#i2").html(goods4);
            $("#i3").attr("class", "on4");
        }
        $("#i1").attr("class", "on1");
        sum();
    }
    function sum() {
        var sum = price1 * goods1 + price2 * goods2 + price3 * goods3 + price4 * goods4;
        $("#sum").html(sum);
    }
</script>
</html>

