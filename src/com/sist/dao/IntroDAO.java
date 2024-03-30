package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class IntroDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url  = "jdbc:oracle:thin:@localhost:1521:XE";
	String username ="c##coin";
	String password ="coin";
	
	public int countUsingRoom() {
		int usingCnt = 0;
		String sql = "select count(pno) from pay where endtime > sysdate";
		try {
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				usingCnt = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("오류 발생:"+e.getMessage());
		}
		return usingCnt;
	}
	
	public int countWaiting() { //대기자 수 세기
		int waitCnt =0 ;
		String sql = "select count(*) from wait";
		try {
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				waitCnt = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("오류 발생:"+e.getMessage());
		}
		return waitCnt;
	}
}
