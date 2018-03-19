<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>优蓝网</title>
	<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="${ctx }/static/css/normalize.min.css" />
	<style>
		*{margin:0;padding:0}
		html{background:#fff;-webkit-text-size-adjust:100%;overflow-y:auto;color:#666}
		body,button,input,select,textarea{font-family:Microsoft YaHei,"微软雅黑",Tahoma,Arial,"\5b8b\4f53",sans-serif}
		body{height: 100%;min-width: 900px;}
		textarea{resize:none;overflow-y:auto}
		div,p{padding:0;margin:0}
		li,ul{list-style:none;padding:0;margin:0}
		a,a:hover{text-decoration:none}
		:-moz-placeholder{color:#0eacb3}
		::-moz-placeholder{color:#0eacb3}
		input:-ms-input-placeholder{color:#0eacb3}
		input::-webkit-input-placeholder{color:#0eacb3}
		.clearfix:after{content:".";display:block;height:0;clear:both;visibility:hidden}
		.mt30{margin-top:30px}
		.banner{height:400px;position:relative}
		.banner:before{background: url(${ctx}/static/images/LP/dot.png) no-repeat;position: absolute;bottom: -8%;z-index: 10;content: "";width: 330px;height: 200px;background-size: 240px;left: 38%;}
		.banner img{width:890px;position:absolute;right:-103px;height:auto;z-index: 20;}
		.footer,.part1{background-color:#0eacb3}
		.part0,.part2,.part4{background-color:#d2e9ef}
		.part0{background:#d2e9ef url(${ctx}/static/images/LP/banner-bar.png) repeat-x;width:100%;background-size:70%}
		.part3{background-color:#fff;padding:30px 30px 45px 30px}
		.part1{padding:0 0 45px 0;position:relative}
		.banner,.card,.feature,.form,.jobList{width:800px;margin:0 auto;box-sizing:border-box; -moz-box-sizing:border-box; -webkit-box-sizing:border-box}
		.card{background-color:#fff;color:#999;font-size:16px;padding:30px 50px;border-radius:13px;position:relative;z-index: 30;}
		.card p{line-height:1.6}
		.card:before{content:"";width:44px;height:44px;display:block;position:absolute;background-color:#0eacb3;left:-22px;top:50%;margin-top:-22px;border-radius:30px}
		.card:after{content:"";width:44px;height:44px;display:block;position:absolute;background-color:#0eacb3;right:-22px;top:50%;margin-top:-22px;border-radius:30px}
		.form{padding:20px 15px 30px 15px}
		.form .title,.jobList .title{color:#0eacb3;font-size:20px;text-align:center;margin:5px auto 25px auto;position:relative}
		.form .title:before,.jobList .title:before{content:"";display:inline-block;width:90px;height:1px;background-color:#0eacb3;position:absolute;left:180px;top:12px}
		.form .title:after,.jobList .title:after{content:"";display:inline-block;width:95px;height:1px;background-color:#0eacb3;position:absolute;right:180px;top:12px}
		.jobList .title:before{left:220px}
		.jobList .title:after{right:220px}
		.form .input-control{font-size:20px;margin-bottom:12px;width:100%;height:48px;box-sizing:border-box; -moz-box-sizing:border-box; -webkit-box-sizing:border-box;padding:10px 20px;border:none;box-shadow:none;border-radius:4px}
		.form .input-code .input-control{width:600px}
		.form .input-code .btn-code{cursor: pointer;vertical-align:2px;height:48px;line-height:48px;border:none;text-align:center;color:#fff;font-size:16px;background-color:#0eacb3;border-radius:4px;margin-left:10px;width:155px}
		.form .input-code .btn-code:active{background-color:#09a3aa}
		.form .btn-login{cursor: pointer;background-color:#f95959;color:#fff;font-size:22px;margin-top:15px;width:100%;height:52px;line-height:52px;border:none;border-radius:4px}
		.form .btn-login:active{background-color:#f04e4e}
		.feature .title{text-align:center;font-family:"Adobe 黑体 Std"}
		.feature .title1{color:#0eacb3;font-size:26px}
		.feature .title2{color:#c8c8c8;font-size:16px;margin-top:5px}
		.feature .list{margin-top:20px}
		.feature .list li{width:25%;float:left;display:inline-block}
		.feature .list .circle{border-radius:100%;text-align:center;height:95px;width:95px;color:#fff;position: relative;margin: 15px auto 20px auto;}
		.feature .list .circle img{position: absolute;width: 62px;height: 62px;top: 17px;left: 17px;}
		.feature .list .circle1{background-color:#f66}
		.feature .list .circle2{background-color:#ffc066}
		.feature .list .circle3{background-color:#66a7ff}
		.feature .list .circle4{background-color:#0eacb3}
		.feature .list .text{font-size:14px;text-align:center;color:#666;line-height:1.5}
		.lookMore{cursor: pointer;border:none;background-color:#f95959;color:#fff;height:38px;line-height:38px;width:220px;text-align:center;margin:5px auto 0 auto;text-decoration:none;display:block}
		.lookMore:active{background-color: #f04e4e;}
		.footer{height:70px;line-height:70px;background-color:#0eacb3;font-size:18px;color:#fff;text-align:center;font-weight: 600;}
		.jobList{padding:20px}
		.jobList .item{width:50%;float:left;display:inline-block}
		.jobList .item ul{width:95%;background-color:#fff;position:relative;padding:12px 5px;margin-bottom:15px;cursor: pointer;}
		.jobList .item .img,.jobList .item .img img{width:80px;height:80px}
		.jobList .item .img{position:relative;border:1px solid #0eacb3}
		.jobList .item .name{font-weight:600;position:absolute;left:95px;top:16px;font-size:16px;color:#333;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;width:265px}
		.jobList .item .salary{position:absolute;top:45px;left:95px;font-size:14px;color:#333}
		.jobList .item .salary .jobName{max-width:100px;display:inline-block;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;margin-right:10px}
		.jobList .item .salary .jobSalary{color:#f66;display:inline-block;overflow:hidden}
		.jobList .item .welfare{position:absolute;top:71px;left:95px;font-size:12px}
		.jobList .item .welfare span{margin-right:4px;border:1px solid #26c4b6;width:50px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;display:inline-block;text-align:center;padding:2px;color:#0eacb3}
		.jobList .item .hot-text{position:absolute;top:0;left:0;background:url(${ctx}/static/images/LP/common.png) no-repeat;width:45px;height:45px;background-size:43px;background-position:0 0;color:#fff}
		.jobList .item .hot-text label{transform:rotate(-45deg);-ms-transform:rotate(-45deg);-webkit-transform:rotate(-45deg);display:inline-block;font-size:12px;position: absolute;top: 7px;left: 4px;}
		.luck-transition{width:100%;height:32px;position:absolute;top:-48px;z-index: 20;}
		.luck-transition .inner{width:100%;height:100px;background-color:#0eacb3;border-radius:100% 100% 0 0}
		.tips{color: #ed5f30;position: relative;top: -10px;font-size: 16px;}
		/*弹出框*/
	    .pop-box{position:absolute;top:0;left:0;width:100%;height:100%;background-color:rgba(0,0,0,.6);z-index:22;display:none}
	    .pop-inner{width:240px;background-color:transparent;position:fixed;left:50%;margin-left:-135px;margin-top:-58px;top:50%;z-index:100;padding:15px;text-align:center;display:none;color: #666;}
		.pop-inner h2{font-size:16px;text-align:center;margin-bottom:10px}
		.pop-inner p{text-align:center;font-size:14px;}
		
		.form-each-input{padding:12px 10px;border-radius:0;height:16px;line-height:16px;width:310px;color:#333;font-size:14px;float:left;border:1px solid #dadbdf}
		.btn-g{display:block;width:314px;border:0;color:#FFF;cursor:pointer;font-size:14px;text-align:center;border-radius:4px;background-color:#ff6c00;height:40px;line-height:40px}
	    .w160{width: 160px!important;}
	    .reg-div {width: 600px;float: left;margin: 0 0 0 20px;}
		.code-layer{width:360px}
		.code-layer .input{margin-top:30px;width:600px;margin-bottom:10px;height:45px;border-radius:2px;float:left;position:relative}
		.code-layer .text-input{width:140px;margin-left:0}
		.code-layer .reg-tips{margin-left:0;width:100%}
		.code-layer .pic-code{position:absolute;left:175px;top:2px;cursor:pointer;width:91px;height:39px}
		.code-layer .pic-code-text{position:absolute;left:280px;top:6px;color:#999;cursor:pointer;line-height:1.3}
		.title-box{padding:0px}
		.title-box .inner{padding:10px 20px;border-bottom:1px solid #ddd;position:relative}
		.title-box .title{font-weight:700;font-size:14px}
		.title-box .close{font-size:25px;color:#b1b1b1;padding:0 10px;position:absolute;right:0;top:0;cursor:pointer}
	    .mt5{margin-top:5px}
	    .show{display:block !important;}
	    .hide{display:none !important;}
	</style>
	<script type="text/javascript">
        (function(root) {
            root._tt_config = true;
            var ta = document.createElement('script'); ta.type = 'text/javascript'; ta.async = true;
            ta.src = document.location.protocol + '//' + 's3.pstatp.com/bytecom/resource/track_log/src/toutiao-track-log.js';
            ta.onerror = function () {
                var request = new XMLHttpRequest();
                var web_url = window.encodeURIComponent(window.location.href);
                var js_url  = ta.src;
                var url = '//ad.toutiao.com/link_monitor/cdn_failed?web_url=' + web_url + '&js_url=' + js_url + '&convert_id=65395864302';
                request.open('GET', url, true);
                request.send(null);
            }
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ta, s);
        })(window);
    </script>
</head>

<body>
<div class="page-wrapper">
	<div class="part0">
		<div class="banner">
			<img class="banner" src="${ctx}/static/images/LP/banner.png" />
		</div>
	</div>
	<div class="part1">
		<div class="luck-transition">
	    	<div class="inner"></div>
	    </div>
		<div class="card">
	    	<p>学历不高？初来乍到？无亲友介绍</p>
	    	<p>担心虚假信息太多？更怕简历无人关照？</p>
	    	<p class="mt30">优蓝网，每天提供新鲜、权威、真实、海量的企业岗位信息，适配各类蓝领人群。</p>
	    	<p>包吃包住、工作餐补、车费报销、加班补贴......各种工作福利，多劳多得！</p>
	    </div>
	</div>
    <!--请填写您的求职信息-->
    <div class="part2">
    	<form action="" id="form_signup">
	    <div class="form">
	    	<p class="title">请填写您的求职信息</p>
	    	<input class="input-control" type="text" placeholder="姓名" name="name" />
	    	<p class="tips error_name"></p>
	    	<input class="input-control" type="text" placeholder="手机号码" name="mobile" maxlength="11"/>
	    	<p class="tips error_mobile"></p>
	    	<div class="input-code">
	    		<input class="input-control" type="text" placeholder="请输入短信验证码" name="thekey" maxlength="4"/>
	    		<input class="btn-code input-send" onclick="sendCode()" type="button" value="获取验证码" />
	    	</div>
	    	<p class="tips error_thekey"></p>
	    	<input class="btn-login" type="button" value="免费报名" />
	    </div>
	    </form>
	</div>
    <!--/请填写您的求职信息-->
    <!--我们的特色-->
    <div class="part3">
	    <div class="feature">
	    	<div class="title">
	    		<p class="title1">我们的特色</p>
	    		<p class="title2">our characteristic</p>
	    	</div>
	    	<ul class="list clearfix">
	    		<li>
	    			<div class="circle circle1">
	    				<img src="${ctx}/static/images/LP/text1.png" />
	    			</div>
	    			<div class="text">
	    				<p>企业直招</p>
	    				<p>不收取任何费用</p>
	    			</div>
	    		</li>
	    		<li>
	    			<div class="circle circle2">
	    				<img src="${ctx}/static/images/LP/text2.png" />
	    			</div>
	    			<div class="text">
	    				<p>所有职位均已</p>
	    				<p>通过优蓝网严格审核认证</p>
	    			</div>
	    		</li>
	    		<li>
	    			<div class="circle circle3">
	    				<img src="${ctx}/static/images/LP/text3.png" />
	    			</div>
	    			<div class="text">
	    				<p>每月100万+名企岗位</p>
	    				<p>遍布300多个城市</p>
	    			</div>
	    		</li>
	    		<li>
	    			<div class="circle circle4">
	    				<img src="${ctx}/static/images/LP/text4.png" />
	    			</div>
	    			<div class="text">
	    				<p>专业服务</p>
	    				<p>入职速度快50%</p>
	    			</div>
	    		</li>
	    	</ul>
	    </div>
	</div>
    <!--/我们的特色-->
    <!--热招岗位-->
    <div class="part4">
	    <div class="jobList">
	    	<div class="title">
	    		<p class="title1">热招岗位</p>
	    	</div>
	    	<div class="clearfix">
	    		<c:forEach items="${newJobs }" var="job" varStatus="status">
	    		<c:if test="${status.index<4 }">
	    		<div class="item">
	    			<a href="${ctx}/zhaopin_${job.jobId}.html" target="_blank">
		    		<ul>
		    			<li class="img"><span class="hot-text"><label>热招</label></span>
		    			<c:choose>
							<c:when test="${empty job.imgPath}">
								<img src="${cdc:getImageByJobType320(job.jobType)}" alt="${job.jobTitle}" />
							</c:when>
							<c:otherwise>
								<img src="${cdc:getImagePath320(job.imgPath)}" alt="${job.jobTitle}" />
							</c:otherwise>
						</c:choose>
		    			</li>
		    			<li class="name">${job.companyName }</li>
		    			<li class="salary">
			    			<span class="jobName">${job.jobName }</span>
			    			<span class="jobSalary">
			    				<c:choose>
			    					<c:when test="${not empty job.salaryFrom }">
			    						￥${job.salaryFrom }<c:if test="${not empty  job.salaryTo}">-${job.salaryTo }元/月</c:if>
		                				<c:if test="${empty  job.salaryTo}">元/月起</c:if>
			    					</c:when>
			    					<c:otherwise>
			    						薪资面议
			    					</c:otherwise>
			    				</c:choose>
							</span>
		    			</li>
		    			<li class="welfare">
		    			<c:forEach items="${job.jobLabel}" var="label" varStatus="status">
		    				<c:if test="${status.index<4 }"><span>${label }</span></c:if>
                    	</c:forEach>
		    			</li>
		    		</ul>
		    		</a>
		    	</div>
		    	</c:if>
		    	</c:forEach>
	    	</div>
	    	<a href="${ctx }/zhaopin/"><button type="button" class="lookMore">更多职位，虚位以待</button></a>
	    </div>
	</div>
    <!--/热招岗位-->
    <div class="footer">优蓝，成就蓝领梦想的平台</div>
</div>
<!--发送短信验证码-->
<div class="code-layer">
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
<!-- 报名成功弹窗 -->
<div class="pop-inner">
    <h2>报名成功</h2>
	<p class="mt5">我们的求职顾问即将联系您</p>
    <p class="mt5 c666">请留意接听021开头的客服电话</p>
    <p class="mt5 c666"><span class="orange span_second"></span>秒后为您跳转优蓝网</p> 
</div>
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/layer.min.js"></script>
<script>
	var name_reg = /((^[\u4E00-\u9FA5]{2,12}$)|(^[a-zA-Z]{3,24}$))/;
	$(function(){
		$('.code-layer').hide();
		//输入验证码
		$(".btn_reg_vali_code").click(function(){
			valiCode();
		});
		$("input[name='mobile']").blur(function(){
			if(!/^1[3-9]\d{9}/.test($.trim($(this).val()))){
				$('.tips').hide();
				$('.error_mobile').show();
				$('.error_mobile').html("请输入正确位手机号码！");
			}else{
				$('.tips').hide();
			}
		});
		$("input[name=thekey]").blur(function(){
			if($.trim($(this).val()).length<1){
				$('.tips').hide();
				$('.error_thekey').show();
				$('.error_thekey').html("请输入您收到的验证码！");
			}else{
				$('.tips').hide();
			}
		});
		$("input[name='name']").blur(function(){
			if(!name_reg.test($(this).val())){
					$('.tips').hide();
					$('.error_name').show();
					$('.error_name').html("请输入您的姓名（2-12位的汉字或3-24位的英文）");
			}else{
				$('.tips').hide();
			}
		});
		
		//免费报名
		$(".btn-login").click(function(){
			if(validate()){
				$.post("${ctx}/ldpg/signUp",$("#form_signup").serialize(),function(data){
					 if(data=="success"){
						 _taq.push({convert_id:"65395864302", event_type:"form"});
						 successMsg();
					  	 timeshow();
			          }else if(data == "failure"){
			        	  $('.error_thekey').show();
						  $('.error_thekey').html("请输入您收到的验证码！");
			          }
				})
			}
		});
	});
	function valiCode(){
	   var value = $("#input_reg_vali_code").val();
		$.post("${ctx}/verification/valiCode",{"mobile":$("input[name='mobile']").val(),"type":"reg","value":value}, function(result){
			if(result == "success"){
				layer.closeAll();
				userGainkey();
				$(".error_msg_valicodeu").html("");
			}else{
				$(".error_msg_valicodeu").html("验证码计算不正确");
			}
		});
	}
	function validate(){
		var m = $.trim($("input[name='mobile']").val());
		var v = $.trim($("input[name='thekey']").val());
		var n = $.trim($("input[name='name']").val());
		$('.tips').hide();
		if(!/^1[3-9]\d{9}/.test(m)){
			$("input[name='mobile']").focus();
			$('.error_mobile').show();
			$('.error_mobile').html("请输入正确位手机号码！");
			return false;
		}else if (v.length < 1) {
			$("input[name='thekey']").focus();
			$('.error_thekey').show();
			$('.error_thekey').html("请输入您收到的验证码！");
			return false;
		}else if(!name_reg.test(n)){
			$("input[name='name']").focus();
			$('.error_name').show();
			$('.error_name').html("请输入您的姓名（2-12位的汉字或3-24位的英文）");
			return false;
		}
		return true;
	}
	//发送短信验证码
	function sendCode(){
		var m_reg=/^1[3-9][0-9]{9}$/i;
		var mobile = $("input[name='mobile']").val();
		$('.tips').hide();
		if(m_reg.test(mobile)){
			//判断发送了多少次发送验证码
			$.layer({
	             type: 1,
	             title: false,
	             closeBtn:false,
	             shadeClose: true,
	             area : ['360px' , '230px'],
	              page : {dom : '.code-layer'}
	            });
		}else{
			$('.error_mobile').show();
			$('.error_mobile').html("您输入的手机号码格式不正确！");
		}
	     //点击关闭按钮 关闭弹出层
	     $('.code-layer .close').click(function(){
	         layer.closeAll();
	     })
	}
	
	//发送验证码
	function userGainkey(){
			var m_reg=/^1[3-9][0-9]{9}$/i;
			var mobile = $("input[name='mobile']").val();
			$('.tips').hide();
			if(m_reg.test(mobile)){
				$(".input-send").attr("onclick", "");
				$.post("${ctx}/register/sendCode",{mobile:mobile,type:'',currentValue:$("#input_reg_vali_code").val()},function(data){
					if(data=="success"){
						$('.error_mobile').hide();
						var thenum = 60;
						var timmer = setInterval(function(){
							$(".input-send").val(thenum+'s后可以重新获取');
							thenum--;
							if(thenum==-1){
								clearInterval(timmer);
					   			$(".input-send").attr("onclick", "sendCode()");
								$(".input-send").val("获取验证码");
							}
						},1000);
					}else if(data=="frozen"){
						$('.error_mobile').show();
						$('.error_mobile').html("该手机号今天收到的短信已达到上限！");
				   		$(".input-send").attr("onclick", "sendCode()");
					}else{
						$('.error_mobile').show();
						$('.error_mobile').html("验证码发送失败请您输入的检查手机号！");
				   		$(".input-send").attr("onclick", "sendCode()");
					}
				});
			}
			else{
				$("input[name='mobile']").parent().find('.tips').show();
				$("input[name='mobile']").parent().find('.tips').html("您输入的手机号码格式不正确！");
			}
		}
	//报名成功弹窗
	function successMsg(){
		$.layer({
			type: 1,
			title: false,
			closeBtn:false,
			shadeClose: true,
			area : ["370px","auto"],
			page : {dom : '.pop-inner'}
		});
	}
	//计时跳转
	var _flag = 2;
   	var _timeOutFlag;
    function timeshow(){
	    if(_flag>0){
	      _timeOutFlag = setTimeout("timeshow()",1000);
	      $('.span_second').html(_flag);
	      _flag--;
	    }else{
	      clearTimeout(_timeOutFlag);
	      window.location.href = "${ctx }";
	    };
    }
    /* 百度统计 */
    var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
      hm.src = "//hm.baidu.com/hm.js?83a185fc9f52ffcad1bcb9e4d889301b";
      var s = document.getElementsByTagName("script")[0];
      s.parentNode.insertBefore(hm, s);
    })();
    /* GIO */
    var _vds = _vds || [];
    window._vds = _vds;
    (function(){
      _vds.push(['setAccountId', '844010516d2d97c9']);
      _vds.push(['setCS1', 'user_id', '${sessionScope.session_user_key.id}']);
      _vds.push(['setCS3', 'user_name', '${sessionScope.session_user_key.username}']);
      (function() {
        var vds = document.createElement('script');
        vds.type='text/javascript';
        vds.async = true;
        vds.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'dn-growing.qbox.me/vds.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(vds, s);
      })();
    })();
</script>
</body>