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
	
	// 浏览电脑使用情况的信息，查询； 
	// 浏览管理人员工资信息，对管理人员信息的查询； 
	// 浏览网吧内 电脑的使用情况，及其故障查询； 
	// 对 网吧内会员信息 的查询，修改，添加；   
	// 对网吧内 消费人员 的信息的查询； 

}
