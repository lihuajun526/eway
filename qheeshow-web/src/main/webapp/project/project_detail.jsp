<%@ page import="com.qheeshow.eway.service.model.Project" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Project project = (Project) request.getAttribute("areas");
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
    <script src="/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div class="g-proj">
    <div class="g-invest">
        <div class="g-proj-img"><img src="<%=project.getLogo()%>" width="200" height="200"/></div>
        <div class="g-proj-tr">
            <div class="g-invest-name2"><%=project.getTitle()%></div>
            <div class="g-invest-t"><%=project.getDemand()%></div>
            <ul class="g-proj-lst">
                <li class="on1"><a><%=project.getAreaName()%></a></li>
                <li class="on2"><a><%=project.getStageName()%></a></li>
                <li class="on3"><a><%=project.getIndustryName()%></a></li>
            </ul>
            <ul class="g-proj-lst2">
                <li><a href="#">专业人士(120)</a></li>
                <li><a href="#">专业人士(120)</a></li>
                <li><a href="#">市场运作经验</a></li>
                <li><a href="#">渠道资源</a></li>
                <li><a href="#">渠道资源</a></li>
            </ul>
            <a href="#" class="g-invest-focus">+关注</a>
            <ul class="g-proj-lst3">
                <li><a href="#">申请成为专职金融顾问</a></li>
                <li><a href="#">查看联系方式</a></li>
            </ul>
            <div class="g-proj-ico2"></div>
        </div>
    </div>

    <div class="g-conter">
        <div class="g-invest-l">
            <div class="g-invest-lone3">
                <ul class="g-proj-titlst">
                    <li><a href="#">项目简介</a></li>
                    <li><a href="#">项目简介</a></li>
                    <li><a href="#">项目简介</a></li>
                    <li><a href="#">项目亮点</a></li>
                    <li><a href="#">项目亮点</a></li>
                </ul>
            </div>
            <div class="g-invest-lone">
                <div class="g-proj-lonetit6">项目简介</div>
                <div class="g-invest-lonect"><%=project.getDescription()%></div>
            </div>
            <div class="g-invest-lone2">
                <div class="g-proj-lonetit2">团队介绍</div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">张成</span><span class="on2">工程师</span></div>
                        <div class="g-invest-tre-rcnt"><a href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a></div>
                    </div>
                </div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">张成</span><span class="on2">工程师</span></div>
                        <div class="g-invest-tre-rcnt"><a href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a></div>
                    </div>
                </div>
            </div>

            <div class="g-invest-lone">
                <div class="g-proj-lonetit6">项目亮点</div>
                <div class="g-invest-lonect">
                    老鹰基金创始人，企业家、天使投资人，20年中国贸易、投资和电讯从业经验。中国长远控股有限公司
                    创办人，董事局主席兼首席执行官，香港中文大学新亚书院校董之一，湖北省黄石锶发矿业有限公司董
                    事长，湖南省张家界盛美达度假酒店董事长，北京威速科技有限公司董事。1993年，刘小鹰创办长远有誉会长。
                </div>
            </div>
            <div class="g-invest-lone3">
                <ul class="g-proj-titlst">
                    <li class="on-bp">项目BP</li>
                </ul>
                <a href="#" class="g-proj-more">登录查看商业计划书</a>
            </div>
            <div class="g-invest-lone2">
                <div class="g-proj-lonetit4">提问互动<span>20条评论</span></div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-invest-tre-rcnt"><a href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a></div>
                        <div class="g-proj-reply"><a href="#">回复</a></div>
                    </div>
                </div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-invest-tre-rcnt"><a href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a></div>
                        <div class="g-proj-reply"><a href="#">回复</a></div>
                        <div class="g-proj-texwap"><textarea name="" cols="" rows="" class="g-proj-textarea"></textarea><div class="g-proj-texcru">还可以输入<span>150</span>个字</div></div>
                        <div class="g-proj-texbtn"><a href="#" class="on1">取消</a><a href="#" class="on2">回复</a></div>

                    </div>
                </div>
                <div class="g-invest-tre">
                    <div class="g-invest-treimg"><img src="images/bg-new1.png" width="60" height="60"/></div>
                    <div class="g-invest-tre-r">
                        <div class="g-invest-tre-rt"><span class="on1">宋仲基</span><span class="on2">今天 17:09</span></div>
                        <div class="g-proj-subtitle">@宋积   您的项目太棒了，我要投资。</div>
                        <div class="g-invest-tre-rcnt"><a href="#">好项目真的好项目真的是立本的是立本的，模式的成立也是需要实际验证的，我看好你们的项目，也表示自己的兴趣。</a></div>
                        <div class="g-proj-reply"><a href="#">回复</a></div>
                    </div>
                </div>
                <div class="i-Page"><a href="#" class="on1">&nbsp;</a><!--<a href="#" class="on4">&nbsp;</a>当前位置及鼠标经过箭头效果--> <a href="#">1</a> <a href="#" class="on">2</a> <a href="#">3</a> <a href="#" class="on3">...</a> <a href="#" class="on2">&nbsp;</a>
                    <!--<a href="#" class="on5">&nbsp;</a>--></div>
            </div>
        </div>
        <!--*************************right star************************-->
        <div class="g-invest-r">
            <div class="g-invest-rone">
                <div class="g-invest-rt2">专职顾问</div>
                <ul class="g-proj-cost">
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>章程</span></a></li>
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>陈芯</span></a></li>
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>章程</span></a></li>
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>章程</span></a></li>
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>章程</span></a></li>
                </ul>
            </div>
            <div class="g-invest-rone">
                <div class="g-invest-rt2">感兴趣的人</div>
                <ul class="g-proj-cost">
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>章程</span></a></li>
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>章程</span></a></li>
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>章程</span></a></li>
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>章程</span></a></li>
                    <li><a href="#"><img src="images/bg-new1.png" width="50" height="50"/><span>章程</span></a></li>
                </ul>
            </div>

            <div class="g-invest-rone">
                <div class="g-invest-rt">常见问题</div>
                <ul class="g-invest-rlst2">
                    <li><span class="on1"></span><span><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></span></li>
                    <li><span class="on1"></span><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></li>
                    <li><span class="on1"></span><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></li>
                    <li><span class="on1"></span><a href="#">逸是一站式微信办公平台，为企业提供整体微信办公解决方案。</a></li>
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
