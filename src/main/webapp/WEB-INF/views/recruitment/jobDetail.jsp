<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${jobBase.title}_${jobBase.company.name}招聘_${jobBase.company.abbreviation}_招聘${jobBase.jobname}_招聘信息_${jobBase.city.cityName }</title>
<meta name="keywords" content="${fn:replace(jobBase.title, '\"',' ')}${jobBase.company.name }招聘  ${jobBase.company.abbreviation} ${jobBase.jobname}招聘信息 ${jobBase.company.abbreviation}招聘信息" />
<meta name="description" content="${jobBase.company.name }最新招聘信息，${jobBase.title}。${jobBase.company.abbreviation}工作地点位于${jobBase.address}，公司规模${cdc:convertDicStaffscale(jobBase.company.staffscale)}，薪资待遇${jobBase.totalsalary}，学历要求${cdc:converDicEducation(jobDetail.education)}。" />
<link rel="icon" href="http://www.youlanw.com/static/images/favicon.ico" mce_href="http://www.youlanw.com/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="http://www.youlanw.com/static/images/favicon.ico" mce_href="http://www.youlanw.com/static/images/favicon.ico" type="image/x-icon">
<script src="http://www.youlanw.com/static/js/jquery-1.11.1.min.js"></script>
<script src="http://www.youlanw.com/static/js/common.js?v=20150820"></script>
<script src="http://www.youlanw.com/static/js/layer.min.js"></script>
<script src="http://www.youlanw.com/static/js/cookie.js"></script>
<script src="${ctx }/static/js/youlanw.pc.js?v=5"></script>
<link rel="stylesheet" href="http://www.youlanw.com/static/css/common.css?v=20150417" />
<style type="text/css">
.reg-layer{width:365px;}
.reg-layer .form-each-input{width:180px;}
.reg-layer .input{width:360px;height:45px;border-radius:2px;float:left;margin:10px 0;position:relative;}
.reg-layer  .reg-label{ width:80px; float:left; display:inline-block; font-size:16px; text-align:right; line-height:42px;}
.reg-layer .text-input{ margin-left:20px; padding:0 10px; height:40px; border-radius:4px; line-height:40px;border:1px solid #999;box-shadow: 1px 3px 4px #EDEDED inset;width:160px;color:#4D4D4D;font-size:14px; float:left;}
.reg-layer .text-input:hover,.reg-div .text-input:focus{ border-color:#ed5f30; box-shadow:0 0 6px rgba(237,95,48,0.2)}
.reg-layer .input.error .text-input{border-color:#ed5f30; box-shadow:0 0 6px rgba(237,95,48,0.2);}
.reg-layer .text-input.code{width:110px;}
.reg-layer .input .code-text{font-size:14px;position:absolute;top:10px;left:165px;}
.reg-tips{font-size:14px;}
.reg-tips.error{ color:#ed5f30; }
.reg-tips{float:left;color:#999;margin:0 0 0 20px;}
.reg-layer .send-btn{background-color:#ed5f30;font-size:16px;}
.reg-layer .send-btn,.reg-layer .send-btn-disabled{position:absolute;left:215px;min-width:110px;padding:0 10px;height:40px;text-align:center;line-height:40px;cursor:pointer;border-radius:4px;color:#fff;}
.reg-layer .send-btn:hover{background:#f67f58;color:#fff;box-shadow:none;border-color:#f67f58;}
.reg-layer .send-btn:active{background:#e65120;}
.reg-layer .send-btn-disabled{cursor:default;background-color:#aaa;font-size:14px;}
.reg-layer .btn-g-out{margin:10px 0 0 20px;}
/*pic-box*/
.job-summary .pic-box{width:415px;height:365px;}
.job-summary .pic-box .pic-box-content{width:415px;height:365px;}
.job-summary .pic-box img{height:365px;}
.job-summary .pic-box .pic-title{height:50px;line-height:50px;bottom:0;font-size:15px;text-align:left;padding-left:45px;width:290px;}
.job-summary .pic-box .pageState{line-height:20px;font-size:14px;color:#fff;right:45px;text-align:right;bottom:15px;}
.job-summary .pic-box .pageState span{color:#ed5f30;}
.job-summary .pic-box .prev:hover,.job-summary .pic-box .next:hover{background-color:#666;}
/*ask-each*/
.ask-each .name,.ask-each .question{margin-left:0;}
.paging{margin:0 0 30px 0;}
.column-title{position:relative;}
.column-title .orange{position:absolute;right:0;font-size:14px;top:3px;}
</style>
</head>
<body>
<div class="page-wrapper bg-grey">
	<jsp:include page="/WEB-INF/layouts/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/layouts/header.jsp"></jsp:include>
	<!--/top-menu--->
	<div class="nav-g clearfix"><span class="fl"><a href="${ctx}/">首页</a>&gt;<a href="${ctx}/zhaopin/">我要找工作</a>&gt;<a href="${ctx}/zhaopin_${jobBase.id}.html" class="red">${jobBase.title }</a></span></div>
    <!--基本信息-->
    <div class="w990">
     <input type="hidden" id="hid_type" value="${type }" />
        <div class="job-summary">
       		<!-- 最近浏览记录 -->
	        <input type="hidden" id="jobid" value="${jobBase.id }" />
	        <input type="hidden" id="jobtitle" value="${jobBase.title }" />
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
	        <input type="hidden" id="salaryfrom" value="${jobBase.jobDetail.salaryfrom }" />
	        <input type="hidden" id="salaryto" value="${jobBase.jobDetail.salaryto }" />
    	   <!-- /最近浏览记录 --> 		
        	<!--图集-->
			<div class="pic-box" >
				<span class="pageState"></span>
                <span class="prev"></span>
                <span class="next"></span>
                <div class="pic-box-content">
                    <ul>
                    	<c:if test="${empty imageList}">
                    		<li><img src="${cdc:getImageByJobType720(jobBase.jobType)}" /><p class="pic-title">${jobBase.title }</p><p class="opacity-bg"></p></li>
                    	</c:if>
						<c:forEach items="${imageList}" var="img" varStatus="i">
							<li><img src="${cdc:getImagePath720(img.imgpath) }" /><p class="pic-title">${img.comment }</p><p class="opacity-bg"></p></li>
						</c:forEach>
                    </ul>
                </div>
			</div>
			<!--/图集-->
           <!--基本信息-->
           <div class="sum">
           		<h1 class="h1-g">${jobBase.title }</h1>
           		<c:if test="${jobBase.jobConfig.isRecruitment == 0}"><label class="status">正在招聘</label></c:if>
           		<c:if test="${jobBase.jobConfig.isRecruitment == 1}"><label class="status">已停招</label></c:if>
                <p class="salary">￥${jobBase.jobDetail.salaryfrom }<c:if test="${not empty  jobBase.jobDetail.salaryto}">-${jobBase.jobDetail.salaryto }元/月</c:if>
		        <c:if test="${empty  jobBase.jobDetail.salaryto}">起</c:if></li></p>
                <p><label class="name-g">上班地址</label>
                ${cdc:convertProvinceAbbreviation(jobBase.company.provinceid)}
                     ${jobBase.company.city.cityName}
                    ${cdc:converCountyName("",jobBase.company.countyid)}
                    ${jobBase.company.address }
                </p>
                <p><label class="name-g">要&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;求</label>
                ${cdc:converDicEducation(jobDetail.education)}<label class="line-g">|</label>${tc:convertGenderLimit(jobDetail.gender)}<label class="line-g">|</label><c:if test="${not empty jobDetail.agefrom }">
                        ${jobDetail.agefrom }<c:if test="${not empty jobDetail.ageto }">~${jobDetail.ageto}岁</c:if>
                        <c:if test="${empty jobDetail.ageto }">岁以上</c:if></c:if>
                        <c:if test="${empty jobDetail.agefrom }">年龄不限</c:if></p>
                <p><label class="name-g">更新时间</label>今天</p>
                <p class="num">
                	<span><strong><c:if test="${empty jobDetail.showApplyCount}">0</c:if>${jobDetail.showApplyCount }</strong>人报名</span>
                    <span>共招聘<strong> &nbsp;${jobBase.recruitcount }</strong>人</span>
                    <span class="to-ask-info cursor bd0"><strong class="advisory-count">0</strong>条咨询</span>
                </p>
                <p class="tags-g mt15">
                 <c:forEach items="${jobBase.jobLabel}" var="label">
					<a href="${ctx}/s_label_${label }">${label }</a>
				 </c:forEach>
				 </p>
                  <c:if test="${(jobBase.cooperationType eq 1||jobBase.cooperationType eq 5) && jobBase.jobConfig.isRecruitment eq 0}"><p class="sign"><a data-id="${jobBase.id }" class="btn signup-layer-trigger btn-signup fl" >申请面试</a></c:if>
                  <c:if test="${(jobBase.cooperationType ne 1 &&jobBase.cooperationType ne 5) || jobBase.jobConfig.isRecruitment ne 0}"> <p class="sign"><a data-id="${jobBase.id }" class="btn signup-layer-trigger btn-signup fl" >立即预约</a></c:if>
                <label class="c999 fl ml20">本岗位已通过优蓝网严格审核认证，请放心报名。</label></p>
                <p class="mt20">
                	<label class="name-g">服务保障</label>
                    <span class="ml10"><label class="text-icon">验</label>100%真实信息</span>
                    <span class="ml20"><label class="text-icon">保</label>不满意随时投诉</span>
                    <span class="ml20"><label class="text-icon">快</label>24小时快速响应</span>
                </p>
           </div>
           <!--/基本信息-->
           <div class="clear"></div>
        </div>
    </div>
     <!--/基本信息-->
    <div class="w990 job-detail img-hover mt10">
    	<!--left-->
        <div class="box-l-wrapper">
             <!--菜单-->
            <div class="box-l">
            	<div id="scroll-menu">
                	<div class="top-fixed">
                        <ul class="tab-g-title nav">
                            <li class="active"><a href="#company-short">企业信息</a></li>
                            <li><a href="#salary-info">工资情况</a></li>
                            <li><a href="#work-info">做啥事情</a></li>
                            <li><a href="#require-info">什么要求</a></li>
                            <li><a href="#eat-info">吃住情况</a></li>
                            <li><a href="#pay-info">福利待遇</a></li>
                 			<c:if test="${(jobBase.cooperationType eq 1||jobBase.cooperationType eq 5) && jobBase.jobConfig.isRecruitment eq 0}"><p class="sign"><a data-id="${jobBase.id }" class="btn signup-layer-trigger " >申请面试</a></c:if>
                  			<c:if test="${(jobBase.cooperationType ne 1&&jobBase.cooperationType ne 5) || jobBase.jobConfig.isRecruitment ne 0}"> <p class="sign"><a data-id="${jobBase.id }" class="btn signup-layer-trigger" >立即预约</a></c:if>
                        </ul>
                    </div>
                </div>
            </div>
            <!--/菜单-->
            <!--企业信息-->
            <div class="box-l mt10">
            	<ul class="company-short" id="company-short">
                	<li class="map">
                    	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=PnYGbChqQrsmSUczLQrRZvw2"></script>
                        <div id="allmap" style="width:255px;height:190px;">
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
                        myGeo.getPoint("${jobBase.company.address}", function(point){
                            if (point) {
                                map.centerAndZoom(point, 16);
                                map.addOverlay(new BMap.Marker(point));
                            }
                        }, "${jobBase.company.city.cityName}");
                        </script>
                    </li>
                    <li class="company"><h2><a href="${ctx}/qiye_${jobBase.company.id}.html">${jobBase.company.name }</a></h2></li>
                    <li class="amount"><label class="c999">企业规模</label>${cdc:convertDicStaffscale(jobBase.company.staffscale) }</li>
                    <li class="addr"><label class="c999">企业地址</label>
                    ${cdc:convertProvinceAbbreviation(jobBase.company.provinceid)}
                     ${jobBase.company.city.cityName}
                    ${cdc:converCountyName("",jobBase.company.countyid)}
                    ${jobBase.company.address }</li>
                    <li class="intro">
                    	<label class="c999 fl">企业简介</label>
                        <span class="fl">${jobBase.company.introductionView}<a href="${ctx}/qiye_${jobBase.company.id}.html" class="a-orange" target="_blank" title="${jobBase.company.name}">(查看更多)</a></span>
                    </li>
                </ul>
            </div>
            <!--/企业信息-->
            <!--详情-->
            <div class="box-l job-content mt10">
                <div style="padding:0 15px;">
                    <!--工资情况--->
                    <div id="salary-info">
                        <h2 class="h2-g-1 icon">[工资情况]<i></i></h2>
                        <p class="h2-sub">出来打个工，每分每毛都是辛苦钱，工资切记要算清。</p>
                        <div class="salary-info-content">${jobDetail.salarydesc }</div>
                    </div>
                    <!--/工资情况-->
                    <div class="hr-g"></div>
                    
                    <!--做啥事情--->
                    <div id="work-info">
                        <h2 class="h2-g-1 icon">[做啥事情]<i class="icon-work"></i></h2>
                        <p class="h2-sub">仔细了解平时都干什么活，能学到啥技术，心里更有底。</p>
                        <div class="salary-info-content">${jobDetail.workdesc }</div>
                    </div>
                    <!--/做啥事情-->
                    <div class="hr-g"></div>
                    
                    <!--什么要求--->
                    <div id="require-info">
                        <h2 class="h2-g-1 icon">[什么要求]<i class="icon-demand"></i></h2>
                        <p class="h2-sub">看清楚入职要求，重要资料带身上，避免吃亏上当。</p>
                        <div class="salary-info-content">${jobDetail.demanddesc }</div>
                    </div>
                    <!--/什么要求-->
                    <div class="hr-g"></div>
                    
                    <!--吃住情况--->
                    <div id="eat-info">
                        <h2 class="h2-g-1 icon">[吃住情况]<i class="icon-eat"></i></h2>
                        <p class="h2-sub">好企业更体恤员工，一日三餐，吃住有保障。</p>
                        <div class="salary-info-content">${jobDetail.mealsdesc }</div>
                    </div>
                    <!--/吃住情况-->
                    <div class="hr-g"></div>
                    
                    <!--福利待遇--->
                    <div id="pay-info">
                        <h2 class="h2-g-1 icon">[福利待遇]<i class="icon-welfare"></i></h2>
                        <p class="h2-sub">蓝领也要待遇好，日常福利不能少。</p>
                        <div class="salary-info-content">${jobDetail.welfaredesc }</div>
                    </div>
                    <!--/福利待遇-->
                </div>
            </div>
            <!--/详情-->
            <!--相似岗位-->
            <div class="box-l bg-white mt10">
            	<div class="box-l-3">
                    <h2 class="column-title">相似岗位<a href="${ctx }/zhaopin/" class="orange">查看更多&gt;&gt;</a></h2>
                    <div class="job-recommend-list job-recommend-list2 img-hover">
                    	 <c:forEach items="${likeJobs}" var="likeJob">
				            <ul class="recommned-each">
	                            <li class="pic"><a href="${ctx}/zhaopin_${likeJob.id}.html">
	                             <c:choose>
									<c:when test="${empty likeJob.thumbnialImage.imgpath}">
										<img src="${cdc:getImageByJobType320(likeJob.jobType)}" alt="${likeJob.title }" />
									</c:when>
									<c:otherwise>
										<img src="${cdc:getImagePath320(likeJob.thumbnialImage.imgpath)}" alt="${likeJob.title}" />
									</c:otherwise>
								</c:choose>
	                            </a></li>
	                            <li class="opacity-bg"></li>
	                            <a href="${ctx}/qiye_${likeJob.company.id}.html">
	                            <li class="opacity-text">${tc:highlightText(likeJob.company.name ,wd) }</li></a>
	                            <li class="salary">￥${likeJob.jobDetail.salaryfrom }<c:if test="${not empty  likeJob.jobDetail.salaryto}">-${likeJob.jobDetail.salaryto }元/月</c:if>
		        				<c:if test="${empty  likeJob.jobDetail.salaryto}">起</c:if></li>
	                            <li class="title"><a href="${ctx}/zhaopin_${likeJob.id}.html">${likeJob.title }</a></li>
	                        </ul>
				         </c:forEach>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
            <!--/相似岗位-->
             <div class="box-l mt10">
            	<!--网友问答--->
                <div class="box-g pdb0">
                	<h2 class="company-title"><span>工友咨询</span>  <!-- <a href="javascript:void(0)" class="company-more-new">更多问答 ></a> --></h2>
                </div>
                <div id="ask-info" class="pdt0">
                    <div class="mb20">
                    <form id="advisory_form" >
                    	<input type="hidden" name="companyId" value="${jobBase.company.id }" />
                     	<input type="hidden" name="jobId" value="${jobBase.id }" />
                        <textarea class="textarea-g tip-text" name="comment" id="advisory_content">我也说两句...</textarea>
                        <span class="btn-square-wrapper">
                        	<input type="button" value="提交问题" class="btn btn-square" id="btn_sub_advisory"/><i><p>提交</p><p>问题</p></i>
                        </span>
                    </form>
                        <div class="clear"></div>
                    </div>
                   <div id="advisory"></div>
                    <div class="clear"></div>
                </div>
                <!--/网友问答-->
            </div>
        </div>
        <!--/left-->
        <!--right-->
        <div class="box-r-wrapper">
            <!--优蓝网手机版-->
            <div class="box-r">
                <div class="m-entry">
                    <div class="text">
                    	<i></i>
                        <h3>优蓝网手机版</h3>
                        <!-- <p>好工作一手掌握</p> -->
                        <p>m.youlanw.com</p>
                    </div>
                   <!--  <p class="mt15">关注【优蓝求职】微信号</p>
                    <p class="mt5">好工作不错过</p> -->
                    <img src="http://www.youlanw.com/static/images/QR2.jpg" alt="" class="mt5"/>
                </div>
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
                    <img src="http://www.youlanw.com/static/images/QR.jpg" alt=""/>
                </div>
            </div>
            <!--/优蓝网微信版-->
            <c:forEach items="${rightJobListRecommend}" var="recommend">
	            <div class="ad-r">
	                <a href="${recommend.link}"><img src="${cdc:getImagePathO(recommend.img) }" alt="${recommend.imgAlt }"/></a>
	            </div>
           </c:forEach>
            <!--如何求职-->
            <div class="box-r mt10">
            	<!---->
                <div>
                    <h2 class="box-title">如何求职</h2>
                    <div class="job-guide">
                    	<ul class="each">
                        	<li class="left">
                            	<p class="icon icon1"></p>
                                <p>在线报名</p>
                            </li>
                        	<li class="right">在线挑选职位，可为自己和他人预约</li>
                            <li class="arrow"></li>
                        </ul>
                        <ul class="each">
                        	<li class="left">
                            	<p class="icon icon2"></p>
                                <p>客服推荐</p>
                            </li>
                        	<li class="right">客服回电，推荐职位并安排面试</li>
                            <li class="arrow"></li>
                        </ul>
                        <ul class="each">
                        	<li class="left">
                            	<p class="icon icon3"></p>
                                <p>前往面试</p>
                            </li>
                        	<li class="right">面试通过，前往企业报到入职</li>
                            <li class="arrow"></li>
                        </ul>
                        <ul class="each">
                        	<li class="left">
                            	<p class="icon icon4"></p>
                                <p>重新面试</p>
                            </li>
                        	<li class="right">面试结果不满意，可再次为您安排</li>
                            <li class="arrow"></li>
                        </ul>
                        <ul class="each">
                        	<li class="left success">
                            	<p class="icon icon5"></p>
                                <p>入职成功</p>
                            </li>
                        	<li class="right">前往企业报到，即表示入职成功</li>
                        </ul>
                    </div>
                </div>
                <!---->
            </div>
            <!--/如何求职-->
           	<!--最近浏览-->
			<div class="box-r mt10">
				<div class="right-fixed right-fixed2" id="recent_browse_list">
					<h2 class="box-title">最近浏览职位</h2>
					<div class="job-list2">
						<p class="no-result">
							<span><i></i></span>暂无浏览记录
						</p>
					</div>
				</div>
			</div>

		<!-- 	<div class="box-r mt10" >
					<div class="right-fixed right-fixed2" id="recent_browse_list">
					
					</div>
			</div> -->
			<!--最近浏览-->
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
      <!---->
		<!--悬浮菜单-->
<!-- 		<ul class="float-menu">
			<li class="qq-wrapper" onclick="javascript:window.open('http://b.qq.com/webc.htm?new=0&sid=4008777816&eid=218808P8z8p8p8p8q8q8P&o=&q=7&ref='+document.location, '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');">
				<span class="des">在线咨询</span><span class="qq"></span>
			</li>
			<li class="signup-wrapper">
				<a href="http://www.youlanw.com/signup/fastSignUp">
					<span class="des">快速报名</span><span class="signup"></span>
				</a>
			</li>
			<li class="to-wrapper">
				<span class="des">返回顶部</span><span class="to"></span>
			</li>
		</ul> -->
		<!--/悬浮菜单-->
		<jsp:include page="/WEB-INF/layouts/footer.jsp"></jsp:include>
	</div>
<!-- 报名弹出层-->
<div class="signup-layer hide">
		<!--注册/登录-->
    <div class="reg-layer">
    	<!---->
    	<div class="div_login_reg hide">
            <div class="title-box">
                <div class="inner"><span class="title">快速报名</span><span class="close">&times;</span></div>
            </div>
            <div class="input mt20">
                <input class="text-input tip-text txt_user_loginname" type="text" value="请输入手机号码"  maxlength="11"/>
                <span class="send-btn" onclick="YL.job.detail.sendCode()">发送验证码</span><!--<span class="send-btn-disabled">60s后重发验证码</span>-->
            </div>
            <div class="reg-tips error"></div>
        </div>
        <!--/-->
        <!--登录-->
    	<div class="hide div_login">
            <div class="title-box">
                <div class="inner"><span class="title"></span><span class="close">&times;</span></div>
            </div>
            <div class="input mt20">
                <input class="text-input tip-text txt_user_loginname" type="text" value="请输入手机号码" maxlength="11"/>
                <span class="send-btn" onclick="YL.job.detail.sendCode()">发送验证码</span>
            </div>
            <div class="reg-tips error"></div>
            <div class="input"> 
                <input class="text-input tip-text code" type="text" value="请输入验证码"/>
                <!-- <p class="code-text div-voice">收不到短信？使用<a href="javascript:;" class="a-blue send-voice" onclick="YL.job.detail.sendVoice()">语音验证码</a></p> -->
            </div>
            <div class="reg-tips error code-error"></div>
           <label class="btn-g-out"><input type="button" value="" class="btn-g btn-login-reg" /></label>
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
        	<c:if test="${empty resumeBase.name }">
	        <!--姓名-->
			<div class="form-each name1">
				<span>
					<input type="text" placeholder="请输入您的姓名" class="form-each-input tip-text" name="name"  value="${resumeBase.name }" />
				</span>
			</div>
           <p class="tip name_err_tip"></p>
			<!--/姓名-->
			</c:if>
			<c:if test="${(jobBase.cooperationType eq 1||jobBase.cooperationType eq 5) && jobBase.jobConfig.isRecruitment eq 0}">
			<c:if test="${empty resumeBase.gender }">
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
            </c:if>
            <c:if test="${empty resumeBase.idCard }">
            <!--身份证-->
			<div class="form-each">
				<span>
					<input type="text" class="form-each-input tip-text" placeholder="请输入身份证" name="idCard" id="input_idCard"
					value="${resumeBase.idCard }" />
				</span>
			</div>
			<p class="tip idCard_err_tip"><!----></p>
			<!--/身份证-->
			</c:if>
			<c:if test="${empty resumeBase.education }">
			<!--学历-->
            <div class="form-each">
               <div class="select-g select-g-2">
					<div class="select-g-title" id="educationTitle">
						<c:if test="${empty resumeBase.education}">
							<span>请选择您的学历</span>
						</c:if>
						<c:if test="${not empty resumeBase.education}">
							<span>${fn:replace(cdc:converDicEducation(resumeBase.education),'以上', '')}</span>
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
            </c:if>
            <c:if test="${empty resumeBase.nativeProvinceid||empty resumeBase.nativeCityid }">
            <!--籍贯-->
            <div class="form-each">
                <!--省-->
                <div class="select-g select-g-3 w150">
                  <div class="select-g-title native">
                 	<c:if test="${resumeBase.nativeProvinceid == null}"><span>您的籍贯（省）</span></c:if>
                    <c:if test="${resumeBase.nativeProvinceid != null}"><span>${cdc:convertProvinceAbbreviation(resumeBase.nativeProvinceid)}</span></c:if>
					<i></i>
				</div>
				<div class="select-g-options ul_province">
                  <ul>
                    <c:forEach items="${provinceList}" var="province">
							<li code="${province.id}" class="li_province">
								${province.abbreviation}
							</li>
					</c:forEach>
                  </ul>
                  </div>
                </div>
                <!--/省-->
                <!--市-->
                <div class="select-g select-g-4 w150 ml10">
                  <div class="select-g-title native selected_city">
                  	<c:if test="${resumeBase.nativeCityid == null}"><span>您的籍贯（市）</span></c:if>
                    <c:if test="${resumeBase.nativeCityid != null}"><span>${cdc:converCityName(resumeBase.nativeCityid)}</span></c:if>
                  	<i></i>
                  </div>
                  <div class = "select-g-options ul_city_list">
                  </div>
                </div>
                <!--/市-->
            </div>
            <input type="hidden" name="nativeProvinceid" id="nativeProvinceid" value="${resumeBase.nativeprovince.id}"/>
            <input type="hidden" name="nativeCityid" id="nativeCityid" value="${resumeBase.nativecity.id}"/>
            <p class="tip native_err_tip"><!----></p>
            <!--/籍贯-->
            </c:if>
            <c:if test="${empty resumeBase.nation }">
           <!--民族-->
            <div class="form-each">
                <div class="select-g select-g-5">
                  <div class="select-g-title">
					 <c:if test="${resumeBase.nation == null}"><span>请选择您的民族</span></c:if>
                   	 <c:if test="${resumeBase.nation != null}"><span>${cdc:converDicNation(resumeBase.nation)}</span></c:if>
	  				<i></i>
	  			</div>
	  			<div class = "select-g-options nation-options">
                  <ul>
                    <li>请选择民族</li>
                     <c:forEach items="${nationsList}" var="nation">
						<li code="${nation.code}">${nation.name}</li>
					 </c:forEach>
                  </ul>
                  </div>
                </div>
            </div>
            <input type="hidden" name="nation" value="${resumeBase.nation}"/>
            <p class="tip nation_err_tip"><!----></p>
            <!--/民族-->
            </c:if>
            <input type="hidden" name="type" value="2" />
            </c:if>
            <c:if test="${(jobBase.cooperationType ne 1&&jobBase.cooperationType ne 5) || jobBase.jobConfig.isRecruitment ne 0}"> 
            	<c:if test="${empty resumeBase.jobTargetProvinceid ||empty resumeBase.jobTargetCityid}">
            <!--意向城市-->
            <div class="form-each">
                <!--省-->
                <div class="select-g select-g-7 w150">
                  <div class="select-g-title">
                 	<c:if test="${resumeBase.jobTargetProvinceid == null}"><span>您的意向城市(省)</span></c:if>
                    <c:if test="${resumeBase.jobTargetProvinceid != null}"><span>${cdc:convertProvinceAbbreviation(resumeBase.jobTargetProvinceid)}</span></c:if>
					<i></i>
				</div>
				<div class = "select-g-options ul_province">
                  <ul>
                    <c:forEach items="${provinceList}" var="province">
							<li code="${province.id}" class="li_province">
								${province.abbreviation}
							</li>
					</c:forEach>
                  </ul>
                  </div>
                </div>
                <!--/省-->
                <!--市-->
                <div class="select-g select-g-8 w150 ml10">
                  <div class="select-g-title selected_city">
                  	<c:if test="${resumeBase.jobTargetCityid == null}"><span>您的意向城市(市)</span></c:if>
                    <c:if test="${resumeBase.jobTargetCityid != null}"><span>${cdc:converCityName(resumeBase.jobTargetCityid)}</span></c:if>
                  	<i></i>
                  </div>
                  <div class = "select-g-options ul_city_list">
                  </div>
                </div>
                <!--/市-->
            </div>
            <input type="hidden" name="jobTargetProvinceid"  value="${resumeBase.jobTargetProvinceid}"/>
            <input type="hidden" name="jobTargetCityid"  value="${resumeBase.jobTargetCityid}"/>
            <p class="tip jobTargetProvinceCity_err_tip"><!----></p>
            <!--/意向城市-->
            </c:if>
            <c:if test="${empty resumeBase.jobTarget }">
			<!--意向职位-->
            <div class="form-each">
                <div class="select-g select-g-9">
                <div class="select-g-title job_target">
                	<c:if test="${resumeBase.jobTarget == null|| resumeBase.jobTarget==''}"><span>您的意向职位</span></c:if>
                    <c:if test="${resumeBase.jobTarget != null}"><span>${resumeBase.jobTarget}</span></c:if>
                	<i></i>
                </div>
                <div class="select-g-options ul_job_target">
					<ul>
						<li>不限岗位</li>
						<c:forEach items="${jobTypes }" var="types">
							<li>${types.jobName }</li>
						</c:forEach>
					</ul>
				</div>
                </div>
                <input type="hidden" name="jobTarget" value="${resumeBase.jobTarget }"/>
            </div>
            <p class="tip" id="jobTarget_err_tip"><!----></p>
            <!--/意向职位-->
            </c:if>
            <input type="hidden" name="type" value="4" />
            </c:if>
            <div class="form-btn"><label class="btn-g-out ml10"><input type="button" value="保存并继续" class="btn-g w120 btn-large"/></label></div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
        </form>
     </div>
    <!--/申请面试-->
	<!--预约成功-->
    <div class="success hide">
    	<div class="title-box">
        	<div class="inner"><span class="title success_span">预约成功</span><span class="close">&times;</span></div>
        </div>
    	<!--提示信息-->
    	<div class="success-tip">
        	<i class="icon-success2"></i>
        	<p class="text1 success_p">恭喜您！预约成功</p>
        </div>
        <!--/提示信息-->
         <a href="${ctx }/my/apply" class="btn btn-medium block-center">查看求职单</a>
         <p class="text-center mt10">您可在个人中心查看您的求职单，跟踪求职进度</p>
        <!---->
    </div>
    <!--/预约成功-->
    <div class="clear"></div>
</div>
<!--/报名弹出层-->
	<script src="http://www.youlanw.com/static/js/jquery.SuperSlide.2.1.js"></script>
	<script type="text/javascript" src="http://www.youlanw.com/static/js/scroll-monitor.js"></script>
	<script src="http://www.youlanw.com/static/js/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" href="http://www.youlanw.com/static/css/jquery.autocomplete.css" />
    <script type="text/javascript">
    $('body').attr({'data-spy':'scroll','data-target':'#scroll-menu','data-offset':'-450'});
    $(function(){
    	//轮播图
		$(".pic-box").slide({ mainCell:".pic-box-content ul", effect:"left",autoPlay:true,delayTime:400,interTime:3000});
    	YL.job.detail.init(${jobBase.id});
    });
    </script>
    <script type="text/javascript">
		//百度商桥
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://"); document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F83a185fc9f52ffcad1bcb9e4d889301b' type='text/javascript'%3E%3C/script%3E"))
	</script>
</body>
</html>