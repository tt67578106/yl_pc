<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>手机优蓝_优蓝网</title>
<style type="text/css">
.page-wrapper{background:#fff!important;}
.box-out{width:1000px;margin:0 auto;padding:0;}
/*app-poster*/
.app-poster{width:100%;min-width:1000px;height:674px;background:url(${ctx}/static/images/datas/about/app-poster.jpg) no-repeat center;position:relative;}
.app-poster .download-btn,.app-poster .download-btn2{position:absolute;left:50%;top:300px;margin-left:267px;width:188px;height:57px;background:url(${ctx}/static/images/datas/about/about-common2.png) no-repeat 0 0;}
.app-poster .download-btn2{top:380px;background-position:0 -85px;}
.app-poster .text{font-size:16px;position:absolute;left:50%;top:560px;margin-left:20px;width:447px;text-align:center;}
.app-poster .qr{position:absolute;left:50%;top:254px;width:190px;height:190px;margin-left:36px;}
/*mobile-des*/
.mobile-des{font-size:14px;line-height:2;padding-bottom:30px;}
.mobile-des .left{float:left;width:645px;color:#888;}
.mobile-des .left li{float:left;width:300px;margin-right:20px;min-height:166px;}
.mobile-des .left .title{font-size:16px;color:#666;padding-left:60px;background:url(${ctx}/static/images/datas/about/about-common.png) no-repeat 0 2px;height:50px;line-height:50px;margin:50px 0 10px 0;}
.mobile-des .left .title.icon2{background-position:0 -58px;}
.mobile-des .left .title.icon3{background-position:0 -122px;}
.mobile-des .left .title.icon4{background-position:0 -185px;}
.mobile-des .right{float:right;width:310px;padding-left:40px;border-left:1px solid #ddd;margin-top:60px;min-height:270px;line-height:2.3;}
.mobile-des .right strong{padding-left:6px;}
</style>
</head>

<body>
<!--page-wrapper-->
<div class="page-wrapper">
   <!--海报-->
    <div class="app-poster">
    	<a href="javascript:;" target="_blank"><img src="${ctx}/static/images/datas/about/app.png" class="qr"/></a>
    	<a href="https://itunes.apple.com/cn/app/id1071374655?mt=8" class="download-btn"></a>
        <a href="http://android.myapp.com/myapp/detail.htm?apkName=com.daile.youlan" class="download-btn2"></a>
        <p class="text">手机浏览器访问&nbsp;&nbsp;<label class="red">youlanw.com</label>，无需安装即可使用</p>
    </div>
    <!--/海报-->
     <!--文字-->
    <div class="box-out">
        <div class="mobile-des">
            <!--left-->
            <div class="left">
            	<ul>
                	<li>
                    	<p class="title icon1">专注蓝领服务</p>
                        <p>诚信资格认证，中国首家只专注名企</p>
                        <p>蓝领招聘的网站，众多名企等你来挑</p>
                    </li>
                    <li>
                    	<p class="title icon2">专注名企招聘</p>
                        <p>一站式定制服务，好企业好机会，</p>
                        <p>一手掌握</p>
                    </li>
                    <li>
                    	<p class="title icon3">全新生活模块</p>
                        <p>找老乡，找同好，圈子资讯，一网打</p>
                        <p>尽</p>
                    </li>
                    <li>
                    	<p class="title icon4">真人求职案例</p>
                        <p>蓝领专属简历，不再为简历发愁，</p>
                        <p>求职上岗快速无忧</p>
                    </li>
                </ul>
            </div>
            <!--/left-->
            <!--right-->
            <div class="right">
                <p><strong>优蓝APP V1.2.1正式版：</strong></p>
                <p>[工作]直面国内外优质名企，精选岗</p>
                <p>位，非你莫属</p>
                <p>[圈子]覆盖全国的工作社区，畅所欲</p>
                <p>言，开拓眼界</p>
                <p>[话题]发现好玩的新鲜话题，打工生</p>
				<p>活，精彩呈现</p>
                <p>[消息]即时聊天交流无障碍，结交工</p>
				<p>友，拓展人脉</p>
            </div>
            <!--/right-->
            <div class="clear"></div>
        </div>
    </div>
    <!--/文字-->
</div>
<!--/page-wrapper--> 
</body>
</html>