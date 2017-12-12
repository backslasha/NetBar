package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.BrokenRecord;
import com.dao.DAOHelper;
import com.dao.IBrokenRecordDAO;
import com.utils.JDBCUtils;

public class BrokenRecordDAOImpl implements IBrokenRecordDAO {
	JDBCUtils utils = JDBCUtils.instance();

	@Override
	public List<BrokenRecord> list() {
		String sql = "select * from " + BrokenRecord.class.getSimpleName();
		List<BrokenRecord> brokenRecords = null;
		try {
			brokenRecords =  utils.findMoreRefResult(sql, null, BrokenRecord.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brokenRecords;
	}

	@Override
	public List<BrokenRecord> list(int start, int count) {
		String sql = "select * from " + BrokenRecord.class.getSimpleName()+" limit "+start+","+count;
		List<BrokenRecord> brokenRecords = null;
		try {
			brokenRecords =  utils.findMoreRefResult(sql, null, BrokenRecord.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brokenRecords;
	}

	@Override
	public List<BrokenRecord> list(int start, int count, String filter, String filterValue) {
		String sql = DAOHelper.generateSql(BrokenRecord.class.getSimpleName(), filter, filterValue, "timeBroken", start, count);
		List<BrokenRecord> brokenRecords = null;
		try {
			brokenRecords =  utils.findMoreRefResult(sql, null, BrokenRecord.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brokenRecords;
	}

	@Override
	public boolean updateTimeRepaired(long brokenRecordNo, String timeRepaired) {
		String sql = "update " + BrokenRecord.class.getSimpleName() + " set timeRepaired='"+timeRepaired+"' where brokenRecordNo="+brokenRecordNo;
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBrokenComment(long brokenRecordNo, String brokenComment) {
		String sql = "update " + BrokenRecord.class.getSimpleName() + " set brokenComment='"+brokenComment+"' where brokenRecordNo="+brokenRecordNo;
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateRepairedCost(long brokenRecordNo, int cost) {
		String sql = "update " + BrokenRecord.class.getSimpleName() + " set cost='"+cost+"' where brokenRecordNo="+brokenRecordNo;
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insert(BrokenRecord brokenRecord) {
		String sql = "insert into BrokenRecord(brokenRecordNo,timeBroken,brokenComment) values (?,?,?)";
		List<Object> params = new ArrayList<>();
		params.add(brokenRecord.getComputerNo());
		params.add(brokenRecord.getTimeBroken());
		params.add(brokenRecord.getBrokenComment());
		try {
			return utils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
