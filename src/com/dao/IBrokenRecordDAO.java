package com.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bean.Computer;
import com.bean.BrokenRecord;
import com.bean.Member;

public interface IBrokenRecordDAO {
	List<BrokenRecord> list();
	List<BrokenRecord> list(int start,int count);
	List<BrokenRecord> list(int start,int count,String filter,String filterValue);
	boolean updateTimeRepaired(long brokenRecordNo,String date);
	boolean updateBrokenComment(long brokenRecordNo,String comment);
	boolean updateRepairedCost(long brokenRecordNo,int cost);
	boolean insert(BrokenRecord brokenRecord);
}
