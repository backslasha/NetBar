package com.dao;

import java.util.List;

import com.bean.Computer;

public interface IComputerDAO {
	Computer find(long computerNo);
	Computer findAvailable();
	List<Computer> findAll();
	boolean updateStatus(long computerNo,String status);
}
