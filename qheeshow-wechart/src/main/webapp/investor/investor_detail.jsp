<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%
    String appPath = Config.get("app.path");
    Investor investor = (Investor) request.getAttribute("investor");
    Map<String, Integer> tags = (HashMap<String, Integer>) request.getAttribute("tags");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title><%=Config.get("app.name")%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wtweixin.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/dropload.css"/>
</head>
<body class="wtxt-pb wtwx-warp">
<%@include file="../pub/head.jsp" %>
<div id="pros_" style="display: none;">
    <div class="shade"></div>
    <div class="wtwx-project2-radius">
        <a onclick="javascript:$('#pros_').hide()" class="wtwx-project-radius-close2"></a>

        <h1>选择要投递的项目</h1>

        <div id="pros" class="wtwx-project2-lst2a"></div>
        <div class="wtwx-project2-btn3"><a onclick="postPro()">确定</a></div>
    </div>
</div>


<div class="wtwx-project-cnt1">
    <div class="wtwx-project-cnt1-l"><img src="<%=investor.getPhoto()%>" width="72" height="72"/></div>
    <div class="wtwx-project-cnt1-r">
        <span class="wtwx-project-fans">粉丝：10人</span>

        <h1><%=investor.getTrueName()%>
        </h1>

        <div class="wtwx-project-cnt1-rnum"><%=investor.getCompanyName()%>-<%=investor.getCompanyRank()%>
        </div>
    </div>
    <div class="wtwx-project-cnt1-rcnt"><%=investor.getSummary()%>
    </div>
    <ul class="wtwx-project-cnt1-lst4">
        <%
            for (String tag : tags.keySet()) {
        %>
        <li><a><%=tag%>(<%=tags.get(tag)%>)</a></li>
        <%
            }
        %>
    </ul>
</div>
<div class="wtwx-project-cnt2">
    <div class="wtwx-activity-tit2">个人介绍</div>
    <div class="wtwx-project-cnt1-conter"><%=investor.getPersonalProfile()%>
    </div>
</div>
<div class="wtwx-project-cnt2">
    <div class="wtwx-activity-tit2">评价</div>
    <form id="commentForm">
        <input type="hidden" name="investorid" value="<%=investor.getId()%>"/>
        <input type="hidden" id="star" name="star" value="0"/>
        <input type="hidden" id="tags" name="tags" value=""/>
        <ul id="tags_" class="wtwx-project-cnt2-lst4">
            <li onclick="setTag(this)" data="专业人士"><a>专业人士</a></li>
            <li onclick="setTag(this)" data="人脉大咖"><a>人脉大咖</a></li>
            <li onclick="setTag(this)" data="金融专家"><a>金融专家</a></li>
            <li onclick="setTag(this)" data="FA达人"><a>FA达人</a></li>
        </ul>
        <div class="wtwx-project-cnt2-lst5">
            <div class="wtwx-project-cnt2-lst5-l">星级评价：</div>
            <div id="stars" class="wtwx-project-cnt2-lst5-r">
                <a onclick="setStar(1)"><img src="<%=appPath%>/images/wtwx-icon16.png" width="19" height="18"/></a>
                <a onclick="setStar(2)"><img src="<%=appPath%>/images/wtwx-icon16.png" width="19" height="18"/></a>
                <a onclick="setStar(3)"><img src="<%=appPath%>/images/wtwx-icon16.png" width="19" height="18"/></a>
                <a onclick="setStar(4)"><img src="<%=appPath%>/images/wtwx-icon16.png" width="19" height="18"/></a>
                <a onclick="setStar(5)"><img src="<%=appPath%>/images/wtwx-icon16.png" width="19" height="18"/></a>
            </div>
        </div>
        <div class="wtwx-project-cnt2-lst5">
            <div class="wtwx-project-cnt2-lst5-la">描述评论：</div>
            <div class="wtwx-project-cnt2-lst5-r">
                <div class="wtwx-project-texta">
                    <textarea id="content" name="content" class="wtwx-project-texta1" placeholder="填写评价"></textarea>
                </div>
                <div class="wtwx-project-btn2"><a onclick="saveComment()" href="#">发表评价</a></div>
            </div>
        </div>
    </form>
    <div id="coms"></div>
</div>
<ul class="wtwx-menu3">
    <a href="#" onclick="follow()">
        <li><img src="<%=appPath%>/images/wtwx-project-meun4.png" width="25" height="25"/><span id="follow_">关注</span>
        </li>
    </a>
    <a href="#" onclick="listPro()">
        <li><img src="<%=appPath%>/images/wtwx-project-meun5.png" width="25" height="25"/><span>投递项目</span></li>
    </a>
    <a href="#" onclick="bound()">
        <li class="on"><img src="<%=appPath%>/images/wtwx-project-meun6.png" width="25" height="25"/><span>获取联系电话</span>
        </li>
    </a>
