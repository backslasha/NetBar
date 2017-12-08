package com.dao;

public class DAOHelper {
	public static String generateSql(String table, String filter,String filterValue,String dateType,int start,int count) {
		StringBuilder sqlBuilder = new StringBuilder("select * from "+ table +" where ");
		
		
		if (filter.equals(dateType)) {
			String last = filterValue.charAt(filterValue.length()-1)+"";
			String tomorrow =  filterValue.substring(0, filterValue.length() - 1)+(Integer.parseInt(last) + 1);
			sqlBuilder = sqlBuilder.append(dateType+" between '" + filterValue + "' and '" + tomorrow + "' ");
		}else {
			if (filterValue.matches(".*[^0-9].*")) {
				filterValue = "'"+filterValue+"'";
			}
			sqlBuilder = sqlBuilder.append(filter + "=" + filterValue);
		}
		sqlBuilder = sqlBuilder.append(" limit " + start + "," + count); 
		String sql = sqlBuilder.toString();
		System.out.println(sql);
		return sql;
		
	}
	
	public static void main(String[] args) {
		System.out.println(DAOHelper.generateSql("Member", "memberNo", "3115005080", "lasatLoginDate", 0, 10));
	}
}
