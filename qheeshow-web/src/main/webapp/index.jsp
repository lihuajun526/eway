<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="/images/animate.min.css">
    <link rel="stylesheet"  href="/images/bootstrap.css">
    <!--*************************bootstrap css end************************-->
    <link rel="stylesheet" href="/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="/images/wt_index.css"/>
</head>
<body>
<!--*************************快速购买通道1************************-->
<div class="shade" style="display:none"></div>
<div class="g-pw1" style="display:none">
    <h1>请先填写企业信息</h1>
    <h3>步骤1/2</h3>
    <ul class="g-pw1-lst">
        <li><input type="text" class="g-pw-ipt" value="请输入真实姓名"/></li>
        <li><input type="text" class="g-pw-ipt" value="手机"/></li>
        <li><input type="text" class="g-pw-ipt" value="邮箱"/></li>
        <li><input type="text" class="g-pw-ipt" value="请输入短信验证码"/></li>
        <li><input type="text" class="g-pw-ipt" value="请输入密码"/></li>
        <li><input type="text" class="g-pw-ipt" value="确认密码"/></li>
    </ul>
    <div class="g-pw-btnw"><input name="" type="button" value="下一步" class="g-pw-btn"></div>
</div>
<!--*************************快速购买通道2************************-->
<div class="g-pw2" style="display:none">
    <h1>请支付</h1>
    <h3>步骤2/2</h3>
    <div class="g-pw2-m">￥1000</div>
    <div class="g-pw2-m2">支付方式</div>
    <ul class="g-pw2-lst">
        <li><img src="images/wt-ewm2.png"/><span>支付宝支付</span></li>
        <li><img src="images/wt-ewm2.png"/><span>微信支付</span></li>
    </ul>
</div>

<!--*************************头部************************-->
<jsp:include page="./pub/head.jsp" flush="true"/>
<!--*************************banner图************************-->
<div class="g-banner">
    <div class="g-sol1">
        <ul class="g-pag">
            <li><a href="#"><img src="images/wt-icon1.png"/></a></li>
            <li><a href="#"><img src="images/wt-icon2.png"/></a></li>
            <li><a href="#"><img src="images/wt-icon2.png"/></a></li>
        </ul>
    </div>
</div>
<!--*************************one************************-->
<div class="g-tit1"> <a href="#" class="on">最新活动	</a> <a href="#">优惠套餐</a></div>
<div class="g-cont">
    <ul class="g-one1">
        <a href="#">
            <li>
                <span><img src="images/bg-new1.png" width="320" height="180"/></span>
                <h1>恭贺滴滴打车融资成功</h1>
                <p>滴滴在本平台融资3亿美金，赫尔资本领投。融资后将在专车市场上细分代驾市场。</p>
                <div href="#" class="icon"><img src="images/wt-icon3.png"/></div>
            </li></a>
        <a href="#">
            <li>
                <span><img src="images/bg-new2.png" width="320" height="180"/></span>
                <h1>Uber融资成功</h1>
                <p>滴滴在本平台融资3亿美金，赫尔资本领投。融资后将在专车市场上细分代驾市场。</p>
                <div href="#" class="icon"><img src="images/wt-icon4.png"/></div>
            </li>
        </a>
        <a href="#">
            <li>
                <span><img src="images/bg-new3.png" width="320" height="180"/></span>
                <h1>Airbnb路演会场</h1>
                <p>滴滴在本平台融资3亿美金，赫尔资本领投。融资后将在专车市场上细分代驾市场。</p>
                <div href="#" class="icon"><img src="images/wt-icon3.png"/></div>
            </li>
        </a>
    </ul>
</div>

