package managerFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager_roomInfo_dao {
    
    public ArrayList<Manager_roomInfo_vo> listRoom(int rno){
        ArrayList<Manager_roomInfo_vo> list = new ArrayList<Manager_roomInfo_vo>();
//        String sql = "SELECT "
//		        		+ "r.rno, "
//		        		+ "TO_CHAR(c.paytime, 'DD MON YY HH24:MI:SS') AS formatted_paytime, "
//		        		+ "TO_CHAR(c.endtime, 'DD MON YY HH24:MI:SS') AS formatted_endtime, "
//		        		+ "c.cno, "
//		        		+ "ROUND((c.endtime - c.paytime) * 24 * 60) AS minutes_left "
//		        	+ "FROM "
//		        		+ "room r "
//		        	+ "JOIN "
//		        		+ "pay c ON r.rno = c.rno "
//		        	+ "WHERE "
//		        		+ "r.rno = "+rno+" AND c.endtime >= SYSDATE ";
        
	      String sql = "SELECT "
	      		+ "  r.rno, "
	      		+ "  TO_CHAR(c.paytime, 'HH24:MI:SS') AS formatted_paytime, "
	      		+ "  TO_CHAR(c.endtime, 'HH24:MI:SS') AS formatted_endtime, "
	      		+ "  c.cno, "
	      		+ "  ROUND((c.endtime - SYSDATE) * 24 * 60) AS minutes_left "
	      		+ "FROM "
	      		+ "  room r "
	      		+ "JOIN "
	      		+ "  pay c ON r.rno = c.rno "
	      		+ "WHERE "
        		+ "r.rno = "+rno+" AND c.endtime >= SYSDATE ";
        
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "c##coin";
            String password = "coin";
            
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                while(rs.next()) {
                	Manager_roomInfo_vo mrv = new Manager_roomInfo_vo();
                    mrv.setRno(rs.getInt(1));
                    mrv.setPaytime(rs.getString(2));
                    mrv.setEndtime(rs.getString(3));
                    mrv.setCno(rs.getInt(4));
                    mrv.setMinutes_left(rs.getInt(5));
                    list.add(mrv);
                }
                
            } catch (Exception e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        return list;
    }
}