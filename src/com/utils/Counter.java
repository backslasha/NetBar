package com.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Counter {
	public static BigDecimal expense(int minutes) {
		int unit = minutes/30;
		if(minutes%30!=0) {
			unit++;
		}
		return new BigDecimal(1.5 *unit); 
	}
}
