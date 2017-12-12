package com.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Manager;
import com.bean.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
//		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
//		request.setCharacterEncoding("UTF-8");  //post  get
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//
//		HttpSession session = request.getSession();
//		
//		Member loginedMember = (Member) session.getAttribute("loginedMember");
//		Manager loginedManager = (Manager) session.getAttribute("loginedManager");
//		
//		Member comingMember = (Member) session.getAttribute("member");
//		Manager comingManager = (Manager) session.getAttribute("manager");
//
//		
//		System.out.println("/start/--------------------------------");
//		System.out.println("isNew():" + session.isNew());
//		System.out.println("getId():" + session.getId());
//		System.out.println("loginedMember :"+loginedMember);
//		System.out.println("comingMember  :"+comingMember);
//		System.out.println("-----------------------------------");
//		
//		PrintWriter out = response.getWriter();
//		
//		// 如果已经有会员帐号登陆
//		if(loginedMember!=null) {
//			if(comingMember==null) {
//				String message = "未登陆或者身份过期，请重新登陆";
//				session.setAttribute("message", message);
//				response.sendRedirect("error.jsp");
//				return;
//			
//			}
//		}
//		
//		
//		// 如果已经有网管帐号登陆
//		if(loginedManager!=null) {
//			if(comingManager==null) {
//				String message = "未登陆或者身份过期，请重新登陆";
//				session.setAttribute("message", message);
//				response.sendRedirect("error.jsp");
//				return;
//				
//			}else if(loginedManager.getManagerNo()!=comingManager.getManagerNo()) {
//				String message = "该电脑已经登陆了一个网管：" + loginedManager.getManagerNo()
//				+"<br>"+"每台电脑只能登陆一个网管";
//				session.setAttribute("message", message);
//				response.sendRedirect("error.jsp");
//				return;
//			}
//		}
//		

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
