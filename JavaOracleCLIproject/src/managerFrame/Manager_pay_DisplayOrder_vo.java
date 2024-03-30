package managerFrame;

public class Manager_pay_DisplayOrder_vo {
	private int rowNum;
	private int cno;
	private int rno;
	private int p_minute;
	private String f_name;
	private int p_cnt;
	private int saleprice;
	private String paytime;
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
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
	public int getP_minute() {
		return p_minute;
	}
	public void setP_minute(int p_minute) {
		this.p_minute = p_minute;
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
	public int getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public Manager_pay_DisplayOrder_vo(int rowNum, int cno, int rno, int p_minute, String f_name, int p_cnt,
			int saleprice, String paytime) {
		super();
		this.rowNum = rowNum;
		this.cno = cno;
		this.rno = rno;
		this.p_minute = p_minute;
		this.f_name = f_name;
		this.p_cnt = p_cnt;
		this.saleprice = saleprice;
		this.paytime = paytime;
	}
	public Manager_pay_DisplayOrder_vo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
