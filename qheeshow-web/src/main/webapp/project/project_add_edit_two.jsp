<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.TeamMember" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("project");
    List<TeamMember> members = (List<TeamMember>) request.getAttribute("members");
%>
<html>
<head>
    <title><%=Config.get("app.name")%>--创建项目</title>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <link rel="stylesheet" href="/images/project.css"/>
    <script type="text/javascript" src="/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="pro-body">
    <input type="hidden" name="projectid" value="<%=project.getId()%>"/>

    <div class="pro-wap">
        <div class="pro-t">创始人信息(2/3)</div>
        <div class="empty"></div>
        <%
            if (members.size() == 0) {
        %>
        <form>
            <input name="id" type="hidden" value="0"/>
            <input name="projectid" type="hidden" value="<%=project.getId()%>"/>

            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人信息</li>
                    <li class="on2">
                        <ul>
                            <li class="pro-c-name"><input name="memberName" class="pro-one-ipt1"
                                                          placeholder="姓名"></li>
                            <li><input name="position" class="pro-one-ipt1" placeholder="职位"></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人简介</li>
                    <li class="on2">
                        <ul class="pro-six-lst">
                            <li><textarea name="summary" class="pro-one-tex" placeholder="个人简介（不超过300字）"></textarea>
                            </li>
                        </ul>
                        <div class="pro-six-janj">详细的团队成员信息，可以让投资人透彻的
                            了解团队的组成情况，团队中每位成员的亮
                            点都能为项目起到加分的作用
                        </div>
                    </li>
                </ul>
            </div>
            <div class="pro-clear"></div>
            <div class="pro-btn1"><a onclick="saveTeam(this);">保 存</a></div>
        </form>
        <%
        } else {
            for (int i = 0; i < members.size(); i++) {
                TeamMember member = members.get(i);
        %>
        <form>
            <input name="id" type="hidden" value="<%=member.getId()%>"/>
            <input name="projectid" type="hidden" value="<%=member.getProjectid()%>"/>

            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人信息</li>
                    <li class="on2">
                        <ul>
                            <li class="pro-c-name"><input name="memberName" class="pro-one-ipt1"
                                                          value="<%=member.getMemberName()%>"></li>
                            <li><input name="position" class="pro-one-ipt1" value="<%=member.getPosition()%>">
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人简介</li>
                    <li class="on2">
                        <ul class="pro-six-lst">
                            <li><textarea name="summary" class="pro-one-tex"><%=member.getSummary()%></textarea></li>
                        </ul>
                        <%
                            if (i == 0) {
                        %>
                        <div class="pro-six-janj">详细的团队成员信息，可以让投资人透彻的
                            了解团队的组成情况，团队中每位成员的亮
                            点都能为项目起到加分的作用
                        </div>
                        <%
                            }
                        %>
                    </li>
                </ul>
            </div>
            <div class="pro-clear"></div>
            <div class="pro-btn1"><a onclick="saveTeam(this);">保 存</a></div>
        </form>
        <%
                }
            }
        %>
        <div id="temp" style="display: none;"></div>
        <div id="members"></div>
        <div class="group"><a onclick="addMember();">添加核心成员</a></div>
        <!--*************************下一步按钮************************-->
        <div class="pro-clear"></div>
        <div class="pro-btn"><a href="/project/<%=project.getId() %>/add/edit/1">上一步</a><a
                href="/project/<%=project.getId() %>/add/edit/3">下一步</a></div>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    //保存基本信息
    function saveTeam(obj) {

        $.ajax({
            type: 'POST',
            url: '/project/team/save',
            cache: false,
            processData: false,
            data: $(obj).parent().parent().serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                alert("保存成功");
                window.location.reload();
            }
        });
    }
    function addMember() {
        $("#temp").load("/project/member_append.jsp?projectid=<%=project.getId()%>", function () {
            $("#members").append($('#temp').html());
        });
    }
</script>
</html>