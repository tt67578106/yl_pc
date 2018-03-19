<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:if test="${not empty param.search_LIKE_jobname}">
	<c:set var="wdt" value="${param.search_LIKE_jobname}_" />
	<c:set var="wd" value="${param.search_LIKE_jobname}" />
</c:if>
<c:if test="${not empty param.search_LIKE_jobLabel}">
	<c:set var="lb" value="${param.search_LIKE_jobLabel}_" />
</c:if>
<c:if test="${not empty param.search_LIKE_jobType}">
	<c:set var="tp" value="${param.search_LIKE_jobType}_" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>${wdt}${lb}${tp}我要找工作_我想免费找高薪好工作</title>
<meta name="description" content="优蓝网我要找工作栏目拥有海量优质招工信息，充分满足您找工作需求，助您轻松找高薪好工作。免费找工作，上优蓝网（www.youlanw.com）" />
<meta name="keywords" content="找工作,我要找工作,找高薪工作,找好工作,免费找工作,我想找工作" />
<link rel="stylesheet" href="${ctx}/static/css/jquery-ui.min.css" />
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
</head>
<body>
    <div class="top-entry">
    	<div class="w990 relative">
    		<span class="fl">在<label class="num">${allJobCount }</label>个岗位中找</span>
            <div class="search-box2">
	            <form action="${ctx}/job/" method="get">
	            	<input type="text" class="tip-text"  placeholder="请输入职位信息"  name="search_LIKE_jobname" value="${wd}"/><input type="submit" value="搜索" class="btn"/>
				</form>
            </div>
            <a href="${ctx}/signup/fastSignUp">
            <div class="quick"><label class="fl">点这里，让客服帮你</label><span class="btn btn-small fl">30秒找工作</span></div>
            </a>
        </div>
    </div>
    <!--/-->
    <!--筛选-->
    <div class="box-990 mt10">
    	<div class="filter-con">
        	<!--所有岗位-->
        	<div class="filter-each">
            	<a id="allJobTypeHref"><label class="name <c:if test="${empty param.search_LIKE_jobType || param.search_LIKE_jobType == ''}">default</c:if>" id="allJobType">所有岗位</label></a>
                <div class="r post">
                <!-- 遍历岗位分类 -->
                	<ul id="job_types">
                		<c:forEach items="${jobTypes}" var="jobType">
                		<li <c:if test="${param.search_LIKE_jobType == jobType.jobName}">class="current"</c:if>><a data-id="${jobType.id}" href="javascript:void(0)">${jobType.jobName}</a></li>
                		</c:forEach>
                    </ul>
                    <div class="sub hide" id="sub_job_types"></div>
                </div>
                <div class="clear"></div>
            </div>
            <!--/所有岗位-->
              
            <!--选择薪资-->
        	<div class="filter-each bd0">
            	<label class="name 
            	<c:if test="${(not empty param.search_LIKE_totalsalary && fn:endsWith(param.search_LIKE_totalsalary, '99999')
            				 && fn:startsWith(param.search_LIKE_totalsalary, '0'))||empty param.search_LIKE_totalsalary }"
            	>default</c:if>" style="margin-top:32px;" id="allSalary">不限薪资</label>
                <div class="r pay-scale">
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
        </div>
    </div>
    <!--/筛选-->
    <!---->
   
    <!--/-->
    <!---->
    <div class="w990 mt10">
    	<!--left-->
    	<div class="box-l-wrapper">
    	 <div class="box-l order">
    	<div class="fl">
	    	<div class="select" id="districtSelect">
	            <p><label id="sdName">${sessionScope.session_key_branch_name}</label>
	            <span class="f12"></span><i class="arrow-d"></i></p>
	            <ul class="order-options order-options-districts">
	            	<a><li value="" id="li_current_city">上海市</li></a>
	              <c:forEach items="${districts }" var="district">
	            	<a><li value="${district.id}">${district.abbreviation}</li></a>
	              		<c:if test="${param.search_EQ_countyid == district.id}">
							<c:set var="dName" value="${district.abbreviation}" />
						 </c:if>	
	              </c:forEach>
	            </ul>
	        </div>
	        <div class="select" id="citySelect">
	            <p><label id="scName">周边城市
	            </label><span class="f12"></span><i class="arrow-d"></i></p>
	            <ul class="order-options order-options-otherCitys">
	            <c:forEach items="${otherCitys }" var="otherCity">
	            	<a><li value="${otherCity.cityId}">${otherCity.cityName} </li></a>
	            	 <c:if test="${paramValues['search_EQ_city.id'][0] == otherCity.cityId}">
					   <c:set var="cName" value="${otherCity.cityName}" />
				     </c:if>	
	            </c:forEach>
	            </ul>
	        </div>
	        <input type="hidden" value="${dName }" id="dName" data="${param.search_EQ_countyid}"/>
	        <input type="hidden" value="${cName }" id="cName" data="${paramValues['search_EQ_city.id'][0]}"/>
	        <input type="hidden" data="${param.search_LIKE_jobType}" id="hJobType">
	        <input type="hidden" data="${param.search_LIKE_subJobType}" id="hsubJobType">
	        <input type="hidden" data="${param.search_LIKE_totalsalary}" id="salaryCondition">
        </div>
	        <ul class="fr mr20" id="sortLable">
			  <c:forEach items="${sortTypes}" var="entry" varStatus="i">
			    <li class="<c:if test="${ i.index == 0}">default </c:if><c:if test="${entry.key == sortType}">current</c:if>" onclick="makeSort(this);" data="${entry.key}">${entry.value}
			    <c:if test="${i.index == 0}" ><li class="line-g">|</li></c:if>
			    <c:if test="${i.index == 1}" ><i class="up"></i><li class="line-g">|</li></c:if>
			    <c:if test="${i.index == 2}" ><i></i></c:if>
			    </li>
			  </c:forEach>
			</ul>
        <div class="clear"></div>
    </div>
        	<!--列表-->
		    	<c:if test="${ empty page.content}">
		    		<div class="box-l-2 text-center f15"><span class="icon-warning"><i></i></span>没找到想要的岗位？不如委托给顾问，帮我<a href="${ctx}/signup/fastSignUp" class="red">30秒找工作</a></div>
               	</c:if>
               <div class="box-l mt10" id="jobContent">
               <c:if test="${empty page.content}">
               		<c:forEach items="${recommendJobList }" var="recommend">
               		<ul class="job-list-each seal-1 <c:if test="${recommend.isUrgency == 2}">seal-5</c:if><c:if test="${recommend.isUrgency == 3}">seal-4</c:if>" >
		            	<li class="pic"><a href="${ctx}/zhaopin_${recommend.jobId}.html" target="_blank">
		            	<c:choose>
							<c:when test="${empty recommend.imgPath}">
								<img src="${cdc:getImageByJobTypeO(recommend.jobType)}" alt="${recommend.jobTitle}" />
							</c:when>
							<c:otherwise>
								<img src="${recommend.imgPath}" alt="${recommend.jobTitle}" />
							</c:otherwise>
						</c:choose>
		            	</a><i></i></li>
		                <li class="title"><h3>[${recommend.jobCityName}${cdc:converCountyName("-",recommend.countyId)}]<a href="${ctx}/zhaopin_${recommend.jobId}.html" target="_blank">${tc:highlightText(recommend.title, wd)}</a></h3></li>
		                <li class="tags-g">
		                <c:forEach items="${recommend.jobLabel}" var="label">
                			<a href="${ctx}/s_label_${label}">${label }</a>
                    	</c:forEach>
		                </li>
		                <li class="summary">${cdc:converDicEducation(recommend.education)} / ${tc:convertGenderLimit(recommend.gender)} / ${recommend.ageFrom }~${recommend.ageTo}岁 /${tc:dayAfter(recommend.updateTime)}发布${recommend.countyId }</li>
		                <li class="salary">￥${recommend.salaryFrom }<c:if test="${not empty  recommend.salaryTo}">-${recommend.salaryTo }元/月</c:if>
		                <c:if test="${empty  recommend.salaryTo}">起</c:if></li>
		                <li class="company"><a href="${ctx}/qiye_${recommend.jobId}.html" target="_blank">${tc:highlightText(recommend.companyName ,wd) }</a>
		                <c:if test="${recommend.validation == 1}"><label class="seal-3">验</label></c:if></li>
		                <li class="signup"><a data-id="${recommend.jobId }" href="${ctx}/zhaopin_${recommend.jobId}.html" class="btn btn-small">去看看</a></li>
		            </ul>
               		</c:forEach>
               </c:if>
               <c:forEach items="${page.content }" var="job">
		            <ul class="job-list-each seal-1 <c:if test="${job.jobConfig.isUrgency == 2}">seal-5</c:if><c:if test="${job.jobConfig.isUrgency == 3}">seal-4</c:if>" >
		            	<li class="pic"><a href="${ctx}/zhaopin_${job.id}.html"  target="_blank">
		            	<c:choose>
							<c:when test="${empty job.thumbnialImage.imgpath}">
								<img src="${cdc:getImageByJobTypeO(job.jobType)}" alt="${job.title }" />
							</c:when>
							<c:otherwise>
								<img src="${job.thumbnialImage.imgpath}" alt="${job.title}" />
							</c:otherwise>
						</c:choose>
		            	</a><i></i></li>
		                <li class="title"><h3>[${job.city.cityName }${cdc:converCountyName("-",job.countyid)}]<a href="${ctx}/zhaopin_${job.id}.html" target="_blank">${tc:highlightText(job.title, wd)}</a></h3></li>
		                <li class="tags-g">
		                <c:forEach items="${job.jobLabel}" var="label">
                			<a href="${ctx}/s_label_${label}">${label }</a>
                    	</c:forEach>
		                </li>
		                <li class="summary">${cdc:converDicEducation(job.jobDetail.education)} / ${tc:convertGenderLimit(job.jobDetail.gender)} / <c:if test="${not empty job.jobDetail.agefrom }">
                        ${job.jobDetail.agefrom }<c:if test="${not empty job.jobDetail.ageto }">~${job.jobDetail.ageto}岁</c:if>
                        <c:if test="${empty job.jobDetail.ageto }">岁以上</c:if></c:if>
                        <c:if test="${empty job.jobDetail.agefrom }">年龄不限</c:if>/${tc:dayAfter(job.updatetime)}发布</li>
		                <li class="salary">￥${job.jobDetail.salaryfrom }<c:if test="${not empty  job.jobDetail.salaryto}">-${job.jobDetail.salaryto }元/月</c:if>
		                <c:if test="${empty  job.jobDetail.salaryto}">起</c:if></li></li>
		                <li class="company"><a href="${ctx}/qiye_${job.company.id}.html" target="_blank">${tc:highlightText(job.company.name ,wd) }</a>
		                <c:if test="${job.company.validation == 1}"><label class="seal-3">验</label></c:if></li>
		                <li class="signup"><a data-id="${job.id }" href="${ctx}/zhaopin_${job.id}.html" class="btn btn-small">去看看</a></li>
		            </ul>
            	</c:forEach>
		          <c:if test="${not empty page.content}">
		             <div id="paginationId"><tags:pagination paginationSize="5" page="${page }" pageType="search/?"/></div>
		          </c:if>
		         </div>
            <!--/列表-->
           
        </div>
