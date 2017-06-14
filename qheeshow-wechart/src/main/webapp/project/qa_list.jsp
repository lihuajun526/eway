<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.service.model.ProjectQa" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.qheeshow.eway.service.model.User" %>
<%
    List<ProjectQa> qs = (List<ProjectQa>) request.getAttribute("qs");
    Map<Integer, ProjectQa> aMap = (Map<Integer, ProjectQa>) request.getAttribute("aMap");
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
    Integer cruser = (Integer) request.getAttribute("cruser");
    Object o = session.getAttribute("loginUser");
    boolean canR = false;
    if (o != null) {
        User loginUser = (User) o;
        canR = loginUser.getId().intValue() == cruser.intValue();
    }
%>
<%
    for (ProjectQa q : qs) {
%>
<div class="wtwx-project-cnt3">
    <div class="wtwx-project-cnt3-l"><img src="<%=q.getPhoto()%>" width="36" height="36"/></div>
    <div class="wtwx-project-cnt3-r">
        <h1><%=URLDecoder.decode(q.getName(), "utf-8")%>
        </h1>

        <div class="wtwx-project-cnt3-rcnt">
            <%=q.getContent()%>
            <%
                if (aMap.get(q.getId()) == null && canR) {
            %>
            <div class="wtwx-project-cancel"><a href="#<%=q.getId()%>F"
                                                onclick="openOrClose(<%=q.getId()%>,this)">回复</a></div>
            <%
                }
            %>
        </div>
        <%
            if (aMap.get(q.getId()) == null) {
        %>
        <div id="<%=q.getId()%>F" class="wtwx-project-cnt3-warp" style="display: none">
            <input id="content<%=q.getId()%>" class="ipt" placeholder="请输入回复"/>
            <a onclick="a(<%=q.getId()%>,<%=q.getUserid()%>)" class="btn">确定</a>
        </div>
        <%
        } else {
            ProjectQa a = aMap.get(q.getId());
        %>
        <div class="wtwx-project-cnt3-rcnt2">
            <span><%=URLDecoder.decode(a.getName(), "utf-8")%>：</span><%=a.getContent()%>
        </div>
        <%
            }
        %>
    </div>
    <a class="wtwx-project-cnt3-praise"><%=sdf.format(q.getCreateTime())%>
    </a>
</div>
<%
    }
%>
<script>
    function openOrClose(id, obj) {
        if ($(obj).html() == "回复") {
            $("#content" + id).parent().show();
            $(obj).html("取消");
        } else if ($(obj).html() == "取消") {
            $("#content" + id).parent().hide();
            $(obj).html("回复");
        }
    }
</script>
