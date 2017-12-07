package com.ajax;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Consumption;
import com.dao.impl.ConsumptionDAOImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ConsumptionsAction implements Action {

	private static final String EMPTY_RESULT = "{consumptions:[]}";
	@Override
	public String ajax(HttpServletRequest request, HttpServletResponse response) {

		ConsumptionDAOImpl dao = new ConsumptionDAOImpl ();
		List<Consumption> members; 
		
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		if(start.contains("-")) {
			return EMPTY_RESULT;
		}
		
		String filter = request.getParameter("filter");
		String filterValue = request.getParameter("filterValue");
		
		// 没有指定范围，则查询全部
		if(start==null||end==null) {
			members = dao.list();
		}
		// 若指定了范围，则查询范围内条目
		else {
			// 若给出了过滤条件，则加上过滤条件
			if(filter!=null&&filterValue!=null) {
				members = dao.list(Integer.parseInt(start),Integer.parseInt(end),filter,filterValue);
			}else {
				members = dao.list(Integer.parseInt(start),Integer.parseInt(end));
			}
		}
		
		// 若查询结果为空集，则直接返回空结果
		if(members==null||members.size()==0) {
			return EMPTY_RESULT;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss");
		
		JsonObject jsonObjectRoot = new JsonObject();
		JsonArray jsonArrayRoot = new JsonArray();
		members.forEach(member->{
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("记录编号",member.getConsumptionNo());
			jsonObject.addProperty("电脑编号",member.getComputerNo());
			jsonObject.addProperty("会员帐号",member.getMemberNo());
			jsonObject.addProperty("登陆时间",df.format(member.getTimelogin()));
			jsonObject.addProperty("下机时间",df.format(member.getTimeLogout()));
			jsonObject.addProperty("花费",member.getCost());
			jsonArrayRoot.add(jsonObject);
		});
		jsonObjectRoot.add("consumptions", jsonArrayRoot);
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
		new ConsumptionsAction().ajax(null, null);
	}

}
