package managerFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Manager_c_new_dao {
	
	public void newCustomer(String cPhone, String cPassword) {
            String sql = "INSERT INTO customer " 
            		+ "VALUES ((SELECT MAX(CNO) + 1 FROM customer), '10', '"+ cPhone + "', '" + cPassword + "')";
            
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "c##coin";
            String password = "coin";
            try {
            	Class.forName(driver);
            	Connection conn = DriverManager.getConnection(url, username, password);
            	Statement stmt = conn.createStatement();
            	int re = stmt.executeUpdate(sql);
            	if (re > 0) {
                    JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다!");
                } else {
                    JOptionPane.showMessageDialog(null, "회원가입 실패!", "오류!", JOptionPane.ERROR_MESSAGE);
                }
                
                // Close the resources after use
                stmt.close();
                conn.close();   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
}
