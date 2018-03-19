package com.ylw.service.base;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ylw.util.Constants;

/**
 * 记录用户访问路
 *  @author jack
 */
@Component
@Transactional
public class CookieService {
	private static Logger logger = LoggerFactory.getLogger(CookieService.class);
	
	/**
	 * 保存cookie 跟踪百度推广进来的
	 * @param jobId
	 * @param response
	 */
	public void setCookie(String cookieKey, String cookieValue, HttpServletResponse response) {
		if (response != null) {
			Cookie cookie = new Cookie(cookieKey, cookieValue);
			cookie.setMaxAge(36000 * 24);
			cookie.setDomain(Constants.DOMAIN_NAME);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
}
