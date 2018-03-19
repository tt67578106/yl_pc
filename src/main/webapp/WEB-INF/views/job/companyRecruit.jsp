<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:if test="${not empty param.search_LIKE_searchName && param.search_LIKE_searchName!='0'}">
	<c:set var="wd" value="${param.search_LIKE_searchName}" />
</c:if>
<c:if test="${not empty param.search_EQ_industry && param.search_EQ_industry!=0}">
	<c:set var="industry" value="${param.search_EQ_industry}" />
</c:if>
<c:if test="${not empty sessionScope.session_key_branch_name}">
	<c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:choose>
	<c:when test="${not empty wd }">
		<title>${wd }_${wd }招聘|招工信息</title>
		<meta name="keywords" content="${wd },${wd }招聘,${wd }招工信息"/>
		<meta name="description" content="优蓝网【${wd }招工招聘】栏目,提供${cityName }企业名录、${wd }招聘,是您查找工厂及企业信息的不错选择,了解更多${wd }招聘信息,就上${cityName }优蓝网"/>
	</c:when>
	<c:when test="${not empty industry }">
		<title>${cityName }${cdc:convertDicIndustryType(industry)}招聘|招工信息</title>
		<meta name="keywords" content="${cityName }${cdc:convertDicIndustryType(industry)}招聘,${cityName }${cdc:convertDicIndustryType(industry)}招工，${cityName }${cdc:convertDicIndustryType(industry)}招工信息"/>
		<meta name="description" content="优蓝网【${cityName }${cdc:convertDicIndustryType(industry)}招工招聘】栏目,提供${cityName }${cdc:convertDicIndustryType(industry)}企业名录、知名企业招聘,是您查找工厂及企业信息的不错选择,了解更多${cityName }${cdc:convertDicIndustryType(industry)}招聘信息,就上${cityName }优蓝网"/>
	</c:when>
	<c:otherwise>
		<title>${cityName }名企招聘_${cityName }知名企业|工厂|电子厂招工招聘</title>
		<meta name="keywords" content="${cityName }企业招聘,${cityName }工厂招工,${cityName }工厂招工信息"/>
		<meta name="description" content="优蓝网【${cityName }名企招聘】栏目,提供${cityName }企业名录、知名企业招聘,是您查找工厂及企业信息的不错选择,了解更多${cityName }企业招聘信息,就上${cityName }优蓝网"/>
	</c:otherwise>
