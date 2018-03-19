<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="Qe3ngwHAwx" />
<title>活动报名_中国就业万里行</title>
</head>
<body>
<link rel="stylesheet" href="${ctx}/static/css/jiuye.css" />
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
</head>
<body>
<!--page-wrapper-->
<div class="page-wrapper jy-bg">

  	 <!--top-menu-->
  	  <jsp:include page="/WEB-INF/layouts/wanlixingHeader.jsp"></jsp:include>
	 <!--/top-menu---> 	

 <!--报名-->
    <div class="box-out">
    	<div class="jy-sign-up fl">
        	<div class="form">
        	  <form action="${ctx}/trip10000/registration/submitSignUp" method="post" >
        		<div id="step1" >
            	<p class="text">*请认真填写您的真实个人信息，如使用虚假个人信息，造成的一切后果，优蓝网概不负责。
					优蓝网承诺尊重您的隐私，您所提供的一切个人资料仅用于优蓝网校园创业PK大赛活动联系及反馈，未取得您的同意，不会与第三方共用或向第三方披露。</p>
                <!--姓名-->
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>姓名</span><input type="text" value="请输入您的真实姓名" class="form-each-input tip-text markInput" name="fullName" maxlength="10"/></div>
                <!--/姓名-->
                <!--职务-->
                <div class="form-each"><span class="form-each-name">职务</span><input type="text" value="请输入您的在校职务" class="form-each-input tip-text" name="post" maxlength="25"/></div>
                <!--/职务-->
                <!--专业-->
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>专业</span><input type="text" value="请输入您所学专业名称" class="form-each-input tip-text markInput" name="specialty" maxlength="25"/></div>
                <!--/专业-->
                <!--所在系-->
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>所在系</span><input type="text" value="请输入您所在院系" class="form-each-input tip-text markInput" name="departmentAndProfessional" maxlength="25"/></div>
                <!--/所在系-->
                <!--手机-->
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>手机</span><input type="text" value="请输入您的手机号码" class="form-each-input tip-text markInput" name="mobile" maxlength="11"/></div>
                <!--/手机-->
                <!--邮箱-->
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>邮箱</span><input type="text" value="请输入您的电子邮箱" class="form-each-input tip-text markInput" name="email"/></div>
                <!--/邮箱-->
                <div class="form-btn"><input type="button" value="下一步" class="btn-g-4" id="nextStep"/></div>
                </div>
                <div id="step2" class="hide">
                <p class="jy-title2 mt50">创业项目信息</p>
                <!--创业项目名称-->
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>创业项目名称</span><input type="text" value="请输入您的创业项目名称" class="form-each-input tip-text markInput" name="title"/></div>
                <!--/创业项目名称-->
                <!--创业项目简介-->
                <div class="form-each"><span class="form-each-name">创业项目简介</span><textarea class="textarea-g tip-text" name="introduction">请输入您的创业项目简介</textarea></div>
                <!--/创业项目简介-->
                <!--项目书PPT文件-->
                <div class="form-each upload-g">
                    <span class="form-each-name"><label class="mark">*</label>项目书PPT文件</span>
                    <span class="form-each-input">请上传您的创业项目PPT文件</span>
                    <input type="file" class="file" id="fileuploadPPT" name="file"/>
                    <input type="button" class="btn-g-5" value="上传"/>
                    <input type="hidden" name="pptFile" id="fileuploadPPTh"/>
                </div>
                <!--/项目书PPT文件-->
                <!--院校名称-->
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>院校名称</span><input type="text" value="请输入您的院校名称" class="form-each-input tip-text markInput" name="school"/></div>
                <!--/院校名称-->
                <!--参赛团队名称-->
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>参赛团队名称</span><input type="text" value="请输入您的参赛团队名称" class="form-each-input tip-text markInput" name="teamName"/></div>
                <!--/参赛团队名称-->
                <!--项目宣传视频-->
                <div class="form-each"><span class="form-each-name">项目宣传视频</span><input type="text" value="" class="form-each-input tip-text" name="video"/></div>
                <!--/项目宣传视频-->
                <!--发起人头像照片-->
                <div class="form-each upload-g">
                    <span class="form-each-name">发起人头像照片：</span>
                    <span class="form-each-input">请上传发起人头像照片</span>
                    <input type="file" class="file" name="file" id="fileuploadLeader"/>
                    <input type="button" class="btn-g-5" value="上传"/>
                    <input type="hidden" name="leaderImage" id="fileuploadLeaderh"/>
                </div>
                <!--/发起人头像照片-->
                <!--团队风采照片-->
                <div id="uploadTeamPic">
                </div>
                <div class="form-each">
                	<span class="form-each-name"></span>
                    <span class="add">上传图片</span>
                    <input type="file" class="hide" id="addImage" name="file"/>
                </div>
                <input type="hidden" name="signUpToken" value="${signUpToken }"/>
                <!--/团队风采照片-->
                <div class="form-btn"><input type="submit" value="提交" class="btn-g-4" id="submitForm"/></div>
                </div>
                </form>
            </div>
            <div class="hr-g fl mt30"></div>
            <ul class="contact">
            	<li class="icon-tel2"></li>
                <li><strong>联系我们</strong></li>
                <li class="c">
                	<span class="l">报名电话：18628853738</span>
                 	   咨询QQ：2355918829
                </li>
                <li class="c">
                	<span class="l">大赛邮箱：2355918829@qq.com</span>官方微博：<a href="http://weibo.com/youlanwHR" rel="nofollow" target="_blank">优蓝网HR</a>
                </li>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <div class="jy-r-panel">
    	<a href="${ctx }/trip10000/registration/signUp" class="sign-link"><!--快速报名--></a>
        <a href="javascript:;" class="go-top"><!--返回顶部--></a>
   		 </div>
    <jsp:include page="/WEB-INF/layouts/footer.jsp"></jsp:include>
    <!--/报名-->
	<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
	<script src="${ctx}/static/js/common.js?v=20150417"></script>
    <script src="${ctx}/static/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
    <script src="${ctx}/static/jquery-file-upload/js/jquery.fileupload.js"></script>
    <script type="text/javascript">
    	window.onbeforeunload = function(e) {
    		return '您的信息未保存';
    	};
	  //上传文件
		$('.upload-g .form-each-input,.upload-g .btn-g-upload').click(function(){
			$(this).parents('.form-each').find('.file').trigger('click');
		});
		$('.add').click(function(){
			$('#addImage').trigger('click');
			uploadImg("addImage",true,true);
		});
	  	$("#fileuploadPs,#fileuploadId,#fileuploadSCS,#fileuploadPs,#fileuploadLeader").click(function(){
	  		uploadImg($(this).attr("id"),false);
	  	});
	  	$("#fileuploadPPT").click(function(){
	  		uploadPPT($(this).attr("id"));
	  	});
    	$("#nextStep").click(function(){
    		$(".form-each-tips").prev().find("input").removeClass("error");
    		$(".form-each-tips").remove();
    		var flag = false;
    		$("#step1 .markInput").each(function(){
    			if (this.value == this.defaultValue) {
    				flag = true;
    				$(this).after('<div class="form-each-tips">'+this.defaultValue+'</div>');
    				$(this).addClass("error");
    				$(this).focus();
    				return false;
    			}
    		  });
    		//初始值为default的返回
    		if(flag){
    			 return false; 
    		}
    		if(!/^[\u4e00-\u9fa5_a-zA-Z]{2,10}$/.test($("input[name='fullName']").val())){
    			$("input[name='fullName']").after('<div class="form-each-tips">请输入姓名</div>');
    			$("input[name='fullName']").addClass("error");
    			$("input[name='fullName']").focus();
    			 return false; 
    		}
    		if($("input[name='post']").val().length>20){
    			$("input[name='post']").after('<div class="form-each-tips">字符太长</div>');
    			$("input[name='post']").addClass("error");
    			$("input[name='post']").focus();
    			 return false; 
    		}
    		if(!/^[\u4e00-\u9fa5_a-zA-Z]{3,25}$/.test($("input[name='specialty']").val())){
    			$("input[name='specialty']").after('<div class="form-each-tips">请输入正确的专业名称</div>');
    			$("input[name='specialty']").addClass("error");
    			$("input[name='specialty']").focus();
    			 return false; 
    		}
    		if(!/^[\u4e00-\u9fa5_a-zA-Z]{2,25}$/.test($("input[name='departmentAndProfessional']").val())){
    			$("input[name='departmentAndProfessional']").after('<div class="form-each-tips">请输入正确的系名称</div>');
    			$("input[name='departmentAndProfessional']").addClass("error");
    			$("input[name='departmentAndProfessional']").focus();
    			 return false; 
    		}
    		 if(!/^1[3-9]\d{9}/.test($("input[name='mobile']").val())){
     			$("input[name='mobile']").after('<div class="form-each-tips">请输入正确手机号码</div>');
    			$("input[name='mobile']").addClass("error");
     			$("input[name='mobile']").focus();
     			 return false; 
     		}
    		 if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test($("input[name='email']").val())){
      			$("input[name='email']").after('<div class="form-each-tips">请输入正确的电子邮箱</div>');
    			$("input[name='email']").addClass("error");
      			$("input[name='email']").focus();
      			 return false; 
      		}
    		$("#step1").hide();
    		$("#step2").show();
    	});
    	//提交表单
    	$("#submitForm").click(function(){
    		$(".form-each-tips").prev().find("input").removeClass("error");
    		$(".form-each-tips").remove();
    		var flag = false;
    		$("#step2 .markInput").each(function(){
    			if (this.value == this.defaultValue) {
    				flag = true;
    				$(this).after('<div class="form-each-tips">'+this.defaultValue+'</div>');
    				$(this).addClass("error");
    				$(this).focus();
    				return false;
    			}
    		  });
    		//初始值为default的返回
    		if(flag){
    			 return false; 
    		}
    		$("input[name='post'],input[name='telephone'],textarea[name='introduction']").each(function(){
    			if (this.value == this.defaultValue) {
    				this.value=null;
    			}
    		  });
    		 if($("input[name='pptFile']").val()==null || $("input[name='pptFile']").val()==""){
    	   			$("input[name='pptFile']").after('<div class="form-each-tips">请上传项目书ppt文件</div>');
    	  			$("input[name='pptFile']").addClass("error");
    	   			$("input[name='pptFile']").focus();
    	   			 return false; 
    	   		}
    	 		if( $("input[name='video']").val().trim() !="" && $("input[name='video']").val() !=null && $("input[name='video']").val() !="请输入优酷、土豆、腾讯、爱奇艺等视频分享地址"
    	 				&& !/^((https|http|ftp|rtsp|mms)?:\/)/.test($("input[name='video']").val())){
    	 			$("input[name='video']").after('<div class="form-each-tips">请输入优酷、土豆、腾讯、爱奇艺等视频分享地址</div>');
    	 			$("input[name='video']").addClass("error");
    	   			$("input[name='video']").focus();
    	   			 return false; 
    	 		 }
    		if($("input[name='video']").val().length>=500){
    			$("input[name='video']").after('<div class="form-each-tips">地址太长,请重新输入</div>');
    			$("input[name='video']").addClass("error");
      			$("input[name='video']").focus();
      			 return false; 
    		 }
    		 var fileKey ="";
    		 var picName ="";
    		 var index=0;
    		 $("#uploadTeamPic .form-each").each(function(){
    			 index++;
    			 fileKey= $(this).find("input[name='leaderTeam']").val();
    			 picName= $(this).find(".pic-name").val();
     			if(picName=="请输入图片名称"){
     				picName="";
     			}
     			$(this).find("input[name='leaderTeam']").val(fileKey+"#"+picName+"#"+index);
     		});
    		 window.onbeforeunload=null;
    	});
    	
    	//团队风采
    	var count = 0;
    	function addInput(){
    			count ++;
    			if(count<50){
    			$("#uploadTeamPic").append('<div class="form-each upload-g with-text"><span class="form-each-name">团队风采照片：</span><input type="text" class="pic-name tip-text" value="请输入图片名称"/>'+
    					'<input type="file" class="file" name="file" id="fileuploadTeam'+count+'"/><input type="button" class="btn-g-5 ml10 new-btn-g-5'+count+'" value="修改"/>'+
    					'<a href="javascript:void(0)"><input type="button" class="btn-g-5 ml10 btn-g-5" value="查看"/></a>'+
    					'<input type="button" class="btn-g-6 del-btn-g-5'+count+' ml10" value="删除"/><input type="hidden" name="leaderTeam" id="fileuploadTeam'+count+'h"/></div>');
    			$("#uploadTeamPic .form-each-name").not(":first").text("");
    			//绑定原始效果
    			$(".upload-g .new-btn-g-5"+count).bind("click",function(){
    				$(this).parents('.form-each').find('.file').trigger('click');
    			});
    			$(".upload-g .del-btn-g-5"+count).bind("click",function(){
    				$(this).parents('.form-each').remove();
    			});
    			$('.tip-text').addClass('tip-text-default').bind({
    				focus : function() {
    					if (this.value == this.defaultValue) {
    						this.value = "";
    						$(this).removeClass('tip-text-default').addClass('tip-text tip-text-input');
    					}
    				},
    				blur : function() {
    					if (this.value == "") {
    						this.value = this.defaultValue;
    						$(this).removeClass('tip-text-input').addClass('tip-text tip-text-default');
    					}
    				}
    			});
    			//绑定上传图片
    			$("#fileuploadTeam"+count).bind("click",function(){
    				uploadImg($(this).attr("id"),true);
    			});
    			}
    			return "fileuploadTeam"+count;
    	}
    	function deleteParent(obj){
    		$("#"+obj).parents('.form-each').remove();
    		$("#uploadTeamPic .form-each-name").eq(0).text("团队风采照片：");
    	}
    	//图片上传
    	function uploadImg(fileId,flag,isNew){
    		$("#"+fileId).fileupload(
				{
					url : "${ctx}/trip10000/registration/upload/registrationImage",
					done : function(e, data) {
						if (data.result == "tomuchcount") {
							alert("请休息一下，短时间内不要上传太多头像");
						} else {
							if(data.result.split("/t").length>1){
								if(flag){
									if(isNew){
										fileId = addInput();
									}
									$("#"+fileId).parent().find("input[type=hidden]").val(data.result.split("/t")[0]);
									$("#"+fileId).parent().find("a").attr('href',data.result.split("/t")[1]);
								}else{
									$("#"+fileId).parent().find("input[type=hidden]").val(data.result.split("/t")[0]);
									$("#"+fileId).parent().find("span").eq(1).text(data.result.split("/t")[1]);
								}
							}
						}
					},
					send : function(e, data) {
						if (!valiFile(data)) {
							alert("文件不支持，上传格式限制为jpg|png|jpeg|gif，不超过5MB！")
							return false;
						}
					},
					acceptFileTypes : /(\.|\/)(jpg|png|jpeg|gif)$/i,
					maxFileSize : 5000000,
					maxNumberOfFiles : 1
				}).prop('disabled', !$.support.fileInput).parent()
				.addClass($.support.fileInput ? undefined : 'disabled');
    	
		function valiFile(data) {
			var regxp = new RegExp("\.(jpg|png|jpeg|gif)$", "g");
			var flag = false;
			$.each(data.files, function(index, file) {
				var namexp = file.name.toLowerCase().match(regxp);
				if (file.size <= 5000000 && namexp != null) {
					flag = true;
				};
			});
			return flag;
		}}
    	//PPt上传
    	function uploadPPT(fileId){
    		$("#"+fileId).fileupload(
				{
					url : "${ctx}/trip10000/registration/upload/registrationPPT",
					done : function(e, data) {
						if (data.result == "tomuchcount") {
							alert("请休息一下，短时间内不要上传太多文件");
						} else {
							if(data.result.split("/t").length>1){
								$("#"+fileId+"h").val(data.result.split("/t")[0])
								$("#"+fileId).parent().find("span").eq(1).text(data.result.split("/t")[1]);
							}
						}
					},
					send : function(e, data) {
						if (!valiFile(data)) {
							alert("文件不支持，上传格式限制为ppt/pptx/pps，不超过5MB！")
							return false;
						}
					},
					acceptFileTypes : /(\.|\/)(ppt|pptx|pps)$/i,
					maxFileSize : 5000000,
					maxNumberOfFiles : 1
				}).prop('disabled', !$.support.fileInput).parent()
				.addClass($.support.fileInput ? undefined : 'disabled');
    	
		function valiFile(data) {
			var regxp = new RegExp("\.(ppt|pptx|pps)$", "g");
			var flag = false;
			$.each(data.files, function(index, file) {
				var namexp = file.name.toLowerCase().match(regxp);
				if (file.size <= 5000000 && namexp != null) {
					flag = true;
				};
			});
			return flag;
		}}
    </script>
</body>
</html>
