<%@ page import="com.qheeshow.eway.service.model.Goods" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Goods goods = (Goods) request.getAttribute("goods");
    Integer flag = (Integer) request.getAttribute("flag");
%>
<div class="wtwx-package-right-h">
    <div class="wtwx-package-r-lst">
        <div class="wtwx-package-r-limg"></div>
        <h2>路演培育系统</h2>
        <h3>①项目路演策划及组织</h3>
        <h3>②商业计划书（路演版）撰写逻辑与制作</h3>
        <h3>③项目路演技巧培训</h3>
        <h3>④投资逻辑研究</h3>
    </div>
    <div class="wtwx-package-r-lst">
        <div class="wtwx-package-r-limg"></div>
        <h2>路演推荐服务</h2>
        <h3>①专场路演展示，通过线下把项目推荐给平台上的投资人</h3>
        <h3>②融资项目通过线上7*24小时全天候，推荐给平台上的投资人</h3>
    </div>
    <div class="wtwx-package-r-lst">
        <div class="wtwx-package-r-limg"></div>
        <h2>投资人约见服务</h2>
        <h3>①线上向投资人推送融资项目，约见投资人</h3>
        <h3>②服务期内，每季度向项目企业批量推送有意向的投资人</h3>
        <h3>③项目企业动态实时跟踪并记录，提升企业融资成功效率</h3>
    </div>
    <div class="wtwx-package-r-lst">
        <div class="wtwx-package-r-limg"></div>
        <h2>资本孵化服务</h2>
        <h3>①股权融资顾问</h3>
        <h3>②融资路线服务</h3>
        <h3>③资本引荐服务</h3>
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
    curGoods = 2;
    var flag = <%=flag%>;
    price2 = <%=goods.getPrice()%>;
    function init() {
        if (flag == 1) {
            $("#i1").attr("class", "on1");
        } else {
            $("#i1").attr("class", "on5");
        }
        if (goods2 == 0) {
            $("#i3").attr("class", "on4");
        } else {
            $("#i3").attr("class", "on3");
        }
        $("#i2").html(goods2);
    }
    init();
</script>
