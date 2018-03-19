<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%--<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />--%>
 <c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" /> 
<c:set var="ctxBase" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<%--<c:set var="ctx" value="http://www.youlanw.com"></c:set>  --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:if test="${not empty sessionScope.session_key_branch_name}">
	<c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="Qe3ngwHAwx" />
<meta name="360-site-verification" content="f6177ad94fa56fce45694d98b98737e8" />
<meta name="sogou_site_verification" content="RJNAqlHvBE"/>
<title>【${cityName }优蓝网】${cityName }名企招聘_${cityName }最新招聘信息_${cityName }找工作</title>
<meta name="description" content="${cityName }优蓝网帮助在${cityName }打工的求职者免费快速找到好工作.每天提供${cityName }名企最新招聘信息,普工,销售,客服,快递员等岗位任您选.想去名企,拿高工资,享高福利,就到${cityName }优蓝网." />
<meta name="keywords" content="${cityName }名企招聘，${cityName }最新招聘信息，${cityName }找工作" />
<link rel="stylesheet" href="${ctx}/static/css/common.css?v=20150430" />
<link rel="icon" href="http://www.youlanw.com/static/images/favicon.ico" mce_href="http://www.youlanw.com/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="http://www.youlanw.com/static/images/favicon.ico" mce_href="http://www.youlanw.com/static/images/favicon.ico" type="image/x-icon">
<style>
.footer-menu-list .qq{background-position:0px -1497px;padding:10px 0 0 65px;height:50px;margin-top:30px;width:165px;color:#2bb7ab;font-size:13px;position:relative;margin-left:5px}
.footer-menu-list .qq p{position:relative;top:-5px}
.footer-menu-list .qq span{font-size:22px;position:relative;top:-5px}
.footer-menu-list li{width:185px}
</style>
<script src="http://www.youlanw.com/static/js/jquery-1.11.1.min.js"></script>
<script src="http://www.youlanw.com/static/js/common.js"></script>
<script src="http://www.youlanw.com/static/js/layer.min.js"></script>
<script src="http://www.youlanw.com/static/js/jquery.SuperSlide.2.1.js"></script>
<script src="http://www.youlanw.com/static/js/cookie.js"></script>
<script>
	if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent) || /(Android)/i.test(navigator.userAgent)) {
		var webPrefixStr = '${fn:substring(pageContext.request.serverName,0,fn:indexOf(pageContext.request.serverName,".youlanw.com"))}';
		if(webPrefixStr == 'www' || webPrefixStr == ''){
			window.location.href = "http://m.youlanw.com/?jumper=" + window.location.pathname;
		}else{
			window.location.href = "http://m.youlanw.com/"+webPrefixStr+"/?jumper=" + window.location.pathname;
		}
	}
		
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?83a185fc9f52ffcad1bcb9e4d889301b";
	  var s = document.getElementsByTagName("script")[0];
	  s.parentNode.insertBefore(hm, s);
	})();
	</script>
<script src='//kefu.easemob.com/webim/easemob.js?tenantId=1780&hide=true' async='async'></script>
</head>
<body>
	<!--page-wrapper-->
	<div class="page-wrapper">
	<c:if test="${sessionScope.session_key_branch_id == 9}">
		<!--广告位-->
	    <div><a href="http://www.cqrc.net/jyfw/index.aspx" target="_blank" rel="nofollow"><img src="${ctxBase }/static/images/datas/ads/job-fair-banner.jpg" alt="" style="width:100%;min-width:1200px;"/></a></div>
	    <!--/广告位-->
    </c:if>
