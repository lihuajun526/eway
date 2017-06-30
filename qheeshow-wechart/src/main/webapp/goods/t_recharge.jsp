<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <ul class="wtwx-package-money">
        <li onclick="checkR(this,141,100)">￥<a>100</a></li>
        <li onclick="checkR(this,142,200)" class="on">￥<a>200</a></li>
        <li onclick="checkR(this,145,500)">￥<a>500</a></li>
    </ul>
    <div class="wtwx-package-money-notes">
        为保护投资人隐私，秉承对企业及投资人负责原则，投融互动申请要求真实有效，经主办方审核通过，签约企业享有平台互动权利，投融双方可在平台实现实时电话互动。梧桐e路为双方平等交流提供虚拟号，虚拟号电话费用￥0.5元/分钟。
    </div>
</div>
<script>
    curGoods = 142;
    projectid = 0;
    $("#sum").html("200");
    function checkR(obj, id, price) {
        $(obj).parent().children('li').each(function () {
            $(this).removeClass("on");
        });
        $(obj).attr("class", "on");
        curGoods = id;
        $("#sum").html(price);
    }
</script>
