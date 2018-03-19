<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="Qe3ngwHAwx" />
<title>项目详情_中国就业万里行</title>
</head>
<body>
    <!--jy-sec1-->
    <div class="jy-secN2">
    	<!--搜索-->
        <div class="jy-search">
        	<form action="${ctx}/trip10000/projects" method="get">
        			<input type="hidden" name="search_EQ_selectionProcess" value="${param.search_EQ_selectionProcess}"/>
	            	<input type="text" class="input tip-text"  placeholder="请输入CEO名字、学校名、项目名、团队名称"  name="search_LIKE_project" value="${param.search_LIKE_project}"/><input type="submit" value="快速搜索" class="btn"/>
			</form>
        </div>
        <!--/搜索-->
    </div>
    <!--/jy-sec1-->

	<!--筛选比赛场次-->
    <div class="jy-filter">
    	<div class="box-out">
        	<ul id="selection">
            	<li data="0" class="<c:if test="${empty param.search_EQ_selectionProcess || param.search_EQ_selectionProcess==0}">current</c:if>">全部<i class="arrow-d"></i><i class="arrow-r"></i></li>
                <li data="1" class="<c:if test="${param.search_EQ_selectionProcess==1}">current</c:if>">A轮（入围）<i class="arrow-d"></i><i class="arrow-r"></i></li>
                <li data="2" class="<c:if test="${param.search_EQ_selectionProcess==2}">current</c:if>">B轮（决赛）<i class="arrow-d"></i><i class="arrow-r"></i></li>
                <li data="3" class="<c:if test="${param.search_EQ_selectionProcess==3}">current</c:if>">C轮（总决赛）<i class="arrow-d"></i></li>
            </ul>
        </div>
    </div>
    <div class="dash-line"></div>
    <!--/筛选比赛场次-->
    
    <!--列表-->
    <div class="box-out">
    	<div class="jy-project">
    		<c:if test="${empty projectList.content}"><span style="font-size: 18px;margin-left: 36%">暂无最新项目</span></c:if>
            <c:forEach items="${projectList.content}" var="project" varStatus="status">
				<ul class="each">
            	<a href="${ctx }/trip10000/detail?projectId=${project.projectId}" target="_blank"><li><img src="${project.leadImage }" alt="" class="portrait"/></li></a>
                <a href="${ctx }/trip10000/detail?projectId=${project.projectId}" target="_blank"><li class="name">${project.ceoName }</li></a>
                <a href="${ctx }/trip10000/detail?projectId=${project.projectId}" target="_blank"><li class="camp">${project.school }</li></a>
                <li class="vote-g">
                    <p class="v" onclick="addVotingCount(${project.projectId});"><i></i><span>投TA一票</span></p>
                    <p class="n"><span id="votingCount${project.projectId}">${project.votingCount }</span>人</p>
                </li>
                <a href="${ctx }/trip10000/detail?projectId=${project.projectId}" target="_blank"><li>
                <c:if test="${not empty project.pdfFile }"><embed src="" class="pic" border="1" >
                <object classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" width="100%" height="100%" border="0" ><!--IE--> 
					<param name="_Version" value="65539"> 
					<param name="_ExtentX" value="20108"> 
					<param name="_ExtentY" value="10866"> 
					<param name="_StockProps" value="0"> 
					<param name="SRC" value="${project.pdfFile}"> 
					<embed src="${project.pdfFile}"  href="" class="pic"></embed><!--FF--> 
				</object> 
                </c:if>
                <c:if test="${empty project.pdfFile }"><image src="" class="pic" ></c:if>
                </li></a>
                <a href="${ctx }/trip10000/detail?projectId=${project.projectId}" target="_blank"><li class="title">《${project.title }》</li></a>
                <a href="${ctx }/trip10000/detail?projectId=${project.projectId}" target="_blank"><li class="team">团队名称：${project.teamName }</li></a>
                <a href="${ctx }/trip10000/detail?projectId=${project.projectId}" target="_blank"><li class="des">${tc:subString(project.introduction, 200)}</li></a>
            </ul>
			</c:forEach>
        </div>
          <!--分页-->
          <c:if test="${not empty projectList.content }">
        <div class="paging pdb30">
        	<tags:paginationRewrite paginationSize="5" page="${projectList }"/>
        </div>
        </c:if>
        <!--/分页-->
    </div>
    <!--/列表-->
    <script src="${ctx}/static/js/layer.min.js"></script>
    <script type="text/javascript">
    
    $("#selection li").click(function(){
    	window.location.href="${ctx}/trip10000/projects?search_EQ_selectionProcess="+$(this).attr("data")
    			+"&search_LIKE_project="+$("input[name='search_LIKE_project']").val();
    });
    
    function addVotingCount(projectId){
		$.post("/trip10000/registration/addVotingCount",{projectId:projectId},function(result){
			if(result=="error"){
				$.layer({
					type: 2,
					title: '用户登录',
					shadeClose: true,
					area : ['400px' , '350px'],
					offset: [($(window).height() - 300)/2+'px', ''],
					iframe: {src: '/login/iframe'}
				});	
			}else if(result=="null"){
				alert("系统繁忙，请稍后再试")
			}else if(result=="failure"){
				alert("您的票数已用光")
			}else{
				$("#votingCount"+projectId).text(result);
			}
		});
	}
	function reload(){
		window.location.reload();
	}
    </script>
</body>
</html>