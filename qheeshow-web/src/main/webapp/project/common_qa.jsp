<%@ page import="com.qheeshow.eway.service.model.CommonQa" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CommonQa> list = (List<CommonQa>) request.getAttribute("commonQas");
    Integer count = (Integer) request.getAttribute("count");
    Integer pageCount = (Integer) request.getAttribute("pageCount");
    Integer projectid = (Integer)request.getAttribute("projectid");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String appPath = Config.get("app.path");
%>
<div class="g-proj-lonetit4">提问互动<span><%=count%>条评论</span></div>
<div class="g-invest-tre">
    <div class="g-invest-treimg"></div>
    <form id="qForm">
        <input name="projectid" type="hidden" value="<%=projectid%>"/>
        <input name="parentid" type="hidden" value="0"/>

        <div class="g-invest-tre-r">
            <div class="g-proj-texwap"><textarea name="content" class="g-proj-textarea"></textarea>

                <div class="g-proj-texcru">最多输入<span>150</span>个字</div>
            </div>
            <div class="g-proj-texbtn"><a href="#" class="on2" onclick="q1112();">提问1</a></div>
            <script>
                function q1112(){
                    alert("d1111");
                }

            </script>
        </div>
    </form>
</div>
<%
    for (CommonQa commonQa : list) {
%>
<div class="g-invest-tre">
    <form id="aForm_<%=commonQa.getId()%>">
        <input type="hidden" name="projectid" value="<%=projectid%>"/>
        <input type="hidden" name="parentid" value="<%=commonQa.getId()%>"/>
        <input type="hidden" name="qUserid" value="<%=commonQa.getUserid()%>"/>
        <div class="g-invest-treimg"><img src="<%=commonQa.getPhoto()%>" width="60" height="60"/></div>
        <div class="g-invest-tre-r">
            <div class="g-invest-tre-rt"><span class="on1"><%=commonQa.getName()%></span><span
                    class="on2"><%=sdf.format(commonQa.getCreateTime())%></span></div>
            <%
                if (!StringUtils.isEmpty(commonQa.getQuestion())) {
            %>
            <div class="g-proj-subtitle">@<%=commonQa.getqName() + " " + commonQa.getQuestion()%>
            </div>
            <%
                }
            %>
            <div class="g-invest-tre-rcnt"><a><%=commonQa.getContent()%>
            </a></div>
            <div class="g-proj-reply"><a onclick="a();">回复</a></div>
            <div id="reply_<%=commonQa.getId()%>">
                <div class="g-proj-texwap">
                    <textarea name="content" class="g-proj-textarea"></textarea>

                    <div class="g-proj-texcru">最多输入<span>150</span>个字</div>
                </div>
                <div class="g-proj-texbtn"><a href="#" class="on1">取消</a><a href="#" class="on2">回复</a></div>
            </div>
        </div>
    </form>
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
        $("#qas").load("<%=appPath%>/qa/list/<%=projectid%>/" + index);
    }
</script>
