<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我要进名企</title>
</head>
<body>
<c:if test="${not empty paramValues['search_LIKE_company.name'][0]}">
	<c:set var="wd" value="${paramValues['search_LIKE_company.name'][0]}" />
</c:if>
	<div class="nav-g">
		<!-- <a href="">首页</a>&gt;<a href="" class="red">名企招聘</a> -->
	</div>

	<!--enterprises-->
	<div class="enterprises w990">
		<!--left-->
		<div class="box-g-l-wrapper">
			<!--名企列表-->
				<c:if test="${empty page.content}">
					<div class="box-l-2 text-center"><span class="icon-warning"><i></i></span>没找到想要的公司？不如委托给顾问，帮我<a href="${ctx}/signup/fastSignUp" class="red">30秒找工作</a></div>
				</c:if>
			<div class="box-g-l fl enterprises-list f14 img-hover">
				<!---->
				<c:forEach items="${page.content }" var="job">
					<ul class="enterprise-each">
						<li class="pic"><a
							href="${ctx}/zhaopin_${job.id}.html"
							target="_blank"><img src="${cdc:getImagePath320(job.thumbnialImage.imgpath) }"
								alt="" /></a></li>
						<li class="company"><h3>
								<a
									href="${ctx}/qiye_${job.company.id}.html"
									target="_blank">${tc:highlightText(job.company.name,wd) }</a>
							</h3></li>
						<li class="logo"><a
							href="${ctx}/qiye_${job.company.id}.html"
							target="_blank"><img src="${cdc:getImagePath320(job.company.logo.imgpath) }"
								alt="" /></a></li>
						<li class="title"><a
							href="${ctx}/zhaopin_${job.id}.html"
							target="_blank">${job.title }</a></li>
						<li class="tags tags-g">
							<c:forEach items="${job.jobLabel }" var="label">
								<a href="${ctx}/s_label_${label}">${label}</a>
							</c:forEach></li>
						<li class="salary">￥${job.jobDetail.salaryfrom }<c:if test="${not empty  job.jobDetail.salaryto}">-${job.jobDetail.salaryto }元/月</c:if>
		        		<c:if test="${empty  job.jobDetail.salaryto}">起</c:if></li>
						<li class="signup"><span class="fl"><label class="red">${job.applycount }人</label>已报名</span><a
							href="javascript:void(0);" data-id="${job.id}"
							class="btn btn-small fl signup-layer-trigger">我要报名</a></li>
					</ul>
				</c:forEach>
				<tags:paginationRewrite paginationSize="5" page="${page }"/>
				
				<!---->
			</div>
			<!--/名企列表-->
		</div>
		<!--/left-->

		<!--right-->
		<div class="box-g-r-wrapper relative">
			<div class="bottom-fixed w310">
		     <!--快速找工作-->
                <div class="box-g-r quick-job bg-white fl height-auto">
                    <h2 class="column-title2">30秒找工作</h2>
                    <div class="hr-g"></div>
                    <div class="text-g-4">
                        <p>留下您的电话号码，优蓝网强大专业的打工顾问团队，全心全意为您服务。</p>
                    </div>
                    <a href="${ctx}/signup/fastSignUp" class="btn fl">帮我介绍工作</a>
                </div>
                <!--快速找工作-->
				<!--岗位热招中-->
				<div class="hot-list box-g-r bg-white fl mt10 height-auto">
					<h2 class="column-title3">
						岗位热招中<a class="view-more" target="_blank" href="${ctx}/job/">更多&gt;</a>
					</h2>
					<c:forEach items="${hotJobs}" var="job">
						<a href="${ctx}/zhaopin_${job.id}.html"
							target="_parent">
							<ul class="hot-list-each">
								<li class="title">${job.title }</li>
								<li class="area">【${job.city.cityName }】</li>
								<li class="salary">￥${job.jobDetail.salaryfrom }<c:if test="${not empty  job.jobDetail.salaryto}">-${job.jobDetail.salaryto }元/月</c:if>
		       					 <c:if test="${empty  job.jobDetail.salaryto}">起</c:if></li>
								<li class="num"><label class="red">${job.applycount }</label>人已报名</li>
							</ul>
						</a>
					</c:forEach>

				</div>
				<!--/岗位热招中-->
				<div class="clear"></div>
				<div class="bottom-fixed-trigger"></div>
			</div>
		</div>
		<!--/right-->
		<div class="clear"></div>
	</div>
	<!--/enterprises-->
</body>
</html>