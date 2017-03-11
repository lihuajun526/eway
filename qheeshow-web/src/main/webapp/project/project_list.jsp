<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    List<Xwcmclassinfo> areas = (List<Xwcmclassinfo>) request.getAttribute("areas");
    List<Xwcmclassinfo> financingLimits = (List<Xwcmclassinfo>) request.getAttribute("financingLimits");
    List<Xwcmclassinfo> industrys = (List<Xwcmclassinfo>) request.getAttribute("industrys");
%>
<html>
<head>
    <title>Title</title>
    <title><%=Config.get("app.name")%>--项目</title>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <link rel="stylesheet" href="/images/project.css"/>
    <script src="/jquery/jquery-1.11.1.js"></script>
    <script>
        var type = 0;//项目类型
        var areaid = 0;//地区id
        var industryid = 0;//产业id
        var limitid = 0;//融资规模id
        var keyword = "";
        var pageIndex = 1;
        function list() {
            $("#projects").load("/project/list/" + type + "/" + areaid + "/" + limitid + "/" + industryid + "/" + pageIndex + "?keyword=" + keyword);
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
    </script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="g-banner2"></div>
<div class="g-proj">
    <div class="g-conter">
        <ul class="g-proj-sev">
            <li><input id="keyword" class="g-proj-ipt" placeholder="输入您要找的好项目"/></li>
            <li><a href="#" class="g-proj-btn"></a></li>
        </ul>
        <div class="g-proj-one">
            <div class="g-proj-onew">
                <div class="g-proj-onel">项目类型：</div>
                <div class="g-proj-onec" onclick="setType(0);">不限</div>
                <div class="g-proj-oner">
                    <ul>
                        <li><a onclick="setType(1);">平台推荐</a></li>
                        <li><a onclick="setType(2);">机构关注</a></li>
                        <li><a onclick="setType(3);">企业自荐</a></li>
                    </ul>
                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">所在区域：</div>
                <div class="g-proj-onec" onclick="setArea(0);">不限</div>
                <div class="g-proj-oner">
                    <ul>
                        <%
                            for (Xwcmclassinfo area : areas) {
                        %>
                        <li><a onclick="setArea(<%=area.getClassinfoid() %>);"><%=area.getCname()%>
                        </a></li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">融资规模：</div>
                <div class="g-proj-onec" onclick="setLimit(0);">不限</div>
                <div class="g-proj-oner">
                    <ul>
                        <%
                            for (Xwcmclassinfo financingLimit : financingLimits) {
                        %>
                        <li><a onclick="setLimit(<%=financingLimit.getClassinfoid() %>);"><%=financingLimit.getCname()%>
                        </a></li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">所属行业：</div>
                <div class="g-proj-onec" onclick="setIndustry(0);">不限</div>
                <div class="g-proj-oner">
                    <ul>
                        <%
                            for (Xwcmclassinfo industry : industrys) {
                        %>
                        <li><a onclick="setIndustry(<%=industry.getClassinfoid() %>);"><%=industry.getCname()%>
                        </a></li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>
        <div id="projects" class="g-proj-two"></div>
        <script>
            list();
        </script>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
</html>
