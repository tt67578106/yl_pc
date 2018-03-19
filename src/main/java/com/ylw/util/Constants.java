package com.ylw.util;


import com.youlanw.util.pc.PcConstants;
/**
 * 常量，理论上来说，本文件从只存放与上线环境不一样的常量
 * @author Nicolas.Cai
 *
 */
public class Constants extends PcConstants {

	/**
     * ftpUrl 通用
     * 线上用：10.168.12.221
     * 测试用：121.40.134.113
     */
    public static final String FTP_URL = "121.40.134.113";
    
    /**
	 * 调用外网公共API地址
	 * 线上用：10.168.76.81:18884
	 * 测试用：121.40.201.157:18884/10.0.11.49:18882
	 */
	public static final String SEND_YOULANW_API_URL = "http://10.0.11.49:18882/api/youlanwapi/";

	/**
	 * 水印路径
	 */
	public static final String IMAGE_ICON_PATH = Constants.class.getClassLoader().getResource("").getPath() + "ylicon.png";

	/**
	 * 用户头像 session  key
	 */
	public static final String SESSION_KEY_USER_HEAD_PATH = "session_user_head_path_key";
	
	/**
	 * 调用APP地址
	 * 线上用：https://life-api.youlanw.com
	 * 测试用：10.0.11.44:8080
	 */
	public static final String SEND_APP_API_URL = "http://10.0.11.44:8080/api/v3/";
	/**
	 * pc 首页区块缓存的 key
	 */
	public static final String CACHE_KEY_ADVERT_AREA_POSITIONS = "cache_pc_key_advert_area_positions";
	
	/**
	 * pc 首页轮播位code值
	 */
	public static final String POSITION_CODE_HOME_SHUFFLING_JOBS_Code = "5772696571d03f0518eae20b";
	/**
	 * 首页 热门搜索广告位
	 */
	public static final String POSITION_CODE_HOME_HOT_RECOMMONDS_Code = "5772693671d03f0518eae209";

	/**
	 * 热门问答搜索
	 */
	public static final String POSITION_CODE_HOT_ADVISORY_Code = "57732dfb71d03f0518eae260";
	/**
	 * 相关招聘 
	 */
	public static final String POSITION_CODE_RELATED_RECRUITMENT_Code = "57732e2671d03f0518eae262";

	/**
	 * 分站web_prefix存储在session中的key
	 */
	public static final String SESSION_KEY_BRANCH_WEB_PREFIX = "session_key_branch_web_prefix";
	/**
	 * 友情链接
	 * 线上环境 577f59ba71d03f6f375ebd1a	
	 * 测试环境 577f592f5118bd30a1ef6da0
	 */
	public static final String POSITION_CODE_HOME_BLOGROLL_Code = "577f592f5118bd30a1ef6da0";

	/**
	 * 域名
	 * 线上环境 youlanw.com
	 * 测试环境 yl.test.tkinghr.com 
	 */
	public static final String DOMAIN_NAME = "yl.test.tkinghr.com ";
	
	/**同步简历Mdb
	 * 正式（不知道）
	 * 测试环境10.0.11.44:8080
	 */
	public static final String MDB_URL = "http://10.0.11.44:8080/api/v7/resume/";
//	public static final String MDB_URL = "http://127.0.0.1:8080/api/v7/resume/";	//本地
	/**
	 * 企业列表广告位code
	 * 线上环境PC名企广告位ID：582970cb71d03f400f70ff17
	 * 测试环境PC名企广告位ID：582970652e1183589147558d
	 */
	public static final String POSITION_CODE__RECOMMOND_COMPANY_Code = "582970652e1183589147558d";

	/**
	 * 求职状态缓存key
	 */
	public static final String CACHE_KEY_DIC_EMPLOYMENTSTATUS = "cache_key_pc_dic_employmentstatus";
	/**
	 * 婚姻状况
	 */
	public static final String CACHE_KEY_DIC_MARITALSTATUS = "cache_key_pc_dic_maritalstatus";
	/**
	 * 工作年限
	 */
	public static final String CACHE_KEY_DIC_WORKYEAR = "cache_key_pc_dic_workyear";
	
	
}
