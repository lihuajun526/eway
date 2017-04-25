<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page import="com.qheeshow.eway.service.model.Investor" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String appPath = Config.get("app.path");
    Investor investor = (Investor) request.getAttribute("investor");
    String flag = "3";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>--投资人信息完善</title>
    <link rel="stylesheet"  href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/investor.css"/>
    <script src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script src="<%=appPath%>/images/bootstrap.min.js"></script>
    <script src="<%=appPath%>/jquery/jquery-form.js"></script>
    <script src="<%=appPath%>/js/util.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp"%>
<div class="inv-body">
    <input id="businessCardPositiveFile" name="businessCardPositiveFile" type="file" multiple="multiple"
           onchange="doUpload('businessCardPositive')" style="display: none"/>
    <input id="businessCardOppositeFile" name="businessCardOppositeFile" type="file" multiple="multiple"
           onchange="doUpload('businessCardOpposite')" style="display: none"/>

    <form id="authForm">
        <input type="hidden" name="id" value="<%=investor.getId()%>"/>
        <input type="hidden" id="businessCardPositive" name="businessCardPositive"
               value="<%=investor.getBusinessCardPositive()%>"/>
        <input type="hidden" id="businessCardOpposite" name="businessCardOpposite"
               value="<%=investor.getBusinessCardOpposite()%>"/>

        <div class="inv-wap">
            <div class="inv-t">认证信息(2/2)</div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">手机：</li>
                    <li class="on2">
                        <input id="mobile" name="mobile" class="inv-one-ipt"
                                           value="<%=StringUtils.isEmpty(investor.getMobile())?"":investor.getMobile()%>"
                                           placeholder="输入手机号"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                    <li class="on3"></li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">邮箱：</li>
                    <li class="on2">
                        <input id="email" name="email" class="inv-one-ipt"
                                           value="<%=StringUtils.isEmpty(investor.getEmail())?"":investor.getEmail()%>"
                                           placeholder="请输入邮箱"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">名片：</li>
                    <li class="on2">
                        <ul class="inv-lst2">
                            <li class="inv-lst2-l">
                                <span>
                                    <img id="businessCardPositiveImg"
                                         src="<%=StringUtils.isEmpty(investor.getBusinessCardPositive())?"/images/wt-icon19.png":investor.getBusinessCardPositive()%>"
                                         width="58"
                                         height="58" onclick="selectFile('businessCardPositiveFile')"/>
                                </span>
                                <span class="head">名片正面（必要）</span></li>
                            <li><span><img id="businessCardOppositeImg"
                                           src="<%=StringUtils.isEmpty(investor.getBusinessCardOpposite())?"/images/wt-icon19.png":investor.getBusinessCardOpposite()%>"
                                           width="58"
                                           height="58" onclick="selectFile('businessCardOppositeFile')"/></span><span
                                    class="head">名片反面（选填）</span></li>
                        </ul>
                    </li>
                    <li class="on3">必填信息</li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">个人资产：</li>
                    <li class="on5">
                        <ul class="inv-one-lst2">
                            <li><input name="personalAssets" type="radio" value="300万以下"></li>
                            <li class="inv-radio2">300万以下</li>
                            <li><input name="personalAssets" type="radio" value="300万-500万"></li>
                            <li class="inv-radio2">300万-500万</li>
                            <li><input name="personalAssets" type="radio" value="500万-1000万"></li>
                            <li class="inv-radio2">500万-1000万</li>
                            <li><input name="personalAssets" type="radio" value="1000万以上"></li>
                            <li class="inv-radio2">1000万以上</li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1"></li>
                    <li class="on6">
                        <span class="inv-one-span"><input name="" type="checkbox" checked>本人承诺上述认证信息情况属实，如有不实，愿意承担相应责任。</span>
                        <span class="inv-one-span"><input name="" type="checkbox"
                                                          checked>本人已认真阅读《风险揭示书》，完全理解并认同全部内容。</span>
                    </li>
                </ul>
            </div>

            <div class="inv-clear"></div>
            <div class="inv-btn2"><a onclick="saveAuth();">提交</a></div>
        </div>
    </form>
</div>
<%@include file="../pub/foot.jsp"%>
</body>
<script>
    function selectFile(id) {
        $('#' + id).click();
    }
    //上传图片
    function doUpload(id) {
        $.ajaxFileUpload({
                    url: '<%=appPath%>/image/upload', //用于文件上传的服务器端请求地址
                    type: 'post',
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: id + 'File', //文件上传域的ID
                    dataType: 'json', //返回值类型 一般设置为json
                    success: function (result) {  //服务器成功响应处理函数
                        if (result.code == -1) {
                            xalert(result.message);
                            return;
                        }
                        $('#' + id + 'Img').attr("src", result.data.path);
                        $('#' + id).val(result.data.path);
                    }
                }
        );
    }
    function saveAuth() {
        if (isEmpty($("#mobile").val())) {
            xalert("请填写手机号码");
            return;
        }
        if (isEmpty($("#email").val())) {
            xalert("请填写电子邮箱");
            return;
        }
        if (isEmpty($("#businessCardPositive").val())) {
            xalert("请上传名片正面照片");
            return;
        }
        $.ajax({
            type: 'POST',
            url: '<%=appPath%>/investor/auth/save/authj',
            cache: false,
            processData: false,
            data: $('#authForm').serialize(),
            dataType: 'json',
            success: function (result) {
                if (result.code < 0) {
                    xalert(result.message);
                    return;
                }
                window.location.href = "<%=appPath%>/index";
            }
        });
    }
    if (!<%=StringUtils.isEmpty(investor.getPersonalAssets())%>) {
        $("input[name='personalAssets'][value='<%=investor.getPersonalAssets()%>']").attr("checked", true);
    }
</script>
</html>
