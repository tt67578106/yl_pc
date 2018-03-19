<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:if test="${not empty sessionScope.session_key_branch_name}">
	<c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta name= "mobile-agent"content= "format=html5;url=http://m.youlanw.com/zhaopin_${jobBase.id }.html">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<title>${jobBase.title}_${fn:trim(jobBase.company.abbreviation) }招聘信息_${jobBase.city.cityName }优蓝网</title>
<meta name="keywords" content="${jobBase.title},${fn:trim(jobBase.company.abbreviation) }招聘,${fn:trim(jobBase.company.abbreviation) }招聘信息" />
<meta name="description" content="${jobBase.city.cityName }优蓝网【${fn:replace(jobBase.title, '\"','')}】,待遇:${fn:trim(jobDetail.salaryfrom)}<c:if test="${not empty  jobDetail.salaryto}">-${jobDetail.salaryto }元/月</c:if><c:if test="${empty jobDetail.salaryto}">起</c:if>,福利:${fn:replace(jobBase.jobLabel,',','|')},了解更多${fn:trim(jobBase.company.abbreviation) }招聘信息，就上${jobBase.city.cityName }优蓝网" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/jobDetail.css"/>
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20150820"></script>
<script src="${ctx}/static/js/layer.min.js"></script>
<script src="${ctx}/static/js/cookie.js"></script>
<script src="${ctx}/static/js/youlanw.pc.js?v=20170811"></script>
<script src='//kefu.easemob.com/webim/easemob.js?tenantId=1780&hide=true' async='async'></script>
<style type="text/css">
	.text-input{width:140px;margin-left:0;}
	.reg-tips{margin-left:0;width:100%;}
	.pic-code{position:absolute;left:210px;top:2px;cursor:pointer;width:91px;height:39px;}
	.pic-code-text{position:absolute;left:280px;top:6px;color:#999;cursor:pointer;line-height:1.3;left: 313px}
</style>
</head>
<body>
<!--page-wrapper-->
<div class="page-wrapper">

	<jsp:include page="/WEB-INF/layouts/top.jsp" />
	<!--menu-->
    <div class="nav-g clearfix"><span class="fl"><a href="${ctx}/">${cityName }找工作</a>&gt;<a href="${ctx}/zhaopin">${cityName }最新招聘信息</a>&gt;<a href="${ctx}/${fn:split(jobBase.jobType,',')[0] }/0_0_0_0_1/">${cityName }${fn:split(jobBase.jobType,',')[0] }招聘</a>&gt;<a href="${ctx}/zhaopin_${jobBase.id}.html" class="red">${fn:trim(jobBase.company.abbreviation) }招聘${jobBase.jobType }</a></span></div>
	<!--/menu--->
    <!--job-chief-->
    <div class="box-wrapper">
    <input type="hidden" id="hid_type" value="${type }" />
    <input type="hidden" id="hid_scheduleCode" value="${scheduleCode }" />
    <!-- 最近浏览记录 -->
    <input type="hidden" id="jobid" value="${jobBase.id }" />
    <input type="hidden" id="jobtitle" value="${jobBase.title }" />
    <input type="hidden" id="salaryfrom" value="${jobDetail.salaryfrom }" />
    <input type="hidden" id="salaryto" value="${jobDetail.salaryto }" />
    <c:choose>
		<c:when test="${empty jobBase.thumbnialImage.imgpath}">
		 <input type="hidden" id="jobimgpath" value="${cdc:getImageByJobType320(jobBase.jobType)}" />
		    	 	 <input type="hidden" id="imgcomment" value="${jobBase.title }" />
		</c:when>
		<c:otherwise>
		 <input type="hidden" id="jobimgpath" value="${cdc:getImagePath320(jobBase.thumbnialImage.imgpath)}" />
		    	 	 <input type="hidden" id="imgcomment" value="${jobBase.thumbnialImage.comment}" />
		</c:otherwise>
	</c:choose>
   <!-- /最近浏览记录 --> 
        <div class="job-chief job-chief-sty">
            <div class="job-chief-pic fl relative">
            	<c:choose>
					<c:when test="${empty jobBase.thumbnialImage.imgpath}">
						<img src="${cdc:getImageByJobType320(jobBase.jobType)}" alt="${jobBase.title}"/>
					</c:when>
					<c:otherwise> 
			       	 	 <img src="${cdc:getImagePath320(jobBase.thumbnialImage.imgpath)}" alt="${jobBase.thumbnialImage.comment}"/>
					</c:otherwise>
				</c:choose>
           	    <c:if test="${jobBase.jobConfig.isRecruitment == 0}"><span class="status-on">在招<i></i></span></c:if>
                	<span class="opacity-bg"></span>
                <a href="${ctx}/company/album_${jobBase.company.id }/">
               	 <span class="opacity-text">${fn:length(imageList)-1}张图片</span>
                </a>
            </div>
            <div class="job-chief-con fl">
            	<div class="p-title">
            	<c:if test="${jobBase.jobConfig.isRecruitment == 0}">
                	<span class="tag-directly">直招</span>
                </c:if>
            		<h1>${jobBase.titleSub }</h1>
                </div>
                <p class="salary">￥${jobDetail.salaryfrom }<c:if test="${not empty  jobDetail.salaryto}">-${jobDetail.salaryto }元/月</c:if>
		        <c:if test="${empty jobDetail.salaryto}">起</c:if></li></p>
                <div class="job-tags">
                    <c:forEach items="${jobBase.jobLabel}" var="label">
						<%-- <a href="${ctx}/s_label_${label }">${label }</a> --%>
					<span>${label }</span>
				 </c:forEach>
                 <div class="clear"></div>
                </div>
                <p class="des">本岗位已通过优蓝网严格审核认证，请放心报名。</p>
                <p class="des2"><span class="name">要&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;求</span>${cdc:converDicEducation(jobDetail.education)}&nbsp;&nbsp;|&nbsp;&nbsp;${tc:convertGenderLimit(jobDetail.gender)}&nbsp;&nbsp;|&nbsp;&nbsp;<c:if test="${not empty jobDetail.agefrom }">
                        ${jobDetail.agefrom }<c:if test="${not empty jobDetail.ageto }">~${jobDetail.ageto}岁</c:if>
                        <c:if test="${empty jobDetail.ageto }">岁以上</c:if></c:if>
                        <c:if test="${empty jobDetail.agefrom }">年龄不限</c:if></p>
                <p class="des2 w500"><span class="name">上班地址</span> ${cdc:convertProvinceAbbreviation(jobBase.provinceid)}
                     ${jobBase.city.cityName}
                    ${cdc:converCountyName("",jobBase.countyid)}</p>
                <c:if test="${not empty jobBase.address }">    
               		<p class="des2 w500"><span class="name">详细地址</span>${jobBase.address }</p>
                </c:if>
                <a data-id="${jobBase.id }" class="btn signup-layer-trigger fl">
	                <div class="job-btn">
		                <c:if test="${(jobBase.cooperationType eq 1||jobBase.cooperationType eq 5) && jobBase.jobConfig.isRecruitment eq 0}">
		                	<p class="t1 sign">申请面试
		                	</p>
		               </c:if> 	
		                <c:if test="${(jobBase.cooperationType ne 1 &&jobBase.cooperationType ne 5) || jobBase.jobConfig.isRecruitment ne 0}"> 
		                	<p class="t1 sign">立即预约
		                	</p>
		                </c:if>
		                    <p class="t2"><c:if test="${empty jobDetail.showApplyCount}">0</c:if>${jobDetail.showApplyCount }人报名</p>
	                </div>
                </a>
                <!--<div class="job-btn disabled">
                	<p class="t1">我要应聘</p>
                    <p class="t2">1023人报名</p>
                </div>-->
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--/job-chief-->
    <!--service-->
    <div class="box-wrapper mt15">
        <label class="name-g ml5 fl">服务保障</label>
        <ul class="service-g">
            <li><span>验</span>100%真实信息</li>
            <li><span>快</span>30分钟内电话</li>
            <li><span>保</span>不满随时投诉</li>
        </ul>
        <p></p>
        <div class="clear"></div>
    </div>
    <!--/service-->
    
    <!--main-->
    <div class="box-wrapper mt15">
    	<!--left-->
    	<div class="fl">	
    	<div class="box-wrapper-l" >
        	<!--job-l-1 工资情况-->
        	<c:if test="${not empty jobDetail.basicSalary
               || not empty jobDetail.attendanceSalary
               || not empty jobDetail.tryoutSalary
               || not empty jobDetail.overtimeSalary
               || not empty jobDetail.workerSalary
               || not empty jobDetail.floatSalary
               }">
        	<div class="job-l-1 f-content" id="f1">
            	<div class="job-col-title relative">
                	<h2>工资情况</h2>
                    <span class="rt">本岗位经专业认证工资情况真实可靠</span>
                </div>
                <div class="job-salary">
                  	 	<div class="row first">
                    	<div class="name"><span>综合工资</span></div>
                        <div class="salary">￥${jobDetail.salaryfrom }<c:if test="${not empty  jobDetail.salaryto}">-${jobDetail.salaryto }元/月</c:if>
	       		 		<c:if test="${empty jobDetail.salaryto}">起</c:if></div>
                    </div>
                    <c:if test="${not empty jobDetail.basicSalary }">
	                    <div class="row">
	                    	<div class="name"><span>基本工资</span></div>
	                        <div>${jobDetail.basicSalary }</div>
	                    </div>
	                </c:if>
	                <c:if test="${not empty jobDetail.attendanceSalary}">
	                    <div class="row">
	                    	<div class="name"><span>全勤奖金</span></div>
	                        <div>${jobDetail.attendanceSalary}</div>
	                    </div>
	                </c:if>
	                <c:if test="${not empty jobDetail.tryoutSalary}">
	                    <div class="row">
	                    	<div class="name"><span>试用工资</span></div>
	                        <div>${jobDetail.tryoutSalary }</div>
	                    </div>
	                </c:if>
	                <c:if test="${not empty jobDetail.workerSalary}">
	                    <div class="row">
	                    	<div class="name"><span>转正工资</span></div>
	                        <div>${jobDetail.workerSalary }</div>
	                    </div>
                    </c:if>
                    <c:if test="${not empty jobDetail.overtimeSalary}">
	                    <div class="row">
	                    	<div class="name"><span>加班工资</span></div>
	                        <div>${jobDetail.overtimeSalary }</div>
	                    </div>
                    </c:if>
                     <div class="last-border"></div>
	             </div>
            </div>
            </c:if>
            <!--/job-l-1 工资情况-->
            <!--job-l-2 职位描述-->
            <c:if test="${not empty jobDetail.mealsdesc 
	            || not empty jobDetail.workdesc
	            || not empty jobDetail.demanddesc  }">
        	<div class="job-l-2 f-content" id="f2">
            	<div class="job-col-title">
                	<h2>职位描述</h2>
                </div>
                <div class="job-des">
                	<c:if test="${not empty jobDetail.mealsdesc }">
                    <h3 class="job-col-sub-title" id="f23">【吃住福利】</h3>
                    <div class="con">
                    	 ${jobDetail.mealsdesc}
                    </div>
                    </c:if>
                    <c:if test="${not empty jobDetail.workdesc }">
                    <h3 class="job-col-sub-title" id="f22">【工作简介】</h3>
                    <div class="con">
                    	 ${jobDetail.workdesc}
                    </div>
                    </c:if>
                    <c:if test="${not empty jobDetail.demanddesc }">
                	<h3 class="job-col-sub-title" id="f21">【招聘要求】</h3>
                    <div class="con">
                    	 ${jobDetail.demanddesc }
                    </div>
                    </c:if>
                </div>
            </div>
            </c:if>
            </div>
            <!--/job-l-2 职位描述-->
            <!--job-l-3 岗位图片-->
            <div class="box-wrapper-l bd0">
            <c:if test="${fn:length(imageList)-1 >= 3}">
	        	<div class="job-l-3 f-content" id="f3">
	            	<div class="title-bar"><span>企业实景</span></div>
	                <div class="job-pic mt15">
	                	<div class="bd fl">
	                        <ul>
	                            <c:if test="${empty imageList}">
		                    		<li class="on"><img src="${cdc:getImageByJobType720(jobBase.jobType)}" class="big fl" id="defaultImage"/></li>
		                    	</c:if>
								<c:forEach items="${imageList}" var="img" varStatus="i">
									<c:if test="${i.index < 5 && i.index != 0}">
										<li>
											<img src="${cdc:getImagePath720(img.imgpath) }" class="big fl"/>
										</li>
									</c:if>
								</c:forEach>
	                        </ul>
	                    </div>
	                    <div class="small fr">
	                    	<span class="pics">
	                        	<div class="hd">
	                               <ul>
										<c:if test="${empty imageList}">
				                    		<li class="on"><img src="${cdc:getImageByJobType720(jobBase.jobType)}" id="defaultImage"/></li>
				                    	</c:if>
										<c:forEach items="${imageList}" var="img" varStatus="i">
											<c:if test="${i.index < 5 && i.index != 0}">
												<li class="on">
													<img src="${cdc:getImagePath720(img.imgpath) }" />
												</li>
											</c:if>
										</c:forEach>
									</ul>
	                            </div>
	                        </span>
	                        <a href="${ctx}/company/album_${jobBase.company.id }/">
	                        <div class="more">
	                        	<div class="inner">
	                                <p>更多图片</p>
	                                <p>(${fn:length(imageList)-1})</p>
	                            </div>
	                        </div>
	                        </a>
	                    </div>
	                    <div class="clear"></div>
	                </div>
	            </div>
            </c:if>
            <!--/job-l-3 岗位图片-->
            <!--最新咨询-->
            <div class="job-consult f-content" id="f4">
 			<div class="title-bar">
		        <a href="${ctx }/wenda?jobId=${jobBase.id}"><p><span>岗位问答（共${askCount }条）</span></p></a>
		        <p class="f13 c999">该企业相关问题答案由优蓝网和热心网友共同提供</p>
		        <a href="${ctx }/wenda/ask?jobId=${jobBase.id}&backUrl=zhaopin_${jobBase.id}.html" class="btn btn-tiny2  btn-small right">我要提问</a>
	        </div>
	       <c:if test="${not empty talkList.content }">
	        <div class="job-consult">
	        	<c:forEach items="${talkList.content }" var="talk">
	        		<ul>
					  <li class="consult-r">
					    <ul>
					      <li><span class="blue-label">问</span><a href=" ${ctx }/wenda/${talk.askId }.html">${talk.title }</a><span class="mess">${talk.createTimeString}&nbsp;${empty talk.askUserName?'优蓝网友':talk.askUserName }</span></li>
						  <c:if test="${not empty talk.userCommunityTalkContentVo }">
						  	 <c:forEach items="${talk.userCommunityTalkContentVo }" var="reply" varStatus="status">
						  	 	<c:if test="${status.index==0 }">
						  	 		 <li class="c999"><span class="gray-label">答</span>${reply.content }<a href="${ctx }/wenda/${talk.askId }.html" class="answer">我来回答&gt;</a></li> 
						  	 	</c:if>
						  	 </c:forEach>
					  	 	<c:if test="${fn:length(talk.userCommunityTalkContentVo)>1 }">
					          <li class="last"><a href="${ctx }/wenda/${talk.askId }.html">查看其他问答&gt;</a> </li>
					  	 	</c:if>
						  </c:if>
						  <c:if test="${empty talk.userCommunityTalkContentVo }">
					         <li class="c999"><span class="gray-label">答</span>还没人回答此问题，我来第一个回答。<a href="${ctx }/wenda/${talk.askId }.html" class="answer">我来回答&gt;</a></li>
					      </c:if>
					    </ul>
					  </li>
					  <div class="clear"></div>
					</ul>
	        	</c:forEach>
	        	<c:if test="${fn:length(talkList.content)>8 }">
					<div class="consult-more"><a href="${ctx }/wenda/so_0_0_${jobBase.id }_1/" class="f14 blue">查看全部问答&gt;&gt;</a></div>
	        	</c:if>
	        </div>
	        </c:if>
            <!--/最新咨询-->
            </div>
        </div>
        </div>
        <!--/left-->
        <!--right-->
        <div class="box-wrapper-r fr">
        	<!--job-com-->
        	<div class="job-com">
            	<a href="${ctx}/qiye_${jobBase.company.id}.html" class="more" target="_blank" title="${jobBase.company.name}">
            		<h2>
            			<span class="compName">${jobBase.company.name}</span>
            			<c:if test="${jobBase.cooperationType==1||jobBase.cooperationType==5 }">
            			<label class="certify-label">认证
	            		<div class="certify-hover">
	            			<p class="blue f16">认证标准</p>
	            			<p class="f16 c333 mt10">该企业已经过优蓝网官方认证，优蓝网确保以下信息真实可靠</p>
	            			<ul class="standards">
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
	            		</c:if>
            		</h2>
            	</a>
                <div class="inner">
                	<img src="${cdc:getImagePath320(jobBase.company.logo.imgpath)}"/>
                    <p><span class="c999">企业规模：</span>${cdc:convertDicStaffscale(jobBase.company.staffscale)}</p>
                    <p><span class="c999">所属行业：</span>${cdc:convertDicIndustryType(jobBase.company.industryid)}</p>
                    <p><span class="c999">平均薪资：</span>${averageSalary }</p>
                </div>
            </div>
            <!--job-com-des-->
            <div class="job-com-des">
                <p >${jobBase.company.introductionView}<a  style="margin-left:10px;" href="${ctx}/qiye_${jobBase.company.id}.html" class="more" target="_blank" title="${jobBase.company.name}">（查看全部）</a></p>
            </div>
            <!--/job-com-des-->
            <!--/job-com-->
            <!--job-map-->
            <div class="job-map">
            <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=PnYGbChqQrsmSUczLQrRZvw2"></script>
                        <div id="allmap" style="width:245px;height:190px;">
                        </div>
                        <script type="text/javascript">
                         // 百度地图API功能
                        var map = new BMap.Map("allmap");
                        var point = new BMap.Point(116.331398,39.897445);
                        map.centerAndZoom(point,12);
                        map.addControl(new BMap.NavigationControl()); 
                        map.addControl(new BMap.ScaleControl()); 
                        // 创建地址解析器实例
                        var myGeo = new BMap.Geocoder();
                        // 将地址解析结果显示在地图上,并调整地图视野
                        myGeo.getPoint("翔安北路1689号", function(point){
                            if (point) {
                                map.centerAndZoom(point, 14);
                                map.addOverlay(new BMap.Marker(point));
                            }
                        }, "厦门市");
                        </script>
            </div> 
            <!--/job-map-->
            <!--job-on-->
            <div class="job-on">
            	<div class="job-col-title">
                	<h2>其他在招职位</h2>
                </div>
                <div class="list">
                   	 <c:forEach items="${jobs }" var="job">
	                    <p class="each">
		                    <a href="${ctx}/zhaopin_${job.id}.html" >${job.title }</a><span class="salary">
		                    	￥${job.jobDetail.salaryfrom }<c:if test="${not empty  job.jobDetail.salaryto}">-${job.jobDetail.salaryto }元/月</c:if>
			        				<c:if test="${empty  job.jobDetail.salaryto}">起</c:if>
	                    	</span>
	                    </p>
                     </c:forEach>
                	<div class="remove-border"></div>
                </div>
            </div>
            <!--/job-on-->
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
            <!--job-common-->
            <div class="box-r job-common">
            	<div class="right-fixed">
                    <div class="job-col-title"><h2>常见问题</h2></div>
                    <div class="inner">
                        <a href="${ctx}/help/index.html#help1">优蓝网是什么</a>
                        <a href="${ctx}/help/ylapplyJob.html#apply2">报名多久收到回复</a>
                        <a href="${ctx}/help/ylapplyJob.html#apply3">如何参加面试</a>
                        <a href="${ctx}/help/guarantee.html#guarantee2"> 找工作收费吗</a>
                        <a href="${ctx}/help/guarantee.html#guarantee3">入职大礼包是什么</a>
                        <a href="${ctx}/help/guarantee.html#guarantee4">入职不满意怎么办</a>
                        <div class="clear"></div>
                    </div> 
                </div>   
            </div>
            <!--/job-common-->
            
            <!--优蓝网手机版-->
            <div class="box-r mt10">
            	<a href="http://m.youlanw.com/zhaopin_${jobBase.id }.html" >
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
    <!--mian-->
    
    <!--job-recommend-->
    <div class="box-wrapper">
    	<div class="job-recommend tab-g">
        	<div class="job-col-title relative">
            	<h2>推荐岗位</h2>
                <div class="tt">
                	<ul class="title">
                        <li class="current bd0"><span>相似岗位</span></li>
                        <li><span>报名最多</span></li>
                    	<li><span>最新发布</span></li>
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
            <!--相似岗位-->
            <div class="content-each show">
            	<div class="inner">
            	 <c:forEach items="${likeJobs}" var="likeJob">
	            	 <div class="each">
	                   	<a href="${ctx}/zhaopin_${likeJob.id}.html">
	                   	<c:choose>
							<c:when test="${empty likeJob.thumbnialImage.imgpath}">
								<img src="${cdc:getImageByJobType320(likeJob.jobType)}" alt="${likeJob.title }"/>
							</c:when>
							<c:otherwise> 
					       	 	<img src="${cdc:getImagePath320(likeJob.thumbnialImage.imgpath)}" alt="${likeJob.title}"/>
							</c:otherwise>
						</c:choose>
	                   	</a>
	                   	<a href="${ctx}/qiye_${likeJob.company.id}.html" class="more" target="_blank" title="${likeJob.company.name}">
	                    	<p class="company">${likeJob.company.name}</p>
	                    </a>
	                    <a href="${ctx}/zhaopin_${likeJob.id}.html"><p class="title">${likeJob.title }</p></a>
	                    <p class="salary">￥${likeJob.jobDetail.salaryfrom }<c:if test="${not empty  likeJob.jobDetail.salaryto}">-${likeJob.jobDetail.salaryto }元/月</c:if>
	       				<c:if test="${empty  likeJob.jobDetail.salaryto}">起</c:if></p>
	                 </div>
               	 </c:forEach>
                <div class="clear"></div>
            </div>
            </div>
            <!--/正在热招-->
            <!--报名最多-->
            <div class="content-each">
            	<div class="inner">
            	<c:forEach items="${mostJobs}" var="mostJob">
	            	 <div class="each">
	                   	<a href="${ctx}/zhaopin_${mostJob.id}.html">
	                   	<c:choose>
							<c:when test="${empty mostJob.thumbnialImage.imgpath}">
								<img src="${cdc:getImageByJobType320(mostJob.jobType)}" alt="${mostJob.title }"/>
							</c:when>
							<c:otherwise> 
					       	 	<img src="${cdc:getImagePath320(mostJob.thumbnialImage.imgpath)}" alt="${mostJob.thumbnialImage.comment}"/>
							</c:otherwise>
						</c:choose>
	                   	</a>
	                    <a href="${ctx}/qiye_${mostJob.company.id}.html" class="more" target="_blank" title="${mostJob.company.name}">
	                    	<p class="company">${mostJob.company.name}</p>
	                    </a>
	                    <a href="${ctx}/zhaopin_${mostJob.id}.html"><p class="title">${mostJob.title }</p></a>
	                    <p class="salary">￥${mostJob.jobDetail.salaryfrom }<c:if test="${not empty mostJob.jobDetail.salaryto}">-${mostJob.jobDetail.salaryto }元/月</c:if>
	       				<c:if test="${empty  mostJob.jobDetail.salaryto}">起</c:if></p>
	                 </div>
               	 </c:forEach>
                <div class="clear"></div>
            </div>
            </div>
            <!--/报名最多-->
            <!--最新发布-->
            <div class="content-each">
            	<div class="inner">
            	<c:forEach items="${mostNewJobs}" var="newJob">
	            	 <div class="each">
	                   	<a href="${ctx}/zhaopin_${newJob.id}.html">
	                   	<c:choose>
							<c:when test="${empty newJob.thumbnialImage.imgpath}">
								<img src="${cdc:getImageByJobType320(newJob.jobType)}" alt="${newJob.title }"/>
							</c:when>
							<c:otherwise> 
					       	 	<img src="${cdc:getImagePath320(newJob.thumbnialImage.imgpath)}" alt="${newJob.thumbnialImage.comment}"/>
							</c:otherwise>
						</c:choose>
	                   	</a>
	                   	<a href="${ctx}/qiye_${newJob.company.id}.html" class="more" target="_blank" title="${newJob.company.name}">
	                    	<p class="company">${newJob.company.name}</p>
	                    </a>
	                    <a href="${ctx}/zhaopin_${newJob.id}.html"><p class="title">${newJob.title }</p></a>
	                    <p class="salary">￥${newJob.jobDetail.salaryfrom }<c:if test="${not empty newJob.jobDetail.salaryto}">-${newJob.jobDetail.salaryto }元/月</c:if>
	       				<c:if test="${empty  newJob.jobDetail.salaryto}">起</c:if></p>
	                 </div>
               	 </c:forEach>
                <div class="clear"></div>
            </div>
            </div>
        </div>
    </div>
    <!--/job-recommend-->
	<jsp:include page="/WEB-INF/layouts/footer.jsp" />
	<!-- 报名弹出层-->
	<div class="signup-layer hide">
		<!--注册/登录-->
    <div class="reg-layer">
    	<!---->
    	 <!-- <div class="div_login_reg hide">
            <div class="title-box">
                <div class="inner"><span class="title">快速报名</span><span class="close">&times;</span></div>
            </div>
            <div class="input mt20">
                <input class="text-input tip-text txt_user_loginname" type="text" value="请输入手机号码"  maxlength="11"/>
                <span class="send-btn" onclick="YL.job.detail.sendCode()">发送验证码</span><span class="send-btn-disabled">60s后重发验证码</span>
            </div>
            <div class="reg-tips error"></div>
        </div>  -->
        <!--/-->
        <!--登录-->
    	<div class="hide div_login">
            <div class="title-box">
                <div class="inner"><span class="title">登录</span><span class="close">&times;</span></div>
            </div>
            <div class="input mt20 div-loginname">
                <input class="text-input tip-text txt_user_loginname" type="text" placeholder="请输入手机号码" value="" maxlength="11"/>
                <span class="send-btn" onclick="YL.job.detail.valiCode()">发送验证码</span>
            </div>
            <div class="reg-tips error loginname-error"></div>
            <div class="div-pic-code hide">
		        <div class="input">
		            <input class="text-input tip-text form-each-input" id="input_reg_vali_code"   type="text" maxlength="3" pattern="\d+" placeholder="请输入右边的计算答案"/>
		            <img src="${ctx}/verification/reg.jpg?" class="pic-code"  onclick="this.src=this.src+Math.random()" alt="验证码" style="width: 90px;height: 40px;margin-left: 5px; "/>
		            <span class="pic-code-text">
		                  <p>看不清</p>
		                  <p>换一张</p>
		            </span>
		        </div>
		        <div class="reg-tips error error_msg_valicodeu"></div>
		        <label class="btn-g-out"><input type="button" value="确定" class="btn-g btn_reg_vali_code"/></label>
		    </div>
            <div class="input div-thekey hide"> 
                <input class="text-input tip-text code" type="text" placeholder="请输入验证码" value="" maxlength="4"/>
                <!-- <p class="code-text div-voice">收不到短信？使用<a href="javascript:;" class="a-blue send-voice" onclick="YL.job.detail.sendVoice()">语音验证码</a></p> -->
            </div>
            <div class="reg-tips error code-error"></div>
            <div class="input div_name hide"> 
                <input class="text-input tip-text input_name" type="text" placeholder="请输入您的姓名" value=""/>
            </div>
            <div class="reg-tips error name-error"></div>
            <label class="btn-g-out lab-login-reg hide"><input type="button" value="登录" class="btn-g btn-login-reg" /></label>
        </div>
        <!--/登录-->
    </div>
    <!--/注册/登录-->
	
	<!--申请面试-->
    <div class="div_signup hide">
    	<form action="" method="post" class="resume_form">
    	<div class="title-box">
        	<div class="inner"><span class="title">请完善以下信息</span><span class="close">&times;</span></div>
        </div>
        <div class="form-g-medium fl mt0">
	        <!--姓名-->
			<div class="form-each name1">
				<span>
					<input type="text" placeholder="请输入您的姓名" class="form-each-input tip-text" name="name"  value="${resumeBase.name }" />
				</span>
			</div>
            <p class="tip name_err_tip"></p>
			<!--/姓名-->
			<!--性别-->
            <div class="form-each">
               <div class="select-g select-g-1">
					<div class="select-g-title" id="genderTitle">
						<c:if test="${empty resumeBase.gender}">
							<span>请选择您的性别</span>
						</c:if>
						<c:if test="${resumeBase.gender==1}"><span>男</span></c:if>
						<c:if test="${resumeBase.gender==2}"><span>女</span></c:if>
						<i></i>
					</div>
					<div  class="select-g-options ul_gender">
					<ul>
						<li code="1">男</li>
						<li code="2">女</li>
					</ul>
					</div>
					<input type="hidden" name="gender" value="${resumeBase.gender }" /> 
				</div>
            </div>
            <p class="tip gender_err_tip"><!----></p>
            <!--/性别-->
			<!--学历-->
            <div class="form-each">
               <div class="select-g select-g-2">
					<div class="select-g-title" id="educationTitle">
						<c:if test="${empty resumeBase.education}">
							<span>请选择您的学历</span>
						</c:if>
						<c:if test="${not empty resumeBase.education}">
							<span>${cdc:converDicEducation(resumeBase.education)}</span>
						</c:if>
						<i></i>
					</div>
					<div class="select-g-options ul_education">
					<ul>
						<c:forEach items="${educationList }" var="education">
						<c:choose>
							<c:when test="${education.code == 1 }">
								<li code="${education.code }">学历不限</li></c:when>
							<c:otherwise>
								<li code="${education.code }">${education.name }</li>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</ul>
					</div>
					<input type="hidden" name="education" value="${resumeBase.education }"/>
				</div>
            </div>
            <p class="tip education_err_tip"><!----></p>
            <!--/学历-->
            <!--年龄-->
			<div class="form-each name1">
				<span>
					<input type="text" id="input_age" placeholder="请输入您的年龄" class="form-each-input tip-text" name="age"  value="${resumeBase.age }" />
				</span>
			</div>
            <p class="tip age_err_tip"></p>
			<!--/年龄-->
            <div class="form-btn"><label class="btn-g-out ml10"><input type="button" value="保存并继续" class="btn-g w120 btn-large"/></label></div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
        </form>
     </div>
    <!--/申请面试-->
	<!--报名成功-->
    <div class="success hide">
    	<div class="title-box">
        	<div class="inner"><span class="title success_span">报名成功</span><span class="close">&times;</span></div>
        </div>
    	<!--提示信息-->
    	<div class="success-tip">
        	<p class="mb20 mt20">您今天还能投递<span class="blue span_upperLimit">10</span>次</p>
        </div>
        <ul class="pop-steps clearfix">
			<li>
				<i class="icon-1"></i>
				<p class="blue">报名职位</p>
			</li>
			<li>
				<i class="icon-2"></i>
				<p class="cccc">确认面试</p>
			</li>
			<li>
				<i class="icon-3"></i>
				<p class="cccc">参加面试</p>
			</li>
			<li>
				<i class="icon-4"></i>
				<p class="cccc">面试安排</p>
			</li>
		</ul>
        <!--/提示信息-->
         <a href="${ctx }/about/mobile.html" class="btn btn-medium block-center">查看报名进度</a>
        <!---->
    </div>
    <!--/报名成功-->
    
    <!--今日投递已达上限-->
    <div class="upperLimit hide">
    	<div class="title-box">
            <div class="inner bdb0"><span class="close">&times;</span></div>
        </div>
    	<!--提示信息-->
    	<div class="success-tip">
        	<i class="icon-limit mt15"></i>
        	<p class="mt5 mb20 f14">今日投递次数已达上限（<span class="blue">10</span>次）</p>
       </div>
       <!--/提示信息-->
    </div>
    <!--/今日投递已达上限-->
    
    <!--选择排期-->
    <c:if test="${not empty scheduleList }">
    <input type="hidden" id="hid_hasSchedule" value="true"/>
    <div class="div_schedule hide">
    	<div class="title-box">
        	<div class="inner"><span class="title">请选择排期</span><span class="close">&times;</span></div>
        </div>
        <ul class="schedule-list clearfix mt10">
        	<c:forEach items="${scheduleList }" var="schedule" varStatus="i">
        	<c:if test="${i.index<6 }">
        		<li data-id="${schedule.dataCode }"  <c:if test="${i.index%2==0 }">class="item-r"</c:if><c:if test="${i.index%2==1 }">class="item-l"</c:if>><span><fmt:formatDate value="${schedule.startTime }" pattern="MM月dd日"/> &nbsp;<fmt:formatDate value="${schedule.startTime }" pattern="HH:mm"/></span></li>
        	</c:if>
        	</c:forEach>
		</ul>
    </div>
    </c:if>
    <!--/选择排期-->
    <div class="clear"></div>
