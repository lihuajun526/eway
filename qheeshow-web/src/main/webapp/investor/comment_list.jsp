<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.service.model.Comment" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLDecoder" %>
<%
    List<Comment> comments = (List<Comment>) request.getAttribute("comments");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Integer recordCount = (Integer) request.getAttribute("recordCount");
    if (comments.size() == 0) {
        return;
    }
%>
<div class="g-invest-two2"><%=recordCount %>条评价</div>
<%
    for (Comment comment : comments) {
%>
<div class="g-invest-tre">
    <div class="g-invest-treimg"><img src="<%=comment.getPhoto()%>" width="60" height="60"/></div>
    <div class="g-invest-tre-r">
        <div class="g-invest-tre-rt"><span class="on1"><%=URLDecoder.decode(comment.getName(),"utf-8")%></span><span
                class="on2"><%=sdf.format(comment.getCreateTime())%></span>
        </div>
        <div class="g-invest-tre-rcnt"><a><%=comment.getContent()%>
        </a></div>
    </div>
</div>
<%
    }
%>
<div class="clear"></div>
<div class="i-Page">
    <%
        int len = 6;
        int index = (Integer) request.getAttribute("pageIndex");
        int start = index < len ? 1 : index - (len - 2);
        int pageCount = (Integer) request.getAttribute("pageCount");
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
        listComs(index);
    }
</script>