<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:if test="${not empty sessionScope.session_key_branch_name}">
	<c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${fn:trim(company.abbreviation) }招聘|招工信息_${fn:trim(company.name) }</title>
<meta name="keywords" content="${fn:trim(company.abbreviation) }招聘,${fn:trim(company.abbreviation) }招工信息,${fn:trim(company.name) }"/>
<meta name="description" content="${cityName }优蓝网【${fn:trim(company.abbreviation) }招聘信息】,提供${fn:trim(company.name) }真实可靠的招聘信息,在${fn:trim(company.name) }打工,成就高薪蓝领工人"/>
</head>
<body>
    <!--/top-menu--->
    <div class="nav-g"><a href="${ctx}/">${cityName }找工作</a>&gt;<a href="${ctx }/mingqi/">${cityName }名企招聘</a>&gt;<a href="${ctx}/qiye_${company.id}.html">${company.abbreviation }</a>&gt;<a href="${ctx}/company/job_${company.id }/">${company.abbreviation }最新招聘</a></div>
    
    
    <!--在招岗位 最新动态 精彩文章 其他人看过-->
    <div class="w1200 mt10">
    	<!--left-->
        <div class="box-l-wrapper">
        	<div class="box-l box-l-img">
                <!--在招职位-->
            	<div class="box-g">
                	<h1 class="company-title">${company.abbreviation }招聘信息</h1>
                	<p class="mb10">该企业共有${jobCount }个在招职位</p>
                </div>
                <c:forEach items="${jobs}" var="job">
	            	<ul class="job-list-each seal-1 <c:if test="${job.jobConfig.isUrgency == 2}">seal-5</c:if><c:if test="${job.jobConfig.isUrgency == 3}">seal-4</c:if>" >
		            	<li class="pic"><a href="${ctx}/zhaopin_${job.id}.html" target="_blank">
		            	<c:choose>
							<c:when test="${empty job.thumbnialImage.imgpath}">
								<img src="${cdc:getImageByJobType320(job.jobType)}" alt="${job.title }" />
							</c:when>
							<c:otherwise>
								<img src="${cdc:getImagePath320(job.thumbnialImage.imgpath)}" alt="${job.title}" />
							</c:otherwise>
						</c:choose>
		            	</a><i></i></li>
		                <li class="title"><h3>[${job.city.cityName }${cdc:converCountyName("-",job.countyid)}]<a href="${ctx}/zhaopin_${job.id}.html" target="_blank">${tc:highlightText(job.title, wd)}</a></h3></li>
		                <li class="tags-g">
		                <c:forEach items="${job.jobLabel}" var="label">
                			<label>${label }</label>
                    	</c:forEach>
		                </li>
		                <li class="summary">${cdc:converDicEducation(job.jobDetail.education)} / ${tc:convertGenderLimit(job.jobDetail.gender)} / <c:if test="${not empty job.jobDetail.agefrom }">
                        ${job.jobDetail.agefrom }<c:if test="${not empty job.jobDetail.ageto }">~${job.jobDetail.ageto}岁</c:if>
                        <c:if test="${empty job.jobDetail.ageto }">岁以上</c:if></c:if>
                        <c:if test="${empty job.jobDetail.agefrom }">年龄不限</c:if>/${tc:dayAfter(job.updatetime)}发布</li>
		                <li class="salary">￥${job.jobDetail.salaryfrom }<c:if test="${not empty  job.jobDetail.salaryto}">-${job.jobDetail.salaryto }元/月</c:if>
		        		<c:if test="${empty  job.jobDetail.salaryto}">起</c:if></li>
		                <li class="company"><a href="${ctx}/qiye_${job.company.id}.html" target="_blank">${tc:highlightText(job.company.name ,wd) }</a>
		                <c:if test="${job.company.validation == 1}"><label class="seal-3">验</label></c:if></li>
		                <li class="signup"><a data-id="${job.id }" href="${ctx}/zhaopin_${job.id}.html" class="btn btn-small">去看看</a></li>
		            </ul>
               </c:forEach>
                <!--/在招职位-->
            </div>
        </div>
        <!--/left-->
        <!--right-->
        <div class="box-r-wrapper">
        	<!--查看企业主页-->
        	<div class="box-r bd0">
	        	<div class="job-com">
	            	<a href="${ctx }/qiye_${company.id }.html" class="more" target="_blank" title="${company.abbreviation }">
	            		<h2>${company.name }</h2>
	            	</a>
	                <div class="inner">
	                	<c:if test="${not empty company.logo.imgpath}">
			        		<img src="${cdc:getImagePath320(company.logo.imgpath)}" alt="${company.abbreviation }_logo" />
	                	</c:if>
	                    <p><span class="c999">企业规模：</span>${cdc:convertDicStaffscale(company.staffscale)}</p>
	                    <p><span class="c999">所属行业：</span>${cdc:convertDicIndustryType(company.industryid)}</p>
	                    <p><span class="c999">平均薪资：</span>${averageSalary }元/月</p>
	                </div>
	            </div>
	            <div class="job-com-a"><a href="${ctx }/company/album_${company.id }/">企业相册&gt;&gt;</a></div>
	            <div class="job-com-a"><a href="${ctx }/company/job_${company.id }/">在招岗位&gt;&gt;</a></div>
	            <div class="job-com-a"><a href="${ctx }/wenda/so_0_${company.id}_0_1/">企业问答&gt;&gt;</a></div>
	        </div>
	        <!--/查看企业主页-->
        	
            <!--最新岗位-->
         	<div class="box-r recruit-tags bd0">
            	<div class="title-bar"><span>最新岗位</span></div>
            	<ul class="about-recruit">
	            	<c:forEach items="${newJobs }" var="job">
		            	<a href="${ctx }/zhaopin_${job.jobId }.html"><li>${job.companyAbbreviation}招聘${job.jobType}</li></a>
            		</c:forEach>
	            </ul>
            </div>
            <!--/最新岗位-->
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
    
	<script src="http://www.youlanw.com/static/js/jquery.SuperSlide.2.1.js"></script>
</body>
</html>