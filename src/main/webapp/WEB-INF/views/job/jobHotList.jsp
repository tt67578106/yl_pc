<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:if test="${not empty sessionScope.session_key_branch_name}">
	<c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" />
</c:if>
<c:if test="${not empty param.search_LIKE_searchName && param.search_LIKE_searchName!='0'}">
	<c:set var="wdt" value="${fn:replace(param.search_LIKE_searchName,'<', '')}_" />
	<c:set var="wd" value="${fn:replace(param.search_LIKE_searchName,'<', '')}" />
</c:if>
<%-- <c:if test="${not empty param.search_LIKE_jobLabel && param.search_LIKE_jobLabel!='0'}">
	<c:set var="lb" value="${param.search_LIKE_jobLabel}_" />
</c:if> --%>
<c:if test="${not empty param.search_LIKE_jobType && param.search_LIKE_jobType!='0' && param.search_LIKE_jobType!='job'}">
	<c:set var="tp" value="${param.search_LIKE_jobType}_" />
	<c:set var="tpt" value="${param.search_LIKE_jobType}" />
	<c:set var="searchJobType" value="${param.search_LIKE_jobType}" />
	<c:if test="${not empty param.search_LIKE_subJobType && param.search_LIKE_subJobType!='0'}">
		<c:set var="searchJobType" value="${param.search_LIKE_subJobType}" />	
	</c:if>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta name= "mobile-agent"content= "format=html5;url=http://m.youlanw.com/<c:if test="${not empty sessionScope.session_key_branch_web_prefix}">${fn:trim(sessionScope.session_key_branch_web_prefix)}_zhaopin/</c:if>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:choose>
	<c:when test="${not empty param.search_LIKE_jobType &&  param.search_LIKE_jobType=='job' }">
		<title>${cityName }招工信息网_${cityName }工厂招工招聘</title>
		<meta name="keywords" content="${cityName }招工,${cityName }最新招聘信息,${cityName }招工信息网" />
		<meta name="description" content="${cityName }优蓝网招工招聘栏目,免费提供海量${cityName }市高薪名企工厂普工,操作工,司机,快递,客服,销售等招工招聘信息,杜绝虚假信息,更好的为广大蓝领求职者服务" />
	</c:when>
	<c:when test="${not empty param.search_LIKE_jobType && param.search_LIKE_jobType!='0' && param.search_LIKE_jobType!='job'&&(empty param.search_LIKE_subJobType || param.search_LIKE_subJobType=='0') }">
		<title>${cityName }${param.search_LIKE_jobType }招聘_${cityName }${param.search_LIKE_jobType }招聘信息_工厂招聘${param.search_LIKE_jobType }</title>
		<meta name="keywords" content="${cityName }${param.search_LIKE_jobType }招聘_${cityName }${param.search_LIKE_jobType }最新招聘信息,工厂招聘${param.search_LIKE_jobType }" />
		<meta name="description" content="${cityName }优蓝网${param.search_LIKE_jobType }招工招聘栏目,免费提供海量${cityName }市高薪名企工厂${param.search_LIKE_jobType }招工招聘信息,杜绝虚假信息,更好的为广大蓝领求职者服务" />
	</c:when>
	<c:when test="${not empty param.search_LIKE_jobType && param.search_LIKE_jobType!='0' && param.search_LIKE_jobType!='job'&& not empty param.search_LIKE_subJobType && param.search_LIKE_subJobType!='0' }">
		<title>${cityName }${param.search_LIKE_jobType }|${param.search_LIKE_subJobType}招聘_${cityName }${param.search_LIKE_jobType }|${param.search_LIKE_subJobType}招聘信息</title>
		<meta name="keywords" content="${cityName }${param.search_LIKE_jobType }招聘,${cityName }${param.search_LIKE_subJobType }招聘,${cityName }${param.search_LIKE_jobType }招聘信息,${cityName }${param.search_LIKE_subJobType }招聘信息" />
		<meta name="description" content="${cityName }优蓝网${param.search_LIKE_jobType }|${param.search_LIKE_subJobType }招工招聘栏目,免费提供海量${cityName }市高薪名企工厂${param.search_LIKE_jobType },${param.search_LIKE_subJobType }招工招聘信息,杜绝虚假信息,更好的为广大蓝领求职者服务" />
	</c:when>
	<c:otherwise>
		<c:if test="${not empty wd }">
			<title>${wd }_${wd }相关职位招聘|招工信息</title>
			<meta name="keywords" content="${wd },${cityName }招工信息网,${cityName }最新招聘信息" />
			<meta name="description" content="${cityName }优蓝网【${wd }招工招聘】栏目拥有海量优质名企大厂招工招聘信息,提供${wd }相关职位招聘信息,收藏您感兴趣的工作,与蓝领朋友讨论${wd },免费找${wd }相关工作,上${cityName }优蓝网" />
		</c:if>
		<c:if test="${empty wd }">
			<title>${cityName }${wd }${searchJobType}招聘_${cityName }${tpt}最新招聘信息_${param.search_LIKE_totalsalary!='0'?param.search_LIKE_totalsalary:''}找工作</title>
			<meta name="keywords" content="${wd },${cityName }${searchJobType}招聘,${cityName }${tpt}最新招聘信息,${cityName }${searchJobType}找工作" />
			<meta name="description" content="${cityName }优蓝网【在${cityName }找${wd }${searchJobType}工作】,提供大量${cityName }${wd }${searchJobType}工人求职招聘信息,介绍${searchJobType}找工作要求及薪资,名企大厂招工求职,${cityName }成就高薪蓝领" />
		</c:if>
	</c:otherwise>
