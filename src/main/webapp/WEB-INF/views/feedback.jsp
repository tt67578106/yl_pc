<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户反馈</title>
</head>
<body>
    <div class="nav-g"><a href="${ctx}/">首页</a>&gt;<a href="${ctx}/feedback.html" class="red">意见反馈</a></div>
    <!--意见反馈-->
    <div class="w990 feedback bg-white">
    	<div class="caution-tip">您好！您的感受和建议非常宝贵，我们将为您提供更优质的服务，感谢您对优蓝网的支持！</div>
        <div class="form-tips mt30"><!--这里是验证提示信息--></div>
        <form action="${ctx}/feedback" method="post" id="feedback_form">
        <div class="form-each">
                	<span class="form-each-name"><label class="mark">*</label>反馈类型：</span>
                    <div class="select-g select-g-1 select-g-big">
                      <div class="select-g-title"><span data-default="请选择反馈类型">请选择反馈类型</span><i></i></div>
                      <div class="select-g-options">
                      <ul>
                      <li>不限</li>
                        <li>报名不成功</li>
                        <li>无法登录注册</li>
                        <li>没有我要的岗位/企业</li>
                        <li>信息错误/虚假</li>
                        <li>页面无法打开/访问过慢</li>
                        <li>使用不方便</li>
                        <li>其他建议反馈</li>
                      </ul>
                      </div>
                      <select class="original-select" id="input_types" name="types">
                      	<option value="">请选择反馈类型</option>
                        <option value="1">报名不成功</option>
                        <option value="2">无法登录注册</option>
                        <option value="3">没有我要的岗位/企业</option>
                        <option value="4">信息错误/虚假</option>
                        <option value="5">页面无法打开/访问过慢</option>
                        <option value="6">使用不方便</option>
                        <option value="7">其他建议反馈</option>
                      </select>
                    </div>
               </div>
        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>意见内容：</span><textarea id="input_content" name="content" class="textarea-g"></textarea></div>
        <div class="form-each"><span class="form-each-name"><label class="mark">*</label>联系方式：</span><input id="input_contactInfo" name="contactInfo" type="text" class="form-each-input"/></div>
       	<div class="form-btn"><label class="btn-g-out"><input type="submit" value="确认提交" class="btn-g w150"/></label></div>
        </form>
    </div>
    <!--/意见反馈-->
    <script>
	$(function(){
    <c:if test="${not empty msg}">alert("${msg }");</c:if>
    $("#feedback_form").submit(function(){
    	var t = $("#input_types").val();
    	var content = $.trim($("#input_content").val());
    	var contact = $.trim($("#input_contactInfo").val());
    	if(t == ""){
    		alert("请选择反馈类型");
        	return false;
    	}else if($.trim(content).length<5){
    		alert("请填写意见内容,最少5个字");
        	return false;
    	}else if($.trim(contact).length<1){
    		alert("请填写你的联系方式");
    		return false;
    	}
    });		
	});
   
    </script>
</body>
</html>