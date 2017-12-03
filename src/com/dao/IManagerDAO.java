package com.dao;

import java.math.BigDecimal;
import java.util.List;

import com.bean.Manager;
import com.bean.Member;

public interface IManagerDAO {
	Manager query(long managerNo);

	List<Manager> queryAll();
	
	boolean add(Manager manager);

	boolean delete(long managerNo);

	boolean update(long managerNo, Manager manager);

	boolean updatePassword(String password);

}
