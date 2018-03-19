<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<center>
	<h1>登录成功</h1>
<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name);//获取当前窗体索引
		setTimeout(function(){
			parent.layer.close(index); //执行关闭
		},1000);
		parent.document.getElementById("link_login").remove();
</script>
</center>
</body>
</html>