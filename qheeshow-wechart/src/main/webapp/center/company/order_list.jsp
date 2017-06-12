<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%
    String appPath = Config.get("app.path");
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    Map<Integer, Project> map = (HashMap<Integer, Project>) request.getAttribute("map");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>订单</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
</head>
<body>
<div>
    <%
        if (orders == null || orders.size() == 0) {
    %>
    <div class="wx-callmmone"><img src="<%=appPath%>/images/wtwx-icon54.png" width="212" height="152"/><span>对不起，暂无订单</span>
    </div>
    <%
    } else {
        for (Order order : orders) {
    %>
    <div class="wtwx-investors-cnt1">
        <div class="wtwx-order-l">￥<%=order.getPrice()%>
        </div>
        <div class="wtwx-order-r">
            <h1>
                <%
                    if (order.getProjectid() == null) {
                %>报名费<%
            } else {
            %><%=order.getTitle()%><%
                }
            %>
            </h1>
            <h4>
                <%
                    if (order.getProjectid() == null) {
                        String title = order.getTitle();
                %>活动：<%=title.substring(title.indexOf("[") + 1, title.lastIndexOf("]") - 1)%><%
            } else {
                Project project = map.get(order.getId());
            %>项目：<%=project.getTitle()%><%
                }
            %>
            </h4>
        </div>
        <%
            if (order.getStatus().intValue() == 1) {
        %><span class="wtwx-order-on3">立即支付</span><%
    } else {
    %><span class="wtwx-order-on1">已支付</span><%
        }
    %>
        <span class="wtwx-order-on2"><%=sdf.format(order.getCreateTime())%></span>
    </div>
    <%
            }
        }
    %>
</div>
</body>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
</script>
</html>