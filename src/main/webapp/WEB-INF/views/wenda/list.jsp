<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<c:set var="seachCompany" value="${not empty jobBase.company?jobBase.company:company}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<c:choose>
	<c:when test="${not empty seachCompany }">
		<title>${fn:trim(seachCompany.abbreviation) }怎么样_${fn:trim(seachCompany.name) }工资待遇|打工生活咨询_求职问答</title>
		<meta name="keywords" content="${fn:trim(seachCompany.abbreviation) },${fn:trim(seachCompany.abbreviation) }怎么样,${fn:trim(seachCompany.abbreviation) }工资"/>
		<meta name="description" content="优蓝社区提供${fn:trim(seachCompany.abbreviation) }咨询问答互动服务，对${fn:trim(seachCompany.abbreviation) }求职招聘、工资待遇、打工生活等各方面提问或回答，了解或发布${fn:trim(seachCompany.abbreviation) }最新动态，是打工者求职的乐园" />
	</c:when>
	<c:otherwise>
	<c:if test="${not empty param.search_LIKE_title && param.search_LIKE_title!='0' }">
		<title>${param.search_LIKE_title }_${param.search_LIKE_title }最新求职问答|咨询动态_求职问答</title>
		<meta name="keywords" content="${param.search_LIKE_title }"/>
		<meta name="description" content="优蓝社区提供${param.search_LIKE_title }相关最新咨询问答互动服务，了解与${param.search_LIKE_title }相关的求职招聘、工资待遇、打工生活等各方面提问、回答及最新动态，打工难就上优蓝社区" />
	</c:if>
	<c:if test="${empty param.search_LIKE_title||param.search_LIKE_title=='0' }">
		<title>求职问答_蓝领工人求职咨询_优蓝社区</title>
		<meta name="keywords" content="求职问答,蓝领求职咨询,优蓝社区"/>
		<meta name="description" content="优蓝社区求职问答栏目，专业求职顾问免费为您提供求职咨询问答，讨论名企工资待遇，工作环境，企业动态，最新招聘信息等内容，是蓝领打工者求职生活的乐园" />
	</c:if>
	</c:otherwise>
