<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data.jobBase.title}_${data.jobBase.company.name}招聘_${data.jobBase.company.abbreviation}_招聘${data.jobBase.jobname}_招聘信息_${data.jobBase.city.cityName }</title>
<meta name="keywords" content="${data.jobBase.title?replace('\"',' ')}${data.jobBase.company.name }招聘  ${data.jobBase.company.abbreviation} ${data.jobBase.jobname}招聘信息 ${data.jobBase.company.abbreviation}招聘信息" />
<meta name="description" content="${data.jobBase.company.name }最新招聘信息，${data.jobBase.title}。${data.jobBase.company.abbreviation}工作地点位于${data.jobBase.address}，公司规模{cdc:convertDicStaffscale(jobBase.company.staffscale)}，薪资待遇${data.jobBase.totalsalary}，学历要求{cdc:converDicEducation(jobDetail.education)}。" />
</head>
<body>
<!--/top-menu--->
    <div class="nav-g"><a href="${ctx}/">首页</a>&gt;<a href="${ctx}/zhaopin/">我要找工作</a>&gt;<a href="${ctx}/zhaopin_${data.jobBase.id}.html" class="red">${data.jobBase.title }</a>
    <a href="${ctx}/zhaopin_${data.nextJobBase.id}.html" class="fr red mr10">[下一条]</a></div>
    <!--基本信息-->
    <div class="w990">
        <div class="job-summary">
        	<!--图集-->
			<div class="pic-slide">
				<div class="bd">
					<ul>
						<#if data.imageList??>
						<#list data.imageList as img>
                    		<#if img_index < 4>
									<li>
										<img src="{cdc:getImagePath720(img.imgpath) }" />
									</li>
							</#if>
						</#list>
						<#else>
                    		<li><img src="{cdc:getImageByJobType720(jobBase.jobType)}" /></li>
                    	</#if>
                    	

					</ul>
				</div>
				<div class="hd">
					<ul>
						<#if data.imageList ??>
							<#list data.imageList as img>
							<#if img_index < 4>
									<li>
										<img src="{cdc:getImagePath720(img.imgpath) }" />
									</li>
							</#if>
						    </#list>
						<#else>
                    		<li><img src="{cdc:getImageByJobType720(jobBase.jobType)}" id="defaultImage"/></li>
                    	</#if>
                    	
					</ul>
					<#if (data.imageList?size>4)>
						 <a href="${ctx}/company/companyDetailAlbum?id=${data.jobBase.company.id }" target="_blank">
							<span class="link">
								<p>更多图片</p>
								<p>（${data.imageList?size - 1}张）</p>
							</span>
						</a>
					</#if>
				</div>
				<a class="prev" href="javascript:void(0)"></a>
				<a class="next" href="javascript:void(0)"></a>
			</div>
			<!--/图集-->
           <!--基本信息-->
           <div class="sum">
           		<h1 class="h1-g">${data.jobBase.title }</h1>
                <p class="salary">￥${data.jobBase.jobDetail.salaryfrom }<#if data.jobBase.jobDetail.salaryto ??>-${data.jobBase.jobDetail.salaryto }元/月</#if>
		        <#if data.jobBase.jobDetail.salaryto??><#else>起</#if></li></p>
                <p><label class="name-g">上班地址</label>
                {cdc:convertProvinceAbbreviation(jobBase.company.provinceid)}
                     ${data.jobBase.company.city.cityName}
                    {cdc:converCountyName("",jobBase.company.countyid)}
                    ${data.jobBase.company.address }
                </p>
                <p><label class="name-g">要&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;求</label>
                {cdc:converDicEducation(data.jobDetail.education)}<label class="line-g">|</label>{tc:convertGenderLimit(data.jobDetail.gender)}<label class="line-g">|</label><#if data.jobDetail.agefrom ??>
                        ${data.jobDetail.agefrom }<#if data.jobDetail.ageto ??>~${data.jobDetail.ageto}岁</#if>
                        <#if data.jobDetail.ageto ??>岁以上</#if></#if>
                        <#if data.jobDetail.agefrom ??>年龄不限</#if></p>
                <p><label class="name-g">更新时间</label>${data.jobBase.updatetimeString}</p>
                <p class="num">
                	<span><strong><#if data.jobDetail.showApplyCount ??>${data.jobDetail.showApplyCount }<#else>0</#if></strong>人报名</span>
                    <span>共招聘<strong> &nbsp;${data.jobBase.recruitcount }</strong>人</span>
                    <span class="to-ask-info cursor bd0"><strong class="advisory-count">0</strong>条咨询</span>
                </p>
                <p class="tags-g mt15">
                	<list data.jobBase.jobLabel as label>
					<a href="${ctx}/s_label_{label }">{label }</a>
				 	</list>
				 </p>
                  <#if (data.jobBase.cooperationType == 1) && (data.jobBase.jobConfig.isRecruitment == 0)><p class="sign"><a data-id="${data.jobBase.id }" class="btn signup-layer-trigger btn-signup fl" >我要报名</a></#if>
                  <#if (data.jobBase.cooperationType != 1) || (data.jobBase.jobConfig.isRecruitment != 0)> <p class="sign"><a data-id="${data.jobBase.id }" class="btn signup-layer-trigger btn-signup fl" >我要预约</a></#if>
                <label class="c999 fl ml20">优蓝网所有工作信息均通过严格审核和认证，请放心报名。</label></p>
                <p class="mt20">
                	<label class="name-g">服务保障</label>
                    <span class="ml10"><label class="text-icon">验</label>100%真实信息</span>
                    <span class="ml20"><label class="text-icon">保</label>不满意随时投诉</span>
                    <span class="ml20"><label class="text-icon">快</label>24小时快速响应</span>
                </p>
           </div>
           <!--/基本信息-->
           <div class="clear"></div>
        </div>
    </div>
     <!--/基本信息-->
    
    <div class="w990 job-detail img-hover mt10">
    	<!--left-->
        <div class="box-l-wrapper">
             <!--菜单-->
            <div class="box-l">
            	<div id="scroll-menu">
                	<div class="top-fixed">
                        <ul class="tab-g-title nav">
                            <li class="active"><a href="#company-short">企业信息</a></li>
                            <li><a href="#salary-info">工资情况</a></li>
                            <li><a href="#work-info">做啥事情</a></li>
                            <li><a href="#require-info">什么要求</a></li>
                            <li><a href="#eat-info">吃住情况</a></li>
                            <li><a href="#pay-info">福利待遇</a></li>
                 			<#if (data.jobBase.cooperationType == 1) && (data.jobBase.jobConfig.isRecruitment == 0 )><p class="sign"><a data-id="${data.jobBase.id }" class="btn signup-layer-trigger " >我要报名</a></#if>
                  			<#if (data.jobBase.cooperationType != 1)|| (data.jobBase.jobConfig.isRecruitment != 0) ><p class="sign"><a data-id="${data.jobBase.id }" class="btn signup-layer-trigger" >我要预约</a></#if>
                        </ul>
                    </div>
                </div>
            </div>
            <!--/菜单-->
            <!--企业信息-->
            <div class="box-l mt10">
            	<ul class="company-short" id="company-short">
                	<li class="map">
                    	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=PnYGbChqQrsmSUczLQrRZvw2"></script>
                        <div id="allmap" style="width:255px;height:190px;">
                        </div>
                        <script type="text/javascript">
                         // 百度地图API功能
                        var map = new BMap.Map("allmap");
                        var point = new BMap.Point(116.331398,39.897445);
                        map.centerAndZoom(point,12);
                        map.addControl(new BMap.NavigationControl()); 
                        map.addControl(new BMap.ScaleControl()); 
                        // 创建地址解析器实例
                        var myGeo = new BMap.Geocoder();
                        // 将地址解析结果显示在地图上,并调整地图视野
                        myGeo.getPoint("${data.jobBase.company.address}", function(point){
                            if (point) {
                                map.centerAndZoom(point, 16);
                                map.addOverlay(new BMap.Marker(point));
                            }
                        }, "${data.jobBase.company.city.cityName}");
                        </script>
                    </li>
                    <li class="company"><h2><a href="${ctx}/qiye_${data.jobBase.company.id}.html">${data.jobBase.company.name }</a></h2></li>
                    <li class="amount"><label class="c999">企业规模</label>{cdc:convertDicStaffscale(jobBase.company.staffscale) }</li>
                    <li class="addr"><label class="c999">企业地址</label>
                    {cdc:convertProvinceAbbreviation(jobBase.company.provinceid)}
                     ${data.jobBase.company.city.cityName}
                    {cdc:converCountyName("",jobBase.company.countyid)}
                    ${data.jobBase.company.address }</li>
                    <li class="intro">
                    	<label class="c999 fl">企业简介</label>
                        <span class="fl">${data.jobBase.company.introductionView}<a href="${ctx}/qiye_${data.jobBase.company.id}.html" class="a-orange" target="_blank" title="${data.jobBase.company.name}">(查看更多)</a></span>
                    </li>
                </ul>
            </div>
            <!--/企业信息-->
            <!--详情-->
            <div class="box-l job-content mt10">
                <div style="padding:0 15px;">
                    <!--工资情况--->
                    <div id="salary-info">
                        <h2 class="h2-g-1 icon">[工资情况]<i></i></h2>
                        <p class="h2-sub">出来打个工，每分每毛都是辛苦钱，工资切记要算清。</p>
                        <div class="salary-info-content">${data.jobDetail.salarydesc }</div>
                    </div>
                    <!--/工资情况-->
                    <div class="hr-g"></div>
                    
                    <!--做啥事情--->
                    <div id="work-info">
                        <h2 class="h2-g-1 icon">[做啥事情]<i class="icon-work"></i></h2>
                        <p class="h2-sub">仔细了解平时都干什么活，能学到啥技术，心里更有底。</p>
                        <div class="salary-info-content">${data.jobDetail.workdesc }</div>
                    </div>
                    <!--/做啥事情-->
                    <div class="hr-g"></div>
                    
                    <!--什么要求--->
                    <div id="require-info">
                        <h2 class="h2-g-1 icon">[什么要求]<i class="icon-demand"></i></h2>
                        <p class="h2-sub">看清楚入职要求，重要资料带身上，避免吃亏上当。</p>
                        <div class="salary-info-content">${data.jobDetail.demanddesc }</div>
                    </div>
                    <!--/什么要求-->
                    <div class="hr-g"></div>
                    
                    <!--吃住情况--->
                    <div id="eat-info">
                        <h2 class="h2-g-1 icon">[吃住情况]<i class="icon-eat"></i></h2>
                        <p class="h2-sub">好企业更体恤员工，一日三餐，吃住有保障。</p>
                        <div class="salary-info-content">${data.jobDetail.mealsdesc }</div>
                    </div>
                    <!--/吃住情况-->
                    <div class="hr-g"></div>
                    
                    <!--福利待遇--->
                    <div id="pay-info">
                        <h2 class="h2-g-1 icon">[福利待遇]<i class="icon-welfare"></i></h2>
                        <p class="h2-sub">蓝领也要待遇好，日常福利不能少。</p>
                        <div class="salary-info-content">${data.jobDetail.welfaredesc }</div>
                    </div>
                    <!--/福利待遇-->
                </div>
            </div>
            <!--/详情-->
            <!--相似岗位-->
            <div class="box-l bg-white mt10">
            	<div class="box-l-3">
                    <h2 class="column-title">相似岗位</h2>
                    <div class="job-recommend-list job-recommend-list2 img-hover">
                    	<#list data.likeJobs as likeJob>
				            <ul class="recommned-each">
	                            <li class="pic"><a href="${ctx}/zhaopin_${likeJob.id}.html">
	                            	<#if likeJob.thumbnialImage.imgpath??>
										<img src="{cdc:getImageByJobType320(likeJob.jobType)}" alt="${likeJob.title }" />
									<#else>
										<img src="{cdc:getImagePath320(likeJob.thumbnialImage.imgpath)}" alt="${likeJob.title}" />
									</#if>
	                            </a></li>
	                            <li class="opacity-bg"></li>
	                            <a href="${ctx}/qiye_${likeJob.company.id}.html">
	                            <li class="opacity-text">{tc:highlightText(likeJob.company.name ,wd) }</li></a>
	                            <li class="salary">￥${likeJob.jobDetail.salaryfrom }<#if likeJob.jobDetail.salaryto??>-${likeJob.jobDetail.salaryto }元/月
		        				<#else>起</#if></li>
	                            <li class="title"><a href="${ctx}/zhaopin_${likeJob.id}.html">${likeJob.title }</a></li>
	                        </ul>
				         </#list>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
            <!--/相似岗位-->
             <div class="box-l mt10">
            	<!--网友问答--->
                <div class="box-g pdb0">
                	<h2 class="company-title"><span>网友问答</span>  <!-- <a href="javascript:void(0)" class="company-more-new">更多问答 ></a> --></h2>
                </div>
                <div id="ask-info" class="pdt0">
                    <div class="mb20">
                    <form id="advisory_form" >
                    	<input type="hidden" name="companyId" value="${data.jobBase.company.id }" />
                     	<input type="hidden" name="jobId" value="${data.jobBase.id }" />
                        <textarea class="textarea-g tip-text" name="comment" id="advisory_content">我也说两句...</textarea>
                        <span class="btn-square-wrapper">
                        	<input type="button" value="提交问题" class="btn btn-square" id="btn_sub_advisory"/><i><p>提交</p><p>问题</p></i>
                        </span>
                    </form>
                        <div class="clear"></div>
                    </div>
                   <div id="advisory"></div>
                    <div class="clear"></div>
                </div>
                <!--/网友问答-->
            </div>
        </div>
        <!--/left-->
        <!--right-->
        <div class="box-r-wrapper">
            <!--优蓝网手机版-->
            <div class="box-r">
                <div class="m-entry">
                    <div class="text">
                    	<i></i>
                        <h3>优蓝网手机版</h3>
                        <!-- <p>好工作一手掌握</p> -->
                        <p>m.youlanw.com</p>
                    </div>
                   <!--  <p class="mt15">关注【优蓝求职】微信号</p>
                    <p class="mt5">好工作不错过</p> -->
                    <img src="${ctx}/static/images/QR2.jpg" alt="" class="mt5"/>
                </div>
            </div>
            <!--/优蓝网手机版-->
               <!--优蓝网微信版-->
            <div class="box-r mt10">
                <div class="m-entry">
                    <div class="text">
                    	<i class="wechat"></i>
                        <h3>优蓝网微信版</h3>
                        <p>微信号：youlanqiuzhi</p>
                    </div>
                    <img src="${ctx}/static/images/QR.jpg" alt=""/>
                </div>
            </div>
            <!--/优蓝网微信版-->
            <#if data.rightJobListRecommend??>
            <#list data.rightJobListRecommend as recommend>
	            <div class="ad-r">
	                <a href="${recommend.link}"><img src="{cdc:getImagePathO(recommend.img) }" alt="${recommend.imgAlt }"/></a>
	            </div>
           </#list>
           </#if>
           	<!--最近浏览-->
			<div class="box-r mt10">
				<div class="right-fixed right-fixed2" id="recent_browse_list">
					<h2 class="box-title">最近浏览职位</h2>
					<div class="job-list2">
						<p class="no-result">
							<span><i></i></span>暂无浏览记录
						</p>
					</div>
				</div>
			</div>

		<!-- 	<div class="box-r mt10" >
					<div class="right-fixed right-fixed2" id="recent_browse_list">
					
					</div>
			</div> -->
			<!--最近浏览-->
        </div>
        <!--/right-->
    	<div class="clear"></div>
    </div>
      <!---->
          <!--关键词-->
		<div class="w990 mt20">
			<ul class="tab-title">
				<li class="current">最新岗位</li>
				<li>热词</li>
			</ul>
			<div class="tab-each show">
			<#if data.newJobs??>
				<#list data.newJobs as hotJob>
					<a href="<c:url value="${ctx}/job/detail/${hotJob.jobId}.html" />">${hotJob.jobTitle }</a>
				</#list>
			</#if>
			</div>
			<div class="tab-each each-zone">
			<#if hotWords??>
			<#list hotWords as hotWord>
				<a href="<c:url value="{tc:getHotWordValue(hotWord,'url')}" />">{tc:getHotWordValue(hotWord,'text') }</a>
			</#list>
			</#if>
			</div>
			<div class="clear"></div>
		</div>
		<!--/关键词-->
