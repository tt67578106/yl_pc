<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="baidu-site-verification" content="Qe3ngwHAwx" />
<title>中国就业万里行首页</title>
</head>
<body>
     <!--jy-sec1-->
    <div class="jy-sec1"></div>
    <!--/jy-sec1-->
    
    <!--jy-sec2-->
    <div class="jy-sec2">
    	<div class="box-out">
        	<div class="jy-title3">
            	<h2><span>大赛进程播报</span><span><i></i></span><div class="clear"></div></h2>
                <p class="sub">就业万里行全国巡回讲座火热进行中</p>
                <a href="/s_articleType_6" class="more" target="_blank">更多>></a>
            </div>
            <!---->
            <div class="jy-scene">
			<c:forEach items="${articleList}" var="article" varStatus="status">
					<ul class="jy-scene-each <c:if test="${status.index==0}">big</c:if>">
	                	<a href="${article.link }" target="_blank"><li><img src="${cdc:getImagePathO(article.img) }" alt="${article.imgAlt }" /></li>
	                    <li class="opacity-bg"></li>
	                    <li class="opacity-text">${article.title }</li></a>
	                </ul>
			</c:forEach>
            </div>
            <!---->
        	<div class="clear"></div>
        </div>
    
    </div>
    <!--/jy-sec2-->
    
