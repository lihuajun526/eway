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
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/investor.css"/>
    <script src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <script src="<%=appPath%>/images/bootstrap.min.js"></script>
    <script src="<%=appPath%>/jquery/jquery-form.js"></script>
    <script src="<%=appPath%>/js/util.js"></script>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div class="inv-body">
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
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                            class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                    <li class="on3"></li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">邮箱：</li>
                    <li class="on2">
                        <input readonly id="email" name="email" class="inv-one-ipt"
                               value="<%=StringUtils.isEmpty(investor.getEmail())?"":investor.getEmail()%>"
                               placeholder="请输入邮箱"/>
                        <span class="pro1-left-top"></span><span class="pro1-right-top"></span><span
                            class="pro1-right-bottom"></span><span class="pro1-left-bottom"></span>
                    </li>
                </ul>
            </div>
            <div class="inv-one">
                <ul class="inv-one-lst">
                    <li class="on1">名片：</li>
                    <li class="on2">
                        <ul class="inv-lst2">
                            <li id="li_businessCardPositive" class="inv-lst2-l">
                                <%
                                    if (StringUtils.isEmpty(investor.getBusinessCardPositive())) {
                                %>
                                <span>
                                    <img id="businessCardPositiveImg" src="<%=appPath%>/images/wt-icon19.png" width="58"
                                         height="58"/>
                                </span>
                                <span class="head">名片正面（选填）</span>
                                <%
                                } else {
                                %>
                                <span class="upload-a"><img src="<%=investor.getBusinessCardPositive()%>" width="170"
                                                            height="110"/></span>
                                <%
                                    }
                                %>
                                <input id="businessCardPositiveFile" name="businessCardPositiveFile" type='file'
                                       onchange="doUpload('businessCardPositive');" unselectable="on" class="on5"/>
                            </li>
                            <li id="li_businessCardOpposite">
                                    <%
                                        if(StringUtils.isEmpty(investor.getBusinessCardOpposite())){
                                            %>
                                    <span>
                                        <img id="businessCardOppositeImg" src="<%=appPath%>/images/wt-icon19.png" width="58" height="58"/>
                                    </span>
                                    <span class="head">名片反面（选填）</span>
                                    <%
                                        }else{
                                            %>
                                    <span class="upload-a"><img src="<%=investor.getBusinessCardOpposite()%>" width="170" height="110"/></span>
                                    <%
                                        }
                                    %>
                                <input id="businessCardOppositeFile" name="businessCardOppositeFile" type='file'
                                       onchange="doUpload('businessCardOpposite');" unselectable="on" class="on5"/>
                            </li>
                        </ul>
                    </li>
                    <%
                        if(!StringUtils.isEmpty(investor.getBusinessCardPositive()) || !StringUtils.isEmpty(investor.getBusinessCardOpposite())){
                            %><li class="on3">点击图片重新选择名片</li><%
                        }
                    %>
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
<%@include file="../pub/foot.jsp" %>
</body>
<script>
    //上传图片
    function doUpload(id) {
        var file = $('#' + id + "File");
        if (!file || !file.val())
            return;
        var patn = /\.jpg$|\.jpeg$|\.png$|\.gif$/i;
        if (!patn.test(file.val())) {
            xalert("请选择图片文件");
            return;
        }
        file.wrap("<form id='myUpload' action='<%=appPath%>/image/upload' method='post' enctype='multipart/form-data'></form>");
        $('#myUpload').ajaxSubmit({
            dataType: 'json',
            success: function (result) {
                file.unwrap();
                resetFileInput(file);
                if (result.code == -1) {
                    xalert(result.message);
                    return;
                }
                $("#li_" + id).html("<span class='upload-a'><img src='" + result.data.path + "' width='170' height='110'/></span><input id='"+id+"File' name='"+id+"File' type='file' onchange='doUpload(\'"+id+"\');' unselectable='on' class='on5'/>");
                $('#' + id).val(result.data.path);
            },
            error: function (xhr) {
                file.unwrap();
                resetFileInput(file);
                xalert('上传失败!');
            }
        });
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
    function resetFileInput(file){
        file.after(file.clone().val(""));
        file.remove();
    }
</script>
</html>
