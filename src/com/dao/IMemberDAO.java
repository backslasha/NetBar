package com.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bean.ComputerUseRecord;
import com.bean.Member;

public interface IMemberDAO {
	
	Member query(long MemberNo);

	Member query(String memberName);

	List<Member> list();
	
	List<Member> list(int start,int count);
	
	List<Member> list(int start,int count, String filter, String filterValue);

	boolean add(Member member);

	boolean delete(long memberNo);

	boolean update(long memberNo, Member member);

	boolean updateFunds(long memberNo,BigDecimal funds);
	
	boolean updateStatus(long memberNo,String status);
	
	boolean updateLastLoginTime(long memberNo);

	boolean updatePassword(String password);
}
