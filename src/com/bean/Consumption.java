package com.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Consumption {
	long consumptionNo;
	long computerNo;
	long memberNo;
	Timestamp timelogin;
	Timestamp timeLogout;
	BigDecimal cost;
	@Override
	public String toString() {
		return "Consumption [consumptionNo=" + consumptionNo + ", computerNo=" + computerNo + ", memberNo=" + memberNo
				+ ", timelogin=" + timelogin + ", timeLogout=" + timeLogout + ", cost=" + cost + "]";
	}
	public long getConsumptionNo() {
		return consumptionNo;
	}
	public void setConsumptionNo(long consumptionNo) {
		this.consumptionNo = consumptionNo;
	}
	public long getComputerNo() {
		return computerNo;
	}
	public void setComputerNo(long computerNo) {
		this.computerNo = computerNo;
	}
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
	public Date getTimelogin() {
		return timelogin;
	}
	public void setTimelogin(Timestamp timelogin) {
		this.timelogin = timelogin;
	}
	public Date getTimeLogout() {
		return timeLogout;
	}
	public void setTimeLogout(Timestamp timeLogout) {
		this.timeLogout = timeLogout;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}
