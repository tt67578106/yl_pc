<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="tcf" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布职位</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
</head>
<body>
    <!---->
    <div class="box-out mt15">
	     <!-- left -->
   		<jsp:include page="/WEB-INF/layouts/enterpriseLeft.jsp"></jsp:include>
    	<!-- /left -->
      <!--right-->
        <div class="ent-box-r-wrapper">
            <div class="ent-box-r">
              <form action="${ctx }/enterpriseJob/jobPost" id="from_data" method="post">
            	<input type="hidden" name="jobPostToken" value="${jobPostToken }"/>
            	<!--发布职位-->
            	<c:if test="${not empty job }">
            		<input type="hidden" name="id" value="${job.id }"/> 
            	</c:if>
            	<div class="ent-post">
            		<input type="hidden" id="hid_jobCount" value="${todayPostJobCount }"/>
                	<strong class="ent-title">发布职位（您还可以免费发布${10-todayPostJobCount }条）</strong>
                    <!--职位标题-->
                    <div class="form-each"><span class="form-each-name"><label class="mark">*</label>职位标题：</span><input type="text" name="title" value="${job.title }" id="inp_jobTitle" class="form-each-input" required="required" maxlength="50"/></div>
                    <div class="form-each-tips hide" id="jobTitle_tips"></div> 
                    <!--/职位标题-->
                    <!--职位分类-->
                    <div class="form-each select-inline-2">
                        <span class="form-each-name"><label class="mark">*</label>职位分类：</span>
                        <!--职位分类，父类-->
                        <div class="select-g select-g-1 ml0">
                          <div class="select-g-title">
						  <span id="parent_type">
                          	<c:if test="${empty job.jobType }">请选择职位分类</c:if>
                          	<c:if test="${not empty job.jobType }">${fn:split(job.jobType ,",")[0] }</c:if>
                          	</span>
						  </div>
						  <ul class="select-g-options select_jobType">
                          	<c:forEach items="${typeList }" var="jobType"><li value="${jobType.id }">${jobType.jobName }</li></c:forEach>
                          </ul>
                          <input type="hidden" name="parentId" id="hidden_parentId"/>
                        </div>
                        <!--/职位分类，父类-->
                        <!--职位分类，子类-->
                        <div class="select-g select-g-1">
                          <div class="select-g-title">
						  <span id="child_type">
                          	<c:if test="${empty job.jobType }">请选择子分类</c:if>
                          	<c:if test="${not empty job.jobType }">${fn:split(job.jobType ,",")[fn:length(fn:split(job.jobType ,","))-1] }</c:if>
                          </span>
						  </div>
                          <ul class="select-g-options" id="jobType_by_parentid"></ul>
                          <input type="hidden" name="jobType" value="${job.jobType }" id="hidden_jobType"/>
                        </div>
                        <!--/职位分类，子类-->
                        <div class="form-each-tips hide" id="jobType_tips"></div> 
                	</div>
                    <!--/职位分类-->
                    <!--招聘人数-->
                    <div class="form-each"><span class="form-each-name">招聘人数：</span><input type="text" name="recruitcount" id="inp_count" value="${job.recruitcount }" class="form-each-input" min="0" max="100000"/></div>
                    <div class="form-each-tips hide" id="count_tips"></div> 
                    <!--/招聘人数-->
                    <!--薪资范围-->
                    <div class="form-each from-to">
                    	<span class="form-each-name"><label class="mark">*</label>薪资范围：</span>
                    	<input type="text" name="jobDetail.salaryfrom" value="${job.jobDetail.salaryfrom }" class="form-each-input" id="inp_salaryfrom" min="0"/><label class="to">到</label><input type="text" name="jobDetail.salaryto" value="${job.jobDetail.salaryto }" id="inp_salaryto" class="form-each-input" max="100000"/>
                    </div>
                     <div class="form-each-tips hide" id="salary_tips"></div> 
                    <!--/薪资范围-->
                    <!--工作地址-->
                    <div class="form-each select-inline-3">
                        <span class="form-each-name"><label class="mark">*</label>工作地址：</span>
                        <!--省-->
                        <div class="select-g select-g-2 ml0">
                          <div class="select-g-title" id="div_province">
						  <span id="sel_province">
                          	<c:if test="${empty job.provinceid }">请选择省</c:if>
                          	<c:if test="${not empty job.provinceid }">${cdc:convertProvinceAbbreviation(job.provinceid) }</c:if>
                          	</span>
						  </div>
						  <ul class="select-g-options province-list">
                          	<c:forEach items="${provinceList }" var="province"><li value="${province.id }">${province.abbreviation }</li></c:forEach>
                          </ul>
                          <input type="hidden" name="provinceid"  value="${job.provinceid }" id="hidden_provinceid"/>
                        </div>
                        <!--/省-->
                        <!--市-->
                        <div class="select-g select-g-2">
                          <div class="select-g-title city-list">
						  <span id="sel_city">
                          	<c:if test="${empty job.city }">请选择市</c:if>
                          	<c:if test="${not empty job.city }">${job.city.abbreviation }</c:if>
                          	</span>
						  </div>
                          <ul class="select-g-options" id="ul_city_list"></ul>
                          <input type="hidden" name="city.id" value="${job.city.id }" id="hidden_city"/>
                        </div>
                        <!--/市-->
                        <!--区-->
                        <div class="select-g select-g-2">
                          <div class="select-g-title county-list">
						   <span id="sel_district">
                          		<c:if test="${empty job.countyid }">请选择区</c:if>
                          		<c:if test="${not empty job.countyid }">${cdc:converDistrictName(job.countyid) }</c:if>
                          	 </span>
						  </div>
                         <ul class="select-g-options" id="ul_county_list"></ul>
                         <input type="hidden" name="countyid" value="${job.countyid }" id="hidden_county"/>
                        </div>
                        <!--/区-->
                        <div class="form-each-tips hide" id="pcc_tips"></div> 
                	</div>
                    <div class="form-each"><span class="form-each-name"></span><input type="text" value="${job.address }" id="inp_address" name="address" class="form-each-input tip-text"/></div>
                    <div class="form-each-tips hide" id="address_tips"></div> 
                    <!--/工作地址-->
                    <!--学历要求-->
                    <div class="form-each">
                        <span class="form-each-name">学历要求：</span>
                        <div class="select-g select-g-3">
                          <div class="select-g-title">
                          	<span>
                          		<c:if test="${empty job.jobDetail.education }">请选择学历要求</c:if>
                          		<c:if test="${not empty job.jobDetail.education }">${cdc:converDicEducation(job.jobDetail.education) }</c:if>
                          	</span>
                          </div>
                          <ul class="select-g-options">
                             <c:forEach items="${educationList }" var="education" >
                         		 <li value="${education.code }"><c:if test="${education.name=='不限' }">学历不限</c:if><c:if test="${education.name!='不限' }">${education.name}</c:if></li>
                         	  </c:forEach>
                          </ul>
                          <select class="original-select" name="jobDetail.education">
                           	 <c:forEach items="${educationList }" var="education">
                          		<option <c:if test="${education.code == job.jobDetail.education }"> selected="selected"</c:if> value="${education.code }"><c:if test="${education.name=='不限' }">学历不限</c:if><c:if test="${education.name!='不限' }">${education.name}</c:if></option>
                         	 </c:forEach>
                          </select>
                        </div>
                	</div>
                    <!--/学历要求-->
                    <!--工作经验-->
                    <div class="form-each">
                        <span class="form-each-name">工作经验：</span>
                        <div class="select-g select-g-4">
                          <div class="select-g-title">
                          	<span>
                          		<c:if test="${empty job.jobDetail }">请选择工作经验</c:if>
                          		<c:if test="${not empty job.jobDetail&&(empty job.jobDetail.workExpFrom||job.jobDetail.workExpFrom==0) }">工作经验不限</c:if>
                          		<c:if test="${not empty job.jobDetail.workExpFrom }">${job.jobDetail.workExpFrom }年以上</c:if>
                          	</span>
                          </div>
                          <ul class="select-g-options">
                            <li value="0">工作经验不限</li>
                            <li value="1">1年以上</li>
                            <li value="2">2年以上</li>
                            <li value="3">3年以上</li>
                            <li value="5">5年以上</li>
                          </ul>
                          <select class="original-select" name="jobDetail.workExpFrom">
                            <option value="0" <c:if test="${empty job.jobDetail.workExpFrom||job.jobDetail.workExpFrom==0 }">selected="selected"</c:if>>工作经验不限</option>
                            <option value="1" <c:if test="${job.jobDetail.workExpFrom==1 }">selected="selected"</c:if>>1年以上</option>
                            <option value="2" <c:if test="${job.jobDetail.workExpFrom==2 }">selected="selected"</c:if>>2年以上</option>
                            <option value="3" <c:if test="${job.jobDetail.workExpFrom==3||job.jobDetail.workExpFrom==4 }">selected="selected"</c:if>>3年以上</option>
                            <option value="5" <c:if test="${job.jobDetail.workExpFrom>=5 }">selected="selected"</c:if>>5年工作经验</option>
                          </select>
                        </div>
                	</div>
                    <!--/工作经验-->
                    <!--性别要求-->
                    <div class="form-each">
                        <span class="form-each-name">性别要求：</span>
                        <div class="select-g select-g-5">
                          <div class="select-g-title">
                          <span>
                          <c:if test="${job.jobDetail.gender==2 }">性别不限</c:if>
                          <c:if test="${job.jobDetail.gender==1 }">男</c:if>
                          <c:if test="${job.jobDetail.gender==0 }">女</c:if>
                          <c:if test="${empty job.jobDetail.gender }">请选择性别要求</c:if>
                          </span>
                          </div>
                          <ul class="select-g-options">
                            <li value="2">性别不限</li>
                            <li value="1">男</li>
                            <li value="0">女</li>
                          </ul>
                          <select class="original-select" name="jobDetail.gender">
                            <option value="2" <c:if test="${job.jobDetail.gender==2 }">selected="selected"</c:if>>性别不限</option>
                            <option value="1" <c:if test="${job.jobDetail.gender==1 }">selected="selected"</c:if>>男</option>
                            <option value="0" <c:if test="${job.jobDetail.gender==0 }">selected="selected"</c:if>>女</option>
                          </select>
                        </div>
                    </div>
                    <!--/性别要求-->
                    <!--年龄要求-->
                    <div class="form-each from-to">
                        <span class="form-each-name">年龄要求：</span>
                    	<input type="text" name="jobDetail.agefrom" id="inp_agefrom" value="<c:if test="${empty job.jobDetail.agefrom }">16</c:if><c:if test="${not empty job.jobDetail.agefrom }">${job.jobDetail.agefrom }</c:if>" class="form-each-input" maxlength="2" min="16" required="required" />
                    	<label class="to">到</label>
                    	<input type="text" name="jobDetail.ageto" id="inp_ageto" value="${job.jobDetail.ageto }" class="form-each-input"  max="200"/>
                	</div>
                	<div class="form-each-tips hide" id="age_tips"></div> 
                    <!--/年龄要求-->
                    <!--福利标签-->
                    <div class="form-each"><span class="form-each-name">福利标签：</span>
                      <input type="hidden" name="jobLabel" value="${job.jobLabel }" id="hid_jobLabel">
                    	<ul class="ent-tags">
                        	<li <c:if test="${fn:contains(job.jobLabel,'无需经验') }">class="selected"</c:if>>无需经验</li>
                            <li <c:if test="${fn:contains(job.jobLabel,'缴纳社保') }">class="selected"</c:if>>缴纳社保</li>
                            <li <c:if test="${fn:contains(job.jobLabel,'五险一金') }">class="selected"</c:if>>五险一金</li>
                            <li <c:if test="${fn:contains(job.jobLabel,'周末双休') }">class="selected"</c:if>>周末双休</li>
                            <li <c:if test="${fn:contains(job.jobLabel,'带薪培训') }">class="selected"</c:if>>带薪培训</li>
                            <li <c:if test="${fn:contains(job.jobLabel,'上五休二') }">class="selected"</c:if>>上五休二</li>
                            <li <c:if test="${fn:contains(job.jobLabel,'包住宿') }">class="selected"</c:if>>包住宿</li>
                            <li <c:if test="${fn:contains(job.jobLabel,'包工作餐') }">class="selected"</c:if>>包工作餐</li>
                            <li <c:if test="${fn:contains(job.jobLabel,'餐补') }">class="selected"</c:if>>餐补</li>
                            <li <c:if test="${fn:contains(job.jobLabel,'试用期短') }">class="selected"</c:if>>试用期短</li>
                        </ul>
                    </div>
                    <!--/福利标签-->
                    <!--薪资描述-->
                    <div class="form-each"><span class="form-each-name">薪资描述：</span><textarea name="jobDetail.salarydesc" class="textarea-g"><c:if test="${empty job.jobDetail.salarydesc }">${tcf:getSalarydescModel() }</c:if><c:if test="${not empty job.jobDetail.salarydesc }">${job.jobDetail.salarydesc }</c:if></textarea></div>
                    <!--/薪资描述-->
                    <!--职位描述-->
                    <div class="form-each"><span class="form-each-name">职位描述：</span><textarea name="jobDetail.workdesc" class="textarea-g"><c:if test="${empty job.jobDetail.workdesc }">${tcf:getWorkdescModel() }</c:if><c:if test="${not empty job.jobDetail.workdesc }">${job.jobDetail.workdesc }</c:if></textarea></div>
                    <!--/职位描述-->
                    <!--职位要求-->
                    <div class="form-each"><span class="form-each-name">职位要求：</span><textarea name="jobDetail.demanddesc" class="textarea-g"><c:if test="${empty job.jobDetail.demanddesc }">${tcf:getDemanddescModel() }</c:if><c:if test="${not empty job.jobDetail.demanddesc }">${job.jobDetail.demanddesc }</c:if></textarea></div>
                    <!--/职位要求-->
                    <!--吃住情况-->
                    <div class="form-each"><span class="form-each-name">吃住情况：</span><textarea name="jobDetail.mealsdesc" class="textarea-g"><c:if test="${empty job.jobDetail.mealsdesc }">${tcf:getMealsdescModel() }</c:if><c:if test="${not empty job.jobDetail.mealsdesc }">${job.jobDetail.mealsdesc }</c:if></textarea></div>
                    <!--/吃住情况-->
                    <!--福利情况-->
                    <div class="form-each"><span class="form-each-name">福利情况：</span><textarea name="jobDetail.welfaredesc" class="textarea-g"><c:if test="${empty job.jobDetail.welfaredesc }">${tcf:getWelfaredescModel() }</c:if><c:if test="${not empty job.jobDetail.welfaredesc }">${job.jobDetail.welfaredesc }</c:if></textarea></div>
                    <!--/福利情况-->
                    <!--上传图片-->
                    <div class="form-each">
                    	<span class="form-each-name"><label class="mark">*</label>上传图片：</span>
                        <div class="upload">
                        	<c:if test="${empty job.thumbnialImage||empty job.thumbnialImage.imgpath }"><img id="thumbnialImage" src="${ctx }/static/images/pic-default.jpg"/></c:if>
                        	<c:if test="${not empty job.thumbnialImage&&not empty job.thumbnialImage.imgpath }"><img id="thumbnialImage" src="${cdc:getImagePath320(job.thumbnialImage.imgpath) }"/></c:if>
                        	<input type="button" value="上传" class="btn-g-3"/>
                        	<input type="hidden" id="hid_image" name="thumbnialImage.id" value="${job.thumbnialImage.id }"/>
                            <input type="file" class="file" name="file"/>
                        </div>
                    </div>
                    <div class="form-each-tips hide" id="image_tips"></div> 
                    <!--/上传图片-->
                    <div class="form-btn"><input type="button" id="btn_save" value="发布" class="btn-g-2"/></div>
                    <div class="clear"></div>
                </div>
                <!--/发布职位-->
              </form>
            </div>
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
    <!---->
    <style>
    .ke-content {color:#666!important;}
    </style>
    <script src="${ctx}/static/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script src="${ctx}/static/jquery-file-upload/js/jquery.iframe-transport.js"></script>
    <script src="${ctx}/static/jquery-file-upload/js/jquery.fileupload.js"></script>
    <script src="${ctx }/static/kindeditor-4.1.10/kindeditor-min.js"></script>
    <script>
    $(function(){
    	var editor_salarydesc = KindEditor.create('textarea[name="jobDetail.salarydesc"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : []
		});
    	var editor_workdesc = KindEditor.create('textarea[name="jobDetail.workdesc"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : []
		});
		var editor_demanddesc = KindEditor.create('textarea[name="jobDetail.demanddesc"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : []
		});
		var editor_mealsdesc = KindEditor.create('textarea[name="jobDetail.mealsdesc"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : []
		});
		var editor_welfaredesc = KindEditor.create('textarea[name="jobDetail.welfaredesc"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : []
		});
    	window.onbeforeunload = function(e) {
    		return '您的信息未保存';
    	};
    	//选择福利标签
    	$('.ent-tags li').click(function(){
    		$(this).toggleClass('selected');
    		var jobLabel='';
    		$(".ent-tags li").each(function(){
    			if ($(this).hasClass("selected"))
       			{
    				jobLabel=jobLabel+$(this).text()+',';
       			}
    		});
   		 	$("#hid_jobLabel").val(jobLabel.substring(0,jobLabel.length-1));
    	});
    	$("#inp_jobTitle").blur(function(){
    		if ($.trim($(this).val()) == "" || $.trim($(this).val()).length < 1) {
    			$("#jobTitle_tips").show();
    			$("#jobTitle_tips").html("请输入岗位名称");
    			$(this).focus();
    		}else{$("#jobTitle_tips").hide();}
    	});
		$("#inp_salaryfrom").blur(function(){
			if($.trim($(this).val())!=""&&!/^([0-9]*$)|([0-9]+(.[0-9]{2})?$)/.test($.trim($(this).val()))){
				$("#salary_tips").show();
				$("#salary_tips").html("薪资格式不正确，请重新填写！");
				$(this).val('');
				$(this).focus();
			}else{$("#salary_tips").hide();}
		});
		$("#inp_salaryto").blur(function(){
			if($.trim($(this).val())!=""&&!/^([0-9]*$)|([0-9]+(.[0-9]{2})?$)/.test($.trim($(this).val()))){
				$("#salary_tips").show();
				$("#salary_tips").html("薪资格式不正确，请重新填写！");
				$(this).focus();
			}
			if($.trim($(this).val())!=""&&eval($.trim($(this).val()))<eval($.trim($("#inp_salaryfrom").val())))
			{
				$("#salary_tips").show();
				$("#salary_tips").html("最高薪资不能低于最低薪资");
				$(this).focus();
			}else{$("#salary_tips").hide();}
		})
		$("#inp_count").blur(function(){
			if($.trim($(this).val())!=""&&!/^[0-9]*$/.test($.trim($(this).val()))){
				$("#count_tips").show();
				$("#count_tips").html("招聘人数格式不正确，请重新填写！");
				$(this).val('');
				$(this).focus();
				return false;
			}else{$("#count_tips").hide();}
		})
		$("#inp_ageto").blur(function(){
			if($.trim($(this).val())!=""&&!/^[0-9]*$/.test($.trim($(this).val()))){
				$("#age_tips").show();
				$("#age_tips").html("年龄格式不正确，请重新填写！");
				$(this).val('');
				$(this).focus();
			}
			if($.trim($(this).val())!=""&&eval($.trim($(this).val()))<eval($.trim($("#inp_agefrom").val())))
			{
				$("#age_tips").show();
				$("#age_tips").html("最大年龄不能低于最小年龄");
				$(this).focus();
			}else{$("#age_tips").hide();}
		})
		$("#inp_agefrom").blur(function(){
			if($.trim($(this).val())!=""&&!/^[0-9]*$/.test($.trim($(this).val())))
			{
				$("#age_tips").show();
				$("#age_tips").html("年龄格式不正确，请重新填写！");
				$(this).val('');
				$(this).focus();
			}else{$("#age_tips").hide();}
		})
		$("#inp_address").blur(function(){
			if($.trim($(this).val()).length<2||$.trim($(this).val()).length>80){
				$("#address_tips").show();
				$("#address_tips").html("请输入正确的详细地址");
				$(this).focus();
			}else{$("#address_tips").hide();}
		})
    	$("#btn_save").click(function(){
    		//防止重复提交
        	if (!jobbaseValidate()) {
        		return false;
        	}else{
        		editor_salarydesc.sync(); 
        		editor_workdesc.sync(); 
        		editor_demanddesc.sync(); 
        		editor_mealsdesc.sync(); 
        		editor_welfaredesc.sync();
            	$("#btn_save").unbind();
        		$("#from_data").submit();
        			 window.onbeforeunload=null; 
        	}
    	})

    	//上传文件
    	$('.ent-post .upload .btn-g-3').click(function(){
    		$(this).parents('.form-each').find('.file').trigger('click');
    	});
    	
    	$(".file").fileupload({
			url : "${ctx}/enterpriseJob/upload/thumbnialImage?jobid=${job.id}",
			done : function(e, data) {
				if (data.result == "tomuchcount") {
					alert('请休息一下，短时间内不要上传太多图片');
				} else {
					$("#thumbnialImage").attr("src",IMAGE_FILE_URL+"320/"+data.result.imgpath + "?t=" + Math.random());
					$("#hid_image").val(data.result.id);
					$("#image_tips").hide();
				}
			},
			send : function(e, data) {
				if (!valiFile(data)) {
					alert('文件不支持，岗位预览图格式限制为jpg/jpeg/png/gif，不超过3MB！');
					return false;
				}
			},
			acceptFileTypes : /(\.|\/)(jpg|png|jpeg|gif)$/i,
			maxFileSize : 3000000,
			maxNumberOfFiles : 1
		}).prop('disabled', !$.support.fileInput).parent()
		.addClass($.support.fileInput ? undefined : 'disabled');
    	
    	//选择职位类别
    	$('.select_jobType li').click(function(){
    		$("#child_type").text("请选择子分类");
    		$("#hidden_parentId").val($(this).val());
    		$("#jobType_tips").show();
    		$("#jobType_tips").html("请至少选择一个岗位子类型");
    	});
    	
    	$("#child_type").click(function(){
 			var parentId=$("#hidden_parentId").val();
 			$.post("${ctx}/enterpriseJob/jobTypes",{parentId:parentId},
 		    		function(data){
 		    		var dov = [];
 		     		$.each(data,function(i,n){
 		     			dov.push('<li value="'+n.id+'">'+n.jobName+'</li>');
 		     		});
 		     		$("#jobType_by_parentid").empty();
 		     		$("#jobType_by_parentid").append(dov.join(''));
 		     			
 		     		 $("#jobType_by_parentid li").click(function(){
 		   				$("#child_type").text($(this).text());
 		   				$("#jobType_by_parentid").css('display','none');
 		   				$("#hidden_jobType").val($("#parent_type").text()+","+$("#child_type").text());
 		   				});
 		     			$("#jobType_tips").hide();
 		    		});
 		});
    	
    	//选择省市区   	
    	$('.province-list li').click(function(){
    		$('.city-list span').text("请选择市");
    		$('.county-list span').text("请选择区");
    		$("#hidden_provinceid").val($(this).val());
    		$("#pcc_tips").show();
			$("#pcc_tips").html("请选择城市");
    	});
    	
 		$("#sel_city").click(function(){
 			var provinceid=$("#hidden_provinceid").val();
 			$.post("${ctx}/enterpriseJob/citys",{provinceid:provinceid},
 		    		function(data){
 		    		var dov = [];
 		     		$.each(data,function(i,n){
 		     			dov.push('<li value="'+n.id+'">'+n.abbreviation+'</li>');
 		     		});
 		     		$("#ul_city_list").empty();
 		     		$("#ul_city_list").append(dov.join(''));
 		     			
 		     		 $("#ul_city_list li").click(function(){
 		   			 	$("#hidden_city").val($(this).val());
 		   				$('.city-list span').text($(this).text());
 		   				$("#ul_city_list").css('display','none');;
 		   		    	$('.county-list span').text("请选择区");
 		   		    	$("#pcc_tips").hide();
 		   				});
 		    		});
 		});
 		$("#sel_district").click(function(){
 			var cityid=$("#hidden_city").val();
 			$.post("${ctx}/enterpriseJob/districts",{cityid:cityid},
 	 	    		function(data){
 	 	    		var uldov = [];
 	 	     		$.each(data,function(i,n){
 	 	     			uldov.push('<li value="'+n.id+'">'+n.districtName+'</li>');
 	 	     		});
 	 	     		$("#ul_county_list").empty();
 	 	     		$("#ul_county_list").append(uldov.join(''));
 	 	     			
 	 	     		$("#ul_county_list li").click(function(){
 	 	  				$("#hidden_county").val($(this).val());
 	 	  				$('.county-list span').text($(this).text());
 	 	  				$("#ul_county_list").css('display','none');
 	 	  				});
 	 	    		});
 			}); 
    });
    //验证图片信息
    function valiFile(data) {
		var regxp = new RegExp("\.(jpg|png|jpeg|gif)$", "g");
		var flag = false;
		$.each(data.files, function(index, file) {
			var namexp = file.name.toLowerCase().match(regxp);
			if (file.size <= 3000000 && namexp != null) {
				flag = true;
			};
		});
		return flag;
	};
	
	function jobbaseValidate() {// 验证表单
		var province = $("#hidden_provinceid").val();
		var city = $("#hidden_city").val();
		var district = $("#hidden_county").val();
		var jobTitle = $("#inp_jobTitle").val();
		var jobType = $.trim($("#parent_type").text());//岗位大类型
		var jobTypeSon =$.trim($("#child_type").text());//岗位小类型
		var salaryfrom = $("#inp_salaryfrom").val();
		var salaryto=$("#inp_salaryto").val();
		var agefrom=$("#inp_agefrom").val();
		var ageto=$("#inp_ageto").val();
		var image=$("#hid_image").val();
		if (jobTitle == "" || jobTitle.length < 1) {
			$("#jobTitle_tips").show();
			$("#jobTitle_tips").html("请输入岗位名称");
			$("#inp_jobTitle").focus();
			return false;
		}else{$("#jobTitle_tips").hide();}
		if (jobType == "" ||jobType=="请选择职位分类"|| jobType.length < 1) {
			$("#jobType_tips").show();
			$("#jobType_tips").html("请选择岗位类型");
			$("html,body").animate({scrollTop:$("#parent_type").offset().top-10},100);
			return false;
		}else{$("#jobType_tips").hide();}
		if (jobTypeSon == ""||jobTypeSon=="请选择子分类"|| jobTypeSon.length < 1) {
			$("#jobType_tips").show();
			$("#jobType_tips").html("请至少选择一个岗位子类型");
			$("html,body").animate({scrollTop:$("#child_type").offset().top-10},100);
			return false;
		}else{$("#jobType_tips").hide();}
		if(salaryfrom != ""&& !/^([0-9]*$)|([0-9]+(.[0-9]{2})?$)/.test(salaryfrom)){
			$("#salary_tips").show();
			$("#salary_tips").html("薪资格式不正确，请重新填写！");
			$("#inp_salaryfrom").val('');
			$("#inp_salaryfrom").focus();
		}else{$("#salary_tips").hide();}
		if (salaryfrom == "" || salaryfrom.length < 1) {
			$("#salary_tips").show();
			$("#salary_tips").html("最低薪资不能为空");
			$("#inp_salaryfrom").focus();
			return false;
		}else{$("#salary_tips").hide();}
		if(salaryto!=""&&!/^([0-9]*$)|([0-9]+(.[0-9]{2})?$)/.test(salaryto)){
			$("#salary_tips").show();
			$("#salary_tips").html("薪资格式不正确，请重新填写！");
			$("#inp_salaryto").focus();
			return false;
		}else{$("#salary_tips").hide();}
		if(salaryto!=""&&eval(salaryto)<eval(salaryfrom))
		{
			$("#salary_tips").show();
			$("#salary_tips").html("最高薪资不能低于最低薪资");
			$("#inp_salaryto").focus();
			return false;
		}else{$("#salary_tips").hide();}
		if($("#inp_count").val()!=""&&!/^[0-9]*$/.test($("#inp_count").val())){
			$("#count_tips").show();
			$("#count_tips").html("招聘人数格式不正确，请重新填写！");
			$("#inp_count").val('');
			$("#inp_count").focus();
			return false;
		}else{$("#count_tips").hide();}
		if(agefrom != ""&& !/^[0-9]*$/.test(agefrom)){
			$("#age_tips").show();
			$("#age_tips").html("年龄格式不正确，请重新填写！");
			$("#inp_agefrom").val('');
			$("#inp_agefrom").focus();
		}else{$("#age_tips").hide();}
		if(ageto!=""&&!/^[0-9]*$/.test(ageto)){
			$("#age_tips").show();
			$("#age_tips").html("年龄格式不正确，请重新填写！");
			$("#inp_ageto").val('');
			$("#inp_ageto").focus();
			return false;
		}else{$("#age_tips").hide();}
		if(ageto!=""&&eval(ageto)<eval(agefrom))
		{
			$("#age_tips").show();
			$("#age_tips").html("最大年龄不能低于最小年龄");
			$("#inp_ageto").focus();
			return false;
		}else{$("#age_tips").hide();}
		if (province == "" || province.length < 1) {
			$("#pcc_tips").show();
			$("#pcc_tips").html("请选择省份");
			$("html,body").animate({scrollTop:$("#div_province").offset().top-10},100);
			return false;
		}else{$("#pcc_tips").hide();}
		if (city == "" || city.length < 1) {
			$("#pcc_tips").show();
			$("#pcc_tips").html("请选择城市");
			$("html,body").animate({scrollTop:$("#div_province").offset().top-100},100);
			return false;
		}else{$("#pcc_tips").hide();}
		if($.trim($("#inp_address").val()).length<2||$.trim($("#inp_address").val()).length>80){
			$("#address_tips").show();
			$("#address_tips").html("请输入正确的详细地址");
			$("#inp_address").focus();
			return false;
		}else{$("#address_tips").hide();}
		if (image == "" || image.length < 1) {
			$("#image_tips").show();
			$("#image_tips").html("请上传岗位图片");
			return false;
		}else{$("#image_tips").hide();}
		return true;
	}
    </script>
</body>
</html>