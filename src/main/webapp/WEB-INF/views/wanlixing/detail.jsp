<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="Qe3ngwHAwx" />
<title>项目详情_中国就业万里行</title>
</head>
<body>
    <!--轮播图-->
    <div class="jy-carousel-out">
    	 <div class="jy-carousel box-out">
    	 <c:if test="${not empty project.pdfFile }">
           <object classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" width="1000" height="630" border="0" ><!--IE--> 
					<param name="_Version" value="65539"> 
					<param name="_ExtentX" value="20108"> 
					<param name="_ExtentY" value="10866"> 
					<param name="_StockProps" value="0"> 
					<param name="SRC" value="${project.pdfFile}"> 
					<embed src="${project.pdfFile}"  href="" class="pic" width="1000" height="630"
					style="margin-top: 35px" autostart=true loop=true></embed><!--FF--> 
			</object>
			</c:if>
            <c:if test="${empty project.pdfFile }"><image src=""></c:if>
    	</div>
    </div>
    <!--/轮播图-->
    
    <!--选手信息-->
    <div class="box-out">
        <ul class="jy-player">
            <li class="pic"><img src="${image.imgPath}" /></li>
            <li class="name">${project.ceoName}</li>
            <li class="sc">学校：${project.school}</li>
            <li class="gr">团队名称：${project.teamName}</li>
            <li class="vote">
            	<p class="v" onclick="addVotingCount(${project.id });"><i></i><span>投TA一票</span></p>
                <p class="n"><span id="votingCount">
                	<c:if test="${empty project.votingCount}">0</c:if>${project.votingCount}</span>人</p>
            </li>
        </ul>
    </div>
    <!--/选手信息-->
    
    <!--项目介绍-->
    <div class="box-out">
    	<div class="hr-g"></div>
    	<p class="f18 mt10">${project.title}</p>
        <div class="text-g mt10">
        	${project.introduction}
        </div>
    </div>
    <!--/项目介绍-->
    
<!--     评委点评
    <div class="box-out">
    	<h2 class="jy-title">评委点评</h2>
        <div class="jy-comment">
        	<ul class="each">
            	<li class="pic"><img src="../images/datas/portrait1.jpg" /></li>
                <li class="name">张三</li>
                <li class="post">某某大学教授</li>
                <li class="text"><i></i>
                	<span>
                    这里是评论内容这里是评论内容
                    </span>
                </li>
            </ul>
        </div>
    </div>
    /评委点评 -->
    
    <!--项目风采展示-->
    <div class="box-out">
    	<h2 class="jy-title">团队风采展示</h2>
    	<ul class="jy-pic-text">
 		<c:forEach items="${images}" var="image" varStatus="status">
        		<li><img src="${image.imgPath }" alt="${image.imgName }" /><p>${image.introduction }</p></li>
		</c:forEach>
        </ul>
        <div class="clear"></div>
    </div>
    <!--/项目风采展示-->
    
    <!--微博热议-->
    <div class="box-out">
    	<h2 class="jy-title">微博热议</h2>
    </div>
    <!--/微博热议-->
    <script src="${ctx}/static/js/layer.min.js"></script>
    <script type="text/javascript">
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
				$("#votingCount").text(result);
			}
		});
	}
	function reload(){
		window.location.reload();
	}
    </script>
</body>
</html>