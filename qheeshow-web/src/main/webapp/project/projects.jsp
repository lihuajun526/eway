<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="java.util.List" %>
<%
    List<Project> projects = (List<Project>) request.getAttribute("projects");
%>
<%
    for (Project project : projects) {
%>
<div class="g-proj-twow">
    <div class="g-proj-twol" style="cursor: pointer" onclick="openDetail(<%=project.getId()%>);"><img src="<%=project.getLogo()%>" width="80"
                                                                              height="80"/></div>
    <div class="g-proj-twoc" onclick="openDetail(<%=project.getId()%>);">
        <div class="g-proj-twoc-t" style="cursor: pointer"><%=project.getTitle()%><span><%=project.getStageName()%></span></div>
        <h3><a><%=project.getDemand() %>
        </a></h3>
    </div>
    <div class="g-proj-twor">
        <ul>
            <li><a><%=project.getAreaName()%>
            </a></li>
            <li><a><%=project.getFinancingLimitName()%>
            </a></li>
            <li><a><%=project.getIndustryName()%>
            </a></li>
        </ul>
    </div>
</div>
<%
    }
%>
<div class="i-Page">
    <%
        int len = 200;
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
<script>
    function goto(index) {
        if (index <= 0 || index ><%=pageCount%>)
            return;
        pageIndex = index;
        list();
    }
    function openDetail(id) {
        window.open("/project/" + id);
    }
</script>