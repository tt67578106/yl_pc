<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="Qe3ngwHAwx" />
<title><sitemesh:title/>_优蓝网</title>
<meta name="description" content="优蓝网是一个免费招聘信息平台，这里拥有海量世界名企招工信息。蓝领工人找工作，招聘网站哪家强？中国优蓝网！全职打工、寒暑假兼职打工、招工启事（招工简章），一站轻松搞定！外出打工上优蓝，找工作不难！ - 优蓝网[www.youlanw.com]" />
<meta name="keywords" content="打工,找工作,招工,招聘" />
<link rel="stylesheet" href="${ctx}/static/css/common.css?v=20150417" />
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20150417"></script>
<script src="${ctx}/static/js/layer.min.js"></script>
<script src="${ctx}/static/js/jquery.SuperSlide.2.1.js"></script>
<script src="${ctx}/static/js/cookie.js"></script>
<sitemesh:head />
<script>
if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)||/(Android)/i.test(navigator.userAgent)) {
	window.location.href = "http://m.youlanw.com/?jumper="	+ window.location.pathname;
}
	$(function() {
		var bId = Cookie.getCookie("branchId");
		var bName = Cookie.getCookie("branchName");
		if (bId == null || bName == null || bId.length == 0 || bName.length == 0) {
			$.get("${ctx}/branch", function(result) {
				if (result.length == 1) {
					var branchId = result[0].id;
					var branchName = encodeURI(result[0].branchName);
					Cookie.setCookie("branchId", branchId, 30);
					Cookie.setCookie("branchName", branchName);
					$("#c_branch_name").text(result[0].branchName);
				} else if (result.length>1) {
					$('.change-city-trigger').click();
				} else {
					//alert("未开通分站");
				}
			});
		} else {
			$("#c_branch_name").text(decodeURI(bName));
		}
		//切换城市弹出层
		$('.change-city-trigger').click(function() {
			$.layer({
				type : 1,
				shade : [ 0 ],
				area : [ 'auto', '330px' ],
				title : '选择分站',
				border : [ 6, 0.3, '#000' ],
				shade : [ 0.5, '#000' ],
				shadeClose : true,
				page : {
					dom : '.change-city'
				}
			});
		})

		//点击城市
		$(".each-zone a").click(function() {
			$("#c_branch_name").text($(this).text() + "站");
			var branch = $(this).attr("branch");
			if (branch != null && branch != "") {
				Cookie.setCookie("branchId", branch, 30);
				Cookie.setCookie("branchName", $(this).text() + "站");
				$.post("${ctx}/branch", {
					"branchId" : branch
				}, function(result) {
					if (result == "success") {
						window.location.href = window.location.href;
					}
				});
			}
		});
		$('.search .screen li').click(function() {
			$(this).addClass('current').siblings().removeClass('current');
			if ($(this).text() == "职位") {
				$("#input_search_LIKE_jobname").attr({
					"name" : "search_LIKE_jobname",
					"placeholder" : "请输入职位信息"
				});
			} else {
				$("#input_search_LIKE_jobname").attr({
					"name" : "search_LIKE_company.name",
					"placeholder" : "请输入企业名称"
				});
			}
		});
		$(".carousel").slide({
			titCell : ".smallImg li",
			mainCell : ".big-img",
			effect : "left",
			autoPlay : true,
			delayTime : 200,
			interTime : 5000,
			startFun : function(i, p) {
				//控制小图自动翻页
				if (i == 0) {
					$(".carousel .sPrev").click()
				} else if (i % 4 == 0) {
					jQuery(".carousel .sNext").click()
				}
			}
		});
		//轮播图小图左滚动切换
		$(".carousel .smallScroll").slide({
			mainCell : "ul",
			delayTime : 200,
			interTime : 5000,
			vis : 4,
			scroll : 1,
			effect : "left",
			autoPage : true,
			prevCell : ".sPrev",
			nextCell : ".sNext",
			pnLoop : false
		});
		//轮播图小图最后一张去边框
		$('.smallImg li:last').css('border', 0);
		<c:if test="${not empty clean}">Cookie.delCookie("yl_vali_cookie");</c:if>
	});
