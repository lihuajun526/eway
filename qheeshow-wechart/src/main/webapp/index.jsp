<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    String m = request.getParameter("m");
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
</head>
<body class="wtxt-pb wtwx-warp">
<%@include file="pub/head.jsp" %>
<div id="container"></div>
<div id="more" class='dropload-refresh'></div>
<%@include file="pub/foot.jsp" %>
</body>
<script>
    function nav(obj, url) {
        $("#nav>a>li").each(function () {
            $(this).removeClass("on");
            var img = $(this).children('img')[0];
            var src = $(img).attr("src");
            $(img).attr("src", src.replace('-a', ''));
        });
        $(obj).parent().attr("class", "on");
        var src = $(obj).attr("src");
        $(obj).attr("src", src.replace('.png', '-a.png'));
        load(url);
    }
    function load(url){
        $('#container').load(url);
    }
    load('<%=m%>');
</script>
</html>
