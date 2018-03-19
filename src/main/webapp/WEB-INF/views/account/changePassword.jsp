<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更改密码</title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/common.css"/>
</head>
<body>
<!--page-wrapper-->
<div class="page-wrapper h-100p" >
	<div class="top-div h-50p" >
		<div class="top-header" >
			<p class="title" style="float: left;"><img src="${ctx }/static/images/v3/logo.png"  /><span >|</span>修改密码</p>
			<p class="hot-line" ><i></i>快速求职热线：4008-777-816</p>
			<div class="clear"></div>
		</div>
	</div>
	<div class="reset-form" >
		<form action="${ctx}/my/changepwd" method="post">
			<div class="form-tips red">${err_msg}</div>
	        <div class="form-each mt40 ml0"><input type="password" name="oldpwd" class="form-each-input" placeholder="请输入原密码" required="required"/></div>
	        <div class="form-each"><input type="password" name="newpwd" class="form-each-input" placeholder="请输入新密码" required="required"/></div>
	        <div class="form-each"><input type="password" name="newpwd2" class="form-each-input" placeholder="请再次输入新密码" required="required"/></div>
	        <div class="form-each"><input type="submit" value="保存" class="btn-g bdr0 btn-green"/></div>
		    <div class="clear"></div>
		 </form>   
	</div>
	
</div>
<div class="footer-sty">沪ICP备14033709号-1&nbsp;Copyright © 2013-2016 youlanw.com All Rights Reserved</div>
<!--/page-wrapper-->
</body>
</html>