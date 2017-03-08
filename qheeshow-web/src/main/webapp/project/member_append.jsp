<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String projectid = request.getParameter("projectid");
%>
<form>
    <input name="id" type="hidden" value="0"/>
    <input name="projectid" type="hidden" value="<%=projectid%>"/>
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
