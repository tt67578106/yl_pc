<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报名记录</title>
</head>
<body>
	<!--我的简历-->
    <div class="w990 mt20">
    	<!---->
    	<div class="box-g-l-user fl bg-white user-menu">
        	<div class="portrait-wrapper">
            	<img src="${cdc:getImagePath320(headimg.imgpath)}" alt="" id="head_img" class="portrait"/>
                <input type="file" title="上传头像" name="file" id="fileupload" value=""/>
            </div>
            <p class="text-center c999 mt10">建议上传图片尺寸200*200</p>
            <div class="user-menu-list mt10 fl">
            	<a href="${ctx}/my">我的简历</a>
            	<a href="${ctx}/my/apply">我的求职</a>
                <a href="${ctx}/my/signupRecord" class="current">报名记录</a>
                <%-- <a href="${ctx}/my/changePassword">修改密码</a> --%>
            </div>
        </div>
        <!--/-->
        <!---->
        <div class="box-g-r-user fr bg-white" id="div_record">
        	<!-- <ul class="record-each"><li class="red">2015年1月29日</li>
                <li>10:20 您报名了“<a href="" target="_blank">苏州华硕电子厂</a>”的工作岗位“<a href="" target="_blank">苏州华硕电子厂招聘普工，综合工资3500-5000元/月</a>”</li>
                <li>10:10 您报名了“<a href="" target="_blank">武汉联想</a>”的工作岗位“<a href="" target="_blank">高薪招聘普工包食宿</a>”</li>
            </ul> -->
			<div class="remove-border"></div>
        </div>
        <!--/-->
    	<div class="clear"></div>
    </div>
    <!--/我的简历-->
<script type="text/javascript">
$(function(){
	pageload(1);
});
function pageload(pagenumber){
	$.post("${ctx}/jobApply/list",{pageNumber:pagenumber},function(data){
		pushdate(data);
	});
}

function pushdate(data){
	var mod = [];
	var date = "";
	$.each(data['content'],function(i,n){
		if(date==""){
			mod.push('<ul class="record-each"><li class="red">'+n.createTimeString+'</li>');
			insertdata(mod,n);
			date = n.createTimeString;
		}else if(date==n.createTimeString){
			insertdata(mod,n);
		}
		else{
			mod.push("</ul>");
			mod.push('<ul class="record-each"><li class="red">'+n.createTimeString+'</li>');
			insertdata(mod,n);
			date = n.createTimeString;
		}
	});
	if (data['content'].length != 0) {
			mod.push("</ul>");
		} else {
			mod.push('<p class="no-result">');
			mod.push('<span><i></i></span>暂无报名记录');
			mod.push("</p>");
		}
		if(data['totalPages']>1){
			mod.push('<div class="paging">');
			for (var i = 0; i < data['totalPages']; i++) {
				if (i == data['number']) {
					mod.push('<a class="page current" href="javascript:;">' + (data['number'] + 1) + '</a></li>');
				} else {
					mod.push('<a class="page" href="javascript:;" onclick="pageload(' + (i + 1) + ')">' + (i + 1) + '</a></li>');
				}
			}
		}
		$("#div_record").empty();
		$("#div_record").append(mod.join(""));
	}

	function insertdata(mod, n) {
		if (n.jobid == null) {
			mod.push("<li><i>" + n.createMomentString + "</i>提交了快速报名：");
			if (n.intention != null) {
				mod.push("<p class='MT10 F15 PDL60'>" + n.intention + "</p>");
			}
			mod.push("</li>");
		} else if (n.companyid == null) {
			mod.push('<li>' + n.createMomentString + '您报名了“<a href="${ctx}/zhaopin_'+n.jobbase.id+'.html" target="_blank">' + n.jobbase.title + '</a>”</li>');
		} else {
			mod.push('<li>' + n.createMomentString + '您报名了“<a href="${ctx}/qiye_'+n.jobbase.company.id+'.html" target="_blank">' + n.jobbase.company.name + '</a>”的工作岗位“<a href="${ctx}/zhaopin_'+n.jobbase.id+'.html" target="_blank">' + n.jobbase.title + '</a>”</li>');
		}
	}
</script>
</body>
</html>