<!--top-menu-->
	<div class="top-menu">
		<div class="box-out" style="width:1200px;">
			<div class="fl">
                <p class="fl">您好，欢迎来到优蓝网！</p>
				<c:if test="${empty sessionScope.session_user_key}">
					<a href="${ctx}/login" rel="nofollow">登录</a>
					<label>|</label>
					<a href="${ctx}/register" rel="nofollow">注册</a>
				</c:if>
				<c:if test="${not empty sessionScope.session_user_key}">
                <!---->
                <div class="with-sub">
                	<a href="javascript:void(0);" rel="nofollow" class="sub user">${sessionScope.session_user_key.username }<i></i></a>
                    <div class="sub-menu-out">
                    	<div class="sub-menu">
                            <a href="${ctx}/my" rel="nofollow">修改资料</a>
                            <%-- <a href="${ctx}/my/changePassword" rel="nofollow">修改密码</a> --%>
                            <a href="${ctx}/logout" rel="nofollow">安全退出</a>
                        </div>
                    </div>
                </div>
                </c:if>
                <!--/-->
                <a href="${ctx }/about/mobile.html" class="icon-common icon-phone" rel="nofollow">手机优蓝</a>
            </div>
			<div class="fr">
				 <!--我的优蓝-->
                <div class="with-sub">
                	<a href="javascript:void(0);" rel="nofollow" class="sub">我的优蓝<i></i></a>
                    <div class="sub-menu-out">
                    	<div class="sub-menu">
                            <a href="${ctx}/my" rel="nofollow">微简历</a>
                            <a href="${ctx}/my/signupRecord" rel="nofollow">报名记录</a>
                        </div>
                    </div>
                </div>
                <!--/我的优蓝-->
                 <!--浏览记录-->
                <div class="with-sub">
                	<a href="javascript:void(0);" rel="nofollow" class="sub">浏览记录<i></i></a>
                    <div class="sub-menu-out">
                    	<div class="sub-menu record">
                            <div class="job-list2" id="top_recent_browse_list">
								<p class="no-result">
									<span><i></i></span>暂无浏览记录
								</p>
                    		</div>
                        </div>
                    </div>
                </div>
                <!--/浏览记录-->
                <!--企业合作-->
                <div class="with-sub">
                	<a href="${ctx }/enterprise/index" rel="nofollow" class="sub">企业合作</a>
                </div>
                <!--/企业合作-->
                <!--网站导航-->
                <div class="with-sub">
                	<a href="javascript:void(0);" rel="nofollow" class="sub">网站导航<i></i></a>
                    <div class="sub-menu-out">
                    	<div class="sub-menu nav">
                            <ul>
                            	<li>
                                	<strong>优蓝服务</strong>
                                    <a href="${ctx}/zhaopin/">找工作</a>
                                    <a href="${ctx}/qiye/">找企业</a>
                                    <a href="${ctx }/mingqi/">名企推荐</a>
                                    <a href="http://www.youlanw.com/zone/">优蓝家园</a>
                                </li>
                                <li>
                                	<strong>热点活动</strong>
                                    <a href="${ctx }/trip10000">就业万里行</a>
                                    <%-- <a href="${ctx }/act/gaotie2015.html">高铁乘务招聘</a> --%>
                                </li>
                                <li class="bd0">
                                	<strong>更多</strong>
                                    <a href="${ctx}/about/index.html" rel="nofollow">关于优蓝</a>
                                    <a href="${ctx }/about/wechat.html" rel="nofollow">关注微信</a>
                                    <a href="http://weibo.com/youlanwHR" rel="nofollow">官方微博</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--/网站导航-->
                
			</div>
		</div>
	</div>
	<!--/top-menu--->
		<!--logo search QR-->
		<div class="logo-search full-width bg-white">
			<div class="w1200 relative">
				<a href="${ctx}/">
					<img src="${ctx}/static/images/logo.png" class="logo" alt="优蓝网" />
				</a>
				<a href="${ctx}/about/chooseCity" class="c-city">
					<p id="c_branch_name">${sessionScope.session_key_branch_name}</p>
					<i class="arrow-d-2"></i>
				</a>
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
						<input type="text" class="search-input tip-text" id="input_search_LIKE_jobname" name="search_LIKE_searchName" placeholder="请输入职位信息" value="${wd}" /> <input type="button" value="搜索" class="btn search-btn search-enter" />
					</div>
				</div>
			<%-- 	<div class="entry">
					<a href="${ctx }/login/enterpriseUser" target="_blank">企业入口</a>
					<!-- <a href="" class="icon2">学校入口</a> -->
				</div> --%>
				 <!--广告位-->
				 <c:forEach items="${topAds}" var="topAd" varStatus="status">
						<c:if test="${status.index==0 }">
							<div class="ad"><a href="${topAd.link }"><img src="${cdc:getImagePathO(topAd.img) }" alt="${topAd.title }" /></a></div>
						</c:if>
					</c:forEach>
           		 
           		 <!--/广告位-->
				<div class="clear"></div>
			</div>
		</div>
		<!--/logo search QR-->
		<!--main-menu-->
		<div class="main-menu-wrapper">
			<div class="w1200 main-menu">
				<div class="list">
					<a href="${ctxBase}/" class="current">首页</a>
					<a href="${ctx}/zhaopin/">我要找工作</a>
					<a href="${ctx}/qiye/">我要进名企</a>
					<a href="http://www.youlanw.com/zone/">优蓝家园</a>
					<a href="${ctx }/about/mobile.html" rel="nofollow">手机优蓝</a>
					<a href="http://b.youlanw.com/" target="_blank">企业合作</a>
					<a href="http://daili.youlanw.com/" target="_blank">加盟合作</a>
                	<span class="tel">4008-777-816</span>
					<%-- <a href="${ctx }/actWanlixingIndex">就业万里行</a> --%>
				</div>
			</div>
		</div>
		<!--/main-menu-->
		<!--轮播图 快速找工作-->
		<div class="full-slide full-slideNew">
			<div class="w1200 relative">
				<!--轮播图-->
				<div class="hd">
					<ul>
						<c:forEach items="${shufflingJobs}" var="recommend" varStatus="status">
							<li></li>
						</c:forEach>
					</ul>
				</div>
				<div class="bd">
				<a class="prev" href="javascript:void(0)"></a>
				<a class="next" href="javascript:void(0)"></a>
					<ul>
						<c:forEach items="${shufflingJobs}" var="recommend" varStatus="status">
							<c:set var="imgUrl" value="" />
							<c:if test="${not empty recommend.img}">
								<c:set var="imgUrl" value="url('${cdc:getImagePathO(recommend.img)}')" />
							</c:if>
							<li style="background:${recommend.style} ${imgUrl} center 0 no-repeat;">
								<a target="_blank" href="${recommend.link}"></a>
								<div class="slide-textbg"></div>
								<span class="slide-text">${recommend.title}</span>
							</li>
						</c:forEach>
					</ul>
				</div>
				<!--/轮播图-->
				<!--30s找工作-->
				<div class="box-r quick-job2" style="position: absolute; z-index: 10; left: 50%; top: 0; margin-left: 320px;">
					<ul class="des">
						<li>
							<a href="${ctx }/qiye/">
								<i class="icon2"></i>
								<p class="text1">${companyCount}家</p>
								<p>优质名企上班，有面子更放心</p>
								<span class="c2">知名企业</span>
							</a>
						</li>
						<li>
							<a href="${ctx }/zhaopin/">
								<i></i>
								<p class="text1">${jobCount}个</p>
								<p>打工上优蓝，找工作不难</p>
								<span class="c1">优质岗位</span>
							</a>
						</li>
						<li>
							<a href="${ctx }/zone/">
								<i class="icon3"></i>
								<p class="text1">${applyCount}位</p>
								<p>在家靠父母，出门靠朋友</p>
								<span class="c3">优蓝网友</span>
							</a>
						</li>
					</ul>
					<a href="${ctx}/signup/fastSignUp" class="btn btn-signup2">
						<i></i>30秒找工作
					</a>
					<p class="text-center c999 mt10">
						<label class="red">24小时内</label>电话回复率100%
					</p>
				</div>
				<!--/30s找工作-->
			</div>
		</div>
		<!--/轮播图 快速找工作-->

		<!--名企专区 大家都想进-->
		<div class="fam-wrapper">
			<div class="w1200">
				<!--名企专区-->
				<div class="fam-ent fl">
					<h2 class="col-title">
						名企专区
						<a href="${ctx }/mingqi/" target="_blank" class="more-g-3">更多&gt;</a>
					</h2>
					<ul>
					<c:forEach items="${recommendCompanyList}" var="recommendCompany" varStatus="status">
						<li>
							<input type="hidden" value="${recommendCompany.companyId}" id="input_companyid" />
							<table>
								<tr>
									<td><a target="_blank" href="${ctx}/qiye_${recommendCompany.companyId}.html">
											<img src="${cdc:getImagePath320(recommendCompany.companyLogo) }" alt="${recommendCompany.companyName }" />
										</a></td>
								</tr>
							</table>
							<span class="company"></span>
							<a target="_blank" href="${ctx}/qiye_${recommendCompany.companyId}.html">
								<span class="fam-ent-info"> <span class="opacity-bg"></span>
									<h3 class="text1">${recommendCompany.abbreviation }</h3>
									<p class="text2"><c:if test="${empty recommendCompany.pointOfPraise}">0</c:if>${recommendCompany.pointOfPraise }人想进</p>
								</span>
							</a>
						</li>
					</c:forEach>
					</ul>
					<div class="fam-ent-job">
						<div class="list" id="jobList"></div>
					</div>
					<div class="clear"></div>
				</div>
				<!--/名企专区-->
				<!--大家都想进-->
				<div class="rel-job fr">
					<h2 class="col-title2">大家都想进</h2>
					<div class="txtMarquee-top">
						<div class="hd">
							<a class="next"></a>
							<a class="prev"></a>
						</div>
						<ul class="list bd">
							<c:forEach items="${companyList}" var="company" varStatus="status">
								<li>
									<span class="time">${cdc:getTime(status.index) }</span>${cdc:getName() }想进
									<a target="_blank" href="${ctx}/qiye_${company.id}.html" class="com">${company.name }</a>
								</li>
							</c:forEach>
							<!-- <div class="remove-border"></div> -->
						</ul>
					</div>
				</div>
				<!--/大家都想进-->
				<div class="clear"></div>
			</div>
		</div>
		<!--/名企专区 大家都想进-->
		<!--优蓝精选岗位 ...-->
		<div class="w1200 mt15">
			<!--left-->
			<div class="box-l-big-wrapper">
				<div class="box-l-big">
					<!--优蓝精选岗位-->
					<h2 class="col-title fl">优蓝精选岗位</h2>
					<!-- <span class="fr r-key"><a href="${ctx}/job?search_LIKE_jobType=汽车工" title="汽车工">汽车工</a><label>|</label> <a href="${ctx}/job?search_LIKE_jobType=普工" title="普工">普工</a><label>|</label> <a href="${ctx}/job?search_LIKE_jobType=电焊工" title="电焊工">电焊工</a><label>|</label> <a href="${ctx}/job?search_LIKE_jobType=服务员" title="服务员">服务员</a><label>|</label> <a href="${ctx}/job?search_LIKE_jobType=乘务员" title="乘务员">乘务员</a></span> -->
					<div class="clear"></div>
					<!---->
					<div class="job-rec" id="div_hot_job">
						<c:forEach items="${recommendJobListYLJX}" var="recommendjob">
							<ul class="job-rec-each">
								<li>
									<a target="_blank" href="${ctx}/zhaopin_${recommendjob.jobId}.html">
										<c:choose>
											<c:when test="${empty recommendjob.imgPath}">
												<img src="${cdc:getImageByJobType320(recommendjob.jobType)}" alt="${recommendjob.jobTitle }" />
											</c:when>
											<c:otherwise>
												<img src="${cdc:getImagePath320(recommendjob.imgPath)}" alt="${recommendjob.jobTitle }" />
											</c:otherwise>
										</c:choose>
										
									</a>
								</li>
								<li class="title">
									<a target="_blank" href="${ctx}/zhaopin_${recommendjob.jobId}.html">${recommendjob.jobTitle }</a>
								</li>
								<li class="company">
									<a target="_blank" href="${ctx}/qiye_${recommendjob.companyId}.html">${recommendjob.companyName }</a>
								</li>
								<li class="info">
									<span class="num"><c:if test="${empty recommendjob.applyCount }">0</c:if><c:if test="${not empty recommendjob.applyCount }">${recommendjob.applyCount}</c:if>人报名</span>
									<c:if test="${empty recommendjob.salaryTo }">
										<span class="salary">￥${ recommendjob.salaryFrom }元/月起</span>
									</c:if>
									<c:if test="${not empty recommendjob.salaryTo }">
										<span class="salary">￥${ recommendjob.salaryFrom }-${ recommendjob.salaryTo }元/月</span>
									</c:if>

								</li>
								<a target="_blank" href="${ctx}/zhaopin_${recommendjob.jobId}.html">
								<li class="rec-signup">
									我要报名
								</li>
								</a>
								<li class="city">${recommendjob.jobCityName }</li>	
							</ul>
						</c:forEach>
						<div class="clear"></div>
					</div>
					<!--/-->
					<!---->
					<div class="job-rec2">
						<c:forEach items="${jobListYLJXbottom}" var="yljxBottomJob">
							<ul class="job-rec2-each">
								<li class="logo">
										<tr>
											<td><a target="_blank" href="${ctx}/zhaopin_${yljxBottomJob.id}.html">
													<c:choose>
														<c:when test="${empty yljxBottomJob.companyLogo}">
					                     				 	<div class="index-exam">${yljxBottomJob.companyAbbreviation }</div>
														</c:when>
														<c:otherwise>
															<img src="${cdc:getImagePath320(yljxBottomJob.companyLogo)}" alt="${yljxBottomJob.jobTitle }" />
														</c:otherwise>
													</c:choose>
												</a></td>
										</tr>
								</li>
								<li class="title">
									<a target="_blank" href="${ctx}/zhaopin_${yljxBottomJob.id}.html">${yljxBottomJob.jobTitle } </a>
								</li>
								<c:if test="${empty yljxBottomJob.salaryTo }">
									<li class="pay">￥${yljxBottomJob.salaryFrom }元/月起</li>
								</c:if>
								<c:if test="${not empty yljxBottomJob.salaryTo }">
									<li class="pay">￥${ yljxBottomJob.salaryFrom }-${ yljxBottomJob.salaryTo }元/月</li>
								</c:if>
							</ul>
						</c:forEach>
						<div class="clear"></div>
					</div>
					<!--/-->
					<!--/优蓝精选岗位-->
				</div>
			</div>
			<!--/left-->
			<!--right-->
			<div class="box-r-wrapper">
				<!---->
				<div class="job-box box-r">
					<h2 class="h-g-2">普工/技工</h2>
					<div class="list">
						<a href="${ctx}/job?search_LIKE_jobType=普工" title="普工">普工</a>
						<a href="${ctx}/job?search_LIKE_jobType=车床工" title="车床工">车床工</a>
						<a href="${ctx}/job?search_LIKE_jobType=电焊工" title="电焊工">电焊工</a>
						<a href="${ctx}/job?search_LIKE_jobType=建筑工" title="建筑工">建筑工</a>
						<a href="${ctx}/job?search_LIKE_jobType=印刷工" title="印刷工">印刷工</a>
						<a href="${ctx}/job?search_LIKE_jobType=纺织工" title="纺织工">纺织工</a>
					</div>
				</div>
				<!--/-->
				<!---->
				<div class="job-box box-r bdt0">
					<h2 class="h-g-2 icon2">服务人员</h2>
					<div class="list">
						<a href="${ctx}/job?search_LIKE_jobType=客服" title="客服">客服</a>
						<a href="${ctx}/job?search_LIKE_jobType=服务员" title="服务员">服务员</a>
						<a href="${ctx}/job?search_LIKE_jobType=营业员" title="营业员">营业员</a>
						<a href="${ctx}/job?search_LIKE_jobType=行政后勤" title="行政后勤">行政后勤</a>
						<a href="${ctx}/job?search_LIKE_jobType=后厨" title="后厨">后厨</a>
						<a href="${ctx}/job?search_LIKE_jobType=家政保洁" title="家政保洁">家政保洁</a>
					</div>
				</div>
				<!--/-->
				<!---->
				<div class="job-box box-r bdt0">
					<h2 class="h-g-2 icon3">其他</h2>
					<div class="list">
						<a href="${ctx}/job?search_LIKE_jobType=销售" title="销售">销售</a>
						<a href="${ctx}/job?search_LIKE_jobType=物流仓储" title="物流仓储">物流仓储</a>
						<a href="${ctx}/job?search_LIKE_jobType=司机" title="司机">司机</a>
						<a href="${ctx}/job?search_LIKE_jobType=机修汽修" title="机修汽修">机修汽修</a>
						<a href="${ctx}/job?search_LIKE_jobType=财务会计" title="财务会计">财务会计</a>
						<a href="${ctx}/job?search_LIKE_jobType=贸易采购" title="贸易采购">贸易采购</a>
					</div>
				</div>
				<!--/-->
				<!--福利标签-->
				<div class="box-r welfare-tags mt20" style="height: 163px;">
					<ul>
						<%-- 	<c:forEach items="${welfareTags }" var="tag">
							<li>
								<a href="javascript:void(0)" title="${tag.tagName }">${tag.tagName }</a>
							</li>
						</c:forEach> --%>
						<li>
							<a href="${ctx}/job?search_LIKE_jobLabel=五险一金" title="五险一金">五险一金</a>
						</li>
						<li>
							<a href="${ctx}/job?search_LIKE_jobLabel=缴纳社保" title="缴纳社保">缴纳社保</a>
						</li>
						<li>
							<a href="${ctx}/job?search_LIKE_jobLabel=包吃包住" title="包吃包住">包吃包住</a>
						</li>
						<li>
							<a href="${ctx}/job?search_LIKE_jobLabel=坐着上班" title="坐着上班">坐着上班</a>
						</li>
						<li>
							<a href="${ctx}/job?search_LIKE_jobLabel=美女多" title="美女多">美女多</a>
						</li>
						<li>
							<a href="${ctx}/job?search_LIKE_jobLabel=不穿无尘衣" title="不穿无尘衣">不穿无尘衣</a>
						</li>
						<li>
							<a href="${ctx}/job?search_LIKE_jobLabel=长白班" title="长白班">长白班</a>
						</li>
						<li>
							<a href="${ctx}/job?search_LIKE_jobLabel=帅哥多" title="帅哥多">帅哥多</a>
						</li>
					</ul>
				</div>
				<!--/福利标签-->
				<!--广告位-->
				<div class="ad-r-2 fl mt15">
					<c:forEach items="${activities}" var="activitie" varStatus="status">
						<c:if test="${status.index==0 }">
							<a target="_blank" href="${activitie.link }">
								<img src="${cdc:getImagePathO(activitie.img) }" alt="${activitie.title }" />
							</a>
						</c:if>
					</c:forEach>
				</div>
				<!--/广告位-->
			</div>
			<!--/right-->
			<div></div>
			<div class="clear"></div>
		</div>
		<!--/优蓝精选岗位 ...-->
		<!--广告位-->
		<div class="ad-g w1200 mt10">
			<c:forEach items="${centerRecommendAd}" var="centerAd" varStatus="status">
				<c:if test="${status.index==0 }">
					<a target="_blank" href="${centerAd.link }">
						<img src="${cdc:getImagePathO(centerAd.img) }" alt="${centerAd.title }" />
					</a>
				</c:if>
			</c:forEach>
		</div>
		<!--/广告位-->

		<!--待遇优厚岗位 ...-->
		<div class="w1200 mt15">
			<!--left-->
			<div class="box-l-big-wrapper">
				<!--小蓝有约-->
				<div class="box-l-big xiaolan">
					<!--图集-->
					<div class="pic-box">
						<span class="pageState"></span> <span class="prev"></span> <span class="next"></span>
						<div class="pic-box-content">
							<ul>
								<c:forEach items="${leftArticle}" var="lArticle" varStatus="status">
									<li>
										<a target="_blank" href="http://www.youlanw.com/xl_${lArticle.id}.html" target="_blank">
											<img src="${cdc:getImagePath720(lArticle.thumbnialImage.imgpath)}" alt="企业实拍图" />
										</a>
										<p class="pic-title">${lArticle.title}</p>
										<p class="opacity-bg"></p>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<!--/图集-->
					<!--文章-->
					<div class="articles fl">
						<c:forEach items="${centerArticle}" var="cArticle">
							<div class="each">
								<a target="_blank" href="http://www.youlanw.com/xl_${cArticle.id}.html" target="_blank">
									<h3 class="title1">${cArticle.title}</h3>
									<div class="des">${cArticle.contentPreview}</div>
									<img src="${cdc:getImagePath320(cArticle.thumbnialImage.imgpath)}" />
								</a>
							</div>
						</c:forEach>
						<div class="remove-border"></div>
					</div>
					<!--/文章-->
				</div>
				<!--/小蓝有约-->
				<div class="box-l-big mt30">
					<!--待遇优厚-->
					<h2 class="col-title fl">待遇优厚</h2>
					<!-- <span class="fr r-key"><a href="javascript:void(0)" title="汽车工">汽车工</a><label>|</label> <a href="javascript:void(0)" title="普工">普工</a><label>|</label> <a href="javascript:void(0)" title="电焊工">电焊工</a><label>|</label> <a href="javascript:void(0)" title="服务员">服务员</a><label>|</label> <a href="javascript:void(0)" title="乘务员">乘务员</a></span> -->
					<div class="clear"></div>
					<!---->
					<div class="job-rec">
						<c:forEach items="${jobListDYYH}" var="dyyhJob">
							<ul class="job-rec-each">
								<li>
									<a target="_blank" href="${ctx}/zhaopin_${dyyhJob.id}.html">
										<c:choose>
											<c:when test="${empty dyyhJob.imgPath}">
												<img src="${cdc:getImageByJobType320(dyyhJob.jobType)}" alt="${dyyhJob.jobTitle}" />
											</c:when>
											<c:otherwise>
												<img src="${cdc:getImagePath320(dyyhJob.imgPath)}" alt="${dyyhJob.jobTitle}" />
											</c:otherwise>
										</c:choose>
									</a>
								</li>
								<li class="title">
									<a target="_blank"  href="${ctx}/zhaopin_${dyyhJob.id}.html">${dyyhJob.jobTitle }</a>
								</li>
								<li class="company">
									<a target="_blank" href="${ctx}/qiye_${dyyhJob.companyId}.html">${dyyhJob.companyName }</a>
								</li>
								<li class="info">
									<span class="num"><c:if test="${empty dyyhJob.applyCount }">0</c:if><c:if test="${not empty dyyhJob.applyCount }">${dyyhJob.applyCount}</c:if>人报名</span>
									<c:if test="${empty dyyhJob.salaryTo }">
										<span class="salary">￥${dyyhJob.salaryFrom }元/月起</span>
									</c:if>
									<c:if test="${not empty dyyhJob.salaryTo }">
										<span class="salary">￥${ dyyhJob.salaryFrom }-${ dyyhJob.salaryTo }元/月</span>
									</c:if>
								</li>
								<a target="_blank" href="${ctx}/zhaopin_${dyyhJob.id}.html">
								<li class="rec-signup">
									我要报名
								</li>
								</a>
								<li class="city">${dyyhJob.jobCityName }</li>	
							</ul>
						</c:forEach>
						<div class="clear"></div>
					</div>
					<!--/-->
					<!---->
					<div class="job-rec2">
						<c:forEach items="${jobListDYYHbottom}" var="dyyhBottomJob">
							<ul class="job-rec2-each">
								<li class="logo">
									<a target="_blank" href="${ctx}/zhaopin_${dyyhBottomJob.id}.html">
										<c:choose>
											<c:when test="${empty dyyhBottomJob.companyLogo}">
												<div class="index-exam">${dyyhBottomJob.companyAbbreviation }</div>
											</c:when>
											<c:otherwise>
												<img src="${cdc:getImagePath320(dyyhBottomJob.companyLogo)}" alt="${dyyhBottomJob.jobTitle }" />
											</c:otherwise>
										</c:choose>
									</a>
								</li>
								<li class="title">
									<a target="_blank" href="${ctx}/zhaopin_${dyyhBottomJob.id}.html">${dyyhBottomJob.jobTitle } </a>
								</li>
								<c:if test="${empty dyyhBottomJob.salaryTo }">
									<li class="pay">￥${dyyhBottomJob.salaryFrom }元/月起</li>
								</c:if>
								<c:if test="${not empty dyyhBottomJob.salaryTo }">
									<li class="pay">￥${ dyyhBottomJob.salaryFrom }-${ dyyhBottomJob.salaryTo }元/月</li>
								</c:if>
							</ul>
						</c:forEach>
						<div class="clear"></div>
					</div>
					<!--/-->
					<div class="clear"></div>
					<a target="_blank" href="${ctx }/zhaopin/" class="job-more">更多机会，请点击查看</a>
					<!--/待遇优厚-->
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
							<p>m.youlanw.com</p>
						</div>
						<img src="http://www.youlanw.com/static/images/QR2.jpg" alt="" />
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
						<img src="http://www.youlanw.com/static/images/QR.jpg" alt="" />
					</div>
				</div>
				<!--/优蓝网微信版-->
				<!--广告位-->
				<div class="ad-r-2">
					<c:forEach items="${activities}" var="activitie" varStatus="status">
						<c:if test="${status.index==1 }">
							<a target="_blank" href="${activitie.link }">
								<img src="${cdc:getImagePathO(activitie.img) }" alt="${activitie.title }" />
							</a>
						</c:if>
					</c:forEach>
				</div>
				<!--/广告位-->

				<!--小蓝有约-->
				<div class="job-help2 box-r mt10">
					<h2 class="col-title2">
						小蓝有约
						<a target="_blank"  href="http://www.youlanw.com/s_articleType_2" class="more-g-3">更多&gt;</a>
					</h2>
					<ul>
						<c:forEach items="${xiaolan}" var="xiaolan">
							<li>
								<a target="_blank" href="http://www.youlanw.com/xl_${xiaolan.id}.html" target="_blank" title="${xiaolan.title}">${xiaolan.title}</a>
							</li>
						</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
				<!--/小蓝有约-->

				<!--广告位-->
				<div class="ad-r-2">
					<c:forEach items="${activities}" var="activitie" varStatus="status">
						<c:if test="${status.index==2 }">
							<a target="_blank" href="${activitie.link }">
								<img src="${cdc:getImagePathO(activitie.img) }" alt="${activitie.title }" />
							</a>
						</c:if>
					</c:forEach>
				</div>
				<!--/广告位-->
				<!--求职帮助-->
				<div class="job-help2 box-r mt10">
					<h2 class="col-title2">
						求职帮助
						<a target="_blank" href="http://www.youlanw.com/s_articleType_3" class="more-g-3">更多&gt;</a>
					</h2>
					<ul>
						<c:forEach items="${helps}" var="help" varStatus="status">
							<li>
								<a target="_blank" href="http://www.youlanw.com/xl_${help.id}.html" target="_blank" title="${help.title}">${help.title}</a>
							</li>
						</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
				<!--/求职帮助-->
			</div>
			<!--/right-->
			<div></div>
			<div class="clear"></div>
		</div>
		<!--/优蓝精选岗位 ...-->
	    <!--悬浮菜单-->
	    <ul class="float-menu">
	    	<li class="qq-wrapper">
	        	<a href="javascript:;" onclick="easemobIM()" tenantId=1780>
	        		<span class="des">在线咨询</span><span class="qq"></span>
	            </a>
	        </li>
	        <li class="signup-wrapper">
	        	<a href="${ctx}/signup/fastSignUp"><span class="des">快速报名</span><span class="signup"></span></a>
	        </li>
	        <li class="to-wrapper">
	        	<span class="des">返回顶部</span><span class="to"></span>
	        </li>
	    </ul>
	    <!--/悬浮菜单-->
		<!--关键词-->
		<div class="w1200 mt20">
			<ul class="tab-title">
				<li class="current">热门城市</li>
				<li>热门岗位</li>
				<li>最新岗位</li>
				<li>友情链接</li>
			</ul>
			<div class="tab-each show each-zone" id="divJobType">
				<%@include file="../template/home_footer_1.jsp" %>
			</div>
			<div class="tab-each">
				<c:forEach items="${hotJobs }" var="hotJob">
					<a href="${ctx}/zhaopin_${hotJob.id}.html">${hotJob.jobTitle }</a>
				</c:forEach>
			</div>
			<div class="tab-each">
				<c:forEach items="${newJobs }" var="newJob">
					<a href="${ctx}/zhaopin_${newJob.id}.html">${newJob.jobTitle }</a>
				</c:forEach>
			</div>
			<div class="tab-each">
				<%@include file="../template/home_footer_4.jsp" %>
			</div>
			<div class="clear"></div>
		</div>
		<!--/关键词-->
		<div class="footer-menu-wrapper home">
			<ul class="footer-menu-list w990">
				<li>
					<span class="title">求职指南</span>
					<a href="${ctx }/help/ylapplyJob.html" rel="nofollow" target="_blank">求职流程</a>
					<a href="${ctx }/help/guarantee.html" rel="nofollow" target="_blank">服务保障</a>
					<a href="${ctx }/help/consultant.html" rel="nofollow" target="_blank">打工顾问</a>
				</li>
				<li>
					<span class="title">常见问题</span>
					<a href="${ctx }/help/index.html" rel="nofollow" target="_blank">新手指南</a>
					<a href="${ctx }/help/ylaccount.html" rel="nofollow" target="_blank">优蓝账户</a>
					<!--  <a href="" rel="nofollow" target="_blank">在线咨询</a> -->
				</li>
				<li>
					<span class="title">关注我们</span>
					<a href="${ctx }/about/wechat.html" rel="nofollow">微信公众号</a>
					<a href="http://weibo.com/youlanwHR" rel="nofollow">新浪微博</a>
				</li>
				<li class="tel icon-common">
					<p>服务热线（免长话费）</p>
					<span>4008-777-816</span>
				</li>
				<a target="_blank" href="${branch.qqJoinCode }">
					<li class="qq icon-common">
		            	<p>本地求职QQ群</p>
		                <span>${branch.qqGroup }</span>
	            	</li>
	            </a>
				<div class="clear"></div>
			</ul>
		</div>
		
	 <jsp:include page="/WEB-INF/layouts/footer.jsp"></jsp:include>
	</div>
	<script src="http://www.youlanw.com/static/js/youlanw.pc.js"></script>
	<script type="text/javascript">
	$(function() {
		YL.header.init();
		YL.top.init();
		YL.index.init();
	});
	</script>
</body>
</html>
