package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.vo.RoomTimeVO;


public class RoomTimeDAO {
	public ArrayList<RoomTimeVO> getTimeLeft() {
		ArrayList<RoomTimeVO> list = new ArrayList<RoomTimeVO>();
		String sql = "select rno, floor((endtime - sysdate)*24*60) minleft, (endtime - sysdate)*24*60*60  - floor((endtime - sysdate)*24*60)*60 secondleft "
				+ "from pay where endtime>sysdate";
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String username ="c##coin";
			String userpassword ="coin";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,userpassword);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new RoomTimeVO(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			
			}
		} catch (Exception e) {
			
		}
		return list;
	}
}