<!--/left-->
        <!--right-->
        <div class="box-r-wrapper">
        	<!--福利标签-->
            <div class="box-r welfare-tags">
            	<ul>
            	    <c:forEach items="${labels.content}" var="label">
                		<li value="${label.id}" 
                		<c:if test="${tc:containsLables(label.tagName,param.search_LIKE_jobLabel)}">class="checked"</c:if>><a>${label.tagName }</a></li>
                    </c:forEach>
                </ul>
                <p class="more"></p>
                <div class="clear"></div>
            </div>
            <!--/福利标签-->
        	<!--优蓝网手机版-->
        	<div class="box-r mt10">
            	<div class="m-entry">
                	<div class="text">
                		<i></i>
                        <h3>优蓝网手机版</h3>
                       <!--  <p>好工作一手掌握</p> -->
                        <p>m.youlanw.com</p>
                    </div>
                  <!--   <p class="mt15">关注【优蓝求职】微信号</p>
                    <p class="mt5">好工作不错过</p> -->
                    <img src="${ctx}/static/images/QR2.jpg" alt="" class="mt5"/>
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
                    <img src="${ctx}/static/images/QR.jpg" alt=""/>
                </div>
            </div>
            <!--/优蓝网微信版-->
            <!--广告位-->
            <c:forEach items="${rightJobListRecommend}" var="recommend">
	            <div class="ad-r">
	                <a href="${recommend.link}"><img src="${recommend.img }" alt=""/></a>
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
				<li>最新岗位</li>
				<li>友情链接</li>
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
					<a href="${ctx}/job/detail/${hotJob.id}.html">${hotJob.jobTitle }</a>
				</c:forEach>
			</div>
			<div class="tab-each">
				<c:forEach items="${newJobs }" var="newJob">
					<a href="${ctx}/job/detail/${newJob.id}.html" >${newJob.jobTitle }</a>
				</c:forEach>
			</div>
			<div class="tab-each">
					<a href="http://www.daomo8.com/" target="_blank">激光刀模人才网</a>
					<a href="http://www.dyzp5.com/" target="_blank">大邑人才网</a>
					<a href="http://www.ycdsrc.com/" target="_blank">宜昌人才市场</a>
					<!-- <a href="http://www.dzchr.com/" target="_blank">打工网</a> -->
					<a href="http://www.365ahr.com/" target="_blank">中国农业人才网</a>
					<a href="http://www.zhangqiurc.com/" target="_blank">章丘人才网</a>
					<a href="http://www.jrokjob.com/" target="_blank">金融招聘</a>
					<a href="http://www.mianjie.cn/" target="_blank">日企</a>
					<a href="http://www.xcqyrc.com/" target="_blank">薛城人才网</a>
					<a href="http://www.yooperhr.com/" target="_blank">中国优品人才网</a>
					<a href="http://www.cdrhcw.com/" target="_blank">新疆人才网</a>
					<a href="http://www.chinaunsv.com/" target="_blank">中国勘测联合网</a>
					<a href="http://www.hyxrsj.gov.cn/" target="_blank">汉阴县人力资源和社会保障局</a>
					<a href="http://www.ncwhr.com/" target="_blank">纳才人才网</a>
					<a href="http://www.138pet.com/" target="_blank">宠物人才网</a>
			</div>
			<div class="clear"></div>
		</div>
		<!--/关键词-->
    
