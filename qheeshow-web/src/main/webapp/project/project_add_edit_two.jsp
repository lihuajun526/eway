<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project)request.getAttribute("project");
%>
<html>
<head>
    <title><%=Config.get("app.name")%>--创建项目</title>
    <link rel="stylesheet" href="/images/animate.min.css">
    <link rel="stylesheet" href="/images/bootstrap.css">
    <!--*************************bootstrap css end************************-->
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <!--*************************创建项目的主链接************************-->
    <link rel="stylesheet" href="/images/project.css"/>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="/js/config.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="pro-body">
    <form id="teamForm">
        <input type="hidden" name="projectid" value="<%=project.getId()%>"/>
        <input type="hidden" name="memberNames" value="<%=project.getId()%>"/>
        <div class="pro-wap">
            <div class="pro-t">项目信息(2/3)</div>
            <div class="empty"></div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目名称</li>
                    <li class="on2">
                        <ul class="pro-six-lst">
                            <li><input type="text" class="pro-one-ipt" value="填写你的创业项目名称，不超过8个字"/></li>
                            <li><input type="text" class="pro-one-ipt" value="一句话介绍你的项目所能解决的市场需求，不超过30个汉字"/></li>
                            <li><textarea name="" cols="" rows="" class="pro-one-tex">一句话介绍你的团队的优势（不超过300字）</textarea></li>
                        </ul>
                    </li>
                    <li class="on3">详细的团队成员信息，可以让投资人透彻的
                        了解团队的组成情况，团队中每位成员的亮
                        点都能为项目起到加分的作用
                    </li>
                </ul>
            </div>
            <!--*************************下一步按钮************************-->
            <div class="pro-clear"></div>
            <div class="pro-btn"><a href="/project/<%=project.getId() %>/add/edit/1">上一步</a><a onclick="saveTeam();">下一步</a></div>
        </div>
    </form>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    //保存基本信息
    function saveTeam() {
        $.ajax({
            type: 'POST',
            url: '/project/team/save',
            cache: false,
            processData: false,
            data: $('#teamForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                window.location.href = "/" + result.data + "/add/edit/3";
            }
        });
    }
</script>
</html>
