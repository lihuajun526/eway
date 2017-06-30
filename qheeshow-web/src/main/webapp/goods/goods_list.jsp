<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page import="java.util.List" %>
<%
    String appPath = Config.get("app.path");
    List<Project> projects = (List<Project>) request.getAttribute("projects");
    String buyBtncls1 = (String) request.getAttribute("buyBtncls1");
    String buyBtncls2 = (String) request.getAttribute("buyBtncls2");
    String buyBtncls3 = (String) request.getAttribute("buyBtncls3");
    String buyBtncls4 = (String) request.getAttribute("buyBtncls4");
    Integer projectid = (Integer) request.getAttribute("projectid");
    String flag = "1";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--优惠套餐</title>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/project.css"/>
    <script src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="<%=appPath%>/images/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div class="g-banner2"></div>
<div id="qr_div" class="g-pw-body" style="display: none;">
    <div class="shade"></div>
    <div class="g-pw-radius">
        <div class="g-pw-radius-top"></div>
        <div class="g-pw2">
            <div id="qr_txt" class="g-pw2-w2">微信支付</div>
            <div class="g-pw2-w3">
                <img id="qr_code" src="" width="275" height="275"/>
                <span><font id="qr_tip">微信扫码支付</font>&nbsp;<a id="cancle_"
                                                              onclick="javascript:$('#qr_div').hide()">取消</a></span>
            </div>
        </div>
        <div class="g-pw-radius-bottom"></div>
    </div>
