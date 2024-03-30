package managerFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager_pay_DisplayOrder_dao {
    
    public ArrayList<Manager_pay_DisplayOrder_vo> listPay(int orderNumber){
        ArrayList<Manager_pay_DisplayOrder_vo> list = new ArrayList<Manager_pay_DisplayOrder_vo>();
        String sql = "SELECT ROWNUM, p.CNO, p.RNO, p.P_MINUTE, f.F_NAME, p.P_CNT, p.SALEPRICE, p.PAYTIME "
        		+ "FROM pay p "
        		+ "LEFT JOIN foodinfo f ON p.FNO = f.FNO "
        		+ "WHERE p.ORDER_NUM = "+ orderNumber + "";        
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
                    Manager_pay_DisplayOrder_vo mpdv = new Manager_pay_DisplayOrder_vo();
                    mpdv.setRowNum(rs.getInt(1));
                    mpdv.setCno(rs.getInt(2));
                    mpdv.setRno(rs.getInt(3));
                    mpdv.setP_minute(rs.getInt(4));
                    mpdv.setF_name(rs.getString(5));
                    mpdv.setP_cnt(rs.getInt(6));
                    mpdv.setSaleprice(rs.getInt(7));
                    mpdv.setPaytime(rs.getString(8));                    
                    list.add(mpdv);
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