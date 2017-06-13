<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="java.util.List" %>
<%
    String appPath = Config.get("app.path");
    List<Xwcmclassinfo> areas = (List<Xwcmclassinfo>) request.getAttribute("areas");
    List<Xwcmclassinfo> financingLimits = (List<Xwcmclassinfo>) request.getAttribute("financingLimits");
    List<Xwcmclassinfo> industrys = (List<Xwcmclassinfo>) request.getAttribute("industrys");

    Integer iType = session.getAttribute("type") == null ? 0 : (Integer) session.getAttribute("type");
    Integer iAreaid = session.getAttribute("area") == null ? 0 : (Integer) session.getAttribute("area");
    Integer iFinancingLimit = session.getAttribute("financingLimit") == null ? 0 : (Integer) session.getAttribute("financingLimit");
    Integer iIndustry = session.getAttribute("industry") == null ? 0 : (Integer) session.getAttribute("industry");
    String keyword = session.getAttribute("keyword") == null ? "" : (String) session.getAttribute("keyword");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>筛选项目</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
</head>
<body>
<div class="wt-search">
    <input id="keyword" type="text" placeholder="搜索" value="<%=keyword%>" class="wt-search-ipt"/>
    <a onclick="setCondition()" class="wt-search-icon"></a>
</div>
<div>
    <div class="wt-search-tit">项目类型</div>
    <ul class="wt-search-cnt">
        <li<%=iType.intValue() == 0 ? " class='on'" : ""%> onclick="setType(0, this)"><a>全部</a></li>
        <li<%=iType.intValue() == 1 ? " class='on'" : ""%> onclick="setType(1, this)"><a>平台推荐</a></li>
        <li<%=iType.intValue() == 2 ? " class='on'" : ""%> onclick="setType(2, this)"><a>机构关注</a></li>
        <li<%=iType.intValue() == 3 ? " class='on'" : ""%> onclick="setType(3, this)"><a>企业自荐</a></li>
    </ul>

    <div class="wt-search-tit">所在区域</div>
    <ul class="wt-search-cnt">
        <li<%=iAreaid.intValue() == 0 ? " class='on'" : ""%> onclick="setArea(0, this)"><a>全部</a></li>
        <%
            for (Xwcmclassinfo area : areas) {
                if (area.getClassinfoid().intValue() == iAreaid.intValue()) {
        %>
        <li class="on" onclick="setArea(<%=area.getClassinfoid()%>, this)"><a><%=area.getCname()%>
        </a></li>
        <%
        } else {
        %>
        <li onclick="setArea(<%=area.getClassinfoid()%>, this)"><a><%=area.getCname()%>
        </a></li>
        <%
                }
            }
        %>
    </ul>

    <div class="wt-search-tit">融资规模</div>
    <ul class="wt-search-cnt">
        <li<%=iFinancingLimit.intValue() == 0 ? " class='on'" : ""%> onclick="setFinancingLimit(0, this)"><a>全部</a></li>
        <%
            for (Xwcmclassinfo financingLimit : financingLimits) {
                if (iFinancingLimit.intValue() == financingLimit.getClassinfoid().intValue()) {
        %>
        <li onclick="setFinancingLimit(<%=financingLimit.getClassinfoid()%>, this)" class="on">
            <a><%=financingLimit.getCname()%>
            </a></li>
        <%
        } else {
        %>
        <li onclick="setFinancingLimit(<%=financingLimit.getClassinfoid()%>, this)"><a><%=financingLimit.getCname()%>
        </a></li>
        <%
                }
            }
        %>
    </ul>

    <div class="wt-search-tit">所属行业</div>
    <ul class="wt-search-cnt">
        <li<%=iIndustry.intValue() == 0 ? " class='on'" : ""%> onclick="setIndustry(0, this)"><a>全部</a></li>
        <%
            for (Xwcmclassinfo industry : industrys) {
                if (industry.getClassinfoid().intValue() == iIndustry.intValue()) {
        %>
        <li onclick="setIndustry(<%=industry.getClassinfoid()%>, this)" class="on"><a><%=industry.getCname()%>
        </a></li>
        <%
        } else {
        %>
        <li onclick="setIndustry(<%=industry.getClassinfoid()%>, this)"><a><%=industry.getCname()%>
        </a></li>
        <%
                }
            }
        %>
    </ul>
    <div class="wt-search-btn2">
        <a onclick="javascript:window.history.back();" class="on1">取消</a>
        <a onclick="setCondition()" class="on2">确定</a>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script type="text/javascript" src="<%=appPath%>/js/config.js"></script>
<script>
    var type = <%=iType%>;
    var area = <%=iAreaid%>;
    var financingLimit = <%=iFinancingLimit%>;
    var industry = <%=iIndustry%>;
    function setClass(obj) {
        $(obj).parent().children('li').each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
    }
    function setType(id, obj) {
        if (id == type)
            return;
        setClass(obj);
        type = id;
    }
    function setArea(id, obj) {
        if (id == area)
            return;
        setClass(obj);
        area = id;
    }
    function setFinancingLimit(id, obj) {
        if (id == financingLimit)
            return;
        setClass(obj);
        financingLimit = id;
    }
    function setIndustry(id, obj) {
        if (id == industry)
            return;
        setClass(obj);
        industry = id;
    }
    function setCondition() {
        $.get(appPath + '/project/do/condition/set/' + type + '/' + area + '/' + financingLimit + '/' + industry + '?keyword=' + $("#keyword").val(), function () {
            window.location.href = appPath + '/index.jsp?m=1';
        });
    }
</script>
</html>