</div>
<div class="g-proj">
    <div class="g-pser">
        <!--充值 star-->
        <div class="g-pser-t">话费充值</div>
        <div class="g-pser-t2a">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为保护投资人隐私，秉承对企业及投资人负责原则，投融互动申请要求真实有效，经主办方审核通过，签约企业享有平台互动权利，投融双方可在平台实现实时电话互动。梧桐e路为双方平等交流提供虚拟号，虚拟号电话费用￥0.5元/分钟。</div>
        <ul id="Ms" class="g-pser-lstb">
            <li style="cursor: pointer" onclick="checkM(this,141);"><a>￥100</a><span
                    class="g-pser-lstmin">（200分钟）</span></li>
            <li style="cursor: pointer" onclick="checkM(this,142);"><a>￥200</a><span
                    class="g-pser-lstmin">（400分钟）</span></li>
            <li style="cursor: pointer" onclick="checkM(this,143);" class="on"><a>￥300</a><span class="g-pser-lstmin">（600分钟）</span>
            </li>
            <li style="cursor: pointer" onclick="checkM(this,144);"><a>￥400</a><span
                    class="g-pser-lstmin">（800分钟）</span></li>
            <li style="cursor: pointer" onclick="checkM(this,145);"><a>￥500</a><span
                    class="g-pser-lstmin">（1000分钟）</span></li>
        </ul>
        <div class="g-pser-paywap"><a onclick="recharge();" class="g-pser-pay">立即充值</a></div>
        <%
            if (loginUser != null) {
        %>
        <div class="g-pser-t">选择想要服务的项目</div>
        <div class="g-pser-t2"><span>温馨提示：</span>选择为您的一个项目购买套餐</div>
        <ul class="g-pser-lst">
            <%
                if (projects.size() == 0) {
            %>
            <li class="on"><a onclick="crPro()">请先创建项目</a><span
                    class="g-pser-left-top"></span><span class="g-pser-right-top"></span><span
                    class="g-pser-right-bottom"></span><span class="g-pser-left-bottom"></span></li>
            <%
            } else {
                for (int i = 0; i < 4 && i < projects.size(); i++) {
                    Project project = projects.get(i);
                    String cls = "";
                    if (projectid == 0 && i == 0) {
                        cls = " class='on'";
                    } else if (project.getId().intValue() == projectid) {
                        cls = " class='on'";
                    } else
                        cls = "";
            %>
            <li<%=cls%> onclick="checkProject(this,<%=project.getId()%>);" style="cursor: pointer">
                <a title="<%=project.getTitle()%>"><%=project.getTitle().length() > 9 ? project.getTitle().substring(0, 8) + "..." : project.getTitle()%>
                </a><span class="g-pser-left-top"></span><span class="g-pser-right-top"></span><span
                    class="g-pser-right-bottom"></span><span class="g-pser-left-bottom"></span></li>
            <%
                    }
                }
            %>
        </ul>
        <%
            }
        %>
        <div class="g-pser-t">购买服务</div>
        <div class="g-pser-t2"></div>
        <div class="g-pser-cnt">
            <h2>套餐一</h2>

            <div class="g-pser-cnttit">
                <div class="on1">￥3万</div>
                <div class="on2">现价<span>￥2万</span></div>
            </div>
            <div class="g-pser-cntont2">
                <ul>
                    <li onmouseover="showT(0)" onmouseout="hideT(0)">路演培育系统</li>
                    <li onmouseover="showT(1)" onmouseout="hideT(1)">路演推荐服务</li>
                    <li onmouseover="showT(2)" onmouseout="hideT(2)">投资人约见服务</li>
                </ul>
            </div>
            <div class="g-purchase"><a onclick="addGoods(1,20000);">购买</a></div>
            <div id="t_0" class="g-invest-bombbox0">
                <div class="g-invest-bombbox-t"></div>
                <div class="g-invest-bombbox-c">
                    <strong>①项目路演策划及组织</strong><br/>
                    组织专业路演团队，协助企业进行路演前材料准备，提前进行项目金融市场调研，根据企业具体阶段及需求进行路演活动组织策划<br/>
                    <strong>②商业计划书（路演版）撰写逻辑与制作</strong><br/>
                    协助企业进行自身项目尽职调查，协助企业撰写商业计划书<br/>
                    <strong>③项目路演技巧培训</strong><br/>
                    安排专人协助企业完成路演技巧辅导工作，协助企业高效沟通投资人<br/>
                    <strong>④投资逻辑研究</strong><br/>
                    对机构及机构思维逻辑进行介绍，协助企业理解投融资语言，有效提供符合投资方需求的信息
                </div>
                <div class="g-invest-bombbox-b"></div>
            </div>
            <div id="t_1" class="g-invest-bombbox1">
                <div class="g-invest-bombbox-t"></div>
                <div class="g-invest-bombbox-c">
                    ①专场路演展示，通过线下把项目推荐给平台上的投资人<br/>
                    ②融资项目通过线上7*24小时全天候，推荐给平台上的投资人
                </div>
                <div class="g-invest-bombbox-b"></div>
            </div>
            <div id="t_2" class="g-invest-bombbox2">
                <div class="g-invest-bombbox-t"></div>
                <div class="g-invest-bombbox-c">
                    ①线上向投资人推送融资项目，约见投资人<br/>
                    ②服务期内，每季度向项目企业批量推送有意向的投资人<br/>
                    ③项目企业动态实时跟踪并记录，提升企业融资成功效率
                </div>
                <div class="g-invest-bombbox-b"></div>
            </div>
        </div>
        <div class="g-pser-cnt">
            <h2>套餐二</h2>

            <div class="g-pser-cnttit">
                <div class="on1">￥8万</div>
                <div class="on2">现价<span>￥6万</span></div>
            </div>
            <div class="g-pser-cntont2">
                <ul>
                    <li onmouseover="showT(3)" onmouseout="hideT(3)">路演培育系统</li>
                    <li onmouseover="showT(4)" onmouseout="hideT(4)">路演推荐服务</li>
                    <li onmouseover="showT(5)" onmouseout="hideT(5)">投资人约见服务</li>
                    <li onmouseover="showT(6)" onmouseout="hideT(6)">资本孵化服务</li>
                </ul>
            </div>
            <div id="buy1" class="g-purchase"><a onclick="addGoods(2,60000);">购买</a></div>
            <div id="t_3" class="g-invest-bombbox3">
                <div class="g-invest-bombbox-t"></div>
                <div class="g-invest-bombbox-c">
                    <strong>①项目路演策划及组织</strong><br/>
                    组织专业路演团队，协助企业进行路演前材料准备，提前进行项目金融市场调研，根据企业具体阶段及需求进行路演活动组织策划<br/>
                    <strong>②商业计划书（路演版）撰写逻辑与制作</strong><br/>
                    协助企业进行自身项目尽职调查，协助企业撰写商业计划书<br/>
                    <strong>③项目路演技巧培训</strong><br/>
                    安排专人协助企业完成路演技巧辅导工作，协助企业高效沟通投资人<br/>
                    <strong>④投资逻辑研究</strong><br/>
                    对机构及机构思维逻辑进行介绍，协助企业理解投融资语言，有效提供符合投资方需求的信息
                </div>
                <div class="g-invest-bombbox-b"></div>
            </div>
            <div id="t_4" class="g-invest-bombbox4">
                <div class="g-invest-bombbox-t"></div>
                <div class="g-invest-bombbox-c">
                    ①专场路演展示，通过线下把项目推荐给平台上的投资人<br/>
                    ②融资项目通过线上7*24小时全天候，推荐给平台上的投资人
                </div>
                <div class="g-invest-bombbox-b"></div>
            </div>
            <div id="t_5" class="g-invest-bombbox5">
                <div class="g-invest-bombbox-t"></div>
                <div class="g-invest-bombbox-c">
                    ①线上向投资人推送融资项目，约见投资人<br/>
                    ②服务期内，每季度向项目企业批量推送有意向的投资人<br/>
                    ③项目企业动态实时跟踪并记录，提升企业融资成功效率
                </div>
                <div class="g-invest-bombbox-b"></div>
            </div>
            <div id="t_6" class="g-invest-bombbox6">
                <div class="g-invest-bombbox-t"></div>
                <div class="g-invest-bombbox-c">
                    <strong>①股权融资顾问</strong><br/>
                    帮助项目企业定位自己的融资路线，清晰自己所处发展阶段和可以利用的各类融资工具<br/>
                    <strong>②融资路线服务</strong><br/>
                    在项目企业引入投资者时，做好股权融资法务和谈判顾问<br/>
                    <strong>③资本引荐服务</strong><br/>
                    通过三个体系（自投/领投/推荐）去帮助项目企业借助各类资本工具，推动企业持续发展
                </div>
                <div class="g-invest-bombbox-b"></div>
            </div>
        </div>

        <div class="clear"></div>
        <div class="g-pser-n">
            <div id="sumPrice" class="g-pser-n1"></div>
            <div class="g-pser-n2">
                <a href="#qr_div" onclick="place('WECHAT')" class="on1">微信支付</a>
            </div>
        </div>
    </div>
