package com.ajax;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Member;
import com.dao.impl.MemberDAOImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MembersAJAX implements AJAX {
	private static final String EMPTY_RESULT = "没有更多数据啦！";
	private static final String UPDATE_FAIL = "更新失败！注意字符数据不能超过 225 字符";
	private static final String INSERT_FAIL = "新增失败！可能会员帐号已存在或者数据不合理";

	@Override
	public String ajax(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		switch (type) {
		case "query":
			return handleQuery(request, response);
		case "update":
			return handleUpdate(request, response);
		case "insert":
			return handleInsert(request, response);
		default:
			return EMPTY_RESULT;
		}

	}

	private String handleInsert(HttpServletRequest request, HttpServletResponse response) {
		MemberDAOImpl dao = new MemberDAOImpl();
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String memberNo = request.getParameter("memberNo");
		String password = request.getParameter("password");
		String funds = request.getParameter("funds");
		try {
			name = URLDecoder.decode(name, "UTF-8");
			gender = URLDecoder.decode(gender, "UTF-8");
			password = URLDecoder.decode(password, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Member member = new Member(
				Long.parseLong(memberNo),
				password, name, gender, 
				Integer.parseInt(age), 
				new BigDecimal(Double.parseDouble(funds))
				);
		
		if(dao.add(member)) {
			return "{\"result\":\"" + memberNo + "\"}";
		}
		return INSERT_FAIL;
	}

	private String handleUpdate(HttpServletRequest request, HttpServletResponse response) {
		MemberDAOImpl dao = new MemberDAOImpl();
		String colomnName = request.getParameter("columnName");
		String memberNo = request.getParameter("memberNo");
		String value = null;
		try {
			value = URLDecoder.decode(request.getParameter(colomnName), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		if (colomnName.equals("status")) {
			if (dao.updateStatus(Long.parseLong(memberNo), value)) {
				return "{\"result\":\"" + value + "\"}";
			}
		} else if (colomnName.equals("gender")) {
			if (dao.updateGender(Long.parseLong(memberNo), value)) {
				return "{\"result\":\"" + value + "\"}";
			}
		} else if (colomnName.equals("funds")) {
			if (dao.updateFunds(Long.parseLong(memberNo), new BigDecimal(Double.parseDouble(value)))) {
				return "{\"result\":\"" + value + "\"}";
			}
		}else if (colomnName.equals("password")) {
			if (dao.updatePassword(Long.parseLong(memberNo), value)) {
				return "{\"result\":\"" + value + "\"}";
			}
		}
		return UPDATE_FAIL;
	}

	private String handleQuery(HttpServletRequest request, HttpServletResponse response) {
		MemberDAOImpl dao = new MemberDAOImpl();
		List<Member> members;

		String start = request.getParameter("start");
		String end = request.getParameter("end");

		if (start.contains("-")) {
			return EMPTY_RESULT;
		}

		String filter = request.getParameter("filter");
		String filterValue = request.getParameter("filterValue");

		// 没有指定范围，则查询全部
		if (start == null || end == null) {
			members = dao.list();
		}
		// 若指定了范围，则查询范围内条目
		else {
			// 若给出了过滤条件，则加上过滤条件
			if (filter != null && filterValue != null) {
				members = dao.list(Integer.parseInt(start), Integer.parseInt(end), filter, filterValue);
			} else {
				members = dao.list(Integer.parseInt(start), Integer.parseInt(end));
			}
		}

		if (members == null || members.size() == 0) {
			return EMPTY_RESULT;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss");

		JsonObject jsonObjectRoot = new JsonObject();
		JsonArray jsonArrayRoot = new JsonArray();
		members.forEach(member -> {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("会员帐号", member.getMemberNo());
			jsonObject.addProperty("姓名", member.getName());
			jsonObject.addProperty("状态", member.getStatus());
			jsonObject.addProperty("性别", member.getGender());
			jsonObject.addProperty("年龄", member.getAge());
			jsonObject.addProperty("帐号余额", member.getFunds());
			jsonObject.addProperty("密码", member.getPassword());
			Timestamp date = member.getLastLoginDate();
			jsonObject.addProperty("最近登陆时间",date==null?"无" :df.format(date));
			jsonArrayRoot.add(jsonObject);
		});
		jsonObjectRoot.add("members", jsonArrayRoot);
		return jsonObjectRoot.toString();
	}

	private String format(Time duration) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(duration);
		String hours = calendar.get(Calendar.HOUR) + "";
		String minutes = calendar.get(Calendar.MINUTE) + "";
		String seconds = calendar.get(Calendar.SECOND) + "";
		if (hours.length() == 1) {
			hours = "0" + hours;
		}
		if (minutes.length() == 1) {
			minutes = "0" + minutes;
		}
		if (seconds.length() == 1) {
			seconds = "0" + seconds;
		}
		return hours + ":" + minutes + ":" + seconds;
	}

	public static void main(String[] args) {
		new MembersAJAX().ajax(null, null);
	}

}
