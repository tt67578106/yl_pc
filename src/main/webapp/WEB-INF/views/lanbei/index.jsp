<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>蓝呗钱包_优蓝网</title>
<link rel="stylesheet" href="${ctx }/static/css/lanbei.css">
<link rel="stylesheet" href="${ctx}/static/css/common.css" />
</head>

<body>
<div class="container_bac">
<jsp:include page="/WEB-INF/layouts/top.jsp" />
    <div style="width:1155px;margin:0 auto;height:auto;" class="container_m">
        <div class="container_left">
            <img src="${ctx }/static/images/lanbei/text.png" alt="" class="text_1">
            <img src="${ctx }/static/images/lanbei/text2.png" alt="" class="text_2">
            <a href="https://www.pgyer.com/lanbei" style="text-decoration:none;"><img src="${ctx }/static/images/lanbei/iphone.png" alt="" class="iphone" ></a>
            <a href="https://www.pgyer.com/LBQB" style="text-decoration:none;"><img src="${ctx }/static/images/lanbei/android.png" alt="" class="android" ></a>
            <img src="${ctx }/static/images/lanbei/corde.png" alt="" class="corde">
        </div>
        <div class="container_right">
            <img src="${ctx }/static/images/lanbei/ph.png" alt="" class="ph">
        </div>
    </div>
</div>
</body>
</html>