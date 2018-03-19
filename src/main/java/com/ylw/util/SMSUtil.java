package com.ylw.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dl.mc.kit.YouLanSmsKit;


/**
 * 短信发送util
 * 
 * @author mabixiang
 *
 */
public class SMSUtil extends Thread{
	private static final String sign = "";//"【优蓝网】";
	private static final String register = "感谢您注册优蓝网，您本次的验证码为@ValidCode，30分钟内有效。直接致电4008-777-816，可享受求职专员专业服务"+sign;
	private static final String forget = "您正在使用短信找回密码，本次验证码为@ValidCode。为保障您的帐号安全，请勿轻易将验证码提供给他人，以免帐号被盗"+sign;
//	private static final String quick ="感谢您使用优蓝网 ，您下次可使用此手机号和密码登录优蓝网，您的默认密码为@ValidCode。如有问题请致电4008-777-816，祝您心情愉快"+sign;
	private static final String valmobile = "感谢您使用优蓝网，您本次验证码为@ValidCode。如有问题直接致电4008-777-816，可享受求职专员专业服务"+sign;
	// private static final Integer limitNumber = 10;// 每小时，每个号码允许发送条数
	private static final String signupSuccess = "我们已收到您的报名求职申请，正在为您安排专属就业顾问进行专业服务，请稍加等待。如有问题请致电4008-777-816，祝您心情愉快"+sign;

	private static Logger logger = LoggerFactory.getLogger(SMSUtil.class);
	
	private String mobile;
	private String smsName;
	private String ip;
	private Integer type;
	private String password;
	public SMSUtil(String mobile, String smsName) {
		super();
		this.mobile = mobile;
		this.smsName = smsName;
	}
	
	public SMSUtil(String mobile, String smsName,Integer type) {
		super();
		this.mobile = mobile;
		this.smsName = smsName;
		this.type = type;
	}
	/**
	 * 快速注册发送密码
	 * @param mobile
	 * @param smsName
	 * @param password
	 */
	public SMSUtil(String mobile,String password, String smsName) {
		super();
		this.mobile = mobile;
		this.smsName = smsName;
		this.password = password;
	}
	@Override
	public void run() {
		letsGo();
	}
	/**
	 * 同步方法发送验证码
	 */
	private synchronized void letsGo(){
		try {
			Object limited = StoreCacheUtil.getCacheValue("smscacheLimit", mobile);
			System.out.println("mobile:"+mobile+"\tlimited"+limited);
			if(limited == null ||(StringUtils.isNumeric(limited+"") && Integer.parseInt(limited+"") < 5) ){//次数超过五条手机号已被限制
				String code = null;
				if(type != null){
					code=sendSMS(mobile,type, getSmsContentByName(smsName, mobile), ip);
				}else if(password!=null){
					code = sendPasswordSMS(mobile,password,smsName);
				}else{
					code=sendSMS(mobile,0,getSmsContentByName(smsName, mobile), ip);
				}
				Cache cache= StoreCacheUtil.getCacheManager().getCache("smscache");
				cache.put(new Element(mobile, code));
				logger.info("发送短信\tmobile:" + mobile + "\tcode:" + code);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 发送密码
	 * @param mobile
	 * @param smsName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String sendPasswordSMS(String mobile,String password,String smsName) throws Exception{
		if(!ifLimited(mobile)){
			String content = getSmsContentByName(smsName,mobile);
			content = content.replace("@ValidCode", password);
			return send(mobile,0,content);
		}else{
			return null;
		}
	}
	/**
	 * 发送短信接口(直接调用HTTP短信网关)
	 * @param mobile
	 * @param content
	 * @return
	 */
	private static String send(String mobile ,Integer type, String content){
		String code = RandomSeriNoUtils.generateNumber4();
		content = content.replace("@ValidCode", code);
		YouLanSmsKit.sendSms(mobile, content);
		return code;
	}
	/**
	 * 发送短信(Http)
	 * @param phoneNum
	 * @param smsContent
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	private static String sendSMS(String phoneNum,Integer type, String smsContent, String ip) throws Exception {
		if(!ifLimited(phoneNum)){
			return send(phoneNum,type, smsContent);
		}
		return null;
	}
	
	/**
	 * 判断是否恶意刷短信，固定时间不能超过固定条数 -- 手机号码验证+ip验证
	 * 
	 * @return
	 */
	private static Integer messageAuth(String phoneNum, String ip) {
		Cache cache = StoreCacheUtil.getCacheManager().getCache("messageAuth");
		Element ele = cache.get(phoneNum + "_" + ip);
		int count = 0;
		if (null != ele && !"".equals(ele) && null != ele.getObjectValue()) {
			count = Integer.parseInt(ele.getObjectValue().toString());
		}
		count = count++;
		cache.put(new Element(phoneNum + "_" + ip, count + 1));
		return count;
	}

	private static String getSmsContentByName(String kname, String phoneNum) {
		if (kname.equals("register")) {
			return register;
		} else if (kname.equals("forget")) {
			return forget;
		} else if (kname.equals("quick")) {
			return "感谢您使用优蓝网，下次您可以直接使用手机号" + phoneNum + "登录优蓝网，默认密码为@ValidCode。如有问题请致电4008-777-816，可享受求职专员专业服务"+sign;
		} else if (kname.equals("valmobile")) {
			return valmobile;
		} else if (kname.equals("signupSuccess")) {
			return signupSuccess;
		} else {
			return "";
		}
	}
	

	/**
	 * 检测是否被限制发送短信
	 * @return true:已被限制/false:未被限制
	 */
	private static boolean ifLimited(String mobilePhone){
		Object objCount = StoreCacheUtil.getCacheValue("smscacheLimit", mobilePhone);
		if(objCount != null && StringUtils.isNumeric(objCount+"") && Integer.parseInt(objCount+"") < 5){
			StoreCacheUtil.putCache("smscacheLimit", mobilePhone, (Integer.parseInt(objCount + "") + 1));
			return false;
		}else if(objCount == null){
			StoreCacheUtil.putCache("smscacheLimit", mobilePhone, 1);
			return false;
		}else{
			return true;
		}
	}
}
