<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %> 
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>${advisory.title }_求职问答</title>
<meta name="keywords" content="${advisory.title }" />
<meta name="description" content=""/>
</head>
<body>
    <!--文章内容-->
    <div class="nav-g"><a href="${ctx}/">优蓝网</a>&gt;<a href="${ctx}/zone">优蓝社区</a>&gt;<a href="${ctx}/wenda">求职问答</a>&gt;<a>${advisory.title }</a></div>
    <div class="w1200 mt10">
    	<!--基本信息-->
       <div class="box-l-wrapper">
       	    <!--筛选-->
          	<div class="box-l bd0">
          		<div class="consult-search">
          			<div class="search-ask">
          				<form action="${ctx }/wenda/${advisory.askId}.html" method="get">
	          				<input type="text" class="tip-text" name="key" value="${key }" placeholder="提问前不妨先搜索下，看看你的问题是否有人询问过...." />
		            	    <input type="submit" value="搜索" class="btn"/>
	            	    </form>
	            	    <%-- <a href="${ctx }/advisory/ask" class="btn btn-ask ml15"><span>+</span>&nbsp;我要提问</a> --%>
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
          		</div>
          	</div>
          	<!--/筛选-->
       	    <div class="box-l mt15">
		        <div class="consult-detail">
		    		<div class="yl-ask">
		    			<ul class="question-each">
                            <li>
                            	<c:choose>
                            		<c:when test="${not empty advisory.askHeadImage }"><img src="${cdc:getImagePathO(advisory.askHeadImage)}" alt="${not empty advisory.askUserName?advisory.askUserName:'优蓝网友' }_头像"></c:when>
                            		<c:otherwise><img src="${ctx }/static/images/man.jpg" alt="${not empty advisory.askUserName?advisory.askUserName:'优蓝网友' }_默认头像"></c:otherwise>
                            	</c:choose>
                            </li>
                            <li class="name">
                            	<span class="mr10">${not empty advisory.askUserName?advisory.askUserName:'优蓝网友' }</span><span class="time">${advisory.createTimeString }</span>
                            	<c:if test="${not empty jobBase }">
                            		<p class="title-r"><span>相关职位</span><a href="${ctx }/zhaopin_${jobBase.id}.html"<span class="blue">${jobBase.title }</a></span></p>
                            	</c:if>
                            </li>
                             <h1><li class="title"><i></i>${advisory.title }</li></h1>
                             <li class="content">${advisory.introduction }</li>
                             <div class="clear"></div>
		    			</ul>
		    			<c:forEach items="${advisory.userCommunityTalkContentVo }" var="reply">
		    				<c:if test="${not empty reply.replyUserId&&reply.replyUserId==53 }">
	                        <ul class="question-each question-ask">
	                            <h2>优蓝官方回复</h2>
	                        	<i class="star"></i>
	                            <li><img src="${cdc:getImagePathO(reply.replyHeadImage)}" alt=""></li>
	                            <li class="name">
	                            	<span class="mr10">优蓝网顾问</span><span class="official mr10">官方</span><span class="time">发表于 ${reply.createTimeString }</span>
	                            </li>
	                            <li class="content">${reply.content }</li>
	                            <div class="clear"></div>
	                        </ul>
		    				</c:if>
		    			</c:forEach>
		    		</div>
		        </div>
		        
		    </div>
		    <div class="box-l all-ask mt15">
		    	<h2>全部回答<span>（${fn:length(advisory.userCommunityTalkContentVo) }）</span></h2>
    			<c:forEach items="${advisory.userCommunityTalkContentVo }" var="reply">
    			<c:if test="${reply.replyUserId!=53 }">
	    			<ul class="question-each">
	                    <li>
						<c:choose>
                        	<c:when test="${not empty reply.replyHeadImage }"><img src="${cdc:getImagePathO(reply.replyHeadImage)}" alt="问题回复者_${not empty reply.replyUserName?reply.replyUserName:'优蓝网友' }_头像"></c:when>
                        	<c:otherwise><img src="${ctx }/static/images/man.jpg" alt="${not empty reply.replyUserName?reply.replyUserName:'优蓝网友' }_默认_头像"></c:otherwise>
                        </c:choose>
						</li>
	                    <li class="name">
	                    	<span class="mr10">${not empty reply.replyUserName?reply.replyUserName:'优蓝网友' }</span><span class="time">${reply.createTimeString }</span>
	                    </li>
	                     <li class="content">${tc:highlightText(reply.content, key)}</li>
	                     <div class="clear"></div>
	    			</ul>
    			</c:if>
    			</c:forEach>
    		</div>
    		<!--我要回答-->
    		<div class="box-l mt15 bd0">
    			<textarea class="textarea-answer tip-text tip-text-input"  rows="20" id="txt_content" placeholder="请输入您要回答的内容..."></textarea>
    			<a href="javascript:void(0);" class="btn btn-answer right mt15 a_reply">我要回答</a>
    		</div>
    		<!--/我要回答-->
	    </div>
       <!--/基本信息-->
       <div class="box-r-wrapper">
        	<!--查看企业主页-->
        	<c:set var="tempCompany" value="" />
        	<c:if test="${not empty company }">
	        	<c:set var="tempCompany" value="${company }" />
        	</c:if>
        	<c:if test="${empty tempCompany && not empty jobBase }">
        		<c:set var="tempCompany" value="${jobBase.company }" />
        	</c:if>
        	<c:if test="${not empty tempCompany }">
	       	<div class="box-r bd0">
	        	<div class="job-com">
	            	<a href="${ctx }/qiye_${tempCompany.id }.html" class="more" target="_blank" title="${tempCompany.name }">
	            		<h2>${tempCompany.name }</h2>
	            	</a>
	                <div class="inner">
	               		<c:if test="${not empty tempCompany.logo.imgpath}">
				        	<img src="${cdc:getImagePath320(tempCompany.logo.imgpath)}" alt="${tempCompany.abbreviation }_logo" />
		                </c:if>
	                    <p><span class="c999">企业规模：</span>${cdc:convertDicStaffscale(tempCompany.staffscale)}</p>
	                    <p><span class="c999">所属行业：</span>${cdc:convertDicIndustryType(tempCompany.industryid)}</p>
	                    <p><span class="c999">平均薪资：</span>${averageSalary }元/月</p>
	                </div>
	            </div>
	            <div class="job-com-a"><a href="${ctx }/company/album_${company.id }/">企业相册&gt;&gt;</a></div>
            	<div class="job-com-a"><a href="${ctx }/company/job_${company.id }/">在招岗位&gt;&gt;</a></div>
            	<div class="job-com-a"><a href="${ctx }/wenda/so_0_${company.id}_0_1/">企业问答&gt;&gt;</a></div>
	        </div>
	        </c:if>
	        <!--/查看企业主页-->
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
	        
            <!--相关问答-->
        	<div class="box-r recruit-tags  mt10 bd0">
            	<div class="title-bar"><span>相关问答</span><a href="${ctx }/wenda/so_0_${not empty company.id?company.id:0}_${not empty jobBase.id?jobBase.id:0}_1/" class="blue right mt10">更多&gt;&gt;</a> </div>
            	<ul class="about-recruit strategy">
            		<c:forEach items="${similarAdvisorys }" var="similarAdvisory">
		            	<li><span class="dot">·</span><a href="${ctx }/wenda/${similarAdvisory.askId }.html">${similarAdvisory.title }</a></li>
            		</c:forEach>
	            </ul>
            </div>
            <!--/相关问答-->
            
        </div>
        <div class="clear"></div>
    </div>
    <script type="text/javascript">
    $(function(){
    	 $('.a_reply').click(function(){
    		 var content = $("#txt_content").val();
				$.post("${ctx}/login/veryfy",function(result){/*判断是否登录*/
    			if(result=="unlogin"){
    				$.layer({
    					type: 2,
    					title: '用户登录',
    					shadeClose: true,
    					area : [ '655px', '380px' ],
    					offset: [($(window).height() - 600)/2+'px', ''],
    					iframe: {src: '/login/iframe'}
    				});
    			}else{
    				if($.trim(content).length==0 || content == ""){
    					layer.alert("请输入您的要回复的内容");
    				}else if($.trim(content).length<5){
    					layer.alert("输入的信息不能少于5个字");
    				}else{
    					$.post("${ctx}/job/postReply",{"userCommunityTalkId":"${advisory.askId}","content": content},function(result){
    						if(result=="success"){
    							layer.alert("提交成功！",9);
    							window.location.reload();
    						}else{
    							layer.alert("提交失败!")
    						}
    					});
    				}
    			}
    		});
    	 });
    });
    /* 多行文本省略 */
    $(function(){
        for(var i=0,_length = $(".recruit-tags ul li").length;i<_length;i++){
                  var _text = $(".recruit-tags ul li").eq(i).find("a").text(); 
                  var _text_length = $(".recruit-tags ul li").eq(i).find("a").text().length; 
                  if(_text_length>35){  //文本长度大于27则省略，小于则不出现省略号
                      _text = _text.substr(0,34) + '...' ;
                      $(".recruit-tags ul li").eq(i).find("a").html(_text);
                  }
      }
    });
    function reload(){
		window.location.reload();
	}
    </script>
</body>
</html>