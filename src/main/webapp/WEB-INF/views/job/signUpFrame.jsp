<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="${ctx}/static/css/common.css" rel="stylesheet"
	type="text/css" />
<title></title>
</head>
<body>
	<div class="signup-layer">
		<!--报名表单-->
		<div id="divSignup">
			<form id="signup_from" onsubmit="return false">
				<ul class="job">
					<li class="tips red" id="err_msg">
						<!-- 这里是验证提示信息 -->
					</li>
					<input type="hidden" name="jobid" value="${job.id}" />
					<li class="pic"><a
						href="${ctx}/zhaopin_${job.id}.html" 
						target="_blank">
						<c:choose>
							<c:when test="${empty job.thumbnialImage.imgpath}">
								<img src="${cdc:getImageByJobType320(job.jobType)}"/>
							</c:when>
							<c:otherwise>
								<img src="${cdc:getImagePath320(job.thumbnialImage.imgpath)}" alt="${job.title }" />
							</c:otherwise>
						</c:choose>
						</a></li>
					<li class="title"><a
						href="${ctx}/zhaopin_${job.id}.html" 
						target="_blank">${job.title}</a></li>
					<li class="salary">￥${job.jobDetail.salaryfrom }<c:if test="${not empty  job.jobDetail.salaryto}">-${job.jobDetail.salaryto }元/月</c:if>
		        <c:if test="${empty  job.jobDetail.salaryto}">起</c:if></li>
					<li class="area">上班地址：${job.address }</li>
				</ul>
				 <div class="clear"></div>
				<div class="hr-dot-g fl full-width mt20"></div>
				 <p class="orange text-center">本岗位已通过严格审核和认证，请放心报名</p>
				<div class="form-g-medium fl">
					<div class="two-inline">
					<!--姓名-->
					<div class="form-each">
						<span class="form-each-name">姓名：</span><input type="text"
							class="form-each-input" name="name" id="input_name"
							value="${resumeBase.name }" />
					</div>
					<!--/姓名-->
					<!--姓名-->
					<div class="form-each">
						<span class="form-each-name">手机：</span><input type="text"
							class="form-each-input" name="mobile" id="input_phone"
							maxlength="11" value="${resumeBase.mobile }" />
					</div>
					<!--/姓名-->
					</div>
					<p class="tip" id="tipMobile">
						<!--请输入正确的手机号-->
					</p>
					<!--学历-->
					<div class="form-each">
						<span class="form-each-name">性别：</span>
						<div class="select-g select-g-2">
							<div class="select-g-title">
								<c:if test="${empty resumeBase.gender}">
									<span>您的性别</span>
								</c:if>
								<c:if test="${resumeBase.gender==1}"><span>男</span></c:if>
								<c:if test="${resumeBase.gender==2}"><span>女</span></c:if>
								<i></i>
							</div>
							<div class="select-g-options">
							<ul>
								<li>男</li>
								<li>女</li>
							</ul>
							</div>
							<select class="original-select" name="gender">
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</div>
					</div>
					<p class="tip" id="tipMobile">
						<!---->
					</p>
					<!--/性别-->
					
					<!--学历-->
					<div class="form-each">
						<span class="form-each-name">学历：</span>
						<div class="select-g select-g-3">
							<div class="select-g-title">
								<c:if test="${empty resumeBase.education}">
									<span>您的学历</span>
								</c:if>
								<c:if test="${not empty resumeBase.education}">
									<span> ${cdc:converDicEducation(resumeBase.education) }</span>
								</c:if>
								<i></i>
							</div>
							<div class="select-g-options">
							<ul>
								<c:forEach items="${educationList }" var="education">
									<li>${education.name }</li>
								</c:forEach>
							</ul>
							</div>
							<select class="original-select" name="education">
								<c:forEach items="${educationList }" var="education">
									<option value="${education.code }"
										<c:if test="${resumeBase.education == education.code}">selected="selected"</c:if>>${education.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<p class="tip" id="tipMobile"></p>
					<!--/学历-->
					<%--   <!--出生日期-->														
            <div class="form-each"><span class="form-each-name">出生年月：</span><input name="age" id="input_birthday" maxlength="2" min="17" max="80" type="number" class="form-each-input" value="${resumeBase.age }"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></div>
            <p class="tip"><!----></p>
            <!--/出生日期--> --%>
					<div class="form-btn">
						<input type="button" value="立即报名" class="btn btn-large"
							id="btn_signup" />
					</div>
					<div class="clear"></div>
					<p class="mt10 c999 text-center">郑重承诺：优蓝网不会直接或间接向求职者收取任何费用！</p>
				</div>
			</form>
		</div>
		<!--/报名表单-->


		<!--报名成功-->
		<div id="divSuccess" class="success hide">
			<!--提示信息-->
			<div class="success-tip" id="divSingUpSuccess">
				<i class="icon-success2"></i>
				<p class="text1">恭喜您，报名成功！</p>
				<p>
					您还可以<a href="${ctx}/my/signupRecord" class="a-blue" target="_blank">查看报名记录</a>
				</p>
			</div>
			<!--/提示信息-->
			<div class="hr-g mt20" id="divSingUpFailure"></div>
			<!---->
			<div class="leadto" id="divLogin">
				<p class="f13">您已经是优蓝网会员，登录后可以查看本次报名情况</p>
				<a href="javascript:void(0);" id="a_login" class="btn btn-medium">立即登录</a>
			</div>
			<!---->
			<!--可能感兴趣的岗位-->
			<div class="job-list" id="divJobs">
				<h2 class="column-title">
					您可能感兴趣的岗位<a href="${ctx}/zhaopin/" class="view-more"
						target="_blank">更多岗位</a>
				</h2>
				<div class="hr-g"></div>
				<div id="divInterestedJob" class="job-recommend-list img-hover height-auto">
				</div>
			</div>

			<!--/可能感兴趣的岗位-->

			<!--激活账户-->
			<div class="activate fl hide" id="divActivate">
				<p class="f13 text-center">
					<span class="icon-warn relative"><i></i></span>您已自动成为优蓝网账户，是否立即激活账户？
				</p>
				<div class="hr-g"></div>
				<div class="form-g-medium fl">
					<!--手机号-->
					<div class="form-each">
						<span class="form-each-name">手机号：</span><input
							id="input_actmobile" type="text" class="form-each-input"
							readonly="readonly" />
					</div>
					<p class="tip">
						<!-- 请输入正确的手机号 -->
					</p>
					<!--/手机号-->
					<!--验证码-->
					<div class="form-each form-each-code">
						<span class="form-each-name">验证码：</span><input id="input_thekey"
							type="text" class="form-each-input" />
						<!--<span class="btn">免费获取验证码</span>-->
						<span id="span_gain_val_key" class="btn" onclick="gainkey()">免费获取验证码</span>
					</div>
					<p class="tip" id="p_thekey"></p>
					<!--/验证码-->
					<!--设置密码-->
					<div class="form-each">
						<span class="form-each-name">设置密码：</span><input type="password"
							id="input_password" class="form-each-input" />
					</div>
					<p class="tip" id="p_password"></p>
					<!--/设置密码-->
					<div class="form-btn">
						<input type="button" id="btn_activate" value="立即激活"
							class="btn btn-large" />
					</div>
					<div class="clear"></div>
					<p class="mt10 c999 text-center">
						<a href="javascript:void(0);" id="a_nothanks">暂时不用，谢谢</a>
					</p>
				</div>
			</div>
			<!--/激活账户-->
		</div>
		<!--/报名成功-->
	</div>

	<script type="text/javascript"
		src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/layer.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<script src="${ctx}/static/js/layer.min.js"></script>
	<script type="text/javascript">
		var m_reg = /^1[3-9][0-9]{9}$/i;
		var name_reg = /((^[\u4E00-\u9FA5]{2,12}$)|(^[a-zA-Z]{4,24}$))/;
		var passwd_reg = /^.{6,16}$/i;
		$(function() {
			//点击我要报名后关闭弹出层的方法
			var index = parent.layer.getFrameIndex(window.name);//获取当前窗体索引

			$('#btn_signup').click(function() {
				var name = $.trim($("#input_name").val());
				var mobile = $.trim($("#input_phone").val());
				if (name.length == 0 || name.length > 12) {
					$('#tipMobile').html('请输入您的姓名');
					$("#input_name").focus();
				} else if (!name_reg.test(name)) {
					$('#tipMobile').html('姓名为2-12位的汉字或3-24位的英文');
					$("#input_name").focus();
				} else if (!m_reg.test(mobile)) {
					$('#tipMobile').html('请输入正确的手机号码');
					$("#input_phone").focus();
				} else {
					$("#input_actmobile").val(mobile);
					$("#btn_signup").unbind();
					$.post("${ctx}/signup/job", $("#signup_from").serialize(), function(result) {
						if (result == "success") {//报名成功
							$.post("${ctx}/signup/isactivate", {
								mobile : mobile
							}, function(data) {//判断手机是否有效
								if (data == "notInit") {//未激活，引导激活
									$("#divSignup").hide();
									$("#divSuccess").show();
									$("#divLogin").hide();
									$("#divJobs").hide();
									$("#divActivate").show();
								} else if (data == "success") {//存在账号
									$.post("${ctx}/login/veryfy", function(data) {//判断是否登录
										if (data == "unlogin") {//未登录，引导登录
											$("#divSignup").hide();
											$("#divSuccess").show();
											$("#divLogin").show();
											getInterestedJob();
											$("#divJobs").show();
											$("#divActivate").hide();

										} else {//已登录
											$("#divSignup").hide();
											$("#divSuccess").show();
											$("#divLogin").hide();
											getInterestedJob();
											$("#divJobs").show();
											$("#divActivate").hide();
										}
									});
								}
							});
						} else if (result == "failure") {//有未处理的报名信息
							alert("您已经投递过该岗位，我们会尽快处理，请您耐心等待！");
							parent.layer.close(index);
						} else {//系统忙碌，请稍后再试！							
							alert("系统忙碌，请您稍后再试！");
							parent.layer.close(index);
						}
					});
				}
			});
			function getInterestedJob(){
				$.get("${ctx}/job/findSimilarJob",{jobId:"${job.id}"},function(result){
					var divs = [];
		    		$.each(result,function(i,j){
		    			divs.push('<ul class="recommned-each">');
		    			divs.push('  <li class="pic"><a');
		    			divs.push('    href="${ctx }/zhaopin_'+j.id+'.html" target="_blank">');
		    			if(j.thumbnialImage.imgpath==null){
		    				divs.push('      <img src="'+getImageByJobType(j.jobType)+'"/>');
		    			}else{
		    				if(j.thumbnialImage.imgpath.indexOf("http://")==-1){
			    				divs.push('      <img src="'+IMAGE_FILE_URL+'360/'+j.thumbnialImage.imgpath+'" />');
		    				}else{
			    				divs.push('      <img src="'+j.thumbnialImage.imgpath+'" />');
		    				}
		    			}
		    			divs.push('    </a></li>');
		    			divs.push('  <li class="salary">'+j.totalsalary+'/月');
		    			divs.push('  </li>');
		    			divs.push('  <li class="title"><a  href="/zhaopin_'+j.id+'.html" target="_blank">'+j.title+'</a></li>');
		    			/* divs.push('  <li class="title"><a  href="/zhaopin_'+j.id+'.html" target="_blank">+'j.title.replace(wd,'<span class="red">' + wd +'<span>'+)+'</a></li>'); */
		    			divs.push('</ul>');
		    		});
		    		divs.push('<div class="clear"></div>');
		    		$("#divInterestedJob").append(divs.join(''));
				})
			}
			
			function getImageByJobType(jobType){
				var image=null;
				jobType : jobType == undefined?"":jobType
				$.ajax({ url: "${ctx}/search/getImageByJobType", 
					type: "post",
					data: {jobType:jobType},
					async: false,
					success: function(data){
						image = data;
			     }});
				return IMAGE_FILE_URL+image;
			}
			//登录
			$('#a_login').click(function() {
				parent.openLoginBox();
				parent.layer.close(index);
			})
			//不激活谢谢！
			$('#a_nothanks').click(function() {
				parent.layer.close(index);
			})
			//激活
			$('#btn_activate').click(function() {//激活账号
				var mobile = $("#input_actmobile").val();
				var password = $("#input_password").val();
				var thekey = $("#input_thekey").val();
				if (thekey.length == 0) {
					$("#p_thekey").html("请填写验证码！");
					$("#input_thekey").focus();
				}
				if (!passwd_reg.test(password)) {
					$("#p_password").html("密码必须是6-16位字符！");
					$("#input_password").focus();
				} else {
					$.post("${ctx}/signup/verifykey", {
						mobile : mobile,
						password : password,
						thekey : thekey
					}, function(result) {
						if (result == "success") {
							alert("恭喜您已经正式成为优蓝网尊贵的会员！");
							parent.layer.close(index); //执行关闭
						} else if (result == "failure") {//验证码出错
							$("#p_thekey").html("验证码出错！");
						} else {
							$("#p_thekey").html("error");
						}
					});
				}
			});

		});

		function gainkey() {
			var mobile = $("#input_phone").val();
			if (m_reg.test(mobile)) {
				$("#span_gain_val_key").attr("onclick", "");
				$.post("${ctx}/signup/valmobile", {
					mobile : mobile
				}, function(data) {
					if (data == "success") {
						$("#span_gain_val_key").addClass("btn-disabled");
						var thenum = 60;
						var timmer = setInterval(function() {
							$("#span_gain_val_key").html(thenum + 's后可以重新获取');
							thenum--;
							if (thenum == -1) {
								clearInterval(timmer);
								$("#span_gain_val_key").removeClass("btn-disabled");
								$("#span_gain_val_key").html("获取手机验证码");
								$("#span_gain_val_key").attr("onclick", "gainkey()");
							}
						}, 1000);
					} else if (data == "failure") {
						alert("该手机号码已经注册");
					} else {
						alert("短信发送失败！");
					}
				});
			} else {
				alert("您输入的手机号码格式不正确");
			}
		}
	</script>
</body>
</html>
