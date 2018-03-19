<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="Qe3ngwHAwx" />
<title>${actWanlixingPeriod.title}_中国就业万里行_优蓝网</title>
</head>
<body>
<link rel="stylesheet" href="${ctx}/static/css/jiuye.css" />
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
</head>
<body>
<!--page-wrapper-->
<div class="page-wrapper">

  	 <!--top-menu-->
<div class="top-menu">
	<div class="box-out">
		<div class="fl"><a href="http://www.youlanw.com">优蓝网首页</a></div>
		<div class="fr">
			<label class="icon-common icon-tel">4008-777-816</label>
			<a href="${ctx }/about/mobile.html" target="_blank" class="icon-common icon-phone" rel="nofollow">手机优蓝</a>
			<a href="http://weibo.com/youlanwHR" target="_blank" rel="nofollow">官方微博</a>
		</div>
	</div>
</div>
<!--/top-menu--->  <!--jy-menu-->
   <div class="jy-menu-out">
       <div class="jy-menu">
           <div class="box-out">
               <a href="${ctx }/trip10000"><img src="${ctx}/static/images/logo-jy.png" alt="优蓝网" class="logo fl"/></a>
               <span class="list">
                   <a href="${ctx }/trip10000" class="${wlxIndexActive }">大赛首页</a>
                   <a href="${ctx }/trip10000/projects" class="${projectsActive }">参赛项目</a>
                   <a href="${ctx }/trip10000/tutors" class="${tutorsActive }">导师阵容</a>
                   <a href="${ctx }/trip10000/itemsList" class="${itemsListActive }">评委风采</a>
                   <a href="${ctx }/trip10000/rules" class="${rulesActive }">参赛规则</a>
                   <a href="${ctx }/trip10000/prizeSetting" class="${prizeSettingActive }">奖品设置</a>
               </span>
               <a href="${ctx }/trip10000/registration/signUp" class="btn-g-2 fr">我的项目</a>
           </div>
       </div>
   </div>
<!--/jy-menu--->
    <!--海报-->
    <div class="jy-bg-spe">${actWanlixingPeriod.title }</div>
    <!--/海报-->
    
    <!--时间地点-->
    <div class="jy-spec-area">
    	<ul class="box-out">
        	<li>地点：${actWanlixingPeriod.address }</li>
            <li>时间：${actWanlixingPeriod.startDateString }</li>
            <li>发布日期：${actWanlixingPeriod.publishTimeString }</li>
            <li>参加人数：${actWanlixingPeriod.partNumber }人</li>
        </ul>
    </div>
    <!--/时间地点-->
    
    <!--专场信息-->
    <div class="box-out">
    	<h2 class="jy-title">专场信息</h2>
        <div class="hr-g"></div>
        <div class="text-g">
        	${actWanlixingPeriod.introduction }
        </div>
    </div>
    <!--/专场信息-->
    
    <!--参会嘉宾-->
    <div class="box-out">
    	<h2 class="jy-title">参会嘉宾</h2>
        <div class="hr-g"></div>
        <div class="jy-spec-guest">
        	<c:forEach items="${guestList}" var="guest" varStatus="status">
	        	<ul class="each">
	            	<li class="pic"><img src="${guest.headImage }" /></li>
	                <li class="name">${guest.fullName }</li>
	                <li class="post">${guest.resume }</li>
	            </ul>
			</c:forEach>
            <div class="clear"></div>
        </div>
    </div>
    <!--/参会嘉宾-->
      
    <!--右侧悬浮-->
   		<div class="jy-r-panel">
    	<a href="${ctx }/trip10000/registration/signUp" class="sign-link"><!--快速报名--></a>
        <a href="javascript:;" class="go-top"><!--返回顶部--></a>
   		 </div>
    <!--/右侧悬浮-->
 <jsp:include page="/WEB-INF/layouts/footer.jsp"></jsp:include>
</div>
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20150417"></script>
</body>
</html>
