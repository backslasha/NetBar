package com.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bean.ComputerUseRecord;
import com.bean.Member;

public interface IComputerUsesDAO {
	List<ComputerUseRecord> queryByComputerNo(long computerNo);
	List<ComputerUseRecord> queryByMemberNo(long memberNo);
	List<ComputerUseRecord> list();
	List<ComputerUseRecord> list(int start,int count);
}