</ul>
<script type="text/javascript" src="<%=appPath%>/zepto/zepto.js"></script>
<script>
    var isFollowed = false;
    function setTag(obj) {
        if ($(obj).attr("class") == "on") {
            $(obj).removeAttr("class");
        } else {
            $(obj).attr("class", "on");
        }
    }
    function setStar(v) {
        var imgs = $("#stars>a>img");
        for (var i = 1; i <= imgs.length; i++) {
            if (i <= v)
                $(imgs[i - 1]).attr("src", "<%=appPath%>/images/wtwx-icon17.png");
            else
                $(imgs[i - 1]).attr("src", "<%=appPath%>/images/wtwx-icon16.png");
        }
        $("#star").val(v);
    }
    function follow() {
        if (isFollowed)
            return;
        $.get("<%=appPath%>/investor/do/follow/<%=investor.getId()%>/v_auth", function (result) {
            openTip(result);
            if (result.code >= 0) {
                $("#follow_").html("已关注");
            }
        }, "json");
    }
    //是否已关注
    $.get("<%=appPath%>/investor/do/isfollow/<%=investor.getId()%>", function (result) {
        if (result.data) {
            $("#follow_").html("已关注");
            isFollowed = true;
        } else {
            $("#follow_").html("关注");
            isFollowed = false;
        }
    }, "json");
    //是否可以投递项目
    function isAblePost() {
        $.get("<%=appPath%>/investor/do/isable/post/v_auth", function (result) {
            openTip(result);
            if (result.code >= 0) {
                $("#pros").show();
            }
        }, "json");
    }
    function bound() {
        $.getJSON("<%=appPath%>/mixcom/do/bound/<%=investor.getUserid()%>/v_authj", function (result) {
            openTip(result);
        }, "json");
    }
    var postProid = 0;
    function listPro() {
        $.get("<%=appPath%>/project/do/list/mypros/v_authj", function (result) {
            if (result.code < 0) {
                openTip(result);
            } else {
                $("#pros").empty();
                for (var i = 0; i < result.data.data.length; i++) {
                    var pro = result.data.data[i];
                    if (i == 0) {
                        postProid = pro.id;
                        $("#pros").append("<a id='li_" + pro.id + "' onclick='selectPro(" + pro.id + ")'><span class='shade2'></span><span class='select'><img src='<%=appPath%>/images/wtwx-icon18.png' width='30' height='30'/></span><img src='" + pro.logo + "' width='68' height='68'/><span class='gfont'>" + pro.title + "</span></a>");
                    } else {
                        $("#pros").append("<a id='li_" + pro.id + "' onclick='selectPro(" + pro.id + ")'><img src='" + pro.logo + "' width='68' height='68'/><span class='gfont'>" + pro.title + "</span></a>");
                    }
                }
                $("#pros_").show();
            }
        }, "json");
    }
    function selectPro(id) {
        if (postProid == 0)
            return;
        var count = 0;
        $("#li_" + postProid).children().each(function () {
            if (count < 2) {
                $(this).remove();
                count++;
            } else
                return;
        });
        postProid = id;
        $("#li_" + postProid).before("<span class='shade2'></span><span class='select'><img src='<%=appPath%>/images/wtwx-icon18.png' width='30' height='30'/></span>");
    }
    function postPro() {
        $.get("<%=appPath%>/investor/do/project/post/<%=investor.getUserid()%>/" + postProid + "/v_authj", function (result) {
            $("#pros_").hide();
            openTip(result);
        }, "json");
    }
    function listComs() {
        $("#coms").load("<%=appPath%>/investor/do/comment/list/<%=investor.getId()%>");
    }
    listComs();
    function saveComment() {
        var tags = "";
        $("#tags_").children('li').each(function () {
            if ($(this).attr("class") == "on")
                tags += $(this).attr("data") + "#";
        });
        $("#tags").val(tags);
        if (!tags.length) {
            openTip({'message': '对不起，请选择评价标签', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        if ($("#star").val() == "0") {
            openTip({'message': '对不起，请选择星级', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        if (!$("#content").val().length) {
            openTip({'message': '对不起，请输入评论内容', 'data': {'link': 'close', 'action': '知道了'}});
            return;
        }
        $.post("<%=appPath%>/investor/do/comment/save/v_authj", $('#commentForm').serialize(), function (result) {
            $("#content").val("");
            listComs();
            openTip(result);
        }, "json");
    }
</script>
</body>
</html>
