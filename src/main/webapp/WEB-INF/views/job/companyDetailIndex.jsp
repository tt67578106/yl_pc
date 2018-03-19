<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name= "mobile-agent"content= "format=html5;url=http://m.youlanw.com/qiye_${company.id }.html">
<title>${fn:trim(company.abbreviation) }招聘|招工信息_${fn:trim(company.name) }待遇怎么样</title>
<meta name="keywords" content="${fn:trim(company.abbreviation) }招聘,${fn:trim(company.abbreviation) }招工信息,${fn:trim(company.name) },${fn:trim(company.name) }待遇怎么样"/>
<meta name="description" content="上海优蓝网【${fn:trim(company.abbreviation) }招工招聘】,提供${fn:trim(company.name) }简介,地址,招工信息,招聘岗位薪资待遇问题的咨询及社区讨论,在${fn:trim(company.abbreviation) }打工,成就高薪蓝领工人"/>
<style>
.compName{max-width: 500px !important;}
.certify-hover {right: -452px !important;}
</style>
</head>
<body>

	<div class="nav-g">
    	<a href="${ctx }/">${cityName }找工作</a>&gt;<a href="${ctx }/mingqi/">${cityName }名企招聘</a>&gt;<a href="${ctx}/qiye_${company.id}.html">${company.abbreviation }</a>
    	<ul class="service-g right">
    		<li><span>验</span>100%真实信息</li>
    		<li><span>快</span>30分钟内电话</li>
    		<li><span>保</span>不满随时投诉</li>
    	</ul>
    </div>
	
	<!--基本信息-->
    <div class="box-1200 mt10">
    	<div class="filter-con">
    		<div class="filter-each filter-first">
    			<i class="authentic"></i>
                <div class="company-basic box-g">
		        	<!--part1-->
		        	<div class="part1">
		                <div class="mid">
		                    <h1 class="h-g">
		                       <span class="compName">${company.name }</span>
		                       <c:if test="${company.cooperationType==1||company.cooperationType==5 }">
		                       <label class="certify-label">认证
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
				        		</c:if>
		                    </h1>
		                    <%-- <span class="ml10">平均工资：<label class="orange f16">${averageSalary }元/月</label></span> --%>
		                    <div class="area mt10">
		                    	<span class="fl"><i class="local"></i><label>${cdc:convertProvinceAbbreviation(company.provinceid)}&nbsp;${company.city.cityName}&nbsp;${cdc:converCountyName("",company.countyid)}&nbsp;${company.address }</label></span>
		                        <div class="clear"></div>
		                    </div>
		                    <div class="tags-g-4 mt10">
		                        <span class="fl"><i class="call"></i><label>${not empty company.contactPhone&&company.contactPhone!='--'?company.contactPhone:'『』' }</label></span>
		                        <div class="clear"></div>
		                    </div>
		                    <div class="area mt10">
								<span class="fl">平均工资：<label class="orange f16">${averageSalary }元/月</label></span>
		                        <div class="clear"></div>
							</div>
		                </div>
		                
		                <c:if test="${not empty company.logo.imgpath}">
		                 	<div class="r">
				                <img src="${cdc:getImagePath320(company.logo.imgpath)}" alt="${company.abbreviation }_logo" class="img-bd" />
		                	</div>
		                </c:if>
		                <div class="clear"></div>
		            </div>
		            <!--/part1-->
		            <div class="clear"></div>
		        </div>
    		</div>
        </div>
    </div>
    <!--/基本信息-->
    
    <!-- 企业在招岗位 -->
    <c:if test="${not empty jobs }">
    <div class="w1200 mt10">
    	<!--left-->
    	<div class="box-l-wrapper">
        	
        	<!--列表-->
        	<div class="box-l mt10 w1200">
        		<ul class="company-ul">
        			<li>
                        <div class="com-jobs">
                        	<ul class="com-jobs-each com-jobs-first">
                                <a href="${ctx}/company/job_${company.id }/"><li class="title"><span>招聘信息</span></li></a>
                                <li>工种</li>
                                <li>学历</li>
                                <li>性别</li>
                                <li>年龄</li>
                                <li>工资</li>
                            </ul>
                            <c:forEach items="${jobs }" var="job">
	                           	<ul class="com-jobs-each">
	                                <li class="title">
	                                	<c:choose>
											<c:when test="${empty job.thumbnialImage.imgpath}">
												<img class="pic65-50 mr5" src="${cdc:getImageByJobType320(job.jobType)}" alt="${job.title }" />
											</c:when>
											<c:otherwise>
												<img class="pic65-50 mr5" src="${cdc:getImagePath320(job.thumbnialImage.imgpath)}" alt="${job.title }" />
											</c:otherwise>
										</c:choose>
	                                	<a href="${ctx}/zhaopin_${job.id }.html">[${company.city.cityName}-${cdc:converCountyName("",company.countyid)}]${job.title }</a>
	                                </li>
	                                <li>${job.jobType }</li>
	                                <li>${cdc:converDicEducation(job.jobDetail.education)}</li>
	                                <li>${tc:convertGenderLimit(job.jobDetail.gender)}</li>
	                                <li>
										<c:if test="${not empty job.jobDetail.agefrom }">
				                        ${job.jobDetail.agefrom }<c:if test="${not empty job.jobDetail.ageto }">~${job.jobDetail.ageto}岁</c:if>
				                        <c:if test="${empty job.jobDetail.ageto }">岁以上</c:if></c:if>
				                        <c:if test="${empty job.jobDetail.agefrom }">年龄不限</c:if>
									</li>
	                                <li class="salary">
	                                	<c:choose>
	                                		<c:when test="${not empty job.jobDetail.salaryfrom&& not empty job.jobDetail.salaryto }">
	                                			￥${job.jobDetail.salaryfrom }<c:if test="${not empty  job.jobDetail.salaryto}">-${job.jobDetail.salaryto }元/月</c:if>
					        					<c:if test="${empty  job.jobDetail.salaryto}">起</c:if>
	                                		</c:when>
	                                		<c:otherwise> 面议</c:otherwise>
	                                	</c:choose>
									</li>
	                                <li><a href="${ctx}/zhaopin_${job.id }.html" class="btn btn-tiny2 btn-small">报名</a></li>
	                            </ul>
                     		</c:forEach>
                            <div class="clear remove-border"></div>
                    	</div>
        			</li>
        			<c:if test="${jobCount>3 }">
        			<li class="look-more">
        				<a href="${ctx}/company/job_${company.id }/">查看全部职位（${jobCount }）<i></i></a>
        			</li>
        			</c:if>
        		</ul>
           </div>
            <!--/列表-->
        </div>
        <!--/left-->
       <div class="clear"></div>
    </div>
    </c:if>
    <!-- 企业在招岗位 -->
    
    <!-- 企业简介、问答 -->
    <div class="w1200 mt10">
    	<!--基本信息-->
       <div class="box-l-wrapper">
	       	<div class="title-bar"><span>${fn:trim(company.abbreviation) }企业简介</span></div>
	        <div class="job-summary bd0">
	           <div class="pic-slide">
	                <div class="bd seal-4">
	                    <ul class="relative">
                        	<c:forEach items="${companyImage}" var="image" varStatus="status">
		                	<a href="${ctx}/company/album_${company.id }/">
		                        <li>
		                        	<c:if test="${status.index==0 }">
		                    			<c:choose>
											<c:when test="${image.length==0}">
												<img src="http://www.youlanw.com/static/images/pic-default.jpg"  alt="${company.name }" />
											</c:when>
											<c:otherwise>
												<img src="${cdc:getImagePath320(image.imgPath)}" alt="${image.imgName }"/>
											</c:otherwise>
										</c:choose>
									</c:if>
		                        </li>
		                        <li class="photo-mun">相册共有<span>${image.length }</span>张照片</li>
	                        </a>
           					</c:forEach>
	                    </ul>
	                </div>
	           </div> 
	           <!--基本信息-->
	           <div class="sum">
	                <p><label class="name-g">企业规模：</label>${cdc:convertDicStaffscale(company.staffscale)}</p>
	                <p><label class="name-g">所属行业：</label>${cdc:convertDicIndustryType(company.industryid)}</p>
	                <p><label class="name-g">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>${company.email }</p>
	                <%-- <p><label class="name-g">联&nbsp;&nbsp;&nbsp;系&nbsp;&nbsp;人：</label> ${company.contactPerson }</p> --%>
	                <p class="mb5"><label class="name-g">网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</label>${company.website }</p>
	                <div class="describe pt5">${company.introduction }</div>
	                <p class="p-toggle"><span class="fold-and-open">展开</span></p>
	           </div>
	           <!--/基本信息-->
	           <div class="clear"></div>
	        </div>
	        
	        <div class="title-bar">
		        <a href="${ctx }/wenda/so_0_${company.id}_0_1/"><p><span>${fn:trim(company.abbreviation) }企业问答（共${askCount }条）</span></p></a>
		        <p class="f13 c999">该企业相关问题答案由优蓝网和热心网友共同提供</p>
		        <a href="${ctx }/wenda/ask?companyId=${company.id}&backUrl=qiye_${company.id}.html" class="btn btn-tiny2 btn-small right">我要提问</a>
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
					<div class="consult-more"><a href="${ctx }/wenda/so_0_${company.id }_0_1/" class="f14 blue">查看全部问答&gt;&gt;</a></div>
	        	</c:if>
	        </div>
	        </c:if>
	    </div>
       <!--/基本信息-->
       <div class="box-r-wrapper">
       		<!-- 地图 -->
            <div class="box-r welfare-tags">
	            <div class="job-map">
                    <div id="allmap" style="width: 267px;height: 190px;"></div>
	            </div>
	            <div class="search-map">
	            	<div><i class="start"></i><input type="text" class="form-each-input" id="line1" /></div>
	            	<div><i class="end"></i><input type="text" class="form-each-input" id="line2" /></div>
	            	<div><input type="button" class="btn btn-tiny2 btn-green ml45" value="查询路线"  id="lineResult" /></div>
	            	<span class="huan exc"></span>
	            </div>
            </div>
            <!-- /地图 -->
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
            
            <!--优蓝网手机版-->
            <div class="box-r mt10">
                <a href="http://m.youlanw.com/qiye_${company.id }.html" >
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
        <div class="clear"></div>
    </div>
    <!-- /企业简介、问答 -->
    

