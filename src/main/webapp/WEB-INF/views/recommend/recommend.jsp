<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>悬赏聘</title>
<link rel="stylesheet" href="${ctx}/static/css/common.css">
<link rel="stylesheet" href="${ctx}/static/css/jquery.fullPage.css">
<style type="text/css">
/**/
#fullPage-nav { _display: none; font-size: 12px;background:#349502;padding:5px 5px;border-radius:15px;}
#fullPage-nav li { width: 20px; height: 21px; margin: 10px 0 0; vertical-align: middle;}
#fullPage-nav li a { float: right; width: 21px; height: 21px; color: #8F9DA4; text-decoration: none; text-align: right; background: url(${ctx}/static/images/dot.png) 5px 5px no-repeat;}
#fullPage-nav li .active { background-position: 0 bottom;}
#fullPage-nav span { display: none;}
.fullPage-tooltip { float: left; top: 0; margin-right: 5px; font: 12px "Microsoft Yahei"; color: #8F9DA4; line-height: 21px;}
/**/
.section { position: relative; overflow: hidden;color:#333;}
.section .bg { position: absolute; left: 0; top: 0; width: 100%; height: 100%;}
.section .bg img { display: block; width: 100%; height: 100%;}
/***/
.section strong { position: absolute; left: 50%; top: 15%; z-index: 10; width: 512px; height: 45px; margin-left: -256px; font-size: 24px; font-weight: 500; color: #748A9E; opacity: 0; -webkit-transform:scale(0.5, 0.5); transform:scale(0.5, 0.5); transition: all 1s;}
/***/
.s11{background:url(${ctx}/static/images/datas/recommend/fullpage1.jpg) no-repeat center;}
.s12{top:15%;padding-left:115px;}
.s13{ position:absolute; left:-50%; top:40%; width:648px; height: 62px; background-image: url(${ctx}/static/images/datas/recommend/fullpage-data-12.png); background-repeat:no-repeat; transition: all 1s;}
.s22{position:absolute; left:-50%; top:20%; width: 450px; height: auto; background-repeat:no-repeat; transition: all 1s;font-size:14px;line-height:2.2;}
.s23{width:451px;height:689px;position: absolute;left:2000px; width: 645px; background: url(${ctx}/static/images/datas/recommend/fullpage-data-23.png) 50% 0 no-repeat; opacity: 0; transition: all 1s;}
.s32{position: absolute; left: -50%; top:25%; width: 253px; height:533px; background: url(${ctx}/static/images/datas/recommend/fullpage-data-32.png) 50% 0 no-repeat; opacity: 0; transition: all 1s;}
.s33{position: absolute; left:150%; top:25%; width: 448px; height:512px; background: url(${ctx}/static/images/datas/recommend/fullpage-data-33.png) 50% 0 no-repeat; opacity: 0; transition: all 1s;}
.s41{ position:absolute; left:-50%; top:15%; width: 253px; height: 533px; background-image: url(${ctx}/static/images/datas/recommend/fullpage-data-41.png); background-repeat:no-repeat; transition: all 1s;}
.s42 { position:absolute; left:150%; top: 25%; width: 648px;height:583px;background-image: url(${ctx}/static/images/datas/recommend/fullpage-data-42.png); opacity: 0; transition: all 1s;}
.s51{position:absolute; left:-50%; top:20%; width: 639px; height: 682px; background-image: url(${ctx}/static/images/datas/recommend/fullpage-data-51.png); background-repeat:no-repeat; transition: all 1s;}
.s52{position:absolute; left:150%; top:27%; width: 305px; height: 542px; background-image: url(${ctx}/static/images/datas/recommend/fullpage-data-52.png); background-repeat:no-repeat; transition: all 1s;}
.s61{position:absolute; left:50%; top:-100%; width: 600px; transition: all 1s;}
.s62{position:absolute; left:-50%; top:-50%; width: 244px; height:434px;background-image: url(${ctx}/static/images/datas/recommend/fullpage-data-62.png); background-repeat:no-repeat; transition: all 1s;}
.s63{position:absolute; left:150%; top:25%; width: 253px; height:533px;background-image: url(${ctx}/static/images/datas/recommend/fullpage-data-63.png); background-repeat:no-repeat; transition: all 1s;}
.s64{position:absolute; left:150%; top:25%; width: 253px; height:533px;background-image: url(${ctx}/static/images/datas/recommend/fullpage-data-64.png); background-repeat:no-repeat; transition: all 1s;}
/* CSS3过度及动画 */
.active strong{ opacity: 1; -webkit-transform:scale(1, 1); transform:scale(1, 1); transition-delay: 0.7s;}
.active .s13{left: 50%; opacity: 1; transition-delay: 0.5s;margin-left: -180px}
.active .s22 { left: 50%; opacity: 1; transition-delay: 0.7s;margin-left: -507px}
.active .s23{ left: 50%;top:18%; margin-left: -110px; opacity: 1; transition-delay: 1.2s;}
.active .s32 { left:50%; margin-left: -500px; opacity: 1; transition-delay: 0.7s;}
.active .s33 { left:50%; margin-left: -150px; opacity: 1; transition-delay: 1s;}
.active .s41 { left: 50%; opacity: 1; transition-delay: 1.0s;margin-left: -507px}
.active .s42 { left:50%; margin-left: -200px; opacity: 1; transition-delay: 1.4s;}
.active .s51 { left: 50%; opacity: 1; transition-delay: 1.2s;margin-left: -507px}
.active .s52 { left: 50%; opacity: 1; transition-delay: 1.6s;margin-left: 180px}
.active .s61 { left: 50%; top:16%; opacity: 1; transition-delay: 0.7s;margin-left: -410px;font-size:22px;}
.active .s62 { left: 50%; top:25%; opacity: 1; transition-delay: 1.4s;margin-left: -410px;}
.active .s63 { left: 50%; opacity: 1; transition-delay: 2s;margin-left: -100px;}
.active .s64 { left: 50%; opacity: 1; transition-delay: 2.7s;margin-left: 250px;}
/* for lt ie 10 */
.ltie10 strong{ opacity: 1; -webkit-transform:scale(1, 1); transform:scale(1, 1); transition-delay: 0.7s;}
.ltie10 .s13{left: 50%; opacity: 1; transition-delay: 0.5s;margin-left: -470px}
.ltie10 .s22 { left: 50%; opacity: 1; transition-delay: 0.7s;margin-left: -507px}
.ltie10 .s23{ left: 50%;top:18%; margin-left: -110px; opacity: 1; transition-delay: 1.2s;}
.ltie10 .s32 { left:50%; margin-left: -500px; opacity: 1; transition-delay: 0.7s;}
.ltie10 .s33 { left:50%; margin-left: -100px; opacity: 1; transition-delay: 1s;}
.ltie10 .s41 { left: 50%; opacity: 1; transition-delay: 1.0s;margin-left: -507px}
.ltie10 .s42 { left:50%; margin-left: -200px; opacity: 1; transition-delay: 1.4s;}
.ltie10 .s51 { left: 50%; opacity: 1; transition-delay: 1.2s;margin-left: -507px}
.ltie10 .s52 { left: 50%; opacity: 1; transition-delay: 1.6s;margin-left: 200px}
.ltie10 .s61 { left: 50%; top:16%; opacity: 1; transition-delay: 0.7s;margin-left: -410px;font-size:22px;}
.ltie10 .s62 { left: 50%; top:25%; opacity: 1; transition-delay: 1.4s;margin-left: -410px;}
.ltie10 .s63 { left: 50%; opacity: 1; transition-delay: 2s;margin-left: -100px;}
.ltie10 .s64 { left: 50%; opacity: 1; transition-delay: 2.7s;margin-left: 250px;}
</style>
<script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js" type="text/javascript"></script>
<script src="http://libs.baidu.com/jqueryui/1.8.22/jquery-ui.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.fullPage.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	if($.browser.msie && $.browser.version < 10){
		$('body').addClass('ltie10');
	}
	$.fn.fullpage({
		verticalCentered: false,
		anchors: ['page1', 'page2', 'page3', 'page4', 'page5', 'page6'],
		navigation: true,
		navigationPosition: 'left',
		resize: false,
		loopBottom: true,
		loopTop: true
	});
});
</script>

