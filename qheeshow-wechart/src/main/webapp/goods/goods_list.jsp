<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Project> projects = (List<Project>) request.getAttribute("projects");
    Integer projectid = (Integer) request.getAttribute("projectid");
    String appPath = Config.get("app.path");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/dropload.css"/>
    <title>购买套餐</title>
</head>
<body class="wtxt-package">
<%@include file="../pub/head.jsp" %>
<div>
    <ul class="wtwx-package-lst">
        <%
            for (int i = 0; i < projects.size(); i++) {
                Project project = projects.get(i);
                if (i == 0) {
        %>
        <a>
            <li class="on1">
                <p><%=project.getTitle().length() > 12 ? project.getTitle().substring(0, 11) + "..." : project.getTitle()%>
                </p></li>
        </a>
        <%
                continue;
            }
        %>
        <a>
            <li>
                <p><%=project.getTitle().length() > 12 ? project.getTitle().substring(0, 11) + "..." : project.getTitle()%>
                </p></li>
        </a>
        <%
            }
        %>
    </ul>
    <div class="wtwx-package-prompt"><%=projects.size() == 0 ? "提示：请先登录梧桐e路电脑版创建项目" : ""%>
    </div>
    <div class="wtwx-package-left">
        <ul id="goodses" class="wtwx-package-left-lst">
            <li class="on" onclick="load(this,'<%=appPath%>/goods/do/get/1')"><a>套餐一</a></li>
            <li onclick="load(this,'<%=appPath%>/goods/do/get/2')"><a>套餐二</a></li>
            <li onclick="load(this,'<%=appPath%>/goods/do/get/3')"><a>话费充值</a></li>
        </ul>
    </div>
    <div id="items" class="wtwx-package-right"></div>
</div>
<div class="wtwx-package-menu">
    价格：￥<span id="sum"></span>元
    <a onclick="place()" class="wtwx-package-menu-pay">立即付款</a>

    <div class="wtwx-package-menu-cart"></div>
</div>

</body>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
    var curGoods = 1;
    var curProject = <%=projectid%>;
    var projectid = curProject;
    function load(obj, url) {
        $("#goodses>li").each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
        $("#items").html("加载中...");
        $("#items").load(url);
    }
    $("#items").load("<%=appPath%>/goods/do/get/1");
    function place() {
        $.post("<%=appPath%>/goods/do/preorder/WECHAT/v_authj", {
            "goodsid": curGoods,
            "projectid": projectid
        }, function (result) {
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

