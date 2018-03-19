package com.ylw.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springside.modules.utils.Collections3;

/**
 * URL重写分页帮助类
 * @author jack
 * @version 2015-8-20 16:48:19
 */
public class URLRewriteUtil {
	/**
	 * 根据参数得到分页后的URL重写参数
	 * @param params
	 * @return
	 */
	public static String getUrlByParams(Map<String, Object> params) {
		if (Collections3.isEmpty(params)) {
			return "";
		}
		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append(entry.getValue());
			if (it.hasNext()) {
				queryStringBuilder.append('_');
			}
		}
		return queryStringBuilder.toString();
	}
}