<script>
var page=1;
var paginationSize = 15;
$(function(){
    $('.welfare-tags li:odd').addClass('odd');
    //筛选条件 展开更多
    $('.more-g-2').click(function(){
        $(this).toggleClass('fold-up').parent().toggleClass('height-auto');
        if($(this).text()=='更多'){$(this).text('收起')}else $(this).text('更多');
    });
    //tab
    $('.tab-title li').click(function(){
        $(this).addClass('current').siblings().removeClass('current');
        var current_index=$('.tab-title li').index($(this));
        $('.tab-each').eq(current_index).show().siblings('.tab-each').hide();
    });
    //控制福利标签展开/收起
    $('.welfare-tags .more').click(function(){
        $('.welfare-tags ul').toggleClass('height-auto');
        $(this).toggleClass('up');
    });
    //福利标签选中，添加class
    $('.welfare-tags li').click(function(){
        $(this).toggleClass('checked');
        searchJob();
    });
    //区域、周边城市选择下拉菜单
    $('.order .select p').click(function(){
   		 $(this).parents('.select').toggleClass('current').siblings('.select').removeClass('current');//给当前菜单 加current样式
    })
    $('.order-options-districts li').click(function(){
        $(this).parents('.select').find('p label').text($(this).text()).end().removeClass('current');
        $("#cName").attr("data","");
        $("#dName").attr("data",$(this).val());
        searchJob();
    });
    $('.order-options-otherCitys li').click(function(){
        $(this).parents('.select').find('p label').text($(this).text()).end().removeClass('current');
        $("#cName").attr("data",$(this).val());
        $("#dName").attr("data","");
        searchJob();
    });
    //点击区域、周边城市下拉菜单以外区域，隐藏下拉菜单(去掉current)
    $(document).bind("click",function(e){
        var target = $(e.target);
        if(target.closest(".order .select").length == 0){
            $('.order .select').removeClass('current');
        }
    });

    //最近浏览记录
    initCookieRecent();
    //行政区，城市选择
    var dName = $("#dName").val();
    var cName = $("#cName").val();
    $("#sdName").text($("#c_branch_name").text().replace("站",""));
    $("#li_current_city").text($("#c_branch_name").text().replace("站",""));
    if(dName!=null&&dName!=""){
    	$("#districtSelect").css({'color':'#ff6c00'});
        $("#sdName").text(dName);
    }
    if(cName!=null&&cName!=""){
    	$("#citySelect").css({'color':'#ff6c00'});
        $("#scName").text(cName);
    }
    initSalaryRang(0,10500,true);
    if($("#job_types .current").length>0){
    	var id = $("#job_types .current a").attr("data-id");
    	var typeName = $("#job_types .current a").text();
    	clickJobType(id,typeName);
    }
    $("#job_types a,#allJobTypeHref").click(function(){
    	if($(this).text()=="所有岗位"){
    		$("#hsubJobType").attr("data","");
    		$("#hJobType").attr("data","");
    		searchJob();
    		return;
    	}
    	var id = $(this).attr("data-id");
    	var typeName = $(this).text();
    	$(this).parent().addClass("current").siblings().removeClass("current");
    	$("#allJobType").removeClass("default");
    	clickJobType(id,typeName);
    	$("#hsubJobType").attr("data","");
    	searchJob();
    });
    $("#sub_job_types").delegate("a","click",function(){
    	$(this).addClass("sub-current").siblings().removeClass("sub-current");
    	$("#hsubJobType").attr("data",$(this).text());
    	if($(this).text()=="所有"){
    		$("#hsubJobType").attr("data","");
    	}
    	searchJob();
    });
});

