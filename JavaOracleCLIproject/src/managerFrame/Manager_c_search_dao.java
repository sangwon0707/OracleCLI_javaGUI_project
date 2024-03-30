package managerFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager_c_search_dao {
    
    public ArrayList<Manager_custInfo_vo> SearchCustomer(String phone){
        ArrayList<Manager_custInfo_vo> list = new ArrayList<Manager_custInfo_vo>();
        String sql = "SELECT c.cno, c.c_phone, c.c_password, SUM(p.p_minute) "
        		+ "FROM customer c "
        		+ "Left outer join pay p "
        		+ "ON c.cno = p.cno " 
        		+ "GROUP BY c.cno, c_phone, c_password "
        		+ "HAVING c_phone = '" + phone + "' ";
		try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "c##coin";
            String password = "coin";
            
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
                
            while(rs.next()) {
            		Manager_custInfo_vo mcv = new Manager_custInfo_vo();
                    mcv.setCno(rs.getInt(1));
                    mcv.setC_phone(rs.getString(2));
                    mcv.setC_password(rs.getString(3));
                    mcv.setC_total_minutes(rs.getInt(4));
                    list.add(mcv);
            }    
            
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        return list;
    }
}