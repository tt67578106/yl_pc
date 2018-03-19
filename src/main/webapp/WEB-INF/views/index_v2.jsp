<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" /> 
<c:set var="ctxBase" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:if test="${not empty sessionScope.session_key_branch_name}"><c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" /></c:if>
<!DOCTYPE html>
<html>
<head>
<meta name= "mobile-agent"content= "format=html5;url=http://m.youlanw.com/<c:if test="${not empty sessionScope.session_key_branch_web_prefix}">${fn:trim(sessionScope.session_key_branch_web_prefix)}/</c:if>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="Qe3ngwHAwx" />
<meta name="360-site-verification" content="f6177ad94fa56fce45694d98b98737e8" />
<meta name="sogou_site_verification" content="RJNAqlHvBE"/>
<title>${cityName }招工信息网_${cityName }普工招聘信息_名企工厂招工招聘_${cityName }优蓝网</title>
<meta name="keywords" content="${cityName }招工信息,${cityName }招工信息网,${cityName }招工网,${cityName }普工招聘,${cityName }普工招聘信息" />
<meta name="description" content="${cityName }招工信息网是专业蓝领人才招聘网站,免费提供海量${cityName }市高薪名企工厂普工,操作工,司机,快递,客服,销售等招工招聘信息,过滤虚假欺骗信息,${cityName }名企打工就上优蓝网" />
<link rel="dns-prefetch" href="//static.life.youlanw.com">
<link rel="dns-prefetch" href="//img.youlanw.com">
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/css/common.css?v=20160701" />
<script type="text/javascript">
if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)||/(Android)/i.test(navigator.userAgent)) {
	window.location.href = "http://m.youlanw.com/?jumper="	+ window.location.pathname;
}

