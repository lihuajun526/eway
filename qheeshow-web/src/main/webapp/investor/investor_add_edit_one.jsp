<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Object object = request.getAttribute("investor");
    Investor investor = object == null ? null : (Investor) object;
    boolean isNull = investor == null;
    List<Xwcmclassinfo> industrys = (List<Xwcmclassinfo>) request.getAttribute("industrys");
    List<Xwcmclassinfo> areas = (List<Xwcmclassinfo>) request.getAttribute("areas");
    List<Xwcmclassinfo> financingLimits = (List<Xwcmclassinfo>) request.getAttribute("financingLimits");
    List<Xwcmclassinfo> stages = (List<Xwcmclassinfo>) request.getAttribute("stages");
    List<Xwcmclassinfo> styles = (List<Xwcmclassinfo>) request.getAttribute("styles");
    String flag = "3";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--投资人信息完善</title>
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/investor.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/images/bootstrap.min.js"></script>
    <script src="<%=appPath%>/jquery/jquery-form.js"></script>
    <script src="<%=appPath%>/js/util.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div class="inv-body">
    <form id="baseForm">
        <input type="hidden" name="id" <%=isNull?"":"value='"+investor.getId()+"'"%>/>
        <input type="hidden" id="industrys_" name="industryId"
               value="<%=isNull?"":investor.getIndustryId()%>"/>
        <input type="hidden" id="areas_" name="cityId" value="<%=isNull?"":investor.getCityId()%>"/>
        <input type="hidden" id="stages_" name="stageId" value="<%=isNull?"":investor.getStageId()%>"/>
        <input type="hidden" id="photo" name="photo" value="<%=isNull?"":investor.getPhoto()%>"/>
        <input type="hidden" id="singlePriceId" name="singlePriceId"
               value="<%=isNull?"":investor.getSinglePriceId()%>"/>
        <input type="hidden" id="styleId" name="styleId" value="<%=isNull?"":investor.getStyleId()%>"/>
        <input type="hidden" name="trueName" value="<%=loginUser.getName()%>"/>

        <div class="inv-wap">
            <div class="inv-t">基本信息(1/2)</div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">个人头像：</li>
                    <li class="on2">
                        <a href="#">
                            <ul id="ul_photo" class="inv-lst1">
                                <%
                                    if (isNull) {
                                %>
                                <li>
                                    <img src="<%=appPath%>/images/wt-icon19.png" width="58" height="58"/>
                                </li>
                                <li class="head">上传头像</li>
                                <%
                                } else {
                                %>
                                <li class="upload"><img src="<%=investor.getPhoto()%>" width="130" height="130"/></li>
                                <%
                                    }
                                %>
                            </ul>
                        </a>
                        <span><a class="oe">上传头像</a></span><input onchange="doUpload()" id="photoFile" name="photoFile"
                                                                  type='file' unselectable="on" class="on2"/>
                    </li>
                    <li class="on3">支持png/jpg/jepg格式</li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">真实姓名：</li>
                    <li class="on2">
                        <input class="inv-one-ipt" value="<%=loginUser.getName()%>" readonly="readonly"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                            class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">我 是：</li>
                    <li class="on2">
                        <ul class="inv-one-lst2">
                            <li><input name="investorType" type="radio" value="1" checked></li>
                            <li class="inv-radio">个人投资者</li>
                            <li><input name="investorType" type="radio" value="2"></li>
                            <li class="inv-radio">机构投资者</li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">所在公司：</li>
                    <li class="on2">
                        <ul>
                            <li class="inv-c-name">
                                <input id="companyName" name="companyName" class="inv-one-ipt1"
                                       value="<%=isNull?"":investor.getCompanyName()%>" placeholder="公司名称">
                                <span class="pro6-left-top"></span><span class="pro6-right-top"></span><span
                                    class="pro6-right-bottom"></span><span class="pro6-left-bottom"></span>
                            </li>
                            <li>
                                <input id="companyRank" name="companyRank"
                                       value="<%=isNull?"":investor.getCompanyRank()%>"
                                       class="inv-one-ipt1" placeholder="头衔">
                                <span class="pro6-left-top"></span><span class="pro6-right-top"></span><span
                                    class="pro6-right-bottom"></span><span class="pro6-left-bottom"></span>
                            </li>
                        </ul>
                    </li>
                    <li class="on3">很重要的信息</li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">个人微信<span>（选填）</span>:</li>
                    <li class="on2">
                        <input name="wechatId" class="inv-one-ipt1"
                               value="<%=isNull?"":StringUtils.isEmpty(investor.getWechatId())?"":investor.getWechatId()%>"
                               placeholder="微信号"/>
                        <span class="pro1-left-top"></span><span class="pro1a-right-top"></span><span
                            class="pro1a-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">活跃城市：</li>
                    <li class="on2">
                        <ul id="areas" class="inv-fo-lst">
                            <%
                                for (Xwcmclassinfo area : areas) {
                            %>
                            <li onclick="checkArea(this)" data="<%=area.getClassinfoid()%>"><a><%=area.getCname()%>
                            </a></li>
                            <%
                                }
                            %>
                        </ul>
                        <script>
                            var areaCount = 0;
                            function checkArea(obj) {
                                if ($(obj).attr("class") == "on") {
                                    $(obj).removeClass("on");
                                    areaCount--;
                                } else {
                                    if (areaCount >= 5) {
                                        xalert("最多选择5项");
                                    } else {
                                        $(obj).attr("class", "on");
                                        areaCount++;
                                    }
                                }
                            }
                        </script>
                    </li>
                    <li class="on3">城市可多选</li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">投资阶段：</li>
                    <li class="on2">
                        <ul id="stages" class="inv-fo-lst">
                            <%
                                for (Xwcmclassinfo stage : stages) {
                            %>
                            <li onclick="checkStage(this);" data="<%=stage.getClassinfoid()%>"><a><%=stage.getCname()%>
                            </a></li>
                            <%
                                }
                            %>
                        </ul>
                        <script>
                            var stageCount = 0;
                            function checkStage(obj) {
                                if ($(obj).attr("class") == "on") {
                                    $(obj).removeClass("on");
                                    stageCount--;
                                } else {
                                    if (stageCount >= 5) {
                                        xalert("最多选择5项");
                                    } else {
                                        $(obj).attr("class", "on");
                                        stageCount++;
                                    }
                                }
                            }
                        </script>
                    </li>
                    <li class="on3">投资阶段可多选</li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">投资领域：</li>
                    <li class="on2">
                        <ul id="industrys" class="inv-fo-lst">
                            <%
                                for (Xwcmclassinfo industry : industrys) {
                            %>
                            <li onclick="checkIndustry(this)" data="<%=industry.getClassinfoid()%>">
                                <a><%=industry.getCname()%>
                                </a></li>
                            <%
                                }
                            %>
                        </ul>
                        <script>
                            var industryCount = 0;
                            function checkIndustry(obj) {
                                if ($(obj).attr("class") == "on") {
                                    $(obj).removeClass("on");
                                    industryCount--;
                                } else {
                                    if (industryCount >= 5) {
                                        xalert("最多选择5项");
                                    } else {
                                        $(obj).attr("class", "on");
                                        industryCount++;
                                    }
                                }
                            }
                        </script>
                    </li>
                    <li class="on3">投资领域可多选</li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">单笔投资：</li>
                    <li class="on2">
                        <div class="inv-two-ipt" onclick="showMenu('limits');">
                            <a id="limit" class="a1">请选择</a>
                            <span class="pro2-left-top"></span><span class="pro2-right-top"></span><span
                                class="pro2-right-bottom"></span><span class="pro2-left-bottom"></span>
                        </div>
                        <div id="limits_" class="inv-menu-ico" style="display: none;"></div>
                        <ul id="limits" class="inv-menu" style="display: none;">
                            <li><a onclick="selectLimit(0,'');">请选择</a></li>
                            <%
                                for (Xwcmclassinfo financingLimit : financingLimits) {
                            %>
                            <li>
                                <a onclick="selectLimit(<%=financingLimit.getClassinfoid()%>,'<%=financingLimit.getCname()%>');"><%=financingLimit
                                        .getCname()%>
                                </a></li>
                            <%
                                }
                            %>
                        </ul>
                    </li>
                </ul>
            </div>
            <%--<div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">投资风格：</li>
                    <li class="on2">
                        <div class="inv-two-ipt" onclick="showMenu('styles');">
                            <a id="style" class="a1">请选择</a>
                        </div>
                        <div id="styles_" class="inv-menu-ico" style="display:none"></div>
                        <ul id="styles" class="inv-menu" style="display:none">
                            <li><a onclick="selectStyle(0,'');">请选择</a></li>
                            <%
                                for (Xwcmclassinfo style : styles) {
                            %>
                            <li><a onclick="selectStyle(<%=style.getClassinfoid()%>,'<%=style.getCname()%>');"><%=style
                                    .getCname()%>
                            </a></li>
                            <%
                                }
                            %>
                        </ul>
                    </li>
                </ul>
            </div>--%>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">投资案例<span>（选填）</span>:</li>
                    <li class="on2">
                        <input name="investorCase" class="inv-one-ipt"
                               value="<%=isNull?"":StringUtils.isEmpty(investor.getInvestorCase())?"":investor.getInvestorCase()%>"
                               placeholder="请输入投资案例"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                            class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">推荐人<span>（选填）</span>:</li>
                    <li class="on2">
                        <input name="recommender" class="inv-one-ipt"
                               value="<%=isNull?"":StringUtils.isEmpty(investor.getRecommender())?"":investor.getRecommender()%>"
                               placeholder="请输入推荐人"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                            class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">一句话介绍:</li>
                    <li class="on2"><textarea id="summary" name="summary" class="inv-one-tex" style="height: 80px;"
                                              placeholder="一句话介绍自己"><%=isNull ? "" : StringUtils.isEmpty(investor.getSummary()) ? "" : investor.getSummary()%></textarea>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                                class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                    <li class="on3">&nbsp;</li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">个人简介:</li>
                    <li class="on2"><textarea id="personalProfile" name="personalProfile" class="inv-one-tex"
                                              placeholder="包括教育经历,工作经验，创业经历，行业背景等内容"><%=isNull ? "" : StringUtils.isEmpty(investor.getPersonalProfile()) ? "" : investor.getPersonalProfile()%></textarea>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                                class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                    <li class="on3">&nbsp;</li>
                </ul>
            </div>
            <div class="inv-clear"></div>
            <div class="inv-btn"><a onclick="saveBase();">保存并申请认证</a></div>
        </div>
    </form>
