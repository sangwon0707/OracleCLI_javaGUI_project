 package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.vo.FoodInfoVO;
import com.sist.vo.OrderNumVO;
import com.sist.vo.PayVO;

public class RoomPayDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String username ="c##coin";
	String userpassword ="coin";
	
	public int insertPay(PayVO v) {
		int re = -1; 
		String sql = "insert into pay values((select max(pno) + 1 from pay),(select max(order_num) + 1 from pay),'p1',"+v.getCno()+","+v.getRno()+",null,"+v.getP_minute()+","+v.getSaleprice()+",null,sysdate,sysdate+interval '"+v.getP_minute()+"'minute)";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,userpassword);
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외 발생: "+e.getMessage());
		}
		return re; 
	}
	
	public void insertFood(PayVO p,FoodInfoVO f,OrderNumVO o) {
		   int re = -1;
		   
		    //insert into pay values(pno,order_num,'p2',cno,null,fno,null,saleprice,p_cnt,paytime,null);
		    // 결제버튼을 누르면 pay 테이블에 위 정보가 저장되는 sql필요   
		   
		   String sql = "insert into pay values ("
		   		+ "(select max(pno) + 1 from pay), "
		   		+ o.getOno()+", "
		   		+ "'p2',"
		   		+ "100, "
		   		+ "null, "
		   		+ "(select fno from foodinfo where f_name = '"+f.getF_name()+"'), "
		   		+ "null, "
		   		+ p.getSaleprice()+","
		   		+ p.getP_cnt()+", "
		   		+ "sysdate, "
		   		+ "null)";
			
			try {
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(url,username,userpassword);
				conn.setAutoCommit(false);
				Statement stmt = conn.createStatement();
				re = stmt.executeUpdate(sql);
				stmt.close();
				conn.close();			
				
			} catch (Exception e) {
				System.out.println("예외발생 : "+e.getMessage());
			}
	    }
}

