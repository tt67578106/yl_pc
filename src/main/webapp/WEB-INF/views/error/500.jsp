<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%	
	//设置返回码200，避免浏览器自带的错误页面
	response.setStatus(200);
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(exception.getMessage(), exception);
%>

<!DOCTYPE html>
<html>
<head>
	<title>无权访问</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style>
		.main-500 a { color:#ff6c00;}
		.main-500 { width:820px; margin:50px auto 0; height:300px;}
		.main-500 .txt { margin:0 auto; text-align:center}
		.main-500 .txt h4{ font-size:66px; color:#ff6c00; font-weight:500; line-height:80px; margin:0 0 10px 0; text-shadow:0 1px 3px rgba(0,0,0,0.2)}
		.main-500 .txt h5{ color:#666; font-size:18px; margin:0; line-height:40px; font-weight:400}
		.main-500 .txt .ptxt { font-size:18px; color:#999;}
		.main-500 .tips { width:324px; height:181;position:relative; text-align:center; margin:0 auto;}
	  
	</style>
</head>

<body>

<div class="main-500">
 <div class="tips"><img src="/static/images/500.png" width="324" height="181"></div> 
 <div class="txt">
  <h5>您访问的网页 不存在或者已删除</h5>
  <p class="ptxt">系统即将转向首页</a><em>或</em>&nbsp;&nbsp; <a href="/" >手动返回首页</a></p>
 </div>
</div>
<script type="text/javascript">
setTimeout(function(){
	window.location.href="/";
},3000);
</script>
</body>
</html>
