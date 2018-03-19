<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %> 
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="${fn:replace(currentArticle.title, '\"',' ')}"/>
<meta name="description" content="${fn:replace(currentArticle.title, '\"',' ')}，${fn:replace(currentArticle.introduction, '\"',' ')}。更多精彩内容尽在优蓝网[www.youlanw.com]"/>
<title>${currentArticle.title }</title>
</head>
<body>
    <!--文章内容-->
    <div class="nav-g"><a href="${ctx}/">优蓝网</a>&gt;<a href="${ctx}/zone">优蓝社区</a>&gt;<a href="${ctx}/s_articleType_${currentArticle.articleType }">${cdc:getArticleTypeByCode(currentArticle.articleType) }</a>&gt;<a href="${ctx}/xl_${currentArticle.id}.html" >${currentArticle.title }</a></div>
	<div class="w1200">
		<!--left-->
		<div class="box-l-wrapper">
			<div class="article-list article-list-wrap article-box">
				<h1>${currentArticle.title }</h1>
				<p class="page-info">
					发布时间：${currentArticle.publishTimeString }<span class="ml20">浏览：${currentArticle.viewCount }次</span>
				</p>
				<div class="article-top">
					<div class="article-label">
						<c:forEach items="${currentArticle.keywords}" var="keyword">
							<a href="${ctx }/tag_${keyword }_1/"><span>${keyword }</span></a>
						</c:forEach>
					</div>
					<p class="article-intro">${currentArticle.introduction }</p>
				</div>
				<div class="article-content">
					<p>${currentArticle.content.content }</p>
				</div>
				<!--分享-->
				<div class="bdsharebuttonbox">
					<a href="#" class="bds_more" data-cmd="more"></a>
					<a title="分享到QQ好友" href="#" class="bds_sqq" data-cmd="sqq"></a>
					<a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a>
					<a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"> </a>
					<a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"> </a>
					<a title="分享到腾讯微博" href="#" class="bds_tqq" data-cmd="tqq"></a>
					<a title="分享到百度贴吧" href="#" class="bds_tieba" data-cmd="tieba"></a>
					<a title="分享到豆瓣网" href="#" class="bds_douban" data-cmd="douban"></a>
					<a title="分享到天涯社区" href="#" class="bds_ty" data-cmd="ty"></a>
					<a title="分享到美丽说" href="#" class="bds_meilishuo" data-cmd="meilishuo"></a>
					<a title="分享到蘑菇街" href="#" class="bds_mogujie" data-cmd="mogujie"></a>
				</div>
				<!--分享-->
				<div class="article-link">
					<c:if test="${preArticle != null}"><div class="fl"> 上一篇：<a href="${ctx }/xl_${preArticle.id}.html">${preArticle.title}</a></div>
					</c:if>
					<c:if test="${preArticle == null&&nextArticle != null}">
						<div class="fl">下一篇：<a href="${ctx}/xl_${nextArticle.id}.html">${nextArticle.title}</a></div>
					</c:if>
					<c:if test="${preArticle != null&&nextArticle != null}">
						<div class="fr">下一篇：<a href="${ctx}/xl_${nextArticle.id}.html">${nextArticle.title}</a></div>
					</c:if>
				</div>
				<!--相关文章推荐-->
				<div class="article-relative">
					<h2 class="column-title">相关文章推荐</h2>
					<ul>
						<c:forEach items="${recommendArticleList }" var="recommendArticle">
							<li>
								<em></em>
								<a href="${ctx}/xl_${recommendArticle.id}.html">${recommendArticle.title}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!--/文章内容-->
		</div>
		<!--/left-->

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
             <div class="box-r recruit-tags recruit-tags-sty  mt10 bd0">
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
	<script>
		window._bd_share_config = {
			"common" : {
				"bdSnsKey" : {},
				"bdText" : "",
				"bdMini" : "2",
				"bdMiniList" : false,
				"bdPic" : "",
				"bdStyle" : "0",
				"bdSize" : "32"
			},
			"share" : {},
			"image" : {
				"viewList" : [ "sqq", "qzone", "weixin", "tsina", "tqq" ],
				"viewText" : "分享到：",
				"viewSize" : "16"
			},
			"selectShare" : {
				"bdContainerClass" : null,
				"bdSelectMiniList" : [ "sqq", "qzone", "weixin", "tsina", "tqq", "tieba", "douban", "ty", "meilishuo", "mogujie" ]
			}
		};
		with (document)
			0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
	</script>
</body>
</html>