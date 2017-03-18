<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Investor investor = (Investor) request.getAttribute("investor");


%>
<html>
<head>
    <title><%=Config.get("app.name")%>--<%=investor.getTrueName()%>
    </title>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <script type="text/javascript" src="/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="g-proj">
    <div class="g-invest">
        <div class="g-invest-img"><img src="<%=investor.getPhoto()%>" width="180" height="180"/></div>
        <div class="g-invest-tr">
            <div class="g-invest-name"><%=investor.getTrueName()%>
            </div>
            <div class="g-invest-t"><%=investor.getCompanyName()%>-<%=investor.getCompanyRank()%>
            </div>
            <div class="g-invest-onect">
                <div class="on1">个人介绍：</div>
                <div class="on2"><%=investor.getSummary()%>
                </div>
            </div>
            <ul class="g-invest-lst">
                <%
                    if (!StringUtils.isEmpty(investor.getTags())) {
                        String[] tags = investor.getTags().split("#");
                        for (String tag : tags) {
                            String[] strs = tag.split(":");
                %>
                <li><a><%=strs[0]%>(<%=strs[1]%>)</a></li>
                <%
                        }
                    }
                %>
            </ul>
            <a href="#" class="g-invest-focus">+关注</a>
            <ul class="g-invest-lst2">
                <li><a href="#">投递项目</a></li>
                <li><a href="#">查看联系方式</a></li>
            </ul>
        </div>
    </div>
    <div class="g-conter">
        <div class="g-invest-l">
            <div class="g-invest-lone">
                <div class="g-invest-lonetit">个人介绍 <span>/Evaluate</span></div>
                <div class="g-invest-lonect"><%=investor.getPersonalProfile()%>
                </div>
            </div>
            <div class="g-invest-lone2">
                <form id="commentForm">
                    <input type="hidden" id="tags" name="tags" value=""/>
                    <input type="hidden" id="star" name="star" value="0"/>
                    <div class="g-invest-two3">
                        <div class="g-invest-lonetit">评价 <span>/Profile</span></div>
                        <ul id="tags_" class="g-invest-lst3">
                            <li onclick="setTag(this);">人脉大咖</li>
                            <li onclick="setTag(this);">专业人士</li>
                            <li onclick="setTag(this);">金融专家</li>
                            <li onclick="setTag(this);">FA达人</li>
                        </ul>
                    </div>
                    <div class="g-invest-two">
                        <div class="g-invest-twl">星级评价：</div>
                        <div class="g-invest-twr">
                            <ul>
                                <li id="li1" onclick="setStar(1);"><img src="images/star-m.png"/></li>
                                <li id="li2" onclick="setStar(2);"><img src="images/star-m.png"/></li>
                                <li id="li3" onclick="setStar(3);"><img src="images/star-m.png"/></li>
                                <li id="li4" onclick="setStar(4);"><img src="images/star-m.png"/></li>
                                <li id="li5" onclick="setStar(5);"><img src="images/star-m.png"/></li>
                            </ul>
                        </div>
                    </div>
                    <div class="g-invest-two1">
                        <div class="g-invest-twl">评论：</div>
                        <div class="g-invest-twr">
                            <textarea name="content" class="g-invest-twrtex"></textarea>
                            <div class="g-invest-twr-fb"><a onclick="saveComment();">发表评论</a></div>
                        </div>
                    </div>
                </form>
                <%--<div class="g-invest-two2">20条评论</div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-invest-tre-rcnt"><a href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a>
                        </div>
                    </div>
                </div>--%>
            </div>
        </div>
        <!--*************************right star************************-->
        <div class="g-invest-r">
            <div class="g-invest-rone">
                <div class="g-invest-rt">参投项目</div>
                <ul class="g-invest-rlst">
                    <li><span class="on1">1</span><span class="on2">金融网</span><span class="on3"><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></span>
                    </li>
                    <li><span class="on1">2</span><span class="on2">金融网</span><span class="on3"><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></span>
                    </li>
                    <li><span class="on1">3</span><span class="on2">金融网</span><span class="on3"><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></span>
                    </li>
                    <li><span class="on1">4</span><span class="on2">金融网</span><span class="on3"><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></span>
                    </li>
                </ul>
                <div class="g-invest-rmore"><a href="#">更多项目</a></div>
            </div>
        </div>
    </div>

</div>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script>
    function setTag() {
        if ($(this).attr("class") == "on") {
            $(this).removeAttr("class");
        } else {
            $(this).attr("class", "on");
        }
    }
    function setStar(v) {
        for (var i = 1; i <= 5; i++) {
            if (i <= v)
                $("#li" + i).html("<img src='images/star-n.png'/>");
            else
                $("#li" + i).html("<img src='images/star-m.png'/>");
        }
        $("#star").val(v);
    }
    function saveComment() {
        $.ajax({
            type: 'POST',
            url: '/comment/save',
            cache: false,
            processData: false,
            data: $('#commentForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                alert("评论成功");
            }
        });
    }
</script>
</html>