</head>
<body>

<!--top-menu-->
<div class="top-menu" style="position:absolute;z-index:9999;display:block!important;top:0;position:fixed;">
    <div class="w990">
        <div class="fl">您好，欢迎来到优蓝网<a href="javascript:void(0);" class="login-layer-trigger" rel="nofollow">登录</a><a href="javascript:void(0);" class="reg-layer-trigger" rel="nofollow">免费注册</a></div>
        <div class="fr">
            <label class="icon-common icon-tel">4008-777-816</label>
            <a href="" target="_blank">优蓝学院</a>
            <a href="" target="_blank">校园大使</a>
            <a href="" target="_blank" class="icon-common icon-phone" rel="nofollow">手机优蓝</a>
            <a href="http://weibo.com/youlanwHR" target="_blank" rel="nofollow">官方微博</a>
        </div>
    </div>
</div>
<!--/top-menu--->


<!--第1屏-->
<div class="section section1">
	<div class="bg s11"></div>
    <strong class="s12"/><img src="${ctx}/static/images/datas/recommend/fullpage-data-11.png"/></strong>
    <div class="s13"></div>
</div>
<!--/第1屏-->


<!--第2屏-->
<div class="section section2">
	<div class="bg" style="background:#fff;"></div>
	<strong style="top:58px;padding-left:115px;font-size:40px;color:#3da905;font-weight:bold;">悬赏聘赚钱规则</strong>
	<div class="s22">
    	<p><b style="color:#3da905">活动规则:</b></p>
        <p class="mt10">1.悬赏聘是优蓝网通过悬赏佣金的方式为合作企业进行招聘，优蓝网用户可以通过推荐亲朋好友入职来赚取佣金。被推荐者入职后满一定天数，即可通过线上或线下的方式将佣金提现。</p>
        <p class="mt20">2.悬赏聘是优蓝网通过悬赏佣金的方式为合作企业进行招聘，优蓝网用户可以通过推荐亲朋好友入职来赚取佣金。被推荐者入职后满一定天数，即可通过线上或线下的方式将佣金提现。</p>
        <p class="mt30">3.悬赏聘是优蓝网通过悬赏佣金的方式为合作企业进行招聘，优蓝网用户可以通过推荐亲朋好友入职来赚取佣金。被推荐者入职后满一定天数，即可通过线上或线下的方式将佣金提现。</p>
    </div>
    <div class="s23">
	</div>
