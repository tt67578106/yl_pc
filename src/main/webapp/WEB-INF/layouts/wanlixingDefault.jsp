<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title />_优蓝网</title>
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20150417"></script>
<sitemesh:head />
<link rel="stylesheet" href="${ctx}/static/css/jiuye.css" />
<script src="${ctx}/static/js/cookie.js"></script>
</head>
<body>
	<div class="page-wrapper">
		<jsp:include page="wanlixingHeader.jsp" />
		<sitemesh:body />
	    <!--右侧悬浮-->
	    <div class="jy-r-panel">
    	<a href="${ctx }/trip10000/registration/signUp" class="sign-link"><!--快速报名--></a>
        <a href="javascript:;" class="go-top"><!--返回顶部--></a>
   		 </div>
	    <!--/右侧悬浮-->
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>