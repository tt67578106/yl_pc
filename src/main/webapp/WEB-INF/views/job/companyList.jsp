<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="${keywords}"/>
<meta name="description" content="${description}"/>
</head>
<body>
<c:if test="${not empty param.search_LIKE_searchName}">
	<c:set var="wd" value="${param.search_LIKE_searchName}" />
</c:if>
    <!---->
    <div class="top-entry">
    	<div class="w990 relative">
    		<span class="fl f14">共有<label class="num">${companyCount }</label> 家知名企业入驻，今日新增<label class="num">${yesterdayCompany }</label> 家企业</span>
           <%--  <form id="searchFrom" action="${ctx}/mingqi/" method="get"> --%>
            <div class="search-box2 rt0">
            	<input type="hidden" value="${wd }" id="wdValue"/>
            	<input type="text" class="tip-text" placeholder="请输入公司信息" id="searchName" name="search_LIKE_searchName"/><input type="button" id = "btn_search" class = "btn search-enter" value="搜索" />
            </div>
            <!-- </form> -->
        </div>
    </div>
	<!--page-wrapper-->
    <!--/-->
    <div class="nav-g"><a href="${ctx}/">首页</a>&gt;<a href="${ctx}/qiye/">我要进名企</a>&gt;<a href="${ctx}/company/list/" class="red">企业列表</a></div>
    <!--筛选-->
     <div class="box-990 mt10">
    	<div class="filter-con">
    <!--所有规模-->
        	<div class="filter-each">
            	<label id="staffscaleAll" class="name <c:if test="${empty param.search_EQ_staffscale || param.search_EQ_staffscale == ''|| param.search_EQ_staffscale == '0'}">default</c:if>">所有规模</label>
                <div class="r post">
                	<ul class="staffscaleSelect">
		                <li <c:if test="${param.search_EQ_staffscale == '6'}">class="current"</c:if>><a href="javascript:;" data="6">10人以下</a></li>
		                <li <c:if test="${param.search_EQ_staffscale == '5'}">class="current"</c:if>><a href="javascript:;" data="5">11-50人</a></li>
		                <li <c:if test="${param.search_EQ_staffscale == '4'}">class="current"</c:if>><a href="javascript:;" data="4">51-200人</a></li>
		                <li <c:if test="${param.search_EQ_staffscale == '3'}">class="current"</c:if>><a href="javascript:;" data="3">201-500人</a></li>
		                <li <c:if test="${param.search_EQ_staffscale == '2'}">class="current"</c:if>><a href="javascript:;" data="2">501-1000人</a></li>
		                <li <c:if test="${param.search_EQ_staffscale == '1'}">class="current"</c:if>><a href="javascript:;" data="1">1001-5000人</a></li>
		                <li <c:if test="${param.search_EQ_staffscale == '7'}">class="current"</c:if>><a href="javascript:;" data="7">5001-10000人</a></li>
		                <li <c:if test="${param.search_EQ_staffscale == '8'}">class="current"</c:if>><a href="javascript:;" data="8">10000人以上</a></li>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <!--/所有规模-->
            
            <!--所有行业-->
        	<div class="filter-each">
            	<label id="industryAll" class="name <c:if test="${empty param.search_EQ_industry || param.search_EQ_industry == ''|| param.search_EQ_industry == '0'}">default</c:if>">所有行业</label>
                <div class="r post cat">
                	<ul class="industrySelect">
                   	<c:forEach items="${industryTypes }" var="industryType">
	                    <li <c:if test="${param.search_EQ_industry == industryType.code}">class="current"</c:if>>
	                    <a href="javascript:;" data="${industryType.code }" title="${industryType.name }">${industryType.name }</a></li>
	            	 </c:forEach>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
    </div>
    </div>
    <!--/筛选-->
    <!---->
    <div class="w990 mt10">
    	<!--left-->
    	<div class="box-l-wrapper">
        	<!--筛选-->
            <div class="box-l order">
                <div class="fl">
                    <div class="select" id="districtSelect">
                        <p><label id="sdName">全${sessionScope.session_key_branch_name}</label><span class="f12"></span><i class="arrow-d"></i></p>
                        <ul class="order-options order-options-districts">
                        <a><li value="" id="li_current_city"></li></a>
                          <c:forEach items="${districts }" var="district">
			            	<a><li value="${district.id}">${district.abbreviation}</li></a>
			              		<c:if test="${param.search_EQ_countyid == district.id}">
									<c:set var="dName" value="${district.abbreviation}" />
								 </c:if>	
			              </c:forEach>
                        </ul>
                    </div>
                    <div class="select" id="citySelect">
                        <p><label id="scName">周边城市</label><span class="f12"></span><i class="arrow-d"></i></p>
                        <ul class="order-options order-options-otherCitys">
                           <c:forEach items="${otherCitys }" var="otherCity">
				            	<a><li value="${otherCity.cityId}">${otherCity.cityName} </li></a>
				            	 <c:if test="${param.search_EQ_cityid == otherCity.cityId}">
								   <c:set var="cName" value="${otherCity.cityName}" />
							     </c:if>	
				            </c:forEach>
                        </ul>
                    </div>
                    <input type="hidden" value="${dName }" id="dName" data="${param.search_EQ_countyid}"/>
	       			<input type="hidden" value="${cName }" id="cName" data="${param.search_EQ_cityid}"/>
                </div>
                <ul class="fr mr20" id="sortLable">
                    <li class="default <c:if test="${'auto' == sortType}">current</c:if>" data="auto" onclick="makeSort(this);">默认排序</li>
                    <li class="line-g">|</li>
                    <li class="time <c:if test="${'updatetime' == sortType}">current</c:if>" data="updatetime" onclick="makeSort(this);">更新时间<i class="up"></i></li>
                </ul>
                <div class="clear"></div>
            </div>
            <!--/筛选-->
            <!--企业列表-->
            <c:if test="${ empty companyVoList.content}">
		    		<div class="box-l-2 text-center f15"><span class="icon-warning"><i></i></span>没找到想要的企业？不如委托给顾问，帮我<a href="${ctx}/signup/fastSignUp" class="red">30秒找工作</a></div>
              </c:if>
             <c:if test="${empty companyVoList.content}">
            <div class="box-l ent-list mt10">
             		<div class=" f15">您可能感兴趣的公司</div>
               		<c:forEach items="${similarCompanyList }" var="companyVo">
               		<ul class="ent-list-each">
                	<li class="top">
                    	<a target="blank"  href="${ctx}/qiye_${companyVo.companyId}.html"><ul>
                        	<li><c:choose>
							<c:when test="${empty companyVo.companyImage}">
								<div class="pic-none">${companyVo.abbreviation }</div>
							</c:when>
							<c:otherwise>
							<img src="${cdc:getImagePath320(companyVo.companyImage)}" class="pic" />
							</c:otherwise>
							</c:choose></li>
                            <li class="title"><h3>${companyVo.companyName }</h3><label class="text-icon2">金牌企业</label>
                            <label class="text-icon">认证</label></li>
                            <li class="des">
                            ${companyVo.introduction }
                            </li>
                            <li class="addr">地址： ${cdc:convertProvinceAbbreviation(companyVo.provinceId)}
		                         ${companyVo.cityName}
		                        ${cdc:converCountyName("",companyVo.icountyid)}
		                         ${companyVo.address }</li>
                            <li class="env"> ${cdc:convertDicIndustryType(companyVo.industryid)}<label class="line-g">|</label>${cdc:convertDicStaffscale(companyVo.staffscale)}</li>
                            <li class="score"><p class="num">${companyVo.satisfaction }%</p><p>员工满意度</p></li>
                            <li></li>
                            <li></li>
                        </ul></a>
                    </li>
                    <li>
                        <div class="com-jobs">
                         <c:forEach items="${companyVo.jobBases}" var="jobbase">
                            <ul class="com-jobs-each">
                                <li class="title"><a target="blank"  href="${ctx}/zhaopin_${jobbase.id}.html">${jobbase.title }</a></li>
                                <li>${cdc:converDicEducation(jobbase.jobDetail.education)}</li>
                                <li>${tc:convertGenderLimit(jobbase.jobDetail.gender)}</li>
                                <li><c:if test="${not empty jobbase.jobDetail.agefrom }">
			                        ${jobbase.jobDetail.agefrom }<c:if test="${not empty jobbase.jobDetail.ageto }">~${jobbase.jobDetail.ageto}岁</c:if>
			                        <c:if test="${empty jobbase.jobDetail.ageto }">岁以上</c:if></c:if>
			                        <c:if test="${empty jobbase.jobDetail.agefrom }">年龄不限</c:if></li>
                                <li class="salary">￥${jobbase.jobDetail.salaryfrom }<c:if test="${not empty  jobbase.jobDetail.salaryto}">-${jobbase.jobDetail.salaryto }元/月</c:if>
		       					 <c:if test="${empty  jobbase.jobDetail.salaryto}">起</c:if></li>
                                <li><a target="blank"  href="${ctx}/zhaopin_${jobbase.id}.html" class="btn btn-tiny2 btn-small">查看</a></li>
                            </ul>
                            </c:forEach>
                            <div class="clear remove-border"></div>
                    	</div>
                    </li>
                </ul>
               		</c:forEach>
               		</div>
               </c:if>
             <c:if test="${ not empty companyVoList.content}">
            <div class="box-l ent-list mt10">
            <c:forEach items="${companyVoList.content}" var="companyVo">
            	<ul class="ent-list-each">
                	<li class="top">
                    	<a target="blank"  href="${ctx}/qiye_${companyVo.companyId}.html"><ul>
                        	<li>
                        	<c:choose>
							<c:when test="${empty companyVo.companyImage}">
								<div class="pic-none">${companyVo.abbreviation }</div> 
							</c:when>
							<c:otherwise>
								<img src="${cdc:getImagePath320(companyVo.companyImage)}" class="pic" />
							</c:otherwise>
							</c:choose>
                            <li class="title"><h3>${tc:highlightText(companyVo.companyName, wd)}</h3><label class="text-icon2">金牌企业</label>
                            <label class="text-icon">认证</label></li>
                            <li class="des">
                            ${companyVo.introduction }
                            </li>
                            <li class="addr">地址： ${cdc:convertProvinceAbbreviation(companyVo.provinceId)}
		                         ${companyVo.cityName}
		                        ${cdc:converCountyName("",companyVo.icountyid)}
		                        ${tc:highlightText(companyVo.address, wd)}</li>
                            <li class="env"> ${cdc:convertDicIndustryType(companyVo.industryid)}<label class="line-g">|</label>${cdc:convertDicStaffscale(companyVo.staffscale)}</li>
                            <li class="score"><p class="num">${companyVo.satisfaction }%</p><p>员工满意度</p></li>
                            <li></li>
                            <li></li>
                        </ul></a>
                    </li>
                    <li>
                        <div class="com-jobs">
                         <c:forEach items="${companyVo.jobBases}" var="jobbase">
                            <ul class="com-jobs-each">
                                <li class="title"><a target="blank"  href="${ctx}/zhaopin_${jobbase.id}.html">${jobbase.title }</a></li>
                                <li>${cdc:converDicEducation(jobbase.jobDetail.education)}</li>
                                <li>${tc:convertGenderLimit(jobbase.jobDetail.gender)}</li>
                                <li><c:if test="${not empty jobbase.jobDetail.agefrom }">
			                        ${jobbase.jobDetail.agefrom }<c:if test="${not empty jobbase.jobDetail.ageto }">~${jobbase.jobDetail.ageto}岁</c:if>
			                        <c:if test="${empty jobbase.jobDetail.ageto }">岁以上</c:if></c:if>
			                        <c:if test="${empty jobbase.jobDetail.agefrom }">年龄不限</c:if></li>
                                <li class="salary">￥${jobbase.jobDetail.salaryfrom }<c:if test="${not empty  jobbase.jobDetail.salaryto}">-${jobbase.jobDetail.salaryto }元/月</c:if>
		       					 <c:if test="${empty  jobbase.jobDetail.salaryto}">起</c:if></li>
                                <li><a target="blank"  href="${ctx}/zhaopin_${jobbase.id}.html" class="btn btn-tiny2 btn-small">查看</a></li>
                            </ul>
                            </c:forEach>
                            <div class="clear remove-border"></div>
                    	</div>
                    </li>
                </ul>
                </c:forEach>
                <!--分页-->
		            <tags:urlrewrite_pagination paginationSize="5" page="${companyVoList }" pageType="qy"/>
                <!--/分页-->
            </div>
             </c:if>
            <!--/企业列表-->
            
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
                    <img src="http://www.youlanw.com/static/images/QR.jpg" alt=""/>
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
    
   <!--关键词-->
		<div class="w990 mt20">
			<ul class="tab-title">
				<li class="current">热门城市</li>
				<li>热门岗位</li>
				<li>最新企业</li>
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
				<c:forEach items="${rightRecommendCompanys }" var="company">
					<a href="${ctx}/qiye_${company.companyId}.html">${company.abbreviation }</a>
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

