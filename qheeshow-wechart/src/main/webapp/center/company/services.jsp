<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.GoodsItem" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%
    String appPath = Config.get("app.path");
    List<GoodsItem> goodsItems = (List<GoodsItem>) request.getAttribute("goodsItems");
    List<Investor> investors = (List<Investor>) request.getAttribute("investors");
%>
<%
    if (goodsItems == null || goodsItems.size() == 0) {
        %><div class="wx-callmmone"><img src="<%=appPath%>/images/wtwx-icon54.png" width="212" height="152"/><span>您未购买任何服务</span>
</div><%
    } else {
        for (GoodsItem goodsItem : goodsItems) {
            %>
<div class="wtwx-project-cnt4">
    <div class="wtwx-activity-tit3"><%=goodsItem.getTitle()%></div>
    <div class="wtwx-project-cnt1-conter"><%=goodsItem.getContent()%></div>
</div>
            <%
            if(goodsItem.getId().intValue()==1){
                if(investors==null|| investors.size()==0){
                    %><div class="wx-call-t2"><span>请耐心等待，平台会在24小时之内为您推荐优质的投资人</span></div><%
                }else{
                    %>
<div class="wx-call-t2"><span>以下是平台为你的项目推荐的投资人</span></div>
                    <%
                        for(Investor investor:investors){
                            %>
<div class="wtwx-investors-cnt1">
    <div class="wtwx-investors-cnt1-l"><img src="<%=investor.getPhoto()%>" width="48" height="48"/></div>
    <a href="#">
        <div class="wtwx-investors-cnt1-r">
            <h1><%=investor.getTrueName()%></h1>
            <h4><%=investor.getCompanyName()%>／<%=investor.getCompanyRank()%></h4>
        </div>
    </a>
    <span class="wtwx-investors-cnt1-on2">LV 4</span>
</div>
                            <%
                        }
                }
            }
        }
    }
%>



