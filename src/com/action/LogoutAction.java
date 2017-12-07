package com.action;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Boss;
import com.bean.Consumption;
import com.bean.Manager;
import com.bean.Member;
import com.dao.impl.BossDAOImpl;
import com.dao.impl.ComputerDAOImpl;
import com.dao.impl.ConsumptionDAOImpl;
import com.dao.impl.ManagerDAOImpl;
import com.dao.impl.MemberDAOImpl;
import com.utils.Counter;

public class LogoutAction implements Action {

	@Override
	public String url(HttpServletRequest request, HttpServletResponse response) {
		String userType = request.getParameter("userType");
		switch(userType) {
		case "member":
			return handleMemberLogout(request,response);
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

	private String handleMemberLogout(HttpServletRequest request, HttpServletResponse response) {
		String memberNo = request.getParameter("memberNo");
		String computerNo = request.getParameter("computerNo");
		
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
		computerDAOImpl.updateStatus(Long.parseLong(computerNo), "idle");
		
		// update funds and status
		MemberDAOImpl memberDAOImpl = new MemberDAOImpl();
		Member member = memberDAOImpl.query(Long.parseLong(memberNo));
		// 用户帐号已经下线
		if(member.getStatus().equals("offline")) {
			request.getSession().setAttribute("message", "扣费失败，该帐号已经结帐下线！");
			return "error.jsp";
		}
		int minutes = (int) (Calendar.getInstance().getTimeInMillis()-member.getLastLoginDate().getTime())/1000/60;
		if(minutes==0)minutes++;
		member.setFunds(member.getFunds().subtract(Counter.expense(minutes)));
		member.setStatus("offline");
		memberDAOImpl.update(Long.parseLong(memberNo), member);

		ConsumptionDAOImpl consumptionDAOImpl = new ConsumptionDAOImpl();
		Consumption consumption = new Consumption();
		consumption.setComputerNo(Long.parseLong(computerNo));
		consumption.setMemberNo(Long.parseLong(memberNo));
		consumption.setTimelogin(member.getLastLoginDate());
		consumption.setTimeLogout(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		consumption.setCost(Counter.expense(minutes));
		
		request.getSession().invalidate();
		
		if(consumptionDAOImpl.insert(consumption)) {
			System.out.println("insert a consumption success.");
		}else {
			System.out.println("insert a consumption fail.");
		}
		// insert (ConsumptionNo | computerNo | memberNo | timeLogin  | timeLogout | cost)
		return "login.jsp";
	}

}
