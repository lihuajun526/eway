<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String appPath = Config.get("app.path");
%>
<html>
<body>
<div class="g-foot">
  <div class="g-conter">
    <div class="g-foot-l">
      <div class="g-fl-t">
        <span class="g-fl-tit"></span>
        <h3>MUX倾力打造的矢量图标管理、交流平台。设计师将图标上传到Iconfont平台，用户可以自定义下载多种格式的icon，平台也可将图标。</h3>
      </div>
      <ul class="g-fl-lst">
        <li><a href="#">服务条款</a></li>
        <li><a href="#">服务条款</a></li>
        <li><a href="#">服务条款</a></li>
        <li><a href="#">服务条款</a></li>
      </ul>
      <div class="g-fl-Remarks">服务热线:020-32039118  /   粤ICP备14063324号  /  ©2 建议用IE8.0 以上浏览器浏览</div>
    </div>
    <div class="g-foot-r">
      <div class="g-fr-t">友情链接</div>
      <ul class="g-fr-lst">
        <li><a href="#">THX</a></li>
        <li><a href="#">阿里妈妈MUX</a></li>
        <li><a href="#">钱庄网</a></li>
        <li><a href="#">网易财经</a></li>
        <li><a href="#">21财经</a></li>
        <li><a href="#">虎嗅</a></li>
        <li><a href="#">36kr</a></li>
        <li><a href="#">搜狐财经</a></li>
        <li><a href="#">新浪网</a></li>
      </ul>
      <div class="g-fr-ewm">不错过任何一个好产品<span><img src="<%=appPath%>/images/wt-ewm.png"/></span></div>
    </div>
  </div>
</div>
<script type="text/javascript" src="<%=appPath%>/js/placeholder.min.js"></script>
<script>
  $(function() {
    $('input, textarea').placeholder();
  });
</script>
</body>
</html>
