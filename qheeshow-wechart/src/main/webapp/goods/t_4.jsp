<%@ page import="com.qheeshow.eway.service.model.Goods" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Goods goods = (Goods) request.getAttribute("goods");
    Integer flag = (Integer) request.getAttribute("flag");
%>
<div class="wtwx-package-right-h">
    <div class="wtwx-package-r-lst">
        <div class="wtwx-package-r-limg"></div>
        <h2>投资人互动</h2>
        <h3>可以和3位投资电话沟通45分钟</h3>
    </div>
    <div class="wtwx-package-r-lst">
        <div class="wtwx-package-r-limg"></div>
        <h2>线下路演</h2>
        <h3>线下路演</h3>
    </div>
    <div class="wtwx-package-r-lst">
        <div class="wtwx-package-r-limg"></div>
        <h2>资本孵化</h2>
        <h3>资本孵化</h3>
    </div>
    <div class="wtwx-package-r-lst">
        <div class="wtwx-package-r-limg"></div>
        <h2>双平台展示</h2>
        <h3>双平台展示</h3>
    </div>
    <div class="wtwx-package-r-lst">
        <div class="wtwx-package-r-limg"></div>
        <h2>项目亮点精编</h2>
        <h3>项目亮点精编</h3>
    </div>
</div>
<div class="wtwx-package-r-botom">
    <span>￥<%=goods.getPrice()%></span>
    <ul class="wtwx-package-r-botomlst">
        <li id="i1" onclick="add(this)"><a></a></li>
        <li id="i2">0</li>
        <li id="i3" onclick="del(this)" class="on4"><a></a></li>
    </ul>
</div>
<script>
    curGoods = 4;
    var flag = <%=flag%>;
    price4 = <%=goods.getPrice()%>;
    function init() {
        if (flag == 1) {
            $("#i1").attr("class", "on1");
        } else {
            $("#i1").attr("class", "on5");
        }
        if (goods4 == 0) {
            $("#i3").attr("class", "on4");
        } else {
            $("#i3").attr("class", "on3");
        }
        $("#i2").html(goods4);
    }
    init();
</script>
