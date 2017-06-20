<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    List<Project> projects = (List<Project>) request.getAttribute("projects");
%>
<div class="g-mg-rwap">
    <ul class="g-mg-rtitlst">
        <li onclick="setType(1)" class="on1"><a>平台推荐</a></li>
        <li onclick="setType(2)" class="on1"><a>关注的项目</a></li>
        <li onclick="setType(3)" class="on"><a>金融顾问</a></li>
    </ul>
    <%
        for (Project project : projects) {
    %>
    <div class="g-invest-tre">
        <div class="g-invest-treimg2"><a href="<%=appPath%>/project/<%=project.getId()%>" target="_blank"><img
                src="<%=project.getLogo()%>" width="80" height="80"/></a></div>
        <div class="g-invest-tre-r">
            <div class="g-mg-rtit2"><a href="<%=appPath%>/project/<%=project.getId()%>" target="_blank"><%=project.getTitle()%>
            </a></div>
            <div class="g-mg-rtit3"><a><%=project.getDemand()%>
            </a></div>
            <div class="g-mg-rtit4"></div>
        </div>
    </div>
    <%
        }
    %>
    <div class="i-Page">
        <%
            int len = 6;
            int index = (Integer) request.getAttribute("pageIndex");
            int start = index < len ? 1 : index - (len - 2);
            int pageCount = (Integer) request.getAttribute("pageCount");
        %>
        <a onclick="goto(<%=index-1%>)" class="on1">&nbsp;</a>
        <%
            for (int i = 0; i < len; i++, start++) {
                if (start == pageCount) {
        %><a onclick="goto(<%=start%>)" <%=start == index ? "class='on'" : "" %>><%=start%>
    </a><%
            break;
        }
        if ((i + 1) == len) {
    %><a onclick="goto(<%=start%>)">...</a><%
    } else {
    %><a onclick="goto(<%=start%>)" <%=start == index ? "class='on'" : "" %>><%=start%>
    </a><%
            }
        }
    %>
        <a onclick="goto(<%=index+1%>)" class="on2">&nbsp;</a>
    </div>
</div>
<script>
    function goto(index) {
        if (index <= 0 || index ><%=pageCount%>)
            return;
        load("<%=appPath%>/center/project/3/5/" + index);
    }
    function setType(v) {
        load("<%=appPath%>/center/project/" + v + "/5/1");
    }
</script>