<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.ProjectQa" %>
<%@ page import="com.qheeshow.eway.service.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ProjectQa> projectQs = (List<ProjectQa>) request.getAttribute("projectQs");
    Integer count = (Integer) request.getAttribute("count");
    Integer pageCount = (Integer) request.getAttribute("pageCount");
    Integer projectid = (Integer) request.getAttribute("projectid");
    Integer userid = (Integer) request.getAttribute("userid");
    Map<Integer, ProjectQa> aMap = (Map<Integer, ProjectQa>)request.getAttribute("aMap");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String appPath = Config.get("app.path");
    User loginUser = session.getAttribute("loginUser") == null ? null : (User) session.getAttribute("loginUser");
%>
<div class="g-proj-lonetit4">提问互动<span><%=count%>条评论</span></div>
<div class="g-invest-tre">
    <div class="g-invest-treimg"></div>
    <form id="qForm">
        <input name="projectid" type="hidden" value="<%=projectid%>"/>
        <input name="parentid" type="hidden" value="0"/>

        <div class="g-invest-tre-r">
            <div class="g-proj-texwap"><textarea id="content" name="content" class="g-proj-textarea"></textarea>

                <div class="g-proj-texcru">最多输入<span>150</span>个字</div>
            </div>
            <div class="g-proj-texbtn"><a class="on2" onclick="q();">提问</a></div>
        </div>
    </form>
</div>
<%
    for (ProjectQa q : projectQs) {
        ProjectQa a = aMap.get(q.getId());
%>
<div class="g-invest-tre">
    <%
        if(a!=null){//已答复
    %>
    <div class="g-invest-tre">
        <div class="g-invest-treimg"><img src="<%=a.getPhoto()%>" width="60" height="60"/></div>
        <div class="g-invest-tre-r">
            <div class="g-invest-tre-rt"><span class="on1"><%=a.getName()%></span><span class="on2"><%=sdf.format(a.getCreateTime())%></span></div>
            <div class="g-proj-subtitle">@<%=q.getName()%>   <%=q.getContent()%></div>
            <div class="g-invest-tre-rcnt"><a><%=a.getContent()%></a></div>
        </div>
    </div>
    <%
        }else{//未答复
    %>
    <form id="aForm_<%=q.getId()%>">
        <input type="hidden" name="projectid" value="<%=projectid%>"/>
        <input type="hidden" name="parentid" value="<%=q.getId()%>"/>
        <input type="hidden" name="qUserid" value="<%=q.getUserid()%>"/>
        <div class="g-invest-tre">
            <div class="g-invest-treimg"><img src="<%=q.getPhoto()%>" width="60" height="60"/></div>
            <div class="g-invest-tre-r">
                <div class="g-invest-tre-rt"><span class="on1"><%=q.getName()%></span><span class="on2"><%=sdf.format(q.getCreateTime())%></span></div>
                <div class="g-invest-tre-rcnt"><a><%=q.getContent()%></a></div>
                <%
                    if (loginUser != null && loginUser.getId().intValue() == userid.intValue()) {
                %>
                <div class="g-proj-reply"><a onclick="openA(<%=q.getId()%>);">回复</a></div>
                <%
                    }
                %>
                <div style="display: none;" id="reply_<%=q.getId()%>">
                    <div class="g-proj-texwap">
                        <textarea id="content_<%=q.getId()%>" name="content" class="g-proj-textarea"></textarea>
                        <div class="g-proj-texcru">最多输入<span>150</span>个字</div>
                    </div>
                    <div class="g-proj-texbtn"><a onclick="closeA(<%=q.getId()%>);" class="on1">取消</a><a class="on2" onclick="a(<%=q.getId()%>)">回复</a>
                    </div>
                </div>

            </div>
        </div>
    </form>
    <%
        }
    %>
</div>
<%
    }
%>
<div class="i-Page">
    <%
        int len = 6;
        int index = (Integer) request.getAttribute("pageIndex");
        int start = index < len ? 1 : index - (len - 2);
    %>
    <a onclick="goto(<%=index-1%>)" class="on1">&nbsp;</a>
    <%

        for (int i = 0; i < len; i++, start++) {
            if (start == pageCount) {
    %><a onclick="goto(<%=start%>)" <%=start == index ? "class='on'" : "" %>><%=start%>
</a><%
        break;
    }
    if ((i + 1) == len) {
%><a onclick="goto(<%=start%>)">...</a><%
} else {
%><a onclick="goto(<%=start%>)" <%=start == index ? "class='on'" : "" %>><%=start%>
</a><%
        }
    }
%>
    <a onclick="goto(<%=index+1%>)" class="on2">&nbsp;</a>
</div>
<script>
    function goto(index) {
        if (index <= 0 || index ><%=pageCount%>)
            return;
        $("#qas").load("<%=appPath%>/project/qa/list/<%=projectid%>/" + index);
    }
</script>