<!--*************************two************************-->
<div class="g-mbg">

    <h1 class="g-two-t">优秀项目推荐</h1>
    <h3 class="g-two-t2">最新活动——优惠的套餐更好的为您提供服务</h3>
    <div class="g-cont">

        <ul class="g-two2">
            <a href="#">
                <li>
                    <span class="on1"><img src="images/bg-1.png" width="100" height="100"/></span>
                    <h1 class="on2">酷我cowork全球</h1>
                    <h3 class="on3">专注提供城市精英社群社交办公场景闭环的联合办公。</h3>
                    <span class="on4"></span>
            <span class="on5">
               <div class="tit">项目：</div>
               <div class="cont">一个专注市场运作的产品形态，对接各种一线投资机构合伙人、行业专家及知名创业者定制化分享融资。</div>
            </span>
                </li>
            </a>
            <a href="#">
                <li>
                    <span class="on1"><img src="images/wt-icon5.png" width="100" height="100"/></span>
                    <h1 class="on2">酷我cowork全球</h1>
                    <h3 class="on3">专注提供城市精英社群社交办公场景闭环的联合办公。</h3>
                    <span class="on4"></span>
            <span class="on5">
               <div class="tit">项目：</div>
               <div class="cont">一个专注市场运作的产品形态，对接各种一线投资机构合伙人、行业专家及知名创业者定制化分享融资。</div>
            </span>
                </li>
            </a>
            <a href="#">
                <li>
                    <span class="on1"><img src="images/bg-3.png" width="100" height="100"/></span>
                    <h1 class="on2">酷我cowork全球</h1>
                    <h3 class="on3">专注提供城市精英社群社交办公场景闭环的联合办公。</h3>
                    <span class="on4"></span>
            <span class="on5">
               <div class="tit">项目：</div>
               <div class="cont">一个专注市场运作的产品形态，对接各种一线投资机构合伙人、行业专家及知名创业者定制化分享融资。</div>
            </span>
                </li>
            </a>
            <a href="#">
                <li>
                    <span class="on1"><img src="images/bg-4.png" width="100" height="100"/></span>
                    <h1 class="on2">酷我cowork全球</h1>
                    <h3 class="on3">专注提供城市精英社群社交办公场景闭环的联合办公。</h3>
                    <span class="on4"></span>
            <span class="on5">
               <div class="tit">项目：</div>
               <div class="cont">一个专注市场运作的产品形态，对接各种一线投资机构合伙人、行业专家及知名创业者定制化分享融资。</div>
            </span>
                </li>
            </a>
            <a href="#">
                <li>
                    <span class="on1"><img src="images/bg-5.png" width="100" height="100"/></span>
                    <h1 class="on2">酷我cowork全球</h1>
                    <h3 class="on3">专注提供城市精英社群社交办公场景闭环的联合办公。</h3>
                    <span class="on4"></span>
            <span class="on5">
               <div class="tit">项目：</div>
               <div class="cont">一个专注市场运作的产品形态，对接各种一线投资机构合伙人、行业专家及知名创业者定制化分享融资。</div>
            </span>
                </li>
            </a>
            <li>
                <span class="on1"><img src="images/wt-icon5.png" width="100" height="100"/></span>
                <h1 class="on2">酷我cowork全球</h1>
                <h3 class="on3">专注提供城市精英社群社交办公场景闭环的联合办公。</h3>
                <span class="on4"></span>
            <span class="on5">
               <div class="tit">项目：</div>
               <div class="cont">一个专注市场运作的产品形态，对接各种一线投资机构合伙人、行业专家及知名创业者定制化分享融资。</div>
            </span>
            </li>

        </ul>

        <div class="g-more"><a href="#">More</a></div>

    </div>

</div>

<!--*************************three************************-->
<div class="g-mbg2">

    <h1 class="g-two-t">优秀案例</h1>
    <h3 class="g-two-t2">最新活动——优惠的套餐更好的为您提供服务</h3>
    <ul class="g-three1">
        <a href="#">
            <li>
                <h1 class="on1">Airbnb全球</h1>
                <h3 class="on2">你的旅行住宿</h3>
                <span><img src="images/bg-3.png" width="100" height="100"/></span>
                <span class="on4">已完成融资</span>
            </li>
        </a>
        <a href="#">
            <li>
                <h1 class="on1">Airbnb全球</h1>
                <h3 class="on2">你的旅行住宿</h3>
                <span><img src="images/bg-4.png" width="100" height="100"/></span>
                <span class="on4">已完成融资</span>
            </li>
        </a>
        <a href="#">
            <li>
                <!--鼠标经过效果-->
                <div class="g-three-ov">
                    <h1 class="on1">Airbnb全球</h1>
                    <h3 class="on2">你的旅行住宿</h3>
                    <span><img src="images/bg-5.png" width="100" height="100"/></span>
                    <span class="on5">已完成融资</span>
                </div>
            </li>
        </a>
        <li>
            <h1 class="on1">Airbnb全球</h1>
            <h3 class="on2">你的旅行住宿</h3>
            <span><img src="images/wt-icon5.png" width="100" height="100"/></span>
            <span class="on4">已完成融资</span>
        </li>
        <li>
            <h1 class="on1">Airbnb全球</h1>
            <h3 class="on2">你的旅行住宿</h3>
            <span><img src="images/bg-4.png" width="100" height="100"/></span>
            <span class="on4">已完成融资</span>
        </li>
        <li>
            <h1 class="on1">Airbnb全球</h1>
            <h3 class="on2">你的旅行住宿</h3>
            <span><img src="images/bg-4.png" width="100" height="100"/></span>
            <span class="on4">已完成融资</span>
        </li>
        <li>
            <h1 class="on1">Airbnb全球</h1>
            <h3 class="on2">你的旅行住宿</h3>
            <span><img src="images/wt-icon5.png" width="100" height="100"/></span>
            <span class="on4">已完成融资</span>
        </li>
        <li>
            <h1 class="on1">Airbnb全球</h1>
            <h3 class="on2">你的旅行住宿</h3>
            <span><img src="images/bg-4.png" width="100" height="100"/></span>
            <span class="on4">已完成融资</span>
        </li>
        <li>
            <h1 class="on1">Airbnb全球</h1>
            <h3 class="on2">你的旅行住宿</h3>
            <span><img src="images/bg-4.png" width="100" height="100"/></span>
            <span class="on4">已完成融资</span>
        </li>
    </ul>

