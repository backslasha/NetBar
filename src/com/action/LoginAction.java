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
		switch(userType) {
		case "member":
			return handleMemberLogin(request,response);
		case "manager":
			return handleManagerLogin(request,response);
		case "boss":
			return handleBossLogin(request,response);
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
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
		Member member = memberDAOImpl.query(Long.parseLong(memberNo));
		Computer computer = computerDAOImpl.findAvailable();
		// 没有空闲的主机
		if(computer==null) {
			request.getSession().setAttribute("message", "没有空闲的主机");
			return "error.jsp";
		}
		try {
			if (member != null) {
				if (member.getPassword().equals(password)) {
					// 如果重数据库中查询到空闲的电脑，则成功开机，即将跳转到登陆成功界面
					if(computerDAOImpl.updateStatus(computer.getComputerNo(), "busy")
							&& memberDAOImpl.updateStatus(Long.parseLong(memberNo), "online")) {
						memberDAOImpl.updateLastLoginTime(Long.parseLong(memberNo));
						member.setLastLoginDate(new Timestamp(new Date().getTime()));
						request.getSession().setAttribute("computer", computer);
						request.getSession().setAttribute("member", member);
						return "member.jsp";
					}
				} else {
					request.getSession().setAttribute("message", "password incorect!");
					return "login.jsp";
				}

			} else {
				request.getSession().setAttribute("message", "member is not exists!");
				return "login.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "error.jsp";
	}

}
