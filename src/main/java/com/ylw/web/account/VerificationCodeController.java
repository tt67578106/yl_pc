package com.ylw.web.account;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylw.util.Constants;
import com.ylw.util.StoreCacheUtil;
import com.ylw.util.VerificationCodeTool;
/**
 * 验证码生成
 * @author nicolas
 *
 */
@Controller
@RequestMapping("verification")
public class VerificationCodeController {

	/**
	 * 登录验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("login.jpg")
	public void loginCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		VerificationCodeTool vcTool = new VerificationCodeTool();
		BufferedImage image = vcTool.drawVerificationCodeImage();
		int vcResult = vcTool.getXyresult();
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SESSION_KEY_VALI_CODE_LOGIN, vcResult);
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/**
	 * 注册验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("reg.jpg")
	public void regCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		VerificationCodeTool vcTool = new VerificationCodeTool();
		BufferedImage image = vcTool.drawVerificationCodeImage();
		int vcResult = vcTool.getXyresult();
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SESSION_KEY_VALI_CODE_REG, vcResult);
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	/**
	 * 验证输入是否正确
	 * @param type
	 * @param value
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="valiCode", method=RequestMethod.POST)
	public String valiCode(@RequestParam String mobile,@RequestParam String type,@RequestParam String value, HttpServletRequest request){
		StoreCacheUtil.putCache("smscacheValiCode", mobile, value);
		if("login".equals(type)){
			String loginCode = request.getSession().getAttribute(Constants.SESSION_KEY_VALI_CODE_LOGIN) + "";
			return value.equals(loginCode)?Constants.RETURN_STATUS_SUCCESS:Constants.RETURN_STATUS_FAILURE;
		}else if("reg".equals(type)){
			String regCode = request.getSession().getAttribute(Constants.SESSION_KEY_VALI_CODE_REG) + "";
			return value.equals(regCode)?Constants.RETURN_STATUS_SUCCESS:Constants.RETURN_STATUS_FAILURE;
		}
		return Constants.RETURN_STATUS_FAILURE;
	}
}
