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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%=Config.get("app.name")%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/dropload.css"/>
</head>
<body class="wtxt-pb2 wtwx-warp">
<div>
    <div class="shade"></div>
    <div class="wtwx-project-radius">
        <a href="#" class="wtwx-project-radius-close"></a>
        <h1>温馨提示</h1>
        <h3>认证投资人才可以关注项目认证投资人资人才可以关注项目</h3>
        <input type="button" class="wtwx-project-radius-btn1" value="马上认证"/>
    </div>
</div>
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
            <li class="on1"><img src="<%=appPath%>/images/wtwx-icon1.png" width="16" height="16"/><%=project.getAreaName()%>
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
        <h1><%=member.getMemberName()%></h1>
        <h4><%=member.getPosition()%></h4>
    </div>
    <div class="wtwx-project-cnt1-conter"><%=member.getSummary()%></div>
    <%
        }
    %>
</div>
<div class="wtwx-activity-tit">提问互动<input id="comment"/><a onclick="openC();" class="wtwx-project-add">添加评论</a></div>
<div class="wtwx-project-cnt3">
    <div class="wtwx-project-cnt3-l"><img src="<%=appPath%>/images/wtwx-img2.png" width="36" height="36"/></div>
    <div class="wtwx-project-cnt3-r">
        <h1>一休哥</h1>
        <div class="wtwx-project-cnt3-rcnt">有没有明确的盈利方案和商业模式吗？后续会做什么促进？</div>
        <div class="wtwx-project-cnt3-rtime">2017年7月9日19:00<a href="#" class="wtwx-project-cnt3-rreply">回复</a></div>
    </div>
    <a href="#" class="wtwx-project-cnt3-praise">872</a>
</div>

<div class="wtwx-project-cnt3">
    <div class="wtwx-project-cnt3-l"><img src="<%=appPath%>/images/wtwx-img2.png" width="36" height="36"/></div>
    <div class="wtwx-project-cnt3-r">
        <h1>一休哥</h1>

        <div class="wtwx-project-cnt3-rcnt">有没有明确的盈利方案和商业模式吗？后续会做什么促进？</div>
        <div class="wtwx-project-cnt3-rtime">2017年7月9日19:00<a href="#" class="wtwx-project-cnt3-rreply">回复</a></div>
    </div>
    <a href="#" class="wtwx-project-cnt3-praise2">872</a>
</div>
<ul class="wtwx-menu2">
    <a href="#"><li><img src="<%=appPath%>/images/wtwx-project-meun1.png" width="25" height="25"/><span>商业计划书</span></li></a>
    <a href="#"><li><img src="<%=appPath%>/images/wtwx-project-meun2.png" width="25" height="25"/><span>联系创始人</span></li></a>
    <a href="#"><li><img src="<%=appPath%>/images/wtwx-project-meun3.png" width="25" height="25"/><span>成为专职顾问</span></li></a>
    <a href="#"><li><img src="<%=appPath%>/images/wtwx-project-meun4.png" width="25" height="25"/><span>关注</span></li></a>
</ul>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
    function openC(){
        $("#comment").focus();
    }
    $("#comment").blur(function(){
        alert($("#comment").val());
    });
</script>
</body>
</html>
