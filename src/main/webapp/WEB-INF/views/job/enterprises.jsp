<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:if test="${not empty sessionScope.session_key_branch_name}">
	<c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta name= "mobile-agent"content= "format=html5;url=http://m.youlanw.com/<c:if test="${not empty sessionScope.session_key_branch_web_prefix}">${fn:trim(sessionScope.session_key_branch_web_prefix)}_qiye/</c:if>">
<title>我要进名企_${cityName }企业招聘_${cityName }名企招聘</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="${cityName }优蓝网我要进名企栏目为您提供${cityName }优质放心的名企信息,想要在${cityName }拿高工资,享受高福利待遇,想在${cityName }找好工作,${cityName }名企满足您,名企招聘更放心,工作更舒心."/>
<meta name="description" content="${cityName }企业招聘,${cityName }名企招聘网,${cityName }优质企业,${cityName }放心企业"/>
<style>
.company-info .company-name{overflow: initial !important;}
.company-info .company-name .certify-hover{right: -287px !important;}
.compName{max-width: 160px !important;}	
</style>
</head>
<body>
    <!---->
    <div class="top-entry">
    	<div class="w1200 relative">
    		<span class="fl f14">共有<label class="num">${companyCount }</label> 家知名企业入驻，今日新增<label class="num">${yesterdayCompany }</label> 家企业</span>
            <div class="search-box2 rt0">
	            <input type="text" class="tip-text"  placeholder="请输入公司信息"  name="search_LIKE_searchName" /><input type="button" id = "btn_search" value="搜索" class="btn search-enter"/>
            </div>
            
        </div>
    </div>
    <!--/-->
    
    <!--名企-->
     <c:if test="${not empty  topRecommendCompany }">
     <div class="w990 mt10">
    	<div class="mr-frbox">
		<a class="mr-frBtnL prev"></a>
		<div class="mr-frUl">
			<ul>
              <c:forEach items="${topRecommendCompany}" var="recommendCompany">
	          <li><a href="${recommendCompany.link }" target="_blank">
					<img src="${cdc:getImagePathO(recommendCompany.img)}" width="200" height="151" />
               	 <p>${recommendCompany.title}</p>
              </a></li>
           </c:forEach>
			</ul>
		</div>
		<a class="mr-frBtnR next"></a>
	</div>
    </div>
    </c:if>
    <!--/名企-->

    
    
    <!---->
    <div class="w1200 mt10">
    	<!--left-->
    	<div class="box-l-wrapper">
        	<!--列表-->
        	<div class="box-l">
                <div class="company-each-list clearfix">
                   <div class="company-news company-wanted">
                     <h4 class="company-title"><span>大家都想进</span></h4>
                     <ul class="clearfix">
                     <c:forEach items="${hottestCompany}" var="company" varStatus="i">
	                     <li>
	                         <div class="company-info-one clearfix"><i class="company-num">${i.index+1}</i> <span class="company-text">${company.name }</span>  <span class="company-ex fr"><em class="red"><c:if test="${empty company.pointOfPraise }">0</c:if>${company.pointOfPraise }</em>人想进</span></div>
	                         <div class="company-info-two clearfix">
	                           <i class="company-num">${i.index+1}</i>
	                           <a href="${ctx}/qiye_${company.id }.html"  target="_blank">
	                            <c:choose>
									<c:when test="${empty company.logo.imgpath}">
										<div class="com-exam01">${company.abbreviation }</div>
									</c:when>
									<c:otherwise>
										<img src="${cdc:getImagePath320(company.logo.imgpath)}" width="76" height="57" />
									</c:otherwise>
								</c:choose></a>
	                           <div class="company-pla"><a href="${ctx}/qiye_${company.id }.html" target="_blank"><h4>${company.name }</h4></a>
	                           <p><span class="company-ex"><em class="red"><c:if test="${empty company.pointOfPraise }">0</c:if>${company.pointOfPraise }</em>人想进</span>
	                           <a href="${ctx}/qiye_${company.id}.html" target="_blank" class="company-more">了解更多>></a></p></div>
	                         </div>
	                     </li>
					  </c:forEach>
                     </ul>
                   </div>
                   
                    <div class="company-news company-wanted" style="margin-right:0;">
                     <h4 class="company-title"><span>大家都在咨询</span></h4>
                     <ul class="clearfix">
                     <c:forEach items="${consultingCompany}" var="company" varStatus="i">
	                     <li>
	                         <div class="company-info-one clearfix"><i class="company-num">${i.index+1}</i> <span class="company-text">${company.key.name }</span>  <span class="company-ex fr"><em class="red">${company.value }</em>人咨询</span></div>
	                         <div class="company-info-two clearfix">
	                           <i class="company-num">${i.index+1}</i>
	                           <a href="${ctx}/qiye_${company.key.id }.html"   target="_blank">
	                            <c:choose>
									<c:when test="${empty company.key.logo.imgpath}">
										<div class="com-exam01">${company.key.abbreviation }</div>
									</c:when>
									<c:otherwise>
										<img src="${cdc:getImagePath320(company.key.logo.imgpath)}" width="76" height="57" />
									</c:otherwise>
								</c:choose></a>
	                           <div class="company-pla"><a href="${ctx}/qiye_${company.key.id }.html"  target="_blank"><h4>${company.key.name }</h4></a>
	                           <p><span class="company-ex"><em class="red">${company.value }</em>人咨询</span>
	                           <a href="${ctx}/qiye_${company.key.id}.html" target="_blank" class="company-more">了解更多>></a></p></div>
	                         </div>
	                     </li>
					  </c:forEach>
                     </ul>
                   </div>
                   
                </div>
                <!--做普工，最好的企业-->
                <div class="company-each-list clearfix">
                   <div class="company-box">
                    <h4 class="company-title"><span>做普工，最好的企业</span>  <a href="${ctx }/mingqi/" class="company-more-new">更多 ></a></h4>
                     <div class="company-info">
                       <c:if test="${fn:length(companyListOne) > 0}">
                       <div class="company-info-lt">
                         <a href="${ctx}/qiye_${companyListOne[0].companyId }.html" class="logo-box">
                         <c:choose>
							<c:when test="${empty companyListOne[0].companyLogo}">
								<div class="com-exam02">${companyListOne[0].abbreviation }</div>
							</c:when>
							<c:otherwise>
								<img src="${cdc:getImagePath320(companyListOne[0].companyLogo)}" width="210" height="105" />
							</c:otherwise>
						</c:choose></a>
                         <a href="${ctx}/qiye_${companyListOne[0].companyId }.html" title="${companyListOne[0].companyName}">
                         <h5 class="company-name">
                         	<span class="compName">${companyListOne[0].companyName}</span>
                         </h5>
                         
                         </a>
                         <c:if test="${not empty  companyListOne[0].jobId}"><a href="${ctx}/zhaopin_${companyListOne[0].jobId}.html" >
                         <p class="mb10">普工<span class="red ml20">￥${companyListOne[0].salaryFrom }<c:if test="${not empty  companyListOne[0].salaryTo}">-${companyListOne[0].salaryTo }元/月</c:if>
		                 <c:if test="${empty  companyListOne[0].salaryTo}">起</c:if></span></p></a></c:if>
                         <p class="tags-g">
                          <c:forEach items="${companyListOne[0].jobLabel}" var="jobLabel">
                          <a href="javascript:void(0)">${jobLabel }</a>
                          </c:forEach>
                         </p>
                       </div>
                       </c:if>
                       <div class="company-info-rt clearfix">
                         <ul>
                         <c:forEach items="${companyListOne}" var="recommendCompany" varStatus="status">
                         <c:if test="${status.index>0 }">
						    <li>
                             <div class="company-top">
                               <a href="${ctx}/qiye_${recommendCompany.companyId }.html">
                               <c:choose>
								<c:when test="${empty recommendCompany.companysence}">
									<div class="com-exam02">${recommendCompany.abbreviation }</div>
								</c:when>
								<c:otherwise>
									<img src="${cdc:getImagePath320(recommendCompany.companysence) }" width="97" height="68"  />
								</c:otherwise>
							</c:choose>
                               </a>
                               <a href="${ctx}/qiye_${recommendCompany.companyId }.html"><span class="company-exp">${tc:subString(recommendCompany.remark,25)}</span></a>
                             </div>
                             <a href="${ctx}/qiye_${recommendCompany.companyId }.html" title="${recommendCompany.companyName }">
                             <h5 class="company-name">
                             	<span class="compName">${recommendCompany.companyName }</span>
                             </h5>
                             
                             </a>
                             <c:if test="${not empty  recommendCompany.jobId}"><a href="${ctx}/zhaopin_${recommendCompany.jobId}.html"><p class="mb10">普工  <span class="red ml20">￥${recommendCompany.salaryFrom }<c:if test="${not empty  recommendCompany.salaryTo}">-${recommendCompany.salaryTo }元/月</c:if>
		                    <c:if test="${empty  recommendCompany.salaryTo}">起</c:if></span></p></a></c:if>                 
                           </li>
                           </c:if>
						  </c:forEach>
                         </ul>
                       </div>
                     </div>
                   </div>                  
                </div>
                
                
                
                <!--做技工，最好的企业-->
                <div class="company-each-list clearfix">
                   <div class="company-box">
                    <h4 class="company-title"><span>做技工，最好的企业</span>  <a href="${ctx }/mingqi/"  class="company-more-new">更多 ></a></h4>
                     <div class="company-info">
                       <c:if test="${fn:length(companyListTWO) > 0}">
                       <div class="company-info-lt">
                         <a href="${ctx}/qiye_${companyListTWO[0].companyId }.html" class="logo-box">
                         <c:choose>
								<c:when test="${empty companyListTWO[0].companyLogo}">
									<div class="com-exam02">${companyListTWO[0].abbreviation }</div>
								</c:when>
								<c:otherwise>
									<img src="${cdc:getImagePath320(companyListTWO[0].companyLogo)}" width="210" height="105"  />
								</c:otherwise>
							</c:choose>
                         </a>
                         <a href="${ctx}/qiye_${companyListTWO[0].companyId }.html" title="${companyListTWO[0].companyName}">
                         	<h5 class="company-name">
                         		<span class="compName">${companyListTWO[0].companyName}</span>
                         	</h5>
                         </a>
                        <c:if test="${not empty  companyListTWO[0].jobId}"> <a href="${ctx}/zhaopin_${companyListTWO[0].jobId}.html" >
                         <p class="mb10">技工<span class="red ml20">￥${companyListTWO[0].salaryFrom }<c:if test="${not empty  companyListTWO[0].salaryTo}">-${companyListTWO[0].salaryTo }元/月</c:if>
		                <c:if test="${empty  companyListTWO[0].salaryTo}">起</c:if></span></p></a></c:if>
		                <c:if test="${empty  recommendCompany.jobId}"><p class="mb10"></p></c:if>   
                         <p class="tags-g">
                          <c:forEach items="${companyListTWO[0].jobLabel}" var="jobLabel">
                          <a href="javascript:void(0)">${jobLabel }</a>
                          </c:forEach>
                         </p>
                       </div>
                       </c:if>
                       <div class="company-info-rt clearfix">
                         <ul>
                         <c:forEach items="${companyListTWO}" var="recommendCompany" varStatus="status">
                         <c:if test="${status.index>0 }">
						    <li>
                             <div class="company-top">
                               <a href="${ctx}/qiye_${recommendCompany.companyId }.html" >
                               <c:choose>
									<c:when test="${empty recommendCompany.companysence}">
										<div class="com-exam02">${cdc:getImagePath320(recommendCompany.abbreviation) }</div>
									</c:when>
									<c:otherwise>
										<img src="${cdc:getImagePath320(recommendCompany.companysence)}" width="97" height="68" />
									</c:otherwise>
								</c:choose>
                               <a href="${ctx}/qiye_${recommendCompany.companyId }.html" ><span class="company-exp">${tc:subString(recommendCompany.remark,25)}</span></a>
                             </div>
                             <a href="${ctx}/qiye_${recommendCompany.companyId }.html"  title="${recommendCompany.companyName }">
                             	<h5 class="company-name">
	                             	<span class="compName">${recommendCompany.companyName }</span>
                             	</h5>
                             </a>
                            <c:if test="${not empty  recommendCompany.jobId}"> <a href="${ctx}/zhaopin_${recommendCompany.jobId}.html" ><p class="mb10">技工  <span class="red ml20">￥${recommendCompany.salaryFrom }<c:if test="${not empty  recommendCompany.salaryTo}">-${recommendCompany.salaryTo }元/月</c:if>
		                	<c:if test="${empty  recommendCompany.salaryTo}">起</c:if></span></p></a></c:if>
		                	 <c:if test="${empty  recommendCompany.jobId}"><p class="mb10"></p></c:if>                  
                           </li>
                           </c:if>
						  </c:forEach>
                         </ul>
                       </div>
                     </div>
                   </div>                  
                </div>
                <!--做客服，最好的企业-->
                <div class="company-each-list clearfix">
                   <div class="company-box">
                    <h4 class="company-title"><span>做客服，最好的企业</span>  <a href="${ctx }/mingqi/" class="company-more-new">更多 ></a></h4>
                     <div class="company-info">
                       <c:if test="${fn:length(companyListThree) > 0}">
                       <div class="company-info-lt">
                         <a href="${ctx}/qiye_${companyListThree[0].companyId }.html" class="logo-box">
                         <c:choose>
								<c:when test="${empty companyListThree[0].companyLogo}">
									<div class="com-exam02">${companyListThree[0].abbreviation }</div>
								</c:when>
								<c:otherwise>
									<img src="${cdc:getImagePath320(companyListThree[0].companyLogo)}" width="210" height="105"  />
								</c:otherwise>
							</c:choose>
                         </a>
                         <a href="${ctx}/qiye_${companyListThree[0].companyId }.html"  title="${companyListThree[0].companyName}">
                         	<h5 class="company-name">
                         		<span class="compName">${companyListThree[0].companyName}</span>
                         	</h5>
                         </a>
                          <c:if test="${not empty  companyListThree[0].jobId}"><a href="${ctx}/zhaopin_${companyListThree[0].jobId}.html">
                         <p class="mb10">客服<span class="red ml20">￥${companyListThree[0].salaryFrom }<c:if test="${not empty  companyListThree[0].salaryTo}">-${companyListThree[0].salaryTo }元/月</c:if>
		                <c:if test="${empty  companyListThree[0].salaryTo}">起</c:if></span></p></a></c:if>
                         <p class="tags-g">
                          <c:forEach items="${companyListThree[0].jobLabel}" var="jobLabel">
                          <a href="javascript:void(0)">${jobLabel }</a>
                          </c:forEach>
                         </p>
                       </div>
                       </c:if>
                       <div class="company-info-rt clearfix">
                         <ul>
                         <c:forEach items="${companyListThree}" var="recommendCompany" varStatus="status">
                         <c:if test="${status.index>0 }">
						    <li>
                             <div class="company-top">
                               <a href="${ctx}/qiye_${recommendCompany.companyId }.html" >
                               <c:choose>
									<c:when test="${empty recommendCompany.companysence}">
										<div class="com-exam02">${recommendCompany.abbreviation }</div>
									</c:when>
									<c:otherwise>
										<img src="${cdc:getImagePath320(recommendCompany.companysence)}" width="97" height="68" />
									</c:otherwise>
								</c:choose>
                               <a href="${ctx}/qiye_${recommendCompany.companyId }.html"><span class="company-exp">${tc:subString(recommendCompany.remark,25)}</span></a>
                             </div>
                             <a href="${ctx}/qiye_${recommendCompany.companyId }.html"  title="${recommendCompany.companyName }">
                             	<h5 class="company-name">
                             		<span class="compName">${recommendCompany.companyName }</span>
                             	</h5>
                             </a>
                             <c:if test="${not empty  recommendCompany.jobId}"><a href="${ctx}/zhaopin_${recommendCompany.jobId}.html"><p class="mb10">客服 <span class="red ml20">￥${recommendCompany.salaryFrom }<c:if test="${not empty  recommendCompany.salaryTo}">-${recommendCompany.salaryTo }元/月</c:if>
		                	<c:if test="${empty  recommendCompany.salaryTo}">起</c:if></span></p></a></c:if>
		                	<c:if test="${empty  recommendCompany.jobId}"><p class="mb10"></p></c:if>                    
                           </li>
                           </c:if>
						  </c:forEach>
                         </ul>
                       </div>
                     </div>
                   </div>                  
                </div>
                
                
               
            </div>
            <!--/列表-->
            
        </div>
        <!--/left-->
        <!--right-->
        <div class="box-r-wrapper">
        	
        	<!--优蓝网手机版-->
            <div class="box-r">
                <a href="http://m.youlanw.com/<c:if test="${not empty sessionScope.session_key_branch_web_prefix}">${fn:trim(sessionScope.session_key_branch_web_prefix)}_qiye/</c:if>" >
                <div class="m-entry">
                    <div class="text">
                    	<i></i>
                        <h3>优蓝网手机版</h3>
                        <p>m.youlanw.com</p>
                    </div>
                    <img src="${ctx }/static/images/QR2.png" alt=""/>
                </div>
                </a>
            </div>
            <!--/优蓝网手机版-->
            <!--优蓝网微信版-->
            <div class="box-r mt10">
                <div class="m-entry">
                    <div class="text">
                    	<i class="wechat"></i>
                        <h3>优蓝网微信版</h3>
                        <p>微信号：youlanqiuzhi</p>
                    </div>
                   <img src="http://www.youlanw.com/static/images/QR.jpg" alt="" class="mt5"/>
                </div>
            </div>
            <!--/优蓝网微信版-->
            <!--广告位-->
			<c:forEach items="${rightRecommendAd}" var="recommend">
	            <div class="ad-r">
	                <a href="${recommend.link}">
	                 <c:choose>
						<c:when test="${empty recommend.img}">
							<img src="http://www.youlanw.com/static/images/pic-default.jpg"  />
						</c:when>
						<c:otherwise>
							<img src="${cdc:getImagePathO(recommend.img)}" />
						</c:otherwise>
					</c:choose></a>
	            </div>
           </c:forEach>
            <!--广告位-->
           <!--最近浏览-->
				<div class="box-r mt10" >
					<div class="right-fixed" id="recent_browse_list">
					<h2 class="box-title">最近浏览职位</h2>
					<div class="job-list2">
						<p class="no-result">
							<span><i></i></span>暂无浏览记录
						</p>
					</div>
				</div>
				</div>
			<!--最近浏览-->
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
    <!---->
    
<!--/page-wrapper-->
<script src="http://www.youlanw.com/static/js/jquery.SuperSlide.2.1.js"></script>
<script type="text/javascript">
$(function(){
	YL.qiye.enterprise.init();
});
</script>
</body>
</html>