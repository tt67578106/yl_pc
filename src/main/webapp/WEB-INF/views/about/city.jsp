<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分站选择</title>
<meta name="description" content=""/>
<meta name="keywords" content="" />
</head>
<body>
	<div class="nav-g"><a href="${ctx}/">首页</a>&gt;<a href="javascript:void(0)" class="red">选择城市分站</a></div>
    <div class="city-search">
      <div class="city-group">
        <label>直接选择：</label>
        <div class="city-controls" >
          <input type="text" value="" placeholder="请选择城市..." id="keyword"/>
        </div>
       </div> 
        <div class="hot-city">
          <span>热门城市：</span>
          		<a href="http://sh.${domainName }" branch="1">上海</a>
				<a href="http://su.${domainName }" branch="2">苏州</a>
				<a href="http://nj.${domainName }" branch="4">南京</a>
				<a href="http://cq.${domainName }" branch="9">重庆</a>
				<a href="http://cd.${domainName }" branch="8">成都</a>
				<a href="http://hz.${domainName }" branch="3">杭州</a>
				<a href="http://tj.${domainName }" branch="13">天津</a>
				<a href="http://wh.${domainName }" branch="16">武汉</a>
				<a href="http://xm.${domainName }" branch="10">厦门</a>
				<a href="http://nb.${domainName }" branch="5">宁波</a>
          </div>
      
    </div>
    <!--/搜索城市-->
    <!--城市列表-->
    <div class="box-1200">
      <div class="all-city">
        <h2>按照拼音首字母选择</h2>
        <c:forEach items="${branchList}" var="branch" varStatus="tag">
			<c:if test="${tag.index==0||(tag.index>0&&fn:substring(branch.webPrefix,0,1) != fn:substring(branchList[tag.index-1].webPrefix,0,1))}" >
				<dl><dt>${cdc:strToUpperCase(fn:substring(branch.webPrefix,0,1))}</dt>
				<dd>
				<c:forEach items="${branchList}" var="branch1" varStatus="tag1">
					<c:if test="${fn:substring(branch1.webPrefix,0,1) == fn:substring(branch.webPrefix,0,1)}">
						<a href="http://${branch1.webPrefix}.${domainName }" branch="${branch1.id }">${branch1.branchName}</a>
					</c:if>
				</c:forEach>
				</dd></dl>
	       	</c:if>
		</c:forEach>
      </div>
    </div>
    <!--/城市列表-->
     <script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/static/js/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.autocomplete.css" />
 <script type="text/javascript">
     var websites = [
				"安康站","安阳站","安庆站","阿坝州站","安顺站","鞍山站","北京站","宝鸡站","蚌埠站","巴中站","毕节站","保山站","版纳站",
				"百色站","北海站","滨州站","保定站","本溪站","白山站","白城站","白银站","成都站","重庆站","长沙站","常州站","长春站","郴州站","常德站",
				"滁州站","巢湖站","池州站","楚雄站","潮州站","崇左站","承德站","沧州站","长治站","朝阳站","徳阳站","东莞站","大连站","达州站","大理站",
				"德宏站","德州站","东营站","大同站","丹东站","大连站","大庆站","定西站","鄂州站","福州站","佛山站","抚州站","阜阳站","防城港站",
				"抚顺站","阜新站","贵阳站","广州站","赣州站","广元站","广安站","甘孜州站","桂林站","贵港站","杭州站","海口站","湖州站","合肥站",
				"惠州站","哈尔滨站","淮安站","鹤壁站","汉中站","黄冈站","黄石站","衡阳站","怀化站","淮北站","亳州站","淮南站","黄山站","红河站",
				"河源站","贺州站","河池站","荷泽站","衡水站","邯郸站","葫芦岛站","黑河站","鹤岗站","呼和浩特站","嘉兴站","济南站","金华站","焦作站",
				"九江站","景德镇站","吉安站","荆州站","揭阳站","江门站","济宁站","晋中站","晋城站","锦州站","佳木斯站","鸡西站","吉林站","酒泉站",
				"嘉峪关站","金昌站","昆山站","昆明站","开封站","连云港站","丽水站","洛阳站","漯河站","娄底站","六安站","乐山站","泸州站","凉山州站",
				"六盘水站","丽江站","临沧站","拉萨站","龙岩站","来宾站","柳州站","莱芜站","聊城站","临沂站","廊坊站","吕梁站","临汾站","辽阳站",
				"辽源站","兰州站","陇南站","绵阳站","马鞍山站","眉山站","梅州站","茂名站","牡丹江站","南京站","宁波站","南昌站","南通站","内江站",
				"宁德站","南宁站","南阳站","南充站","怒江站","南平站","平顶山站","濮阳站","萍乡站","攀枝花站","莆田站","盘锦站","平凉站","青岛站",
				"泉州站","衢州站","黔东南站","黔南站","黔西南站","曲靖站","清远站","钦州站","秦皇岛站","七台河站","齐齐哈尔站","庆阳站","日照站",
				"上海站","苏州站","绍兴站","商洛站","十堰站","深圳站","三亚站","石家庄站","沈阳站","宿迁站","三门峡站","商丘站","上饶站","随州站"
				,"邵阳站","宿州站","遂宁站","昭通站","三明站","韶关站","汕尾站","汕头站","朔州站","双鸭山站","绥化站","四平站","松原站","天津站",
				"泰州站","台州站","太原站","铜川站","铜陵站","铜仁站","泰安站","唐山站","铁岭站","通化站","天水站","无锡站","武汉站","芜湖站",
				"温州站","渭南站","文山站","梧州站","潍坊站","威海站","武威站","厦门站","西安站","仙桃站","襄阳站","徐州站","许昌站","新乡站",
				"信阳站","咸阳站","新余站","孝感站","咸宁站","湘潭站","宣城站","邢台站","忻州站","扬州站","盐城站","宜宾站","烟台站","榆林站",
				"延安站","宜春站","鹰潭站","宜昌站","岳阳站","永州站","益阳站","雅安站","玉溪站","阳江站","云浮站","玉林站","阳泉站","运城站",
				"营口站","伊春站","郑州站","镇江站","自贡站","珠海站","中山站","舟山站","周口站","驻马店站","张家界站","株洲站","资阳站","遵义站",
				"漳州站","肇庆站","湛江站","淄博站","枣庄站","张家口站","张掖站" ];

            $(function() {
                	$("#keyword").autocomplete(websites,{
                		minChars: 0,
                		max: 5,
                		autoFill: true,
                		matchContains: true,
                		scrollHeight: 220,
                		formatItem: function(data, i, total) {
                			return "<I>"+data[0]+"</I>";
                		},
                		formatMatch: function(data, i, total) {
                			return data[0];
                		},
                		formatResult: function(data) {
                			return data[0];
                		}
                    }).result(function(event, row, formatted) {
                    	changeCity(row);
                    });
            });
    function changeCity(cityName){
    	$(".all-city a").each(function(){
    		if(cityName==$(this).text()){
    			$(this).trigger("click");
    		}
    	});
    } 
  //点击城市
 	$(".all-city a,.hot-city a").click(function() {
		$("#c_branch_name").text($(this).text());
		var branch = $(this).attr("branch");
		if (branch != null && branch != "") {
			Cookie.setCookie("branchId", branch, 30);
			Cookie.setCookie("branchName", $(this).text());
			$.post("${ctx}/branch", {
				"branchId" : branch
			}, function(result) {
				if (result != "error") {
					window.location.href = "http://"+result+".${domainName }";
				}
			});
		}
	}); 
    </script>
</body>
</html>