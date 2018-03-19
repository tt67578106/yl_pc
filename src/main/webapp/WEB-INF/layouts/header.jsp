<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- top-menu -->
<div class="top-menu-wrapper">
		<div class="w990">
			<a href="${ctx}/"><img src="${ctx}/static/images/logo.png" alt="优蓝网" class="logo fl"/></a>
            <span class="fl change">
                <p id="c_branch_name">${sessionScope.session_key_branch_name}</p>
                <a href="${ctx}/about/chooseCity" class="c999">[切换]</a>
            </span>
            <span class="list">
            	<a href="${ctx}/" class="${homeActive }">首页</a>
                <a href="${ctx}/zhaopin/" class="${jobActive }">我要找工作</a>
                <a href="${ctx}/qiye/" class="${companyActive }">我要进名企</a>
                <a href="http://www.youlanw.com/zone" class="${articleActive }">优蓝家园</a>
            </span>
            <div class="icon-tel3 fr">服务热线：4008-777-816</div>
		</div>
	</div>
<!-- top-menu -->

<script>
	$(function() {
		YL.header.init();
	});
	
</script>

