<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%
    String appPath = Config.get("app.path");
    Object o = session.getAttribute("loginUser");
    if (o != null) {
        User loginUser = (User) o;
        Investor investor = (Investor) request.getAttribute("investor");
        String photo = StringUtils.isEmpty(loginUser.getPhoto()) ? loginUser.getHeadimgurl() : loginUser.getPhoto();
%>
<div>
    <div class="wtwx-user">
        <div class="wtwx-user-l"><img src="<%=photo%>" width="55" height="55"/></div>
        <div class="wtwx-user-r">
            <h1><%=loginUser.getName()%>
            </h1>
            <%
                if (investor != null) {
            %><h4><%=investor.getCompanyRank()%> | <%=investor.getCompanyName()%>
        </h4><%
            }
        %>
        </div>
    </div>
    <ul class="wtwx-user-lst">
        <%
            if (loginUser.getRoleid().intValue() >= 30 && loginUser.getRoleid().intValue() < 40) {//投资人
        %>
        <li class="on7"><a href="#">项目管理</a></li>
        <%
        } else if (loginUser.getRoleid().intValue() >= 20 && loginUser.getRoleid().intValue() < 30) {//创业者
        %>
        <li class="on6">剩余通话时间<span class="wtwx-user-num"><%=loginUser.getCallTime()%>分钟</span><a href="#"
                                                                                                  class="wtwx-user-btn">充值</a>
        </li>
        <li class="on2"><a href="#">购买的服务</a></li>
        <li class="on3"><a href="#">我的金融顾问</a></li>
        <li class="on4"><a href="#">关注的投资人</a></li>
        <li class="on5"><a href="#">订单</a></li>
        <%
            }
        %>

    </ul>
</div>
<%
    }
%>
<script>
    $("#more").empty();
</script>

