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
    <script src="../statics/jquery/jquery-form.js"></script>
    <script src="../statics/js/config.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div id="baseDiv" style="display: none;">
    <div>基本信息</div>
    <div>
        <input type="file" id="logoFile" name="logoFile" style="display: none;"/>
        <input type="file" id="bpFile" name="bpFile" style="display: none;"/>
        <form id="baseForm">
            <input type="hidden" id="id" name="id" value="0"/>
            <input type="hidden" id="logo" name="logo"/>
            <input type="hidden" id="bp" name="bp"/>
            <input type="hidden" id="bpName" name="bpName"/>
            <input type="hidden" id="industry" name="industry" value="0"/>
            <table>
                <tr>
                    <td>LOGO</td>
                    <td>
                        <img id="logoImg"
                             src="<%=(isNull|| StringUtils.isEmpty(project.getLogo()))?"https://www.vchello.com/NewHome/src/images/upload-logo.png":project.getLogo() %>"
                             title="点击添加图片"
                             width="180" height="180" style="position: relative; z-index: 1;" onclick="selectFile('logoFile');">
                        <input type="button" value="上传" onclick="uploadImage('logoFile','logoImg','logo')"/></td>
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
                                    industrys.html("<span onclick='fixIndustry(" + result.data.id + ")'>" + result.data.name + "</span>&nbsp;&nbsp;");
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
                        <select id="area" name="area"></select>
                        <script>
                            <%
                                if(!isNull && project.getIndustry()!=null){
                                %>
                            $.get("/classinfo/get/<%=project.getArea() %>", function (result) {
                                if (result.code < 0)
                                    return;
                                $("#area").val(result.data.area);
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
                        <select id="financingLimit" name="financingLimit"></select>
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
                    <td>
                        <%
                            if (isNull || StringUtils.isEmpty(project.getBp())) {
                        %><span id="bpSelect" onclick="selectFile('bpFile')">选择BP</span><input type="button" value="上传"
                                                                                               onclick="uploadFile('bpFile','bpSelect')"/><%
                    } else {
                    %><%=project.getBpName() %>&nbsp;<span id="bpSelect" onclick="selectFile('bpFile')">选择BP</span><input
                            type="button" value="上传"
                            onclick="uploadFile('bpFile','bpSelect')"/>
                        <%
                            }
                        %>
                    </td>
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
                        <button type="button" onclick="saveInfo();">保存</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="memberDiv">
    <div>团队信息</div>
    <div id="members"></div>
    <a href="#" onclick="modalShow('#bigModal', '', modalDataInit(0));">添加核心成员</a>
    <a href="#" onclick="">保存</a>
</div>
<div id="videoDiv" style="display: none;">
    <div>媒体报道</div>
    <div></div>
</div>
<div class="modal bs-example-modal-lg" id="bigModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" onclick="modalHide('#bigModal', '');" class="close"
                        data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">核心成员</h4>
            </div>
            <div class="modal-body">
                <input type="file" id="photoFile" name="photoFile" style="display: none;"/>
                <form id="memberForm">
                    <input type="hidden" id="photo" name="photo"/>
                    <input type="hidden" id="memberid" name="id" value="0"/>
                    <input type="hidden" id="projectid" name="projectid"/>
                    <table>
                        <tr>
                            <td>头像</td>
                            <td>
                                <img width="180" height="180" id="photoImg" onclick="selectFile('photoFile');"/>&nbsp;
                                <a href="#" onclick="uploadImage('photoFile','photoImg','photo')">上传</a>
                            </td>
                        </tr>
                        <tr>
                            <td>姓名</td>
                            <td><input id="memberName" name="memberName"/></td>
                        </tr>
                        <tr>
                            <td>是否创始人</td>
                            <td>
                                <input type="radio" name="isFounder" value="1"/>是
                                <input type="radio" name="isFounder" value="0" checked="checked"/>否
                            </td>
                        </tr>
                        <tr>
                            <td>职位</td>
                            <td><input id="position" name="position"/></td>
                        </tr>
                        <tr>
                            <td>个人介绍</td>
                            <td><input id="memberSummary" name="summary"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="button" value="保存" onclick="saveMember()"/></td>
                        </tr>
                    </table>
                </form>
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
        //callback.apply(this);
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
        //callback.apply(this);
    }
    var modalDataInit = function (memberid) {
        $('#memberForm')[0].reset();
        $("#projectid").val(projectid);
        if (memberid == 0) {
            $("#photoImg").attr("src", "https://www.vchello.com/NewHome/src/images/founderImg.png");
        } else {

        }
    }

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
</script>
</html>
