<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!--top-menu-->
	<div class="top-menu">
		<div class="box-out" style="width:1200px;">
			<div class="fl">
                 <p class="fl"><a href="${ctx }/">优蓝网首页</a></p>
				<c:if test="${empty sessionScope.session_user_key}">
					<a href="${ctx}/login" rel="nofollow">登录</a>
					<%-- <label>|</label>
					<a href="${ctx}/register" rel="nofollow">注册</a> --%>
				</c:if>
				<c:if test="${not empty sessionScope.session_user_key}">
                <!---->
                <div class="with-sub">
                	<a href="javascript:void(0);" rel="nofollow" class="sub user">${sessionScope.session_user_key.username }<i></i></a>
                    <div class="sub-menu-out">
                    	<div class="sub-menu">
                            <a href="${ctx}/my" rel="nofollow">修改资料</a>
                           <%--  <a href="${ctx}/my/changePassword" rel="nofollow">修改密码</a> --%>
                            <a href="${ctx}/logout" rel="nofollow">安全退出</a>
                        </div>
                    </div>
                </div>
                </c:if>
                <!--/-->
                <a href="${ctx }/about/mobile.html" class="icon-common icon-phone" rel="nofollow">手机优蓝</a>
            </div>
			<div class="fr">
				 <!--我的优蓝-->
                <div class="with-sub">
                	<a href="javascript:void(0);" rel="nofollow" class="sub">我的优蓝<i></i></a>
                    <div class="sub-menu-out">
                    	<div class="sub-menu">
                            <a href="${ctx}/my" rel="nofollow">微简历</a>
                            <a href="${ctx}/my/signupRecord" rel="nofollow">报名记录</a>
                        </div>
                    </div>
                </div>
                <!--/我的优蓝-->
                 <!--浏览记录-->
                <div class="with-sub">
                	<a href="javascript:void(0);" rel="nofollow" class="sub">浏览记录<i></i></a>
                    <div class="sub-menu-out">
                    	<div class="sub-menu record">
                            <div class="job-list2" id="top_recent_browse_list">
								<p class="no-result">
									<span><i></i></span>暂无浏览记录
								</p>
                    		</div>
                        </div>
                    </div>
                </div>
                <!--/浏览记录-->
                <!--企业合作-->
                <div class="with-sub">
                	<a href="${ctx }/enterprise/index" rel="nofollow" class="sub">企业合作</a>
                </div>
                <!--/企业合作-->
                <!--网站导航-->
                <div class="with-sub">
                	<a href="javascript:void(0);" rel="nofollow" class="sub">网站导航<i></i></a>
                    <div class="sub-menu-out">
                    	<div class="sub-menu nav">
                            <ul>
                            	<li>
                                	<strong>优蓝服务</strong>
                                    <a href="${ctx}/zhaopin/">找工作</a>
                                    <a href="${ctx}/qiye/">找企业</a>
                                    <a href="${ctx }/mingqi/">名企推荐</a>
                                    <a href="http://www.youlanw.com/zone/">优蓝家园</a>
                                </li>
                                <li>
                                	<strong>热点活动</strong>
                                    <a href="${ctx }/trip10000">就业万里行</a>
                                    <%-- <a href="${ctx }/act/gaotie2015.html">高铁乘务招聘</a> --%>
                                </li>
                                <li class="bd0">
                                	<strong>更多</strong>
                                    <a href="${ctx}/about/index.html" rel="nofollow">关于优蓝</a>
                                    <a href="${ctx }/about/wechat.html" rel="nofollow">关注微信</a>
                                    <a href="http://weibo.com/youlanwHR" rel="nofollow">官方微博</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--/网站导航-->
                
			</div>
		</div>
	</div>
	<!--/top-menu--->
<!-- top-menu -->
  <!--jy-menu-->
   <div class="jy-menu-out">
       <div class="jy-menu">
           <div class="box-out">
               <a href="${ctx }/trip10000"><img src="${ctx}/static/images/logo-jy.png" alt="优蓝网" class="logo fl"/></a>
               <span class="list">
                   <a href="${ctx }/trip10000" class="${wlxIndexActive }">大赛首页</a>
                   <a href="${ctx }/trip10000/projects" class="${projectsActive }">参赛项目</a>
                   <a href="${ctx }/trip10000/tutors" class="${tutorsActive }">导师阵容</a>
                   <a href="${ctx }/trip10000/itemsList" class="${itemsListActive }">评委风采</a>
                   <a href="${ctx }/trip10000/rules" class="${rulesActive }">参赛规则</a>
                   <a href="${ctx }/trip10000/prizeSetting" class="${prizeSettingActive }">奖品设置</a>
               </span>
               <a href="${ctx }/trip10000/registration/signUp" class="btn-g-2 fr">我的项目</a>
           </div>
       </div>
   </div>
<!--/jy-menu--->

<script>
$(function(){
	getCookieRecent();//读取浏览记录
	$('.clear-record').click(function(){
		 Cookie.setCookie('recent_browse_job', escape("[]"), 5);
		 $("#top_recent_browse_list").empty();
		 var divs = [];
		divs.push('<p class="no-result"><span><i></i></span>暂无浏览记录</p>');
		$("#top_recent_browse_list").append(divs.join(''));
	});//清空浏览记录
});
//读取浏览记录
function getCookieRecent(){
    var ids = [];
    var rbs = Cookie.getCookie("recent_browse_job");
    if(rbs!=null&&rbs!=undefined&&rbs!=""){
        var jsonObj = eval("("+unescape(rbs)+")");
        var tmpArray = [];
            $.each(jsonObj,function(i){
            	var j=jsonObj[jsonObj.length-i-1];
                if($.inArray(j.id,ids)==-1 && tmpArray.length<5){
                	var tmp = '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"'+j.imgpath+'","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.imgAlt+'"}';
                    tmpArray.push(tmp);
                    ids.push(j.id);
                }
            });
        getRecent(tmpArray);
    }
}
//显示浏览记录
function getRecent(data){
    $("#top_recent_browse_list").empty();
    var divs = [];
	if(data==null||data=="")
	{
		divs.push('<p class="no-result"><span><i></i></span>暂无浏览记录</p>');
	}
	else
	{
		$.each(data,function(i,j){
		   var obj = eval("("+j+")");
		   divs.push('<a href="${ctx}/zhaopin_'+obj.id+'.html" class="link">'); 
		   divs.push('<ul class="job-list2-each">');
		   divs.push('<li class="pic"><img src="'+obj.imgpath+'" alt="'+obj.imgAlt+'" /></li>');
		   divs.push('<li class="title">'+obj.title+'</li>');
		   divs.push('<li class="salary">￥'+obj.totalsalary+'</li>');
		   divs.push('</ul></a>');
		});
		 divs.push('<div class="remove-border2"></div><a href="javascript:;" class="clear-record">清空最近浏览记录</a>');
	}
    $("#top_recent_browse_list").append(divs.join(''));
}
</script>