<!--jy-sec3-->
    <div class="jy-sec3">
    	<div class="box-out">
        	<div class="jy-title3">
            	<h2><span>创业巡演/强势来袭</span><span><i></i></span><div class="clear"></div></h2>
                <p class="sub">历经六省200余所大中专院校</p>
            </div>
            <!---->
            <div class="jy-route">
            	<div class="map">
               		  <img src="${ctx}/static/images/datas/jiuye/map.png" alt="" />
                     <div class="list">
                     <c:forEach items="${provinceList}" var="pList" varStatus="status">
	                     	<c:if test="${status.index==0}">
	                     	<div class="fl text">
                        		<span class="prov-name"><span>${pList.provinceName }</span>站</span>一共有<label class="num">${pList.count }</label>场讲座
                        	</div>
                        	<a href="javascript:;" class="fr jy-route-layer-trigger prov-Id" data-area="${pList.provinceName }" data-areaId="${pList.provinceId }">点击查看</a>
							</c:if>
						</c:forEach>
                     </div>
                </div>
                <ul class="prov">
                	 <c:forEach items="${provinceList}" var="pList" varStatus="status">
						<li data-num="${pList.count }" class="<c:if test="${status.index==0}">current</c:if>" data-id="${pList.provinceId }"><span>${pList.provinceName }</span>站</li>
					</c:forEach>
                </ul>
            </div>
            <!--/-->
        	<div class="clear"></div>
        </div>
    
    </div>
    <!--/jy-sec3-->
    
    <c:if test="${not empty itemsList }">
    <!--jy-sec4-->
    <div class="jy-sec4">
    	<div class="box-out">
        	<div class="jy-title3">
            	<h2><span>项目大PK/精彩呈现</span><span><i></i></span><div class="clear"></div></h2>
                <p class="sub">汇聚1000名校园CEO创业团队</p>
            </div>
            <!---->
            
            <div class="jy-pro-out">
                <span class="prev"></span>
                <span class="next"></span>
                <div class="jy-pro-content">
                	<c:forEach items="${itemsList}" var="items" varStatus="status">
               		<c:if test="${status.index%9==0}"><div class="jy-pro"></c:if> 
	                		 <a href="${items.link }" target="_blank" ><ul class="jy-pro-each">
	                            <li><img src="${cdc:getImagePathO(items.img) }" alt="${items.imgAlt }" /></li>
	                            <li class="opacity-bg"></li>
	                            <li class="opacity-text">${items.title }</li>
	                        </ul></a>
                		 <c:if test="${(status.index!=0&&(status.index+1)%9==0)||(status.index==fn:length(itemsList) && (fn:length(itemsList)+1)%9!=0)}"></div></c:if> 
					</c:forEach>
	                        </div>
                </div>
            </div>
            <!--/-->
        	<div class="clear"></div>
        </div>
    </div>
    <!--/jy-sec4-->
    </c:if>
    
     <!--jy-sec5-->
    <div class="jy-sec5">
    	<div class="box-out">
        	<div class="jy-title3">
            	<h2><span>创业导师介绍</span><span><i></i></span><div class="clear"></div></h2>
                <p class="sub">力邀24位业内专家组成创业导师团（以下排名不分先后）</p>
                 <a href="${ctx }/trip10000/tutors" class="more" target="_blank">查看全部导师>></a>
            </div>
            <!--创业导师-->
            <div>
            	<ul class="jy-tutor">
                    <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds01.jpg" alt=""/>
                        <p class="name">梅建平</p>
                        <p class="post">长江商学院副院长</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds03.jpg" alt=""/>
                        <p class="name">裘  新</p>
                        <p class="post">上海报业集团董事长</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds04.jpg" alt=""/>
                        <p class="name">洪  生</p>
                        <p class="post">国际咨询专家、教授</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds05.jpg" alt=""/>
                        <p class="name">江南春</p>
                        <p class="post">分众传媒董事长</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds06.jpg" alt=""/>
                        <p class="name">张  斌</p>
                        <p class="post">胜者集团董事长</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds07.jpg" alt=""/>
                        <p class="name">钱学锋</p>
                        <p class="post">汉理资本董事长</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds08.jpg" alt=""/>
                        <p class="name">翁哲锋</p>
                        <p class="post">伯乐遇马天使投资人</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds09.jpg" alt=""/>
                        <p class="name">刘源金</p>
                        <p class="post">红星美凯龙执行总裁</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds10.jpg" alt=""/>
                        <p class="name">唐  博</p>
                        <p class="post">温州四川商会会长</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds11.jpg" alt=""/>
                        <p class="name">周建华</p>
                        <p class="post">苏州四川商会会长</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds12.jpg" alt=""/>
                        <p class="name">王  珏</p>
                        <p class="post">温州四川商会常务副会长</p>
                    </li>
                    <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds13.jpg" alt=""/>
                        <p class="name">孙启亮</p>
                        <p class="post">联想控股集团人力资源总监</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds14.jpg" alt=""/>
                        <p class="name">李永平</p>
                        <p class="post">富士康科技集团资深处长</p>
                    </li>
                     <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds15.jpg" alt=""/>
                        <p class="name">蒲彩林</p>
                         <p class="post">鸿博建筑集团董事长</p>
                    </li>
                    <li class="each">
                        <img src="${ctx}/static/images/datas/jiuye/wlxcyds16.jpg" alt=""/>
                        <p class="name">顾  俊</p>
                         <p class="post">申夏资本总经理</p>
                    </li>
                </ul>
            </div>
            <!--/创业导师-->
        	<div class="clear"></div>
        </div>
    
    </div>
    <!--/jy-sec5-->
    
    <!--jy-sec6-->
    <div class="jy-sec6">
    	<div class="box-out">
        	<div class="jy-player-new">
            	<p class="text1"><strong>NO.1</strong></p>
                <p class="text2">实现CEO的梦想</p>
                <img src="${ctx}/static/images/datas/jiuye/pic1.png" alt="" class="pic block-center"/>
                <a href="${ctx }/trip10000/registration/signUp" class="btn-g-black block-center mt50">立即加入</a>
            </div>
        	<div class="clear"></div>
        </div>
    </div>
    <!--/jy-sec6-->
    <!--创业巡演专场弹出层-->
    <div class="jy-route-layer">
        <div class="clear"></div>
    </div>
<!--/创业巡演专场弹出层-->
    
