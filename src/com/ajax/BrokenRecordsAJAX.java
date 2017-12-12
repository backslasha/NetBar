package com.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.BrokenRecord;
import com.bean.Computer;
import com.dao.impl.BrokenRecordDAOImpl;
import com.dao.impl.ComputerDAOImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.utils.WebUtils;

public class BrokenRecordsAJAX implements AJAX {
	private static final String EMPTY_RESULT = "{borkenRecords:[]}";
	private static final String UPDATE_FAIL = "更新失败！";
	private static final String INSERT_FAIL = "新增失败！";
	@Override
	public String ajax(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		switch(type) {
		case "query":
			return handleQuery(request,response);
		case "update":
			return handleUpdate(request,response);
		case "insert":
			return handleInsert(request, response);
		default :
			return EMPTY_RESULT;
		}
	}

	private String handleInsert(HttpServletRequest request, HttpServletResponse response) {
		BrokenRecordDAOImpl dao = new BrokenRecordDAOImpl();
		String computerNo = request.getParameter("computerNo");
		String timeBroken= request.getParameter("timeBroken");
		String brokenComment = request.getParameter("brokenComment");
		try {
			brokenComment = URLDecoder.decode(brokenComment, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		BrokenRecord brokenRecord = new BrokenRecord(Long.parseLong(computerNo),new Timestamp( Timestamp.parse(WebUtils.standard(timeBroken))), brokenComment);
		if(dao.insert(brokenRecord)) {
			return "{\"result\":\"" + brokenRecord.getBrokenRecordNo() + "\"}";
		}
		return INSERT_FAIL;
	}

	private String handleUpdate(HttpServletRequest request, HttpServletResponse response) {
		BrokenRecordDAOImpl dao = new BrokenRecordDAOImpl();
		String colomnName= request.getParameter("columnName");
		String value = request.getParameter(colomnName);
		String brokenRecordNo = request.getParameter("brokenRecordNo");
		try {
			value = URLDecoder.decode(request.getParameter(colomnName),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(colomnName.equals("brokenComment")) {
			if(dao.updateBrokenComment(Long.parseLong(brokenRecordNo), value)) {
				return "{\"result\":\""+value+"\"}";
			}
		}else if(colomnName.equals("timeRepaired")) {
			if(dao.updateTimeRepaired(Long.parseLong(brokenRecordNo), value)) {
				return "{\"result\":\""+value+"\"}";
			}
		}else if(colomnName.equals("timeRepaired")) {
			if(dao.updateRepairedCost(Long.parseLong(brokenRecordNo), Integer.parseInt(value))) {
				return "{\"result\":\""+value+"\"}";
			}
		}
		return UPDATE_FAIL;
	}

	private String handleQuery(HttpServletRequest request, HttpServletResponse response) {
		BrokenRecordDAOImpl dao = new BrokenRecordDAOImpl();
		List<BrokenRecord> brokenRecords;
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		if (start.contains("-")) {
			return EMPTY_RESULT;
		}

		String filter = request.getParameter("filter");
		String filterValue = request.getParameter("filterValue");

		// 没有指定范围，则查询全部
		if (start == null || end == null) {
			brokenRecords = dao.list();
		}
		// 若指定了范围，则查询范围内条目
		else {
			{
				// 若给出了过滤条件，则加上过滤条件
				if (filter != null && filterValue != null) {
					brokenRecords = dao.list(Integer.parseInt(start), Integer.parseInt(end), filter, filterValue);
				} else {
					brokenRecords = dao.list(Integer.parseInt(start), Integer.parseInt(end));
				}
			}
		}

		if (brokenRecords == null || brokenRecords.size() == 0) {
			return EMPTY_RESULT;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss");

		JsonObject jsonObjectRoot = new JsonObject();
		JsonArray jsonArrayRoot = new JsonArray();
		brokenRecords.forEach(brokenRecord -> {
			JsonObject jsonObject = new JsonObject();
			Timestamp timeRepaired = brokenRecord.getTimeRepaired();
			Timestamp timeBroken = brokenRecord.getTimeBroken();
			
			jsonObject.addProperty("brokenRecordNo", brokenRecord.getBrokenRecordNo());
			jsonObject.addProperty("computerNo", brokenRecord.getComputerNo());
			jsonObject.addProperty("timeBroken", timeBroken!=null?df.format(timeBroken):"无");
			jsonObject.addProperty("timeRepaired",timeRepaired!=null?df.format(timeRepaired):"无");
			jsonObject.addProperty("brokenComment", brokenRecord.getBrokenComment());
			jsonArrayRoot.add(jsonObject);
		});
		jsonObjectRoot.add("brokenRecords", jsonArrayRoot);

		return jsonObjectRoot.toString();

	}


	public static void main(String[] args) {
		new BrokenRecordsAJAX().ajax(null, null);
	}

}
