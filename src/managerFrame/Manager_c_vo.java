package managerFrame;

import java.util.Date;

public class Manager_c_vo {
	private int cno;
	private String c_phone;
	private String c_password;
	private int c_total_minutes;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getC_phone() {
		return c_phone;
	}
	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}
	public String getC_password() {
		return c_password;
	}
	public void setC_password(String c_password) {
		this.c_password = c_password;
	}
	public int getC_total_minutes() {
		return c_total_minutes;
	}
	public void setC_total_minutes(int c_total_minutes) {
		this.c_total_minutes = c_total_minutes;
	}
	public Manager_c_vo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager_c_vo(int cno, String c_phone, String c_password, int c_total_minutes) {
		super();
		this.cno = cno;
		this.c_phone = c_phone;
		this.c_password = c_password;
		this.c_total_minutes = c_total_minutes;
	}
    
}
