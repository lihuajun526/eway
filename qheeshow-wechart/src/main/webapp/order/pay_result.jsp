<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Order" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%
    String appPath = Config.get("app.path");
    Order order = (Order) request.getAttribute("order");
    Project project = (Project) request.getAttribute("project");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
</head>
<body>
<div>
    <div class="wx-pay-ok"><img src="<%=appPath%>/images/wtwx-icon40.png" width="110" height="110"/><span>支付成功</span>
    </div>
    <ul class="wx-pay-lst">
        <li>商品名称<span><%=order.getTitle()%></span></li>
        <li>付款金额<span>￥<%=order.getPrice()%>元</span></li>
        <li>下单时间<span><%=sdf.format(order.getCreateTime())%></span></li>
        <%
            if (project.getId() != null && project.getId().intValue()>0) {
        %>
        <li>服务项目<span><%=project.getTitle()%></span></li>
        <%
            }
        %>
    </ul>
    <div class="wx-pay-btn"><a href="javascript:" onclick="self.location=document.referrer;">返回</a></div>
</div>
</body>
</html>