var _vds = _vds || [];
window._vds = _vds;
(function(){
  _vds.push(['setAccountId', '844010516d2d97c9']);
  _vds.push(['setCS1', 'user_id', '${sessionScope.session_user_key.id}']);
  _vds.push(['setCS3', 'user_name', '${sessionScope.session_user_key.username}']);
  (function() {
    var vds = document.createElement('script');
    vds.type='text/javascript';
    vds.async = true;
    vds.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'dn-growing.qbox.me/vds.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(vds, s);
  })();
})();
</script>
<script src='//kefu.easemob.com/webim/easemob.js?tenantId=1780&hide=true' async='async'></script>
<style>
.footer-menu-list .qq{background-position:0px -1497px;padding:10px 0 0 65px;height:50px;margin-top:30px;width:165px;color:#2bb7ab;font-size:13px;position:relative;margin-left:5px}
.footer-menu-list .qq p{position:relative;top:-5px}
.footer-menu-list .qq span{font-size:22px;position:relative;top:-5px}
.footer-menu-list li{width:185px}
/*图片缩放显示*/
.fam-ent .article-list-each .pic,.job-rec-each>li:first-child{overflow: hidden;}
.article-list-each .pic img,.job-rec-each img{transition: transform 0.6s ease 0s;}
.article-list-each .pic img:hover,.job-rec-each img:hover{-webkit-transform: scale(1.15);transform: scale(1.15);}
.slide-center{width:1200px;position:absolute;left:50%;margin-left:-600px;height:370px}
.slide-center .center-left{height:330px;position:absolute;left:0;z-index:5;top:50%;margin-top:-170px;width:500px;background-color:#fff;padding-top:10px;box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.2);}
.slide-center .center-left .form-each-input{border-radius:0;box-shadow:none;color:#999;cursor:text}
.slide-center .center-left .form-each-input label{background-color:#80d4cd;color:#fff;padding:4px 4px;margin-right:4px;width:50px;display:inline-block;text-align:center;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;font-size:12px;margin-top:-3px}
.slide-center .center-left .form-each-input label span{cursor:pointer;background:url(../images/v3/delete.png) no-repeat;vertical-align:middle;width:15px;height:18px;display:inline-block;margin-left:4px}
.slide-center .center-left .tab-title{width:150px;text-align:center;border:none}
.slide-center .center-left .tabs{background-color:#FFF}
.slide-center .center-left .tabs li{font-size:18px;color:#999;float:none;border-top:1px solid #ddd;height:80px}
.slide-center .center-left .tabs li.current{border-left:3px solid #2bb7ab;margin-left:-1px}
.slide-center .center-left .tabs li:first-child{border-top:none}
.slide-center .center-left .tab-content{float:left}
.slide-center .center-left .tab-content .tab-each{width:360px;border:none;padding:10px 20px 10px 20px;height:282px}
</style>
</head>
<body>
<div class="page-wrapper">
	<jsp:include page="/WEB-INF/layouts/top.jsp" />
	<!--右侧固定菜单栏-->
	<div class="fix-menu">
		<div class="er-code">
			<i></i><span class="arrow"></span>
			<ul class="ul-outer">
				 <li>
				 	<ul  class="ul-inner">
				 		<li><img src="${ctx}/static/images/data/img-code2.png"/></li>
				 		<li>
				 			<h3>下载优蓝APP</h3>
				 			<p>入职更快50%</p>
				 			<p>享受员工福利</p>
				 		</li>
				 	</ul>
				 </li>
				 <li>
				 	<ul  class="ul-inner">
				 		<li><img src="${ctx}/static/images/data/img-code1.png"/></li>
				 		<li>
				 			<h3>关注优蓝网微信</h3>
				 			<p>了解更多求职资讯</p>
				 			<p>微信号：youlanqiuzhi</p>
				 		</li>
				 	</ul>
				 </li>
			</ul>
		</div>
		<c:if test="${not empty sessionScope.session_user_key}">
		<div class="person">
			<a href="${ctx }/my"><i></i><span class="arrow"></span></a>
			<div class=""><a href="${ctx }/my">微简历</a></div>
		</div>
		</c:if>
		<div class="menus">
			<ul>
				<li class="time">
					<i></i><span class="arrow"></span>
					<div class="box-r" id="recent_browse_list"><div class="job-list2 mt0"><p class="no-result"><span><i></i></span>暂无浏览记录</p></div></div>
				</li>
				<li class="love">
					<a href="${ctx }/my/apply"><i></i></a>
					<span class="arrow"></span>
					<div class=""><a href="${ctx }/my/apply">面试安排</a></div>
				</li>
			</ul>
		</div>
		<div class="menu-bottom">
			<ul>
				<li class="edit">
					<a href="${ctx }/signup/fastSignUp"><i></i><span class="arrow"></span></a>
					<div class=""><a href="${ctx }/signup/fastSignUp">快速报名</a></div>
				</li>
				<li class="consult">
					<a href="javascript:;" onclick="easemobIM()" tenantId=1780><i></i><span class="arrow"></span></a>
					<div class=""><a href="javascript:;" onclick="easemobIM()" tenantId=1780>在线咨询</a></div>
				</li>
				<li class="top mb0">
					<i></i><span class="arrow"></span>
					<div class="">返回顶部</div>
				</li>
			</ul>
		</div>
	</div>
	<!--/右侧固定菜单栏-->
    <!--news-->
    <div class="news">
    	<jsp:include page="/WEB-INF/template/home_top_marqueue.jsp"></jsp:include>
    </div>
    <!--/news-->
	<!--轮播图 快速找工作-->
	<div class="slide-center">
   		<div class="center-left">
    		<div class="form-each relative">
    			<span class="form-each-name">意向城市</span>
   			    <input type="text" value="${cityName }" data-code="${sessionScope.session_key_branch_web_prefix }" class="form-each-input intention-input" readonly="readonly" />
   			    <div class="intention-city">
    			    	<ul class="city-nav">
    			    		<li class="current">热门</li><li>ABCDEF</li><li>GHJKLM</li><li>NPQRS</li><li>TWXYZ</li>
    			    		<div class="clear"></div>
    			    	</ul>
    			    	<div class="city-content">
    			    		<div class="item current">
    			    			<ul>
    			    				<li data="sh">上海</li><li data="su">苏州</li><li data="nj">南京</li><li data="cq">重庆</li>
    			    				<li data="cd">成都</li><li data="hz">杭州</li><li data="tj">天津</li><li data="wh">武汉</li>
    			    				<li data="xm">厦门</li><li data="nb">宁波</li><li data="wx">无锡</li><li data="hf">合肥</li>
    			    				<li data="nc">南昌</li><li data="bj">北京</li><li data="xa">西安</li><li data="zz">郑州</li>
    			    			</ul>
    			    		</div>
    			    		<div class="item">
    			    		<c:forEach items="${branchList}" var="branch" varStatus="tag">
	    			    		<c:if test="${tag.index == 0 || (tag.index > 0 && fn:substring(branchList[tag.index].webPrefix,0,1) != fn:substring(branchList[tag.index-1].webPrefix,0,1)&&tag.index<58)}" >
			               			<div class="letter">${cdc:strToUpperCase(fn:substring(branch.webPrefix,0,1))}</div>
	    			    			<div class="city">
	    			    				<c:forEach items="${branchList}" var="branch1" varStatus="tag1">
							                  <c:if test="${fn:substring(branch1.webPrefix,0,1) == fn:substring(branch.webPrefix,0,1)}">
							                  	<span data="${branch1.webPrefix}">${branch1.abbr}</span>
							                  </c:if>
				                  		</c:forEach>
	    			    			</div>
				                </c:if> 
    			    		</c:forEach>
    			    		</div>
    			    		<div class="item">
    			    		<c:forEach items="${branchList}" var="branch" varStatus="tag">
	    			    		<c:if test="${tag.index > 57 && fn:substring(branchList[tag.index].webPrefix,0,1) != fn:substring(branchList[tag.index-1].webPrefix,0,1)&&tag.index<148 }" >
			               			<div class="letter">${cdc:strToUpperCase(fn:substring(branch.webPrefix,0,1))}</div>
	    			    			<div class="city">
	    			    				<c:forEach items="${branchList}" var="branch1" varStatus="tag1">
							                  <c:if test="${fn:substring(branch1.webPrefix,0,1) == fn:substring(branch.webPrefix,0,1)}">
							                  	<span data="${branch1.webPrefix}">${branch1.abbr}</span>
							                  </c:if>
				                  		</c:forEach>
	    			    			</div>
				                </c:if> 
    			    		</c:forEach>
    			    		</div>
    			    		<div class="item">
    			    		<c:forEach items="${branchList}" var="branch" varStatus="tag">
	    			    		<c:if test="${tag.index > 147 && fn:substring(branchList[tag.index].webPrefix,0,1) != fn:substring(branchList[tag.index-1].webPrefix,0,1)&&tag.index < 206}" >
			               			<div class="letter">${cdc:strToUpperCase(fn:substring(branch.webPrefix,0,1))}</div>
	    			    			<div class="city">
	    			    				<c:forEach items="${branchList}" var="branch1" varStatus="tag1">
							                  <c:if test="${fn:substring(branch1.webPrefix,0,1) == fn:substring(branch.webPrefix,0,1)}">
							                  	<span data="${branch1.webPrefix}">${branch1.abbr}</span>
							                  </c:if>
				                  		</c:forEach>
	    			    			</div>
				                </c:if> 
    			    		</c:forEach>
    			    		</div>
    			    		<div class="item">
    			    		<c:forEach items="${branchList}" var="branch" varStatus="tag">
	    			    		<c:if test="${tag.index > 205 && fn:substring(branchList[tag.index].webPrefix,0,1) != fn:substring(branchList[tag.index-1].webPrefix,0,1) }" >
			               			<div class="letter">${cdc:strToUpperCase(fn:substring(branch.webPrefix,0,1))}</div>
	    			    			<div class="city">
	    			    				<c:forEach items="${branchList}" var="branch1" varStatus="tag1">
							                  <c:if test="${fn:substring(branch1.webPrefix,0,1) == fn:substring(branch.webPrefix,0,1)}">
							                  	<span data="${branch1.webPrefix}">${branch1.abbr}</span>
							                  </c:if>
				                  		</c:forEach>
	    			    			</div>
				                </c:if> 
    			    		</c:forEach>
    			    		</div>
    			    	</div>
    			</div>
    		</div>
    		<div class="form-each">
    			<span class="form-each-name">期望薪资</span>
    			<div class="form-each-input bd0 slider-boxsize">
    			    <input type="hidden" class="single-slider" value="3000" />
    			</div> 
    		</div>
    		<div class="form-each relative">
    			<span class="form-each-name">期望工作</span>
    			<div class="form-each-input hope-input">选择工作</div> 
    			<div class="hope-job">
    				<ul>
    					<c:forEach items="${jobTypes }" var="jobType">
	    					<li data="${jobType.jobName }"><span>${jobType.jobName }</span></li>
    					</c:forEach>
    					<div class="clear"></div>
    				</ul>
    			</div>
    		</div>
    		<div class="form-each relative">
    			<span class="form-each-name">附加要求</span>
    			<div class="form-each-input demand-input">选择福利</div> 
    			<div class="demand">
    				<ul>
    					<c:forEach items="${jobTags }" var="jobTag">
	    					<li data="${jobTag.tagName }"><input type="checkbox" /><span>${jobTag.tagName }</span></li>
    					</c:forEach>
    					<div class="clear"></div>
    				</ul>
    				<div class="btn-group mt10">
    					<input type="hidden" id="hid_job_tag" />
    					<span class="orange left">附加要求最多可选五项</span>
    					<button type="button" class="btn btn-tiny2 btn-green ml10 w90 right clean-demand">清空</button>
    					<button type="button" class="btn btn-tiny2 btn-green w90 right save-demand">确定</button>
    					<div class="clear"></div>
    				</div>
    			</div>
    		</div>
    		<div class="form-each relative">
    			<input class="btn-g btn-search"  type="button" value="搜索" /> 
    			<p class="hot-recommond"><label>热门：</label>
    			<c:forEach items="${hotRecommonds }" var="recommond">
    				<a href="${recommond.resource.linkUrl }"><span>${recommond.resource.title }</span></a>
    			</c:forEach>
				</p>
    		</div>
    		<div class="clear"></div>
    		<div class="tips">提示：优蓝网岗位均已通过优蓝网严格审核认证，请放心报名。</div>
   		</div>
   		
       </div>
	<div class="full-slide full-slideNew">
      <div class="relative" style="height: 370px;">
    	<!--轮播图-->
		<div class="bd">
			<ul>
				<c:forEach items="${shufflingJobs}" var="recommend" varStatus="status">
					<c:set var="imgUrl" value="" />
					<c:if test="${not empty recommend.resource.thumbImageUrl}">
						<c:set var="imgUrl" value="url('${recommend.resource.thumbImageUrl}')" />
					</c:if>
					<li style="background:#02020a ${imgUrl} center 0 no-repeat;">
						<a target="_blank" href="${recommend.resource.linkUrl}"></a>
						<%-- <div class="slide-textbg"></div>
						<span class="slide-text">${recommend.title}</span> --%>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="hd-outer">
		    <div class="hd">
				<ul>
					<c:forEach items="${shufflingJobs}" var="recommend" varStatus="status">
						<c:if test="${not empty recommend.resource.thumbImageUrl}">
							<li></li>
						</c:if>
					</c:forEach>
				</ul>
	        </div>
	    </div> 
        <!--/轮播图-->
      </div>
	</div>
    <!--/轮播图 快速找工作-->
    <!--招聘链接-->
    <div class="bg-gray">
	    <div class="w1200 relative">
	        <span class="bar-left">最新招聘<i></i></span>
		    <ul class="link-job">
		    	<c:forEach items="${newJobs }" var="job">
		    		<a href="${ctx }/zhaopin_${job.jobId}.html"><li title="${job.jobTitle }">${job.jobTitle }</li></a>
		    	</c:forEach>
		    	<div class="clear"></div>
		    </ul>
	    </div>
	</div>
    <!--招聘链接-->
    <!--名企专区 大家都想进-->
    <div class="fam-wrapper bdt0 pdt0">
        <div class="w1200">
            <!--名企专区-->
           <c:forEach items="${advertPositionVos }" var="areaPosition">
            <div class="fam-ent">
            	<h2 class="col-title" style="color: ${areaPosition.areaColor };"><a href="${areaPosition.linkUrl }" target="_blank"><i style="background:url(${areaPosition.areaIcon })" ></i>${areaPosition.areaName }</a><a href="${areaPosition.linkUrl }" target="_blank" class="more-g-3">${areaPosition.areaRemark }</a></h2>
	            <div class="ent-inner" style="border-top-color: ${areaPosition.areaColor };">   
	            	<div class="inner-left">
	            		<c:forEach items="${areaPosition.positionLinks }" var="positionLink" varStatus="status" >
	            			<c:if test="${status.index==0 && not empty positionLink.linkTitle  }">
		            			<h3>${positionLink.linkTitle }
		            				<c:if test="${not empty positionLink.linkUrl }"><a href="${positionLink.linkUrl }">全部<i class="all-link"></i></a></c:if>
		            			</h3>
	            			<ul>
	            			</c:if>
	            			<c:if test="${status.index==9 && not empty positionLink.linkTitle }">
		            			</ul>
		            			<div class="clear"></div>
		            			<h3>${positionLink.linkTitle }
		            				<c:if test="${not empty positionLink.linkUrl }"><a href="${positionLink.linkUrl }">全部<i class="all-link"></i></a></c:if>
		            			</h3>
		            			<ul>
	            			</c:if>
		            		<c:if test="${((status.index>0 && status.index<9)||(status.index>9 && status.index<14)) && not empty positionLink.linkTitle}"><a href="${positionLink.linkUrl }"><li>${positionLink.linkTitle }</li></a></c:if>
	            		</c:forEach>
            			</ul><div class="clear"></div>
	            	</div>
	            	<div class="inner-center">
	            	<c:forEach items="${areaPosition.advResourcePubRecordList }" var="advResource" varStatus="restatus">
	            		<c:if test="${restatus.index>0 }">
	            			<c:if test="${(restatus.index % 2)==1 }">
	            				<ul>
	            					<li>
			           					<ul class="article-list-each">
						                    <li class="pic">
						                    <c:if test="${advResource.resource.resourceType == 1 }">
						                    	<a href="${ctx }/zhaopin_${advResource.resource.resourceValue }.html" target="_blank">
						                    </c:if>
						                    <c:if test="${advResource.resource.resourceType == 4 }">
						                    	<a href="${advResource.resource.linkUrl }" target="_blank">
						                    </c:if>
						                    <img src="${advResource.resource.thumbImageUrl }" alt="${advResource.resource.title }">
						                    </a></li>
						                    <li class="title"><h3>						                    
						                    <c:if test="${advResource.resource.resourceType == 1 }">
						                    	<a href="${ctx }/zhaopin_${advResource.resource.resourceValue }.html" target="_blank">
						                    </c:if>
						                    <c:if test="${advResource.resource.resourceType == 4 }">
						                    	<a href="${advResource.resource.linkUrl }" target="_blank">
						                    </c:if>
											${advResource.resource.title }</a></h3></li>
						                    <li class="num">${advResource.resource.description }</li>
						                    <li class="summary"><span class="label">${advResource.resource.label1 }</span><span class="orange">${advResource.resource.subtitle }</span></li>
					                	</ul>
	            					</li>
	            			</c:if>
	            			<c:if test="${(restatus.index % 2)==0 }">
	            					<li>
			           					<ul class="article-list-each">
						                    <li class="pic">						                    
						                    <c:if test="${advResource.resource.resourceType == 1 }">
						                    	<a href="${ctx }/zhaopin_${advResource.resource.resourceValue }.html" target="_blank">
						                    </c:if>
						                    <c:if test="${advResource.resource.resourceType == 4 }">
						                    	<a href="${advResource.resource.linkUrl }" target="_blank">
						                    </c:if>
											<img src="${advResource.resource.thumbImageUrl }" alt="${advResource.resource.title }"></a></li>
						                    <li class="title"><h3>						                    
						                    <c:if test="${advResource.resource.resourceType == 1 }">
						                    	<a href="${ctx }/zhaopin_${advResource.resource.resourceValue }.html" target="_blank">
						                    </c:if>
						                    <c:if test="${advResource.resource.resourceType == 4 }">
						                    	<a href="${advResource.resource.linkUrl }" target="_blank">
						                    </c:if>
						                    ${advResource.resource.title }</a></h3></li>
						                    <li class="num">${advResource.resource.description }</li>
						                    <li class="summary"><span class="label">${advResource.resource.label1 }</span><span class="orange">${advResource.resource.subtitle }</span></li>
					                	</ul>
	            					</li>
	            				</ul>
	            				<div class="clear"></div>
	            			</c:if>
	            		</c:if>
	            		</c:forEach>
	            	</div>
	                <div class="inner-right fr">
	                	<c:forEach items="${areaPosition.advResourcePubRecordList }" var="advResource" varStatus="advstatus">
	                	<c:if test="${advstatus.index==0 }">
		            	    <ul class="job-rec-each">
			                	<li>
			                	<c:if test="${advResource.resource.resourceType == 1 }">
			                    	<a href="${ctx }/zhaopin_${advResource.resource.resourceValue }.html" target="_blank">
			                    </c:if>
			                    <c:if test="${advResource.resource.resourceType == 4 }">
			                    	<a href="${advResource.resource.linkUrl }" target="_blank">
			                    </c:if>
						        <img src="${advResource.resource.thumbImageUrl }" alt="${advResource.resource.title }"></a></li>
			                    <li class="title">
			                    <c:if test="${advResource.resource.resourceType == 1 }">
			                    	<a href="${ctx }/zhaopin_${advResource.resource.resourceValue }.html" target="_blank">
			                    </c:if>
			                    <c:if test="${advResource.resource.resourceType == 4 }">
			                    	<a href="${advResource.resource.linkUrl }" target="_blank">
			                    </c:if>
			                    ${advResource.resource.title }</a></li>
			                    <li class="info"><span class="salary">${advResource.resource.subtitle }</span></li>
			                    <c:if test="${advResource.resource.resourceType == 1 }">
			                    	<a href="${ctx }/zhaopin_${advResource.resource.resourceValue }.html" target="_blank">
			                    </c:if>
			                    <c:if test="${advResource.resource.resourceType == 4 }">
			                    	<a href="${advResource.resource.linkUrl }" target="_blank">
			                    </c:if>
			                    <li class="rec-signup">${advResource.resource.description }</li></a>
			                </ul>
		                </c:if>
		                </c:forEach>
		            </div>
                    <div class="clear"></div>
                </div>
            </div>
            </c:forEach>
               <div class="fam-ent">
                <h2 class="col-title" style="color: #80b4de;"><i style="background:url(${ctx}/static/images/v3/title-name5.png)"></i>打工攻略</h2>
                <div class="ent-inner" style="border-top-color: #80b4de;">   
                    <div class="inner-left">
                        <div class="">
                            <div class="rel-news strategy mt10">
                                <ul class="bd">
                                	<c:forEach items="${helps}" var="help">
                                    	<li><span class="dot">&middot;</span><a href="http://www.youlanw.com/xl_${help.id}.html" target="_blank" title="${help.title}" class="job-name">${help.title}</a></li>
                                	</c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="inner-center">
                   <c:forEach items="${centerArticle}" var="centerArticle" varStatus="status">
	                   <c:if test="${status.index>0&&status.index%2==1 }">
	                    <ul>
                            <li>
                                <ul class="article-list-each">
                                    <li class="pic"><a href="http://www.youlanw.com/xl_${centerArticle.id}.html" target="_blank"><img src="${cdc:getImagePath720(centerArticle.thumbnialImage.imgpath)}" alt="${centerArticle.title}"></a></li>
                                    <li class="title"><h3><a href="http://www.youlanw.com/xl_${centerArticle.id}.html" target="_blank">${centerArticle.title}</a></h3></li>
                                    <li class="info-sty"><a href="http://www.youlanw.com/xl_${centerArticle.id}.html" target="_blank">${centerArticle.contentPreview}</a></li>
                                </ul>
                            </li>
	                   </c:if> 
                       <c:if test="${status.index>0&&status.index%2==0 }">
                            <li>
                                <ul class="article-list-each">
                                     <li class="pic"><a href="http://www.youlanw.com/xl_${centerArticle.id}.html" target="_blank"><img src="${cdc:getImagePath720(centerArticle.thumbnialImage.imgpath)}" alt="${centerArticle.title}"></a></li>
                                    <li class="title"><h3><a href="http://www.youlanw.com/xl_${centerArticle.id}.html" target="_blank">${centerArticle.title}</a></h3></li>
                                    <li class="info-sty"><a href="http://www.youlanw.com/xl_${centerArticle.id}.html" target="_blank">${centerArticle.contentPreview}</a></li>
                                </ul>
                            </li>
                        </ul>
                        <div class="clear"></div>
                        </c:if>
                        </c:forEach>
                    </div>
                    <div class="inner-right fr">
                     <c:forEach items="${centerArticle}" var="centerArticle" varStatus="status">
	                   <c:if test="${status.index==0 }">
                        <ul class="job-rec-each">
                            <li><a href="http://www.youlanw.com/xl_${centerArticle.id}.html" target="_blank"><img src="${cdc:getImagePath720(centerArticle.thumbnialImage.imgpath)}" alt="${centerArticle.title}"></a></li>
                            <li class="title-1 title-h"><a href="http://www.youlanw.com/xl_${centerArticle.id}.html" target="_blank">${centerArticle.title}</a></li>
                            <a href="http://www.youlanw.com/xl_${centerArticle.id}.html" target="_blank"><li class="rec-signup-1">${centerArticle.contentPreview}</li></a>
                        </ul>
                        </c:if>
                    </c:forEach>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <!--/名企专区-->
        </div>
    </div>
    <jsp:include page="/WEB-INF/layouts/index-footer.jsp" />
</div>    
<script src="${ctx}/static/js/common.js?v=20160701"></script>
<script src="${ctx}/static/js/layer.min.js"></script>
<script src="${ctx}/static/js/cookie.js"></script>
<script src="${ctx}/static/js/youlanw.pc.js?v=20160701"></script>
<script src="http://www.youlanw.com/static/js/jquery.SuperSlide.2.1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.range.js"></script>
<script type="text/javascript" src="${ctx}/static/js/home_20160701.js"></script>
<script>
  for(var i=0,_length = $(".article-list-each .info-sty a").length;i<_length;i++){
       var _text = $(".fam-ent .ent-inner .inner-center > ul > li").eq(i).find(".info-sty a").text();
       var _text_length = $(".fam-ent .ent-inner .inner-center > ul > li").eq(i).find(".info-sty a").text().length;
       if(_text_length>34){  //文本长度大于34则省略，小于则不出现省略号
           _text = _text.substr(0,33) + '...' ;
           $(".fam-ent .ent-inner .inner-center > ul > li").eq(i).find(".info-sty a").html(_text);
       }
  }
  var _text1 = $(".rec-signup-1").text(); 
  var _text_length1 = $(".rec-signup-1").text().length; 
  if($(".title-h").height()>25){
	  if(_text_length1>41){  //文本长度大于41则省略，小于则不出现省略号
	  	_text1 = _text1.substr(0,40) + '...' ;
	      $(".rec-signup-1").html(_text1);
	  }
  }else{
	  if(_text_length1>66){  //文本长度大于66则省略，小于则不出现省略号
	  	_text1 = _text1.substr(0,65) + '...' ;
	      $(".rec-signup-1").html(_text1);
	  }
  }
</script>
</body>
</html>
