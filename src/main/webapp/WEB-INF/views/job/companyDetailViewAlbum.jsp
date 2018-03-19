<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:if test="${not empty sessionScope.session_key_branch_name}">
	<c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta name= "mobile-agent"content= "format=html5;url=http://m.youlanw.com/company/album.html?c=${company.id }">
<title>${fn:trim(company.name) }图片相册_${fn:trim(company.abbreviation) }实景图片|工作及住宿条件</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="${fn:trim(company.name) },${fn:trim(company.abbreviation) }实景图片,${fn:trim(company.abbreviation) }"/>

<meta name="description" content="上海优蓝网【${fn:trim(company.name) }相册】,海量${fn:trim(company.abbreviation) }企业实景图片,工作环境图片,住宿条件及餐厅条件等图片和照片,让您更深入了解${fn:trim(company.abbreviation) }"/>
</head>
<body>
    <!--/top-menu--->
    <div class="nav-g"><a href="${ctx}/">${cityName }找工作</a>&gt;<a href="${ctx }/mingqi/">${cityName }名企招聘</a>&gt;<a href="${ctx}/qiye_${company.id}.html">${company.abbreviation }</a>&gt;<a href="${ctx }/company/album_${company.id}/">${company.abbreviation }相册</a></div>
   
   <!-- 相册 -->
   <div class="w1200 mt10">
   	<!--left-->
       <div class="box-l-wrapper">
       	<div class="box-l box-l-title">
       		<div class="box-g">
               	<h1 class="company-title">${company.name }企业相册</h1>
               	<p class="mb10">该企业共有<c:forEach items="${companyImageCount }" var="totalCount" varStatus="status"><c:if test="${status.index==0}">${totalCount.length }</c:if></c:forEach>张图片</p>
               </div>
               <ul class="album-g pdb10" id="enviroment">
               	<c:forEach items="${companyImage }" var="image">
               		<li>
	                   	<span class="pic"><img src="${cdc:getImagePath1080(image.imgPath) }" alt="${image.imgName }"/></span>
	                    <strong class="des">${image.imgName }</strong>
                   </li>
                 </c:forEach>
                 <div class="clear"></div>
               </ul>
           </div>
       </div>
       <!--/left-->
  		<!--right-->
       <div class="box-r-wrapper">
       	<!--查看企业主页-->
       	<div class="box-r bd0">
        	<div class="job-com">
            	<a href="${ctx }/qiye_${company.id }.html" class="more" target="_blank" title="${company.name }">
            		<h2>${company.name }</h2>
            	</a>
                <div class="inner">
               		<c:if test="${not empty company.logo.imgpath}">
			        	<img src="${cdc:getImagePath320(company.logo.imgpath)}" alt="${company.abbreviation }_logo" />
	                </c:if>
                    <p><span class="c999">企业规模：</span>${cdc:convertDicStaffscale(company.staffscale)}</p>
                    <p><span class="c999">所属行业：</span>${cdc:convertDicIndustryType(company.industryid)}</p>
                    <p><span class="c999">平均薪资：</span>${averageSalary }元/月</p>
                </div>
            </div>
           	<div class="job-com-a"><a href="${ctx }/company/album_${company.id }/">企业相册&gt;&gt;</a></div>
            <div class="job-com-a"><a href="${ctx }/company/job_${company.id }/">在招岗位&gt;&gt;</a></div>
            <div class="job-com-a"><a href="${ctx }/wenda/so_0_${company.id}_0_1/">企业问答&gt;&gt;</a></div>
        </div>
        <!--/查看企业主页-->
        <!--相册目录-->
        <div class="box-r mt15">
            <h2 class="box-title pdb0">相册目录</h2>
            <div class="album-cat">
             <c:forEach items="${companyImageCount }" var="imageCount">
              <c:if test="${not empty imageCount }">
                <a href="${ctx}/company/album_${company.id }/?type=${imageCount.id }" class='<c:if test="${type==imageCount.id }">current</c:if>'>${imageCount.imgName } (${imageCount.length })</a>
              	</c:if>
             </c:forEach>
            </div>
        </div>
           <!--/相册目录-->
          <!--最新岗位-->
        	<div class="box-r recruit-tags bd0">
           	<div class="title-bar"><span>最新岗位</span></div>
           	<ul class="about-recruit">
            	<c:forEach items="${newJobs }" var="job">
	            	<a href="${ctx }/zhaopin_${job.jobId }.html"><li>${job.companyAbbreviation}招聘${job.jobType}</li></a>
           		</c:forEach>
            </ul>
           </div>
       	<!--/最新岗位-->
       <!--/right-->
   	
   </div>
   <div class="clear"></div>
   </div>
    <!--/相册-->
	<script src="http://www.youlanw.com/static/js/jquery.SuperSlide.2.1.js"></script>
	<script type="text/javascript" src="http://www.youlanw.com/static/js/layer.min.js"></script>
    <script type="text/javascript" src="http://www.youlanw.com/static/js/extend/layer.ext.js"></script>
    <script type="text/javascript">
    //文字滚动
		$(".marquee").slide({mainCell:".bd",autoPlay:true,effect:"topMarquee",vis:8,interTime:50});
		$(function(){
			layer.photosPage({
				parent:'#enviroment',
				title: ''
			});
		})
    </script>
</body>
</html>