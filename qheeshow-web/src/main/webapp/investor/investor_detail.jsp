<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="com.qheeshow.eway.service.model.Xwcmclassinfo" %>
<%@ page import="java.util.List" %>
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
                <div class="on2"><%=investor.getPersonalProfile()%>
                </div>
            </div>
            <ul class="g-invest-lst">
                <li><a href="#">专业人士(120)</a></li>
                <li><a href="#">专业人士(120)</a></li>
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
                <div class="g-invest-lonect">
                    老鹰基金创始人，企业家、天使投资人，20年中国贸易、投资和电讯从业经验。中国长远控股有限公司
                    创办人，董事局主席兼首席执行官，香港中文大学新亚书院校董之一，湖北省黄石锶发矿业有限公司董
                    事长，湖南省张家界盛美达度假酒店董事长，北京威速科技有限公司董事。1993年，刘小鹰创办长远有
                    限公司，成功取得诺基亚移动电话中国市场第一家全国总代理资格，在中国建立全国性分销和加盟连锁
                    销售网络，并创下年销售额30亿港元的佳绩。2000年1月带领长远集团于香港创业板上市，于2004年
                    转往主板挂牌。 积极参与和推动中港信息科技的交流和发展，曾任香港无线科技商会(WITA)副会长，
                    历任广东省外商公会副会长，香港潮人联会副会长，上海潮汕商会名誉会长。
                </div>
            </div>

            <div class="g-invest-lone2">
                <div class="g-invest-two3">
                    <div class="g-invest-lonetit">评价 <span>/Profile</span></div>
                    <ul class="g-invest-lst3">
                        <li><a href="#">人脉大咖</a></li>
                        <li><a href="#">专业人士</a></li>
                        <li><a href="#">专业人士</a></li>
                        <li><a href="#">专业人士</a></li>
                        <li class="on"><a href="#">添加</a></li>
                    </ul>
                </div>
                <div class="g-invest-two">
                    <div class="g-invest-twl">星级评价：</div>
                    <div class="g-invest-twr">
                        <ul>
                            <li><img src="images/star-n.png"/></li>
                            <li><img src="images/star-n.png"/></li>
                            <li><img src="images/star-n.png"/></li>
                            <li><img src="images/star-m.png"/></li>
                        </ul>
                    </div>
                </div>
                <div class="g-invest-two1">
                    <div class="g-invest-twl">描述评论：</div>
                    <div class="g-invest-twr">
                        <textarea name="" cols="" rows="" class="g-invest-twrtex"></textarea>
                        <div class="g-invest-twr-fb"><a href="#">发表评论</a></div>
                    </div>
                </div>
                <div class="g-invest-two2">20条评论</div>

                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-invest-tre-rcnt"><a href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a>
                        </div>
                    </div>
                </div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-invest-tre-rcnt"><a href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a>
                        </div>
                    </div>
                </div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-invest-tre-rcnt"><a href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a>
                        </div>
                    </div>
                </div>

                <div class="i-Page"><a href="#" class="on1">&nbsp;</a><!--<a href="#" class="on4">&nbsp;</a>当前位置及鼠标经过箭头效果--> <a
                        href="#">1</a> <a href="#" class="on">2</a> <a href="#">3</a> <a href="#" class="on3">...</a> <a
                        href="#" class="on2">&nbsp;</a>
                    <!--<a href="#" class="on5">&nbsp;</a>--></div>
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

</script>
</html>
