<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Investor investor = (Investor) request.getAttribute("investor");
    Map<String, Integer> tags = (HashMap<String, Integer>) request.getAttribute("tags");
%>
<html>
<head>
    <title><%=Config.get("app.name")%>--<%=investor.getTrueName()%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp?flag=3" flush="true"/>
<div class="g-proj">
    <div class="g-invest">
        <div class="g-invest-img"><img src="<%=investor.getPhoto()%>" width="180" height="180"/></div>
        <div class="g-invest-tr">
            <div class="g-invest-name"><%=investor.getTrueName()%>
            </div>
            <div class="g-invest-t"><%=investor.getCompanyName()%>-<%=investor.getCompanyRank()%>
            </div>
            <div class="g-invest-onect">
                <div class="on1">个人简介：</div>
                <div class="on2"><%=investor.getSummary()%>
                </div>
            </div>
            <ul class="g-invest-lst">
                <%
                    for (String tag : tags.keySet()) {
                %>
                <li><a><%=tag%>(<%=tags.get(tag)%>)</a></li>
                <%
                    }
                %>
            </ul>
            <a id="follow_" class="g-invest-focus"
               onclick="follow(<%=investor.getUserid()%>,<%=investor.getId()%>)"></a>
            <ul class="g-invest-lst2">
                <li><a href="#">投递项目</a></li>
                <li><a onclick="bound(<%=investor.getUserid()%>)">查看联系方式</a></li>
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
                    <input type="hidden" id="investorid" name="investorid" value="<%=investor.getId()%>"/>

                    <div class="g-invest-two3">
                        <div class="g-invest-lonetit">评价 <span>/Profile</span></div>
                        <ul id="tags_" class="g-invest-lst3">
                            <li onclick="setTag(this);" data="人脉大咖"><a>人脉大咖</a></li>
                            <li onclick="setTag(this);" data="专业人士"><a>专业人士</a></li>
                            <li onclick="setTag(this);" data="金融专家"><a>金融专家</a></li>
                            <li onclick="setTag(this);" data="FA达人"><a>FA达人</a></li>
                        </ul>
                    </div>
                    <div class="g-invest-two">
                        <div class="g-invest-twl">星级评价：</div>
                        <div class="g-invest-twr">
                            <ul>
                                <li id="li1" onclick="setStar(1);"><img src="<%=appPath%>/images/star-m.png"/></li>
                                <li id="li2" onclick="setStar(2);"><img src="<%=appPath%>/images/star-m.png"/></li>
                                <li id="li3" onclick="setStar(3);"><img src="<%=appPath%>/images/star-m.png"/></li>
                                <li id="li4" onclick="setStar(4);"><img src="<%=appPath%>/images/star-m.png"/></li>
                                <li id="li5" onclick="setStar(5);"><img src="<%=appPath%>/images/star-m.png"/></li>
                            </ul>
                        </div>
                    </div>
                    <div class="g-invest-two1">
                        <div class="g-invest-twl">评论内容：</div>
                        <div class="g-invest-twr">
                            <textarea id="content" name="content" class="g-invest-twrtex"
                                      placeholder=" 请输入评论内容"></textarea>

                            <div class="g-invest-twr-fb"><a onclick="saveComment();">发表评论</a></div>
                        </div>
                    </div>
                </form>
                <div class="g-invest-two2"></div>
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
    function setTag(obj) {
        if ($(obj).attr("class") == "on") {
            $(obj).removeAttr("class");
        } else {
            $(obj).attr("class", "on");
        }
    }
    function setStar(v) {
        for (var i = 1; i <= 5; i++) {
            if (i <= v)
                $("#li" + i).html("<img src='<%=appPath%>/images/star-n.png'/>");
            else
                $("#li" + i).html("<img src='<%=appPath%>/images/star-m.png'/>");
        }
        $("#star").val(v);
    }
    function saveComment() {
        var tags = "";
        $("#tags_").children('li').each(function () {
            if ($(this).attr("class") == "on")
                tags += $(this).attr("data") + "#";
        });
        $("#tags").val(tags);
        if (!tags.length) {
            alert("请选择标签");
            return;
        }
        if ($("#star").val() == "0") {
            alert("请选择星级");
            return;
        }
        if (!$("#content").val().length) {
            alert("请输入评论内容");
            return;
        }
        $.ajax({
            type: 'POST',
            url: '<%=appPath%>/comment/save/authj',
            cache: false,
            processData: false,
            data: $('#commentForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    alert(result.message);
                    return;
                }
                $("#content").val("");
                alert("评论成功");
            }
        });
    }
    function follow(userid, investorid) {
        if ("已关注" == $("#follow_").html())
            return;
        $.get("<%=appPath%>/investor/follow/" + userid + "/" + investorid, function (result) {
            if (result.data) {
                $("#follow_").html("已关注");
            } else {
                alert(result.message);
            }
        }, "json");
    }
    //是否已关注
    $.get("<%=appPath%>/investor/isfollow/<%=investor.getId()%>", function (result) {
        if (result.data) {
            $("#follow_").html("已关注");
        } else {
            $("#follow_").html("+关注");
        }
    }, "json");

    function bound(userid) {
        $.get("<%=appPath%>/mixcom/bound/" + userid + "/authj", function (result) {
            if (result.code < 0) {
                alert(result.message);
                return;
            }
            alert("<%=investor.getTrueName()%>的联系电话是：" + result.data + "，该电话10分钟内有效");
        }, "json");
    }
</script>
</html>
