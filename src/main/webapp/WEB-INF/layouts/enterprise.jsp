<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title />_优蓝网</title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/enterprise.css?v=20150505"/>
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20150417"></script>
<script src="${ctx}/static/js/layer.min.js"></script>
<script src="${ctx}/static/js/cookie.js"></script>
<sitemesh:head />
<script type="text/javascript">
if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)||/(Android)/i.test(navigator.userAgent)) {
	window.location.href = "http://m.youlanw.com/?jumper="	+ window.location.pathname;
}
</script>
</head>

<body>
	<div class="page-wrapper ent-bg">
		<jsp:include page="enterpriseTop.jsp" />
		<jsp:include page="enterpriseHeader.jsp" />
		<sitemesh:body />
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>