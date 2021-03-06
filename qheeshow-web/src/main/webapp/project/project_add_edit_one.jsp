<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Object object = request.getAttribute("project");
    Project project = object == null ? null : (Project) object;
    boolean isNull = project == null;
    Calendar calendar = Calendar.getInstance();
    int curYear = calendar.get(Calendar.YEAR);
    List<Xwcmclassinfo> industrys = (List<Xwcmclassinfo>) request.getAttribute("industrys");
    List<Xwcmclassinfo> areas = (List<Xwcmclassinfo>) request.getAttribute("areas");
    List<Xwcmclassinfo> financingLimits = (List<Xwcmclassinfo>) request.getAttribute("financingLimits");
    List<Xwcmclassinfo> stages = (List<Xwcmclassinfo>) request.getAttribute("stages");
    List<Xwcmclassinfo> tags = (List<Xwcmclassinfo>) request.getAttribute("tags");
    String[] datas1 =
            isNull || StringUtils.isEmpty(project.getLastOne()) ? null : project.getLastOne().split(":")[1].split("#");
    String[] datas2 =
            isNull || StringUtils.isEmpty(project.getLastTwo()) ? null : project.getLastTwo().split(":")[1].split("#");
    String flag = "2";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--创建项目</title>
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/project.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/images/bootstrap.min.js"></script>
    <script src="<%=appPath%>/jquery/jquery-form.js"></script>
    <script src="<%=appPath%>/js/config.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div class="pro-body">
    <form id="baseForm">
        <input type="hidden" id="lastOne" name="lastOne" value="<%=curYear-1 %>:500万以下#100万以下#1000万以下#500万以下"/>
        <input type="hidden" id="lastTwo" name="lastTwo" value="<%=curYear-2 %>:500万以下#100万以下#1000万以下#500万以下"/>
        <%
            if (isNull) {
        %><input type="hidden" id="id" name="id"/><%
    } else {
    %><input type="hidden" id="id" name="id" value="<%=project.getId()%>"/><%
        }
    %>
        <input type="hidden" id="id" name="id" value="<%=isNull?"":project.getId()%>"/>
        <input type="hidden" name="status" value="1"/>
        <input type="hidden" name="isCase" value="<%=isNull?"0":project.getIsCase()%>"/>
        <input type="hidden" name="focus" value="<%=isNull?"0":project.getFocus()%>"/>
        <input type="hidden" id="logo" name="logo" value="<%=isNull?"":project.getLogo()%>"/>
        <input type="hidden" id="bp" name="bp" value="<%=isNull?"":project.getBp()%>"/>
        <input type="hidden" id="bpName" name="bpName" value="<%=isNull?"":project.getBpName()%>"/>
        <input type="hidden" id="financingLimit" name="financingLimit"
               value="<%=isNull?"0":project.getFinancingLimit()%>"/>
        <input type="hidden" id="financingLimitName" name="financingLimitName"
               value="<%=isNull?"":project.getFinancingLimitName()%>"/>
        <input type="hidden" id="industry" name="industry" value="<%=isNull?"0":project.getIndustry()%>"/>
        <input type="hidden" id="industryName" name="industryName" value="<%=isNull?"":project.getIndustryName()%>"/>
        <input type="hidden" id="area" name="area" value="<%=isNull?"0":project.getArea()%>"/>
        <input type="hidden" id="areaName" name="areaName" value="<%=isNull?"":project.getAreaName()%>"/>
        <input type="hidden" id="stage" name="stage" value="<%=isNull?"0":project.getStage()%>"/>
        <input type="hidden" id="stageName" name="stageName" value="<%=isNull?"":project.getStageName()%>"/>
        <input type="hidden" name="type" value="3"/>
        <input type="hidden" id="tags" name="tags" value=""/>

        <div class="pro-wap">
            <div class="pro-t">项目信息(1/3)</div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目LOGO</li>
                    <li class="on2">
                        <img id="logoImg"
                             src="<%=(isNull|| StringUtils.isEmpty(project.getLogo()))?appPath+"/images/bg-new1.png":project.getLogo() %>"
                             class="oimg"/>
                        <span><a class="on">上传LOGO</a></span><input id="logoFile" name="logoFile" type='file'
                                                                    unselectable="on" class="on2"
                                                                    onchange="uploadImage('logoFile','logoImg','logo')"/>
                    </li>
                    <li class="on3">支持png/jpg/jepg格式</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目名称</li>
                    <li class="on2"><input name="title"
                                           value="<%=(isNull || StringUtils.isEmpty(project.getTitle()))?"":project.getTitle()%>"
                                           class="pro-one-ipt" placeholder="填写你的项目名称，不超过8个字"/><span
                            class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                            class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">解决需求</li>
                    <li class="on2"><input name="demand"
                                           value="<%=(isNull || StringUtils.isEmpty(project.getDemand()))?"":project.getDemand()%>"
                                           class="pro-one-ipt" placeholder="一句话介绍你的项目所能解决的市场需求，不超过30个汉字"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                                class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目亮点</li>
                    <li class="on2">
                        <textarea name="highlights" class="pro-one-tex"
                                  placeholder="一句话介绍你的团队的优势（不超过300字）"><%=(isNull || StringUtils
                                .isEmpty(project.getHighlights())) ? "" : project.getHighlights()%></textarea>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                            class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                    <li class="on3">投资人重点关注</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目标签</li>
                    <li class="on8">
                        <ul id="tags_" class="pro-fo-lst">
                            <%
                                int count = 0;
                                for (Xwcmclassinfo tag : tags) {
                                    boolean isCheck = false;
                                    if (!isNull && !StringUtils.isEmpty(project.getTags())) {
                                        for (String sTag : project.getTags().split("#")) {
                                            if (sTag.equals(tag.getCname())) {
                                                count++;
                                                isCheck = true;
                                                break;
                                            }
                                        }
                                    }
                            %>
                            <li data="<%=tag.getCname()%>" <%=isCheck ? "class='on'" : ""%>
                                onclick="checkTag(this);">
                                <a><%=tag.getCname() %>
                                </a></li>
                            <%
                                }
                            %>
                            <script>
                                var count = <%=count%>;
                                function checkTag(obj) {
                                    if ($(obj).attr("class") == "on") {
                                        $(obj).removeClass("on");
                                        count--;
                                    } else {
                                        if (count >= 4) {
                                            xalert("最多选择4个标签");
                                        } else {
                                            $(obj).attr("class", "on");
                                            count++;
                                        }
                                    }
                                }
                            </script>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目视频<span>（选填）</span></li>
                    <li class="on2"><input name="videoLink"
                                           value="<%=(isNull || StringUtils.isEmpty(project.getVideoLink()))?"":project.getVideoLink()%>"
                                           class="pro-one-ipt" placeholder="添加视频播放地址"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                                class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                    <li class="on3">支持MP4格式／优酷／土豆视频链接</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目详述</li>
                    <li class="on2">
                        <textarea name="description" class="pro-one-tex"
                                  placeholder="项目详述"><%=(isNull || StringUtils.isEmpty(project.getDescription())) ?
                                "" :
                                project.getDescription()%></textarea>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                            class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                    <li class="on3">详细描述你的项目亮点，优势等等，便于下一轮投资者更完整了解该项目</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">财务数据<span>（选填）</span></li>
                    <li class="on2">
                        <div class="clear"></div>
                        <div class="pro-two-wap">
                            <span class="time"><%=curYear - 1 %>年</span>
                            <ul class="pro-two-lst">
                                <li class="on6">主营收入:</li>
                                <li class="on7">
                                    <div id="one1__" class="pro-two-ipt" onclick="showMenu('one1');">
                                        <%
                                            if (datas1 != null) {
                                        %><a class="a1" href="#"><%=datas1[0]%>
                                    </a><span class="pro2-left-top"></span><span class="pro2-right-top"></span><span
                                            class="pro2-right-bottom"></span><span class="pro2-left-bottom"></span><%
                                    } else {
                                    %><a class="a1" href="#">500万以下</a><span class="pro2-left-top"></span><span
                                            class="pro2-right-top"></span><span class="pro2-right-bottom"></span><span
                                            class="pro2-left-bottom"></span><%
                                        }
                                    %>
                                    </div>
                                    <div id="one1_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="one1" class="pro-menu" style="display: none;">
                                        <li><a onclick="setOne1('500万以下')">500万以下</a></li>
                                        <li><a onclick="setOne1('500~2000万')">500~2000万</a></li>
                                        <li><a onclick="setOne1('2000~5000万')">2000~5000万</a>
                                        </li>
                                        <li><a onclick="setOne1('5000~10000万')">5000~10000万</a>
                                        </li>
                                        <li><a onclick="setOne1('10000万以上')">10000万以上</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="pro-two-lst">
                                <li class="on6">利润:</li>
                                <li class="on7">
                                    <div id="one2__" class="pro-two-ipt" onclick="showMenu('one2');">
                                        <%
                                            if (datas1 != null) {
                                        %><a class="a1" href="#"><%=datas1[1]%>
                                    </a><span class="pro2-left-top"></span><span class="pro2-right-top"></span><span
                                            class="pro2-right-bottom"></span><span class="pro2-left-bottom"></span><%
                                    } else {
                                    %><a class="a1" href="#">100万以下</a><span class="pro2-left-top"></span><span
                                            class="pro2-right-top"></span><span class="pro2-right-bottom"></span><span
                                            class="pro2-left-bottom"></span><%
                                        }
                                    %>
                                    </div>
                                    <div id="one2_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="one2" class="pro-menu" style="display: none;">
                                        <li><a onclick="setOne2('100万以下')">100万以下</a></li>
                                        <li><a onclick="setOne2('100~500万')">100~500万</a></li>
                                        <li><a onclick="setOne2('500~1000万')">500~1000万</a></li>
                                        <li><a onclick="setOne2('1000~3000万')">1000~3000万</a>
                                        </li>
                                        <li><a onclick="setOne2('3000万以上')">3000万以上</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="pro-two-lst">
                                <li class="on6">总资产:</li>
                                <li class="on7">
                                    <div id="one3__" class="pro-two-ipt" onclick="showMenu('one3');">
                                        <%
                                            if (datas1 != null) {
                                        %><a class="a1" href="#"><%=datas1[2]%>
                                    </a><span class="pro2-left-top"></span><span class="pro2-right-top"></span><span
                                            class="pro2-right-bottom"></span><span class="pro2-left-bottom"></span><%
                                    } else {
                                    %><a class="a1" href="#">1000万以下</a><span class="pro2-left-top"></span><span
                                            class="pro2-right-top"></span><span class="pro2-right-bottom"></span><span
                                            class="pro2-left-bottom"></span><%
                                        }
                                    %>
                                    </div>
                                    <div id="one3_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="one3" class="pro-menu" style="display: none;">
                                        <li><a onclick="setOne3('1000万以下')">1000万以下</a></li>
                                        <li><a onclick="setOne3('1000~5000万')">1000~5000万</a>
                                        </li>
                                        <li><a onclick="setOne3('5000~10000万')">5000~10000万</a>
                                        </li>
                                        <li><a onclick="setOne3('10000万以上')">10000万以上</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="pro-two-lst">
                                <li class="on6">净资产:</li>
                                <li class="on7">
                                    <div id="one4__" class="pro-two-ipt" onclick="showMenu('one4');">
                                        <%
                                            if (datas1 != null) {
                                        %><a class="a1" href="#"><%=datas1[3]%>
                                    </a><span class="pro2-left-top"></span><span class="pro2-right-top"></span><span
                                            class="pro2-right-bottom"></span><span class="pro2-left-bottom"></span><%
                                    } else {
                                    %><a class="a1" href="#">1000万以下</a><span class="pro2-left-top"></span><span
                                            class="pro2-right-top"></span><span class="pro2-right-bottom"></span><span
                                            class="pro2-left-bottom"></span><%
                                        }
                                    %>
                                    </div>
                                    <div id="one4_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="one4" class="pro-menu" style="display: none;">
                                        <li><a onclick="setOne4('500万以下')">500万以下</a></li>
                                        <li><a onclick="setOne4('500~2000万')">500~2000万</a></li>
                                        <li><a onclick="setOne4('2000~5000万')">2000~5000万</a>
                                        </li>
                                        <li><a onclick="setOne4('5000万以上')">5000万以上</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="clear"></div>
                        <div class="pro-two-wap">
                            <span class="time"><%=curYear - 2 %>年</span>
                            <ul class="pro-two-lst">
                                <li class="on6">主营收入:</li>
                                <li class="on7">
                                    <div id="two1__" class="pro-two-ipt" onclick="showMenu('two1');">
                                        <%
                                            if (datas2 != null) {
                                        %><a class="a1" href="#"><%=datas2[0]%>
                                    </a><span class="pro2-left-top"></span><span class="pro2-right-top"></span><span
                                            class="pro2-right-bottom"></span><span class="pro2-left-bottom"></span><%
                                    } else {
                                    %><a class="a1" href="#">500万以下</a><span class="pro2-left-top"></span><span
                                            class="pro2-right-top"></span><span class="pro2-right-bottom"></span><span
                                            class="pro2-left-bottom"></span><%
                                        }
                                    %>
                                    </div>
                                    <div id="two1_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="two1" class="pro-menu" style="display: none;">
                                        <li><a onclick="setTwo1('500万以下')">500万以下</a></li>
                                        <li><a onclick="setTwo1('500~2000万')">500~2000万</a></li>
                                        <li><a onclick="setTwo1('2000~5000万')">2000~5000万</a>
                                        </li>
                                        <li><a onclick="setTwo1('5000~10000万')">5000~10000万</a>
                                        </li>
                                        <li><a onclick="setTwo1('10000万以上')">10000万以上</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="pro-two-lst">
                                <li class="on6">利润:</li>
                                <li class="on7">
                                    <div id="two2__" class="pro-two-ipt" onclick="showMenu('two2');">
                                        <%
                                            if (datas2 != null) {
                                        %><a class="a1" href="#"><%=datas2[1]%>
                                    </a><span class="pro2-left-top"></span><span class="pro2-right-top"></span><span
                                            class="pro2-right-bottom"></span><span class="pro2-left-bottom"></span><%
                                    } else {
                                    %><a class="a1" href="#">100万以下</a><span class="pro2-left-top"></span><span
                                            class="pro2-right-top"></span><span class="pro2-right-bottom"></span><span
                                            class="pro2-left-bottom"></span><%
                                        }
                                    %>
                                    </div>
                                    <div id="two2_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="two2" class="pro-menu" style="display: none;">
                                        <li><a onclick="setTwo2('100万以下')">100万以下</a></li>
                                        <li><a onclick="setTwo2('100~500万')">100~500万</a></li>
                                        <li><a onclick="setTwo2('500~1000万')">500~1000万</a></li>
                                        <li><a onclick="setTwo2('1000~3000万')">1000~3000万</a>
                                        </li>
                                        <li><a onclick="setTwo2('3000万以上')">3000万以上</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="pro-two-lst">
                                <li class="on6">总资产:</li>
                                <li class="on7">
                                    <div id="two3__" class="pro-two-ipt" onclick="showMenu('two3');">
                                        <%
                                            if (datas2 != null) {
                                        %><a class="a1" href="#"><%=datas2[2]%>
                                    </a><span class="pro2-left-top"></span><span class="pro2-right-top"></span><span
                                            class="pro2-right-bottom"></span><span class="pro2-left-bottom"></span><%
                                    } else {
                                    %><a class="a1" href="#">1000万以下</a><span class="pro2-left-top"></span><span
                                            class="pro2-right-top"></span><span class="pro2-right-bottom"></span><span
                                            class="pro2-left-bottom"></span><%
                                        }
                                    %>
                                    </div>
                                    <div id="two3_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="two3" class="pro-menu" style="display: none;">
                                        <li><a onclick="setTwo3('1000万以下')">1000万以下</a></li>
                                        <li><a onclick="setTwo3('1000~5000万')">1000~5000万</a>
                                        </li>
                                        <li><a onclick="setTwo3('5000~10000万')">5000~10000万</a>
                                        </li>
                                        <li><a onclick="setTwo3('10000万以上')">10000万以上</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="pro-two-lst">
                                <li class="on6">净资产:</li>
                                <li class="on7">
                                    <div id="two4__" class="pro-two-ipt" onclick="showMenu('two4');">
                                        <%
                                            if (datas2 != null) {
                                        %><a class="a1" href="#"><%=datas2[3]%>
                                    </a><span class="pro2-left-top"></span><span class="pro2-right-top"></span><span
                                            class="pro2-right-bottom"></span><span class="pro2-left-bottom"></span><%
                                    } else {
                                    %><a class="a1" href="#">1000万以下</a><span class="pro2-left-top"></span><span
                                            class="pro2-right-top"></span><span class="pro2-right-bottom"></span><span
                                            class="pro2-left-bottom"></span><%
                                        }
                                    %>
                                    </div>
                                    <div id="two4_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="two4" class="pro-menu" style="display: none;">
                                        <li><a onclick="setTwo4('500万以下')">500万以下</a></li>
                                        <li><a onclick="setTwo4('500~2000万')">500~2000万</a></li>
                                        <li><a onclick="setTwo4('2000~5000万')">2000~5000万</a>
                                        </li>
                                        <li><a onclick="setTwo4('5000万以上')">5000万以上</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="on3">财务数据是投资人最关心的数据之一</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">需融资金额</li>
                    <li class="on8">
                        <ul class="pro-fo-lst">
                            <%
                                for (Xwcmclassinfo xwcmclassinfo : financingLimits) {
                                    if (!isNull && xwcmclassinfo.getClassinfoid().intValue() == project.getFinancingLimit()
                                            .intValue()) {
                            %>
                            <li class="on"
                                onclick="fixFinancingLimit(this,<%=xwcmclassinfo.getClassinfoid() %>,'<%=xwcmclassinfo.getCname() %>');">
                                <a><%=xwcmclassinfo.getCname() %>
                                </a></li>
                            <%
                            } else {
                            %>
                            <li onclick="fixFinancingLimit(this,<%=xwcmclassinfo.getClassinfoid() %>,'<%=xwcmclassinfo.getCname() %>');">
                                <a><%=xwcmclassinfo.getCname() %>
                                </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                        <script>
                            function fixFinancingLimit(obj, id, name) {
                                $(obj).parent().children('li').each(function () {
                                    $(this).removeClass("on");
                                });
                                $(obj).attr("class", "on");
                                $("#financingLimit").val(id);
                                $("#financingLimitName").val(name);
                            }
                        </script>
                    </li>
                </ul>
                <ul class="pro-one-lst">
                    <li class="on1">所属行业</li>
                    <li class="on8">
                        <ul class="pro-fo-lst">
                            <%
                                for (Xwcmclassinfo xwcmclassinfo : industrys) {
                                    if (!isNull && xwcmclassinfo.getClassinfoid().intValue() == project.getIndustry()
                                            .intValue()) {
                            %>
                            <li class="on"
                                onclick="fixIndustry(this,<%=xwcmclassinfo.getClassinfoid() %>,'<%=xwcmclassinfo.getCname() %>');">
                                <a><%=xwcmclassinfo.getCname() %>
                                </a></li>
                            <%
                            } else {
                            %>
                            <li onclick="fixIndustry(this,<%=xwcmclassinfo.getClassinfoid() %>,'<%=xwcmclassinfo.getCname() %>');">
                                <a><%=xwcmclassinfo.getCname() %>
                                </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                        <script>
                            function fixIndustry(obj, id, name) {
                                $(obj).parent().children('li').each(function () {
                                    $(this).removeClass("on");
                                });
                                $(obj).attr("class", "on");
                                $("#industry").val(id);
                                $("#industryName").val(name);
                            }
                        </script>
                    </li>
                </ul>
                <ul class="pro-one-lst">
                    <li class="on1">项目地点</li>
                    <li class="on8">
                        <ul class="pro-fo-lst">
                            <%
                                for (Xwcmclassinfo xwcmclassinfo : areas) {
                                    if (!isNull && xwcmclassinfo.getClassinfoid().intValue() == project.getArea().intValue()) {
                            %>
                            <li class="on"
                                onclick="fixArea(this,<%=xwcmclassinfo.getClassinfoid() %>,'<%=xwcmclassinfo.getCname() %>');">
                                <a><%=xwcmclassinfo.getCname() %>
                                </a></li>
                            <%
                            } else {
                            %>
                            <li onclick="fixArea(this,<%=xwcmclassinfo.getClassinfoid() %>,'<%=xwcmclassinfo.getCname() %>');">
                                <a><%=xwcmclassinfo.getCname() %>
                                </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                        <script>
                            function fixArea(obj, id, name) {
                                $(obj).parent().children('li').each(function () {
                                    $(this).removeClass("on");
                                });
                                $(obj).attr("class", "on");
                                $("#area").val(id);
                                $("#areaName").val(name);
                            }
                        </script>
                    </li>
                </ul>
                <ul class="pro-one-lst">
                    <li class="on1">融资阶段</li>
                    <li class="on8">
                        <ul class="pro-fo-lst">
                            <%
                                for (Xwcmclassinfo xwcmclassinfo : stages) {
                                    if (!isNull && xwcmclassinfo.getClassinfoid().intValue() == project.getStage()) {
                            %>
                            <li class="on"
                                onclick="fixStage(this,<%=xwcmclassinfo.getClassinfoid() %>,'<%=xwcmclassinfo.getCname() %>');">
                                <a><%=xwcmclassinfo.getCname() %>
                                </a></li>
                            <%
                            } else {
                            %>
                            <li onclick="fixStage(this,<%=xwcmclassinfo.getClassinfoid() %>,'<%=xwcmclassinfo.getCname() %>');">
                                <a><%=xwcmclassinfo.getCname() %>
                                </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                        <script>
                            function fixStage(obj, id, name) {
                                $(obj).parent().children('li').each(function () {
                                    $(this).removeClass("on");
                                });
                                $(obj).attr("class", "on");
                                $("#stage").val(id);
                                $("#stageName").val(name);
                            }
                        </script>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">商业计划书<span>（选填）</span></li>
                    <li class="on2">
                        <ul class="pro-fiv-lst">
                            <li class="e1"><a>上传商业计划书</a><input id="bpFile" name="bpFile" type='file' unselectable="on"
                                                                class="on3" onchange="uploadFile('bpFile')"/><span
                                    class="pro5-left-top"></span><span class="pro5-right-top"></span><span
                                    class="pro5-right-bottom"></span><span class="pro5-left-bottom"></span></li>
                            <li class="e3">
                                <span id="fileName"><%=(isNull || StringUtils.isEmpty(project.getBp())) ? "" : "<a href='" + project.getBp() + "'>" + project.getBpName() + "</a>"%></span>
                            </li>
                        </ul>
                    </li>
                    <li class="on3">支持pdf/ppt/pptx/doc/docx格式</li>
                </ul>
            </div>
            <div class="pro-clear"></div>
            <div class="pro-btn"><a onclick="saveBase();">下一步</a></div>
        </div>
    </form>
</div>
<%@include file="../pub/foot.jsp" %>
</body>
<script>
    function selectFile(id) {
        $('#' + id).click();
    }
    //上传logo
    function uploadImage(fileid, imgid, hiddenid) {
        var file = $('#' + fileid);
        if (!file || !file.val())
            return;
        var patn = /\.jpg$|\.jpeg$|\.png$|\.gif$/i;
        if (!patn.test(file.val())) {
            xalert("请选择图片文件");
            return;
        }
        $('#logoFile').wrap("<form id='myUpload' action='<%=appPath%>/image/upload' method='post' enctype='multipart/form-data'></form>");
        $('#myUpload').ajaxSubmit({
            dataType: 'json',
            success: function (result) {
                $('#logoFile').unwrap();
                resetFileInput(file);
                $('#' + imgid).attr("src", result.data.path);
                $('#' + hiddenid).val(result.data.path);
            },
            error: function (xhr) {
                $('#logoFile').unwrap();
                resetFileInput(file);
                alert('上传失败!');
            }
        });
    }
    //上传文件
    function uploadFile(fileid) {
        var file = $('#' + fileid);
        if (!file || !file.val())
            return;
        var patn = /\.pdf$|\.ppt$|\.pptx$/i;
        if (!patn.test(file.val())) {
            xalert("文件类型不正确");
            return;
        }
        $('#bpFile').wrap("<form id='myUpload' action='<%=appPath%>/file/upload' method='post' enctype='multipart/form-data'></form>");
        $('#myUpload').ajaxSubmit({
            dataType: 'json',
            success: function (result) {
                $('#bpFile').unwrap();
                resetFileInput(file);
                if (result.code == -1) {
                    xalert(result.message);
                    return;
                }
                $('#bp').val(result.data.path);
                $('#bpName').val(result.data.name);
                $('#fileName').html(result.data.name);
            },
            error: function (xhr) {
                $('#bpFile').unwrap();
                resetFileInput(file);
                alert('上传失败!');
            }
        });
    }
    //保存基本信息
    function saveBase() {
        $("#lastOne").val("<%=curYear-1%>:" + one1 + "#" + one2 + "#" + one3 + "#" + one4);
        $("#lastTwo").val("<%=curYear-2%>:" + two1 + "#" + two2 + "#" + two3 + "#" + two4);
        var tags = "";
        $("#tags_").children('li').each(function () {
            if ($(this).attr("class") == "on")
                tags += $(this).attr("data") + "#";
        });
        $("#tags").val(tags);
        $.ajax({
            type: 'POST',
            url: '<%=appPath%>/project/base/save/authj',
            cache: false,
            processData: false,
            data: $('#baseForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    xalert(result.message);
                    return;
                }
                window.location.href = "<%=appPath%>/project/" + result.data + "/add/edit/2/auth";
            }
        });
    }
    var one1 = "500万以下", one2 = "100万以下", one3 = "1000万以下", one4 = "500万以下", two1 = "500万以下", two2 = "100万以下", two3 = "1000万以下", two4 = "500万以下";
    function setOne1(value) {
        one1 = value;
        hideMenu();
        $("#one1__").html("<a class='a1' href='#'>" + value + "</a>");
    }
    function setOne2(value) {
        one2 = value;
        hideMenu();
        $("#one2__").html("<a class='a1' href='#'>" + value + "</a>");
    }
    function setOne3(value) {
        one3 = value;
        hideMenu();
        $("#one3__").html("<a class='a1' href='#'>" + value + "</a>");
    }
    function setOne4(value) {
        one4 = value;
        hideMenu();
        $("#one4__").html("<a class='a1' href='#'>" + value + "</a>");
    }
    function setTwo1(value) {
        two1 = value;
        hideMenu();
        $("#two1__").html("<a class='a1' href='#'>" + value + "</a>");
    }
    function setTwo2(value) {
        two2 = value;
        hideMenu();
        $("#two2__").html("<a class='a1' href='#'>" + value + "</a>");
    }
    function setTwo3(value) {
        two3 = value;
        hideMenu();
        $("#two3__").html("<a class='a1' href='#'>" + value + "</a>");
    }
    function setTwo4(value) {
        two4 = value;
        hideMenu();
        $("#two4__").html("<a class='a1' href='#'>" + value + "</a>");
    }

    function showMenu(id) {
        hideMenu();
        $("#" + id).show();
        $("#" + id + "_").show();
    }

    function hideMenu() {
        for (var i = 1; i <= 4; i++) {
            $("#one" + i).hide();
            $("#one" + i + "_").hide();
            $("#two" + i).hide();
            $("#two" + i + "_").hide();
        }
    }
    function resetFileInput(file) {
        file.after(file.clone().val(""));
        file.remove();
    }
</script>
</html>
