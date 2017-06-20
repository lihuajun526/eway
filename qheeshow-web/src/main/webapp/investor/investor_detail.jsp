<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Investor investor = (Investor) request.getAttribute("investor");
    Map<String, Integer> tags = (HashMap<String, Integer>) request.getAttribute("tags");
    String flag = "3";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--<%=investor.getTrueName()%>
    </title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/images/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div class="g-proj">

    <div class="g-invest-wwarp">

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
                    <li>
                        <a><%=tag%>(<%=tags.get(tag)%>)</a>
                        <span class="invest1-left-top"></span><span class="invest1-right-top"></span><span
                            class="invest1-right-bottom"></span><span class="invest1-left-bottom"></span>
                    </li>
                    <%
                        }
                    %>
                </ul>
                <a id="follow_" class="g-invest-focus"
                   onclick="follow(<%=investor.getUserid()%>,<%=investor.getId()%>)"></a>
                <ul class="g-invest-lst2">
                    <li><a onclick="listProject()">投递项目</a><span
                            class="invest1-left-top"></span><span
                            class="invest1-right-top"></span><span class="invest1-right-bottom"></span><span
                            class="invest1-left-bottom"></span></li>
                    <li><a onclick="bound(<%=investor.getUserid()%>)">查看联系方式</a><span
                            class="invest1-left-top"></span><span
                            class="invest1-right-top"></span><span class="invest1-right-bottom"></span><span
                            class="invest1-left-bottom"></span></li>
                </ul>
            </div>
        </div>

        <div id="pros_div" class="g-invest-bomb" style="display: none">
            <div class="g-invest-bomb-l"></div>
            <div class="g-invest-bomb-c">
                <ul id="pros" class="g-invest-bomb-clst"></ul>
                <div class="g-invest-bomb-cbtn"><a onclick="postProject()">确定投递</a></div>
            </div>
            <div class="g-invest-bomb-r"></div>
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
                            <li onclick="setTag(this);" data="人脉大咖"><a>人脉大咖</a><span
                                    class="invest2-left-top"></span><span class="invest2-right-top"></span><span
                                    class="invest2-right-bottom"></span><span class="invest2-left-bottom"></span></li>
                            <li onclick="setTag(this);" data="专业人士"><a>专业人士</a><span
                                    class="invest2-left-top"></span><span class="invest2-right-top"></span><span
                                    class="invest2-right-bottom"></span><span class="invest2-left-bottom"></span></li>
                            <li onclick="setTag(this);" data="金融专家"><a>金融专家</a><span
                                    class="invest2-left-top"></span><span class="invest2-right-top"></span><span
                                    class="invest2-right-bottom"></span><span class="invest2-left-bottom"></span></li>
                            <li onclick="setTag(this);" data="FA达人"><a>FA达人</a><span
                                    class="invest2-left-top"></span><span class="invest2-right-top"></span><span
                                    class="invest2-right-bottom"></span><span class="invest2-left-bottom"></span></li>
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

                            <div class="g-invest-twr-fb"><a onclick="saveComment();">发布评价</a></div>
                        </div>
                    </div>
                </form>
                <div id="coms"></div>
                <div class="g-invest-two2"></div>
            </div>
        </div>
        <!--*************************right star************************-->
        <div class="g-invest-r">
            <div class="g-invest-rone">
                <div class="g-invest-rt">常见问题</div>
                <ul class="g-invest-rlst2">
                    <li>
                        <span class="on1"></span>
                        <span>
                            <a href="<%=appPath%>/about/common_qa.jsp#2F">梧桐e路的优势是什么呢？</a>
                        </span>
                    </li>
                    <li>
                        <span class="on1"></span>
                        <span>
                            <a href="<%=appPath%>/about/common_qa.jsp#20F">梧桐e路是如何收费的？</a>
                        </span>
                    </li>
                    <li>
                        <span class="on1"></span>
                        <span>
                            <a href="<%=appPath%>/about/common_qa.jsp#7F">相对于企业自己寻找资金方，平台的优势在哪儿？</a>
                        </span>
                    </li>
                    <li>
                        <span class="on1"></span>
                        <span>
                            <a href="<%=appPath%>/about/common_qa.jsp#6F">企业融资周期要多久，是不是一定能获得融资？</a>
                        </span>
                    </li>
                </ul>
                <div class="g-invest-rmore"><a href="<%=appPath%>/about/common_qa.jsp">更多问题</a></div>
            </div>
        </div>
    </div>

