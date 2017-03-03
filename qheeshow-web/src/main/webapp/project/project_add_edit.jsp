<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object object = request.getAttribute("project");
    Project project = object == null ? null : (Project) object;
    boolean isNull = project == null;
    Calendar calendar = Calendar.getInstance();
    int curYear = calendar.get(Calendar.YEAR);
    List<Xwcmclassinfo> industrys = request.getAttribute("industrys") == null ? new ArrayList<Xwcmclassinfo>() : (List<Xwcmclassinfo>) request.getAttribute("industrys");
    List<Xwcmclassinfo> areas = request.getAttribute("areas") == null ? new ArrayList<Xwcmclassinfo>() : (List<Xwcmclassinfo>) request.getAttribute("areas");
    List<Xwcmclassinfo> financingLimits = request.getAttribute("financingLimits") == null ? new ArrayList<Xwcmclassinfo>() : (List<Xwcmclassinfo>) request.getAttribute("financingLimits");
    List<Xwcmclassinfo> stages = request.getAttribute("stages") == null ? new ArrayList<Xwcmclassinfo>() : (List<Xwcmclassinfo>) request.getAttribute("stages");
    String[] datas1 = isNull ? null : project.getLastOne().split(":")[1].split("#");
    String[] datas2 = isNull ? null : project.getLastTwo().split(":")[1].split("#");
