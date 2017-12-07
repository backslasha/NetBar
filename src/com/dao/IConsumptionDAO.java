package com.dao;

import java.util.List;

import com.bean.Consumption;

public interface IConsumptionDAO {
	Consumption find(long ConsumptionNo);
	boolean insert(Consumption consumption);
	List<Consumption> list();
	List<Consumption> list(int start,int count);
	List<Consumption> list(int start,int count,String filter,String filterValue);
}
