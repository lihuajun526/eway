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
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--创建项目</title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/project.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script src="<%=appPath%>/jquery/ajaxfileupload.js"></script>
    <script>
        var flag = true;
    </script>
</head>
<body>
<jsp:include page="../pub/head.jsp?flag=2" flush="true"/>
<div class="pro-body">
    <input type="file" id="photoFile" name="photoFile" style="display: none;" onchange="uploadImage()"/>

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
                        <div class="pro-lst2"><img id="photoImg_0" src="<%=appPath%>/images/bg-new1.png"
                                                   onclick="selectFile(0);"
                                                   title="点击上传照片" width="130" height="130"/></div>
                    </li>
                    <li class="on3">支持png/jpg/jepg格式</li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人信息</li>
                    <li class="on2">
                        <ul>
                            <li class="pro-c-name"><input name="memberName" class="pro-one-ipt1"
                                                          placeholder="姓名"></li>
                            <li><input name="position" class="pro-one-ipt1" placeholder="职位"></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="pro-one">
                <ul class="pro-one-lst">
                    <li class="on1">个人简介</li>
                    <li class="on2">
                        <ul class="pro-six-lst">
                            <li><textarea name="summary" class="pro-one-tex" placeholder="个人简介（不超过300字）"></textarea>
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
            <div class="pro-btn1"><a onclick="saveTeam(this);">保 存</a></div>
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
                                 onclick="selectFile(<%=member.getId()%>);" title="点击上传照片" width="130" height="130"/>
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
                            <li class="pro-c-name"><input name="memberName" class="pro-one-ipt1"
                                                          value="<%=member.getMemberName()%>"></li>
                            <li><input name="position" class="pro-one-ipt1" value="<%=member.getPosition()%>">
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
                            <li><textarea name="summary" class="pro-one-tex"><%=member.getSummary()%></textarea></li>
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
            <div class="pro-btn1"><a onclick="saveTeam(this);">保 存</a></div>
        </form>
        <%
                }
            }
        %>
        <div id="members"></div>
        <div class="group"><a onclick="addMember();">添加核心成员</a></div>
        <!--*************************下一步按钮************************-->
        <div class="pro-clear"></div>
        <div class="pro-btn"><a href="<%=appPath%>/project/<%=project.getId() %>/add/edit/1/auth">上一步</a><a
                href="<%=appPath%>/project/<%=project.getId() %>/add/edit/3/auth">下一步</a></div>
    </div>
</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    //保存基本信息
    function saveTeam(obj) {
        $.ajax({
            type: 'POST',
            url: '<%=appPath%>/project/team/save/authj',
            cache: false,
            processData: false,
            data: $(obj).parent().parent().serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                alert("保存成功");
                window.location.reload();
            }
        });
    }
    function addMember() {
        if (!flag) {
            alert("请先保存团队信息");
            return;
        }
        $("#members").load("<%=appPath%>/project/member_append.jsp?projectid=<%=project.getId()%>");
    }
    var imgid = null;
    var hiddenid = null;
    //上传photo
    function uploadImage() {
        var file = $('#photoFile');
        if (!file || !file.val())
            return;
        var patn = /\.jpg$|\.jpeg$|\.png$|\.gif$/i;
        if (!patn.test(file.val())) {
            alert("请选择图片文件");
            return;
        }
        $.ajaxFileUpload({
                    url: '<%=appPath%>/image/upload', //用于文件上传的服务器端请求地址
                    type: 'post',
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'photoFile', //文件上传域的ID
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
    function selectFile(id) {
        imgid = "photoImg_" + id;
        hiddenid = "photo_" + id;
        $('#photoFile').click();
    }
</script>
</html>