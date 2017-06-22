<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Activity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Activity activity = (Activity) request.getAttribute("activity");
    Boolean isFull = (Boolean) request.getAttribute("isFull");
    String flag = "4";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--<%=activity.getTitle()%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/images/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div id="qr_div" class="g-pw-body" style="display: none;">
    <div class="shade"></div>
    <div class="g-pw-radius">
        <div class="g-pw-radius-top"></div>
        <div class="g-pw2">
            <div id="qr_txt" class="g-pw2-w2">微信支付</div>
            <div class="g-pw2-w3">
                <img id="qr_code" src="" width="275" height="275"/>
                <span><font id="qr_tip">微信扫码支付</font>&nbsp;<a id="cancle_" onclick="javascript:$('#qr_div').hide()">取消</a></span>
            </div>
        </div>
        <div class="g-pw-radius-bottom"></div>
    </div>
</div>
<div class="g-proj">
    <div class="g-conter">
        <div class="g-actlst-one-warp">
            <div class="g-actlst-onel"><img src="<%=activity.getLogo()%>" width="440" height="250"/></div>
            <div class="g-actlst-oneer">
                <h1><%=activity.getTitle()%>
                </h1>
                <ul class="g-actlst-onelst">
                    <li class="on1">2017年3月28日 8:00-2017年3月28日 18:00</li>
                    <li class="on2"><%=activity.getAddress()%>
                    </li>
                    <li class="on3">限额<%=activity.getLimitNum()%>人</li>
                    <li class="on4"><span><img src="<%=appPath%>/images/wt-icon26.png"/></span><span
                            class="on5"><%=activity.getSponsor()%></span><span
                            class="on6"><a href="#">联系主办方：<%=activity.getTel()%>
                    </a></span></li>
                </ul>
                <ul class="g-actlst-onelst2">
                    <li>
                        <%
                            if (isFull) {
                        %><input id="signBtn" type="button" value="名额已满" class="g-actlst-btn2"><%
                    } else {
                    %><input id="signBtn" onclick="activitySign(<%=activity.getId()%>);" type="button" value="我要报名"
                             class="g-actlst-btn1"><%
                        }
                    %>
                    </li>
                </ul>
            </div>
        </div>
        <div class="g-actlst-twol">
            <div class="g-actlst-twol-tit"><span>活动内容</span></div>
            <div class="g-actlst-twol-cnt">
                <ul class="g-actlst-twolst2"><%=activity.getContent()%>
                </ul>
            </div>
        </div>
        <div class="g-actlst-twor">
            <div class="g-actlst-two-tit">活动地址</div>
            <ul class="g-actlst-two-lst">
                <li><img src="<%=activity.getBaiduMap()%>"/></li>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
</div>
<%@include file="../pub/foot.jsp" %>
</body>
<script>
    var orderid = 0;
    function activitySign(actid) {
        $.get("<%=appPath%>/activity/sign/" + actid + "/authj", function (result) {

            if (result.code < 0) {
                if (result.code == -1) {
                    xalert1(result.message, "去登录", "<%=appPath%>/user/login.jsp");
                } else {
                    xalert(result.message);
                }
            } else {
                if (result.code == 1) {
                    $("#signBtn").val("已报名");
                    $("#signBtn").attr("class", "g-actlst-btn2");
                } else if (result.code == 2) {
                    $("#qr_div").show();
                    orderid = result.data.orderid;
                    $("#qr_code").attr("src", result.data.qrcode);
                    timer1 = window.setInterval(getStatus, 3000);
                } else
                    xalert(result.message);
            }
        }, "json");
    }
    <%
        if(loginUser!=null){
        %>
    $.get("<%=appPath%>/activity/issign/<%=activity.getId()%>", function (result) {
        if (result.data) {
            $("#signBtn").val("已报名");
            $("#signBtn").attr("class", "g-actlst-btn2");
        }
    }, "json");
    <%
    }
%>
    var timer1 = 0;
    var counter1 = 0;
    function getStatus() {
        counter1++;
        if (counter1 > 100) {//5分钟之内完成支付
            window.clearInterval(timer1);
            return;
        }
        $.get("<%=appPath%>/order/status/" + orderid + "?r=" + Math.random(), function (result) {
            if (result.code >= 0 && result.data == 2) {
                $("#cancle_").hide();
                $("#qr_tip").html("支付成功&nbsp;<a onclick='refres();'>返回</a>");
            }
        }, "json");
    }
    function refres() {
        window.location.reload();
    }
</script>
</html>
