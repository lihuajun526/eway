<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.service.model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<Comment> comments = (List<Comment>) request.getAttribute("comments");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<%
    for (Comment comment : comments) {
%>
<div class="wtwx-project-cnt3">
    <div class="wtwx-project-cnt3-l"><img src="<%=comment.getPhoto()%>" width="36" height="36"/></div>
    <div class="wtwx-project-cnt3-r">
        <h1><%=comment.getName()%>
        </h1>

        <div class="wtwx-project-cnt3-rcnt"><%=comment.getContent()%>
        </div>
        <div class="wtwx-project-cnt3-rtime"><%=sdf.format(comment.getCreateTime())%>
        </div>
    </div>
</div>
<%
    }
%>
