<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关于优蓝</title>
<style type="text/css">
	.about{min-height:500px;width:700px;}
	.about-title{position:relative;border-bottom:1px solid #ed5f30;margin:20px 0 30px -30px;width:760px;z-index:1;height:28px;}
	.about-title h2{position:absolute;width:115px;padding-left:30px;height:36px;line-height:36px;color:#fff;background:#fff url(${ctx}/static/images/datas/about/t1.png) no-repeat 0 0;left:30px;top:10px;margin-top:0!important;z-index:2;}
</style>
</head>
<body>
	<div class="w990 bg-white mt10">
    	<div class="box-g-l-text fl" style="border-right:none">
        	<div class="">
	            <a href="index.html" class="current">关于优蓝</a>
	            <div class="about-sub">
	            	<a href="#about-sub1">公司简介</a>
	                <a href="#about-sub2">合作优势</a>
	                <a href="#about-sub3">产品服务</a>
	                <a href="#about-sub4">团队风采</a>
	                <a href="#about-sub5">公司大事</a>
	                <a href="#about-sub6">未来展望</a>
	            </div>
	         	<a href="agreement.html">用户协议</a>
				<a href="statement.html">网站声明</a>
				<a href="mediaReports.html">媒体报道</a>
				<a href="contact.html">联系我们</a>
				<a href="wechat.html">微信公众号</a>
            </div>
        </div>
        <div class="box-g-r-text fl about-out">      
        	<div class="about relative" style="margin-left:15px;">
            	<img src="${ctx }/static/images/datas/about/1.png" alt="关于优蓝网" />
                <!--优蓝网简介-->
                <div class="about-title" id="about-sub1"><h2>简介</h2></div>
                <p class="text-indent">
              优蓝网成立于2014年，隶属于上海优尔蓝信息科技股份有限公司，是一家专门做蓝领招聘的网站。每日提供100万+名企岗位，专业求职顾问24小时提供咨询服务，遍布全国300个城市的服务顾问协助蓝领安全入职。优蓝网现已成为国内规模最大、布局最广、用户最多最专业的蓝领招聘服务平台，截止2017年5月，优蓝网精准蓝领数据库已达4000多万。
                </p>
                <!--/优蓝网简介-->

                <!--我们的优势-->
                <div class="about-title" id="about-sub2"><h2>合作优势</h2></div>
                <img src="${ctx }/static/images/datas/about/6.jpg" alt="我们的优势" class="mt10"/>
                <!--/我们的优势-->
                
                <!--我们的fuwu-->
                <div class="about-title" id="about-sub3"><h2>产品服务</h2></div>
                <img src="${ctx }/static/images/datas/about/7.jpg" alt="产品服务" class="mt10"/>
                <!--/我们的优势-->
                
                
                <!--我们的团队-->
                <div class="about-title" id="about-sub4"><h2>团队风采</h2></div>
                <img src="${ctx }/static/images/datas/about/4.jpg" alt="团队风采" class="mt10"/>
                <!--/我们的团队-->
                
                <!--平台大事记-->
                <div class="about-title" id="about-sub5"><h2>平台里程碑</h2></div>
                <img src="${ctx }/static/images/datas/about/5.jpg" alt="平台里程碑" class="mt10"/>
                <!--/平台大事记-->
                
                <!--我们的未来-->
                <div class="about-title" id="about-sub6"><h2>未来展望</h2></div>
                <img src="${ctx }/static/images/datas/about/3.jpg" alt="未来展望"/>
                <!--/我们的未来-->
                
                
            </div>
        </div>
    	<div class="clear"></div>
    </div>
 <script type="text/javascript">
		$(function(){//关于优蓝二级菜单锚点
			$('.about-sub a').click(function(){
				var current_index=$('.about-sub a').index(this);
				$('body,html').animate({scrollTop:$('.about-title').eq(current_index).offset().top-30},500);
				return false;
			});
		})
    </script>
</body>
</html>