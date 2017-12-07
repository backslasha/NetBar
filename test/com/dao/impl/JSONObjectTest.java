package com.dao.impl;

import java.util.List;

import org.junit.Test;

import com.bean.ComputerUseRecord;
import com.utils.JDBCUtils;

import junit.framework.TestCase;

public class JSONObjectTest extends TestCase{
	@Test
	public void testJSON() {
		ComputerUsesDAOImpl dao = new ComputerUsesDAOImpl();
		List<ComputerUseRecord> records = dao.list();
		assertNotNull(records);
		int cols = 4;
		int rows = records.size();
		JDBCUtils.instance().releaseConn();
	}
}
