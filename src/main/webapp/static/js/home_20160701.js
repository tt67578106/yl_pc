/**
 * 
 */
$(function(){
		
		//选择意向城市
		$(".city-content span").click(function(){
			$(".intention-input").val($(this).text());
			$(".intention-input").attr("data-code",$(this).attr("data"));
			$(".intention-city").hide();
		});
		
		$(".city-content ul li").click(function(){
			$(".intention-input").val($(this).text());
			$(".intention-input").attr("data-code",$(this).attr("data"));
			$(".intention-city").hide();
		}); 
		
		//选择期望工作
		$(".hope-job ul li").click(function(){
			$('.hope-input').html('<label class="label_job_type">'+$(this).attr("data")+'</label>');
			$(".hope-job").hide();
			$(".hope-input").css("border-color","#dadbdf");
		});
		
		//选择福利
		$(".demand ul li").click(function(){
			var selectJobTag = $("#hid_job_tag").val();
			var thisTag = $(this).attr("data")+",";
			if($(this).find('input[type=checkbox]').is(":checked")){
				if((selectJobTag.split(",").length-1)>=5){
					alert("最多可以选五项");
					$(this).find('input[type=checkbox]').attr("checked",false);
				}else{
					selectJobTag = selectJobTag + thisTag;
				}
			}else{
				selectJobTag = selectJobTag.replace(thisTag,"");
			}
			$("#hid_job_tag").val(selectJobTag);
		});
		//确定
		$(".save-demand").click(function(){
			var selectJobTag = $("#hid_job_tag").val();
			var mod=[];
			$.each(selectJobTag.split(","),function(i,j){
				if(j.length>1){
					mod.push('<label>'+j+'</label>');
				}
			});
			$(".demand-input").empty();
			$(".demand-input").append(mod.join(''));
			$(".demand").hide();
			$(".demand-input").css("border-color","#dadbdf");
		});
		//清空
		$(".clean-demand").click(function(){
			$(".demand-input").empty();
			$("#hid_job_tag").val('');
			$(".demand ul li").find("input[type=checkbox]").attr("checked",false);
		});
		
		//全屏轮播图
		$(".full-slide").slide({ titCell:".hd li", mainCell:".bd ul", effect:"fold", autoPlay:true, delayTime:500,interTime:3000});
		
		//返回顶部
		$(window).scroll(function(){
			if ($(window).scrollTop()>100)
			{$('.fix-menu .top').fadeIn(500)}
			else{$('.fix-menu .top').fadeOut(500);}
		});
		$(".fix-menu .top").click(function(){
			$('body,html').animate({scrollTop:0},500);
			return false;
	    });
		//福利标签 为第偶数个a添加clas
		$('.welfare-tags li:odd').addClass('odd');
		//优蓝精选企业
		$('.job-rec-each').hover(function(){
			$(this).find('.rec-signup').stop(false,true).animate({
				bottom:0
			},300);
		}).mouseleave(function() {
            $(this).find('.rec-signup').stop(false,true).animate({
				bottom:-40
			},300);
        });
		
		//搜索
		$(".btn-search").click(function(){
			var webPrefix = $('.intention-input').attr("data-code");
			var jobTag = $("#hid_job_tag").val();
			var jobType = $(".label_job_type").text();
			var salary = $('.single-slider').val()+"-10500";
			var condition = [];
			if(jobType==null || jobType ==""|| jobType =="job"){
				jobType = "zhaopin";
			}
			condition.push("0");
			if(jobTag!=null && jobTag!=""){
				jobTag = jobTag.substring(0,jobTag.length-1).replace("undefined","");
				condition.push(jobTag);
			}else{
				condition.push("0");
			}
			condition.push("0");
			if(salary!=null && salary!=""){
		   		condition.push(salary);
			}else{
				condition.push("0");
			}
			window.location.href="http://"+webPrefix+".youlanw.com/"+jobType+"/" + condition.join("_")+"_1/";
		});
		
		 $(".intention-city .city-nav li").click(function(){
			   	var _index = $(".intention-city .city-nav li").index(this);
			   	$(this).addClass("current").siblings().removeClass("current");
			   	$(".intention-city .city-content .item").eq(_index).addClass("current").siblings().removeClass("current");
		   });
		   //意向城市输入框
		   $(".intention-input").focus(function(){
		   		$(".intention-city").show();
		   });
		   $(document).click(function(e){
		        var _con = $('.intention-input,.city-nav,.city-content');   // 设置目标区域
		        if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
		        $(".intention-city").hide();
		        }
		    });
		    //期望工作选择
		    $(".hope-input").click(function(){
		    	$(".hope-job").show();
		    	$(this).css("border-color","#ed5f30");
		    });
		   $(document).click(function(e){
		        var _con = $('.hope-input,.hope-job');   // 设置目标区域
		        if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
		        $(".hope-job").hide();
		        $(".hope-input").css("border-color","#dadbdf");
		        }
		    });
		    //附加要求
		    $(".demand-input").click(function(){
		    	$(".demand").show();
		    	$(this).css("border-color","#ed5f30");
		    });
		   $(document).click(function(e){
		        var _con = $('.demand-input,.demand');   // 设置目标区域
		        if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
		        $(".demand").hide();
		        $(".demand-input").css("border-color","#dadbdf");
		        }
		    });
		    //range
		    $('.single-slider').jRange({
				from: 1500,
				to: 10500,
				step: 500,
				width: 330,
				showLabels: true,
				showScale: false,
				format: '%s'+'元以上'
			});
		    /*浏览记录*/
		    getCookieRecent();
		    $('.clear-record').click(function(){
    			Cookie.setCookie('recent_browse_job', escape("[]"), 5);
    			$("#recent_browse_list").empty();
    			var divs = [];
    			divs.push('<div class="job-list2 mt0"><p class="no-result"><span><i></i></span>暂无浏览记录</p></div>');
    			$("#recent_browse_list").append(divs.join(''));
    		});
		
	});
   
	function getRecent(data){
		$("#recent_browse_list").empty();
	    var divs = [];
		if(data==null||data==""){
			divs.push('<div class="job-list2 mt0"><p class="no-result"><span><i></i></span>暂无浏览记录</p></div>');
		}else{
			divs.push('<h2 class="box-title text-left">最近浏览职位</h2>');
			divs.push('<div class="job-list2">');
			$.each(data,function(i,j){
			   var obj = eval("("+j+")");
			   divs.push('<a href="'+YL.ctx+'/zhaopin_'+obj.id+'.html">'); 
			   divs.push('<ul class="job-list2-each">');
			   divs.push('<li class="pic"><img src="'+obj.imgpath+'" alt="'+obj.imgAlt+'" /></li>');
			   divs.push('<li class="title">'+obj.title+'</li>');
			   divs.push('<li class="salary">￥'+obj.totalsalary+'</li>');
			   divs.push('</ul></a>');
			});
			 divs.push('<div class="remove-border2"></div><a href="javascript:;" class="clear-record">清空最近浏览记录</a><div class="clear"></div></div>');
		}
	    $("#recent_browse_list").append(divs.join(''));
	}
	/*浏览记录*/
	function getCookieRecent(){
		var ids = [];
	    var rbs = Cookie.getCookie("recent_browse_job");
	    var defaultImage = $("#defaultImage").attr("src");
	    if(rbs!=null&&rbs!=undefined&&rbs!=""){
	        var jsonObj = eval("("+unescape(rbs)+")");
	        var tmpArray = [];
	            $.each(jsonObj,function(i){
	            	var j=jsonObj[jsonObj.length-i-1];
	                if($.inArray(j.id,ids)==-1 && tmpArray.length<4){
	                	var tmp = '{"id":"'+j.id+'","title":"'+j.title+'","imgpath":"'+j.imgpath+'","totalsalary":"'+j.totalsalary+'","imgAlt":"'+j.imgAlt+'"}';
	                    tmpArray.push(tmp);
	                    ids.push(j.id);
	                }
	            });
	        getRecent(tmpArray);
	    }
	}