</c:choose>
<link rel="stylesheet" href="http://www.youlanw.com/static/css/jquery-ui.min.css" />
<script src="http://www.youlanw.com/static/js/jquery-ui.min.js"></script>
<style>
.welfare-tags li{width:133px!important}
.job-list-each{height:120px !important;}
.job-list-each .pic{width:135px !important;height:92px !important;}
.job-list-each .pic img{width:135px !important;height:92px !important;}
.job-list-each .company,.job-list-each .summary,.job-list-each .tags-g,.job-list-each .title{left:160px !important;}
.job-list-each{border-bottom:1px solid #e2e2e2!important}
.filter-each .r {margin-left: 0px  !important;}
.filter-each .pay-scale {margin-left:40px !important;}
.com-jobs-each li{width:95px !important;}
.com-jobs .title{width: 240px !important;padding-left: 20px !important;}
.com-jobs-each .salary {width:130px !important;}

.job-list-each.job-list-each-sty {
    padding: 20px 15px !important;
}
.job-list-each.job-list-each-sty .pic img {
    width: 165px !important;
   height: 130px !important;
}
.job-list-each.job-list-each-sty .company, .job-list-each.job-list-each-sty .summary,.job-list-each.job-list-each-sty .tags-g, .job-list-each.job-list-each-sty .title {
    left: 190px !important;
}
.certify-hover {right: -454px !important;}
.job-list-each .title{position:static;top: initial;left: initial;margin-left: 148px;margin-top: -75px;}
</style>
</head>
<body>
	<div class="nav-g">
        <a href="${ctx }/">首页</a>&gt;<a href="${ctx }/zhaopin/">${cityName }找工作</a>
        <ul class="service-g right">
            <li><span>验</span>100%真实信息</li>
            <li><span>快</span>30分钟内电话</li>
            <li><span>保</span>不满随时投诉</li>
        </ul>
    </div>
    
    <!--筛选-->
    <div class="box-1200 mt10">
    	<div class="filter-con">
        	<!--所有岗位-->
        	
        	<!--地点-->
            <div class="filter-each filter-first">
                <div class="form-each mt0">
                    <select class="form-each-input w150 select_city" name="search_EQ_cityid" id="scName">
                    	<option value="0">周边城市</option>
                    	<c:forEach items="${otherCitys }" var="otherCity">
	                        <option value="${otherCity.cityId}" <c:if test="${param.search_EQ_cityid == otherCity.cityId}">selected="selected"</c:if> >${otherCity.cityName}</option>
					    </c:forEach>
                    </select>
                    <input type="hidden" value="${wd}" id="wdValue"/>
                    <input type="text" class="form-each-input w310" name="search_LIKE_searchName" id="searchName" placeholder="请输入企业、岗位关键字..."/>
                    <input type="button" class="btn-g btn-g-small search-enter" id = "btn_seach" value="搜索" style="border-radius: 2px;border-bottom: 2px solid #B33A11;"/>
                    <div class="recommend">
                        <label class="form-each-name orange">热门搜索：</label>
                        <c:forEach items="${hotRecommonds }" var="recommond">
	    					<a href="${recommond.resource.linkUrl }"><span class="form-each-name">${recommond.resource.title }</span></a>
	    				</c:forEach>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <!--/地点-->
            <!--选择薪资-->
        	<div class="filter-each">
        		<label class="name mt30">工资</label>
            	<label class="name default
            	<c:if test="${(not empty param.search_LIKE_totalsalary && fn:endsWith(param.search_LIKE_totalsalary, '10500')
            				 && fn:startsWith(param.search_LIKE_totalsalary, '0'))||empty param.search_LIKE_totalsalary || param.search_LIKE_totalsalary=='0' }"
            	>current</c:if> mt30" id="allSalary">不限</label>
                <div class="r pay-scale ml40">
                   <div class="range-tip" style="">
                     <div class="tip-content"> <span id="amount"></span></div>
                     <span class="tip-arrow"></span>
                	</div>
                   
                	<div class="slider">
                		<div class="bar"  id="slider-range"></div>
                    </div>
                    <ul class="num">
                        <li>2千</li>
                        <li>3千</li>
                        <li>4千</li>
                        <li>5千</li>
                        <li>6千</li>
                        <li>7千</li>
                        <li>8千</li>
                        <li>9千</li>
                        <li>1万</li>
                        <div class="clear"></div>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <!--/选择薪资-->
            <!--职位-->
        	<div class="filter-each">
            	<label class="name">职位</label>
            	<a id="allJobTypeHref"><label class="name default <c:if test="${empty param.search_LIKE_jobType || param.search_LIKE_jobType == ''|| param.search_LIKE_jobType == '0'|| param.search_LIKE_jobType == 'job'}">current</c:if>" id="allJobType">不限</label></a>
                <div class="r post">
                	<ul id="job_types">
                        <c:forEach items="${jobTypes}" var="jobType" varStatus="status">
                		<li <c:if test="${param.search_LIKE_jobType == jobType.jobName}">class="current"</c:if>><a data-id="${jobType.id}" href="${ctx }/${jobType.jobName}/0_0_0_0_1/">${jobType.jobName}</a></li>
                		<c:if test="${ status.index !=0 && (status.index+1) % 11 ==0}">
		                    <li class="sub sub_job_types hide" id="sub_job_types<fmt:formatNumber maxFractionDigits="0" type="number" value="${status.index / 11  }"/>"></li>
                		</c:if>
                		</c:forEach>
                		<li class="sub sub_job_types hide" id="sub_job_types0"></li>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <!--/职位-->
            <!--福利-->
        	<div class="filter-each bd0">
            	<label class="name">福利</label>
            	<label id="allLabel" class="name default <c:if test="${empty param.search_LIKE_searchName || param.search_LIKE_searchName == ''|| param.search_LIKE_searchName == '0'}">current</c:if>">不限</label>
             	<%--<form action="" method="get" id="form_search_label">--%>
                  <%-- <input type="hidden" name="search_LIKE_jobLabel" value="${param.search_LIKE_jobLabel }" /> --%>
               <%-- </form> --%>
                <div class="r post">
                	<ul>
                		<c:forEach items="${labels.content}" var="label">
	                		<li value="${label.tagName}"><a javascript:;><input type="checkbox" class="cb_label" <c:if test="${tc:containsLables(label.tagName,param.search_LIKE_searchName)}">checked="checked"</c:if> />${label.tagName }</a></li>
                    	</c:forEach>
                    </ul>
                </div>
                </a>
                <div class="clear"></div>
            </div>
            <!--/福利-->
        </div>
    </div>
    <!--/筛选-->
    <!---->
    <div class="w1200 mt10">
    	<!--left-->
    	<div class="box-l-wrapper">
    	 <input type="hidden" value="${dName }" id="dName" data="${param.search_EQ_countyid}"/>
	     <input type="hidden" value="${cName }" id="cName" data="${paramValues['search_EQ_city.id'][0]}"/>
	     <input type="hidden" data="${param.search_LIKE_jobType}" id="hJobType">
	     <input type="hidden" data="${param.search_LIKE_subJobType}" id="hsubJobType">
	     <input type="hidden" data="${param.search_LIKE_totalsalary}" id="salaryCondition">
        	<!--筛选-->
            <div class="box-l order">
                <h1 class="f16 ml15">${cityName}${tpt}招工<strong class="blue">${jobCount }</strong>个招聘信息</h1>
            </div>
            <!--/筛选-->
        	<!--列表-->
	    	<c:if test="${ empty page.content}">
	    		<div class="box-l-2 text-center f15"><span class="icon-warning"><i></i></span>没找到想要的岗位？不如委托给顾问，帮我<a href="${ctx}/signup/fastSignUp" class="red">30秒找工作</a></div>
            </c:if>
            <!-- <div class="box-l mt10" id="jobContent"> -->
            <div class="" id="jobContent">
              <c:if test="${empty page.content}">
              <div class="box-l mt10">
              	<c:forEach items="${recommendJobList }" var="recommend">
               		<ul class="job-list-each seal-1 <c:if test="${recommend.isUrgency == 2}">seal-5</c:if><c:if test="${recommend.isUrgency == 3}">seal-4</c:if> job-list-each-sty"  style="overflow: hidden;">
		            	<li class="pic"><a href="${ctx}/zhaopin_${recommend.jobId}.html" target="_blank">
		            	<c:choose>
							<c:when test="${empty recommend.imgPath}">
								<img src="${cdc:getImageByJobType320(recommend.jobType)}" alt="${recommend.jobTitle}" />
							</c:when>
							<c:otherwise>
								<img src="${cdc:getImagePath320(recommend.imgPath)}" alt="${recommend.jobTitle}" />
							</c:otherwise>
						</c:choose>
		            	</a><i></i></li>
		                <li class="title"><h3>[${recommend.jobCityName}${cdc:converCountyName("-",recommend.countyId)}]<a href="${ctx}/zhaopin_${recommend.jobId}.html" target="_blank">${tc:highlightText(recommend.title, wd)}</a></h3></li>
		                <li class="tags-g">
		                <c:forEach items="${recommend.jobLabel}" var="label">
                			<a href="${ctx}/s_label_${label}">${label }</a>
                    	</c:forEach>
		                </li>
		                <li class="summary">${cdc:converDicEducation(recommend.education)} / ${tc:convertGenderLimit(recommend.gender)} / ${recommend.ageFrom }~${recommend.ageTo}岁 /${tc:dayAfter(recommend.updateTime)}发布</li>
		                <li class="salary">￥${recommend.salaryFrom }<c:if test="${not empty  recommend.salaryTo}">-${recommend.salaryTo }元/月</c:if>
		                <c:if test="${empty  recommend.salaryTo}">起</c:if></li>
		                <li class="company"><a href="${ctx}/qiye_${recommend.companyId}.html" target="_blank">${tc:highlightText(recommend.companyName ,wd) }</a>
		                <c:if test="${recommend.validation == 1}"><label class="seal-3">验</label></c:if></li>
		                <li class="signup"><a data-id="${recommend.jobId }" href="${ctx}/zhaopin_${recommend.jobId}.html" class="btn btn-small">去看看</a></li>
		            </ul>
               		</c:forEach>
               		</div>
               </c:if>
              <c:forEach items="${page.content }" var="companyVo">
        	   <div class="box-l mt10">
        		<ul>
        			<li>
        				<ul class="job-list-each seal-4 seal-1">
		                    <li class="pic">
			                    <a href="${ctx}/qiye_${companyVo.companyId}.html" target="_blank">
			                    <c:choose>
		                            <c:when test="${empty companyVo.companyImage}">
		                                <div class="pic-none">${companyVo.abbreviation }</div> 
		                            </c:when>
		                            <c:otherwise>
		                                <img src="${cdc:getImagePath320(companyVo.companyImage)}" alt="${companyVo.abbreviation }_logo"/>
		                            </c:otherwise>
	                            </c:choose>
			                    </a>
		                    </li>
		                    <li class="title">
		                    	<h3>
		                    		<a href="${ctx}/qiye_${companyVo.companyId}.html" target="_blank">${tc:highlightText(companyVo.companyName, wd)}</a>
			                    	<!--<label class="text-icon">金牌企业</label>-->
			                    	<%-- <c:if test="${companyVo.cooperationType==1||companyVo.cooperationType==5 }"> --%>
			                    	<label class="text-icon3">认证
					            		<div class="certify-hover">
					            			<p class="blue f16">认证标准</p>
					            			<p class="f16 c333 mt10">该企业已经过优蓝网官方认证，优蓝网确保以下信息真实可靠</p>
					            			<ul class="standards clearfix">
									    		<li>该企业与优蓝网已签署官方委托协议</li>
									    		<li>优蓝网已派专人严格审核企业岗位</li>
									    		<li>优蓝网已派专属服务人员进厂入住接待</li>
									    	</ul>
									    	<p class="blue f16">专享服务</p>
									    	<ul class="specialService clearfix">
									    		<li>
									    			<div class="circle circle1"><p>全程</p><p>0费用</p></div>
									    			<p>参加面试入职</p>
									    			<p>全程无任何费用</p>
									    		</li>
									    		<li>
									    			<div class="circle circle2"><p>专属</p><p>服务</p></div>
									    			<p>匹配专属顾问</p>
									    			<p>一对一贴心服务</p>
									    		</li>
									    		<li>
									    			<div class="circle circle3"><p>全流程</p><p>覆盖</p></div>
									    			<p>从报名到入职</p>
									    			<p>全流程跟踪服务</p>
									    		</li>
									    	</ul>
					            		</div>
			                    	</label>
			                    	<%-- </c:if> --%>
			                    	<c:if test="${companyVo.isLoan==1 }">
				                    	<label class="text-icon2 relative wjd">微借贷
				                    	<div class="code-img">
				                    		<img src="${ctx }/static/images/v3/er-code.jpg" />
				                    		<p>下载优蓝APP申请借贷</p>
				                    	</div>
				                    	</label>
			                    	</c:if>
		                    	</h3>
		                    </li>
		                    <li class="summary"><i class="local"></i>${cdc:convertDicIndustryType(companyVo.industryid)}<label class="line-g">|</label>${cdc:convertDicStaffscale(companyVo.staffscale)}</li>
		                    <li class="company"><i class="area"></i>地址： ${cdc:convertProvinceAbbreviation(companyVo.provinceId)}
                                 ${companyVo.cityName}
                                ${cdc:converCountyName("",companyVo.icountyid)}
                                 ${companyVo.address }</li>
		                </ul>
        			</li>
        			<li>
                        <div class="com-jobs">
                        	<ul class="com-jobs-each com-jobs-first">
                                <a href="${ctx }/company/job_${companyVo.companyId }/"><li class="title"><span>招聘信息</span></li></a>
                                <li>工种</li>
                                <li>学历</li>
                                <li>性别</li>
                                <li>年龄</li>
                                <li>工资</li>
                            </ul>
                            <c:forEach items="${companyVo.jobBases}" var="jobbase" varStatus="status">
                            <c:if test="${status.index<3 }">
                            <ul class="com-jobs-each">
                                <li class="title"><a href="${ctx}/zhaopin_${jobbase.id }.html">[${jobbase.city.cityName}-${cdc:converCountyName("",jobbase.countyid)}]${jobbase.title }</a></li>
                                <li>${jobbase.jobType }</li>
                                <li>${cdc:converDicEducation(jobbase.jobDetail.education)}</li>
                                <li>${tc:convertGenderLimit(jobbase.jobDetail.gender)}</li>
                                <li><c:if test="${not empty jobbase.jobDetail.agefrom }">
                                    ${jobbase.jobDetail.agefrom }<c:if test="${not empty jobbase.jobDetail.ageto }">~${jobbase.jobDetail.ageto}岁</c:if>
                                    <c:if test="${empty jobbase.jobDetail.ageto }">岁以上</c:if></c:if>
                                    <c:if test="${empty jobbase.jobDetail.agefrom }">年龄不限</c:if></li>
                                <li class="salary">￥${jobbase.jobDetail.salaryfrom }<c:if test="${not empty  jobbase.jobDetail.salaryto}">-${jobbase.jobDetail.salaryto }元/月</c:if>
                                 <c:if test="${empty  jobbase.jobDetail.salaryto}">起</c:if></li>
                                <li><a href="${ctx}/zhaopin_${jobbase.id }.html" class="btn btn-tiny2 btn-small">查看</a></li>
                            </ul>
                            </c:if>
                            </c:forEach>
                            <div class="clear remove-border"></div>
                    	</div>
        			</li>
        			<c:if test="${fn:length(companyVo.jobBases)>=3 }">
        			<li class="look-more">
        				<a href="${ctx}/company/job_${companyVo.companyId }/">查看全部职位<i></i></a>
        			</li>
        			</c:if>
        		</ul>
            </div>
          </c:forEach>
          <div class="clear"></div>
          <c:if test="${not empty page.content}">
             <div id="paginationId"><tags:urlrewrite_job_pagination paginationSize="5" page="${page }"/></div>
          </c:if>
         </div>
         <!--/列表-->
        </div>
		<!--/left-->
       <!--right-->
        <div class="box-r-wrapper">
        	
        	<!--最新岗位-->
         	<div class="box-r recruit-tags bd0 pdt0">
            	<div class="title-bar pdt0"><span>最新岗位</span></div>
            	<ul class="about-recruit">
	            	<c:forEach items="${newJobs }" var="job">
		            	<a href="${ctx }/zhaopin_${job.jobId }.html"><li>${job.companyAbbreviation}招聘${job.jobType}</li></a>
            		</c:forEach>
	            </ul>
            </div>
            <!--/最新岗位-->
            <!--名企推荐-->
            <c:if test="${not empty recommondCompanys }">
            <div class="box-r recruit-tags bd0">
            	<div class="title-bar pdt0"><span>名企推荐</span><a href="${ctx }/mingqi/" class="blue right mt10">更多&gt;&gt;</a> </div>
            	<ul class="about-company clearfix">
           		    <c:forEach items="${recommondCompanys }" var="recommond">
    					<a href="${recommond.resource.linkUrl }"><li><img src="${recommond.resource.thumbImageUrl }" alt="${recommond.resource.title }"></li></a>
    				</c:forEach>
	            </ul>
            </div>
            </c:if>
            <!--/名企推荐-->
        	<!--福利标签-->
            <div class="box-r recruit-tags bd0 welfare-tags">
            	<div class="title-bar"><span>求职流程</span></div>
            	<div class="steps">
	            	<div class="step">
	            		<img src="${ctx }/static/images/v3/step1.jpg" alt="网上预约" />
	            		<p>网上预约</p>
	            	</div>
	                <p class="more"></p>
	                <div class="step">
	            		<img src="${ctx }/static/images/v3/step2.jpg" alt="客服联系" />
	            		<p>客服联系</p>
	            	</div>
	                <p class="more"></p>
	                <div class="step">
	            		<img src="${ctx }/static/images/v3/step3.jpg" alt="成功入职" />
	            		<p>成功入职</p>
	            	</div>
	                <div class="clear"></div>
	            </div>
            </div>
            <!--/福利标签-->
            
            <!--最近浏览-->
			<div class="box-r mt10" >
				<div class="right-fixed right-fixed-sty" id="recent_browse_list">
				<h2 class="box-title">最近浏览职位</h2>
				<div class="job-list2">
					<p class="no-result">
						<span><i></i></span>暂无浏览记录
					</p>
				</div>
			</div>
			</div>
			<!--最近浏览-->

        	<!--优蓝网手机版-->
            <div class="box-r mt10">
            	<a href="http://m.youlanw.com/<c:if test="${not empty sessionScope.session_key_branch_web_prefix}">${fn:trim(sessionScope.session_key_branch_web_prefix)}_zhaopin/</c:if>" >
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
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
    <!---->
    <script>
    $(function(){
    	YL.job.list.init();
    })
    </script>
</body>
</html>