<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业资料</title>
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
            <c:if test="${enterprise.validation==-1 }">
                    <div class="ent-no-result">
                		<span class="icon-smile"><i></i></span>企业信息正在审核中，请耐心等待！
               		</div>
               		<div class="clear"></div> 
           </c:if>
           <c:if test="${enterprise.validation==0 }">
                <div class="ent-no-result">
                	<span class="icon-smile"><i></i></span>审核失败，<a href="javascript:void(0)" id="refSave" class="ml10">重新提交</a>
                	<input type="hidden" id="hid_ref" value="${enterprise.id}"/>
               	</div>
           </c:if>
           <c:if test="${empty enterprise.validation||enterprise.validation==1||enterprise.validation==2 }">
            <form action="${ctx }/enterprise/information" id="from_data" method="post">
            	<input type="hidden" name="informationToken" value="${informationToken }"/>
            	<!--企业资料-->
            	<div class="ent-data">
                	<!--上传logo-->
                    <div class="form-each upload" id="div_file">
                    	<span class="form-each-name"><label class="mark">*</label>上传logo：</span>
                        <span  class="form-each-input" id="span_file">${fn:split(enterprise.logo.imgpath ,"/")[fn:length(fn:split(enterprise.logo.imgpath ,"/"))-1] }</span>
                        <input type="hidden" id="hid_logo" name="logo.id" value="${enterprise.logo.id }"/>
                        <input type="file" class="file" name="file" />
                        <input type="button" class="btn-g-3" value="上传"/>
                        <p class="form-each-des">请上传3M以内200*150的jpg(或png、gif)格式图片</p>
                        <div class="form-each-tips hide" id="logo_tips"></div> 
                    </div>
                    <!--/上传logo-->
                    <!--企业名称-->
                    <div class="form-each"><span class="form-each-name"><label class="mark">*</label>企业名称：</span><input type="text" name="name" id="inp_company_name" value="${enterprise.name }" class="form-each-input" maxlength="50"/></div>
                   	<div class="form-each-tips hide" id="companyname_tips"></div> 
                    <!--/企业名称-->
                    <!--企业简称-->
                    <div class="form-each"><span class="form-each-name">企业简称：</span><input type="text" name="abbreviation" value="${enterprise.abbreviation }" class="form-each-input" maxlength="50"/></div>
                    <!--/企业简称-->
                    <!--所属行业-->
                    <div class="form-each"><span class="form-each-name"><label class="mark">*</label>所属行业：</span>
                    	<div class="select-g select-g-1">
                          <div class="select-g-title">
                          	<span id="sel_industryid">
                          	<c:if test="${empty enterprise.industryid}">请选择所属行业</c:if>
                      		<c:if test="${not empty enterprise.industryid}">${cdc:convertDicIndustryType(enterprise.industryid)}</c:if>
							</span>
						  </div>
                          <ul class="select-g-options">
                           <c:forEach items="${intentList }" var="intent" >
                         	 <li value="${intent.code }">${intent.name }</li>
                           </c:forEach>
                          </ul>
                          <select class="original-select" name="industryid">
                         	<c:forEach items="${intentList}" var="intent">
                     		 <option <c:if test="${intent.code == enterprise.industryid }"> selected="selected"</c:if> value="${intent.code }">${intent.name }</option>
                      		</c:forEach>
                          </select>
                        </div>
                        <div class="form-each-tips hide" id="industry_tips"></div> 
                    </div>
                    <!--/所属行业-->
                    <!--企业规模-->
                    <div class="form-each"><span class="form-each-name"><label class="mark">*</label>企业规模：</span>
                    	<div class="select-g select-g-3">
                          <div class="select-g-title">
                          	<span id="sel_staffscale">
                          		<c:if test="${empty enterprise.staffscale }">请选择企业规模</c:if>
                          		<c:if test="${not empty enterprise.staffscale }">${cdc:convertDicStaffscale(enterprise.staffscale) }</c:if>
                          	</span>
                          </div>
                          <ul class="select-g-options">
                              <c:forEach items="${staffscaleList }" var="staffscale" >
                         		 <li value="${staffscale.code }">${staffscale.name }</li>
                         	  </c:forEach>
                          </ul>
                          <select class="original-select" name="staffscale">
                         	 <c:forEach items="${staffscaleList }" var="staffscale">
                          		<option <c:if test="${staffscale.code == enterprise.staffscale }"> selected="selected"</c:if> value="${staffscale.code }">${staffscale.name }</option>
                         	 </c:forEach>
                          </select>
                        </div>
                        <div class="form-each-tips hide" id="staffscale_tips"></div> 
                    </div>
                    <!--/企业规模-->
                    <!--详细地址-->
                     <div class="form-each select-inline-3">
                        <span class="form-each-name"><label abel class="mark">*</label>详细地址：</span>
                        <!--省-->
                        <div class="select-g select-g-4 ml0">
                          <div class="select-g-title" id="div_province">
                          	<span id="sel_province">
                          	<c:if test="${empty enterprise.provinceid }">请选择省</c:if>
                          	<c:if test="${not empty enterprise.provinceid }">${cdc:convertProvinceAbbreviation(enterprise.provinceid) }</c:if>
                          	</span>
                          </div>
                          <ul class="select-g-options province-list">
                          	<c:forEach items="${provinceList }" var="province"><li value="${province.id }">${province.abbreviation }</li></c:forEach>
                          </ul>
                          <input type="hidden" name="provinceid"  value="${enterprise.provinceid }" id="hidden_provinceid"/>
                        </div>
                        <!--/省-->
                        <!--市-->
                        <div class="select-g select-g-4">
                          <div class="select-g-title city-list">
                          	<span id="sel_city">
                          	<c:if test="${empty enterprise.city }">请选择市</c:if>
                          	<c:if test="${not empty enterprise.city }">${enterprise.city.abbreviation }</c:if>
                          	</span>
                          </div>
                          <ul class="select-g-options" id="ul_city_list"></ul>
                          <input type="hidden" name="city.id" value="${enterprise.city.id }" id="hidden_city"/>
                         </div>
                        <!--/市-->
                        <!--区-->
                        <div class="select-g select-g-4">
                          <div class="select-g-title county-list">
                         	 <span id="sel_district">
                          		<c:if test="${empty enterprise.countyid }">请选择区</c:if>
                          		<c:if test="${not empty enterprise.countyid }">${cdc:converDistrictName(enterprise.countyid) }</c:if>
                          	 </span>
                          </div>
                          <ul class="select-g-options" id="ul_county_list"></ul>
                           <input type="hidden" name="countyid" value="${enterprise.countyid }" id="hidden_county"/>
                        </div>
                        <!--/区-->
                        <div class="form-each-tips hide" id="pcc_tips"></div> 
                	</div>
                    <div class="form-each"><span class="form-each-name"></span><input type="text" id="inp_address" name="address" value="${enterprise.address }" class="form-each-input" maxlength="100"/></div>
                    <div class="form-each-tips hide" id="address_tips"></div>
                    <!--/详细地址-->
                    <!--企业亮点-->
                    <div class="form-each">
                    	<span class="form-each-name">企业亮点：</span>
                    	<%-- <input type="hidden" name="brightSpot" value="${enterprise.brightSpot }" id="hid_brightSpot"> --%>
                       <%--  <ul class="ent-tags">
                        	<li <c:if test="${fn:contains(enterprise.brightSpot,'包吃包住') }">class="selected"</c:if>>包吃包住</li>
                            <li <c:if test="${fn:contains(enterprise.brightSpot,'年底奖金') }">class="selected"</c:if>>年底奖金</li>
                            <li <c:if test="${fn:contains(enterprise.brightSpot,'五险一金') }">class="selected"</c:if>>五险一金</li>
                            <li <c:if test="${fn:contains(enterprise.brightSpot,'包吃包住') }">class="selected"</c:if>>包吃包住</li>
                            <li <c:if test="${fn:contains(enterprise.brightSpot,'年底奖金') }">class="selected"</c:if>>年底奖金</li>
                        </ul> --%>
                        <div class="ent-tags-input">
                        	<input type="text" name="brightSpot" maxlength="20" value="${fn:split(enterprise.brightSpot ,',')[0] }"/>
                            <input type="text" name="brightSpot" maxlength="20" value="${fn:split(enterprise.brightSpot ,',')[1] }"/>
                            <input type="text" name="brightSpot" maxlength="20" value="${fn:split(enterprise.brightSpot ,',')[2] }"/>
                            <input type="text" name="brightSpot" maxlength="20" value="${fn:split(enterprise.brightSpot ,',')[3] }"/>
                            <input type="text" name="brightSpot" maxlength="20" value="${fn:split(enterprise.brightSpot ,',')[4] }"/>
                        </div>
                    </div>
                    <!--/企业亮点-->
                    <!--企业优势-->
                    <div class="form-each"><span class="form-each-name">企业优势：</span><textarea  name="advantages" class="textarea-g">${enterprise.advantages }</textarea></div>
                    <!--/企业优势-->
                    <!--详细介绍-->
                    <div class="form-each"><span class="form-each-name">详细介绍：</span><textarea name="introduction" class="textarea-g">${enterprise.introduction }</textarea></div>
                    <!--/详细介绍-->
                    <!--联系人-->
                    <div class="form-each"><span class="form-each-name"><label class="mark">*</label>联系人：</span><input type="text" id="input_contactname" name="contactPerson" value="${enterprise.contactPerson }" class="form-each-input" maxlength="25"/></div>
                    <div class="form-each-tips hide" id="contactname_tips"></div> 
                    <!--/联系人-->
                    <!--联系方式-->
                    <div class="form-each"><span class="form-each-name"><label class="mark">*</label>联系方式：</span><input type="text" id="input_contactphone" name="contactPhone" value="${enterprise.contactPhone }" class="form-each-input" maxlength="25"/></div>
                    <div class="form-each-tips hide" id="contactphone_tips"></div> 
                    <!--/联系方式-->
                    <div class="form-btn"><input type="button" id="btn_save" value="保存资料" class="btn-g-2"/></div>
                    <div class="clear"></div>
                </div>	
                <!--/企业资料-->
              </form>
               </c:if>
            </div>
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
    
    <!---->
    <script src="${ctx}/static/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script src="${ctx}/static/jquery-file-upload/js/jquery.iframe-transport.js"></script>
    <script src="${ctx}/static/jquery-file-upload/js/jquery.fileupload.js"></script>
    <script src="${ctx }/static/kindeditor-4.1.10/kindeditor-min.js"></script>
    <script>
    $(function(){
    	var editor_advantages = KindEditor.create('textarea[name="advantages"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : []
		});
    	var editor_introduction = KindEditor.create('textarea[name="introduction"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : []
		});
    	window.onbeforeunload = function(e) {
    		return '您的信息未保存';
    	}; 
    	<c:if test="${not empty error}">
    		alert("${error}");
    	</c:if>
    	<c:if test="${not empty enterprise&&enterprise.isdel==0}">
    		alert("对不起你的企业信息已被删除，请联系客服")
			location.href ="${ctx}/about/contact.html";
    	</c:if>
    	$("#refSave").click(function(){
    		$.post("${ctx }/enterprise/reinformation",{enterpriseId:$("#hid_ref").val()},function(result){window.location.reload();});
    		window.onbeforeunload =null;
    	})
    	//选择福利标签
    	/* $('.ent-tags li').click(function(){
    		$(this).toggleClass('selected');
    		var brightSpot='';
    		$(".ent-tags li").each(function(){
    			if ($(this).hasClass("selected"))
       			{
       			 	brightSpot=brightSpot+$(this).text()+',';
       			}
    		});
   		 	$("#hid_brightSpot").val(brightSpot.substring(0,brightSpot.length-1));
    	}); */
    	$("#inp_company_name").blur(function(){
    		if ($(this).val() == "" || $(this).val().length < 1) {
    			$("#companyname_tips").show();
    			$("#companyname_tips").html("请输入企业名称");
    		}else{$("#companyname_tips").hide();}
    	});
    	$("#inp_address").blur(function(){
    		if ($(this).val() == ""||$.trim($(this).val()) == "请输入详细地址" || $(this).val().length < 2||$(this).val().length > 80) {
    			$("#address_tips").show();
    			$("#address_tips").html("请输入正确的企业详细地址");
    		}else{$("#address_tips").hide();}
    	});
    	$("#input_contactname").blur(function(){
    		if(!/((^[\u4E00-\u9FA5]{2,6}$)|(^[a-zA-Z]{3,12}$))/.test($.trim($("#input_contactname").val())))
    		{
    			$("#contactname_tips").show();
    			$("#contactname_tips").html("请您输入正确的联系人姓名（格式为为2-5位的汉字或3-12位的英文）");
    		}else{$("#contactname_tips").hide();}
    	});
    	$("#input_contactphone").blur(function(){
    		if(!/(^[[0-9]{3}-|\[0-9]{4}-]?([0-9]{8}|[0-9]{7})?$)|(^1[3-9][0-9]{9}$)/.test($.trim($("#input_contactphone").val()))){
    			$("#contactphone_tips").show();
    			$("#contactphone_tips").html("请您输入正确的联系方式（手机号或者座机）");
    		}else{$("#contactphone_tips").hide();}
    	})	
    	$("#btn_save").click(function(){
    		if (!companyValidate()) {
    			window.onbeforeunload =null;
    			return false;
    		}else{
    			editor_advantages.sync();
    			editor_introduction.sync();
    			//防止重复提交
        		$("#btn_save").unbind();
    			$("#from_data").submit();
    		 	 window.onbeforeunload=null; 
    		}
    	})
    	//上传文件
     	$('.ent-data .upload .form-each-input,.ent-data .upload .btn-g-3').click(function(){
    		$('.ent-data .upload .file').trigger('click');
    	}); 
    	$(".file").fileupload({
				url : "${ctx}/enterprise/upload/logo?companyid=${enterprise.id}",
				done : function(e, data) {
					if (data.result == "tomuchcount") {
						alert('请休息一下，短时间内不要上传太多logo');
					} else {
						alert("上传成功！");
						$("#logo_img").attr("src",IMAGE_FILE_URL+"320/"+data.result.imgpath + "?t=" + Math.random());
						$("#hid_logo").val(data.result.id);
						var imgpath=data.result.imgpath.split("/");
						$("#span_file").text(imgpath[imgpath.length-1])
						$("#logo_tips").hide();
					}
				},
				send : function(e, data) {
					if (!valiFile(data)) {
						alert('文件不支持，企业logo格式限制为jpg/jpeg/png/gif，不超过3MB！');
						return false;
					}
				},
				acceptFileTypes : /(\.|\/)(jpg|png|jpeg|gif)$/i,
				maxFileSize : 3000000,
				maxNumberOfFiles : 10
			}).prop('disabled', !$.support.fileInput).parent()
			.addClass($.support.fileInput ? undefined : 'disabled');
    	
    	$('.province-list li').click(function(){
    		$('.city-list span').text("请选择市");
    		$('.county-list span').text("请选择区");
    		$("#hidden_provinceid").val($(this).val());
    		$("#pcc_tips").show();
			$("#pcc_tips").html("请选择城市");
    	});
 		$("#sel_city").click(function(){
 			var provinceid=$("#hidden_provinceid").val();
 			$.post("${ctx}/enterprise/citys",{provinceid:provinceid},
 		    		function(date){
 		    		var dov = [];
 		     		$.each(date,function(i,n){
 		     			dov.push('<li value="'+n.id+'">'+n.abbreviation+'</li>');
 		     		});
 		     		$("#ul_city_list").empty();
 		     		$("#ul_city_list").append(dov.join(''));
 		     			
	     		 	$("#ul_city_list li").click(function(){
 		   			 	$("#hidden_city").val($(this).val());
 		   				$('.city-list span').text($(this).text());
 		   				$("#ul_city_list").css('display','none');
 		   		    	$('.county-list span').text("请选择区");
 		   		    	$("#pcc_tips").hide();
 		   				});
 		    		});
 		});
 		$("#sel_district").click(function(){
 			var cityid=$("#hidden_city").val();
 			$.post("${ctx}/enterprise/districts",{cityid:cityid},
 	 	    		function(date){
 	 	    		var uldov = [];
 	 	     		$.each(date,function(i,n){
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
	
	// 验证表单
	function companyValidate() {
		var name_reg=/((^[\u4E00-\u9FA5]{2,12}$)|(^[a-zA-Z]{3,24}$))/;
		var phone_reg=/(^[[0-9]{3}-|\[0-9]{4}-]?([0-9]{8}|[0-9]{7})?$)|(^1[3-9][0-9]{9}$)/;
		var logo=$.trim($("#hid_logo").val());
		var name = $.trim($("#inp_company_name").val());
		var province =$.trim($("#sel_province ").text());
		var city = $.trim($("#sel_city").text());
		var district = $.trim($("#sel_district").text());
		var industryid = $.trim($("#sel_industryid").text());
		var staffscale = $.trim($("#sel_staffscale").text());
		var address = $.trim($("#inp_address").val());
		if (logo == "" || logo.length < 1) {
			$("#logo_tips").show();
			$("#logo_tips").html("请上传企业logo");
			$("html,body").animate({scrollTop:$("#div_file").offset().top-10},100);
			return false;
		}else{$("#logo_tips").hide();}
		if (name == "" || name.length < 1) {
			$("#companyname_tips").show();
			$("#companyname_tips").html("请输入企业名称");
			$("#inp_company_name").focus();
			return false;
		}else{$("#companyname_tips").hide();}
		if (industryid == "请选择所属行业" || industryid.length < 1) {
			$("#industry_tips").show();
			$("#industry_tips").html("请选择企业所属行业");
			$("html,body").animate({scrollTop:$("#sel_industryid").offset().top-10},100);
			return false;
		}else{$("#industry_tips").hide();}
		if (staffscale == "请选择企业规模" || staffscale.length < 1) {
			$("#staffscale_tips").show();
			$("#staffscale_tips").html("请选择企业人员规模");
			$("html,body").animate({scrollTop:$("#sel_staffscale").offset().top-10},100);
			return false;
		}else{$("#staffscale_tips").hide();}
		if (province == "请选择省" || province.length < 1) {
			$("#pcc_tips").show();
			$("#pcc_tips").html("请选择企业所在省份");
			$("html,body").animate({scrollTop:$("#div_province").offset().top-10},100);
			return false;
		}else{$("#pcc_tips").hide();}
		if (city == "请选择市" || city.length < 1) {
			$("#pcc_tips").show();
			$("#pcc_tips").html("请选择企业所在城市");
			$("html,body").animate({scrollTop:$("#div_province").offset().top-10},100);
			return false;
		}else{$("#pcc_tips").hide();}
		if (address == "请输入详细地址" || address.length < 2|| address.length >80) {
			$("#address_tips").show();
			$("#address_tips").html("请输入正确的企业详细地址");
			$("#inp_address").focus();
			return false;
		}else{$("#address_tips").hide();}
		if(!name_reg.test($.trim($("#input_contactname").val())))
		{
			$("#contactname_tips").show();
			$("#contactname_tips").html("请您输入正确的联系人姓名（格式为为2-12位的汉字或3-24位的英文）");
			$("#input_contactname").focus();
			return false;
		}else{$("#contactname_tips").hide();}
		if(!phone_reg.test($.trim($("#input_contactphone").val())))
		{
			$("#contactphone_tips").show();
			$("#contactphone_tips").html("请您输入正确的联系方式（手机号或者座机）");
			$("#input_contactphone").focus();
			return false;
		}else{$("#contactphone_tips").hide();}
		return true;
	}
    </script>
</body>
</html>