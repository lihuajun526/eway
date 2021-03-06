<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    List<Xwcmclassinfo> areas = (List<Xwcmclassinfo>) request.getAttribute("areas");
    List<Xwcmclassinfo> financingLimits = (List<Xwcmclassinfo>) request.getAttribute("financingLimits");
    List<Xwcmclassinfo> industrys = (List<Xwcmclassinfo>) request.getAttribute("industrys");
    String flag = "2";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--项目</title>
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/project.css"/>
    <script src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script>
        var type = 0;//项目类型
        var areaid = 0;//地区id
        var industryid = 0;//产业id
        var limitid = 0;//融资规模id
        var keyword = "";
        var pageIndex = 1;
        function list() {
            $("#projects").load("<%=appPath%>/project/list/" + type + "/" + areaid + "/" + limitid + "/" + industryid + "/" + pageIndex + "?keyword=" + keyword);
        }
        function setType(value, obj, id) {
            type = value;
            keyword = $("#keyword").val();
            pageIndex = 1;
            setCheck1(value, obj, id);
            list();
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
        function setLimit(value, obj, id) {
            limitid = value;
            keyword = $("#keyword").val();
            setCheck(value, obj, id);
            list();
        }
        function goSearch() {
            keyword = $("#keyword").val();
            list();
        }
        function setCheck(value, obj, id) {
            if (id) {
                $("#" + id).children('li').each(function () {
                    $(this).removeClass("on");
                    $(this).children("span").remove();
                });
            } else {
                $(obj).parent().children('li').each(function () {
                    $(this).removeClass("on");
                    $(this).children("span").remove();
                });
            }
            if (id)
                return;
            $(obj).append("<span class='proj1-left-top'></span><span class='proj1-right-top'></span><span class='proj1-right-bottom'></span><span class='proj1-left-bottom'></span>");
            $(obj).attr("class", "on");
        }
        function setCheck1(value, obj, id) {
            $(obj).parent().children('li').each(function () {
                $(this).removeClass("ove");
                $(obj).remove("span");
            });
            $(obj).append("<span></span>");
            $(obj).attr("class", "ove");
        }
    </script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div class="g-banner2"></div>
<div class="g-proj">
    <div class="g-conter">
        <ul class="g-proj-sev">
            <li><input id="keyword" class="g-proj-ipt" placeholder="输入您要找的好项目"/></li>
            <li><a onclick="goSearch();" class="g-proj-btn"></a></li>
        </ul>
        <div class="g-proj-one3">
            <div class="g-proj-onel">项目类型：</div>
            <div class="g-proj-oner3">
                <ul>
                    <li onclick="setType(0,this);" class="ove"><a>全部</a><span></span></li>
                    <li onclick="setType(1,this);"><a>平台推荐</a></li>
                    <li onclick="setType(2,this);"><a>机构关注</a></li>
                    <li onclick="setType(3,this);"><a>企业自荐</a></li>
                </ul>
            </div>
        </div>
        <div class="g-proj-one">
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
                <div class="g-proj-onel">融资规模：</div>
                <div class="g-proj-onec" onclick="setLimit(0,this,'limits');"><a>不限</a></div>
                <div class="g-proj-oner">
                    <ul id="limits">
                        <%
                            for (Xwcmclassinfo financingLimit : financingLimits) {
                        %>
                        <li onclick="setLimit(<%=financingLimit.getClassinfoid() %>,this);">
                            <a><%=financingLimit.getCname()%>
                            </a></li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">所属行业：</div>
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
        </div>
        <div id="projects" class="g-proj-two"></div>
        <script>
            list();
        </script>
    </div>
</div>
<%@include file="../pub/foot.jsp" %>
</body>
</html>
