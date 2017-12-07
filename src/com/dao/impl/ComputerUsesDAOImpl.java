package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Boss;
import com.bean.ComputerUseRecord;
import com.dao.IComputerUsesDAO;
import com.utils.JDBCUtils;

public class ComputerUsesDAOImpl implements IComputerUsesDAO {
	JDBCUtils utils = JDBCUtils.instance();

	@Override
	public List<ComputerUseRecord> queryByComputerNo(long computerNo) {
		// TODO Auto-generated method stub
		String sql = "select * from " + ComputerUseRecord.class.getSimpleName() + " where computerNo=?";
		List<Object> params = new ArrayList<>();
		params.add(computerNo);
		List<ComputerUseRecord> computerUsages = null;
		try {
			computerUsages = utils.findMoreRefResult(sql, params, ComputerUseRecord.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computerUsages;
	}

	@Override
	public List<ComputerUseRecord> queryByMemberNo(long memberNo) {
		// TODO Auto-generated method stub
		String sql = "select * from " + ComputerUseRecord.class.getSimpleName() + " where memberNo=?";
		List<Object> params = new ArrayList<>();
		params.add(memberNo);
		List<ComputerUseRecord> computerUsages = null;
		try {
			computerUsages = utils.findMoreRefResult(sql, params, ComputerUseRecord.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computerUsages;
	}

	@Override
	public List<ComputerUseRecord> list() {
		// TODO Auto-generated method stub
		String sql = "select * from " + ComputerUseRecord.class.getSimpleName();
		List<ComputerUseRecord> computerUsages = null;
		try {
			computerUsages = utils.findMoreRefResult(sql, null, ComputerUseRecord.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computerUsages;
	}

	@Override
	public List<ComputerUseRecord> list(int start, int count) {
		// TODO Auto-generated method stub
				String sql = "select * from " + ComputerUseRecord.class.getSimpleName()+" limit "+start+","+count;
				List<ComputerUseRecord> computerUsages = null;
				try {
					computerUsages = utils.findMoreRefResult(sql, null, ComputerUseRecord.class);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return computerUsages;
	}

}
