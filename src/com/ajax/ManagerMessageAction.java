package com.ajax;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ComputerUseRecord;
import com.bean.Manager;
import com.dao.impl.ComputerUsesDAOImpl;
import com.dao.impl.ManagerDAOImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ManagerMessageAction implements Action {

	@Override
	public String ajax(HttpServletRequest request, HttpServletResponse response) {

		long managerNo = Long.parseLong(request.getParameter("managerNo"));
		ManagerDAOImpl dao = new ManagerDAOImpl();
		Manager manager = dao.query(managerNo);
		if(manager==null) {
			return "{}";
		}else {
			JsonObject jsonObjectRoot = new JsonObject();
			jsonObjectRoot.addProperty("网管工号", manager.getManagerNo());
			jsonObjectRoot.addProperty("姓名", manager.getName());
			jsonObjectRoot.addProperty("性别", manager.getGender());
			jsonObjectRoot.addProperty("年龄", manager.getAge());
			return jsonObjectRoot.toString();
		}
	
	}


	public static void main(String[] args) {
		new ManagerMessageAction().ajax(null, null);
	}

}
