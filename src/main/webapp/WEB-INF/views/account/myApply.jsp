<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的求职</title>
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
.certify-hover {right: -452px !important;}
</style>
</head>
<body>
	<!--我的简历-->
    <div class="w1200 mt20">
    	<!---->
    	<div class="box-g-l-user fl bg-white user-menu">
        	<div class="portrait-wrapper">
            	<img src="${cdc:getImagePathO(headimg.imgpath)}" alt="" id="head_img" class="portrait"/>
                <input type="file" title="上传头像" name="file" id="fileupload" value=""/>
            </div>
            <p class="text-center c999 mt10">建议上传图片尺寸200*200</p>
            <div class="user-menu-list mt10 fl">
            	<a href="${ctx}/my">我的简历</a>
            	<a href="${ctx}/my/apply" class="current">面试安排</a>
                <%-- <a href="${ctx}/my/changePassword">修改密码</a> --%>
            </div>
        </div>
        <!--/-->
        
        <!--我的求职-->
        <div class="box-g-r-user fr bg-white f14">
        	<div class="my-apply">
            	<div class="tab-g tab-style-1">
                	<ul class="title clearfix">
                    	<li class="current">全部求职</li>
                        <li >面试（${applyCount }）</li>
                        <li>预约（${orderCount }）</li>
                    </ul>
                    <!--全部-->
                    <div class="content-each all-record show">
                    </div>
                    <!--/全部-->
                    <!--面试-->
                    <div class="content-each apply-record">
                    </div>
                    <!--/面试-->
                    <!--预约-->
                    <div class="content-each order-record">
                    </div>
                    <!--/预约-->
                </div>
            </div>
        </div>
    	<div class="clear"></div>
    </div>
    <!--/我的简历-->
