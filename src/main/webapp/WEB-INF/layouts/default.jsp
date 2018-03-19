<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:if test="${not empty sessionScope.session_key_branch_name}">
	<c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title />_${cityName }优蓝网</title>
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20160701"></script>
<script src="${ctx}/static/js/layer.min.js"></script>
<script src="${ctx}/static/js/cookie.js"></script>
<script src="${ctx}/static/js/youlanw.pc.js?v=20160701"></script>
<sitemesh:head />
<link rel="stylesheet" href="${ctx}/static/css/common.css?v=20160701" />
<%@ include file="/WEB-INF/template/default_script.jsp" %>
<script src='//kefu.easemob.com/webim/easemob.js?tenantId=1780&hide=true' async='async'></script>
</head>

<body>
	<div class="page-wrapper">
		<jsp:include page="top.jsp" />
		<%-- <jsp:include page="header.jsp" /> --%>
		<sitemesh:body />
		<!--悬浮菜单-->
<%-- 		<ul class="float-menu">
			<li onclick="easemobIM()" tenantId=1780>
				<span class="des">在线咨询</span><span class="qq"></span>
			</li>
			<li class="signup-wrapper">
				<a href="${ctx}/signup/fastSignUp">
					<span class="des">快速报名</span><span class="signup"></span>
				</a>
			</li>
			<li class="to-wrapper">
				<span class="des">返回顶部</span><span class="to"></span>
			</li>
		</ul> --%>
		<!--/悬浮菜单-->
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>