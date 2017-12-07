package com.ajax;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Computer;
import com.dao.impl.ComputerDAOImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ComputerStatusAction implements Action {

	@Override
	public String ajax(HttpServletRequest request, HttpServletResponse response) {
		ComputerDAOImpl dao = new ComputerDAOImpl();
		
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		if(start.contains("-")) {
			return "{"
					+ "records:[]"
					+ "}";
		}
		
		List<Computer> computers; 
		if(start==null||end==null) {
			computers = dao.list();
		}else {
			computers = dao.list(Integer.parseInt(start),Integer.parseInt(end));
		}
		
		if(computers==null||computers.size()==0) {
			return "{"
					+ "records:[]"
					+ "}";
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss");
		
		JsonObject jsonObjectRoot = new JsonObject();
		JsonArray jsonArrayRoot = new JsonArray();
		computers.forEach(computer->{
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("电脑编号",computer.getComputerNo());
			jsonObject.addProperty("电脑状态",toChinese(computer.getStatus()));
			jsonObject.addProperty("备注",computer.getComment());
			jsonArrayRoot.add(jsonObject);
		});
		jsonObjectRoot.add("computers", jsonArrayRoot);
		
		return jsonObjectRoot.toString();
	
	}


	private String toChinese(String status) {
		if(status.equals("idle")) {
			return "空闲";
		}else if(status.equals("busy")) {
			return "正在使用";
		}else if(status.equals("broken")) {
			return "待维修";
		}else return "未知";
	}


	public static void main(String[] args) {
		new ComputerStatusAction().ajax(null, null);
	}

}
