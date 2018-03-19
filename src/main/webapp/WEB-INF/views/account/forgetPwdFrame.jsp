<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="${ctx}/static/css/common.css" rel="stylesheet" type="text/css" />
<title>找回密码</title>
</head>

<body>
	<div class="login-div" id="reg-div">
    	<form action="${ctx}/register/forgetPwd" method="post" id="register_form">
			<div class="tips">${register_err_msg}</div>
			<div class="input">
				<span class="user-icon"></span><input name="mobile" id="input_phone" class="text-input" type="text" required="required" maxlength="11" placeholder="手机号码"></input>
			</div>
			<div class="input mt20">
				<span class="password-icon"></span><input name="password" class="text-input" type="password" value="" required="required"  placeholder="新密码"></input>
				
			</div>
			<div class="input mt20 w150">
				<span class="code-icon"></span><input name="thekey" class="text-input w120" type="text" value="" placeholder="验证码"></input>
			</div>
			<span class="code" id="li_gain_val_key" onclick="gainkey()">发送验证码</span>
	        <!--<span class="code code-disabled">60s后可重新发送</span>-->
			<div class="login-layer-btn"><input type="submit" value="确认更改" /></div>
		</form>
	</div>
    
<script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	var m_reg=/^1[3-9][0-9]{9}$/i;
	function gainkey(){
		var mobile = $("#input_phone").val();
		if(m_reg.test(mobile)){
			$("#li_gain_val_key").attr("onclick", "");
			$("#li_gain_val_key").addClass("code-disabled");
			$.post("${ctx}/register/valmobileForForget",{mobile:mobile},function(data){
				if(data=="success"){
					var thenum = 60;
					var timmer = setInterval(function(){
						$("#li_gain_val_key").html(thenum+'s后可以重新获取');
						thenum--;
						if(thenum==-1){
							clearInterval(timmer);
							$("#li_gain_val_key").removeClass("code-disabled");
							$("#li_gain_val_key").html("获取手机验证码");
							$("#li_gain_val_key").attr("onclick", "gainkey()");
						}
					},1000);
				}else if(data == "null"){
					alert("该手机号码尚未注册或未被绑定");
					return;
				}else{
					alert("验证码发送不成功！");
					$("#li_gain_val_key").attr("onclick", "gainkey()");
					$("#li_gain_val_key").removeClass("code-disabled");
				}
			});
		}
		else{
			alert("您输入的手机号码格式不正确");
		}
	}
    </script>
</body>
</html>
