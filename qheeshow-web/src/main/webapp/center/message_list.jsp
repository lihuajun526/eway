<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Message" %>
<%
    List<Message> messages = (List<Message>) request.getAttribute("messages");
%>
<div class="g-mg-rwap">
    <ul class="g-mg-rtitlst">
        <li class="on"><a href="#">消息通知</a></li>
    </ul>
    <%
        for (Message message : messages) {
    %>
    <div class="g-mg-mesg">
        <div class="g-mg-rtit6"><a><%=message.getTitle()%>
        </a><span class="time"><%=message.getCreateTime()%></span></div>
        <div class="g-mg-rtit3"><a><%=message.getContent()%></a></div>
        <div class="g-mg-rtit4"><a href="#">删除</a></div>
    </div>
    <%
        }
    %>
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
</div>