<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("project");
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
        <div class="pro-wap">
            <div class="pro-t">创始人信息(2/3)</div>
            <div class="empty"></div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人信息</li>
                    <li class="on2">
                        <ul>
                            <li class="pro-c-name"><input type="text" class="pro-one-ipt1" value="姓名"></li>
                            <li><input type="text" class="pro-one-ipt1" value="职位"></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人简介</li>
                    <li class="on2">
                        <ul class="pro-six-lst">
                            <li><textarea name="" cols="" rows="" class="pro-one-tex">一句话介绍你的团队的优势（不超过300字）</textarea></li>
                        </ul>
                        <div class="pro-six-janj">详细的团队成员信息，可以让投资人透彻的
                            了解团队的组成情况，团队中每位成员的亮
                            点都能为项目起到加分的作用
                        </div>

                    </li>
                </ul>
            </div>
            <!--下面是多个的情况 复制上面2块-->
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人信息</li>
                    <li class="on2">
                        <ul>
                            <li class="pro-c-name"><input type="text" class="pro-one-ipt1" value="姓名"></li>
                            <li><input type="text" class="pro-one-ipt1" value="职位"></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人简介</li>
                    <li class="on2">
                        <ul class="pro-six-lst">
                            <li><textarea name="" cols="" rows="" class="pro-one-tex">一句话介绍你的团队的优势（不超过300字）</textarea></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!--下面是多个的情况 复制上面2块  end-->
            <div class="pro-clear"></div>
            <div class="pro-btn1"><a href="#">保 存</a></div>
            <div class="group"><a href="#">添加核心成员</a></div>
            <!--*************************下一步按钮************************-->
            <div class="pro-clear"></div>
            <div class="pro-btn"><a href="#">上一步</a><a href="#">下一步</a></div>
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
