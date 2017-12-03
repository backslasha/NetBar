package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Boss;
import com.bean.Manager;
import com.dao.IBossDAO;
import com.utils.JDBCUtils;

public class BossDAOImpl implements IBossDAO {
	JDBCUtils utils = JDBCUtils.instance();

	@Override
	public Boss query(String account) {
		// TODO Auto-generated method stub
		String sql = "select * from " + Boss.class.getSimpleName() + " where account=?";
		List<Object> params = new ArrayList<>();
		params.add(account);
		Boss boss = null;
		try {
			boss = (Boss) utils.findSimpleRefResult(sql, params, Boss.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boss;
	}

}
