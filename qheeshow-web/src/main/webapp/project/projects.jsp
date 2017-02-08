<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="java.util.List" %>
<%
    List<Project> projects = (List<Project>) request.getAttribute("projects");
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
</head>
<body>
<table>
    <%
        for (Project project : projects) {
            String[] tags = project.getTags().split(",");
    %>
    <tr>
        <td><img src="<%=project.getLogo()%>"/></td>
        <td><%=project.getTitle()%>
        </td>
        <td><%=project.getSummary()%>
        </td>
        <td><%=project.getAreaName()%>&nbsp;<%=project.getFinancingLimit()%>&nbsp;<%=project.getIndustryName()%>
        </td>
        <td>
            <%
                for (String tag:tags) {
            %>
            <%=tag%>&nbsp;
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<div>
    总记录数:<a>records</a><br />
    每页条数:<a>pageSize</a><br />
    当前第几页:<a>pageIndex</a><br />
    总页数:<a>pageCount</a><br />
    <%
        int records = 100;
        int pageSize = 10;
        int pageIndex = 10;
        int pageCount = 20;

        int cur = pageIndex;

        if(pageIndex == 0||pageIndex == 1){
        }else{
            %><a style="cursor: pointer" onclick="goto(1)">首页</a>|<a style="cursor: pointer" onclick="goto(<%=(pageIndex-1) %>)">上一页 </a> | <%
        }
        if(pageIndex<=3){
            for(int i=1; i<=(pageCount<=5?pageCount:5); i++){
                if(i==pageIndex){
                    %><%=i+" | " %><%
                }else{
                    %><a style="cursor: pointer" onclick="goto(<%=i %>)"><%=i%></a> | <%
                }
            }
        }else if(pageIndex>3 && pageCount<=(pageIndex+2)){
            for(int i=pageCount-4; i<=pageCount; i++){
                if(i==pageIndex){
                    %><%=i+" | " %><%
                }else{
                    %><a style="cursor: pointer" onclick="goto(<%=i %>)"><%=i %></a> | <%
                }
            }
        }else if(pageIndex>3 && pageCount>(pageIndex+2)){
            for(int i=pageIndex-2; i<=pageIndex+2; i++){
                if(i==pageIndex){
                    %><%=i+" | " %><%
                }else{
                    %><a style="cursor: pointer" onclick="goto(<%=i %>)"><%=i %></a> | <%
                }
            }
        }
        if(pageIndex == pageCount){
        }else{
            %>
            <a style="cursor: pointer" onclick="goto(<%=(pageIndex+1) %>)">下一页 </a> |
            <a style="cursor: pointer" onclick="goto(<%=pageSize %>)">尾页</a>|
            <%
        }
    %>
</div>
</body>
<script>
    function goto(pageIndex) {
        window.parent.goto(pageIndex);
    }
</script>
</html>
