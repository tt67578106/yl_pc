<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setStatus(404);%>
<!DOCTYPE html>
<html>
<head>
	<title>您访问的页面不存在</title>
	<meta name="description" content=""/>
	<meta name="keywords" content="" />
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/common.css"/>
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
</head>
<body>
<div class="page-wrapper">
<jsp:include page="/WEB-INF/layouts/top.jsp" />
<!--404显示-->
<div class="notFind">
	<img src="${ctx }/static/images/v3/404.png" />
	<p class="sorry">很抱歉，您查看的页面暂时无法访问</p>
	<div class="links"><a href="/"><label id="time"></label>秒后返回首页</a><span>|</span><a href="javascript:void(0);" onclick="history.go(-1)">返回上一页</a></div>
</div>
<!--/404显示-->
<jsp:include page="/WEB-INF/layouts/footer.jsp" />
</div>
<script type="text/javascript" src="${ctx }/static/js/jquery-1.11.1.min.js"></script>
<script>
$(function(){
	var thenum = 5;
	setInterval(function() {
		$("#time").html(thenum);
		thenum--;
		if (thenum == 0) {
			window.location.href="/";
		}
	}, 1000);
})
</script>
</body>
</html>