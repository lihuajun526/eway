<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="com.qheeshow.eway.service.model.TeamMember" %>
<%@ page import="java.util.List" %>
<%
    String appPath = Config.get("app.path");
    Project project = (Project) request.getAttribute("project");
    List<TeamMember> members = (List<TeamMember>) request.getAttribute("members");
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
<body class="wtxt-pb2 wtwx-warp">
<%@include file="../pub/head.jsp" %>
<div class="wtwx-project-cnt1">
    <div class="wtwx-project-cnt1-l"><img src="<%=project.getLogo()%>" width="72" height="72"/></div>
    <div class="wtwx-project-cnt1-r">
        <h1><%=project.getTitle()%>
        </h1>

        <div class="wtwx-project-cnt1-rnum"><span class="on1"></span>浏览量<span class="on2">（56）</span></div>
    </div>
    <div class="wtwx-project-cnt1-rcnt"><%=project.getDemand()%>
    </div>
    <ul class="wtwx-project-cnt1-lst4">
        <%
            String tags = project.getTags();
            if (!StringUtils.isEmpty(tags)) {
                String[] aTags = tags.split("#");
                for (int i = 0; i < aTags.length; i++) {
        %>
        <li><a><%=aTags[i]%>
        </a></li>
        <%
                }
            }
        %>
    </ul>
    <ul class="wtwx-project-cnt1-bootm">
        <a>
            <li class="on1"><img src="<%=appPath%>/images/wtwx-icon1.png" width="16"
                                 height="16"/><%=project.getAreaName()%>
            </li>
        </a>
        <a>
            <li><img src="<%=appPath%>/images/wtwx-icon2.png" width="16" height="16"/><%=project.getStageName()%>
            </li>
        </a>
        <a>
            <li class="on2"><img src="<%=appPath%>/images/wtwx-icon3.png" width="16"
                                 height="16"/><%=project.getFinancingLimitName()%>
            </li>
        </a>
        <a>
            <li><img src="<%=appPath%>/images/wtwx-icon4.png" width="16" height="16"/><%=project.getIndustryName()%>
            </li>
        </a>
    </ul>
</div>
<div class="wtwx-project-cnt2">
    <div class="wtwx-activity-tit2">项目简介</div>
    <div class="wtwx-project-cnt1-conter"><%=project.getDescription()%>
    </div>
    <div class="wtwx-activity-tit2">投资亮点</div>
    <div class="wtwx-project-cnt1-conter"><%=project.getHighlights()%>
    </div>
</div>
<div class="wtwx-project-cnt2">
    <div class="wtwx-activity-tit2">团队介绍</div>
    <%
        for (TeamMember member : members) {
    %>
    <div class="wtwx-project-cnt2-l"><img src="<%=member.getPhoto()%>" width="48" height="48"/></div>
    <div class="wtwx-project-cnt2-r">
        <h1><%=member.getMemberName()%>
        </h1>
        <h4><%=member.getPosition()%>
        </h4>
    </div>
    <div class="wtwx-project-cnt1-conter"><%=member.getSummary()%>
    </div>
    <%
        }
    %>
</div>

<div class="wtwx-activity-tit">提问互动<a onclick="openQa()" class="wtwx-project-add">添加问题</a></div>
<div class="wtwx-project-textarea">
    <textarea class="wtwx-project-textareacnt" placeholder="填写您的问题..."></textarea>

    <div class="wtwx-project-textabtn">
        <a class="on1">确定</a>
        <a class="on2">取消</a>
    </div>
</div>

<div class="wtwx-project-cnt3">
    <div class="wtwx-project-cnt3-l"><img src="<%=appPath%>/images/wtwx-img2.png" width="36" height="36"/></div>
    <div class="wtwx-project-cnt3-r">
        <h1>一休哥</h1>

        <div class="wtwx-project-cnt3-rcnt">有没有明确的盈利方案和商业模式吗？后续会做什么促进？</div>
        <!--点击回复的时候下面有输入框 star-->
        <input type="text" class="wtwx-project-cnt3-ripnt" placeholder="回复"/>
        <!--点击回复的时候下面有输入框end-->
    </div>
    <a href="#" class="wtwx-project-cnt3-praise">2017年7月9日19:00</a>
