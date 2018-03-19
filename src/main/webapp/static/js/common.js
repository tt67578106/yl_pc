var IMAGE_FILE_URL="http://img.youlanw.com/";
$(function(){
	//顶部菜单滚动到顶部时固定在顶部
	$(window).scroll(function(){
		if($(this).scrollTop()>$('.main-menu-wrapper').height()){
			$('.main-menu-wrapper').addClass('on');
		}
		else{
			$('.main-menu-wrapper').removeClass('on');
		}
	});
	//回车搜索
	document.onkeydown = function (e) {
		var theEvent = window.event || e;
		var code = theEvent.keyCode || theEvent.which;
		if (code == 13) {
			$(".search-enter").click();
		}
	}
	
	//tab
	$('.tab-title li').mouseenter(function(){
		$(this).addClass('current').siblings().removeClass('current');
		var current_index=$('.tab-title li').index($(this));
		$('.tab-each').eq(current_index).show().siblings('.tab-each').hide();
	});
	
	//构造select
	$(".select-g-options").hide();
	$('.select-g-title').click(function(){
		$(document).find('.select-g-options').not($(this).next('.select-g-options')).hide();
		$(this).next('.select-g-options').toggle();
		$(this).parents('.select-g').toggleClass('open');
	});
	$(".select-g-options li").click(function() {
		if($(this).text()!='不限'){
			$(this).parents('.select-g-options').prev('.select-g-title').find('span').text($(this).text());	
		}
		else{
			$(this).parents('.select-g-options').prev('.select-g-title').find('span').text($(this).parents('.select-g-options').prev('.select-g-title').find('span').data('default'));
		}
		var current_index=$(this).index();
		$(this).parents('.select-g-options').next('.original-select').find('option').removeAttr("selected").eq(current_index).attr("selected","selected");
		$(this).parents('.select-g-options').hide();
		$(this).parents('.select-g').removeClass('open');
	});
	//点击构造下拉菜单以外区域，关掉构造select
	$(document).bind("click",function(e){
		var target = $(e.target);
		if(target.closest(".select-g").length == 0){
			$(".select-g-options").hide();
			$('.select-g').removeClass('open');
		}
	})
	//默认提示文字
	//text or textarea
	$('.tip-text').addClass('tip-text-default').bind({
		focus:function(){
			if (this.value == this.defaultValue){
			this.value="";
			$(this).removeClass('tip-text-default').addClass('tip-text tip-text-input');
			}
		},
		blur:function(){
			if (this.value == ""){
			this.value = this.defaultValue;
			$(this).removeClass('tip-text-input').addClass('tip-text tip-text-default');
			}
		}
	});
	//输入密码一项，密码框与文本框切换
	$('.input-tt').focus(function(){
		$(this).hide().parents('.password-w').find('.input-tp').show().focus();
	})
	$('.input-tp').blur(function(){
		if(!$(this).val()){
			$(this).hide().parents('.password-w').find('.input-tt').show();
		}
	});
	//构造radio
	$('.radio-g').click(
		function(){
			$(this).addClass('radio-g-current').siblings().removeClass('radio-g-current');
		}
	);
	$('.radio-g-input').click(function(){
		$(this).attr('checked','checked');
		$(this).parent().siblings().find('.radio-g-input').attr('checked',false);
	});
	//返回顶部
	$('.float-menu li').mouseover(function(){
		$(this).find('.des').show();
		$(this).addClass('on').siblings().removeClass('on');
		$(this).css('width','150px').siblings().css({'width':'50px','margin-left':'100px'});
	}).mouseout(function(){
		$(this).find('.des').hide();
		$(this).removeClass('on').css('width','50px')
		$('.float-menu li').css('margin-left','0');
	});
	$(window).scroll(function(){
		if ($(window).scrollTop()>100)
		{$('.float-menu .to-wrapper').fadeIn(500)}
		else{$('.float-menu .to-wrapper').fadeOut(500);}
	});
	$(".float-menu .to-wrapper").click(function(){
		$('body,html').animate({scrollTop:0},500);
		return false;
    });
	//通用返回顶部
	$('.go-top').click(function(){
		$('body,html').animate({scrollTop:0},500);
		return false;
    });
	//右侧固定
	/*if($('.right-fixed').length || $('.right-fixed2').length)
	var right_fixed_offset_top=$('.right-fixed').offset().top;
	$('.right-fixed').each(function(){//高度补偿
		$(this).parents('.box-r').height($(this).height());
	});
	$(window).scroll(function(){
		if($(this).scrollTop()>right_fixed_offset_top){
			$('.right-fixed').css({'position':'fixed','top':0})
			$('.right-fixed2').css({'position':'fixed','top':58})
		}
		else $('.right-fixed').css({'position':'static'})
	});*/
	if($('.right-fixed-sty').length )
		var right_fixed_offset_top=$('.right-fixed-sty').offset().top;
		$('.right-fixed-sty').each(function(){//高度补偿
			$(this).parents('.box-r').height($(this).height());
		});
		$(window).scroll(function(){
			if($(this).scrollTop()>right_fixed_offset_top){
				$('.right-fixed-sty').css({'position':'fixed','top':0})
			}
			else $('.right-fixed-sty').css({'position':'static'})
		});
	//职位列表，鼠标放到某条招聘信息上，显示按钮
	$('.job-list-each').hover(function(){
		$(this).find('.signup').stop(false,true).animate({
			top:100
		},350)
	}).mouseleave(function(){
		
		$(this).find('.signup').stop(false,true).animate({
			top:180
		},350);
	});
	//tab
	$('.tab-g .title li').click(function(){
		$(this).addClass('current').siblings().removeClass('current');
		var current_index=$('.tab-g .title li').index(this);
		$('.tab-g .content-each').eq(current_index).show().siblings('.content-each').hide();
	});
	//上传logo/文件 通用
	$('.upload-file .inner').click(function(){
		$(this).parents('.upload-file').find('.file').trigger('click');
	})
	//input or textarea focus
	$('.form-each-input,.textarea-g').focus(function(){
		$(this).addClass('focus');
	});
	$('.form-each-input,.textarea-g').blur(function(){
		$(this).removeClass('focus');
	});
})