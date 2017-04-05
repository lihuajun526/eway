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
                <div class="pro-lst2"><img id="photoImg_0" src="<%=appPath%>/images/bg-new1.png" onclick="selectFile(0);"
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
                    <li class="pro-c-name"><input name="memberName" class="pro-one-ipt1" placeholder="姓名"></li>
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
                    <li><textarea name="summary" class="pro-one-tex" placeholder="个人简介（不超过300字）"></textarea></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="pro-clear"></div>
    <div class="pro-btn1"><a onclick="saveTeam(this);">保 存</a></div>
</form>
<script>flag = false;</script>