</div>

<!--*************************four************************-->
<div class="g-ban-ad1"></div>
<!--*************************Fives************************-->

<div class="g-mbg3">

    <h1 class="g-two-t">优秀投资人</h1>
    <h3 class="g-two-t2">平台活跃投资人，著名投资经纪人或机构</h3>
    <div class="g-conter">
        <div class="g-fivesen">
            <div class="g-fivesen-t"><img src="images/bg-tzr.png" width="110" height="110"/></div>
            <div class="g-fivesen-c">
                <div class="on1"><span class="name">东东东</span><span class="pt">平台推荐</span></div>
                <h2>IDG投资经纪人 | 北京</h2>
                <h3><a href="#">重庆易一天使是活跃在西南地区的天使投资机构，专注于TMT行业的早期投资，是奇虎、猪八戒的天使投资人。</a></h3>
            </div>
            <div class="g-fivesen-r">
                <div class="on1">感兴趣的</div>
                <div class="on2"><span>天使轮 | A轮</span><span>100W-5000W</span></div>
                <ul class="on3">
                    <li><a href="#">大数据</a></li>
                    <li><a href="#">o2o服务</a></li>
                    <li><a href="#">p2p</a></li>
                    <li><a href="#">赞服务</a></li>
                </ul>
            </div>
        </div>

        <ul class="g-lst1">
            <a href="#">
                <li>
                    <span><img src="images/bg-tzr-1.png" width="240" height="181"/></span>
                    <h1>成果龙</h1>
                    <h5>天使基金会 | 创始人</h5>
                    <h6>北京 | 汽车服务 数据服务 旅游</h6>
                </li>
            </a>
            <a href="#">
                <li>
                    <span><img src="images/bg-tzr-2.png" width="240" height="181"/></span>
                    <h1>人名称</h1>
                    <h5>天使基金会 | 创始人</h5>
                    <h6>北京 | 汽车服务 数据服务 旅游</h6>
                </li>
            </a>
            <a href="#">
                <li>
                    <span><img src="images/bg-tzr-3.png" width="240" height="181"/></span>
                    <h1>人名称</h1>
                    <h5>天使基金会 | 创始人</h5>
                    <h6>北京 | 汽车服务 数据服务 旅游</h6>
                </li>
            </a>
            <a href="#">
                <li>
                    <span><img src="images/bg-tzr-4.png" width="240" height="181"/></span>
                    <h1>人名称</h1>
                    <h5>天使基金会 | 创始人</h5>
                    <h6>北京 | 汽车服务 数据服务 旅游</h6>
                </li>
            </a>
            <a href="#">
                <li>
                    <span><img src="images/bg-tzr-5.png" width="240" height="181"/></span>
                    <h1>人名称</h1>
                    <h5>天使基金会 | 创始人</h5>
                    <h6>北京 | 汽车服务 数据服务 旅游</h6>
                </li>
            </a>
            <a href="#">
                <li>
                    <span><img src="images/bg-tzr-6.png" width="240" height="181"/></span>
                    <h1>人名称</h1>
                    <h5>天使基金会 | 创始人</h5>
                    <h6>北京 | 汽车服务 数据服务 旅游</h6>
                </li>
            </a>
            <a href="#">
                <li>
                    <span><img src="images/bg-tzr-7.png" width="240" height="181"/></span>
                    <h1>人名称</h1>
                    <h5>天使基金会 | 创始人</h5>
                    <h6>北京 | 汽车服务 数据服务 旅游</h6>
                </li>
            </a>
            <a href="#">
                <li>
                    <span><img src="images/bg-tzr-8.png" width="240" height="181"/></span>
                    <h1>人名称</h1>
                    <h5>天使基金会 | 创始人</h5>
                    <h6>北京 | 汽车服务 数据服务 旅游</h6>
                </li>
            </a>
        </ul>

        <div class="g-more2"><a href="#">More</a></div>
    </div>
</div>
<!--*************************footer************************-->
<jsp:include page="./pub/foot.jsp" flush="true"/>
</html>
