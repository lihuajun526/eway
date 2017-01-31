<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object object = request.getAttribute("project");
    Project project = object == null ? null : (Project) object;
    boolean isNull = project == null;
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="../statics/jquery/ajaxfileupload.js"></script>
    <script src="../statics/js/config.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div id="baseDiv">
    <div>基本信息</div>
    <div>
        <form id="baseForm">
            <input type="hidden" id="id" name="id" value="0"/>
            <table>
                <tr>
                    <td>LOGO</td>
                    <td>
                        <img id="logoImg"
                             src="<%=(isNull|| StringUtils.isEmpty(project.getLogo()))?"https://www.vchello.com/NewHome/src/images/upload-logo.png":project.getLogo() %>"
                             title="点击添加图片"
                             width="180" height="180" style="position: relative; z-index: 1;" onclick="selectLogo();">
                        <input type="file" id="logoFile" name="logoFile" style="display: none;"/>
                        <input type="button" value="上传" onclick="uploadPic('logoFile','logoImg')"/></td>
                </tr>
                <tr>
                    <td>项目名</td>
                    <td><input name="title"
                               value="<%=(isNull || StringUtils.isEmpty(project.getTitle()))?"":project.getTitle()%>"/>
                    </td>
                </tr>
                <tr>
                    <td>一句话介绍</td>
                    <td><textarea name="summary"><%=(isNull || StringUtils.isEmpty(project.getSummary())) ?
                            "" :
                            project.getSummary()%></textarea></td>
                </tr>
                <tr>
                    <td>所属行业</td>
                    <td>
                        <div id="industrys" onclick="listIndustry();"></div>
                        <script>
                            <%
                                if(!isNull && project.getIndustry()!=null){
                                %>
                            $.get("/classinfo/get/<%=project.getIndustry() %>", function (result) {
                                if (result.code < 0)
                                    return;
                                var industrys = $('#industrys');
                                if (result.data != null)
                                    industrys.html("<span>" + result.data.name + "</span>");
                            }, "json");
                            <%
                            }else{
                            %>
                            $("#industrys").html("选择行业");
                            <%
                            }
                        %>
                        </script>
                    </td>
                </tr>
                <tr>
                    <td>所在城市</td>
                    <td>
                        <select id="areas"></select>
                        <script>
                            <%
                                if(!isNull && project.getIndustry()!=null){
                                %>
                            $.get("/classinfo/get/<%=project.getArea() %>", function (result) {
                                if (result.code < 0)
                                    return;
                                $("#areas").val(result.data.area);
                            }, "json");
                            <%
                            }
                            %>
                        </script>
                    </td>
                </tr>
                <tr>
                    <td>融资规模</td>
                    <td>
                        <select id="financingLimit"></select>
                        <script>
                            <%
                                if(!isNull && project.getFinancingLimit()!=null){
                                %>
                            $.get("/classinfo/get/<%=project.getFinancingLimit() %>", function (result) {
                                if (result.code < 0)
                                    return;
                                $("#financingLimit").val(result.data.financingLimit);
                            }, "json");
                            <%
                            }
                            %>
                        </script>
                        出让比例<input name="percent"
                                   value="<%=(isNull || project.getPercent()==null)?"":String.valueOf(project.getPercent())%>"/>
                    </td>
                </tr>
                <tr>
                    <td>推荐人姓名</td>
                    <td><input name="referee"
                               value="<%=(isNull || StringUtils.isEmpty(project.getReferee()))?"":project.getReferee()%>"/>
                    </td>
                </tr>
                <tr>
                    <td>商业计划书</td>
                    <td><input type="file" name="bp"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="button" onclick="saveBase();">保存</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="projectDiv" style="display: none;">
    <div>项目信息</div>
    <div>
        <form id="projectForm">
        <table>
            <tr>
                <td>项目介绍</td>
                <td><textarea name="desc"><%=(isNull || StringUtils.isEmpty(project.getDesc())) ?
                        "" :
                        project.getDesc() %></textarea></td>
            </tr>
            <tr>
                <td>宣传视频</td>
                <td><input name="videoLink" <%=(isNull || StringUtils.isEmpty(project.getVideoLink())) ?
                        "" :
                        project.getVideoLink()%>/></td>
            </tr>
            <tr>
                <td>产品网址</td>
                <td><input name="proLink"<%=(isNull || StringUtils.isEmpty(project.getProLink())) ?
                        "" :
                        project.getProLink()%>/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button">保存</button>
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>
<div id="memberDiv" style="display: none;">
    <div>团队信息</div>
    <div></div>
    <a href="#">添加核心成员</a>
</div>
<div id="videoDiv" style="display: none;">
    <div>媒体报道</div>
    <div></div>
</div>
<button type="button" class="btn btn-primary  test-btn" onclick="modalShow('#bigModal', '', modalDataInit('test'));">
    模态框测试
