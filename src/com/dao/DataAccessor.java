package com.dao;

import java.util.List;

import com.bean.Member;
import com.utils.JDBCUtils;

public class DataAccessor {

	public static void main(String[] args) {
		JDBCUtils utils =JDBCUtils.instance();

		String sql = "select * from MEMBER;";
		try {
			System.out.println(utils.findSimpleRefResult(sql, null, Member.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
