<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>30秒找工作_优蓝网</title>
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
	<div class="nav-g">
		<a href="${ctx}/">首页</a>
		&gt;
		<a href="javascript:void(0);" class="red">30秒找工作</a>
	</div>
	<!--快速报名-->
	<div class="w990 mt20">
		<!--left-->
		<div class="box-g-l-wrapper">
			<div class="box-g-l bg-white fl">
				<div id="div_msg" class="form-tips red">
					<!--这里是验证提示信息-->
				</div>
				<!--30秒快速报名-->
				<!--信息填写-->
				<div id="div_signup" style="min-height: 376px;">
					<div class="form-each">
						<span class="form-each-name">姓名：</span><input id="input_name" type="text" name="name" class="form-each-input" value="${resumeBase.name }" maxlength="12" />
					</div>
					<!--性别-->
					<div class="form-each">

						<span class="form-each-name">性别：</span>
						<div class="radio-g <c:if test="${resumeBase.gender != 2}"> radio-g-current</c:if>">
							<input type="radio" name="gender" value="1" <c:if test="${resumeBase.gender != 2}">checked="checked"</c:if> class="radio-g-input" /><i class="radio-g-img"></i>
							<label class="radio-g-name">男</label>
						</div>
						<div class="radio-g <c:if test="${resumeBase.gender == 2}"> radio-g-current</c:if>">
							<input type="radio" name="gender" value="2" <c:if test="${resumeBase.gender == 2}">checked="checked"</c:if> class="radio-g-input" /><i class="radio-g-img"></i><label class="radio-g-name">女</label>
						</div>
					</div>
					<!--/性别-->
					<div class="form-each">
						<span class="form-each-name">手机号码：</span><input id="input_mobile" type="text" class="form-each-input" value="${resumeBase.mobile }" maxlength="11" />
					</div>
					<div class="form-each select-g-inline">
						<span class="form-each-name">要求：</span>
						<!--我的要求-->
						<!--在哪里上班-->
						<div class="select-g select-g-1 w150">
							<div class="select-g-title">
								<span data-default="1.在哪里上班">1.在哪里上班</span>
								<i></i>
							</div>
							<div class="select-g-options">
							<ul>
								<li>不限</li>
								<li>上海</li>
								<li>苏州</li>
								<li>杭州</li>
								<li>南京</li>
								<li>宁波</li>
								<li>厦门</li>
								<li>天津</li>
								<li>武汉</li>
								<li>成都</li>
								<li>重庆</li>
							</ul>
							</div>
							<select class="original-select" id="select_city_index">
								<option>工作地不限</option>
								<option value="上海">上海</option>
								<option value="苏州">苏州</option>
								<option value="杭州">杭州</option>
								<option value="南京">南京</option>
								<option value="宁波">宁波</option>
								<option value="厦门">厦门</option>
								<option value="天津">天津</option>
								<option value="武汉">武汉</option>
								<option value="成都">成都</option>
								<option value="重庆">重庆</option>
							</select>
						</div>
						<!--/在哪里上班-->

						<!--做什么事情-->
						<div class="select-g select-g-2 w150 ml10">
							<div class="select-g-title">
								<span data-default="2.做什么事情">2.做什么事情</span>
								<i></i>
							</div>
							<div class="select-g-options">
							<ul>
								<li>不限</li>
								<li>普工</li>
								<li>技工</li>
								<li>快递</li>
								<li>保安</li>
								<li>司机</li>
								<li>物流仓管</li>
								<li>装卸工</li>
								<li>人事行政</li>
								<li>销售</li>
								<li>店员</li>
								<li>服务员</li>
								<li>客服</li>
							</ul>
							</div>
							<select class="original-select" id="select_postion_index">
								<option>职位不限</option>
								<option value="普工">普工</option>
								<option value="技工">技工</option>
								<option value="快递">快递</option>
								<option value="保安">保安</option>
								<option value="司机">司机</option>
								<option value="物流仓管">物流仓管</option>
								<option value="装卸工">装卸工</option>
								<option value="人事行政">人事行政</option>
								<option value="销售">销售</option>
								<option value="店员">店员</option>
								<option value="服务员">服务员</option>
								<option value="客服">客服</option>
							</select>
						</div>
						<!--/做什么事情-->

						<!--能拿多少钱-->
						<div class="select-g select-g-3 w150 ml10">
							<div class="select-g-title">
								<span data-default="3.能拿多少钱">3.能拿多少钱</span>
								<i></i>
							</div>
							<div class="select-g-options">
							<ul>
								<li>不限</li>
								<li>小于2000元/月</li>
								<li>2000-3000元/月</li>
								<li>3000-4000元/月</li>
								<li>4000-5000元/月</li>
								<li>5000-6000元/月</li>
								<li>6000-7000元/月</li>
								<li>7000-8000元/月</li>
								<li>大于8000元/月</li>
							</ul>
							</div>
							<select class="original-select" id="select_salary_index">
								<option>薪资不限</option>
								<option value="小于2000元/月">小于2000元/月</option>
								<option value="2000-3000元/月">2000-3000元/月</option>
								<option value="3000-4000元/月">3000-4000元/月</option>
								<option value="4000-5000元/月">4000-5000元/月</option>
								<option value="5000-6000元/月">5000-6000元/月</option>
								<option value="6000-7000元/月">6000-7000元/月</option>
								<option value="7000-8000元/月">7000-8000元/月</option>
								<option value="大于8000元/月">大于8000元/月</option>
							</select>
						</div>
						<!--/能拿多少钱-->
						<!--/我的要求-->
					</div>
					<div class="form-btn"><label class="btn-g-out"><input  id="btn_save" type="button" value="帮我介绍工作" class="btn-g w150"/></label></div>
					<div class="clear"></div>
					<p class="text-center f12 c999 mt15">郑重承诺：优蓝网不会直接或间接向求职者收取任何费用！</p>
				</div>
				<!--/信息填写-->
				<!--短信验证-->
				<div id="div_captcha" class="hide" style="min-height: 346px;">
					<p class="text-center f13 c999 mt30" style="padding-top: 50px;">欢迎初次体验优蓝网，请在下方输入您收到的验证码</p>
					<div class="form-each form-each-code">
						<span class="form-each-name">验证码：</span>
						<input id="input_thekey" type="text" class="form-each-input" />
						<span id="span_gain_val_key" class="btn btn-grey" onclick="sendCode()">免费获取验证码</span>
						<!-- <span class="btn btn-disabled">60s后可重新获取</span> -->
					</div>
					<div class="form-btn">
						<input type="button" value="确定" class="btn btn-large" />
					</div>
					<div class="clear"></div>
				</div> 
				<!--/短信验证-->
				<!--报名成功-->
				<div id="div_success" class="hide">
					<p class="text-center h50 lh50 f28 red mt30">
						<span class="icon-success"><i></i></span><span id="span_success">恭喜您报名成功！</span>
					</p>
					<div id="div_unlogin" style="margin-left: 270px; line-height: 2.2;">
						<p class="c999" style="margin: 20px 0 0 -80px;">您已经是优蓝网会员，登录后可以查看本次报名情况</p>
						<!-- <a href="javascript:void(0);" id="a_login" class="btn btn-medium" style="margin: 20px 0 0 -100px;">立即登录</a> -->
					</div>
					<div id="div_login" style="margin-left: 270px; line-height: 2.2;">
						<p id="p_message" class="c999">客服人员会在24小时内与您联系</p>
						<p class="f13">
							<a href="${ctx}/" class="a-blue">返回首页</a>
							<a href="${ctx}/my/apply" class="a-blue ml20">查看报名记录</a>
						</p>
					</div>
				</div>
				<!--/报名成功-->
				<!--/30秒快速报名-->
			</div>

			<!--好工作推荐-->
			<div id="div_jobs" class="box-g-l bg-white fl mt10 hide">
				<h2 class="column-title">您可以看看以下职位</h2>
				<div class="job-recommend-list job-recommend-list2 img-hover">

					<c:forEach begin="0" end="2" var="i">
						<c:set var="recommendjob" value="${recommendJobList[i]}" />
						<ul class="recommned-each">
							<a href="${ctx}/zhaopin_${recommendjob.jobId}.html"><li class="pic">
							<c:choose>
								<c:when test="${empty recommendjob.imgPath}">
									<img src="${cdc:getImageByJobType320(recommendjob.jobType)}" alt="" />
								</c:when>
								<c:otherwise>
									<img src="${cdc:getImagePath320(recommendjob.imgPath) }" alt="" />
								</c:otherwise>
							</c:choose>
							</li></a>
							<li class="opacity-bg"></li>
							<a href="${ctx}/qiye_${recommendjob.companyId}.html"><li class="opacity-text">
							${recommendjob.companyName}</li></a>
							<li class="salary">￥${recommendjob.salaryFrom }<c:if test="${not empty  recommendjob.salaryTo}">-${recommendjob.salaryTo }元/月</c:if>
		        			<c:if test="${empty  recommendjob.salaryTo}">起</c:if></li>
							<li class="title">
								<a href="${ctx}/zhaopin_${recommendjob.jobId}.html">${recommendjob.title }</a>
							</li>
						</ul>
					</c:forEach>
					<div class="clear"></div>
				</div>
			</div>
			<!--/好工作推荐-->
		</div>
		<!--/left-->
		<!--right-->
		<div class="box-g-r-wrapper">
			<!--快速找工作-->
			<div class="box-g-r quick-job bg-white fr" style="height: 200px;">
				<div class="text-g-2 mt10">
					<p>留下您的电话号码</p>
					<p>优蓝网强大专业的打工顾问团队</p>
					<p>全心全意为您服务</p>
					<p>
						已成功服务<label class="red">${applyCount}人</label>
					</p>
				</div>
				<ul class="feature">
					<li class="first">态度好</li>
					<li class="second">有保障</li>
					<li class="third">速度快</li>
				</ul>
			</div>
			<!--快速找工作-->
			<!--常见问题-->
			<div class="job-help box-g-r fl bg-white fr mt10 height-auto">
				<h2 class="column-title2">常见问题</h2>
				<ul>
					<li>
						<a href="${ctx }/about/common.html" target="_blank">1. 优蓝网的服务过程是怎样的？</a>
					</li>
					<li>
						<a href="${ctx }/about/common.html" target="_blank">2. 收不到验证短信怎么办？</a>
					</li>
					<li>
						<a href="${ctx }/about/common.html" target="_blank">3. 优蓝网找工作要花钱吗？</a>
					</li>
				</ul>
			</div>
			<!--/常见问题-->
		</div>
		<!--/right-->
		<div class="clear"></div>
	</div>
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
	<!--/快速报名-->
	<script type="text/javascript">
	var m_reg = /^1[3-9][0-9]{9}$/i;
	var name_reg = /((^[\u4E00-\u9FA5]{2,12}$)|(^[a-zA-Z]{3,24}$))/;
	$(function(){
		$("#btn_save").click(function() {
			var gender = $("input[name='gender']:checked").val();
			var name = $.trim($("#input_name").val());
			var mobile = $.trim($("#input_mobile").val());
			if (name.length == 0 || name.length > 12) {
				$('#div_msg').html('请输入您的姓名');
				$("#input_name").focus();
			} else if (!name_reg.test(name)) {
				$('#div_msg').html('姓名为2-12位的汉字或3-24位的英文');
				$("#input_name").focus();
			} else if (!m_reg.test(mobile)) {
				$('#div_msg').html('请输入正确的手机号码');
				$("#input_mobile").focus();
			} else {
				$.post("${ctx}/signup/isactivate",{mobile : mobile}, function(data) {//判断是否存在优蓝网用户
					if (data == "failure") {//不存在，需要验证手机号
						$("#div_signup").hide();
						$("#div_captcha").show();
						$("#div_unlogin").hide();
						$("#div_login").hide();
						$("#div_jobs").hide();
						$('#div_msg').html('');
						sendCode();
					} else if (data == "success") {//存在
						save();
					}
				});
				
			}
		});
		/* 输入验证码 */
		$(".btn-large").click(function(){
			save();
		})
		
		//输入验证码
		$(".btn_reg_vali_code").click(function(){
			valiCode();
		});
	})
	
	function save() {
		var select_city = $("#select_city_index").val();
		var select_postion = $("#select_postion_index").val();
		var select_salary = $("#select_salary_index").val();
		var intention = select_city + "," + select_postion + "," + select_salary;
		var gender = $("input[name='gender']:checked").val();
		var name = $("#input_name").val();
		var mobile = $("#input_mobile").val();
		var thekey = $("#input_thekey").val();
		$("#btn_save").unbind();
		$.post("${ctx}/signup/fastSignUp", {
			"name" : name,
			"mobile" : mobile,
			"gender" : gender,
			"thekey" : thekey,
			"intention" : intention
		}, function(result) {
			if (result == "success") {
				$("#div_signup").hide();
				$("#div_captcha").hide();
				$("#div_unlogin").hide();
				$("#div_success").show();
				$("#div_login").show();
				$("#div_jobs").show();//显示成功页面
			} else {
				$('#div_msg').html('系统忙，请您稍后再试！');
			}
		});
	}

	function valiCode(){
	   var value = $("#input_reg_vali_code").val();
		$.post("${ctx}/verification/valiCode",{"mobile":$("#input_mobile").val(),"type":"reg","value":value}, function(result){
			if(result == "success"){
				layer.closeAll();
				gainkey();
				$(".error_msg_valicodeu").html("");
			}else{
				$(".error_msg_valicodeu").html("验证码计算不正确");
			}
		});
	}
		
	//发送短信验证码
	function sendCode(){
		 $.layer({
             type: 1,
             title: false,
             closeBtn:false,
             shadeClose: true,
             area : ['360px' , '230px'],
              page : {dom : '.code-layer'}
             });
	     //点击关闭按钮 关闭弹出层
	     $('.code-layer .close').click(function(){
	         layer.closeAll();
	     })
	}
	function gainkey() {
		var mobile = $("#input_mobile").val();
		$("#div_msg").html("");
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
