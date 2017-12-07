package com.action;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Boss;
import com.bean.Computer;
import com.bean.Manager;
import com.bean.Member;
import com.dao.impl.BossDAOImpl;
import com.dao.impl.ComputerDAOImpl;
import com.dao.impl.ManagerDAOImpl;
import com.dao.impl.MemberDAOImpl;

public class LoginAction implements Action {

	@Override
	public String url(HttpServletRequest request, HttpServletResponse response) {
		String userType = request.getParameter("userType");
		switch (userType) {
		case "member":
			return handleMemberLogin(request, response);
		case "manager":
			return handleManagerLogin(request, response);
		case "boss":
			return handleBossLogin(request, response);
		default:
			return "error.jsp";
		}
	}

	private String handleBossLogin(HttpServletRequest request, HttpServletResponse response) {
		String userNo = request.getParameter("userNo");
		String password = request.getParameter("password");
		Boss boss = new BossDAOImpl().query(userNo);
		try {
			if (boss != null) {
				if (boss.getPassword().equals(password)) {
					request.getSession().setAttribute("boss", boss);
					return "boss.jsp";
				} else {
					request.getSession().setAttribute("message", "password incorect!");
					return "login.jsp";
				}

			} else {
				request.getSession().setAttribute("message", "boss is not exists!");
				return "login.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "error.jsp";
	}

	private String handleManagerLogin(HttpServletRequest request, HttpServletResponse response) {
		String userNo = request.getParameter("userNo");
		String password = request.getParameter("password");
		Manager manager = new ManagerDAOImpl().query(Long.parseLong(userNo));
		try {
			if (manager != null) {
				if (manager.getPassword().equals(password)) {
					request.getSession().setAttribute("manager", manager);
					return "manager.jsp";
				} else {
					request.getSession().setAttribute("message", "password incorect!");
					return "login.jsp";
				}

			} else {
				request.getSession().setAttribute("message", "manager is not exists!");
				return "login.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "error.jsp";
	}

	private String handleMemberLogin(HttpServletRequest request, HttpServletResponse response) {
		String memberNo = request.getParameter("userNo");
		String password = request.getParameter("password");
		MemberDAOImpl memberDAOImpl = new MemberDAOImpl();
		Member member = memberDAOImpl.query(Long.parseLong(memberNo));
		// 找不到用户帐号
		if (member == null) {
			request.getSession().setAttribute("message", "该用户帐号不存在！");
			return "login.jsp";
		}

		// 密码不对
		if (!member.getPassword().equals(password)) {
			request.getSession().setAttribute("message", "密码不正确！");
			return "login.jsp";
		}

		// 用户帐号已经在线
		if (member.getStatus().equals("online")) {
			request.getSession().setAttribute("message", "该帐号已经在另一台主机登陆，若非本人行为，请联系本吧网管！");
			return "error.jsp";
		}
		
		
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
		Computer computer = computerDAOImpl.findAvailable();
		// 没有空闲的主机
		if (computer == null) {
			request.getSession().setAttribute("message", "不好意思，已经没有空闲的主机了！");
			return "error.jsp";
		}
		
		// 如果重数据库中查询到空闲的电脑，则成功开机，即将跳转到登陆成功界面
		if (computerDAOImpl.updateStatus(computer.getComputerNo(), "busy")
					&& memberDAOImpl.updateStatus(Long.parseLong(memberNo), "online")) {
				
				// 用户上线
				memberDAOImpl.updateLastLoginTime(Long.parseLong(memberNo));
				// 用户最近登陆时间改变
				member.setLastLoginDate(new Timestamp(new Date().getTime()));
				
				request.getSession().setAttribute("computer", computer);
				request.getSession().setAttribute("member", member);
				return "member.jsp";
		} 

		request.getSession().setAttribute("message", "登陆失败，未知错误！");
		return "error.jsp";
	}

}
