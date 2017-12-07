package com.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.bean.Boss;
import com.bean.Consumption;
import com.bean.Manager;
import com.bean.Member;
import com.dao.IBossDAO;
import com.dao.IComputerDAO;
import com.dao.IConsumptionDAO;
import com.utils.JDBCUtils;

public class ConsumptionDAOImpl implements IConsumptionDAO {
	JDBCUtils utils = JDBCUtils.instance();

	@Override
	public Consumption find(long ConsumptionNo) {
		// TODO Auto-generated method stub
		String sql = "select * from " + Boss.class.getSimpleName() + " where ConsumptionNo=?";
		List<Object> params = new ArrayList<>();
		params.add(ConsumptionNo);
		Consumption consumption = null;
		try {
			consumption = (Consumption) utils.findSimpleRefResult(sql, params, Consumption.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return consumption;
	}

	@Override
	public boolean insert(Consumption consumption) {
		String sql = "insert into Consumption(computerNo,memberNo,timeLogin,timeLogout,cost) "
				+ "values(?, ?, ? , ?,?)";
		List<Object> params = new ArrayList<>();
		params.add(consumption.getComputerNo());
		params.add(consumption.getMemberNo());
		params.add(consumption.getTimelogin());
		params.add(consumption.getTimeLogout());
		params.add(consumption.getCost());
		try {
			return utils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Consumption> list() {
		// TODO Auto-generated method stub
		String sql = "select * from Consumption";
		List<Consumption> consumptions = null;
		try {
			consumptions = utils.findMoreRefResult(sql, null, Consumption.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return consumptions;
	}

	@Override
	public List<Consumption> list(int start, int count) {
		// TODO Auto-generated method stub
		String sql = "select * from Consumption" + " limit " + start + "," + count;
		;
		List<Consumption> consumptions = null;
		try {
			consumptions = utils.findMoreRefResult(sql, null, Consumption.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return consumptions;
	}

	@Override
	public List<Consumption> list(int start, int count, String filter, String filterValue) {
		// TODO Auto-generated method stub
		String sql = "select * from Consumption where ";
		if (filter.equals("date")) {
			String last = filterValue.charAt(filterValue.length()-1)+"";
			String tomorrow =  filterValue.substring(0, filterValue.length() - 1)+(Integer.parseInt(last) + 1);
			sql = sql + "timeLogin between '" + filterValue + "' and '" + tomorrow + "' ";
		} else {
			sql = sql + filter + "=" + filterValue;
		}
		sql = sql + " limit " + start + "," + count;
		System.out.println(sql);
		List<Consumption> consumptions = null;
		try {
			consumptions = utils.findMoreRefResult(sql, null, Consumption.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return consumptions;
	}

	public static void main(String[] args) {
		ConsumptionDAOImpl dao = new ConsumptionDAOImpl();
		dao.list(3, 4, "memberNo", "3115005078").forEach(value -> {
			System.out.println(value);
		});
		System.out.println();
		dao.list(3, 4, "memberNo", "3115005080").forEach(value -> {
			System.out.println(value);
		});
		System.out.println();
		dao.list(3, 4, "computerNo", "3115005080").forEach(value -> {
			System.out.println(value);
		});
		System.out.println();
		dao.list(3, 4, "computerNo", "8080000").forEach(value -> {
			System.out.println(value);
		});
		System.out.println();
		dao.list(3, 4, "date", "2017-12-006").forEach(value -> {
			System.out.println(value);
		});
		System.out.println();
		dao.list(3, 4, "date", "2017-12-08").forEach(value -> {
			System.out.println(value);
		});
		System.out.println();
		JDBCUtils.instance().releaseConn();
	}

}
