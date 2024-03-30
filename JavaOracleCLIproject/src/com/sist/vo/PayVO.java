package com.sist.vo;

import java.util.Date;

public class PayVO {
	private int pno;
	private int order_num;
	private String pt_code;
	private int cno;
	private int rno;
	private int fno;
	private int p_minute;
	private int saleprice;
	private int  p_cnt;
	private Date paytime;
	private Date endDate;
	
	
	
	public PayVO(int saleprice, int p_cnt) {
		super();
		this.saleprice = saleprice;
		this.p_cnt = p_cnt;
	}

	public PayVO(int cno, int rno, int p_minute, int saleprice) {
		super();
		this.cno = cno;
		this.rno = rno;
		this.p_minute = p_minute;
		this.saleprice = saleprice;
	}
	
	public PayVO(int pno, int order_num, String pt_code, int cno, int rno, int fno, int p_minute, int saleprice,
			int p_cnt, Date paytime, Date endDate) {
		super();
		this.pno = pno;
		this.order_num = order_num;
		this.pt_code = pt_code;
		this.cno = cno;
		this.rno = rno;
		this.fno = fno;
		this.p_minute = p_minute;
		this.saleprice = saleprice;
		this.p_cnt = p_cnt;
		this.paytime = paytime;
		this.endDate = endDate;
	}



	public int getP_cnt() {
		return p_cnt;
	}



	public void setP_cnt(int p_cnt) {
		this.p_cnt = p_cnt;
	}



	public PayVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getPt_code() {
		return pt_code;
	}
	public void setPt_code(String pt_code) {
		this.pt_code = pt_code;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getP_minute() {
		return p_minute;
	}
	public void setP_minute(int p_minute) {
		this.p_minute = p_minute;
	}
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}

