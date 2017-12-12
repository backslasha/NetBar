package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Manager;
import com.dao.DAOHelper;
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
	public List<Manager> list() {
		// TODO Auto-generated method stub
				String sql = "select * from Manager";
				List<Manager> managers = null;
				try {
					managers = utils.findMoreRefResult(sql, null, Manager.class);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return managers;
	}

	@Override
	public List<Manager> list(int start,int count) {
		// TODO Auto-generated method stub
		String sql = "select * from Manager"+" limit "+start+","+count;;
		List<Manager> managers = null;
		try {
			managers = utils.findMoreRefResult(sql, null, Manager.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managers;
	}
	
	@Override
	public List<Manager> list(int start,int count, String filter, String filterValue){
		String sql = DAOHelper.generateSql(Manager.class.getSimpleName(), filter, filterValue, "", start, count);
		List<Manager> managers = null;
		try {
			managers = utils.findMoreRefResult(sql, null, Manager.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managers;
	}
	
	@Override
	public boolean insert(Manager manager) {
		String sql = "insert into Manager(name,gender,age,managerNo,password,salary,status) values (?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<>();
		params.add(manager.getName());
		params.add(manager.getGender());
		params.add(manager.getAge());
		params.add(manager.getManagerNo());
		params.add(manager.getPassword());
		params.add(manager.getSalary());
		params.add(manager.getStatus());
		try {
			return utils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public boolean updatePassword(long managerNo,String password) {
		// TODO Auto-generated method stub
		String sql = "update " + Manager.class.getSimpleName() + " set password='" + password + "' where managerNo='" + managerNo
				+ "'";
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updateSalary(long managerNo,int salary) {
		// TODO Auto-generated method stub
				String sql = "update " + Manager.class.getSimpleName() + " set salary='" + salary + "' where managerNo='" + managerNo
						+ "'";
				try {
					return utils.updateByPreparedStatement(sql, null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
	}
	@Override
	public boolean updateGender(long managerNo, String gender) {
		// TODO Auto-generated method stub
		String sql = "update " + Manager.class.getSimpleName() + " set gender='" + gender + "' where managerNo='"
				+ managerNo + "'";
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStatus(long managerNo, String status) {
		// TODO Auto-generated method stub
		String sql = "update " + Manager.class.getSimpleName() + " set status='" + status + "' where managerNo='"
				+ managerNo + "'";
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
