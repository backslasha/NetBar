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

import com.bean.Manager;
import com.dao.impl.ManagerDAOImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ManagersAJAX implements AJAX {
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
		ManagerDAOImpl dao = new ManagerDAOImpl();
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String managerNo = request.getParameter("managerNo");
		String password = request.getParameter("password");
		String salary = request.getParameter("salary");
		try {
			name = URLDecoder.decode(name, "UTF-8");
			gender = URLDecoder.decode(gender, "UTF-8");
			password = URLDecoder.decode(password, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Manager manager = new Manager(
				Long.parseLong(managerNo),
				password, 
				name,
				gender, 
				Integer.parseInt(age),
				Integer.parseInt(salary) 
				);
		if(dao.insert(manager)) {
			return "{\"result\":\"" + managerNo + "\"}";
		}
		return INSERT_FAIL;
	}

	private String handleUpdate(HttpServletRequest request, HttpServletResponse response) {
		ManagerDAOImpl dao = new ManagerDAOImpl();
		String colomnName = request.getParameter("columnName");
		String managerNo = request.getParameter("managerNo");
		String value = null;
		try {
			value = URLDecoder.decode(request.getParameter(colomnName), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		if (colomnName.equals("password")) {
			if (dao.updatePassword(Long.parseLong(managerNo), value)) {
				return "{\"result\":\"" + value + "\"}";
			}
		} else if (colomnName.equals("gender")) {
			if (dao.updateGender(Long.parseLong(managerNo), value)) {
				return "{\"result\":\"" + value + "\"}";
			}
		} else if (colomnName.equals("salary")) {
			if (dao.updateSalary(Long.parseLong(managerNo), Integer.parseInt(value))) {
				return "{\"result\":\"" + value + "\"}";
			}
		}
		return UPDATE_FAIL;
	}

	private String handleQuery(HttpServletRequest request, HttpServletResponse response) {
		ManagerDAOImpl dao = new ManagerDAOImpl();
		List<Manager> managers;

		String start = request.getParameter("start");
		String end = request.getParameter("end");

		if (start.contains("-")) {
			return EMPTY_RESULT;
		}

		String filter = request.getParameter("filter");
		String filterValue = request.getParameter("filterValue");
		try {
			if (filter != null && filterValue != null) {
				System.out.println(filterValue);
				filterValue = URLDecoder.decode(filterValue, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		// 没有指定范围，则查询全部
		if (start == null || end == null) {
			managers = dao.list();
		}
		// 若指定了范围，则查询范围内条目
		else {
			// 若给出了过滤条件，则加上过滤条件
			if (filter != null && filterValue != null) {
				managers = dao.list(Integer.parseInt(start), Integer.parseInt(end), filter, filterValue);
			} else {
				managers = dao.list(Integer.parseInt(start), Integer.parseInt(end));
			}
		}

		if (managers == null || managers.size() == 0) {
			return EMPTY_RESULT;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss");

		JsonObject jsonObjectRoot = new JsonObject();
		JsonArray jsonArrayRoot = new JsonArray();
		managers.forEach(manager -> {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("工号", manager.getManagerNo());
			jsonObject.addProperty("密码", manager.getPassword());
			jsonObject.addProperty("工资", manager.getSalary());
			jsonObject.addProperty("姓名", manager.getName());
			jsonObject.addProperty("状态", manager.getStatus());
			jsonObject.addProperty("性别", manager.getGender());
			jsonObject.addProperty("年龄", manager.getAge());
			jsonArrayRoot.add(jsonObject);
		});
		jsonObjectRoot.add("managers", jsonArrayRoot);
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
		new ManagersAJAX().ajax(null, null);
	}

}
