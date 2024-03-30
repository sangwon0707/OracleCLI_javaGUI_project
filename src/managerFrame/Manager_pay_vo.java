package managerFrame;

import java.util.Date;

public class Manager_pay_vo {
	private int pno;
	private int order_num;
	private String pt_type;
	private int cno;
    private String f_name;
    private int p_cnt;
    private int rno;
    private int p_minute;
    private String paytime;
    private String endtime;
    private int saleprice;
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
	public String getPt_type() {
		return pt_type;
	}
	public void setPt_type(String pt_type) {
		this.pt_type = pt_type;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public int getP_cnt() {
		return p_cnt;
	}
	public void setP_cnt(int p_cnt) {
		this.p_cnt = p_cnt;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getP_minute() {
		return p_minute;
	}
	public void setP_minute(int p_minute) {
		this.p_minute = p_minute;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public Manager_pay_vo(int pno, int order_num, String pt_type, int cno, String f_name, int p_cnt, int rno,
			int p_minute, String paytime, String endtime, int saleprice) {
		super();
		this.pno = pno;
		this.order_num = order_num;
		this.pt_type = pt_type;
		this.cno = cno;
		this.f_name = f_name;
		this.p_cnt = p_cnt;
		this.rno = rno;
		this.p_minute = p_minute;
		this.paytime = paytime;
		this.endtime = endtime;
		this.saleprice = saleprice;
	}
	public Manager_pay_vo() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
	 
}
