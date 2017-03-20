function isEmpty(str) {
    if (str == null || str == '')
        return true;
    return false;
}
//显示本地图片
function getObjectURL(file) {
    var url = null ;
    if (window.createObjectURL!=undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}
//获取微信授权信息后回调函数
function nextTodo(result){
    if(result != null){
    	if(result.id != null){
    		//若该微信号一绑定平台账号则前往首页
        	window.location.href = "/index";
        }else{
        	sessionStorage.setItem("openid",result.openid);
        	sessionStorage.setItem("nickname",result.nickname);
        	//若未绑定则前往绑定页
        	window.location.href = appName + "/weChat/toBand.html";
        }
    }else{
    	alert("用户信息获取失败");
    }
}