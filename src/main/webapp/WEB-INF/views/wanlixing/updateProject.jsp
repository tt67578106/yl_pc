<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="Qe3ngwHAwx" />
<title>提交项目书_活动报名_中国就业万里行</title>
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
<div class="top-menu">
	<div class="box-out">
		<div class="fl"><a href="http://www.youlanw.com">优蓝网首页</a></div>
		<div class="fr">
			<label class="icon-common icon-tel">4008-777-816</label>
			<a href="${ctx }/about/mobile.html" target="_blank" class="icon-common icon-phone" rel="nofollow">手机优蓝</a>
			<a href="http://weibo.com/youlanwHR" target="_blank" rel="nofollow">官方微博</a>
		</div>
	</div>
</div>
<!--/top-menu--->  <!--jy-menu-->
   <div class="jy-menu-out">
       <div class="jy-menu">
           <div class="box-out">
                <a href="${ctx }/trip10000"><img src="${ctx}/static/images/logo-jy.png" alt="优蓝网" class="logo fl"/></a>
               <span class="list">
                   <a href="${ctx }/trip10000" class="${wlxIndexActive }">大赛首页</a>
                   <a href="${ctx }/trip10000/projects" class="${projectsActive }">参赛项目</a>
                   <a href="${ctx }/trip10000/tutors" class="${tutorsActive }">导师阵容</a>
                   <a href="${ctx }/trip10000/itemsList" class="${itemsListActive }">评委风采</a>
                   <a href="${ctx }/trip10000/rules" class="${rulesActive }">参赛规则</a>
                   <a href="${ctx }/trip10000/prizeSetting" class="${prizeSettingActive }">奖品设置</a>
               </span>
               <a href="${ctx }/trip10000/registration/signUp" class="btn-g-2 fr">我的项目</a>
           </div>
       </div>
   </div>
