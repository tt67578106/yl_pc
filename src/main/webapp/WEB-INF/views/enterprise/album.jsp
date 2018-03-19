<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdc" uri="http://youlanw.com/tags/dic_code_functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业相册</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/common.js?v=20150417"></script>
<script src="${ctx}/static/js/layer.min.js"></script>
<script src="${ctx}/static/js/cookie.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/enterprise.css?v=20150505"/>
</head>
<body>
 <!--page-wrapper-->
<div class="page-wrapper ent-bg">
	<!--ent-top-->
    <jsp:include page="/WEB-INF/layouts/enterpriseTop.jsp"></jsp:include>
    <!--/ent-top-->
    <!--header-->
    <jsp:include page="/WEB-INF/layouts/enterpriseHeader.jsp"></jsp:include>
    <!--/header-->
    <!---->
    <div class="box-out mt15">
    	<!--left-->
        <jsp:include page="/WEB-INF/layouts/enterpriseLeft.jsp"></jsp:include>
        <!--/left-->
        <!--right-->
        <div class="ent-box-r-wrapper">
            <div class="ent-box-r">
            	<!--tab-g-->
                <div class="tab-g ent-album">
                	<!--title-->
                	<ul class="title">
                    	<a href="${ctx}/album" ><li class="${current}" >全部图片</li>
                        <a href="${ctx}/album?category=1"><li  class="${current1 }">企业实景<span class="num">（${scene1}）</span></li></a>
                        <a href="${ctx}/album?category=2"><li  class="${current2 }">工作环境<span class="num">（${scene2}）</span></li></a>
                        <a href="${ctx}/album?category=3"><li  class="${current3 }">食堂宿舍<span class="num">（${scene3}）</span></li></a>
                        <a href="${ctx}/album?category=4"><li  class="${current4 }">生活娱乐<span class="num">（${scene4}）</span></li></a>
                        <a href="${ctx}/album?category=5"><li  class="${current5 }">企业周边<span class="num">（${scene5}）</span></li></a>
                    	<a href="${ctx}/album?category=6"><li  class="bdr0 ${current6 }">其他<span class="num">（${scene6}）</span></li></a>
                    </ul>
                    <!--/title-->
                    <!--全部图片-->
                    <div class="content-each show">
                    	
                   		<c:if test="${fn:length(page.content)==0}">
	                   	<!--暂无照片-->
	                       <div class="ent-no-result">
	                           <span class="icon-smile"><i></i></span>暂时还没有上传照片哦~！ <a href="javascript:void(0)" class="ml10 ent-upload-trigger">上传照片</a>
	                       </div>
                   		<!--/暂无照片-->
                   		</c:if>
                        <c:if test="${fn:length(page.content)>0}">
                        <a href="javascript:;" class="btn-g-2 upload ent-upload-trigger">上传照片</a>
                        <!--图片-->
                        <div class="ent-album-list">
                        	<ul>
								<c:forEach items="${page.content}" var="scene">                       	
	                                <li class="">
	                                    <img src="${cdc:getImagePath320(scene.image.imgpath)}" alt="${scene.image.comment}" />
	                                    <p class="des" >${scene.image.comment}</p>
                                    	<input type="hidden" value="${scene.id}" id="id" name="id">
                                    	<input type="text" class="des-input hide" name="comment" id="comment" onBlur="updateComment(this)" value="${scene.image.comment}"/>
	                                    <span class="oper">
	                                    	<a href="javascript:;" class="btn-g-blue-small fl updateAlbum" >修改</a>
	                                        <a href="javascript:;" class="btn-g-2-small fl ml15" onclick="delCompanySceneById(${scene.id})">删除</a>
	                                    </span>
	                                </li>
                                </c:forEach> 
                            </ul>
                        </div>
                        <!--/图片-->
                         <!--分页-->  
                         <div class="clear"></div>
						<c:if test="${not empty page.content}">
	          				<div class="paging"><tags:pagination paginationSize="5" page="${page }" pageType="album?category=${category }&&"/></div>
	       				</c:if>	                      
                		<!--/分页-->
                		</c:if>
                      </div>
                    <!--/全部图片-->
                    <div class="clear"></div>
                </div>
                <!--/tab-g--> 
            </div>
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
    <!---->
    <!--footer-->
   	<jsp:include page="/WEB-INF/layouts/footer.jsp"></jsp:include>
    <!--/footer-->    
</div>   
<!--/page-wrapper-->

