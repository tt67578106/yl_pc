<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请认证</title>
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
                 <c:if test="${certification.status==0 }">
                 	<div class="ent-no-result">
                		<span class="icon-smile"><i></i></span>您以于<a href="javascript:void(0);" class="ml10">${certification.createTimeString }</a>提交申请，工作人员将在24小时<br/>内审核，如有疑问，请拨打<a href="javascript:void(0);" class="ml10">4008-777-816.</a>
               		</div>
                </c:if>
                <c:if test="${certification.status==1 }">
                    <div class="ent-no-result">
                		<span class="icon-smile"><i></i></span>恭喜您，您的认证信息审核通过，快去<a href="${ctx }/enterpriseJob/jobPost" class="ml10">发布岗位</a>吧！
               		</div>
                </c:if>
                <c:if test="${certification.status==2 }">
                    <div class="ent-no-result">
                		<span class="icon-smile"><i></i></span>申请认证失败，${certificationWorkOrder.remark } <a href="javascript:void(0)" id="refSave" class="ml10">重新认证</a>
                		<input type="hidden" id="hid_ref" value="${certification.id}"/>
               		</div>
                </c:if>
            	<!--申请认证-->
            	<div class="ent-data">
            	<c:if test="${empty certification||empty certification.status||certification.status==3 }">
            	<form action="javascript:void(0);" id="form_data" method="post">
                	<input type="hidden" name="certifyToken" value="${certifyToken }"/>
                	<!--第一步-->
                    <div class="ent-step show">
                    	 <div class="ent-progress fl">
                            <ul class="step-first">
                                <li class="first"><strong>1</strong>企业用户信息<i class="r"></i></li>
                                <li class="second"><strong>2</strong>对公账户信息<i class="r"></i></li>
                                <li class="third"><strong>3</strong>法人信息<i class="r"></i></li>
                                <li class="fourth"><strong>4</strong>申请人信息<i class="r"></i></li>
                            </ul>
                        </div>
                        <!--企业全称-->
                        <div class="form-each"><span class="form-each-name">企业全称：</span><input type="text" readonly="readonly" name="enterpriseFullName" value="${enterprise.name  }" class="form-each-input c999"/></div>
                        <!--/企业全称-->
                        <!--企业地址-->
                        <div class="form-each"><span class="form-each-name">企业地址：</span><span class="form-each-input c999">${cdc:convertProvinceName(enterprise.provinceid) } ${enterprise.city.cityName } ${cdc:converDistrictName(enterprise.countyid) } ${enterprise.address}</span></div>
                        <!--/企业地址-->
                        <!--联系电话-->
                        <div class="form-each"><span class="form-each-name">联系电话：</span><span class="form-each-input c999">${enterprise.contactPhone }</span></div>
                        <!--/联系电话-->
                        <!--申请公函-->
                        <div class="form-each upload">
                            <span class="form-each-name"><label class="mark">*</label>申请公函：</span>
                            <span class="form-each-input" id="span_applicationLeterImg">${fn:split(certification.applicationLeterImg ,"/")[fn:length(fn:split(certification.applicationLeterImg ,"/"))-1] }</span>
                            <input type="file"  id="applicationLeterImg" class="file" name="file" />
                            <input type="button" class="btn-g-3" value="上传"/>
                            <div class="thumbnail">
                            	<div class="type1">
                                	<p class="text-center mb10">申请公函</p>
                                	<c:if test="${empty  certification.applicationLeterImg}">
                                		<a href="${ctx }/static/images/datas/example1.jpg" target="_blank"><img src="${ctx }/static/images/datas/example1.jpg" /></a>
                                	</c:if>
                                	<c:if test="${not empty  certification.applicationLeterImg}">
                                		<a href="${certification.applicationLeterImg}" target="_blank"><img src="${certification.applicationLeterImg}" /></a>
                                	</c:if>
                                </div>
                            </div>
                            <div class="form-each-tips hide" id="applicationLeterImg_tips"></div>
                        </div>
                        <p class="form-each-des">
                            为确保企业招聘过程合法有效，请提供由您所在企业行政人事部门提供的书面证明扫描件，加盖企业公章后上传。
                        </p>
                        <!--/申请公函-->
                        <div class="hr-g-3 fl mb0"></div>
                        <!--营业执照注册号-->
                        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>营业执照注册号：</span><input type="text" id="txt_businessLicenseNum" name="businessLicenseNum" value="${certification.businessLicenseNum }" class="form-each-input" maxlength="50"/></div>
                        <div class="form-each-tips hide" id="businessLicenseNum_tips"></div>
                        <!--/营业执照注册号-->
                        <!--营业执照扫描件-->
                        <div class="form-each upload">
                            <span class="form-each-name"><label class="mark">*</label>营业执照扫描件：</span>
                            <span class="form-each-input" id="span_businessLicenseImg">${fn:split(certification.businessLicenseImg ,"/")[fn:length(fn:split(certification.businessLicenseImg ,"/"))-1] }</span>
                            <input type="file" id="businessLicenseImg" class="file" name="file"/>
                            <input type="button" class="btn-g-3" value="上传"/>
                            <div class="thumbnail">
                            	<div class="type2">
                            		<c:if test="${empty certification.businessLicenseImg }">
                                		<a href="${ctx }/static/images/datas/example2.jpg" target="_blank"><img src="${ctx }/static/images/datas/example2.jpg" /></a>
                                    </c:if>
                                    <c:if test="${not empty certification.businessLicenseImg }">
                                		<a href="${certification.businessLicenseImg }" target="_blank"><img src="${certification.businessLicenseImg }" /></a>
                                    </c:if>
                                    <p class="text-center mt10">营业执照预览</p>
                                </div>
                           </div>
                            <div class="form-each-tips hide" id="businessLicenseImg_tips"></div>
                        </div>
                        <!--/营业执照扫描件-->
                        <!--组织机构代码-->
                        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>组织机构代码：</span><input type="text" id="txt_organizationCode" name="organizationCode" value="${certification.organizationCode }" class="form-each-input" maxlength="50"/></div>
                        <div class="form-each-tips hide" id="organizationCode_tips"></div>
                        <!--/组织机构代码-->
                        <!--组织机构代码证-->
                         <div class="form-each upload">
                            <span class="form-each-name"><label class="mark">*</label>组织机构代码证：</span>
                            <span class="form-each-input" id="span_organizationCodeImg">${fn:split(certification.organizationCodeImg ,"/")[fn:length(fn:split(certification.organizationCodeImg ,"/"))-1] }</span>
                            <input type="file" class="file" name="file" id="organizationCodeImg"/>
                            <input type="button" class="btn-g-3" value="上传"/>
                            <div class="thumbnail">
                            	<div class="type2" style="top:-20px;">
                            		<c:if test="${empty certification.organizationCodeImg }">
                                		<a href="${ctx }/static/images/datas/example3.jpg" target="_blank"><img src="${ctx }/static/images/datas/example3.jpg" /></a>
                                    </c:if>
                                    <c:if test="${not empty certification.organizationCodeImg }">
                                		<a href="${certification.organizationCodeImg}" target="_blank"><img src="${certification.organizationCodeImg}" /></a>
                                    </c:if>
                                    <p class="text-center mt10">组织机构代码证预览</p>
                                </div>
                           </div>
                           <div class="form-each-tips hide" id="organizationCodeImg_tips"></div>
                        </div>
                        <!--/组织机构代码证-->
                        <div class="form-btn"><input type="button" id="button_step1" value="下一步" class="btn-g-2"/></div>
                    </div>
                    <!--/第一步-->
                    
                    <!--第二步-->
                    <div class="ent-step">
                    	<div class="ent-progress fl">
                            <ul class="step-second">
                                <li class="first"><strong>1</strong>企业用户信息<i class="r"></i></li>
                                <li class="second"><strong>2</strong>对公账户信息<i class="r"></i></li>
                                <li class="third"><strong>3</strong>法人信息<i class="r"></i></li>
                                <li class="fourth"><strong>4</strong>申请人信息<i class="r"></i></li>
                            </ul>
                        </div>
                        <!--开户银行-->
                        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>开户银行：</span><input type="text" id="txt_enterpriseBank" name="enterpriseBank" value="${certification.enterpriseBank }" class="form-each-input" maxlength="50"/></div>
                      	<div class="form-each-tips hide" id="enterpriseBank_tips"></div>
                        <!--/开户银行-->
                        <!--开户支行-->
                        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>开户支行：</span><input type="text" id="txt_enterpriseBankBranch" name="enterpriseBankBranch" value="${certification.enterpriseBankBranch }" class="form-each-input" maxlength="50"/></div>
                      	<div class="form-each-tips hide" id="enterpriseBankBranch_tips"></div> 
                        <!--/开户支行-->
                        <!--企业对公账号-->
                        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>企业对公账号：</span><input type="text" id="txt_enterpriseBankAccount" name="enterpriseBankAccount" value="${certification.enterpriseBankAccount }" class="form-each-input" maxlength="50"/></div>
                        <div class="form-each-tips hide" id="enterpriseBankAccount_tips"></div> 
                        <!--/企业对公账号-->
                        <!--开户许可证-->
                        <div class="form-each upload">
                            <span class="form-each-name"><label class="mark">*</label>开户许可证：</span>
                            <span class="form-each-input" id="span_openingPermitImg">${fn:split(certification.openingPermitImg ,"/")[fn:length(fn:split(certification.openingPermitImg ,"/"))-1] }</span>
                            <input type="file" class="file" name="file" id="openingPermitImg" />
                            <input type="button" class="btn-g-3" value="上传"/>
                             <div class="thumbnail">
                            	<div class="type1">
                                	<p class="text-center mb10">证明预览</p>
                                	<c:if test="${empty certification.openingPermitImg }">
                                		<a href="${ctx }/static/images/datas/example4.jpg" target="_blank"><img src="${ctx }/static/images/datas/example4.jpg" /></a>
                                	</c:if>
                                	<c:if test="${not empty certification.openingPermitImg }">
                                		<a href="${certification.openingPermitImg }" target="_blank"><img src="${certification.openingPermitImg }" /></a>
                                	</c:if>
                                </div>
                            </div>
                             <div class="form-each-tips hide" id="openingPermitImg_tips"></div> 
                        </div>
                        <p class="form-each-des">
                           	 为确保企业招聘过程合法有效，请提供由您所在企业行政人事部门提供的书面证明扫描件，加盖企业公章后上传。
                        </p>
                        <!--/开户许可证-->
                        <div class="form-btn"><input type="button" id="button_step2" value="下一步" class="btn-g-2"/><a href="javascript:void(0);" class="back-g">返回修改</a></div>
                    </div>
                    <!--/第二步-->
                    
                    <!--第三步-->
                    <div class="ent-step">
                    	<div class="ent-progress fl">
                            <ul class="step-third">
                                <li class="first"><strong>1</strong>企业用户信息<i class="r"></i></li>
                                <li class="second"><strong>2</strong>对公账户信息<i class="r"></i></li>
                                <li class="third"><strong>3</strong>法人信息<i class="r"></i></li>
                                <li class="fourth"><strong>4</strong>申请人信息<i class="r"></i></li>
                            </ul>
                        </div>
                        <!--法人真实姓名-->
                        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>法人真实姓名：</span><input type="text" id="txt_legalRepresentative" name="legalRepresentative" value="${certification.legalRepresentative }" class="form-each-input" maxlength="50"/></div>
                        <div class="form-each-tips hide" id="legalRepresentative_tips"></div> 
                        <!--/法人真实姓名-->
                        <!--法人身份证号码-->
                        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>法人身份证号码：</span><input type="text" id="txt_legalIdCard" name="legalIdCard" value="${certification.legalIdCard }" class="form-each-input" maxlength="20"/></div>
                        <div class="form-each-tips hide" id="legalIdCard_tips"></div> 
                        <!--/法人身份证号码-->
                        <!--身份证正面图片-->
                        <div class="form-each upload">
                            <span class="form-each-name"><label class="mark">*</label>身份证正面图片：</span>
                            <span class="form-each-input" id="span_legalIdCardFrontImg">${fn:split(certification.legalIdCardFrontImg ,"/")[fn:length(fn:split(certification.legalIdCardFrontImg ,"/"))-1] }</span>
                            <input type="file" class="file" id="legalIdCardFrontImg" name="file" />
                            <input type="button" class="btn-g-3" value="上传"/>
                            <div class="thumbnail">
                            	<div class="type3">
                            		<c:if test="${empty certification.legalIdCardFrontImg }">
                                		<a href="${ctx }/static/images/datas/example5.jpg" target="_blank"><img src="${ctx }/static/images/datas/example5.jpg" /></a>
                                   	</c:if>
                                   	<c:if test="${not empty certification.legalIdCardFrontImg }">
                                		<a href="${certification.legalIdCardFrontImg }" target="_blank"><img src="${certification.legalIdCardFrontImg }" /></a>
                                   	</c:if>
                                    <p class="text-center mt10">身份证正面预览</p>
                                </div>
                            </div>
                            <div class="form-each-tips hide" id="legalIdCardFrontImg_tips"></div> 
                        </div>
                        <!--/身份证正面图片-->
                        <!--身份证反面图片-->
                        <div class="form-each upload">
                            <span class="form-each-name"><label class="mark">*</label>身份证反面图片：</span>
                            <span class="form-each-input" id="span_legalIdCardBackImg">${fn:split(certification.legalIdCardBackImg ,"/")[fn:length(fn:split(certification.legalIdCardBackImg ,"/"))-1] }</span>
                            <input type="file" class="file" name="file" id="legalIdCardBackImg" />
                            <input type="button" class="btn-g-3" value="上传"/>
                            <div class="thumbnail">
                            	<div class="type4">
                            		<c:if test="${empty certification.legalIdCardBackImg }">
                                		<a href="${ctx }/static/images/datas/example6.jpg" target="_blank"><img src="${ctx }/static/images/datas/example6.jpg" /></a>
                                    </c:if>
                                    <c:if test="${not empty certification.legalIdCardBackImg }">
                                		<a href="${certification.legalIdCardBackImg }" target="_blank"><img src="${certification.legalIdCardBackImg }" /></a>
                                    </c:if>
                                    <p class="text-center mt10">身份证反面预览</p>
                                </div>
                            </div>
                            <div class="form-each-tips hide" id="legalIdCardBackImg_tips"></div> 
                        </div>
                        <p class="form-each-des">
                         	   为确保企业招聘过程合法有效，请提供由您所在企业行政人事部门提供的书面证明扫描件，加盖企业公章后上传。
                        </p>
                        <!--/身份证反面图片-->
                        <!--开户许可证-->
                        
                        <!--/开户许可证-->
                        <div class="form-btn"><input type="button" id="button_step3" value="下一步" class="btn-g-2"/><a href="javascript:void(0);" class="back-g">返回修改</a></div>
                    </div>
                    <!--/第三步-->
                    
                    <!--第四步-->
                    <div class="ent-step">
                    	<div class="ent-progress fl">
                            <ul class="step-fourth">
                                <li class="first"><strong>1</strong>企业用户信息<i class="r"></i></li>
                                <li class="second"><strong>2</strong>对公账户信息<i class="r"></i></li>
                                <li class="third"><strong>3</strong>法人信息<i class="r"></i></li>
                                <li class="fourth"><strong>4</strong>申请人信息<i class="r"></i></li>
                            </ul>
                        </div>
                        <!--申请人姓名-->
                        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>申请人姓名：</span><input type="text" id="txt_applicantName" name="applicantName" value="${certification.applicantName }" class="form-each-input" maxlength="50"/></div>
                        <div class="form-each-tips hide" id="applicantName_tips"></div> 
                        <!--/申请人姓名-->
                        <!--申请人手机号-->
                        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>申请人手机号：</span><input type="text" id="txt_applicantMobile" name="applicantMobile" value="${certification.applicantMobile }" class="form-each-input" maxlength="20"/></div>
                        <div class="form-each-tips hide" id="applicantMobile_tips"></div> 
                        <!--/申请人手机号-->
                        <div class="form-btn"><input type="button" id="button_step4" value="完成" class="btn-g-green btn-g-2"/><a href="javascript:void(0);" class="back-g">返回修改</a></div>
                    </div>
                    <!--/第四步-->
                    <div class="ent-no-result ent-step">
                	<span class="icon-smile"><i></i></span>您以于<a href="javascript:void(0);" class="ml10">${certification.createTimeString }</a>提交申请，工作人员将在24小时<br/>内审核，如有疑问，请拨打<a href="javascript:void(0);" class="ml10">4008-777-816.</a>
               		</div>
                     <!--第四步-->
                     <div class="clear"></div>
                   </form>
                </c:if>
                </div>	
                <!--/申请认证-->
            </div>
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
    <!---->
    <script src="${ctx}/static/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script src="${ctx}/static/jquery-file-upload/js/jquery.iframe-transport.js"></script>
    <script src="${ctx}/static/jquery-file-upload/js/jquery.fileupload.js"></script>
    <script>
    var validateMark=0;
    $(function(){
    	window.onbeforeunload = function(e) {
    		return '您的信息未保存';
    	}; 
    	<c:if test="${not empty error}">
			alert("${error}");
		</c:if>
		
		$("#refSave").click(function(){
    		$.post("${ctx }/enterprise/recertify",{certificationId:$("#hid_ref").val()},function(result){window.location.reload();});
    		window.onbeforeunload =null;
    	})
    	//上传文件
    	$('.ent-data .upload .form-each-input,.ent-data .upload .btn-g-3').click(function(){
    		$(this).parents('.form-each').find('.file').trigger('click');
    	});
    	//上一步
    	$('.form-btn .back-g').click(function(){
    		$(this).parents('.ent-step').hide().prev('.ent-step').show();
    	});
    	$(".file").click(function(){
    		var url="${ctx}/enterprise/upload/certifyImage?key="+$(this).attr("id");
    		uploadImage(url);
    	 });
    	
    	$("#txt_businessLicenseNum").blur(function(){
    		if(!/^\d{15}/.test($(this).val())){
    			$("#businessLicenseNum_tips").show();
    			$("#businessLicenseNum_tips").html("请输入正确的营业执照注册号（营业执照注册号为15位数字）");
    		}else{$("#businessLicenseNum_tips").hide();}
    	});//营业执照注册号
    	$("#txt_organizationCode").blur(function(){
    		if(!/^[a-zA-Z\d]{8}\-[a-zA-Z\d]$/.test($(this).val())){
    			$("#organizationCode_tips").show();
    			$("#organizationCode_tips").html("请输入正确的组织机构代码（格式为xxxxxxxx-x）");
    		}else{$("#organizationCode_tips").hide();}
    	});//组织机构代码 
    	$("#txt_enterpriseBank").blur(function(){
    		if(!/((^[\u4E00-\u9FA5]{2,20}$)|(^[a-zA-Z]{2,20}$))/.test($(this).val())){
    			$("#enterpriseBank_tips").show();
    			$("#enterpriseBank_tips").html("请输入正确的开户银行(银行名称为2至20位的英文或汉字)");
    		}else{$("#enterpriseBank_tips").hide();}
    	});//开户银行
      	$("#txt_enterpriseBankBranch").blur(function(){
    		if(!/((^[\u4E00-\u9FA5]{2,20}$)|(^[a-zA-Z]{2,20}$))/.test($(this).val())){
    			$("#enterpriseBankBranch_tips").show();
    			$("#enterpriseBankBranch_tips").html("请输入正确的开户支行(银行支行名称为2至20位的英文或汉字)");
    			$(this).focus();
    		}else{$("#enterpriseBankBranch_tips").hide();}
    	});//开户银行
      	$("#txt_enterpriseBankAccount").blur(function(){
    		if(!/^\d{16}|\d{19}$/.test($(this).val())){
    			$("#enterpriseBankAccount_tips").show();
    			$("#enterpriseBankAccount_tips").html("请输入正确的企业对公账号(银行账号为16或19位的数字)");
    		}else{$("#enterpriseBankAccount_tips").hide();}
    	});//对公账号 
     	$("#txt_legalRepresentative").blur(function(){
    		if(!/((^[\u4E00-\u9FA5]{2,20}$)|(^[a-zA-Z]{2,20}$))/.test($(this).val())){
    			$("#legalRepresentative_tips").show();
    			$("#legalRepresentative_tips").html("请输入正确的法人真实姓名(法人真实姓名为2至20位的英文或汉字)");
    		}else{$("#legalRepresentative_tips").hide();}
    	});//法人真实姓名
     	$("#txt_legalIdCard").blur(function(){
    		if(!/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test($(this).val())){
    			$("#legalIdCard_tips").show();
    			$("#legalIdCard_tips").html("请输入正确的法人身份证号码！");
    		}else{$("#legalIdCard_tips").hide();}
    	});//法人身份证号码 
     	$("#txt_applicantName").blur(function(){
    		if(!/((^[\u4E00-\u9FA5]{2,5}$)|(^[a-zA-Z]{3,12}$))/.test($(this).val())){
    			$("#applicantName_tips").show();
    			$("#applicantName_tips").html("请输入正确的申请人姓名(申请人姓名为2至5位汉字或3至12位英文)");
    		}else{$("#applicantName_tips").hide();}
    	});//申请人姓名 
    	$("#txt_applicantMobile").blur(function(){
    		if(!/^1[3-9]\d{9}/.test($(this).val())){
    			$("#applicantMobile_tips").show();
    			$("#applicantMobile_tips").html("请输入正确的申请人手机号(输入的手机号长度或格式不对！)");
    		}else{$("#applicantMobile_tips").hide();}
    	});//申请人手机号
    });
	//form提交
	$(".form-btn .btn-g-2").click(function(){
		var step=$(this);
		if(certificationValidate(step)){
			$.post("${ctx }/enterprise/certify",$("#form_data").serialize(),function(result){
				if(result=="success"){
					step.parents('.ent-step').hide().next('.ent-step').show();
				}
				else{
					alert("网络繁忙，请稍后重试！")
				}
			});
		}
		window.onbeforeunload=null; 
	});
	 
	function certificationValidate(step){
		if(step.attr("id")=="button_step1"){
			return step1Validate();
		}
		if(step.attr("id")=="button_step2"){
			return step2Validate();
		}
		if(step.attr("id")=="button_step3"){
			return step3Validate();
		}
		if(step.attr("id")=="button_step4"){
			return step4Validate();
		}
	}
	function step1Validate() {
		var mark=true;
		//申请公函
		if($("#span_applicationLeterImg").text().length<1){
			$("#applicationLeterImg_tips").show();
			$("#applicationLeterImg_tips").html("请上传申请公函");
			return false;
			
		}else{$("#applicationLeterImg_tips").hide();}
		//营业执照注册号
		if(!/^\d{15}/.test($("#txt_businessLicenseNum").val())){
			$("#businessLicenseNum_tips").show();
			$("#businessLicenseNum_tips").html("请输入正确的营业执照注册号（营业执照注册号为15位数字）");
			$("#txt_businessLicenseNum").focus();
			return false;
		}else{$("#businessLicenseNum_tips").hide();}
		//营业执照扫描件
		if($("#span_businessLicenseImg").text().length<1){
			$("#businessLicenseImg_tips").show();
			$("#businessLicenseImg_tips").html("请上传营业执照扫描件");
			return false;
		}else{$("#businessLicenseImg_tips").hide();}
		//组织机构代码
		if(!/^[a-zA-Z\d]{8}\-[a-zA-Z\d]$/.test($("#txt_organizationCode").val())){
			$("#organizationCode_tips").show();
			$("#organizationCode_tips").html("请输入正确的组织机构代码（格式为xxxxxxxx-x）");
			$("#txt_organizationCode").focus();
			return false;
		}else{$("#organizationCode_tips").hide();}
		//组织机构代码证
		if($("#span_organizationCodeImg").text().length<1){
			$("#organizationCodeImg_tips").show();
			$("#organizationCodeImg_tips").html("请上传组织机构代码证");
			return false;
		}else{$("#organizationCodeImg_tips").hide();}
		
		return mark;
	}
	
	function step2Validate() {
		var mark=true;
		//开户银行
		if(!/((^[\u4E00-\u9FA5]{2,20}$)|(^[a-zA-Z]{2,20}$))/.test($("#txt_enterpriseBank").val())){
			$("#enterpriseBank_tips").show();
			$("#enterpriseBank_tips").html("请输入正确的开户银行（银行名称为2至20位的英文或汉字）");
			$("#txt_enterpriseBank").focus();
			return false;
		}else{$("#enterpriseBank_tips").hide();}
		//开户支行
		if(!/((^[\u4E00-\u9FA5]{2,20}$)|(^[a-zA-Z]{2,20}$))/.test($("#txt_enterpriseBankBranch").val())){
			$("#enterpriseBankBranch_tips").show();
			$("#enterpriseBankBranch_tips").html("请输入正确的开户支行（银行支行名称为2至20位的英文或汉字）");
			$("#txt_enterpriseBankBranch").focus();
			return false;
		}else{$("#enterpriseBankBranch_tips").hide();}
		//对公账号
		if(!/^\d{16}|\d{19}$/.test($("#txt_enterpriseBankAccount").val())){
			$("#enterpriseBankAccount_tips").show();
			$("#enterpriseBankAccount_tips").html("请输入正确的企业对公账号（企业对公银行账号为16或19位数字）");
			$("#txt_enterpriseBankAccount").focus();
			return false;
		}else{$("#enterpriseBankAccount_tips").hide();}
		//开户许可证
		if($("#span_openingPermitImg").text().length<1){
			$("#openingPermitImg_tips").show();
			$("#openingPermitImg_tips").html("请上传企业的开户许可证");
			return false;
		}else{$("#openingPermitImg_tips").hide();}
		return mark;
	}
	function step3Validate() {
		var mark=true;
		//法人真实姓名
		if(!/((^[\u4E00-\u9FA5]{2,20}$)|(^[a-zA-Z]{2,20}$))/.test($("#txt_legalRepresentative").val())){
			$("#legalRepresentative_tips").show();
			$("#legalRepresentative_tips").html("请输入正确的法人真实姓名（法人真实姓名为2至20位的英文或汉字）");
			$("#txt_legalRepresentative").focus();
			return false;
		}else{$("#legalRepresentative_tips").hide();}
		//法人真实姓名身份证号码
		if(!/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test($("#txt_legalIdCard").val())){
			$("#legalIdCard_tips").show();
			$("#legalIdCard_tips").html("请输入正确的法人身份证号码！");
			$("#txt_legalIdCard").focus();
			return false;
		}else{$("#legalIdCard_tips").hide();}
		//法人身份证正面
		if($("#span_legalIdCardFrontImg").text().length<1){
			$("#legalIdCardFrontImg_tips").show();
			$("#legalIdCardFrontImg_tips").html("请上传法人身份证正面");
			return false;
		}else{$("#legalIdCardFrontImg_tips").hide();}
		//法人身份证反面
		if($("#span_legalIdCardBackImg").text().length<1){
			$("#legalIdCardBackImg_tips").show();
			$("#legalIdCardBackImg_tips").html("请上传法人身份证反面");
			return false;
		}else{$("#legalIdCardBackImg_tips").hide();}
		return mark;
	}
	function step4Validate() {
		var mark=true;
		//申请人姓名
		if(!/((^[\u4E00-\u9FA5]{2,5}$)|(^[a-zA-Z]{3,12}$))/.test($("#txt_applicantName").val())){
			$("#applicantName_tips").show();
			$("#applicantName_tips").html("请输入正确的申请人姓名(申请人姓名为2至5位汉字或3至12位英文)");
			$("#txt_applicantName").focus();
			return false;
		}else{$("#applicantName_tips").hide();}
		
		//申请人手机号
		if(!/^1[3-9]\d{9}/.test($("#txt_applicantMobile").val())){
			$("#applicantMobile_tips").show();
			$("#applicantMobile_tips").html("请输入正确的申请人手机号(输入的手机号长度或格式不对！)");
			$("#txt_applicantMobile").focus();
			return false;
		}else{$("#applicantMobile_tips").hide();}
		return mark;
	}
	function uploadImage(url)
	{
		$('.file').fileupload({
			url : url,
			done : function(e, data) {
				if (data.result == "tomuchcount") {
					alert('请休息一下，短时间内不要上传太多图片');
				} else {
					alert("上传成功！");
					$(this).parents('.form-each').find('img').attr("src",IMAGE_FILE_URL+"320/"+data.result + "?t=" + Math.random());
					$(this).parents('.form-each').find('a').attr("href",IMAGE_FILE_URL+"320/"+data.result + "?t=" + Math.random());
					var imgpath=data.result.split("/");
					$(this).parents('.form-each').find('.form-each-input').text(imgpath[imgpath.length-1]);
					$(this).parents('.form-each').find('.form-each-tips').hide();
				}
			},
			send : function(e, data) {
				if (!valiFile(data)) {
					alert('文件不支持，图片格式限制为jpg/jpeg/png/gif，不超过3MB！');
					return false;
				}
			},
			acceptFileTypes : /(\.|\/)(jpg|png|jpeg|gif)$/i,
			maxFileSize : 3000000,
			maxNumberOfFiles : 1
		}).prop('disabled', !$.support.fileInput).parent()
		.addClass($.support.fileInput ? undefined : 'disabled');
	}
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
    </script>
</body>
</html>