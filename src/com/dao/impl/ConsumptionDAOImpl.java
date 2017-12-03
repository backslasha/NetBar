package com.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.bean.Boss;
import com.bean.Consumption;
import com.bean.Manager;
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

}