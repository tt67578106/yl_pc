<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<!DOCTYPE html>
<html>
<head>
<title>${recruitment.pageTitle }</title>
<meta name="description" content="${recruitment.pageDescription }"/>
<meta name="keywords" content="${recruitment.pageKeywords }" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/common.css"/>
<link rel="shortcut icon" href="${ctx }/static/images/favicon.ico" mce_href="http://www.youlanw.com/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx }/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx }/static/js/common.js?v=20150820"></script>
<script src="${ctx }/static/js/layer.min.js"></script>
<script src="${ctx }/static/js/cookie.js"></script>
<script src="${ctx }/static/js/youlanw.pc.js?v=5"></script>
<style type="text/css">
/*search-box*/
.logo-search .search{width:393px;}
.search-box{width:388px;}
.search-input{width:298px;}
/*logo-search*/
.logo-search{border-bottom:1px solid #ddd;}
.logo-search .search{left:auto;right:0;}
.logo-search .tag-name{position:absolute;left:220px;top:2px;font-weight:normal;font-size:22px;color:#333;height:50px;line-height:50px;padding-left:15px;border-left:1px solid #ddd;}
/*pro-act*/
.pro-act{border:1px solid #ddd;}
.pro-act .inner{padding:15px;}
.pro-act .inner .pic{width:620px;height:115px;}
.pro-act .r{width:315px;}
.pro-act .r .des{color:#999;font-size:14px;line-height:2;}
/*pro-job-list*/
.pro-job-list{background-color:#fff;padding:0 10px 5px 10px;border:1px solid #ddd;border-top:4px solid #ed5f30;}
.pro-job-list .each{border-bottom:1px dashed #e3e3e3;padding:10px 0 10px 120px;position:relative;min-height:85px;}
.pro-job-list .pic{position:absolute;left:0;width:110px;height:85px;}
.pro-job-list .title{font-size:15px;color:#666;margin-top:2px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
.pro-job-list .sum{margin-top:10px;padding-right:130px;}
.pro-job-list .company,.pro-job-list .company a{font-size:14px;color:#999;}
.pro-job-list .company{position:relative;top:-1px;}
.pro-job-list .company a{max-width:258px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;display:inline-block;vertical-align:middle;}
.pro-job-list .des{color:#999;margin-top:3px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
.pro-job-list .salary{position:absolute;right:10px;top:50%;margin-top:-15px;font-size:23px;height:30px;line-height:30px;}
.pro-job-list .text-icon{background-color:#338b09;padding:2px 3px;margin-left:5px;position:relative;top:1px;*left:5px;*top:3px;}
.pro-job-list .labels{position:relative;top:-4px;overflow:hidden;height:26px;}
.pro-job-list .labels a{padding:3px 6px;border:1px solid #ed5f30;color:#ed5f30;margin-left:10px;float:left;}
.pro-job-list .labels a:hover{background-color:#ed5f30;color:#fff;}
.pro-job-list a:hover,.pro-job-list .title:hover{color:#ed5f30;}
.pro-more{display:block;margin:0 auto;width:160px;text-align:center;font-size:18px;color:#ed5f30;margin-top:15px;height:40px;line-height:40px;border-radius:4px;border:1px solid #ed5f30;}
.pro-more:hover{background-color:#ed5f30;color:#fff;}
/*pro-tab-title*/
.pro-tab-title{height:35px;line-height:35px;font-size:16px;}
.pro-tab-title li{float:left;width:100px;text-align:center;background-color:#ddd;margin-left:10px;border:1px solid #d2d2d2;height:33px;line-height:33px;border-radius:3px 3px 0 0;cursor:pointer;}
.pro-tab-title li.current{background-color:#ed5f30;color:#fff;border-color:#ed5f30;}
.pro-tab-title .name{color:#ed5f30;font-size:15px;font-weight:bold;}
/*pro-city*/
.pro-city{width:975px;padding:5px 0 15px 15px;border:1px solid #ddd;background-color:#fff;font-size:14px;}
.pro-city .name{float:left;margin:10px 15px 0 0;}
.pro-city a{color:#2674d0;margin:10px 15px 0 0;white-space:nowrap;float:left;}
.pro-city a:hover{color:#ed5f30;}
</style>
</head>
<body>
<div class="page-wrapper bg-grey">
	<jsp:include page="/WEB-INF/layouts/top.jsp"></jsp:include>
    <!--logo search QR-->
    <div class="logo-search full-width bg-white">
        <div class="w990 relative">
        	<a href="${ctx }/"><img src="${ctx }/static/images/logo.png" class="logo" alt="优蓝网"/></a>
            <h1 class="tag-name">${recruitment.title }</h1>
            <div class="search">
                <div class="search-box">
                	<div class="con">
                    	<label class="con-text"><span>岗位</span>
							<i class="arrow-d-2"></i>
						</label>
						<ul class="con-list">
							<li>公司</li>
						</ul>
                    </div>
                    <input type="text" class="search-input tip-text" placeholder="请输入职位信息" id="input_search_LIKE_jobname" name="search_LIKE_searchName"/>
                    <input type="button" value="搜索" class="btn search-btn search-enter"/>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--/logo search QR-->
    <!--推广活动-->
    <div class="box-out bg-white pro-act mt10">
    	<div class="inner">
        	<img src="${cdc:getImagePath720(recruitment.imgUrl) }" alt="" class="pic fl"/>
            <div class="r fr">
                 <div class="des">
                 	<p>
                	  ${recruitment.introductionPreview }  
                	</p>
                </div> 
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--/推广活动-->
    
     <!--城市-->
    <div class="pro-city box-out mt10">
        <div class="pro-city-list">
        	<label class="name fl">城市：</label>
        	<span>
        		<c:forEach items="${branchList }" var ="branch" varStatus="list">
        			<c:if test="${list.index<30 }">
                		<a href="http://${branch.webPrefix }.youlanw.com/zhaopin/" target="_blank">${branch.branchName }</a>
                	</c:if>
                </c:forEach>
            </span>
            <a href="${ctx }/about/chooseCity">更多城市 &gt;&gt;</a>
        </div>
        <div class="clear"></div>
    </div>
    <!--/城市-->
    
    <!--职位-->
    <div class="box-out mt15">
    	<div class="tab-g">
        	<div class="pro-tab-title">
            	<label class="name fl">热门城市：</label>
                <ul class="title fl">
                	<c:if test="${empty branch }">
                		<li class="current">全国</li>
                	</c:if>
                	<c:forEach items="${recruitmentBranchList }" var="recruitBranch" varStatus="branchStatus">
                	<c:if test="${branchStatus.index<8 }">
                	 <a href="http://${recruitBranch.branch.webPrefix}.youlanw.com/recruitment_${recruitCode }.html">
                	 	<c:choose>
	                	 	<c:when test="${recruitBranch.branch.id == branch.id }">
	                	 		<li class="current">${recruitBranch.branch.abbr }</li>
	                	 	</c:when>
	                	 	<c:otherwise>
	                	 		<li>${recruitBranch.branch.abbr }</li>
	                	 	</c:otherwise>
                	 	</c:choose>
                	 </a>
                	 </c:if>
                	</c:forEach>
                </ul>
                <div class="clear"></div>
            </div>
            <div class="content-each show">
            <div class="pro-job-list">
            <c:forEach items="${jobList }" var="recruitJob">
	            <div class="each">
                  	<a href="${ctx }/zhaopin_${recruitJob.job.id}.html" target="_blank">
                  		<c:choose>
							<c:when test="${empty recruitJob.job.thumbnialImage.imgpath}">
								<img src="${cdc:getImageByJobType320(recruitJob.job.jobType)}" alt="${recruitJob.job.title }" class="pic" />
							</c:when>
							<c:otherwise>
								<img src="${cdc:getImagePath320(recruitJob.job.thumbnialImage.imgpath)}" alt="${recruitJob.job.title }"  class="pic"/>
							</c:otherwise>
						</c:choose>
	                </a>
	                <a href="${ctx }/zhaopin_${recruitJob.job.id}.html" target="_blank">
	                	<h3 class="title">
	                	<c:choose>
							<c:when test="${empty recruitJob.job.recruitmentJobDesc.jobTitle }">
								${recruitJob.job.title }
							</c:when>
							<c:otherwise>
								${recruitJob.job.recruitmentJobDesc.jobTitle}
							</c:otherwise>
						</c:choose>
	                	</h3>
	                </a>
	                <div class="sum">
	                    <span class="company fl">（<a href="${ctx }/qiye_${recruitJob.job.company.id }.html" target="_blank">${recruitJob.job.company.name }</a><c:if test="${recruitJob.job.company.validation == 1 }"><label class="text-icon">验</label></c:if>）</span>
	                    <div class="labels fl">
	                     <c:forEach items="${recruitJob.job.jobLabel}" var="label" varStatus="labelStatus">
	                     	<c:if test="${labelStatus.index<4 }">
                				<a href="${ctx}/s_label_${label}" target="_blank">${label }</a>
                			</c:if>
                    	 </c:forEach>
	                    </div>
	                    <div class="clear"></div>
	                </div>
	                <div class="des">
	                	<c:choose>
							<c:when test="${empty recruitJob.job.recruitmentJobDesc.jobDescription }">
								招收${fn:trim(recruitJob.job.jobname) }${recruitJob.job.recruitcount }名，要求${cdc:converDicEducation(recruitJob.job.jobDetail.education)}，${tc:convertGenderLimit(recruitJob.job.jobDetail.gender)}，年龄 ${recruitJob.job.jobDetail.agefrom }~${recruitJob.job.jobDetail.ageto}岁。
							</c:when>
							<c:otherwise>
								${recruitJob.job.recruitmentJobDesc.jobDescription }
							</c:otherwise>
						</c:choose>
	                </div>
	                <div class="salary">
	                	￥${recruitJob.job.jobDetail.salaryfrom }
	                	<c:if test="${not empty  recruitJob.job.jobDetail.salaryto}">-${recruitJob.job.jobDetail.salaryto }元/月</c:if>
		                <c:if test="${empty  recruitJob.job.jobDetail.salaryto}">起</c:if>
	                </div>
            	</div>
            </c:forEach>
            <div class="remove-border"></div>
        </div>
        		<a href="${ctx }/zhaopin" target="_blank" class="pro-more">查看更多</a>
            </div>
        </div>
    </div>
    <!--/职位-->
    
    <div class="w990 mt20">
			<ul class="tab-title">
				<li class="current">热门城市</li>
				<li>热门岗位</li>
				<li>最新岗位</li>
				<li>热词</li>
			</ul>
			<div class="tab-each show each-zone" id="divJobType">
				<a href="http://su.youlanw.com/" branch="2">苏州找工作</a>
				<a href="http://sh.youlanw.com/" branch="1">上海找工作</a>
				<a href="http://nj.youlanw.com/" branch="4">南京找工作</a>
				<a href="http://hz.youlanw.com/" branch="3">杭州找工作</a>
				<a href="http://nb.youlanw.com/" branch="5">宁波找工作</a>
				<a href="http://wx.youlanw.com/" branch="12">无锡找工作</a>
				<a href="http://ks.youlanw.com/" branch="6">昆山找工作</a>
				<a href="http://xm.youlanw.com/" branch="10">厦门找工作</a>
				<a href="http://gz.youlanw.com/" branch="11">广州找工作</a>
				<a href="http://hk.youlanw.com/" branch="17">海口找工作</a>
				<a href="http://cd.youlanw.com/" branch="8">成都找工作</a>
				<a href="http://cq.youlanw.com/" branch="9">重庆找工作</a>
				<a href="http://gy.youlanw.com/" branch="19">贵阳找工作</a>
				<a href="http://bj.youlanw.com/" branch="14">北京找工作</a>
				<a href="http://tj.youlanw.com/" branch="13">天津找工作</a>
				<a href="http://qd.youlanw.com/" branch="20">青岛找工作</a>
				<a href="http://zz.youlanw.com/" branch="7">郑州找工作</a>
				<a href="http://wh.youlanw.com/" branch="16">武汉找工作</a>
				<a href="http://hf.youlanw.com/" branch="40">合肥找工作</a>
				<a href="http://nc.youlanw.com/" branch="15">南昌找工作</a>
				
			</div>
			<div class="tab-each">
				<c:forEach items="${hotJobs }" var="hotJob">
					<a href="${ctx}/zhaopin_${hotJob.jobId}.html">${hotJob.jobTitle }</a>
				</c:forEach>
			</div>
			<div class="tab-each">
				<c:forEach items="${newJobs }" var="newJob">
					<a href="${ctx}/zhaopin_${newJob.jobId}.html">${newJob.jobTitle }</a>
				</c:forEach>
			</div>
			<div class="tab-each">
			<c:forEach items="${hotWords }" var="hotWord">
				<a href="${ctx }${tc:getHotWordValue(hotWord,'url')}">${tc:getHotWordValue(hotWord,'text') }</a>
			</c:forEach>
			</div>
			<div class="clear"></div>
		</div>
		<!--/关键词-->
		
		<jsp:include page="/WEB-INF/layouts/footer.jsp"></jsp:include>
	</div>
	
    <script type="text/javascript">
	$(function(){
		//tab
		$('.tab-title li').click(function(){
			$(this).addClass('current').siblings().removeClass('current');
			var current_index=$('.tab-title li').index($(this));
			$('.tab-each').eq(current_index).show().siblings('.tab-each').hide();
		});
		//标签区域宽度
		$('.pro-job-list .each').each(function(){
			$(this).find('.labels').css('width',830-$(this).find('.company').width()-$(this).find('.salary').width());
		});
		//切换搜索
		$('.con-text').click(function() {
			$('.con-list').toggle();
			if ($('.con-text span').text() == "岗位") {
				$('.con-list li').text("公司");
			} else {
				$('.con-list li').text("岗位");
			}
		});
		$('.con-list li').click(function() {
			$('.con-list').hide();
			$('.con-text span').text($(this).text());
			$(this).attr("code");
			if ($(this).text() == "岗位") {
				$("#input_search_LIKE_jobname").attr({
					"name" : "search_LIKE_searchName",
					"placeholder" : "请输入职位信息"
				});
			} else {
				$("#input_search_LIKE_jobname").attr({
					"name" : "search_LIKE_searchName",
					"placeholder" : "请输入企业名称"
				});
			}
		});
		//搜索
		$('.search-btn').click(function(){
			var key = 0; 
			if($('.search-input').val() != null && $('.search-input').val()!=""){
				key = $('.search-input').val().replace('_','&');;
			}
			if ($.trim($('.con-text').text()) == "岗位") {
				window.location.href=YL.ctx+'/zp_auto_'+key+'_1.html';
			} else {
				window.location.href=YL.ctx+'/qy_auto_'+key+'_1.html';
			}
		});
	})
    </script>
</body>
</html>