<!--/jy-menu--->
  <!--报名-->
    <div class="box-out">
    	<div class="jy-sign-up fl">
        	<c:if test="${empty project.isValidate || project.isValidate==1}"><p class="suc">您的项目正在审核中，请耐心等待三个工作日</p></c:if>
        	<c:if test="${project.isValidate==2}"><p class="suc">恭喜您，已成功当上校园CEO</p></c:if>
        	<c:if test="${project.isValidate==3}"><p class="suc">很遗憾，您的项目审核不通过，请修改后重新申请</p></c:if>
        	<div class="form">
        	<form action="${ctx}/trip10000/registration/updateProject" method="post" >
            	<p class="jy-title2 mt50">我的创业项目</p>
                <!--创业项目名称-->
                <c:if test="${empty project.title}">
               		 <div class="form-each"><span class="form-each-name"><label class="mark">*</label>创业项目名称</span><input type="text" name="title" value="请输入您的创业项目名称" class="form-each-input tip-text markInput"/></div>
                </c:if>
                <c:if test="${not empty project.title}">
               		 <div class="form-each"><span class="form-each-name"><label class="mark">*</label>创业项目名称</span><input type="text" name="title" value="${project.title}" class="form-each-input"/></div>
                </c:if>
                <!--/创业项目名称-->
                <!--创业项目简介-->
                <c:if test="${empty project.introduction}">
                 <div class="form-each"><span class="form-each-name">创业项目简介</span><textarea name="introduction" class="textarea-g tip-text">请输入您的创业项目简介</textarea></div>
                </c:if>
                <c:if test="${not empty project.introduction}">
                 <div class="form-each"><span class="form-each-name">创业项目简介</span><textarea name="introduction" class="textarea-g ">${project.introduction}</textarea></div>
                </c:if>
                <!--/创业项目简介-->
                <!--项目书PPT文件-->
                <div class="form-each upload-g">
                    <span class="form-each-name"><label class="mark">*</label>项目书PPT文件</span>
                    <span class="form-each-input"><c:if test="${empty project.pptFile}">请上传您的创业项目PPT文件</c:if>${project.pptFile }</span>
                    <input type="file" class="file" id="fileuploadPPT" name="file"/>
                    <input type="button" class="btn-g-5 btn-g-upload" value="上传"/>
                    <input type="hidden" name="pptFile" id="fileuploadPPTh" value="${registrationPPT.key }"/>
                </div>
                <!--/项目书PPT文件-->
                <!--院校名称-->
                <c:if test="${empty project.school}">
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>院校名称</span><input type="text" name="school" value="请输入您的院校名称" class="form-each-input tip-text markInput"/></div>
                </c:if>
                <c:if test="${not empty project.school}">
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>院校名称</span><input type="text" name="school" value="${project.school}" class="form-each-input"/></div>
                </c:if>
                <!--/院校名称-->
                <!--参赛团队名称-->
                <c:if test="${empty project.teamName}">
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>参赛团队名称</span><input type="text" value="请输入您的参赛团队名称" name="teamName" class="form-each-input tip-text markInput"/></div>
                </c:if>
                <c:if test="${not empty project.teamName}">
                <div class="form-each"><span class="form-each-name"><label class="mark">*</label>参赛团队名称</span><input type="text" value="${project.teamName}" name="teamName" class="form-each-input"/></div>
                </c:if>
                <!--/参赛团队名称-->
                <!--项目宣传视频-->
                <c:if test="${empty project.video}">
                <div class="form-each"><span class="form-each-name">项目宣传视频</span><input type="text" name="video" value="" class="form-each-input tip-text" name="video"/></div>
               </c:if>
                <c:if test="${not empty project.video}">
                <div class="form-each"><span class="form-each-name">项目宣传视频</span><input type="text" name="video" value="${project.video}" class="form-each-input" name="video"/></div>
                </c:if>
                <!--/项目宣传视频-->
                 <!--发起人头像照片-->
                <div class="form-each upload-g">
                    <span class="form-each-name">发起人头像照片：</span>
                    <span class="form-each-input"><c:if test="${empty image.imgPath}">请上传发起人头像照片</c:if>${image.imgPath }</span>
                    <input type="file" class="file" name="file" id="fileuploadLeader"/>
                    <input type="button" class="btn-g-5 btn-g-upload" value="上传"/>
                    <input type="hidden" name="leaderImage" id="fileuploadLeaderh" value="${image.key}"/>
                </div>
                <!--/发起人头像照片-->
                <div id="uploadTeamPic">
                <c:forEach items="${images}" var="image" varStatus="status">
					 <div class="form-each upload-g with-text">
					 <span class="form-each-name">团队风采照片：</span>
	                    <input type="file" class="file imageFileUpload" name="file" id="fileuploadTeam${status.index}" />
	                    <c:if test="${empty image.introduction}">
	                     <input type="text" class="pic-name tip-text" value="请输入图片名称"/>
	                     </c:if>
               			 <c:if test="${not empty image.introduction}">
               			 	<input type="text" class="pic-name" value="${image.introduction }"/>
                 		 </c:if>
	                    	<input type="button" class="btn-g-5 btn-g-upload ml10" value="修改"/>
	                    	<a target="_blank" href="${image.imgPath }"><input type="button" class="btn-g-5 ml10" value="查看"/></a>
	                        <input type="button" class="btn-g-6 ml10 del-btn-g-5${status.index}" value="删除" onclick="deleteParent('fileuploadTeam${status.index}')"/>
	                        <input type="hidden" name="leaderTeam" id="fileuploadTeam${status.index}h" value="${image.key }"/>
			                <input name="imageIds" type="hidden" value="${image.id }">
			                <input name="excludesImageIds" type="hidden">
	                </div>
				</c:forEach>
				</div>
				 <div class="form-each">
                	<span class="form-each-name"></span>
                    <span class="add">上传图片</span>
                    <input type="file" class="hide" id="addImage" name="file"/>
                </div>
                <!--/团队风采照片-->
                
                <div class="form-btn"><input type="submit" value="提交" class="btn-g-4" id="submitForm"/></div>
                <input type="hidden" name="id" value="${project.id }"/>
                 <input type="hidden" name="signUpToken" value="${signUpToken }"/>
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
$(".imageFileUpload").click(function(){
	uploadImg($(this).attr("id"),true);
});
//团队风采
//提交表单
  	$("#submitForm").click(function(){
  		$(".form-each-tips").prev().find("input").removeClass("error");
		$(".form-each-tips").remove();
  		var flag = false;
  		$(".markInput").each(function(){
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
  		if($("textarea[name='title']")==""){
  			$("input[name='title']").after('<div class="form-each-tips">请输入创业项目名称</div>');
			$("input[name='title']").addClass("error");
  			$("input[name='title']").focus();
  			 return false; 
  		}
  		if($("textarea[name='introduction']").val()=="请输入您的创业项目简介"){
  			$("textarea[name='introduction']").val(null);
  		}
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
  		window.onbeforeunload=null;
  		 var fileKey ="";
		 var picName ="";
		 var index=0;
		 $("#uploadTeamPic .form-each").each(function(){
			 index++;
			 fileKey = $(this).find("input[name='leaderTeam']").val();
			 picName = $(this).find(".pic-name").val();
   			if(picName=="请输入图片名称"){
   				picName="";
   			}
   		 $(this).find("input[name='leaderTeam']").val(fileKey+"#"+picName+"#"+index);
   		});
  	});
  //团队风采
  $("#uploadTeamPic .form-each-name").not(":first").text("");
	var count = 50;
	function addInput(){
			count ++;
			if(count<100){
			$("#uploadTeamPic").append('<div class="form-each upload-g with-text"><span class="form-each-name">团队风采照片：</span><input type="text" class="pic-name tip-text" value="请输入图片名称"/>'+
					'<input type="file" class="file" name="file" id="fileuploadTeam'+count+'"/><input type="button" class="btn-g-5 new-btn-g-5'+count+' ml10" value="修改"/>'+
					'<a href="javascript:void(0)"><input type="button" class="btn-g-5 ml10" value="查看"/></a>'+
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
		$("#fileuploadPPT").click(function(){
			uploadPPT($(this).attr("id"));
		});
	
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
							$("#"+fileId).parent().find("input[type=hidden]").eq(0).val(""+data.result.split("/t")[0]);
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