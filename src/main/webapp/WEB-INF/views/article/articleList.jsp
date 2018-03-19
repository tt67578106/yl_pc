<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:set var="strArticleType" value="${cdc:getArticleTypeByCode(articleTypeCode) }" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:if test="${not empty param.search_LIKE_keywords && param.search_LIKE_keywords!='0'}">
	<c:set var="keyWord" value="${param.search_LIKE_keywords}" />
	<c:set var="strArticleType" value="${param.search_LIKE_keywords}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:choose>
	<c:when test="${not empty keyWord }">
	<c:forEach items="${page.content }" var="article" varStatus="status">
 		<c:if test="${status.index==0 }">
			<c:set var="top3ArticleTitle0" value="${article.title}" />
		</c:if> 
		<c:if test="${status.index==1 }">
			<c:set var="top3ArticleTitle1" value="${article.title}" />
		</c:if> 
		<c:if test="${status.index==2 }">
			<c:set var="top3ArticleTitle2" value="${article.title}" />
		</c:if> 
	</c:forEach>
		<title>${keyWord}_${keyWord}相关新闻|知识|技巧_<c:if test="${pageNumber>1 }">_第${pageNumber}页</c:if></title>
		<meta name="keywords" content="${keyWord}"/>
		<meta name="description" content="优蓝网${keyWord}栏目，展示${keyWord}相关知识及新闻：${top3ArticleTitle0}，${top3ArticleTitle1}，${top3ArticleTitle2}"/>
	</c:when>
	<c:otherwise>
		<title>${title}</title>
		<meta name="keywords" content="${keywords}"/>
		<meta name="description" content="${description}"/>
	</c:otherwise>
</c:choose>
</head>
<body>
   <div class="nav-g">
	   	<a href="${ctx}/">优蓝网</a>&gt;
	   	<a href="${ctx}/zone">优蓝社区</a>&gt;
	   	<c:choose>
		   	<c:when test="${not empty keyWord }">
				<a href="${ctx}/tag_${keyWord }_1/" >${strArticleType }</a>
			</c:when>
			<c:otherwise>
			   	<a href="${ctx}/s_articleType_${articleTypeCode}" >${strArticleType }</a>
			</c:otherwise>
		</c:choose>
   	</div>
    <div class="w1200">
    <div class="box-l-wrapper box-l box-l-img">
	    <div class="box-g">
            <h1 class="company-title">${strArticleType }</h1>
            <p class="mb10">该栏目共有${totalCount}个『${strArticleType }』资讯</p>
        </div>
    	<!--article-list-->
    	<div class="article-list article-list-wrap bg-white img-hover">
        	<!---->
        	<c:forEach items="${page.content }" var="article" varStatus="status">
        	<ul class="article-list-each <c:if test="${not empty keyWord }">label-list-each</c:if>">
            	<li class="pic"><a href="${ctx}/xl_${article.id}.html" target="_blank"><img src="${cdc:getImagePath320(article.thumbnialImage.imgpath) }" alt="${article.title}" /></a></li>
                <li class="title"><h3><c:if test="${article.isRecommend == 1}"><span class="tag">推荐</span></c:if><a href="${ctx}/xl_${article.id}.html" target="_blank" title="${article.title }"> ${article.title }</a></h3></li>
                <li class="num"><span class="n">浏览：${article.viewCount == null?0:article.viewCount }次</span><span class="t">发布时间：${article.publishTimeString }</span></li>
                <li class="summary">${article.introduction }</li>
                <c:if test="${not empty keyWord }">
                	<li class="labels">
                		<c:forEach items="${article.keywords }" var="keyword">
                			<a href="${ctx }/tag_${keyword }_1/"><span class="label">${keyword }</span></a>
                		</c:forEach>
                	</li>
            	</c:if>
            </ul>
        	</c:forEach>
            <!---->
            <!--分页-->
			<tags:pagination paginationSize="5" page="${page }" pageType="article/?"/>
        	<!--分页-->
        </div>
        </div>
        <!--/article-list-->
        <!--right-->
		<div class="box-r-wrapper">
			<!--栏目-->
			<div class="box-r">
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
            
            <!-- 相关招聘 -->
             <div class="box-r recruit-tags recruit-tags-sty mt10 bd0">
            	<div class="title-bar"><span>招聘推荐</span></div>
            	<ul class="about-recruit">
            	<c:forEach items="${relatedRecruitments }" var="recruitment">
	            	<a href="${recruitment.resource.linkUrl }"><li>${recruitment.resource.title }</li></a>
           		</c:forEach>
	            </ul>
            </div>
            <!--/相关招聘-->
		</div>
		<!--/right-->
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
</body>
</html>