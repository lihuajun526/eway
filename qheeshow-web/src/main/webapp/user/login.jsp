<%--
  Created by IntelliJ IDEA.
  User: lihuajun
  Date: 17-1-19
  Time: 上午10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../pub/head.jsp" flush="true"/>
<div>用户登录</div>
<input id="userName" placeholder="用户名" value="zhuyue"/>
		<input id="userPassword" placeholder="密码" value="123123"/>
		<button onclick="login()" >登录</button>
<jsp:include page="../pub/foot.jsp" flush="true"/>
</body>
<script type="text/javascript" src="../statics/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript">
function login(){
	$.ajax({
		type:'GET',
		url:"http://localhost:8080/web/user/login.json?username=" + $("#userName").val() + 
			"&password=" + $("#userPassword").val(),
		success:function(data){
			if(data.success){
				alert("登录成功");
			}else{
				alert(data.data);
			}
		}
	});
}
</script>
</html>
