<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.service.model.Goods" %>
<%
    List<Project> projects = (List<Project>) request.getAttribute("projects");
    List<Goods> goodses = (List<Goods>) request.getAttribute("goodses");
%>
<html>
<head>
    <title><%=Config.get("app.name")%>--优惠套餐</title>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <link rel="stylesheet" href="/images/project.css"/>
    <script src="/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="g-banner2"></div>
<div class="g-proj">
    <div class="g-pser">
        <div class="g-pser-t">选择想购买的服务</div>
        <div class="g-pser-t2"><span>温馨提示：</span>选择为您的一个项目购买服务</div>
        <ul class="g-pser-lst">
            <%
                for (Project project : projects) {
            %>
            <li onclick="checkProject(this,<%=project.getId()%>);"><a><%=project.getTitle()%>
            </a></li>
            <%
                }
            %>
        </ul>
        <div class="g-pser-t">购买服务</div>
        <div class="g-pser-t2"><span>温馨提示：</span>套餐一和套餐二支持叠加购买（可购买多次），套餐三和套餐四不支持叠加购买（仅支持一次购买）</div>
        <div class="g-pser-cnt">
            <h2>套餐一</h2>
            <div class="g-pser-cnttit">
                <div class="on1">2999</div>
                <div class="on2">现价<span>￥1299</span></div>
            </div>
            <div class="g-pser-cntont">
                <h1>投资人互动</h1>
                <h3>3个投资人／45分钟起</h3>
            </div>
            <div class="g-purchase"><a href="#">购买</a></div>
        </div>
        <div class="g-pser-cnt">
            <h2>套餐一</h2>
            <div class="g-pser-cnttit">
                <div class="on3">￥2999</div>
            </div>
            <div class="g-pser-cntont">
                <h1>项目亮点精编</h1>
                <span>投资人互动</span>
                <h3>3个投资人／45分钟起</h3>
            </div>
            <div class="g-purchase2"><a href="#">购买</a></div>
        </div>
        <div class="g-pser-cnt">
            <h2>套餐一</h2>
            <div class="g-pser-cnttit">
                <div class="on3">￥2999</div>
            </div>
            <div class="g-pser-cntont2">
                <ul>
                    <li><a href="#">场外市场挂牌</a></li>
                    <li><a href="#">场外市场挂牌</a></li>
                    <li><a href="#">场外市场挂牌</a><span>商业计划书/培训</span></li>
                    <li><a href="#">线下路演</a></li>
                    <li><a href="#">场外市场挂牌</a></li>
                    <li><a href="#">项目亮点精编</a></li>
                </ul>
            </div>
            <div class="g-purchase3"><a href="#">购买服务</a></div>
        </div>
        <div class="g-pser-cnt">
            <h2>套餐一</h2>
            <div class="g-pser-cnttit">
                <div class="on3">￥2999</div>
            </div>
            <div class="g-pser-cntont2">
                <ul>
                    <li><a href="#">场外市场挂牌</a></li>
                    <li><a href="#">场外市场挂牌</a></li>
                    <li><a href="#">场外市场挂牌</a><span>商业计划书/培训</span></li>
                    <li><a href="#">线下路演</a></li>
                    <li><a href="#">场外市场挂牌</a></li>
                </ul>
            </div>
            <div class="g-purchase3"><a href="#">购买服务</a></div>
        </div>
        <div class="clear"></div>
        <div id="cart" class="g-pser-n">
            <ul class="g-pser-nlst">
                <li id="goods1"><a href="#" class="on1">套餐一</a>
                    <ul class="g-pser-nlst2">
                        <li class="on4"><a onclick="delGoods(1);">-</a></li>
                        <li id="counter1" class="on5"></li>
                        <li class="on6"><a onclick="addGoods(1)">+</a></li>
                    </ul>
                </li>
                <li id="linker1" class="on2">+</li>
                <li id="goods2"><a class="on1">套餐二</a></li>
                <li id="linker2" class="on2">+</li>
                <li id="goods3"><a class="on1">套餐三</a></li>
                <li id="linker3" class="on2">+</li>
                <li id="goods4"><a class="on1">套餐四</a></li>
            </ul>
            <div class="g-pser-n1">合计：￥278763元</div>
            <div class="g-pser-n2"><a href="#">立即支付</a></div>
        </div>
        <!--*************************选购套餐数量************************-->
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    var projectid = 0;
    var count1 = 0, count2 = 0, count3 = 0, count4 = 0;
    var kindCount = 0;
    function checkProject(obj, id) {
        $(obj).attr("class", "on");
        projectid = id;
    }
    function addGoods(index) {
        if (index == 1) {
            count1++;
            if (count1 == 1)
                kindCount++;
            $("#counter1").html("X" + count1);
        } else if (index == 2) {
            if (count2 == 1)
                return;
            count2++;
            if (count2 == 1)
                kindCount++;
        } else if (index == 3) {
            if (count3 == 1)
                return;
            count3++;
            if (count3 == 1)
                kindCount++;
        } else if (index == 4) {
            if (count4 == 1)
                return;
            count4++;
            if (count4 == 1)
                kindCount++;
        }
        draw();
    }
    function delGoods(index) {
        if (index == 1) {
            count1--;
            if (count1 == 0)
                kindCount--;
            $("#counter1").html("X" + count1);
        } else if (index == 2) {
            count2--;
            if (count2 == 0)
                kindCount--;
        } else if (index == 3) {
            count3--;
            if (count3 == 0)
                kindCount--;
        } else if (index == 4) {
            count4--;
            if (count4 == 0)
                kindCount--;
        }
        draw();
    }
    function draw() {
        var sum = 0;
        var kindCount = kindCount - 1;
        if (kindCount > 0)
            $("#cart").show();
        else {
            $("#cart").hide();
            return;
        }
        if (count1 > 0) {
            $("#goods1").show();
            if (sum < kindCount) {
                $("#linker1").show();
                sum++;
            } else
                $("#linker1").hide();
        } else
            $("#goods1").hide();

        if (count2 > 0) {
            $("#goods2").show();
            if (sum < kindCount) {
                $("#linker2").show();
                sum++;
            } else
                $("#linker2").hide();
        } else
            $("#goods2").hide();

        if (count3 > 0) {
            $("#goods3").show();
            if (sum < kindCount) {
                $("#linker3").show();
                sum++;
            } else
                $("#linker3").hide();
        } else
            $("#goods3").hide();

        if (count4 > 0) {
            $("#goods4").show();
        } else
            $("#goods4").hide();

    }


</script>
</html>
