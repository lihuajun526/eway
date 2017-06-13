<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="java.util.List" %>
<%
    String appPath = Config.get("app.path");
    List<Investor> investors = (List<Investor>) request.getAttribute("investors");
    Integer size = 0;
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>我关注的投资人</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
</head>
<body>
<div id="m">
    <%
        if (investors == null || investors.size() == 0) {
    %>
    <div class="wx-callmmone"><img src="<%=appPath%>/images/wtwx-icon54.png" width="212"
                                   height="152"/><span>您没有关注的投资人</span>
    </div>
    <%
    } else {
        size = investors.size();
        for (Investor investor : investors) {
    %>
    <div id="inv_<%=investor.getId()%>" class="wtwx-investors-cnt1">
        <a href="<%=appPath%>/investor/do/<%=investor.getId()%>">
            <div class="wtwx-adviser-l"><img src="<%=investor.getPhoto()%>" width="41" height="41"/></div>
            <div class="wtwx-adviser-r"><%=investor.getTrueName()%><span>|</span><span><%=investor.getCompanyName()%> <%=investor.getCompanyRank()%></span>
            </div>
        </a>
        <span onclick="unF(<%=investor.getId()%>)" class="wtwx-adviser-on2">取消关注</span>
    </div>
    <%
            }
        }
    %>
</div>
</body>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
    var size = <%=size%>;
    function unF(id) {
        $.get('<%=appPath%>/user/center/do/unfollow/' + id, function (result) {
            if (result.code >= 0) {
                $("#inv_" + id).remove();
                size--;
                if (size == 0) {
                    $("#m").before("<div class='wx-callmmone'><img src='<%=appPath%>/images/wtwx-icon54.png' width='212' height='152'/><span>您没有关注的投资人</span></div>");
                }
            }
        }, "json");
    }
</script>
</html>