<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>筛选项目</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
</head>
<body>
<div class="wt-search">
    <input type="text" placeholder="搜索" class="wt-search-ipt"/>
    <a href="#" class="wt-search-icon"></a>
</div>
<div>
    <div class="wt-search-tit">状态</div>
    <ul class="wt-search-cnt">
        <li class="on"><a href="#">全部</a></li>
        <li><a href="#">预热中</a></li>
        <li><a href="#">合投中</a></li>
        <li><a href="#">新上线</a></li>
        <li><a href="#">已成功</a></li>
    </ul>
    <div class="wt-search-tit">地区</div>
    <ul class="wt-search-cnt">
        <li class="on"><a href="#">全部</a></li>
        <li><a href="#">广州</a></li>
        <li><a href="#">广州</a></li>
        <li><a href="#">广州</a></li>
        <li><a href="#">广州</a></li>
        <li><a href="#">广州</a></li>
        <li><a href="#">广州</a></li>
        <li><a href="#">广州</a></li>
        <li><a href="#">广州</a></li>
        <li><a href="#">广州</a></li>
    </ul>
    <div class="wt-search-tit">领域</div>
    <ul class="wt-search-cnt">
        <li class="on"><a href="#">全部</a></li>
        <li><a href="#">预热中</a></li>
        <li><a href="#">合投中</a></li>
        <li><a href="#">金融</a></li>
        <li><a href="#">消费升级</a></li>
        <li><a href="#">产业互联网</a></li>
        <li><a href="#">企业服务</a></li>
        <li><a href="#">科技智能</a></li>
    </ul>
    <div class="wt-search-btn2">
        <a href="#" class="on1">取消</a>
        <a href="#" class="on2">确定</a>
    </div>

</div>


</body>
</html>
