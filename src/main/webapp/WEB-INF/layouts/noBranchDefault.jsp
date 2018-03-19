<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title />_优蓝网</title>
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20150820"></script>
<script src="${ctx}/static/js/layer.min.js"></script>
<script src="${ctx}/static/js/cookie.js"></script>
<script src="${ctx}/static/js/youlanw.pc.js?v=5"></script>
<link rel="stylesheet" href="${ctx}/static/css/common.css?v=20150417" />
<sitemesh:head />
<script type="text/javascript">
if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)||/(Android)/i.test(navigator.userAgent)) {
	window.location.href = "http://m.youlanw.com/?jumper="	+ window.location.pathname;
}

var _vds = _vds || [];
window._vds = _vds;
(function(){
  _vds.push(['setAccountId', '844010516d2d97c9']);
  _vds.push(['setCS1', 'user_id', '100324']);
  _vds.push(['setCS3', 'user_name', '王同学']);
  (function() {
    var vds = document.createElement('script');
    vds.type='text/javascript';
    vds.async = true;
    vds.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'dn-growing.qbox.me/vds.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(vds, s);
  })();
})();
</script>
<script src='//kefu.easemob.com/webim/easemob.js?tenantId=1780&hide=true' async='async'></script>
</head>

<body>
	<div class="page-wrapper bg-grey">
		<jsp:include page="top.jsp" />
		<sitemesh:body />
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>