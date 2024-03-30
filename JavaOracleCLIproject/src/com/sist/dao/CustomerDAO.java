package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sist.vo.CustomerVO;

public class CustomerDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String username ="c##coin";
	String userpassword ="coin";
	
	
	public int insertCustomer(CustomerVO c) {
		int re = -1;
		String sql = "insert into customer values((select max(cno) + 1 from customer), 10, '"+c.getPhone()+"', '"+c.getPassword()+"')";
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
	
	public int Logincustomer(CustomerVO c) {
		int yescust = 0;
		String sql = "select cno from customer where c_phone ='"+c.getPhone()+"'and c_password ='"+c.getPassword()+"'";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, userpassword);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				 yescust = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();

		}catch (Exception e) {
			System.out.println("예외발생 : "+e.getMessage() );
		}
		return yescust;
	}
	
	
}
