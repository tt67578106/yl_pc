<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的简历</title>
<meta name="description" content=""/>
<meta name="keywords" content="" />
</head>
<body>
	 <div class="nav-g"><a href="${ctx }/">首页</a>&gt;个人中心</div>

    <!--我的简历-->
    <div class="w1200">
        <!---->
        <div class="box-g-l-user fl bg-white user-menu">
            <div class="portrait-wrapper">
                <img src="${cdc:getImagePathO(resumeBase.image.imgpath)}" alt="" id="head_img" class="portrait"/>
                <input type="file" title="上传头像" name="file" id="fileupload" value=""/>
            </div>
            <p class="text-center c999 mt10">建议上传图片尺寸200*200</p>
            <div class="user-menu-list mt10 fl">
                <a href="${ctx}/my" class="current">我的简历</a>
                <a href="${ctx}/my/apply">面试安排</a>
                <%-- <a href="${ctx}/my/changePassword">修改密码</a> --%>
            </div>
        </div>
        <!--/-->	 
	    <!--简历-->
        <div class="box-g-r-user fr bg-white f14">
            <!--编辑模式-->
            <div class="resume-form show">
            <form action="${ctx}/my/resume" method="post" id="resume_form">
            	<input type="hidden" name="nativeProvinceid" value="${resumeBase.nativeProvinceid }"/>
	        	<input type="hidden" name="nativeCityid" id="nativeCityid" value="${resumeBase.nativecity.id}"/>
	        	<input type="hidden" name="nation" value="${resumeBase.nation }"/>
				<input type="hidden" name="education" value="${resumeBase.education}"/>
				<input type="hidden" name="jobTargetProvinceid" value="${resumeBase.jobTargetProvinceid}"/>
				<input type="hidden" name="jobTargetCityid" value="${resumeBase.jobTargetCityid}"/>
				<input type="hidden" name="jobTargetProvinceName" value="${resume.jobTargetProvinceName}"/>
				<input type="hidden" name="jobTargetCityName" value="${resume.jobTargetCityName}"/>
				<input type="hidden" name="intentionSalary" value="${resumeBase.intentionSalary}"/>
				<input type="hidden" name="jobTarget" value="${resumeBase.jobTarget}">
           		<input type="hidden" name="residentProvinceid" value="${resume.residentProvinceid }"/>
	        	<input type="hidden" name="residentCityid" value="${resume.residentCityid }"/>
	        	<input type="hidden" name="nativeProvinceName" value="${resume.nativeProvinceName }"/>
	        	<input type="hidden" name="nativeCityName" value="${resume.nativeCityName }"/>
	        	<input type="hidden" name="residentProvinceName" value="${resume.residentProvinceName }"/>
	        	<input type="hidden" name="residentCityName" value="${resume.residentCityName }"/>
	        	<input type="hidden" name="employmentStatus" value="${resume.employmentStatus }"/>
	        	<input type="hidden" name="maritalStatus" value="${resume.maritalStatus }"/>
	        	<input type="hidden" name="startWorkYear" value="${resume.startWorkYear }"/>
           		<!---->
                <div class="title-orange">基本信息</div>
                <!--姓名-->
                <div class="form-each">
                	<span class="form-each-name">姓名：</span><input type="text" class="form-each-input" placeholder="请输入姓名" name="name" id="input_name" value="${resumeBase.name }" maxlength="12"/>
                	<div class="form-tips error err_name hide"><div class="tips-inner"></div></div>
                </div>
                <!--/姓名-->
                
                <!--求职状态-->
                <div class="form-each">
                	<span class="form-each-name">求职状态：</span>
                    <div class="select-g select-g-1">
                      <div class="select-g-title">
                      	<c:if test="${resumeBase.employmentStatus == null}"><span>请选择您的求职状态</span></c:if>
                       	<c:if test="${resumeBase.employmentStatus != null}"><span>${cdc:employmentStatusName(resumeBase.employmentStatus)}</span></c:if>
						<i></i>
                      </div>
                      <div class="select-g-options employmentStatus-options">
                          <ul>
	                        <c:forEach items="${employmentStatusList}" var="employmentStatus">
								<li code="${employmentStatus.code}">${employmentStatus.name}</li>
							</c:forEach>
                          </ul>
                      </div>
                    </div>
                </div>
                <!--/求职状态-->
                
                <!--工作年限-->
                <div class="form-each">
                	<span class="form-each-name">工作年限：</span>
                    <div class="select-g select-g-2">
                      <div class="select-g-title">
                      	<c:if test="${resumeBase.startWorkYear == null}"><span>请选择您的工作年限</span></c:if>
                       	<c:if test="${resumeBase.startWorkYear != null}"><span>${resumeBase.startWorkYear}</span></c:if>
						<i></i>
                      </div>
                      <div class="select-g-options workYear-options">
                          <ul>
	                        <c:forEach items="${workYearList}" var="workYear">
								<li>${workYear.name}</li>
							</c:forEach>
                          </ul>
                      </div>
                    </div>
                </div>
                <!--/工作年数-->
                
                <!--身份证号码-->
                <div class="form-each">
                	<span class="form-each-name">身份证号码：</span><input type="text" class="form-each-input" placeholder="请输入身份证号码" name="idCard" id="input_idCard" value="${resumeBase.idCard }"/>
                	<div class="form-tips error err_id_card hide"><div class="tips-inner"></div></div>
                </div>
                <!--/身份证号码-->
                
                <!--民族-->
                <div class="form-each">
                	<span class="form-each-name">民族：</span>
                    <div class="select-g select-g-3">
                      <div class="select-g-title">
                      	<c:if test="${resumeBase.nation == null}"><span>请选择民族</span></c:if>
                       	<c:if test="${resumeBase.nation != null}"><span>${cdc:converDicNation(resumeBase.nation)}</span></c:if>
						<i></i>
                      </div>
                      <div class="select-g-options nation-options">
                          <ul>
	                        <c:forEach items="${nationList}" var="nation">
								<li code="${nation.code}">${nation.name}</li>
							</c:forEach>
                          </ul>
                      </div>
                    </div>
                </div>
                <!--/民族-->
                
               <!--婚姻状况-->
                <div class="form-each">
                	<span class="form-each-name">婚姻状况：</span>
                    <div class="select-g select-g-4">
                      <div class="select-g-title">
                      	<c:if test="${resumeBase.maritalStatus == null}"><span>请选择您的婚姻状况</span></c:if>
                       	<c:if test="${resumeBase.maritalStatus != null}"><span>${cdc:maritalStatusName(resumeBase.maritalStatus)}</span></c:if>
						<i></i>
                      </div>
                      <div class="select-g-options maritalStatus-options">
                          <ul>
	                        <c:forEach items="${maritalStatusList}" var="maritalStatus">
								<li code="${maritalStatus.code}">${maritalStatus.name}</li>
							</c:forEach>
                          </ul>
                      </div>
                    </div>
                </div>
                <!--/婚姻状况-->
                
                <!--现居地址-->
                <div class="form-each">
                	<span class="form-each-name">现居地址：</span>
                    <div class="select-g select-g-5 w150">
                      <div class="select-g-title">
                      	<c:if test="${empty resumeBase.residentProvinceName }"><span>请选择省</span></c:if>
                       	<c:if test="${not empty resumeBase.residentProvinceName }"><span>${resumeBase.residentProvinceName}</span></c:if>
						<i></i>
					  </div>
                      <div class="select-g-options ul_province">
                          <ul>
                          <c:forEach items="${provinceList}" var="province">
							<li code="${province.id}" class="li_province">
								<span>${province.abbreviation}</span><i></i>
							</li>
						  </c:forEach>
                          </ul>
                      </div>
                    </div>
                    
                    <div class="select-g select-g-5 w150 ml32">
                      <div class="select-g-title selected_city">
						<c:if test="${empty resumeBase.residentCityName }"><span>请选择市</span></c:if>
                       	<c:if test="${not empty resumeBase.residentCityName }"><span>${resumeBase.residentCityName}</span></c:if>
						<i></i>
					  </div>
                      <div class="select-g-options ul_city_list">
                      </div>
                    </div>
                </div>
                <!--/现居地址-->

                <!--户籍地址-->
                <div class="form-each">
                	<span class="form-each-name">户籍地址：</span>
                    <div class="select-g select-g-6 w150">
                      <div class="select-g-title">
                      	<c:if test="${empty resumeBase.nativeProvinceName }"><span>请选择省</span></c:if>
                       	<c:if test="${not empty resumeBase.nativeProvinceName }"><span>${resumeBase.nativeProvinceName}</span></c:if>
						<i></i>
					  </div>
                      <div class="select-g-options ul_province">
                          <ul>
                          <c:forEach items="${provinceList}" var="province">
							<li code="${province.id}" class="li_province">
								<span>${province.abbreviation}</span><i></i>
							</li>
						  </c:forEach>
                          </ul>
                      </div>
                    </div>
                    
                    <div class="select-g select-g-6 w150 ml32">
                      <div class="select-g-title selected_city">
						<c:if test="${empty resumeBase.nativeCityName }"><span>请选择市</span></c:if>
                       	<c:if test="${not empty resumeBase.nativeCityName }"><span>${resumeBase.nativeCityName}</span></c:if>
						<i></i>
					  </div>
                      <div class="select-g-options ul_city_list">
                      </div>
                    </div>
                    
                </div>
                <!--/户籍地址-->
                <div class="clear"></div>
                <div id="div_intention" <c:if test="${not empty resumeBase.employmentStatus&&resumeBase.employmentStatus==2 }">class="hide"</c:if>>
                <div class="title-orange mt30">求职期望</div>
                <!--期望城市-->
                <!--期望城市-->
                <div class="form-each">
                	<span class="form-each-name">期望城市：</span>
                    <div class="select-g select-g-7 w150">
                      <div class="select-g-title">
                      	<c:if test="${empty resumeBase.jobTargetProvinceName }"><span>请选择省</span></c:if>
                       	<c:if test="${not empty resumeBase.jobTargetProvinceName }"><span>${resumeBase.jobTargetProvinceName}</span></c:if>
						<i></i>
					  </div>
                      <div class="select-g-options ul_province">
                          <ul>
                          <c:forEach items="${provinceList}" var="province">
							<li code="${province.id}" class="li_province">
								<span>${province.abbreviation}</span><i></i>
							</li>
						  </c:forEach>
                          </ul>
                      </div>
                    </div>
                    
                    <div class="select-g select-g-7 w150 ml32">
                      <div class="select-g-title selected_city">
                      	<c:if test="${empty resumeBase.jobTargetCityName}"><span>请选择市</span></c:if>
                       	<c:if test="${not empty resumeBase.jobTargetCityName}"><span>${resumeBase.jobTargetCityName}</span></c:if>
						<i></i>
                      </div>
                      <div class="select-g-options ul_city_list">
                      </div>
                    </div>
                </div>
                <!--/期望城市-->
                <!--期望工作-->
                <div class="form-each">
                	<span class="form-each-name">期望工作：</span>
                    <div class="select-g select-g-8">
                      <div class="select-g-title">
                      	<c:if test="${empty resumeBase.jobTarget}"><span data-default="请选择您期望从事的工作">请选择您期望从事的工作</span></c:if>
                       	<c:if test="${not empty resumeBase.jobTarget}"><span>${resumeBase.jobTarget}</span></c:if>
						<i></i>
                      </div>
                      <div class="select-g-options jobTarget-options">
                          <ul>
	                        <c:forEach items="${jobTypes}" var="type">
								<li code="${type.jobName}">${type.jobName}</li>
							</c:forEach>
                          </ul>
                      </div>
                    </div>
                </div>
                <!--/期望工作-->
                <div class="form-each">
                	<span class="form-each-name">期望薪资：</span>
                    <div class="select-g select-g-9">
                      <div class="select-g-title">
                      	<c:if test="${empty resumeBase.intentionSalary}"><span data-default="请选择您期望的薪资">请选择您期望的薪资</span></c:if>
                       	<c:if test="${not empty resumeBase.intentionSalary}"><span>${resumeBase.intentionSalary}</span></c:if>
						<i></i>
                      </div>
                      <div class="select-g-options salary-options">
                          <ul>
	                      <c:forEach items="${salaryList}" var="salary">
	        			  	<li code="${salary.code}">${salary.name}</li>
	        			  </c:forEach>
                          </ul>
                      </div>
                    </div>
                </div>
                <div class="clear"></div>
                </div>
                <div class="title-orange mt30">最高学历</div>
                <!--学历-->
                <div class="form-each">
                	<span class="form-each-name">学历水平：</span>
                    <div class="select-g select-g-10">
                      <div class="select-g-title">
                      	<c:if test="${resumeBase.education == null}"><span data-default="请填写您的最高学历">请填写您的最高学历</span></c:if>
                        <c:if test="${resumeBase.education != null}"><span>${cdc:converDicEducation(resumeBase.education)}</span></c:if>
						<i></i>
					  </div>
                      <div class="select-g-options edu-options">
                          <ul>
	                        <c:forEach items="${educationList }" var="education">
								<li code="${education.code}">${education.name }</li>
							</c:forEach>
                          </ul>
                      </div>
                    </div>
                </div>
                <!--/学历-->
                <!---->
                <div class="form-btn"><input type="submit" value="保存简历" class="btn-g btn-g-small w120 ml130 mt20"/></div>
            </form>
            </div>
            <!--/编辑模式-->
        </div>
        <!--/简历-->
    	<div class="clear"></div>
    </div>
    <!--/我的简历-->
	<script src="${ctx}/static/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script src="${ctx}/static/jquery-file-upload/js/jquery.iframe-transport.js"></script>
	<script src="${ctx}/static/jquery-file-upload/js/jquery.fileupload.js"></script>
   	<script src="${ctx}/static/js/localResizeIMG4/lrz.bundle.js"></script>
    <script type="text/javascript">
    <c:if test="${not empty msg}">alert("${msg}")</c:if>
		$(function(){
			<c:if test="${result == 'success'}">
			layer.alert('保存成功', {
			    skin: 'layui-layer-molv' //
			});
			</c:if>
			$('.edit-trigger').click(function(){
				$('.myresume').hide('slow');
				$('.myresume-edit').show('slow');
			});
			$('.show-trigger').click(function(){
				$('.myresume-edit').hide('slow');
				$('.myresume').show('slow');
			});
			
			$(".select-g-options li").click(function() {
				$(this).parent().prev('.select-g-title').find('span').text($(this).text());	
			});
			
			$('.portrait').click(function(){
				$('.portrait-wrapper input').trigger('click');
				
			});
			$("#input_name").blur(function(){
				var text = $(this).val();
				if(text.length>0&&!/((^[\u4E00-\u9FA5]{2,12}$)|(^[a-zA-Z]{3,24}$))/.test(text)){
					$('.form-tips').hide();
					$('.err_name').show();
					$('.err_name').find('.tips-inner').html("<i></i>姓名格式不正确（2-12位的汉字或3-24位的英文）");
					$("#input_name").val("");
				}
			});
			$("#input_idCard").blur(function(){
				var text = $(this).val();
				var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
				var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])(\d{3})(\d|X)$/;
				if(text.length>0&&(!isIDCard1.test(text) && !isIDCard2.test(text))){
					$('.form-tips').hide();
					$('.err_id_card').show();
					$('.err_id_card').find('.tips-inner').html("<i></i>身份证号码格式不正确");
					$("#input_idCard").val("");
				}
			});
			/*省市选择*/
			$('.ul_province li').click(function(){
				var selectedCity = $(this).parent().parent().parent().parent().find('.selected_city').find('span');
				var ulCityList = $(this).parent().parent().parent().parent().find('.ul_city_list');
				var provinceName='';
				var cityName='';
				var provinceId = $(this).attr("code");
				provinceName=$.trim($(this).text());
				$.get("${ctx}/my/cityList",{"provinceId":provinceId},function(result){
					var lis = [];
					lis.push('<ul>');
					$.each(result,function(i,j){
						lis.push('<li code="'+j.id+'">'+j.abbreviation+'</li>');
					});
					lis.push('</ul>');
					$(ulCityList).empty();
					$(ulCityList).append(lis.join(''));
					$(selectedCity).html('请选择市');
					/* $('.selected_city').html('<span>请选择市</span>'); */
					//点击选择某个城市后执行
				 	$('.ul_city_list li').click(function(){
				 		cityName=$(this).text();
				 		$(this).parent().parent().hide();
						$(this).parent().parent().parents('.select-g').removeClass('open');
						$(this).parent().parent().parent().find('.selected_city').find('span').html(cityName);
						if($(this).parent().parent().parent().parent().find('.form-each-name').text()=="现居地址："){
							$("input[name='residentProvinceid']").val(provinceId);
							$("input[name='residentCityid']").val($(this).attr("code"));
							$("input[name='residentProvinceName']").val(provinceName);
							$("input[name='residentCityName']").val(cityName);
						}else if($(this).parent().parent().parent().parent().find('.form-each-name').text()=="户籍地址："){
							$("input[name='nativeProvinceid']").val(provinceId);
							$("input[name='nativeCityid']").val($(this).attr("code"));
							$("input[name='nativeProvinceName']").val(provinceName);
							$("input[name='nativeCityName']").val(cityName);
						}else if($(this).parent().parent().parent().parent().find('.form-each-name').text()=="期望城市："){
							$("input[name='jobTargetProvinceid']").val(provinceId);
							$("input[name='jobTargetCityid']").val($(this).attr("code"));
							$("input[name='jobTargetProvinceName']").val(provinceName);
							$("input[name='jobTargetCityName']").val(cityName);
						}
					});  
				});
			});
			/* 求职状态 */
			$('.employmentStatus-options li').click(function(){
				$("input[name='employmentStatus']").val($(this).attr("code"));
				if($(this).attr("code")==2){
					$("#div_intention").addClass("hide");
				}else{
					$("#div_intention").removeClass("hide");
				}
			});
			/* 工作年限 */
			$('.workYear-options li').click(function(){
				$("input[name='startWorkYear']").val($(this).text());
			});
			/* 婚姻状况 */
			$('.maritalStatus-options li').click(function(){
				$("input[name='maritalStatus']").val($(this).attr("code"));
			});
			/*民族*/
			$('.nation-options li').click(function(){
				$("input[name='nation']").val($(this).attr("code"));
			});
			/*学历选择*/
			$('.edu-options li').click(function(){
				$("input[name='education']").val($(this).attr("code"));
			});
			/*薪资选择*/
			$('.salary-options li').click(function(){
				$("input[name='intentionSalary']").val($(this).attr("code"));
			});

			/*籍贯省*/
			$('.province-options li').click(function(){
				$("input[name='nativeProvinceid']").val($(this).attr("code"));
			});
			
			/*去哪里工作*/
			/* $('.jobTargetProvince li').click(function(){
				$("input[name='jobTargetProvinceid']").val($(this).attr("code"));
			}); */
			/*籍贯市*/
			$('.city-option').click(function(){
				alert($(this).attr("code"));
				$("input[name='nativeCityid']").val($(this).attr("code"));
			});
			/*做什么工作*/
			$('.jobTarget-options li').click(function(){
				$("input[name='jobTarget']").val($(this).attr("code"));
			});
			/*输入框聚焦*/
			$(".form-g-input").bind("focus", function() {
		        $(this).parents(".form-g-each").addClass("focus");
		    });
			$("#fileupload").change(function() {
		    	$("#head_img").attr("src","${ctx}/static/images/loading.gif");
				var that = this;
				if(that.value==""){
					var headPath =IMAGE_FILE_URL +"o/"+'${imagePath}';
		    		$("#head_img").attr({"src":headPath});
				}else{
					 lrz(that.files[0], {
					        width: 1024
					    }).then(function (rst) {
							$("#head_img").attr({"src":rst.base64,"data-src":rst.base64});
							$.post("${ctx}/my/saveHeadImage",{"headData":rst.base64.substr(23)});
					        return rst;
					    });
				}
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
    </script>
</body>
</html>