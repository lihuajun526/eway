<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    List<Project> projects = (List<Project>) request.getAttribute("projects");
%>
<div class="g-mgen">
    <ul>
        <li class="on1"><a href="<%=appPath%>/project/0/add/edit/1/auth" target="_blank"><img
                src="<%=appPath%>/images/add-pr.png" width="100"
                height="100"/></a></li>
        <li class="on4">创建项目</li>
        <li class="on5">一个账户最多创建5个目</li>
    </ul>
</div>
<%
    for (Project project : projects) {
%>
<div class="g-mgen">
    <ul>
        <li class="on1"><img src="<%=project.getLogo()%>" width="100" height="100"/></li>
        <li class="on2" title="<%=project.getTitle()%>">
            <%=project.getTitle().length() > 11 ? project.getTitle().substring(0, 10) + "..." : project.getTitle()%>
        </li>
        <%--<li class="on3">
            <a href="#">公开</a>
            <!-- ----------下拉 隐藏了---------->
            <ul class="g-mgen-lst1" style="display:none;">
                <li><a href="#">公开</a></li>
                <li><a href="#">不公开</a></li>
            </ul>
        </li>--%>
    </ul>
    <a href="<%=appPath%>/project/<%=project.getId()%>/add/edit/1/auth" target="_blank" class="g-mgen-1">编辑</a>

    <div class="g-mgen-2">关注人数<span><%=project.getFollows()%></span></div>
</div>
<%
    }
%>
