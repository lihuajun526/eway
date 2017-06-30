<%@ page import="com.qheeshow.eway.service.model.Goods" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Goods goods = (Goods) request.getAttribute("goods");
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
</div>
<script>
    curGoods = 1;
    projectid = curProject;
    $("#sum").html(<%=goods.getPrice()%>);
</script>
