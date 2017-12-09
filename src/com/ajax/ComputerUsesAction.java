package com.ajax;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ComputerUseRecord;
import com.dao.impl.ComputerUsesDAOImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ComputerUsesAction implements Action {

	@Override
	public String ajax(HttpServletRequest request, HttpServletResponse response) {

		ComputerUsesDAOImpl dao = new ComputerUsesDAOImpl();
		
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		if(start.contains("-")) {
			return "{"
					+ "records:[]"
					+ "}";
		}
		
		List<ComputerUseRecord> records; 
		if(start==null||end==null) {
			records = dao.list();
		}else {
			records = dao.list(Integer.parseInt(start),Integer.parseInt(end));
		}
		
		if(records==null||records.size()==0) {
			return "{"
					+ "records:[]"
					+ "}";
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss");
		
		JsonObject jsonObjectRoot = new JsonObject();
		JsonArray jsonArrayRoot = new JsonArray();
		records.forEach(record->{
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("电脑编号",record.getComputerNo());
			jsonObject.addProperty("登陆帐号",record.getMemberNo());
			jsonObject.addProperty("使用时长",format(record.getDuration()));
			jsonObject.addProperty("登陆时间",df.format(record.getTimeLogin()));
			jsonArrayRoot.add(jsonObject);
		});
		jsonObjectRoot.add("computerUses", jsonArrayRoot);
		return jsonObjectRoot.toString();
	}

	private String format(Time duration) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(duration);
		String hours = calendar.get(Calendar.HOUR)+"";
		String minutes = calendar.get(Calendar.MINUTE)+"";
		String seconds = calendar.get(Calendar.SECOND)+"";
		if(hours.length()==1) {
			hours = "0"+hours;
		}
		if(minutes.length()==1) {
			minutes = "0"+minutes;
		}
		if(seconds.length()==1) {
			seconds = "0"+seconds;
		}
		return hours+":"+minutes+":"+seconds;
	}

	public static void main(String[] args) {
		new ComputerUsesAction().ajax(null, null);
	}

}