%>
<html>
<head>
    <title><%=Config.get("app.name")%>--创建项目</title>
    <link rel="stylesheet" href="/images/animate.min.css">
    <link rel="stylesheet" href="/images/bootstrap.css">
    <!--*************************bootstrap css end************************-->
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <!--*************************创建项目的主链接************************-->
    <link rel="stylesheet" href="/images/project.css"/>
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="/jquery/ajaxfileupload.js"></script>
    <script src="/jquery/jquery-form.js"></script>
    <script src="/js/config.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="pro-body">
    <form id="baseForm">
        <input type="hidden" id="lastOne" name="lastOne" value="<%=curYear-1 %>:1_0#2_0#3_0#4_0"/>
        <input type="hidden" id="lastTwo" name="lastTwo" value="<%=curYear-2 %>:1_0#2_0#3_0#4_0"/>
        <input type="hidden" id="id" name="id" value="0"/>
        <input type="hidden" id="logo" name="logo"/>
        <input type="hidden" id="bp" name="bp"/>
        <input type="hidden" id="bpName" name="bpName"/>
        <input type="hidden" id="industry" name="industry" value="0"/>
        <input type="file" id="logoFile" name="logoFile" style="display: none;"/>
        <input type="file" id="bpFile" name="bpFile" style="display: none;"/>
        <div class="pro-wap">
            <div class="pro-t">项目信息(1/3)</div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目LOGO</li>
                    <li class="on2">
                        <img id="logoImg" onclick="selectFile('logoFile');" title="点击添加图片"
                             src="<%=(isNull|| StringUtils.isEmpty(project.getLogo()))?"/images/bg-new1.png":project.getLogo() %>"
                             class="oimg"/>
                        <span><a href="#" class="on"
                                 onclick="uploadImage('logoFile','logoImg','logo')">上传LOGO</a></span>
                    </li>
                    <li class="on3">支持png/jpg/jepg格式</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目名称</li>
                    <li class="on2"><input name="title"
                                           value="<%=(isNull || StringUtils.isEmpty(project.getTitle()))?"":project.getTitle()%>"
                                           class="pro-one-ipt" placeholder="填写你的项目名称，不超过8个字"/></li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">解决需求</li>
                    <li class="on2"><input name="demand"
                                           value="<%=(isNull || StringUtils.isEmpty(project.getDemand()))?"":project.getDemand()%>"
                                           class="pro-one-ipt" placeholder="一句话介绍你的项目所能解决的市场需求，不超过30个汉字"/>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目亮点</li>
                    <li class="on2">
                        <textarea name="highlights" class="pro-one-tex"
                                  placeholder="一句话介绍你的团队的优势（不超过300字）"><%=(isNull || StringUtils.isEmpty(project.getHighlights())) ? "" : project.getHighlights()%></textarea>
                    </li>
                    <li class="on3">投资人重点关注</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目视频<span>（选填）</span></li>
                    <li class="on2"><input name="videoLink"
                                           value="<%=(isNull || StringUtils.isEmpty(project.getVideoLink()))?"":project.getVideoLink()%>"
                                           class="pro-one-ipt" placeholder="添加视频播放地址"/></li>
                    <li class="on3">支持MP4格式／优酷／土豆视频链接</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">项目详述</li>
                    <li class="on2">
                        <textarea name="description" class="pro-one-tex"
                                  placeholder="项目详述"><%=(isNull || StringUtils.isEmpty(project.getDescription())) ? "" : project.getDescription()%></textarea>
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
                                    </a><%
                                    } else {
                                    %><a class="a1" href="#">500万以下</a><%
                                        }
                                    %>
                                    </div>
                                    <div id="one1_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="one1" class="pro-menu" style="display: none;">
                                        <li><a style="cursor: pointer" onclick="setOne1('500万以下')">500万以下</a></li>
                                        <li><a style="cursor: pointer" onclick="setOne1('500~2000万')">500~2000万</a></li>
                                        <li><a style="cursor: pointer" onclick="setOne1('2000~5000万')">2000~5000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setOne1('5000~10000万')">5000~10000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setOne1('10000万以上')">10000万以上</a></li>
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
                                    </a><%
                                    } else {
                                    %><a class="a1" href="#">100万以下</a><%
                                        }
                                    %>
                                    </div>
                                    <div id="one2_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="one2" class="pro-menu" style="display: none;">
                                        <li><a style="cursor: pointer" onclick="setOne2('100万以下')">100万以下</a></li>
                                        <li><a style="cursor: pointer" onclick="setOne2('100~500万')">100~500万</a></li>
                                        <li><a style="cursor: pointer" onclick="setOne2('500~1000万')">500~1000万</a></li>
                                        <li><a style="cursor: pointer" onclick="setOne2('1000~3000万')">1000~3000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setOne2('3000万以上')">3000万以上</a></li>
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
                                    </a><%
                                    } else {
                                    %><a class="a1" href="#">1000万以下</a><%
                                        }
                                    %>
                                    </div>
                                    <div id="one3_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="one3" class="pro-menu" style="display: none;">
                                        <li><a style="cursor: pointer" onclick="setOne3('1000万以下')">1000万以下</a></li>
                                        <li><a style="cursor: pointer" onclick="setOne3('1000~5000万')">1000~5000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setOne3('5000~10000万')">5000~10000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setOne3('10000万以上')">10000万以上</a></li>
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
                                    </a><%
                                    } else {
                                    %><a class="a1" href="#">1000万以下</a><%
                                        }
                                    %>
                                    </div>
                                    <div id="one4_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="one4" class="pro-menu" style="display: none;">
                                        <li><a style="cursor: pointer" onclick="setOne4('500万以下')">500万以下</a></li>
                                        <li><a style="cursor: pointer" onclick="setOne4('500~2000万')">500~2000万</a></li>
                                        <li><a style="cursor: pointer" onclick="setOne4('2000~5000万')">2000~5000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setOne4('5000万以上')">5000万以上</a></li>
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
                                    </a><%
                                    } else {
                                    %><a class="a1" href="#">500万以下</a><%
                                        }
                                    %>
                                    </div>
                                    <div id="two1_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="two1" class="pro-menu" style="display: none;">
                                        <li><a style="cursor: pointer" onclick="setTwo1('500万以下')">500万以下</a></li>
                                        <li><a style="cursor: pointer" onclick="setTwo1('500~2000万')">500~2000万</a></li>
                                        <li><a style="cursor: pointer" onclick="setTwo1('2000~5000万')">2000~5000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setTwo1('5000~10000万')">5000~10000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setTwo1('10000万以上')">10000万以上</a></li>
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
                                    </a><%
                                    } else {
                                    %><a class="a1" href="#">100万以下</a><%
                                        }
                                    %>
                                    </div>
                                    <div id="two2_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="two2" class="pro-menu" style="display: none;">
                                        <li><a style="cursor: pointer" onclick="setTwo2('100万以下')">100万以下</a></li>
                                        <li><a style="cursor: pointer" onclick="setTwo2('100~500万')">100~500万</a></li>
                                        <li><a style="cursor: pointer" onclick="setTwo2('500~1000万')">500~1000万</a></li>
                                        <li><a style="cursor: pointer" onclick="setTwo2('1000~3000万')">1000~3000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setTwo2('3000万以上')">3000万以上</a></li>
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
                                    </a><%
                                    } else {
                                    %><a class="a1" href="#">1000万以下</a><%
                                        }
                                    %>
                                    </div>
                                    <div id="two3_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="two3" class="pro-menu" style="display: none;">
                                        <li><a style="cursor: pointer" onclick="setTwo3('1000万以下')">1000万以下</a></li>
                                        <li><a style="cursor: pointer" onclick="setTwo3('1000~5000万')">1000~5000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setTwo3('5000~10000万')">5000~10000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setTwo3('10000万以上')">10000万以上</a></li>
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
                                    </a><%
                                    } else {
                                    %><a class="a1" href="#">1000万以下</a><%
                                        }
                                    %>
                                    </div>
                                    <div id="two4_" class="pro-menu-ico" style="display: none;"></div>
                                    <ul id="two4" class="pro-menu" style="display: none;">
                                        <li><a style="cursor: pointer" onclick="setTwo4('500万以下')">500万以下</a></li>
                                        <li><a style="cursor: pointer" onclick="setTwo4('500~2000万')">500~2000万</a></li>
                                        <li><a style="cursor: pointer" onclick="setTwo4('2000~5000万')">2000~5000万</a>
                                        </li>
                                        <li><a style="cursor: pointer" onclick="setTwo4('5000万以上')">5000万以上</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="on3">财务数据是投资人最关心的数据之一</li>
                </ul>
            </div>
            <!--*************************需融资金额************************-->
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">需融资金额</li>
                    <li class="on8">
                        <ul class="pro-fo-lst">
                            <%
                                for (Xwcmclassinfo xwcmclassinfo : financingLimits) {
                                    if (!isNull && xwcmclassinfo.getClassinfoid().intValue() == project.getFinancingLimit()) {
                            %>
                            <li class="on"><a href="#" onclick="fixFinancingLimit();"><%=xwcmclassinfo.getCname() %>
                            </a></li>
                            <%
                            } else {
                            %>
                            <li><a href="#"><%=xwcmclassinfo.getCname() %>
                            </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                    </li>
                </ul>
                <ul class="pro-one-lst">
                    <li class="on1">所属行业</li>
                    <li class="on8">
                        <ul class="pro-fo-lst">
                            <%
                                for (Xwcmclassinfo xwcmclassinfo : industrys) {
                                    if (!isNull && xwcmclassinfo.getClassinfoid().intValue() == project.getFinancingLimit()) {
                            %>
                            <li class="on"><a href="#" onclick="fixIndustry();"><%=xwcmclassinfo.getCname() %>
                            </a></li>
                            <%
                            } else {
                            %>
                            <li><a href="#"><%=xwcmclassinfo.getCname() %>
                            </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                    </li>
                </ul>
                <ul class="pro-one-lst">
                    <li class="on1">项目地点</li>
                    <li class="on8">
                        <ul class="pro-fo-lst">
                            <%
                                for (Xwcmclassinfo xwcmclassinfo : areas) {
                                    if (!isNull && xwcmclassinfo.getClassinfoid().intValue() == project.getFinancingLimit()) {
                            %>
                            <li class="on"><a href="#" onclick="fixArea();"><%=xwcmclassinfo.getCname() %>
                            </a></li>
                            <%
                            } else {
                            %>
                            <li><a href="#"><%=xwcmclassinfo.getCname() %>
                            </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                    </li>
                </ul>
                <ul class="pro-one-lst">
                    <li class="on1">融资阶段</li>
                    <li class="on8">
                        <ul class="pro-fo-lst">
                            <%
                                for (Xwcmclassinfo xwcmclassinfo : stages) {
                                    if (!isNull && xwcmclassinfo.getClassinfoid().intValue() == project.getFinancingLimit()) {
                            %>
                            <li class="on"><a href="#" onclick="fixStage();"><%=xwcmclassinfo.getCname() %>
                            </a></li>
                            <%
                            } else {
                            %>
                            <li><a href="#"><%=xwcmclassinfo.getCname() %>
                            </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">商业计划书<span>（选填）</span></li>
                    <li class="on2">
                        <ul class="pro-fiv-lst">
                            <li class="e1"><a id="bpSelect" onclick="selectFile('bpFile')" href="#">点击选择</a></li>
                            <li class="e3"><span><a href="#" onclick="uploadFile('bpFile','bpSelect')">上传</a></span>
                            </li>
                        </ul>
                    </li>
                    <li class="on3">支持pdf/ppt/pptx/doc/docx格式</li>
                </ul>
            </div>
            <!--*************************下一步按钮************************-->
            <div class="pro-clear"></div>
            <div class="pro-btn"><a href="#">下一步</a></div>
        </div>
    </form>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    var projectid = <%=isNull?"0":project.getId().toString()%>;
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
            alert("请选择图片文件");
            return;
        }
        $.ajaxFileUpload({
                    url: '/image/upload', //用于文件上传的服务器端请求地址
                    type: 'post',
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: fileid, //文件上传域的ID
                    dataType: 'json', //返回值类型 一般设置为json
                    success: function (result) {  //服务器成功响应处理函数
                        if (result.code == -1) {
                            alert(result.message);
                            return;
                        }
                        $('#' + imgid).attr("src", result.data.path);
                        $('#' + hiddenid).val(result.data.path);
                    }
                }
        );
    }
    //上传文件
    function uploadFile(fileid, showid) {
        var file = $('#' + fileid);
        if (!file || !file.val())
            return;
        var patn = /\.pdf$|\.ppt$|\.pptx$/i;
        if (!patn.test(file.val())) {
            alert("文件类型不正确");
            return;
        }
        $.ajaxFileUpload({
                    url: '/file/upload', //用于文件上传的服务器端请求地址
                    type: 'post',
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: fileid, //文件上传域的ID
                    dataType: 'json', //返回值类型 一般设置为json
                    success: function (result) {  //服务器成功响应处理函数
                        if (result.code == -1) {
                            alert(result.message);
                            return;
                        }
                        $('#' + showid).html(result.data.name);
                        $('#bp').val(result.data.path);
                        $('#bpName').val(result.data.name);
                    }
                }
        );
    }
    //获得行业信息
    function listIndustry() {
        $.get("/classinfo/list/root/" + classinfo_rootid_industry, function (result) {
            if (result.code < 0)
                return;
            var industrys = $('#industrys');
            industrys.html("");
            for (i = 0; i < result.data.length; i++) {
                industrys.append("<span onclick='fixIndustry(" + result.data[i].id + ")'>" + result.data[i].name + "</span>&nbsp;&nbsp;");
            }
        }, "json");
    }
    //获得地域信息
    $.get("/classinfo/list/root/" + classinfo_rootid_area, function (result) {
        if (result.code < 0)
            return;
        var area = $('#area');
        area.html("<option value='0'>请选择</option>");
        for (i = 0; i < result.data.length; i++) {
            area.append("<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>");
        }
    }, "json");
    //获得融资额度信息
    $.get("/classinfo/list/root/" + classinfo_rootid_financing_limit, function (result) {
        if (result.code < 0)
            return;
        var financingLimit = $('#financingLimit');
        financingLimit.html("<option value='0'>请选择</option>");
        for (i = 0; i < result.data.length; i++) {
            financingLimit.append("<option value='" + result.data[i].id + "'>" + result.data[i].name + "</option>");
        }
    }, "json");
    //保存基本信息
    function saveBase() {
        $.ajax({
            type: 'POST',
            url: '/project/base/save',
            cache: false,
            processData: false,
            data: $('#baseForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                $("#id").val(result.data);
                $("#baseDiv").hide();
                $("#projectDiv").show();
            }
        });
    }
    //保存项目信息
    function saveInfo() {
        $.ajax({
            type: 'POST',
            url: '/project/info/save/' + projectid,
            cache: false,
            processData: false,
            data: $('#projectForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                $("#projectDiv").hide();
                $("#memberDiv").show();
            }
        });
    }
    //保存成员信息
    function saveMember() {
        $.ajax({
            type: 'POST',
            url: '/team/member/save/',
            cache: false,
            processData: false,
            data: $('#memberForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                modalHide('#bigModal', '');
                var memberid = $("#memberid").val();
                var member = result.data;
                if (memberid == 0)
                    $("#members").append("<div id='memberDiv" + member.id + "'>姓名:" + member.memberName + "&nbsp;头像:<img width='180' height='180' src='" + member.photo + "'/>&nbsp;<a href='#' onclick='modifyMember(" + member.id + ");'>编辑</a>&nbsp;<a href='#' onclick='delMember(" + member.id + ");'>删除</a></div>")
                else
                    $("#memberDiv" + member.id).html("姓名:" + member.memberName + "&nbsp;头像:<img width='180' height='180' src='" + member.photo + "'/>&nbsp;<a href='#' onclick='modifyMember(" + member.id + ");'>编辑</a>&nbsp;<a href='#' onclick='delMember(" + member.id + ");'>删除</a>")
            }
        });
    }
    //删除成员
    function delMember(memberid) {
        $.get("/team/member/del/" + memberid, function (result) {
            if (result.code < 0) {
                alert(result.message);
                return;
            }
            $("#memberDiv" + memberid).remove();
        }, "json");
    }
    //修改成员
    function modifyMember(memberid) {
        $.get("/team/member/get/" + memberid, function (result) {
            if (result.code < 0) {
                alert(result.message);
                return;
            }
            var member = result.data;
            $("#photo").val(member.photo);
            $("#memberid").val(member.id);
            $("#projectid").val(member.projectid);
            $("#photoImg").attr("src", member.photo);
            $("#memberName").val(member.memberName);
            $("input[name='isFounder'][value='" + member.isFounder + "']").attr("checked", true);
            $("#position").val(member.position);
            $("#memberSummary").val(member.summary);
        }, "json");
    }
    //选择所属行业
    function fixIndustry(id) {
        $("#industry").val(id);
    }
    var one1 = "0";
    var one2 = "0";
    var one3 = "0";
    var one4 = "0";
    var two1 = "0";
    var two2 = "0";
    var two3 = "0";
    var two4 = "0";
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
</script>
</html>
