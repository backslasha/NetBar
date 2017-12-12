package com.bean;

import java.sql.Time;
import java.sql.Timestamp;

public class BrokenRecord {
	long brokenRecordNo,computerNo;
	Timestamp timeRepaired,timeBroken;
	int repairedCost;
	String brokenComment;
	public long getBrokenRecordNo() {
		return brokenRecordNo;
	}
	public void setBrokenRecordNo(long brokenRecordNo) {
		this.brokenRecordNo = brokenRecordNo;
	}
	public long getComputerNo() {
		return computerNo;
	}
	public void setComputerNo(long computerNo) {
		this.computerNo = computerNo;
	}
	public Timestamp getTimeRepaired() {
		return timeRepaired;
	}
	public void setTimeRepaired(Timestamp timeRepaired) {
		this.timeRepaired = timeRepaired;
	}
	public Timestamp getTimeBroken() {
		return timeBroken;
	}
	public void setTimeBroken(Timestamp timeBroken) {
		this.timeBroken = timeBroken;
	}
	public int getRepairedCost() {
		return repairedCost;
	}
	public void setRepairedCost(int repairedCost) {
		this.repairedCost = repairedCost;
	}
	public String getBrokenComment() {
		return brokenComment;
	}
	public void setBrokenComment(String brokenComment) {
		this.brokenComment = brokenComment;
	}
	@Override
	public String toString() {
		return "BrokenRecord [brokenRecordNo=" + brokenRecordNo + ", computerNo=" + computerNo + ", timeRepaired="
				+ timeRepaired + ", timeBroken=" + timeBroken + ", repairedCost=" + repairedCost + ", brokenComment="
				+ brokenComment + "]";
	}
	public BrokenRecord(long computerNo, Timestamp timeBroken, String brokenComment) {
		super();
		this.computerNo = computerNo;
		this.timeBroken = timeBroken;
		this.brokenComment = brokenComment;
	}
	public BrokenRecord() {
		super();
	}
	
}
