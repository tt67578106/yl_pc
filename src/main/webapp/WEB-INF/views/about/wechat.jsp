<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信公众号</title>
<style type="text/css">
	.about{padding:45px 0 0 260px;min-height:500px;}
	.about .pic{position:absolute;left:0;top:30px;}
	.about .qr{padding:35px 0 0 180px;height:125px;font-weight:bold;margin-top:30px;}
	.about .qr img{position:absolute;left:0;top:0;width:160px;height:160px;}
</style>
</head>
<body>


	<div class="nav-g">
		<!-- <a href="">首页</a>&gt;<a href="" class="red">联系我们</a> -->
	</div>

	<div class="w990 bg-white mt10">
		<div class="box-g-l-text fl" style="border-right:none">
		<a href="index.html">关于优蓝</a>
			<a href="agreement.html">用户协议</a>
			<a href="statement.html">网站声明</a>
			<a href="mediaReports.html">媒体报道</a>
			<a href="contact.html">联系我们</a>
			<a href="wechat.html" class="current">微信公众号</a>
		</div>
		 <div class="box-g-r-text fl">      
        	<div class="about relative">
            	<img src="${ctx }/static/images/datas/about/about.png" alt="关于优蓝网" class="pic"/>
                <p style="font-size:16px;"><strong>优蓝求职</strong></p>
                <p class="text-indent">这里是为千万同胞解决就业问题的一个有温度的微信公众平台→优蓝求职。我们提供最新、最权威、最海量的企业岗位信息，全程覆盖50个城市，高达10000个免费高薪职位供你选择。每天发布最新最热的岗位需求，你还傻傻等什么？

				</p>
                <div class="qr relative">
                	<img src="${ctx }/static/images/QR.jpg" alt="优蓝求职" />
                    <p>请扫描左侧二维码</p>
                    <p>或</p>
                    <p>在微信搜索关注<strong class="red">youlanqiuzhi</strong></p>
                </div>
            </div>
        </div>
    	<div class="clear"></div>
    </div>


</body>
</html>