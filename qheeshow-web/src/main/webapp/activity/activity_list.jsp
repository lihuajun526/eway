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
    <title><%=Config.get("app.name")%>--投资人</title>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
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
            <li><input type="text" class="g-proj-ipt" placeholder="输入您要找的投资人"/></li>
            <li><a href="#" class="g-proj-btn"></a></li>
        </ul>
        <div class="g-proj-one">
            <div class="g-proj-onew">
                <div class="g-proj-onel">所属行业：</div>
                <div class="g-proj-onec">不限</div>
                <div class="g-proj-oner">
                    <ul>
                        <li><a href="#">平台推荐</a></li>
                        <li><a href="#">机构关注</a></li>
                        <li><a href="#">企业自荐</a></li>
                    </ul>
                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">所在区域：</div>
                <div class="g-proj-onec-a">不限</div>
                <div class="g-proj-oner">
                    <ul>
                        <li><a href="#">上海</a></li>
                        <li><a href="#">广州</a></li>
                        <li><a href="#">深圳</a></li>
                        <li><a href="#">杭州</a></li>
                        <li class="on"><a href="#">福建</a></li>
                        <li><a href="#">北京</a></li>
                    </ul>

                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">偏好阶段：</div>
                <div class="g-proj-onec">不限</div>
                <div class="g-proj-oner">
                    <ul>
                        <li><a href="#">50万-100万</a></li>
                        <li><a href="#">100万-200万</a></li>
                        <li><a href="#">200万-300万</a></li>
                    </ul>
                    <a href="#" class="g-proj-oner-m">全部</a>
                    <!--<a href="#" class="g-proj-oner-m2">全部</a> 改变箭头的代码-->
                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">人物身份：</div>
                <div class="g-proj-onec">不限</div>
                <div class="g-proj-oner">
                    <ul>
                        <li><a href="#">电子商务</a></li>
                        <li><a href="#">社交网络</a></li>
                        <li><a href="#">智能硬件</a></li>
                        <li><a href="#">消费生活</a></li>
                    </ul>

                </div>
            </div>
            <div class="g-proj-onew">
                <div class="g-proj-onel">分      类：</div>
                <div class="g-proj-onec">不限</div>
                <div class="g-proj-oner">
                    <ul>
                        <li><a href="#">投资类</a></li>
                        <li><a href="#">咨询类</a></li>
                        <li><a href="#">财务类</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="investors" class="g-people"></div>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
</html>
