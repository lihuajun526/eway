<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="com.qheeshow.eway.service.model.GoodsItem" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    List<Project> projects = (List<Project>) request.getAttribute("projects");
    Integer projectid = (Integer) request.getAttribute("projectid");
    List<GoodsItem> goodsItems = (List<GoodsItem>) request.getAttribute("goodsItems");
    List<Investor> investors = (List<Investor>) request.getAttribute("investors");
%>
<div class="g-mg-rwap">
    <ul class="g-mg-rtitlst">
        <%
            for (int i = 0; i < 4 && i < projects.size(); i++) {
                Project project = projects.get(i);
                if (projectid.intValue() == project.getId().intValue()) {
        %>
        <li class="on"><a><%=project.getTitle()%>
        </a></li>
        <%
        } else {
        %>
        <li style="cursor: pointer" onclick="load('<%=appPath%>/center/myservices/<%=project.getId()%>');">
            <a><%=project.getTitle()%>
            </a></li>
        <%
                }
            }
        %>
    </ul>
    <%
        for (GoodsItem goodsItem : goodsItems) {
            if (goodsItem.getType().intValue() == 1) {
    %>
    <div class="g-mg-mesg1 g-mg-mesg2">
        <div class="g-mg-rtit6"><a><%=goodsItem.getTitle()%>
        </a><%--<span class="left-line"></span>--%></div>
        <div class="g-mg-rtit3"><a><%=goodsItem.getContent()%>
        </a></div>
        <div class="g-sev-one">
            <ul class="g-sev-lst">
                <%
                    for (int i = 0; i < 10 && i < investors.size(); i++) {
                        Investor investor = investors.get(i);
                %>
                <li id="img_<%=investor.getId()%>">
                    <a><img onclick="show(<%=investor.getId()%>);" src="<%=investor.getPhoto()%>" width="42"
                            height="42"/></a>
                    <!--弹出详细-->
                    <div class="g-sev-ico"></div>
                    <ul id="more_<%=investor.getId()%>" class="g-sev-ov" style="display: none;">
                        <li class="on1"><img src="<%=investor.getPhoto()%>" width="60" height="60"/></li>
                        <li class="on2"><span><%=investor.getTrueName()%></span><span class="g-sev-sline">|</span><span
                                class="g-sev-post"><%=investor.getCompanyName()%><%=investor.getCompanyRank()%></span>
                        </li>
                        <li class="on3"></li>
                        <li class="on4"><a href="<%=appPath%>/investor/<%=investor.getId()%>" target="_blank">查看详情</a>
                        </li>
                    </ul>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
    <%
    } else {
    %>
    <div class="g-mg-mesg1">
        <div class="g-mg-rtit6"><a><%=goodsItem.getTitle()%>
        </a><%--<span class="left-line"></span>--%></div>
        <div class="g-mg-rtit3"><a><%=goodsItem.getContent()%>
        </a></div>
        <%
            if (goodsItem.getType().intValue() == 4) {
        %>
        <div class="g-sev-one"><a class="g-sev-btn">预约排期</a></div>
        <%
            }
        %>
    </div>
    <%
            }
        }
    %>
</div>
<script>
    var moreid = 0;
    function show(id) {
        $(".g-sev-ov").each(function () {
            $(this).hide();
        });
        $("#more_" + id).show();
        moreid = id;
    }
    /*function hid(event) {
        if (moreid == 0)
            return;
        var div = document.getElementById("more_" + moreid);
        var x = $(event).offset().left;
        var y = $(event).offset().top;
        var divx1 = div.offsetLeft;
        var divy1 = div.offsetTop;
        var divx2 = div.offsetLeft + div.offsetWidth;
        var divy2 = div.offsetTop + div.offsetHeight;

        debugger;

        var img = document.getElementById("img_" + moreid);
        var imgx1 = $('#img_'+moreid).offset().left;
        var imgy1 = $('#img_'+moreid).offset().top;

        var imgx2 = imgx1 + img.offsetWidth;
        var imgy2 = imgy1 + img.offsetHeight;

        alert((x < divx1 || x > divx2 || y < divy1 || y > divy2));
        alert(x < imgx1 || x > imgx2 || y < imgy1 || y > imgy2);

        if ((x < divx1 || x > divx2 || y < divy1 || y > divy2) && (x < imgx1 || x > imgx2 || y < imgy1 || y > imgy2)) {
            $("#more_" + moreid).hide();
            moreid = 0;
        }
    }*/
</script>
