<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.service.model.TeamMember" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("project");
    List<TeamMember> members = (List<TeamMember>) request.getAttribute("members");
%>
<html>
<head>
    <title><%=Config.get("app.name")%>--<%=project.getTitle()%>
    </title>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <script src="/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
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
                <li><a href="#">申请成为专职顾问</a></li>
                <li><a href="#">查看联系方式</a></li>
            </ul>
            <div class="g-proj-ico2"></div>
        </div>
    </div>

    <div class="g-conter">
        <div class="g-invest-l">
            <div class="g-invest-lone3">
                <ul class="g-proj-titlst">
                    <li><a href="#">项目简介</a></li>
                    <li><a href="#">团队介绍</a></li>
                    <li><a href="#">项目亮点</a></li>
                    <li><a href="#">项目BP</a></li>
                    <li><a href="#">提问互动</a></li>
                </ul>
            </div>
            <div class="g-invest-lone">
                <div class="g-proj-lonetit6">项目简介</div>
                <div class="g-invest-lonect"><%=project.getDescription()%>
                </div>
            </div>
            <div class="g-invest-lone2">
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
            <div class="g-invest-lone">
                <div class="g-proj-lonetit6">项目亮点</div>
                <div class="g-invest-lonect"><%=project.getHighlights()%>
                </div>
            </div>
            <div class="g-invest-lone3">
                <ul class="g-proj-titlst">
                    <li class="on-bp">项目BP</li>
                </ul>
                <a href="#" class="g-proj-more">登录查看商业计划书</a>
            </div>
            <div class="g-invest-lone2">
                <div class="g-proj-lonetit4">提问互动<span>20条评论</span></div>

                <!--提问-->
                <div class="g-invest-tre">
                    <div class="g-invest-tre-r">
                        <div class="g-proj-texwap"><textarea name="" cols="" rows="" class="g-proj-textarea"></textarea>
                            <div class="g-proj-texcru">还可以输入<span>150</span>个字</div>
                        </div>
                        <div class="g-proj-texbtn">请先登录<a href="#" class="on2">提问</a></div>
                    </div>
                </div>
                <!------>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-invest-tre-rcnt"><a
                                href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a>
                        </div>
                        <div class="g-proj-reply"><a href="#">回复</a></div>
                    </div>
                </div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-invest-tre-rcnt"><a
                                href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a>
                        </div>
                        <div class="g-proj-reply"><a href="#">回复</a></div>
                        <div class="g-proj-texwap"><textarea name="" cols="" rows="" class="g-proj-textarea"></textarea>

                            <div class="g-proj-texcru">还可以输入<span>150</span>个字</div>
                        </div>
                        <div class="g-proj-texbtn"><a href="#" class="on1">取消</a><a href="#" class="on2">回复</a></div>

                    </div>
                </div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-proj-subtitle">@宋积 您的项目太棒了，我要投资。</div>
                        <div class="g-invest-tre-rcnt"><a
                                href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a>
                        </div>
                        <div class="g-proj-reply"><a href="#">回复</a></div>
                    </div>
                </div>
                <div class="i-Page"><a href="#" class="on1">&nbsp;</a><!--<a href="#" class="on4">&nbsp;</a>当前位置及鼠标经过箭头效果-->
                    <a
                            href="#">1</a> <a href="#" class="on">2</a> <a href="#">3</a> <a href="#"
                                                                                             class="on3">...</a> <a
                            href="#" class="on2">&nbsp;</a>
                    <!--<a href="#" class="on5">&nbsp;</a>--></div>
            </div>
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
                    <li><span class="on1"></span><span><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></span></li>
                    <li><span class="on1"></span><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></li>
                    <li><span class="on1"></span><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></li>
                    <li><span class="on1"></span><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></li>
                </ul>
                <div class="g-invest-rmore"><a href="#">更多项目</a></div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    $.ajax({
        type: 'GET',
        url: '/project/isfollow/<%=project.getId() %>',
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
            url: '/project/follow/<%=project.getId() %>',
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
            url: '/project/follow/list/<%=project.getId() %>',
            dataType: 'json',
            success: function (result) {
                if (result.code < 0)
                    return;
                var investors = result.data;
                for (var i = 0; i < investors.length; i++) {
                    var investor = investors[i];
                    $("#follows").append("<li><a href='#' target='_blank'><img src='" + investor.img + "' width='50' height='50'/><span>" + investor.trueName + "</span></a></li>");
                }
            }
        });
    }
    function listAdvisers() {
        $.ajax({
            type: 'GET',
            url: '/project/adviser/list/<%=project.getId() %>',
            dataType: 'json',
            success: function (result) {
                if (result.code < 0)
                    return;
                var investors = result.data;
                for (var i = 0; i < investors.length; i++) {
                    var investor = investors[i];
                    $("#advisers").append("<li><a href='#' target='_blank'><img src='" + investor.img + "' width='50' height='50'/><span>" + investor.trueName + "</span></a></li>");
                }
            }
        });
    }
</script>
</html>
