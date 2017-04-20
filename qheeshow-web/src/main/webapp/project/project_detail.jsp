<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.service.model.TeamMember" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.CommonQa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Project project = (Project) request.getAttribute("project");
    List<TeamMember> members = (List<TeamMember>) request.getAttribute("members");
    List<CommonQa> commonQas = (List<CommonQa>) request.getAttribute("commonQas");
    String flag = "2";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--<%=project.getTitle()%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/images/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp"%>
<div class="g-proj">
    <div class="g-invest">
        <div class="g-proj-img"><img src="<%=project.getLogo()%>" width="200" height="200"/></div>
        <div class="g-proj-tr">
            <div class="g-invest-name2"><%=project.getTitle()%>
            </div>
            <div class="g-invest-t"><%=project.getDemand()%>
            </div>
            <ul class="g-proj-lst">
                <li class="on1"><%=project.getAreaName()%>
                </li>
                <li class="on2"><%=project.getStageName()%>
                </li>
                <li class="on3"><%=project.getIndustryName()%>
                </li>
            </ul>
            <ul class="g-proj-lst2">
                <%
                    for (String tag : project.getTags().split("#")) {
                %>
                <li><a><%=tag%>
                </a></li>
                <%
                    }
                %>
            </ul>
            <a id="follow" class="g-invest-focus" onclick="follow();"></a>
            <ul class="g-proj-lst3">
                <li><a id="apply" onclick="applyAdviser(<%=project.getId()%>);">申请成为专职顾问</a></li>
                <li><a onclick="bound(<%=project.getUserid()%>)">查看联系方式</a></li>
            </ul>
            <div class="g-proj-ico2"></div>
        </div>
    </div>

    <div class="g-conter">
        <div class="g-invest-l">
            <div class="g-invest-lone3">
                <ul class="g-proj-titlst">
                    <li><a href="#1F">项目亮点</a></li>
                    <li><a href="#2F">项目简介</a></li>
                    <li><a href="#3F">团队介绍</a></li>
                    <li><a href="#4F">项目BP</a></li>
                </ul>
            </div>
            <div id="1F" class="g-invest-lone">
                <div class="g-proj-lonetit6">项目亮点</div>
                <div class="g-invest-lonect"><%=project.getHighlights()%>
                </div>
            </div>
            <div id="2F" class="g-invest-lone">
                <div class="g-proj-lonetit6">项目简介</div>
                <div class="g-invest-lonect"><%=project.getDescription()%>
                </div>
            </div>
            <div id="3F" class="g-invest-lone2">
                <div class="g-proj-lonetit2">团队介绍</div>
                <%
                    for (TeamMember member : members) {
                %>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="<%=member.getPhoto()%>" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1"><%=member.getMemberName()%></span><span
                                class="on2"><%=member.getPosition()%></span></div>
                        <div class="g-invest-tre-rcnt"><a><%=member.getSummary()%>
                        </a>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            <div id="4F" class="g-invest-lone3">
                <ul class="g-proj-titlst">
                    <li class="on-bp">项目BP</li>
                </ul>
                <a href="#" class="g-proj-more">登录查看商业计划书</a>
            </div>
            <div id="qas" class="g-invest-lone2"></div>
        </div>
        <!--*************************right star************************-->
        <div class="g-invest-r">
            <div class="g-invest-rone">
                <div class="g-invest-rt2">专职顾问</div>
                <ul id="advisers" class="g-proj-cost"></ul>
            </div>
            <div class="g-invest-rone">
                <div class="g-invest-rt2">感兴趣的人</div>
                <ul id="follows" class="g-proj-cost"></ul>
            </div>
            <div class="g-invest-rone">
                <div class="g-invest-rt">常见问题</div>
                <ul class="g-invest-rlst2">
                    <%
                        for (CommonQa commonQa : commonQas) {
                    %>
                    <li><span class="on1"></span><span><a href="#"><%=commonQa.getQuestion()%>
                    </a></span></li>
                    <%
                        }
                    %>
                </ul>
                <div class="g-invest-rmore"><a href="#">更多项目</a></div>
            </div>
        </div>
    </div>
</div>
<%@include file="../pub/foot.jsp"%>
</body>
<script>
    $.ajax({
        type: 'GET',
        url: '<%=appPath%>/project/isfollow/<%=project.getId() %>',
        dataType: 'json',
        success: function (result) {
            if (!result.data) {
                $("#follow").html("+关注");
            } else {
                $("#follow").html("已关注");
            }
        }
    });
    function follow() {
        $.ajax({
            type: 'GET',
            url: '<%=appPath%>/project/follow/<%=project.getId() %>',
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    $("#follow").html("已关注");
                } else {
                    alert(result.message);
                }
            }
        });
    }
    function listFollows() {
        $.ajax({
            type: 'GET',
            url: '<%=appPath%>/project/follow/list/<%=project.getId() %>',
            dataType: 'json',
            success: function (result) {
                if (result.code < 0)
                    return;
                var investors = result.data;
                for (var i = 0; i < investors.length; i++) {
                    var investor = investors[i];
                    $("#follows").append("<li><a href='<%=appPath%>/investor/" + investor.id + "' target='_blank'><img src='" + investor.photo + "' width='50' height='50'/><span>" + investor.trueName + "</span></a></li>");
                }
            }
        });
    }
    function listAdvisers() {
        $.ajax({
            type: 'GET',
            url: '<%=appPath%>/project/adviser/list/<%=project.getId() %>',
            dataType: 'json',
            success: function (result) {
                if (result.code < 0)
                    return;
                var investors = result.data;
                for (var i = 0; i < investors.length; i++) {
                    var investor = investors[i];
                    $("#advisers").append("<li><a href='<%=appPath%>/investor/" + investor.id + "' target='_blank'><img src='" + investor.photo + "' width='50' height='50'/><span>" + investor.trueName + "</span></a></li>");
                }
            }
        });
    }
    function bound(userid) {
        $.get("<%=appPath%>/mixcom/bound/" + userid + "/authj", function (result) {
            if (result.code < 0) {
                alert(result.message);
                return;
            }
            alert("项目负责人的联系电话是：" + result.data + "，该电话10分钟内有效");
        }, "json");
    }
    function applyAdviser(projectid) {
        $.get("<%=appPath%>/project/adviser/apply/" + projectid + "/authj", function (result) {
            if (result.code < 0) {
                alert(result.message);
                return;
            }
            $("#apply").html("已申请专职顾问");
        }, "json");
    }
    listAdvisers();
    listFollows();
    $("#qas").load("<%=appPath%>/qa/list/<%=project.getId()%>/1");
    function q1111(){alert("dfasd");
        /*$.ajax({
            type: 'POST',
            dataType: 'json',
            url: '<%=appPath%>/qa/q',
            data: $('#qaForm').serialize(),
            success: function (result) {
                if (result.code == -1) {
                    alert(result.message);
                    return;
                }
            }
        });*/
    }
</script>
</html>