<!--报名弹出层-->
<div class="signup-layer relative hide">

	<!--我要报名-->
	<div id="divSignup">
	<form id="signup_from" onsubmit="return false">
        <ul class="job">
					<li class="tips red" id="err_msg">
						<!-- 这里是验证提示信息 -->
					</li>
					<input type="hidden" name="jobid" value="${data.jobBase.id}" />
					<li class="pic"><a href="${ctx}/zhaopin_${data.jobBase.id}.html" target="_blank">
						<#if data.jobBase.thumbnialImage.imgpath??>
						<img src="{cdc:getImagePath320(jobBase.thumbnialImage.imgpath)}" alt="${data.jobBase.title }" />
						<#else>
						<img src="{cdc:getImageByJobType320(jobBase.jobType)}"/>
						</#if>
						</a></li>
					<li class="title">
						<a href="${ctx}/zhaopin_${data.jobBase.id}.html" target="_blank">${data.jobBase.title}</a></li>
					<li class="salary">月薪：￥${data.jobBase.jobDetail.salaryfrom }
					<#if data.jobBase.jobDetail.salaryto??>-${data.jobBase.jobDetail.salaryto }元/月<#else>起</#if>
		        </li>
					<li class="area">地址： 
					{cdc:convertProvinceAbbreviation(jobBase.company.provinceid)}
                     ${data.jobBase.company.city.cityName}
                    {cdc:converCountyName("",data.jobBase.company.countyid)}
                    ${data.jobBase.company.address }</li>
				</ul>
        <div class="clear"></div>
        <div class="hr-dot-g full-width mt20"></div>
		<#if (data.jobBase.cooperationType == 1)&& (data.jobBase.jobConfig.isRecruitment == 0)>
			<p class="orange text-center">本岗位已通过严格审核和认证，请放心报名</p>
		<#else>
			<p class="orange text-center">该岗位报名人数已满，您可以预约其他同类型岗位</p>
		</#if>
		
        <div class="form-g-medium fl">
        	<div class="two-inline">
            	<!--姓名-->
				<div class="form-each">
					<span class="form-each-name">姓名：</span><input type="text"
						class="form-each-input" name="name" id="input_name"
						value="" />
				</div>
				<!--/姓名-->
				<!--手机-->
				<div class="form-each">
					<span class="form-each-name">手机：</span><input type="text"
						class="form-each-input" name="mobile" id="input_phone"
						maxlength="11" value="" />
				</div>
				<!--/手机-->
                <div class="clear"></div>
            </div>
            <p class="tip" id="NameOrMobile">
						<!--请输入正确的手机号，姓名-->
			</p>
          <!--在哪里-->
            <div class="form-each">
                <span class="form-each-name">在哪里：</span>
                 <input type="text" name="intention" placeholder="请输入城市或省份..." id="keywordSignUp" class="form-each-input" />
            </div>
            <p class="tip" id="keywordSignUpTag"><!----></p>
            <!--/在哪里-->
            
           <#if (data.jobBase.cooperationType == 1) && (data.jobBase.jobConfig.isRecruitment == 0)>
			<!--性别-->
            <div class="form-each">
                <span class="form-each-name">性别：</span>
               <div class="select-g select-g-2">
					<div class="select-g-title" id="genderTitle">
						<span>您的性别</span>
						<span>男</span>
						<span>女</span>
					</div>
					<ul class="select-g-options">
						<li>男</li>
						<li>女</li>
					</ul>
					<select class="original-select" name="gender" id="genderSelect">
						<option value="1">男</option>
						<option value="2">女</option>
					</select>
				</div>
            </div>
            <p class="tip" id="genderTitleTip"><!----></p>
            <!--/性别-->
            
            <!--学历-->
            <div class="form-each">
                <span class="form-each-name">学历：</span>
               <div class="select-g select-g-3">
					<div class="select-g-title" id="educationTitle">
					</div>
					<ul class="select-g-options">
						<#list data.educationList as education>
							<li>${education.name }</li>
						</#list>
					</ul>
					<select class="original-select" name="education">
						<#list data.educationList as education>
							<option value="${education.code }">${education.name }</option>
						</#list>
					</select>
				</div>
            </div>
            <p class="tip" id="educationTitleTip"><!----></p>
            <!--/学历-->
            <input type="hidden" name="type" value="2"/>
            <div class="form-btn"><input type="button" value="我要报名" class="btn btn-large" id="btn_signup"/></div>
             <input type="hidden" value="恭喜你报名成功" id="loginMsg"/>
            </#if>
             <#if (data.jobBase.cooperationType != 1) || (data.jobBase.jobConfig.isRecruitment!=0) >
			<!--做什么-->
            <div class="form-each">
                <span class="form-each-name">做什么：</span>
                <div class="select-g select-g-2">
                <div class="select-g-title" id="industryTitle"><span>做什么事情</span></div>
					<ul class="select-g-options">
						<li>不限岗位</li>
						<#list data.jobTypes as types>
							<li>${types.jobName }</li>
						</#list>
					</ul>
					<select class="original-select" name="intentionIndustry">
						<option value="">不限岗位</option>
						<#list data.jobTypes as types>
							<option value="${types.id }">${types.jobName }</option>
						</#list>
					</select>
                </div>
            </div>
            <p class="tip" id="industryTitleTip"><!----></p>
            <!--/做什么-->
            
            <!--联系时间-->
            <div class="form-each">
                <span class="form-each-name">联系时间：</span>
                <div class="select-g select-g-3">
                  <div class="select-g-title" id="timeTitle"><span>希望在什么时间联系您</span></div>
                  <ul class="select-g-options">
                    <li>任何时间</li>
                   <li>上午（9:00-12:00）</li>
					<li>中午（12:00-14:00）</li>
					<li>下午（14:00-19:00）</li>
					<li>晚上（19:00以后）</li>
                  </ul>
                  <select class="original-select" name="contactTime">
                 	    <option value="任何时间">任何时间</option>
                  		<option value="上午（9:00-12:00）">上午（9:00-12:00）</option>
                  		<option value="中午（12:00-14:00）">中午（12:00-14:00）</option>
                  		<option value="下午（14:00-19:00）">下午（14:00-19:00）</option>
                  		<option value="晚上（19:00以后）">晚上（19:00以后）</option>
					</select>
                </div>
            </div>
            <p class="tip" id="timeTitleTip"><!----></p>
            <!--/联系时间-->

			<div class="clear"></div>
            <div class="form-checkbox">
            	<input type="checkbox" checked="checked" name="isAcceptRec"/>
            	如果该职位暂停招聘，可以考虑相似职位
            </div>
             <input type="hidden" name="type" value="4"/>
            <div class="form-btn"><input type="button" value="我要预约" class="btn btn-large" id="btn_signup"/></div>
             <input type="hidden" value="恭喜你预约成功" id="loginMsg"/>
            </#if>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    <!--/我要报名-->
	</form>
	</div>
	<#if (data.jobBase.cooperationType!=1) || (data.jobBase.jobConfig.isRecruitment!=0) >
 <!--报名成功-->
		<div id="divSuccess" class="success hide">
			<!--提示信息-->
			<div class="success-tip" id="divSingUpSuccess">
				<i class="icon-success2"></i>
				<p class="text1">您已预约成功</p>
			</div>
			<!--/提示信息-->
			<!---->
			<div class="leadto" id="divLogin">
				<p class="f13"  style="position:relative;left:122px;top:-15px;text-align:left">您已经是优蓝网会员，现在可以直接登录</p>
				<a href="javascript:void(0);" id="a_login" class="btn btn-medium">直接登录</a>
				<a href="javascript:void(0);" class="notLogin a-blue f14 layer-close">暂不登录</a>
			</div>
			<!---->

			<!--激活账户-->
			<div class="activate fl hide" id="divActivate">
				<p class="f13 text-center">
					<span class="icon-warn relative"></span>您目前还不是优蓝网会员，是否激活账户
				</p>
				<div class="hr-g"></div>
				<div class="form-g-medium fl">
					<!--手机号-->
					<div class="form-each">
						<span class="form-each-name">手机号：</span><input
							id="input_actmobile" type="text" class="form-each-input"
							readonly="readonly" />
					</div>
					<p class="tip">
						<!-- 请输入正确的手机号 -->
					</p>
					<!--/手机号-->
					<!--验证码-->
					<div class="form-each form-each-code">
						<span class="form-each-name">验证码：</span><input id="input_thekey"
							type="text" class="form-each-input" />
						<!--<span class="btn">免费获取验证码</span>-->
						<span id="span_gain_val_key" class="btn" onclick="gainkey()">免费获取验证码</span>
					</div>
					<p class="tip" id="p_thekey"></p>
					<!--/验证码-->
					<!--设置密码-->
					<div class="form-each">
						<span class="form-each-name">设置密码：</span><input type="password"
							id="input_password" class="form-each-input" />
					</div>
					<p class="tip" id="p_password"></p>
					<!--/设置密码-->
					<div class="form-btn">
						<input type="button" id="btn_activate" value="立即激活"
							class="btn btn-large" />
					</div>
					<div class="clear"></div>
					<p class="mt10 c999 text-center">
						<a href="javascript:void(0);" id="a_nothanks">暂不激活</a>
					</p>
				</div>
			</div>
			<!--/激活账户-->
		</div>
		<!--/报名成功-->
    </#if>
    <#if (data.jobBase.cooperationType == 1) && (data.jobBase.jobConfig.isRecruitment == 0)>
    <!--报名成功-->
		<div id="divSuccess" class="success hide">
			<!--提示信息-->
			<div class="success-tip" id="divSingUpSuccess">
				<i class="icon-success2"></i>
				<p class="text1">恭喜您！报名成功</p>
				
			</div>
			<!--/提示信息-->
			<!---->
			<div class="leadto" id="divLogin">
				<p class="f13"  style="position:relative;left:122px;top:-15px;text-align:left">您已经是优蓝网会员，现在可以直接登录</p>
				<a href="javascript:void(0);" id="a_login" class="btn btn-medium">直接登录</a>
				<a href="javascript:void(0);" class="notLogin a-blue f14 layer-close">暂不登录</a>
			</div>
			<!---->

			<!--激活账户-->
			<div class="activate fl hide" id="divActivate">
				<p class="f13 text-center">
					<span class="icon-warn relative"></span>您目前还不是优蓝网会员，是否激活账户
				</p>
				<div class="hr-g"></div>
				<div class="form-g-medium fl">
					<!--手机号-->
					<div class="form-each">
						<span class="form-each-name">手机号：</span><input
							id="input_actmobile" type="text" class="form-each-input"
							readonly="readonly" />
					</div>
					<p class="tip">
						<!-- 请输入正确的手机号 -->
					</p>
					<!--/手机号-->
					<!--验证码-->
					<div class="form-each form-each-code">
						<span class="form-each-name">验证码：</span><input id="input_thekey"
							type="text" class="form-each-input" />
						<!--<span class="btn">免费获取验证码</span>-->
						<span id="span_gain_val_key" class="btn" onclick="gainkey()">获取手机验证码</span>
					</div>
					<p class="tip" id="p_thekey"></p>
					<!--/验证码-->
					<!--设置密码-->
					<div class="form-each">
						<span class="form-each-name">设置密码：</span><input type="password"
							id="input_password" class="form-each-input" />
					</div>
					<p class="tip" id="p_password"></p>
					<!--/设置密码-->
					<div class="form-btn">
						<input type="button" id="btn_activate" value="立即激活"
							class="btn btn-large" />
					</div>
					<div class="clear"></div>
					<p class="mt10 c999 text-center">
						<a href="javascript:void(0);" id="a_nothanks">暂不激活</a>
					</p>
				</div>
			</div>
			<!--/激活账户-->
		</div>
		<!--/报名成功-->
    </#if>
    <div class="clear"></div>
