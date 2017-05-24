<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Activity" %>
<%
    String appPath = Config.get("app.path");
    Activity activity = (Activity) request.getAttribute("activity");
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
<body class="wtwx-warp2">
<%@include file="../pub/head.jsp" %>
<div class="wtwx-activity-cnt2">
    <div><img src="<%=activity.getLogo()%>" width="100%"/><span class="on1"></span></div>
    <h1><%=activity.getTitle()%>
    </h1>

    <div class="wtwx-activity-lst2">
        <div class="time"><img src="../../../images/wtwx-icon7.png" width="18" height="18"/>时间：</div>
        <div class="time-cnt"><%=activity.getsBeginTime()%>
        </div>
    </div>
    <div class="wtwx-activity-lst2">
        <div class="time"><img src="../../../images/wtwx-icon8.png" width="18" height="18"/>地点：</div>
        <div class="time-cnt"><%=activity.getAddress()%>
        </div>
    </div>
    <div class="wtwx-activity-lst2">
        <div class="number"><img src="../../../images/wtwx-icon9.png" width="18"
                                 height="18"/>报名人数：<span>100/<%=activity.getLimitNum()%></span></div>
    </div>
    <div class="wtwx-activity-lst2">
        <div class="number"><img src="../../../images/wtwx-icon10.png" width="18" height="18"/>报名费：<span
                class="red">￥<%=activity.getCost()%></span></div>
    </div>
</div>
<div class="wtwx-activity-cnt2">
    <div class="wtwx-activity-tit">活动介绍</div>
    <div class="wtwx-activity-conter"><%=activity.getSummary()%>
    </div>
</div>
<div class="wtwx-activity-cnt3">
    <div class="wtwx-activity-tit">活动详情</div>
    <div class="wtwx-activity-conter">
        <ul class="wtwx-activity-lst3"><%=activity.getContent()%>
        </ul>
    </div>
</div>
<%
    if (activity.getStyle().equals("on1")) {
%><div class="wtwx-activity-ipt1"><a onclick="preOrder()">立即报名</a></div><%
} else {
%><div class="wtwx-activity-ipt2"><a>已爆满</a></div>
<%
    }
%>
</body>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
    function preOrder() {
        $.get("<%=appPath%>/activity/do/preorder/wechat/<%=activity.getId()%>/v_auth", function (result) {
            if (result.code == -1) {
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
                    if (res.err_msg == "get_brand_wcpay_request:ok") {
                        alert("oko");
                    }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                }
        );
    }
</script>
</html>