</div>
<%@include file="../pub/foot.jsp" %>
</body>
<script>
    //保存基本信息
    function saveBase() {
        var industryIds = "#";
        $("#industrys").children('li').each(function () {
            if ($(this).attr("class") == "on") {
                industryIds += $(this).attr("data") + "#";
            }
        });
        $("#industrys_").val(industryIds);
        var stageIds = "#";
        $("#stages").children('li').each(function () {
            if ($(this).attr("class") == "on") {
                stageIds += $(this).attr("data") + "#";
            }
        });
        $("#stages_").val(stageIds);
        var areaIds = "#";
        $("#areas").children('li').each(function () {
            if ($(this).attr("class") == "on") {
                areaIds += $(this).attr("data") + "#";
            }
        });
        $("#areas_").val(areaIds);
        var singlePriceId = $("#singlePriceId").val();
        var styleId = $("#styleId").val();
        if (isEmpty($("#photo").val())) {
            xalert("请上传照片");
            return;
        }
        if (isEmpty($("#companyName").val())) {
            xalert("请填写公司名称");
            return;
        }
        if (isEmpty($("#companyRank").val())) {
            xalert("请填写职位名称");
            return;
        }
        if (isEmpty(areaIds)) {
            xalert("请选择活跃城市");
            return;
        }
        if (isEmpty(stageIds)) {
            xalert("请选择投资阶段");
            return;
        }
        if (isEmpty(industryIds)) {
            xalert("请选择投资领域");
            return;
        }
        if (singlePriceId == 0) {
            xalert("请选择单笔投资金额");
            return;
        }
        /*if (styleId == 0) {
            xalert("请选择投资风格");
            return;
        }*/
        if (isEmpty($("#personalProfile").val())) {
            xalert("请填写个人简介");
            return;
        }
        $.ajax({
            type: 'POST',
            url: '<%=appPath%>/investor/base/save/authj',
            cache: false,
            processData: false,
            data: $('#baseForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    xalert(result.message);
                    return;
                }
                window.location.href = "<%=appPath%>/investor/" + result.data + "/add/edit/2/auth";
            }
        });
    }
    function showMenu(id) {
        hideMenu();
        $("#" + id).show();
        $("#" + id + "_").show();
    }
    function hideMenu() {
        $("#styles").hide();
        $("#styles_").hide();
        $("#limits").hide();
        $("#limits_").hide();
    }
    function selectStyle(id, value) {
        $("#styleId").val(id);
        $("#style").val(value);
        hideMenu();
        if (id == 0)
            $("#style").html("请选择");
        else
            $("#style").html(value);
    }
    function selectLimit(id, value) {
        $("#singlePriceId").val(id);
        $("#singlePrice").val(value);
        hideMenu();
        if (id == 0)
            $("#limit").html("请选择");
        else
            $("#limit").html(value);
    }
    //上传图片
    function doUpload() {
        var file = $('#photoFile');
        if (!file || !file.val())
            return;
        var patn = /\.jpg$|\.jpeg$|\.png$|\.gif$/i;
        if (!patn.test(file.val())) {
            xalert("请选择图片文件");
            return;
        }
        $('#photoFile').wrap("<form id='myUpload' action='<%=appPath%>/image/upload' method='post' enctype='multipart/form-data'></form>");
        $('#myUpload').ajaxSubmit({
            dataType:'json',
            success:function(result){
                if (result.code == -1) {
                    xalert(result.message);
                    return;
                }
                $("#ul_photo").html("<li class='upload'><img src='"+result.data.path+"' width='130' height='130'/></li>");
                $('#photo').val(result.data.path);
                $('#photoFile').unwrap();
                resetFileInput(file);
            },
            error:function(xhr){
                xalert('上传失败!');
                $('#photoFile').unwrap();
                resetFileInput(file);
            }
        });
    }
    function selectFile(id) {
        $('#' + id).click();
    }

    if (!<%=isNull%>) {
        $("input[name='investorType'][value='<%=isNull?"1":investor.getInvestorType()%>']").attr("checked", true);
    }
    function resetFileInput(file){
        file.after(file.clone().val(""));
        file.remove();
    }
</script>
</html>