</div>
<!--/报名弹出层-->
    <!--fixed-nav-->
    <div class="fixed-nav no-pic">
    	<ul>
    		<c:if test="${not empty jobDetail.basicSalary
               || not empty jobDetail.attendanceSalary
               || not empty jobDetail.tryoutSalary
               || not empty jobDetail.overtimeSalary
               || not empty jobDetail.workerSalary
               || not empty jobDetail.floatSalary
               }">
        	<li class="f1" data-target="f1">
            	<p class="icon"></p>
                <p class="name">工资情况</p>
            </li>
            </c:if>
            <c:if test="${not empty jobDetail.mealsdesc 
	            || not empty jobDetail.workdesc
	            || not empty jobDetail.demanddesc  }">
            <li class="f2" data-target="f2">
            	<p class="icon"></p>
                <p class="name">职位描述</p>
                <div class="f2-sub">
                    <p data-target="f23">吃住福利</p>
                    <p data-target="f22">岗位简介</p>
                	<p data-target="f21">招聘要求</p>
                </div>
            </li>
            </c:if>
            <c:if test="${fn:length(imageList)-1 >= 3}">
	            <li class="f3" data-target="f3">
	            	<p class="icon"></p>
	                <p class="name">图片(${fn:length(imageList)-1})</p>
	   	        </li>
   	        </c:if>
            <li class="f4" data-target="f4">
            	<p class="icon"></p>
                <p class="name">咨询<%-- (${advisoryCount }) --%></p>
            </li>
        </ul>
    </div>
    <!--/fixed-nav-->
