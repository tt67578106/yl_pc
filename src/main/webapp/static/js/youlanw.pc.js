var YL = {
		/**上下文路径临时变量*/
		ctx:undefined,
		initCtx:function(ctx){
			if(ctx == undefined || ctx == null){
				if(YL._ctx == undefined || YL.ctx == null){
					var obj=window.location;
					var contextPath=obj.pathname.split("/")[1];
					var basePath=obj.protocol+"//"+obj.host;
				    YL.ctx = basePath;
				}
			}else{
				YL.ctx = ctx;
			}
		},
		areas:["北京市","天津市","石家庄市","唐山市","秦皇岛市","邯郸市","邢台市","保定市","张家口市","承德市","沧州市","廊坊市","衡水市","太原市","大同市",
               "阳泉市","长治市","晋城市","朔州市","晋中市","运城市","忻州市","临汾市","吕梁市","呼和浩特市","包头市","乌海市","赤峰市","通辽市","鄂尔多斯市",
               "呼伦贝尔市","巴彦淖尔市","乌兰察布市","兴安盟","锡林郭勒盟","阿拉善盟","沈阳市","大连市","鞍山市","抚顺市","本溪市","丹东市","锦州市","营口市",
               "阜新市","辽阳市","盘锦市","铁岭市","朝阳市","葫芦岛市","长春市","吉林市","四平市","辽源市","通化市","白山市","松原市","白城市",
               "延边朝鲜族自治州","哈尔滨市","齐齐哈尔市","鸡西市","鹤岗市","双鸭山市","大庆市","伊春市","佳木斯市","七台河市","牡丹江市","黑河市","绥化市",
               "大兴安岭地区","上海市","南京市","无锡市","徐州市","常州市","苏州市","南通市","连云港市","淮安市","盐城市","扬州市","镇江市","泰州市","宿迁市",
               "杭州市","宁波市","温州市","嘉兴市","湖州市","绍兴市","金华市","衢州市","舟山市","台州市","丽水市","合肥市","芜湖市","蚌埠市","淮南市",
               "马鞍山市","淮北市","铜陵市","安庆市","黄山市","滁州市","阜阳市","宿州市","巢湖市","六安市","亳州市","池州市","宣城市","福州市","厦门市",
               "莆田市","三明市","泉州市","漳州市","南平市","龙岩市","宁德市","南昌市","景德镇市","萍乡市","九江市","新余市","鹰潭市","赣州市","吉安市",
               "宜春市","抚州市","上饶市","济南市","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市",
               "莱芜市","临沂市","德州市","聊城市","滨州市","荷泽市","郑州市","开封市","洛阳市","平顶山市","安阳市","鹤壁市","新乡市","焦作市",
               "濮阳市","许昌市","漯河市","三门峡市","南阳市","商丘市","信阳市","周口市","驻马店市","武汉市","黄石市","十堰市","宜昌市",
               "襄阳市","鄂州市","荆门市","孝感市","荆州市","黄冈市","咸宁市","随州市","恩施","神农架","长沙市","株洲市","湘潭市","衡阳市",
               "邵阳市","岳阳市","常德市","张家界市","益阳市","郴州市","永州市","怀化市","娄底市","湘西土家族苗族自治州","广州市","韶关市",
               "深圳市","珠海市","汕头市","佛山市","江门市","湛江市","茂名市","肇庆市","惠州市","梅州市","汕尾市","河源市","阳江市","清远市",
               "东莞市","中山市","潮州市","揭阳市","云浮市","南宁市","柳州市","桂林市","梧州市","北海市","防城港市","钦州市","贵港市","玉林市",
               "百色市","贺州市","河池市","来宾市","崇左市","海口市","三亚市","重庆市","成都市","自贡市","攀枝花市","泸州市","德阳市","绵阳市",
               "广元市","遂宁市","内江市","乐山市","南充市","眉山市","宜宾市","广安市","达州市","雅安市","巴中市","资阳市","阿坝藏族羌族自治州",
               "甘孜藏族自治州","凉山彝族自治州","贵阳市","六盘水市","遵义市","安顺市","铜仁地区","黔西南布依族苗族自治州","毕节地区",
               "黔东南苗族侗族自治州","黔南布依族苗族自治州","昆明市","曲靖市","玉溪市","保山市","昭通市","丽江市","思茅市","临沧市",
               "楚雄彝族自治州","红河哈尼族彝族自治州","文山壮族苗族自治州","西双版纳傣族自治州","大理白族自治州","德宏傣族景颇族自治州",
               "怒江傈僳族自治州","迪庆藏族自治州","拉萨市","昌都地区","山南地区","日喀则地区","那曲地区","阿里地区","林芝地区","西安市",
               "铜川市","宝鸡市","咸阳市","渭南市","延安市","汉中市","榆林市","安康市","商洛市","兰州市","嘉峪关市","金昌市","白银市",
               "天水市","武威市","张掖市","平凉市","酒泉市","庆阳市","定西市","陇南市","临夏回族自治州","甘南藏族自治州","西宁市","海东地区",
               "海北藏族自治州","黄南藏族自治州","海南藏族自治州","果洛藏族自治州","玉树藏族自治州","海西蒙古族藏族自治州","银川市","石嘴山市",
               "吴忠市","固原市","中卫市","乌鲁木齐市","克拉玛依市","吐鲁番地区","哈密地区","昌吉回族自治州","博尔塔拉蒙古自治州",
               "巴音郭楞蒙古自治州","阿克苏地区","克孜勒苏柯尔克孜自治州","喀什地区","和田地区","伊犁哈萨克自治州","塔城地区","阿勒泰地区",
               "石河子市","阿拉尔市","图木舒克市","五家渠市","台湾省","昆山市","仙桃市","河北省","山西省","内蒙古自治区","辽宁省","吉林省",
               "黑龙江省","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","广西壮族自治区",
               "海南省","四川省","贵州省","云南省","西藏自治区","陕西省","甘肃省","青海省","宁夏回族自治区","新疆维吾尔自治区","香港特别行政区",
               "澳门特别行政区","台湾省","海外"],
        top:{
        	getRecent:function(data){
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
        			   divs.push('<a href="'+YL.ctx+'/zhaopin_'+obj.id+'.html" class="link">'); 
        			   divs.push('<ul class="job-list2-each">');
        			   divs.push('<li class="pic"><img src="'+obj.imgpath+'" alt="'+obj.imgAlt+'" /></li>');
        			   divs.push('<li class="title">'+obj.title+'</li>');
        			   divs.push('<li class="salary">￥'+obj.totalsalary+'</li>');
        			   divs.push('</ul></a>');
        			});
        			 divs.push('<div class="remove-border2"></div><a href="javascript:;" class="clear-record">清空最近浏览记录</a>');
        		}
        	    $("#top_recent_browse_list").append(divs.join(''));
        	},
        	getCookieRecent:function(){
        		var ids = [];
        	    var rbs = Cookie.getCookie("recent_browse_job");
        	    var defaultImage = $("#defaultImage").attr("src");
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
        	        YL.top.getRecent(tmpArray);
        	    }
        	},
        	init:function(){
        		YL.top.getCookieRecent();
        		$('.clear-record').click(function(){
        			Cookie.setCookie('recent_browse_job', escape("[]"), 5);
        			$("#top_recent_browse_list").empty();
        			var divs = [];
        			divs.push('<p class="no-result"><span><i></i></span>暂无浏览记录</p>');
        			$("#top_recent_browse_list").append(divs.join(''));
        		});
        	}
        },
    	header:{
    	init:function(){
    		var bId = Cookie.getCookie("branchId");
    		var bName = Cookie.getCookie("branchName");

    		/*切换城市弹出层*/
    		$('.change-city-trigger').click(function() {
    			$.layer({
    				type : 1,
    				shade : [ 0 ],
    				area : [ 'auto', '330px' ],
    				title : '选择分站',
    				border : [ 6, 0.3, '#000' ],
    				shade : [ 0.5, '#000' ],
    				shadeClose : true,
    				page : {
    					dom : '.change-city'
    				}
    			});
    		})
    	}
    },
    index:{
    	findJobByCompanyid:function(companyId){
    		$.post(YL.ctx+"/index/jobListAjax", {
				companyId : companyId
			}, function(result) {
				var dov = [];
				$.each(result['content'], function(i, n) {
					dov.push('<a href="'+YL.ctx+'/zhaopin_'+n.id+'.html" target="_blank">' + n.jobname + '</a>');
				});
				if (result.totalPages>1) {
					dov.push('<a href="'+YL.ctx+'/company/companyDetailJobList?id=' + companyId + '" class="more" target="_blank">更多职位</a>');
				}
				$("#jobList").empty();
				$("#jobList").append(dov.join(''));
			});
    	},
    	init:function(){
    		var bId = Cookie.getCookie("branchId");
			var bName = Cookie.getCookie("branchName");
			//合作单位
			$(".partners").slide({
				mainCell : ".piclist ul",
				effect : "left",
				vis : 6,
				pnLoop : true,
				scroll : 6,
				autoPage : true
			});
			//小蓝有约图集
			$(".pic-box").slide({
				mainCell : ".pic-box-content ul",
				effect : "left",
				autoPlay : true,
				delayTime : 400
			});
			//全屏轮播图
			$(".full-slide").slide({
				titCell : ".hd li",
				mainCell : ".bd ul",
				effect : "fold",
				autoPlay : true,
				delayTime : 700
			});
			
			$('.con-list li').click(function() {
				if ($(this).text() == "岗位") {
					$("#input_search_LIKE_jobname").attr({
						"name" : "search_LIKE_searchName",
						"placeholder" : "请输入职位信息"
					});
				} else {
					$("#input_search_LIKE_jobname").attr({
						"name" : "search_LIKE_searchName",
						"placeholder" : "请输入企业名称"
					});
				}
			});
			$('.search-btn').click(function(){
				var key = 0; 
				if($('.search-input').val() != null && $('.search-input').val()!=""){
					key = $('.search-input').val().replace('_','&');;
				}
				if ($.trim($('.con-text').text()) == "岗位") {
					window.location.href=YL.ctx+'/zp_auto_'+key+'_1.html';
				} else {
					window.location.href=YL.ctx+'/qy_auto_'+key+'_1.html';
				}
			});
			$(".txtMarquee-top").slide({
				mainCell : ".bd",
				autoPlay : true,
				effect : "topMarquee",
				vis : 5,
				interTime : 50
			});

			/*福利标签 为第偶数个a添加clas*/
			$('.welfare-tags li:odd').addClass('odd');
			/*优蓝精选企业*/
	        $('.job-rec-each').hover(function(){
	            $(this).find('.rec-signup').stop(false,true).animate({
	                bottom:0
	            },300);
	        }).mouseleave(function() {
	            $(this).find('.rec-signup').stop(false,true).animate({
	                bottom:-40
	            },300);
	        });
			
            $('.tab-title li').click(function(){
                $(this).addClass('current').siblings().removeClass('current');
                var current_index=$('.tab-title li').index($(this));
                $('.tab-each').eq(current_index).show().siblings('.tab-each').hide();
            });
			
            /*名企专区*/
            $('.fam-ent li').hover(function() {
				var _this = $(this);
				$(this).find('.fam-ent-info').stop(true, false).animate({
					'top' : 0
				}, 300);
				/*显示企业名称*/
				var companyid = _this.find('#input_companyid').val();
				/*根据企业id得到该企业下的所有岗位*/
				YL.index.findJobByCompanyid(companyid); 
				$('.fam-ent-job').stop(true, false).slideDown().offset({
					top : _this.offset().top + 102,
					left : _this.offset().left + 1
				});
				$('.fam-ent-job').height($('.fam-ent-job .list').height()+11);
			}).mouseleave(function() {
				$(this).find('.fam-ent-info').stop(true, false).animate({
					'top' : -100
				}, 300);
				$('.fam-ent-job').stop(true, false).slideUp().hover(function() {
					$(this).stop(true, false).slideDown();
				});
				$('.fam-ent-job').mouseleave(function() {
					$(this).stop(true, false).slideUp();
				});
			});
			$('.con-text').click(function() {
				$('.con-list').toggle();
				if ($('.con-text span').text() == "岗位") {
					$('.con-list li').text("公司");
				} else {
					$('.con-list li').text("岗位");
				}
			});
			$('.con-list li').click(function() {
				$('.con-list').hide();
				$('.con-text span').text($(this).text());
				$(this).attr("code");
			});
    	}
    },
	job:{
		detail:{
			init:function(_jobBaseId){
				var m_reg = /^1[3-9][0-9]{9}$/i;
    			var name_reg = /((^[\u4E00-\u9FA5]{2,12}$)|(^[a-zA-Z]{3,24}$))/;
    			var passwd_reg = /^.{6,16}$/i;
    			var e_reg = /.+/i;
    			var idcard_reg=/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X|x)$/i;
				/**required autocomplete*/
    			/*验证手机号*/
    			$('.txt_user_loginname').blur(function(){
    				var mobile = $(this).val();
    				if(!m_reg.test(mobile)){
    		   			$('.txt_user_loginname').focus();
    		   			$(".txt_user_loginname").addClass("error");
    		   			$('.loginname-error').html('请输入正确的手机号');
    		   		}else{
    		   			$('.loginname-error').html('');
    		   		}
    			})
    			/*输入验证码*/
				$('.code').blur(function(){
					var code = $('.code').val();
					if (code.length != 4) {
						$(".code").focus();
						$(".code").addClass("error");
						$('.code-error').html("请输入您收到的4位验证码！");
					}else{
						$('.code-error').html("");
					}
				});
				/*输入姓名*/
				$('.input_name').blur(function(){
					var name = $('.input_name').val();
					if (!$('.div_name').hasClass('hide')&&name!=undefined&&!e_reg.test(name)) {
						$('.input_name').focus();
						$(".input_name").addClass("error");
			   			$('.name-error').html('请输入您的姓名');
    				} else if (!$('.div_name').hasClass('hide')&&name!=undefined&&!name_reg.test(name)) {
    					$('.input_name').focus();
						$(".input_name").addClass("error");
    					$('.name-error').html('姓名为2-12位的汉字或3-24位的英文');
    				}else{
    					$(".input_name").removeClass("error");
			   			$('.name-error').html('');
    				}
				})
				//输入验证发验证码
				$('.btn_reg_vali_code').click(function(){
			   		var mobile = $(".txt_user_loginname").val();
			   		var value = $("#input_reg_vali_code").val();
		   			$.post(YL.ctx+"/verification/valiCode",{"mobile":mobile,"type":"reg","value":value}, function(result){
						if(result == "success"){
							YL.job.detail.sendCode();
							$(".error_msg_valicodeu").html("");
						}else{
							$(".error_msg_valicodeu").html("验证码计算不正确");
						}
					});
				})
				//更换验证码
				$('.pic-code-text').click(function(){
					$(this).prev().click();
				})
				/*注册、登录*/
				$('.btn-login-reg').click(function(){
					var mobile = $('.txt_user_loginname').val();
					var code = $('.code').val();
					var name = $('.input_name').val();
			   		if(!m_reg.test(mobile)){
			   			$('.txt_user_loginname').focus();
			   			$(".txt_user_loginname").addClass("error");
			   			$('.loginname-error').html('请输入正确的手机号');
			   		}else if (code.length != 4) {
						$(".code").focus();
						$(".code").addClass("error");
						$('.code-error').html("请输入您收到的4位验证码！");
					}else if (!$('.div_name').hasClass('hide')&&name!=undefined&&!e_reg.test(name)) {
						$('.input_name').focus();
						$(".input_name").addClass("error");
			   			$('.name-error').html('请输入您的姓名');
    				} else if (!$('.div_name').hasClass('hide')&&name!=undefined&&!name_reg.test(name)) {
    					$('.input_name').focus();
						$(".input_name").addClass("error");
			   			$('.name-error').html('姓名为2-12位的汉字或3-24位的英文');
    				} else{
    					$.post(YL.ctx+'/register/fastLoginOrReg',{mobile:mobile,thekey:code,name:name},function(result){
    						if(result == "error"){
    							$(".code").focus();
    							$(".code").addClass("error");
    							$('.code-error').html("验证码错误！");
    						}else if(result == "success"){
    							var type = $("#hid_type").val();
    							var scheduleCode = $("#hid_scheduleCode").val();
    							location.href=YL.ctx+"/zhaopin_"+_jobBaseId+".html?type="+type+"&scheduleCode="+scheduleCode;
    						}
    					});
    				}
				});
				/*底部tab切换*/
    			$('.tab-title li').click(function(){
    				$(this).addClass('current').siblings().removeClass('current');
    				var current_index=$('.tab-title li').index($(this));
    				$('.tab-each').eq(current_index).show().siblings('.tab-each').hide();
    			});
				/*省市选择*/
//				$('.ul_province li').click(function(){
//					 var provinceId = $(this).attr("code");
//					$.get(YL.ctx+"/my/cityList",{"provinceId":provinceId},function(result){
//						var lis = [];
//						lis.push('<ul>');
//						$.each(result,function(i,j){
//							lis.push('<li code="'+j.id+'">'+j.abbreviation+'</li>');
//						});
//						lis.push('</ul>');
//						$(".ul_city_list").empty();
//						$(".ul_city_list").append(lis.join(''));
//						if($('.ul_province').parent().find('.select-g-title').hasClass("native")){
//							$('.selected_city').html('<span>您的籍贯（市）</span>');
//						}else{
//							$('.selected_city').html('<span>您的意向城市(市)</span>');
//						}
//						//点击选择某个城市后执行
//					 	$('.ul_city_list li').click(function(){
//							$('.ul_city_list').hide();
//							$('.selected_city').html('<span>'+$(this).text()+'</span>');
//							if($('.ul_city_list').parent().find('.select-g-title').hasClass("native")){
//								$("input[name='nativeProvinceid']").val(provinceId);
//								$("input[name='nativeCityid']").val($(this).attr("code"));
//							}else{
//								$("input[name='jobTargetProvinceid']").val(provinceId);
//								$("input[name='jobTargetCityid']").val($(this).attr("code"));
//							}
//						});  
//					});
//				});
				/*性别*/
				$('.ul_gender li').click(function(){
					$("input[name='gender']").val($(this).attr("code"));
				});
				/*学历*/
				$('.ul_education li').click(function(){
					$("input[name='education']").val($(this).attr("code"));
				});
//				/*民族*/
//				$('.nation-options li').click(function(){
//					$("input[name='nation']").val($(this).attr("code"));
//				});
//				/*意向职位*/
//				$('.ul_job_target li').click(function(){
//					$("input[name='jobTarget']").val($(this).text());
//				});
				$("body").attr({"data-spy":"scroll","data-target":"#scroll-menu","data-offset":"-450"});
				/*焦点图功能*/
    	        $(".pic-slide").slide({ mainCell:".bd ul", effect:"fold", delayTime:300, interTime:3000, autoPlay:true });
    			/*地图弹出层*/
    			$('.map_layer_trigger').on('click', function(){
    				$.layer({
    				type: 1,
    				shade: [0.35, '#000'],
    				shadeClose: true,
    				area: ['auto', 'auto'],
    				title: false,
    				page: {dom : '.bigmap'}
    				});
    			});
    			/*网友问答*/
/*    			$.get(YL.ctx+"/job/advisories/"+_jobBaseId,function(data){
    				$("#askCount").text(data.totalElements);
    				YL.job.detail.pushAdvisory(data,2,_jobBaseId);
    	    	});*/
    			/*显示回复输入框*/
    			$('#advisory').on('click','.answer',function(){
    				$(this).parents(".ask-each").siblings(".submit-div").slideDown();
    			});
    			/*提交回复*/
    			$('#advisory').on('click','.submit-answer',function(){
    				var content = $(this).parent().find('.div-reply').val();
    				var dom = $(this);
    				$.post(YL.ctx+"/login/veryfy",function(result){/*判断是否登录*/
    	    			if(result=="unlogin"){
    	    				$.layer({
    	    					type: 2,
    	    					title: '用户登录',
    	    					shadeClose: true,
    	    					area : [ '300px', '350px' ],
    	    					offset: [($(window).height() - 300)/2+'px', ''],
    	    					iframe: {src: '/login/iframe'}
    	    				});
    	    			}else{
    	    				if($.trim(content).length==0 || content == ""){
    	    					layer.alert("请输入您的要回复的内容");
    	    				}else if($.trim(content).length<5){
    	    					layer.alert("输入的信息不能少于5个字");
    	    				}else{
    	    					$.post(YL.ctx+"/job/postReply",{"userCommunityTalkId":dom.attr("code"),"content": content},function(result){
    	    						if(result=="success"){
    	    							dom.parent().find('.div-reply').val("");
    	    							layer.alert("提交成功！",9);
    	    							window.location.reload();
    	    						}else{
    	    							layer.alert("提交失败!")
    	    						}
    	    					});
    	    					dom.parent().slideUp();
    	    				}
    	    			}
    	    		});
    			});
    			/*查看更多*/
/*    			$(".load-more").click(function(){
    				$.get(YL.ctx+"/job/advisories/"+_jobBaseId,{"page":Number($("#hide_pageNumber").val())+1},function(data){
    					YL.job.detail.pushAdvisory(data,2,_jobBaseId);
    				});
    			});*/
    			/*提交网友问答*/
    	    	$("#btn_sub_advisory").click(function(){
    	    		$.post(YL.ctx+"/login/veryfy",function(result){
    	    			if(result=="unlogin"){
    	    				$.layer({
    	    					type: 2,
    	    					title: '用户登录',
    	    					shadeClose: true,
    	    					area : [ '300px', '350px' ],
    	    					offset: [($(window).height() - 300)/2+'px', ''],
    	    					iframe: {src: '/login/iframe'}
    	    				});
    	    			}else{
    	    				var content = $("#advisory_content").val();
    	    				if($.trim(content).length==0 || content == "我也说两句..."){
    	    					layer.alert("请输入您的问题或建议");
    	    				}else if($.trim(content).length<5){
    	    					layer.alert("输入的信息不能少于5个字");
    	    				}else{
    	    					$.post(YL.ctx+"/job/postAdvisory",$("#advisory_form").serialize(),function(result){
    	    						if(result=="success"){
    	    							$("#advisory_content").val("");
    	    							layer.alert("提交成功！",9);
    	    							window.location.reload();
    	    						}else{
    	    							layer.alert("提交失败")
    	    						}
    	    					});
    	    				}
	    						$("#advisory_content").focus();
    	    			}
    	    		});
    	    	});
    			/*选择排期*/
    			$(".schedule-list li").click(function(){
    				$("#hid_scheduleCode").val($(this).attr("data-id"));
    				YL.job.detail.signUp($("#hid_type").val(),_jobBaseId);
//    				YL.job.detail.valResume($("#hid_type").val(),_jobBaseId);
    			});
    	    	/*定位到咨询*/
    			$('.to-ask-info').bind('click',function(){
    				$('body,html').animate({scrollTop:$('#ask-info').offset().top-165},500);
    			});
    			/*登录成功后返回该页面弹框*/
    			if($("#hid_type").val()!=null&&$("#hid_type").val()!=""){
    				YL.job.detail.signUp($("#hid_type").val(),_jobBaseId);
//    				YL.job.detail.valResume($("#hid_type").val(),_jobBaseId);
    			}
    			/*报名*/
    			$('.signup-layer-trigger').on('click', function(){
    				var type=4;
    				if($(this).text().indexOf('申请面试')>0){
    					type=2;
    				}
    				$("#hid_type").val(type);
    				$.post(YL.ctx+"/login/veryfy",function(result){/*判断是否登录*/
    					if(result=="unlogin"){/*未登录*/
    						YL.job.detail.resetDiv();
    						$(".div_login").show();
    						YL.job.detail.resize_layer();
    						YL.job.detail.applyForJob("370px","auto");
    					}else{
    						if($("#hid_hasSchedule").val()=='true'){//判断有无排期
    	    					YL.job.detail.resetDiv();
    	    					$(".div_schedule").show();
    	    					YL.job.detail.resize_layer();
    	    					YL.job.detail.applyForJob("370px","auto");
    	    				}else{
    	    					YL.job.detail.signUp($("#hid_type").val(),_jobBaseId);
//    	    					YL.job.detail.valResume(type,_jobBaseId);
    	    				}
    					}
    				});
    			});
    			/*保存到简历*/
    			$('.btn-large').click(function() {
    				var name = $("input[name='name']").val();
    				var age = $("#input_age").val();
    				$(".tip").empty();
    				if (name!=undefined&&!e_reg.test(name)) {
    					$('.name_err_tip').html('请输入您的姓名');
    					$("input[name='name']").focus();
    				} else if (name!=undefined&&!name_reg.test(name)) {
    					$('.name_err_tip').html('姓名为2-12位的汉字或3-24位的英文');
    					$("input[name='name']").focus();
    				} else if(age!=null&&age!=''&&(age<0||age>200)){
    					$('.age_err_tip').html('请输入正确的年龄');
    					$("#input_age").focus();
    					return false;
    				} else {
    					$.post(YL.ctx+"/signup/updateResume", $(".resume_form").serialize(), function(result) {
    						if(result=="success"){
    							YL.job.detail.resetDiv();
								$('.success').show();
								YL.job.detail.resize_layer();
								YL.job.detail.applyForJob("370px","auto");
//    							YL.job.detail.valResume(type,_jobBaseId);
    						}
    					});
    				}
    			});
    			/*插入浏览记录*/
    	    	var ids = [];
    	    	var rbs = Cookie.getCookie("recent_browse_job");
    	    	if(rbs!=null&&rbs!=undefined&&rbs!=""){
    	    		var jobid=$("#jobid").val();
    	    		var jobtitle=$("#jobtitle").val();
    	    		var imgpath=$("#jobimgpath").val();
    	    		var imgcomment=$("#imgcomment").val();
    	    		var totalsalary = $("#salaryfrom").val();
    	    		var salaryTo = $("#salaryto").val();
    	    		if(salaryTo==null || salaryTo ==""){
    	    			totalsalary +="起";
    	    		}else{
    	    			totalsalary +="-"+salaryTo+"元/月";
    	    		}
    	    		var str = '{"id":"'+jobid+'","title":"'+jobtitle+'","imgpath":"'+imgpath+'","totalsalary":"'+totalsalary+'","imgAlt":"'+imgcomment+'"}';
    	    		var jsonObj = eval("("+unescape(rbs)+")");
    	    		var tmpArray = [];
    	    			$.each(jsonObj,function(i,j){
    	    				if($.inArray(j.id,ids)==-1){
    	        				var tmp= '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"'+j.imgpath+'","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.imgAlt+'"}';
    	        				tmpArray.push(tmp);
    	        				ids.push(j.id);
    	    				}
    	        		});
    	    			YL.job.detail.saveRecent(tmpArray);
    	    			tmpArray.push(str);
    	        		Cookie.setCookie('recent_browse_job', escape("["+tmpArray.join(",")+"]"), 5);
    	        	
    	    	}else{
    	    		Cookie.setCookie('recent_browse_job', escape("[]"), 5);
    	    	}
			},
			/*插入问答*/
/*			pushAdvisory	:function(data,source,dataId){source 1,企业 2,岗位
				var html = [];
				$.each(data.content,function(i,j){
					html.push('<ul>');
					html.push('  <li class="consult-r">');
					html.push('    <ul>');
					html.push('      <li><span class="blue-label">问</span><a href=" '+YL.ctx+'/wenda/'+j.askId +'.html">'+j.title+'</a><span class="mess">'+j.createTimeString+'&nbsp;'+(j.askUserName == null ?"优蓝网友":j.askUserName)+'</span></li>');
					if(j.userCommunityTalkContentVo != null && j.userCommunityTalkContentVo.length>0){
					  $.each(j.userCommunityTalkContentVo,function(m,n){
					    if(m==0){
					      html.push('      <li class="c999"><span class="gray-label">答</span>'+n.content+'<a href="'+YL.ctx+'/wenda/'+j.askId+'.html" class="answer">我来回答&gt;</a></li>'); 
					    }
					  })
					  if(j.userCommunityTalkContentVo.length>1){
					    html.push('      <li class="last"><a href="'+YL.ctx+'/wenda/'+j.askId+'.html">查看其他问答&gt;</a> </li>');
					  }
					}else{
					   html.push('      <li class="c999"><span class="gray-label">答</span>还没人回答此问题，我来第一个回答。<a href="'+YL.ctx+'/wenda/'+j.askId+'.html" class="answer">我来回答&gt;</a></li>');
					}
					html.push('    </ul>');
					html.push('  </li>');
					html.push('  <div class="clear"></div>');
					html.push('</ul>');
	    		});
				if(data.content.length>8){
					if(source==1){
						html.push('<div class="consult-more"><a href="'+YL.ctx+'/wenda/so_0_'+dataId+'_0_1/" class="f14 blue">查看全部问答&gt;&gt;</a></div>');
					}
					if(source==2){
						html.push('<div class="consult-more"><a href="'+YL.ctx+'/wenda/so_0_0_'+dataId+'_1/" class="f14 blue">查看全部问答&gt;&gt;</a></div>');
					}
				}
				$("#advisory").append(html.join(''));
			},*/
			
			//验证码
			valiCode :function(){
				var m_reg = /^1[3-9][0-9]{9}$/i;
				var mobile = $(".txt_user_loginname").val();
				if(m_reg.test(mobile)){
					$('.pic-code-text').click();
					$(".div-thekey").hide(); //验证码
					$(".lab-login-reg").addClass("hide");//登录按钮
					$('.div-loginname').hide();
					$('.div-pic-code').show();
					$('.div_name').addClass('hide');
					$('#input_reg_vali_code').val('');
					YL.job.detail.resize_layer();
					YL.job.detail.applyForJob("370px","auto");
		   		}else{
		   			$(".txt_user_loginname").parent().addClass("error");
					$(".txt_user_loginname").parent().next().html("您输入的手机号码格式不正确！");
		   		}
			},
			
			/*发送验证码*/
			sendCode :function(){
				var m_reg=/^1[3-9][0-9]{9}$/i;
				var mobile = $(".txt_user_loginname").val();
				$('.div-loginname').show();//手机号
				$(".div-pic-code").hide();//验证
				$(".div-thekey").show(); //验证码
				$(".lab-login-reg").removeClass("hide");//登录按钮
		   		if(m_reg.test(mobile)){
		   			$.post(YL.ctx+"/signup/isactivate",{mobile:mobile},function(data){
		   				if(data=="failure"){
		   					$(".div_login").show();
		   					$(".div_name").removeClass('hide');
		   					//YL.job.detail.userGainkey('',mobile);
		   					YL.job.detail.resize_layer();
		   					YL.job.detail.applyForJob("370px","auto");
		   				}else {
		   					$(".div_login").show();
		   					$(".div_name").addClass('hide');
		   					YL.job.detail.resize_layer();
		   					YL.job.detail.applyForJob("370px","auto");
		   				}
		   				YL.job.detail.userGainkey('',mobile);
		   			});
		   		}else{
		   			$(".txt_user_loginname").parent().addClass("error");
					$(".txt_user_loginname").parent().next().html("您输入的手机号码格式不正确！");
		   		}
			},
			/*语音验证*/
//			sendVoice :function(){
//				var m_reg=/^1[3-9][0-9]{9}$/i;
//		   		var mobile = $(".txt_user_loginname").val();
//		   		if(m_reg.test(mobile)){
//		   			YL.job.detail.userGainkey('voice',mobile);
//		   		}else{
//		   			$("#txt_reg_user_loginname").parent().addClass("error");
//					$("#txt_reg_user_loginname").parent().next().html("您输入的手机号码格式不正确！");
//		   		}
//			},
			userGainkey :function(type,loginname){
				$('.txt_user_loginname').val(loginname);
		   		var m_reg=/^1[3-9][0-9]{9}$/i;
		   		var mobile = $(".txt_user_loginname").val();
		   		if(m_reg.test(mobile)){
		   			$(".div-voice").show();
//		   			$(".send-voice").attr("onclick","");
		   			$(".send-btn").attr("onclick","");
		   			$(".lab-login-reg").addClass("disabled");
//		   			$(".btn-login-reg").attr("disabled","disabled");
		   			$(".send-btn").addClass("send-btn-disabled");
		   			$.post(YL.ctx+"/register/sendCode",{mobile:mobile,type:type,currentValue:$("#input_reg_vali_code").val()},function(data){
		   				if(data=="success"){
		   					$(".lab-login-reg").removeClass("disabled");
		   					$(".txt_user_loginname").parent().removeClass("error");
		   					$(".txt_user_loginname").parent().next().html("");
		   					var thenum = 60;
		   					var timmer = setInterval(function(){
		   						$(".send-btn").html(thenum+'s后可以重新获取');
		   						thenum--;
		   						if(thenum==-1){
		   							clearInterval(timmer);
		   							//$(".send-voice").attr("onclick","YL.job.detail.sendVoice()");
		   				   			$(".send-btn").attr("onclick","YL.job.detail.valiCode()");
		   							$(".send-btn").removeClass("send-btn-disabled");
		   							$(".send-btn").html("获取手机验证码");
//		   							$(".btn-login-reg").removeAttr("disabled");
		   						}
		   					},1000);
		   				}else if(data=="frozen"){
		   					YL.job.detail.resetDiv();
							$(".div_login").show();
							YL.job.detail.resize_layer();
							YL.job.detail.applyForJob("370px","auto");
							$(".txt_user_loginname").parent().addClass("error");
		   					$(".txt_user_loginname").parent().next().html("该手机号今天收到的短信已达到上限！");
		   					//$(".send-voice").attr("onclick","YL.job.detail.sendVoice()");
   				   			$(".send-btn").attr("onclick","YL.job.detail.valiCode()");
		   					$(".send-btn").removeClass("send-btn-disabled");
		   				}else{
		   					YL.job.detail.resetDiv();
							$(".div_login").show();
							YL.job.detail.resize_layer();
							YL.job.detail.applyForJob("370px","auto");
		   					$(".txt_user_loginname").parent().addClass("error");
		   					$(".txt_user_loginname").parent().next().html("验证码发送失败请您输入的检查手机号！");
		   					//$(".send-voice").attr("onclick","YL.job.detail.sendVoice()");
   				   			$(".send-btn").attr("onclick","YL.job.detail.valiCode()");
		   					$(".send-btn").removeClass("send-btn-disabled");
		   				}
		   			});
		   		}
		   		else{
		   			$(".txt_user_loginname").parent().addClass("error");
					$(".txt_user_loginname").parent().next().html("您输入的手机号码格式不正确！");
		   		}
		   	},
			
			applyForJob		:function(width,height){
				$.layer({
    				type: 1,
    				title: false,
    				closeBtn:false,
    				shadeClose: true,
    				area : [width , height],
    				 page : {dom : '.signup-layer'}
    				});
    				//点击关闭按钮 关闭弹出层
    				$('.signup-layer .close').click(function(){
    					layer.closeAll();
    				})
			},
//			valResume		:function(type,jobid){
//				$.post(YL.ctx+"/signup/valresume",{type:type},function(result){/*报名和预约时判断必填字段是否完整*/
//					if(result=="success"){/*已完善，直接报名或预约*/
//						YL.job.detail.signUp(type,jobid);
//					}else if(result=="failure"){/*未完善，弹出未完善字段*/
//						YL.job.detail.resetDiv();
//						$(".div_signup").show();
//						YL.job.detail.resize_layer();
//						YL.job.detail.applyForJob("370px","auto");
//					}else if(result=="notInit"){/*未登录*/
//						YL.job.detail.resetDiv();
//						$(".div_login").show();
//						YL.job.detail.resize_layer();
//						YL.job.detail.applyForJob("370px","auto");
//					}
//				});
//			},
			signUp		:function(type,jobid){
				$.post(YL.ctx+"/signup/countJobApply",function(result){//查询已报名次数
					if(result==10){//报名次数已满
						YL.job.detail.resetDiv();
						$('.upperLimit').show();
						YL.job.detail.resize_layer();
						YL.job.detail.applyForJob("370px","auto");
					}else{
						var upperLimit = 9-result;
						var scheduleCode = $("#hid_scheduleCode").val();
						$.post(YL.ctx+"/signup/job",{jobid:jobid,type:type,scheduleCode:scheduleCode},function(result){/*报名和预约*/
							if(result=="success"){//报名成功
    							//完善简历
								$('.span_upperLimit').html(upperLimit);
    							YL.job.detail.resetDiv();
    							$(".div_signup").show();
    							YL.job.detail.resize_layer();
    							YL.job.detail.applyForJob("370px","auto");
							}else if(result=="failure"){//有未处理的报名信息
								layer.closeAll();
								layer.alert('您已经投递过该岗位，我们会尽快处理，请您耐心等待！',9);
							}else if(result=="notInit"){/*未登录*/
								YL.job.detail.resetDiv();
								$(".div_login").show();
								YL.job.detail.resize_layer();
								YL.job.detail.applyForJob("370px","auto");
							}
						});
					}
			});
		},
			resize_layer	:function(){
				$('.xubox_main').height($('.signup-layer').height()+20);
				$('.xubox_border').height($('.xubox_main').height()+12);
			},
			openLoginBox	:function(){
				$.layer({
    	    		type : 2,
    				title : '我要登录',
    				shadeClose : true,
    				area : [ '300px', '350px' ],
    				offset : [ ($(window).height() - 350) / 2 + 'px', '' ],
    				iframe : {
    					src : YL.ctx+'/login/iframe'
    				}
    			});
			},
			resetDiv		:function(){
				$(".txt_user_loginname").parent().removeClass("error");
				$(".txt_user_loginname").parent().next().html("");
				$(".div_login").hide();
				$(".div_signup").hide();
				$(".success").hide();
				$(".div_schedule").hide();
				$(".upperLimit").hide();
			},
			saveRecent		:function(data){
				$("#recent_browse_list").empty();
	        	var divs = [];
	        	divs.push('<h2 class="box-title">最近浏览职位</h2><div class="job-list2">');
	        	if(data==null||data=="")
	        	{
	        		divs.push('<p class="no-result"><span><i></i></span>暂无浏览记录</p>');
	        	}
	        	else
	        	{
	        		$.each(data,function(i){
	        		var j=data[data.length-i-1];
	        		if(i<4){
	        				var obj = eval("("+j+")");
    	        			divs.push('<a href="'+YL.ctx+'/zhaopin_'+obj.id+'.html">');
    	        			divs.push('<ul class="job-list2-each">');
        	        		divs.push('<li class="pic"><img src="'+obj.imgpath+'" alt="'+obj.imgAlt+'" /></li>');
    	        			divs.push('<li class="title">'+obj.title+'</li>');
    	        			divs.push('<li class="salary">￥'+obj.totalsalary+'</li>');
    	        			divs.push('</ul></a>');
	        			}
	        		});
	        	}
	        	divs.push('<div class="remove-border2"></div><div class="clear"></div></div>');
	        	$("#recent_browse_list").append(divs.join(''));
	            $("#recent_browse_list").parent(".box-r").height($("#recent_browse_list").height());
			}
		},
		list:{
			page:1,
			paginationSize:15,
			init:function(){
				/*周边城市*/
				$('.select_city').change(function(){
					YL.job.list.searchJob();
				});
				/*薪资选择*/
				$("#allSalary").click(function(){
					$(this).addClass('current');
					YL.job.list.initSalaryRang(0,10500,false);
				});
				/*加载最近浏览记录*/
			    YL.job.list.initCookieRecent();
			    YL.job.list.initSalaryRang(0,10500,true);
			    if($("#job_types .current").length>0){
			    	var id = $("#job_types .current a").attr("data-id");
			    	var typeName = $("#job_types .current a").text();
			    	YL.job.list.clickJobType(id,typeName);
			    }else{
			    	$(".sub_job_types").addClass("hide");
			    }
			    $("#job_types a,#allJobTypeHref").click(function(){
			    	if($(this).text()=="不限"){
			    		$("#hsubJobType").attr("data","");
			    		$("#hJobType").attr("data","");
			    		YL.job.list.searchJob();
			    		return;
			    	}
			    	var id = $(this).attr("data-id");
			    	var typeName = $(this).text();
			    	$(this).parent().addClass("current").siblings().removeClass("current");
			    	$("#allJobType").removeClass("current");
			    	YL.job.list.clickJobType(id,typeName);
			    	$("#hsubJobType").attr("data","");
			    	YL.job.list.searchJob();
			    });
			    $(".sub_job_types").delegate("a","click",function(){
			    	$(this).addClass("sub-current").siblings().removeClass("sub-current");
			    	$("#hsubJobType").attr("data",$(this).text());
			    	if($(this).text()=="所有"){
			    		$("#hsubJobType").attr("data","");
			    	}
			    	YL.job.list.searchJob();
			    });
			    if($("#wdValue").val()!="0"){
					$("input[name='search_LIKE_searchName']").val($("#wdValue").val());	
				}
			    $('.welfare-tags li:odd').addClass('odd');
			    /*福利标签搜索*/
			    $("#allLabel").click(function(){//福利不限
			    	$(this).addClass("current");
			    	$("input[name='search_LIKE_searchName']").val('');
			    	YL.job.list.searchJob();
			    })
			    
				$(".cb_label").click(function(){
					var selectLabel = $("input[name='search_LIKE_searchName']").val();
					var thisLabel = $(this).parent().parent().attr("value")+"," ;
					if($(this).is(":checked")){
						selectLabel = selectLabel + thisLabel;
					}else{
						if(selectLabel.indexOf(',')==-1){
							selectLabel = selectLabel.replace($(this).parent().parent().attr("value"),"");
						}else{
							selectLabel = selectLabel.replace(thisLabel,"");
						}
					}
					$("input[name='search_LIKE_searchName']").val(selectLabel);
					YL.job.list.searchJob();
				});
			    $("#btn_seach").click(function(){
			    	 YL.job.list.searchJob();
			    });
			},
			clickJobType:function(id,typeName){
				var jobType;
				$.get(YL.ctx+"/job/findJobTypesByParent",{"parentId":id},function(result){
					if(result.length > 0){
						var subs = [];
						subs.push("<a href='"+YL.ctx+"/"+typeName+"/0_0_0_0_1/'>所有</a>");
						$.each(result,function(i,j){
							if(j.jobName==$("#hsubJobType").attr("data")){
								subs.push("<a class='current sub-current' href='"+YL.ctx+"/"+typeName+"/0_0_"+j.jobName+"_0_1/'>"+j.jobName+"</a>");
							}else{
								subs.push("<a href='"+YL.ctx+"/"+typeName+"/0_0_"+j.jobName+"_0_1/'>"+j.jobName+"</a>");
							}
						});
						$(".sub_job_types").empty();
						/*二级标签显示地点*/
						var typeindex;
						var jobTypeSubId;
						$("#job_types li").each(function(index){
				    		if($(this).hasClass("current")){
				    			typeindex = index;
				    		}
				        });
						var light_flag = Math.ceil(typeindex / 11);
						if(typeindex%11==0){
							light_flag = Math.ceil((typeindex+1) / 11);
						}
						$("div [id^='sub_job_types']").hide();
						if($("#sub_job_types"+light_flag).size() == 0){
							$("#sub_job_types0").show();
							light_flag=0;
						}else{
							$("#sub_job_types"+light_flag).show();
						}
						 $("#sub_job_types"+light_flag).append(subs.join(''));	
						 if($("#sub_job_types"+jobTypeSubId+" .sub-current").length==0){	
						    $("#sub_job_types"+jobTypeSubId+" a").eq(0).addClass("sub-current");
						 } 
					}
				}); 
				$("#hJobType").attr("data",typeName);
			},
			initCookieRecent:function(){
				var ids = [];
			    var rbs = Cookie.getCookie("recent_browse_job");
			    if(rbs!=null&&rbs!=undefined&&rbs!=""){
			        var jsonObj = eval("("+unescape(rbs)+")");
			        var tmpArray = [];
			            $.each(jsonObj,function(i){
			            	var j=jsonObj[jsonObj.length-i-1];
			                if($.inArray(j.id,ids)==-1 && tmpArray.length<4){
			                	var tmp;
			    				if(j.imgpath==null||j.imgpath==""){
			    					tmp = '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"'+YL.ctx+'/static/images/pic-default.jpg","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.title+'"}';
			    				}else{
			    					tmp = '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"'+j.imgpath+'","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.imgAlt+'"}';
			    				}
			                    tmpArray.push(tmp);
			                    ids.push(j.id);
			                }
			            });
			        YL.job.list.saveRecent(tmpArray);
			    }else{
			    	var divs = [];
			        Cookie.setCookie('recent_browse_job', escape("[]"), 5);
			    }
			},
			replaceAll:function(str, sptr, sptr1){
                while (str.indexOf(sptr) >= 0){
                   str = str.replace(sptr, sptr1);
                }
                return str;
			},
			/**查询岗位操作*/
			searchJob:function(){
				var jobType = $("#hJobType").attr("data");
				var searchName = $("#searchName").val();
				var subJobType = $("#hsubJobType").attr("data")==null?"":$("#hsubJobType").attr("data");
				var salary = $("#salaryCondition").attr("data");
				var otherCity = $("#scName").val();
				var condition = [];
				searchName = YL.job.list.replaceAll(searchName,'_','&');
				searchName = YL.job.list.replaceAll(searchName,'#','');
				searchName = YL.job.list.replaceAll(searchName,'%','');
				searchName = YL.job.list.replaceAll(searchName,'*','');
				if(jobType==null || jobType ==""|| jobType =="job"){
					jobType = "zhaopin";
				}
				if(otherCity!=null && otherCity!=""){
					condition.push(otherCity);
				}else{
					condition.push("0");
				}
				if(searchName!=null && searchName !=""){
			   		condition.push(searchName);
				}else{
					condition.push("0");
				}
				if(subJobType!=null && subJobType !=""){
			   		condition.push(subJobType);
				}else{
					condition.push("0");
				}
				if(salary!=null && salary!=""){
			   		condition.push(salary);
				}else{
					condition.push("0");
				}
				window.location.href=YL.ctx+"/"+jobType+"/" + condition.join("_")+"_1/";
			},
			makeSort:function(obj){
				$(obj).addClass('current').siblings().removeClass("current");
				YL.job.list.searchJob();
			},
			saveRecent		:function(data){
				$("#recent_browse_list").empty();
			    var divs = [];
			    divs.push('<h2 class="box-title">最近浏览职位</h2><div class="job-list2">');
				if(data==null||data=="")
				{
					divs.push('<p class="no-result"><span><i></i></span>暂无浏览记录</p>');
				}
				else
				{
					$.each(data,function(i,j){
					   var obj = eval("("+j+")");
					   divs.push('<a href="'+YL.ctx+'/zhaopin_'+obj.id+'.html">');
					   divs.push('<ul class="job-list2-each">');
					   divs.push('<li class="pic"><img src="'+obj.imgpath+'" alt="'+obj.imgAlt+'" /></li>');
					   divs.push('<li class="title">'+obj.title+'</li>');
					   divs.push('<li class="salary">￥'+obj.totalsalary+'</li>');
					   divs.push('</ul></a>');
					});
				}
			   
			    divs.push('<div class="remove-border2"></div><div class="clear"></div>');
			    $("#recent_browse_list").append(divs.join(''));
			    $("#recent_browse_list").parent(".box-r").height($("#recent_browse_list").height());
			},
			initSalaryRang:function(gza,gzb,flag){
				var reg = new RegExp("^[0-9]*$");
				var gzAll = $("#salaryCondition").attr("data");
				if(gzAll!=null && flag){
					gza = (gzAll.split("-")[0]==null || gzAll.split("-")[0]=="" || !reg.test(gzAll.split("-")[0]))?0:gzAll.split("-")[0];
					gzb = (gzAll.split("-")[1]==null || gzAll.split("-")[1]=="" || !reg.test(gzAll.split("-")[0]))?10500:gzAll.split("-")[1];
				}
			    var pageConfig = {linkurl:YL.ctx,searchurl:YL.ctx+"/job/"};
			    pageConfig.filter = {sex0:'/m/2000-0/',sex1:'/f/2000-0/',gz1:gza,gz2:gzb,gzurl:'/_gongzi_/',pgurl:'/2000-0/_page_/'};
			    var gongzi1 = pageConfig.filter.gz1;
			    var gongzi2 = pageConfig.filter.gz2;
			    /*工资范围初始化*/
			    if(gongzi2>10000){
			    	if(gongzi1==0){
			    		$( "#amount" ).text("不限薪资");
			    	}else{
			    		if(gongzi1>10000){
			    			$( "#amount" ).text("10000以上");
			    		}else{
				    		$( "#amount" ).text(gongzi1+"以上");
			    		}
			    	}
			    }else if(gongzi2==1500){
			    	$( "#amount" ).text('请选择薪资范围');
			    }else{
			    	 $( "#amount" ).text(gongzi1+'-'+gongzi2+" 元/月");
			    }
			    /*拖动居中显示*/
			  	var gongziTemple = gongzi2>10000?10500:gongzi2;
				var w,wrange,m;
				var w2= ((gongziTemple - gongzi1)/1000)*92;
				var w3= w2/2;
				wrange = (((gongzi1-2000)/1000)*92)+w3;
				w = -19;
				m=w+ wrange;
				/*动画效果*/
				$(".range-tip").stop(1,1).animate({marginLeft:m+125});	
				/*工资范围*/
			    $( "#slider-range" ).slider({
			        range: true,
			        min: 1500,
			        max: 10500,
			        step: 500,
			        values: [gongzi1,gongzi2],
			        slide: function( event, ui ) {
			        	var w,wrange,m;
						var w2= ((ui.values[1] - ui.values[0])/1000)*92;
						var w3= w2/2;
						wrange = (((ui.values[0]-2000)/1000)*92)+w3;
						w = -17
						m=w+ wrange;
			            $(".range-tip").stop(1,1).animate({marginLeft:m});
			            if(ui.values[ 1 ]==10500){
			                if(ui.values[0]==1500){
			                    $( "#amount" ).text('不限');
								$('.pay-scale').prev('.name').addClass('current');
			                } else if(ui.values[0]>10000){$( "#amount" ).text('10000以上');}
							else{
			                    $( "#amount" ).text(ui.values[ 0 ] + ' 以上');
			                }
			            } else if(ui.values[0]==1500){
								if(ui.values[1]==1500){$( "#amount" ).text('请选择薪资范围');}
								else if(ui.values[1]<=10000 && ui.values[1]>1500){
								$( "#amount" ).text(0 + "-" + ui.values[ 1 ] +' 元/月');
								} else $( "#amount" ).text('不限');
							}
							else if(ui.values[1]>10000&&ui.values[0]<10000){
								$( "#amount" ).text(ui.values[0] +' 以上');
							}
							else if(ui.values[0]>10000){
								$( "#amount" ).text('10000以上');
							}
						else{
			                $( "#amount" ).text( ui.values[ 0 ] + "-" + ui.values[ 1 ] +' 元/月');
			            }
			        },
			        change:function(event,ui){
			        	$("#allSalary").removeClass('current');
			            var gongzi_base_url = pageConfig.filter.gzurl;
			            var salaryCondition;
			             if(ui.values[1] >10000){
			             	ui.values[ 1 ]="10500";
			             }
			             if(ui.values[0] < 2000){
			              	ui.values[ 0 ]="0";
			              }
			            if(ui.values[0]==0 && ui.values[1] ==10000){
			                salaryCondition = gongzi_base_url.replace('/_gongzi_/',ui.values[ 0 ] + "-" + ui.values[ 1 ]);
			            }else{
			                salaryCondition  = gongzi_base_url.replace('/_gongzi_/',ui.values[ 0 ] + "-" + ui.values[ 1 ]);
			            }
				        $("#salaryCondition").attr("data",salaryCondition);
			            YL.job.list.searchJob();
			        }
			    });
			    $( ".range-tip" ).css("margin-left",416);
			}
		}
	},
	qiye:{
		enterprise:{
			initCookieRecent:function(){
				var ids = [];
			    var rbs = Cookie.getCookie("recent_browse_job");
			    if(rbs!=null&&rbs!=undefined&&rbs!=""){
			        var jsonObj = eval("("+unescape(rbs)+")");
			        var tmpArray = [];
			            $.each(jsonObj,function(i,j){
			                if($.inArray(j.id,ids)==-1 && i<4 ){
			                	var tmp;
			    				if(j.imgpath==null||j.imgpath=="")
			    				{
			    					tmp = '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"'+YL.ctx+'/static/images/pic-default.jpg","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.title+'"}';
			    				}
			    				else
			    				{
			    					tmp = '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"'+j.imgpath+'","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.imgAlt+'"}';
			    				}
			                    tmpArray.push(tmp);
			                    ids.push(j.id);
			                }
			            });
			        YL.qiye.enterprise.saveRecent(tmpArray);
			        Cookie.setCookie('recent_browse_job', escape("["+tmpArray.join(",")+"]"), 5);
			    }else{
			    	var divs = [];
			        Cookie.setCookie('recent_browse_job', escape("[]"), 5);
			    }
			},
			saveRecent:function(data){
				$("#recent_browse_list").empty();
			    var divs = [];
				divs.push('<h2 class="box-title">最近浏览职位</h2><div class="job-list2">');
				if(data==null||data=="")
				{
					divs.push('<p class="no-result"><span><i></i></span>暂无浏览记录</p>');
				}
				else
				{
					$.each(data,function(i,j){
					   var obj = eval("("+j+")");
					   divs.push('<a href="'+YL.ctx+'/zhaopin_'+obj.id+'.html">');
					   divs.push('<ul class="job-list2-each">');
					   divs.push('<li class="pic"><img src="'+obj.imgpath+'" alt="'+obj.imgAlt+'" /></li>');
					   divs.push('<li class="title">'+obj.title+'</li>');
					   divs.push('<li class="salary">￥'+obj.totalsalary+'</li>');
					   divs.push('</ul></a>');
					});
				}
			    divs.push('<div class="remove-border2"></div><div class="clear"></div>');
			    $("#recent_browse_list").append(divs.join(''));
			    $("#recent_browse_list").parent(".box-r").height($("#recent_browse_list").height());
			},

			weekly_ani:function (e){
				var me=$(e.target).closest("li");
				if(me.hasClass("current")) 
				return;
				var orili=me.parent().find(".current");
				$(this).data("setTime",setTimeout(function(){
					YL.qiye.enterprise.weekly_move(me,orili,57,20)
				},150));
			},
			
			weekly_move:function (me,orili,h,h2){
				me.addClass("current");
				$(".company-wanted li").unbind("mouseenter",YL.qiye.enterprise.weekly_ani);
				setTimeout(function(){
					var cur_h=me.height();
					if(cur_h < h-2){
						var cur_orih=orili.height();
						var dh=Math.round((h-cur_h)/2.5);
						me.css("height",cur_h+dh);
						orili.css("height",cur_orih-dh);
						setTimeout(arguments.callee,25);
					}else{
						me.addClass("current").css("height",h);
						orili.css("height",h2);
						$(".company-wanted li").bind("mouseenter",YL.qiye.enterprise.weekly_ani);
						orili.removeClass("current");
					}
				},25);
			},
			init:function(){
				$(".company-wanted li").bind("mouseenter",YL.qiye.enterprise.weekly_ani).bind("mouseleave",function(){
					clearTimeout(
						$(this).data("setTime")
					);
				});
				
				$(".company-wanted").find("li:first").addClass("current").animate({height:57}, 300);
				YL.qiye.enterprise.initCookieRecent();

				/*轮播*/
				$(".mr-frbox").slide({
					titCell:"",
					mainCell:".mr-frUl ul",
					autoPage:true,
					effect:"leftLoop",
					autoPlay:true,
					vis:4
				});
				/*筛选条件 展开更多*/
				$('.more-g-2').click(function(){
					$(this).toggleClass('fold-up').parent().toggleClass('height-auto');
					if($(this).text()=='更多'){$(this).text('收起')}else $(this).text('更多');
				});
				$('.tab-title li').click(function(){
					$(this).addClass('current').siblings().removeClass('current');
					var current_index=$('.tab-title li').index($(this));
					$('.tab-each').eq(current_index).show().siblings('.tab-each').hide();
				});
				/*控制福利标签展开/收起*/
				$('.welfare-tags .more').click(function(){
					$('.welfare-tags ul').toggleClass('height-auto');
					$(this).toggleClass('up');
				});
				/*福利标签选中，添加class*/
				$('.welfare-tags li').click(function(){
					$(this).toggleClass('checked');
				});
				/*区域、周边城市选择下拉菜单*/
				$('.order .select p').click(function(){
					/*给当前菜单 加current样式*/
					$(this).parents('.select').toggleClass('current').siblings('.select').removeClass('current');
				})
				$('.order-options li').click(function(){
					$(this).parents('.select').find('p label').text($(this).text()).end().removeClass('current');
				});
				
				
				/*点击区域、周边城市下拉菜单以外区域，隐藏下拉菜单(去掉current)*/
				$(document).bind("click",function(e){
				var target = $(e.target);
				if(target.closest(".order .select").length == 0){
						$('.order .select').removeClass('current');
					}
				});
				
				$('.welfare-tags li:odd').addClass('odd');
				
				$('#btn_search').click(function(){
					var key = 0; 
					if($('.tip-text').val() != null && $('.tip-text').val()!=""){
						key = $('.tip-text').val().replace('_','&');;
					}
					window.location.href=YL.ctx+'/qy_auto_'+key+'_1.html';
				});
			}
		},
		detail:{
			init:function(_companyId){
				$('.tab-title li').click(function(){
					$(this).addClass('current').siblings().removeClass('current');
					var current_index=$('.tab-title li').index($(this));
					$('.tab-each').eq(current_index).show().siblings('.tab-each').hide();
				});
					$(".marquee").slide({mainCell:".bd",autoPlay:true,effect:"topMarquee",vis:8,interTime:50});
					/*企业介绍 展开 收起*/
					$(".fold-and-open").click(function(){
						if($(this).text() == "展开"){
							$(this).text("收起");
							}
						else {$(this).text("展开");}
						$(".job-summary .sum,.sum .describe").toggleClass("height-auto");
						$(".job-summary .p-toggle").toggleClass("relative");
					});
					/*网友问答*/
/*					$.get(YL.ctx+"/company/advisories/"+_companyId,function(data){
						$("#askCount").text(data.totalElements);
						YL.job.detail.pushAdvisory(data,1,_companyId);
					});*/
	    			/*提交回复*/
	    			$('#advisory').on('click','.submit-answer',function(){
	    				var content = $(this).parent().find('.div-reply').val();
	    				var dom = $(this);
	    				$.post(YL.ctx+"/login/veryfy",function(result){/*判断是否登录*/
	    	    			if(result=="unlogin"){
	    	    				$.layer({
	    	    					type: 2,
	    	    					title: '用户登录',
	    	    					shadeClose: true,
	    	    					area : [ '300px', '350px' ],
	    	    					offset: [($(window).height() - 300)/2+'px', ''],
	    	    					iframe: {src: '/login/iframe'}
	    	    				});
	    	    			}else{
	    	    				if($.trim(content).length==0 || content == ""){
	    	    					layer.alert("请输入您的要回复的内容");
	    	    				}else if($.trim(content).length<5){
	    	    					layer.alert("输入的信息不能少于5个字");
	    	    				}else{
	    	    					$.post(YL.ctx+"/job/postReply",{"userCommunityTalkId":dom.attr("code"),"content": content},function(result){
	    	    						if(result=="success"){
	    	    							dom.parent().find('.div-reply').val("");
	    	    							layer.alert("提交成功！",9);
	    	    							window.location.reload();
	    	    						}else{
	    	    							layer.alert("提交失败!")
	    	    						}
	    	    					});
	    	    					dom.parent().slideUp();
	    	    				}
	    	    			}
	    	    		});
	    			});
	    			/*查看更多*/
/*	    			$(".load-more").click(function(){
	    				$.get(YL.ctx+"/company/advisories/"+_companyId,{"page":Number($("#hide_pageNumber").val())+1},function(data){
	    					YL.job.detail.pushAdvisory(data,1,_companyId);
	    				});
	    			});*/
					
					$("#btn_sub_advisory").click(function(){
			    		$.post(YL.ctx+"/login/veryfy",function(result){
			    			if(result=="unlogin"){
			    				$.layer({
			    					type: 2,
			    					title: '用户登录',
			    					shadeClose: true,
			    					area : ['400px' , '350px'],
			    					offset: [($(window).height() - 300)/2+'px', ''],
			    					iframe: {src: YL.ctx+'/login/iframe'}
			    				});
			    			}else{
			    				var content = $("#advisory_content").val();
			    				if($.trim(content).length==0 || content == "我也说两句..."){
			    					layer.alert("请输入您的问题或建议");
			    				}else if($.trim(content).length<5){
			    					layer.alert("输入的信息不能少于5个字");
			    				}else{
			    					$.post(YL.ctx+"/job/postAdvisory",$("#advisory_form").serialize(),function(result){
			    						if(result=="success"){
			    							$("#advisory_content").val("");
			    							layer.alert("提交成功！",9);
			    							window.location.reload();
			    						}else{
			    							layer.alert("提交失败")
			    						}
			    					});
			    				}
			    				$("#advisory_content").focus();
			    			}
			    		});
			    	});
					$(".btn-small").click(function(){
						YL.qiye.detail.addPointOfPraise($("#hid_company_id").val());
					});
			},
			addPointOfPraise:function(companyId){
				$.post(YL.ctx+"/company/addPointOfPraise",{id:companyId},function(result){
					if(result=="error"){
						layer.alert("系统繁忙，请稍后再试")
					}else if(result=="failure"){
						layer.alert("已赞过")
					}else{
						$("#point").text(result);
					}
				});
			}
		}
	}
};
/**初始化上下文路径*/
YL.initCtx();