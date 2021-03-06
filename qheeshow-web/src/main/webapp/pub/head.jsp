<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loginUser = session.getAttribute("loginUser") == null ? null : (User) session.getAttribute("loginUser");
%>
<div class="g-top">
    <div class="g-logo"></div>
    <div class="g-link">
        <ul>
            <li<%="1".equals(flag) ? " class='on'" : ""%>><a href="<%=appPath%>/index">首页</a></li>
            <li<%="2".equals(flag) ? " class='on'" : ""%>><a href="<%=appPath%>/project/list">项目</a></li>
            <li<%="3".equals(flag) ? " class='on'" : ""%>><a href="<%=appPath%>/investor/list">投资人</a></li>
            <li<%="4".equals(flag) ? " class='on'" : ""%>><a href="<%=appPath%>/activity/list">活动</a></li>
        </ul>
    </div>
    <div class="g-login">
        <ul>
            <%
                if (loginUser != null) {
            %>
            <li class="on5"><a
                    href="<%=appPath%>/center/index/auth"><%=StringUtils.isEmpty(loginUser.getName()) ? loginUser.getMobile() : loginUser.getName()%>
            </a>&nbsp;<a href="<%=appPath%>/user/logout">退出</a></li>
            <li class="on4"><a href="<%=appPath%>/center/index/auth"><img
                    src="<%=StringUtils.isEmpty(loginUser.getPhoto())?appPath+"/images/wt-top3.png":loginUser.getPhoto()%>"
                    width="30"
                    height="30"/></a></li>
            <%
                if (loginUser.getRoleid().intValue() >= 20 && loginUser.getRoleid().intValue() < 30) {
            %>
            <li class="on1"><a href="<%=appPath%>/goods/list/0">购买服务</a></li>
            <li class="on2"><a href="<%=appPath%>/project/0/add/edit/1/auth">创建项目</a></li>
            <%
                }else if(loginUser.getRoleid().intValue() >= 30 && loginUser.getRoleid().intValue() < 40){
                    %><li class="on1"><a href="<%=appPath%>/goods/list/0">话费充值</a></li><%
                }
            } else {
            %>
            <li class="on1"><a href="<%=appPath%>/goods/list/0">购买服务</a></li>
            <li class="on2"><a href="<%=appPath%>/project/0/add/edit/1/auth">创建项目</a></li>
            <%--<li class="on3"><a href="<%=appPath%>/user/login.jsp"><img src="<%=appPath%>/images/wt-top3.png"/></a></li>--%>
            <li class="on5">
                <a href="<%=appPath%>/user/login.jsp">登录</a>&nbsp;
                <a href="<%=appPath%>/user/regist.jsp">注册</a>
            </li>
            <%
                }
            %>
        </ul>
    </div>
</div>
<div class="modal fade col-center-block" tabindex="-1" role="dialog" id="modal" style="width: 600px;display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <p id="tip"></p>
            </div>
            <div id="t_action" class="modal-footer">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                <%--<button type="button" class="btn btn-primary" data-dismiss="modal">知道了</button>--%>
                <%--<a href="http://www.baidu.com" class="btn btn-primary">知道了1</a>--%>
            </div>
        </div>
    </div>
</div>
<script>
    function xalert(tip) {
        $("#tip").html(tip);
        $("#t_action").html("<button type='button' class='btn btn-primary' data-dismiss='modal'>知道了</button>");
        $("#modal").modal("show");
    }
    function xalert1(tip, action, link) {
        $("#tip").html(tip);
        $("#t_action").html("<a href='" + link + "' class='btn btn-primary'>" + action + "</a>");
        $("#modal").modal("show");
    }
</script>