<!--上传照片--> 
<div class="ent-upload">
	<!--inner-->
	<div class="ent-upload-inner">
    	<!--top-->
        <div class="top">
       		<form id="upload_form" action="" method="post">
        	<div class="fl upload-file">
            	<div class="inner">
            	<a href="javascript:;" class="btn-g-blue">选择文件</a>
            	</div>
                <input type="file" name="file" class="file hide" multiple="multiple"/>
            </div>
           <!--  <a href="javascript:;" class="btn-g-2 fl btn-g-grey">开始上传</a> -->
            <!--相册分类-->
              <div class="form-each">
                <span class="form-each-name">相册分类：</span>
                <div class="select-g select-g-2">
                  <div class="select-g-title">
                  <c:if test="${category == 0}">
                  	<span >请选择相册分类</span>
                  </c:if>	
                  <c:if test="${category == 1}">
                  	<span >企业实景</span>
                  </c:if>
                  <c:if test="${category == 2}">
                  	<span >工作环境</span>
                  </c:if>
                  <c:if test="${category == 3}">
                  	<span >食堂宿舍</span>
                  </c:if>
                  <c:if test="${category == 4}">
                  	<span >生活娱乐</span>
                  </c:if>
                  <c:if test="${category == 5}">
                  	<span >企业周边</span>
                  </c:if>
                   <c:if test="${category == 6}">
                  	<span >其他</span>
                  </c:if>
                  </div>
                  <ul class="select-g-options">
                    <li >企业实景</li>
                    <li >工作环境</li>
                    <li >食堂宿舍</li>
                    <li >生活娱乐</li>
                    <li >企业周边</li>
                    <li >其他</li>
                  </ul>
                  <select class="original-select" name="category" id="category">
			         <option value="1" ${category=="1"?'selected':''}>企业实景</option>
			         <option value="2" ${category=="2"?'selected':''}>工作环境</option>
			         <option value="3" ${category=="3"?'selected':''}>食堂宿舍</option>
			         <option value="4" ${category=="4"?'selected':''}>生活娱乐</option>
			         <option value="5" ${category=="5"?'selected':''}>企业周边</option>
			         <option value="6" ${category=="6"?'selected':''}>其他</option>
                  </select>
                </div>
            </div>
            </form>
            <!--/相册分类-->
        </div>
        <!--/top-->
        <!--没有照片-->
        <c:if test="${fn:length(page.content) == 0}">
	        <div class="no-res upload-file">
	        	<div class="inner">
	        		<a href="javascript:;" class="btn-g-blue-big">添加照片</a>
	            </div>
	            <input type="file" name="file" class="file hide" multiple="multiple"/>
	        </div>
        </c:if>
        <!--没有照片-->
        <div class="clear"></div>
        <c:if test="${fn:length(page.content) != 0}">
        <!--照片预览-->
        <div class="list">
        	<ul>            
   			  <c:forEach items="${page.content}" var="scene">      
	           		<li>
	               	<img src="${cdc:getImagePath320(scene.image.imgpath)}" />
	                   <p class="des"/>${scene.image.comment}</p>
	               </li>
               </c:forEach>		
            </ul>
        <div class="clear"></div>
        </div>
        <!--/照片预览-->
        </c:if>
        <div class="clear"></div>
    </div>
    <!--/inner-->
</div>
    <script src="${ctx}/static/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script src="${ctx}/static/jquery-file-upload/js/jquery.iframe-transport.js"></script> 
    <script src="${ctx}/static/jquery-file-upload/js/jquery.fileupload.js"></script> 
<!--/上传照片-->
    <script type="text/javascript">
    $(function(){
    	//上传照片弹出层
    	$('.ent-upload-trigger').click(function(){
    		var page_ent_upload=$.layer({
    			type:1,
    			shade:[0],
    			area:['630px', 'auto'],
    			title:'上传照片',
    			border:[6, 0.3, '#000'],
    			shade:[0.5, '#000'],
    			shadeClose:true,
    			page:{dom :'.ent-upload'}
    		});
    	})
    	//点击修改显示出文本框提供修改
    	$(".updateAlbum").click(function(){
    		$(this).parent().parent().addClass("edit-mode");
    		$(this).parent().parent().find("input").removeClass("hide");
    		$(this).parent().parent().find("input").focus();
    	});
    	//图片上传
    	$("#upload_form").fileupload({
			url : "${ctx}/album/upload/companyAlbum?companyid=${enterprise.id}",
			done : function(e, data) {
				if (data.result == "tomuchcount") {
					alert('短时间内不要上传太多');
				}else{
					window.location.reload();
				}
			},
			//formData: {"category":$("#category").val()},
			send : function(e, data) {
				if (!valiFile(data)) {
					alert('文件不支持，企业logo格式限制为jpg/jpeg/png/gif，不超过3MB！');
					return false;
				}
			},
			success: function (data, textStatus) {  
				alert("图片上传成功");
		    },  
		    error: function (XMLHttpRequest, textStatus, errorThrown) {  
		      var msg = "上传出错，服务器出错，错误内容：" + XMLHttpRequest.responseText;  
		      $.messager.showWin({ msg: msg, title: '错误提示', color: 'red' });  
		    },  
			acceptFileTypes : /(\.|\/)(jpg|png|jpeg|gif)$/i,
			maxFileSize : 3000000,
			maxNumberOfFiles : 10
		}).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');;
});
 //图片上传类型校验
 function valiFile(data) {
	var regxp = new RegExp("\.(jpg|png|jpeg|gif)$", "g");
	var flag = false;
	$.each(data.files, function(index, file) {
		var namexp = file.name.toLowerCase().match(regxp);
		if (file.size <= 3000000 && namexp != null) {
			flag = true;
		};
	});
	return flag;
 };
//修改相册描述
 function updateComment(dom){
	var pattern=/[`~!！@#$%^&*()_+<>?？:"{},.\/;'[\]]/im;  
    if(pattern.test($(dom).val())){  
        alert("不能包含特殊字符");     
    }else{
    	$.post("${ctx}/album/updateAlbum",{"id":$(dom).parent().find("input[name='id']").val(),"comment":$(dom).val()},function(result){
      		 $(dom).addClass("hide");
      		 $(dom).parent().removeClass("edit-mode");
      		 $(dom).parent().find("p").html(result); 
      	 }); 
    }    
 }
//删除企业相册（假删）
 function delCompanySceneById(imgid) {
 	if(confirm("您确定要删除该图片吗?")){
 		$.get("${ctx}/album/deleteImg", {
 			"id" : imgid
 		}, function(result){
 			if (result == "success") {
 				alert("删除成功");
 				window.location.reload();
 			} else {
 				alert("删除失败");
 			}
 		});
 	}
 };
</script>
</body>
</html>