</div>
<%@include file="../pub/foot.jsp" %>
</body>
<script>
    function setTag(obj) {
        if ($(obj).attr("class") == "on") {
            $(obj).remove("span");
            $(obj).append("<span class='invest2-left-top'></span><span class='invest2-right-top'></span><span class='invest2-right-bottom'></span><span class='invest2-left-bottom'></span>");
            $(obj).removeAttr("class");
        } else {
            $(obj).remove("span");
            $(obj).append("<span class='invest3-left-top'></span><span class='invest3-right-top'></span><span class='invest3-right-bottom'></span><span class='invest3-left-bottom'></span>");
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
            xalert("请选择标签");
            return;
        }
        if ($("#star").val() == "0") {
            xalert("请选择星级");
            return;
        }
        if (!$("#content").val().length) {
            xalert("请输入评论内容");
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
                    xalert(result.message);
                    return;
                }
                $("#content").val("");
                xalert("评论成功");
                listComs(1);
            }
        });
    }
    function follow(userid, investorid) {
        if ("已关注" == $("#follow_").html())
            return;
        $.get("<%=appPath%>/investor/follow/" + userid + "/" + investorid, function (result) {
            if (result.data) {
                $("#follow_").html("已关注");
            } else if (result.code == -1) {
                xalert1(result.message, "去登录", "<%=appPath%>/user/login.jsp");
            } else {
                xalert(result.message);
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
                if (result.code == -1) {
                    xalert1(result.message, "去登录", "<%=appPath%>/user/login.jsp");
                } else if (result.code == -3) {
                    xalert1(result.message, "立即购买", "<%=appPath%>/goods/list/0");
                } else
                    xalert(result.message);
                return;
            }
            xalert("<%=investor.getTrueName()%>的联系电话是：" + result.data + "，该电话号码10分钟内有效，请尽快联系对方");
        }, "json");
    }

    function listProject() {

        $.get("<%=appPath%>/project/list/mypros/authj", function (result) {
            if (result.code < 0) {
                if (result.code == -1)
                    xalert1(result.message, "去登录", "<%=appPath%>/user/login.jsp");
                else
                    xalert(result.message);
                return;
            }
            if (result.data.length == 0) {
                xalert1("您尚未创建项目，请先创建项目", "去创建", "<%=appPath%>/project/0/add/edit/1/auth");
                return;
            }
            for (var i = 0; i < result.data.length; i++) {
                var pro = result.data[i];
                $("#pros").append("<li><img src='" + pro.logo + "' width='82' height='82'/><span><i><input name='s_pro' type='radio' value='" + pro.id + "'></i>" + pro.title + "</span></li>");
            }
            $("input[name='s_pro']:first").attr('checked', 'checked');
            $("#pros_div").show();
        }, "json");
    }

    function postProject() {
        var id = $("input[name='s_pro']:checked").val();
        $.get("<%=appPath%>/investor/project/post/<%=investor.getUserid()%>/" + id + "/authj", function (result) {
            if (!result.data) {
                if (result.code = -1) {
                    xalert1(result.message, "去上传", "<%=appPath%>/project/" + id + "/add/edit/1/auth");
                } else
                    xalert(result.message);
                return;
            }
            $("#pros_div").hide();
            xalert("投递成功");
        }, "json");
    }

    function listComs(index) {
        $("#coms").load("<%=appPath%>/comment/list/<%=investor.getId()%>/" + index);
    }
    listComs(1);
</script>
</html>
