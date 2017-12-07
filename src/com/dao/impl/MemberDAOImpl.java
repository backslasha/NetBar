package com.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.Member;
import com.dao.IMemberDAO;
import com.utils.JDBCUtils;

public class MemberDAOImpl implements IMemberDAO {

	JDBCUtils utils = JDBCUtils.instance();

	@Override
	public Member query(long memberNo) {
		// TODO Auto-generated method stub
		String sql = "select * from Member where memberNo=?";
		List<Object> params = new ArrayList<>();
		params.add(memberNo);
		Member member = null;
		try {
			member = utils.findSimpleRefResult(sql, params, Member.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		String sql = "select * from Member";
		List<Member> members = null;
		try {
			members = utils.findMoreRefResult(sql, null, Member.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return members;
	}
	@Override
	public List<Member> list(int start,int count) {
		// TODO Auto-generated method stub
		String sql = "select * from Member"+" limit "+start+","+count;;
		List<Member> members = null;
		try {
			members = utils.findMoreRefResult(sql, null, Member.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return members;
	}
	@Override
	public List<Member> list(int start,int count, String filter, String filterValue) {
		String sql = "select * from Member where ";
		if (filter.equals("date")) {
			String last = filterValue.charAt(filterValue.length()-1)+"";
			String tomorrow =  filterValue.substring(0, filterValue.length() - 1)+(Integer.parseInt(last) + 1);
			sql = sql + "lastLoginDate between '" + filterValue + "' and '" + tomorrow + "' ";
		} else {
			sql = sql + filter + "=" + filterValue;
		}
		sql = sql + " limit " + start + "," + count;
		System.out.println(sql);
		List<Member> members = null;
		try {
			members = utils.findMoreRefResult(sql, null, Member.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return members;
	}

	public static void main(String[] args) {
		List<Member> members = new MemberDAOImpl().list();
		members.forEach(member -> {
			System.out.println(member);
		});
	}

	@Override
	public boolean add(Member member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long memberNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(long memberNo, Member member) {
		// TODO Auto-generated method stub
		String sql = "update Member set "
				+ "password='" + member.getPassword() + "'"
				+ ", name='" + member.getName()+ "'"
				+ ", status='" + member.getStatus()+ "'"
				+ ", gender='" + member.getGender()+ "'"
				+ ", age='" + member.getAge()+ "'"
				+ ", funds='" + member.getFunds()+ "'"
				+ ", lastLoginDate='"+ member.getLastLoginDate()+ "'"
				+ " where memberNo='" + memberNo + "'";
		System.out.println(sql);
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateFunds(long memberNo, BigDecimal funds) {
		// TODO Auto-generated method stub
		String sql = "update " + Member.class.getSimpleName() + " set funds='" + funds + "' where memberNo='" + memberNo
				+ "'";
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member query(String memberName) {
		// TODO Auto-generated method stub
		String sql = "select * from Member where name=?";
		List<Object> params = new ArrayList<>();
		params.add(memberName);
		Member member = null;
		try {
			member = utils.findSimpleRefResult(sql, params, Member.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public boolean updateStatus(long memberNo, String status) {
		// TODO Auto-generated method stub
		String sql = "update " + Member.class.getSimpleName() + " set status='" + status + "' where memberNo='"
				+ memberNo + "'";
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateLastLoginTime(long memberNo) {
		// TODO Auto-generated method stub

		String sql = "update " + Member.class.getSimpleName() + " set lastLoginDate='"
				+ new Timestamp(new Date().getTime()) + "' where memberNo='" + memberNo
				+ "'";
		System.out.println(sql);
		try {
			return utils.updateByPreparedStatement(sql, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
