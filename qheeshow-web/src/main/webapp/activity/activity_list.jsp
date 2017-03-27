<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<html>
<head>
    <title><%=Config.get("app.name")%>--活动</title>
    <link rel="stylesheet" href="/images/animate.min.css">
    <link rel="stylesheet" href="/images/bootstrap.css">
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <script src="/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp?flag=4" flush="true"/>
<div class="g-banner2"></div>
<div class="g-proj">
    <div class="g-actlst-tit">
        <ul>
            <li class="on"><a>全部</a><span></span></li>
            <li><a>常规金融培训会议</a></li>
            <li><a>常规路演活动</a></li>
            <li><a>精品项目路演会</a></li>
            <li><a>路演项目进展</a></li>
            <li><a>全国性赛事</a></li>
        </ul>
    </div>
    <div class="g-actlst-piece">
        <div class="g-actlst-wap">
            <div id="activitys"></div>
            <div class="clear"></div>
            <div id="more" class="g-actlst-more"><a onclick="listActivity();">查看更多</a></div>
        </div>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    var pageIndex = 1;
    function listActivity() {
        $.get("/activity/list/0/" + pageIndex + "/6", function (result) {
            if (result.data.length < 6) {
                $("#more").hide();
            } else
                pageIndex++;
            for (var i = 0; i < result.data.length; i++) {
                var activity = result[i];
                var cls = "g-actlst-status";
                var status = "报名中";
                if (activity.status == 2) {
                    cls = "g-actlst-status-1";
                    status = "已结束";
                }
                $("#activitys").append("<div class='g-actlst-div'><div><a href='/activity/get/" + activity.id + "' target='_blank'><img src='" + activity.logo + "' width='326' height='186'/></a></div><div class='g-actlst-cnt'><h1><a href='/activity/get/" + activity.id + "' target='_blank'>" + activity.title + "</a></h1><span class='" + cls + "'>" + status + "</span><h3>" + activity.summary + "</h3></div></div>");
            }
        }, "json");
    }
    listActivity();
</script>
</html>
