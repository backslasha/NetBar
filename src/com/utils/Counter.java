package com.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Counter {
	public static BigDecimal expense(int minutes) {
		return new BigDecimal((2.5 * Math.ceil(minutes/30))); 
	}
	
}