</button>
<div class="modal bs-example-modal-lg" onclick="modalHide('#bigModal', '');"
     id="bigModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" onclick="modalHide('#bigModal', '');" class="close"
                        data-dismiss="modal">
                    <span aria-hidden="true">
                        ×
                    </span>
                    <span class="sr-only">
                        Close
                    </span>
                </button>
                <h4 class="modal-title">
                    模态框标题
                </h4>
            </div>
            <div class="modal-body">
            </div>
        </div>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    //animate.css动画触动一次方法
    $.fn.extend({
        animateCss: function (animationName) {
            var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
            this.addClass('animated ' + animationName).one(animationEnd, function () {
                $(this).removeClass('animated ' + animationName);
            });
        }
    });
    /**
     * 显示模态框方法
     * @param targetModel 模态框选择器，jquery选择器
     * @param animateName 弹出动作
     * @ callback 回调方法
     */
    var modalShow = function (targetModel, animateName, callback) {
        var animationIn = ["bounceIn", "bounceInDown", "bounceInLeft", "bounceInRight", "bounceInUp",
            "fadeIn", "fadeInDown", "fadeInLeft", "fadeInRight", "fadeOutUp",
            "fadeInDownBig", "fadeInLeftBig", "fadeOutRightBig", "fadeOutUpBig", "flipInX", "flipInY",
            "lightSpeedIn", "rotateIn", "rotateInDownLeft", "rotateInDownRight", "rotateInUpLeft", "rotateInUpRight",
            "zoomIn", "zoomInDown", "zoomInLeft", "zoomInRight", "zoomInUp", "slideInDown", "slideInLeft",
            "slideInRight", "slideInUp", "rollIn"];
        if (!animateName || animationIn.indexOf(animateName) == -1) {
            console.log(animationIn.length);
            var intRandom = Math.floor(Math.random() * animationIn.length);
            animateName = animationIn[intRandom];
        }
        console.log(targetModel + " " + animateName);
        $(targetModel).show().animateCss(animateName);
        callback.apply(this);
    }
    /**
     * 隐藏模态框方法
     * @param targetModel 模态框选择器，jquery选择器
     * @param animateName 隐藏动作
     * @ callback 回调方法
     */
    var modalHide = function (targetModel, animateName, callback) {
        var animationOut = ["bounceOut", "bounceOutDown", "bounceOutLeft", "bounceOutRight", "bounceOutUp",
            "fadeOut", "fadeOutDown", "fadeOutLeft", "fadeOutRight", "fadeOutUp",
            "fadeOutDownBig", "fadeOutLeftBig", "fadeOutRightBig", "fadeOutUpBig", "flipOutX", "flipOutY",
            "lightSpeedOut", "rotateOut", "rotateOutDownLeft", "rotateOutDownRight", "rotateOutUpLeft", "rotateOutUpRight",
            "zoomOut", "zoomOutDown", "zoomOutLeft", "zoomOutRight", "zoomOutUp",
            "zoomOut", "zoomOutDown", "zoomOutLeft", "zoomOutRight", "zoomOutUp", "slideOutDown", "slideOutLeft",
            "slideOutRight", "slideOutUp", "rollOut"];
        if (!animateName || animationOut.indexOf(animateName) == -1) {
            console.log(animationOut.length);
            var intRandom = Math.floor(Math.random() * animationOut.length);
            animateName = animationOut[intRandom];
        }
        $(targetModel).children().click(function (e) {
            e.stopPropagation()
        });
        $(targetModel).animateCss(animateName);
        $(targetModel).delay(900).hide(1, function () {
            $(this).removeClass('animated ' + animateName);
        });
        callback.apply(this);
    }
    var modalDataInit = function (info) {
        //alert(info);
        //填充数据，对弹出模态框数据样式初始化或修改
    }

    function selectLogo() {
        $('#logoFile').click();
    }

    function uploadPic(fileid, imgid) {
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
                        $('#' + imgid).attr("src", result.data);
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
                industrys.append("<span>" + result.data[i].name + "</span>");
            }
        }, "json");
    }
    //获得地域信息
    $.get("/classinfo/list/root/" + classinfo_rootid_area, function (result) {
        if (result.code < 0)
            return;
        var areas = $('#areas');
        industrys.html("<option id='0'>请选择</option>");
        for (i = 0; i < result.data.length; i++) {
            industrys.append("<span>" + result.data[i].name + "</span>");
        }
    }, "json");
    //获得融资额度信息
    $.get("/classinfo/list/root/" + classinfo_rootid_financing_limit, function (result) {
        if (result.code < 0)
            return;
        var financingLimit = $('#financingLimit');
        financingLimit.html("<option id='0'>请选择</option>");
        for (i = 0; i < result.data.length; i++) {
            financingLimit.append("<span>" + result.data[i].name + "</span>");
        }
    }, "json");
    //保存基本信息
    function saveBase() {
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '/project/base/save',
            data: $('#baseForm').serialize(),
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

</script>
</html>
