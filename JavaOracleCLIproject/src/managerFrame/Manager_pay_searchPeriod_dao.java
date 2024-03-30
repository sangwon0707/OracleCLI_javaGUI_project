package managerFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager_pay_searchPeriod_dao {
    
    public ArrayList<Manager_pay_vo> listPayPeriod(String periodBegin, String periodEnd){
        ArrayList<Manager_pay_vo> list = new ArrayList<Manager_pay_vo>();
        String sql = "SELECT "
        	    +"p.PNO, "
        	    +"p.ORDER_NUM, "
        	    +"pt.PT_TYPE, "
        	    +"p.CNO, "
        	    +"fi.F_NAME, "
        	    +"p.P_CNT, "
        	    +"p.RNO, "
        	    +"p.P_MINUTE, "
        	    +"p.PAYTIME, "
        	    +"p.ENDTIME, "
        	    +"p.SALEPRICE "
        	+"FROM "
        	    +"pay p "
        	+"LEFT OUTER JOIN "
        	    +"customer c ON p.CNO = c.CNO "
        	+"LEFT OUTER JOIN "
        	    +"paytype pt ON p.pt_code = pt.pt_code "
        	+"LEFT OUTER JOIN "
        	    +"foodinfo fi ON p.FNO = fi.FNO "
        	+"WHERE "
        		+"p.PAYTIME BETWEEN TO_DATE('"+periodBegin+"', 'DD/MM/YY') AND TO_DATE('"+periodEnd+"', 'DD/MM/YY')"
           	+"ORDER BY "
        	    +"p.PNO DESC ";        
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
                    Manager_pay_vo mpv = new Manager_pay_vo();
                    mpv.setPno(rs.getInt(1));
                    mpv.setOrder_num(rs.getInt(2));
                    mpv.setPt_type(rs.getString(3));
                    mpv.setCno(rs.getInt(4));
                    mpv.setF_name(rs.getString(5));
                    mpv.setP_cnt(rs.getInt(6));
                    mpv.setRno(rs.getInt(7));
                    mpv.setP_minute(rs.getInt(8));
                    mpv.setPaytime(rs.getString(9));
                    mpv.setEndtime(rs.getString(10));
                    mpv.setSaleprice(rs.getInt(11));
                    list.add(mpv);
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
