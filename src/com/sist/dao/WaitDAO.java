package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class WaitDAO {

	public void waitinsertmethod(String phone) {
	      int re;
	      String sql = "insert into wait values((select max(wno) + 1 from wait),'"+phone+"')";
	      try {
	         String driver = "oracle.jdbc.driver.OracleDriver";
	         String url = "jdbc:oracle:thin:@localhost:1521:XE";
	         String username = "c##coin";
	         String userpassword = "coin";
	         Class.forName(driver);
	         Connection conn = DriverManager.getConnection(url, username, userpassword);
	         Statement stmt = conn.createStatement();
	         
	         String lastFour = phone.substring(7, 11);
	         
	          re = stmt.executeUpdate(sql);
	          if(re==1) { //
	             JOptionPane.showMessageDialog(null, lastFour+"님\n대기 예약이 완료되었습니다.");
	          } else {
	             JOptionPane.showMessageDialog(null, lastFour+"님\n대기 예약에 실패하였습니다.");
	          }
	         stmt.close();
	         conn.close();   
	      }catch (Exception e) {
	         System.out.println("예외발생 : "+e.getMessage() );
	      }

	   }
}
