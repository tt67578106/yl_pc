<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="${ctx}/static/css/common.css" rel="stylesheet" type="text/css" />
<title></title>
</head>
<body>
<div class="signup-layer-wrapper">
	<ul class="signup-layer quick-signup-layer">
    	<li class="tips red" id="err_msg"><!-- 这里是验证提示信息 --></li>
    	<li class="tipimg"><img src="${ctx}/static/images/datas/tipimg.jpg"/></li>
        <li class="title red">您的工作要求：</li>
        <li class="require">
        	<p>1.${param.city}</p>
            <p>2.${param.postion}</p>
            <p>3.${param.salary}</p>
        </li>
        <form id="signup_from">
        <input type="hidden" name="intention" value="${param.city},${param.postion},${param.salary}"/>
        <li class="name"><i>姓名：</i><input type="text" class="input" name="name"/></li>
        <li class="tel"><i>手机号：</i><input type="text" class="input" name="mobile" id="input_phone" maxlength="11"/></li>
        <li class="code"><i>验证码：</i><input type="text" class="input input-code" name="thekey" maxlength="4"/></li>
        <li class="send-code" id="li_gain_val_key" onclick="gainkey()">获取手机验证码</li>
        <!--<li class="send-code send-code-disabled">获取手机验证码</li>-->
        <li class="signup"><input type="button" value="立即报名" class="btn btn-small"/></li>
        </form>
    </ul>
</div>
 <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
 <script type="text/javascript" src="${ctx}/static/js/layer.min.js"></script>
 <script type="text/javascript">
 	$(function(){
		//点击我要报名后关闭弹出层的方法
		var index = parent.layer.getFrameIndex(window.name);//获取当前窗体索引
		$('.btn').click(function(){
			$.post("${ctx}/signup/job",$("#signup_from").serialize(),function(result){
				if(result=="success"){
					parent.layer.close(index); //执行关闭
				}
				else if(result=="failure"){//验证码出错
					$("#err_msg").html("验证码出错！");
				}
				else{
					$("#err_msg").html("error");
				}
			});
		});
		
		
	});
 	var m_reg=/^1[3-9][0-9]{9}$/i;
	function gainkey(){
		var mobile = $("#input_phone").val();
		if(m_reg.test(mobile)){
			$("#li_gain_val_key").attr("onclick", "");
			$.post("${ctx}/signup/valmobile",{mobile:mobile},function(data){
				if(data=="success"){
					$("#li_gain_val_key").addClass("send-code-disabled");
					var thenum = 60;
					var timmer = setInterval(function(){
						$("#li_gain_val_key").html(thenum+'s后可以重新获取');
						thenum--;
						if(thenum==-1){
							clearInterval(timmer);
							$("#li_gain_val_key").removeClass("send-code-disabled");
							$("#li_gain_val_key").html("获取手机验证码");
							$("#li_gain_val_key").attr("onclick", "gainkey()");
						}
					},1000);
				}
				else{
					alert("手机号码可能有问题！");
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
