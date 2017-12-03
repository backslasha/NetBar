package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Manager;
import com.dao.IManagerDAO;
import com.utils.JDBCUtils;

public class ManagerDAOImpl implements IManagerDAO {
	JDBCUtils utils = JDBCUtils.instance();

	@Override
	public Manager query(long managerNo) {
		// TODO Auto-generated method stub
		String sql = "select * from Manager where managerNo=?" ;
		List<Object> params = new ArrayList<>();
		params.add(managerNo);
		Manager manager = null;
		try {
			manager = (Manager) utils.findSimpleRefResult(sql, params, Manager.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager;
	}

	@Override
	public List<Manager> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long managerNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(long managerNo, Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