</div>
<!--/page-wrapper-->
	<script src="${ctx}/static/js/jquery.SuperSlide.2.1.js"></script>
	<script src="${ctx}/static/js/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.autocomplete.css" />
    <script type="text/javascript">
    $(function(){
    	YL.job.detail.init(${jobBase.id});
    	//pic slide
		$(".job-pic").slide({ mainCell:".bd ul", effect:"left", autoPlay:true, delayTime:300, triggerTime:50, interTime:3500, startFun:function(i){
			}
		});
		//left side fixed nav
		function nav_ini(){
			if($(window).width()<1348){
				$('.fixed-nav').hide();
			}
			else $('.fixed-nav').show();
		}
		nav_ini();
		$(window).resize(nav_ini);
        $('.fixed-nav li').click(function(){
            $(this).addClass('active').siblings('li').removeClass('active');
            $('body,html').animate({scrollTop:$('#'+$(this).data('target')).offset().top},300);
            $('.fixed-nav li.f2 p').removeClass('on');
        });
        $('.f2-sub p').click(function(event){
            $('.fixed-nav li').removeClass('active');
            $(this).parents('li').addClass('active');
            $(this).addClass('on').siblings().removeClass('on');
            $('body,html').animate({scrollTop:$('#'+$(this).data('target')).offset().top},300);
            event.stopPropagation();
        });
        //scroll
        var win_h=$(window).height();
        var monitor=true;
        $(window).scroll(function(){
            if(monitor){
                setTimeout(function(){
                    $('.f-content').each(function(){
                        if($(window).scrollTop()>=$(this).offset().top-win_h/2){
                            $('.'+$(this).attr('id')).addClass('active').siblings().removeClass('active');
                        }
                        if($(window).scrollTop()<200 || $(window).scrollTop()>$(document).height()-win_h-50){
                            $('.fixed-nav li').removeClass('active');
                        }
                    });
                    monitor=true;
                },300);
                monitor=false;
            }
        });
    }); 
    </script>
</body>
</html>