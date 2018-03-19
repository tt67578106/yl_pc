<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业账户_修改密码</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
</head>
<body>
	<!---->
	<div class="box-out mt15">
		<!-- left -->
		<jsp:include page="/WEB-INF/layouts/enterpriseLeft.jsp"></jsp:include>
		<!-- /left -->
		<!--right-->
		<div class="ent-box-r-wrapper">
			<div class="ent-box-r">
			<form action="${ctx}/enterprise/changepwd" method="post">
				<div class="change-pwd">
					<div style="color:red">${err_msg }</div>
					<!--当前密码-->
					<div class="form-each">
						<span class="form-each-name">当前密码：</span><input type="password" name="oldpwd" class="form-each-input" required="required" />
					</div>
					<!--/当前密码-->
					<!--新密码-->
					<div class="form-each">
						<span class="form-each-name">新密码：</span><input type="password" name="newpwd" class="form-each-input" required="required" />
					</div>
					<!--/新密码-->
					<!--确认新密码-->
					<div class="form-each">
						<span class="form-each-name">确认新密码：</span><input type="password" name="newpwd2" class="form-each-input" required="required"/>
					</div>
					<!--/确认新密码-->
					<div class="form-btn">
						<input type="submit" value="修改密码" class="btn-g-2" />
					</div>
				</div>
				</form>
				<div class="clear"></div>
			</div>
		</div>
		<!--/right-->
		<div class="clear"></div>
	</div>
	<!---->
	<script type="text/javascript">
	$(function(){
			<c:if test="${err_msg=='更新密码成功！'}">
				alert("修改密码成功，请重新登录");
				location.href="${ctx}/enterprise/logout";
			</c:if>
	});
	</script>
</body>
</html>