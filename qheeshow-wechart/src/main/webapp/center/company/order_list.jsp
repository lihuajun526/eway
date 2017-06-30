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
    <div class="wx-callmmone"><img src="<%=appPath%>/images/wtwx-icon54.png" width="212"
                                   height="152"/><span>对不起，暂无订单</span>
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
            } else if (order.getProjectid().intValue() == 0) {
            %>话费<%
            } else {
                String title = order.getTitle();
            %><%=title.length() > 9 ? title.substring(0, 8) + "..." : title%><%
                }
            %>
            </h1>
            <h4>
                <%
                    if (order.getProjectid() == null) {
                        String title = order.getTitle();
                        title = title.substring(title.indexOf("[") + 1, title.lastIndexOf("]") - 1);
                        title = title.length() > 12 ? title.substring(0, 11) + "..." : title;
                %>活动：<%=title%><%
            } else if (order.getProjectid().intValue() == 0) {
            %>话费充值<%
            } else {
                Project project = map.get(order.getId());
                String title = project.getTitle();
                title = title.length() > 12 ? title.substring(0, 11) + "..." : title;
            %>项目：<%=title%><%
                }
            %>
            </h4>
        </div>
        <%
            if (order.getStatus().intValue() == 1) {
        %><span class="wtwx-order-on3" onclick="payOrder(<%=order.getId()%>)">立即支付</span><%
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
    function payOrder(id) {
        $.get("<%=appPath%>/order/do/pay/WECHART/" + id + "/v_authj", function (result) {
            if (result.code < 0) {
                openTip(result);
                return;
            }
            //发起支付
            onBridgeReady(result.data.data);
        }, "json");
    }

    function onBridgeReady(result) {
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId": result.appid,     //公众号名称，由商户传入
                    "timeStamp": result.timeStamp,         //时间戳，自1970年以来的秒数
                    "nonceStr": result.nonce_str, //随机串
                    "package": "prepay_id=" + result.prepay_id,
                    "signType": "MD5",         //微信签名方式：
                    "paySign": result.sign //微信签名
                },
                function (res) {
                    if (res.err_msg == "get_brand_wcpay_request:ok") {// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                        window.location.href = "<%=appPath%>/order/do/get/" + result.orderno;
                    }
                }
        );
    }
</script>
</html>