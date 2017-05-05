<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    String projectid = request.getParameter("projectid");
%>
<form>
    <input name="id" type="hidden" value="0"/>
    <input name="projectid" type="hidden" value="<%=projectid%>"/>
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
                        <textarea id="summary_0" name="summary" class="pro-one-tex" placeholder="个人简介（不超过300字）"></textarea>
                        <span class="pro6-left-top"></span><span class="pro6-right-top"></span><span
                            class="pro6-right-bottom"></span><span class="pro6-left-bottom"></span>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="pro-clear"></div>
    <div class="pro-btn1"><a onclick="saveTeam(this,0);">保 存</a></div>
</form>
<script>
    flag = false;
    $(function() {
        $('input, textarea').placeholder();
    });
</script>