//点击岗位类型
function clickJobType(id,typeName){
	$("#sub_job_types").addClass("hide");
	var jobType;
	 $.get("${ctx}/job/findJobTypesByParent",{"parentId":id},function(result){
		if(result.length > 0){
			var subs = [];
			subs.push("<a>所有</a>");
			$.each(result,function(i,j){
				if(j.jobName==$("#hsubJobType").attr("data")){
					subs.push("<a class='current sub-current'>"+j.jobName+"</a>");
				}else{
					subs.push("<a>"+j.jobName+"</a>");
				}
			});
			$("#sub_job_types").removeClass("hide").empty();
			$("#sub_job_types").append(subs.join(''));	
		}
	}); 
	$("#hJobType").attr("data",typeName);
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
            $.each(jsonObj,function(i){
            	var j=jsonObj[jsonObj.length-i-1];
                if($.inArray(j.id,ids)==-1 && tmpArray.length<4){
                	var tmp;
    				if(j.imgpath==null||j.imgpath=="")
    				{
    					tmp = '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"${ctx}/static/images/pic-default.jpg","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.title+'"}';
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
//排序
function makeSort(obj){
	$(obj).addClass('current').siblings().removeClass("current");
	searchJob();
}

//跳转查询工作列表
function searchJob(){
	var jobType = $("#hJobType").attr("data");
	var subJobType = $("#hsubJobType").attr("data")==null?"":$("#hsubJobType").attr("data");
	var salary = $("#salaryCondition").attr("data");
	var otherCity = $("#cName").attr("data");
	var districts = $("#dName").attr("data");
	var sortType=$("#sortLable .current").attr("data");
	var lables;
	$('.welfare-tags .checked').each(function(index){
    	lables += $(this).text()+",";
    });
	var condition = [];
    if(lables!=null && lables!=""){
        lables = lables.substring(0,lables.length-1).replace("undefined","");
        condition.push("search_LIKE_jobLabel" +"="+lables);
    }else{
    	condition.push("search_LIKE_jobLabel" +"="+"");
    }
	if(jobType!=null && jobType !=""){
   		condition.push("search_LIKE_jobType" +"="+jobType);
	}else{
		condition.push("search_LIKE_jobType" +"="+"");
	}
	if(subJobType!=null && subJobType !=""){
   		condition.push("search_LIKE_subJobType" +"="+subJobType);
	}else{
		condition.push("search_LIKE_subJobType" +"="+"");
	}
	if(salary!=null && salary!=""){
   		condition.push("search_LIKE_totalsalary" +"="+salary);
	}else{
		condition.push("search_LIKE_totalsalary" +"="+"");
	}
	if(otherCity!=null && otherCity!=""){
		condition.push("search_EQ_city.id" +"="+otherCity);
	}else{
		condition.push("search_EQ_city.id" +"="+"");
	}
	if(districts!=null && districts!=""){
		condition.push("search_EQ_countyid" +"="+districts);
	}else{
		condition.push("search_EQ_countyid" +"="+"");
	}
	window.location.href="${ctx}/search/?sortType="+sortType+"&" + condition.join("&");
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

//不限薪资
$("#allSalary").click(function(){
	$(this).addClass('default');
	initSalaryRang(0,10500,false);
});

/**
 * 初始化工资选择控件
 */
function initSalaryRang(gza,gzb,flag){
	 var reg = new RegExp("^[0-9]*$");
	var gzAll = $("#salaryCondition").attr("data");
	if(gzAll!=null && flag){
		gza = (gzAll.split("-")[0]==null || gzAll.split("-")[0]=="" || !reg.test(gzAll.split("-")[0]))?0:gzAll.split("-")[0];
		gzb = (gzAll.split("-")[1]==null || gzAll.split("-")[1]=="" || !reg.test(gzAll.split("-")[0]))?10500:gzAll.split("-")[1];
	}
    var pageConfig = {linkurl:"${ctx}/",searchurl:"${ctx}/job/"};
    pageConfig.filter = {sex0:'/m/2000-0/',sex1:'/f/2000-0/',gz1:gza,gz2:gzb,gzurl:'/_gongzi_/',pgurl:'/2000-0/_page_/'};
    var gongzi1 = pageConfig.filter.gz1;
    var gongzi2 = pageConfig.filter.gz2;
    //工资范围初始化
    if(gongzi2>10000){
    	if(gongzi1==0){
    		$( "#amount" ).text("不限薪资");
    	}else{
    		if(gongzi1>10000){
    			$( "#amount" ).text("10000以上");
    		}else{
	    		$( "#amount" ).text(gongzi1+"以上");
    		}
    	}
    }else if(gongzi2==1500){
    	$( "#amount" ).text('请选择薪资范围');
    }else{
    	 $( "#amount" ).text(gongzi1+'-'+gongzi2+" 元/月");
    }
  	//拖动居中显示
  	var gongziTemple = gongzi2>10000?10500:gongzi2;//临时变量
	var w,wrange,m;
	var w2= ((gongziTemple - gongzi1)/1000)*92;
	var w3= w2/2;
	wrange = (((gongzi1-2000)/1000)*92)+w3;
	w = -19;
	m=w+ wrange;
	$(".range-tip").stop(1,1).animate({marginLeft:m});	//动画效果	
    //工资范围
    $( "#slider-range" ).slider({
        range: true,
        min: 1500,
        max: 10500,
        step: 500,
        values: [gongzi1,gongzi2],
        slide: function( event, ui ) {
        	var w,wrange,m;
			var w2= ((ui.values[1] - ui.values[0])/1000)*92;
			var w3= w2/2;
			wrange = (((ui.values[0]-2000)/1000)*92)+w3;
			w = -17
			m=w+ wrange;
            $(".range-tip").stop(1,1).animate({marginLeft:m});
            if(ui.values[ 1 ]==10500){
                if(ui.values[0]==1500){
                    $( "#amount" ).text('不限薪资');
					$('.pay-scale').prev('.name').addClass('default');
                } else if(ui.values[0]>10000){$( "#amount" ).text('10000以上');}
				else{
                    $( "#amount" ).text(ui.values[ 0 ] + ' 以上');
                }
            } else if(ui.values[0]==1500){
					if(ui.values[1]==1500){$( "#amount" ).text('请选择薪资范围');}
					else if(ui.values[1]<=10000 && ui.values[1]>1500){
					$( "#amount" ).text(0 + "-" + ui.values[ 1 ] +' 元/月');
					} else $( "#amount" ).text('不限薪资');
				}
				else if(ui.values[1]>10000&&ui.values[0]<10000){
					$( "#amount" ).text(ui.values[0] +' 以上');
				}
				else if(ui.values[0]>10000){
					$( "#amount" ).text('10000以上');
				}
			else{
                $( "#amount" ).text( ui.values[ 0 ] + "-" + ui.values[ 1 ] +' 元/月');
            }
        },
        change:function(event,ui){
        	$("#allSalary").removeClass('default');
            var gongzi_base_url = pageConfig.filter.gzurl;
            var salaryCondition;
             if(ui.values[1] >10000){
             	ui.values[ 1 ]="99999";
             }
             if(ui.values[0] < 2000){
              	ui.values[ 0 ]="0";
              }
            if(ui.values[0]==0 && ui.values[1] ==10000){
                salaryCondition = gongzi_base_url.replace('/_gongzi_/',ui.values[ 0 ] + "-" + ui.values[ 1 ]);
            }else{
                salaryCondition  = gongzi_base_url.replace('/_gongzi_/',ui.values[ 0 ] + "-" + ui.values[ 1 ]);
            }
	        $("#salaryCondition").attr("data",salaryCondition);
            searchJob();
        }
    });
    $( ".range-tip" ).css("margin-left",351);
}
</script>
</body>
</html>