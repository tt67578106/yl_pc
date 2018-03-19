<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>职场知识大全_面试_简历_职场法则_劳动法规_全部标签<c:if test="${pageNumber>1 }">_第${pageNumber}页</c:if></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="求职帮助,面试技巧,简历规范,职场法则"/>
<meta name="description" content="优蓝网求职帮助栏目为打工者解决在找工作途中所遇到的：薪酬福利，面试，简历书写，被中介骗钱，劳动法律法规等各种问题，帮助打工者简单快速找到好工作。"/>
</head>
<body>
   <div class="nav-g"><a href="${ctx}/">优蓝网</a>&gt;<a href="${ctx}/zone">优蓝社区</a>&gt;<a href="${ctx}/tag/">职场知识大全</a></div>
    <div class="w1200">
        	<div class="seo-label">
    		<ul class="label-ul">
    		<c:forEach items="${page.content }" var="keyword">
    			<a href="${ctx }/tag_${fn:trim(keyword.keyWord) }_1/"><li>${keyword.keyWord }</li></a>
    		</c:forEach>
    		</ul>
    		<div class="clear"></div>
            <!--分页-->
			<tags:urlrewrite_job_pagination paginationSize="5" page="${page }"/>
            <!--/分页-->
    	</div>
    </div>
</body>
</html>