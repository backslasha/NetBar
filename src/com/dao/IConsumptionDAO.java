package com.dao;

import com.bean.Consumption;

public interface IConsumptionDAO {
	Consumption find(long ConsumptionNo);
	boolean insert(Consumption consumption);
}
