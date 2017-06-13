<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="java.util.List" %>
<%
    String appPath = Config.get("app.path");
    List<Investor> investorList = (List<Investor>) request.getAttribute("investors");
%>
<%
    if (investorList == null || investorList.size() == 0) {
%>
<div class="wx-callmmone"><img src="<%=appPath%>/images/wtwx-icon54.png" width="212"
                               height="152"/><span>暂无金融顾问...</span></div>
<%
} else {
    for (Investor investor : investorList) {
%>
<a href="<%=appPath%>/investor/do/<%=investor.getId()%>">
<div class="wtwx-investors-cnt1">
    <div class="wtwx-adviser-l"><img src="<%=investor.getPhoto()%>" width="41" height="41"/></div>
    <div class="wtwx-adviser-r"><%=investor.getTrueName()%>
    </div>
    <span class="wtwx-adviser-on1"><%=investor.getCompanyName()%> | <%=investor.getCompanyRank()%></span>
</div>
</a>
<%
        }
    }
%>



