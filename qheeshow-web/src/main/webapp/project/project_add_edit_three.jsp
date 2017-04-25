<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Project project = (Project) request.getAttribute("project");
    String flag = "2";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--创建项目</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/project.css"/>
    <script src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp"%>
<div class="pro-body">
    <form id="financingForm">
        <input type="hidden" id="lastStage" name="lastStage" value=""/>
        <input type="hidden" name="id" value="<%=project.getId()%>"/>

        <div class="pro-wap">
            <div class="pro-t">上一轮融资情况(3/3)</div>
            <div class="empty"></div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">投资机构<span>（选填）</span></li>
                    <li class="on2"><input name="lastInvestment" type="text" class="pro-one-ipt" placeholder="填写上一轮投资机构"
                                           value="<%=StringUtils.isEmpty(project.getLastInvestment())?"":project.getLastInvestment()%>"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">融资轮次<span>（选填）</span></li>
                    <li class="on8">
                        <ul class="pro-fo-lst">
                            <li onclick="setLastStage(this,'种子轮');" <%="种子轮".equals(project.getLastStage()) ? "class='on'" : ""%>>
                                <a>种子轮</a></li>
                            <li onclick="setLastStage(this,'天使轮');" <%="天使轮".equals(project.getLastStage()) ? "class='on'" : ""%>>
                                <a>天使轮</a></li>
                            <li onclick="setLastStage(this,'A轮');" <%="A轮".equals(project.getLastStage()) ? "class='on'" : ""%>>
                                <a>A轮</a></li>
                            <li onclick="setLastStage(this,'B轮');" <%="B轮".equals(project.getLastStage()) ? "class='on'" : ""%>>
                                <a>B轮</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">融资金额<span>（选填）</span></li>
                    <li class="on2"><input name="lastLimit" type="text" class="pro-one-ipt" placeholder="填写上一轮融资金额"
                                           value="<%=StringUtils.isEmpty(project.getLastLimit())?"":project.getLastLimit() %>"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="empty"></div>
            <div class="pro-clear"></div>
            <div class="pro-btn"><a href="<%=appPath%>/project/<%=project.getId() %>/add/edit/2/auth">上一步</a><a
                    onclick="saveFinancing();">完成</a></div>
        </div>
    </form>
</div>
<%@include file="../pub/foot.jsp"%>
</body>
<script>
    function saveFinancing() {
        $.ajax({
            type: 'POST',
            url: '<%=appPath%>/project/financing/save/authj',
            cache: false,
            processData: false,
            data: $('#financingForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                window.location.href = "<%=appPath%>/index";
            }
        });
    }
    function setLastStage(obj, value) {
        $(obj).parent().children('li').each(function () {
            $(this).removeClass("on")
        });
        $(obj).attr("class", "on");
        $("#lastLimit").val(value);
    }
</script>
</html>
