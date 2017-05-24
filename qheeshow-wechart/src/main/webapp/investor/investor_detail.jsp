<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%
    String appPath = Config.get("app.path");
    Investor investor = (Investor) request.getAttribute("investor");
    Map<String, Integer> tags = (HashMap<String, Integer>) request.getAttribute("tags");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title><%=Config.get("app.name")%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/dropload.css"/>
</head>
<body class="wtxt-pb wtwx-warp">
<%@include file="../pub/head.jsp" %>
<div id="pros" style="display: none;">
    <div class="shade"></div>
    <div class="wtwx-project2-radius">
        <h1>选择要投递的项目</h1>
        <ul class="wtwx-project2-lst2"></ul>
        <div class="wtwx-project2-btn3"><a onclick="postPro()">确定</a></div>
    </div>
</div>
<div class="wtwx-project-cnt1">
    <div class="wtwx-project-cnt1-l"><img src="<%=investor.getPhoto()%>" width="72" height="72"/></div>
    <div class="wtwx-project-cnt1-r">
        <span class="wtwx-project-fans">粉丝：10人</span>

        <h1><%=investor.getTrueName()%>
        </h1>

        <div class="wtwx-project-cnt1-rnum"><%=investor.getCompanyName()%>-<%=investor.getCompanyRank()%>
        </div>
    </div>
    <div class="wtwx-project-cnt1-rcnt"><%=investor.getSummary()%>
    </div>
    <ul class="wtwx-project-cnt1-lst4">
        <%
            for (String tag : tags.keySet()) {
        %>
        <li><a><%=tag%>(<%=tags.get(tag)%>)</a></li>
        <%
            }
        %>
    </ul>
</div>
<div class="wtwx-project-cnt2">
    <div class="wtwx-activity-tit2">个人介绍</div>
    <div class="wtwx-project-cnt1-conter"><%=investor.getPersonalProfile()%>
    </div>
</div>
<div class="wtwx-project-cnt2">
    <div class="wtwx-activity-tit2">评价</div>
    <ul class="wtwx-project-cnt2-lst4">
        <li onclick="setTag(this)" data="专业人士"><a>专业人士</a></li>
        <li onclick="setTag(this)" data="人脉大咖"><a>人脉大咖</a></li>
        <li onclick="setTag(this)" data="金融专家"><a>金融专家</a></li>
        <li onclick="setTag(this)" data="FA达人"><a>FA达人</a></li>
    </ul>
    <div class="wtwx-project-cnt2-lst5">
        <div class="wtwx-project-cnt2-lst5-l">星级评价：</div>
        <div id="stars" class="wtwx-project-cnt2-lst5-r">
            <a onclick="setStar(1)"><img src="images/wtwx-icon16.png" width="19" height="18"/></a>
            <a onclick="setStar(2)"><img src="images/wtwx-icon16.png" width="19" height="18"/></a>
            <a onclick="setStar(3)"><img src="images/wtwx-icon16.png" width="19" height="18"/></a>
            <a onclick="setStar(4)"><img src="images/wtwx-icon16.png" width="19" height="18"/></a>
            <a onclick="setStar(5)"><img src="images/wtwx-icon16.png" width="19" height="18"/></a>
        </div>
    </div>
    <div class="wtwx-project-cnt2-lst5">
        <div class="wtwx-project-cnt2-lst5-la">描述评论：</div>
        <div class="wtwx-project-cnt2-lst5-r">
            <div class="wtwx-project-texta"><textarea name="" cols="" rows=""
                                                      class="wtwx-project-texta1">添加评论</textarea></div>
            <div class="wtwx-project-btn2"><a href="#">发表评论</a></div>
        </div>
    </div>
    <div class="wtwx-project-cnt3">
        <div class="wtwx-project-cnt3-l"><img src="images/wtwx-img2.png" width="36" height="36"/></div>
        <div class="wtwx-project-cnt3-r">
            <h1>一休哥</h1>

            <div class="wtwx-project-cnt3-rcnt">有没有明确的盈利方案和商业模式吗？后续会做什么促进？</div>
            <div class="wtwx-project-cnt3-rtime">2017年7月9日19:00<a href="#" class="wtwx-project-cnt3-rreply">回复</a></div>
        </div>
        <a href="#" class="wtwx-project-cnt3-praise">872</a>
    </div>
    <div class="wtwx-project-cnt3">
        <div class="wtwx-project-cnt3-l"><img src="images/wtwx-img2.png" width="36" height="36"/></div>
        <div class="wtwx-project-cnt3-r">
            <h1>一休哥</h1>

            <div class="wtwx-project-cnt3-rcnt">有没有明<span class="blue">@一宿哥</span>确的盈利方案和商业模式吗？后续会做什么促进？</div>
            <div class="wtwx-project-cnt3-rtime">2017年7月9日19:00<a href="#" class="wtwx-project-cnt3-rreply">回复</a></div>
        </div>
        <a href="#" class="wtwx-project-cnt3-praise2">872</a>
    </div>
</div>
<ul class="wtwx-menu3">
    <a onclick="follow()">
        <li><img src="images/wtwx-project-meun4.png" width="25" height="25"/><span id="follow_">关注</span></li>
    </a>
    <a onclick="postPro()">
        <li><img src="images/wtwx-project-meun5.png" width="25" height="25"/><span>投递项目</span></li>
    </a>
    <a onclick="bound()">
        <li class="on"><img src="images/wtwx-project-meun6.png" width="25" height="25"/><span>获取联系电话</span></li>
    </a>
</ul>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
    var isFollowed = false;
    function setTag(obj) {
        if ($(obj).attr("class") == "on") {
            $(obj).removeAttr("class");
        } else {
            $(obj).attr("class", "on");
        }
    }
    function setStar(v) {
        var imgs = $("#stars>a>img");
        for (var i = 1; i <= imgs.length; i++) {
            if (i <= v)
                $(imgs[i]).attr("src", "images/wtwx-icon17.png");
            else
                $(imgs[i]).attr("src", "images/wtwx-icon16.png");
        }
        $("#star").val(v);
    }
    function follow() {
        if (isFollowed)
            return;
        $.get("<%=appPath%>/investor/do/follow/<%=investor.getId()%>/v_auth", function (result) {
            openTip(result);
            if (result.code >= 0) {
                $("#follow_").html("已关注");
            }
        }, "json");
    }
    //是否已关注
    $.get("<%=appPath%>/investor/do/isfollow/<%=investor.getId()%>", function (result) {
        if (result.data) {
            $("#follow_").html("已关注");
            isFollowed = true;
        } else {
            $("#follow_").html("关注");
            isFollowed = false;
        }
    }, "json");
    //是否可以投递项目
    function isAblePost() {
        $.get("<%=appPath%>/investor/do/isable/post/v_auth", function (result) {
            openTip(result);
            if (result.code >= 0) {
                $("#pros").show();
            }
        }, "json");
    }
    //投递项目
    function postPro(id) {
        $.get("<%=appPath%>/investor/do/project/post/" + id + "/<%=investor.getUserid()%>/v_auth", function (result) {
            openTip(result);
        }, "json");
    }
    function bound() {
        $.getJSON("<%=appPath%>/mixcom/do/bound/<%=investor.getUserid()%>/v_authj", function (result) {
            openTip(result);
        }, "json");
    }
</script>
</body>
</html>
