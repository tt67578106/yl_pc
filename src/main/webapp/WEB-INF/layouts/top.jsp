<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!--[if lt IE 9]>
<div class="browser-tips">
    <h4>系统检测到您使用的浏览器版本过低，为达到更好的体验效果请升级您的浏览器</h4>
</div>
<![endif]-->
    <!--main-menu-->
    <div class="main-menu-wrapper">
    	<div class="w1200 main-menu">
        	<ul class="list">
        		<li>
	        		<a href="${ctx }/" class="logo-a">
	        			<img src="${ctx }/static/images/v3/logo.png" class="logo" alt="优蓝网" style="margin-top: -1px;" />
	        		</a>
        		</li>
        		<li class="mr60">
        			<jsp:include page="hotCity.jsp" />
		        </li>
		        <li><a href="${ctx }/" class="${homeActive }">首页</a></li>
                <li><a href="${ctx}/zhaopin/" class="${jobActive }">找工作</a></li>
                <li> <a href="${ctx }/zone" class="${articleActive }">社区</a></li>
                <li> <a href="${ctx }/wenda" class="${advisoryActive }">问答</a></li>
                <li class="cooperate">
                	<a href="javascript:void(0)">合作<i class="thin-arrow"></i></a>
                	<ul>
	        			<li><a href="http://b.youlanw.com" target="_blank">企业招聘</a></li>
	        		</ul>
                </li>
            </ul>
            <div class="right-menu" id="link_login">
            	<!--登录前-->
            	<c:if test="${empty sessionScope.session_user_key}">
            	<div class="login" >
            		<a href="${ctx}/login" class="login-layer">登录</a><%-- <span class="ml10 mr10">|</span><a href="${ctx}/register">注册</a> --%>
            	</div>
            	</c:if>
                <!--/登录前-->
                <!--登录后-->
                <c:if test="${not empty sessionScope.session_user_key}">
                <div class="user">
                	<c:if test="${not empty sessionScope.session_user_head_path_key && sessionScope.session_user_head_path_key!='null'}">
                		<img src="${cdc:getImagePathO(sessionScope.session_user_head_path_key)}" alt="${sessionScope.session_user_key.username }_头像" /><span>${sessionScope.session_user_key.username }</span><i></i>
                	</c:if>
                	<c:if test="${empty sessionScope.session_user_head_path_key ||sessionScope.session_user_head_path_key=='null'}">
                		<img src="${ctx }/static/images/v3/user.png" alt="${sessionScope.session_user_key.username }_头像" /><span>${sessionScope.session_user_key.username }</span><i></i>
                	</c:if>
                	<ul>
	        			<a href="${ctx}/my"><li class="person-icon">个人资料</li></a>
	        			<%-- <a href="${ctx}/my/changePassword"><li class="password-icon">修改密码</li></a> --%>
	        			<a href="${ctx}/logout"><li class="quit-icon">退出</li></a>
	        		</ul>
                </div>
                </c:if>
                <!--/登录后-->
            </div>
        </div>
    </div>
    <!--/main-menu-->

