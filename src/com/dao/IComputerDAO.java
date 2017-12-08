package com.dao;

import java.util.List;

import com.bean.Computer;

public interface IComputerDAO {
	Computer find(long computerNo);
	Computer findAvailable();
	List<Computer> list();
	boolean updateStatus(long computerNo,String status);
	List<Computer> listAvailable(int start,int count);
	List<Computer> list(int start,int count);
	List<Computer> list(int start,int count,String filter,String filterValue);
}
