package com.sist.vo;

public class CustomerVO {
	private int cno;
    private String mCode;
    private String Phone;
    private String Password;
    
	public CustomerVO(String phone, String password) {
		super();
		Phone = phone;
		Password = password;
	}
	public CustomerVO(int cno, String mCode, String phone, String password) {
		super();
		this.cno = cno;
		this.mCode = mCode;
		Phone = phone;
		Password = password;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getmCode() {
		return mCode;
	}
	public void setmCode(String mCode) {
		this.mCode = mCode;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public CustomerVO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
