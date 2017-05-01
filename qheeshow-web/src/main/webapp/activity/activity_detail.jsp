<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Activity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Activity activity = (Activity) request.getAttribute("activity");
    String flag = "4";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--<%=activity.getTitle()%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<%--<div class="g-banner4"></div>--%>
<div class="g-proj">
    <div class="g-conter">
        <div class="g-actlst-one-warp">
            <div class="g-actlst-onel"><img src="<%=activity.getLogo()%>" width="440" height="250"/></div>
            <div class="g-actlst-oneer">
                <h1><%=activity.getTitle()%>
                </h1>
                <ul class="g-actlst-onelst">
                    <li class="on1">2017年3月28日 8:00-2017年3月28日 18:00</li>
                    <li class="on2"><%=activity.getAddress()%>
                    </li>
                    <li class="on3">限额<%=activity.getLimitNum()%>人</li>
                    <li class="on4"><span><img src="<%=appPath%>/images/wt-icon26.png"/></span><span
                            class="on5"><%=activity.getSponsor()%></span><span
                            class="on6"><a href="#">联系主办方：<%=activity.getTel()%>
                    </a></span></li>
                </ul>
                <ul class="g-actlst-onelst2">
                    <li><input name="" type="button" value="名额已满" class="g-actlst-btn2"></li>
                </ul>
            </div>
        </div>
        <div class="g-actlst-twol">
            <div class="g-actlst-twol-tit"><span>活动内容</span></div>
            <div class="g-actlst-twol-cnt">
                <ul class="g-actlst-twolst2"><%=activity.getContent()%>
                </ul>
                <div><img src="images/actlst-img.jpg"/></div>
            </div>
        </div>
        <div class="g-actlst-twor">
            <ul class="g-actlst-two-lst">
                <li><a href="#"><img src="<%=appPath%>/images/actlst-img2.jpg"/></a></li>
            </ul>
            <div class="g-actlst-two-tit">活动地址</div>
            <ul class="g-actlst-two-lst">
                <li><img src="<%=activity.getBaiduMap()%>"/></li>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
</div>
<%@include file="../pub/foot.jsp" %>
</body>
<script>

</script>
</html>
