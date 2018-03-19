package com.ylw.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信发送工具
 * @author Nicolas.Cai
 * @since 2015年11月27日 下午4:25:58
 */
public class SmsSafeUtil implements Runnable{
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 24小时内次数限制
	 */
	private static final int ONE_DAY_LIMIT_COUNT = 5;
	/**
	 * 1小时内次数限制
	 */
	private static final int ONE_HOUR_LIMIT_COUNT = 5;
	/**
	 * 1分钟内次数限制
	 */
	private static final int ONE_MIN_LIMIT_COUNT = 5;
	/**
	 * 30秒内次数限制
	 */
	private static final int THIRTY_SECOND_LIMIT_COUNT = 5;
	/**
	 * 5秒内次数限制
	 */
	private static final int FIVE_SECOND_LIMIT_COUNT = 5;
	/**
	 * 线上发送短信接口地址
	 */
	private String mobile;
	private String content;
	/**
	 * 发送短信
	 */
	@Override
	public void run() {
		Object obj = StoreCacheUtil.getCacheValue("oneDayCache", "sms_send_forbidden_" + mobile);
		if(obj == null){
			//sendSms();
			logger.warn("sms_has_send\t" + mobile + content);
		}else{
//			logger.info("sms_not_send\t" + mobile+"\thas been forbidden in this time by server");
		}
	}
	/**
	 * 24小时总次数限制
	 * @param mobile
	 * @return
	 */
	public synchronized static boolean oneDayCountLimit(String mobile){
		Object dayCache = StoreCacheUtil.getCacheValue("oneDayCache", "one_day_sms_fbd_" + mobile);
		int dayCount = dayCache == null?0:Integer.parseInt(dayCache+"");
		if(dayCount >= ONE_DAY_LIMIT_COUNT){
			return false;
		}
		return oneHourTimeOutLimit(mobile);
	}
	/**
	 * 1小时频率限制
	 * @param mobile
	 * @return
	 */
	private synchronized static boolean oneHourTimeOutLimit(String mobile){
		Object oneHourCache = StoreCacheUtil.getCacheValue("dieInOneHourCache", "one_hour_sms_fbd_" + mobile);
		int oneHourCount = oneHourCache == null?0:Integer.parseInt(oneHourCache+"");
		if(oneHourCount > 0){
			StoreCacheUtil.setCacheValue("dieInOneHourCache", "one_hour_sms_fbd_" + mobile, (oneHourCount + 1));
			if(oneHourCount >= ONE_HOUR_LIMIT_COUNT){
				StoreCacheUtil.setCacheValue("oneDayCache", "one_day_sms_fbd_" + mobile, ONE_DAY_LIMIT_COUNT);
			}
			return false;
		}
		return OneMinTimeOutLimit(mobile);
	}
	/**
	 * 1分钟频率限制
	 * @param mobile
	 * @return
	 */
	private synchronized static boolean OneMinTimeOutLimit(String mobile){
		Object oneMinCache = StoreCacheUtil.getCacheValue("dieInOneMinCache", "one_min_sms_fbd_" + mobile);
		int oneMinCount = oneMinCache == null?0:Integer.parseInt(oneMinCache+"");
		if(oneMinCount > 0){
			StoreCacheUtil.setCacheValue("dieInOneMinCache", "one_min_sms_fbd_" + mobile, (oneMinCount + 1));
			if(oneMinCount >= ONE_MIN_LIMIT_COUNT){
				StoreCacheUtil.setCacheValue("dieInOneHourCache", "one_hour_sms_fbd_" + mobile, 1);
			}
			return false;
		}
		return thirtySecondTimeOutLimit(mobile);
	}
	/**
	 * 30秒频率限制
	 * @param mobile
	 * @return
	 */
	private synchronized static boolean thirtySecondTimeOutLimit(String mobile){
		Object thirtySecondCache = StoreCacheUtil.getCacheValue("dieInThirtySecondCache", "thirty_sec_sms_fbd_" + mobile);
		int thirtySecondCount = thirtySecondCache == null?0:Integer.parseInt(thirtySecondCache+"");
		if(thirtySecondCount > 0){
			StoreCacheUtil.setCacheValue("dieInThirtySecondCache", "thirty_sec_sms_fbd_" + mobile, (thirtySecondCount + 1));
			if(thirtySecondCount >= THIRTY_SECOND_LIMIT_COUNT){
				StoreCacheUtil.setCacheValue("dieInOneMinCache", "one_min_sms_fbd_" +  mobile, 1);
			}
			return false;
		}
		return fiveSecondTimeOutLimit(mobile);
	}
	/**
	 * 5秒频率控制
	 * @param mobile
	 * @return
	 */
	public synchronized static boolean fiveSecondTimeOutLimit(String mobile){
		Object fiveSecondCache = StoreCacheUtil.getCacheValue("dieInFiveSecondCache", "five_sec_sms_fbd_" + mobile);
		int fiveSecondCount = fiveSecondCache == null?0:Integer.parseInt(fiveSecondCache+"");
		if(fiveSecondCount > 0){
			StoreCacheUtil.setCacheValue("dieInFiveSecondCache", "five_sec_sms_fbd_" + mobile, (fiveSecondCount + 1));
			if(fiveSecondCount >= FIVE_SECOND_LIMIT_COUNT){
				StoreCacheUtil.setCacheValue("dieInThirtySecondCache", "thirty_sec_sms_fbd_" + mobile, 1);
			}
			return false;
		}else{
			StoreCacheUtil.setCacheValue("dieInFiveSecondCache", "five_sec_sms_fbd_" + mobile, (fiveSecondCount + 1));
			Object dayCache = StoreCacheUtil.getCacheValue("oneDayCache", mobile);
			int dayCount = dayCache == null?0:Integer.parseInt(dayCache+"");
			StoreCacheUtil.setCacheValue("oneDayCache", mobile, (dayCount + 1));
			return true;
		}
	}
}
