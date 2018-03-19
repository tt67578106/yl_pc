package com.ylw.web.account;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylw.entity.sys.SysUser;
import com.ylw.service.base.AccountService;
import com.ylw.service.sys.SysUserService;
import com.ylw.util.Constants;
import com.ylw.util.RandomSeriNoUtils;
import com.ylw.util.SMSUtil;
import com.ylw.util.StoreCacheUtil;


@Controller
@RequestMapping("forgetpwd")
public class ForgetPasswordController {
	private static final Logger log = LoggerFactory.getLogger(ForgetPasswordController.class);
	@Autowired
	private AccountService accountService;
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping
	public String form(Model model){
		return "account/forgetPassword";
	}
	/**
	 * 忘记密码，验证手机号码
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="valmobileForForget",method=RequestMethod.POST)
	public String valmobileForForget(@RequestParam("mobile") String mobile,HttpServletRequest request){
		boolean hasUserByUser=true;
		hasUserByUser=accountService.hasUserByUsername(mobile);
		if(!hasUserByUser){//不存在用户不发短信
			return Constants.RETURN_STATUS_NULL;
		}
		SMSUtil smsUtil =  new SMSUtil(mobile, "valmobile");
		smsUtil.start();
		return Constants.RETURN_STATUS_SUCCESS;
	}
	/**
	 * 验证验证码
	 * @param mobile
	 * @param thekey
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="valiKey",method=RequestMethod.POST)
	public String valiKey(@RequestParam("mobile")String mobile,@RequestParam("thekey")String thekey,HttpServletRequest request){
		Cache cache = StoreCacheUtil.getCacheManager().getCache("smscache");
		Element ele = cache.get(mobile);
		if(ele != null && ele.getObjectValue() != null && ele.getObjectValue().equals(thekey)){
			String code = RandomSeriNoUtils.genCodeByTime("vali");
			request.getSession().setAttribute(Constants.VALI_STATUS_CODE, code);
			return Constants.RETURN_STATUS_SUCCESS + "_" + code;
		}
		return Constants.RETURN_STATUS_FAILURE;
	}
	/**
	 * 修改密码
	 * @param vali
	 * @param mobile
	 * @param password
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="resetPassword", method=RequestMethod.POST)
	public String resetPassword(@RequestParam("vali")String vali,@RequestParam("mobile")String mobile,@RequestParam("pass")String password,HttpServletRequest request){
		String valiCode = request.getSession().getAttribute(Constants.VALI_STATUS_CODE)+"";
		if (vali.equals(valiCode)) {
			accountService.changePwd(mobile, password);
			return Constants.RETURN_STATUS_SUCCESS;
		}
		return Constants.RETURN_STATUS_FAILURE;
	}
}
