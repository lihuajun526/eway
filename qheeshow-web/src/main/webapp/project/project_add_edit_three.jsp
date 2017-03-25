<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("project");
%>
<html>
<head>
    <title><%=Config.get("app.name")%>--创建项目</title>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <link rel="stylesheet" href="/images/project.css"/>
    <script src="/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp?flag=2" flush="true"/>
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
                    </li>
                </ul>
            </div>
            <div class="empty"></div>
            <div class="pro-clear"></div>
            <div class="pro-btn"><a href="/project/<%=project.getId() %>/add/edit/2">上一步</a><a
                    onclick="saveFinancing();">完成</a></div>
        </div>
    </form>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    function saveFinancing() {
        $.ajax({
            type: 'POST',
            url: '/project/financing/save',
            cache: false,
            processData: false,
            data: $('#financingForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                window.location.href = "/index";
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
