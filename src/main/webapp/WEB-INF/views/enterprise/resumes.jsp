<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收到的简历</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
</head>
<body>
  <!---->
    <div class="box-out mt15">
    	<!--left-->
        <jsp:include page="/WEB-INF/layouts/enterpriseLeft.jsp"></jsp:include>
        <!--/left-->
       <!--right-->
        <div class="ent-box-r-wrapper">
            <div class="ent-box-r">
            	<!--暂无简历-->
            	 <c:if test="${empty page.content }">
                <div class="ent-no-result">
                	<span class="icon-smile"><i></i></span>暂时还没收到简历哦~！<a href="${ctx }/enterpriseJob/jobPost" class="ml10">发布岗位</a>
                </div>
                <div class="clear"></div> 
                </c:if>
                <!--/暂无简历-->
                <c:if test="${not empty page.content }">
            	<!--收到的简历-->
                <div class="ent-resume">
                	<div class="btn-with-des"><a href="javascript:void(0)" class="btn-g-blue fl" id="select_all">全选</a><a href="javascript:void(0)" id="delAll" class="btn-g-2 ml10">删除所有</a></div>
                    <div class="table-g">
                        <ul class="row-each col-name">
                        	<li class="c"><input type="checkbox" id="input_check" onclick="SelcetAll()"/></li>
                            <li class="name">姓名</li>
                            <li class="sex">性别</li>
                            <li class="age">年龄</li>
                            <li class="edu">学历</li>
                            <li class="m">用户手机</li>
                            <li class="area">所在地</li>
                            <li class="job">投递岗位</li>
                            <li class="oper">操作</li>
                        </ul>
                       <c:forEach items="${page.content }" var="resumeBox">
                        <ul class="row-each">
                        	<li class="c"><input type="checkbox" name="chk_list" onclick='CheckChange()' /></li>
                        	<input type="hidden" value="${resumeBox.id }"/>
                            <li class="name">${resumeBox.resume.name }</li>
                            <li class="sex"><c:if test="${resumeBox.resume.gender==1 }">男</c:if><c:if test="${resumeBox.resume.gender==2 }">女</c:if></li>
                            <li class="age">${resumeBox.resume.age }</li>
                            <li class="edu">${cdc:converDicEducation(resumeBox.resume.education) }</li>
                            <li class="m">${resumeBox.resume.mobile }</li>
                            <li class="area">${resumeBox.resume.residentcity.cityName }</li>
                            <li class="job"><a href="${ctx}/zhaopin_${resumeBox.jobBase.id}.html">${resumeBox.jobBase.title }</a></li>
                            <li class="oper"><a id="a_del" href="javascript:void(0)">删除</a></li>
                        </ul>
                        </c:forEach>
                    </div>
                    <div class="clear"></div>
                     <!--分页-->
                     <c:if test="${not empty page.content}">
		            	 <div class="paging"><tags:pagination paginationSize="5" page="${page }" pageType="enterprise/resumes?"/></div>
		           	 </c:if> 
                     <!--/分页-->
                </div>
                <!--/收到的简历-->
                </c:if>
            </div>
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
    <!---->
	<script type="text/javascript">
    $(function(){
    	<c:if test="${not empty error}">
			alert("${error}");
		</c:if>
		// 全选 
		$("#select_all").click(function() { 
			$("#input_check").click();
			 if ($("#input_check").is(":checked")) {
				 	$("#select_all").text("取消全选")
		        } else {
		        	 $("#select_all").text("全选")
		        }
		});
		$("#a_del").click(function(){
			if(confirm('确实要删除吗?'))
	 	    {
				$.post("${ctx }/enterprise/delResume",{idList:$(this).parent().parent().find("input:hidden").val()},
					function(result){alert("删除成功！"); window.location.reload();});
	 	    }
		})
    });
    $("#delAll").click(function(){
    	var idList='';
    	var arrChk=$("input[name='chk_list']:checked");
	    $(arrChk).each(function(){
	    	idList+=$(this).parent().parent().find("input:hidden").val()+",";
	    });  
	    idList=idList.substring(0,idList.length-1);
	    if(idList=='')
	    {
	    	alert("请选择你要删除的简历！");
	    }
	    else{
	    	//防止重复提交
			$("#delAll").unbind();
	    	if(confirm('确实要删除吗?'))
	 	    {
	 	    	$.post("${ctx }/enterprise/delResume",{idList:idList},function(result){
	 	    		alert("删除成功！");
	 	    		window.location.reload();
	 	    	})
	 	    }
	    }
    })
    //全选
    function SelcetAll() {
        var temp = document.getElementsByName("chk_list");
        for (var index = 0; index < temp.length; index++) {
            temp[index].checked = document.getElementById("input_check").checked;
        }
    }
	
    function CheckChange() {
        var temp = document.getElementsByName("chk_list");
        for (var index = 0; index < temp.length; index++) {
            if (temp[index].checked == false) {
                document.getElementById("input_check").checked = false;
                return;
            }
            if (index == temp.length - 1) {
                document.getElementById("input_check").checked = true;
            }
        }
    }
    </script>
</body>
</html>