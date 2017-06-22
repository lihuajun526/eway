<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.TeamMember" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Project project = (Project) request.getAttribute("project");
    List<TeamMember> members = (List<TeamMember>) request.getAttribute("members");
    String flag = "2";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--创建项目</title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/project.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/images/bootstrap.min.js"></script>
    <script src="<%=appPath%>/jquery/jquery-form.js"></script>
    <script>
        var flag = true;
    </script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div class="pro-body">
    <div class="pro-wap">
        <div class="pro-t">创始人信息(2/3)</div>
        <div class="empty"></div>
        <%
            if (members.size() == 0) {
        %>
        <form>
            <input name="id" type="hidden" value="0"/>
            <input name="projectid" type="hidden" value="<%=project.getId()%>"/>
            <input id="photo_0" name="photo" type="hidden"/>

            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人头像</li>
                    <li class="on2">
                        <div class="pro-lst2">
                            <img id="photoImg_0" src="<%=appPath%>/images/bg-new1.png" width="130" height="130"/>
                            <span><a class="on">上传头像</a></span><input name="photoFile" type='file' unselectable="on"
                                                                      class="on2" onchange="uploadImage(this,0)"/>
                        </div>
                    </li>
                    <li class="on3">支持png/jpg/jepg格式</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人信息</li>
                    <li class="on2">
                        <ul>
                            <li class="pro-c-name">
                                <input id="memberName_0" name="memberName" class="pro-one-ipt1" placeholder="姓名">
                                <span class="pro6-left-top"></span><span class="pro6-right-top"></span><span
                                    class="pro6-right-bottom"></span><span class="pro6-left-bottom"></span>
                            </li>
                            <li>
                                <input id="position_0" name="position" class="pro-one-ipt1" placeholder="职位">
                                <span class="pro6-left-top"></span><span class="pro6-right-top"></span><span
                                    class="pro6-right-bottom"></span><span class="pro6-left-bottom"></span>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人简介</li>
                    <li class="on2">
                        <ul class="pro-six-lst">
                            <li>
                                <textarea id="summary_0" name="summary" class="pro-one-tex"
                                          placeholder="个人简介（不超过300字）"></textarea>
                                <span class="pro6-left-top"></span><span class="pro6-right-top"></span><span
                                    class="pro6-right-bottom"></span><span class="pro6-left-bottom"></span>
                            </li>
                        </ul>
                        <div class="pro-six-janj">详细的团队成员信息，可以让投资人透彻的
                            了解团队的组成情况，团队中每位成员的亮
                            点都能为项目起到加分的作用
                        </div>
                    </li>
                </ul>
            </div>
            <div class="pro-clear"></div>
            <div class="pro-btn1"><a onclick="saveTeam(this,0);">保 存</a></div>
        </form>
        <script>flag = false;</script>
        <%
        } else {
            for (int i = 0; i < members.size(); i++) {
                TeamMember member = members.get(i);
        %>
        <form>
            <input name="id" type="hidden" value="<%=member.getId()%>"/>
            <input name="projectid" type="hidden" value="<%=member.getProjectid()%>"/>
            <input id="photo_<%=member.getId()%>" name="photo" type="hidden" value="<%=member.getPhoto()%>"/>

            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人头像</li>
                    <li class="on2">
                        <div class="pro-lst2">
                            <img id="photoImg_<%=member.getId()%>"
                                 src="<%=StringUtils.isEmpty(member.getPhoto())?appPath+"/images/bg-new1.png":member.getPhoto()%>"
                                 width="130" height="130"/>
                            <span><a class="on">修改头像</a></span><input name="photoFile" type='file' unselectable="on"
                                                                      class="on2"
                                                                      onchange="uploadImage(this,<%=member.getId()%>)"/>
                        </div>
                    </li>
                    <li class="on3">支持png/jpg/jepg格式</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人信息</li>
                    <li class="on2">
                        <ul>
                            <li class="pro-c-name">
                                <input id="memberName_<%=member.getId()%>" name="memberName" class="pro-one-ipt1"
                                       value="<%=member.getMemberName()%>">
                                <span class="pro6-left-top"></span><span class="pro6-right-top"></span><span
                                    class="pro6-right-bottom"></span><span class="pro6-left-bottom"></span>
                            </li>
                            <li>
                                <input id="position_<%=member.getId()%>" name="position" class="pro-one-ipt1"
                                       value="<%=member.getPosition()%>">
                                <span class="pro6-left-top"></span><span class="pro6-right-top"></span><span
                                    class="pro6-right-bottom"></span><span class="pro6-left-bottom"></span>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人简介</li>
                    <li class="on2">
                        <ul class="pro-six-lst">
                            <li>
                                <textarea id="summary_<%=member.getId()%>" name="summary"
                                          class="pro-one-tex"><%=member.getSummary()%></textarea>
                                <span class="pro6-left-top"></span><span class="pro6-right-top"></span><span
                                    class="pro6-right-bottom"></span><span class="pro6-left-bottom"></span>
                            </li>
                        </ul>
                        <%
                            if (i == 0) {
                        %>
                        <div class="pro-six-janj">详细的团队成员信息，可以让投资人透彻的
                            了解团队的组成情况，团队中每位成员的亮
                            点都能为项目起到加分的作用
                        </div>
                        <%
                            }
                        %>
                    </li>
                </ul>
            </div>
            <div class="pro-clear"></div>
            <div class="pro-btn1"><a onclick="saveTeam(this,<%=member.getId()%>);">保 存</a></div>
        </form>
        <%
                }
            }
        %>
        <div id="members"></div>
        <div class="group"><a onclick="addMember();">添加核心成员</a></div>
        <div class="pro-clear"></div>
        <div class="pro-btn"><a href="<%=appPath%>/project/<%=project.getId() %>/add/edit/1/auth">上一步</a><a
                href="<%=appPath%>/project/<%=project.getId() %>/add/edit/3/auth">下一步</a></div>
    </div>
