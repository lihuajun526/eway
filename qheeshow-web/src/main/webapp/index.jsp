<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    String flag = "1";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/images/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=appPath%>/js/util.js"></script>
</head>
<body>
<%@include file="pub/head.jsp"%>
<div class="g-banner">
    <div class="g-sol1">
        <ul class="g-pag">
            <li><a href="#"><img src="<%=appPath%>/images/wt-icon1.png"/></a></li>
            <li><a href="#"><img src="<%=appPath%>/images/wt-icon2.png"/></a></li>
            <li><a href="#"><img src="<%=appPath%>/images/wt-icon2.png"/></a></li>
        </ul>
    </div>
</div>
<div class="g-tit1"><a class="on">最新活动</a><a href="<%=appPath%>/goods/list/0">优惠套餐</a></div>
<div class="g-cont">
    <ul class="g-one1" id="activitys"></ul>
</div>
<div class="g-mbg">
    <h1 class="g-two-t">优秀项目推荐</h1>

    <h3 class="g-two-t2">最新活动——优惠的套餐更好的为您提供服务</h3>

    <div class="g-cont">
        <ul id="bestPros" class="g-two2"></ul>
        <div class="g-more"><a href="<%=appPath%>/project/list">More</a></div>
    </div>

</div>
<div class="g-mbg2">

    <h1 class="g-two-t">优秀案例</h1>

    <h3 class="g-two-t2">最新活动——优惠的套餐更好的为您提供服务</h3>
    <ul id="bestCases" class="g-three1"></ul>
</div>
<div class="g-ban-ad1"></div>
<div class="g-mbg3">
    <h1 class="g-two-t">优秀投资人</h1>

    <h3 class="g-two-t2">平台活跃投资人，著名投资经纪人或机构</h3>

    <div class="g-conter">
        <div id="first" class="g-fivesen"></div>
        <ul id="investors" class="g-lst1"></ul>
        <div class="g-more2"><a href="<%=appPath%>/investor/list">More</a></div>
    </div>
</div>
<%@include file="pub/foot.jsp"%>
<script>
    $.get("<%=appPath%>/index/activity/latest", function (result) {
        var data = result.data;
        for (var i = 0; i < data.length; i++) {
            var activity = data[i];
            $("#activitys").append("<a href='<%=appPath%>/activity/get/" + activity.id + "'><li><span><img src='" + activity.logo + "' width='320' height='180'/></span><h1>" + activity.title + "</h1><p>" + activity.summary + "</p><div href='<%=appPath%>/activity/get/" + activity.id + "' class='icon'><img src='<%=appPath%>/images/wt-icon4.png'/></div></li></a>");
        }
    }, "json");
    $.get("<%=appPath%>/index/project/best/suggest", function (result) {
        var data = result.data;
        for (var i = 0; i < data.length; i++) {
            var project = data[i];
            $("#bestPros").append("<a href='<%=appPath%>/project/" + project.id + "'><li><span class='on1'><img src='" + project.logo + "' width='100' height='100'/></span><h1 class='on2'>" + project.title + "</h1><h3 class='on3'>" + project.demand + "</h3><span class='on4'></span><span class='on5'><div class='cont'>" + project.highlights + "</div></span></li></a>");
        }
    }, "json");
    $.get("<%=appPath%>/index/project/best/case", function (result) {
        var data = result.data;
        for (var i = 0; i < data.length; i++) {
            var project = data[i];
            $("#bestCases").append("<a href='<%=appPath%>/project/" + project.id + "'><li><div onmouseover='showCase(this)'><h1 class='on1'>" + project.title + "</h1><h3 class='on2'>" + project.demand + "</h3><span><img src='" + project.logo + "' width='100' height='100'/></span><span class='on5'>已完成融资</span></div></li></a>");
        }
    }, "json");
    function showCase(o) {
        $("#bestCases").find('div').each(function () {
            $(this).removeAttr("class");
        });
        $(o).attr("class", "g-three-ov");
    }
    function listbest() {
        $.get("<%=appPath%>/index/investor/best", function (result) {
            var data = result.data;
            for (var i = 0; i < data.length; i++) {
                var investor = data[i];
                if (i == 0) {
                    $("#first").html("<div class='g-fivesen-t'><a href='<%=appPath%>/investor/" + investor.id + "'><img src='" + investor.photo + "' width='110' height='110'/></a></div><div class='g-fivesen-c'><div class='on1'><span class='name'>" + investor.trueName + "</span><span class='pt'>平台推荐</span></div><h2>" + investor.companyName + " | <span>" + investor.firstCity + "</span></h2><h3><a>" + investor.summary + "</a></h3></div><div class='g-fivesen-r'><div class='on1'>感兴趣的</div><div class='on2'><span id='stages'></span><span>" + investor.singlePrice + "</span></div><ul id='indus' class='on3'></ul></div>");
                    var indus = "";
                    if (!isEmpty(investor.industryName)) {
                        indus = investor.industryName.split("#");
                        for (var j = 0; j < indus.length; j++) {
                            $("#indus").append("<li><a>" + indus[j] + "</a></li>");
                        }
                    }
                    var stages = "";
                    if (!isEmpty(investor.stageName)) {
                        stages = investor.stageName.split("#");
                        for (var m = 0; m < stages.length; m++) {
                            $("#indus").append("<li><a>" + stages[m] + "</a></li>");
                            if (m == 0) {
                                $("#stages").append("" + stages[m] + " ");
                            } else {
                                $("#stages").append("| " + stages[m] + "");
                            }
                        }
                    }
                } else {
                    $("#investors").append("<a href='<%=appPath%>/investor/" + investor.id + "'><li><span><img src='" + investor.photo + "' width='240' height='181'/></span><h1>" + investor.trueName + "</h1><h5>" + investor.companyName + " | " + investor.companyRank + "</h5><h6><span id='area_" + investor.id + "'></span> | <span id='indus_" + investor.id + "'></span></h6></li></a>");
                    var areas = "";
                    if (!isEmpty(investor.cityName)) {
                        areas = investor.cityName.split("#");
                        $("#area_" + investor.id).html(areas[0]);
                    }
                    var indus = "";
                    if (!isEmpty(investor.industryName)) {
                        indus = investor.industryName.split("#");
                        for (var x = 0; x < indus.length; x++) {
                            $("#indus_" + investor.id).append(" " + indus[x]);
                        }
                    }
                }
            }
        }, "json");
    }
    listbest();
    //$("#modal").modal("show");
    xalert("这里是提示");
</script>
</html>
