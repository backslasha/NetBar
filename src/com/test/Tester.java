package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.utils.Counter;

public class Tester {

	@Test
	public void testCounter() {
		assert(Counter.expense(61).floatValue()==7.5);
	}
	
}
