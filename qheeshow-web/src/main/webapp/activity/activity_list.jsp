<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    String flag = "4";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--活动</title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp"%>
<div class="g-banner4"></div>
<div class="g-proj">
    <div class="g-actlst-tit">
        <ul>
            <li id="all" class="on" onclick="listActivity(0,this,true);"><a>全部</a><span></span></li>
            <li onclick="listActivity(1,this,true);"><a>常规金融培训会议</a></li>
            <li onclick="listActivity(2,this,true);"><a>常规路演活动</a></li>
            <li onclick="listActivity(3,this,true);"><a>精品项目路演会</a></li>
            <li onclick="listActivity(4,this,true);"><a>路演项目进展</a></li>
            <li onclick="listActivity(5,this,true);"><a>全国性赛事</a></li>
        </ul>
    </div>
    <div class="g-actlst-piece">
        <div class="g-actlst-wap">
            <div id="activitys"></div>
            <div class="clear"></div>
            <div id="more" class="g-actlst-more"><a onclick="more();">查看更多</a></div>
        </div>
    </div>
</div>
<%@include file="../pub/foot.jsp"%>
</body>
<script>
    var pageIndex = 1;
    var activityClass = 0;
    var activityObj = null;
    function listActivity(type, obj, flag) {
        if (activityClass != type)
            pageIndex = 1;
        activityClass = type;
        activityObj = obj;
        $(obj).parent().children('li').each(function () {
            $(this).removeAttr("class");
        });
        $(obj).attr("class", "on");
        $.get("<%=appPath%>/activity/list/" + type + "/" + pageIndex + "/6", function (result) {
            if (result.data.length < 6) {
                $("#more").hide();
            } else{
                $("#more").show();
                pageIndex++;
            }
            var sData = "";
            for (var i = 0; i < result.data.length; i++) {
                var activity = result.data[i];
                var cls = "g-actlst-status";
                var status = "报名中";
                if (activity.status == 2) {
                    cls = "g-actlst-status-1";
                    status = "已结束";
                }
                sData += "<div class='g-actlst-div'><div><a href='<%=appPath%>/activity/get/" + activity.id + "' target='_blank'><img src='" + activity.logo + "' width='326' height='186'/></a></div><div class='g-actlst-cnt'><h1><a href='<%=appPath%>/activity/get/" + activity.id + "' target='_blank'>" + activity.title + "</a></h1><span class='" + cls + "'>" + status + "</span><h3>" + activity.summary + "</h3></div></div>";
            }
            if (flag)
                $("#activitys").html(sData);
            else
                $("#activitys").append(sData);
        }, "json");
    }
    $("#all").click();
    function more() {
        listActivity(activityClass, activityObj, false);
    }
</script>
</html>
