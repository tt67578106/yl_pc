<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码_优蓝网</title>
<meta name="description" content=""/>
<meta name="keywords" content="" />
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${ctx}/static/css/common.css?v=20150417" />
</head>
<body>
<div class="page-wrapper h-100p">
    <!--/top--->
	<div class="top-div h-50p" >
		<div class="top-header" >
			<p class="title" style="float: left;"><img src="${ctx }/static/images/v3/logo.png" alt="优蓝网_logo" /><span >|</span>找回密码</p>
			<p class="hot-line" ><i></i>快速求职热线：4008-777-816</p>
			<div class="clear"></div>
		</div>
	</div>

	<!--找回密码-->
	<div class="reset-form find-form" >
        <div class="form-each mt40">
        	<!--进度-->
            <ul class="progress-g p-g-1">
            	<li class="l1"></li>
                <li class="c1"></li>
                <li class="l2"></li>
                <li class="c2"></li>
                <li class="l4"></li>
                <li class="t1">手机号码验证</li>
                <li class="t2">重置密码</li>
            </ul>
            <!--/进度-->
            <div class="clear"></div>
        </div>
        <!-- 第一步 -->
        <div id="step1">
	        <div class="form-each form-code"><input type="tel" class="form-each-input"  id="input_mobile" maxlength="11"  placeholder="请输入已验证的手机号"/><span id="btn_gain_val_key" onclick="gainkey()" >获取验证码</span><p class="tips hide err_mobile"></p></div>
	        <div class="form-each"><input type="text" class="form-each-input" id="input_vali" maxlength="4" placeholder="请输入验证码"/><p class="tips hide err_vali"></p></div>
	        <div class="form-each mt40"><input type="button" value="下一步" class="btn-g bdr0 btn-green step-g-trigger" data-step="1"/></div>
	    </div>
	    <!-- 第二步 -->
	    <div id="step2" class="hide">
		   	<div class="form-each"><input id="input_planpass" type="password" class="form-each-input" placeholder="请输入新密码"/><p class="tips hide err_password"></p></div>
	        <div class="form-each"><input id="input_repass" type="password" class="form-each-input" placeholder="请再次输入新密码"/><p class="tips hide err_repassword"></p></div>
	        <div class="form-each"><input type="button" value="保存" class="btn-g bdr0 btn-green step-g-trigger" data-step="2"/></div>
	    </div>
	    <div class="clear"></div>
	</div>
    <!--/找回密码-->
</div>
   
<div class="footer-sty">沪ICP备14033709号-1&nbsp;&nbsp;&nbsp;Copyright © 2013-2016 youlanw.com All Rights Reserved</div>
   
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
    var m_reg=/^1[3-9][0-9]{9}$/i;
	function gainkey(){
		$(".tips").hide();
		var mobile = $.trim($("#input_mobile").val());
		if(m_reg.test(mobile)){
			$("#btn_gain_val_key").attr("onclick", "");
			$("#btn_gain_val_key").addClass("send-disabled");
			$.post("${ctx}/forgetpwd/valmobileForForget",{mobile:mobile},function(data){
				if(data=="success"){
					var thenum = 60;
					var timmer = setInterval(function(){
						$("#btn_gain_val_key").html(thenum+'s后可以重新获取');
						thenum--;
						if(thenum==-1){
							clearInterval(timmer);
							$("#btn_gain_val_key").removeClass("send-disabled");
							$("#btn_gain_val_key").html("获取手机验证码");
							$("#btn_gain_val_key").attr("onclick", "gainkey()");
						}
					},1000);
				}else if(data == "null"){
					$(".err_mobile").show();
					$(".err_mobile").html("该手机号码尚未注册或未被绑定！");
					$("#btn_gain_val_key").attr("onclick", "gainkey()");
					$("#btn_gain_val_key").removeClass("send-disabled");
				}else{
					$(".err_mobile").show();
					$(".err_mobile").html("验证码发送不成功！");
					$("#btn_gain_val_key").attr("onclick", "gainkey()");
					$("#btn_gain_val_key").removeClass("send-disabled");
				}
			});
		}else{
			$(".err_mobile").show();
			$(".err_mobile").html("您输入的手机号码格式不正确");
		}
	}

$(function(){
	$("#input_mobile").blur(function(){
		if(!m_reg.test($(this).val())){
			$(this).focus();
			$(".tips").hide();
			$(".err_mobile").show();
			$(".err_mobile").html("请输入正确的手机号码！");
		}else{$(".err_mobile").hide();}
	});
	//找回密码 下一步
	$('.step-g-trigger').click(function(){
		var dom = this;
		var step = $(dom).attr("data-step");
		if(step == 1){
			var m = $.trim($("#input_mobile").val());
			var v = $.trim($("#input_vali").val());
			if(m_reg.test(m) && v.length == 4){
				$.post("${ctx}/forgetpwd/valiKey",{"mobile":m,"thekey":v},function(result){
					if(result.length>7 && result.substring(0,7) == "success"){
						$("#msg_tip").text("");
						$("#text_mobile").text(m);
						$("#input_mobile").data("vali",result.substr(8));
						$("#step1").hide();
						$("#step2").show();
						$('.progress-g').removeClass('p-g-1').addClass('p-g-2');			
					}else{
						$(".tips").hide();
						$(".err_vali").show();
						$(".err_vali").html("您输入的验证码有误，请检查后重试!");
						$("#input_vali").focus();
					}
				});
			}else{
				$(".tips").hide();
				$(".err_vali").show();
				$(".err_vali").html("请输入手机号和收到的验证码!");
			}
		}else if(step == 2){
			var mobile = $.trim($("#input_mobile").val());
			var planpass = $.trim($("#input_planpass").val());
			var repass = $.trim($("#input_repass").val());
			if(planpass==null||planpass==''){
				$(".tips").hide();
				$(".err_password").show();
				$(".err_password").html("请输入您的新密码!");
				return false;
			}else if(planpass === repass){
				$(".tips").hide();
				$.post("${ctx}/forgetpwd/resetPassword",{"vali":$("#input_mobile").data("vali"),"mobile":mobile,"pass":planpass},function(result){
					if(result == "success"){
						alert("修改成功！");
						window.location.href="${ctx}/";
					}else{
						alert("验证码超时，请重试");
						window.location.href = window.location.href ;
					}
				});
			}else{
				$(".tips").hide();
				$(".err_repassword").show();
				$(".err_repassword").html("两次密码输入不一致!");
				return false;
			}
		}
	});
});
</script>
</body>
</html>