package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderNumDAO {
//	public ArrayList<OrderNumVO> ordernumPlus(){
//		ArrayList<OrderNumVO> list = new ArrayList<OrderNumVO>();
//		//아래 문장 +로 연결할 때 문장마다 한칸씩 띄워주기
//		//아래 실행한걸 기준으로 e.eno부터 1, e.name 2 (컬럼번호)
//		String sql = "select max(order_num) from pay";
//				
//		try {
//			String driver = "oracle.jdbc.driver.OracleDriver";
//			String url = "jdbc:oracle:thin:@localhost:1521:XE";
//			String username = "c##coin";
//			String passowrd = "coin";
//			
//			Class.forName(driver);
//			Connection conn = DriverManager.getConnection(url, username, passowrd);
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			while(rs.next()) {
//				OrderNumVO o = new OrderNumVO(); 
//				o.setOno(rs.getInt(1));
//				list.add(o);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//			
//		}catch (Exception e) {
//			System.out.println("예외발생:"+e.getMessage());
//		}
//		return list;
//}
	public int orderNumber(){
		String sql = "select max(order_num) from pay";
		int order = 0;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String username = "c##coin";
			String passowrd = "coin";
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, passowrd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				order = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
			}
		catch(Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
		return order;
	}
}
