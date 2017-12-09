package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Boss;
import com.bean.Computer;
import com.bean.Manager;
import com.dao.DAOHelper;
import com.dao.IBossDAO;
import com.dao.IComputerDAO;
import com.utils.JDBCUtils;

public class ComputerDAOImpl implements IComputerDAO {
	JDBCUtils utils = JDBCUtils.instance();

	@Override
	public Computer find(long computerNo) {
		String sql = "select * from " + Computer.class.getSimpleName() + " where computerNo=?";
		List<Object> params = new ArrayList<>();
		params.add(computerNo);
		Computer computer = null;
		try {
			computer = (Computer) utils.findSimpleRefResult(sql, params, Computer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public Computer findAvailable() {
		String sql = "select * from " + Computer.class.getSimpleName() + " where status='idle' limit 1";
		Computer computer = null;
		try {
			computer = (Computer) utils.findSimpleRefResult(sql, null, Computer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public List<Computer> list() {
		String sql = "select * from " + Computer.class.getSimpleName();
		List<Computer> computers = null;
		try {
			computers =  utils.findMoreRefResult(sql, null, Computer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computers;
	}
	
	@Override
	public List<Computer> list(int start,int count) {
		String sql = "select * from " + Computer.class.getSimpleName()+" limit "+start+","+count;
		List<Computer> computers = null;
		try {
			computers =  utils.findMoreRefResult(sql, null, Computer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computers;
	}
	
	@Override
	public List<Computer> list(int start,int count,String filter,String filterValue) {
		String sql = DAOHelper.generateSql(Computer.class.getSimpleName(), filter, filterValue, "none", start, count);
		List<Computer> computers = null;
		try {
			computers =  utils.findMoreRefResult(sql, null, Computer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computers;
	}
	 
	@Override
	public List<Computer> listAvailable(int start,int count) {
		String sql = "select * from " + Computer.class.getSimpleName()+"limit "+start+","+count;
		List<Computer> computers = null;
		try {
			computers =  utils.findMoreRefResult(sql, null, Computer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computers;
	}
	
	@Override
	public boolean updateStatus(long computerNo, String status) {
		String sql = "update " + Computer.class.getSimpleName() + " set status='"+status+"' where computerNo="+computerNo;
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateComment(long computerNo, String comment) {
		String sql = "update " + Computer.class.getSimpleName() + " set comment='"+comment+"' where computerNo="+computerNo;
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