</script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?83a185fc9f52ffcad1bcb9e4d889301b";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();
</script>
<script src='//kefu.easemob.com/webim/easemob.js?tenantId=1780&hide=true' async='async'></script>
</head>
<body>
<div class="page-wrapper bg-grey">
	<!--top-menu-->
	<div class="top-menu">
		<div class="w990">
			<div class="fl">您好，欢迎来到优蓝网
			<c:if test="${empty sessionScope.session_user_key}">
			<a href="${ctx}/login" class="login-layer-trigger" rel="nofollow">登录</a><%-- <a href="${ctx}/register" class="reg-layer-trigger" rel="nofollow">免费注册</a> --%>
			</c:if>
			</div>
			<div class="fr">
				<label class="icon-common icon-tel">4008-777-816</label>
				<a href="${ctx}/about/mobile.html" target="_blank" class="icon-common icon-phone" rel="nofollow">手机优蓝</a>
				<a href="http://weibo.com/youlanwHR" target="_blank" rel="nofollow">官方微博</a>
			</div>
		</div>
	</div>
	<!--/top-menu--->
    <!--logo search QR-->
    <div class="logo-search full-width bg-white">
        <div class="w990 relative">
        	<a href="${ctx}/"><img src="${ctx}/static/images/logo.png" class="logo" alt="优蓝网"/></a>
            <img src="${ctx}/static/images/slogan.png" class="slogan" alt="免费、靠谱、快速"/>
            <div class="c-city">
            <p class="current" id="c_branch_name">${sessionScope.session_key_branch_name}</p>
            <p><a href="javascript:void(0);" class="link change-city-trigger">切换</a></p>
            </div>
            <div class="search">
			<ul class="screen">
				<li <c:if test="${empty tabs || tabs == 1}">class="current"</c:if>>职位</li>
				<li <c:if test="${tabs == 2}"> class="current"</c:if>>企业</li>
			</ul>
			<form action="${ctx}/search/" method="get">
				<div class="search-box">
					<span class="icon-common icon-search"></span> <input type="text" class="search-input" <c:if test="${empty tabs || tabs == 1}">name="search_LIKE_jobname"</c:if> <c:if test="${tabs == 2}">name="search_LIKE_company.name"</c:if> id="input_search_LIKE_jobname" placeholder="请输入职位信息" value="${wd}" /> <input type="submit" value="搜索" class="btn search-btn" />
					<div class="hot">
						热门搜索：
						<a href="${ctx}/s_type_普工">普工</a>
						<a href="${ctx}/s_type_销售">销售</a>
						<a href="${ctx}/s_type_技工">技工</a>
						<a href="${ctx}/s_type_司机">司机</a>
						<a href="${ctx}/s_type_服务员">服务员</a>
					</div>
				</div>
			</form>
		</div>
            <img src="${ctx}/static/images/qr.png" alt="优蓝网微信公众平台二维码" class="qr"/>
            <div class="clear"></div>
        </div>
    </div>
    <!--/logo search QR-->
    <!--main-menu-->
 <div class="main-menu-wrapper">
    	<div class="w990 main-menu">
        	<a href="${ctx}/" class="${homeActive }">首页</a>
            <a href="${ctx}/zhaopin/">我要找工作</a>
            <a href="${ctx}/qiye/">我要进名企</a>
            <a class="${articleActive }" href="${ctx}/xiaolan/">优蓝家园</a>
           <div class="my">
			<a href="javascript:void(0);" class="sub-title">
				<i class="icon-common icon-my"></i>我的优蓝<i class="icon-common icon-arrow-d"></i>
			</a>
			<div class="my-sub-menu">
				<a href="${ctx}/my">我的微简历</a>
				<c:if test="${sessionScope.session_user_key !=null && sessionScope.session_user_key.id!=null}">
					<a href="${ctx}/my/signupRecord" class="record">报名记录</a>
					<%-- <a href="${ctx}/my/changePassword" class="password">修改密码</a> --%>
					<a href="${ctx}/logout" class="exit">退出登录</a>
				</c:if>
			</div>
		</div>
     </div>
