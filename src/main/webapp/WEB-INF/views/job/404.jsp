<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%response.setStatus(404);%>
<!DOCTYPE html>
<html>
<head>
	<title>该岗位已经下线了</title>
	<meta name="description" content=""/>
	<meta name="keywords" content="" />
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/jobDetail.css"/>
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
</head>
<body>
<div class="page-wrapper">
<jsp:include page="/WEB-INF/layouts/top.jsp" />
<!--404显示-->
<div class="notFind">
	<div class="inner clearfix">
        <img src="${ctx }/static/images/img-404.png" />
		<div class="right">
			<p class="sorry">对不起，该岗位已经下线了。</p>
	        <p class="links">您可以委托给顾问，<a href="${ctx }/signup/fastSignUp">帮我30秒找工作</a></p>
	        <p>您也可以看看我们为您推荐的优质岗位</p>
		</div>
	</div>
</div>
<!--/404显示-->
<!--job-recommend-->
<c:if test="${not empty likeJobs }">
<div class="box-wrapper">
	<div class="job-recommend tab-g">
		<div class="job-col-title relative">
			<h2>推荐岗位</h2>
		</div>	
		<!--推荐岗位-->
		<div class="content-each show">
			<div class="inner">
				<c:forEach items="${likeJobs}" var="likeJob">
					<div class="each">
						<a href="${ctx}/zhaopin_${likeJob.id}.html">
							<c:choose>
								<c:when test="${empty likeJob.thumbnialImage.imgpath}">
									<img src="${cdc:getImageByJobType320(likeJob.jobType)}" alt="${likeJob.title }"/>
								</c:when>
								<c:otherwise> 
									<img src="${cdc:getImagePathO(likeJob.thumbnialImage.imgpath)}" alt="${likeJob.title}"/>
								</c:otherwise>
							</c:choose>
						</a>
						<a href="${ctx}/qiye_${likeJob.company.id}.html" class="more" target="_blank" title="${likeJob.company.name}">
							<p class="company">${likeJob.company.name}</p>
						</a>
						<a href="${ctx}/zhaopin_${likeJob.id}.html"><p class="title">${likeJob.title }</p></a>
						<p class="orange">￥${likeJob.jobDetail.salaryfrom }<c:if test="${not empty  likeJob.jobDetail.salaryto}">-${likeJob.jobDetail.salaryto }元/月</c:if>
							<c:if test="${empty  likeJob.jobDetail.salaryto}">起</c:if>
						</p>
					</div>
				</c:forEach>
				<div class="clear"></div>
			</div>
		</div>
		<!--/推荐岗位-->
		<!--查看更多职位-->
		<div class="text-center mb20">
			<a href="${ctx }/zhaopin/" class="btn-look-more">查看更多职位&gt;&gt;</a>
		</div>
		<!--/查看更多职位-->
	</div>
</div>
</c:if>
<!--/job-recommend-->
<jsp:include page="/WEB-INF/layouts/footer.jsp" />
</div>
</body>
</html>