<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="java.util.List" %>
<%
    List<Investor> investors = (List<Investor>) request.getAttribute("investors");
%>
<%
    for (Investor investor : investors) {
%>
<div class="g-people-lst">
    <span><img src="images/bg-tzr-3.png" width="240" height="181"/></span>

    <h1><%=investor.getTrueName()%>
    </h1>
    <h5><%=investor.getCompanyName()%> | <%=investor.getCompanyRank()%>
    </h5>

    <div class="g-people-lst-heg">
        <ul class="g-people-lst-ul">
            <li class="on1">关注领域：</li>
            <li class="on2">
                <%
                    if (!StringUtils.isEmpty(investor.getIndustryName())) {
                        for (String industry : investor.getIndustryName().split("#")) {
                %><span><%=industry%></span><%
                    }
                }
            %>
            </li>
        </ul>
        <ul class="g-people-lst-ul">
            <li class="on1">偏好投资：</li>
            <li class="on2">
                <%
                    if (!StringUtils.isEmpty(investor.getStageName())) {
                        for (String stage : investor.getStageName().split("#")) {
                %><span><%=stage%></span><%
                    }
                }
            %>
            </li>
        </ul>
        <ul class="g-people-lst-ul">
            <li class="on1">个人介绍：</li>
            <li class="on2">
                <%
                    String summary = investor.getSummary();
                    if (!StringUtils.isEmpty(summary)) {
                %><%=summary.length() > 50 ? summary.substring(0, 50) : summary%><%
                }
            %>
            </li>
        </ul>
        <div class="g-people-lst-btn">
            <a href="#">沟通量(34)</a>
            <a href="#">综合评分(4.5星)</a>
        </div>
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