<!--/page-wrapper-->
    <script type="text/javascript">
	$(function(){
		if($("#wdValue").val()!="0"){
			$("input[name='search_LIKE_searchName']").val($("#wdValue").val());	
		}
		//区域、周边城市选择下拉菜单
		$('.order .select p').click(function(){
			$(this).parents('.select').toggleClass('current').siblings('.select').removeClass('current');//给当前菜单 加current样式
		})
		$('.order-options li').click(function(){
			$(this).parents('.select').find('p label').text($(this).text()).css({'color':'#ff6c00'}).end().removeClass('current');
		});
		//点击区域、周边城市下拉菜单以外区域，隐藏下拉菜单(去掉current)
		$(document).bind("click",function(e){
		var target = $(e.target);
		if(target.closest(".order .select").length == 0){
				$('.order .select').removeClass('current');
			}
		})
		//行业选择 ,规模选择
		$('.industrySelect li,.staffscaleSelect li').click(function(){
			$(this).addClass('current').siblings().removeClass('current');
			searchCompany();
		});
		//所有规模。所有行业
	    $("#staffscaleAll").click(function(){
	    	$(".staffscaleSelect li").removeClass('current');
	        searchCompany();
    	});
	    $("#industryAll").click(function(){
	    	$(".industrySelect li").removeClass('current');
	        searchCompany();
    	});
		//tab
		$('.tab-title li').click(function(){
			$(this).addClass('current').siblings().removeClass('current');
			var current_index=$('.tab-title li').index($(this));
			$('.tab-each').eq(current_index).show().siblings('.tab-each').hide();
		});
		initCookieRecent();
		//行政区，城市选择
		  $('.order-options-districts li').click(function(){
		        $(this).parents('.select').find('p label').text($(this).text()).end().removeClass('current');
		        $("#cName").attr("data","");
		        $("#dName").attr("data",$(this).val());
		        searchCompany();
	    });
	    $('.order-options-otherCitys li').click(function(){
	        $(this).parents('.select').find('p label').text($(this).text()).end().removeClass('current');
	        $("#cName").attr("data",$(this).val());
	        $("#dName").attr("data","");
	        searchCompany();
	    });
	    var dName = $("#dName").val();
	    var cName = $("#cName").val();
	    $("#sdName").text("全"+$("#c_branch_name").text().replace("站",""));
	    $("#li_current_city").text("全"+$("#c_branch_name").text().replace("站",""));
	    if(dName!=null&&dName!=""){
	    	$("#districtSelect").css({'color':'#ff6c00'});
	        $("#sdName").text(dName);
	    }
	    if(cName!=null&&cName!=""){
	    	$("#citySelect").css({'color':'#ff6c00'});
	        $("#scName").text(cName);
	    }
	    
	    $("#btn_search").click(function(){ searchCompany();});
	    
	})
	//排序
