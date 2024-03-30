package com.sist.vo;

public class RoomTimeVO {
	private int rno;
	private int min_left;
	private int sec_left;
	public RoomTimeVO() {
		
	
	}
	public RoomTimeVO(int rno, int min_left, int sec_left) {
		super();
		this.rno = rno;
		this.min_left = min_left;
		this.sec_left = sec_left;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getMin_left() {
		return min_left;
	}
	public void setMin_left(int min_left) {
		this.min_left = min_left;
	}
	public int getSec_left() {
		return sec_left;
	}
	public void setSec_left(int sec_left) {
		this.sec_left = sec_left;
	}
	
	
}