<script>
	var type=null
	$(function(){
		pageload(1,null);
		$('.clearfix li').click(function(){
			if($(this).text().indexOf("面试")>-1){
				type=2;
			 }else if($(this).text().indexOf("预约")>-1){//预约
				type=4;
			 }else{
			 	type=null;
			 }
			pageload(1,type);
		});
	});
	function pageload(pagenumber,type){
		$.post("${ctx}/jobApply/list",{pageNumber:pagenumber,type:type},function(data){
			var test = eval(data);
			pushdate(data);
		});
	}

	function pushdate(data){
		var mod = [];
		var date = "";
		$.each(data['content'],function(i,n){
			mod.push('<ul class="each">');
			mod.push('<li class="t">');
			mod.push('    <span class="fl">'+n.createTimeString+'</span>');
			mod.push('    <span class="fl num">求职单：'+n.numberCreateTimeString+n.id+'</span>');
			if(n.status==0){
				mod.push('<span class="fr orange">正在处理</span>');
			}else if(n.status==1){
				mod.push('<span class="fr orange">参加面试</span>');
			}else if(n.status==2){
				mod.push('<span class="fr orange">准备入职</span>');
			}else if(n.status==3){
				mod.push('<span class="fr orange">入职成功</span>');
			}else if(n.status==4){
				mod.push('<span class="fr orange">已关闭</span>');
			}
			mod.push('</li>');
			mod.push('<a href="${ctx}/jobApply/flow/'+n.id+'.html">');
			//mod.push('<a href="${ctx}/zhaopin_'+n.jobbase.id+'.html">');
			mod.push('<li class="c">');
			mod.push('    <ul>');
			if(n.jobbase!=null){
				mod.push('        <li class="pic">');
				if(n.jobbase.thumbnialImage!=null&&n.jobbase.thumbnialImage.imgpath!=""  && n.jobbase.thumbnialImage.imgpath !=null){
					if(n.jobbase.thumbnialImage.imgpath.indexOf("http://")==-1){
						mod.push('<img src="'+IMAGE_FILE_URL+'320/'+n.jobbase.thumbnialImage.imgpath+'"></li>');
					}else{
						mod.push('<img src="'+n.jobbase.thumbnialImage.imgpath+'"></li>');
					}
				}else{
					mod.push('<img src="'+getImageByJobType(n.jobbase.jobType)+'"></li>');
				}
				mod.push('</li>');
				mod.push('        <li class="t1">'+n.jobbase.title+'</li>');
				mod.push('        <li class="orange">');
				if(n.jobbase.jobDetail!=null){
					if(n.jobbase.jobDetail.salaryfrom!=null&&n.jobbase.jobDetail.salaryfrom>0){
						mod.push('￥'+n.jobbase.jobDetail.salaryfrom);
						if(n.jobbase.jobDetail.salaryto!=null&&n.jobbase.jobDetail.salaryto>0){
							mod.push('-'+n.jobbase.jobDetail.salaryto+'元/月');
						}else{
							mod.push('起');
						}
					}else{
						mod.push('薪资面议');
					} 
				}else{
					mod.push('薪资面议');
				}
				mod.push('</li>');
				if(n.jobbase.company!=null){
					mod.push('        <li class="company"><span class="compName">'+ n.jobbase.company.name);
					if(n.jobbase.company.cooperationType==1||n.jobbase.company.cooperationType==5){
						mod.push('</span><label class="certify-label">认证');
						mod.push('  <div class="certify-hover">');
						mod.push('    <p class="blue f16">认证标准</p>');
						mod.push('    <p class="f16 c333 mt10">该企业已经过优蓝网官方认证，优蓝网确保以下信息真实可靠</p>');
						mod.push('    <ul class="standards">');
						mod.push('      <li>该企业与优蓝网已签署官方委托协议</li>');
						mod.push('      <li>优蓝网已派专人严格审核企业岗位</li>');
						mod.push('      <li>优蓝网已派专属服务人员进厂入住接待</li>');
						mod.push('     </ul>');
						mod.push('     <p class="blue f16">专享服务</p>');
						mod.push('     <ul class="specialService clearfix">');
						mod.push('        <li>');
						mod.push('        <div class="circle circle1"><p>全程</p><p>0费用</p></div>');
						mod.push('        <p>参加面试入职</p>');
						mod.push('        <p>全程无任何费用</p>');
						mod.push('        </li>');
						mod.push('        <li>');
						mod.push('        <div class="circle circle2"><p>专属</p><p>服务</p></div>');
						mod.push('        <p>匹配专属顾问</p>');
						mod.push('        <p>一对一贴心服务</p>');
						mod.push('        </li>');
						mod.push('        <li>');
						mod.push('        <div class="circle circle3"><p>全流程</p><p>覆盖</p></div>');
						mod.push('        <p>从报名到入职</p>');
						mod.push('        <p>全流程跟踪服务</p>');
					}
					mod.push('        </li>');
					mod.push('    </ul>');
					mod.push('  </div>');
					mod.push('</label>');
					mod.push('</span></li>');
				}
			}
			mod.push('        <li class="user">求职人：${realname}</li>');
			mod.push('        <li class="consult" onclick="contactour();">客服咨询</li>');
			mod.push('    </ul>');
			mod.push('</li>');
			mod.push('</a>');
			mod.push('</ul>');
		});
		if (data['content'].length == 0) {
			mod.push('<p class="no-result">');
			mod.push('<span><i></i></span>您还没有求职信息');
			mod.push("</p>");
		}
		if(data['totalPages']>1){
			mod.push('<div class="paging">');
			for (var i = 1; i <= data['totalPages']; i++) {
				if (i == data['number']+1) {
					mod.push('<a class="page current" href="javascript:;">' + (data['number']+1) + '</a></li>');
				} else {
					mod.push('<a class="page" href="javascript:;" onclick="pageload(' + i + ','+type+')">' + i + '</a></li>');
				}
			}
		}
		if(type==2){
			$(".apply-record").empty();
			$(".apply-record").append(mod.join(""));
		}else if(type==4){
			$(".order-record").empty();
			$(".order-record").append(mod.join(""));
		}else{
			$(".all-record").empty();
			$(".all-record").append(mod.join(""));
		}
	}

	function contactour(){
		window.open('http://b.qq.com/webc.htm?new=0&sid=4008777816&eid=218808P8z8p8p8p8q8q8P&o=&q=7&ref='+document.location, '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');
	}
	function getImageByJobType(jobType){
		var image=null;
		jobType : jobType == undefined?"":jobType
		$.ajax({ url: "${ctx}/job/getImageByJobType", 
			type: "post",
			data: {jobType:jobType},
			async: false,
			success: function(data){
				image = data;
	     }});
		return IMAGE_FILE_URL+"320/"+image;
	}

</script>
</body>
</html>