<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业用户登录_优蓝网</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20150417"></script>
<script src="${ctx}/static/js/cookie.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/enterprise.css?v=20150505"/>
<style type="text/css">
/*quick-banner*/
.quick-banner{width:100%;min-width:1000px;height:553px;background:url(${ctx}/static/images/datas/enterprise/banner-bg.jpg) no-repeat center;position:relative;top:-2px;padding-top:1px;}
.quick-banner .box-out{position:relative;}
.quick-banner h1{font-size:48px;color:#fff;font-weight:normal;margin-top:50px;}
.quick-banner .des{font-size:16px;color:#eee;margin-top:10px;line-height:2;}
.quick-banner .qr{width:258px;height:258px;border-radius:10px;margin:15px 0 0 15px;}
.quick-banner .qr-des{font-size:16px;color:#fff;width:288px;text-align:center;margin-top:15px;}
.quick-banner .banner-element{position:absolute;width:596px;height:411px;bottom:-100px;right:0;}
/*high-lights*/
.high-lights{margin:70px auto 50px auto;font-size:24px;}
.high-lights li{float:left;width:270px;padding-left:50px;background:url(${ctx}/static/images/datas/enterprise/ok.png) no-repeat 0 2px;}
</style>
</head>
<body>
<div class="page-wrapper">
<!--top-menu-->
	<div class="top-menu">
		<div class="box-out">
			<div class="fl">
                <a href="${ctx }/" class="ml0">回优蓝网首页</a>
            </div>
			<div class="fr">
				<c:if test="${empty sessionScope.session_enterprise_user_key}">
				 <a href="${ctx}/login?role=enterprise" rel="nofollow">登录</a>
				 <label class="line-g">|</label>
                <a href="${ctx}/register?role=enterprise" rel="nofollow" class="ml0">注册</a>
				</c:if>
				<c:if test="${not empty sessionScope.session_enterprise_user_key}">
          		欢迎回来，<a href="${ctx }/enterprise" target="_blank"> ${sessionScope.session_enterprise_user_key.userName }</a><label class="line">|</label><a href="${ctx}/enterprise/logout" class="exit">退出</a>
       			</c:if>
			</div>
		</div>
	</div>
	<!--/top-menu--->
   <!--menu-->
	<div class="top-menu-wrapper">
		<div class="box-out">
			<a href="${ctx }/"><img src="${ctx }/static/images/logo.png" alt="优蓝网" class="logo fl"/></a>
            <label class="col-name fl">企业版</label>
            <div class="icon-tel3 fr">服务热线：4008-777-816</div>
            <div class="clear"></div>
		</div>
	</div>
	<!--/menu--->
    
    <!---->
    <div class="quick-banner">
    	<div class="box-out">
    		<h1>优蓝快招  让你招聘更轻松</h1>
            <div class="des">
                <p>『优蓝快招』是优蓝网专为企业HR提供的微信招聘服务号，无需下载安</p>
                <p>装APP，即可轻松发布 招聘信息，随时收发简历，招人前所未有轻松。</p>
            </div>
            <p><img src="${ctx }/static/images/QR3.jpg" alt="优蓝快招二维码" class="qr"/></p>
            <p class="qr-des">使用微信扫描上方二维码即可关注</p>
            <img src="${ctx }/static/images/datas/enterprise/banner-element.png" alt="" class="banner-element"/>
        </div>
    </div>
    <!--/-->
    <!---->
    <div class="high-lights box-out">
    	<ul>
        	<li>招聘0成本</li>
            <li>轻松收发简历</li>
            <li>专属客服服务</li>
        </ul>
        <div class="clear"></div>
    </div>
    <!--/-->
    <!--/企业用户-->
     <jsp:include page="/WEB-INF/layouts/footer.jsp"></jsp:include>
</div>
</body>
</html>