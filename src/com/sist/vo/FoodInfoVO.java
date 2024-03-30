package com.sist.vo;

public class FoodInfoVO {
	private int fno;
	private String f_name ;
	private int f_price;
	
	
	
	public FoodInfoVO(String f_name) {
		super();
		this.f_name = f_name;
	}
	public FoodInfoVO(int fno, String f_name, int f_price) {
		super();
		this.fno = fno;
		this.f_name = f_name;
		this.f_price = f_price;
	}
	public FoodInfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public int getF_price() {
		return f_price;
	}
	public void setF_price(int f_price) {
		this.f_price = f_price;
	}

	
}
