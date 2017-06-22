<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.service.model.TeamMember" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.ProjectQa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Project project = (Project) request.getAttribute("project");
    List<TeamMember> members = (List<TeamMember>) request.getAttribute("members");
    List<ProjectQa> commonQas = (List<ProjectQa>) request.getAttribute("commonQas");
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
    <script type='text/javascript' src='<%=appPath%>/images/jwplayer.js'></script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
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
                <li>
                    <a><%=tag%>
                    </a><span class="invest1-left-top"></span><span class="invest1-right-top"></span><span
                        class="invest1-right-bottom"></span><span class="invest1-left-bottom"></span>
                </li>
                <%
                    }
                %>
            </ul>
            <a id="follow" class="g-invest-focus" onclick="follow();"></a>
            <ul class="g-proj-lst3">
                <li>
                    <a id="apply" onclick="applyAdviser(<%=project.getId()%>);"></a>
                    <span class="invest1-left-top"></span><span class="invest1-right-top"></span><span
                        class="invest1-right-bottom"></span><span class="invest1-left-bottom"></span>
                </li>
                <li>
                    <a onclick="bound(<%=project.getUserid()%>)">查看联系方式</a>
                    <span class="invest1-left-top"></span><span class="invest1-right-top"></span><span
                        class="invest1-right-bottom"></span><span class="invest1-left-bottom"></span>
                </li>
            </ul>
            <div class="g-proj-ico2"></div>
        </div>
    </div>

    <div class="g-conter">
        <div class="g-invest-l">
            <div class="g-invest-lone3">
                <ul class="g-proj-titlst">
                    <li><a href="#0F">路演视频</a></li>
                    <li><a href="#1F">投资亮点</a></li>
                    <%
                        if (StringUtils.isEmpty(project.getOnepage())) {
                    %>
                    <li><a href="#2F">项目简介</a></li>
                    <li><a href="#3F">团队介绍</a></li>
                    <%
                    } else {
                    %>
                    <li><a href="#23F">一页通</a></li>
                    <%
                        }
                    %>
                    <%
                        if (!StringUtils.isEmpty(project.getBp())) {
                    %>
                    <li><a href="#4F">项目BP</a></li>
                    <%
                        }
                    %>
                </ul>
            </div>
            <div id="0F" class="g-invest-lone">
                <div class="g-proj-lonetit6">路演视频</div>
                <div class="g-invest-lonect">
                    <div id='mediaspace'></div>
                    <script type='text/javascript'>
                        jwplayer('mediaspace').setup({
                            'flashplayer': '<%=appPath%>/images/player.swf',
                            'file': '405_0573_01.flv',
                            'streamer': 'rtmp://media.qheeshow.com/oflaDemo',
                            'controlbar': 'bottom',
                            'width': '676',
                            'height': '315'
                        });
                    </script>
                </div>
            </div>
            <div id="1F" class="g-invest-lone">
                <div class="g-proj-lonetit6">投资亮点</div>
                <div class="g-invest-lonect"><%=project.getHighlights()%>
                </div>
            </div>
            <%
                if (StringUtils.isEmpty(project.getOnepage())) {
            %>
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
            <%
            } else {
            %>
            <div id="23F" class="g-invest-lone">
                <div class="g-proj-lonetit6">一页通</div>
                <div class="g-invest-lonect">
                    <img src="<%=project.getOnepage()%>" width="676"/>
                </div>
            </div>
            <%
                }
            %>
            <%
                if (!StringUtils.isEmpty(project.getBp())) {
            %>
            <div id="4F" class="g-invest-lone3">
                <ul class="g-proj-titlst">
                    <li class="on-bp">项目BP</li>
                </ul>
                <a class="g-proj-more" onclick="downloadBp();">下载商业计划书</a>
            </div>
            <%
                }
            %>
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
                    <li>
                        <span class="on1"></span>
                        <span>
                            <a href="<%=appPath%>/about/common_qa.jsp#2F">梧桐e路的优势是什么呢？</a>
                        </span>
                    </li>
                    <li>
                        <span class="on1"></span>
                        <span>
                            <a href="<%=appPath%>/about/common_qa.jsp#20F">梧桐e路是如何收费的？</a>
                        </span>
                    </li>
                    <li>
                        <span class="on1"></span>
                        <span>
                            <a href="<%=appPath%>/about/common_qa.jsp#7F">相对于企业自己寻找资金方，平台的优势在哪儿？</a>
                        </span>
                    </li>
                    <li>
                        <span class="on1"></span>
                        <span>
                            <a href="<%=appPath%>/about/common_qa.jsp#6F">企业融资周期要多久，是不是一定能获得融资？</a>
                        </span>
                    </li>
                </ul>
                <div class="g-invest-rmore"><a href="<%=appPath%>/about/common_qa.jsp">更多问题</a></div>
            </div>
        </div>
    </div>
