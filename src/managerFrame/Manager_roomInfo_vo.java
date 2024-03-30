package managerFrame;

import java.sql.Timestamp;

public class Manager_roomInfo_vo {
	private int rno;
	private String paytime;
	private String endtime;
	private int cno;
	private int minutes_left;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
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
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getMinutes_left() {
		return minutes_left;
	}
	public void setMinutes_left(int minutes_left) {
		this.minutes_left = minutes_left;
	}
	public Manager_roomInfo_vo(int rno, String paytime, String endtime, int cno, int minutes_left) {
		super();
		this.rno = rno;
		this.paytime = paytime;
		this.endtime = endtime;
		this.cno = cno;
		this.minutes_left = minutes_left;
	}
	public Manager_roomInfo_vo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Timestamp getPaytimeAsTimestamp() {
        // Assuming paytime is stored as a formatted string "DD MON YY HH24:MI:SS"
        return Timestamp.valueOf(paytime);
    }

    public Timestamp getEndtimeAsTimestamp() {
        // Assuming endtime is stored as a formatted string "DD MON YY HH24:MI:SS"
        return Timestamp.valueOf(endtime);
    }
	
}