</div>
    <!--/main-menu-->
    <sitemesh:body />
    <!--悬浮菜单-->
	<ul class="float-menu">
		<li onclick="easemobIM()" tenantId=1780>
			<span class="des">在线咨询</span><span class="qq"></span>
		</li>
		<li class="signup-wrapper">
			<a href="${ctx}/signup/fastSignUp">
				<span class="des">快速报名</span><span class="signup"></span>
			</a>
		</li>
		<li class="to-wrapper">
			<span class="des">返回顶部</span><span class="to"></span>
		</li>
	</ul>
    <div class="footer-supply"></div>
	<div class="footer-menu-wrapper">
	    <p class="text-center footer-sub"><a href="${ctx}/about/index.html">关于优蓝</a>|<a href="${ctx }/about/agreement.html">用户协议</a>|<a href="${ctx}/about/statement.html">网站声明</a>|<a href="${ctx }/about/mediaReports.html">媒体报道</a>|<a href="${ctx}/about/contact.html">联系我们</a>|<a href="${ctx}/feedback.html">意见反馈</a>|<a href="javascript:void(0)">网站地图</a></p>
	   <p class="text-center">沪ICP备14033709号-1&nbsp;&nbsp;&nbsp;Copyright&nbsp;&copy;&nbsp;2013-2015 youlanw.com All Rights Reserved
&nbsp;&nbsp;</p>	
	</div>
    </div>
    <!--切换城市-->
<div class="change-city">
	<ul class="each-zone">
		<li class="name relative">
			城市：<i class="icon-hot"></i>
		</li>
		<li class="list">
			<a href="javascript:;" branch="1">上海</a>
			<a href="javascript:;" branch="2">苏州</a>
			<a href="javascript:;" branch="4">南京</a>
			<a href="javascript:;" branch="9">重庆</a>
			<a href="javascript:;" branch="8">成都</a>
			<a href="javascript:;" branch="3">杭州</a>
			<a href="javascript:;" branch="13">天津</a>
			<a href="javascript:;" branch="16">武汉</a>
			<a href="javascript:;" branch="10">厦门</a>
			<a href="javascript:;" branch="5">宁波</a>
		</li>
	</ul>
	<div class="hr-g fl full-width"></div>
	<ul class="each-zone">
		<li class="name">华东：</li>
		<li class="list">
			<a href="javascript:void(0)" branch="2">苏州</a>
			<a href="javascript:void(0)" branch="1">上海</a>
			<a href="javascript:void(0)" branch="4">南京</a>
			<a href="javascript:void(0)" branch="3">杭州</a>
			<a href="javascript:void(0)" branch="5">宁波</a>
			<a href="javascript:void(0)" branch="12">无锡</a>
			<a href="javascript:void(0)" branch="6">昆山</a>
		</li>
	</ul>
	<ul class="each-zone">
		<li class="name">华南：</li>
		<li class="list">
			<a href="javascript:void(0)" branch="10">厦门</a>
			<a href="javascript:void(0)" branch="11">广州</a>
			<a href="javascript:void(0)" branch="17">海口</a>
		</li>
	</ul>
	<ul class="each-zone">
		<li class="name">西南：</li>
		<li class="list">
			<a href="javascript:void(0)" branch="8">成都</a>
			<a href="javascript:void(0)" branch="9">重庆</a>
			<a href="javascript:void(0)" branch="19">贵阳</a>
		</li>
	</ul>
	<ul class="each-zone">
		<li class="name">华北：</li>
		<li class="list">
			<a href="javascript:void(0)" branch="14">北京</a>
			<a href="javascript:void(0)" branch="13">天津</a>
			<a href="javascript:void(0)" branch="20">青岛</a>
		</li>
	</ul>
	<ul class="each-zone">
		<li class="name">华中：</li>
		<li class="list">
			<a href="javascript:void(0)" branch="7">郑州</a>
			<a href="javascript:void(0)" branch="16">武汉</a>
			<a href="javascript:void(0)" branch="18">合肥</a>
			<a href="javascript:void(0)" branch="15">南昌</a>
		</li>
	</ul>
	<div class="clear"></div>
</div>
	<!--/切换城市-->
 </body>
</html>
