<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新用户注册_优蓝网</title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/common.css"/>
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" mce_href="${ctx}/static/images/favicon.ico" type="image/x-icon">
<style type="text/css">
.page-wrapper.reg{background:#f68532 url(${ctx}/static/images/datas/reg-bg.jpg) no-repeat center 50px; width:100%;padding-bottom:100px;}
.copyright{text-align:center;font-size:14px;color:#fff;width:100%;height:80px;line-height:80px;position:absolute;bottom:0;}
/*reg-l*/
.reg-l{width:565px;}
.reg-l .title{margin-top:30px;color:#feff9e;text-align:center;width:530px;line-height:1.3;}
.reg-l .text{font-size:32px;}
.reg-l .text .num{font-size:40px;}
.reg-l .text2{font-size:28px;}
.reg-l .text2 i{font-size:36px;}
.reg-l .tab-g .title li{float:left;font-size:18px;color:#fff;height:40px;line-height:40px;min-width:40px;padding:0 15px;text-align:center;margin-right:10px;cursor:pointer;}
.reg-l .tab-g .title li.current{background-color:#f26536;}
.reg-l .list .each{height:50px;line-height:50px;border-bottom:1px dashed #f68550;}
.reg-l .list .each:hover{background-color:#f58b61;}
.reg-l .list li{float:left;font-size:16px;text-align:center;margin-left:15px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
.reg-l .list .company{color:#fff;width:210px;margin-left:0;}
.reg-l .list .post{color:#fff;width:150px;}
.reg-l .list .salary{color:#fced84;}
/*reg-r*/
.reg-r{width:400px;min-height:460px;background-color:#fff;border-radius:5px;margin:50px 0 0 0;position:relative;}
.reg-r .title{color:#333;font-size:20px;margin:25px 0 15px 20px;}
.reg-r .form{padding:0 20px;}
.reg-r .form .each .tip-text{width:340px;height:16px;line-height:16px;padding:15px 10px;font-size:16px;border-radius:3px;border:1px solid #ddd; box-shadow:0 0 4px #ddd}
.reg-r .form .tips{height:25px;line-height:25px;visibility:visible;padding:0 10px;color:#f3040f;}
.reg-r .form .btn-reg{width:100%;height:50px;background-color:#ed5f30;color:#fff;border-radius:3px;border:none;font-size:20px;cursor:pointer;}
.reg-r .form .btn-reg:hover{background-color:#f36e42;}
.reg-r .form .btn-reg:active{background-color:#d94e20;}
.reg-r .form .code{position:relative;}
.reg-r .form .code .tip-text{width:140px;}
.reg-r .form .code .pic{position:absolute;left:180px;top:8px;}
.reg-r .form .code .pic img{width:81px;height:35px;margin-right:10px;}
.reg-r .form .agree{height:50px;line-height:50px;color:#999;font-size:14px;}
.reg-r .form .agree input{position:relative;top:1px;margin-right:5px;cursor:pointer;}
.reg-r .have-account{height:60px;line-height:60px;background-color:#f2fffc;position:absolute;bottom:0;width:100%;left:0;border-top:1px solid #ddd;text-align:right;}
.reg-r .have-account a{font-size:16px;color:#0e65a7;margin-right:30px;position:relative;padding-right:40px;}
.reg-r .have-account a i{position:absolute;width:30px;height:30px;position:absolute;right:0;top:-3px;background:url(${ctx}/static/images/common22.png) no-repeat -2px -2px;}
/*fam-partners*/
.fam-partners{margin-top:55px;}
.fam-partners li{float:left;width:110px;height:85px;background-color:#fff;position:relative;margin-left:5px;text-align:center;}
.fam-partners li .p{display:inline-block;width:0;height:85px;vertical-align:middle;}
.fam-partners li img{vertical-align:middle;max-width:100px;max-height:75px;}
.fam-partners li .shade{position:absolute;width:110px;height:85px;background-color:#000;top:0;left:0;opacity:0.2;filter:alpha(opacity=20);}
.fam-partners li:hover .shade{opacity:0;filter:alpha(opacity=0);}
</style>
</head>

<body>
<!---->
<div class="page-wrapper reg">
    <!--menu-->
    <div class="top-menu-wrapper" style="box-shadow:none;border:0;">
        <div class="box-out">
            <a href="${ctx }"><img src="${ctx}/static/images/logo.png" alt="优蓝网" class="logo fl"/></a>
            <div class="icon-tel3 fr">服务热线：4008-777-816</div>
            <div class="clear"></div>
        </div>
    </div>
    <!--/menu--->
    
    <!---->
    <div class="box-out">
        <!--reg-l-->
        <div class="reg-l fl">
        
            <div class="title">
                <p class="text"><span class="num">300</span> 个城市，<span class="num">10</span> 万家名企</p>
                <p class="text2">好职位随你<i>挑</i></p>
            </div>
            
            <div class="tab-g">
                <ul class="title">
                    <li class="current">普工</li>
                    <li>客服</li>
                    <li>销售</li>
                    <li>服务员</li>
                    <li>司机</li>
                    <li>车床工</li>
                </ul>
                <div class="clear"></div>
                <div class="content-each show">
                    <div class="list">
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">大昶电子</li>
                            <li class="post">作业员</li>
                            <li class="salary">￥2500-3500元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">群光电能</li>
                            <li class="post">作业员</li>
                            <li class="salary">￥3000-5000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">旭硕科技</li>
                            <li class="post">作业员</li>
                            <li class="salary">￥3200-5000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">仁宝电脑</li>
                            <li class="post">普工</li>
                            <li class="salary">￥3500-5000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">德尔福派克</li>
                            <li class="post">普工</li>
                            <li class="salary">￥3600-4200元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">力帆汽车</li>
                            <li class="post">普工</li>
                            <li class="salary">￥3000-4500元/月</li>
                        </ul>
                        </a>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="content-each">
                    <div class="list">
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">森博国际教育</li>
                            <li class="post">客服</li>
                            <li class="salary">￥2000-4000元/月</li>
                        </ul>
                        </a>
                         <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">徐一记汽车用品</li>
                            <li class="post">淘宝客服</li>
                            <li class="salary">￥3000-5000元/月</li>
                        </ul>
                        </a>
                         <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">典雅集团</li>
                            <li class="post">话务员</li>
                            <li class="salary">￥1800-3000元/月</li>
                        </ul>
                        </a>
                         <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">古尚集团</li>
                            <li class="post">淘宝专员</li>
                            <li class="salary">￥3000-5000元/月</li>
                        </ul>
                        </a>
                         <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">广联达</li>
                            <li class="post">话务员</li>
                            <li class="salary">￥2000-3000元/月</li>
                        </ul>
                        </a>
                         <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">百望九赋</li>
                            <li class="post">接听客服</li>
                            <li class="salary">￥2500-4500元/月</li>
                        </ul>
                        </a>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="content-each">
                    <div class="list">
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">英特福斯</li>
                            <li class="post">专员</li>
                            <li class="salary">￥3000-5000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">津住汽车线束</li>
                            <li class="post">储备干部</li>
                            <li class="salary">￥3000-5000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">大茂伟瑞柯车灯</li>
                            <li class="post">储备干部</li>
                            <li class="salary">￥3500-4500元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">零号线</li>
                            <li class="post">储备干部</li>
                            <li class="salary">￥3500-5000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">庆爱尚豪车销售</li>
                            <li class="post">销售顾问</li>
                            <li class="salary">￥2000-3500元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">台励福叉车集团</li>
                            <li class="post">销售人员</li>
                            <li class="salary">￥2000-4000元/月</li>
                        </ul>
                        </a>
                        
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="content-each">
                    <div class="list">
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">宜家家居</li>
                            <li class="post">服务员</li>
                            <li class="salary">￥2000-4000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">万达集团</li>
                            <li class="post">服务员</li>
                            <li class="salary">￥2000-3000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">千百度</li>
                            <li class="post">服务员</li>
                            <li class="salary">￥3000-5000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">和记黄埔</li>
                            <li class="post">服务员</li>
                            <li class="salary">￥1500-3000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">奥蓝酒店</li>
                            <li class="post">服务员</li>
                            <li class="salary">￥2000-4500元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">首创餐饮</li>
                            <li class="post">服务员</li>
                            <li class="salary">￥2500-3500元/月</li>
                        </ul>
                        </a>
                        
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="content-each">
                    <div class="list">
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">德邦物流</li>
                            <li class="post">司机</li>
                            <li class="salary">￥4500-6000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">樱花卫厨</li>
                            <li class="post">司机</li>
                            <li class="salary">￥2500-4500元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">神州租车</li>
                            <li class="post">司机</li>
                            <li class="salary">￥2000-4500元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">双玛科技</li>
                            <li class="post">司机</li>
                            <li class="salary">￥2000-4000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">助友创美物业</li>
                            <li class="post">司机</li>
                            <li class="salary">￥2000-4000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">京东方科技集团</li>
                            <li class="post">司机</li>
                            <li class="salary">￥2000-4000元/月</li>
                        </ul>
                        </a>
                        
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="content-each">
                    <div class="list">
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">东风小康</li>
                            <li class="post">注塑工</li>
                            <li class="salary">￥3000-5000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">宗申发动机制造</li>
                            <li class="post">车床工</li>
                            <li class="salary">￥3500-4500元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">长安福特马自达</li>
                            <li class="post">叉车工</li>
                            <li class="salary">￥2500-4000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">前卫科技集团</li>
                            <li class="post">技术工</li>
                            <li class="salary">￥3000-4500元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">巨泰机械</li>
                            <li class="post">数控车工</li>
                            <li class="salary">￥3000-5000元/月</li>
                        </ul>
                        </a>
                        <a href="javascript:;" target="_blank">
                        <ul class="each">
                            <li class="company">新兴齿轮</li>
                            <li class="post">磨工</li>
                            <li class="salary">￥3500-4500元/月</li>
                        </ul>
                        </a>
                        
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
            
        </div>
        <!--/reg-l-->
        <!--reg-r-->
        <div class="reg-r fr">
            <p class="title">10秒注册，立即上岗</p>
            <div class="form">
             <form id="from_user" action="${ctx}/portal" method="post">
                <div class="each">
                    <input type="text" id="txt_reg_user_loginname" name="loginname" class="tip-text" placeholder="请输入正确的11位手机号码"  maxlength="11"/>
                </div>
                <div class="tips">${err_loginname }</div>
                <div class="each">
                    <input type="password" id="txt_reg_user_password" name="password" class="tip-text" placeholder="请输入6~18位字母、数字" maxlength="18"/>
                </div>
                <div class="tips">${err_passwore }</div>
                <div class="each code">
                    <input class="tip-text" type="text" id="txt_reg_vali_code" name="valiCode" placeholder="请输入验证码" maxlength="3" pattern="\d+" />
                     <span class="pic">
                     	<img style="width: 90px;height: 40px;margin-left: 5px; " src="${ctx}/verification/reg.jpg?"  onclick="this.src=this.src+Math.random()" alt="验证码"/>
                     	<a href="javascript:;" onclick="$(this).prev().click();"  class="blue">换一张</a>
                    </span>
                </div>
                <div class="tips">${err_valiCodeu }</div>
                <div class="each">
                    <input type="button" id="btn_reg_user" value="立即注册" class="btn-reg" />
                </div>
                <div class="agree">
                    <label class="cursor"><input type="checkbox" id="cb_user" checked="checked"/>我已阅读并同意</label><a href="${ctx }/about/agreement" target="_blank" class="c999">《用户使用协议》</a>
                </div>
                <div class="have-account"><a href="${ctx }/login">已有账号，去登录<i></i></a></div>
                </form>
            </div>
        </div>
        <!--/reg-r-->
        <div class="clear"></div>
    </div>
    <!--/-->
    
    <!---->
    <div class="fam-partners box-out">
        <ul>
            <a href="javascript:;" target="_blank">
            <li><span class="p"></span><img src="${ctx}/static/images/datas/fam-partner1.jpg"/><span class="shade"></span></li>
            </a>
            <a href="javascript:;" target="_blank">
            <li><span class="p"></span><img src="${ctx}/static/images/datas/fam-partner2.jpg"/><span class="shade"></span></li>
            </a>
            <a href="javascript:;" target="_blank">
            <li><span class="p"></span><img src="${ctx}/static/images/datas/fam-partner3.jpg"/><span class="shade"></span></li>
            </a>
            <a href="javascript:;" target="_blank">
            <li><span class="p"></span><img src="${ctx}/static/images/datas/fam-partner4.jpg"/><span class="shade"></span></li>
            </a>
            <a href="javascript:;" target="_blank">
            <li><span class="p"></span><img src="${ctx}/static/images/datas/fam-partner5.jpg"/><span class="shade"></span></li>
            </a>
            <a href="javascript:;" target="_blank">
            <li><span class="p"></span><img src="${ctx}/static/images/datas/fam-partner6.jpg"/><span class="shade"></span></li>
            </a>
            <a href="javascript:;" target="_blank">
            <li><span class="p"></span><img src="${ctx}/static/images/datas/fam-partner7.jpg"/><span class="shade"></span></li>
            </a>
            <a href="javascript:;" target="_blank">
            <li><span class="p"></span><img src="${ctx}/static/images/datas/fam-partner8.jpg"/><span class="shade"></span></li>
            </a>
        </ul>
        <div class="clear"></div>
    </div>
    <!--/-->
    
    <div class="copyright">沪ICP备14033709号-1   Copyright &copy; 优蓝网  www.youlanw.com</div>
</div>   
<!--/page-wrapper-->    
    <script type="text/javascript" src="${ctx }/static/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/js/common.js" charset="utf-8"></script>
    <script type="text/javascript">
    $(function(){
    	$("#cb_user").click(function(){
			if($(this).is(':checked')){
				$("#btn_reg_user").show();
			}else{
				$("#btn_reg_user").hide();
			}
		});
    	$("#txt_reg_user_loginname").blur(function(){
			if(!/^1[3-9]\d{9}/.test($.trim($(this).val()))){
				$(this).parent().next().html("请输入正确位手机号码！");
			}else{
				$(this).parent().next().html("");
			}
		});
		$("#txt_reg_user_password").blur(function(){
			if(!/^[a-zA-Z0-9]{6,18}$/.test($(this).val())){
				$(this).parent().next().html("请输入密码6~18位字母、数字！");
			}else{
				$(this).parent().next().html("");
			}
		});
		$("#txt_reg_vali_code").blur(function(){
			if($.trim($(this).val()).length<1){
				$(this).parent().next().html("请输入验证码！");
			}else{
				$(this).parent().next().html("");
			}
		});
		//个人用户注册
		$("#btn_reg_user").click(function(){
			if(userValidate()){
				$("#btn_reg_user").unbind();
				$("#from_user").submit();
			}
		});
    });
    function userValidate(){
		var m = $.trim($("#txt_reg_user_loginname").val());
		var p = $.trim($("#txt_reg_user_password").val());
		var v = $.trim($("#txt_reg_vali_code").val());
		if(!/^1[3-9]\d{9}/.test(m)){
			$("#txt_reg_user_loginname").focus();
			$("#txt_reg_user_loginname").parent().next().html("请输入正确位手机号码！");
			return false;
		}else if (p.length < 1) {
			$("#txt_reg_user_password").focus();
			$("#txt_reg_user_password").parent().next().html("请输入密码！");
			return false;
		}else if(!/^[a-zA-Z0-9]{6,18}$/.test(p)){
			$("#txt_reg_user_password").focus();
			$("#txt_reg_user_password").parent().next().html("请输入密码6~18位字母、数字！");
			return false;
		}else if (v.length < 1) {
			$("#txt_reg_vali_code").focus();
			$("#txt_reg_vali_code").parent().next().html("请输入验证码！");
			return false;
		}
		return true;
	}
    </script>
</body>