</c:choose>
<style>
.gray-label {margin-right: 9px !important;}
</style>
</head>
<body>
    <!--文章内容-->
    <div class="nav-g"><a href="${ctx}/">优蓝网</a>&gt;<a href="${ctx}/zone">优蓝社区</a>&gt;<a href="${ctx}/wenda">求职问答</a>&gt;
    <c:choose>
    	<c:when test="${not empty param.search_LIKE_title&&param.search_LIKE_title!='0'}">
    		 <a href="${ctx}/wenda/so_${param.search_LIKE_title }_0_0_1/">${param.search_LIKE_title }</a>
    	</c:when>
    	<c:when test="${not empty seachCompany}">
    		 <a href="${ctx}/wenda/so_0_${seachCompany.id}_0_1/">${fn:trim(seachCompany.abbreviation) }怎么样</a>
    	</c:when>
    </c:choose>
    </div>
	<div class="w1200 mt10">
    	<!--基本信息-->
       <div class="box-l-wrapper">
       	    <!--筛选-->
          	<div class="box-l bd0">
          		<div class="consult-search">
          			<div class="search-ask">
         				<input type="hidden" name="jobId" value="${jobBase.id }" />
         				<input type="hidden" name="companyId" value="${seachCompany.id }"  />
         				<input type="hidden" name="title" value="${param.search_LIKE_title }"  />
          				<input type="text"  class="tip-text" id="key" placeholder="提问前不妨先搜索下，看看你的问题是否有人询问过...." />
	            	    <input type="button" value="搜索" class="btn search-enter"/>
	            	    <%-- <a href="${ctx }/advisory/ask?companyId=${companyId}&jobId=${jobId}" class="btn btn-ask ml15"><span>+</span>&nbsp;我要提问</a> --%>
	                    <div class="clear"></div>
          			</div>
	            	<div class="hot-search mt10">
	            		<label>热门搜索：</label>
	            		<ul>
	            			<c:forEach items="${hotAdvisorys }" var="hotAdvisory">
		            			<a href="${hotAdvisory.resource.linkUrl }"><li>${hotAdvisory.resource.title }</li></a>
	            			</c:forEach>
	            			<div class="clear"></div>
	            		</ul>
	            	</div>
	            	<p class="c333 f16 mt5">企业问答</p>
	            	<c:if test="${not empty param.search_LIKE_title||not empty seachCompany||not empty jobBase}">
	            	<div class="company-consult">
	            		<c:if test="${not empty param.search_LIKE_title&&param.search_LIKE_title!='0'}">
	            			<h2 data-type="key" class="label-del">${param.search_LIKE_title }<span></span></h2>
	            		</c:if>
	            		<c:if test="${not empty seachCompany}">
	            			<h2 data-type="companyId" data-code="${seachCompany.id }"  class="label-del" >${seachCompany.name }<span></span></h2>
	            		</c:if>
	            		<c:if test="${not empty jobBase}">
	            			<h2 data-type="jobId" data-code="${jobBase.id }" class="label-del">${jobBase.title }<span></span></h2>
	            		</c:if>
	            		<span class="search-result">为您搜索到<span id="sp_total">${totalCount }</span>个问答</span>
	            	</div>
	            	</c:if>
          		</div>
          	</div>
          	<!--/筛选-->
       	    <div class="box-l mt15">
		        <div class="job-consult">
		        	<c:forEach items="${advisorys.content }" var="advisory">
		        		<ul>
			    			<li class="consult-r">
			    				<ul>
				    				<li><span class="blue-label">问</span>
				    				<a class="c666" href="${ctx }/qiye_${advisory.companyId }.html">${advisory.companyAbbreviation }</a>
				    				<span class="pl3 pr3">|</span>
				    				<a href="${ctx }/wenda/${advisory.askId }.html">
									<c:choose>
			    						<c:when test="${not empty param.search_LIKE_title&&param.search_LIKE_title != '0' }">${tc:highlightText(advisory.title, param.search_LIKE_title)}</c:when>
			    						<c:otherwise>${advisory.title}</c:otherwise>
			    					</c:choose>
			    					</a>
				    				<span class="mess">${advisory.createTimeString }&nbsp;&nbsp;${not empty advisory.askUserName?advisory.askUserName:'优蓝网友' }</span>
				    				</li>
			    					<c:choose>
			    						<c:when test="${not empty advisory.userCommunityTalkContentVo&&fn:length(advisory.userCommunityTalkContentVo)>0 }">
			    							<c:forEach items="${advisory.userCommunityTalkContentVo }" var="reply" varStatus="status">
			    								<c:if test="${status.index==0 }">
			    									<li class="c999"><span class="gray-label">答</span>${reply.content }<a href="${ctx }/wenda/${advisory.askId }.html" class="answer">我来回答&gt;</a></li>
			    								</c:if>
			    							</c:forEach>
			    							<c:if test="${fn:length(advisory.userCommunityTalkContentVo)>1 }"><li class="last"><a href="${ctx }/wenda/${advisory.askId }.html">查看其他两条问答&gt;</a></li></c:if>
			    						</c:when>
			    						<c:otherwise><li class="c999"><span class="gray-label">答</span>还没人回答此问题，我来第一个回答。<a href="${ctx }/wenda/${advisory.askId }.html" class="answer">我来回答&gt;</a></li></c:otherwise>
			    					</c:choose>
			    				</ul>
			    			</li>
			    			<div class="clear"></div>
		    			</ul>
		        	</c:forEach>
		        </div>
		        
		        <c:if test="${not empty advisorys.content}">
		             <div id="paginationId"><tags:urlrewrite_job_pagination paginationSize="5" page="${advisorys }" /></div>
		          </c:if>
        		<%-- <input type="hidden" value="2" id="hidden_pagenumber" />
    			<div class="consult-more <c:if test="${fn:length(advisorys)<5 }">hide</c:if>"><a href="javascript:void(0);" class="f14 blue a-more">查看更多问答&gt;&gt;</a></div> --%>
		    </div>
	    </div>
       <!--/基本信息-->
       <div class="box-r-wrapper">
            <!--热帖推荐-->
        	<div class="box-r recruit-tags  bd0">
            	<div class="title-bar pdt0"><span>热帖推荐</span><a href="${ctx }/s_articleType_3" class="blue right mt10">更多&gt;&gt;</a> </div>
            	<ul class="about-recruit strategy">
            		<c:forEach items="${helps}" var="help" varStatus="status">
		            	<li><span class="dot">·</span><a target="_blank" href="http://www.youlanw.com/xl_${help.id}.html" title="${help.title}">${help.title}</a></li>
					</c:forEach>
	            </ul>
            </div>
            <!--/热帖推荐-->
            <!--相关招聘-->
         	<div class="box-r recruit-tags bd0">
            	<div class="title-bar"><span>相关招聘</span></div>
            	<ul class="about-recruit">
	            	<c:forEach items="${relatedRecruitments }" var="recruitment">
		            	<a href="${recruitment.resource.linkUrl }"><li>${recruitment.resource.title }</li></a>
            		</c:forEach>
	            </ul>
            </div>
            <!--/相关招聘-->
        </div>
        <div class="clear"></div>
    </div>
    <!---->
    <script>
    	$(function(){
    		//删除搜索条件
	 		$(".company-consult h2 span").click(function(){
	 			if($(".company-consult h2").length == 1){
	 				$(this).parent().remove();
			    	$(".company-consult .search-result").remove();
		    	}else{
		    	    $(this).parent().remove();
		    	}
	 			var dataType = $(this).parent().attr("data-type");
	 			if(dataType=="key"){
	 				$("input[name='title']").val('');
	 			}
	 			if(dataType=="companyId"){
	 				$("input[name='companyId']").val('');
	 			}
	 			if(dataType=="jobId"){
	 				$("input[name='jobId']").val('')
	 			}
	 			searchWenDa();
		    });
    		//搜索
    		$(".search-enter").click(function(){
    			$("input[name='title']").val($("#key").val());
    			searchWenDa();
    		});
    	});
    	
    	function searchWenDa(){
 			var key = $("input[name='title']").val();
 			var companyId = $("input[name='companyId']").val();
    		var jobId = $("input[name='jobId']").val();
    		var condition = [];
    		if(key!=null && key!=""){
    	   		condition.push(key);
    		}else{
    			condition.push("0");
    		}
    		if(companyId!=null && companyId!=""){
    			condition.push(companyId);
    		}else{
    			condition.push("0");
    		}
    		if(jobId!=null && jobId!=""){
    			condition.push(jobId);
    		}else{
    			condition.push("0");
    		}
    		window.location.href="${ctx}/wenda/so_" + condition.join("_")+"_1/";
    	}
	</script>
</body>
</html>