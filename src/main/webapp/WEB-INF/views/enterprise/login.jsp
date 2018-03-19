<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业用户登录_优蓝网</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20150417"></script>
<script src="${ctx}/static/js/cookie.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/enterprise.css?v=20150505"/>
</head>
<body>
<div class="page-wrapper bg-grey">
<jsp:include page="/WEB-INF/layouts/enterpriseTop.jsp"></jsp:include>
    <!--登录-->
    <div class="ent-login-wrapper">
        <div class="box-out">
            <div class="ent-login">
          		<!--left-->
            	<div class="l fl">
                	<p class="text1">品牌定制，由你做主！</p>
                    <p class="text2">为您量身定做的功能，开创招聘极致体验时代！</p>
                    <ul class="icons">
                    	<li><i><i class="icon1"></i></i>求职商店</li>
                        <li><i><i class="icon2"></i></i>企业相册</li>
                        <li><i><i class="icon3"></i></i>优质招聘</li>
                        <li><i><i class="icon4"></i></i>企业认证</li>
                    </ul>
                </div>
                <!--/left-->
                  <!--right-->
                <div class="r fr ent-login-box tab-g" id="login">
                <!---->
                    <ul class="title"><li class="current first">企业登录</li><li>企业用户注册</li></ul>
                    <!--登录-->
                    <div class="content-each show">
                    	<div class="tips error-g">${msg}</div>
                    	  <form action="${ctx}/login" method="post" onsubmit="return cklg()">
                    	  <input type="hidden" name="role" value="enterprise"/>
                    	<div class="input-each"><input type="text" id="input_loginname" name="loginname" value="请输入用户名" maxlength="50"  class="tip-text input-g-2 error" onblur="return cklg()"/></div>
                        <div class="input-each"><input type="password" id="input_password" name="password" placeholder="请输入密码" maxlength="50"  class="tip-text input-g-2" onblur="return cklg()"/></div>
                        <div class="input-each code-wrapper"><input type="text" name="valiCode" id="input_valiCode" value="请输入验证码" class="tip-text input-g-2 code" /><img id="img_login_captcha" /></div>
                        <div class="rem"><label class="fl"><input name="rememberMe" type="checkbox"/>记住密码</label><a href="${ctx}/forgetpwd?role=enterprise" target="_blank" class="fr">忘记密码</a></div>
                        <input type="submit" value="登录" class="btn-g"/>
                        </form>
                    </div>
                    <!--/登录-->
                     <!--注册-->
                    <div class="content-each">
                    	<div class="tips error-g"></div>
                    	 <form action="${ctx}/register" method="post" onsubmit="return ckrg()">
                    	  <input type="hidden" name="role" value="enterprise"/>
                    	<div class="input-each"><input type="text" id="input_reg_loginname" name="loginname" value="请输入用户名" class="tip-text input-g-2" maxlength="50" onblur="return ckun()"/></div>
                        <div class="input-each"><input type="tel" id="input_reg_mobile" name="mobile" value="请输入手机号" class="tip-text input-g-2" maxlength="11" onblur="return ckrg()"/></div>
                        <div class="input-each code-wrapper"><input class="tip-text input-g-2 code" type="text" id="input_reg_vali" name="thekey" value="请输入验证码" style="width:120px;" onblur="return ckrg()">
                        <input type="button" value="发送验证码" id="btn_gain_vali_key" onclick="gainkey()" class="send-btn"/></div>
                        <div class="input-each"><input type="password" placeholder="请输入密码" id="input_reg_password" name="password" maxlength="50" class="tip-text input-g-2" onblur="return ckrg()"/></div>
                        <input type="submit" value="注册" class="btn-g"/>
                        </form>
                    </div>
                    <!--/注册-->
                    <!---->
                </div>
                <!--/right-->
                 <div class="clear"></div>
            </div>
        </div>
    </div>
    <!--/登录-->
    <!--企业用户-->
    <div class="ent-user-out">
        <div class="box-out">
            <div class="ent-user">
                <p class="text">以下知名企业，都已加入<label class="theme-color">优蓝网</label></p>
                <ul class="logos">
                    <li><table><tr><td><a href="${ctx }/qiye_28.html" target="_blank"><img src="${ctx }/static/images/datas/partner1.jpg" alt="京北方" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_69.html" target="_blank"><img src="${ctx }/static/images/datas/partner2.jpg" alt="九五太维" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_61.html" target="_blank"><img src="${ctx }/static/images/datas/partner3.jpg" alt="达运精密工业" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_52.html" target="_blank"><img src="${ctx }/static/images/datas/partner4.jpg" alt="神州租车" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_410.html" target="_blank"><img src="${ctx }/static/images/datas/partner5.jpg" alt="群创光电" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_110.html" target="_blank"><img src="${ctx }/static/images/datas/partner6.jpg" alt="苏州名硕" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_886.html" target="_blank"><img src="${ctx }/static/images/datas/partner7.jpg" alt="德尔福派克" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_55.html" target="_blank"><img src="${ctx }/static/images/datas/partner8.jpg" alt="华宝通讯" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_1114.html" target="_blank"><img src="${ctx }/static/images/datas/partner9.jpg" alt="新远洋" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_48.html" target="_blank"><img src="${ctx }/static/images/datas/partner10.jpg" alt="顺衡物流" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_122.html" target="_blank"><img src="${ctx }/static/images/datas/partner11.jpg" alt="康师傅" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_556.html" target="_blank"><img src="${ctx }/static/images/datas/partner12.jpg" alt="联想" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_106.html" target="_blank"><img src="${ctx }/static/images/datas/partner13.jpg" alt="名幸电子" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_71.html" target="_blank"><img src="${ctx }/static/images/datas/partner14.jpg" alt="中粮" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_116.html" target="_blank"><img src="${ctx }/static/images/datas/partner15.jpg" alt="仁宝电脑" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_58.html" target="_blank"><img src="${ctx }/static/images/datas/partner16.jpg" alt="旭硕科技" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_109.html" target="_blank"><img src="${ctx }/static/images/datas/partner17.jpg" alt="天马辉电子" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_101.html" target="_blank"><img src="${ctx }/static/images/datas/partner18.jpg" alt="巨腾电子" /></a></td></tr></table></li>
                    <li><table><tr><td><a href="${ctx }/qiye_2242.html" target="_blank"><img src="${ctx }/static/images/datas/partner19.jpg" alt="去哪儿" /></a></td></tr></table></li>
                </ul>
                <div class="clear"></div>
                <a href="#login" class="btn-g-2 block-center">立即加入</a>
            </div>
        </div>
    </div>
    <!--/企业用户-->
     <jsp:include page="/WEB-INF/layouts/footer.jsp"></jsp:include>