<script src="http://www.youlanw.com/static/js/jquery.SuperSlide.2.1.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=PnYGbChqQrsmSUczLQrRZvw2"></script>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(116.331398,39.897445);
    map.addControl(new BMap.NavigationControl()); 
    map.addControl(new BMap.ScaleControl()); 
    // 创建地址解析器实例
    var myGeo = new BMap.Geocoder();
    // 将地址解析结果显示在地图上,并调整地图视野
    var address =null;
    myGeo.getPoint("${company.city.cityName}", function(point){
	   	 map.centerAndZoom(point,12);
        if (point) {
            map.centerAndZoom(point, 16);
            map.addOverlay(new BMap.Marker(point));
        }
    }, address);
    //搜索框收索
	var transit = new BMap.TransitRoute(map, {
			renderOptions: {map: map},
			policy: 0
		});
	$("#lineResult").click(function(){
    var start = $("#line1").val();
	var end = $("#line2").val();
		map.clearOverlays(); 
		search(start,end); 
		function search(start,end){
			transit.search(start,end);
		}
	});
    $(".exc").click(function(){
    	var line1 = $("#line1").val();
    	var line2 = $("#line2").val();
    	$("#line1").val(line2);
    	$("#line2").val(line1);
    });
    $(function(){
    	YL.qiye.detail.init(${company.id});
    });
	</script>
</body>
</html>