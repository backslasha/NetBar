package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bean.Boss;
import com.bean.ComputerUseRecord;
import com.dao.IComputerUsesDAO;
import com.utils.JDBCUtils;

import junit.framework.TestCase;

public class ComputerUseRecordDAOImplTest extends TestCase {

	ComputerUsesDAOImpl dao = new ComputerUsesDAOImpl();

	@Override
	protected void tearDown() throws Exception {
		JDBCUtils.instance().releaseConn();
	}
	
	@Test
	public void testQeryByComputerNo() {
		assertTrue(
				dao.queryByComputerNo(3115005080L)==null
				||dao.queryByComputerNo(3115005080L).size()==0);
	}
	
	@Test
	public void testQueryByMemberNo(){
		assertFalse(
				dao.queryByMemberNo(3115005080L)==null
				||dao.queryByMemberNo(3115005080L).size()==0);
	}
	
	@Test
	public void testList() {
		assertTrue(
				dao.list()!=null
				&& dao.list().size()!=0);
	}
}