</div>
<!--/第2屏-->


<!--第3屏-->
<div class="section section3">
	<div class="bg" style="background:url(${ctx}/static/images/datas/recommend/fullpage3.jpg) no-repeat center;"></div>
	<strong style="top:15%;padding-left:115px;font-size:40px;color:#fff;font-weight:bold;">手机扫一扫，立即赚大钱</strong>
	<div class="s32"></div>
    <div class="s33"></div>
</div>
<!--/第3屏-->


<!--第4屏-->
<div class="section section4">
	<div class="bg" style="#fff"></div>
    <strong style="top:10%;padding-left:50px;font-size:30px;color:#3da905;font-weight:bold;">①&nbsp;推荐应聘</strong>
    <div class="s41"></div>
    <div class="s42"></div>
</div>
<!--/第4屏-->



<!--第5屏-->
<div class="section section5">
	<strong style="top:10%;padding-left:50px;font-size:30px;color:#fef722;font-weight:bold;">②&nbsp;成功入职</strong>
	<div class="bg" style="background:url(${ctx}/static/images/datas/recommend/fullpage3.jpg) no-repeat center;"></div>
    <div class="s51"></div>
    <div class="s52"></div>
</div>
<!--/第三5屏-->

<!--第6屏-->
<div class="section section6">
	<strong style="top:8%;margin-left:-415px;font-size:30px;color:#3da905;font-weight:bold;">③&nbsp;领取佣金</strong>
	<div class="bg" style="background:#fff"></div>
    <div class="s61">到账佣金会在你的个人账户显示，可随时进行提现</div>
    <div class="s62"></div>
    <div class="s63"></div>
    <div class="s64"></div>
</div>
<!--/第6屏-->

<!---->
<div style="width:122px;height:270px;position:fixed;right:10px;top:50%;margin-top:-135px;background:url(${ctx}/static/images/datas/recommend/qr.jpg)"></div>
<!---->


</body>
</html>