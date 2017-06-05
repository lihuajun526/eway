<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.service.model.ProjectQa" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<ProjectQa> qs = (List<ProjectQa>) request.getAttribute("qs");
    Map<Integer, ProjectQa> aMap = (Map<Integer, ProjectQa>) request.getAttribute("aMap");
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
%>
<%
    for (ProjectQa q : qs) {
%>
<div class="wtwx-project-cnt3">
    <div class="wtwx-project-cnt3-l"><img src="<%=q.getPhoto()%>" width="36" height="36"/></div>
    <div class="wtwx-project-cnt3-r">
        <h1><%=q.getName()%>
        </h1>

        <div class="wtwx-project-cnt3-rcnt">
            <%=q.getContent()%>
            <%
                if (aMap.get(q.getId()) == null) {
            %>
            <div class="wtwx-project-cancel"><a href="#<%=q.getId()%>F"
                                                onclick="openOrClose(<%=q.getId()%>,this)">回复</a></div>
            <%
                }
            %>
        </div>
        <%
            if (aMap.get(q.getId()) == null) {
        %>
        <div id="<%=q.getId()%>F" class="wtwx-project-cnt3-warp" style="display: none">
            <input id="content<%=q.getId()%>" class="ipt" placeholder="请输入回复"/>
            <a onclick="a(<%=q.getId()%>)" class="btn">确定</a>
        </div>
        <%
        } else {
            ProjectQa a = aMap.get(q.getId());
        %>
        <div class="wtwx-project-cnt3-rcnt2">
            <span><%=a.getName()%>：</span><%=a.getContent()%>
        </div>
        <%
            }
        %>
    </div>
    <a class="wtwx-project-cnt3-praise"><%=sdf.format(q.getCreateTime())%>
    </a>
</div>
<%
    }
%>
<script>
    function openOrClose(id, obj) {
        if ($(obj).html() == "回复") {
            $("#content" + id).parent().show();
            $(obj).html("取消");
        } else if ($(obj).html() == "取消") {
            $("#content" + id).parent().hide();
            $(obj).html("回复");
        }
    }
</script>