</c:choose>
<style>
.compName{max-width: 800px !important;}
.certify-hover {right: -452px !important;}
</style>
</head>
<body>
    <!---->
    <div class="nav-g">
    	<a href="${ctx }/">首页</a>&gt;<a href="${ctx }/mingqi/">入驻名企</a>
    	<ul class="service-g right">
    		<li><span>验</span>100%真实信息</li>
    		<li><span>快</span>30分钟内电话</li>
    		<li><span>保</span>不满随时投诉</li>
    	</ul>
    </div>
    
    <!--筛选-->
    <div class="box-1200 mt10">
    	<div class="all-job">
    		<h1 id="industryAll" class="<c:if test="${empty param.search_EQ_industry || param.search_EQ_industry == ''|| param.search_EQ_industry == '0'}">active</c:if>">所有行业</h1>
    		<ul class="industrySelect">
    			<c:forEach items="${industryTypes }" var="industryType">
                    <li <c:if test="${param.search_EQ_industry == industryType.code}">class="active"</c:if>>
                    <a href="${ctx }/qy_auto_0_0_${industryType.code }_0_0_1.html" data="${industryType.code }" title="${industryType.name }">${industryType.name }</a></li>
            	 </c:forEach>
    			<div class="clear"></div>
    		</ul>
    	</div>
    </div>
    <!--/筛选-->
    
    <div class="w1200 mt10">
    	<!--基本信息-->
       <div class="box-l-wrapper">
	       	<!--筛选-->
	       	<div class="search-company">
	       		<div class="form-each relative">
	       			<input type="hidden" value="${wd }" id="wdValue"/>
            		<input type="text" class="form-each-input" placeholder="请输入公司信息" id="searchName" name="search_LIKE_searchName"/>
            		<input type="button" id = "btn_search" class = "btn-g-defalut btn-g-small ml10 w90 search-enter" value="搜索" />
                	<div class="recommend">
                		<label>热门搜索：</label>
                		<c:forEach items="${hotRecommonds }" var="recommond">
	    					<a href="${recommond.resource.linkUrl }"><span class="form-each-name">${recommond.resource.title }</span></a>
	    				</c:forEach>
                	</div>
	       		</div>
	       		<div class="clear"></div>
	       	</div>
	        <!--/筛选-->
	        
	         <!--企业列表-->
	        <c:forEach items="${companyVoList.content}" var="companyVo">
	        <div class="recruit-list">
	        	<a target="_blank"  href="${ctx}/qiye_${companyVo.companyId}.html">
	        	<h2>
	        		<span class="compName">${tc:highlightText(companyVo.companyName, wd)}</span>
	        		<c:if test="${companyVo.cooperationType==1||companyVo.cooperationType==5 }">
	        		<label class="certify-label">认证
	        		<div class="certify-hover">
            			<p class="blue f16">认证标准</p>
            			<p class="f16 c333 mt10">该企业已经过优蓝网官方认证，优蓝网确保以下信息真实可靠</p>
            			<ul class="standards clearfix">
				    		<li>该企业与优蓝网已签署官方委托协议</li>
				    		<li>优蓝网已派专人严格审核企业岗位</li>
				    		<li>优蓝网已派专属服务人员进厂入住接待</li>
				    	</ul>
				    	<p class="blue f16">专享服务</p>
				    	<ul class="specialService clearfix">
				    		<li>
				    			<div class="circle circle1"><p>全程</p><p>0费用</p></div>
				    			<p>参加面试入职</p>
				    			<p>全程无任何费用</p>
				    		</li>
				    		<li>
				    			<div class="circle circle2"><p>专属</p><p>服务</p></div>
				    			<p>匹配专属顾问</p>
				    			<p>一对一贴心服务</p>
				    		</li>
				    		<li>
				    			<div class="circle circle3"><p>全流程</p><p>覆盖</p></div>
				    			<p>从报名到入职</p>
				    			<p>全流程跟踪服务</p>
				    		</li>
				    	</ul>
            		</div>
	        		</label>
	        		</c:if>
	        	</h2>
	        	</a>
        		<ul class="mt10">
        			<a href="${ctx }/zhaopin/${companyVo.cityId }_0_0_0_1/" target="_blank"><li>${companyVo.cityName}</li></a>
        			<a href="${ctx }/qy_auto_0_0_${companyVo.industryid }_0_0_1.html" target="_blank"><li>${cdc:convertDicIndustryType(companyVo.industryid)}</li></a>
        			<a href="${ctx }/company/album_${companyVo.companyId}/" target="_blank"><li>企业实景</li></a>
        			<a href="${ctx }/zone" target="_blank"><li>企业社区</li></a>
        			<a href="${ctx }/wenda/so_0_${companyVo.companyId}_0_1/" target="_blank"><li>企业问答</li></a>
        			<a href="${ctx }/wenda/ask?companyId=${companyVo.companyId}&backUrl=mingqi" target="_blank"><li>我要提问</li></a>
        			<a href="${ctx }/company/job_${companyVo.companyId}/" target="_blank"><li>招聘信息</li></a>
        			<c:forEach items="${companyVo.jobBases}" var="jobbase" varStatus="status">
        				<c:if test="${status.index<3 }">
        					<a target="blank"  href="${ctx}/zhaopin_${jobbase.id}.html"><li>${jobbase.title }</li></a>
        				</c:if>
        			</c:forEach>
        			<div class="clear"></div>
        		</ul>
	        </div>
	        </c:forEach>
	        <tags:urlrewrite_pagination paginationSize="5" page="${companyVoList }" pageType="qy"/>
	</div>
	
	 <!--/基本信息-->
   	 <div class="box-r-wrapper company-recruit">
        	<!--最新入驻企业-->
            <div class="box-r recruit-tags  mt10 bd0">
            	<div class="title-bar"><span>最新入驻企业</span></div>
            	<ul class="about-recruit">
            	<c:forEach items="${rightRecommendCompanys }" var="company">
					<a href="${ctx}/qiye_${company.companyId}.html"><li>${company.companyName }</li></a>
				</c:forEach>
	            </ul>
            </div>
           <!--/最新入驻企业-->
           <!--热门名企招聘-->
            <div class="box-r recruit-tags  mt10 bd0">
            	<div class="title-bar"><span>热门名企招聘</span></div>
            	<ul class="about-recruit">
            		<c:forEach items="${relatedRecruitments }" var="recruitment">
		            	<a href="${recruitment.resource.linkUrl }"><li>${recruitment.resource.title }</li></a>
            		</c:forEach>
	            </ul>
            </div>
           <!--/热门名企招聘-->
           <!--求职贴士-->
            <div class="box-r recruit-tags  mt10 bd0">
            	<div class="title-bar"><span>求职贴士</span><a href="${ctx }/s_articleType_3" class="blue right mt10">更多&gt;&gt;</a> </div>
            	<ul class="about-recruit">
            		<c:forEach items="${helps}" var="help" varStatus="status">
						<a target="_blank" href="http://www.youlanw.com/xl_${help.id}.html" target="_blank" title="${help.title}"><li>${help.title}</li></a>
					</c:forEach>
	            </ul>
            </div>
           <!--/求职贴士-->
            
        </div>
        <div class="clear"></div>
    </div>
    <!---->
    

    <script type="text/javascript">
	$(function(){
		if($("#wdValue").val()!="0"){
			$("input[name='search_LIKE_searchName']").val($("#wdValue").val());	
		}
		//区域、周边城市选择下拉菜单
		$('.order .select p').click(function(){
			$(this).parents('.select').toggleClass('current').siblings('.select').removeClass('current');//给当前菜单 加current样式
		})
		$('.order-options li').click(function(){
			$(this).parents('.select').find('p label').text($(this).text()).css({'color':'#ff6c00'}).end().removeClass('current');
		});
		//点击区域、周边城市下拉菜单以外区域，隐藏下拉菜单(去掉current)
		$(document).bind("click",function(e){
		var target = $(e.target);
		if(target.closest(".order .select").length == 0){
				$('.order .select').removeClass('current');
			}
		})
		//行业选择 ,规模选择
		$('.industrySelect li,.staffscaleSelect li').click(function(){
			$(this).addClass('current').siblings().removeClass('current');
			searchCompany();
		});
		//所有规模。所有行业
	    $("#staffscaleAll").click(function(){
	    	$(".staffscaleSelect li").removeClass('current');
	        searchCompany();
    	});
	    $("#industryAll").click(function(){
	    	$(".industrySelect li").removeClass('current');
	        searchCompany();
    	});
		//tab
		$('.tab-title li').click(function(){
			$(this).addClass('current').siblings().removeClass('current');
			var current_index=$('.tab-title li').index($(this));
			$('.tab-each').eq(current_index).show().siblings('.tab-each').hide();
		});
		//行政区，城市选择
		  $('.order-options-districts li').click(function(){
		        $(this).parents('.select').find('p label').text($(this).text()).end().removeClass('current');
		        $("#cName").attr("data","");
		        $("#dName").attr("data",$(this).val());
		        searchCompany();
	    });
	    $('.order-options-otherCitys li').click(function(){
	        $(this).parents('.select').find('p label').text($(this).text()).end().removeClass('current');
	        $("#cName").attr("data",$(this).val());
	        $("#dName").attr("data","");
	        searchCompany();
	    });
	    var dName = $("#dName").val();
	    var cName = $("#cName").val();
	    $("#sdName").text("全"+$("#c_branch_name").text().replace("站",""));
	    $("#li_current_city").text("全"+$("#c_branch_name").text().replace("站",""));
	    if(dName!=null&&dName!=""){
	    	$("#districtSelect").css({'color':'#ff6c00'});
	        $("#sdName").text(dName);
	    }
	    if(cName!=null&&cName!=""){
	    	$("#citySelect").css({'color':'#ff6c00'});
	        $("#scName").text(cName);
	    }
	    
	    $("#btn_search").click(function(){ searchCompany();});
	    
	})
	//排序
	function makeSort(obj){
		$(obj).addClass('current').siblings().removeClass("current");
		searchCompany();
	}
	//跳转查询公司列表
function searchCompany(){
	var searchName = $("#searchName").val().replace('_','&');
	var industry = $(".industrySelect .current a").attr("data");
	var scale = $(".staffscaleSelect .current a").attr("data");
	var otherCity = $("#cName").attr("data");
	var districts = $("#dName").attr("data");
	var sortType="auto";
	var condition = [];
	if(otherCity!=null && otherCity!=""){
   		condition.push(otherCity);
	}else{
		condition.push("0");
	}
	if(districts!=null && districts!=""){
		condition.push(districts);
	}else{
		condition.push("0");
	}
	if(industry!=null && industry!=""){
		condition.push(industry);
	}else{
		condition.push("0");
	}
	if(scale!=null && scale !=""){
   		condition.push(scale);
	}else{ 
		condition.push("0");
	}
	if(searchName!=null && searchName !=""){
   		condition.push(searchName);
	}else{
		condition.push("0");
	}
	window.location.href="${ctx}/qy_"+sortType+"_" + condition.join("_")+"_1.html";
}
</script>
</body>
</html>