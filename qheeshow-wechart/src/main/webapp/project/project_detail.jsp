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
    <script type='text/javascript' src='<%=appPath%>/images/jwplayer.js'></script>
</head>
<body id="top" class="wtxt-pb2 wtwx-warp">
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
<%--<div class="wtwx-project-cnt2a">
    <div class="wtwx-activity-tit2">路演视频</div>
    <div class="wtwx-project-cnt1-conter1">
        &lt;%&ndash;<img src="<%=appPath%>/images/wtwx-img2.png" width="100%" height="195"/>
        <a href="#" class="play"></a>&ndash;%&gt;

        <div id='mediaspace'></div>
        <script type='text/javascript'>
            jwplayer('mediaspace').setup({
                'flashplayer': '<%=appPath%>/images/player.swf',
                'file': '405_0573_01.flv',
                'streamer': 'rtmp://media.qheeshow.com/oflaDemo',
                'controlbar': 'bottom',
                'width': '100%',
                'height': '195'
            });
        </script>

    </div>
</div>--%>
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

<div class="wtwx-activity-tit">提问互动<a onclick="javascript:$('#q_').show()" class="wtwx-project-add">点击提问</a></div>
<div id="q_" class="wtwx-project-textarea" style="display: none">
    <textarea id="question" class="wtwx-project-textareacnt" placeholder="填写您的问题..."></textarea>

    <div class="wtwx-project-textabtn">
        <a href="#top" class="on1" onclick="q()">确定</a>
        <a onclick="javascript:$('#q_').hide()" class="on2">取消</a>
    </div>
</div>
<div id="qas"></div>
<ul class="wtwx-menu2">
    <a href="#" onclick="getBp()">
        <li><img src="<%=appPath%>/images/wtwx-project-meun1.png" width="25" height="25"/><span>商业计划书</span></li>
    </a>
    <a href="#" onclick="bound()">
        <li><img src="<%=appPath%>/images/wtwx-project-meun2.png" width="25" height="25"/><span>联系创始人</span></li>
    </a>
    <a href="#" onclick="applyAdviser()">
        <li><img src="<%=appPath%>/images/wtwx-project-meun3.png" width="25" height="25"/><span>成为专职顾问</span></li>
    </a>
    <a href="#" onclick="followOrNot()">
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
    function listQas() {
        $("#qas").load("<%=appPath%>/project/do/qa/list/<%=project.getId()%>");
    }
    listQas();
    function q() {
        if (!$("#question").val().length) {
            openTip({'message': '请输入您的问题', 'data': {'action': '知道了', 'link': 'close'}});
            return;
        }
        /*$.post("
        <%=appPath%>/project/do/q/
        <%=project.getId()%>/v_authj", function (result) {
         openTip(result);
         }, "json");

         $.post('
        <%=appPath%>/project/do/q/
        <%=project.getId()%>/v_authj', {sample: 'payload'}, function (response) {
         // process response
         })*/
    }
</script>
</body>
</html>
