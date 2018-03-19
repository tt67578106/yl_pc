<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${not empty sessionScope.session_key_branch_name}">
	<c:set var="cityName" value="${fn:substring(sessionScope.session_key_branch_name, 0, fn:length(sessionScope.session_key_branch_name)-1)}" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<title>优蓝社区_打工帮助_蓝领打工者求职面试指南|指导|讨论</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="优蓝社区,优蓝家园,求职指南,求职指导,职业规划"/>
<meta name="description" content="优蓝网【优蓝社区】频道，是一个免费为打工者提供求职帮助的平台，为打工者提供求职指南，制定职业规划，解决职场困惑，使蓝领工人能够快速清楚自己的职业发展方向，找到合适自己的工作。"/>
</head>
<body>
	 <div class="nav-g"><a href="${ctx }/">首页</a>&gt;社区</div>
	 
	 <div class="w1200">
	   <!--left-->
       <div class="box-l-wrapper">
       	    <ul class="community-img">
       	    	<c:forEach items="${rdxws }" var="hotNews">
    			<li>
    				<a href="${ctx}/xl_${hotNews.id}.html" target="_blank" title="${hotNews.title}">
    					<img src="${cdc:getImagePath720(hotNews.thumbnialImage.imgpath) }" alt="${hotNews.title}" />
	    				<h2>${hotNews.title}</h2>
	    				<p>${not empty hotNews.introduction?hotNews.introduction:hotNews.jxcontentPreview }</p>
    				</a>
    			</li>
    			</c:forEach>
    			<div class="clear"></div>
    		</ul>
	       	<div class="title-bar-sty mt10"><span>最新资讯</span><a href="${ctx}/s_articleType_1" class="right blue mt10">更多&gt;&gt;</a></div>
	        <div class="community-list">
	        	<c:forEach items="${newActives }" var="active">
		        	<a href="${ctx}/xl_${active.id}.html">
	                <ul class="community-list-each">
	                	<li class="bar">${cdc:getShortArticleTypeByCode(active.articleType) }</li>
	                    <li class="pic"><img src="${cdc:getImagePath320(active.thumbnialImage.imgpath) }" alt="${active.title }"></li>
	                    <li class="title">${active.title }</li>
	                    <li class="time">${active.publishTimeString }<span class="eye">${active.viewCount }</span></li>
	                    <div class="clear"></div>
	                </ul>
	                </a>
	        	</c:forEach>
	        </div>
	    </div>
	 	<!-- /left -->
	 	
	 	<!-- right -->
	 	<div class="box-r-wrapper">
       	    <!--日历-->
       	    <div class="box-r weather">
       	    	<div class="part1">
       	    		<i></i>今天是<span class="right">${cityName }</span>
       	    	</div>
       	    	<div class="part2">
       	    		<span class="num"><fmt:formatDate value="${today }" pattern="MM"/></span>月<span class="num"><fmt:formatDate value="${today }" pattern="dd"/></span>日&nbsp;/&nbsp;<fmt:formatDate value="${today }" pattern="yyyy"/>年<span class="right"><fmt:formatDate value="${today }" pattern="E"/></span>
       	    	</div>
       	    </div>
       	    <!--/日历-->
        	<!--栏目-->
        	<!--栏目-->
			<div class="box-r mt10">
				<ul class="zone-col">
					<a href="${ctx}/s_articleType_1" target="_blank" title="行业新闻">
						<li>
							<i></i> <span>行业新闻</span>
						</li>
					</a>
					<a href="${ctx}/s_articleType_6" target="_blank" title="优蓝动态">
						<li class="icon4">
							<i></i> <span>优蓝动态</span>
						</li>
					</a>
					<a href="${ctx}/s_articleType_2" target="_blank" title="小蓝有约">
						<li class="icon2">
							<i></i> <span>小蓝有约</span>
						</li>
					</a>
					<a href="${ctx}/s_articleType_3" target="_blank" title="求职帮助">
						<li class="icon5">
							<i></i> <span>求职帮助</span>
						</li>
					</a>
					<a href="${ctx}/s_articleType_5" target="_blank" title="优蓝现场">
						<li class="icon3 bd0">
							<i></i> <span>优蓝现场</span>
						</li>
					</a>
					<a href="${ctx}/wenda" target="_blank" title="企业问答">
						<li class="icon6 bd0">
							<i></i> <span>企业问答</span>
						</li>
					</a>
				</ul>
			</div>
			<!--/栏目-->
			<div class="box-r mt10">
				<div class="title-bar pl10 bd0"><span>大家都想进</span></div>
				<div class="job-list2">
					<c:forEach items="${rightRecommendCompanys }" var="item">
						<a href="${ctx}/qiye_${item.companyId}.html">
							<ul class="job-list2-each">
								<li class="pic">
									<c:choose>
										<c:when test="${empty item.companyLogo }">
											<div class="com-exam04">${item.abbreviation }</div>
										</c:when>
										<c:otherwise>
											<img src="${cdc:getImagePath320(item.companyLogo) }" alt="${item.abbreviation }" />
										</c:otherwise>
									</c:choose>
								</li>
								<li class="title">${item.companyName }</li>
								<li class="salary">平均月薪￥${item.totalsalary }</li>
							</ul>
						</a>
					</c:forEach>
					<div class="remove-border2"></div>
					<div class="clear"></div>
				</div>
			</div>
            <!--求职帮助-->
        	<div class="box-r recruit-tags recruit-tags-sty  mt10">
            	<div class="title-bar bd0"><span>求职帮助</span><a href="${ctx}/s_articleType_3" class="blue right mt5">更多&gt;&gt;</a> </div>
            	<ul class="about-recruit strategy">
            		<c:forEach items="${qzbzs}" var="help" varStatus="status">
		            	<li><span class="dot">·</span><a href="${ctx}/xl_${help.id}.html" target="_blank" title="${help.title}">${help.title}</a></li>
					</c:forEach>
	            </ul>
            </div>
            <!--/求职帮助-->
        </div>
	 	<!-- /right -->
        <div class="clear"></div>
    </div>
    <script>
    $(function(){
        for(var i=0,_length = $(".community-img li").length;i<_length;i++){
		    var _text = $(".community-img li").eq(i).find("h2").text(); 
		    var _text_length = $(".community-img li").eq(i).find("h2").text().length; 
		    if(_text_length>27){  //文本长度大于27则省略，小于则不出现省略号
		    	_text = _text.substr(0,26) + '...' ;
		        $(".community-img li").eq(i).find("h2").html(_text);
		    }
     	}
    });
    </script>
</body>
</html>