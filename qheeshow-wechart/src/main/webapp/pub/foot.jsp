<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="nav" class="wtwx-menu">
  <a><li class="on"><img onclick="load(this,'project/project_list.html')" src="images/wtwx-menu1-a.png" width="25" height="25"/><span>项目</span></li></a>
  <a><li><img onclick="load(this,'investor/investor_list.html')" src="images/wtwx-menu2.png" width="25" height="25"/><span>投资人</span></li></a>
  <a><li><img onclick="load(this,'activity/activity_list.html')" src="images/wtwx-menu3.png" width="25" height="25"/><span>活动</span></li></a>
  <a><li><img onclick="load(this,'project/project_list.html')" src="images/wtwx-menu4.png" width="25" height="25"/><span>我的</span></li></a>
</ul>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script type="text/javascript" src="<%=appPath%>/dot/doT.min.js"></script>
<script type="text/javascript" src="<%=appPath%>/js/config.js"></script>