</div>
<!--/报名弹出层-->
	<script src="${ctx}/static/js/jquery.SuperSlide.2.1.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/scroll-monitor.js"></script>
	<script src="${ctx}/static/js/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.autocomplete.css" />
	<script src="${ctx}/static/js/youlanw.pc.js"></script>
	<script>
    $(function(){
    $(".fl a[rel=nofollow]:eq(0)").hide().next().hide().next().hide();
				var ids = [];
    			var rbs = Cookie.getCookie("recent_browse_job");
    	    	if(rbs!=null&&rbs!=undefined&&rbs!=""){
    	    	var totalsalary = "${data.jobBase.jobDetail.salaryfrom}";
    	    	var salaryTo = "${data.jobBase.jobDetail.salaryto}";
    	    	var defaultImage = $("#defaultImage").attr("src");
    	    	if(salaryTo==null || salaryTo ==""){
    	    		totalsalary +="起";
    	    	}else{
    	    		totalsalary +="-"+salaryTo+"元/月";
    	    	}
    	    	var str='';
    	    	<#if data.jobBase.thumbnialImage.imgpath??>{cdc:getImageByJobType320(jobBase.jobType)}
	    		 	str = '{"id":"${data.jobBase.id}","title":"${data.jobBase.title}","imgpath":"{cdc:getImagePath320(jobBase.thumbnialImage.imgpath!)}","totalsalary":"'+totalsalary+'","imgAlt":"${data.jobBase.thumbnialImage.comment!}"}';
    		 	<#else>
	    		 	str = '{"id":"${data.jobBase.id}","title":"${data.jobBase.title}","imgpath":"{cdc:getImageByJobType320(jobBase.jobType)}","totalsalary":"'+totalsalary+'","imgAlt":"${data.jobBase.thumbnialImage.comment!}"}';
	    		</#if>
    	    	var jsonObj = eval("("+unescape(rbs)+")");
    	    	var tmpArray = [];
    	    		$.each(jsonObj,function(i,j){
    	    			if($.inArray(j.id,ids)==-1){
    	    				var tmp= '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"'+j.imgpath+'","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.imgAlt+'"}';
    	       				tmpArray.push(tmp);
    	       				ids.push(j.id);
    	    			}
    	    		});
    	    		saveRecent(tmpArray);
    	    		tmpArray.push(str);
    	    		Cookie.setCookie('recent_browse_job', escape("["+tmpArray.join(","))+"]", 5);
    	        	
    	    }else{
    	    		Cookie.setCookie('recent_browse_job', escape("[]"), 5);
    	    }
	});
	</script>
</body>
</html>