</div>
<%@include file="../pub/foot.jsp" %>
</body>
<script>
    //保存基本信息
    function saveTeam(obj, id) {
        if (!$("#memberName_" + id).val().length) {
            xalert("姓名不能为空");
            return;
        }
        if (!$("#position_" + id).val().length) {
            xalert("职位不能为空");
            return;
        }
        if (!$("#summary_" + id).val().length) {
            xalert("个人简介不能为空");
            return;
        }
        $.ajax({
            type: 'POST',
            url: '<%=appPath%>/project/team/save/authj',
            cache: false,
            processData: false,
            data: $(obj).parent().parent().serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    xalert(result.message);
                    return;
                }
                xalert("保存成功");
                window.location.reload();
            }
        });
    }
    function addMember() {
        if (!flag) {
            xalert("请先保存团队信息");
            return;
        }
        $("#members").load("<%=appPath%>/project/member_append.jsp?projectid=<%=project.getId()%>");
    }
    var imgid = null;
    var hiddenid = null;
    //上传photo
    function uploadImage(obj, id) {
        var file = $(obj);
        if (!file || !file.val())
            return;
        imgid = "photoImg_" + id;
        hiddenid = "photo_" + id;
        var patn = /\.jpg$|\.jpeg$|\.png$|\.gif$/i;
        if (!patn.test(file.val())) {
            xalert("请选择图片文件");
            return;
        }
        $(obj).wrap("<form id='myUpload' action='<%=appPath%>/image/upload' method='post' enctype='multipart/form-data'></form>");
        $('#myUpload').ajaxSubmit({
            dataType: 'json',
            success: function (result) {
                $(obj).unwrap();
                resetFileInput(file);
                if (result.code == -1) {
                    xalert(result.message);
                    return;
                }
                $('#' + imgid).attr("src", result.data.path);
                $('#' + hiddenid).val(result.data.path);
            },
            error: function (xhr) {
                $(obj).unwrap();
                resetFileInput(file);
                xalert('上传失败!');
            }
        });
    }
    function resetFileInput(file) {
        file.after(file.clone().val(""));
        file.remove();
    }
</script>
</html>