<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>用户注册_优蓝网</title>
<meta name="description" content=""/>
<meta name="keywords" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
<style>
/*code-layer*/
.reg-div {
    width: 600px;
    float: left;
    margin: 0px 0 0 20px;
}
.reg-tips.error {
    color: #ed5f30;
}
.reg-div label{display: inline-block;}
.code-layer{width:360px;}
.code-layer .input{margin-top:30px;width: 600px;margin-bottom: 10px;
height: 45px;
border-radius: 2px;
float: left;
position: relative;}
.code-layer .text-input{width:140px;margin-left:0;}
.code-layer .reg-tips{margin-left:0;width:100%;}
.code-layer .pic-code{position:absolute;left:175px;top:2px;cursor:pointer;width:91px;height:39px;}
.code-layer .pic-code-text{position:absolute;left:280px;top:6px;color:#999;cursor:pointer;line-height:1.3;}
</style>
</head>
<body>
<div class="page-wrapper h-100p">
	<!--/top--->
	<div class="top-div h-50p" >
		<div class="top-header" >
			<p class="title"><img src="${ctx }/static/images/v3/logo.png" alt="优蓝网_logo" /><span >|</span>注册</p>
			<p class="hot-line" ><i></i>快速求职热线：4008-777-816</p>
			<div class="clear"></div>
		</div>
	</div>
    <!--注册-->
    <div class="login-form regist-form" >
		<div class="login-l">
		 <form id="from_user" action="${ctx}/register" method="post">
	         <input type="hidden" name="clientId" id="hidden_fingerprint_id" />
	        <div class="form-each mt53">
	        	<input type="text" class="form-each-input" placeholder="请输入正确的11位手机号" id="txt_reg_user_loginname" name="loginname" maxlength="11"/>
	        	<p class="tips hide">${err_loginname }</p>
	        </div>
	        <div class="form-each form-code">
	        	<input type="text" class="form-each-input" id="txt_reg_vali_user" name="thekey" maxlength="4" placeholder="请输入验证码"/>
	        	<span class="send-span" onclick="sendCode()">获取验证码</span>
	        	<p class="tips hide">${err_valiCode }</p>
	        </div>
	        <div class="reg-tips hide  div-voice">一直收不到短信？点击发送<a href="javascript:;" class="a-blue send-voice" onclick="sendVoice()">语音验证码</a></div>
	        <div class="form-each">
	        	<input type="password" id="txt_reg_user_password" name="password" maxlength="18" class="form-each-input" placeholder="请输入密码"/>
	        	<p class="tips hide">${err_password }</p>
	        </div>
	        <div class="form-each"><input type="button" id="btn_reg_user" value="注册" class="btn-g bdr0 btn-green"/></div>
	        <div class="form-each"><p  class="c999">点击注册表示您已阅读并同意<a href="${ctx }/about/agreement" class="black-link">《优蓝网用户协议》</a></p></div>
	        <div class="form-each"><p class="sign-now">或<a href="${ctx }/login" class="blue">立即登录</a></p></div>
		    <div class="clear"></div>
	    </form>
	    </div>
	    <div class="login-m" style="top: 49.5%;">or</div>
	    <div class="login-r">
	    	<p class="f24 a-blue"><strong>下载优蓝APP</strong></p>
	    	<p>手机报名&nbsp;&nbsp;入职速度快50%</p>
	    	<div class="er-code">
	    		<img src="${ctx }/static/images/data/img-code2.png" />
	    		<p>扫一扫即刻体验</p>
	    	</div>
	    </div>
	</div>
</div>
<div class="footer-sty">沪ICP备14033709号-1&nbsp;&nbsp;&nbsp;Copyright © 2013-2016 youlanw.com All Rights Reserved</div>
<!--发送短信验证码-->
<div class="code-layer hide">
	<div class="title-box">
        <div class="inner"><span class="title">发送短信验证码</span><span class="close">&times;</span></div>
    </div>
    <div class="reg-div">
        <div class="input">
            <input class="text-input tip-text form-each-input" id="input_reg_vali_code"   type="text" maxlength="3" pattern="\d+" placeholder="请输入右边的计算答案"/>
            <img src="${ctx}/verification/reg.jpg?" class="pic-code"  onclick="this.src=this.src+Math.random()" alt="验证码" style="width: 90px;height: 40px;margin-left: 5px; "/>
            <span class="pic-code-text">
                  <p>看不清</p>
                  <p>换一张</p>
            </span>
        </div>
        <div class="reg-tips error error_msg_valicodeu"></div>
        <label class="btn-g-out mt10"><input type="button" value="确定" class="btn-g w160 btn_reg_vali_code"/></label>
    </div>
</div>
<!--/发送短信验证码--> 
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script> 
<script src="${ctx}/static/js/layer.min.js"></script>
<script src="${ctx}/static/js/fingerprint.js"></script>
 <script>
   $(function(){
   	var clientId = new Fingerprint().get();
	$("#hidden_fingerprint_id").val(clientId);
   	//更换验证码
   	$('.pic-code-text').click(function(){
   		$(this).prev().click();
   	})
   	
	$("#txt_reg_user_loginname").blur(function(){
		if(!/^1[3-9]\d{9}/.test($.trim($(this).val()))){
			$('.tips').hide();
			$(this).parent().find('.tips').show();
			$(this).parent().find('.tips').html("请输入正确位手机号码！");
		}else{
			$(this).parent().find('.tips').hide();
		}
	});
	$("#txt_reg_vali_user").blur(function(){
		if($.trim($(this).val()).length<1){
			$('.tips').hide();
			$(this).parent().find('.tips').show();
			$(this).parent().find('.tips').html("请输入验证码！");
		}else{
			$(this).parent().find('.tips').hide();
		}
	});
	$("#txt_reg_user_password").blur(function(){
		if(!/^[a-zA-Z0-9]{6,18}$/.test($(this).val())){
				$('.tips').hide();
				$(this).parent().find('.tips').show();
				$(this).parent().find('.tips').html("请输入密码6~18位字母、数字！");
		}else{
			$(this).parent().find('.tips').hide();
		}
	});
	//个人用户注册
	$("#btn_reg_user").click(function(){
		if(userValidate()){
			$("#btn_reg_user").unbind();
			$("#from_user").submit();
		}
	});
	//输入验证码
	$(".btn_reg_vali_code").click(function(){
		layer.closeAll();
		valiCode();
	});
	
});

   function valiCode(){
	   var value = $("#input_reg_vali_code").val();
		$.post("${ctx}/verification/valiCode",{"mobile":$("#txt_reg_user_loginname").val(),"type":"reg","value":value}, function(result){
			if(result == "success"){
				userGainkey('');
				$(".error_msg_valicodeu").html("");
			}else{
				$(".error_msg_valicodeu").html("验证码计算不正确");
			}
		});
   }
   
   function userValidate(){
	var m = $.trim($("#txt_reg_user_loginname").val());
	var v = $.trim($("#txt_reg_vali_user").val());
	var p = $.trim($("#txt_reg_user_password").val());
	$('.tips').hide();
	if(!/^1[3-9]\d{9}/.test(m)){
		$("#txt_reg_user_loginname").focus();
		$("#txt_reg_user_loginname").parent().find('.tips').show();
		$("#txt_reg_user_loginname").parent().find('.tips').html("请输入正确位手机号码！");
		return false;
	}else if (v.length < 1) {
		$("#txt_reg_vali_user").focus();
		$("#txt_reg_vali_user").parent().find('.tips').show();
		$("#txt_reg_vali_user").parent().find('.tips').html("请输入验证码！");
		return false;
	}else if(!/^[a-zA-Z0-9]{6,18}$/.test(p)){
		$("#txt_reg_user_password").focus();
		$("#txt_reg_user_password").parent().find('.tips').show();
		$("#txt_reg_user_password").parent().find('.tips').html("请输入密码6~18位字母、数字！");
		return false;
	}
	return true;
}
//发送短信验证码
function sendCode(){
		var m_reg=/^1[3-9][0-9]{9}$/i;
   		var mobile = $("#txt_reg_user_loginname").val();
   		$('.tips').hide();
   		if(m_reg.test(mobile)){
   			$.post("${ctx}/signup/isactivate",{mobile:mobile},function(data){
   				if(data=="failure"){
   				 $.layer({
   		             type: 1,
   		             title: false,
   		             closeBtn:false,
   		             shadeClose: true,
   		             area : ['360px' , '230px'],
   		              page : {dom : '.code-layer'}
   		             });
   				}else {
   					$("#txt_reg_user_loginname").parent().find('.tips').show();
   					$("#txt_reg_user_loginname").parent().find('.tips').html("手机号码已注册，请登录！");
   				}
   			});
   		}else{
   			$("#txt_reg_user_loginname").parent().find('.tips').show();
			$("#txt_reg_user_loginname").parent().find('.tips').html("您输入的手机号码格式不正确！");
   		}
        //点击关闭按钮 关闭弹出层
        $('.code-layer .close').click(function(){
            layer.closeAll();
        })
}
//发送语音验证码
function sendVoice(){
	var m_reg=/^1[3-9][0-9]{9}$/i;
	var mobile = $("#txt_reg_user_loginname").val();
	$('.tips').hide();
	if(m_reg.test(mobile)){
		$.post("${ctx}/signup/isactivate",{mobile:mobile},function(data){
			if(data=="failure"){
				userGainkey('voice');
			}else {
				$("#txt_reg_user_loginname").parent().find('.tips').show();
				$("#txt_reg_user_loginname").parent().find('.tips').html("手机号码已注册，请登录！");
			}
		});
	}else{
		$('.tips').hide();
		$("#txt_reg_user_loginname").parent().find('.tips').show();
		$("#txt_reg_user_loginname").parent().find('.tips').html("您输入的手机号码格式不正确！");
	}
}  
//发送验证码
function userGainkey(type){
   		var m_reg=/^1[3-9][0-9]{9}$/i;
   		var mobile = $("#txt_reg_user_loginname").val();
   		$('.tips').hide();
   		if(m_reg.test(mobile)){
   			$('.send-voice').attr("onclick", "");
   			$(".send-span").attr("onclick", "");
   			$(".div-voice").show();
   			$(".send-span").addClass("send-disabled");
   			$.post("${ctx}/register/sendCode",{mobile:mobile,type:type,currentValue:$("#input_reg_vali_code").val()},function(data){
   				if(data=="success"){
   					$("#txt_reg_user_loginname").parent().find('.tips').hide();
   					var thenum = 60;
   					var timmer = setInterval(function(){
   						$(".send-span").html(thenum+'s后可以重新获取');
   						thenum--;
   						if(thenum==-1){
   							clearInterval(timmer);
   							$(".send-span").removeClass("send-disabled");
   							$('.send-voice').attr("onclick", "sendVoice()");
   				   			$(".send-span").attr("onclick", "sendCode()");
   							$(".send-span").html("获取手机验证码");
   						}
   					},1000);
   				}else if(data=="frozen"){
   					$("#txt_reg_user_loginname").parent().find('.tips').show();
   					$("#txt_reg_user_loginname").parent().find('.tips').html("该手机号今天收到的短信已达到上限！");
   					$(".div-voice").hide();
   					$('.send-voice').attr("onclick", "sendVoice()");
			   		$(".send-span").attr("onclick", "sendCode()");
   					$(".send-span").removeClass("send-disabled");
   				}else{
   					$("#txt_reg_user_loginname").parent().find('.tips').show();
   					$("#txt_reg_user_loginname").parent().find('.tips').html("验证码发送失败请您输入的检查手机号！");
   					$(".div-voice").hide();
   					$('.send-voice').attr("onclick", "sendVoice()");
			   		$(".send-span").attr("onclick", "sendCode()");
   					$(".send-span").removeClass("send-disabled");
   				}
   			});
   		}
   		else{
   			$("#txt_reg_user_loginname").parent().find('.tips').show();
			$("#txt_reg_user_loginname").parent().find('.tips').html("您输入的手机号码格式不正确！");
   		}
   	}
   </script>
</body>
</html>