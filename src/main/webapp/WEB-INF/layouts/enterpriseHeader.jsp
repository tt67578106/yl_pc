<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
 <!--企业信息-->
    <div class="box-out">
    	<div class="ent-info">
    		<div class="logo fl upload-file">
            	<div class="inner">
                    <table><tr><td><img src="<c:if test="${not empty enterprise.logo.imgpath }">${cdc:getImagePath320(enterprise.logo.imgpath) }</c:if><c:if test="${empty enterprise.logo.imgpath }">${ctx }/static/images/pic-default.jpg</c:if>" alt="" id="logo_img" /></td></tr></table>
                    <span class="opacity-bg"></span>
                    <span class="opacity-text">点击修改logo</span>
                </div>
                <input type="file" name="file" class="file hide"/>
            </div>
            <div class="fl top">
            	<h1 class="h-g-2 fl">${enterprise.name }</h1>
                <span class="fl ent-seal-1">
                	<c:if test="${empty certification||certification.status==0 }">未认 证</c:if>
                	<c:if test="${certification.status==1 }">已认 证</c:if>
                </span>
                <span class="fl ent-seal-2">${cdc:getCertificationLevelByCode(certification.certificationLevel) }</span>
            </div>
            <ul class="fl bottom">
            	<li><strong>新收到简历：</strong>${resumeCount }</li>
                <li><strong>在招职位：</strong>${jobCount }</li>
                <li><strong>上次登录时间：</strong>${lastLoginTime }</li>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <!--/企业信息-->