function makeSort(obj){
	$(obj).addClass('current').siblings().removeClass("current");
	searchCompany();
}
	//跳转查询公司列表
function searchCompany(){
	var searchName = $("#searchName").val().replace('_','&');
	var industry = $(".industrySelect .current a").attr("data");
	var scale = $(".staffscaleSelect .current a").attr("data");
	var otherCity = $("#cName").attr("data");
	var districts = $("#dName").attr("data");
	var sortType=$("#sortLable .current").attr("data");
	var condition = [];
	if(otherCity!=null && otherCity!=""){
   		condition.push(otherCity);
	}else{
		condition.push("0");
	}
	if(districts!=null && districts!=""){
		condition.push(districts);
	}else{
		condition.push("0");
	}
	if(industry!=null && industry!=""){
		condition.push(industry);
	}else{
		condition.push("0");
	}
	if(scale!=null && scale !=""){
   		condition.push(scale);
	}else{ 
		condition.push("0");
	}
	if(searchName!=null && searchName !=""){
   		condition.push(searchName);
	}else{
		condition.push("0");
	}
	window.location.href="${ctx}/qy_"+sortType+"_" + condition.join("_")+"_1.html";
}
	
	   /**
     * 初始化cookie最近记录
     */
    function initCookieRecent(){
        var ids = [];
        var rbs = Cookie.getCookie("recent_browse_job");
        if(rbs!=null&&rbs!=undefined&&rbs!=""){
            var jsonObj = eval("("+unescape(rbs)+")");
            var tmpArray = [];
                $.each(jsonObj,function(i,j){
                	var j=jsonObj[jsonObj.length-i-1];
                    if($.inArray(j.id,ids)==-1 && tmpArray.length<4 ){
                    	var tmp;
        				if(j.imgpath==null||j.imgpath=="")
        				{
        					tmp = '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"http://www.youlanw.com/static/images/pic-default.jpg","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.title+'"}';
        				}
        				else
        				{
        					tmp = '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"'+j.imgpath+'","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.imgAlt+'"}';
        				}
                        tmpArray.push(tmp);
                        ids.push(j.id);
                    }
                });
            saveRecent(tmpArray);
        }else{
        	var divs = [];
            Cookie.setCookie('recent_browse_job', escape("[]"), 5);
        }
    }
	/**
	 * 保存最近浏览
	 */
	function saveRecent(data){
	    $("#recent_browse_list").empty();
	    var divs = [];
		divs.push('<h2 class="box-title">最近浏览职位</h2><div class="job-list2">');
		if(data==null||data=="")
		{
			divs.push('<p class="no-result"><span><i></i></span>暂无浏览记录</p>');
		}
		else
		{
			$.each(data,function(i,j){
			   var obj = eval("("+j+")");
			   divs.push('<a href="${ctx}/zhaopin_'+obj.id+'.html">');
			   divs.push('<ul class="job-list2-each">');
			   divs.push('<li class="pic"><img src='+obj.imgpath+' alt='+obj.imgAlt+'/></li>');
			   divs.push('<li class="title">'+obj.title+'</li>');
			   divs.push('<li class="salary">￥'+obj.totalsalary+'</li>');
			   divs.push('</ul></a>');
			});
		}
	   
	    divs.push('<div class="remove-border2"></div><div class="clear"></div>');
	    $("#recent_browse_list").append(divs.join(''));
	    $("#recent_browse_list").parent(".box-r").height($("#recent_browse_list").height());
	}
    </script>
</body>
</html>