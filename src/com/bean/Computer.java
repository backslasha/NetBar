package com.bean;

public class Computer {
	long computerNo;
	String status = "idle";
	String comment;
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
