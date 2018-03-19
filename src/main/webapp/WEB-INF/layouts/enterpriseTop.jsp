<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

	<!--ent-header-->
    <div class="ent-header">
    	<div class="box-out">
        	<a href="${ctx }/" target="_blank"><img src="${ctx }/static/images/logo2.png" alt="优蓝网" class="logo fl"/></a>
            <span class="tag fl">企业版</span>
            <div class="fr">
             <c:if test="${empty sessionScope.session_enterprise_user_key}">
            	<span class="ent-icon-tel fl"><i></i>咨询热线：4008-777-816</span> 
          	</c:if>
          	<c:if test="${not empty sessionScope.session_enterprise_user_key}">
          		欢迎回来，${sessionScope.session_enterprise_user_key.userName }<label class="line">|</label><a href="${ctx}/enterprise/logout" class="exit">退出</a>
       		</c:if>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--/ent-header-->
