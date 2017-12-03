package com.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * create table Memeber(
    memeberNo bigint(11) not null PRIMARY KEY,
    password char(16) not null ,
    status enum('offline','online') default 'offline',
    name char(14) not null ,
    gender char(2) not null ,
    age smallint(2) not null ,
    funds decimal(5,1) not null ,
    lastLoginDate datetime not null
);
 */
public class Member {
	long memberNo;
	String password ;
	String name;
	String status;
	String gender;
	int age;
	BigDecimal funds;
	Timestamp lastLoginDate;
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", password=" + password + ", name=" + name + ", status=" + status
				+ ", gender=" + gender + ", age=" + age + ", funds=" + funds + ", lastLoginDate=" + lastLoginDate + "]";
	}
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public BigDecimal getFunds() {
		return funds;
	}
	public void setFunds(BigDecimal funds) {
		this.funds = funds;
	}
	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
}
