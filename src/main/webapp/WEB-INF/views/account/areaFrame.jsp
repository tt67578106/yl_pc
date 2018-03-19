<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="${ctx}/static/css/common.css" rel="stylesheet" type="text/css" />
<title></title>
</head>

<body>
	
<!--省市区选择-->
<div class="city-layer tab-g">
	<div class="current-choice"><label class="c999">您的选择是：</label><label class="pcd" id="label_all"></label>
	<label class="p hide" id="label_p"></label>
	<label class="c hide" id="label_c"></label>
	<label class="d hide" id="label_d"></label></div>
	<!---->
	<ul class="tab-g-title">
        <li class="province current">请选择省</li>
        <li class="city hide">请选择市</li>
        <li class="district hide">请选择区</li>
    </ul>
    <!---->
    <!---->
    <div class="tab-g-content">
    	<div class="tab-g-content-each show province-list">
        	<ul class="area-list">
            	<c:forEach items="${provinces}" var="province">
            		<li onclick="selectProvince(${province.id},'${province.abbreviation}')">${province.abbreviation}</li>
            	</c:forEach>
            </ul>
        </div>
        <div class="tab-g-content-each city-list">
        	<ul class="area-list" id="ul_citys">
            	<!-- <li>南京</li> -->
             
            </ul>
        </div>
        <div class="tab-g-content-each district-list">
        	<ul class="area-list" id="ul_districts">
            	<!-- <li>玄武区</li> -->
                
            </ul>
        </div>
    </div>
    <!---->
</div>
<!--/省市区选择-->



 <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
 <script type="text/javascript">
	 var index = parent.layer.getFrameIndex(window.name);//获取当前窗体索引
		//通用tab
		$('.tab-g .tab-g-title li').click(function(){
			var current_index=$('.tab-g-title li').index(this);
			$(this).addClass('current').siblings().removeClass('current');
			$('.tab-g-content-each').eq(current_index).fadeIn(150).siblings('.tab-g-content-each').hide();
			$('.current-choice').css('visibility','visible');
		});
		//省市区联动选择控制
		
	/* 	$('.area-list li').click(function(){
			$('.current-choice .pcd').text($('.current-choice .p').text()+$('.current-choice .c').text()+$('.current-choice .d').text());
		});
		 */
 	
 	function selectProvince(provinceid,provincename){
 		$.post("${ctx}/my/citys",{provinceid:provinceid},function(result){
 			var dov = [];
 			$.each(result,function(i,n){
 				dov.push('<li onclick="selectCity('+n.id+',\''+n.abbreviation+'\')">'+n.abbreviation+'</li>');
 			});
 			$("#ul_citys").empty();
 			$("#ul_citys").append(dov.join(''));
 			
 			$('.province').text(provincename);
			$('.city').show().trigger('click');
			$('.current-choice .p').text(provinceid);
			$('#label_all').empty();
			$('#label_all').append(provincename);
 		});
 	}
 	
 	function selectCity(cityid,cityname){
 		$.post("${ctx}/my/districts",{cityid:cityid},function(result){
 			var dov = [];
 			$.each(result,function(i,n){
 				dov.push('<li onclick="selectDistricts('+n.id+',\''+n.abbreviation+'\')">'+n.abbreviation+'</li>');
 			});
 			$("#ul_districts").empty();
 			$("#ul_districts").append(dov.join(''));
 			
 			$('.city').text(cityname);
			$('.district').show().trigger('click');
			$('.current-choice .c').text(cityid);
			$('#label_all').append("-"+cityname);
 		});
 	}
 	function selectDistricts(disid,disname){
 		$('.district').text(disname);
		$('.district').show().trigger('click');
		$('.current-choice .d').text(disid);
		
		$('#label_all').append("-"+disname);
		 <c:if test="${param.type == 1}">
	 		window.parent.document.getElementById("hometown").value = $('#label_all').html();
			window.parent.document.getElementById("nativeProvinceid").value = $('#label_p').html();
			window.parent.document.getElementById("nativeCityid").value = $('#label_c').html();
			window.parent.document.getElementById("nativeCountyid").value = $('#label_d').html();
		 </c:if>
		 <c:if test="${param.type == 2}">
		 		window.parent.document.getElementById("othertown").value = $('#label_all').html();
				window.parent.document.getElementById("residentProvinceid").value = $('#label_p').html();
				window.parent.document.getElementById("residentCityid").value = $('#label_c').html();
				window.parent.document.getElementById("residentCountyid").value = $('#label_d').html();
		 </c:if>
		
		parent.layer.close(index); //执行关闭
 	}
 	$('.area-list li,.city-list li,.district-list li').each(function(){
		if(($(this).text().length)>3){$(this).addClass('long')}
	});
 </script>

</body>
</html>