</div>
<%@include file="../pub/foot.jsp" %>
</body>
<script>
    var projectid = <%=projects.size()>0?projects.get(0).getId():0%>;
    var goodsid = 0;
    var isLogin = <%=loginUser==null?"false":"true"%>;
    function checkProject(obj, id) {
        $(obj).parent().children('li').each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
        projectid = id;
        window.location.href = "<%=appPath%>/goods/list/" + projectid;
    }

    function addGoods(goodsid_, price) {
        if (!isLogin) {
            window.location.href = "<%=appPath%>/user/login.jsp";
            return;
        }
        if (projectid == 0) {
            xalert("对不起，创建项目后您才能购买套餐");
            return;
        }
        goodsid = goodsid_;
        $("#sumPrice").html("合计：￥" + price + "元");
    }
    var orderid = 0;
    function place(payType) {
        if (projectid == 0) {
            xalert("对不起，创建项目后您才能购买套餐");
            return;
        }
        if (goodsid == 0) {
            xalert("对不起，请选择要购买的套餐");
            return;
        }
        $.get("<%=appPath%>/order/place/" + projectid + "/" + goodsid + "/" + payType + "/authj", function (result) {
            window.location.href = "#";
            if (result.code < 0) {
                xalert(result.message);
                return;
            } else {
                $("#qr_div").show();
                orderid = result.data.orderid;
                $("#qr_code").attr("src", result.data.qrcode);
                timer1 = window.setInterval(getStatus, 3000);
            }
        }, "json");
    }
    var timer1 = 0;
    var counter1 = 0;
    function getStatus() {
        counter1++;
        if (counter1 > 100) {//5分钟之内完成支付
            window.clearInterval(timer1);
            return;
        }
        $.get("<%=appPath%>/order/status/" + orderid + "?r=" + Math.random(), function (result) {
            if (result.code >= 0 && result.data == 2) {
                $("#cancle_").hide();
                $("#qr_tip").html("支付成功&nbsp;<a onclick='refres();'>返回</a>");
            }
        }, "json");
    }
    function refres() {
        window.location.reload();
    }
    function showT(id) {
        $("#t_" + id).show();
    }
    function hideT(id) {
        $("#t_" + id).hide();
    }
    var rechargeGoodsid = 143;
    function recharge() {
        $.get("<%=appPath%>/order/recharge/" + rechargeGoodsid + "/WECHAT/authj", function (result) {
            window.location.href = "#";
            if (result.code < 0) {
                xalert(result.message);
                return;
            } else {
                $("#qr_div").show();
                orderid = result.data.orderid;
                $("#qr_code").attr("src", result.data.qrcode);
                timer1 = window.setInterval(getStatus, 3000);
            }
        }, "json");
    }
    function checkM(obj, id) {
        $("#Ms").children('li').each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
        rechargeGoodsid = id;
    }
    function crPro() {
        <%
            if(loginUser.getRoleid().intValue()>=20 && loginUser.getRoleid().intValue()<30){
            %>window.location.href = "<%=appPath%>/project/0/add/edit/1/auth";
        <%
                    }else{
                    %>xalert("对不起，您的身份不是企业/创业者不能创建项目");
        <%
                    }
                %>

    }
</script>
</html>