</div>
	<script>
		$(function() {
			$('.ent-user .logos li').eq(0).css({
				'margin-left' : '90px'
			});
			$('.ent-user .logos li').eq(13).css({
				'margin-left' : '90px'
			});
			$('.ent-user .logos li').eq(6).css({
				'margin-left' : 0
			});
			$("#img_login_captcha").attr("src", "${ctx}/api/util/captcha");
			$("#img_login_captcha").click(function() {
				this.src = this.src + "?v=" + Math.random();
			});
		});
		function gainkey() {
			var m_reg = /^1[3-9][0-9]{9}$/i;
			var mobile = $("#input_reg_mobile").val();
			if (m_reg.test(mobile)) {
				$("#btn_gain_vali_key").attr("onclick", "");
				$("#btn_gain_vali_key").addClass("disabled");
				$.post("${ctx}/register/valmobile", {
					mobile : mobile,
					role : "enterprise"
				}, function(data) {
					if (data == "success") {

						var thenum = 60;
						var timmer = setInterval(function() {
							$("#btn_gain_vali_key").val(thenum + 's后可以重新获取');
							thenum--;
							if (thenum == -1) {
								clearInterval(timmer);
								$("#btn_gain_vali_key").removeClass("disabled");
								$("#btn_gain_vali_key").html("获取手机验证码");
								$("#btn_gain_vali_key").attr("onclick", "gainkey()");
							}
						}, 1000);
					} else if (data == "failure") {
						$('.error-g').html("该手机号已被绑定，请更换！");
						$("#btn_gain_vali_key").attr("onclick", "gainkey()");
						$("#btn_gain_vali_key").removeClass("disabled");
					} else {
						$('.error-g').html("手机号码可能有问题！");
						$("#btn_gain_vali_key").attr("onclick", "gainkey()");
						$("#btn_gain_vali_key").removeClass("disabled");
					}
				});
			} else {
				$('.error-g').html("您输入的手机号码格式不正确");
			}
		}

		function ckun() {
			var loginname = $.trim($("#input_reg_loginname").val());
			if (/^[a-zA-Z]{1}[a-zA-Z0-9|-|_]{2,14}[a-zA-Z0-9]{1}$/.test(loginname)) {
				$.post("${ctx}/register/valloginname", {
					loginname : loginname
				}, function(data) {
					if (data == "success") {
						$('.error-g').html("");
						return true
					} else if (data == "failure") {
						$('.error-g').html("该用户名已被占用！");
						return false;
					}
				});
			} else {
				$('.error-g').html("用户名由字母开头，含数字下划线，长度为的4～16位！");
				return false;
			}
		}

		function ckrg() {
			var u = $.trim($("#input_reg_loginname").val());
			var m = $.trim($("#input_reg_mobile").val());
			var p = $.trim($("#input_reg_password").val());
			var v = $.trim($("#input_reg_vali").val());
			if (!/^[a-zA-Z]{1}[a-zA-Z0-9|-|_]{2,14}[a-zA-Z0-9]{1}$/.test(u)) {
				$('.error-g').html("用户名由字母开头，含数字下划线，长度为的4～16位！");
				return false;
			} else if (!/^1[3-9]\d{9}/.test(m)) {
				$('.error-g').html("请输入正确的手机号码");
				return false;
			} else if (v.length != 4 || v == "请输入验证码") {
				$('.error-g').html("请输入验证码");
				return false;
			} else if (p.length < 1 || p == "请输入密码") {
				$('.error-g').html("请输入密码");
				return false;
			}
		}
		function cklg() {
			var u = $.trim($("#input_loginname").val());
			var p = $.trim($("#input_password").val());
			var v = $.trim($("#input_valiCode").val());
			if (u.length < 1 || u == "请输入用户名") {
				$('.error-g').html("请输入用户名");
				return false;
			} else if (p.length < 1 || p == "请输入密码") {
				$('.error-g').html("请输入密码");
				return false;
			} else if (v.length < 1 || v == "请输入验证码") {
				$('.error-g').html("请输入验证码");
				return false;
			}
		}
	</script>
</body>
</html>