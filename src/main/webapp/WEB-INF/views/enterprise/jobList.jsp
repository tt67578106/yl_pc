<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在招职位</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
</head>
<body>
  <!---->
    <div class="box-out mt15">
    	<!--left-->
        <jsp:include page="/WEB-INF/layouts/enterpriseLeft.jsp"></jsp:include>
        <!--/left-->
        <!--right-->
        <div class="ent-box-r-wrapper">
            <div class="ent-box-r">
            	<c:if test="${empty page.content }">
                    <div class="ent-no-result">
                		<span class="icon-smile"><i></i></span>您还没有发布职位，快去<a href="${ctx }/enterpriseJob/jobPost" class="ml10">发布岗位</a>吧！
               		</div>
                </c:if>
                <c:if test="${not empty page.content }">
            	<!---->
                <div class="ent-jobs-out">
                    <div class="ent-jobs">
                        <ul class="ent-jobs-each col-name">
                            <li class="title">职位标题</li>
                            <li class="cat">分类</li>
                            <li class="num">人数</li>
                            <li class="time">发布时间</li>
                            <li class="state">状态</li>
                            <li class="oper">操作</li>
                            <li class="edit">编辑</li>
                        </ul>
                         <c:forEach items="${page.content }" var="job">
                        <ul class="ent-jobs-each">
                            <li class="title">
                            	<c:if test="${job.jobConfig.isPublish==1 }"><a href="${ctx}/zhaopin_${job.id}.html" target="_blank">${job.title }</a></c:if>
                            	<c:if test="${job.jobConfig.isPublish!=1 }">${job.title }</c:if>
                            </li>
                            <li class="cat">${job.jobType }</li>
                            <li class="num">${job.recruitcount }</li>
                            <li class="time">${job.updatetimeString }</li>
                            <c:if test="${job.jobConfig.isPublish==-1 }">
                           	 <li class="state">审核中</li>
                           	 <li class="oper">-</li>
                           	 <li class="edit">-</li>
                            </c:if>
                            <c:if test="${job.jobConfig.isPublish==0 }">
                           	 <li class="state">未通过</li>
                           	 <li class="oper">-</li>
                           	 <li class="edit"><a href="${ctx }/enterpriseJob/jobPost?jobid=${job.id}&&type=edit">修改</a></li>
                            </c:if>
                            <c:if test="${job.jobConfig.isPublish==1 }">
                             	<li class="state">已发布</li>
                            	<c:if test="${job.jobConfig.isRecruitment==0 }"><li class="oper"><a href="${ctx }/enterpriseJob/isRecruitment?jobid=${job.id}&&isRecruitment=1">停招</c:if><c:if test="${job.jobConfig.isRecruitment!=0 }"><li class="oper stop"><a href="${ctx }/enterpriseJob/isRecruitment?jobid=${job.id}&&isRecruitment=0">在招</c:if></a></li>
                           		<li class="edit"><a href="${ctx }/enterpriseJob/jobPost?jobid=${job.id}&&type=edit">编辑</a></li>
                            </c:if>
                        </ul>
                        </c:forEach>
                        <div class="clear"></div>
                    </div>
                </div>
                <!--分页-->
                 <c:if test="${not empty page.content}">
		            <div class="paging"><tags:pagination paginationSize="5" page="${page }" pageType="enterpriseJob?"/></div>
		         </c:if> 
                <!--/分页 -->
                </c:if>
                <!--/-->
                <div class="clear"></div>
            </div>
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
    <!---->
    <script type="text/javascript">
    $(function(){
    	<c:if test="${not empty error}">
			alert("${error}");
		</c:if>
    })
    </script>
</body>
</html>