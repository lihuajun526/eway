<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="java.util.List" %>
<%
    List<Project> projects = (List<Project>) request.getAttribute("projects");
%>
<html>
<head>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <link rel="stylesheet" href="/images/project.css"/>
    <script src="/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<div class="g-proj-two">

    <%
        for (Project project : projects) {
    %>
    <div class="g-proj-twow">
        <div class="g-proj-twol"><img src="<%=project.getLogo() %>" width="80" height="80"/></div>
        <div class="g-proj-twoc">
            <div class="g-proj-twoc-t"><%=project.getTitle() %><span><%=project.getStageName()%></span></div>
            <h3><a><%=project.getDemand()%>
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
            int len = 6;
            int index = 4;
            int start = index < len ? 1 : index - (len - 2);
            int pageCount = 10;
        %>
        <a onclick="goto(<%=index-1%>)" class="on1">&nbsp;</a>
        <%

            for (int i = 0; i < len; i++, start++) {
                if (start == pageCount) {
                    %><a onclick="goto(<%=start%>)" <%=start==index?"class='on'":"" %>><%=start%></a><%
                    break;
                }
                if((i+1)==len){
                    %><a onclick="goto(<%=start%>)">...</a><%
                }else{
                    %><a onclick="goto(<%=start%>)" <%=start==index?"class='on'":"" %>><%=start%></a><%
                }
            }
        %>
        <a onclick="goto(<%=index+1%>)" class="on2">&nbsp;</a>
    </div>
</div>
</body>
<script>
    function goto(pageIndex) {
        if (pageIndex <= 0 || pageIndex ><%=pageCount%>)
            return;
        window.parent.goto(pageIndex);
    }
</script>
</html>
