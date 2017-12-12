package com.bean;

public class Computer {
	long computerNo;
	String status = "idle";
	String comment;
	public Computer() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Computer [computerNO=" + computerNo + ", status=" + status + ", comment=" + comment + "]";
	}
	public long getComputerNo() {
		return computerNo;
	}
	public void setComputerNO(long computerNO) {
		this.computerNo = computerNO;
	}
	public String getStatus() {
		return status;
	}
	public Computer(long computerNo, String status, String comment) {
		super();
		this.computerNo = computerNo;
		this.status = status;
		this.comment = comment;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