</div>

<div class="wtwx-project-cnt3">
    <div class="wtwx-project-cnt3-l"><img src="<%=appPath%>/images/wtwx-img2.png" width="36" height="36"/></div>
    <div class="wtwx-project-cnt3-r">
        <h1>一休哥</h1>

        <div class="wtwx-project-cnt3-rcnt">有没有明确的盈利方案和商业模式吗？后续会做什么促进？</div>
        <!--点击回复时候 star-->
        <div class="wtwx-project-cancel"><a href="#">取消</a></div>
        <div class="wtwx-project-cnt3-warp">
            <input type="text" class="ipt" value="请输入要文字回复"/>
            <a href="#" class="btn">确定</a>
        </div>
        <!--点击回复时候 end-->
    </div>
    <a href="#" class="wtwx-project-cnt3-praise">2017年7月9日19:00</a>
</div>

<div class="wtwx-project-cnt3">
    <div class="wtwx-project-cnt3-l"><img src="<%=appPath%>/images/wtwx-img2.png" width="36" height="36"/></div>
    <div class="wtwx-project-cnt3-r">
        <h1>一休哥</h1>

        <div class="wtwx-project-cnt3-rcnt">有没有明确的盈利方案和商业模式吗？后续会做什么促进？</div>
        <!--回复成功后 star-->
        <div class="wtwx-project-cnt3-rcnt2">
            <span>一休哥：</span>我想说为什么那么好的项目你没有看上呢，这可能是你错过阿里巴巴这样大的企业哦。只想知道你是出于什么考虑呢。
        </div>
        <!--回复成功后 end-->
    </div>
    <a href="#" class="wtwx-project-cnt3-praise">2017年7月9日19:00</a>
</div>


<ul class="wtwx-menu2">
    <a onclick="getBp()">
        <li><img src="<%=appPath%>/images/wtwx-project-meun1.png" width="25" height="25"/><span>商业计划书</span></li>
    </a>
    <a onclick="bound()">
        <li><img src="<%=appPath%>/images/wtwx-project-meun2.png" width="25" height="25"/><span>联系创始人</span></li>
    </a>
    <a onclick="applyAdviser()">
        <li><img src="<%=appPath%>/images/wtwx-project-meun3.png" width="25" height="25"/><span>成为专职顾问</span></li>
    </a>
    <a onclick="followOrNot()">
        <li><img src="<%=appPath%>/images/wtwx-project-meun4.png" width="25" height="25"/><span id="follow_">关注</span>
        </li>
    </a>
</ul>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
    var isFollowed = false;
    function openC() {
        $("#comment").focus();
    }
    $("#comment").blur(function () {
        alert($("#comment").val());
    });
    function getBp() {
        $.getJSON('<%=appPath%>/project/do/bp/download/<%=project.getId()%>/v_authj', function (result) {
            openTip(result);
        });
    }
    function bound() {
        $.getJSON("<%=appPath%>/mixcom/do/bound/<%=project.getUserid()%>/v_authj", function (result) {
            openTip(result);
        }, "json");
    }
    function follow() {
        $.getJSON("<%=appPath%>/project/do/follow/<%=project.getId()%>/v_authj", function (result) {
            if (result.code == 0) {
                openTip(result);
                isFollowed = true;
                $("#follow_").html("已关注");
            }
        }, "json");
    }
    function followOrNot() {
        if (!isFollowed)
            follow();
    }
    $.getJSON("<%=appPath%>/project/do/isfollow/<%=project.getId() %>", function (result) {
        if (!result.data) {
            isFollowed = false;
            $("#follow_").html("关注");
        } else {
            isFollowed = true;
            $("#follow_").html("已关注");
        }
    });
    function applyAdviser() {
        $.getJSON("<%=appPath%>/project/do/adviser/apply/<%=project.getId()%>/v_authj", function (result) {
            openTip(result);
        }, "json");
    }
</script>
</body>
</html>