</div>
<%@include file="../pub/foot.jsp" %>
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
    $.ajax({
        type: 'GET',
        url: '<%=appPath%>/project/adviser/isApply/<%=project.getId() %>',
        dataType: 'json',
        success: function (result) {
            if (result.data) {
                $("#apply").html("已申请专职顾问");
            } else {
                $("#apply").html("申请成为专职顾问");
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
                } else if (result.code == -1) {
                    xalert1(result.message, "去登录", "<%=appPath%>/user/login.jsp");
                } else {
                    xalert(result.message);
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
                if (result.code == -1) {
                    xalert1(result.message, "去登录", "<%=appPath%>/user/login.jsp");
                } else if (result.code == -4) {
                    <%
                        if(loginUser!=null){
                        %>xalert1(result.message, "去认证", "<%=appPath%>/investor/<%=loginUser.getId()%>/add/edit/2/auth");
                    <%
                                            }
                                        %>
                } else if (result.code == -5) {
                    <%
                        if(loginUser!=null){
                        %>xalert1(result.message, "去完善", "<%=appPath%>/investor/<%=loginUser.getId()%>/add/edit/1/auth");
                    <%
                                            }
                                        %>
                }
                else
                    xalert(result.message);
                return;
            }
            xalert("项目负责人的联系电话是：" + result.data + "，该电话10分钟内有效");
        }, "json");
    }
    function applyAdviser(projectid) {
        $.get("<%=appPath%>/project/adviser/apply/" + projectid + "/authj", function (result) {
            if (result.code < 0) {
                if (result.code == -1) {
                    xalert1(result.message, "去登录", "<%=appPath%>/user/login.jsp");
                } else if (result.code == -2) {
                    <%
                        if(loginUser!=null){
                        %>xalert1(result.message, "去认证", "<%=appPath%>/investor/<%=loginUser.getId()%>/add/edit/2/auth");
                    <%
                                            }
                                        %>
                } else if (result.code == -3) {
                    xalert(result.message);
                } else if (result.code == -4) {
                    <%
                        if(loginUser!=null){
                        %>xalert1(result.message, "去完善", "<%=appPath%>/investor/<%=loginUser.getId()%>/add/edit/1/auth");
                    <%
                                            }
                                        %>
                }
                else
                    xalert(result.message);
                return;
            }
            $("#apply").html("已申请专职顾问");
        }, "json");
    }
    function listQa() {
        $("#qas").load("<%=appPath%>/project/qa/list/<%=project.getId()%>/1");
    }
    listAdvisers();
    listFollows();
    listQa();
    function q() {
        if (!$("#content").val().length) {
            xalert("请输入您的问题");
            return;
        }
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '<%=appPath%>/project/qa/q/authj',
            data: $('#qForm').serialize(),
            success: function (result) {
                if (result.code == -1) {
                    xalert(result.message);
                    return;
                }
                listQa();
            }
        });
    }
    function openA(id) {
        $("#reply_" + id).show();
    }
    function closeA(id) {
        $("#reply_" + id).hide();
    }
    function a(id) {
        if (!$("#content_" + id).val().length) {
            xalert("请输入您的回复");
            return;
        }
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '<%=appPath%>/project/qa/a/authj',
            data: $('#aForm_' + id).serialize(),
            success: function (result) {
                if (result.code == -1) {
                    xalert(result.message);
                    return;
                }
                listQa();
            }
        });
    }

    function downloadBp() {

        $.get("<%=appPath%>/project/bp/download/<%=project.getId()%>/authj", function (result) {
            if (result.code < 0) {
                if (result.code == -1) {
                    xalert1(result.message, "去登录", "<%=appPath%>/user/login.jsp");
                } else
                    xalert(result.message);
                return;
            }
            window.location.href = result.data;
        }, "json");
    }
</script>
</html>
