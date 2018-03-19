<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>求职状态</title>
<style type="text/css">
/*tab-style-1*/
.tab-style-1 .title{border-bottom:2px solid #e6e6e6;height:50px;line-height:50px;}
.tab-style-1 .title li{float:left;min-width:80px;padding:0 10px;cursor:pointer;text-align:center;}
.tab-style-1 .title li.current{color:#ed5f30;border-bottom:2px solid #ed5f30;}
/*my-apply*/
.my-apply .each{margin-top:20px;border:1px solid #e6e6e6;}
.my-apply .each .t{background-color:#f7f7f7;height:45px;line-height:45px;padding:0 15px;position:relative;}
.my-apply .each .t .num{position:absolute;left:140px;}
.my-apply .each .c{position:relative;padding-left:200px;padding-left:142px;line-height:2;height:116px;}
.my-apply .each .c .pic{position:absolute;left:15px;top:15px;}
.my-apply .each .c .pic img{width:115px;height:85px;}
.my-apply .each .c .t1{font-size:16px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;padding-top:12px;}
.my-apply .each .c .user{position:absolute;width:200px;height:50px;line-height:50px;right:150px;top:50%;margin-top:-25px;text-align:right;}
.my-apply .each .c .consult{position:absolute;width:68px;height:32px;line-height:26px;text-align:center;top:50%;right:15px;margin-top:-15px;background:url(${ctx}/static/images/qq-bg.png) no-repeat 0 1px;color:#d20000;padding-left:22px;cursor:pointer;}
/**/
.back-to{color:#ed5f30;background:url(${ctx}/static/images/back.png) no-repeat right 0;padding-right:55px;}
/*my-apply-status*/
.my-apply-status .spec{color:#65b16f;}
.my-apply-status .title{color:#ed5f30;height:50px;line-height:50px;padding:0 15px;border-bottom:1px solid #ddd;font-size:16px;}
.my-apply-status .time{margin-right:10px;}
.my-apply-status .each{padding:10px 0 0 130px;position:relative;}
.my-apply-status .each .date{position:absolute;left:15px;color:#ed5f30;top:18px;}
.my-apply-status .each .text{margin-top:10px;}
.my-apply-status .each .line{position:absolute;height:100%;border-left:1px solid #ddd;margin-top:15px;left:110px;top:15px;}
.my-apply-status .each .line i{position:absolute;width:10px;height:10px;border-radius:10px;background-color:#d9d9d9;left:-5px;top:-5px;}
.my-apply-status .each .des{padding:10px 48px;background-color:#f9f9f9;color:#999;margin:15px 0  0 0;line-height:2.2;}
.certify-hover {right: -452px !important;}
</style>
</head>
<body>
	<!--我的简历-->
    <div class="w1200 mt20">
    	<!---->
    	<div class="box-g-l-user fl bg-white user-menu">
        	<div class="portrait-wrapper">
            	<img src="${cdc:getImagePath320(headimg.imgpath)}" alt="" id="head_img" class="portrait"/>
                <input type="file" title="上传头像" name="file" id="fileupload" value=""/>
            </div>
            <p class="text-center c999 mt10">建议上传图片尺寸200*200</p>
            <div class="user-menu-list mt10 fl">
            	<a href="${ctx}/my">我的简历</a>
            	<a href="${ctx}/my/apply">面试安排</a>
               <%--  <a href="${ctx}/my/changePassword">修改密码</a> --%>
            </div>
        </div>
        <!--/-->
      <!--我的求职-->
        <div class="box-g-r-user fr bg-white f14">
        	<div class="my-apply">
            	<div class="clearfix"><a href="${ctx }/my/apply"><span class="fr back-to">返回列表</span></a></div>
            	<ul class="each">
                    <li class="t">
                        <span class="fl">${apply.createTimeString }</span>
                        <span class="fl num">求职单：${number }</span>
                        <span class="fr orange">${cdc:getJobApplyStatus(apply.status) }</span>
                    </li>
                    <a href="${ctx }/zhaopin_${apply.jobbase.id}.html" target="_blank">
                    <li class="c">
                        <ul>
                            <li class="pic">
                          		<c:choose>
									<c:when test="${empty apply.jobbase.thumbnialImage.imgpath}">
										<img src="${cdc:getImageByJobType320(apply.jobbase.jobType)}" alt="${apply.jobbase.title}" />
									</c:when>
									<c:otherwise>
										<img src="${cdc:getImagePath320(apply.jobbase.thumbnialImage.imgpath)}" alt="${apply.jobbase.thumbnialImage.comment}" />
									</c:otherwise>
								</c:choose>
                            </li>
                            <li class="t1">${apply.jobbase.title }</li>
                            <li class="orange">
                            	<c:choose>
                            	<c:when test="${not empty apply.jobbase.jobDetail.salaryfrom && apply.jobbase.jobDetail.salaryfrom > 0}">
                            		￥${apply.jobbase.jobDetail.salaryfrom }
                            		<c:if test="${not empty  apply.jobbase.jobDetail.salaryto}">-${apply.jobbase.jobDetail.salaryto }元/月</c:if>
		                			<c:if test="${empty  apply.jobbase.jobDetail.salaryto}">起</c:if>
                            	</c:when>
                            	<c:otherwise>薪资面议</c:otherwise>
                            	</c:choose>
		                	</li>
                            <li class="company">
                               <span class="compName">${apply.jobbase.company.name}</span>
                               <c:if test="${apply.jobbase.cooperationType==1||apply.jobbase.cooperationType==5}">
                               <label class="certify-label">认证
			            		<div class="certify-hover">
			            			<p class="blue f16">认证标准</p>
			            			<p class="f16 c333 mt10">该企业已经过优蓝网官方认证，优蓝网确保以下信息真实可靠</p>
			            			<ul class="standards">
							    		<li>该企业与优蓝网已签署官方委托协议</li>
							    		<li>优蓝网已派专人严格审核企业岗位</li>
							    		<li>优蓝网已派专属服务人员进厂入住接待</li>
							    	</ul>
							    	<p class="blue f16">专享服务</p>
							    	<ul class="specialService clearfix">
							    		<li>
							    			<div class="circle circle1"><p>全程</p><p>0费用</p></div>
							    			<p>参加面试入职</p>
							    			<p>全程无任何费用</p>
							    		</li>
							    		<li>
							    			<div class="circle circle2"><p>专属</p><p>服务</p></div>
							    			<p>匹配专属顾问</p>
							    			<p>一对一贴心服务</p>
							    		</li>
							    		<li>
							    			<div class="circle circle3"><p>全流程</p><p>覆盖</p></div>
							    			<p>从报名到入职</p>
							    			<p>全流程跟踪服务</p>
							    		</li>
							    	</ul>
			            		</div>
			            		</label>
			            		</c:if>
                            </li>
                            <li class="user">求职人：${realname}</li>
                            <li class="consult" onclick="javascript:window.open('http://b.qq.com/webc.htm?new=0&sid=4008777816&eid=218808P8z8p8p8p8q8q8P&o=&q=7&ref='+document.location, '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');">客服咨询</li>
                        </ul>
                    </li>
                    </a>
                </ul>
            </div>
            <div class="my-apply-status">
            </div>
        </div>
        <!--/我的求职-->
    	<div class="clear"></div>
    </div>
    <!--/我的求职-->
    <script>
    $(function(){
    	var applyId = "${apply.id}";
    	var applyStatus = "${apply.status}";
    	var creatime = "${apply.createTimeString}";
    	var createMoment = "${apply.createMomentString}";
    	var lastSynHrTime = "${apply.lastSynHrTimeString}";
    	var lastSynHrTimeMoment = "${apply.lastSynHrTimeMomentString}"
    	if(applyId !=null && applyId >0 ){
    		$.post("${ctx}/jobApply/flownode",{"applyId":applyId},function(data){
    			 var mod = [];
    			 var date = "";
    			 if(date == ""){
	    			 mod.push('<div class="each">');
	    			 mod.push('  <div class="date">'+creatime+'</div>');
	    			 mod.push('    <div class="line"><i></i></div>');
	    			 mod.push('    <div class="text"><label class="time">'+createMoment+'</label><span class="spec">报名成功，正式转入客服处理</span></div>');
	    			 date = creatime;
    			 }
    			 if(date == lastSynHrTime){
	    			 mod.push('    <div class="text"><label class="time">'+lastSynHrTimeMoment+'</label>订单已受理开始处理，职业顾问将为您服务。请保持您的手机畅通，如果对我们的服务有疑问可以拨打：4008-777-816 进行咨询！</div>');
    			 }else if(lastSynHrTime!=null&&lastSynHrTime!=""){
    				 mod.push('</div>');
	    			 mod.push('<div class="each">');
	    			 mod.push('  <div class="date">'+lastSynHrTime+'</div>');
	    			 mod.push('    <div class="line"><i></i></div>');
	    			 mod.push('    <div class="text"><label class="time">'+lastSynHrTimeMoment+'</label>订单已受理开始处理，职业顾问将为您服务。请保持您的手机畅通，如果对我们服务疑问可以拨打：4008-777-816 进行咨询！</div>');
	    			 date = lastSynHrTime;
    			 }
    			 $.each(data,function(i,n){
    		    	 if(date == n.createTimeString){
    		    		 insertdata(mod,n,applyStatus);
    		    	 }else{
    		    		 mod.push('</div>');
    		    		 mod.push('<div class="each">');
    	    			 mod.push('  <div class="date">'+n.createTimeString+'</div>');
    	    			 mod.push('    <div class="line"><i></i></div>');
   		    			insertdata(mod,n,applyStatus);
   		    			date = n.createTimeString;
    		    	 }
    		     });
    			 mod.push('</div>');
    		     $(".my-apply-status").empty();
    		     $(".my-apply-status").append(mod.join(''));
    		});
    	}
    });
    
    var flag = false;
    function insertdata(mod,n,applyStatus){
    	 if(n.flowNode!=null&&n.flowNode>=0){
    		 if(applyStatus == 4 ){
    			 getCloseByFlowNode(mod,n);
    		 }
	    	 if(n.flowNode == 41){
		    	 mod.push('<!--入职成功-->');
		    	 mod.push('    <div class="text"><label class="time">'+n.createMomentString+'</label><span class = "red">恭喜您，入职成功！</span></div>');
		    	 mod.push('  <!--/入职成功-->');
	    	 }
	    	 if(n.flowNode == 27||n.flowNode == 33){
	    		 mod.push('  <!--面试成功-->');
	    		 mod.push('    <div class="text"><label class="time">'+n.createMomentString+'</label>面试成功，请按时前往公司报到入职。</div>');
	    		 mod.push('  <!--/面试成功-->');
	    	 }
	    	 if(flag==false&&(n.flowNode == 12||n.flowNode == 21||n.flowNode == 24) ){
	    		 mod.push('   <!--订单已确认-->');
	    		 mod.push('    <div class="text"><label class="time">'+n.createMomentString+'</label>订单已确认，您的面试信息如下：</div>');
	    		 flag = true;
		    	 if(n.operateContent!=null&&n.operateContent!=""){
		    		 mod.push('    <div class="des">');
    		    	 var json = eval("(" + n.operateContent + ")");
    		    	 if(json.contactName!=null&&json.contactName!=""){
    		    		 mod.push('   		<p>联系人：'+json.contactName+'</p>');
    		    	 }
    		    	 if(json.interviewTime!=null&&json.interviewTime!=""){
    		    		 mod.push('   		<p>面试时间：'+json.interviewTime+'</p>');
    		    	 }
    		    	 if(json.interviewPlace!=null && json.interviewPlace!=""){
    		    		 mod.push('   		<p>面试地点：'+json.interviewPlace+'</p>');
    		    	 }
    		    	 mod.push('          </div>');
		    	 }
		    	 mod.push('  <!--/订单已确认-->');
	    	 }
    	 }
    	 return mod;
    }
    
	function getCloseByFlowNode(mod,n){
    	var flag = false;
    	 if( n.flowNode == 22){
	    	 mod.push('<!--面试取消 关闭-->');
	    	 mod.push('    <div class="text"><label class="time">'+n.createMomentString+'</label><span class = "red">订单已关闭（关闭原因：报备未通过）<br/>想看看还有什么好工作，请继续在优蓝网上浏览适合您的岗位。</span></div>');
	    	 mod.push('  <!--/面试取消 关闭-->');
    	 }
    	 if(flag==false&&(n.flowNode == 23||n.flowNode == 25 || n.flowNode == 28)){
	    	 flag = true;
    		 mod.push('<!--面试失败 关闭-->');
    		 mod.push('    <div class="text"><label class="time">'+n.createMomentString+'</label><span class = "red">订单已关闭（关闭原因：面试失败）<br/>想看看还有什么好工作，请继续在优蓝网上浏览适合您的岗位。</span></div>');
	    	 mod.push('  <!--/面试失败 关闭-->');
    	 }
    	 if( n.flowNode == 42){
	    	 mod.push('<!--入职失败 关闭-->');
	    	 mod.push('    <div class="text"><label class="time">'+n.createMomentString+'</label><span class = "red">订单已关闭（关闭原因：入职失败）<br/>未成功入职，如果对我们服务疑问可以拨打：4008-777-816 进行咨询！</span></div>');
	    	 mod.push('  <!--/入职失败 关闭-->');
    	 }
    	 if( n.flowNode == 13){
	    	 mod.push('<!--直接关闭-->');
	    	 mod.push('    <div class="text"><label class="time">'+n.createMomentString+'</label><span class = "red">订单已关闭，感谢您的使用！</span></div>');
	    	 mod.push('  <!--/直接关闭-->');
    	 }
		return mod;
    }
    </script>
</body>
</html>