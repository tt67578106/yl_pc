<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tc" uri="http://youlanw.com/tags/text_code_functions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--关键词-->

<div class="w1200 mt20">
   	<ul class="tab-title">
       	<li class="current">热门城市</li>
           <li>名企招聘</li>
           <li>招工热搜</li>
       </ul>
       <div class="tab-each show">
       	<%@ include file="/WEB-INF/template/home_footer_1.jsp" %>
       </div>
       <div class="tab-each">
       	<%@ include file="/WEB-INF/template/home_footer_2.jsp" %>
       </div>
       <div class="tab-each">
       	<%@ include file="/WEB-INF/template/home_footer_3.jsp" %>
       </div>
   	<div class="clear"></div>
</div>

<!--/关键词-->
 <!--footer-->
<div class="footer-banner">
   	<div class="w1200">
    	<ul>
    		<li>
    			<i class="icon-free"></i>
    			<h3>免费求职</h3>
    			<p>企业直招&nbsp;不收取任何费用</p>
    		</li>
    		<li>
    			<i class="icon-profession"></i>
    			<h3>专业招聘</h3>
    			<p>专人服务&nbsp;入职速度快50%</p>
    		</li>
    		<li>
    			<i class="icon-quality"></i>
    			<h3>优质服务</h3>
    			<p>企业社区问答&nbsp;免费借贷</p>
    		</li>
    		<li class="mt4">
    			<i class="icon-contact"></i>
    			<p>服务热线：4008-777-816</p>
    			<p>求职服务QQ群：485689212</p>
    		</li>
    		<div class="clear"></div>
    	</ul>
    </div>
   </div>
   <div class="YL_footer">
	<ul class="all-ul clearfix">
		<li class="first">
			<ul class="footer-left">
				<li>
					<div>
						<i class="connect"></i>
					</div>
					<p>服务热线(免长话费)</p>
					<p>4008-777-816</p>
					<p>工作日 9:00-19:00</p>
				</li>
				<li>
					<h5>优蓝网</h5>
					<a href="http://www.youlanw.com/about/index.html" target="_blank"><p>官网介绍</p></a>
					<a href="http://www.youlanw.com/about/mediaReports.html" target="_blank"><p>媒体报道</p></a>
					<a href="http://www.youlanw.com/about/contact.html" target="_blank"><p>联系我们</p></a>
					<a href="http://www.youlanw.com/about/statement.html" target="_blank"><p>网站声明</p></a>
					<a href="http://www.youlanw.com/about/agreement.html" target="_blank"><p>用户协议</p></a>
					<a href="http://www.youlanw.com/feedback.html" target="_blank"><p>意见反馈</p></a>
				</li>
				
				<li>
					<h5>求职帮助</h5>
					<a href="http://www.youlanw.com/help/ylapplyJob.html" target="_blank"><p>求职指南</p></a>
					<a href="http://www.youlanw.com/help/ylkz.html"><p>企业帮助指南</p></a>
					<a href="http://www.youlanw.com/help/index.html" target="_blank"><p>用户注册</p></a>
					<a href="http://www.youlanw.com/help/ylapplyJob.html" target="_blank"><p>求职流程</p></a>
					<a href="http://www.youlanw.com/help/consultant.html" target="_blank"><p>求职顾问</p></a>
				</li>
				<li>
					<h5>蓝领社区</h5>
					<a href="http://www.youlanw.com/zone" target="_blank"><p>优蓝家园</p></a>
					<a href="http://www.youlanw.com/s_articleType_1" target="_blank"><p>行业新闻</p></a>
					<a href="http://www.youlanw.com/s_articleType_3" target="_blank"><p>求职培训</p></a>
					<a href="http://www.youlanw.com/s_articleType_6" target="_blank"><p>优蓝动态</p></a>
					<a href="${ctx }/tag/" target="_blank"><p>职场大全</p></a>
				</li>
				<li>
					<h5>求职招聘</h5>
					<a href="http://www.youlanw.com/zhaopin" target="_blank"><p>找工作</p></a>
					<a href="http://www.youlanw.com/qiye/" target="_blank"><p>大厂招工</p></a>
					<a href="${ctx }/mingqi/" target="_blank"><p>入驻名企</p></a>
					<a href="http://m.youlanw.com/<c:if test="${not empty sessionScope.session_key_branch_web_prefix}">${fn:trim(sessionScope.session_key_branch_web_prefix)}/</c:if>" target="_blank"><p>手机优蓝</p></a>
					<a href="${ctx }/about/mobile.html" target="_blank"><p>手机APP下载</p></a>
				</li>
				<div class="clear"></div>
			</ul>
		</li>
		<li class="second">
			<ul class="codes">
				<li><img src="${ctx }/static/images/data/img-code1.png">
				<p>关注优蓝微信</p></li>
				<li><img src="${ctx }/static/images/data/img-code2.png">
				<p>下载优蓝APP</p></li>
				<div class="clear"></div>
			</ul>
		</li>
	</ul>
	<p class="footer-p">Copyright © 2016 www.youlanw.com 上海优尔蓝信息科技股份有限公司 版权所有 沪ICP备14033709号-1</p>
</div>
<!--/footer-->
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?83a185fc9f52ffcad1bcb9e4d889301b";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();
(function(){
    var bp = document.createElement('script');
    var curProtocol = window.location.protocol.split(':')[0];
    if (curProtocol === 'https') {
        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';        
    }
    else {
        bp.src = 'http://push.zhanzhang.baidu.com/push.js';
    }
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
})();
var _vds = _vds || [];
window._vds = _vds;
(function(){
  _vds.push(['setAccountId', '844010516d2d97c9']);
  _vds.push(['setCS1', 'user_id', '${sessionScope.session_user_key.id}']);
  _vds.push(['setCS3', 'user_name', '${sessionScope.session_user_key.username}']);
  (function() {
    var vds = document.createElement('script');
    vds.type='text/javascript';
    vds.async = true;
    vds.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'dn-growing.qbox.me/vds.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(vds, s);
  })();
})();
//360自动推送
(function(){
   var src = (document.location.protocol == "http:") ? "http://js.passport.qihucdn.com/11.0.1.js?1e5f795a5788577acd7af2732135053e":"https://jspassport.ssl.qhimg.com/11.0.1.js?1e5f795a5788577acd7af2732135053e";
   document.write('<script src="' + src + '" id="sozz"><\/script>');
})();

</script>