<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<div class="top-menu">
    <div class="fl">
        <div class="with-sub">
        	<a href="" rel="nofollow" class="sub user">${sessionScope.session_key_branch_name}<i></i></a>
        	<div class="sub-menu-out">
            	<div class="sub-menu">
                    <h4 class="doc-des-h4 blue">热门城市</h4>
                    <div class="city-hot">
                    	<a href="http://sh.youlanw.com" >上海</a><a href="http://su.youlanw.com">苏州</a><a href="http://nj.youlanw.com">南京</a><a href="http://cq.youlanw.com">重庆</a><a href="http://cd.youlanw.com">成都</a><a href="http://hz.youlanw.com">杭州</a><a href="http://tj.youlanw.com">天津</a>
                    	<a href="http://wh.youlanw.com">武汉</a><a href="http://xm.youlanw.com">厦门</a><a href="http://nb.youlanw.com">宁波</a><a href="http://wx.youlanw.com">无锡</a><a href="http://hf.youlanw.com">合肥</a><a href="http://nc.youlanw.com">南昌</a><a href="http://bj.youlanw.com">北京</a>
                    	<a href="http://xa.youlanw.com">西安</a>
                    </div> 
                    <div class="city-area">
                    	<div class="area-item">
                    		<span class="area-left">华北地区</span>
                    		<div class="area-right">
                    			<a href="http://bj.youlanw.com">北京</a><a href="http://tj.youlanw.com">天津</a><a href="http://bd.youlanw.com">保定</a><a href="http://sjz.youlanw.com">石家庄</a><a href="http://lf.youlanw.com">临汾</a><a href="http://hd.youlanw.com">邯郸</a>
                    			<a href="http://ty.youlanw.com">太原</a><a href="http://tangshan.youlanw.com">唐山</a><a href="http://yuncheng.youlanw.com">运城</a><a href="http://chengde.youlanw.com">承德</a><a href="http://langfang.youlanw.com">廊坊</a><a href="http://xingtai.youlanw.com">邢台</a>
                    		</div>
                    	</div>
                    	<div class="area-item">
                    		<span class="area-left">华东地区</span>
                    		<div class="area-right">
                    			<a href="http://sh.youlanw.com">上海</a><a href="http://su.youlanw.com">苏州</a><a href="http://hz.youlanw.com">杭州</a><a href="http://wx.youlanw.com">无锡</a><a href="http://xm.youlanw.com">厦门</a><a href="http://nj.youlanw.com">南京</a>
                    			<a href="http://nb.youlanw.com">宁波</a><a href="http://nt.youlanw.com">南通</a><a href="http://hf.youlanw.com">合肥</a><a href="http://nc.youlanw.com">南昌</a><a href="http://qd.youlanw.com">青岛</a><a href="http://fz.youlanw.com">福州</a>
                    		</div>
                    	</div>
                    	<div class="area-item">
                    		<span class="area-left">华南西南</span>
                    		<div class="area-right">
                    			<a href="http://cd.youlanw.com">成都</a><a href="http://nn.youlanw.com">南宁</a><a href="http://cq.youlanw.com">重庆</a><a href="http://sz.youlanw.com">深圳</a><a href="http://gz.youlanw.com">广州</a><a href="http://dg.youlanw.com">东莞</a>
                    			<a href="http://guilin.youlanw.com">桂林</a><a href="http://leshan.youlanw.com">乐山</a><a href="http://meishan.youlanw.com">眉山</a><a href="http://km.youlanw.com">昆明</a><a href="http://dz.youlanw.com">德州</a><a href="http://yb.youlanw.com">宜宾</a>
                    		</div>
                    	</div>
                    	<div class="area-item">
                    		<span class="area-left">华中西北</span>
                    		<div class="area-right">
                    			<a href="http://wh.youlanw.com">武汉</a><a href="http://xa.youlanw.com">西安</a><a href="http://baoji.youlanw.com">宝鸡</a><a href="http://zz.youlanw.com">郑州</a><a href="http://cs.youlanw.com">长沙</a><a href="http://weinan.youlanw.com">渭南</a>
                    			<a href="http://puyang.youlanw.com">濮阳</a><a href="http://ak.youlanw.com">安康</a><a href="http://lz.youlanw.com">兰州</a><a href="http://anyang.youlanw.com">安阳</a><a href="http://smx.youlanw.com">三门峡</a><a href="http://xuchang.youlanw.com">许昌</a>
                    		</div>
                    	</div>
                    </div>
                    <h4 class="doc-des-h4 city-more"><a href="${ctx }/about/chooseCity" class="right">更多&nbsp;&gt;&gt;</a></h4>
                </div>
            </div>
        </div>
    </div>
</div>
