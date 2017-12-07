package com.bean;

import java.sql.Time;
import java.sql.Timestamp;

public class ComputerUseRecord {
	long memberNo;
	long computerNo;
	Time duration;
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
	public long getComputerNo() {
		return computerNo;
	}
	public void setComputerNo(long computerNo) {
		this.computerNo = computerNo;
	}
	public Time getDuration() {
		return duration;
	}
	public void setDuration(Time duration) {
		this.duration = duration;
	}
	public Timestamp getTimeLogin() {
		return timeLogin;
	}
	public void setTimeLogin(Timestamp timeLogin) {
		this.timeLogin = timeLogin;
	}
	@Override
	public String toString() {
		return "ComputerUsage [memberNo=" + memberNo + ", computerNo=" + computerNo + ", duration=" + duration
				+ ", timeLogin=" + timeLogin + "]";
	}
	Timestamp timeLogin;
}
