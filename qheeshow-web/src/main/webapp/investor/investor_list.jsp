<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    List<Xwcmclassinfo> areas = (List<Xwcmclassinfo>) request.getAttribute("areas");
    List<Xwcmclassinfo> industrys = (List<Xwcmclassinfo>) request.getAttribute("industrys");
    List<Xwcmclassinfo> stages = (List<Xwcmclassinfo>) request.getAttribute("stages");
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--投资人</title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script>
        var areaid = 0;//地区id
        var industryid = 0;//行业id
        var stageid = 0;//阶段id
        var keyword = "";
        var pageIndex = 1;
        function list() {
            $("#investors").load("<%=appPath%>/investor/list/" + areaid + "/" + industryid + "/" + stageid + "/" + pageIndex + "?keyword=" + keyword);
        }
        function setArea(value, obj, id) {
            areaid = value;
            keyword = $("#keyword").val();
            setCheck(value, obj, id);
            list();
        }
        function setIndustry(value, obj, id) {
            industryid = value;
            keyword = $("#keyword").val();
            setCheck(value, obj, id);
            list();
        }
        function setStage(value, obj, id) {
            limitid = value;
            keyword = $("#keyword").val();
            setCheck(value, obj, id);
            list();
        }
        function search() {
            keyword = $("#keyword").val();
            list();
        }
        function setCheck(value, obj, id) {
            if (value != 0) {
                $(obj).parent().children('li').each(function () {
                    $(this).removeClass("on");
                });
                $(obj).attr("class", "on");
            } else
                $("#" + id).children('li').each(function () {
                    $(this).removeClass("on");
                });
        }
    </script>
</head>
<body>
<jsp:include page="../pub/head.jsp?flag=3" flush="true"/>
<div class="g-banner3"></div>
<div class="g-proj">
    <div class="g-conter">
        <ul class="g-proj-sev">
            <li><input id="keyword" class="g-proj-ipt" placeholder="输入您要找的投资人"/></li>
            <li><a class="g-proj-btn"></a></li>
        </ul>
        <div class="g-proj-one">
            <div class="g-proj-onew">
                <div class="g-proj-onel">关注行业：</div>
                <div class="g-proj-onec" onclick="setIndustry(0,this,'industrys');"><a>不限</a></div>
                <div class="g-proj-oner">
                    <ul id="industrys">
                        <%
                            for (Xwcmclassinfo industry : industrys) {
                        %>
                        <li onclick="setIndustry(<%=industry.getClassinfoid() %>,this);"><a><%=industry.getCname()%>
                        </a></li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">所在区域：</div>
                <div class="g-proj-onec" onclick="setArea(0,this,'areas');"><a>不限</a></div>
                <div class="g-proj-oner">
                    <ul id="areas">
                        <%
                            for (Xwcmclassinfo area : areas) {
                        %>
                        <li onclick="setArea(<%=area.getClassinfoid() %>,this);"><a><%=area.getCname()%>
                        </a></li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">偏好阶段：</div>
                <div class="g-proj-onec" onclick="setStage(0,this,'stages');"><a>不限</a></div>
                <div class="g-proj-oner">
                    <ul id="stages">
                        <%
                            for (Xwcmclassinfo stage : stages) {
                        %>
                        <li onclick="setArea(<%=stage.getClassinfoid() %>,this);"><a><%=stage.getCname()%>
                        </a></li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>
        <div id="investors" class="g-people"></div>
        <script>
            list();
        </script>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
</html>
