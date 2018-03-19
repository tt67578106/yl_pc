<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    	<!--left-->
        <div class="ent-box-l-wrapper">
        	<!--菜单一-->
            <div class="ent-box-l">
            	<div class="ent-menu">
            		<a href="${ctx }/enterpriseJob/jobPost" class="${jobPostActive }"><i class="post"></i>发布职位</a>
                	<a href="${ctx }/enterpriseJob" class="${jobListActive }"><i></i>在招职位（${jobCount }）</a>
                    <a href="${ctx }/enterprise/resumes" class="bdb0 ${resumesActive }"><i class="resume"></i>收到简历（${resumeCount }）</a>
                </div>
            </div>
            <!--/菜单一-->
            <!--菜单二-->
            <div class="ent-box-l mt15">
            	<div class="ent-menu">
                	<a href="${ctx }/enterprise" class="${enterpriseActive }"><i class="data"></i>企业资料</a>
                    <a href="${ctx }/album" class="${albumActive }"><i class="album"></i>企业相册</a>
                    <a href="${ctx }/enterprise/certify" class="${certifyActive} }"><i class="cer"></i>申请认证</a>
                    <a href="${ctx }/enterprise/changePassword" class="bdb0 ${changepwdActive }"><i class="pwd"></i>修改密码</a>
                </div>
            </div>
            <!--/菜单二-->
        </div>
        <!--/left-->
