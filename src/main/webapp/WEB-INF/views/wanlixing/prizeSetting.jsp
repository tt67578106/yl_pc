<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>奖品设置_中国就业万里行</title>
</head>
<body>
     <!--jy-sec1-->
    <div class="jy-secN"></div>
    <!--/jy-sec1-->
    
   <!--奖品-->
    <div class="box-out">
        <div class="jy-prizes">
            <ul>
            	<li>
                	<img src="${ctx}/static/images/datas/jiuye/A.jpg" alt=""/>
                    <p class="des">一等奖</p>
                </li>
                <li>
                	<img src="${ctx}/static/images/datas/jiuye/B.jpg" alt=""/>
                    <p class="des">二等奖</p>
                </li>
                <li>
                	<img src="${ctx}/static/images/datas/jiuye/C.jpg" alt=""/>
                    <p class="des">三等奖</p>
                </li>
            </ul>
            <div class="clear"></div>
        </div>
    </div>
    <!--/奖品-->
    
    <!---->
    <div class="box-out pdtb30">
    	<div class="text-g">
        	<p>奖项设置：</p>
			<ul><li>A轮大赛，评选出100个参赛作品，将获得“就业万里行”颁发的荣誉证书以及奖杯。</li>
			
			<li>B轮大赛，评选出10个参赛作品，将获得“就业万里行”颁发的1万元奖金、荣誉证书以及奖杯</li>
			
			<li>C轮大赛，评选出前三甲，将分别获得15万、10万、6万创业资金、荣誉证书以及奖杯。</li></ul>
        	
        </div>
    </div>
    <!--/-->
    
</body>
</html>
