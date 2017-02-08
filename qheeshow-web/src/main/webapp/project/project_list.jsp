<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Classinfo" %>
<%
    List<Classinfo> areas = (List<Classinfo>) request.getAttribute("areas");
    List<Classinfo> financingLimits = (List<Classinfo>) request.getAttribute("financingLimits");
    List<Classinfo> industrys = (List<Classinfo>) request.getAttribute("industrys");
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <script src="/statics/jquery/jquery-1.11.1.js"></script>
    <script>
        var type = 0;//项目类型
        var areaid = 0;//地区id
        var industryid = 0;//产业id
        var limitid = 0;//融资规模id
        var keyword = "";
        var pageIndex = 1;
        function list() {
            $("#iframe_list").attr("src", "http://127.0.0.1:8093/project/list/" + type + "/" + areaid + "/" + limitid + "/" + industryid + "/" + pageIndex + "?keyword=" + keyword);
        }
        function setType(value) {
            type = value;
            keyword = $("#keyword").val();
            list();
        }
        function setArea(value) {
            areaid = value;
            keyword = $("#keyword").val();
            list();
        }
        function setIndustry(value) {
            industryid = value;
            keyword = $("#keyword").val();
            list();
        }
        function setLimit(value) {
            limitid = value;
            keyword = $("#keyword").val();
            list();
        }
        function search() {
            keyword = $("#keyword").val();
            list();
        }
        function goto(pageIndex) {
            pageIndex = pageIndex;
            list();
        }
    </script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div>
    项目类型:
    <a style="cursor: pointer" onclick="setType(1);">平台推荐</a>&nbsp;
    <a style="cursor: pointer" onclick="setType(2);">机构关注</a>&nbsp;
    <a style="cursor: pointer" onclick="setType(3);">企业自荐</a>&nbsp;
</div>
<div>
    地域:
    <%
        for (Classinfo classinfo : areas) {
    %><a style="cursor: pointer" onclick="setArea(<%=classinfo.getId() %>);"><%=classinfo.getName()%>
</a>&nbsp;<%
    }
%>
</div>
<div>
    所属行业:
    <%
        for (Classinfo classinfo : industrys) {
    %><a style="cursor: pointer" onclick="setIndustry(<%=classinfo.getId() %>);"><%=classinfo.getName()%>
</a>&nbsp;<%
    }
%>
</div>
<div>
    融资规模:
    <%
        for (Classinfo classinfo : financingLimits) {
    %><a style="cursor: pointer" onclick="setLimit(<%=classinfo.getId() %>);"><%=classinfo.getName()%>
</a>&nbsp;<%
    }
%>
</div>
<div>
    <input id="keyword"/><input type="button" value="搜索" onclick="search();"/>
    <iframe id="iframe_list" src="http://127.0.0.1:8093/project/list/1/1/1/1/1?keyword=" width="900px;"/>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
</html>