<script src="${ctx}/static/js/jquery.SuperSlide.2.1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/layer.min.js"></script>
<script type="text/javascript">
$(function(){
	//项目大PK
	$(".jy-pro-out").slide({ mainCell:".jy-pro-content", effect:"left",autoPlay:true,delayTime:400,interTime:5000});
	//创业巡演
	$('.jy-route .prov li').click(function(){
		$(this).addClass('current').siblings().removeClass('current');
		$('.jy-route .prov-Id').attr("data-areaid",$(this).attr("data-id"));
		$('.jy-route .prov-name').text($(this).text());//更改省份
		$('.jy-route .num').text($(this).data('num'));//更改专场数量
		var ini_location={
				'province':['上海','四川','河南','湖北','山西','陕西','黑龙江','吉林','辽宁','河北','北京','天津','山东','江苏','浙江','福建','广东','海南','安徽','江西','湖南','广西','内蒙古','云南','贵州','重庆','甘肃','新疆','西藏','宁夏','青海','香港','澳门','台湾'],
				'coordinate':[
								'left:420px;top:230px;',//上海
								'left:180px;top:250px;',//四川
								'left:310px;top:190px;',//河南
								'left:320px;top:240px;',//湖北
								'left:300px;top:140px;',//山西
								'left:260px;top:190px;',//陕西
								'left:440px;top:-10px;',//黑龙江
								'left:440px;top:40px;',//吉林省
								'left:415px;top:55px;',//辽宁省
								'left:330px;top:130px;',//河北省
								'left:340px;top:90px;',//北京市
								'left:360px;top:90px;',//天津市
								'left:360px;top:145px;',//山东省
								'left:390px;top:210px;',//江苏省
								'left:400px;top:250px;',//浙江省
								'left:380px;top:310px;',//福建省
								'left:330px;top:350px;',//广东省
								'left:285px;top:405px;',//海南省
								'left:370px;top:225px;',//安徽省
								'left:355px;top:265px;',//江西省
								'left:315px;top:275px;',//湖南省
								'left:255px;top:355px;',//广西省
								'left:285px;top:90px;',//内蒙古
								'left:185px;top:340px;',//云南省
								'left:230px;top:310px;',//贵州省
								'left:240px;top:250px;',//重庆市
								'left:200px;top:170px;',//甘肃省
								'left:20px;top:60px;',//新疆
								'left:40px;top:250px;',//西藏
								'left:225px;top:140px;',//宁夏
								'left:155px;top:160px;',//青海省
								'left:340px;top:365px;',//香港
								'left:320px;top:370px;',//澳门
								'left:430px;top:330px;'//台湾省
							]
			};
		
		for(i=0;i<ini_location.province.length;i++){//找到与当前选择省份匹配的数据
			if(ini_location.province[i]==$(this).find("span").text()){
				$('.jy-route .list').attr('style',ini_location.coordinate[i]);//位置变动*/
			}
		}
	});
	//切换城市弹出层
	$('.jy-route-layer-trigger').click(function(){
		findActivityByProvinceId($(this).attr('data-areaId'));
		var pageii=$.layer({
			type:1,
			shade:[0],
			area:['930px', '600px'],
			title:'上海站',
			border:[6, 0.3, '#000'],
			shade:[0.5, '#000'],
			shadeClose:true,
			page:{dom :'.jy-route-layer'}
		});
		var site_title=$(this).parents('.list').find('.prov-name').text();
		layer.title(site_title,pageii);//动态变换弹出层标题
	})
})

function findActivityByProvinceId(provinceId){
	$(".jy-route-layer").empty();
	$.post("${ctx}/trip10000/findActivityByProvinceId", {
		provinceId : provinceId
	}, function(result) {
		$.each(result, function(i, n) {
			var status;
			if(n.endDate>new Date()){
				status="进行中";
			}else{
				status="已结束";
			}
        $(".jy-route-layer").append('<a href="${ctx }/trip10000/lectureDetail?periodId='+n.id+'" target="_blank"><ul class="each"><li class="pic"><img src="'+n.mainImage+'" alt="" />'+
        		'</li><li class="text1">'+status+'</li> <li class="text2">'+n.title+'</li></ul></a>');
		});
	});
}
</script>
</body>
</html>
