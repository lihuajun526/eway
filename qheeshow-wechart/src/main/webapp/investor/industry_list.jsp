<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%
    String appPath = Config.get("app.path");
    List<Xwcmclassinfo> industrys = (List<Xwcmclassinfo>) request.getAttribute("industrys");
    Map<Integer, Integer> map = (HashMap<Integer, Integer>) request.getAttribute("map");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>选择投资领域</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
</head>
<body class="wtxt-pb">
<%@include file="../pub/head.jsp" %>
<div>
    <%
        for (Xwcmclassinfo industry : industrys) {
    %>
    <div class="wtwx-investors-cnt1">
        <div class="wtwx-investors-cnt1-l"><img src="<%=appPath%>/images/wtwx-icon23.png" width="45" height="44"/></div>
        <a href="<%=appPath%>/index.jsp?indusid=<%=industry.getClassinfoid()%>">
            <div class="wtwx-investors-cnt1-r">
                <h1><%=industry.getCname()%>
                </h1>
                <h4><%=map.get(industry.getClassinfoid())%>
                </h4>
            </div>
            <span class="wtwx-investors-field-arrow"></span>
        </a>
    </div>
    <%
        }
    %>
</div>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>

</script>
</body>
</html>
