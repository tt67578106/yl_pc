package com.ylw.web.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域跳转专用
 * @author Nicolas
 *
 */
@WebServlet(urlPatterns={"/redirect"})
public class RedirectServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		resp.sendRedirect(req.getParameter("url"));
	}
}
