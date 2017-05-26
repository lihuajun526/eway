<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%
    String appPath = Config.get("app.path");
    String flag = "1";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><%=Config.get("app.name")%>
    </title>
    <script type="text/javascript" src="<%=appPath%>/jquery/jquery-1.11.1.js"></script>
    <link rel="stylesheet" href="<%=appPath%>/images/animate.min.css">
    <link rel="stylesheet" href="<%=appPath%>/images/bootstrap.css">
    <link rel="stylesheet" href="<%=appPath%>/images/global_v2.0.0.css"/>
    <link rel="stylesheet" href="<%=appPath%>/images/wt_index.css"/>
</head>
<body>
<%@include file="../pub/head.jsp" %>
<div class="g-proj">
    <div class="g-pser">
        <div class="g-read-t">常见问题</div>

        <div class="g-read-cnt">
            <p>问题1：如何成为平台推荐项目？
                <br/>1. 用户在遵守本协议的前提下，依法享有梧桐e路提供的服务，有权对梧桐e路的服务进行监督、批评和指导，本平台欢迎用户提出服务改进建议，并会以勤勉态度为用户提供服务。
                <br/>2. 用户应当按照本协议和本平台的有关管理规则提交注册信息，并保证注册信息的真实性和完整性。用户可选择不公开或部分公开自己的注册信息，对用户选择公开的信息以及用户上传的项目、资金信息等资料，梧桐e路、其他用户及其他浏览者将有权依据各自权限级别浏览、查阅和利用。
                <br/>3. 用户应当妥善的保存自己的用户名和密码，不得以任何形式擅自转让或授权他人使用自己在梧桐e路的用户帐号。用户对其在梧桐e路注册的用户名和密码的安全性负全部责任，并对以其用户名进行的所有活动和事件负全部责任，用户若发现任何非法使用其账号或存在安全漏洞的情况，应立即通知梧桐e路。
                <br/>4. 用户保证通过梧桐e路发布的信息是真实、合法的，用户发布的信息不能含有任何违反国家有关法律、法规及中华人民共和国承认或加入的国际条约的内容，包括但不限于危害国家安全、淫秽色情、虚假、侮辱、诽谤、恐吓或骚扰、蓄意毁坏、恶意干扰、秘密地截取或侵占任何系统、数据等侵犯他人知识产权、人身权或其他合法权益以及有违社会公序良俗的内容或指向这些内容的链接。
                <br/>5. 用户有权享有梧桐e路提供信息交流的各种服务，在本平台进行包括但不限于信息发布、信息浏览、信息订阅等相关活动时，应当严格遵守梧桐e路的相关管理规则。
                <br/>6. 用户通过梧桐e路进行信息发布、信息浏览、信息订阅等活动所引起的一切法律纠纷均与梧桐e路无关。因用户违反有关法律、法规或本协议的有关规定而给梧桐e路或者第三方造成损失的，用户同意赔偿因此而导致的全部损失，包括但不限于实际损失、可预期利益、因此发生的诉讼费、律师费、赔偿金等。
                <br/>7. 用户在梧桐e路实施了违法行为，导致第三方投诉（包括但不限于第三方以发函等形式指控梧桐e路侵权，对梧桐e路提起诉讼、仲裁，或使梧桐e路面临相关主管机关的审查或质询），梧桐e路有权先暂停用户对梧桐e路的使用。用户应在收到梧桐e路通知后，应以自己名义出面与第三方协商、参与诉讼或接受相关主管机关审查或质询，并承担所有费用，并赔偿因此给梧桐e路造成的全部损失。
                <br/>8. 用户不得通过任何方式攻击、干扰或试图攻击、干扰梧桐e路的正常运作及其有关活动，也不得帮助或教唆任何第三方从事上述活动。
            </p>
        </div>

    </div>
</div>
<%@include file="../pub/foot.jsp" %>
</body>
</html>
