<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions" %> 
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>我要提问</title>
<meta name="description" content=""/>
<meta name="keywords" content="" />
</head>
<body>
    <!--文章内容-->
    <div class="nav-g"><a href="${ctx}/">优蓝网</a>&gt;<a href="${ctx}/wenda/so_0_${not empty companyId?companyId:0}_${not empty jobId?jobId:0}_1/">求职问答</a>&gt;我的提问</div>
    <div class="w1200 mt10">
    	<div class="my-question">
	       <h2>我要提问</h2>
	       <form action="" id="advisory_form">
	       <input type="hidden" name="companyId" value="${companyId }" />
	       <input type="hidden" name="jobId" value="${jobId }" />
	       <div class="box-l-wrapper">
	       	    <div class="form-each">
	    			<span class="form-each-name">问题标题</span>
	    			<input class="form-each-input" name="title" type="text" id="advisory_title" value="" placeholder="" /> 
	    		</div>
	    		<div class="form-each">
	    			<span class="form-each-name">问题描述</span>
	    			<textarea class="textarea-g" name="comment" id="advisory_content"></textarea>
	    		</div>
	    		<div class="form-each">
	    			<input class="btn-g btn-g-small w120 ml130" type="button" value="发布问题" /> 
	    		</div>
		    </div>
			</form>
	       <div class="box-r-wrapper">
	        	<h3>如何写问题标题？</h3>
	        	<p class="mt20">描述清晰并带有具体职位或者企业的问题标题，可以帮助你更快获得回答。</p>
	        	<p>中介/投诉/无关求职的问题会被删除。标题长度至少10字以上。</p>
	        	<h3 class="mt40">如何填写详细描述?</h3>
	        	<p class="mt20">详细填写你对企业、在招岗位等相关疑问描述，能够更好地辅助别人理解你的问题，从而获得更高质量的回答。</p>
	        </div>
	        <div class="clear"></div>
	    </div>
    </div>
    <!---->
    <script type="text/javascript">
	/*提交网友问答*/
	$(".btn-g-small").click(function(){
		$.post("${ctx}/login/veryfy",function(result){
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
				var content = $("#advisory_content").val();
				var title = $("#advisory_title").val();
				if($.trim(title).length==0){
					layer.alert("请输入您的问题的标题");
				}else if($.trim(content).length==0){
					layer.alert("请输入您的问题描述");
				}else if($.trim(content).length<5){
					layer.alert("输入的信息不能少于5个字");
				}else{
					$.post("${ctx}/wenda/ask",$("#advisory_form").serialize(),function(result){
						if(result=="success"){
							$("#advisory_content").val("");
							layer.alert("提交成功！",9);
							window.location.href="${ctx}/${backUrl}";
						}else{
							layer.alert("提交失败")
						}
					});
				}
				$("#advisory_content").focus();
			}
		});
	});
	function reload(){
		window.location.reload();
	}
    </script>
</body>
</html>